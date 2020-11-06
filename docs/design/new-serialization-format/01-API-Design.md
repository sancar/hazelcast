# New Serialization Format API

|ℹ️ Since: 4.2  Beta |
|--------------------|

# Background

This work is intended to introduce a new format which will supersede Portable. Main goals are as follows: 

- Ease of use: The format should require as little as configuration as possible. 			
- Non-Java friendly: The  format has to be easy to use also for non-Java developers.	
- Space Efficiency: The format should be more efficient than Portable. In Portable, we store also field names. We can eliminate this to provide more compact format.		
- Schema Evolution: The format should support schema evolution.	
- Querying friendly/Partial deserialization: The format should allow partial deserialization to provide optimal performance on queries.

# API Interaction 

API usage will differ from use case to use case. In this document, we will try to describe each use case and how will the 
usage will look like. Sample domain class to be used in examples:
```
package com.sample.domainclasses;

public class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Employee() {

    }
    
    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
```

1. Simplest use-case is java only and the domain class is in our class-path.
For Employee object to be serialized in the new format, one of the following two should be satisfied: 
    1. It should not implement Portable, IdentifiedDataSerializable, DataSerializable, Serializable, and user should not configure a global serializer.  This is to ensure backward compatibility. In any existing code  migrating from old version, the serialization method should not change for existing objects.
        ```
         HazelcastInstance instance = Hazelcast.newHazelcastInstance();
         IMap<Object, Object> map = instance.getMap("map");
         map.put(1, new Employee("John", 20));
         Employee employee = (Employee) map.get(1);
        ```
    2. User should register Employee class explicitly. This API will be useful when user does not have access to Employee class, but using
    it from another library.      
        ```
        Config config = new Config();
        CompactSerializerConfig compactSerializerConfig = config.getSerializationConfig().getCompactSerializerConfig();
        compactSerializerConfig.register(Employee.class);
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        IMap<Object, Object> map = instance.getMap("map");
        map.put(1, new Employee("John", 20));
        Employee employee = (Employee) map.get(1);
        ```
2. A second use case is when the domain class is not in our class path
    1. In case of read, since the user object can not be created, `GenericRecord` will be returned instead: 
        ``` 
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        IMap<Object, Object> map = instance.getMap("map");
        GenericRecord employee = (GenericRecord) map.get(1);
        int age = employee.readInt("age");
        String name = employee.readUTF("name")
        ```
       The field names are determined by the sender. If no specific schema passed, they will be same as field names of class. 
       
    2. In case of write, we will create a `GenericRecord` that represents our class. 
       Only addition to existing GenericRecord API is `GenericRecord.Builder.compact(Schema schema)` method.
        ```
       HazelcastInstance instance = Hazelcast.newHazelcastInstance();
       IMap<Object, Object> map = instance.getMap("map");

       SchemaBuilder schemaBuilder = new SchemaBuilder("employee");
       Schema schema = schemaBuilder.addUTFField("name").addIntField("age").build();

       GenericRecord genericRecord = GenericRecord.Builder.compact(schema)
               .writeUTF("name", "John")
               .writeInt("age", 20).build();
       map.put(1, genericRecord);
       GenericRecord person = (GenericRecord) map.get(1);
        ```
       This usage also a good fit for EntryProcessor use case where sender have the class but the class is not available on the remote cluster.
       ```
       map.executeOnKey(1, new EntryProcessor<Object, Object, Object>() {
           @Override
           public Object process(Map.Entry<Object, Object> entry) {
               GenericRecord record = (GenericRecord) entry.getKey();
               GenericRecord genericRecord = record.cloneWithBuilder()
                       .writeInt("age", 21).build();
               entry.setValue(genericRecord);
               return true;
           }
       });
       ```     
3. The use case when it needs to be interoperable between java and non-java will require some additional configuration.
Although it is not a must, it is advised to give alias class name. Default class name includes package name of the java class, which is not 
friendly to be used from non-java clients.
It is advised to give explicit serializer. This way it is simpler to match types of the fields.
When user does not give explicit serializer, we can try to create a reflective serializer but not straightforward 
in most of the languages.
    - C++ does not have this.
    - Node js does not have different types for integer, long etc. Not clear in which type we should write a number.


