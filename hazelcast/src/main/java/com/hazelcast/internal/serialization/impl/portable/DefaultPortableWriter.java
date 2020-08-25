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

import com.hazelcast.internal.nio.BufferObjectDataOutput;
import com.hazelcast.internal.nio.IOUtil;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.ClassDefinition;
import com.hazelcast.nio.serialization.FieldDefinition;
import com.hazelcast.nio.serialization.FieldType;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.HazelcastSerializationException;
import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Set;

import static com.hazelcast.internal.nio.Bits.INT_SIZE_IN_BYTES;
import static com.hazelcast.internal.nio.Bits.NULL_ARRAY_LENGTH;
import static com.hazelcast.internal.util.SetUtil.createHashSet;

public class DefaultPortableWriter implements PortableWriter {

    private final PortableSerializer serializer;
    private final ClassDefinition cd;
    private final BufferObjectDataOutput out;
    private final int begin;
    private final int offset;
    private final Set<String> writtenFields;

    private boolean raw;

    DefaultPortableWriter(PortableSerializer serializer, BufferObjectDataOutput out, ClassDefinition cd) throws IOException {
        this.serializer = serializer;
        this.out = out;
        this.cd = cd;
        this.writtenFields = createHashSet(cd.getFieldCount());
        this.begin = out.position();

        // room for final offset
        out.writeZeroBytes(4);

        out.writeInt(cd.getFieldCount());

        this.offset = out.position();
        // one additional for raw data
        int fieldIndexesLength = (cd.getFieldCount() + 1) * INT_SIZE_IN_BYTES;
        out.writeZeroBytes(fieldIndexesLength);
    }

    public int getVersion() {
        return cd.getVersion();
    }

    @Override
    public void writeInt(String fieldName, int value) throws IOException {
        setPosition(fieldName, FieldType.INT);
        out.writeInt(value);
    }

    @Override
    public void writeLong(String fieldName, long value) throws IOException {
        setPosition(fieldName, FieldType.LONG);
        out.writeLong(value);
    }

    @Override
    public void writeUTF(String fieldName, String str) throws IOException {
        setPosition(fieldName, FieldType.UTF);
        out.writeUTF(str);
    }

    @Override
    public void writeBoolean(String fieldName, boolean value) throws IOException {
        setPosition(fieldName, FieldType.BOOLEAN);
        out.writeBoolean(value);
    }

    @Override
    public void writeByte(String fieldName, byte value) throws IOException {
        setPosition(fieldName, FieldType.BYTE);
        out.writeByte(value);
    }

    @Override
    public void writeChar(String fieldName, int value) throws IOException {
        setPosition(fieldName, FieldType.CHAR);
        out.writeChar(value);
    }

    @Override
    public void writeDouble(String fieldName, double value) throws IOException {
        setPosition(fieldName, FieldType.DOUBLE);
        out.writeDouble(value);
    }

    @Override
    public void writeFloat(String fieldName, float value) throws IOException {
        setPosition(fieldName, FieldType.FLOAT);
        out.writeFloat(value);
    }

    @Override
    public void writeShort(String fieldName, short value) throws IOException {
        setPosition(fieldName, FieldType.SHORT);
        out.writeShort(value);
    }

    @Override
    public void writePortable(String fieldName, Portable portable) throws IOException {
        FieldDefinition fd = setPosition(fieldName, FieldType.PORTABLE);
        final boolean isNull = portable == null;
        out.writeBoolean(isNull);

        out.writeInt(fd.getFactoryId());
        out.writeInt(fd.getClassId());

        if (!isNull) {
            checkPortableAttributes(fd, portable);
            serializer.writeInternal(out, portable);
        }
    }

    public void writeGenericRecord(String fieldName, GenericRecord portable) throws IOException {
        FieldDefinition fd = setPosition(fieldName, FieldType.PORTABLE);
        final boolean isNull = portable == null;
        out.writeBoolean(isNull);

        out.writeInt(fd.getFactoryId());
        out.writeInt(fd.getClassId());

        if (!isNull) {
            PortableGenericRecord record = (PortableGenericRecord) portable;
            checkPortableAttributes(fd, record.getClassDefinition());
            serializer.writePortableGenericRecordInternal(out, record);
        }
    }

    private void checkPortableAttributes(FieldDefinition fd, Portable portable) {
        if (fd.getFactoryId() != portable.getFactoryId()) {
            throw new HazelcastSerializationException("Wrong Portable type! Generic portable types are not supported! "
                    + " Expected factory-id: " + fd.getFactoryId() + ", Actual factory-id: " + portable.getFactoryId());
        }
        if (fd.getClassId() != portable.getClassId()) {
            throw new HazelcastSerializationException("Wrong Portable type! Generic portable types are not supported! "
                    + "Expected class-id: " + fd.getClassId() + ", Actual class-id: " + portable.getClassId());
        }
    }

