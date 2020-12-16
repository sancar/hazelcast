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

import com.hazelcast.internal.nio.BufferObjectDataInput;
import com.hazelcast.internal.nio.BufferObjectDataOutput;
import com.hazelcast.internal.nio.IOUtil;
import com.hazelcast.internal.serialization.impl.compact.Compact;
import com.hazelcast.internal.serialization.impl.compact.DefaultCompactReader;
import com.hazelcast.internal.serialization.impl.compact.DefaultCompactWriter;
import com.hazelcast.internal.util.function.TriConsumer;
import com.hazelcast.nio.serialization.FieldType;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.Portable;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;

/**
 * Should always be consistent with {@link FieldType}
 * Could have been merged with {@link FieldType}, if FieldType was not a public API.
 * <p>
 * Have methods to write and read a field type in various contexts.
 * Purpose is to avoid `switch` on all the types
 * The other benefit is when a type is added, this will be the only place to add related operations
 * Otherwise, one more case should be added for the new type in all places where `switch` on all types are used
 */
public enum FieldOperations {

    PORTABLE((genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObject(fieldName);
    }, (serializer, out, value) -> {
        throw new UnsupportedOperationException();
    }, (serializer, in) -> {
        throw new UnsupportedOperationException();
    }, (genericRecord, fieldName) -> {
        return genericRecord.readGenericRecord(fieldName);
    }, (writer, record, fieldName) -> {
        writer.writeGenericRecord(fieldName, record.readGenericRecord(fieldName));
    }),
    BYTE((genericRecord, fieldName) -> {
        return genericRecord.readByte(fieldName);
    }, (serializer, out, value) -> {
        out.writeByte((Byte) value);
    }, (serializer, in) -> {
        return in.readByte();
    }, null, (writer, record, fieldName) -> {
        writer.writeByte(fieldName, record.readByte(fieldName));
    }),
    BOOLEAN((genericRecord, fieldName) -> {
        return genericRecord.readBoolean(fieldName);
    }, (serializer, out, value) -> {
        out.writeBoolean((Boolean) value);
    }, (serializer, in) -> {
        return in.readBoolean();
    }, null, (writer, record, fieldName) -> {
        writer.writeBoolean(fieldName, record.readBoolean(fieldName));
    }),
    CHAR((genericRecord, fieldName) -> {
        return genericRecord.readChar(fieldName);
    }, (serializer, out, value) -> {
        out.writeChar((Character) value);
    }, (serializer, in) -> {
        return in.readChar();
    }, null, (writer, record, fieldName) -> {
        writer.writeChar(fieldName, record.readChar(fieldName));
    }),
    SHORT((genericRecord, fieldName) -> {
        return genericRecord.readShort(fieldName);
    }, (serializer, out, value) -> {
        out.writeShort((Short) value);
    }, (serializer, in) -> {
        return in.readShort();
    }, null, (writer, record, fieldName) -> {
        writer.writeShort(fieldName, record.readShort(fieldName));
    }),
    INT((genericRecord, fieldName) -> {
        return genericRecord.readInt(fieldName);
    }, (serializer, out, value) -> {
        out.writeInt((Integer) value);
    }, (serializer, in) -> {
        return in.readInt();
    }, null, (writer, record, fieldName) -> {
        writer.writeInt(fieldName, record.readInt(fieldName));
    }),
    LONG((genericRecord, fieldName) -> {
        return genericRecord.readLong(fieldName);
    }, (serializer, out, value) -> {
        out.writeLong((Long) value);
    }, (serializer, in) -> {
        return in.readLong();
    }, null, (writer, record, fieldName) -> {
        writer.writeLong(fieldName, record.readLong(fieldName));
    }),
    FLOAT((genericRecord, fieldName) -> {
        return genericRecord.readFloat(fieldName);
    }, (serializer, out, value) -> {
        out.writeFloat((Float) value);
    }, (serializer, in) -> {
        return in.readFloat();
    }, null, (writer, record, fieldName) -> {
        writer.writeFloat(fieldName, record.readFloat(fieldName));
    }),
    DOUBLE((genericRecord, fieldName) -> {
        return genericRecord.readDouble(fieldName);
    }, (serializer, out, value) -> {
        out.writeDouble((Double) value);
    }, (serializer, in) -> {
        return in.readDouble();
    }, null, (writer, record, fieldName) -> {
        writer.writeDouble(fieldName, record.readDouble(fieldName));
    }),
    UTF((genericRecord, fieldName) -> {
        return genericRecord.readUTF(fieldName);
    }, (serializer, out, value) -> {
        out.writeUTF((String) value);
    }, (serializer, in) -> {
        return in.readUTF();
    }, null, (writer, record, fieldName) -> {
        writer.writeUTF(fieldName, record.readUTF(fieldName));
    }),
    PORTABLE_ARRAY((genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObjectArray(fieldName, Portable.class);
    }, (serializer, out, value) -> {
        //?? TODO sancar not supported
    }, (serializer, in) -> {
        return null; //. ?
    }, (genericRecord, fieldName) -> {
        return genericRecord.readGenericRecordArray(fieldName);
    }, (record, fieldName, index) -> {
        return record.readObjectFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readGenericRecordArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeGenericRecordArray(fieldName, record.readGenericRecordArray(fieldName));
    }),
    BYTE_ARRAY((genericRecord, fieldName) -> {
        return genericRecord.readByteArray(fieldName);
    }, (serializer, out, value) -> {
        out.writeByteArray((byte[]) value);
    }, (serializer, in) -> {
        return in.readByteArray();
    }, null, (record, fieldName, index) -> {
        return record.readByteFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readByteArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeByteArray(fieldName, record.readByteArray(fieldName));
    }),
    BOOLEAN_ARRAY((genericRecord, fieldName) -> {
        return genericRecord.readBooleanArray(fieldName);
    }, (serializer, out, value) -> {
        out.writeBooleanArray((boolean[]) value);
    }, (serializer, in) -> {
        return in.readBooleanArray();
    }, null, (record, fieldName, index) -> {
        return record.readBooleanFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readBooleanArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeBooleanArray(fieldName, record.readBooleanArray(fieldName));
    }),
    CHAR_ARRAY((genericRecord, fieldName) -> {
        return genericRecord.readCharArray(fieldName);
    }, (serializer, out, value) -> {
        out.writeCharArray((char[]) value);
    }, (serializer, in) -> {
        return in.readCharArray();
    }, null, (record, fieldName, index) -> {
        return record.readCharFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readCharArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeCharArray(fieldName, record.readCharArray(fieldName));
    }),
    SHORT_ARRAY((genericRecord, fieldName) -> {
        return genericRecord.readShortArray(fieldName);
    }, (serializer, out, value) -> {
        out.writeShortArray((short[]) value);
    }, (serializer, in) -> {
        return in.readShortArray();
    }, null, (record, fieldName, index) -> {
        return record.readShortFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readShortArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeShortArray(fieldName, record.readShortArray(fieldName));
    }),
    INT_ARRAY((genericRecord, fieldName) -> {
        return genericRecord.readIntArray(fieldName);
    }, (serializer, out, value) -> {
        out.writeIntArray((int[]) value);
    }, (serializer, in) -> {
        return in.readIntArray();
    }, null, (record, fieldName, index) -> {
        return record.readIntFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readIntArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeIntArray(fieldName, record.readIntArray(fieldName));
    }),
    LONG_ARRAY((genericRecord, fieldName) -> {
        return genericRecord.readLongArray(fieldName);
    }, (serializer, out, value) -> {
        out.writeLongArray((long[]) value);
    }, (serializer, in) -> {
        return in.readLongArray();
    }, null, (record, fieldName, index) -> {
        return record.readLongFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readLongArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeLongArray(fieldName, record.readLongArray(fieldName));
    }),
    FLOAT_ARRAY((genericRecord, fieldName) -> {
        return genericRecord.readFloatArray(fieldName);
    }, (serializer, out, value) -> {
        out.writeFloatArray((float[]) value);
    }, (serializer, in) -> {
        return in.readFloatArray();
    }, null, (record, fieldName, index) -> {
        return record.readFloatFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readFloatArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeFloatArray(fieldName, record.readFloatArray(fieldName));
    }),
    DOUBLE_ARRAY((genericRecord, fieldName) -> {
        return genericRecord.readDoubleArray(fieldName);
    }, (serializer, out, value) -> {
        out.writeDoubleArray((double[]) value);
    }, (serializer, in) -> {
        return in.readDoubleArray();
    }, null, (record, fieldName, index) -> {
        return record.readDoubleFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readDoubleArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeDoubleArray(fieldName, record.readDoubleArray(fieldName));
    }),
    UTF_ARRAY((genericRecord, fieldName) -> {
        return genericRecord.readUTFArray(fieldName);
    }, (serializer, out, value) -> {
        out.writeUTFArray((String[]) value);
    }, (serializer, in) -> {
        return in.readUTFArray();
    }, null, (record, fieldName, index) -> {
        return record.readUTFFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readUTFArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeUTFArray(fieldName, record.readUTFArray(fieldName));
    }),
    BIG_INTEGER((genericRecord, fieldName) -> {
        return genericRecord.readBigInteger(fieldName);
    }, (serializer, out, value) -> {
        IOUtil.writeBigInteger(out, (BigInteger) value);
    }, (serializer, in) -> {
        return IOUtil.readBigInteger(in);
    }, null, (writer, record, fieldName) -> {
        writer.writeBigInteger(fieldName, record.readBigInteger(fieldName));
    }),
    BIG_INTEGER_ARRAY((genericRecord, fieldName) -> {
        return genericRecord.readBigIntegerArray(fieldName);
    }, (serializer, out, value) -> {
        DefaultCompactWriter.writeRawArray(out, value, (out1, value1) -> IOUtil.writeBigInteger(out1, (BigInteger) value1));
    }, (serializer, in) -> {
        return DefaultCompactReader.readBigIntegerArray0(in);
    }, null, (record, fieldName, index) -> {
        return record.readBigIntegerFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readBigIntegerArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeBigIntegerArray(fieldName, record.readBigIntegerArray(fieldName));
    }),
    BIG_DECIMAL((genericRecord, fieldName) -> {
        return genericRecord.readBigDecimal(fieldName);
    }, (serializer, out, value) -> {
        IOUtil.writeBigDecimal(out, (BigDecimal) value);
    }, (serializer, in) -> {
        return IOUtil.readBigDecimal(in);
    }, null, (writer, record, fieldName) -> {
        writer.writeBigDecimal(fieldName, record.readBigDecimal(fieldName));
    }),
    BIG_DECIMAL_ARRAY((genericRecord, fieldName) -> {
        return genericRecord.readBigDecimalArray(fieldName);
    }, (serializer, out, value) -> {
        DefaultCompactWriter.writeRawArray(out, value, (out1, value1) -> IOUtil.writeBigDecimal(out1, (BigDecimal) value1));
    }, (serializer, in) -> {
        return DefaultCompactReader.readBigDecimalArray0(in);
    }, null, (record, fieldName, index) -> {
        return record.readBigDecimalFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readBigDecimalArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeBigDecimalArray(fieldName, record.readBigDecimalArray(fieldName));
    }),
    LOCAL_TIME((genericRecord, fieldName) -> {
        return genericRecord.readLocalTime(fieldName);
    }, (serializer, out, value) -> {
        IOUtil.writeLocalTime(out, (LocalTime) value);
    }, (serializer, in) -> {
        return IOUtil.readLocalTime(in);
    }, null, (writer, record, fieldName) -> {
        writer.writeLocalTime(fieldName, record.readLocalTime(fieldName));
    }),
    LOCAL_TIME_ARRAY((genericRecord, fieldName) -> {
        return genericRecord.readLocalTimeArray(fieldName);
    }, (serializer, out, value) -> {
        DefaultCompactWriter.writeLocalTimeArray0(out, (LocalTime[]) value);
    }, (serializer, in) -> {
        return DefaultCompactReader.readLocalTimeArray(in);
    }, null, (record, fieldName, index) -> {
        return record.readLocalTimeFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readLocalTimeArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeLocalTimeArray(fieldName, record.readLocalTimeArray(fieldName));
    }),
    LOCAL_DATE((genericRecord, fieldName) -> {
        return genericRecord.readLocalDate(fieldName);
    }, (serializer, out, value) -> {
        IOUtil.writeLocalDate(out, (LocalDate) value);
    }, (serializer, in) -> {
        return IOUtil.readLocalDate(in);
    }, null, (writer, record, fieldName) -> {
        writer.writeLocalDate(fieldName, record.readLocalDate(fieldName));
    }),
    LOCAL_DATE_ARRAY((genericRecord, fieldName) -> {
        return genericRecord.readLocalDateArray(fieldName);
    }, (serializer, out, value) -> {
        DefaultCompactWriter.writeLocalDateArray0(out, (LocalDate[]) value);
    }, (serializer, in) -> {
        return DefaultCompactReader.readLocalDateArray(in);
    }, null, (record, fieldName, index) -> {
        return record.readLocalDateFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readLocalDateArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeLocalDateArray(fieldName, record.readLocalDateArray(fieldName));
    }),
    LOCAL_DATE_TIME((genericRecord, fieldName) -> {
        return genericRecord.readLocalDateTime(fieldName);
    }, (serializer, out, value) -> {
        IOUtil.writeLocalDateTime(out, (LocalDateTime) value);
    }, (serializer, in) -> {
        return IOUtil.readLocalTime(in);
    }, null, (writer, record, fieldName) -> {
        writer.writeLocalDateTime(fieldName, record.readLocalDateTime(fieldName));
    }),
    LOCAL_DATE_TIME_ARRAY((genericRecord, fieldName) -> {
        return genericRecord.readLocalDateTimeArray(fieldName);
    }, (serializer, out, value) -> {
        DefaultCompactWriter.writeLocalDateTimeArray0(out, (LocalDateTime[]) value);
    }, (serializer, in) -> {
        return DefaultCompactReader.readLocalDateTimeArray(in);
    }, null, (record, fieldName, index) -> {
        return record.readLocalDateTimeFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readLocalDateTimeArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeLocalDateTimeArray(fieldName, record.readLocalDateTimeArray(fieldName));
    }),
    OFFSET_DATE_TIME((genericRecord, fieldName) -> {
        return genericRecord.readOffsetDateTime(fieldName);
    }, (serializer, out, value) -> {
        IOUtil.writeOffsetDateTime(out, (OffsetDateTime) value);
    }, (serializer, in) -> {
        return IOUtil.readOffsetDateTime(in);
    }, null, (writer, record, fieldName) -> {
        writer.writeOffsetDateTime(fieldName, record.readOffsetDateTime(fieldName));
    }),
    OFFSET_DATE_TIME_ARRAY((genericRecord, fieldName) -> {
        return genericRecord.readOffsetDateTimeArray(fieldName);
    }, (serializer, out, value) -> {
        DefaultCompactWriter.writeOffsetDateTimeArray0(out, (OffsetDateTime[]) value);
    }, (serializer, in) -> {
        return DefaultCompactReader.readOffsetDateTimeArray(in);
    }, null, (record, fieldName, index) -> {
        return record.readOffsetDateTimeFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readOffsetDateTimeArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeOffsetDateTimeArray(fieldName, record.readOffsetDateTimeArray(fieldName));
    }),
    COMPOSED((genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObject(fieldName);
    }, (serializer, out, value) -> {
        serializer.writeObject(out, value);
    }, (serializer, in) -> {
        return serializer.readObject(in);
    }, (genericRecord, fieldName) -> {
        return genericRecord.readGenericRecord(fieldName);
    }, (writer, record, fieldName) -> {
        writer.writeGenericRecord(fieldName, record.readGenericRecord(fieldName));
    }),
    COMPOSED_ARRAY((genericRecord, fieldName) -> {
        return ((InternalGenericRecord) genericRecord).readObjectArray(fieldName, Object.class);
    }, (serializer, out, value) -> {
        DefaultCompactWriter.writeRawArray(out, value, (DefaultCompactWriter.Writer<Object>) (out1, o) -> serializer.write(out1, o));
    }, (serializer, in) -> {
        return DefaultCompactReader.readRawArray(in, in1 -> serializer.readObject(in1));
    }, (genericRecord, fieldName) -> {
        return genericRecord.readGenericRecordArray(fieldName);
    }, (record, fieldName, index) -> {
        return record.readObjectFromArray(fieldName, index);
    }, (record, fieldName) -> {
        return Arrays.hashCode(record.readGenericRecordArray(fieldName));
    }, (writer, record, fieldName) -> {
        writer.writeGenericRecordArray(fieldName, record.readGenericRecordArray(fieldName));
    }),
    ANY(null, null, null, null, null),
    COLLECTION((genericRecord, fieldName) -> {
        return null;//TODO sancar
    }, (serializer, out, value) -> {
        DefaultCompactWriter.writeCollection(serializer, out, (Collection) value);
    }, (serializer, in) -> {
        return DefaultCompactReader.readCollection(serializer, in, ArrayList::new);
    }, (genericRecord, fieldName) -> {
        return null;//TODO sancar
    }, (writer, record, fieldName) -> {
        //TODO sancar
    });

