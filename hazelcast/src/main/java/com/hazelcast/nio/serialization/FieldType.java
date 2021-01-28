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

    PORTABLE(0, MAX_VALUE),
    BYTE(1, BYTE_SIZE_IN_BYTES),
    BOOLEAN(2, BOOLEAN_SIZE_IN_BYTES),
    CHAR(3, CHAR_SIZE_IN_BYTES),
    SHORT(4, SHORT_SIZE_IN_BYTES),
    INT(5, INT_SIZE_IN_BYTES),
    LONG(6, LONG_SIZE_IN_BYTES),
    FLOAT(7, FLOAT_SIZE_IN_BYTES),
    DOUBLE(8, DOUBLE_SIZE_IN_BYTES),
    UTF(9, MAX_VALUE),

    PORTABLE_ARRAY(10, MAX_VALUE, PORTABLE),
    BYTE_ARRAY(11, MAX_VALUE, BYTE),
    BOOLEAN_ARRAY(12, MAX_VALUE, BOOLEAN),
    CHAR_ARRAY(13, MAX_VALUE, CHAR),
    SHORT_ARRAY(14, MAX_VALUE, SHORT),
    INT_ARRAY(15, MAX_VALUE, INT),
    LONG_ARRAY(16, MAX_VALUE, LONG),
    FLOAT_ARRAY(17, MAX_VALUE, FLOAT),
    DOUBLE_ARRAY(18, MAX_VALUE, DOUBLE),
    UTF_ARRAY(19, MAX_VALUE, UTF),

    BIG_INTEGER(20, MAX_VALUE),
    BIG_INTEGER_ARRAY(21, MAX_VALUE, BIG_INTEGER),
    BIG_DECIMAL(22, MAX_VALUE),
    BIG_DECIMAL_ARRAY(23, MAX_VALUE, BIG_DECIMAL),
    LOCAL_TIME(24, 3 * BYTE_SIZE_IN_BYTES + INT_SIZE_IN_BYTES),
    LOCAL_TIME_ARRAY(25, MAX_VALUE, LOCAL_TIME),
    LOCAL_DATE(26, INT_SIZE_IN_BYTES),
    LOCAL_DATE_ARRAY(27, MAX_VALUE, LOCAL_DATE),
    LOCAL_DATE_TIME(28, LOCAL_TIME.getTypeSize() + LOCAL_DATE.getTypeSize()),
    LOCAL_DATE_TIME_ARRAY(29, MAX_VALUE, LOCAL_DATE_TIME),
    OFFSET_DATE_TIME(30, LOCAL_DATE_TIME.getTypeSize() + INT_SIZE_IN_BYTES),
    OFFSET_DATE_TIME_ARRAY(31, MAX_VALUE, OFFSET_DATE_TIME),
    COMPOSED(32, MAX_VALUE),
    COMPOSED_ARRAY(33, MAX_VALUE, COMPOSED),
    ANY(34, MAX_VALUE),
    COLLECTION(35, MAX_VALUE, ANY);

    private static final FieldType[] ALL = FieldType.values();

    private final byte type;
    private final boolean isArrayType;
    private final int elementSize;
    private final FieldType singleType;

    FieldType(int type, int elementSize) {
        this.type = (byte) type;
        this.isArrayType = false;
        this.elementSize = elementSize;
        this.singleType = this;
    }

    FieldType(int type, int elementSize, FieldType singleType) {
        this.type = (byte) type;
        this.isArrayType = true;
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
        return singleType;
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
