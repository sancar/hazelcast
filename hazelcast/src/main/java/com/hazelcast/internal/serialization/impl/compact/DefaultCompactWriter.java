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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Iterator;

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
import static com.hazelcast.nio.serialization.FieldType.COMPOSED;
import static com.hazelcast.nio.serialization.FieldType.COMPOSED_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.OFFSET_DATE_TIME;
import static com.hazelcast.nio.serialization.FieldType.OFFSET_DATE_TIME_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.SHORT;
import static com.hazelcast.nio.serialization.FieldType.SHORT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.UTF;
import static com.hazelcast.nio.serialization.FieldType.UTF_ARRAY;

public class DefaultCompactWriter implements CompactWriter {

    private final boolean isDebug = System.getProperty("com.hazelcast.serialization.compact.debug") != null;
    protected final Compact serializer;
    protected final Schema schema;
    protected final BufferObjectDataOutput out;
    protected final int offset;
    private final int[] fieldPositions;

    public DefaultCompactWriter(Compact serializer,
                                BufferObjectDataOutput out, SchemaImpl schema) {
        this.serializer = serializer;
        this.out = out;
        this.schema = schema;
        this.fieldPositions = new int[schema.getNumberOfVariableLengthFields()];
        offset = out.position() + INT_SIZE_IN_BYTES;
        //skip for length and primitives
        out.writeZeroBytes(schema.getPrimitivesLength() + INT_SIZE_IN_BYTES);
        if (isDebug) {
            System.out.println("DEBUG WRITE " + schema.getClassName() + "  offset  " + offset + " " + out);
            System.out.println("DEBUG WRITE " + "schema.getNumberOfVariableLengthFields() " + schema.getNumberOfVariableLengthFields());
        }
    }

    public byte[] toByteArray() {
        return out.toByteArray();
    }

