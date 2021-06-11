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

package com.hazelcast.jet.sql.impl.inject;

import com.hazelcast.internal.serialization.impl.compact.Schema;
import com.hazelcast.nio.serialization.FieldType;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.GenericRecordBuilder;
import com.hazelcast.sql.impl.QueryException;
import com.hazelcast.sql.impl.type.QueryDataType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.hazelcast.jet.sql.impl.inject.UpsertInjector.FAILING_TOP_LEVEL_INJECTOR;

@NotThreadSafe
class CompactUpsertTarget implements UpsertTarget {

    private final Schema schema;

    private final Map<String, Object> values;

    CompactUpsertTarget(@Nonnull Schema schema) {
        this.schema = schema;

        this.values = new HashMap<String, Object>();
    }

    @Override
    public UpsertInjector createInjector(@Nullable String path, QueryDataType type) {
        if (path == null) {
            return FAILING_TOP_LEVEL_INJECTOR;
        }

        boolean hasField = schema.hasField(path);
        return value -> {
            if (!hasField && value != null) {
                throw QueryException.error("Unable to inject a non-null value to \"" + path + "\"");
            }

            if (hasField) {
                values.put(path, value);
            }
        };
    }

    @Override
    public void init() {
        values.clear();
    }

    @Override
    public Object conclude() {
        GenericRecord record = toRecord(schema, values);
        values.clear();
        return record;
    }

    private static GenericRecord toRecord(Schema schema, Map<String, Object> values) {
        GenericRecordBuilder builder = GenericRecordBuilder.compact(schema.getTypeName());
        for (String name : schema.getFieldNames()) {
            FieldType type = schema.getField(name).getType();

            Object value = values.get(name);
            try {
                switch (type) {
                    case BOOLEAN:
                        builder.setBoolean(name, value != null && (boolean) value);
                        break;
                    case BYTE:
                        builder.setByte(name, value == null ? (byte) 0 : (byte) value);
                        break;
                    case SHORT:
                        builder.setShort(name, value == null ? (short) 0 : (short) value);
                        break;
                    case CHAR:
                        builder.setChar(name, value == null ? (char) 0 : (char) value);
                        break;
                    case INT:
                        builder.setInt(name, value == null ? 0 : (int) value);
                        break;
                    case LONG:
                        builder.setLong(name, value == null ? 0L : (long) value);
                        break;
                    case FLOAT:
                        builder.setFloat(name, value == null ? 0F : (float) value);
                        break;
                    case DOUBLE:
                        builder.setDouble(name, value == null ? 0D : (double) value);
                        break;
                    case DECIMAL:
                        builder.setDecimal(name, (BigDecimal) value);
                        break;
                    case UTF:
                        builder.setString(name, (String) QueryDataType.VARCHAR.convert(value));
                        break;
                    case TIME:
                        builder.setTime(name, (LocalTime) value);
                        break;
                    case DATE:
                        builder.setDate(name, (LocalDate) value);
                        break;
                    case TIMESTAMP:
                        builder.setTimestamp(name, (LocalDateTime) value);
                        break;
                    case TIMESTAMP_WITH_TIMEZONE:
                        builder.setTimestampWithTimezone(name, (OffsetDateTime) value);
                        break;
                    case COMPOSED:
                        //TODO sancar check if there is a cast exception here
                        builder.setGenericRecord(name, (GenericRecord) value);
                        break;
                    case BOOLEAN_ARRAY:
                        builder.setBooleanArray(name, (boolean[]) value);
                        break;
                    case BYTE_ARRAY:
                        builder.setByteArray(name, (byte[]) value);
                        break;
                    case SHORT_ARRAY:
                        builder.setShortArray(name, (short[]) value);
                        break;
                    case CHAR_ARRAY:
                        builder.setCharArray(name, (char[]) value);
                        break;
                    case INT_ARRAY:
                        builder.setIntArray(name, (int[]) value);
                        break;
                    case LONG_ARRAY:
                        builder.setLongArray(name, (long[]) value);
                        break;
                    case FLOAT_ARRAY:
                        builder.setFloatArray(name, (float[]) value);
                        break;
                    case DOUBLE_ARRAY:
                        builder.setDoubleArray(name, (double[]) value);
                        break;
                    case DECIMAL_ARRAY:
                        builder.setDecimalArray(name, (BigDecimal[]) value);
                        break;
                    case UTF_ARRAY:
                        builder.setStringArray(name, (String[]) value);
                        break;
                    case TIME_ARRAY:
                        builder.setTimeArray(name, (LocalTime[]) value);
                        break;
                    case DATE_ARRAY:
                        builder.setDateArray(name, (LocalDate[]) value);
                        break;
                    case TIMESTAMP_ARRAY:
                        builder.setTimestampArray(name, (LocalDateTime[]) value);
                        break;
                    case TIMESTAMP_WITH_TIMEZONE_ARRAY:
                        builder.setTimestampWithTimezoneArray(name, (OffsetDateTime[]) value);
                        break;
                    case COMPOSED_ARRAY:
                        //TODO sancar check if there is a cast exception here
                        builder.setGenericRecordArray(name, (GenericRecord[]) value);
                        break;
                    default:
                        //TODO sancar add missing types
                        throw QueryException.error("Unsupported type: " + type);
                }
            } catch (Exception e) {
                throw QueryException.error("Cannot set value " +
                        (value == null ? "null" : " of type " + value.getClass().getName())
                        + " to field \"" + name + "\" of type " + type + ": " + e.getMessage(), e);
            }
        }
        return builder.build();
    }
}
