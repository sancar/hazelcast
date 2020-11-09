package com.hazelcast.internal.serialization.impl.compact;

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
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DeserializedGenericRecord implements GenericRecord {

    public final Map<String, Object> objects;
    public final Schema schema;

    public DeserializedGenericRecord(Schema schema, Map<String, Object> objects) {
        this.schema = schema;
        this.objects = objects;
    }

    public Schema getSchema() {
        return schema;
    }

    @Nonnull
    @Override
    public Builder newBuilder() {
        return new DeserializedSchemaBoundGenericRecordBuilder(schema);
    }

    @Nonnull
    @Override
    public Builder cloneWithBuilder() {
        return new DeserializedGenericRecordCloner(schema, objects);
    }

    @Nonnull
    @Override
    public FieldType getFieldType(@Nonnull String fieldName) {
        return schema.getField(fieldName).getType();
    }

    @Override
    public boolean hasField(@Nonnull String fieldName) {
        return objects.containsKey(fieldName);
    }

    @Override
    @Nonnull
    public Set<String> getFieldNames() {
        return objects.keySet();
    }

    @Override
    public boolean readBoolean(@Nonnull String fieldName) {
        return read(fieldName, FieldType.BOOLEAN);
    }

    @Override
    public byte readByte(@Nonnull String fieldName) {
        return read(fieldName, FieldType.BYTE);
    }

    @Override
    public char readChar(@Nonnull String fieldName) {
        return read(fieldName, FieldType.CHAR);
    }

    @Override
    public double readDouble(@Nonnull String fieldName) {
        return read(fieldName, FieldType.DOUBLE);
    }

    @Override
    public float readFloat(@Nonnull String fieldName) {
        return read(fieldName, FieldType.FLOAT);
    }

    @Override
    public int readInt(@Nonnull String fieldName) {
        return read(fieldName, FieldType.INT);
    }

    @Override
    public long readLong(@Nonnull String fieldName) {
        return read(fieldName, FieldType.LONG);
    }

    @Override
    public short readShort(@Nonnull String fieldName) {
        return read(fieldName, FieldType.SHORT);
    }

    @Override
    @Nullable
    public String readUTF(@Nonnull String fieldName) {
        return read(fieldName, FieldType.UTF);
    }

    @Override
    @Nullable
    public BigInteger readBigInteger(@Nonnull String fieldName) {
        return read(fieldName, FieldType.BIG_INTEGER);
    }

    @Override
    @Nullable
    public BigDecimal readBigDecimal(@Nonnull String fieldName) {
        return read(fieldName, FieldType.BIG_DECIMAL);
    }

    @Override
    @Nullable
    public LocalTime readLocalTime(@Nonnull String fieldName) {
        return read(fieldName, FieldType.LOCAL_TIME);
    }

    @Override
    @Nullable
    public LocalDate readLocalDate(@Nonnull String fieldName) {
        return read(fieldName, FieldType.LOCAL_DATE);
    }

    @Override
    @Nullable
    public LocalDateTime readLocalDateTime(@Nonnull String fieldName) {
        return read(fieldName, FieldType.LOCAL_DATE_TIME);
    }

    @Override
    @Nullable
    public OffsetDateTime readOffsetDateTime(@Nonnull String fieldName) {
        return read(fieldName, FieldType.OFFSET_DATE_TIME);
    }

    @Nullable
    @Override
    public GenericRecord readGenericRecord(@Nonnull String fieldName) {
        return read(fieldName, FieldType.OBJECT);
    }

    @Override
    @Nullable
    public boolean[] readBooleanArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.BOOLEAN_ARRAY);
    }

    @Override
    public byte[] readByteArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.BYTE_ARRAY);
    }

    @Override
    public char[] readCharArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.CHAR_ARRAY);
    }

    @Override
    public double[] readDoubleArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.DOUBLE_ARRAY);
    }

    @Override
    public float[] readFloatArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.FLOAT_ARRAY);
    }

    @Override
    public int[] readIntArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.INT_ARRAY);
    }

    @Override
    public long[] readLongArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.LONG_ARRAY);
    }

    @Override
    public short[] readShortArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.SHORT_ARRAY);
    }

    @Override
    public String[] readUTFArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.UTF_ARRAY);
    }

    @Override
    public BigInteger[] readBigIntegerArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.BIG_INTEGER_ARRAY);
    }

    @Override
    public BigDecimal[] readBigDecimalArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.BIG_DECIMAL_ARRAY);
    }

    @Override
    public LocalTime[] readLocalTimeArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.LOCAL_TIME_ARRAY);
    }

    @Override
    public LocalDate[] readLocalDateArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.LOCAL_DATE_ARRAY);
    }

    @Override
    public LocalDateTime[] readLocalDateTimeArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.LOCAL_DATE_TIME_ARRAY);
    }

    @Override
    public OffsetDateTime[] readOffsetDateTimeArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.OFFSET_DATE_TIME_ARRAY);
    }

    @Nullable
    @Override
    public GenericRecord[] readGenericRecordArray(@Nonnull String fieldName) {
        return read(fieldName, FieldType.OBJECT_ARRAY);
    }


    private <T> T read(@Nonnull String fieldName, FieldType fieldType) {
        check(fieldName, fieldType);
        return (T) objects.get(fieldName);
    }

    private void check(@Nonnull String fieldName, FieldType fieldType) {
        FieldDescriptor fd = schema.getField(fieldName);
        if (fd == null) {
            throw new HazelcastSerializationException("Invalid field name: '" + fieldName + " for " + schema);
        }
        if (!fd.getType().equals(fieldType)) {
            throw new HazelcastSerializationException("Invalid field type: '" + fieldName + " for " + schema
                    + ", expected : " + fd.getType() + ", given : " + fieldType);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeserializedGenericRecord that = (DeserializedGenericRecord) o;
        return Objects.equals(objects, that.objects) &&
                Objects.equals(schema, that.schema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objects, schema);
    }
}
