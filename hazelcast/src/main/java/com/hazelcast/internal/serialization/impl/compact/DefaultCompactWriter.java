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
import java.util.ArrayList;

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
import static com.hazelcast.nio.serialization.FieldType.PORTABLE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.SHORT;
import static com.hazelcast.nio.serialization.FieldType.SHORT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.UTF;
import static com.hazelcast.nio.serialization.FieldType.UTF_ARRAY;

public class DefaultCompactWriter implements CompactWriter {

    protected final Compact serializer;
    protected final Schema schema;
    protected final BufferObjectDataOutput out;
    private final int offset;
    private final int[] fieldPositions;

    public DefaultCompactWriter(Compact serializer,
                                BufferObjectDataOutput out, SchemaImpl schema) {
        this.serializer = serializer;
        this.out = out;
        this.schema = schema;
        this.fieldPositions = new int[schema.getNumberOfComplexFields()];
        offset = out.position();
        //skip for length and primitives
        out.writeZeroBytes(schema.getPrimitiveOffsetEnd());
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
            out.writeInt(offset, length);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeInt(String fieldName, int value) {
        int position = getPrimitivePosition(fieldName, INT);
        try {
            out.writeInt(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeLong(String fieldName, long value) {
        int position = getPrimitivePosition(fieldName, LONG);
        try {
            out.writeLong(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeBoolean(String fieldName, boolean value) {
        int position = getPrimitivePosition(fieldName, BOOLEAN);
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
        int position = getPrimitivePosition(fieldName, BYTE);
        try {
            out.writeByte(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeChar(String fieldName, char value) {
        int position = getPrimitivePosition(fieldName, CHAR);
        try {
            out.writeChar(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeDouble(String fieldName, double value) {
        int position = getPrimitivePosition(fieldName, DOUBLE);
        try {
            out.writeDouble(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeFloat(String fieldName, float value) {
        int position = getPrimitivePosition(fieldName, FLOAT);
        try {
            out.writeFloat(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeShort(String fieldName, short value) {
        int position = getPrimitivePosition(fieldName, SHORT);
        try {
            out.writeShort(position, value);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    private <T> void writeNullable(String fieldName, T object, FieldType fieldType, Writer<BufferObjectDataOutput, T> writer) {
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
        setPosition(fieldName, UTF);
        try {
            out.writeUTF(str);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public void writeObject(String fieldName, Object value) {
        writeNullable(fieldName, value, OBJECT, serializer::writeObject);
    }

    public void writeGenericRecord(String fieldName, GenericRecord value) {
        writeNullable(fieldName, value, OBJECT, serializer::writeGenericRecord);
    }

    @Override
    public void writeBigInteger(String fieldName, BigInteger value) {
        writeNullable(fieldName, value, BIG_INTEGER, IOUtil::writeBigInteger);
    }

    @Override
    public void writeBigDecimal(String fieldName, BigDecimal value) {
        writeNullable(fieldName, value, BIG_DECIMAL, IOUtil::writeBigDecimal);
    }

    @Override
    public void writeLocalTime(String fieldName, LocalTime value) {
        writeNullable(fieldName, value, LOCAL_TIME, IOUtil::writeLocalTime);
    }

    @Override
    public void writeLocalDate(String fieldName, LocalDate value) {
        writeNullable(fieldName, value, LOCAL_DATE, IOUtil::writeLocalDate);
    }

    @Override
    public void writeLocalDateTime(String fieldName, LocalDateTime value) {
        writeNullable(fieldName, value, LOCAL_DATE_TIME, IOUtil::writeLocalDateTime);
    }

    @Override
    public void writeOffsetDateTime(String fieldName, OffsetDateTime value) {
        writeNullable(fieldName, value, OFFSET_DATE_TIME, IOUtil::writeOffsetDateTime);
    }

    @Override
    public void writeByteArray(String fieldName, byte[] values) {
        writeNullable(fieldName, values, BYTE_ARRAY, ObjectDataOutput::writeByteArray);
    }

    @Override
    public void writeBooleanArray(String fieldName, boolean[] values) {
        writeNullable(fieldName, values, BOOLEAN_ARRAY, ObjectDataOutput::writeBooleanArray);
    }

    @Override
    public void writeCharArray(String fieldName, char[] values) {
        writeNullable(fieldName, values, CHAR_ARRAY, ObjectDataOutput::writeCharArray);
    }

    @Override
    public void writeIntArray(String fieldName, int[] values) {
        writeNullable(fieldName, values, INT_ARRAY, ObjectDataOutput::writeIntArray);
    }

    @Override
    public void writeLongArray(String fieldName, long[] values) {
        writeNullable(fieldName, values, LONG_ARRAY, ObjectDataOutput::writeLongArray);
    }

    @Override
    public void writeDoubleArray(String fieldName, double[] values) {
        writeNullable(fieldName, values, DOUBLE_ARRAY, ObjectDataOutput::writeDoubleArray);
    }

    @Override
    public void writeFloatArray(String fieldName, float[] values) {
        writeNullable(fieldName, values, FLOAT_ARRAY, ObjectDataOutput::writeFloatArray);
    }

    @Override
    public void writeShortArray(String fieldName, short[] values) {
        writeNullable(fieldName, values, SHORT_ARRAY, ObjectDataOutput::writeShortArray);
    }

    @Override
    public void writeUTFArray(String fieldName, String[] values) {
        writeObjectArrayField(fieldName, UTF_ARRAY, values, ObjectDataOutput::writeUTF);
    }


    interface Writer<O, T> {

        void write(O out, T value) throws IOException;
    }

    private <T> void writeObjectArrayField(String fieldName, FieldType fieldType, T[] values, Writer<BufferObjectDataOutput, T> writer) {
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
            out.writeZeroBytes(len * 4);
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

    private void setPositionAsNull(String fieldName, FieldType fieldType) {
        FieldDescriptorImpl field = checkFieldDefinition(fieldName, fieldType);
        int index = field.getIndex();
        fieldPositions[index] = -1;
    }

    private void setPosition(String fieldName, FieldType fieldType) {
        FieldDescriptorImpl field = checkFieldDefinition(fieldName, fieldType);
        int pos = out.position();
        int fieldPosition = pos - offset;
        int index = field.getIndex();
        fieldPositions[index] = fieldPosition;

    }

    private int getPrimitivePosition(String fieldName, FieldType fieldType) {
        FieldDescriptorImpl fieldDefinition = checkFieldDefinition(fieldName, fieldType);
        return fieldDefinition.getOffset() + offset;
    }

    @NotNull
    private FieldDescriptorImpl checkFieldDefinition(String fieldName, FieldType fieldType) {
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
    public <T> void writeObjectArrayList(String fieldName, ArrayList<T> values) {
        try {
            if (values == null) {
                setPositionAsNull(fieldName, PORTABLE_ARRAY);
                return;
            }
            setPosition(fieldName, PORTABLE_ARRAY);
            int len = values.size();
            out.writeInt(len);
            int offset = out.position();
            out.writeZeroBytes(offset + len * INT_SIZE_IN_BYTES);
            for (int i = 0; i < len; i++) {
                Object value = values.get(i);
                if (value != null) {
                    int position = out.position();
                    out.writeInt(offset + i * INT_SIZE_IN_BYTES, position);
                    serializer.writeObject(out, value);
                } else {
                    out.writeInt(offset + i * INT_SIZE_IN_BYTES, -1);
                }
            }
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

}