```
Config config = new Config();
CompactSerializerConfig compactSerializerConfig = config.getSerializationConfig().getCompactSerializerConfig();
compactSerializerConfig.register(Employee.class, "employee", new CompactSerializer<Employee>() {
               @Override
               public Employee read(Schema schema, CompactReader in) throws IOException {
                   String name = in.readUTF("name");
                   int age = in.readInt("age");
                   return new Employee(name, age);
               }

               @Override
               public void write(CompactWriter out, Employee object) throws IOException {
                   out.writeUTF("name", object.getName());
                   out.writeInt("age", object.getAge());
               }
           });
                  
HazelcastInstance instance = Hazelcast.newHazelcastInstance();
IMap<Object, Object> map = instance.getMap("map");
map.put(1, new Employee());
Employee employee = (Employee) map.get(1);      
```
4. This use case is when a class is evolved. Added/removed some field or a type of the field is changed.
Lets say that we have added `surname` field to our `Employee` class later. 
    1. The basic example where user does not give any serializer will work. We will be able to write any version of the Employee
        to the cluster.
        ```
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        IMap<Object, Object> map = instance.getMap("map");
        map.put(1, new Employee("John", 20, "Smith"));
       ```
       For the read, we have 3 possible states. 
            - A field `Employee` has could be removed in the new data.
            - A new field could be added, which local `Employee` class does not have.
            - A field type could change which does not fit to `Employee`. This is actually same as removing a field, and adding
            a new unrelated field.   
       ```
        Employee employee = (Employee) map.get(1);
        ```
       If the incoming data(evolved class on the cluster) have an extra field, the user's object will be created with the fields it has.
       New fields will just be ignored.  
       If the incoming data(evolved class on the cluster) have removed a field/changed the type of the field than related field
       of the `Employee` object will be leave unset. So if `Employee` class is constructed with some default values in the 
       default constructor, they will be untouched.  
    2.  Another use case is when a user configures an explicit serializer and the data evolves.  
        The user should plan ahead in this case. Any field that could be removed in the future, should be guarded with 
        `schema.hasField()` 
        ```
        Config config = new Config();
        CompactSerializationConfig compactSerializationConfig = config.getSerializationConfig().getCompactSerializationConfig();
        compactSerializationConfig.register(Employee.class, "employee", new CompactSerializer<Employee>() {
            @Override
            public Employee read(Schema CompactReader in) throws IOException {
                String name = in.readUTF("name");
                int age = in.readInt("age");
                if (schema.hasField("surname" , Type.UTF)) {
                    String surname = in.readUTF("surname");
                    return new Employee(name, age, surname);
                } else {
                    return new Employee(name, age, "NOT AVAILABLE");        
                }                      
            }
        
            @Override
            public void write(CompactWriter out, Employee object) throws IOException {
                out.writeUTF("name", object.getName());
                out.writeInt("age", object.getAge());
                out.writeUTF("surname", object.getSurname());
            }
        });
        
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        IMap<Object, Object> map = instance.getMap("map");
        map.put(1, new Employee());
        Employee employee = (Employee) map.get(1);
        ```
# API Design

In here, we will list all the new classes and methods that are necessary for the new format API. 

### CompactSerializer

```
public interface CompactSerializer<T> {
    /**
     * @param schema of the object being deserialized
     * @param in     reader to read fields of an object
     * @return the object created as a result of read method
     * @throws IOException
     */
    T read(Schema schema, CompactReader in) throws IOException;

    /**
     * @param out    CompactWriter to serialize the fields onto
     * @param object to be serialized
     * @throws IOException
     */
    void write(CompactWriter out, T object) throws IOException;
}
```

### CompactReader

Reader and Writer API's is very likely to be changed during the design. For example,methods for more types could be added.

```
/**
 * All read methods throws HazelcastSerializationException when related field is not found or there is a type mismatch
 * To avoid exceptions user can make use of `getSchema` to check a field exist. Especially useful, when class is evolved,
 * (a new field is added to/removed from the class).
 */
public interface CompactReader {

    Schema getSchema();

    byte readByte(String fieldName);

    short readShort(String fieldName);

    int readInt(String fieldName);

    long readLong(String fieldName);

    float readFloat(String fieldName);

    double readDouble(String fieldName);

    boolean readBoolean(String fieldName);

    char readChar(String fieldName);

    String readUTF(String fieldName);

    BigInteger readBigInteger(String fieldName);

    BigDecimal readBigDecimal(String fieldName);

    LocalTime readLocalTime(String fieldName);

    LocalDate readLocalDate(String fieldName);

    LocalDateTime readLocalDateTime(String fieldName);

    OffsetDateTime readOffsetDateTime(String fieldName);

    /**
     * @param <T> must be registered via{@link com.hazelcast.nio.serialization.compact.Compact#register}
     * @throws com.hazelcast.core.HazelcastException If the object is not able to be created because the related class not be
     *                                               found in the classpath
     */
    <T> T readObject(String fieldName);

    byte[] readByteArray(String fieldName);

    boolean[] readBooleanArray(String fieldName);

    char[] readCharArray(String fieldName);

    int[] readIntArray(String fieldName);

    long[] readLongArray(String fieldName);

    double[] readDoubleArray(String fieldName);

    float[] readFloatArray(String fieldName);

    short[] readShortArray(String fieldName);

    String[] readUTFArray(String fieldName);

    BigInteger[] readBigIntegerArray(String fieldName);

    BigDecimal[] readBigDecimalArray(String fieldName);

    LocalTime[] readLocalTimeArray(String fieldName);

    LocalDate[] readLocalDateArray(String fieldName);

    LocalDateTime[] readLocalDateTimeArray(String fieldName);

    OffsetDateTime[] readOffsetDateTimeArray(String fieldName);

    /**
     * @return class type of items must be registered via{@link com.hazelcast.nio.serialization.compact.Compact#register}
     * @throws com.hazelcast.core.HazelcastException If the object is not able to be created because the related class not be
     *                                               found in the classpath
     */
    Object[] readObjectArray(String fieldName);

    /**
     * @param <T> must be registered via{@link com.hazelcast.nio.serialization.compact.Compact#register}
     * @throws com.hazelcast.core.HazelcastException If the object is not able to be created because the related class not be
     *                                               found in the classpath
     */
    <T> List<T> readObjectList(String fieldName);

}
```

