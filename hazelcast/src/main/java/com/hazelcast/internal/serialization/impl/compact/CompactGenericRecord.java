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
import com.hazelcast.nio.serialization.FieldType;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.HazelcastSerializationException;
import com.hazelcast.nio.serialization.compact.Compact;
import com.hazelcast.nio.serialization.compact.CompactGenericRecordCloner;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.Schema;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.function.Function;

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
import static com.hazelcast.nio.serialization.FieldType.OFFSET_DATE_TIME;
import static com.hazelcast.nio.serialization.FieldType.OFFSET_DATE_TIME_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.PORTABLE;
import static com.hazelcast.nio.serialization.FieldType.PORTABLE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.SHORT;
import static com.hazelcast.nio.serialization.FieldType.SHORT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.UTF;
import static com.hazelcast.nio.serialization.FieldType.UTF_ARRAY;

public class CompactGenericRecord implements InternalGenericRecord, CompactReader {

    private static final int NULL_POSITION = -1;
    private final Schema schema;
    private final Compact serializer;
    private final int classID;

    private final BufferObjectDataInput in;
    private final int finalPosition;
    private final int offset;

    public CompactGenericRecord(Compact serializer, int classID, BufferObjectDataInput in, Schema schema) {
        this.in = in;
        this.serializer = serializer;
        this.schema = schema;
        this.classID = classID;

        try {
            offset = in.position();
            finalPosition = in.readInt(offset) + offset;
            in.position(finalPosition);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    public BufferObjectDataInput getIn() {
        return  in;
    }

    @Override
    public Schema getSchema() {
        return schema;
    }

    public int getClassID() {
        return classID;
    }

    @Override
    public GenericRecord.Builder newBuilder() {
        return GenericRecord.Builder.compact(serializer, classID, schema);
    }

    @Override
    public Builder cloneWithBuilder() {
        return new CompactGenericRecordCloner(serializer, schema, classID, this,
                bytes -> serializer.getInternalSerializationService().createObjectDataInput(bytes),
                () -> serializer.getInternalSerializationService().createObjectDataOutput());
    }

    @Override
    public FieldType getFieldType(@Nonnull String fieldName) {
        return schema.getField(fieldName).getType();
    }

    @Override
    public byte readByte(@Nonnull String fieldName) {
        try {
            return in.readByte(readPrimitivePosition(fieldName, BYTE));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
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
    public int readInt(@Nonnull String fieldName) {
        try {
            return in.readInt(readPrimitivePosition(fieldName, INT));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
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
    public float readFloat(@Nonnull String fieldName) {
        try {
            return in.readFloat(readPrimitivePosition(fieldName, FLOAT));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
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
    public boolean readBoolean(@Nonnull String fieldName) {
        try {
            return in.readBoolean(readPrimitivePosition(fieldName, BOOLEAN));
        } catch (IOException e) {
            throw illegalStateException(e);
        }
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
    public String readUTF(@Nonnull String fieldName) {
        //TODO SANCAR can we use readNullableField as we do in BigInteger etc.
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
    public BigDecimal readBigDecimal(@Nonnull String fieldName) {
        return readNullableField(fieldName, BIG_DECIMAL, IOUtil::readBigDecimal);
    }

    @Override
    public LocalTime readLocalTime(@Nonnull String fieldName) {
        return readNullableField(fieldName, LOCAL_TIME, IOUtil::readLocalTime);
    }

    @Override
    public LocalDate readLocalDate(@Nonnull String fieldName) {
        return readNullableField(fieldName, LOCAL_DATE, IOUtil::readLocalDate);
    }

    @Override
    public LocalDateTime readLocalDateTime(@Nonnull String fieldName) {
        return readNullableField(fieldName, LOCAL_DATE_TIME, IOUtil::readLocalDateTime);
    }

    @Override
    public OffsetDateTime readOffsetDateTime(@Nonnull String fieldName) {
        return readNullableField(fieldName, OFFSET_DATE_TIME, IOUtil::readOffsetDateTime);
    }

    @Override
    public GenericRecord readGenericRecord(@Nonnull String fieldName) {
        return readNullableField(fieldName, PORTABLE, serializer::readGenericRecord);
    }

    @Override
    public <T> T readObject(@Nonnull String fieldName) {
        return readNullableField(fieldName, PORTABLE, serializer::readObject);
    }

    @Override
    public boolean hasField(@Nonnull String fieldName) {
        return schema.hasField(fieldName);
    }

    @Override
    public byte[] readByteArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, BYTE_ARRAY, ObjectDataInput::readByteArray);
    }

    @Override
    public boolean[] readBooleanArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, BOOLEAN_ARRAY, ObjectDataInput::readBooleanArray);
    }

    @Override
    public char[] readCharArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, CHAR_ARRAY, ObjectDataInput::readCharArray);
    }

    @Override
    public int[] readIntArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, INT_ARRAY, ObjectDataInput::readIntArray);
    }

    @Override
    public long[] readLongArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, LONG_ARRAY, ObjectDataInput::readLongArray);
    }

