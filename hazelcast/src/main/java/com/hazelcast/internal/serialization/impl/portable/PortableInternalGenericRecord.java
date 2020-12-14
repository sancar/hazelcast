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

package com.hazelcast.internal.serialization.impl.portable;

import com.hazelcast.internal.nio.Bits;
import com.hazelcast.internal.nio.BufferObjectDataInput;
import com.hazelcast.internal.nio.IOUtil;
import com.hazelcast.internal.serialization.impl.InternalGenericRecord;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.serialization.AbstractGenericRecord;
import com.hazelcast.nio.serialization.ClassDefinition;
import com.hazelcast.nio.serialization.FieldDefinition;
import com.hazelcast.nio.serialization.FieldType;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.HazelcastSerializationException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.function.Function;

import static com.hazelcast.internal.nio.Bits.BOOLEAN_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.BYTE_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.CHAR_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.DOUBLE_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.FLOAT_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.INT_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.LONG_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.SHORT_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.combineToLong;
import static com.hazelcast.nio.serialization.FieldType.BIG_DECIMAL_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.BIG_INTEGER_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.LOCAL_DATE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.LOCAL_DATE_TIME_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.LOCAL_TIME_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.OFFSET_DATE_TIME_ARRAY;

public class PortableInternalGenericRecord extends AbstractGenericRecord implements InternalGenericRecord {
    protected final ClassDefinition cd;
    protected final PortableSerializer serializer;

    private final BufferObjectDataInput in;
    private final int offset;
    private final boolean readGenericLazy;

    PortableInternalGenericRecord(PortableSerializer serializer, BufferObjectDataInput in,
                                  ClassDefinition cd, boolean readGenericLazy) {
        this.in = in;
        this.serializer = serializer;
        this.cd = cd;
        this.readGenericLazy = readGenericLazy;

        int fieldCount;
        try {
            // final position after portable is read
            in.readInt();
            // field count
            fieldCount = in.readInt();
        } catch (IOException e) {
            throw illegalStateException(e);
        }
        if (fieldCount != cd.getFieldCount()) {
            throw new IllegalStateException("Field count[" + fieldCount + "] in stream does not match " + cd);
        }
        this.offset = in.position();
    }

    public ClassDefinition getClassDefinition() {
        return cd;
    }

    public int getVersion() {
        return cd.getVersion();
    }

    @Override
    public boolean hasField(@Nonnull String fieldName) {
        return cd.hasField(fieldName);
    }

    @Nonnull
    @Override
    public Set<String> getFieldNames() {
        return cd.getFieldNames();
    }

    @Override
    @Nonnull
    public FieldType getFieldType(@Nonnull String fieldName) {
        return cd.getFieldType(fieldName);
    }

