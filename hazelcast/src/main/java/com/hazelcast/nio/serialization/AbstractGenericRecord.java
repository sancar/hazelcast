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
            case OBJECT_ARRAY:
                return Arrays.hashCode(record.readGenericRecordArray(path));
            case BIG_INTEGER_ARRAY:
                return Arrays.hashCode(record.readBigDecimalArray(path));
            case BIG_DECIMAL_ARRAY:
                return Arrays.hashCode(record.readBigDecimalArray(path));
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
        switch (type) {
            case BYTE:
                return record.readByte(path);
            case BYTE_ARRAY:
                return record.readByteArray(path);
            case SHORT:
                return record.readShort(path);
            case SHORT_ARRAY:
                return record.readShortArray(path);
            case INT:
                return record.readInt(path);
            case INT_ARRAY:
                return record.readIntArray(path);
            case LONG:
                return record.readLong(path);
            case LONG_ARRAY:
                return record.readLongArray(path);
            case FLOAT:
                return record.readFloat(path);
            case FLOAT_ARRAY:
                return record.readFloatArray(path);
            case DOUBLE:
                return record.readDouble(path);
            case DOUBLE_ARRAY:
                return record.readDoubleArray(path);
            case BOOLEAN:
                return record.readBoolean(path);
            case BOOLEAN_ARRAY:
                return record.readBooleanArray(path);
            case CHAR:
                return record.readChar(path);
            case CHAR_ARRAY:
                return record.readCharArray(path);
            case UTF:
                return record.readUTF(path);
            case UTF_ARRAY:
                return record.readUTFArray(path);
            case PORTABLE:
            case OBJECT:
                return record.readGenericRecord(path);
            case PORTABLE_ARRAY:
            case OBJECT_ARRAY:
                return record.readGenericRecordArray(path);
            case BIG_INTEGER:
                return record.readBigInteger(path);
            case BIG_INTEGER_ARRAY:
                return record.readBigIntegerArray(path);
            case BIG_DECIMAL:
                return record.readBigDecimal(path);
            case BIG_DECIMAL_ARRAY:
                return record.readBigDecimalArray(path);
            case LOCAL_TIME:
                return record.readLocalTime(path);
            case LOCAL_TIME_ARRAY:
                return record.readLocalTimeArray(path);
            case LOCAL_DATE:
                return record.readLocalDate(path);
            case LOCAL_DATE_ARRAY:
                return record.readLocalDateArray(path);
            case LOCAL_DATE_TIME:
                return record.readLocalDateTime(path);
            case LOCAL_DATE_TIME_ARRAY:
                return record.readLocalDateTimeArray(path);
            case OFFSET_DATE_TIME:
                return record.readOffsetDateTime(path);
            case OFFSET_DATE_TIME_ARRAY:
                return record.readOffsetDateTimeArray(path);
            default:
                throw new IllegalArgumentException("Unsupported type " + type);
        }
    }

}
