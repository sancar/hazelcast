package com.hazelcast.internal.serialization.impl.compact;

import com.hazelcast.config.CompactSerializationConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.EntryProcessor;
import com.hazelcast.map.IMap;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hazelcast.nio.serialization.compact.Schema;
import domainclasses.Employee;

import java.io.IOException;
import java.util.Map;

public class XSerialization2API {


    public static void main(String[] args) throws InterruptedException {

        {
            //Employee class in our classpath.
            {
                //In order Employee be serialized via new serializer,
                // 1) it should not extend Portable,IdentifiedDataSerializable,Serializable.
                //    And user should not configure a global serializer!!!

                HazelcastInstance instance = Hazelcast.newHazelcastInstance();
                IMap<Object, Object> map = instance.getMap("map");
                map.put(1, new Employee("aName", 20));
                Employee employee = (Employee) map.get(1);
            }
            {
                // 2) Or it should be configured explicitly in config
                Config config = new Config();
                CompactSerializationConfig compactSerializationConfig = config.getSerializationConfig().getCompactSerializationConfig();
                compactSerializationConfig.register(Employee.class);

                HazelcastInstance instance = Hazelcast.newHazelcastInstance();
                IMap<Object, Object> map = instance.getMap("map");
                map.put(1, new Employee());
                Employee employee = (Employee) map.get(1);
            }

        }
        {
            //Lets say that this map has `Person` object and the object is not in our classpath
            HazelcastInstance instance = Hazelcast.newHazelcastInstance();
            IMap<Object, Object> map = instance.getMap("map");
            GenericRecord person = (GenericRecord) map.get(1);

        }
        {
            //Lets say that this map has `Person` object and the object is not in our classpath
            HazelcastInstance instance = Hazelcast.newHazelcastInstance();
            IMap<Object, Object> map = instance.getMap("map");

            SchemaBuilder schemaBuilder = new SchemaBuilder("employee");
            Schema schema = schemaBuilder.addUTFField("name").addIntField("age").build();

            GenericRecord genericRecord = GenericRecord.Builder.compact(schema)
                    .writeUTF("name", "John")
                    .writeInt("age", 20).build();
            map.put(1, genericRecord);
            GenericRecord person = (GenericRecord) map.get(1);
        }
        {
            //Lets say that this map has `Person` object and the object is not in our classpath
            HazelcastInstance instance = Hazelcast.newHazelcastInstance();
            IMap<Object, Object> map = instance.getMap("map");

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
        }
        {
            //When it needs to be interoperable between java and non-java
            Config config = new Config();
            CompactSerializationConfig compactSerializationConfig = config.getSerializationConfig().getCompactSerializationConfig();
            compactSerializationConfig.register(Employee.class);
            //it is advised to give explicit class name. Default class name includes package name
            //it is advised to give explicit serializer. This way it is simpler to match types of the fields.
            //When user does not give explicit serializer, we can try to create a reflective serializer but not straightforward
            //in most of the languages.
            // 1. C++ does not have this.
            // 2. Node js does not have different types for integer, long etc. Not clear in which type we should write a number.
            //......
            compactSerializationConfig.register(Employee.class, "employee", new CompactSerializer<Employee>() {
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
        }

        {
            //When it needs to be interoperable between java and non-java
            //And the caller does not have the class in its classpath
            //No config is necessary
            HazelcastInstance instance = Hazelcast.newHazelcastInstance();

            SchemaBuilder schemaBuilder = new SchemaBuilder("employee");
            Schema schema = schemaBuilder.addUTFField("name").addIntField("age").build();

//          Integration with enterprise might require this API.
//          To support serialization config like byte order. Unsafe factory, we may need this kind of API.
//            Compact compactSerializer = instance.getCompactSerializer();
//            GenericRecord genericRecord = compactSerializer.genericRecordBuilder("employee", schema)
            //OR
//            GenericRecord genericRecord = GenericRecord.Builder.compact(compactSerializer, "employee", schema)
            //OR
//            GenericRecord genericRecord = GenericRecord.Builder.compact(compactSerializer, "employee", schema)

            GenericRecord genericRecord = GenericRecord.Builder.compact(schema)
                    .writeUTF("name", "myName")
                    .writeInt("age", 20).build();

            IMap<Object, Object> map = instance.getMap("map");
            map.put(1, genericRecord);
            GenericRecord response = (GenericRecord) map.get(1);
        }
        {
            //Example for schema evolution
            Config config = new Config();
            CompactSerializationConfig compactSerializationConfig = config.getSerializationConfig().getCompactSerializationConfig();
            compactSerializationConfig.register(Employee.class, "employee", new CompactSerializer<Employee>() {
                @Override
                public Employee read(Schema schema, CompactReader in) throws IOException {
                    String name = in.readUTF("name");
                    int age = in.readInt("age");
                    if (schema.hasField("surname")) {
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
        }

    }
}
