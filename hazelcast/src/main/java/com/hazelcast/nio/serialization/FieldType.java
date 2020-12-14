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

package com.hazelcast.nio.serialization;

import com.hazelcast.internal.serialization.impl.InternalGenericRecord;
import com.hazelcast.internal.util.function.TriFunction;

import java.util.function.BiFunction;

import static com.hazelcast.internal.nio.Bits.BOOLEAN_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.BYTE_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.CHAR_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.DOUBLE_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.FLOAT_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.INT_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.LONG_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.SHORT_SIZE_IN_BYTES;
import static java.lang.Integer.MAX_VALUE;

public enum FieldType {

    // SINGLE-VALUE TYPES
    PORTABLE(0, MAX_VALUE, (genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObject(fieldName);
    }, GenericRecord::readGenericRecord),//used to represent any nested object
    BYTE(1, BYTE_SIZE_IN_BYTES, GenericRecord::readByte),
    BOOLEAN(2, BOOLEAN_SIZE_IN_BYTES, GenericRecord::readBoolean),
    CHAR(3, CHAR_SIZE_IN_BYTES, GenericRecord::readChar),
    SHORT(4, SHORT_SIZE_IN_BYTES, GenericRecord::readShort),
    INT(5, INT_SIZE_IN_BYTES, GenericRecord::readInt),
    LONG(6, LONG_SIZE_IN_BYTES, GenericRecord::readLong),
    FLOAT(7, FLOAT_SIZE_IN_BYTES, GenericRecord::readFloat),
    DOUBLE(8, DOUBLE_SIZE_IN_BYTES, GenericRecord::readDouble),
    UTF(9, MAX_VALUE, GenericRecord::readUTF),

    // ARRAY TYPES
    PORTABLE_ARRAY(10, MAX_VALUE, PORTABLE, (genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObjectArray(fieldName, Portable.class);
    }, GenericRecord::readGenericRecordArray, InternalGenericRecord::readObjectFromArray),
    BYTE_ARRAY(11, MAX_VALUE, BYTE, GenericRecord::readByteArray, InternalGenericRecord::readByteFromArray),
    BOOLEAN_ARRAY(12, MAX_VALUE, BOOLEAN, GenericRecord::readBooleanArray, InternalGenericRecord::readBooleanFromArray),
    CHAR_ARRAY(13, MAX_VALUE, CHAR, GenericRecord::readCharArray, InternalGenericRecord::readCharFromArray),
    SHORT_ARRAY(14, MAX_VALUE, SHORT, GenericRecord::readShortArray, InternalGenericRecord::readShortFromArray),
    INT_ARRAY(15, MAX_VALUE, INT, GenericRecord::readIntArray, InternalGenericRecord::readIntFromArray),
    LONG_ARRAY(16, MAX_VALUE, LONG, GenericRecord::readLongArray, InternalGenericRecord::readLongFromArray),
    FLOAT_ARRAY(17, MAX_VALUE, FLOAT, GenericRecord::readFloatArray, InternalGenericRecord::readFloatFromArray),
    DOUBLE_ARRAY(18, MAX_VALUE, DOUBLE, GenericRecord::readDoubleArray, InternalGenericRecord::readDoubleFromArray),
    UTF_ARRAY(19, MAX_VALUE, UTF, GenericRecord::readUTFArray, InternalGenericRecord::readUTFFromArray),

    BIG_INTEGER(20, MAX_VALUE, GenericRecord::readBigInteger),
    BIG_INTEGER_ARRAY(21, MAX_VALUE, BIG_INTEGER, GenericRecord::readBigIntegerArray, InternalGenericRecord::readBigIntegerFromArray),

    BIG_DECIMAL(22, MAX_VALUE, GenericRecord::readBigDecimal),
    BIG_DECIMAL_ARRAY(23, MAX_VALUE, BIG_DECIMAL, GenericRecord::readBigDecimalArray, InternalGenericRecord::readBigDecimalFromArray),

    LOCAL_TIME(24, 3 * BYTE_SIZE_IN_BYTES + INT_SIZE_IN_BYTES, GenericRecord::readLocalTime),
    LOCAL_TIME_ARRAY(25, MAX_VALUE, LOCAL_TIME, GenericRecord::readLocalTimeArray, InternalGenericRecord::readLocalTimeFromArray),

    LOCAL_DATE(26, INT_SIZE_IN_BYTES, GenericRecord::readLocalDate),
    LOCAL_DATE_ARRAY(27, MAX_VALUE, LOCAL_DATE, GenericRecord::readLocalDateArray, InternalGenericRecord::readLocalDateFromArray),

