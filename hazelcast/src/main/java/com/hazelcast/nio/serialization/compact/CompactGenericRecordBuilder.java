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

package com.hazelcast.nio.serialization.compact;

import com.hazelcast.internal.nio.BufferObjectDataInput;
import com.hazelcast.internal.nio.BufferObjectDataOutput;
import com.hazelcast.internal.serialization.impl.compact.CompactGenericRecord;
import com.hazelcast.internal.serialization.impl.compact.DefaultCompactWriter;
import com.hazelcast.internal.serialization.impl.compact.SchemaImpl;
import com.hazelcast.nio.serialization.GenericRecord;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompactGenericRecordBuilder implements GenericRecord.Builder {

    private final DefaultCompactWriter defaultCompactWriter;
    private final Compact serializer;
    private final Schema schema;
    private final Function<byte[], BufferObjectDataInput> bufferObjectDataInputFunc;
    private final int classID;

    public CompactGenericRecordBuilder(Compact serializer, Schema schema,
                                       int classID,
                                       Function<byte[], BufferObjectDataInput> bufferObjectDataInputFunc,
                                       Supplier<BufferObjectDataOutput> bufferObjectDataOutputSupplier) {
        this.serializer = serializer;
        this.schema = schema;
        this.classID = classID;
        this.defaultCompactWriter = new DefaultCompactWriter(serializer, bufferObjectDataOutputSupplier.get(), (SchemaImpl) schema);
        this.bufferObjectDataInputFunc = bufferObjectDataInputFunc;
    }

    public GenericRecord build() {
        defaultCompactWriter.end();
        //TODO SANCAR avoid copy
        byte[] bytes = defaultCompactWriter.toByteArray();
        return new CompactGenericRecord(serializer, classID, bufferObjectDataInputFunc.apply(bytes), schema);
    }

    @Override
    public GenericRecord.Builder writeInt(String fieldName, int value) {
        defaultCompactWriter.writeInt(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLong(String fieldName, long value) {
        defaultCompactWriter.writeLong(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeUTF(String fieldName, String value) {
        defaultCompactWriter.writeUTF(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeBoolean(String fieldName, boolean value) {
        defaultCompactWriter.writeBoolean(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeByte(String fieldName, byte value) {
        defaultCompactWriter.writeByte(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeChar(String fieldName, char value) {
        defaultCompactWriter.writeChar(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeDouble(String fieldName, double value) {
        defaultCompactWriter.writeDouble(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeFloat(String fieldName, float value) {
        defaultCompactWriter.writeFloat(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeShort(String fieldName, short value) {
        defaultCompactWriter.writeShort(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeBigDecimal(String fieldName, BigDecimal value) {
        defaultCompactWriter.writeBigDecimal(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalTime(String fieldName, LocalTime value) {
        defaultCompactWriter.writeLocalTime(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalDate(String fieldName, LocalDate value) {
        defaultCompactWriter.writeLocalDate(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalDateTime(String fieldName, LocalDateTime value) {
        defaultCompactWriter.writeLocalDateTime(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeOffsetDateTime(String fieldName, OffsetDateTime value) {
        defaultCompactWriter.writeOffsetDateTime(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeGenericRecord(String fieldName, GenericRecord value) {
        defaultCompactWriter.writeGenericRecord(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeGenericRecordArray(String fieldName, GenericRecord[] value) {
        defaultCompactWriter.writeGenericRecordArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeByteArray(String fieldName, byte[] value) {
        defaultCompactWriter.writeByteArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeBooleanArray(String fieldName, boolean[] value) {
        defaultCompactWriter.writeBooleanArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeCharArray(String fieldName, char[] value) {
        defaultCompactWriter.writeCharArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeIntArray(String fieldName, int[] value) {
        defaultCompactWriter.writeIntArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLongArray(String fieldName, long[] value) {
        defaultCompactWriter.writeLongArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeDoubleArray(String fieldName, double[] value) {
        defaultCompactWriter.writeDoubleArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeFloatArray(String fieldName, float[] value) {
        defaultCompactWriter.writeFloatArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeShortArray(String fieldName, short[] value) {
        defaultCompactWriter.writeShortArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeUTFArray(String fieldName, String[] value) {
        defaultCompactWriter.writeUTFArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeBigInteger(String fieldName, BigInteger value) {
        defaultCompactWriter.writeBigInteger(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeBigIntegerArray(String fieldName, BigInteger[] value) {
        defaultCompactWriter.writeBigIntegerArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeBigDecimalArray(String fieldName, BigDecimal[] value) {
        defaultCompactWriter.writeBigDecimalArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalTimeArray(String fieldName, LocalTime[] value) {
        defaultCompactWriter.writeLocalTimeArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalDateArray(String fieldName, LocalDate[] value) {
        defaultCompactWriter.writeLocalDateArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalDateTimeArray(String fieldName, LocalDateTime[] value) {
        defaultCompactWriter.writeLocalDateTimeArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeOffsetDateTimeArray(String fieldName, OffsetDateTime[] value) {
        defaultCompactWriter.writeOffsetDateTimeArray(fieldName, value);
        return this;
    }

}
