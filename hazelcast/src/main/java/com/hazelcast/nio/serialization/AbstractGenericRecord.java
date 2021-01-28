package com.hazelcast.nio.serialization;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Set;

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
        Object classIdentifier = that.getClassIdentifier();
        Object thisClassIdentifier = getClassIdentifier();
        if (!classIdentifier.equals(thisClassIdentifier)) {
            that.getClassIdentifier();
            getClassIdentifier();
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
            Object thisField = readAny(fieldName);
            Object thatField = that.readAny(fieldName);
            if (thatFieldType.isArrayType()) {
                if (!Objects.deepEquals(thisField, thatField)) {
                    Objects.deepEquals(thisField, thatField);
                    that.readAny(fieldName);
                    return false;
                }
            } else {
                if (!Objects.equals(readAny(fieldName), thatField)) {
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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClassIdentifier().toString());
        str.append("\n");
        Set<String> thisFieldNames = getFieldNames();
        for (String fieldName : thisFieldNames) {
            str.append("fieldName ").append(fieldName).append(" : ").append(readAny(fieldName).toString()).append("\n");
        }
        return str.toString();
    }

    private static int arrayHashCode(GenericRecord record, String path, FieldType type) {
        return fieldOperations(type).hashCodeArray(record, path);
    }

    @Override
    public final <T> T readAny(@Nonnull String fieldName) {
        FieldType type = getFieldType(fieldName);
        return (T) fieldOperations(type).readFieldInSerializedForm(this, fieldName);
    }

}
