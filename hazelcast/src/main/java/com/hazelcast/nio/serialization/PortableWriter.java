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

package com.hazelcast.nio.serialization;

import com.hazelcast.nio.ObjectDataOutput;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;

/**
 * Provides means for writing portable fields to binary data in the form of java primitives,
 * arrays of java primitives, nested portable fields and arrays of portable fields.
 */
public interface PortableWriter {

    /**
     * Writes a primitive int.
     *
     * @param fieldName name of the field
     * @param value     int value to be written
     * @throws IOException in case of any exceptional case
     */
    void writeInt(String fieldName, int value) throws IOException;

    /**
     * Writes a primitive long.
     *
     * @param fieldName name of the field
     * @param value     long value to be written
     * @throws IOException in case of any exceptional case
     */
    void writeLong(String fieldName, long value) throws IOException;

    /**
     * Writes an UTF string.
     *
     * @param fieldName name of the field
     * @param value     utf string value to be written
     * @throws IOException in case of any exceptional case
     */
    void writeUTF(String fieldName, String value) throws IOException;

    /**
     * Writes a primitive boolean.
     *
     * @param fieldName name of the field
     * @param value     int value to be written
     * @throws IOException in case of any exceptional case
     */
    void writeBoolean(String fieldName, boolean value) throws IOException;

    /**
     * Writes a primitive byte.
     *
     * @param fieldName name of the field
     * @param value     int value to be written
     * @throws IOException in case of any exceptional case
     */
    void writeByte(String fieldName, byte value) throws IOException;

    /**
     * Writes a primitive char.
     *
     * @param fieldName name of the field
     * @param value     int value to be written
     * @throws IOException in case of any exceptional case
     */
    void writeChar(String fieldName, int value) throws IOException;

    /**
     * Writes a primitive double.
     *
     * @param fieldName name of the field
     * @param value     int value to be written
     * @throws IOException in case of any exceptional case
     */
    void writeDouble(String fieldName, double value) throws IOException;

    /**
     * Writes a primitive float.
     *
     * @param fieldName name of the field
     * @param value     int value to be written
     * @throws IOException in case of any exceptional case
     */
    void writeFloat(String fieldName, float value) throws IOException;

    /**
     * Writes a primitive short.
     *
     * @param fieldName name of the field
     * @param value     int value to be written
     * @throws IOException in case of any exceptional case
     */
    void writeShort(String fieldName, short value) throws IOException;

    /**
     * Writes a Portable.
     *
     * @param fieldName name of the field
     * @param portable  Portable to be written
     * @throws IOException in case of any exceptional case
     */
    void writePortable(String fieldName, Portable portable) throws IOException;

    /**
     * To write a null portable value, user needs to provide class and factoryIds of related class.
     *
     * @param fieldName name of the field
     * @param factoryId factory ID of related portable class
     * @param classId   class ID of related portable class
     * @throws IOException in case of any exceptional case
     */
    void writeNullPortable(String fieldName, int factoryId, int classId) throws IOException;

    /**
     * Writes a BigInteger.
     *
     * @param fieldName name of the field
     * @param value     BigInteger value to be written
     * @throws IOException in case of any exceptional case
     */
    void writeBigInteger(String fieldName, BigInteger value) throws IOException;

    /**
     * Writes a BigDecimal.
     *
     * @param fieldName name of the field
     * @param value     BigDecimal value to be written
     * @throws IOException in case of any exceptional case
     */
    void writeBigDecimal(String fieldName, BigDecimal value) throws IOException;

    /**
     * Writes a LocalTime.
     *
     * @param fieldName name of the field
     * @param value     LocalTime value to be written
     * @throws IOException in case of any exceptional case
     */
    void writeLocalTime(String fieldName, LocalTime value) throws IOException;

    /**
     * Writes a LocalDate.
     *
     * @param fieldName name of the field
     * @param value     LocalDate value to be written
     * @throws IOException in case of any exceptional case
     */
    void writeLocalDate(String fieldName, LocalDate value) throws IOException;

    /**
     * Writes a LocalDateTime.
     *
     * @param fieldName name of the field
     * @param value     LocalDateTime value to be written
     * @throws IOException in case of any exceptional case
     */
    void writeLocalDateTime(String fieldName, LocalDateTime value) throws IOException;

    /**
     * Writes a OffsetDateTime.
     *
     * @param fieldName name of the field
     * @param value     OffsetDateTime value to be written
     * @throws IOException in case of any exceptional case
     */
    void writeOffsetDateTime(String fieldName, OffsetDateTime value) throws IOException;

