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
    PORTABLE(0, false, MAX_VALUE),//used to represent any nested object
    BYTE(1, false, BYTE_SIZE_IN_BYTES),
    BOOLEAN(2, false, BOOLEAN_SIZE_IN_BYTES),
    CHAR(3, false, CHAR_SIZE_IN_BYTES),
    SHORT(4, false, SHORT_SIZE_IN_BYTES),
    INT(5, false, INT_SIZE_IN_BYTES),
    LONG(6, false, LONG_SIZE_IN_BYTES),
    FLOAT(7, false, FLOAT_SIZE_IN_BYTES),
    DOUBLE(8, false, DOUBLE_SIZE_IN_BYTES),
    UTF(9, false, MAX_VALUE),

    // ARRAY TYPES
    PORTABLE_ARRAY(10, true, MAX_VALUE, PORTABLE),//used to represent any nested object array
    BYTE_ARRAY(11, true, MAX_VALUE, BYTE),
    BOOLEAN_ARRAY(12, true, MAX_VALUE, BOOLEAN),
    CHAR_ARRAY(13, true, MAX_VALUE, CHAR),
    SHORT_ARRAY(14, true, MAX_VALUE, SHORT),
    INT_ARRAY(15, true, MAX_VALUE, INT),
    LONG_ARRAY(16, true, MAX_VALUE, LONG),
    FLOAT_ARRAY(17, true, MAX_VALUE, FLOAT),
    DOUBLE_ARRAY(18, true, MAX_VALUE, DOUBLE),
    UTF_ARRAY(19, true, MAX_VALUE, UTF),

    BIG_INTEGER(20, false, MAX_VALUE),
    BIG_INTEGER_ARRAY(21, true, MAX_VALUE, BIG_INTEGER),
    BIG_DECIMAL(22, false, MAX_VALUE),
    BIG_DECIMAL_ARRAY(23, true, MAX_VALUE, BIG_DECIMAL),
    LOCAL_TIME(24, false, 3 * BYTE_SIZE_IN_BYTES  + INT_SIZE_IN_BYTES),
    LOCAL_TIME_ARRAY(25, true, MAX_VALUE, LOCAL_TIME),
    LOCAL_DATE(26, false, INT_SIZE_IN_BYTES),
    LOCAL_DATE_ARRAY(27, true, MAX_VALUE, LOCAL_DATE),
    LOCAL_DATE_TIME(28, false, LOCAL_TIME.getTypeSize() + LOCAL_DATE.getTypeSize()),
    LOCAL_DATE_TIME_ARRAY(29, true, MAX_VALUE, LOCAL_DATE_TIME),
    OFFSET_DATE_TIME(30, false,     LOCAL_DATE_TIME.getTypeSize() + INT_SIZE_IN_BYTES),
    OFFSET_DATE_TIME_ARRAY(31, true, MAX_VALUE, OFFSET_DATE_TIME),
    OBJECT(32, false, MAX_VALUE),
    OBJECT_ARRAY(33, true, MAX_VALUE, OBJECT);

    private static final FieldType[] ALL = FieldType.values();

    private final byte type;
    private final boolean isArrayType;
    private final int elementSize;
    private final FieldType singleType;

    FieldType(int type, boolean isArrayType, int elementSize) {
        this.type = (byte) type;
        this.isArrayType = isArrayType;
        this.elementSize = elementSize;
        this.singleType = null;
    }

    FieldType(int type, boolean isArrayType, int elementSize, FieldType singleType) {
        this.type = (byte) type;
        this.isArrayType = isArrayType;
        this.elementSize = elementSize;
        this.singleType = singleType;
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
