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


import com.hazelcast.internal.nio.BufferObjectDataInput;
import com.hazelcast.internal.nio.BufferObjectDataOutput;
import com.hazelcast.nio.serialization.GenericRecord;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.function.Function;
import java.util.function.Supplier;

public class SerializingGenericRecordBuilder implements GenericRecord.Builder {

    private final DefaultCompactWriter defaultCompactWriter;
    private final Compact serializer;
    private final Schema schema;
    private final Function<byte[], BufferObjectDataInput> bufferObjectDataInputFunc;

    public SerializingGenericRecordBuilder(Compact serializer, Schema schema,
                                           Function<byte[], BufferObjectDataInput> bufferObjectDataInputFunc,
                                           Supplier<BufferObjectDataOutput> bufferObjectDataOutputSupplier) {
        this.serializer = serializer;
        this.schema = schema;
        this.defaultCompactWriter = new DefaultCompactWriter(serializer, bufferObjectDataOutputSupplier.get(), (SchemaImpl) schema);
        this.bufferObjectDataInputFunc = bufferObjectDataInputFunc;
    }

    public @NotNull GenericRecord build() {
        defaultCompactWriter.end();
        byte[] bytes = defaultCompactWriter.toByteArray();
        return new DefaultCompactReader(serializer, bufferObjectDataInputFunc.apply(bytes), schema, null, bytes.length);
    }

    @Override
    public GenericRecord.Builder writeInt(@NotNull String fieldName, int value) {
        defaultCompactWriter.writeInt(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLong(@NotNull String fieldName, long value) {
        defaultCompactWriter.writeLong(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeUTF(@NotNull String fieldName, String value) {
        defaultCompactWriter.writeUTF(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeBoolean(@NotNull String fieldName, boolean value) {
        defaultCompactWriter.writeBoolean(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeByte(@NotNull String fieldName, byte value) {
        defaultCompactWriter.writeByte(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeChar(@NotNull String fieldName, char value) {
        defaultCompactWriter.writeChar(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeDouble(@NotNull String fieldName, double value) {
        defaultCompactWriter.writeDouble(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeFloat(@NotNull String fieldName, float value) {
        defaultCompactWriter.writeFloat(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeShort(@NotNull String fieldName, short value) {
        defaultCompactWriter.writeShort(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeBigDecimal(@NotNull String fieldName, BigDecimal value) {
        defaultCompactWriter.writeBigDecimal(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalTime(@NotNull String fieldName, LocalTime value) {
        defaultCompactWriter.writeLocalTime(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalDate(@NotNull String fieldName, LocalDate value) {
        defaultCompactWriter.writeLocalDate(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalDateTime(@NotNull String fieldName, LocalDateTime value) {
        defaultCompactWriter.writeLocalDateTime(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeOffsetDateTime(@NotNull String fieldName, OffsetDateTime value) {
        defaultCompactWriter.writeOffsetDateTime(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeGenericRecord(@NotNull String fieldName, GenericRecord value) {
        defaultCompactWriter.writeGenericRecord(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeGenericRecordArray(@NotNull String fieldName, GenericRecord[] value) {
        defaultCompactWriter.writeGenericRecordArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeByteArray(@NotNull String fieldName, byte[] value) {
        defaultCompactWriter.writeByteArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeBooleanArray(@NotNull String fieldName, boolean[] value) {
        defaultCompactWriter.writeBooleanArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeCharArray(@NotNull String fieldName, char[] value) {
        defaultCompactWriter.writeCharArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeIntArray(@NotNull String fieldName, int[] value) {
        defaultCompactWriter.writeIntArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLongArray(@NotNull String fieldName, long[] value) {
        defaultCompactWriter.writeLongArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeDoubleArray(@NotNull String fieldName, double[] value) {
        defaultCompactWriter.writeDoubleArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeFloatArray(@NotNull String fieldName, float[] value) {
        defaultCompactWriter.writeFloatArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeShortArray(@NotNull String fieldName, short[] value) {
        defaultCompactWriter.writeShortArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeUTFArray(@NotNull String fieldName, String[] value) {
        defaultCompactWriter.writeUTFArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeBigInteger(@NotNull String fieldName, BigInteger value) {
        defaultCompactWriter.writeBigInteger(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeBigIntegerArray(@NotNull String fieldName, BigInteger[] value) {
        defaultCompactWriter.writeBigIntegerArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeBigDecimalArray(@NotNull String fieldName, BigDecimal[] value) {
        defaultCompactWriter.writeBigDecimalArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalTimeArray(@NotNull String fieldName, LocalTime[] value) {
        defaultCompactWriter.writeLocalTimeArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalDateArray(@NotNull String fieldName, LocalDate[] value) {
        defaultCompactWriter.writeLocalDateArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeLocalDateTimeArray(@NotNull String fieldName, LocalDateTime[] value) {
        defaultCompactWriter.writeLocalDateTimeArray(fieldName, value);
        return this;
    }

    @Override
    public GenericRecord.Builder writeOffsetDateTimeArray(@NotNull String fieldName, OffsetDateTime[] value) {
        defaultCompactWriter.writeOffsetDateTimeArray(fieldName, value);
        return this;
    }

}
