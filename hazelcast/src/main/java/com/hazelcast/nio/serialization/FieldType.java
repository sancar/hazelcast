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

import com.hazelcast.internal.serialization.impl.IndexedReader;
import com.hazelcast.internal.serialization.impl.InternalGenericRecord;
import com.hazelcast.internal.serialization.impl.compact.DefaultCompactWriter;
import com.hazelcast.internal.util.function.TriConsumer;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;

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

    PORTABLE(0, MAX_VALUE, (genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObject(fieldName);
    }, GenericRecord::readGenericRecord, (writer, record, fieldName) -> {
        writer.writeGenericRecord(fieldName, record.readGenericRecord(fieldName));
    }),//used to represent any nested object
    BYTE(1, BYTE_SIZE_IN_BYTES, GenericRecord::readByte, (writer, record, fieldName) -> {
        writer.writeByte(fieldName, record.readByte(fieldName));
    }),
    BOOLEAN(2, BOOLEAN_SIZE_IN_BYTES, GenericRecord::readBoolean, (writer, record, fieldName) -> {
        writer.writeBoolean(fieldName, record.readBoolean(fieldName));
    }),
    CHAR(3, CHAR_SIZE_IN_BYTES, GenericRecord::readChar, (writer, record, fieldName) -> {
        writer.writeChar(fieldName, record.readChar(fieldName));
    }),
    SHORT(4, SHORT_SIZE_IN_BYTES, GenericRecord::readShort, (writer, record, fieldName) -> {
        writer.writeShort(fieldName, record.readShort(fieldName));
    }),
    INT(5, INT_SIZE_IN_BYTES, GenericRecord::readInt, (writer, record, fieldName) -> {
        writer.writeInt(fieldName, record.readInt(fieldName));
    }),
    LONG(6, LONG_SIZE_IN_BYTES, GenericRecord::readLong, (writer, record, fieldName) -> {
        writer.writeLong(fieldName, record.readLong(fieldName));
    }),
    FLOAT(7, FLOAT_SIZE_IN_BYTES, GenericRecord::readFloat, (writer, record, fieldName) -> {
        writer.writeFloat(fieldName, record.readFloat(fieldName));
    }),
    DOUBLE(8, DOUBLE_SIZE_IN_BYTES, GenericRecord::readDouble, (writer, record, fieldName) -> {
        writer.writeDouble(fieldName, record.readDouble(fieldName));
    }),
    UTF(9, MAX_VALUE, GenericRecord::readUTF, (writer, record, fieldName) -> {
        writer.writeUTF(fieldName, record.readUTF(fieldName));
    }),

    PORTABLE_ARRAY(10, MAX_VALUE, PORTABLE, (genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObjectArray(fieldName, Portable.class);
    }, GenericRecord::readGenericRecordArray, InternalGenericRecord::readObjectFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readGenericRecordArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeGenericRecordArray(fieldName, record.readGenericRecordArray(fieldName));
            }),
    BYTE_ARRAY(11, MAX_VALUE, BYTE, GenericRecord::readByteArray, InternalGenericRecord::readByteFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readByteArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeByteArray(fieldName, record.readByteArray(fieldName));
            }),
    BOOLEAN_ARRAY(12, MAX_VALUE, BOOLEAN, GenericRecord::readBooleanArray, InternalGenericRecord::readBooleanFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readBooleanArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeBooleanArray(fieldName, record.readBooleanArray(fieldName));
            }),
    CHAR_ARRAY(13, MAX_VALUE, CHAR, GenericRecord::readCharArray, InternalGenericRecord::readCharFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readCharArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeCharArray(fieldName, record.readCharArray(fieldName));
            }),
    SHORT_ARRAY(14, MAX_VALUE, SHORT, GenericRecord::readShortArray, InternalGenericRecord::readShortFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readShortArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeShortArray(fieldName, record.readShortArray(fieldName));
            }),
    INT_ARRAY(15, MAX_VALUE, INT, GenericRecord::readIntArray, InternalGenericRecord::readIntFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readIntArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeIntArray(fieldName, record.readIntArray(fieldName));
            }),
    LONG_ARRAY(16, MAX_VALUE, LONG, GenericRecord::readLongArray, InternalGenericRecord::readLongFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readLongArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeLongArray(fieldName, record.readLongArray(fieldName));
            }),
    FLOAT_ARRAY(17, MAX_VALUE, FLOAT, GenericRecord::readFloatArray, InternalGenericRecord::readFloatFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readFloatArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeFloatArray(fieldName, record.readFloatArray(fieldName));
            }),
    DOUBLE_ARRAY(18, MAX_VALUE, DOUBLE, GenericRecord::readDoubleArray, InternalGenericRecord::readDoubleFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readDoubleArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeDoubleArray(fieldName, record.readDoubleArray(fieldName));
            }),
    UTF_ARRAY(19, MAX_VALUE, UTF, GenericRecord::readUTFArray, InternalGenericRecord::readUTFFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readUTFArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeUTFArray(fieldName, record.readUTFArray(fieldName));
            }),
    BIG_INTEGER(20, MAX_VALUE, GenericRecord::readBigInteger, (writer, record, fieldName) -> {
        writer.writeBigInteger(fieldName, record.readBigInteger(fieldName));
    }),
    BIG_INTEGER_ARRAY(21, MAX_VALUE, BIG_INTEGER, GenericRecord::readBigIntegerArray, InternalGenericRecord::readBigIntegerFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readBigIntegerArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeBigIntegerArray(fieldName, record.readBigIntegerArray(fieldName));
            }),
    BIG_DECIMAL(22, MAX_VALUE, GenericRecord::readBigDecimal, (writer, record, fieldName) -> {
        writer.writeBigDecimal(fieldName, record.readBigDecimal(fieldName));
    }),
    BIG_DECIMAL_ARRAY(23, MAX_VALUE, BIG_DECIMAL, GenericRecord::readBigDecimalArray, InternalGenericRecord::readBigDecimalFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readBigDecimalArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeBigDecimalArray(fieldName, record.readBigDecimalArray(fieldName));
            }),
    LOCAL_TIME(24, 3 * BYTE_SIZE_IN_BYTES + INT_SIZE_IN_BYTES, GenericRecord::readLocalTime, (writer, record, fieldName) -> {
        writer.writeLocalTime(fieldName, record.readLocalTime(fieldName));
    }),
    LOCAL_TIME_ARRAY(25, MAX_VALUE, LOCAL_TIME, GenericRecord::readLocalTimeArray, InternalGenericRecord::readLocalTimeFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readLocalTimeArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeLocalTimeArray(fieldName, record.readLocalTimeArray(fieldName));
            }),
    LOCAL_DATE(26, INT_SIZE_IN_BYTES, GenericRecord::readLocalDate, (writer, record, fieldName) -> {
        writer.writeLocalDate(fieldName, record.readLocalDate(fieldName));
    }),
    LOCAL_DATE_ARRAY(27, MAX_VALUE, LOCAL_DATE, GenericRecord::readLocalDateArray, InternalGenericRecord::readLocalDateFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readLocalDateArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeLocalDateArray(fieldName, record.readLocalDateArray(fieldName));
            }),
    LOCAL_DATE_TIME(28, LOCAL_TIME.getTypeSize() + LOCAL_DATE.getTypeSize(), GenericRecord::readLocalDateTime, (writer, record, fieldName) -> {
        writer.writeLocalDateTime(fieldName, record.readLocalDateTime(fieldName));
    }),
    LOCAL_DATE_TIME_ARRAY(29, MAX_VALUE, LOCAL_DATE_TIME, GenericRecord::readLocalDateTimeArray, InternalGenericRecord::readLocalDateTimeFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readLocalDateTimeArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeLocalDateTimeArray(fieldName, record.readLocalDateTimeArray(fieldName));
            }),
    OFFSET_DATE_TIME(30, LOCAL_DATE_TIME.getTypeSize() + INT_SIZE_IN_BYTES, GenericRecord::readOffsetDateTime, (writer, record, fieldName) -> {
        writer.writeOffsetDateTime(fieldName, record.readOffsetDateTime(fieldName));
    }),
    OFFSET_DATE_TIME_ARRAY(31, MAX_VALUE, OFFSET_DATE_TIME, GenericRecord::readOffsetDateTimeArray, InternalGenericRecord::readOffsetDateTimeFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readOffsetDateTimeArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeOffsetDateTimeArray(fieldName, record.readOffsetDateTimeArray(fieldName));
            }),
    COMPOSED(32, MAX_VALUE, (genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObject(fieldName);
    }, GenericRecord::readGenericRecord, (writer, record, fieldName) -> {
        writer.writeGenericRecord(fieldName, record.readGenericRecord(fieldName));
    }),
    COMPOSED_ARRAY(33, MAX_VALUE, COMPOSED, (genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObjectArray(fieldName, Object.class);
    }, GenericRecord::readGenericRecordArray, InternalGenericRecord::readObjectFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readGenericRecordArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeGenericRecordArray(fieldName, record.readGenericRecordArray(fieldName));
            });

    private static final FieldType[] ALL = FieldType.values();

    private final byte type;
    private final boolean isArrayType;
    private final int elementSize;
    private final FieldType singleType;
    private final BiFunction<GenericRecord, String, Object> objectReader;
    private final ToIntBiFunction<GenericRecord, String> arrayHashCoder;
    private final BiFunction<GenericRecord, String, Object> serializedFormReader;
    private final IndexedReader indexedReader;
    private final TriConsumer<DefaultCompactWriter, GenericRecord, String> recordToWriter;

    FieldType(int type, int elementSize, BiFunction<GenericRecord, String, Object> objectReader, TriConsumer<DefaultCompactWriter, GenericRecord, String> recordToWriter) {
        this.type = (byte) type;
        this.recordToWriter = recordToWriter;
        this.isArrayType = false;
        this.elementSize = elementSize;
        this.objectReader = objectReader;
        this.serializedFormReader = objectReader;
        this.indexedReader = null;
        this.singleType = null;
        this.arrayHashCoder = null;
    }

    FieldType(int type, int elementSize, BiFunction<GenericRecord, String, Object> objectReader,
              BiFunction<GenericRecord, String, Object> serializedFormReader, TriConsumer<DefaultCompactWriter, GenericRecord, String> recordToWriter) {
        this.type = (byte) type;
        this.recordToWriter = recordToWriter;
        this.isArrayType = false;
        this.elementSize = elementSize;
        this.objectReader = objectReader;
        this.serializedFormReader = serializedFormReader;
        this.indexedReader = null;
        this.singleType = null;
        this.arrayHashCoder = null;
    }

    FieldType(int type, int elementSize, FieldType singleType,
              BiFunction<GenericRecord, String, Object> objectReader,
              IndexedReader indexedReader,
              ToIntBiFunction<GenericRecord, String> arrayHashCoder, TriConsumer<DefaultCompactWriter, GenericRecord, String> recordToWriter) {
        this.type = (byte) type;
        this.recordToWriter = recordToWriter;
        this.isArrayType = true;
        this.elementSize = elementSize;
        this.singleType = singleType;
        this.objectReader = objectReader;
        this.serializedFormReader = objectReader;
        this.indexedReader = indexedReader;
        this.arrayHashCoder = arrayHashCoder;
    }

    FieldType(int type, int elementSize, FieldType singleType,
              BiFunction<GenericRecord, String, Object> objectReader,
              BiFunction<GenericRecord, String, Object> serializedFormReader,
              IndexedReader indexedReader,
              ToIntBiFunction<GenericRecord, String> arrayHashCoder, TriConsumer<DefaultCompactWriter, GenericRecord, String> recordToWriter) {
        this.type = (byte) type;
        this.recordToWriter = recordToWriter;
        this.isArrayType = true;
        this.elementSize = elementSize;
        this.singleType = singleType;
        this.objectReader = objectReader;
        this.serializedFormReader = serializedFormReader;
        this.indexedReader = indexedReader;
        this.arrayHashCoder = arrayHashCoder;
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

    public IndexedReader getIndexedObjectReader() {
        return indexedReader;
    }

    public ToIntBiFunction<GenericRecord, String> getArrayHashCoder() {
        return arrayHashCoder;
    }

    public TriConsumer<DefaultCompactWriter, GenericRecord, String> getRecordToWriter() {
        return recordToWriter;
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
