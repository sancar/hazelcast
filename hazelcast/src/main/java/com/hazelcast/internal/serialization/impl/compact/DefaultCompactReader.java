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
import java.io.DataInput;
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

    protected static final int NULL_POSITION = -1;
    private final Compact serializer;

    protected final SchemaImpl schema;
    protected final BufferObjectDataInput in;
    protected final int finalPosition;
    protected final int offset;

    private final @Nullable
    Class associatedClass;

    public DefaultCompactReader(Compact serializer, BufferObjectDataInput in, Schema schema, @Nullable Class associatedClass) {
        this.in = in;
        this.serializer = serializer;
        this.schema = (SchemaImpl) schema;
        this.associatedClass = associatedClass;

        try {
            offset = in.position();
            finalPosition = in.readInt(offset) + offset;
            in.position(finalPosition);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
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
        return new DeserializedSchemaBoundGenericRecordBuilder(schema);
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
        //TODO sancar can we use readNullableField as we do in BigInteger etc.
        //in.readUTF already supports nullable
        final int currentPos = in.position();
        try {
            int pos = readPosition(fieldName, UTF);
            in.position(pos);
            return in.readUTF();
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public String readUTF(String fieldName, String defaultValue) {
        return isFieldExists(fieldName, UTF) ? readUTF(fieldName) : defaultValue;
    }

    private <T> T readNullableField(@Nonnull String fieldName, FieldType fieldType, Reader<ObjectDataInput, T> reader) {
        int currentPos = in.position();
        try {
            int pos = readPosition(fieldName, fieldType);
            if (pos == NULL_POSITION) {
                return null;
            }
            in.position(pos);
            return reader.read(in);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public BigInteger readBigInteger(@Nonnull String fieldName) {
        return readNullableField(fieldName, BIG_INTEGER, IOUtil::readBigInteger);
    }

    @Override
    public BigInteger readBigInteger(String fieldName, BigInteger defaultValue) {
        return isFieldExists(fieldName, BIG_INTEGER) ? readBigInteger(fieldName) : defaultValue;
    }

    @Override
    public BigDecimal readBigDecimal(@Nonnull String fieldName) {
        return readNullableField(fieldName, BIG_DECIMAL, IOUtil::readBigDecimal);
    }

    @Override
    public BigDecimal readBigDecimal(String fieldName, BigDecimal defaultValue) {
        return isFieldExists(fieldName, BIG_DECIMAL) ? readBigDecimal(fieldName) : defaultValue;
    }

    @Override
    public LocalTime readLocalTime(@Nonnull String fieldName) {
        return readNullableField(fieldName, LOCAL_TIME, IOUtil::readLocalTime);
    }

    @Override
    public LocalTime readLocalTime(String fieldName, LocalTime defaultValue) {
        return isFieldExists(fieldName, LOCAL_TIME) ? readLocalTime(fieldName) : defaultValue;
    }

    @Override
    public LocalDate readLocalDate(@Nonnull String fieldName) {
        return readNullableField(fieldName, LOCAL_DATE, IOUtil::readLocalDate);
    }

    @Override
    public LocalDate readLocalDate(String fieldName, LocalDate defaultValue) {
        return isFieldExists(fieldName, LOCAL_DATE) ? readLocalDate(fieldName) : defaultValue;
    }

    @Override
    public LocalDateTime readLocalDateTime(@Nonnull String fieldName) {
        return readNullableField(fieldName, LOCAL_DATE_TIME, IOUtil::readLocalDateTime);
    }

    @Override
    public LocalDateTime readLocalDateTime(String fieldName, LocalDateTime defaultValue) {
        return isFieldExists(fieldName, LOCAL_DATE_TIME) ? readLocalDateTime(fieldName) : defaultValue;
    }

    @Override
    public OffsetDateTime readOffsetDateTime(@Nonnull String fieldName) {
        return readNullableField(fieldName, OFFSET_DATE_TIME, IOUtil::readOffsetDateTime);
    }

    @Override
    public OffsetDateTime readOffsetDateTime(String fieldName, OffsetDateTime defaultValue) {
        return isFieldExists(fieldName, OFFSET_DATE_TIME) ? readOffsetDateTime(fieldName) : defaultValue;
    }

    @Override
    public GenericRecord readGenericRecord(@Nonnull String fieldName) {
        return readNullableField(fieldName, OBJECT, serializer::readGenericRecord);
    }

    @Override
    public <T> T readObject(@Nonnull String fieldName) {
        return readNullableField(fieldName, OBJECT, serializer::readObject);
    }

    @Override
    public <T> T readObject(String fieldName, T defaultValue) {
        return isFieldExists(fieldName, OBJECT) ? readObject(fieldName) : defaultValue;
    }

    @Override
    public byte[] readByteArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, BYTE_ARRAY, ObjectDataInput::readByteArray);
    }

    @Override
    public byte[] readByteArray(String fieldName, byte[] defaultValue) {
        return isFieldExists(fieldName, BYTE_ARRAY) ? readByteArray(fieldName) : defaultValue;
    }

    @Override
    public boolean[] readBooleanArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, BOOLEAN_ARRAY, ObjectDataInput::readBooleanArray);
    }

    @Override
    public boolean[] readBooleanArray(String fieldName, boolean[] defaultValue) {
        return isFieldExists(fieldName, BOOLEAN_ARRAY) ? readBooleanArray(fieldName) : defaultValue;
    }

    @Override
    public char[] readCharArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, CHAR_ARRAY, ObjectDataInput::readCharArray);
    }

    @Override
    public char[] readCharArray(String fieldName, char[] defaultValue) {
        return isFieldExists(fieldName, CHAR_ARRAY) ? readCharArray(fieldName) : defaultValue;
    }

    @Override
    public int[] readIntArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, INT_ARRAY, ObjectDataInput::readIntArray);
    }

    @Override
    public int[] readIntArray(String fieldName, int[] defaultValue) {
        return isFieldExists(fieldName, INT_ARRAY) ? readIntArray(fieldName) : defaultValue;
    }

    @Override
    public long[] readLongArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, LONG_ARRAY, ObjectDataInput::readLongArray);
    }

    @Override
    public long[] readLongArray(String fieldName, long[] defaultValue) {
        return isFieldExists(fieldName, LONG_ARRAY) ? readLongArray(fieldName) : defaultValue;
    }

    @Override
    public double[] readDoubleArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, DOUBLE_ARRAY, ObjectDataInput::readDoubleArray);
    }

    @Override
    public double[] readDoubleArray(String fieldName, double[] defaultValue) {
        return isFieldExists(fieldName, DOUBLE_ARRAY) ? readDoubleArray(fieldName) : defaultValue;
    }

    @Override
    public float[] readFloatArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, FLOAT_ARRAY, ObjectDataInput::readFloatArray);
    }

    @Override
    public float[] readFloatArray(String fieldName, float[] defaultValue) {
        return isFieldExists(fieldName, FLOAT_ARRAY) ? readFloatArray(fieldName) : defaultValue;
    }

    @Override
    public short[] readShortArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, SHORT_ARRAY, ObjectDataInput::readShortArray);
    }

    @Override
    public short[] readShortArray(String fieldName, short[] defaultValue) {
        return isFieldExists(fieldName, SHORT_ARRAY) ? readShortArray(fieldName) : defaultValue;
    }

    @Override
    public String[] readUTFArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, UTF_ARRAY, String[]::new, DataInput::readUTF);
    }

    @Override
    public String[] readUTFArray(String fieldName, String[] defaultValue) {
        return isFieldExists(fieldName, UTF_ARRAY) ? readUTFArray(fieldName) : defaultValue;
    }

    @Override
    public BigInteger[] readBigIntegerArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, BIG_INTEGER_ARRAY, BigInteger[]::new, IOUtil::readBigInteger);
    }

    @Override
    public BigInteger[] readBigIntegerArray(String fieldName, BigInteger[] defaultValue) {
        return isFieldExists(fieldName, BIG_INTEGER_ARRAY) ? readBigIntegerArray(fieldName) : defaultValue;
    }

    @Override
    public BigDecimal[] readBigDecimalArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, BIG_DECIMAL_ARRAY, BigDecimal[]::new, IOUtil::readBigDecimal);
    }

    @Override
    public BigDecimal[] readBigDecimalArray(String fieldName, BigDecimal[] defaultValue) {
        return isFieldExists(fieldName, BIG_DECIMAL_ARRAY) ? readBigDecimalArray(fieldName) : defaultValue;
    }

    @Override
    public LocalTime[] readLocalTimeArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, LOCAL_TIME_ARRAY, LocalTime[]::new, IOUtil::readLocalTime);
    }

    @Override
    public LocalTime[] readLocalTimeArray(String fieldName, LocalTime[] defaultValue) {
        return isFieldExists(fieldName, LOCAL_TIME_ARRAY) ? readLocalTimeArray(fieldName) : defaultValue;
    }

    @Override
    public LocalDate[] readLocalDateArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, LOCAL_DATE_ARRAY, LocalDate[]::new, IOUtil::readLocalDate);
    }

    @Override
    public LocalDate[] readLocalDateArray(String fieldName, LocalDate[] defaultValue) {
        return isFieldExists(fieldName, LOCAL_DATE_ARRAY) ? readLocalDateArray(fieldName) : defaultValue;
    }

    @Override
    public LocalDateTime[] readLocalDateTimeArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, LOCAL_DATE_TIME_ARRAY, LocalDateTime[]::new, IOUtil::readLocalDateTime);
    }

    @Override
    public LocalDateTime[] readLocalDateTimeArray(String fieldName, LocalDateTime[] defaultValue) {
        return isFieldExists(fieldName, LOCAL_DATE_TIME_ARRAY) ? readLocalDateTimeArray(fieldName) : defaultValue;
    }

    @Override
    public OffsetDateTime[] readOffsetDateTimeArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, OFFSET_DATE_TIME_ARRAY, OffsetDateTime[]::new, IOUtil::readOffsetDateTime);
    }

    @Override
    public OffsetDateTime[] readOffsetDateTimeArray(String fieldName, OffsetDateTime[] defaultValue) {
        return isFieldExists(fieldName, OFFSET_DATE_TIME_ARRAY) ? readOffsetDateTimeArray(fieldName) : defaultValue;
    }

    @Override
    public GenericRecord[] readGenericRecordArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, OBJECT_ARRAY, GenericRecord[]::new, serializer::readGenericRecord);
    }

    @Override
    public Object[] readObjectArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, OBJECT_ARRAY, Object[]::new, serializer::readObject);
    }

    @Override
    public Object[] readObjectArray(String fieldName, Object[] defaultValue) {
        return isFieldExists(fieldName, OBJECT_ARRAY) ? readObjectArray(fieldName) : defaultValue;
    }

    @Override
    public <T> List<T> readObjectList(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            int position = readPosition(fieldName, OBJECT_ARRAY);
            if (position == Bits.NULL_ARRAY_LENGTH) {
                return null;
            }
            in.position(position);
            int len = in.readInt();
            ArrayList<T> objects = new ArrayList<>(len);
            int offset = in.position();
            for (int i = 0; i < len; i++) {
                int pos = in.readInt(offset + i * Bits.INT_SIZE_IN_BYTES);
                if (pos != Bits.NULL_ARRAY_LENGTH) {
                    in.position(pos);
                    objects.add(serializer.readObject(in));
                }
            }
            return objects;
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

    protected interface Reader<T, R> {
        R read(T t) throws IOException;
    }

    private <T> T[] readObjectArrayField(@Nonnull String fieldName, FieldType fieldType, Function<Integer, T[]> constructor,
                                         Reader<ObjectDataInput, T> reader) {
        int currentPos = in.position();
        try {
            int position = readPosition(fieldName, fieldType);
            if (position == Bits.NULL_ARRAY_LENGTH) {
                return null;
            }
            in.position(position);
            int len = in.readInt();
            T[] values = constructor.apply(len);
            int offset = in.position();
            for (int i = 0; i < len; i++) {
                int pos = in.readInt(offset + i * Bits.INT_SIZE_IN_BYTES);
                if (pos != Bits.NULL_ARRAY_LENGTH) {
                    in.position(pos);
                    values[i] = reader.read(in);
                }
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
        return primitiveOffset + offset;
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
            int pos = in.readInt(finalPosition - (index + 1) * INT_SIZE_IN_BYTES);
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

    private boolean doesNotHaveIndex(int beginPosition, int index) {
        try {
            int numberOfItems = in.readInt(beginPosition);
            return numberOfItems <= index;
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public Byte readByteFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, BYTE_ARRAY);
        if (position == NULL_POSITION || doesNotHaveIndex(position, index)) {
            return null;
        }
        try {
            return in.readByte(INT_SIZE_IN_BYTES + position + (index * BYTE_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public Boolean readBooleanFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, BOOLEAN_ARRAY);
        if (position == NULL_POSITION || doesNotHaveIndex(position, index)) {
            return null;
        }
        try {
            return in.readBoolean(INT_SIZE_IN_BYTES + position + (index * BOOLEAN_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public Character readCharFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, CHAR_ARRAY);
        if (position == NULL_POSITION || doesNotHaveIndex(position, index)) {
            return null;
        }
        try {
            return in.readChar(INT_SIZE_IN_BYTES + position + (index * CHAR_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public Integer readIntFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, INT_ARRAY);
        if (position == NULL_POSITION || doesNotHaveIndex(position, index)) {
            return null;
        }
        try {
            return in.readInt(INT_SIZE_IN_BYTES + position + (index * INT_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public Long readLongFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, LONG_ARRAY);
        if (position == NULL_POSITION || doesNotHaveIndex(position, index)) {
            return null;
        }
        try {
            return in.readLong(INT_SIZE_IN_BYTES + position + (index * LONG_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public Double readDoubleFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, DOUBLE_ARRAY);
        if (position == NULL_POSITION || doesNotHaveIndex(position, index)) {
            return null;
        }
        try {
            return in.readDouble(INT_SIZE_IN_BYTES + position + (index * DOUBLE_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public Float readFloatFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, FLOAT_ARRAY);
        if (position == NULL_POSITION || doesNotHaveIndex(position, index)) {
            return null;
        }
        try {
            return in.readFloat(INT_SIZE_IN_BYTES + position + (index * FLOAT_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public Short readShortFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, SHORT_ARRAY);
        if (position == NULL_POSITION || doesNotHaveIndex(position, index)) {
            return null;
        }
        try {
            return in.readShort(INT_SIZE_IN_BYTES + position + (index * SHORT_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public String readUTFFromArray(@Nonnull String fieldName, int index) {
        return readObjectFromArrayField(fieldName, UTF_ARRAY, ObjectDataInput::readUTF, index);
    }

    @Override
    public GenericRecord readGenericRecordFromArray(@Nonnull String fieldName, int index) {
        return readObjectFromArrayField(fieldName, OBJECT_ARRAY, serializer::readGenericRecord, index);
    }

    @Override
    public BigInteger readBigIntegerFromArray(@Nonnull String fieldName, int index) {
        return readObjectFromArrayField(fieldName, BIG_INTEGER_ARRAY, IOUtil::readBigInteger, index);
    }

    @Override
    public BigDecimal readBigDecimalFromArray(@Nonnull String fieldName, int index) {
        return readObjectFromArrayField(fieldName, BIG_DECIMAL_ARRAY, IOUtil::readBigDecimal, index);
    }

    @Override
    public LocalTime readLocalTimeFromArray(@Nonnull String fieldName, int index) {
        return readObjectFromArrayField(fieldName, LOCAL_TIME_ARRAY, IOUtil::readLocalTime, index);
    }

    @Override
    public LocalDate readLocalDateFromArray(@Nonnull String fieldName, int index) {
        return readObjectFromArrayField(fieldName, LOCAL_DATE_ARRAY, IOUtil::readLocalDate, index);
    }

    @Override
    public LocalDateTime readLocalDateTimeFromArray(@Nonnull String fieldName, int index) {
        return readObjectFromArrayField(fieldName, LOCAL_DATE_TIME_ARRAY, IOUtil::readLocalDateTime, index);
    }

    @Override
    public OffsetDateTime readOffsetDateTimeFromArray(@Nonnull String fieldName, int index) {
        return readObjectFromArrayField(fieldName, OFFSET_DATE_TIME_ARRAY, IOUtil::readOffsetDateTime, index);
    }

    @Override
    public Object readObjectFromArray(@Nonnull String fieldName, int index) {
        return readObjectFromArrayField(fieldName, OBJECT_ARRAY, serializer::readObject, index);
    }

    private <T> T readObjectFromArrayField(@Nonnull String fieldName, FieldType fieldType,
                                           Reader<ObjectDataInput, T> reader, int index) {
        int currentPos = in.position();
        try {
            int pos = readPosition(fieldName, fieldType);
            if (pos == NULL_POSITION || doesNotHaveIndex(pos, index)) {
                return null;
            }
            int indexedItemPosition = in.readInt((pos + INT_SIZE_IN_BYTES) + index * INT_SIZE_IN_BYTES);
            if (indexedItemPosition != NULL_POSITION) {
                in.position(indexedItemPosition);
                return reader.read(in);
            }
            return null;
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

    @Override
    public String toString() {
        return "CompactGenericRecord{" +
                "schema=" + schema +
                ", finalPosition=" + finalPosition +
                ", offset=" + offset +
                '}';
    }
}