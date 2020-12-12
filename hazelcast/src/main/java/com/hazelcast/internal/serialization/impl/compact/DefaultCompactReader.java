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

import com.hazelcast.internal.nio.Bits;
import com.hazelcast.internal.nio.BufferObjectDataInput;
import com.hazelcast.internal.nio.IOUtil;
import com.hazelcast.internal.serialization.impl.InternalGenericRecord;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.serialization.AbstractGenericRecord;
import com.hazelcast.nio.serialization.FieldType;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.HazelcastSerializationException;
import com.hazelcast.nio.serialization.compact.CompactReader;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.hazelcast.internal.nio.Bits.BOOLEAN_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.BYTE_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.CHAR_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.DOUBLE_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.FLOAT_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.INT_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.LONG_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.NULL_ARRAY_LENGTH;
import static com.hazelcast.internal.nio.Bits.SHORT_SIZE_IN_BYTES;
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

public class DefaultCompactReader extends AbstractGenericRecord implements InternalGenericRecord, CompactReader {

    private final boolean isDebug = !System.getProperty("com.hazelcast.serialization.compact.debug").isEmpty();
    protected static final int NULL_POSITION = -1;
    private final Compact serializer;

    protected final SchemaImpl schema;
    protected final BufferObjectDataInput in;
    protected final int finalPosition;
    protected final int totalLength;
    protected final int offset;

    private final int variableLengthFieldOffsetsStart;
    private final @Nullable
    Class associatedClass;

    public DefaultCompactReader(Compact serializer, BufferObjectDataInput in, Schema schema,
                                @Nullable Class associatedClass, int totalLength) {
        this.in = in;
        this.serializer = serializer;
        this.schema = (SchemaImpl) schema;
        this.associatedClass = associatedClass;
        this.offset = in.position();
        this.variableLengthFieldOffsetsStart = offset + ((SchemaImpl) schema).getPrimitiveOffsetEnd();
        this.totalLength = totalLength;
        finalPosition = totalLength + offset;
        if (isDebug) {
            System.out.print("DEBUG READ DefaultCompactReader::new length " + totalLength + ", offset " + offset + ", in " + in);
        }
        in.position(finalPosition);
    }

    @Nullable
    public Class getAssociatedClass() {
        return associatedClass;
    }

    public BufferObjectDataInput getIn() {
        return in;
    }

    public Schema getSchema() {
        return schema;
    }

    @Override
    public GenericRecord.Builder newBuilder() {
        return serializer.createGenericRecordBuilder(schema);
    }

    @Override
    public Builder cloneWithBuilder() {
        return new SerializingGenericRecordCloner(serializer, schema, this,
                bytes -> serializer.getInternalSerializationService().createObjectDataInput(bytes),
                () -> serializer.getInternalSerializationService().createObjectDataOutput());
    }

    @Override
    public FieldType getFieldType(@Nonnull String fieldName) {
        return schema.getField(fieldName).getType();
    }

    @Override
    public boolean hasField(@Nonnull String fieldName) {
        return schema.hasField(fieldName);
    }

    @Nonnull
    @Override
    public Set<String> getFieldNames() {
        return schema.getFields().stream().map(FieldDescriptor::getName).collect(Collectors.toSet());
    }

