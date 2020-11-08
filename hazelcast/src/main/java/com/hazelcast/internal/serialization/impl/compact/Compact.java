/*
 * Copyright (c) 2008-2020, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.internal.serialization.impl.compact;

import com.hazelcast.config.CompactSerializationConfig;
import com.hazelcast.core.ManagedContext;
import com.hazelcast.internal.nio.BufferObjectDataInput;
import com.hazelcast.internal.nio.BufferObjectDataOutput;
import com.hazelcast.internal.nio.ClassLoaderUtil;
import com.hazelcast.internal.serialization.InternalSerializationService;
import com.hazelcast.internal.serialization.impl.InternalGenericRecord;
import com.hazelcast.internal.util.TriTuple;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.AdvancedSerializer;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.HazelcastSerializationException;
import com.hazelcast.nio.serialization.StreamSerializer;
import com.hazelcast.nio.serialization.compact.CompactSerializer;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.hazelcast.internal.serialization.impl.SerializationConstants.COMPACT_SERIALIZER;

public class Compact implements StreamSerializer<Object>, AdvancedSerializer {

    private final Map<Class, ConfigurationRegistry> classToRegistryMap = new ConcurrentHashMap<>();
    private final Map<String, ConfigurationRegistry> classNameToRegistryMap = new ConcurrentHashMap<>();

    private final SchemaRegistrar schemaRegistrar = new SchemaRegistrar();
    private final ReflectiveCompactSerializer reflectiveSerializer = new ReflectiveCompactSerializer();
    private final ManagedContext managedContext;
    //TODO sancar cleanup by solving enterprise integration better. We should not need all the serialization servie
    private final InternalSerializationService internalSerializationService;

    public Compact(CompactSerializationConfig compactSerializationConfig,
                   InternalSerializationService internalSerializationService,
                   ManagedContext managedContext) {
        this.managedContext = managedContext;
        this.internalSerializationService = internalSerializationService;
        Map<String, TriTuple<Class, String, CompactSerializer>> registries = compactSerializationConfig.getRegistries();
        for (Map.Entry<String, TriTuple<Class, String, CompactSerializer>> entry : registries.entrySet()) {
            String aliasClassName = entry.getKey();
            InternalCompactSerializer compactSerializer = entry.getValue().element3 == null ? reflectiveSerializer : null;
            Class clazz = entry.getValue().element1;
            classToRegistryMap.put(clazz, new ConfigurationRegistry(clazz, aliasClassName, compactSerializer));
            classNameToRegistryMap.put(aliasClassName, new ConfigurationRegistry(clazz, aliasClassName, compactSerializer));
        }
    }

    public InternalSerializationService getInternalSerializationService() {
        return internalSerializationService;
    }

    @Override
    public void setMetaDataService(MetaDataService metaDataService) {
        this.schemaRegistrar.metaDataService = metaDataService;
    }

    @Override
    public InternalGenericRecord readAsInternalGenericRecord(ObjectDataInput input) throws IOException {
        return (InternalGenericRecord) readGenericRecord(input);
    }

    @Override
    public int getTypeId() {
        return COMPACT_SERIALIZER;
    }

    //========================== WRITE =============================//
    @Override
    public void write(ObjectDataOutput out, Object o) throws IOException {
        assert out instanceof BufferObjectDataOutput;
        BufferObjectDataOutput bufferObjectDataOutput = (BufferObjectDataOutput) out;
        if (o instanceof GenericRecord) {
            writeGenericRecord(bufferObjectDataOutput, (GenericRecord) o);
        }
        writeObject(bufferObjectDataOutput, o);

    }

    void writeGenericRecord(BufferObjectDataOutput out, GenericRecord o) throws IOException {
        if (o instanceof SerializedGenericRecord) {
            writeSerializedGenericRecord(out, (SerializedGenericRecord) o);
        } else {
            writeDeserializedGenericRecord(out, (DeserializedGenericRecord) o);
        }
    }

    void writeSerializedGenericRecord(BufferObjectDataOutput out,
                                      SerializedGenericRecord record) throws IOException {
        SchemaImpl schema = (SchemaImpl) record.getSchema();
        schemaRegistrar.registerSchemaToLocalAndCluster(schema);
        out.writeLong(schema.getSchemaId());
        record.getIn().readTo(out);
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:CyclomaticComplexity"})
    void writeDeserializedGenericRecord(BufferObjectDataOutput output,
                                        DeserializedGenericRecord record) throws IOException {
        SchemaImpl schema = (SchemaImpl) record.getSchema();
        schemaRegistrar.registerSchemaToLocalAndCluster(schema);
        output.writeLong(schema.getSchemaId());

        DefaultCompactWriter writer = new DefaultCompactWriter(this, output, schema);
        Collection<FieldDescriptor> fields = schema.getFields();
        for (FieldDescriptor fieldDescriptor : fields) {
            String fieldName = fieldDescriptor.getName();
            switch (fieldDescriptor.getType()) {
                case OBJECT:
                    writer.writeGenericRecord(fieldName, record.readGenericRecord(fieldName));
                    break;
                case BYTE:
                    writer.writeByte(fieldName, record.readByte(fieldName));
                    break;
                case BOOLEAN:
                    writer.writeBoolean(fieldName, record.readBoolean(fieldName));
                    break;
                case CHAR:
                    writer.writeChar(fieldName, record.readChar(fieldName));
                    break;
                case SHORT:
                    writer.writeShort(fieldName, record.readShort(fieldName));
                    break;
                case INT:
                    writer.writeInt(fieldName, record.readInt(fieldName));
                    break;
                case LONG:
                    writer.writeLong(fieldName, record.readLong(fieldName));
                    break;
                case FLOAT:
                    writer.writeFloat(fieldName, record.readFloat(fieldName));
                    break;
                case DOUBLE:
                    writer.writeDouble(fieldName, record.readDouble(fieldName));
                    break;
                case UTF:
                    writer.writeUTF(fieldName, record.readUTF(fieldName));
                    break;
                case BIG_INTEGER:
                    writer.writeBigInteger(fieldName, record.readBigInteger(fieldName));
                    break;
                case BIG_DECIMAL:
                    writer.writeBigDecimal(fieldName, record.readBigDecimal(fieldName));
                    break;
                case LOCAL_TIME:
                    writer.writeLocalTime(fieldName, record.readLocalTime(fieldName));
                    break;
                case LOCAL_DATE:
                    writer.writeLocalDate(fieldName, record.readLocalDate(fieldName));
                    break;
                case LOCAL_DATE_TIME:
                    writer.writeLocalDateTime(fieldName, record.readLocalDateTime(fieldName));
                    break;
                case OFFSET_DATE_TIME:
                    writer.writeOffsetDateTime(fieldName, record.readOffsetDateTime(fieldName));
                    break;
                case OBJECT_ARRAY:
                    writer.writeGenericRecordArray(fieldName, record.readGenericRecordArray(fieldName));
                    break;
                case BYTE_ARRAY:
                    writer.writeByteArray(fieldName, record.readByteArray(fieldName));
                    break;
                case BOOLEAN_ARRAY:
                    writer.writeBooleanArray(fieldName, record.readBooleanArray(fieldName));
                    break;
                case CHAR_ARRAY:
                    writer.writeCharArray(fieldName, record.readCharArray(fieldName));
                    break;
                case SHORT_ARRAY:
                    writer.writeShortArray(fieldName, record.readShortArray(fieldName));
                    break;
                case INT_ARRAY:
                    writer.writeIntArray(fieldName, record.readIntArray(fieldName));
                    break;
                case LONG_ARRAY:
                    writer.writeLongArray(fieldName, record.readLongArray(fieldName));
                    break;
                case FLOAT_ARRAY:
                    writer.writeFloatArray(fieldName, record.readFloatArray(fieldName));
                    break;
                case DOUBLE_ARRAY:
                    writer.writeDoubleArray(fieldName, record.readDoubleArray(fieldName));
                    break;
                case UTF_ARRAY:
                    writer.writeUTFArray(fieldName, record.readUTFArray(fieldName));
                    break;
                case BIG_INTEGER_ARRAY:
                    writer.writeBigIntegerArray(fieldName, record.readBigIntegerArray(fieldName));
                    break;
                case BIG_DECIMAL_ARRAY:
                    writer.writeBigDecimalArray(fieldName, record.readBigDecimalArray(fieldName));
                    break;
                case LOCAL_TIME_ARRAY:
                    writer.writeLocalTimeArray(fieldName, record.readLocalTimeArray(fieldName));
                    break;
                case LOCAL_DATE_ARRAY:
                    writer.writeLocalDateArray(fieldName, record.readLocalDateArray(fieldName));
                    break;
                case LOCAL_DATE_TIME_ARRAY:
                    writer.writeLocalDateTimeArray(fieldName, record.readLocalDateTimeArray(fieldName));
                    break;
                case OFFSET_DATE_TIME_ARRAY:
                    writer.writeOffsetDateTimeArray(fieldName, record.readOffsetDateTimeArray(fieldName));
                    break;
                default:
                    throw new IllegalStateException("Unexpected field type: " + fieldDescriptor);
            }
        }
        writer.end();
    }

    public void writeObject(ObjectDataOutput out, Object o) throws IOException {
        Class<?> aClass = o.getClass();
        ConfigurationRegistry registry = getOrCreateRegistry(aClass);

        SchemaImpl schema = schemaRegistrar.lookupSchema(aClass);
        if (schema == null) {
            SchemaBuilder builder = new SchemaBuilder(registry.aliasClassName);
            SchemaWriter writer = new SchemaWriter(builder);
            registry.compactSerializer.write(writer, o);
            schema = (SchemaImpl) builder.build();
            schemaRegistrar.registerSchema(schema, aClass);
        }
        out.writeLong(schema.getSchemaId());
        DefaultCompactWriter writer = new DefaultCompactWriter(this, (BufferObjectDataOutput) out, schema);
        reflectiveSerializer.write(writer, o);
        writer.end();
    }


    //========================== READ =============================//

    @Override
    public Object read(ObjectDataInput in) throws IOException {
        BufferObjectDataInput input = (BufferObjectDataInput) in;
        long schemaId = input.readLong();
        Schema schema = schemaRegistrar.lookupSchema(schemaId);
        ConfigurationRegistry registry = getOrCreateRegistry(schema.getClassName());

        if (registry == null) {
            //we have tried to load class via class loader, it did not work. We are returning a GenericRecord.
            input.steal();
            return new SerializedGenericRecord(this, input, schema, null);
        }

        SerializedGenericRecord genericRecord = new SerializedGenericRecord(this, input, schema, registry.clazz);
        Object object = registry.compactSerializer.read(genericRecord);
        return managedContext != null ? managedContext.initialize(object) : object;

    }

    public <T> T readObject(ObjectDataInput in) throws IOException {
        BufferObjectDataInput input = (BufferObjectDataInput) in;
        long schemaId = input.readLong();
        Schema schema = schemaRegistrar.lookupSchema(schemaId);
        ConfigurationRegistry registry = getOrCreateRegistry(schema.getClassName());

        if (registry == null) {
            throw new HazelcastSerializationException("The class should be in the classpath to be read via readObject* methods." +
                    "Associated schema for the data : " + schema);
        }

        SerializedGenericRecord genericRecord = new SerializedGenericRecord(this, input, schema, registry.clazz);
        Object object = registry.compactSerializer.read(genericRecord);
        return managedContext != null ? (T) managedContext.initialize(object) : (T) object;
    }

    private ConfigurationRegistry getOrCreateRegistry(Class clazz) {
        return classToRegistryMap.computeIfAbsent(clazz,
                aClass -> new ConfigurationRegistry(aClass, aClass.getName(), reflectiveSerializer));
    }

    private ConfigurationRegistry getOrCreateRegistry(String className) {
        return classNameToRegistryMap.computeIfAbsent(className, s -> {
            try {
                Class<?> clazz = ClassLoaderUtil.loadClass(internalSerializationService.getClassLoader(), className);
                return getOrCreateRegistry(clazz);
            } catch (ClassNotFoundException e) {
                return null;
            }
        });
    }

    public GenericRecord readGenericRecord(ObjectDataInput in) throws IOException {
        long schemaId = in.readLong();
        Schema schema = schemaRegistrar.lookupSchema(schemaId);
        BufferObjectDataInput input = (BufferObjectDataInput) in;
        //make sure that this is not returned to pool
        input.steal();
        return new SerializedGenericRecord(this, input, schema, null);
    }
}
