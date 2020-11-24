package com.hazelcast.internal.serialization.impl.compact;

import com.hazelcast.nio.serialization.FieldType;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SchemaRegistrar {
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

    public SchemaRegistrar(MetaDataService metaDataService) {
        if (metaDataService != null) {
            this.metaDataService = metaDataService;
        }
    }

    void registerSchemaToLocalAndCluster(SchemaImpl schema) {
        long schemaId = schema.getSchemaId();
        if (schemaCache.get(schemaId) != null) {
            return;
        }

        long id = schema.getSchemaId();
        byte[] serialized = schema.getSerializedSchema();
        schemaCache.put(id, schema);
        metaDataService.put(id, serialized);
    }

    public SchemaImpl lookupSchema(long schemaId) {
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
        long id = schema.getSchemaId();
        byte[] serialized = schema.getSerializedSchema();
        schemaCache.put(id, schema);
        metaDataService.put(id, serialized);
        classToSchemaMap.put(aClass, schema);
    }

    private Schema toSchema(byte[] bytes) {
        ByteBuffer in = ByteBuffer.wrap(bytes);
        String className = readBasicString(in);
        SchemaBuilder builder = new SchemaBuilder(className);
        int fieldCount = in.get();
        for (int i = 0; i < fieldCount; i++) {
            String name = readBasicString(in);
            FieldType type = FieldType.get(in.get());
            builder.addField(new FieldDescriptorImpl(name, type));
        }
        return builder.build();
    }

    private static String readBasicString(ByteBuffer in) {
        int len = in.getInt();
        byte[] bytes = new byte[len];
        in.get(bytes, 0, len);
        return new String(bytes);
    }


    public SchemaImpl lookupSchema(Class<?> aClass) {
        return classToSchemaMap.get(aClass);
    }
}