    @Override
    public byte readByte(@Nonnull String fieldName) {
        try {
            return in.readByte(readPrimitivePosition(fieldName, BYTE));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    boolean isFieldExists(@Nonnull String fieldName, @Nonnull FieldType type) {
        FieldDescriptor field = schema.getField(fieldName);
        if (field == null) {
            return false;
        }
        return field.getType().equals(type);
    }

    @Override
    public byte readByte(String fieldName, byte defaultValue) {
        return isFieldExists(fieldName, BYTE) ? readByte(fieldName) : defaultValue;
    }

    @Override
    public short readShort(@Nonnull String fieldName) {
        try {
            return in.readShort(readPrimitivePosition(fieldName, SHORT));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public short readShort(String fieldName, short defaultValue) {
        return isFieldExists(fieldName, SHORT) ? readShort(fieldName) : defaultValue;
    }

    @Override
    public int readInt(@Nonnull String fieldName) {
        try {
            return in.readInt(readPrimitivePosition(fieldName, INT));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public int readInt(String fieldName, int defaultValue) {
        return isFieldExists(fieldName, INT) ? readInt(fieldName) : defaultValue;
    }

    @Override
    public long readLong(@Nonnull String fieldName) {
        try {
            return in.readLong(readPrimitivePosition(fieldName, LONG));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public long readLong(String fieldName, long defaultValue) {
        return isFieldExists(fieldName, LONG) ? readLong(fieldName) : defaultValue;
    }

    @Override
    public float readFloat(@Nonnull String fieldName) {
        try {
            return in.readFloat(readPrimitivePosition(fieldName, FLOAT));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public float readFloat(String fieldName, float defaultValue) {
        return isFieldExists(fieldName, FLOAT) ? readFloat(fieldName) : defaultValue;
    }

    @Override
    public double readDouble(@Nonnull String fieldName) {
        try {
            return in.readDouble(readPrimitivePosition(fieldName, DOUBLE));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public double readDouble(String fieldName, double defaultValue) {
        return isFieldExists(fieldName, DOUBLE) ? readDouble(fieldName) : defaultValue;
    }

    @Override
    public boolean readBoolean(@Nonnull String fieldName) {
        try {
            return in.readBoolean(readPrimitivePosition(fieldName, BOOLEAN));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public boolean readBoolean(String fieldName, boolean defaultValue) {
        return isFieldExists(fieldName, BOOLEAN) ? readBoolean(fieldName) : defaultValue;
    }

    @Override
    public char readChar(@Nonnull String fieldName) {
        try {
            return in.readChar(readPrimitivePosition(fieldName, CHAR));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public char readChar(String fieldName, char defaultValue) {
        return isFieldExists(fieldName, CHAR) ? readChar(fieldName) : defaultValue;
    }

    @Override
    public String readUTF(@Nonnull String fieldName) {
        return readVariableLength(fieldName, UTF, BufferObjectDataInput::readUTF);
    }

    @Override
    public String readUTF(String fieldName, String defaultValue) {
        return isFieldExists(fieldName, UTF) ? readUTF(fieldName) : defaultValue;
    }

    private <T> T readVariableLength(@Nonnull String fieldName, FieldType fieldType,
                                     VariableLengthFieldReader<BufferObjectDataInput, T> reader) {
        int currentPos = in.position();
        try {
            int index = getFieldDefinition(fieldName, fieldType).getIndex();
            int variableLengthFieldOffset = in.readInt(variableLengthFieldOffsetsStart + index * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            in.position(variableLengthFieldOffset + offset);
            int length = getLength(index, variableLengthFieldOffset + offset);
            if (isDebug) {
                System.out.println("DEBUG READ " + schema.getClassName() + " " + fieldType + " " + fieldName + " pos " + variableLengthFieldOffset + ", length " + length + " with offset " + (variableLengthFieldOffset + offset));
            }
            return reader.read(in, length);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    private int getLength(int index, int variableLengthFieldPos) {
        try {
            int length;
            if (index + 1 == schema.getNumberOfVariableLengthFields()) {
                length = totalLength - variableLengthFieldPos;
            } else {
                length = Math.abs(in.readInt(variableLengthFieldOffsetsStart + (index + 1) * INT_SIZE_IN_BYTES)) -
                        variableLengthFieldPos;
            }
            return length;
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public BigInteger readBigInteger(@Nonnull String fieldName) {
        return readVariableLength(fieldName, BIG_INTEGER, DefaultCompactReader::readBigInteger);
    }

    @Override
    public BigInteger readBigInteger(String fieldName, BigInteger defaultValue) {
        return isFieldExists(fieldName, BIG_INTEGER) ? readBigInteger(fieldName) : defaultValue;
    }

    @Override
    public BigDecimal readBigDecimal(@Nonnull String fieldName) {
        return readVariableLength(fieldName, BIG_DECIMAL, (bufferObjectDataInput, length) -> {
            final byte[] bytes = new byte[length - INT_SIZE_IN_BYTES];
            in.readFully(bytes);
            BigInteger bigInteger = new BigInteger(bytes);
            int scale = in.readInt();
            return new BigDecimal(bigInteger, scale);
        });
    }

    @Override
    public BigDecimal readBigDecimal(String fieldName, BigDecimal defaultValue) {
        return isFieldExists(fieldName, BIG_DECIMAL) ? readBigDecimal(fieldName) : defaultValue;
    }

    @Override
    public LocalTime readLocalTime(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            in.position(readPrimitivePosition(fieldName, LOCAL_TIME));
            return IOUtil.readLocalTime(in);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public LocalTime readLocalTime(String fieldName, LocalTime defaultValue) {
        return isFieldExists(fieldName, LOCAL_TIME) ? readLocalTime(fieldName) : defaultValue;
    }

    @Override
    public LocalDate readLocalDate(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            in.position(readPrimitivePosition(fieldName, LOCAL_DATE));
            return IOUtil.readLocalDate(in);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public LocalDate readLocalDate(String fieldName, LocalDate defaultValue) {
        return isFieldExists(fieldName, LOCAL_DATE) ? readLocalDate(fieldName) : defaultValue;
    }

    @Override
    public LocalDateTime readLocalDateTime(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            in.position(readPrimitivePosition(fieldName, LOCAL_DATE_TIME));
            return IOUtil.readLocalDateTime(in);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public LocalDateTime readLocalDateTime(String fieldName, LocalDateTime defaultValue) {
        return isFieldExists(fieldName, LOCAL_DATE_TIME) ? readLocalDateTime(fieldName) : defaultValue;
    }

    @Override
    public OffsetDateTime readOffsetDateTime(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            in.position(readPrimitivePosition(fieldName, OFFSET_DATE_TIME));
            return IOUtil.readOffsetDateTime(in);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public OffsetDateTime readOffsetDateTime(String fieldName, OffsetDateTime defaultValue) {
        return isFieldExists(fieldName, OFFSET_DATE_TIME) ? readOffsetDateTime(fieldName) : defaultValue;
    }

    @Override
    public GenericRecord readGenericRecord(@Nonnull String fieldName) {
        return readVariableLength(fieldName, OBJECT, serializer::readGenericRecord);
    }

    @Override
    public <T> T readObject(@Nonnull String fieldName) {
        return readVariableLength(fieldName, OBJECT, serializer::readObject);
    }

    @Override
    public <T> T readObject(String fieldName, T defaultValue) {
        return isFieldExists(fieldName, OBJECT) ? readObject(fieldName) : defaultValue;
    }

    @Override
    public byte[] readByteArray(@Nonnull String fieldName) {
        return readVariableLength(fieldName, BYTE_ARRAY, DefaultCompactReader::readByteArray);
    }

    @Override
    public byte[] readByteArray(String fieldName, byte[] defaultValue) {
        return isFieldExists(fieldName, BYTE_ARRAY) ? readByteArray(fieldName) : defaultValue;
    }

    @Override
    public boolean[] readBooleanArray(@Nonnull String fieldName) {
        return readVariableLength(fieldName, BOOLEAN_ARRAY, DefaultCompactReader::readBooleanArray);
    }

    @Override
    public boolean[] readBooleanArray(String fieldName, boolean[] defaultValue) {
        return isFieldExists(fieldName, BOOLEAN_ARRAY) ? readBooleanArray(fieldName) : defaultValue;
    }

    @Override
    public char[] readCharArray(@Nonnull String fieldName) {
        return readVariableLength(fieldName, CHAR_ARRAY, DefaultCompactReader::readCharArray);
    }

    @Override
    public char[] readCharArray(String fieldName, char[] defaultValue) {
        return isFieldExists(fieldName, CHAR_ARRAY) ? readCharArray(fieldName) : defaultValue;
    }

    @Override
    public int[] readIntArray(@Nonnull String fieldName) {
        return readVariableLength(fieldName, INT_ARRAY, DefaultCompactReader::readIntArray);
    }

    @Override
    public int[] readIntArray(String fieldName, int[] defaultValue) {
        return isFieldExists(fieldName, INT_ARRAY) ? readIntArray(fieldName) : defaultValue;
    }

    @Override
    public long[] readLongArray(@Nonnull String fieldName) {
        return readVariableLength(fieldName, LONG_ARRAY, DefaultCompactReader::readLongArray);
    }

    @Override
    public long[] readLongArray(String fieldName, long[] defaultValue) {
        return isFieldExists(fieldName, LONG_ARRAY) ? readLongArray(fieldName) : defaultValue;
    }

    @Override
    public double[] readDoubleArray(@Nonnull String fieldName) {
        return readVariableLength(fieldName, DOUBLE_ARRAY, DefaultCompactReader::readDoubleArray);
    }

    @Override
    public double[] readDoubleArray(String fieldName, double[] defaultValue) {
        return isFieldExists(fieldName, DOUBLE_ARRAY) ? readDoubleArray(fieldName) : defaultValue;
    }

    @Override
    public float[] readFloatArray(@Nonnull String fieldName) {
        return readVariableLength(fieldName, FLOAT_ARRAY, DefaultCompactReader::readFloatArray);
    }

    @Override
    public float[] readFloatArray(String fieldName, float[] defaultValue) {
        return isFieldExists(fieldName, FLOAT_ARRAY) ? readFloatArray(fieldName) : defaultValue;
    }

    @Override
    public short[] readShortArray(@Nonnull String fieldName) {
        return readVariableLength(fieldName, SHORT_ARRAY, DefaultCompactReader::readShortArray);
    }

    @Override
    public short[] readShortArray(String fieldName, short[] defaultValue) {
        return isFieldExists(fieldName, SHORT_ARRAY) ? readShortArray(fieldName) : defaultValue;
    }

    @Override
    public String[] readUTFArray(@Nonnull String fieldName) {
        return readVariableLengthFieldArray(fieldName, UTF_ARRAY, String[]::new, BufferObjectDataInput::readUTF);
    }

    @Override
    public String[] readUTFArray(String fieldName, String[] defaultValue) {
        return isFieldExists(fieldName, UTF_ARRAY) ? readUTFArray(fieldName) : defaultValue;
    }

    @Override
    public BigInteger[] readBigIntegerArray(@Nonnull String fieldName) {
        return readVariableLengthFieldArray(fieldName, BIG_INTEGER_ARRAY, BigInteger[]::new, DefaultCompactReader::readBigInteger);
    }

    @Override
    public BigInteger[] readBigIntegerArray(String fieldName, BigInteger[] defaultValue) {
        return isFieldExists(fieldName, BIG_INTEGER_ARRAY) ? readBigIntegerArray(fieldName) : defaultValue;
    }

    @Override
    public BigDecimal[] readBigDecimalArray(@Nonnull String fieldName) {
        return readVariableLengthFieldArray(fieldName, BIG_DECIMAL_ARRAY, BigDecimal[]::new, DefaultCompactReader::readBigDecimal);
    }

    @Override
    public BigDecimal[] readBigDecimalArray(String fieldName, BigDecimal[] defaultValue) {
        return isFieldExists(fieldName, BIG_DECIMAL_ARRAY) ? readBigDecimalArray(fieldName) : defaultValue;
    }

    @Override
    public LocalTime[] readLocalTimeArray(@Nonnull String fieldName) {
        return readVariableLength(fieldName, LOCAL_TIME_ARRAY, DefaultCompactReader::readLocalTimeArray);
    }

    @Override
    public LocalTime[] readLocalTimeArray(String fieldName, LocalTime[] defaultValue) {
        return isFieldExists(fieldName, LOCAL_TIME_ARRAY) ? readLocalTimeArray(fieldName) : defaultValue;
    }

    @Override
    public LocalDate[] readLocalDateArray(@Nonnull String fieldName) {
        return readVariableLength(fieldName, LOCAL_DATE_ARRAY, DefaultCompactReader::readLocalDateArray);
    }

    @Override
    public LocalDate[] readLocalDateArray(String fieldName, LocalDate[] defaultValue) {
        return isFieldExists(fieldName, LOCAL_DATE_ARRAY) ? readLocalDateArray(fieldName) : defaultValue;
    }

    @Override
    public LocalDateTime[] readLocalDateTimeArray(@Nonnull String fieldName) {
        return readVariableLength(fieldName, LOCAL_DATE_TIME_ARRAY, DefaultCompactReader::readLocalDateTimeArray);
    }

    @Override
    public LocalDateTime[] readLocalDateTimeArray(String fieldName, LocalDateTime[] defaultValue) {
        return isFieldExists(fieldName, LOCAL_DATE_TIME_ARRAY) ? readLocalDateTimeArray(fieldName) : defaultValue;
    }

    @Override
    public OffsetDateTime[] readOffsetDateTimeArray(@Nonnull String fieldName) {
        return readVariableLength(fieldName, OFFSET_DATE_TIME_ARRAY, DefaultCompactReader::readOffsetDateTimeArray);
    }

    @Override
    public OffsetDateTime[] readOffsetDateTimeArray(String fieldName, OffsetDateTime[] defaultValue) {
        return isFieldExists(fieldName, OFFSET_DATE_TIME_ARRAY) ? readOffsetDateTimeArray(fieldName) : defaultValue;
    }

    @Override
    public GenericRecord[] readGenericRecordArray(@Nonnull String fieldName) {
        return readVariableLengthFieldArray(fieldName, OBJECT_ARRAY, GenericRecord[]::new, serializer::readGenericRecord);
    }

    @Override
    public Object[] readObjectArray(@Nonnull String fieldName) {
        return readVariableLengthFieldArray(fieldName, OBJECT_ARRAY, Object[]::new, serializer::readObject);
    }

    @Override
    public Object[] readObjectArray(String fieldName, Object[] defaultValue) {
        return isFieldExists(fieldName, OBJECT_ARRAY) ? readObjectArray(fieldName) : defaultValue;
    }

    @Override
    public <T> List<T> readObjectList(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            int index = getFieldDefinition(fieldName, OBJECT_ARRAY).getIndex();
            int variableLengthFieldOffset = in.readInt(variableLengthFieldOffsetsStart + index * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            in.position(variableLengthFieldOffset + offset);
            int length = getLength(index, variableLengthFieldOffset);
            ArrayList<T> values = new ArrayList<>(length);
            int arrayOffsets = variableLengthFieldOffset + offset;
            int pos = in.readInt(arrayOffsets);
            for (int i = 0; i < length; i++) {
                arrayOffsets += INT_SIZE_IN_BYTES;
                int nextPos = in.readInt(arrayOffsets);
                values.add(serializer.readObject(in, nextPos - pos));
                pos = nextPos;
            }
            return values;
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public <T> List<T> readObjectList(String fieldName, List<T> defaultValue) {
        return isFieldExists(fieldName, OBJECT_ARRAY) ? readObjectList(fieldName) : defaultValue;
    }

    protected interface VariableLengthFieldReader<T, R> {
        R read(T t, int length) throws IOException;
    }

    private <T> T[] readVariableLengthFieldArray(@Nonnull String fieldName, FieldType fieldType,
                                                 Function<Integer, T[]> constructor,
                                                 VariableLengthFieldReader<BufferObjectDataInput, T> reader) {
        int currentPos = in.position();
        try {
            int fieldOffsetIndex = getFieldDefinition(fieldName, fieldType).getIndex();
            int variableLengthFieldOffset
                    = in.readInt(variableLengthFieldOffsetsStart + fieldOffsetIndex * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            in.position(variableLengthFieldOffset + offset);
            int length = getLength(fieldOffsetIndex, variableLengthFieldOffset);
            T[] values = constructor.apply(length);
            int arrayOffsets = variableLengthFieldOffset + offset;
            int pos = in.readInt(arrayOffsets);
            for (int i = 0; i < length; i++) {
                arrayOffsets += INT_SIZE_IN_BYTES;
                int nextPos = in.readInt(arrayOffsets);
                in.position(pos);
                values[i] = reader.read(in, nextPos - pos);
                pos = nextPos;
            }
            return values;
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    private int readPrimitivePosition(@Nonnull String fieldName, FieldType fieldType) {
        FieldDescriptorImpl fd = getFieldDefinition(fieldName, fieldType);
        int primitiveOffset = fd.getOffset();
        int readOffset = primitiveOffset + offset;
        if (isDebug) {
            System.out.println("DEBUG READ " + schema.getClassName() + " " + fieldType + " " + fieldName + " " + primitiveOffset + " withOffset " + readOffset);
        }
        return readOffset;
    }

    @NotNull
    protected FieldDescriptorImpl getFieldDefinition(@Nonnull String fieldName, FieldType fieldType) {
        FieldDescriptorImpl fd = (FieldDescriptorImpl) schema.getField(fieldName);
        if (fd == null) {
            throw throwUnknownFieldException(fieldName);
        }
        if (fd.getType() != fieldType) {
            throw new HazelcastSerializationException("Not a '" + fieldType + "' field: " + fieldName);
        }
        return fd;
    }

    protected int readPosition(@Nonnull String fieldName, FieldType fieldType) {
        try {
            FieldDescriptorImpl fd = getFieldDefinition(fieldName, fieldType);
            int index = fd.getIndex();
            int pos = in.readInt(variableLengthFieldOffsetsStart + index * INT_SIZE_IN_BYTES);
            return pos == NULL_POSITION ? NULL_POSITION : pos + offset;
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    protected HazelcastSerializationException throwUnknownFieldException(@Nonnull String fieldName) {
        return new HazelcastSerializationException("Unknown field name: '" + fieldName
                + "' for " + schema);
    }

    //indexed methods//

    public Byte readByteFromArray(@Nonnull String fieldName, int index) {
        try {
            int fieldOffsetIndex = getFieldDefinition(fieldName, BYTE_ARRAY).getIndex();
            int variableLengthFieldOffset
                    = in.readInt(variableLengthFieldOffsetsStart + fieldOffsetIndex * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            int length = getLength(fieldOffsetIndex, variableLengthFieldOffset);
            if (length <= index) {
                return null;
            }
            return in.readByte(variableLengthFieldOffset + offset + (index * BYTE_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public Boolean readBooleanFromArray(@Nonnull String fieldName, int index) {
        try {
            int fieldOffsetIndex = getFieldDefinition(fieldName, BYTE_ARRAY).getIndex();
            int variableLengthFieldOffset
                    = in.readInt(variableLengthFieldOffsetsStart + fieldOffsetIndex * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            int length = getLength(fieldOffsetIndex, variableLengthFieldOffset);
            if (length <= index) {
                return null;
            }
            return in.readBoolean(variableLengthFieldOffset + offset + (index * BOOLEAN_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public Character readCharFromArray(@Nonnull String fieldName, int index) {
        try {
            int fieldOffsetIndex = getFieldDefinition(fieldName, CHAR_ARRAY).getIndex();
            int variableLengthFieldOffset
                    = in.readInt(variableLengthFieldOffsetsStart + fieldOffsetIndex * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            int length = getLength(fieldOffsetIndex, variableLengthFieldOffset);
            if (length <= index) {
                return null;
            }
            return in.readChar(variableLengthFieldOffset + offset + (index * CHAR_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public Integer readIntFromArray(@Nonnull String fieldName, int index) {
        try {
            int fieldOffsetIndex = getFieldDefinition(fieldName, INT_ARRAY).getIndex();
            int variableLengthFieldOffset
                    = in.readInt(variableLengthFieldOffsetsStart + fieldOffsetIndex * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            int length = getLength(fieldOffsetIndex, variableLengthFieldOffset);
            if (length <= index) {
                return null;
            }
            return in.readInt(variableLengthFieldOffset + offset + (index * INT_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public Long readLongFromArray(@Nonnull String fieldName, int index) {
        try {
            int fieldOffsetIndex = getFieldDefinition(fieldName, LONG_ARRAY).getIndex();
            int variableLengthFieldOffset
                    = in.readInt(variableLengthFieldOffsetsStart + fieldOffsetIndex * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            int length = getLength(fieldOffsetIndex, variableLengthFieldOffset);
            if (length <= index) {
                return null;
            }
            return in.readLong(variableLengthFieldOffset + offset + (index * LONG_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public Double readDoubleFromArray(@Nonnull String fieldName, int index) {
        try {
            int fieldOffsetIndex = getFieldDefinition(fieldName, DOUBLE_ARRAY).getIndex();
            int variableLengthFieldOffset
                    = in.readInt(variableLengthFieldOffsetsStart + fieldOffsetIndex * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            int length = getLength(fieldOffsetIndex, variableLengthFieldOffset);
            if (length <= index) {
                return null;
            }
            return in.readDouble(variableLengthFieldOffset + offset + (index * DOUBLE_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public Float readFloatFromArray(@Nonnull String fieldName, int index) {
        try {
            int fieldOffsetIndex = getFieldDefinition(fieldName, FLOAT_ARRAY).getIndex();
            int variableLengthFieldOffset
                    = in.readInt(variableLengthFieldOffsetsStart + fieldOffsetIndex * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            int length = getLength(fieldOffsetIndex, variableLengthFieldOffset);
            if (length <= index) {
                return null;
            }
            return in.readFloat(variableLengthFieldOffset + offset + (index * FLOAT_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public Short readShortFromArray(@Nonnull String fieldName, int index) {
        try {
            int fieldOffsetIndex = getFieldDefinition(fieldName, SHORT_ARRAY).getIndex();
            int variableLengthFieldOffset
                    = in.readInt(variableLengthFieldOffsetsStart + fieldOffsetIndex * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            int length = getLength(fieldOffsetIndex, variableLengthFieldOffset);
            if (length <= index) {
                return null;
            }
            return in.readShort(variableLengthFieldOffset + offset + (index * SHORT_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public String readUTFFromArray(@Nonnull String fieldName, int index) {
        return readObjectFromArrayField(fieldName, UTF_ARRAY, BufferObjectDataInput::readUTF, index);
    }

    @Override
    public GenericRecord readGenericRecordFromArray(@Nonnull String fieldName, int index) {
        return readObjectFromArrayField(fieldName, OBJECT_ARRAY, serializer::readGenericRecord, index);
    }

    @Override
    public BigInteger readBigIntegerFromArray(@Nonnull String fieldName, int index) {
        return readObjectFromArrayField(fieldName, BIG_INTEGER_ARRAY, DefaultCompactReader::readBigInteger, index);
    }

    @Override
    public BigDecimal readBigDecimalFromArray(@Nonnull String fieldName, int index) {
        return readObjectFromArrayField(fieldName, BIG_DECIMAL_ARRAY, DefaultCompactReader::readBigDecimal, index);
    }

    @Override
    public LocalTime readLocalTimeFromArray(@Nonnull String fieldName, int index) {
        try {
            int fieldOffsetIndex = getFieldDefinition(fieldName, LOCAL_TIME_ARRAY).getIndex();
            int variableLengthFieldOffset
                    = in.readInt(variableLengthFieldOffsetsStart + fieldOffsetIndex * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            int length = getLength(fieldOffsetIndex, variableLengthFieldOffset);
            if (length <= index) {
                return null;
            }
            in.position(variableLengthFieldOffset + offset + (index * LOCAL_TIME_ARRAY.getTypeSize()));
            return IOUtil.readLocalTime(in);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public LocalDate readLocalDateFromArray(@Nonnull String fieldName, int index) {
        try {
            int fieldOffsetIndex = getFieldDefinition(fieldName, LOCAL_DATE_ARRAY).getIndex();
            int variableLengthFieldOffset
                    = in.readInt(variableLengthFieldOffsetsStart + fieldOffsetIndex * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            int length = getLength(fieldOffsetIndex, variableLengthFieldOffset);
            if (length <= index) {
                return null;
            }
            in.position(variableLengthFieldOffset + offset + (index * LOCAL_DATE.getTypeSize()));
            return IOUtil.readLocalDate(in);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public LocalDateTime readLocalDateTimeFromArray(@Nonnull String fieldName, int index) {
        try {
            int fieldOffsetIndex = getFieldDefinition(fieldName, LOCAL_DATE_TIME_ARRAY).getIndex();
            int variableLengthFieldOffset
                    = in.readInt(variableLengthFieldOffsetsStart + fieldOffsetIndex * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            int length = getLength(fieldOffsetIndex, variableLengthFieldOffset);
            if (length <= index) {
                return null;
            }
            in.position(variableLengthFieldOffset + offset + (index * LOCAL_DATE_TIME.getTypeSize()));
            return IOUtil.readLocalDateTime(in);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public OffsetDateTime readOffsetDateTimeFromArray(@Nonnull String fieldName, int index) {
        try {
            int fieldOffsetIndex = getFieldDefinition(fieldName, OFFSET_DATE_TIME_ARRAY).getIndex();
            int variableLengthFieldOffset
                    = in.readInt(variableLengthFieldOffsetsStart + fieldOffsetIndex * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            int length = getLength(fieldOffsetIndex, variableLengthFieldOffset);
            if (length <= index) {
                return null;
            }
            in.position(variableLengthFieldOffset + offset + (index * OFFSET_DATE_TIME.getTypeSize()));
            return IOUtil.readOffsetDateTime(in);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public Object readObjectFromArray(@Nonnull String fieldName, int index) {
        return readObjectFromArrayField(fieldName, OBJECT_ARRAY, serializer::readObject, index);
    }

    private <T> T readObjectFromArrayField(@Nonnull String fieldName, FieldType fieldType,
                                           VariableLengthFieldReader<BufferObjectDataInput, T> reader, int index) {
        int currentPos = in.position();
        try {
            int fieldOffsetIndex = getFieldDefinition(fieldName, fieldType).getIndex();
            int variableLengthFieldOffset = in.readInt(variableLengthFieldOffsetsStart + fieldOffsetIndex * INT_SIZE_IN_BYTES);
            if (variableLengthFieldOffset < 0) {
                return null;
            }
            in.position(variableLengthFieldOffset + offset);
            int length = getLength(fieldOffsetIndex, variableLengthFieldOffset);
            if (length <= index) {
                return null;
            }
            in.position(variableLengthFieldOffset + offset + (index) * Bits.INT_SIZE_IN_BYTES);
            int indexedItemPosition = in.readInt();
            int nextIndexedItemPosition = in.readInt();
            in.position(indexedItemPosition);
            return reader.read(in, nextIndexedItemPosition - indexedItemPosition);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    protected Object getClassIdentifier() {
        return schema.getClassName();
    }

    protected IllegalStateException illegalStateException(IOException e) {
        return new IllegalStateException("IOException is not expected since we read from a well known format and position");
    }

    public static byte[] readByteArray(ObjectDataInput in, int size) throws IOException {
        if (size == NULL_ARRAY_LENGTH) {
            return null;
        }
        if (size > 0) {
            byte[] b = new byte[size];
            in.readFully(b);
            return b;
        }
        return new byte[0];
    }

    public static boolean[] readBooleanArray(ObjectDataInput in, int size) throws IOException {
        if (size == NULL_ARRAY_LENGTH) {
            return null;
        }
        if (size > 0) {
            boolean[] values = new boolean[size];
            for (int i = 0; i < size; i++) {
                values[i] = in.readBoolean();
            }
            return values;
        }
        return new boolean[0];
    }

    public static char[] readCharArray(ObjectDataInput in, int size) throws IOException {
        if (size == NULL_ARRAY_LENGTH) {
            return null;
        }
        if (size > 0) {
            int len = size >> 1;
            char[] values = new char[len];
            for (int i = 0; i < len; i++) {
                values[i] = in.readChar();
            }
            return values;
        }
        return new char[0];
    }

    public static int[] readIntArray(ObjectDataInput in, int size) throws IOException {
        if (size == NULL_ARRAY_LENGTH) {
            return null;
        }
        if (size > 0) {
            int len = size >> 2;
            int[] values = new int[len];
            for (int i = 0; i < len; i++) {
                values[i] = in.readInt();
            }
            return values;
        }
        return new int[0];
    }

    public static long[] readLongArray(ObjectDataInput in, int size) throws IOException {
        if (size == NULL_ARRAY_LENGTH) {
            return null;
        }
        if (size > 0) {
            int len = size >> 3;
            long[] values = new long[len];
            for (int i = 0; i < len; i++) {
                values[i] = in.readLong();
            }
            return values;
        }
        return new long[0];
    }

    public static double[] readDoubleArray(ObjectDataInput in, int size) throws IOException {
        if (size == NULL_ARRAY_LENGTH) {
            return null;
        }
        if (size > 0) {
            int len = size >> 3;
            double[] values = new double[len];
            for (int i = 0; i < len; i++) {
                values[i] = in.readDouble();
            }
            return values;
        }
        return new double[0];
    }

    public static float[] readFloatArray(ObjectDataInput in, int size) throws IOException {
        if (size == NULL_ARRAY_LENGTH) {
            return null;
        }
        if (size > 0) {
            int len = size >> 2;
            float[] values = new float[len];
            for (int i = 0; i < len; i++) {
                values[i] = in.readFloat();
            }
            return values;
        }
        return new float[0];
    }

    public static short[] readShortArray(ObjectDataInput in, int size) throws IOException {
        if (size == NULL_ARRAY_LENGTH) {
            return null;
        }
        if (size > 0) {
            int len = size >> 1;
            short[] values = new short[len];
            for (int i = 0; i < len; i++) {
                values[i] = in.readShort();
            }
            return values;
        }
        return new short[0];
    }

    public static LocalDate[] readLocalDateArray(ObjectDataInput in, int size) throws IOException {
        if (size == NULL_ARRAY_LENGTH) {
            return null;
        }
        if (size > 0) {
            int len = size >> 2;
            LocalDate[] values = new LocalDate[len];
            for (int i = 0; i < len; i++) {
                values[i] = IOUtil.readLocalDate(in);
            }
            return values;
        }
        return new LocalDate[0];
    }

    public static LocalTime[] readLocalTimeArray(ObjectDataInput in, int size) throws IOException {
        if (size == NULL_ARRAY_LENGTH) {
            return null;
        }
        if (size > 0) {
            int len = size / 7;
            LocalTime[] values = new LocalTime[len];
            for (int i = 0; i < len; i++) {
                values[i] = IOUtil.readLocalTime(in);
            }
            return values;
        }
        return new LocalTime[0];
    }

    public static LocalDateTime[] readLocalDateTimeArray(ObjectDataInput in, int size) throws IOException {
        if (size == NULL_ARRAY_LENGTH) {
            return null;
        }
        if (size > 0) {
            int len = size >> 3;
            LocalDateTime[] values = new LocalDateTime[len];
            for (int i = 0; i < len; i++) {
                values[i] = IOUtil.readLocalDateTime(in);
            }
            return values;
        }
        return new LocalDateTime[0];
    }

    public static OffsetDateTime[] readOffsetDateTimeArray(ObjectDataInput in, int size) throws IOException {
        if (size == NULL_ARRAY_LENGTH) {
            return null;
        }
        if (size > 0) {
            int len = size / 9;
            OffsetDateTime[] values = new OffsetDateTime[len];
            for (int i = 0; i < len; i++) {
                values[i] = IOUtil.readOffsetDateTime(in);
            }
            return values;
        }
        return new OffsetDateTime[0];
    }

    public static BigDecimal readBigDecimal(ObjectDataInput in, int size) throws IOException {
        final byte[] bytes = new byte[size - INT_SIZE_IN_BYTES];
        in.readFully(bytes);
        BigInteger bigInteger = new BigInteger(bytes);
        int scale = in.readInt();
        return new BigDecimal(bigInteger, scale);
    }

    public static BigInteger readBigInteger(ObjectDataInput in, int size) throws IOException {
        final byte[] bytes = new byte[size];
        in.readFully(bytes);
        return new BigInteger(bytes);
    }

    @Override
    public String toString() {
        return "CompactGenericRecord{" +
                "schema=" + schema +
                ", finalPosition=" + finalPosition +
                ", offset=" + offset +
                '}';
    }
}