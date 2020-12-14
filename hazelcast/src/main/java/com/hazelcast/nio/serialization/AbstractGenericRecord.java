package com.hazelcast.nio.serialization;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

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
                if (!Objects.deepEquals(readAny(that, fieldName, thatFieldType), readAny(this, fieldName, thisFieldType))) {
                    return false;
                }
            } else {
                if (!Objects.equals(readAny(that, fieldName, thatFieldType), readAny(this, fieldName, thisFieldType))) {
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
                result = 31 * result + Objects.hashCode(readAny(this, fieldName, fieldType));
            }
        }
        return result;
    }

    private static int arrayHashCode(GenericRecord record, String path, FieldType type) {
        switch (type) {
            case BYTE_ARRAY:
                return Arrays.hashCode(record.readByteArray(path));
            case SHORT_ARRAY:
                return Arrays.hashCode(record.readShortArray(path));
            case INT_ARRAY:
                return Arrays.hashCode(record.readIntArray(path));
            case LONG_ARRAY:
                return Arrays.hashCode(record.readLongArray(path));
            case FLOAT_ARRAY:
                return Arrays.hashCode(record.readFloatArray(path));
            case DOUBLE_ARRAY:
                return Arrays.hashCode(record.readDoubleArray(path));
            case BOOLEAN_ARRAY:
                return Arrays.hashCode(record.readBooleanArray(path));
            case CHAR_ARRAY:
                return Arrays.hashCode(record.readCharArray(path));
            case UTF_ARRAY:
                return Arrays.hashCode(record.readUTFArray(path));
            case PORTABLE_ARRAY:
            case COMPOSED_ARRAY:
                return Arrays.hashCode(record.readGenericRecordArray(path));
            case BIG_INTEGER_ARRAY:
                return Arrays.hashCode(record.readBigDecimalArray(path));
            case BIG_DECIMAL_ARRAY:
                return Arrays.hashCode(record.readBigIntegerArray(path));
            case LOCAL_TIME_ARRAY:
                return Arrays.hashCode(record.readLocalTimeArray(path));
            case LOCAL_DATE_ARRAY:
                return Arrays.hashCode(record.readLocalDateArray(path));
            case LOCAL_DATE_TIME_ARRAY:
                return Arrays.hashCode(record.readLocalDateTimeArray(path));
            case OFFSET_DATE_TIME_ARRAY:
                return Arrays.hashCode(record.readOffsetDateTimeArray(path));
            default:
                throw new IllegalArgumentException("Unsupported type " + type);
        }
    }

    private static Object readAny(GenericRecord record, String path, FieldType type) {
        return type.getSerializedFormReader().apply(record, path);
    }

}
