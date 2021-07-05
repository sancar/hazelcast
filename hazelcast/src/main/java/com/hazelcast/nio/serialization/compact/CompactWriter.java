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

import com.hazelcast.spi.annotation.Beta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;

/**
 * @since Hazelcast 5.0 as BETA
 */
@Beta
public interface CompactWriter {

    void writeInt(@Nonnull String fieldName, int value);

    void writeLong(@Nonnull String fieldName, long value);

    void writeString(@Nonnull String fieldName, String value);

    void writeBoolean(@Nonnull String fieldName, boolean value);

    void writeByte(@Nonnull String fieldName, byte value);

    void writeChar(@Nonnull String fieldName, char value);

    void writeDouble(@Nonnull String fieldName, double value);

    void writeFloat(@Nonnull String fieldName, float value);

    void writeShort(@Nonnull String fieldName, short value);

    void writeDecimal(@Nonnull String fieldName, @Nullable BigDecimal value);

    void writeTime(@Nonnull String fieldName, @Nonnull LocalTime value);

    void writeDate(@Nonnull String fieldName, @Nonnull LocalDate value);

    void writeTimestamp(@Nonnull String fieldName, @Nonnull LocalDateTime value);

    void writeTimestampWithTimezone(@Nonnull String fieldName, @Nonnull OffsetDateTime value);

    void writeByteArray(@Nonnull String fieldName, @Nullable byte[] value);

    void writeBooleanArray(@Nonnull String fieldName, @Nullable boolean[] booleans);

    void writeCharArray(@Nonnull String fieldName, @Nullable char[] value);

    void writeIntArray(@Nonnull String fieldName, @Nullable int[] value);

    void writeLongArray(@Nonnull String fieldName, @Nullable long[] value);

    void writeDoubleArray(@Nonnull String fieldName, @Nullable double[] value);

    void writeFloatArray(@Nonnull String fieldName, @Nullable float[] value);

    void writeShortArray(@Nonnull String fieldName, @Nullable short[] value);

    void writeStringArray(@Nonnull String fieldName, @Nullable String[] value);

    void writeDecimalArray(@Nonnull String fieldName, @Nullable BigDecimal[] values);

    void writeTimeArray(@Nonnull String fieldName, @Nullable LocalTime[] values);

    void writeDateArray(@Nonnull String fieldName, @Nullable LocalDate[] values);

    void writeTimestampArray(@Nonnull String fieldName, @Nullable LocalDateTime[] values);

    void writeTimestampWithTimezoneArray(@Nonnull String fieldName, @Nullable OffsetDateTime[] values);

    <T> void writeObject(@Nonnull String fieldName, @Nullable T value);

    <T> void writeObjectArray(@Nonnull String fieldName, @Nullable T[] value);

}
