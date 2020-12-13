package com.hazelcast.internal.serialization.impl.compact;

import com.hazelcast.internal.nio.BufferObjectDataInput;
import com.hazelcast.internal.nio.BufferObjectDataOutput;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.HazelcastSerializationException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class SerializingGenericRecordCloner implements GenericRecord.Builder {

    interface Writer {
        void write() throws IOException;
    }

    private final Schema schema;
    private final DefaultCompactReader genericRecord;
    private final DefaultCompactWriter compactWriter;
    private final Compact serializer;
    private final Map<String, Writer> overwrittenFields = new HashMap<>();
    private final Function<byte[], BufferObjectDataInput> bufferObjectDataInputFunc;

    public SerializingGenericRecordCloner(Compact serializer, Schema schema, DefaultCompactReader record,
                                          Function<byte[], BufferObjectDataInput> bufferObjectDataInputFunc,
                                          Supplier<BufferObjectDataOutput> bufferObjectDataOutputSupplier) {
        this.serializer = serializer;
        this.schema = schema;
        this.genericRecord = record;
        this.compactWriter = new DefaultCompactWriter(serializer, bufferObjectDataOutputSupplier.get(), (SchemaImpl) schema);
        this.bufferObjectDataInputFunc = bufferObjectDataInputFunc;
    }

    @Override
    public GenericRecord build() {
        try {
            for (FieldDescriptor field : schema.getFields()) {
                String fieldName = field.getName();
                Writer writer = overwrittenFields.get(fieldName);
                if (writer != null) {
                    writer.write();
                    continue;
                }
                switch (field.getType()) {
                    case OBJECT:
                        compactWriter.writeGenericRecord(fieldName, genericRecord.readGenericRecord(fieldName));
                        break;
                    case BYTE:
                        compactWriter.writeByte(fieldName, genericRecord.readByte(fieldName));
                        break;
                    case BOOLEAN:
                        compactWriter.writeBoolean(fieldName, genericRecord.readBoolean(fieldName));
                        break;
                    case CHAR:
                        compactWriter.writeChar(fieldName, genericRecord.readChar(fieldName));
                        break;
                    case SHORT:
                        compactWriter.writeShort(fieldName, genericRecord.readShort(fieldName));
                        break;
                    case INT:
                        compactWriter.writeInt(fieldName, genericRecord.readInt(fieldName));
                        break;
                    case LONG:
                        compactWriter.writeLong(fieldName, genericRecord.readLong(fieldName));
                        break;
                    case FLOAT:
                        compactWriter.writeFloat(fieldName, genericRecord.readFloat(fieldName));
                        break;
                    case DOUBLE:
                        compactWriter.writeDouble(fieldName, genericRecord.readDouble(fieldName));
                        break;
                    case UTF:
                        compactWriter.writeUTF(fieldName, genericRecord.readUTF(fieldName));
                        break;
                    case BIG_INTEGER:
                        compactWriter.writeBigInteger(fieldName, genericRecord.readBigInteger(fieldName));
                        break;
                    case BIG_DECIMAL:
                        compactWriter.writeBigDecimal(fieldName, genericRecord.readBigDecimal(fieldName));
                        break;
                    case LOCAL_TIME:
                        compactWriter.writeLocalTime(fieldName, genericRecord.readLocalTime(fieldName));
                        break;
                    case LOCAL_DATE:
                        compactWriter.writeLocalDate(fieldName, genericRecord.readLocalDate(fieldName));
                        break;
                    case LOCAL_DATE_TIME:
                        compactWriter.writeLocalDateTime(fieldName, genericRecord.readLocalDateTime(fieldName));
                        break;
                    case OFFSET_DATE_TIME:
                        compactWriter.writeOffsetDateTime(fieldName, genericRecord.readOffsetDateTime(fieldName));
                        break;
                    case OBJECT_ARRAY:
                        compactWriter.writeGenericRecordArray(fieldName, genericRecord.readGenericRecordArray(fieldName));
                        break;
                    case BYTE_ARRAY:
                        compactWriter.writeByteArray(fieldName, genericRecord.readByteArray(fieldName));
                        break;
                    case BOOLEAN_ARRAY:
                        compactWriter.writeBooleanArray(fieldName, genericRecord.readBooleanArray(fieldName));
                        break;
                    case CHAR_ARRAY:
                        compactWriter.writeCharArray(fieldName, genericRecord.readCharArray(fieldName));
                        break;
                    case SHORT_ARRAY:
                        compactWriter.writeShortArray(fieldName, genericRecord.readShortArray(fieldName));
                        break;
                    case INT_ARRAY:
                        compactWriter.writeIntArray(fieldName, genericRecord.readIntArray(fieldName));
                        break;
                    case LONG_ARRAY:
                        compactWriter.writeLongArray(fieldName, genericRecord.readLongArray(fieldName));
                        break;
                    case FLOAT_ARRAY:
                        compactWriter.writeFloatArray(fieldName, genericRecord.readFloatArray(fieldName));
                        break;
                    case DOUBLE_ARRAY:
                        compactWriter.writeDoubleArray(fieldName, genericRecord.readDoubleArray(fieldName));
                        break;
                    case UTF_ARRAY:
                        compactWriter.writeUTFArray(fieldName, genericRecord.readUTFArray(fieldName));
                        break;
                    case BIG_INTEGER_ARRAY:
                        compactWriter.writeBigIntegerArray(fieldName, genericRecord.readBigIntegerArray(fieldName));
                        break;
                    case BIG_DECIMAL_ARRAY:
                        compactWriter.writeBigDecimalArray(fieldName, genericRecord.readBigDecimalArray(fieldName));
                        break;
                    case LOCAL_TIME_ARRAY:
                        compactWriter.writeLocalTimeArray(fieldName, genericRecord.readLocalTimeArray(fieldName));
                        break;
                    case LOCAL_DATE_ARRAY:
                        compactWriter.writeLocalDateArray(fieldName, genericRecord.readLocalDateArray(fieldName));
                        break;
                    case LOCAL_DATE_TIME_ARRAY:
                        compactWriter.writeLocalDateTimeArray(fieldName, genericRecord.readLocalDateTimeArray(fieldName));
                        break;
                    case OFFSET_DATE_TIME_ARRAY:
                        compactWriter.writeOffsetDateTimeArray(fieldName, genericRecord.readOffsetDateTimeArray(fieldName));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + field.getType());
                }
            }
            compactWriter.end();
            byte[] bytes = compactWriter.toByteArray();
            Class associatedClass = genericRecord.getAssociatedClass();
            BufferObjectDataInput dataInput = bufferObjectDataInputFunc.apply(bytes);
            return new DefaultCompactReader(serializer, dataInput, schema, associatedClass);
        } catch (IOException e) {
            throw new HazelcastSerializationException(e);
        }
    }

    @Override
    public GenericRecord.Builder writeInt(String fieldName, int value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeInt(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeLong(String fieldName, long value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeLong(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeUTF(String fieldName, String value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeUTF(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeBoolean(String fieldName, boolean value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeBoolean(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeByte(String fieldName, byte value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeByte(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeChar(String fieldName, char value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeChar(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeDouble(String fieldName, double value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeDouble(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeFloat(String fieldName, float value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeFloat(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeShort(String fieldName, short value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeShort(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeBigInteger(String fieldName, BigInteger value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeBigInteger(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeBigDecimal(String fieldName, BigDecimal value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeBigDecimal(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalTime(String fieldName, LocalTime value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeLocalTime(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalDate(String fieldName, LocalDate value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeLocalDate(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalDateTime(String fieldName, LocalDateTime value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeLocalDateTime(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeOffsetDateTime(String fieldName, OffsetDateTime value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeOffsetDateTime(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeGenericRecord(String fieldName, GenericRecord value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeGenericRecord(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeGenericRecordArray(String fieldName, GenericRecord[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeGenericRecordArray(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeByteArray(String fieldName, byte[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeByteArray(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeBooleanArray(String fieldName, boolean[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeBooleanArray(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeCharArray(String fieldName, char[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeCharArray(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeIntArray(String fieldName, int[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeIntArray(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeLongArray(String fieldName, long[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeLongArray(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeDoubleArray(String fieldName, double[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeDoubleArray(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeFloatArray(String fieldName, float[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeFloatArray(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeShortArray(String fieldName, short[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeShortArray(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeUTFArray(String fieldName, String[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeUTFArray(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeBigIntegerArray(String fieldName, BigInteger[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeBigIntegerArray(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeBigDecimalArray(String fieldName, BigDecimal[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeBigDecimalArray(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalTimeArray(String fieldName, LocalTime[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeLocalTimeArray(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalDateArray(String fieldName, LocalDate[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeLocalDateArray(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalDateTimeArray(String fieldName, LocalDateTime[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeLocalDateTimeArray(fieldName, value));
        return this;
    }

    @Override
    public GenericRecord.Builder writeOffsetDateTimeArray(String fieldName, OffsetDateTime[] value) {
        overwrittenFields.put(fieldName, () -> compactWriter.writeOffsetDateTimeArray(fieldName, value));
        return this;
    }

}
