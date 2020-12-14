package com.hazelcast.internal.serialization.impl.compact;

import com.hazelcast.nio.serialization.FieldType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.hazelcast.nio.serialization.FieldType.BIG_DECIMAL;
import static com.hazelcast.nio.serialization.FieldType.BIG_DECIMAL_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.BIG_INTEGER;
import static com.hazelcast.nio.serialization.FieldType.BIG_INTEGER_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.BOOLEAN;
import static com.hazelcast.nio.serialization.FieldType.BOOLEAN_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.BYTE;
import static com.hazelcast.nio.serialization.FieldType.BYTE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.CHAR;
import static com.hazelcast.nio.serialization.FieldType.CHAR_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.DOUBLE;
import static com.hazelcast.nio.serialization.FieldType.DOUBLE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.FLOAT;
import static com.hazelcast.nio.serialization.FieldType.FLOAT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.INT;
import static com.hazelcast.nio.serialization.FieldType.INT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.LOCAL_DATE;
import static com.hazelcast.nio.serialization.FieldType.LOCAL_DATE_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.LOCAL_DATE_TIME;
import static com.hazelcast.nio.serialization.FieldType.LOCAL_DATE_TIME_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.LOCAL_TIME;
import static com.hazelcast.nio.serialization.FieldType.LOCAL_TIME_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.LONG;
import static com.hazelcast.nio.serialization.FieldType.LONG_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.OFFSET_DATE_TIME;
import static com.hazelcast.nio.serialization.FieldType.OFFSET_DATE_TIME_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.SHORT;
import static com.hazelcast.nio.serialization.FieldType.SHORT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.UTF;
import static com.hazelcast.nio.serialization.FieldType.UTF_ARRAY;

public class TypeUtil {

    private static final Map<Class, FieldType> classToFieldType = new HashMap<>();

    static {
        classToFieldType.put(Byte.class, BYTE);
        classToFieldType.put(Byte.TYPE, BYTE);
        classToFieldType.put(Boolean.class, BOOLEAN);
        classToFieldType.put(Boolean.TYPE, BOOLEAN);
        classToFieldType.put(Character.class, CHAR);
        classToFieldType.put(Character.TYPE, CHAR);
        classToFieldType.put(Short.class, SHORT);
        classToFieldType.put(Short.TYPE, SHORT);
        classToFieldType.put(Integer.class, INT);
        classToFieldType.put(Integer.TYPE, INT);
        classToFieldType.put(Long.class, LONG);
        classToFieldType.put(Long.TYPE, LONG);
        classToFieldType.put(Float.class, FLOAT);
        classToFieldType.put(Float.TYPE, FLOAT);
        classToFieldType.put(Double.class, DOUBLE);
        classToFieldType.put(Double.TYPE, DOUBLE);
        classToFieldType.put(String.class, UTF);
        classToFieldType.put(BigInteger.class, BIG_INTEGER);
        classToFieldType.put(BigDecimal.class, BIG_DECIMAL);
        classToFieldType.put(LocalTime.class, LOCAL_TIME);
        classToFieldType.put(LocalDate.class, LOCAL_DATE);
        classToFieldType.put(LocalDateTime.class, LOCAL_DATE_TIME);
        classToFieldType.put(OffsetDateTime.class, OFFSET_DATE_TIME);
    }

    private static final Map<Class, FieldType> classToArrayFieldType = new HashMap<>();

    static {
        classToArrayFieldType.put(Byte.class, BYTE_ARRAY);
        classToArrayFieldType.put(Byte.TYPE, BYTE_ARRAY);
        classToArrayFieldType.put(Boolean.class, BOOLEAN_ARRAY);
        classToArrayFieldType.put(Boolean.TYPE, BOOLEAN_ARRAY);
        classToArrayFieldType.put(Character.class, CHAR_ARRAY);
        classToArrayFieldType.put(Character.TYPE, CHAR_ARRAY);
        classToArrayFieldType.put(Short.class, SHORT_ARRAY);
        classToArrayFieldType.put(Short.TYPE, SHORT_ARRAY);
        classToArrayFieldType.put(Integer.class, INT_ARRAY);
        classToArrayFieldType.put(Integer.TYPE, INT_ARRAY);
        classToArrayFieldType.put(Long.class, LONG_ARRAY);
        classToArrayFieldType.put(Long.TYPE, LONG_ARRAY);
        classToArrayFieldType.put(Float.class, FLOAT_ARRAY);
        classToArrayFieldType.put(Float.TYPE, FLOAT_ARRAY);
        classToArrayFieldType.put(Double.class, DOUBLE_ARRAY);
        classToArrayFieldType.put(Double.TYPE, DOUBLE_ARRAY);
        classToArrayFieldType.put(String.class, UTF_ARRAY);
        classToArrayFieldType.put(BigInteger.class, BIG_INTEGER_ARRAY);
        classToArrayFieldType.put(BigDecimal.class, BIG_DECIMAL_ARRAY);
        classToArrayFieldType.put(LocalTime.class, LOCAL_TIME_ARRAY);
        classToArrayFieldType.put(LocalDate.class, LOCAL_DATE_ARRAY);
        classToArrayFieldType.put(LocalDateTime.class, LOCAL_DATE_TIME_ARRAY);
        classToArrayFieldType.put(OffsetDateTime.class, OFFSET_DATE_TIME_ARRAY);
    }

    static FieldType getFieldType(Class aClass) {
        return classToFieldType.get(aClass);
    }

    static FieldType getArrayFieldType(Class aClass) {
        return classToArrayFieldType.get(aClass);
    }
}