    LOCAL_DATE_TIME(28, LOCAL_TIME.getTypeSize() + LOCAL_DATE.getTypeSize(), GenericRecord::readLocalDateTime),
    LOCAL_DATE_TIME_ARRAY(29, MAX_VALUE, LOCAL_DATE_TIME, GenericRecord::readLocalDateTimeArray, InternalGenericRecord::readLocalDateTimeFromArray),

    OFFSET_DATE_TIME(30, LOCAL_DATE_TIME.getTypeSize() + INT_SIZE_IN_BYTES, GenericRecord::readOffsetDateTime),
    OFFSET_DATE_TIME_ARRAY(31, MAX_VALUE, OFFSET_DATE_TIME, GenericRecord::readOffsetDateTimeArray, InternalGenericRecord::readOffsetDateTimeFromArray),

    COMPOSED(32, MAX_VALUE, (genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObject(fieldName);
    }, GenericRecord::readGenericRecord),
    COMPOSED_ARRAY(33, MAX_VALUE, COMPOSED, (genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObjectArray(fieldName, Object.class);
    }, GenericRecord::readGenericRecordArray, InternalGenericRecord::readObjectFromArray),

    NULL(34, 0, (genericRecord, s) -> null),
    EMPTY_ARRAY(35, 0, NULL, (genericRecord, s) -> null, (internalGenericRecord, s, integer) -> null);

    private static final FieldType[] ALL = FieldType.values();

    private final byte type;
    private final boolean isArrayType;
    private final int elementSize;
    private final FieldType singleType;
    private final BiFunction<GenericRecord, String, Object> objectReader;
    private final BiFunction<GenericRecord, String, Object> serializedFormReader;
    private final TriFunction<InternalGenericRecord, String, Integer, Object> indexedReader;

    FieldType(int type, int elementSize, BiFunction<GenericRecord, String, Object> objectReader) {
        this.type = (byte) type;
        this.isArrayType = false;
        this.elementSize = elementSize;
        this.objectReader = objectReader;
        this.serializedFormReader = objectReader;
        this.indexedReader = null;
        this.singleType = null;
    }

    FieldType(int type, int elementSize, BiFunction<GenericRecord, String, Object> objectReader,
              BiFunction<GenericRecord, String, Object> serializedFormReader) {
        this.type = (byte) type;
        this.isArrayType = false;
        this.elementSize = elementSize;
        this.objectReader = objectReader;
        this.serializedFormReader = serializedFormReader;
        this.indexedReader = null;
        this.singleType = null;
    }

    FieldType(int type, int elementSize, FieldType singleType,
              BiFunction<GenericRecord, String, Object> objectReader,
              TriFunction<InternalGenericRecord, String, Integer, Object> indexedReader) {
        this.type = (byte) type;
        this.isArrayType = true;
        this.elementSize = elementSize;
        this.singleType = singleType;
        this.objectReader = objectReader;
        this.serializedFormReader = objectReader;
        this.indexedReader = indexedReader;
    }

    FieldType(int type, int elementSize, FieldType singleType,
              BiFunction<GenericRecord, String, Object> objectReader,
              BiFunction<GenericRecord, String, Object> serializedFormReader,
              TriFunction<InternalGenericRecord, String, Integer, Object> indexedReader) {
        this.type = (byte) type;
        this.isArrayType = true;
        this.elementSize = elementSize;
        this.singleType = singleType;
        this.objectReader = objectReader;
        this.serializedFormReader = serializedFormReader;
        this.indexedReader = indexedReader;
    }

    public byte getId() {
        return type;
    }

    public static FieldType get(byte type) {
        return ALL[type];
    }

    public boolean isArrayType() {
        return isArrayType;
    }

    public FieldType getSingleType() {
        if (isArrayType) {
            return singleType;
        }
        return this;
    }

    public BiFunction<GenericRecord, String, Object> getObjectReader() {
        return objectReader;
    }

    public BiFunction<GenericRecord, String, Object> getSerializedFormReader() {
        return serializedFormReader;
    }

    public TriFunction<InternalGenericRecord, String, Integer, Object> getIndexedObjectReader() {
        return indexedReader;
    }

    public boolean hasDefiniteSize() {
        return elementSize != MAX_VALUE;
    }

    /**
     * @return size of an element of the type represented by this object
     * @throws IllegalArgumentException if the type does not have a definite size.
     *                                  Invoke {@link #hasDefiniteSize()} to check first.
     */
    public int getTypeSize() throws IllegalArgumentException {
        if (elementSize == MAX_VALUE) {
            // unknown size case
            throw new IllegalArgumentException("Unsupported type - the size is variable or unknown");
        }
        return elementSize;
    }

}
