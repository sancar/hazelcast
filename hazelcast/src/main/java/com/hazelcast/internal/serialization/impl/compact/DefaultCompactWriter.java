/*
 * Copyright (c) 2008-2021, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.internal.serialization.impl.compact;

import com.hazelcast.internal.nio.BufferObjectDataOutput;
import com.hazelcast.internal.nio.IOUtil;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.FieldType;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.HazelcastSerializationException;
import com.hazelcast.nio.serialization.compact.CompactWriter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.DataOutput;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;

import static com.hazelcast.internal.nio.Bits.INT_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.NULL_ARRAY_LENGTH;
import static com.hazelcast.internal.serialization.impl.compact.OffsetReader.BYTE_OFFSET_READER_RANGE;
import static com.hazelcast.internal.serialization.impl.compact.OffsetReader.SHORT_OFFSET_READER_RANGE;
import static com.hazelcast.nio.serialization.FieldType.BOOLEAN;
import static com.hazelcast.nio.serialization.FieldType.BOOLEAN_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.BYTE;
import static com.hazelcast.nio.serialization.FieldType.BYTE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.CHAR;
import static com.hazelcast.nio.serialization.FieldType.CHAR_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.COMPOSED;
import static com.hazelcast.nio.serialization.FieldType.COMPOSED_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.DATE;
import static com.hazelcast.nio.serialization.FieldType.DATE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.DECIMAL;
import static com.hazelcast.nio.serialization.FieldType.DECIMAL_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.DOUBLE;
import static com.hazelcast.nio.serialization.FieldType.DOUBLE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.FLOAT;
import static com.hazelcast.nio.serialization.FieldType.FLOAT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.INT;
import static com.hazelcast.nio.serialization.FieldType.INT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.LONG;
import static com.hazelcast.nio.serialization.FieldType.LONG_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_BOOLEAN;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_BOOLEAN_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_BYTE;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_BYTE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_CHAR;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_CHAR_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_DATE;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_DATE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_DOUBLE;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_DOUBLE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_FLOAT;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_FLOAT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_INT;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_INT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_LONG;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_LONG_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_SHORT;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_SHORT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_TIME;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_TIMESTAMP;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_TIMESTAMP_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_TIMESTAMP_WITH_TIMEZONE;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_TIMESTAMP_WITH_TIMEZONE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.NULLABLE_TIME_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.SHORT;
import static com.hazelcast.nio.serialization.FieldType.SHORT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.TIME;
import static com.hazelcast.nio.serialization.FieldType.TIMESTAMP;
import static com.hazelcast.nio.serialization.FieldType.TIMESTAMP_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.TIMESTAMP_WITH_TIMEZONE;
import static com.hazelcast.nio.serialization.FieldType.TIMESTAMP_WITH_TIMEZONE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.TIME_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.UTF;
import static com.hazelcast.nio.serialization.FieldType.UTF_ARRAY;

public class DefaultCompactWriter implements CompactWriter {

    private final CompactStreamSerializer serializer;
    private final Schema schema;
    private final BufferObjectDataOutput out;
    private final int dataStartPosition;
    private final int[] fieldOffsets;
    private final boolean includeSchemaOnBinary;

    public DefaultCompactWriter(CompactStreamSerializer serializer,
                                BufferObjectDataOutput out, Schema schema, boolean includeSchemaOnBinary) {
        this.serializer = serializer;
        this.out = out;
        this.schema = schema;
        if (schema.getNumberOfVariableSizeFields() != 0) {
            this.fieldOffsets = new int[schema.getNumberOfVariableSizeFields()];
            dataStartPosition = out.position() + INT_SIZE_IN_BYTES;
            //skip for length and primitives
            out.writeZeroBytes(schema.getFixedSizeFieldsLength() + INT_SIZE_IN_BYTES);
        } else {
            this.fieldOffsets = null;
            dataStartPosition = out.position();
            //skip for  primitives
            out.writeZeroBytes(schema.getFixedSizeFieldsLength());
        }
        this.includeSchemaOnBinary = includeSchemaOnBinary;
    }

    public byte[] toByteArray() {
        return out.toByteArray();
    }

    public void end() {
        try {
            if (schema.getNumberOfVariableSizeFields() == 0) {
                //There are no variable size fields
                return;
            }
            int position = out.position();
            int dataLength = position - dataStartPosition;
            writeOffsets(dataLength, fieldOffsets);
            //write dataLength
            out.writeInt(dataStartPosition - INT_SIZE_IN_BYTES, dataLength);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    private void writeOffsets(int dataLength, int[] offsets) throws IOException {
        if (dataLength < BYTE_OFFSET_READER_RANGE) {
            for (int offset : offsets) {
                out.writeByte(offset);
            }
        } else if (dataLength < SHORT_OFFSET_READER_RANGE) {
            for (int offset : offsets) {
                out.writeShort(offset);
            }
        } else {
            for (int offset : offsets) {
                out.writeInt(offset);
            }
        }
    }

    IllegalStateException illegalStateException(IOException cause) {
        return new IllegalStateException("IOException is not expected from BufferObjectDataOutput ", cause);
    }

    @Override
    public void writeByte(@Nonnull String fieldName, byte value) {
        int position = getFixedSizeFieldPosition(fieldName, BYTE);
        try {
            out.writeByte(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeBoolean(@Nonnull String fieldName, boolean value) {
        FieldDescriptor fieldDefinition = checkFieldDefinition(fieldName, BOOLEAN);
        int offsetInBytes = fieldDefinition.getOffset();
        int offsetInBits = fieldDefinition.getBitOffset();
        int writeOffset = offsetInBytes + dataStartPosition;
        try {
            out.writeBooleanBit(writeOffset, offsetInBits, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeChar(@Nonnull String fieldName, char value) {
        int position = getFixedSizeFieldPosition(fieldName, CHAR);
        try {
            out.writeChar(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeShort(@Nonnull String fieldName, short value) {
        int position = getFixedSizeFieldPosition(fieldName, SHORT);
        try {
            out.writeShort(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeInt(@Nonnull String fieldName, int value) {
        int position = getFixedSizeFieldPosition(fieldName, INT);
        try {
            out.writeInt(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeLong(@Nonnull String fieldName, long value) {
        int position = getFixedSizeFieldPosition(fieldName, LONG);
        try {
            out.writeLong(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeFloat(@Nonnull String fieldName, float value) {
        int position = getFixedSizeFieldPosition(fieldName, FLOAT);
        try {
            out.writeFloat(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeDouble(@Nonnull String fieldName, double value) {
        int position = getFixedSizeFieldPosition(fieldName, DOUBLE);
        try {
            out.writeDouble(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeString(@Nonnull String fieldName, @Nullable String str) {
        writeVariableSizeField(fieldName, UTF, str, ObjectDataOutput::writeString);
    }

    protected <T> void writeVariableSizeField(@Nonnull String fieldName, FieldType fieldType, @Nullable T object,
                                              Writer<T> writer) {
        try {
            if (object == null) {
                setPositionAsNull(fieldName, fieldType);
            } else {
                setPosition(fieldName, fieldType);
                writer.write(out, object);
            }
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeObject(@Nonnull String fieldName, @Nullable Object value) {
        writeVariableSizeField(fieldName, COMPOSED, value,
                (out, val) -> serializer.writeObject(out, val, includeSchemaOnBinary));
    }

    public void writeGenericRecord(@Nonnull String fieldName, @Nullable GenericRecord value) {
        writeVariableSizeField(fieldName, COMPOSED, value,
                (out, val) -> serializer.writeGenericRecord(out, (CompactGenericRecord) val, includeSchemaOnBinary));
    }

    @Override
    public void writeDecimal(@Nonnull String fieldName, @Nullable BigDecimal value) {
        writeVariableSizeField(fieldName, DECIMAL, value, IOUtil::writeBigDecimal);
    }

    @Override
    public void writeTime(@Nonnull String fieldName, @Nonnull LocalTime value) {
        int lastPos = out.position();
        try {
            out.position(getFixedSizeFieldPosition(fieldName, TIME));
            IOUtil.writeLocalTime(out, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            out.position(lastPos);
        }
    }

    @Override
    public void writeDate(@Nonnull String fieldName, @Nonnull LocalDate value) {
        int lastPos = out.position();
        try {
            out.position(getFixedSizeFieldPosition(fieldName, DATE));
            IOUtil.writeLocalDate(out, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            out.position(lastPos);
        }
    }

    @Override
    public void writeTimestamp(@Nonnull String fieldName, @Nonnull LocalDateTime value) {
        int lastPos = out.position();
        try {
            out.position(getFixedSizeFieldPosition(fieldName, TIMESTAMP));
            IOUtil.writeLocalDateTime(out, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            out.position(lastPos);
        }
    }

    @Override
    public void writeTimestampWithTimezone(@Nonnull String fieldName, @Nonnull OffsetDateTime value) {
        int lastPos = out.position();
        try {
            out.position(getFixedSizeFieldPosition(fieldName, TIMESTAMP_WITH_TIMEZONE));
            IOUtil.writeOffsetDateTime(out, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            out.position(lastPos);
        }
    }

    @Override
    public void writeByteArray(@Nonnull String fieldName, @Nullable byte[] value) {
        writeVariableSizeField(fieldName, BYTE_ARRAY, value, ObjectDataOutput::writeByteArray);
    }

    @Override
    public void writeBooleanArray(@Nonnull String fieldName, @Nullable boolean[] value) {
        writeVariableSizeField(fieldName, BOOLEAN_ARRAY, value, DefaultCompactWriter::writeBooleanBits);
    }

    @Override
    public void writeCharArray(@Nonnull String fieldName, @Nullable char[] value) {
        writeVariableSizeField(fieldName, CHAR_ARRAY, value, ObjectDataOutput::writeCharArray);
    }

    @Override
    public void writeIntArray(@Nonnull String fieldName, @Nullable int[] value) {
        writeVariableSizeField(fieldName, INT_ARRAY, value, ObjectDataOutput::writeIntArray);
    }

    @Override
    public void writeLongArray(@Nonnull String fieldName, @Nullable long[] value) {
        writeVariableSizeField(fieldName, LONG_ARRAY, value, ObjectDataOutput::writeLongArray);
    }

    @Override
    public void writeDoubleArray(@Nonnull String fieldName, @Nullable double[] value) {
        writeVariableSizeField(fieldName, DOUBLE_ARRAY, value, ObjectDataOutput::writeDoubleArray);
    }

    @Override
    public void writeFloatArray(@Nonnull String fieldName, @Nullable float[] value) {
        writeVariableSizeField(fieldName, FLOAT_ARRAY, value, ObjectDataOutput::writeFloatArray);
    }

    @Override
    public void writeShortArray(@Nonnull String fieldName, @Nullable short[] value) {
        writeVariableSizeField(fieldName, SHORT_ARRAY, value, ObjectDataOutput::writeShortArray);
    }

    @Override
    public void writeStringArray(@Nonnull String fieldName, @Nullable String[] value) {
        writeVariableSizeArray(fieldName, UTF_ARRAY, value, ObjectDataOutput::writeString);
    }

    interface Writer<T> {
        void write(BufferObjectDataOutput out, T value) throws IOException;
    }

    protected <T> void writeVariableSizeArray(@Nonnull String fieldName, FieldType fieldType, @Nullable T[] value,
                                              Writer<T> writer) {
        if (value == null) {
            setPositionAsNull(fieldName, fieldType);
            return;
        }
        try {
            setPosition(fieldName, fieldType);
            int itemCount = value.length;
            out.writeInt(itemCount);
            int dataLengthOffset = out.position();
            out.writeZeroBytes(INT_SIZE_IN_BYTES);

            int offset = out.position();
            int[] offsets = new int[itemCount];
            for (int i = 0; i < itemCount; i++) {
                if (value[i] != null) {
                    offsets[i] = out.position() - offset;
                    writer.write(out, value[i]);
                } else {
                    offsets[i] = -1;
                }
            }
            int dataLength = out.position() - offset;
            out.writeInt(dataLengthOffset, dataLength);
            writeOffsets(dataLength, offsets);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeDecimalArray(@Nonnull String fieldName, @Nullable BigDecimal[] value) {
        writeVariableSizeArray(fieldName, DECIMAL_ARRAY, value, IOUtil::writeBigDecimal);
    }

    @Override
    public void writeTimeArray(@Nonnull String fieldName, @Nullable LocalTime[] value) {
        writeVariableSizeField(fieldName, TIME_ARRAY, value, DefaultCompactWriter::writeLocalTimeArray0);
    }

    @Override
    public void writeDateArray(@Nonnull String fieldName, @Nullable LocalDate[] value) {
        writeVariableSizeField(fieldName, DATE_ARRAY, value, DefaultCompactWriter::writeLocalDateArray0);
    }

    @Override
    public void writeTimestampArray(@Nonnull String fieldName, @Nullable LocalDateTime[] value) {
        writeVariableSizeField(fieldName, TIMESTAMP_ARRAY, value, DefaultCompactWriter::writeLocalDateTimeArray0);
    }

    @Override
    public void writeTimestampWithTimezoneArray(@Nonnull String fieldName, @Nullable OffsetDateTime[] value) {
        writeVariableSizeField(fieldName, TIMESTAMP_WITH_TIMEZONE_ARRAY, value, DefaultCompactWriter::writeOffsetDateTimeArray0);
    }

    protected void setPositionAsNull(@Nonnull String fieldName, FieldType fieldType) {
        FieldDescriptor field = checkFieldDefinition(fieldName, fieldType);
        int index = field.getIndex();
        fieldOffsets[index] = -1;
    }

    protected void setPosition(@Nonnull String fieldName, FieldType fieldType) {
        FieldDescriptor field = checkFieldDefinition(fieldName, fieldType);
        int pos = out.position();
        int fieldPosition = pos - dataStartPosition;
        int index = field.getIndex();
        fieldOffsets[index] = fieldPosition;
    }

    private int getFixedSizeFieldPosition(@Nonnull String fieldName, FieldType fieldType) {
        FieldDescriptor fieldDefinition = checkFieldDefinition(fieldName, fieldType);
        return fieldDefinition.getOffset() + dataStartPosition;
    }

    protected FieldDescriptor checkFieldDefinition(@Nonnull String fieldName, FieldType fieldType) {
        FieldDescriptor field = schema.getField(fieldName);
        if (field == null) {
            throw new HazelcastSerializationException("Invalid field name: '" + fieldName + " for " + schema);
        }
        if (!field.getType().equals(fieldType)) {
            throw new HazelcastSerializationException("Invalid field type: '" + fieldName + " for " + schema);
        }
        return field;
    }

    @Override
    public <T> void writeObjectArray(@Nonnull String fieldName, @Nullable T[] value) {
        writeVariableSizeArray(fieldName, COMPOSED_ARRAY, value,
                (out, val) -> serializer.writeObject(out, val, includeSchemaOnBinary));
    }

    @Override
    public void writeNullableTime(@Nonnull String fieldName, @Nullable LocalTime value) {
        writeVariableSizeField(fieldName, NULLABLE_TIME, value, IOUtil::writeLocalTime);
    }

    @Override
    public void writeNullableDate(@Nonnull String fieldName, @Nullable LocalDate value) {
        writeVariableSizeField(fieldName, NULLABLE_DATE, value, IOUtil::writeLocalDate);
    }

    @Override
    public void writeNullableTimestamp(@Nonnull String fieldName, @Nullable LocalDateTime value) {
        writeVariableSizeField(fieldName, NULLABLE_TIMESTAMP, value, IOUtil::writeLocalDateTime);
    }

    @Override
    public void writeNullableTimestampWithTimezone(@Nonnull String fieldName, @Nullable OffsetDateTime value) {
        writeVariableSizeField(fieldName, NULLABLE_TIMESTAMP_WITH_TIMEZONE, value, IOUtil::writeOffsetDateTime);
    }

    public void writeGenericRecordArray(@Nonnull String fieldName, GenericRecord[] value) {
        writeVariableSizeArray(fieldName, COMPOSED_ARRAY, value,
                (out, val) -> serializer.writeGenericRecord(out, (CompactGenericRecord) val, includeSchemaOnBinary));
    }

    @Override
    public void writeNullableTimeArray(@Nonnull String fieldName, @Nullable LocalTime[] value) {
        writeVariableSizeArray(fieldName, NULLABLE_TIME_ARRAY, value, IOUtil::writeLocalTime);
    }

    @Override
    public void writeNullableDateArray(@Nonnull String fieldName, @Nullable LocalDate[] value) {
        writeVariableSizeArray(fieldName, NULLABLE_DATE_ARRAY, value, IOUtil::writeLocalDate);
    }

    @Override
    public void writeNullableTimestampArray(@Nonnull String fieldName, @Nullable LocalDateTime[] value) {
        writeVariableSizeArray(fieldName, NULLABLE_TIMESTAMP_ARRAY, value, IOUtil::writeLocalDateTime);
    }

    @Override
    public void writeNullableTimestampWithTimezoneArray(@Nonnull String fieldName, @Nullable OffsetDateTime[] value) {
        writeVariableSizeArray(fieldName, NULLABLE_TIMESTAMP_WITH_TIMEZONE_ARRAY, value, IOUtil::writeOffsetDateTime);
    }

    @Override
    public void writeNullableByte(@Nonnull String fieldName, @Nullable Byte value) {
        writeVariableSizeField(fieldName, NULLABLE_BYTE, value, (Writer<Byte>) DataOutput::writeByte);
    }

    @Override
    public void writeNullableBoolean(@Nonnull String fieldName, @Nullable Boolean value) {
        writeVariableSizeField(fieldName, NULLABLE_BOOLEAN, value, DataOutput::writeBoolean);
    }

    @Override
    public void writeNullableChar(@Nonnull String fieldName, @Nullable Character value) {
        writeVariableSizeField(fieldName, NULLABLE_CHAR, value, (Writer<Character>) DataOutput::writeChar);
    }

    @Override
    public void writeNullableShort(@Nonnull String fieldName, @Nullable Short value) {
        writeVariableSizeField(fieldName, NULLABLE_SHORT, value, (Writer<Short>) DataOutput::writeShort);
    }

    @Override
    public void writeNullableInt(@Nonnull String fieldName, @Nullable Integer value) {
        writeVariableSizeField(fieldName, NULLABLE_INT, value, DataOutput::writeInt);
    }

    @Override
    public void writeNullableLong(@Nonnull String fieldName, @Nullable Long value) {
        writeVariableSizeField(fieldName, NULLABLE_LONG, value, DataOutput::writeLong);
    }

    @Override
    public void writeNullableFloat(@Nonnull String fieldName, @Nullable Float value) {
        writeVariableSizeField(fieldName, NULLABLE_FLOAT, value, DataOutput::writeFloat);
    }

    @Override
    public void writeNullableDouble(@Nonnull String fieldName, @Nullable Double value) {
        writeVariableSizeField(fieldName, NULLABLE_DOUBLE, value, DataOutput::writeDouble);
    }

    @Override
    public void writeNullableByteArray(@Nonnull String fieldName, @Nullable Byte[] value) {
        writeVariableSizeArray(fieldName, NULLABLE_BYTE_ARRAY, value, (Writer<Byte>) DataOutput::writeByte);
    }

    @Override
    public void writeNullableBooleanArray(@Nonnull String fieldName, @Nullable Boolean[] value) {
        writeVariableSizeArray(fieldName, NULLABLE_BOOLEAN_ARRAY, value, DataOutput::writeBoolean);
    }

    @Override
    public void writeNullableCharArray(@Nonnull String fieldName, @Nullable Character[] value) {
        writeVariableSizeArray(fieldName, NULLABLE_CHAR_ARRAY, value, (Writer<Character>) DataOutput::writeChar);
    }

    @Override
    public void writeNullableShortArray(@Nonnull String fieldName, @Nullable Short[] value) {
        writeVariableSizeArray(fieldName, NULLABLE_SHORT_ARRAY, value, (Writer<Short>) DataOutput::writeShort);
    }

    @Override
    public void writeNullableIntArray(@Nonnull String fieldName, @Nullable Integer[] value) {
        writeVariableSizeArray(fieldName, NULLABLE_INT_ARRAY, value, DataOutput::writeInt);
    }

    @Override
    public void writeNullableLongArray(@Nonnull String fieldName, @Nullable Long[] value) {
        writeVariableSizeArray(fieldName, NULLABLE_LONG_ARRAY, value, DataOutput::writeLong);
    }

    @Override
    public void writeNullableFloatArray(@Nonnull String fieldName, @Nullable Float[] value) {
        writeVariableSizeArray(fieldName, NULLABLE_FLOAT_ARRAY, value, DataOutput::writeFloat);
    }

    @Override
    public void writeNullableDoubleArray(@Nonnull String fieldName, @Nullable Double[] value) {
        writeVariableSizeArray(fieldName, NULLABLE_DOUBLE_ARRAY, value, DataOutput::writeDouble);
    }


    public static void writeLocalDateArray0(ObjectDataOutput out, @Nonnull LocalDate[] value) throws IOException {
        out.writeInt(value.length);
        for (LocalDate localDate : value) {
            IOUtil.writeLocalDate(out, localDate);
        }
    }

    public static void writeLocalTimeArray0(ObjectDataOutput out, @Nonnull LocalTime[] value) throws IOException {
        out.writeInt(value.length);
        for (LocalTime localTime : value) {
            IOUtil.writeLocalTime(out, localTime);
        }
    }

    public static void writeLocalDateTimeArray0(ObjectDataOutput out, @Nonnull LocalDateTime[] value) throws IOException {
        out.writeInt(value.length);
        for (LocalDateTime localDateTime : value) {
            IOUtil.writeLocalDateTime(out, localDateTime);
        }
    }

    public static void writeOffsetDateTimeArray0(ObjectDataOutput out, @Nonnull OffsetDateTime[] value) throws IOException {
        out.writeInt(value.length);
        for (OffsetDateTime offsetDateTime : value) {
            IOUtil.writeOffsetDateTime(out, offsetDateTime);
        }
    }

    static void writeBooleanBits(BufferObjectDataOutput out, @Nullable boolean[] booleans) throws IOException {
        int len = (booleans != null) ? booleans.length : NULL_ARRAY_LENGTH;
        out.writeInt(len);
        int position = out.position();
        if (len > 0) {
            int index = 0;
            out.writeZeroBytes(1);
            for (boolean v : booleans) {
                if (index == Byte.SIZE) {
                    index = 0;
                    out.writeZeroBytes(1);
                    position++;
                }
                out.writeBooleanBit(position, index, v);
                index++;
            }
        }
    }

}
