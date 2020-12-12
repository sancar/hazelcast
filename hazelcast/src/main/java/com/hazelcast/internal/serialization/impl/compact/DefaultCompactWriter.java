/*
 * Copyright (c) 2008-2020, Hazelcast, Inc. All Rights Reserved.
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
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Collection;

import static com.hazelcast.internal.nio.Bits.INT_SIZE_IN_BYTES;
import static com.hazelcast.nio.serialization.FieldType.BIG_DECIMAL;
import static com.hazelcast.nio.serialization.FieldType.BIG_DECIMAL_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.BIG_INTEGER;
import static com.hazelcast.nio.serialization.FieldType.BIG_INTEGER_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.BOOLEAN;
import static com.hazelcast.nio.serialization.FieldType.BOOLEAN_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.BYTE;
import static com.hazelcast.nio.serialization.FieldType.BYTE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.CHAR;
import static com.hazelcast.nio.serialization.FieldType.CHAR_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.DOUBLE;
import static com.hazelcast.nio.serialization.FieldType.DOUBLE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.FLOAT;
import static com.hazelcast.nio.serialization.FieldType.FLOAT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.INT;
import static com.hazelcast.nio.serialization.FieldType.INT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.LOCAL_DATE;
import static com.hazelcast.nio.serialization.FieldType.LOCAL_DATE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.LOCAL_DATE_TIME;
import static com.hazelcast.nio.serialization.FieldType.LOCAL_DATE_TIME_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.LOCAL_TIME;
import static com.hazelcast.nio.serialization.FieldType.LOCAL_TIME_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.LONG;
import static com.hazelcast.nio.serialization.FieldType.LONG_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.OBJECT;
import static com.hazelcast.nio.serialization.FieldType.OBJECT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.OFFSET_DATE_TIME;
import static com.hazelcast.nio.serialization.FieldType.OFFSET_DATE_TIME_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.SHORT;
import static com.hazelcast.nio.serialization.FieldType.SHORT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.UTF;
import static com.hazelcast.nio.serialization.FieldType.UTF_ARRAY;

public class DefaultCompactWriter implements CompactWriter {

    private final boolean isDebug = !System.getProperty("com.hazelcast.serialization.compact.debug").isEmpty();
    protected final Compact serializer;
    protected final Schema schema;
    protected final BufferObjectDataOutput out;
    protected final int offset;
    private final int[] fieldPositions;
    private final int variableLengthFieldOffsetsStart;

    public DefaultCompactWriter(Compact serializer,
                                BufferObjectDataOutput out, SchemaImpl schema) {
        this.serializer = serializer;
        this.out = out;
        this.schema = schema;
        int numberOfVariableLengthFields = schema.getNumberOfVariableLengthFields();
        this.fieldPositions = new int[numberOfVariableLengthFields];
        offset = out.position();
        out.writeZeroBytes(schema.getPrimitiveOffsetEnd() + (INT_SIZE_IN_BYTES * numberOfVariableLengthFields));
        if (isDebug) {
            System.out.println("DEBUG WRITE schema id  pos " + offset + " " + schema.getClassName() + " " + schema.getSchemaId());
        }
        try {
            out.writeLong(offset, schema.getSchemaId());
        } catch (IOException e) {
            throw illegalStateException(e);
        }
        variableLengthFieldOffsetsStart = schema.getPrimitiveOffsetEnd() + offset;
        //skip  primitives and variableLengthFieldOffsets
        if (isDebug) {
            System.out.println("DEBUG WRITE offset " + offset);
            System.out.println("DEBUG WRITE getPrimitiveOffsetEnd " + schema.getPrimitiveOffsetEnd());
            System.out.println("DEBUG WRITE numberOfVariableLengthFields " + numberOfVariableLengthFields);
            System.out.println("DEBUG WRITE start position of variable length " + out.position());
        }
    }

    public byte[] toByteArray() {
        return out.toByteArray();
    }

    public void end() {
        int position = out.position();
        try {
            out.position(variableLengthFieldOffsetsStart);
            writeIntArray0(out, fieldPositions);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            out.position(position);
        }
    }

    @Override
    public void writeInt(String fieldName, int value) {
        int position = getFixedLengthFieldPosition(fieldName, INT);
        try {
            out.writeInt(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeLong(String fieldName, long value) {
        int position = getFixedLengthFieldPosition(fieldName, LONG);
        try {
            out.writeLong(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeBoolean(String fieldName, boolean value) {
        int position = getFixedLengthFieldPosition(fieldName, BOOLEAN);
        try {
            out.writeBoolean(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    IllegalStateException illegalStateException(IOException cause) {
        return new IllegalStateException("IOException is not expected from BufferObjectDataOutput ", cause);
    }

    @Override
    public void writeByte(String fieldName, byte value) {
        int position = getFixedLengthFieldPosition(fieldName, BYTE);
        try {
            out.writeByte(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeChar(String fieldName, char value) {
        int position = getFixedLengthFieldPosition(fieldName, CHAR);
        try {
            out.writeChar(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeDouble(String fieldName, double value) {
        int position = getFixedLengthFieldPosition(fieldName, DOUBLE);
        try {
            out.writeDouble(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeFloat(String fieldName, float value) {
        int position = getFixedLengthFieldPosition(fieldName, FLOAT);
        try {
            out.writeFloat(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeShort(String fieldName, short value) {
        int position = getFixedLengthFieldPosition(fieldName, SHORT);
        try {
            out.writeShort(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    protected <T> void writeVariableLength(String fieldName, T object, FieldType fieldType, Writer<BufferObjectDataOutput, T> writer) {
        try {
            if (object == null) {
                setPosition(fieldName, fieldType, true);
            } else {
                setPosition(fieldName, fieldType, false);
                writer.write(out, object);
            }
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeUTF(String fieldName, String str) {
        writeVariableLength(fieldName, str, UTF, (out, value) -> out.write(str.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public void writeObject(String fieldName, Object value) {
        writeVariableLength(fieldName, value, OBJECT, serializer::writeObject);
    }

    public void writeGenericRecord(String fieldName, GenericRecord value) {
        writeVariableLength(fieldName, value, OBJECT, serializer::writeGenericRecord);
    }

    @Override
    public void writeBigInteger(String fieldName, BigInteger value) {
        writeVariableLength(fieldName, value, BIG_INTEGER, (out, v) -> out.write(v.toByteArray()));
    }

    @Override
    public void writeBigDecimal(String fieldName, BigDecimal value) {
        writeVariableLength(fieldName, value, BIG_DECIMAL, (out, v) -> {
            out.write(v.unscaledValue().toByteArray());
            out.writeInt(v.scale());
        });
    }

    @Override
    public void writeLocalTime(String fieldName, LocalTime value) {
        int lastPos = out.position();
        try {
            out.position(getFixedLengthFieldPosition(fieldName, LOCAL_TIME));
            IOUtil.writeLocalTime(out, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            out.position(lastPos);
        }
    }

    @Override
    public void writeLocalDate(String fieldName, LocalDate value) {
        int lastPos = out.position();
        try {
            out.position(getFixedLengthFieldPosition(fieldName, LOCAL_DATE));
            IOUtil.writeLocalDate(out, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            out.position(lastPos);
        }
    }

    @Override
    public void writeLocalDateTime(String fieldName, LocalDateTime value) {
        int lastPos = out.position();
        try {
            out.position(getFixedLengthFieldPosition(fieldName, LOCAL_DATE_TIME));
            IOUtil.writeLocalDateTime(out, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            out.position(lastPos);
        }
    }

    @Override
    public void writeOffsetDateTime(String fieldName, OffsetDateTime value) {
        int lastPos = out.position();
        try {
            out.position(getFixedLengthFieldPosition(fieldName, OFFSET_DATE_TIME));
            IOUtil.writeOffsetDateTime(out, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            out.position(lastPos);
        }
    }

    @Override
    public void writeByteArray(String fieldName, byte[] values) {
        writeVariableLength(fieldName, values, BYTE_ARRAY, DefaultCompactWriter::writeByteArray0);
    }

    @Override
    public void writeBooleanArray(String fieldName, boolean[] values) {
        writeVariableLength(fieldName, values, BOOLEAN_ARRAY, DefaultCompactWriter::writeBooleanArray0);
    }

    @Override
    public void writeCharArray(String fieldName, char[] values) {
        writeVariableLength(fieldName, values, CHAR_ARRAY, DefaultCompactWriter::writeCharArray0);
    }

    @Override
    public void writeIntArray(String fieldName, int[] values) {
        writeVariableLength(fieldName, values, INT_ARRAY, DefaultCompactWriter::writeIntArray0);
    }

    @Override
    public void writeLongArray(String fieldName, long[] values) {
        writeVariableLength(fieldName, values, LONG_ARRAY, DefaultCompactWriter::writeLongArray0);
    }

    @Override
    public void writeDoubleArray(String fieldName, double[] values) {
        writeVariableLength(fieldName, values, DOUBLE_ARRAY, DefaultCompactWriter::writeDoubleArray0);
    }

    @Override
    public void writeFloatArray(String fieldName, float[] values) {
        writeVariableLength(fieldName, values, FLOAT_ARRAY, DefaultCompactWriter::writeFloatArray0);
    }

    @Override
    public void writeShortArray(String fieldName, short[] values) {
        writeVariableLength(fieldName, values, SHORT_ARRAY, DefaultCompactWriter::writeShortArray0);
    }

    @Override
    public void writeUTFArray(String fieldName, String[] values) {
        writeObjectArrayField(fieldName, UTF_ARRAY, values,(out, value) -> out.write(value.getBytes(StandardCharsets.UTF_8)));
    }


    interface Writer<O, T> {

        void write(O out, T value) throws IOException;
    }

    protected <T> void writeObjectArrayField(String fieldName, FieldType fieldType, T[] values, Writer<BufferObjectDataOutput, T> writer) {
        //TODO sancar make it same with writeArrayList
        if (values == null) {
            setPosition(fieldName, fieldType, true);
            return;
        }
        try {
            setPosition(fieldName, fieldType, false);
            int len = values.length;

            int arrayOffset = out.position();
            out.writeZeroBytes((len + 1) * INT_SIZE_IN_BYTES);
            for (int i = 0; i < len; i++) {
                int position = out.position();
                out.writeInt(arrayOffset + i * INT_SIZE_IN_BYTES, position);
                writer.write(out, values[i]);
            }
            out.writeInt(arrayOffset + (len + 1) * INT_SIZE_IN_BYTES, out.position());
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeBigIntegerArray(String fieldName, BigInteger[] values) {
        writeObjectArrayField(fieldName, BIG_INTEGER_ARRAY, values, IOUtil::writeBigInteger);
    }

    @Override
    public void writeBigDecimalArray(String fieldName, BigDecimal[] values) {
        writeObjectArrayField(fieldName, BIG_DECIMAL_ARRAY, values, IOUtil::writeBigDecimal);
    }

    @Override
    public void writeLocalTimeArray(String fieldName, LocalTime[] values) {
        writeObjectArrayField(fieldName, LOCAL_TIME_ARRAY, values, IOUtil::writeLocalTime);
    }

    @Override
    public void writeLocalDateArray(String fieldName, LocalDate[] values) {
        writeObjectArrayField(fieldName, LOCAL_DATE_ARRAY, values, IOUtil::writeLocalDate);
    }

    @Override
    public void writeLocalDateTimeArray(String fieldName, LocalDateTime[] values) {
        writeObjectArrayField(fieldName, LOCAL_DATE_TIME_ARRAY, values, IOUtil::writeLocalDateTime);
    }

    @Override
    public void writeOffsetDateTimeArray(String fieldName, OffsetDateTime[] values) {
        writeObjectArrayField(fieldName, OFFSET_DATE_TIME_ARRAY, values, IOUtil::writeOffsetDateTime);
    }

    protected void setPosition(String fieldName, FieldType fieldType, boolean isNull) {
        FieldDescriptorImpl field = checkFieldDefinition(fieldName, fieldType);
        int pos = out.position();
        int fieldPosition = pos - offset;
        int index = field.getIndex();
        if (isNull) {
            fieldPositions[index] = -fieldPosition;
        } else {
            fieldPositions[index] = fieldPosition;
        }
        if (isDebug) {
            System.out.println("DEBUG WRITE " + schema.getClassName() + " " + fieldType + " " + fieldName + " " + fieldPositions[index] + ", with offset " + pos + " at index " + index);
        }
    }

    private int getFixedLengthFieldPosition(String fieldName, FieldType fieldType) {
        FieldDescriptorImpl fieldDefinition = checkFieldDefinition(fieldName, fieldType);
        int writeOffset = fieldDefinition.getOffset() + offset;
        if (isDebug) {
            System.out.println("DEBUG WRITE " + schema.getClassName() + " " + fieldType + " " + fieldName + " " + fieldDefinition.getOffset() + ", with offset " + writeOffset);
        }
        return writeOffset;
    }

    @NotNull
    protected FieldDescriptorImpl checkFieldDefinition(String fieldName, FieldType fieldType) {
        FieldDescriptorImpl field = (FieldDescriptorImpl) schema.getField(fieldName);
        if (field == null) {
            throw new HazelcastSerializationException("Invalid field name: '" + fieldName + " for " + schema);
        }
        if (!field.getType().equals(fieldType)) {
            throw new HazelcastSerializationException("Invalid field type: '" + fieldName + " for " + schema);
        }
        return field;
    }

    @Override
    public <T> void writeObjectArray(String fieldName, T[] values) {
        writeObjectArrayField(fieldName, OBJECT_ARRAY, values, serializer::writeObject);
    }

    public void writeGenericRecordArray(String fieldName, GenericRecord[] values) {
        writeObjectArrayField(fieldName, OBJECT_ARRAY, values, serializer::writeGenericRecord);
    }

    @Override
    public <T> void writeObjectCollection(String fieldName, Collection<T> values) {
        try {
            if (values == null) {
                setPosition(fieldName, OBJECT_ARRAY, true);
                return;
            }
            setPosition(fieldName, OBJECT_ARRAY, false);
            int len = values.size();
            int arrayOffset = out.position();
            out.writeZeroBytes((len + 1) * INT_SIZE_IN_BYTES);
            int i = 0;
            for (T value : values) {
                int position = out.position();
                out.writeInt(arrayOffset + i * INT_SIZE_IN_BYTES, position);
                serializer.writeObject(out, value);
                i++;
            }
            out.writeInt(arrayOffset + (len + 1) * INT_SIZE_IN_BYTES, out.position());
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public static void writeByteArray0(ObjectDataOutput out, byte[] value) throws IOException {
        out.write(value);
    }

    public static void writeBooleanArray0(ObjectDataOutput out, boolean[] value) throws IOException {
        for (boolean b : value) {
            out.writeBoolean(b);
        }
    }

    public static void writeCharArray0(ObjectDataOutput out, char[] value) throws IOException {
        for (char c : value) {
            out.writeChar(c);
        }
    }

    public static void writeIntArray0(ObjectDataOutput out, int[] value) throws IOException {
        for (int i : value) {
            out.writeInt(i);
        }
    }

    public static void writeLongArray0(ObjectDataOutput out, long[] value) throws IOException {
        for (long l : value) {
            out.writeLong(l);
        }
    }

    public static void writeDoubleArray0(ObjectDataOutput out, double[] value) throws IOException {
        for (double d : value) {
            out.writeDouble(d);
        }
    }

    public static void writeFloatArray0(ObjectDataOutput out, float[] value) throws IOException {
        for (float f : value) {
            out.writeFloat(f);
        }
    }

    public static void writeShortArray0(ObjectDataOutput out, short[] value) throws IOException {
        for (short s : value) {
            out.writeShort(s);
        }
    }

    public static void writeLocalDateArray0(ObjectDataOutput out, LocalDate[] value) throws IOException {
        for (LocalDate localDate : value) {
            IOUtil.writeLocalDate(out, localDate);
        }
    }

    public static void writeLocalTimeArray0(ObjectDataOutput out, LocalTime[] value) throws IOException {
        for (LocalTime localTime : value) {
            IOUtil.writeLocalTime(out, localTime);
        }
    }

    public static void writeLocalDateTimeArray0(ObjectDataOutput out, LocalDateTime[] value) throws IOException {
        for (LocalDateTime localDateTime : value) {
            IOUtil.writeLocalDateTime(out, localDateTime);
        }
    }

    public static void writeOffsetDateTimeArray0(ObjectDataOutput out, OffsetDateTime[] value) throws IOException {
        for (OffsetDateTime offsetDateTime : value) {
            IOUtil.writeOffsetDateTime(out, offsetDateTime);
        }
    }

}
