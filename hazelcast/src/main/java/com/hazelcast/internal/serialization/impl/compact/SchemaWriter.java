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

import com.hazelcast.nio.serialization.FieldType;
import com.hazelcast.nio.serialization.compact.CompactWriter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;

public final class SchemaWriter implements CompactWriter {

    private final SchemaBuilder builder;

    public SchemaWriter(SchemaBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void writeInt(String fieldName, int value) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.INT));
    }

    @Override
    public void writeLong(String fieldName, long value) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.LONG));
    }

    @Override
    public void writeUTF(String fieldName, String str) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.UTF));
    }

    @Override
    public void writeBoolean(String fieldName, boolean value) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.BOOLEAN));
    }

    @Override
    public void writeByte(String fieldName, byte value) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.BYTE));
    }

    @Override
    public void writeChar(String fieldName, char value) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.CHAR));
    }

    @Override
    public void writeDouble(String fieldName, double value) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.DOUBLE));
    }

    @Override
    public void writeFloat(String fieldName, float value) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.FLOAT));
    }

    @Override
    public void writeShort(String fieldName, short value) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.SHORT));
    }

    @Override
    public void writeObject(String fieldName, Object object) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.OBJECT));
    }

    @Override
    public void writeBigInteger(String fieldName, BigInteger value) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.BIG_INTEGER));
    }

    @Override
    public void writeBigDecimal(String fieldName, BigDecimal value) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.BIG_DECIMAL));
    }

    @Override
    public void writeLocalTime(String fieldName, LocalTime value) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.LOCAL_TIME));
    }

    @Override
    public void writeLocalDate(String fieldName, LocalDate value) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.LOCAL_DATE));
    }

    @Override
    public void writeLocalDateTime(String fieldName, LocalDateTime value) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.LOCAL_DATE_TIME));
    }

    @Override
    public void writeOffsetDateTime(String fieldName, OffsetDateTime value) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.OFFSET_DATE_TIME));
    }

    @Override
    public void writeByteArray(String fieldName, byte[] bytes) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.BYTE_ARRAY));
    }

    @Override
    public void writeBooleanArray(String fieldName, boolean[] booleans) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.BOOLEAN_ARRAY));
    }

    @Override
    public void writeCharArray(String fieldName, char[] chars) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.CHAR_ARRAY));
    }

    @Override
    public void writeIntArray(String fieldName, int[] ints) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.INT_ARRAY));
    }

    @Override
    public void writeLongArray(String fieldName, long[] longs) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.LONG_ARRAY));
    }

    @Override
    public void writeDoubleArray(String fieldName, double[] values) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.DOUBLE_ARRAY));
    }

    @Override
    public void writeFloatArray(String fieldName, float[] values) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.FLOAT_ARRAY));
    }

    @Override
    public void writeShortArray(String fieldName, short[] values) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.SHORT_ARRAY));
    }

    @Override
    public void writeUTFArray(String fieldName, String[] values) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.UTF_ARRAY));
    }

    @Override
    public void writeBigIntegerArray(String fieldName, BigInteger[] values) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.BIG_INTEGER_ARRAY));
    }

    @Override
    public void writeBigDecimalArray(String fieldName, BigDecimal[] values) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.BIG_DECIMAL_ARRAY));
    }

    @Override
    public void writeLocalTimeArray(String fieldName, LocalTime[] values) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.LOCAL_TIME_ARRAY));
    }

    @Override
    public void writeLocalDateArray(String fieldName, LocalDate[] values) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.LOCAL_DATE_ARRAY));
    }

    @Override
    public void writeLocalDateTimeArray(String fieldName, LocalDateTime[] values) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.LOCAL_DATE_TIME_ARRAY));
    }

    @Override
    public void writeOffsetDateTimeArray(String fieldName, OffsetDateTime[] values) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.OFFSET_DATE_TIME_ARRAY));
    }

    @Override
    public <T> void writeObjectList(String fieldName, List<T> arrayList) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.OBJECT_ARRAY));
    }

    @Override
    public void writeObjectArray(String fieldName, Object[] values) {
        builder.addField(new FieldDescriptorImpl(fieldName, FieldType.OBJECT_ARRAY));
    }

}
