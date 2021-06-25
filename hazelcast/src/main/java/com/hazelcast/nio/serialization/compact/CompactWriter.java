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

package com.hazelcast.nio.serialization.compact;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;

public interface CompactWriter {

    void writeByte(@Nonnull String fieldName, byte value);

    void writeBoolean(@Nonnull String fieldName, boolean value);

    void writeChar(@Nonnull String fieldName, char value);

    void writeShort(@Nonnull String fieldName, short value);

    void writeInt(@Nonnull String fieldName, int value);

    void writeLong(@Nonnull String fieldName, long value);

    void writeFloat(@Nonnull String fieldName, float value);

    void writeDouble(@Nonnull String fieldName, double value);

    void writeString(@Nonnull String fieldName, @Nullable String value);

    <T> void writeObject(@Nonnull String fieldName, @Nullable T value);

    void writeDecimal(@Nonnull String fieldName, @Nullable BigDecimal value);

    void writeTime(@Nonnull String fieldName, @Nonnull LocalTime value);

    void writeDate(@Nonnull String fieldName, @Nonnull LocalDate value);

    void writeTimestamp(@Nonnull String fieldName, @Nonnull LocalDateTime value);

    void writeTimestampWithTimezone(@Nonnull String fieldName, @Nonnull OffsetDateTime value);

    void writeByteArray(@Nonnull String fieldName, @Nullable byte[] value);

    void writeBooleanArray(@Nonnull String fieldName, @Nullable boolean[] value);

    void writeCharArray(@Nonnull String fieldName, @Nullable char[] value);

    void writeIntArray(@Nonnull String fieldName, @Nullable int[] value);

    void writeLongArray(@Nonnull String fieldName, @Nullable long[] value);

    void writeDoubleArray(@Nonnull String fieldName, @Nullable double[] value);

    void writeFloatArray(@Nonnull String fieldName, @Nullable float[] value);

    void writeShortArray(@Nonnull String fieldName, @Nullable short[] value);

    void writeStringArray(@Nonnull String fieldName, @Nullable String[] value);

    void writeDecimalArray(@Nonnull String fieldName, @Nullable BigDecimal[] value);

    void writeTimeArray(@Nonnull String fieldName, @Nullable LocalTime[] value);

    void writeDateArray(@Nonnull String fieldName, @Nullable LocalDate[] value);

    void writeTimestampArray(@Nonnull String fieldName, @Nullable LocalDateTime[] value);

    void writeTimestampWithTimezoneArray(@Nonnull String fieldName, @Nullable OffsetDateTime[] value);

    <T> void writeObjectArray(@Nonnull String fieldName, @Nullable T[] value);

    void writeNullableTime(@Nonnull String fieldName, @Nullable LocalTime value);

    void writeNullableDate(@Nonnull String fieldName, @Nullable LocalDate value);

    void writeNullableTimestamp(@Nonnull String fieldName, @Nullable LocalDateTime value);

    void writeNullableTimestampWithTimezone(@Nonnull String fieldName, @Nullable OffsetDateTime value);

    void writeNullableTimeArray(@Nonnull String fieldName, @Nullable LocalTime[] value);

    void writeNullableDateArray(@Nonnull String fieldName, @Nullable LocalDate[] value);

    void writeNullableTimestampArray(@Nonnull String fieldName, @Nullable LocalDateTime[] value);

    void writeNullableTimestampWithTimezoneArray(@Nonnull String fieldName, @Nullable OffsetDateTime[] value);

    void writeNullableByte(@Nonnull String fieldName, @Nullable Byte value);

    void writeNullableBoolean(@Nonnull String fieldName, @Nullable Boolean value);

    void writeNullableChar(@Nonnull String fieldName, @Nullable Character value);

    void writeNullableShort(@Nonnull String fieldName, @Nullable Short value);

    void writeNullableInt(@Nonnull String fieldName, @Nullable Integer value);

    void writeNullableLong(@Nonnull String fieldName, @Nullable Long value);

    void writeNullableFloat(@Nonnull String fieldName, @Nullable Float value);

    void writeNullableDouble(@Nonnull String fieldName, @Nullable Double value);

    void writeNullableByteArray(@Nonnull String fieldName, @Nullable Byte[] value);

    void writeNullableBooleanArray(@Nonnull String fieldName, @Nullable Boolean[] value);

    void writeNullableCharArray(@Nonnull String fieldName, @Nullable Character[] value);

    void writeNullableShortArray(@Nonnull String fieldName, @Nullable Short[] value);

    void writeNullableIntArray(@Nonnull String fieldName, @Nullable Integer[] value);

    void writeNullableLongArray(@Nonnull String fieldName, @Nullable Long[] value);

    void writeNullableFloatArray(@Nonnull String fieldName, @Nullable Float[] value);

    void writeNullableDoubleArray(@Nonnull String fieldName, @Nullable Double[] value);
}