    public void end() {
        try {
            for (int i = fieldPositions.length - 1; i >= 0; i--) {
                out.writeInt(fieldPositions[i]);
            }
            int position = out.position();
            int length = position - offset;
            //write length
            out.writeInt(offset - INT_SIZE_IN_BYTES, length);
            if (isDebug) {
                System.out.println("DEBUG WRITE " + schema.getClassName() + "  pos  " + (offset - INT_SIZE_IN_BYTES) + " length " + length);
            }
        } catch (IOException e) {
            throw illegalStateException(e);
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

    protected <T> void writeVariableLength(String fieldName, FieldType fieldType, T object, Writer<T> writer) {
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
    public void writeUTF(String fieldName, String str) {
        writeVariableLength(fieldName, UTF, str, ObjectDataOutput::writeUTF);
    }

    @Override
    public void writeObject(String fieldName, Object value) {
        writeVariableLength(fieldName, COMPOSED, value, serializer::writeObject);
    }

    public void writeGenericRecord(String fieldName, GenericRecord value) {
        writeVariableLength(fieldName, COMPOSED, value, serializer::writeGenericRecord);
    }

    @Override
    public void writeBigInteger(String fieldName, BigInteger value) {
        writeVariableLength(fieldName, BIG_INTEGER, value, IOUtil::writeBigInteger);
    }

    @Override
    public void writeBigDecimal(String fieldName, BigDecimal value) {
        writeVariableLength(fieldName, BIG_DECIMAL, value, IOUtil::writeBigDecimal);
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
        writeVariableLength(fieldName, BYTE_ARRAY, values, ObjectDataOutput::writeByteArray);
    }

    @Override
    public void writeBooleanArray(String fieldName, boolean[] values) {
        writeVariableLength(fieldName, BOOLEAN_ARRAY, values, ObjectDataOutput::writeBooleanArray);
    }

    @Override
    public void writeCharArray(String fieldName, char[] values) {
        writeVariableLength(fieldName, CHAR_ARRAY, values, ObjectDataOutput::writeCharArray);
    }

    @Override
    public void writeIntArray(String fieldName, int[] values) {
        writeVariableLength(fieldName, INT_ARRAY, values, ObjectDataOutput::writeIntArray);
    }

    @Override
    public void writeLongArray(String fieldName, long[] values) {
        writeVariableLength(fieldName, LONG_ARRAY, values, ObjectDataOutput::writeLongArray);
    }

    @Override
    public void writeDoubleArray(String fieldName, double[] values) {
        writeVariableLength(fieldName, DOUBLE_ARRAY, values, ObjectDataOutput::writeDoubleArray);
    }

    @Override
    public void writeFloatArray(String fieldName, float[] values) {
        writeVariableLength(fieldName, FLOAT_ARRAY, values, ObjectDataOutput::writeFloatArray);
    }

    @Override
    public void writeShortArray(String fieldName, short[] values) {
        writeVariableLength(fieldName, SHORT_ARRAY, values, ObjectDataOutput::writeShortArray);
    }

    @Override
    public void writeUTFArray(String fieldName, String[] values) {
        writeObjectArrayField(fieldName, UTF_ARRAY, values, ObjectDataOutput::writeUTF);
    }

    interface Writer<T> {
        void write(BufferObjectDataOutput out, T value) throws IOException;
    }

    protected <T> void writeObjectArrayField(String fieldName, FieldType fieldType, T[] values, Writer<T> writer) {
        //TODO sancar make it same with writeArrayList
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
    public void writeBigIntegerArray(String fieldName, BigInteger[] values) {
        writeObjectArrayField(fieldName, BIG_INTEGER_ARRAY, values, IOUtil::writeBigInteger);
    }

    @Override
    public void writeBigDecimalArray(String fieldName, BigDecimal[] values) {
        writeObjectArrayField(fieldName, BIG_DECIMAL_ARRAY, values, IOUtil::writeBigDecimal);
    }

    @Override
    public void writeLocalTimeArray(String fieldName, LocalTime[] values) {
        writeVariableLength(fieldName, LOCAL_TIME_ARRAY, values, DefaultCompactWriter::writeLocalTimeArray0);
    }

    @Override
    public void writeLocalDateArray(String fieldName, LocalDate[] values) {
        writeVariableLength(fieldName, LOCAL_DATE_ARRAY, values, DefaultCompactWriter::writeLocalDateArray0);
    }

    @Override
    public void writeLocalDateTimeArray(String fieldName, LocalDateTime[] values) {
        writeVariableLength(fieldName, LOCAL_DATE_TIME_ARRAY, values, DefaultCompactWriter::writeLocalDateTimeArray0);
    }

    @Override
    public void writeOffsetDateTimeArray(String fieldName, OffsetDateTime[] values) {
        writeVariableLength(fieldName, OFFSET_DATE_TIME_ARRAY, values, DefaultCompactWriter::writeOffsetDateTimeArray0);
    }

    protected void setPositionAsNull(String fieldName, FieldType fieldType) {
        FieldDescriptorImpl field = checkFieldDefinition(fieldName, fieldType);
        int index = field.getIndex();
        fieldPositions[index] = -1;
    }

    protected void setPosition(String fieldName, FieldType fieldType) {
        FieldDescriptorImpl field = checkFieldDefinition(fieldName, fieldType);
        int pos = out.position();
        int fieldPosition = pos - offset;
        int index = field.getIndex();
        fieldPositions[index] = fieldPosition;
        if (isDebug) {
            System.out.println("DEBUG WRITE " + schema.getClassName() + " " + fieldName + " index " + index + " pos " + fieldPosition + " with offset " + pos);
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
        writeObjectArrayField(fieldName, COMPOSED_ARRAY, values, serializer::writeObject);
    }

    public void writeGenericRecordArray(String fieldName, GenericRecord[] values) {
        writeObjectArrayField(fieldName, COMPOSED_ARRAY, values, serializer::writeGenericRecord);
    }

    @Override
    public <T> void writeObjectCollection(String fieldName, Collection<T> values) {
        try {
            if (values == null) {
                setPositionAsNull(fieldName, COMPOSED_ARRAY);
                return;
            }
            setPosition(fieldName, COMPOSED_ARRAY);
            int len = values.size();
            out.writeInt(len);
            int offset = out.position();
            out.writeZeroBytes(len * INT_SIZE_IN_BYTES);
            int i = 0;
            for (T value : values) {
                if (value != null) {
                    int position = out.position();
                    out.writeInt(offset + i * INT_SIZE_IN_BYTES, position);
                    serializer.writeObject(out, value);
                } else {
                    out.writeInt(offset + i * INT_SIZE_IN_BYTES, -1);
                }
                i++;
            }
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public <T> void writeAnyCollection(String fieldName, Collection<T> values) {
        if(arrayList.isEmpty()) {
            return;
        }
        Iterator<T> iterator = arrayList.iterator();
        T next = iterator.next();
        Class<?> aClass = next.getClass();
        FieldType arrayFieldType = TypeUtil.getArrayFieldType(aClass);
        FieldType componentType = arrayFieldType.getSingleType();
        if(componentType.hasDefiniteSize()) {
            writeVariableLength(fieldName, componentType, values, new Writer<Collection<T>>() {
                @Override
                public void write(BufferObjectDataOutput out, Collection<T> value) throws IOException {
                    out.writeBooleanArray(v);
                }
            });
        } else {

        }

        while (iterator.hasNext()) {

        }
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

}
