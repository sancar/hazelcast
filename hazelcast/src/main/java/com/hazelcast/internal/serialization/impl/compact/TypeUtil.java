package com.hazelcast.internal.serialization.impl.compact;

import com.hazelcast.nio.serialization.FieldType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Collection;
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
import static com.hazelcast.nio.serialization.FieldType.COLLECTION;
import static com.hazelcast.nio.serialization.FieldType.COMPOSED;
import static com.hazelcast.nio.serialization.FieldType.COMPOSED_ARRAY;
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


/**
 * TODO sancar remove if unnucessary
 * Initial idea is to use it in {@link ReflectiveCompactSerializer} to avoid gigantic switch
 */
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
        classToFieldType.put(Byte[].class, BYTE_ARRAY);
        classToFieldType.put(byte[].class, BYTE_ARRAY);
        classToFieldType.put(Boolean[].class, BOOLEAN_ARRAY);
        classToFieldType.put(boolean[].class, BOOLEAN_ARRAY);
        classToFieldType.put(Character[].class, CHAR_ARRAY);
        classToFieldType.put(char[].class, CHAR_ARRAY);
        classToFieldType.put(Short[].class, SHORT_ARRAY);
        classToFieldType.put(short[].class, SHORT_ARRAY);
        classToFieldType.put(Integer[].class, INT_ARRAY);
        classToFieldType.put(int[].class, INT_ARRAY);
        classToFieldType.put(Long[].class, LONG_ARRAY);
        classToFieldType.put(long[].class, LONG_ARRAY);
        classToFieldType.put(Float[].class, FLOAT_ARRAY);
        classToFieldType.put(float[].class, FLOAT_ARRAY);
        classToFieldType.put(Double[].class, DOUBLE_ARRAY);
        classToFieldType.put(double[].class, DOUBLE_ARRAY);
        classToFieldType.put(String[].class, UTF_ARRAY);
        classToFieldType.put(BigInteger[].class, BIG_INTEGER_ARRAY);
        classToFieldType.put(BigDecimal[].class, BIG_DECIMAL_ARRAY);
        classToFieldType.put(LocalTime[].class, LOCAL_TIME_ARRAY);
        classToFieldType.put(LocalDate[].class, LOCAL_DATE_ARRAY);
        classToFieldType.put(LocalDateTime[].class, LOCAL_DATE_TIME_ARRAY);
        classToFieldType.put(OffsetDateTime[].class, OFFSET_DATE_TIME_ARRAY);
    }

    static FieldType getFieldType(Class aClass) {
        if(Collection.class.isAssignableFrom(aClass)) {
            return COLLECTION;
        }
        FieldType fieldType = classToFieldType.get(aClass);
        if(fieldType != null) {
            return fieldType;
        }
        if(aClass.isArray()) {
            return COMPOSED_ARRAY;
        }
        return COMPOSED;
    }
}
