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

package com.hazelcast.nio.serialization.compact;

import com.hazelcast.core.ManagedContext;
import com.hazelcast.internal.nio.BufferObjectDataInput;
import com.hazelcast.internal.nio.BufferObjectDataOutput;
import com.hazelcast.internal.serialization.InternalSerializationService;
import com.hazelcast.internal.serialization.impl.InternalGenericRecord;
import com.hazelcast.internal.serialization.impl.compact.CompactGenericRecord;
import com.hazelcast.internal.serialization.impl.compact.CompactRegistry;
import com.hazelcast.internal.serialization.impl.compact.DefaultCompactWriter;
import com.hazelcast.internal.serialization.impl.compact.FieldDefinitionImpl;
import com.hazelcast.internal.serialization.impl.compact.ReflectiveCompactSerializer;
import com.hazelcast.internal.serialization.impl.compact.SchemaBuilder;
import com.hazelcast.internal.serialization.impl.compact.SchemaImpl;
import com.hazelcast.internal.serialization.impl.compact.SchemaWriter;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.AdvancedSerializer;
import com.hazelcast.nio.serialization.FieldType;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.hazelcast.internal.util.Preconditions.checkNotNull;
import static com.hazelcast.internal.util.Preconditions.checkPositive;

public class Compact implements StreamSerializer<Object>, AdvancedSerializer {

    private final Map<Class, CompactRegistry> classToRegistryMap = new ConcurrentHashMap<>();
    private final Map<String, CompactRegistry> classNameToRegistryMap = new ConcurrentHashMap<>();

    private final SchemaRegistrar schemaRegistrar = new SchemaRegistrar();
    private final ReflectiveCompactSerializer reflectiveSerializer = new ReflectiveCompactSerializer();
    private InternalSerializationService internalSerializationService;
    private ManagedContext managedContext;

    public CompactGenericRecordBuilder genericRecordBuilder(String className, Schema schema) {
        return new CompactGenericRecordBuilder(this, schema, classID,
                bytes -> this.getInternalSerializationService().createObjectDataInput(bytes),
                () -> this.getInternalSerializationService().createObjectDataOutput());
    }

    private class SchemaRegistrar {
        private final Map<Class, SchemaImpl> classToSchemaMap = new ConcurrentHashMap<>();
        private final Map<Long, SchemaImpl> schemaCache = new ConcurrentHashMap<>();

        MetaDataService metaDataService = new MetaDataService() {
            private Map<Object, byte[]> map = new ConcurrentHashMap();

            @Override
            public byte[] get(Object key) {
                return map.get(key);
            }

            @Override
            public Object put(Object key, byte[] metaData) {
                return map.put(key, metaData);
            }
        };

        private void tryRegisterSchema(SchemaImpl schema) {
            long schemaId = schema.getId();
            if (schemaCache.get(schemaId) != null) {
                return;
            }

            long id = schema.getId();
            byte[] serialized = schema.getSerialized();
            schemaCache.put(id, schema);
            metaDataService.put(id, serialized);
        }

        SchemaImpl lookupSchema(long schemaId) {
            SchemaImpl schema = schemaCache.get(schemaId);
            if (schema != null) {
                return schema;
            }
            byte[] bytes = metaDataService.get(schemaId);
            schema = (SchemaImpl) toSchema(bytes);
            schemaCache.put(schemaId, schema);
            return schema;
        }

        void registerSchema(SchemaImpl schema, Class<?> aClass) {
            long id = schema.getId();
            byte[] serialized = schema.getSerialized();
            schemaCache.put(id, schema);
            metaDataService.put(id, serialized);
            classToSchemaMap.put(aClass, schema);
        }

        private Schema toSchema(byte[] bytes) {
            ByteBuffer in = ByteBuffer.wrap(bytes);
            SchemaBuilder builder = new SchemaBuilder();
            int fieldCount = in.get();
            for (int i = 0; i < fieldCount; i++) {
                String name = readBasicString(in);
                FieldType type = FieldType.get(in.get());
                builder.addField(new FieldDefinitionImpl(name, type));
            }
            return builder.build();
        }

        public SchemaImpl lookupSchema(Class<?> aClass) {
            return classToSchemaMap.get(aClass);
        }
    }


    //TODO SANCAR do we need to depend on internalSerializationService ? Enterprise
    @Override
    public void setInternalSerializationService(InternalSerializationService internalSerializationService) {
        this.internalSerializationService = internalSerializationService;
        this.managedContext = internalSerializationService.getManagedContext();
    }

    //TODO SANCAR remove
    public InternalSerializationService getInternalSerializationService() {
        return internalSerializationService;
    }

    @Override
    public void setMetaDataService(MetaDataService metaDataService) {
        this.schemaRegistrar.metaDataService = metaDataService;
    }

    @Override
    public InternalGenericRecord readAsInternalGenericRecord(ObjectDataInput input) {
        try {
            return (InternalGenericRecord) readGenericRecord(input);
        } catch (IOException e) {
            //TODO SANCAR ??
            return null;
        }
    }

    public Compact() {
    }

    @Override
    public int getTypeId() {
        return 1;
    }

    @Override
    public void write(ObjectDataOutput out, Object o) throws IOException {
        if (!(o instanceof CompactGenericRecord)) {
            writeObject(out, o);
            return;
        }
        writeGenericRecord(out, (GenericRecord) o);

    }