    /**
     * Writes a primitive byte-array.
     *
     * @param fieldName name of the field
     * @param bytes     byte array to be written
     * @throws IOException in case of any exceptional case
     */
    void writeByteArray(String fieldName, byte[] bytes) throws IOException;

    /**
     * Writes a primitive boolean-array.
     *
     * @param fieldName name of the field
     * @param booleans  boolean array to be written
     * @throws IOException in case of any exceptional case
     */
    void writeBooleanArray(String fieldName, boolean[] booleans) throws IOException;

    /**
     * Writes a primitive char-array.
     *
     * @param fieldName name of the field
     * @param chars     char array to be written
     * @throws IOException in case of any exceptional case
     */
    void writeCharArray(String fieldName, char[] chars) throws IOException;

    /**
     * Writes a primitive int-array.
     *
     * @param fieldName name of the field
     * @param ints      int array to be written
     * @throws IOException in case of any exceptional case
     */
    void writeIntArray(String fieldName, int[] ints) throws IOException;

    /**
     * Writes a primitive long-array.
     *
     * @param fieldName name of the field
     * @param longs     long array to be written
     * @throws IOException in case of any exceptional case
     */
    void writeLongArray(String fieldName, long[] longs) throws IOException;

    /**
     * Writes a primitive double array.
     *
     * @param fieldName name of the field
     * @param values    double array to be written
     * @throws IOException in case of any exceptional case
     */
    void writeDoubleArray(String fieldName, double[] values) throws IOException;

    /**
     * Writes a primitive float array.
     *
     * @param fieldName name of the field
     * @param values    float array to be written
     * @throws IOException in case of any exceptional case
     */
    void writeFloatArray(String fieldName, float[] values) throws IOException;

    /**
     * Writes a primitive short-array.
     *
     * @param fieldName name of the field
     * @param values    short array to be written
     * @throws IOException in case of any exceptional case
     */
    void writeShortArray(String fieldName, short[] values) throws IOException;

    /**
     * Writes a String-array.
     *
     * @param fieldName name of the field
     * @param values    String array to be written
     * @throws IOException in case of any exceptional case
     */
    void writeUTFArray(String fieldName, String[] values) throws IOException;

    /**
     * Writes a an array of Portables.
     *
     * @param fieldName name of the field
     * @param values    portable array to be written
     * @throws IOException in case of any exceptional case
     */
    void writePortableArray(String fieldName, Portable[] values) throws IOException;

    /**
     * Writes a BigInteger array.
     *
     * @param fieldName name of the field
     * @param values    BigInteger array to be written
     * @throws IOException in case of any exceptional case
     */
    void writeBigIntegerArray(String fieldName, BigInteger[] values) throws IOException;

    /**
     * Writes a BigDecimal array.
     *
     * @param fieldName name of the field
     * @param values    BigDecimal array to be written
     * @throws IOException in case of any exceptional case
     */
    void writeBigDecimalArray(String fieldName, BigDecimal[] values) throws IOException;

    /**
     * Writes a LocalTime array.
     *
     * @param fieldName name of the field
     * @param values    LocalTime array to be written
     * @throws IOException in case of any exceptional case
     */
    void writeLocalTimeArray(String fieldName, LocalTime[] values) throws IOException;

    /**
     * Writes a LocalDate array.
     *
     * @param fieldName name of the field
     * @param values    LocalDate array to be written
     * @throws IOException in case of any exceptional case
     */
    void writeLocalDateArray(String fieldName, LocalDate[] values) throws IOException;

    /**
     * Writes a LocalDateTime array.
     *
     * @param fieldName name of the field
     * @param values    LocalDateTime array to be written
     * @throws IOException in case of any exceptional case
     */
    void writeLocalDateTimeArray(String fieldName, LocalDateTime[] values) throws IOException;

    /**
     * Writes a OffsetDateTime array.
     *
     * @param fieldName name of the field
     * @param values    OffsetDateTime array to be written
     * @throws IOException in case of any exceptional case
     */
    void writeOffsetDateTimeArray(String fieldName, OffsetDateTime[] values) throws IOException;

    /**
     * After writing portable fields one can subsequently write remaining fields in the old-fashioned way.
     * Users should note that after calling this method, trying to write portable fields will result
     * in an IOException.
     *
     * @return ObjectDataOutput
     * @throws IOException in case of any exceptional case
     */
    ObjectDataOutput getRawDataOutput() throws IOException;

}