    private void checkPortableAttributes(FieldDefinition fd, ClassDefinition classDefinition) {
        if (fd.getFactoryId() != classDefinition.getFactoryId()) {
            throw new HazelcastSerializationException("Wrong Portable type! Generic portable types are not supported! "
                    + " Expected factory-id: " + fd.getFactoryId() + ", Actual factory-id: " + classDefinition.getFactoryId());
        }
        if (fd.getClassId() != classDefinition.getClassId()) {
            throw new HazelcastSerializationException("Wrong Portable type! Generic portable types are not supported! "
                    + "Expected class-id: " + fd.getClassId() + ", Actual class-id: " + classDefinition.getClassId());
        }
    }

    @Override
    public void writeNullPortable(String fieldName, int factoryId, int classId) throws IOException {
        setPosition(fieldName, FieldType.PORTABLE);
        out.writeBoolean(true);

        out.writeInt(factoryId);
        out.writeInt(classId);
    }

    @Override
    public void writeBigInteger(String fieldName, BigInteger value) throws IOException {
        setPosition(fieldName, FieldType.BIG_INTEGER);
        boolean isNull = value == null;
        out.writeBoolean(isNull);
        if (!isNull) {
            IOUtil.writeBigInteger(out, value);
        }
    }

    @Override
    public void writeBigDecimal(String fieldName, BigDecimal value) throws IOException {
        setPosition(fieldName, FieldType.BIG_DECIMAL);
        boolean isNull = value == null;
        out.writeBoolean(isNull);
        if (!isNull) {
            IOUtil.writeBigDecimal(out, value);
        }
    }

    @Override
    public void writeLocalTime(String fieldName, LocalTime value) throws IOException {
        setPosition(fieldName, FieldType.LOCAL_TIME);
        boolean isNull = value == null;
        out.writeBoolean(isNull);
        if (!isNull) {
            IOUtil.writeLocalTime(out, value);
        }
    }

    @Override
    public void writeLocalDate(String fieldName, LocalDate value) throws IOException {
        setPosition(fieldName, FieldType.LOCAL_DATE);
        boolean isNull = value == null;
        out.writeBoolean(isNull);
        if (!isNull) {
            IOUtil.writeLocalDate(out, value);
        }
    }

    @Override
    public void writeLocalDateTime(String fieldName, LocalDateTime value) throws IOException {
        setPosition(fieldName, FieldType.LOCAL_DATE_TIME);
        boolean isNull = value == null;
        out.writeBoolean(isNull);
        if (!isNull) {
            IOUtil.writeLocalDateTime(out, value);
        }
    }

    @Override
    public void writeOffsetDateTime(String fieldName, OffsetDateTime value) throws IOException {
        setPosition(fieldName, FieldType.OFFSET_DATE_TIME);
        boolean isNull = value == null;
        out.writeBoolean(isNull);
        if (!isNull) {
            IOUtil.writeOffsetDateTime(out, value);
        }
    }

    @Override
    public void writeByteArray(String fieldName, byte[] values) throws IOException {
        setPosition(fieldName, FieldType.BYTE_ARRAY);
        out.writeByteArray(values);
    }

    @Override
    public void writeBooleanArray(String fieldName, boolean[] booleans) throws IOException {
        setPosition(fieldName, FieldType.BOOLEAN_ARRAY);
        out.writeBooleanArray(booleans);
    }

    @Override
    public void writeCharArray(String fieldName, char[] values) throws IOException {
        setPosition(fieldName, FieldType.CHAR_ARRAY);
        out.writeCharArray(values);
    }

    @Override
    public void writeIntArray(String fieldName, int[] values) throws IOException {
        setPosition(fieldName, FieldType.INT_ARRAY);
        out.writeIntArray(values);
    }

    @Override
    public void writeLongArray(String fieldName, long[] values) throws IOException {
        setPosition(fieldName, FieldType.LONG_ARRAY);
        out.writeLongArray(values);
    }

    @Override
    public void writeDoubleArray(String fieldName, double[] values) throws IOException {
        setPosition(fieldName, FieldType.DOUBLE_ARRAY);
        out.writeDoubleArray(values);
    }

    @Override
    public void writeFloatArray(String fieldName, float[] values) throws IOException {
        setPosition(fieldName, FieldType.FLOAT_ARRAY);
        out.writeFloatArray(values);
    }

    @Override
    public void writeShortArray(String fieldName, short[] values) throws IOException {
        setPosition(fieldName, FieldType.SHORT_ARRAY);
        out.writeShortArray(values);
    }

    @Override
    public void writeUTFArray(String fieldName, String[] values) throws IOException {
        setPosition(fieldName, FieldType.UTF_ARRAY);
        out.writeUTFArray(values);
    }

