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


import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.function.Function;

/**
 * All read(String fieldName) methods throw HazelcastSerializationException when the related field is not found or there is a type mismatch.
 * To avoid exception when the field is not found, the user can make use of `read(String fieldName, T defaultValue)`.
 * Especially useful, when class is evolved  (a new field is added to/removed from the class).
 */
public interface CompactReader {

    byte readByte(String fieldName);

    byte readByte(String fieldName, byte defaultValue);

    short readShort(String fieldName);

    short readShort(String fieldName, short defaultValue);

    int readInt(String fieldName);

    int readInt(String fieldName, int defaultValue);

    long readLong(String fieldName);

    long readLong(String fieldName, long defaultValue);

    float readFloat(String fieldName);

    float readFloat(String fieldName, float defaultValue);

    double readDouble(String fieldName);

    double readDouble(String fieldName, double defaultValue);

    boolean readBoolean(String fieldName);

    boolean readBoolean(String fieldName, boolean defaultValue);

    char readChar(String fieldName);

    char readChar(String fieldName, char defaultValue);

    String readUTF(String fieldName);

    String readUTF(String fieldName, String defaultValue);

    BigInteger readBigInteger(String fieldName);

    BigInteger readBigInteger(String fieldName, BigInteger defaultValue);

    BigDecimal readBigDecimal(String fieldName);

    BigDecimal readBigDecimal(String fieldName, BigDecimal defaultValue);

    LocalTime readLocalTime(String fieldName);

    LocalTime readLocalTime(String fieldName, LocalTime defaultValue);

    LocalDate readLocalDate(String fieldName);

    LocalDate readLocalDate(String fieldName, LocalDate defaultValue);

    LocalDateTime readLocalDateTime(String fieldName);

    LocalDateTime readLocalDateTime(String fieldName, LocalDateTime defaultValue);

    OffsetDateTime readOffsetDateTime(String fieldName);

    OffsetDateTime readOffsetDateTime(String fieldName, OffsetDateTime defaultValue);

    byte[] readByteArray(String fieldName);

    byte[] readByteArray(String fieldName, byte[] defaultValue);

    boolean[] readBooleanArray(String fieldName);

    boolean[] readBooleanArray(String fieldName, boolean[] defaultValue);

    char[] readCharArray(String fieldName);

    char[] readCharArray(String fieldName, char[] defaultValue);

    int[] readIntArray(String fieldName);

    int[] readIntArray(String fieldName, int[] defaultValue);

    long[] readLongArray(String fieldName);

    long[] readLongArray(String fieldName, long[] defaultValue);

    double[] readDoubleArray(String fieldName);

    double[] readDoubleArray(String fieldName, double[] defaultValue);

    float[] readFloatArray(String fieldName);

    float[] readFloatArray(String fieldName, float[] defaultValue);

    short[] readShortArray(String fieldName);

    short[] readShortArray(String fieldName, short[] defaultValue);

    String[] readUTFArray(String fieldName);

    String[] readUTFArray(String fieldName, String[] defaultValue);

    BigInteger[] readBigIntegerArray(String fieldName);

    BigInteger[] readBigIntegerArray(String fieldName, BigInteger[] defaultValue);

    BigDecimal[] readBigDecimalArray(String fieldName);

    BigDecimal[] readBigDecimalArray(String fieldName, BigDecimal[] defaultValue);

    LocalTime[] readLocalTimeArray(String fieldName);

    LocalTime[] readLocalTimeArray(String fieldName, LocalTime[] defaultValue);

    LocalDate[] readLocalDateArray(String fieldName);

    LocalDate[] readLocalDateArray(String fieldName, LocalDate[] defaultValue);

    LocalDateTime[] readLocalDateTimeArray(String fieldName);

    LocalDateTime[] readLocalDateTimeArray(String fieldName, LocalDateTime[] defaultValue);

    OffsetDateTime[] readOffsetDateTimeArray(String fieldName);

    OffsetDateTime[] readOffsetDateTimeArray(String fieldName, OffsetDateTime[] defaultValue);

    /**
     * @throws com.hazelcast.core.HazelcastException If the object is not able to be created because the related class not be
     *                                               found in the classpath
     */
    <T> T readAny(String fieldName);

    <T> T readAny(String fieldName, T defaultValue);

    /**
     * @throws com.hazelcast.core.HazelcastException If the object is not able to be created because the related class not be
     *                                               found in the classpath
     */
    <T> T[] readAnyArray(String fieldName, Class<T> componentType);

    <T> T[] readAnyArray(String fieldName, Class<T> componentType, T[] defaultValue);

    /**
     * @throws com.hazelcast.core.HazelcastException If the object is not able to be created because the related class not be
     *                                               found in the classpath
     */
    <T> Collection<T> readAnyCollection(String fieldName, Function<Integer, Collection<T>> constructor);

    <T> Collection<T> readAnyCollection(String fieldName, Function<Integer, Collection<T>> constructor, Collection<T> defaultValue);

}
