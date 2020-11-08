package com.hazelcast.internal.serialization.impl.compact;

import com.hazelcast.internal.serialization.impl.compact.FieldDescriptor;
import com.hazelcast.internal.serialization.impl.compact.Schema;
import com.hazelcast.nio.serialization.FieldType;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.HazelcastSerializationException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;

abstract class AbstractGenericRecordBuilder implements GenericRecord.Builder {

    @Override
    public GenericRecord.Builder writeInt(@Nonnull String fieldName, int value) {
        return write(fieldName, value, FieldType.INT);
    }

    @Override
    public GenericRecord.Builder writeLong(@Nonnull String fieldName, long value) {
        return write(fieldName, value, FieldType.LONG);
    }

    @Override
    public GenericRecord.Builder writeUTF(@Nonnull String fieldName, String value) {
        return write(fieldName, value, FieldType.UTF);
    }

    @Override
    public GenericRecord.Builder writeBoolean(@Nonnull String fieldName, boolean value) {
        return write(fieldName, value, FieldType.BOOLEAN);
    }

    @Override
    public GenericRecord.Builder writeByte(@Nonnull String fieldName, byte value) {
        return write(fieldName, value, FieldType.BYTE);
    }

    @Override
    public GenericRecord.Builder writeChar(@Nonnull String fieldName, char value) {
        return write(fieldName, value, FieldType.CHAR);
    }

    @Override
    public GenericRecord.Builder writeDouble(@Nonnull String fieldName, double value) {
        return write(fieldName, value, FieldType.DOUBLE);
    }

    @Override
    public GenericRecord.Builder writeFloat(@Nonnull String fieldName, float value) {
        return write(fieldName, value, FieldType.FLOAT);
    }

    @Override
    public GenericRecord.Builder writeShort(@Nonnull String fieldName, short value) {
        return write(fieldName, value, FieldType.SHORT);
    }

    @Override
    public GenericRecord.Builder writeGenericRecord(@Nonnull String fieldName, @Nullable GenericRecord value) {
        return write(fieldName, value, FieldType.PORTABLE);
    }

    @Override
    public GenericRecord.Builder writeBigInteger(@Nonnull String fieldName, @Nullable BigInteger value) {
        return write(fieldName, value, FieldType.BIG_INTEGER);
    }

    @Override
    public GenericRecord.Builder writeBigDecimal(@Nonnull String fieldName, @Nullable BigDecimal value) {
        return write(fieldName, value, FieldType.BIG_DECIMAL);
    }

    @Override
    public GenericRecord.Builder writeLocalTime(@Nonnull String fieldName, @Nullable LocalTime value) {
        return write(fieldName, value, FieldType.LOCAL_TIME);
    }

    @Override
    public GenericRecord.Builder writeLocalDate(@Nonnull String fieldName, @Nullable LocalDate value) {
        return write(fieldName, value, FieldType.LOCAL_DATE);
    }

    @Override
    public GenericRecord.Builder writeLocalDateTime(@Nonnull String fieldName, @Nullable LocalDateTime value) {
        return write(fieldName, value, FieldType.LOCAL_DATE_TIME);
    }

    @Override
    public GenericRecord.Builder writeOffsetDateTime(@Nonnull String fieldName, @Nullable OffsetDateTime value) {
        return write(fieldName, value, FieldType.OFFSET_DATE_TIME);
    }

    @Override
    public GenericRecord.Builder writeGenericRecordArray(@Nonnull String fieldName, @Nullable GenericRecord[] value) {
        return write(fieldName, value, FieldType.OBJECT_ARRAY);
    }

    @Override
    public GenericRecord.Builder writeByteArray(@Nonnull String fieldName, byte[] value) {
        return write(fieldName, value, FieldType.BYTE_ARRAY);
    }

    @Override
    public GenericRecord.Builder writeBooleanArray(@Nonnull String fieldName, boolean[] value) {
        return write(fieldName, value, FieldType.BOOLEAN_ARRAY);
    }

    @Override
    public GenericRecord.Builder writeCharArray(@Nonnull String fieldName, char[] value) {
        return write(fieldName, value, FieldType.CHAR_ARRAY);
    }

    @Override
    public GenericRecord.Builder writeIntArray(@Nonnull String fieldName, int[] value) {
        return write(fieldName, value, FieldType.INT_ARRAY);
    }

    @Override
    public GenericRecord.Builder writeLongArray(@Nonnull String fieldName, long[] value) {
        return write(fieldName, value, FieldType.LONG_ARRAY);
    }

    @Override
    public GenericRecord.Builder writeDoubleArray(@Nonnull String fieldName, double[] value) {
        return write(fieldName, value, FieldType.DOUBLE_ARRAY);
    }

    @Override
    public GenericRecord.Builder writeFloatArray(@Nonnull String fieldName, float[] value) {
        return write(fieldName, value, FieldType.FLOAT_ARRAY);
    }

    @Override
    public GenericRecord.Builder writeShortArray(@Nonnull String fieldName, short[] value) {
        return write(fieldName, value, FieldType.SHORT_ARRAY);
    }

    @Override
    public GenericRecord.Builder writeUTFArray(@Nonnull String fieldName, String[] value) {
        return write(fieldName, value, FieldType.UTF_ARRAY);
    }

    @Override
    public GenericRecord.Builder writeBigIntegerArray(String fieldName, BigInteger[] value) {
        return write(fieldName, value, FieldType.BIG_INTEGER_ARRAY);
    }

    @Override
    public GenericRecord.Builder writeBigDecimalArray(String fieldName, BigDecimal[] value) {
        return write(fieldName, value, FieldType.BIG_DECIMAL_ARRAY);
    }

    @Override
    public GenericRecord.Builder writeLocalTimeArray(String fieldName, LocalTime[] value) {
        return write(fieldName, value, FieldType.LOCAL_TIME_ARRAY);
    }

    @Override
    public GenericRecord.Builder writeLocalDateArray(String fieldName, LocalDate[] value) {
        return write(fieldName, value, FieldType.LOCAL_DATE_ARRAY);
    }

    @Override
    public GenericRecord.Builder writeLocalDateTimeArray(String fieldName, LocalDateTime[] value) {
        return write(fieldName, value, FieldType.LOCAL_DATE_TIME_ARRAY);
    }

    @Override
    public GenericRecord.Builder writeOffsetDateTimeArray(String fieldName, OffsetDateTime[] value) {
        return write(fieldName, value, FieldType.OFFSET_DATE_TIME_ARRAY);
    }

    protected abstract GenericRecord.Builder write(String fieldName, Object value, FieldType fieldType);

    static void checkTypeWithSchema(Schema schema, @Nonnull String fieldName, FieldType fieldType) {
        FieldDescriptor fd = schema.getField(fieldName);
        if (fd == null) {
            throw new HazelcastSerializationException("Invalid field name: '" + fieldName + "' for " + schema);
        }
        if (!fd.getType().equals(fieldType)) {
            throw new HazelcastSerializationException("Invalid field type: '" + fieldName
                    + "' for " + schema + ", expected : " + fd.getType() + ", given : " + fieldType);
        }
    }

}