### CompactWriter

```
public interface CompactWriter {

    void writeInt(String fieldName, int value);

    void writeLong(String fieldName, long value);

    void writeUTF(String fieldName, String value);

    void writeBoolean(String fieldName, boolean value);

    void writeByte(String fieldName, byte value);

    void writeChar(String fieldName, char value);

    void writeDouble(String fieldName, double value);

    void writeFloat(String fieldName, float value);

    void writeShort(String fieldName, short value);
    
    <T> void writeObject(String fieldName, T value);

    void writeBigInteger(String fieldName, BigInteger value);

    void writeBigDecimal(String fieldName, BigDecimal value);

    void writeLocalTime(String fieldName, LocalTime value);

    void writeLocalDate(String fieldName, LocalDate value);

    void writeLocalDateTime(String fieldName, LocalDateTime value);

    void writeOffsetDateTime(String fieldName, OffsetDateTime value);

    void writeByteArray(String fieldName, byte[] value);

    void writeBooleanArray(String fieldName, boolean[] booleans);

    void writeCharArray(String fieldName, char[] value);

    void writeIntArray(String fieldName, int[] value);

    void writeLongArray(String fieldName, long[] value);

    void writeDoubleArray(String fieldName, double[] value);

    void writeFloatArray(String fieldName, float[] value);

    void writeShortArray(String fieldName, short[] value);

    void writeUTFArray(String fieldName, String[] value);

    void writeBigIntegerArray(String fieldName, BigInteger[] values);

    void writeBigDecimalArray(String fieldName, BigDecimal[] values);

    void writeLocalTimeArray(String fieldName, LocalTime[] values);

    void writeLocalDateArray(String fieldName, LocalDate[] values);

    void writeLocalDateTimeArray(String fieldName, LocalDateTime[] values);

    void writeOffsetDateTimeArray(String fieldName, OffsetDateTime[] values);

    <T> void writeObjectArray(String fieldName, T[] value);

    <T> void writeObjectList(String fieldName, List<T> list);

}
```
### GenericRecord.Builder.compact

A single method will be added to existing GenericRecord.Builder class 

```
@Beta
public interface GenericRecord {

    //........   
    interface Builder {
        //........    
    
        /**
         * @return a new constructed GenericRecord to be serialized in new format
         */
        @Nonnull
        static Builder compact(Schema schema) {
            //......
            return compactBuilder;
        }

    }
}

```

### Schema 

This is the equivalent of  `com.hazelcast.nio.serialization.ClassDefinition` for new format. We can not use ClassDefinition
because it has specific methods which are not applicable to new format like: getFactoryId, getClassId, getVersion etc.

```
/**
 * Represents the schema of a class.
 * Consists of field definitions and the class name.
 */
public interface Schema {

    /**
     * The class name provided when building a schema
     * In java, when an alias is not configured explicitly, this falls back to full class name including the path.
     * Otherwise, it returns configured `alias`
     * @return name of the class
     */
    String getClassName();

    Collection<FieldDefinition> getFields();

    int getFieldCount();

    FieldDefinition getField(String fieldName);

    boolean hasField(String fieldName);

}
``` 

### SchemaBuilder