    public void writeGenericRecord(ObjectDataOutput out, GenericRecord o) throws IOException {
        assert out instanceof BufferObjectDataOutput;
        CompactGenericRecord record = (CompactGenericRecord) o;
        SchemaImpl schema = (SchemaImpl) record.getSchema();
        schemaRegistrar.tryRegisterSchema(schema);
        out.writeInt(record.getClassID());
        out.writeLong(schema.getId());
        record.getIn().readTo((BufferObjectDataOutput) out);
    }

    public void writeObject(ObjectDataOutput out, Object o) throws IOException {
        SchemaImpl schema = lookupOrRegisterSchema(o);
        CompactRegistry registry = classToRegistryMap.get(o.getClass());
        out.writeInt(registry.classID);
        out.writeLong(schema.getId());
        DefaultCompactWriter writer = new DefaultCompactWriter(this, (BufferObjectDataOutput) out, schema);
        CompactSerializer compactSerializer = registry.compactSerializer;
        compactSerializer.write(writer, o);
        writer.end();
    }

    @Override
    public Object read(ObjectDataInput in) throws IOException {
        int classID = in.readInt();
        long schemaId = in.readLong();
        Schema schema = schemaRegistrar.lookupSchema(schemaId);
        BufferObjectDataInput input = (BufferObjectDataInput) in;
        CompactRegistry registry = classNameToRegistryMap.get(classID);
        if (registry == null) {
            //make sure that this is not returned to pool
            input.steal();
            return new CompactGenericRecord(this, classID, input, schema);
        } else if(registry.compactSerializer == null){
            CompactGenericRecord genericRecord = new CompactGenericRecord(this, classID, input, schema);
            return reflectiveSerializer.read(registry.clazz, schema, genericRecord);
        } else {
            CompactGenericRecord genericRecord = new CompactGenericRecord(this, classID, input, schema);
            return registry.compactSerializer.read(schema, genericRecord);
        }
    }

    public <T> T readObject(ObjectDataInput in) throws IOException {
        int classID = in.readInt();
        long schemaId = in.readLong();
        Schema schema = schemaRegistrar.lookupSchema(schemaId);
        BufferObjectDataInput input = (BufferObjectDataInput) in;
        CompactRegistry registry = getRegistry(classID);
        CompactGenericRecord genericRecord = new CompactGenericRecord(this, classID, input, schema);
        return (T) registry.compactSerializer.read(schema, genericRecord);
    }

    public GenericRecord readGenericRecord(ObjectDataInput in) throws IOException {
        int classID = in.readInt();
        long schemaId = in.readLong();
        Schema schema = schemaRegistrar.lookupSchema(schemaId);
        BufferObjectDataInput input = (BufferObjectDataInput) in;
        //make sure that this is not returned to pool
        input.steal();
        return new CompactGenericRecord(this, classID, input, schema);
    }

    public <T> T toObject(Class aClass, CompactGenericRecord genericRecord) throws IOException {
        CompactRegistry registry = getRegistry(aClass);
        T o = (T) registry.compactSerializer.read(genericRecord.getSchema(), genericRecord);
        return managedContext != null ? (T) managedContext.initialize(o) : o;
    }


    private static String readBasicString(ByteBuffer in) {
        int len = in.getInt();
        byte[] bytes = new byte[len];
        in.get(bytes, 0, len);
        return new String(bytes);
    }

    private SchemaImpl lookupOrRegisterSchema(Object o) throws IOException {
        Class<?> aClass = o.getClass();
        SchemaImpl schema = schemaRegistrar.lookupSchema(aClass);
        if (schema != null) {
            return schema;
        }

        //buid schema
        SchemaBuilder builder = new SchemaBuilder();
        SchemaWriter writer = new SchemaWriter(builder);
        CompactRegistry registry = classToRegistryMap.get(aClass);
        if(registry != null && registry.compactSerializer != null) {
            registry.compactSerializer.write(writer, o);
        } else {
            reflectiveSerializer.write(writer, o);
        }
        schema = (SchemaImpl) builder.build();

        schemaRegistrar.registerSchema(schema ,aClass);

        return schema;
    }

    public void register(Class clazz, String aliasClassName, CompactSerializer compactSerializer) {
        checkNotNull(aliasClassName, "aliasClassName");
        classToRegistryMap.put(clazz, new CompactRegistry(clazz, aliasClassName, compactSerializer));
        classNameToRegistryMap.put(aliasClassName, new CompactRegistry(clazz, aliasClassName, compactSerializer));
    }

    public void register(Class clazz, CompactSerializer compactSerializer) {
        classToRegistryMap.put(clazz, new CompactRegistry(clazz, clazz.getName(), compactSerializer));
        classNameToRegistryMap.put(clazz.getName(), new CompactRegistry(clazz, clazz.getName(), compactSerializer));
    }

    public void register(Class clazz, String aliasClassName) {
        checkNotNull(aliasClassName, "aliasClassName");
        classToRegistryMap.put(clazz, new CompactRegistry(clazz, aliasClassName, null));
        classNameToRegistryMap.put(aliasClassName, new CompactRegistry(clazz, aliasClassName, null));
    }


}
