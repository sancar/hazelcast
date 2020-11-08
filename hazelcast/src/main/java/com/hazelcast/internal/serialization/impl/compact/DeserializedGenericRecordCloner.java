package com.hazelcast.internal.serialization.impl.compact;

import com.hazelcast.nio.serialization.FieldType;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.HazelcastSerializationException;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DeserializedGenericRecordCloner extends AbstractGenericRecordBuilder {
    private final Map<String, Object> objects;
    private final Schema schema;
    private final Set<String> overwrittenFields = new HashSet<>();

    public DeserializedGenericRecordCloner(Schema schema, Map<String, Object> objects) {
        this.objects = objects;
        this.schema = schema;
    }

    @Nonnull
    @Override
    public GenericRecord build() {
        return new DeserializedGenericRecord(schema, objects);
    }


    protected GenericRecord.Builder write(String fieldName, Object value, FieldType fieldType) {
        checkTypeWithSchema(schema, fieldName, fieldType);
        if(overwrittenFields.contains(fieldName)) {
            throw new HazelcastSerializationException("Field can only overwritten once with `cloneWithBuilder`");
        }
        objects.put(fieldName, value);
        return this;
    }
}
