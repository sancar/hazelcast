/*
 * Copyright (c) 2008-2021, Hazelcast, Inc. All Rights Reserved.
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.TreeMap;

public final class SchemaWriter implements CompactWriter {

    private final TreeMap<String, FieldDescriptor> fieldDefinitionMap = new TreeMap<>(Comparator.naturalOrder());
    private final String className;

    public SchemaWriter(String className) {
        this.className = className;
    }

    public Schema build() {
        return new Schema(className, fieldDefinitionMap);
    }

    public void addField(FieldDescriptor fieldDefinition) {
        fieldDefinitionMap.put(fieldDefinition.getFieldName(), fieldDefinition);
    }

    @Override
    public void writeByte(@Nonnull String fieldName, byte value) {
        addField(new FieldDescriptor(fieldName, FieldType.BYTE));
    }

    @Override
    public void writeBoolean(@Nonnull String fieldName, boolean value) {
        addField(new FieldDescriptor(fieldName, FieldType.BOOLEAN));
    }

    @Override
    public void writeChar(@Nonnull String fieldName, char value) {
        addField(new FieldDescriptor(fieldName, FieldType.CHAR));
    }

    @Override
    public void writeShort(@Nonnull String fieldName, short value) {
        addField(new FieldDescriptor(fieldName, FieldType.SHORT));
    }

    @Override
    public void writeInt(@Nonnull String fieldName, int value) {
        addField(new FieldDescriptor(fieldName, FieldType.INT));
    }

    @Override
    public void writeLong(@Nonnull String fieldName, long value) {
        addField(new FieldDescriptor(fieldName, FieldType.LONG));
    }

    @Override
    public void writeString(@Nonnull String fieldName, @Nullable String str) {
        addField(new FieldDescriptor(fieldName, FieldType.UTF));
    }

    @Override
    public void writeObject(@Nonnull String fieldName, @Nullable Object object) {
        addField(new FieldDescriptor(fieldName, FieldType.COMPOSED));
    }

    @Override
    public void writeFloat(@Nonnull String fieldName, float value) {
        addField(new FieldDescriptor(fieldName, FieldType.FLOAT));
    }

    @Override
    public void writeDouble(@Nonnull String fieldName, double value) {
        addField(new FieldDescriptor(fieldName, FieldType.DOUBLE));
    }

    @Override
    public void writeDecimal(@Nonnull String fieldName, @Nullable BigDecimal value) {
        addField(new FieldDescriptor(fieldName, FieldType.DECIMAL));
    }

    @Override
    public void writeTime(@Nonnull String fieldName, @Nonnull LocalTime value) {
        addField(new FieldDescriptor(fieldName, FieldType.TIME));
    }

    @Override
    public void writeDate(@Nonnull String fieldName, @Nonnull LocalDate value) {
        addField(new FieldDescriptor(fieldName, FieldType.DATE));
    }

    @Override
    public void writeTimestamp(@Nonnull String fieldName, @Nonnull LocalDateTime value) {
        addField(new FieldDescriptor(fieldName, FieldType.TIMESTAMP));
    }

    @Override
    public void writeTimestampWithTimezone(@Nonnull String fieldName, @Nonnull OffsetDateTime value) {
        addField(new FieldDescriptor(fieldName, FieldType.TIMESTAMP_WITH_TIMEZONE));
    }

    @Override
    public void writeByteArray(@Nonnull String fieldName, @Nullable byte[] bytes) {
        addField(new FieldDescriptor(fieldName, FieldType.BYTE_ARRAY));
    }

    @Override
    public void writeBooleanArray(@Nonnull String fieldName, @Nullable boolean[] booleans) {
        addField(new FieldDescriptor(fieldName, FieldType.BOOLEAN_ARRAY));
    }

    @Override
    public void writeCharArray(@Nonnull String fieldName, @Nullable char[] chars) {
        addField(new FieldDescriptor(fieldName, FieldType.CHAR_ARRAY));
    }

    @Override
    public void writeIntArray(@Nonnull String fieldName, @Nullable int[] ints) {
        addField(new FieldDescriptor(fieldName, FieldType.INT_ARRAY));
    }

    @Override
    public void writeLongArray(@Nonnull String fieldName, @Nullable long[] longs) {
        addField(new FieldDescriptor(fieldName, FieldType.LONG_ARRAY));
    }

    @Override
    public void writeDoubleArray(@Nonnull String fieldName, @Nullable double[] values) {
        addField(new FieldDescriptor(fieldName, FieldType.DOUBLE_ARRAY));
    }

    @Override
    public void writeFloatArray(@Nonnull String fieldName, @Nullable float[] values) {
        addField(new FieldDescriptor(fieldName, FieldType.FLOAT_ARRAY));
    }

    @Override
    public void writeShortArray(@Nonnull String fieldName, @Nullable short[] values) {
        addField(new FieldDescriptor(fieldName, FieldType.SHORT_ARRAY));
    }

    @Override
    public void writeStringArray(@Nonnull String fieldName, @Nullable String[] values) {
        addField(new FieldDescriptor(fieldName, FieldType.UTF_ARRAY));
    }

    @Override
    public void writeDecimalArray(@Nonnull String fieldName, @Nullable BigDecimal[] values) {
        addField(new FieldDescriptor(fieldName, FieldType.DECIMAL_ARRAY));
    }

    @Override
    public void writeTimeArray(@Nonnull String fieldName, @Nullable LocalTime[] values) {
        addField(new FieldDescriptor(fieldName, FieldType.TIME_ARRAY));
    }

    @Override
    public void writeDateArray(@Nonnull String fieldName, @Nullable LocalDate[] values) {
        addField(new FieldDescriptor(fieldName, FieldType.DATE_ARRAY));
    }

    @Override
    public void writeTimestampArray(@Nonnull String fieldName, @Nullable LocalDateTime[] values) {
        addField(new FieldDescriptor(fieldName, FieldType.TIMESTAMP_ARRAY));
    }

    @Override
    public void writeTimestampWithTimezoneArray(@Nonnull String fieldName, @Nullable OffsetDateTime[] values) {
        addField(new FieldDescriptor(fieldName, FieldType.TIMESTAMP_WITH_TIMEZONE_ARRAY));
    }

    @Override
    public void writeNullableTime(@Nonnull String fieldName, @Nullable LocalTime nullableTime) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_TIME));
    }

    @Override
    public void writeNullableDate(@Nonnull String fieldName, @Nullable LocalDate value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_DATE));
    }

    @Override
    public void writeNullableTimestamp(@Nonnull String fieldName, @Nullable LocalDateTime value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_TIMESTAMP));
    }

    @Override
    public void writeNullableTimestampWithTimezone(@Nonnull String fieldName, @Nullable OffsetDateTime value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_TIMESTAMP_WITH_TIMEZONE));
    }

    @Override
    public void writeObjectArray(@Nonnull String fieldName, @Nullable Object[] values) {
        addField(new FieldDescriptor(fieldName, FieldType.COMPOSED_ARRAY));
    }

    @Override
    public void writeNullableTimeArray(@Nonnull String fieldName, @Nullable LocalTime[] value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_TIME_ARRAY));
    }

    @Override
    public void writeNullableDateArray(@Nonnull String fieldName, @Nullable LocalDate[] value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_DATE_ARRAY));
    }

    @Override
    public void writeNullableTimestampArray(@Nonnull String fieldName, @Nullable LocalDateTime[] value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_TIMESTAMP_ARRAY));
    }

    @Override
    public void writeNullableTimestampWithTimezoneArray(@Nonnull String fieldName, @Nullable OffsetDateTime[] value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_TIMESTAMP_WITH_TIMEZONE_ARRAY));
    }

    @Override
    public void writeNullableByte(@Nonnull String fieldName, @Nullable Byte value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_BYTE));
    }

    @Override
    public void writeNullableBoolean(@Nonnull String fieldName, @Nullable Boolean value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_BOOLEAN));
    }

    @Override
    public void writeNullableChar(@Nonnull String fieldName, @Nullable Character value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_CHAR));
    }

    @Override
    public void writeNullableShort(@Nonnull String fieldName, @Nullable Short value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_SHORT));
    }

    @Override
    public void writeNullableInt(@Nonnull String fieldName, @Nullable Integer value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_INT));
    }

    @Override
    public void writeNullableLong(@Nonnull String fieldName, @Nullable Long value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_LONG));
    }

    @Override
    public void writeNullableFloat(@Nonnull String fieldName, @Nullable Float value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_FLOAT));
    }

    @Override
    public void writeNullableDouble(@Nonnull String fieldName, @Nullable Double value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_DOUBLE));
    }

    @Override
    public void writeNullableByteArray(@Nonnull String fieldName, @Nullable Byte[] value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_BYTE_ARRAY));
    }

    @Override
    public void writeNullableBooleanArray(@Nonnull String fieldName, @Nullable Boolean[] value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_BOOLEAN_ARRAY));
    }

    @Override
    public void writeNullableCharArray(@Nonnull String fieldName, @Nullable Character[] value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_CHAR_ARRAY));
    }

    @Override
    public void writeNullableShortArray(@Nonnull String fieldName, @Nullable Short[] value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_SHORT_ARRAY));
    }

    @Override
    public void writeNullableIntArray(@Nonnull String fieldName, @Nullable Integer[] value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_INT_ARRAY));
    }

    @Override
    public void writeNullableLongArray(@Nonnull String fieldName, @Nullable Long[] value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_LONG_ARRAY));
    }

    @Override
    public void writeNullableFloatArray(@Nonnull String fieldName, @Nullable Float[] value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_FLOAT_ARRAY));
    }

    @Override
    public void writeNullableDoubleArray(@Nonnull String fieldName, @Nullable Double[] value) {
        addField(new FieldDescriptor(fieldName, FieldType.NULLABLE_DOUBLE_ARRAY));
    }
}
