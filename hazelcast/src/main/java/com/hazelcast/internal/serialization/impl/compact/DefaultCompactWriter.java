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
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;

import static com.hazelcast.internal.nio.Bits.INT_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.NULL_ARRAY_LENGTH;
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
    private final int offset;
    private final int[] fieldPositions;
    private final boolean includeSchemaOnBinary;

    public DefaultCompactWriter(CompactStreamSerializer serializer,
                                BufferObjectDataOutput out, Schema schema, boolean includeSchemaOnBinary) {
        this.serializer = serializer;
        this.out = out;
        this.schema = schema;
        if (schema.getNumberOfVariableLengthFields() != 0) {
            this.fieldPositions = new int[schema.getNumberOfVariableLengthFields()];
            offset = out.position() + INT_SIZE_IN_BYTES;
            //skip for length and primitives
            out.writeZeroBytes(schema.getPrimitivesLength() + INT_SIZE_IN_BYTES);
        } else {
            this.fieldPositions = null;
            offset = out.position();
            //skip for  primitives
            out.writeZeroBytes(schema.getPrimitivesLength());
        }
        this.includeSchemaOnBinary = includeSchemaOnBinary;
    }

    public byte[] toByteArray() {
        return out.toByteArray();
    }

    public void end() {
        try {
            if (schema.getNumberOfVariableLengthFields() == 0) {
                //There are no var length
                return;
            }
            for (int i = fieldPositions.length - 1; i >= 0; i--) {
                out.writeInt(fieldPositions[i]);
            }
            int position = out.position();
            int length = position - offset;
            //write length
            out.writeInt(offset - INT_SIZE_IN_BYTES, length);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeInt(@Nonnull String fieldName, int value) {
        int position = getFixedLengthFieldPosition(fieldName, INT);
        try {
            out.writeInt(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeLong(@Nonnull String fieldName, long value) {
        int position = getFixedLengthFieldPosition(fieldName, LONG);
        try {
            out.writeLong(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeBoolean(@Nonnull String fieldName, boolean value) {
        FieldDescriptor fieldDefinition = checkFieldDefinition(fieldName, BOOLEAN);
        int offsetInBits = fieldDefinition.getOffset();
        int offsetInBytes = offsetInBits == 0 ? 0 : (offsetInBits / Byte.SIZE);
        int writeOffset = offsetInBytes + offset;
        int booleanOffsetWithinLastByte = offsetInBits % Byte.SIZE;
        try {
            out.writeBooleanBit(writeOffset, booleanOffsetWithinLastByte, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    IllegalStateException illegalStateException(IOException cause) {
        return new IllegalStateException("IOException is not expected from BufferObjectDataOutput ", cause);
    }

    @Override
    public void writeByte(@Nonnull String fieldName, byte value) {
        int position = getFixedLengthFieldPosition(fieldName, BYTE);
        try {
            out.writeByte(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeChar(@Nonnull String fieldName, char value) {
        int position = getFixedLengthFieldPosition(fieldName, CHAR);
        try {
            out.writeChar(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeDouble(@Nonnull String fieldName, double value) {
        int position = getFixedLengthFieldPosition(fieldName, DOUBLE);
        try {
            out.writeDouble(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeFloat(@Nonnull String fieldName, float value) {
        int position = getFixedLengthFieldPosition(fieldName, FLOAT);
        try {
            out.writeFloat(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeShort(@Nonnull String fieldName, short value) {
        int position = getFixedLengthFieldPosition(fieldName, SHORT);
        try {
            out.writeShort(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    protected <T> void writeVariableLength(@Nonnull String fieldName, FieldType fieldType, T object, Writer<T> writer) {
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
    public void writeString(@Nonnull String fieldName, String str) {
        writeVariableLength(fieldName, UTF, str, ObjectDataOutput::writeString);
    }

    @Override
    public void writeObject(@Nonnull String fieldName, Object value) {
        writeVariableLength(fieldName, COMPOSED, value,
                (out, val) -> serializer.writeObject(out, val, includeSchemaOnBinary));
    }

    public void writeGenericRecord(@Nonnull String fieldName, GenericRecord value) {
        writeVariableLength(fieldName, COMPOSED, value,
                (out, val) -> serializer.writeGenericRecord(out, (CompactGenericRecord) val, includeSchemaOnBinary));
    }

    @Override
    public void writeDecimal(@Nonnull String fieldName, BigDecimal value) {
        writeVariableLength(fieldName, DECIMAL, value, IOUtil::writeBigDecimal);
    }

    @Override
    public void writeTime(@Nonnull String fieldName, LocalTime value) {
        int lastPos = out.position();
        try {
            out.position(getFixedLengthFieldPosition(fieldName, TIME));
            IOUtil.writeLocalTime(out, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            out.position(lastPos);
        }
    }

    @Override
    public void writeDate(@Nonnull String fieldName, LocalDate value) {
        int lastPos = out.position();
        try {
            out.position(getFixedLengthFieldPosition(fieldName, DATE));
            IOUtil.writeLocalDate(out, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            out.position(lastPos);
        }
    }

    @Override
    public void writeTimestamp(@Nonnull String fieldName, LocalDateTime value) {
        int lastPos = out.position();
        try {
            out.position(getFixedLengthFieldPosition(fieldName, TIMESTAMP));
            IOUtil.writeLocalDateTime(out, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            out.position(lastPos);
        }
    }

    @Override
    public void writeTimestampWithTimezone(@Nonnull String fieldName, OffsetDateTime value) {
        int lastPos = out.position();
        try {
            out.position(getFixedLengthFieldPosition(fieldName, TIMESTAMP_WITH_TIMEZONE));
            IOUtil.writeOffsetDateTime(out, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            out.position(lastPos);
        }
    }

    @Override
    public void writeByteArray(@Nonnull String fieldName, byte[] values) {
        writeVariableLength(fieldName, BYTE_ARRAY, values, ObjectDataOutput::writeByteArray);
    }

    @Override
    public void writeBooleanArray(@Nonnull String fieldName, boolean[] values) {
        writeVariableLength(fieldName, BOOLEAN_ARRAY, values, DefaultCompactWriter::writeBooleanBits);
    }

    @Override
    public void writeCharArray(@Nonnull String fieldName, char[] values) {
        writeVariableLength(fieldName, CHAR_ARRAY, values, ObjectDataOutput::writeCharArray);
    }

    @Override
    public void writeIntArray(@Nonnull String fieldName, int[] values) {
        writeVariableLength(fieldName, INT_ARRAY, values, ObjectDataOutput::writeIntArray);
    }

    @Override
    public void writeLongArray(@Nonnull String fieldName, long[] values) {
        writeVariableLength(fieldName, LONG_ARRAY, values, ObjectDataOutput::writeLongArray);
    }

    @Override
    public void writeDoubleArray(@Nonnull String fieldName, double[] values) {
        writeVariableLength(fieldName, DOUBLE_ARRAY, values, ObjectDataOutput::writeDoubleArray);
    }

    @Override
    public void writeFloatArray(@Nonnull String fieldName, float[] values) {
        writeVariableLength(fieldName, FLOAT_ARRAY, values, ObjectDataOutput::writeFloatArray);
    }

    @Override
    public void writeShortArray(@Nonnull String fieldName, short[] values) {
        writeVariableLength(fieldName, SHORT_ARRAY, values, ObjectDataOutput::writeShortArray);
    }

    @Override
    public void writeStringArray(@Nonnull String fieldName, String[] values) {
        writeObjectArrayField(fieldName, UTF_ARRAY, values, ObjectDataOutput::writeString);
    }

    interface Writer<T> {
        void write(BufferObjectDataOutput out, T value) throws IOException;
    }

    protected <T> void writeObjectArrayField(@Nonnull String fieldName, FieldType fieldType, T[] values, Writer<T> writer) {
        if (values == null) {
            setPositionAsNull(fieldName, fieldType);
            return;
        }
        try {
            setPosition(fieldName, fieldType);
            int len = values.length;
            out.writeInt(len);

            int offset = out.position();
            out.writeZeroBytes(len * INT_SIZE_IN_BYTES);
            for (int i = 0; i < len; i++) {
                if (values[i] != null) {
                    int position = out.position();
                    out.writeInt(offset + i * INT_SIZE_IN_BYTES, position);
                    writer.write(out, values[i]);
                } else {
                    out.writeInt(offset + i * INT_SIZE_IN_BYTES, -1);
                }
            }
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeDecimalArray(@Nonnull String fieldName, BigDecimal[] values) {
        writeObjectArrayField(fieldName, DECIMAL_ARRAY, values, IOUtil::writeBigDecimal);
    }

    @Override
    public void writeTimeArray(@Nonnull String fieldName, LocalTime[] values) {
        writeVariableLength(fieldName, TIME_ARRAY, values, DefaultCompactWriter::writeLocalTimeArray0);
    }

    @Override
    public void writeDateArray(@Nonnull String fieldName, LocalDate[] values) {
        writeVariableLength(fieldName, DATE_ARRAY, values, DefaultCompactWriter::writeLocalDateArray0);
    }

    @Override
    public void writeTimestampArray(@Nonnull String fieldName, LocalDateTime[] values) {
        writeVariableLength(fieldName, TIMESTAMP_ARRAY, values, DefaultCompactWriter::writeLocalDateTimeArray0);
    }

    @Override
    public void writeTimestampWithTimezoneArray(@Nonnull String fieldName, OffsetDateTime[] values) {
        writeVariableLength(fieldName, TIMESTAMP_WITH_TIMEZONE_ARRAY, values, DefaultCompactWriter::writeOffsetDateTimeArray0);
    }

    protected void setPositionAsNull(@Nonnull String fieldName, FieldType fieldType) {
        FieldDescriptor field = checkFieldDefinition(fieldName, fieldType);
        int index = field.getIndex();
        fieldPositions[index] = -1;
    }

    protected void setPosition(@Nonnull String fieldName, FieldType fieldType) {
        FieldDescriptor field = checkFieldDefinition(fieldName, fieldType);
        int pos = out.position();
        int fieldPosition = pos - offset;
        int index = field.getIndex();
        fieldPositions[index] = fieldPosition;
    }

    private int getFixedLengthFieldPosition(@Nonnull String fieldName, FieldType fieldType) {
        FieldDescriptor fieldDefinition = checkFieldDefinition(fieldName, fieldType);
        int writeOffset = fieldDefinition.getOffset() + offset;
        return writeOffset;
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
    public <T> void writeObjectArray(@Nonnull String fieldName, T[] values) {
        writeObjectArrayField(fieldName, COMPOSED_ARRAY, values,
                (out, val) -> serializer.writeObject(out, val, includeSchemaOnBinary));
    }

    public void writeGenericRecordArray(@Nonnull String fieldName, GenericRecord[] values) {
        writeObjectArrayField(fieldName, COMPOSED_ARRAY, values,
                (out, val) -> serializer.writeGenericRecord(out, (CompactGenericRecord) val, includeSchemaOnBinary));
    }

    public static void writeLocalDateArray0(ObjectDataOutput out, LocalDate[] value) throws IOException {
        out.writeInt(value.length);
        for (LocalDate localDate : value) {
            IOUtil.writeLocalDate(out, localDate);
        }
    }

    public static void writeLocalTimeArray0(ObjectDataOutput out, LocalTime[] value) throws IOException {
        out.writeInt(value.length);
        for (LocalTime localTime : value) {
            IOUtil.writeLocalTime(out, localTime);
        }
    }

    public static void writeLocalDateTimeArray0(ObjectDataOutput out, LocalDateTime[] value) throws IOException {
        out.writeInt(value.length);
        for (LocalDateTime localDateTime : value) {
            IOUtil.writeLocalDateTime(out, localDateTime);
        }
    }

    public static void writeOffsetDateTimeArray0(ObjectDataOutput out, OffsetDateTime[] value) throws IOException {
        out.writeInt(value.length);
        for (OffsetDateTime offsetDateTime : value) {
            IOUtil.writeOffsetDateTime(out, offsetDateTime);
        }
    }

    static void writeBooleanBits(BufferObjectDataOutput out, boolean[] booleans) throws IOException {
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
