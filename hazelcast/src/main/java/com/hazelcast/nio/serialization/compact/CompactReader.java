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
import java.util.ArrayList;

/**
 * All read methods throws HazelcastSerializationException when related field is not found or there is a type mismatch
 *
 */
public interface CompactReader {

    byte readByte(String fieldName);

    short readShort(String fieldName);

    int readInt(String fieldName);

    long readLong(String fieldName);

    float readFloat(String fieldName);

    double readDouble(String fieldName);

    boolean readBoolean(String fieldName);

    char readChar(String fieldName);

    String readUTF(String fieldName);

    BigInteger readBigInteger(String fieldName);

    BigDecimal readBigDecimal(String fieldName);

    LocalTime readLocalTime(String fieldName);

    LocalDate readLocalDate(String fieldName);

    LocalDateTime readLocalDateTime(String fieldName);

    OffsetDateTime readOffsetDateTime(String fieldName);

    /**
     * @param <T> must be registered via{@link com.hazelcast.nio.serialization.compact.Compact#register}
     * @throws com.hazelcast.core.HazelcastException If the object is not able to be created because the related class not be
     *                                               found in the classpath
     */
    <T> T readObject(String fieldName);

    byte[] readByteArray(String fieldName);

    boolean[] readBooleanArray(String fieldName);

    char[] readCharArray(String fieldName);

    int[] readIntArray(String fieldName);

    long[] readLongArray(String fieldName);

    double[] readDoubleArray(String fieldName);

    float[] readFloatArray(String fieldName);

    short[] readShortArray(String fieldName);

    String[] readUTFArray(String fieldName);

    BigInteger[] readBigIntegerArray(String fieldName);

    BigDecimal[] readBigDecimalArray(String fieldName);

    LocalTime[] readLocalTimeArray(String fieldName);

    LocalDate[] readLocalDateArray(String fieldName);

    LocalDateTime[] readLocalDateTimeArray(String fieldName);

    OffsetDateTime[] readOffsetDateTimeArray(String fieldName);

    /**
     * @return class type of items must be registered via{@link com.hazelcast.nio.serialization.compact.Compact#register}
     * @throws com.hazelcast.core.HazelcastException If the object is not able to be created because the related class not be
     *                                               found in the classpath
     */
    Object[] readObjectArray(String fieldName);

    /**
     * @param <T> must be registered via{@link com.hazelcast.nio.serialization.compact.Compact#register}
     * @throws com.hazelcast.core.HazelcastException If the object is not able to be created because the related class not be
     *                                               found in the classpath
     */
    <T> ArrayList<T> readObjectArrayList(String fieldName);

}
