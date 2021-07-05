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
 * All read(String fieldName) methods throw HazelcastSerializationException when the related field is not found or
 * there is a type mismatch.
 * To avoid exception when the field is not found, the user can make use of `read*(String fieldName, T defaultValue)`.
 * Especially useful, when class is evolved  (a new field is added to/removed from the class).
 *
 * @since Hazelcast 5.0 as BETA
 */
@Beta
public interface CompactReader {

    byte readByte(@Nonnull String fieldName);

    byte readByte(@Nonnull String fieldName, byte defaultValue);

    short readShort(@Nonnull String fieldName);

    short readShort(@Nonnull String fieldName, short defaultValue);

    int readInt(@Nonnull String fieldName);

    int readInt(@Nonnull String fieldName, int defaultValue);

    long readLong(@Nonnull String fieldName);

    long readLong(@Nonnull String fieldName, long defaultValue);

    float readFloat(@Nonnull String fieldName);

    float readFloat(@Nonnull String fieldName, float defaultValue);

    double readDouble(@Nonnull String fieldName);

    double readDouble(@Nonnull String fieldName, double defaultValue);

    boolean readBoolean(@Nonnull String fieldName);

    boolean readBoolean(@Nonnull String fieldName, boolean defaultValue);

    char readChar(@Nonnull String fieldName);

    char readChar(@Nonnull String fieldName, char defaultValue);

    @Nullable
    String readString(@Nonnull String fieldName);

    @Nullable
    String readString(@Nonnull String fieldName, @Nullable String defaultValue);

    @Nullable
    BigDecimal readDecimal(@Nonnull String fieldName);

    @Nullable
    BigDecimal readDecimal(@Nonnull String fieldName, BigDecimal defaultValue);

    @Nonnull
    LocalTime readTime(@Nonnull String fieldName);

    @Nonnull
    LocalTime readTime(@Nonnull String fieldName, @Nonnull LocalTime defaultValue);

    @Nonnull
    LocalDate readDate(@Nonnull String fieldName);

    @Nonnull
    LocalDate readDate(@Nonnull String fieldName, @Nonnull LocalDate defaultValue);

    @Nonnull
    LocalDateTime readTimestamp(@Nonnull String fieldName);

    @Nonnull
    LocalDateTime readTimestamp(@Nonnull String fieldName, @Nonnull LocalDateTime defaultValue);

    @Nonnull
    OffsetDateTime readTimestampWithTimezone(@Nonnull String fieldName);

    @Nonnull
    OffsetDateTime readTimestampWithTimezone(@Nonnull String fieldName, @Nonnull OffsetDateTime defaultValue);

    /**
     * @throws com.hazelcast.core.HazelcastException If the object is not able to be created
     */
    @Nullable
    <T> T readObject(@Nonnull String fieldName);

    /**
     * @throws com.hazelcast.core.HazelcastException If the object is not able to be created
     */
    @Nullable
    <T> T readObject(@Nonnull String fieldName, @Nullable T defaultValue);

    @Nullable
    byte[] readByteArray(@Nonnull String fieldName);

    @Nullable
    byte[] readByteArray(@Nonnull String fieldName, @Nullable byte[] defaultValue);

    @Nullable
    boolean[] readBooleanArray(@Nonnull String fieldName);

    @Nullable
    boolean[] readBooleanArray(@Nonnull String fieldName, @Nullable boolean[] defaultValue);

    @Nullable
    char[] readCharArray(@Nonnull String fieldName);

    @Nullable
    char[] readCharArray(@Nonnull String fieldName, @Nullable char[] defaultValue);

    @Nullable
    int[] readIntArray(@Nonnull String fieldName);

    @Nullable
    int[] readIntArray(@Nonnull String fieldName, @Nullable int[] defaultValue);

    @Nullable
    long[] readLongArray(@Nonnull String fieldName);

    @Nullable
    long[] readLongArray(@Nonnull String fieldName, @Nullable long[] defaultValue);

    @Nullable
    double[] readDoubleArray(@Nonnull String fieldName);

    @Nullable
    double[] readDoubleArray(@Nonnull String fieldName, @Nullable double[] defaultValue);

    @Nullable
    float[] readFloatArray(@Nonnull String fieldName);

    @Nullable
    float[] readFloatArray(@Nonnull String fieldName, @Nullable float[] defaultValue);

    @Nullable
    short[] readShortArray(@Nonnull String fieldName);

    @Nullable
    short[] readShortArray(@Nonnull String fieldName, @Nullable short[] defaultValue);

    @Nullable
    String[] readStringArray(@Nonnull String fieldName);

    @Nullable
    String[] readStringArray(@Nonnull String fieldName, @Nullable String[] defaultValue);

    @Nullable
    BigDecimal[] readDecimalArray(@Nonnull String fieldName);

    @Nullable
    BigDecimal[] readDecimalArray(@Nonnull String fieldName, @Nullable BigDecimal[] defaultValue);

    /**
     * Array items can not be null
     */
    @Nullable
    LocalTime[] readTimeArray(@Nonnull String fieldName);

    /**
     * Array items can not be null
     */
    @Nullable
    LocalTime[] readTimeArray(@Nonnull String fieldName, @Nullable LocalTime[] defaultValue);

    /**
     * Array items can not be null
     */
    @Nullable
    LocalDate[] readDateArray(@Nonnull String fieldName);

    /**
     * Array items can not be null
     */
    @Nullable
    LocalDate[] readDateArray(@Nonnull String fieldName, @Nullable LocalDate[] defaultValue);

    /**
     * Array items can not be null
     */
    @Nullable
    LocalDateTime[] readTimestampArray(@Nonnull String fieldName);

    /**
     * Array items can not be null
     */
    @Nullable
    LocalDateTime[] readTimestampArray(@Nonnull String fieldName, @Nullable LocalDateTime[] defaultValue);

    /**
     * Array items can not be null
     */
    @Nullable
    OffsetDateTime[] readTimestampWithTimezoneArray(@Nonnull String fieldName);

    /**
     * Array items can not be null
     */
    @Nullable
    OffsetDateTime[] readTimestampWithTimezoneArray(@Nonnull String fieldName, @Nullable OffsetDateTime[] defaultValue);

    @Nullable
    <T> T[] readObjectArray(@Nonnull String fieldName, @Nullable Class<T> componentType);

    @Nullable
    <T> T[] readObjectArray(@Nonnull String fieldName, @Nullable Class<T> componentType, T[] defaultValue);

}
