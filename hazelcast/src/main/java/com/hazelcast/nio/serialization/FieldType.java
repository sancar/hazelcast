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
    BYTE(1, true, BYTE_SIZE_IN_BYTES),
    BOOLEAN(2, true, BOOLEAN_SIZE_IN_BYTES),
    CHAR(3, true, CHAR_SIZE_IN_BYTES),
    SHORT(4, true, SHORT_SIZE_IN_BYTES),
    INT(5, true, INT_SIZE_IN_BYTES),
    LONG(6, true, LONG_SIZE_IN_BYTES),
    FLOAT(7, true, FLOAT_SIZE_IN_BYTES),
    DOUBLE(8, true, DOUBLE_SIZE_IN_BYTES),
    UTF(9, false, MAX_VALUE),

    // ARRAY TYPES
    PORTABLE_ARRAY(10, false, MAX_VALUE),//used to represent any nested object array
    BYTE_ARRAY(11, false, MAX_VALUE),
    BOOLEAN_ARRAY(12, false, MAX_VALUE),
    CHAR_ARRAY(13, false, MAX_VALUE),
    SHORT_ARRAY(14, false, MAX_VALUE),
    INT_ARRAY(15, false, MAX_VALUE),
    LONG_ARRAY(16, false, MAX_VALUE),
    FLOAT_ARRAY(17, false, MAX_VALUE),
    DOUBLE_ARRAY(18, false, MAX_VALUE),
    UTF_ARRAY(19, false, MAX_VALUE),

    BIG_INTEGER(20, false, MAX_VALUE),
    BIG_INTEGER_ARRAY(21, false, MAX_VALUE),
    BIG_DECIMAL(22, false, MAX_VALUE),
    BIG_DECIMAL_ARRAY(23, false, MAX_VALUE),
    LOCAL_TIME(24, false, INT_SIZE_IN_BYTES * 4),
    LOCAL_TIME_ARRAY(25, false, MAX_VALUE),
    LOCAL_DATE(26, false, INT_SIZE_IN_BYTES * 3),
    LOCAL_DATE_ARRAY(27, false, MAX_VALUE),
    LOCAL_DATE_TIME(28, false, INT_SIZE_IN_BYTES * 7),
    LOCAL_DATE_TIME_ARRAY(29, false, MAX_VALUE),
    OFFSET_DATE_TIME(30, false, INT_SIZE_IN_BYTES * 8),
    OFFSET_DATE_TIME_ARRAY(31, false, MAX_VALUE);

    private static final FieldType[] ALL = FieldType.values();
    private static final int TYPES_COUNT = 10;

    private final byte type;
    private final boolean isPrimitive;
    private final int elementSize;

    FieldType(int type, boolean isPrimitive, int elementSize) {
        this.type = (byte) type;
        this.isPrimitive = isPrimitive;
        this.elementSize = elementSize;
    }

    public byte getId() {
        return type;
    }

    public static FieldType get(byte type) {
        return ALL[type];
    }

    public boolean isArrayType() {
        if (type < BIG_INTEGER.type) {
            return type >= PORTABLE_ARRAY.type;
        }
        return type % 2 != 0;
    }

    public FieldType getSingleType() {
        byte id = getId();
        if (type < BIG_INTEGER.type) {
            return get((byte) (id % TYPES_COUNT));
        }
        if (id % 2 == 0) {
            return get(id);
        } else {
            return get((byte) (id - 1));
        }
    }

    public boolean isPrimitive() {
        return isPrimitive;
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
