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

package com.hazelcast.internal.serialization.impl;

import com.hazelcast.internal.serialization.impl.compact.DefaultCompactWriter;
import com.hazelcast.internal.util.function.TriConsumer;
import com.hazelcast.nio.serialization.FieldType;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.Portable;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;

/**
 * Should always be consistent with {@link com.hazelcast.nio.serialization.FieldType}
 */
public enum FieldOperations {

    PORTABLE((genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObject(fieldName);
    }, GenericRecord::readGenericRecord, (writer, record, fieldName) -> {
        writer.writeGenericRecord(fieldName, record.readGenericRecord(fieldName));
    }),//used to represent any nested object
    BYTE(GenericRecord::readByte, (writer, record, fieldName) -> {
        writer.writeByte(fieldName, record.readByte(fieldName));
    }),
    BOOLEAN(GenericRecord::readBoolean, (writer, record, fieldName) -> {
        writer.writeBoolean(fieldName, record.readBoolean(fieldName));
    }),
    CHAR(GenericRecord::readChar, (writer, record, fieldName) -> {
        writer.writeChar(fieldName, record.readChar(fieldName));
    }),
    SHORT(GenericRecord::readShort, (writer, record, fieldName) -> {
        writer.writeShort(fieldName, record.readShort(fieldName));
    }),
    INT(GenericRecord::readInt, (writer, record, fieldName) -> {
        writer.writeInt(fieldName, record.readInt(fieldName));
    }),
    LONG(GenericRecord::readLong, (writer, record, fieldName) -> {
        writer.writeLong(fieldName, record.readLong(fieldName));
    }),
    FLOAT(GenericRecord::readFloat, (writer, record, fieldName) -> {
        writer.writeFloat(fieldName, record.readFloat(fieldName));
    }),
    DOUBLE(GenericRecord::readDouble, (writer, record, fieldName) -> {
        writer.writeDouble(fieldName, record.readDouble(fieldName));
    }),
    UTF(GenericRecord::readUTF, (writer, record, fieldName) -> {
        writer.writeUTF(fieldName, record.readUTF(fieldName));
    }),