    @Override
    public boolean readBoolean(@Nonnull String fieldName) {
        try {
            return in.readBoolean(readPosition(fieldName, FieldType.BOOLEAN));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public byte readByte(@Nonnull String fieldName) {
        try {
            return in.readByte(readPosition(fieldName, FieldType.BYTE));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public char readChar(@Nonnull String fieldName) {
        try {
            return in.readChar(readPosition(fieldName, FieldType.CHAR));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public double readDouble(@Nonnull String fieldName) {
        try {
            return in.readDouble(readPosition(fieldName, FieldType.DOUBLE));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public float readFloat(@Nonnull String fieldName) {
        try {
            return in.readFloat(readPosition(fieldName, FieldType.FLOAT));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public int readInt(@Nonnull String fieldName) {
        try {
            return in.readInt(readPosition(fieldName, FieldType.INT));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public long readLong(@Nonnull String fieldName) {
        try {
            return in.readLong(readPosition(fieldName, FieldType.LONG));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public short readShort(@Nonnull String fieldName) {
        try {
            return in.readShort(readPosition(fieldName, FieldType.SHORT));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public String readUTF(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            int pos = readPosition(fieldName, FieldType.UTF);
            in.position(pos);
            return in.readUTF();
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    protected interface Reader<T, R> {
        R read(T t) throws IOException;
    }

    private <T> T readNullableField(@Nonnull String fieldName, FieldType fieldType, Reader<ObjectDataInput, T> reader) {
        int currentPos = in.position();
        try {
            int pos = readPosition(fieldName, fieldType);
            in.position(pos);
            boolean isNull = in.readBoolean();
            if (isNull) {
                return null;
            }
            return reader.read(in);
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public BigInteger readBigInteger(@Nonnull String fieldName) {
        return readNullableField(fieldName, FieldType.BIG_INTEGER, IOUtil::readBigInteger);
    }

    @Override
    public BigDecimal readBigDecimal(@Nonnull String fieldName) {
        return readNullableField(fieldName, FieldType.BIG_DECIMAL, IOUtil::readBigDecimal);
    }

    @Override
    public LocalTime readLocalTime(@Nonnull String fieldName) {
        return readNullableField(fieldName, FieldType.LOCAL_TIME, IOUtil::readLocalTime);
    }

    @Override
    public LocalDate readLocalDate(@Nonnull String fieldName) {
        return readNullableField(fieldName, FieldType.LOCAL_DATE, IOUtil::readLocalDate);
    }

    @Override
    public LocalDateTime readLocalDateTime(@Nonnull String fieldName) {
        return readNullableField(fieldName, FieldType.LOCAL_DATE_TIME, IOUtil::readLocalDateTime);
    }

    @Override
    public OffsetDateTime readOffsetDateTime(@Nonnull String fieldName) {
        return readNullableField(fieldName, FieldType.OFFSET_DATE_TIME, IOUtil::readOffsetDateTime);
    }

    private boolean isNullOrEmpty(int pos) {
        return pos == -1;
    }

    @Override
    public boolean[] readBooleanArray(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            int position = readPosition(fieldName, FieldType.BOOLEAN_ARRAY);
            if (isNullOrEmpty(position)) {
                return null;
            }
            in.position(position);
            return in.readBooleanArray();
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public byte[] readByteArray(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            int position = readPosition(fieldName, FieldType.BYTE_ARRAY);
            if (isNullOrEmpty(position)) {
                return null;
            }
            in.position(position);
            return in.readByteArray();
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }

    }

    @Override
    public char[] readCharArray(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            int position = readPosition(fieldName, FieldType.CHAR_ARRAY);
            if (isNullOrEmpty(position)) {
                return null;
            }
            in.position(position);
            return in.readCharArray();
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public double[] readDoubleArray(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            int position = readPosition(fieldName, FieldType.DOUBLE_ARRAY);
            if (isNullOrEmpty(position)) {
                return null;
            }
            in.position(position);
            return in.readDoubleArray();
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public float[] readFloatArray(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            int position = readPosition(fieldName, FieldType.FLOAT_ARRAY);
            if (isNullOrEmpty(position)) {
                return null;
            }
            in.position(position);
            return in.readFloatArray();
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public int[] readIntArray(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            int position = readPosition(fieldName, FieldType.INT_ARRAY);
            if (isNullOrEmpty(position)) {
                return null;
            }
            in.position(position);
            return in.readIntArray();
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public long[] readLongArray(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            int position = readPosition(fieldName, FieldType.LONG_ARRAY);
            if (isNullOrEmpty(position)) {
                return null;
            }
            in.position(position);
            return in.readLongArray();
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public short[] readShortArray(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            int position = readPosition(fieldName, FieldType.SHORT_ARRAY);
            if (isNullOrEmpty(position)) {
                return null;
            }
            in.position(position);
            return in.readShortArray();
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public String[] readUTFArray(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            int position = readPosition(fieldName, FieldType.UTF_ARRAY);
            if (isNullOrEmpty(position)) {
                return null;
            }
            in.position(position);
            return in.readUTFArray();
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }


    private <T> T[] readObjectArrayField(@Nonnull String fieldName, FieldType fieldType, Function<Integer, T[]> constructor,
                                         Reader<ObjectDataInput, T> reader) {
        int currentPos = in.position();
        try {
            int position = readPosition(fieldName, fieldType);
            if (isNullOrEmpty(position)) {
                return null;
            }
            in.position(position);
            int len = in.readInt();

            if (len == Bits.NULL_ARRAY_LENGTH) {
                return null;
            }

            T[] values = constructor.apply(len);
            if (len > 0) {
                int offset = in.position();
                for (int i = 0; i < len; i++) {
                    int pos = in.readInt(offset + i * Bits.INT_SIZE_IN_BYTES);
                    if (pos != -1) {
                        in.position(pos);
                        values[i] = reader.read(in);
                    }
                }
            }
            return values;
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public BigInteger[] readBigIntegerArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, BIG_INTEGER_ARRAY, BigInteger[]::new, IOUtil::readBigInteger);
    }

    @Override
    public BigDecimal[] readBigDecimalArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, BIG_DECIMAL_ARRAY, BigDecimal[]::new, IOUtil::readBigDecimal);
    }

    @Override
    public LocalTime[] readLocalTimeArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, LOCAL_TIME_ARRAY, LocalTime[]::new, IOUtil::readLocalTime);
    }

    @Override
    public LocalDate[] readLocalDateArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, LOCAL_DATE_ARRAY, LocalDate[]::new, IOUtil::readLocalDate);
    }

    @Override
    public LocalDateTime[] readLocalDateTimeArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, LOCAL_DATE_TIME_ARRAY, LocalDateTime[]::new, IOUtil::readLocalDateTime);
    }

    @Override
    public OffsetDateTime[] readOffsetDateTimeArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, OFFSET_DATE_TIME_ARRAY, OffsetDateTime[]::new, IOUtil::readOffsetDateTime);
    }

    private void checkFactoryAndClass(FieldDefinition fd, int factoryId, int classId) {
        if (factoryId != fd.getFactoryId()) {
            throw new IllegalArgumentException("Invalid factoryId! Expected: "
                    + fd.getFactoryId() + ", Current: " + factoryId);
        }
        if (classId != fd.getClassId()) {
            throw new IllegalArgumentException("Invalid classId! Expected: "
                    + fd.getClassId() + ", Current: " + classId);
        }
    }


    private int readPosition(@Nonnull String fieldName, FieldType fieldType) {
        FieldDefinition fd = cd.getField(fieldName);
        if (fd == null) {
            throw throwUnknownFieldException(fieldName);
        }
        if (fd.getType() != fieldType) {
            throw new HazelcastSerializationException("Not a '" + fieldType + "' field: " + fieldName);
        }
        return readPosition(fd);
    }

    private IllegalStateException illegalStateException(IOException e) {
        return new IllegalStateException("IOException is not expected since we read from a well known format and position");
    }

    private HazelcastSerializationException throwUnknownFieldException(@Nonnull String fieldName) {
        return new HazelcastSerializationException("Unknown field name: '" + fieldName
                + "' for ClassDefinition {id: " + cd.getClassId() + ", version: " + cd.getVersion() + "}");
    }

    private int readPosition(FieldDefinition fd) {
        try {
            int pos = in.readInt(offset + fd.getIndex() * Bits.INT_SIZE_IN_BYTES);
            short len = in.readShort(pos);
            // name + len + type
            return pos + Bits.SHORT_SIZE_IN_BYTES + len + 1;
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Nonnull
    @Override
    public Builder newBuilder() {
        throw new UnsupportedOperationException();
    }

    @Nonnull
    @Override
    public Builder cloneWithBuilder() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GenericRecord[] readGenericRecordArray(@Nonnull String fieldName) {
        return readNestedArray(fieldName, GenericRecord[]::new, false);
    }

    private <T> T[] readNestedArray(@Nonnull String fieldName, Function<Integer, T[]> constructor, boolean asPortable) {
        int currentPos = in.position();
        try {
            FieldDefinition fd = cd.getField(fieldName);
            if (fd == null) {
                throw throwUnknownFieldException(fieldName);
            }
            if (fd.getType() != FieldType.PORTABLE_ARRAY) {
                throw new HazelcastSerializationException("Not a Portable array field: " + fieldName);
            }

            int position = readPosition(fd);
            if (isNullOrEmpty(position)) {
                return null;
            }
            in.position(position);
            int len = in.readInt();
            int factoryId = in.readInt();
            int classId = in.readInt();

            if (len == Bits.NULL_ARRAY_LENGTH) {
                return null;
            }

            checkFactoryAndClass(fd, factoryId, classId);

            T[] portables = constructor.apply(len);
            if (len > 0) {
                int offset = in.position();
                for (int i = 0; i < len; i++) {
                    int start = in.readInt(offset + i * Bits.INT_SIZE_IN_BYTES);
                    in.position(start);
                    if (asPortable) {
                        portables[i] = serializer.readAsObject(in, factoryId, classId);
                    } else {
                        portables[i] = serializer.readAndInitialize(in, factoryId, classId, readGenericLazy);
                    }
                }
            }
            return portables;
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public GenericRecord readGenericRecord(@Nonnull String fieldName) {
        return readNested(fieldName, false);
    }

    private <T> T readNested(@Nonnull String fieldName, boolean asPortable) {
        int currentPos = in.position();
        try {
            FieldDefinition fd = cd.getField(fieldName);
            if (fd == null) {
                throw throwUnknownFieldException(fieldName);
            }
            if (fd.getType() != FieldType.PORTABLE) {
                throw new HazelcastSerializationException("Not a Portable field: " + fieldName);
            }

            int pos = readPosition(fd);
            in.position(pos);

            boolean isNull = in.readBoolean();
            int factoryId = in.readInt();
            int classId = in.readInt();

            checkFactoryAndClass(fd, factoryId, classId);

            if (!isNull) {
                if (asPortable) {
                    return serializer.readAsObject(in, factoryId, classId);
                } else {
                    return serializer.readAndInitialize(in, factoryId, classId, readGenericLazy);
                }
            }
            return null;
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    private boolean doesNotHaveIndex(int beginPosition, int index) {
        try {
            int numberOfItems = in.readInt(beginPosition);
            return numberOfItems <= index;
        } catch (IOException e) {
            throw illegalStateException(e);
        }

    }

    @Override
    public Byte readByteFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, FieldType.BYTE_ARRAY);
        if (isNullOrEmpty(position) || doesNotHaveIndex(position, index)) {
            return null;
        }
        try {
            return in.readByte(INT_SIZE_IN_BYTES + position + (index * BYTE_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @SuppressFBWarnings({"NP_BOOLEAN_RETURN_NULL"})
    @Override
    public Boolean readBooleanFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, FieldType.BOOLEAN_ARRAY);
        if (isNullOrEmpty(position) || doesNotHaveIndex(position, index)) {
            return null;
        }
        try {
            return in.readBoolean(INT_SIZE_IN_BYTES + position + (index * BOOLEAN_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public Character readCharFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, FieldType.CHAR_ARRAY);
        if (isNullOrEmpty(position) || doesNotHaveIndex(position, index)) {
            return null;
        }
        try {
            return in.readChar(INT_SIZE_IN_BYTES + position + (index * CHAR_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public Double readDoubleFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, FieldType.DOUBLE_ARRAY);
        if (isNullOrEmpty(position) || doesNotHaveIndex(position, index)) {
            return null;
        }
        try {
            return in.readDouble(INT_SIZE_IN_BYTES + position + (index * DOUBLE_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public Float readFloatFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, FieldType.FLOAT_ARRAY);
        if (isNullOrEmpty(position) || doesNotHaveIndex(position, index)) {
            return null;
        }
        try {
            return in.readFloat(INT_SIZE_IN_BYTES + position + (index * FLOAT_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public Integer readIntFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, FieldType.INT_ARRAY);
        if (isNullOrEmpty(position) || doesNotHaveIndex(position, index)) {
            return null;
        }
        try {
            return in.readInt(INT_SIZE_IN_BYTES + position + (index * INT_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public Long readLongFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, FieldType.LONG_ARRAY);
        if (isNullOrEmpty(position) || doesNotHaveIndex(position, index)) {
            return null;
        }
        try {
            return in.readLong(INT_SIZE_IN_BYTES + position + (index * LONG_SIZE_IN_BYTES));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public Short readShortFromArray(@Nonnull String fieldName, int index) {
        int position = readPosition(fieldName, FieldType.SHORT_ARRAY);
        if (isNullOrEmpty(position) || doesNotHaveIndex(position, index)) {
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
        int currentPos = in.position();
        try {
            int pos = readPosition(fieldName, FieldType.UTF_ARRAY);
            in.position(pos);
            int length = in.readInt();
            if (length <= index) {
                return null;
            }
            if (isNullOrEmpty(pos)) {
                return null;
            }
            for (int i = 0; i < index; i++) {
                int itemLength = in.readInt();
                if (itemLength > 0) {
                    in.position(in.position() + itemLength);
                }
            }
            return in.readUTF();
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }

    @Override
    public GenericRecord readGenericRecordFromArray(@Nonnull String fieldName, int index) {
        return readNestedFromArray(fieldName, index, false);
    }

    @Override
    public Object readObjectFromArray(@Nonnull String fieldName, int index) {
        return readNestedFromArray(fieldName, index, true);
    }

    private <T> T readNestedFromArray(@Nonnull String fieldName, int index, boolean asPortable) {
        int currentPos = in.position();
        try {
            FieldDefinition fd = cd.getField(fieldName);
            if (fd == null) {
                throw throwUnknownFieldException(fieldName);
            }
            if (fd.getType() != FieldType.PORTABLE_ARRAY) {
                throw new HazelcastSerializationException("Not a Portable array field: " + fieldName);
            }

            int position = readPosition(fd);
            if (isNullOrEmpty(position)) {
                return null;
            }
            in.position(position);
            int len = in.readInt();
            if (len == Bits.NULL_ARRAY_LENGTH || len == 0 || len <= index) {
                return null;
            }
            int factoryId = in.readInt();
            int classId = in.readInt();

            checkFactoryAndClass(fd, factoryId, classId);

            int offset = in.position();
            int start = in.readInt(offset + index * Bits.INT_SIZE_IN_BYTES);
            in.position(start);
            if (asPortable) {
                return serializer.readAsObject(in, factoryId, classId);
            } else {
                return serializer.readAndInitialize(in, factoryId, classId, readGenericLazy);
            }
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }


    private <T> T readObjectFromArrayField(@Nonnull String fieldName, FieldType fieldType,
                                           Reader<ObjectDataInput, T> reader, int index) {
        int currentPos = in.position();
        try {
            int position = readPosition(fieldName, fieldType);
            if (isNullOrEmpty(position)) {
                return null;
            }
            in.position(position);
            int len = in.readInt();
            if (len == Bits.NULL_ARRAY_LENGTH || len == 0 || len <= index) {
                return null;
            }

            int offset = in.position();
            int pos = in.readInt(offset + index * Bits.INT_SIZE_IN_BYTES);
            if (pos != -1) {
                in.position(pos);
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
    public <T> T[] readObjectArray(@Nonnull String fieldName, Class<T> componentType) {
        return readNestedArray(fieldName, length -> (T[]) Array.newInstance(componentType, length), true);
    }

    @Override
    public Object readObject(@Nonnull String fieldName) {
        return readNested(fieldName, true);
    }

    @Override
    protected Object getClassIdentifier() {
        return combineToLong(cd.getClassId(), cd.getFactoryId());
    }
}