```
public class SchemaBuilder {

    public SchemaBuilder(String className);

    public SchemaBuilder addBooleanField(String fieldName) {
        addField(new FieldDefinitionImpl(fieldName, FieldType.BOOLEAN));
        return this;
    }

    public SchemaBuilder addByteField(String fieldName) {
        addField(new FieldDefinitionImpl(fieldName, FieldType.BYTE));
        return this;
    }

//...........

    public SchemaBuilder addBooleanArrayField(String fieldName) {
        addField(new FieldDefinitionImpl(fieldName, FieldType.BOOLEAN_ARRAY));
        return this;
    }


    public SchemaBuilder addByteArrayField(String fieldName) {
        addField(new FieldDefinitionImpl(fieldName, FieldType.BYTE_ARRAY));
        return this;
    }

  
    public SchemaBuilder addObjectField(String fieldName) {
        addField(new FieldDefinitionImpl(fieldName, FieldType.PORTABLE));
        return this;
    }

    public SchemaBuilder addObjectArrayField(String fieldName) {
        addField(new FieldDefinitionImpl(fieldName, FieldType.PORTABLE_ARRAY));
        return this;
    }

    public Schema build() {
        //.......
        return schema;
    }
```

### FieldDescription and FieldType

We can not reuse FieldDefinition of Portable because of some Portable specific methods like: getFactoryId, getClassId, getVersion etc..

We will reuse FieldType because it is exposed from `GenericRecord.getFieldType` as a common type descriptor for all formats
that implements `GenericRecord` . We are reusing `FieldType` because we want to make the return value of
`ClassDefinition.getField` consistent with `GenericRecord.getFieldType` . 
TODO: re-evaluate this decision. May be we should not care about Portable(ClassDefinition API) anymore since it will be deprecated.

```
/**
 * Represents the name, type of a field in compact serialization
 */
public interface FieldDescription {

    FieldType getType();

    String getName();

}


public enum FieldType {
    PORTABLE(0, false, MAX_VALUE), //this value has no meaning for new format. It will never be used for new format.
    BYTE(1, true, BYTE_SIZE_IN_BYTES),
    BOOLEAN(2, true, BOOLEAN_SIZE_IN_BYTES),
......
    OBJECT(32, false, MAX_VALUE),         //two additional enums to replace PORTABLE and PORTABLE_ARRAY in the new format.
    OBJECT_ARRAY(33, false, MAX_VALUE);   //similarly these two have no meaning for PORTABLE. 
}
```


### CompactSerializerConfig

#### Programmatic
The CompactSerializerConfig will be accessed from SerializationConfig as follows o both client and member API.
```
//get method
CompactSerializerConfig compactSerializerConfig = config.getSerializationConfig().getCompactSerializerConfig();
//set method
config.getSerializationConfig().setCompactSerializerConfig(...);
```

```
public class CompactSerializerConfig {
    /**
     * Register class to be serialized via compact serializer.
     * Overrides Portable,Identified,Java Serializable or GlobalSerializer
     * <p>
     * class name is determined automatically from clazz. It is full path including package by default
     * fields are determined automatically from class via reflection
     *
     * @param clazz Class to be serialized via compact serializer
     */
    public <T> void register(Class<T> clazz) {
    }

    /**
     * Register class to be serialized via compact serializer.
     * Overrides Portable,Identified,Java Serializable or GlobalSerializer
     * <p>
     * fields are determined automatically from class via reflection
     *
     * @param clazz Class to be serialized via compact serializer
     */
    public <T> void register(Class<T> clazz, String alias) {
    }

    /**
     * Register class to be serialized via compact serializer.
     * Overrides Portable,Identified,Java Serializable or GlobalSerializer
     *
     * @param clazz Class to be serialized via compact serializer
     */
    public <T> void register(Class<T> clazz, String alias, CompactSerializer<T> explicitSerializer) {
    }
}
```

#### XML

TODO: Should user be able to define schema in config as an alternative to a serializer class ?

```
 <serialization>
    <compact-serializer>
        <classes>
            <class name="com.sample.domainClass.Employee"></class>
            <class name="com.sample.domainClass.Person">
                <alias>person</alias>
            </class>
            <class name="domainClass.Intern">
                <alias>employee</alias>
                <serializer>com.sample.serializers.InternSerializer</serializer>
            </class>
        </classes>
    </compact-serializer>        
</serialization>
```

Note that com.sample.serializers.InternSerializer should be instance of `CompactSerializer`

#### YAML

```
serialization:
    compact-serializer:
      classes:
        com.sample.domainClass.Employee:
        com.sample.domainClass.Person:
          alias: person
        com.sample.domainClass.Intern:
          alias: employee
          serializer: com.sample.serializers.InternSerializer
```
Note that com.sample.serializers.InternSerializer should be instance of `CompactSerializer`