    @Override
    public void writePortableArray(String fieldName, Portable[] portables) throws IOException {
        FieldDefinition fd = setPosition(fieldName, FieldType.PORTABLE_ARRAY);
        final int len = portables == null ? NULL_ARRAY_LENGTH : portables.length;
        out.writeInt(len);

        out.writeInt(fd.getFactoryId());
        out.writeInt(fd.getClassId());

        if (len > 0) {
            final int offset = out.position();
            out.writeZeroBytes(len * 4);
            for (int i = 0; i < len; i++) {
                Portable portable = portables[i];
                checkPortableAttributes(fd, portable);
                int position = out.position();
                out.writeInt(offset + i * INT_SIZE_IN_BYTES, position);
                serializer.writeInternal(out, portable);
            }
        }
    }

    interface Writer<O, T> {

        void write(O out, T value) throws IOException;
    }

    private <T> void writeObjectArrayField(String fieldName, FieldType fieldType, T[] values, Writer<ObjectDataOutput, T> writer)
            throws IOException {
        setPosition(fieldName, fieldType);
        final int len = values == null ? NULL_ARRAY_LENGTH : values.length;
        out.writeInt(len);

        if (len > 0) {
            final int offset = out.position();
            out.writeZeroBytes(len * 4);
            for (int i = 0; i < len; i++) {
                int position = out.position();
                if (values[i] == null) {
                    throw new HazelcastSerializationException("Array items can not be null");
                } else {
                    out.writeInt(offset + i * INT_SIZE_IN_BYTES, position);
                    writer.write(out, values[i]);
                }
            }
        }
    }

    @Override
    public void writeBigIntegerArray(String fieldName, BigInteger[] values) throws IOException {
        writeObjectArrayField(fieldName, FieldType.BIG_INTEGER_ARRAY, values, IOUtil::writeBigInteger);
    }

    @Override
    public void writeBigDecimalArray(String fieldName, BigDecimal[] values) throws IOException {
        writeObjectArrayField(fieldName, FieldType.BIG_DECIMAL_ARRAY, values, IOUtil::writeBigDecimal);
    }

    @Override
    public void writeLocalTimeArray(String fieldName, LocalTime[] values) throws IOException {
        writeObjectArrayField(fieldName, FieldType.LOCAL_TIME_ARRAY, values, IOUtil::writeLocalTime);
    }

    @Override
    public void writeLocalDateArray(String fieldName, LocalDate[] values) throws IOException {
        writeObjectArrayField(fieldName, FieldType.LOCAL_DATE_ARRAY, values, IOUtil::writeLocalDate);
    }

    @Override
    public void writeLocalDateTimeArray(String fieldName, LocalDateTime[] values) throws IOException {
        writeObjectArrayField(fieldName, FieldType.LOCAL_DATE_TIME_ARRAY, values, IOUtil::writeLocalDateTime);
    }

    @Override
    public void writeOffsetDateTimeArray(String fieldName, OffsetDateTime[] values) throws IOException {
        writeObjectArrayField(fieldName, FieldType.OFFSET_DATE_TIME_ARRAY, values, IOUtil::writeOffsetDateTime);
    }

    void writeGenericRecordArray(String fieldName, GenericRecord[] portables) throws IOException {
        FieldDefinition fd = setPosition(fieldName, FieldType.PORTABLE_ARRAY);
        final int len = portables == null ? NULL_ARRAY_LENGTH : portables.length;
        out.writeInt(len);

        out.writeInt(fd.getFactoryId());
        out.writeInt(fd.getClassId());

        if (len > 0) {
            final int offset = out.position();
            out.writeZeroBytes(len * 4);
            for (int i = 0; i < len; i++) {
                PortableGenericRecord portable = (PortableGenericRecord) portables[i];
                checkPortableAttributes(fd, portable.getClassDefinition());
                int position = out.position();
                out.writeInt(offset + i * INT_SIZE_IN_BYTES, position);
                serializer.writePortableGenericRecordInternal(out, portable);
            }
        }
    }

    private FieldDefinition setPosition(String fieldName, FieldType fieldType) throws IOException {
        if (raw) {
            throw new HazelcastSerializationException("Cannot write Portable fields after getRawDataOutput() is called!");
        }
        FieldDefinition fd = cd.getField(fieldName);
        if (fd == null) {
            throw new HazelcastSerializationException("Invalid field name: '" + fieldName
                    + "' for ClassDefinition {id: " + cd.getClassId() + ", version: " + cd.getVersion() + "}");
        }
        if (writtenFields.add(fieldName)) {
            int pos = out.position();
            int index = fd.getIndex();
            out.writeInt(offset + index * INT_SIZE_IN_BYTES, pos);
            out.writeShort(fieldName.length());
            out.writeBytes(fieldName);
            out.writeByte(fieldType.getId());
        } else {
            throw new HazelcastSerializationException("Field '" + fieldName + "' has already been written!");
        }
        return fd;
    }

    @Override
    public ObjectDataOutput getRawDataOutput() throws IOException {
        if (!raw) {
            int pos = out.position();
            // last index
            int index = cd.getFieldCount();
            out.writeInt(offset + index * INT_SIZE_IN_BYTES, pos);
        }
        raw = true;
        return out;
    }

    void end() throws IOException {
        // write final offset
        int position = out.position();
        out.writeInt(begin, position);
    }
}