    private static final FieldOperations[] ALL = FieldOperations.values();

    private final BiFunction<GenericRecord, String, Object> objectReader;
    private final BufferObjectDataOutputWriter objectDataOutputWriter;
    private final BufferObjectDataInputReader objectDataInputReader;
    private final ToIntBiFunction<GenericRecord, String> arrayHashCoder;
    private final BiFunction<GenericRecord, String, Object> serializedFormReader;
    private final IndexedReader indexedReader;
    private final TriConsumer<DefaultCompactWriter, GenericRecord, String> recordToWriter;

    FieldOperations(BiFunction<GenericRecord, String, Object> objectReader,
                    BufferObjectDataOutputWriter objectDataOutputWriter,
                    BufferObjectDataInputReader objectDataInputReader,
                    BiFunction<GenericRecord, String, Object> serializedFormReader,
                    TriConsumer<DefaultCompactWriter, GenericRecord, String> recordToWriter) {
        this.objectDataInputReader = objectDataInputReader;
        this.recordToWriter = recordToWriter;
        this.objectReader = objectReader;
        this.serializedFormReader = serializedFormReader == null ? objectReader : serializedFormReader;
        this.objectDataOutputWriter = objectDataOutputWriter;
        this.indexedReader = null;
        this.arrayHashCoder = null;
    }