    PORTABLE_ARRAY((genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObjectArray(fieldName, Portable.class);
    }, GenericRecord::readGenericRecordArray, InternalGenericRecord::readObjectFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readGenericRecordArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeGenericRecordArray(fieldName, record.readGenericRecordArray(fieldName));
            }),
    BYTE_ARRAY(GenericRecord::readByteArray, InternalGenericRecord::readByteFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readByteArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeByteArray(fieldName, record.readByteArray(fieldName));
            }),
    BOOLEAN_ARRAY(GenericRecord::readBooleanArray, InternalGenericRecord::readBooleanFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readBooleanArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeBooleanArray(fieldName, record.readBooleanArray(fieldName));
            }),
    CHAR_ARRAY(GenericRecord::readCharArray, InternalGenericRecord::readCharFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readCharArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeCharArray(fieldName, record.readCharArray(fieldName));
            }),
    SHORT_ARRAY(GenericRecord::readShortArray, InternalGenericRecord::readShortFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readShortArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeShortArray(fieldName, record.readShortArray(fieldName));
            }),
    INT_ARRAY(GenericRecord::readIntArray, InternalGenericRecord::readIntFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readIntArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeIntArray(fieldName, record.readIntArray(fieldName));
            }),
    LONG_ARRAY(GenericRecord::readLongArray, InternalGenericRecord::readLongFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readLongArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeLongArray(fieldName, record.readLongArray(fieldName));
            }),
    FLOAT_ARRAY(GenericRecord::readFloatArray, InternalGenericRecord::readFloatFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readFloatArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeFloatArray(fieldName, record.readFloatArray(fieldName));
            }),
    DOUBLE_ARRAY(GenericRecord::readDoubleArray, InternalGenericRecord::readDoubleFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readDoubleArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeDoubleArray(fieldName, record.readDoubleArray(fieldName));
            }),
    UTF_ARRAY(GenericRecord::readUTFArray, InternalGenericRecord::readUTFFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readUTFArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeUTFArray(fieldName, record.readUTFArray(fieldName));
            }),
    BIG_INTEGER(GenericRecord::readBigInteger, (writer, record, fieldName) -> {
        writer.writeBigInteger(fieldName, record.readBigInteger(fieldName));
    }),
    BIG_INTEGER_ARRAY(GenericRecord::readBigIntegerArray, InternalGenericRecord::readBigIntegerFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readBigIntegerArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeBigIntegerArray(fieldName, record.readBigIntegerArray(fieldName));
            }),
    BIG_DECIMAL(GenericRecord::readBigDecimal, (writer, record, fieldName) -> {
        writer.writeBigDecimal(fieldName, record.readBigDecimal(fieldName));
    }),
    BIG_DECIMAL_ARRAY(GenericRecord::readBigDecimalArray, InternalGenericRecord::readBigDecimalFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readBigDecimalArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeBigDecimalArray(fieldName, record.readBigDecimalArray(fieldName));
            }),
    LOCAL_TIME(GenericRecord::readLocalTime, (writer, record, fieldName) -> {
        writer.writeLocalTime(fieldName, record.readLocalTime(fieldName));
    }),
    LOCAL_TIME_ARRAY(GenericRecord::readLocalTimeArray, InternalGenericRecord::readLocalTimeFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readLocalTimeArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeLocalTimeArray(fieldName, record.readLocalTimeArray(fieldName));
            }),
    LOCAL_DATE(GenericRecord::readLocalDate, (writer, record, fieldName) -> {
        writer.writeLocalDate(fieldName, record.readLocalDate(fieldName));
    }),
    LOCAL_DATE_ARRAY(GenericRecord::readLocalDateArray, InternalGenericRecord::readLocalDateFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readLocalDateArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeLocalDateArray(fieldName, record.readLocalDateArray(fieldName));
            }),
    LOCAL_DATE_TIME(GenericRecord::readLocalDateTime, (writer, record, fieldName) -> {
        writer.writeLocalDateTime(fieldName, record.readLocalDateTime(fieldName));
    }),
    LOCAL_DATE_TIME_ARRAY(GenericRecord::readLocalDateTimeArray, InternalGenericRecord::readLocalDateTimeFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readLocalDateTimeArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeLocalDateTimeArray(fieldName, record.readLocalDateTimeArray(fieldName));
            }),
    OFFSET_DATE_TIME(GenericRecord::readOffsetDateTime, (writer, record, fieldName) -> {
        writer.writeOffsetDateTime(fieldName, record.readOffsetDateTime(fieldName));
    }),
    OFFSET_DATE_TIME_ARRAY(GenericRecord::readOffsetDateTimeArray, InternalGenericRecord::readOffsetDateTimeFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readOffsetDateTimeArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeOffsetDateTimeArray(fieldName, record.readOffsetDateTimeArray(fieldName));
            }),
    COMPOSED((genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObject(fieldName);
    }, GenericRecord::readGenericRecord, (writer, record, fieldName) -> {
        writer.writeGenericRecord(fieldName, record.readGenericRecord(fieldName));
    }),
    COMPOSED_ARRAY((genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObjectArray(fieldName, Object.class);
    }, GenericRecord::readGenericRecordArray, InternalGenericRecord::readObjectFromArray,
            (record, fieldName) -> {
                return Arrays.hashCode(record.readGenericRecordArray(fieldName));
            },
            (writer, record, fieldName) -> {
                writer.writeGenericRecordArray(fieldName, record.readGenericRecordArray(fieldName));
            });

    private static final FieldOperations[] ALL = FieldOperations.values();

    private final BiFunction<GenericRecord, String, Object> objectReader;
    private final ToIntBiFunction<GenericRecord, String> arrayHashCoder;
    private final BiFunction<GenericRecord, String, Object> serializedFormReader;
    private final IndexedReader indexedReader;
    private final TriConsumer<DefaultCompactWriter, GenericRecord, String> recordToWriter;

    FieldOperations(BiFunction<GenericRecord, String, Object> objectReader, TriConsumer<DefaultCompactWriter, GenericRecord, String> recordToWriter) {
        this.recordToWriter = recordToWriter;
        this.objectReader = objectReader;
        this.serializedFormReader = objectReader;
        this.indexedReader = null;
        this.arrayHashCoder = null;
    }

    FieldOperations(BiFunction<GenericRecord, String, Object> objectReader,
                    BiFunction<GenericRecord, String, Object> serializedFormReader, TriConsumer<DefaultCompactWriter, GenericRecord, String> recordToWriter) {
        this.recordToWriter = recordToWriter;
        this.objectReader = objectReader;
        this.serializedFormReader = serializedFormReader;
        this.indexedReader = null;
        this.arrayHashCoder = null;
    }

    FieldOperations(BiFunction<GenericRecord, String, Object> objectReader,
                    IndexedReader indexedReader,
                    ToIntBiFunction<GenericRecord, String> arrayHashCoder, TriConsumer<DefaultCompactWriter, GenericRecord, String> recordToWriter) {
        this.recordToWriter = recordToWriter;
        this.objectReader = objectReader;
        this.serializedFormReader = objectReader;
        this.indexedReader = indexedReader;
        this.arrayHashCoder = arrayHashCoder;
    }

    FieldOperations(BiFunction<GenericRecord, String, Object> objectReader,
                    BiFunction<GenericRecord, String, Object> serializedFormReader,
                    IndexedReader indexedReader,
                    ToIntBiFunction<GenericRecord, String> arrayHashCoder, TriConsumer<DefaultCompactWriter, GenericRecord, String> recordToWriter) {
        this.recordToWriter = recordToWriter;
        this.objectReader = objectReader;
        this.serializedFormReader = serializedFormReader;
        this.indexedReader = indexedReader;
        this.arrayHashCoder = arrayHashCoder;
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

    public static FieldOperations fieldOperations(FieldType fieldType) {
        return ALL[fieldType.getId()];
    }

}