    @Override
    public double[] readDoubleArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, DOUBLE_ARRAY, ObjectDataInput::readDoubleArray);
    }

    @Override
    public float[] readFloatArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, FLOAT_ARRAY, ObjectDataInput::readFloatArray);
    }

    @Override
    public short[] readShortArray(@Nonnull String fieldName) {
        return readNullableField(fieldName, SHORT_ARRAY, ObjectDataInput::readShortArray);
    }

    @Override
    public String[] readUTFArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, UTF_ARRAY, String[]::new, DataInput::readUTF);
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

    @Override
    public GenericRecord[] readGenericRecordArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, PORTABLE_ARRAY, GenericRecord[]::new, serializer::readGenericRecord);
    }

    @Override
    public Object[] readObjectArray(@Nonnull String fieldName) {
        return readObjectArrayField(fieldName, PORTABLE_ARRAY, Object[]::new, serializer::readObject);
    }

    @Override
    public <T> ArrayList<T> readObjectArrayList(@Nonnull String fieldName) {
        int currentPos = in.position();
        try {
            int beginOffset = readPosition(fieldName, PORTABLE_ARRAY);
            if (beginOffset == Bits.NULL_ARRAY_LENGTH) {
                return null;
            }
            in.position(beginOffset);
            int len = in.readInt();

            ArrayList<T> objects = new ArrayList<>(len);
            for (int i = 0; i < len; i++) {
                int pos = in.readInt((beginOffset + INT_SIZE_IN_BYTES) + i * INT_SIZE_IN_BYTES);
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
        FieldDefinitionImpl fd = getFieldDefinition(fieldName, fieldType);
        int primitiveOffset = fd.getOffset();
        return primitiveOffset + offset;
    }

    @NotNull
    private FieldDefinitionImpl getFieldDefinition(@Nonnull String fieldName, FieldType fieldType) {
        FieldDefinitionImpl fd = (FieldDefinitionImpl) schema.getField(fieldName);
        if (fd == null) {
            throw throwUnknownFieldException(fieldName);
        }
        if (fd.getType() != fieldType) {
            throw new HazelcastSerializationException("Not a '" + fieldType + "' field: " + fieldName);
        }
        return fd;
    }

    private int readPosition(@Nonnull String fieldName, FieldType fieldType) {
        try {
            FieldDefinitionImpl fd = getFieldDefinition(fieldName, fieldType);
            int index = fd.getIndex();
            int pos = in.readInt(finalPosition - (index + 1) * INT_SIZE_IN_BYTES);
            return pos == NULL_POSITION ? NULL_POSITION : pos + offset;
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    private HazelcastSerializationException throwUnknownFieldException(@Nonnull String fieldName) {
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
        return readObjectFromArrayField(fieldName, PORTABLE_ARRAY, serializer::readGenericRecord, index);
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
        return readObjectFromArrayField(fieldName, PORTABLE_ARRAY, serializer::readObject, index);
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompactGenericRecord that = (CompactGenericRecord) o;
        if (finalPosition - offset != that.finalPosition - that.offset) {
            return false;
        }
        int thatIndex = that.offset;
        for (int thisIndex = offset; thisIndex < finalPosition; thisIndex++, thatIndex++) {
            try {
                if (in.readByte(thisIndex) != that.in.readByte(thatIndex)) {
                    return false;
                }
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    private IllegalStateException illegalStateException(IOException e) {
        return new IllegalStateException("IOException is not expected since we read from a well known format and position");
    }

    @Override
    public int hashCode() {
        int result = 1;
        try {
            for (int i = offset; i < finalPosition; i++) {
                result = 31 * result + in.readByte(i);
            }
        } catch (IOException e) {
            assert false;
        }
        return result;
    }

    @Override
    public String toString() {
        return "CompactGenericRecord{" +
                "schema=" + schema +
                ", classID=" + classID +
                ", finalPosition=" + finalPosition +
                ", offset=" + offset +
                '}';
    }
}