    FieldOperations(BiFunction<GenericRecord, String, Object> objectReader,
                    BufferObjectDataOutputWriter objectDataOutputWriter,
                    BufferObjectDataInputReader objectDataInputReader,
                    BiFunction<GenericRecord, String, Object> serializedFormReader,
                    IndexedReader indexedReader,
                    ToIntBiFunction<GenericRecord, String> arrayHashCoder,
                    TriConsumer<DefaultCompactWriter, GenericRecord, String> recordToWriter) {
        this.objectDataOutputWriter = objectDataOutputWriter;
        this.objectDataInputReader = objectDataInputReader;
        this.recordToWriter = recordToWriter;
        this.objectReader = objectReader;
        this.serializedFormReader = serializedFormReader == null ? objectReader : serializedFormReader;
        this.indexedReader = indexedReader;
        this.arrayHashCoder = arrayHashCoder;
    }

    public void writeToObjectDataOutput(Compact serializer, BufferObjectDataOutput out, Object value) throws IOException {
        objectDataOutputWriter.write(serializer, out, value);
    }

    public <T> T readFromObjectDataInput(Compact serializer, BufferObjectDataInput in) throws IOException {
        return (T) objectDataInputReader.read(serializer, in);
    }

    public Object readFieldAsObject(InternalGenericRecord record, String fieldName) {
        return objectReader.apply(record, fieldName);
    }

    public Object readFieldInSerializedForm(GenericRecord record, String fieldName) {
        return serializedFormReader.apply(record, fieldName);
    }

    public Object readFieldFromArrayIndex(InternalGenericRecord record, String path, int index) {
        return indexedReader.readIndexed(record, path, index);
    }

    public int hashCodeArray(GenericRecord record, String path) {
        return arrayHashCoder.applyAsInt(record, path);
    }

    public void writeRecordFieldToWriter(DefaultCompactWriter writer, GenericRecord record, String fieldName) {
        recordToWriter.consume(writer, record, fieldName);
    }

    public static FieldOperations fieldOperations(FieldType fieldType) {
        return ALL[fieldType.getId()];
    }

    interface BufferObjectDataOutputWriter {
        void write(Compact serializer, BufferObjectDataOutput out, Object value) throws IOException;
    }

    interface BufferObjectDataInputReader {
        Object read(Compact serializer, BufferObjectDataInput out) throws IOException;
    }

    public interface IndexedReader {
        Object readIndexed(InternalGenericRecord record, String fieldName, int index);
    }

}
