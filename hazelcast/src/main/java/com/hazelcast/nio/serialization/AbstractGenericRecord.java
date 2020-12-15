package com.hazelcast.nio.serialization;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;

import static com.hazelcast.internal.serialization.impl.FieldOperations.fieldOperations;

/**
 * Implementation of GenericRecord interface to give common equals and hashcode implementation
 */
public abstract class AbstractGenericRecord implements GenericRecord {

    protected abstract Object getClassIdentifier();

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractGenericRecord)) return false;
        AbstractGenericRecord that = (AbstractGenericRecord) o;
        if (!that.getClassIdentifier().equals(getClassIdentifier())) {
            return false;
        }
        Set<String> thatFieldNames = that.getFieldNames();
        Set<String> thisFieldNames = getFieldNames();
        if (!Objects.equals(thatFieldNames, thisFieldNames)) {
            return false;
        }
        for (String fieldName : thatFieldNames) {
            FieldType thatFieldType = that.getFieldType(fieldName);
            FieldType thisFieldType = getFieldType(fieldName);
            if (!thatFieldType.equals(thisFieldType)) {
                return false;
            }
            if (thatFieldType.isArrayType()) {
                if (!Objects.deepEquals(readAny(fieldName), that.readAny(fieldName))) {
                    return false;
                }
            } else {
                if (!Objects.equals(readAny(fieldName), that.readAny(fieldName))) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        int result = Objects.hash(getClassIdentifier());
        Set<String> thisFieldNames = getFieldNames();
        for (String fieldName : thisFieldNames) {
            FieldType fieldType = getFieldType(fieldName);
            if (fieldType.isArrayType()) {
                result = 31 * result + arrayHashCode(this, fieldName, fieldType);
            } else {
                result = 31 * result + Objects.hashCode(readAny(fieldName));
            }
        }
        return result;
    }

    private static int arrayHashCode(GenericRecord record, String path, FieldType type) {
        return fieldOperations(type).getArrayHashCoder().applyAsInt(record, path);
    }

    @Override
    public final <T> T readAny(@Nonnull String fieldName) {
        FieldType type = getFieldType(fieldName);
        BiFunction<GenericRecord, String, Object> reader = fieldOperations(type).getSerializedFormReader();
        return (T) reader.apply(this, fieldName);
    }

}
