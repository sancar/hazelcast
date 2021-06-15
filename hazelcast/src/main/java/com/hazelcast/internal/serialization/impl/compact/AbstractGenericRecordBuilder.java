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
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.GenericRecordBuilder;
import com.hazelcast.nio.serialization.HazelcastSerializationException;
import com.hazelcast.nio.serialization.InternalFieldTypeIDS;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;

abstract class AbstractGenericRecordBuilder implements GenericRecordBuilder {

    @Nonnull
    @Override
    public GenericRecordBuilder setInt(@Nonnull String fieldName, int value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setLong(@Nonnull String fieldName, long value) {
        return write(fieldName, value, InternalFieldTypeIDS.LONG, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setString(@Nonnull String fieldName, String value) {
        return write(fieldName, value, InternalFieldTypeIDS.UTF, false);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setBoolean(@Nonnull String fieldName, boolean value) {
        return write(fieldName, value, InternalFieldTypeIDS.BOOLEAN, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setByte(@Nonnull String fieldName, byte value) {
        return write(fieldName, value, InternalFieldTypeIDS.BYTE, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setChar(@Nonnull String fieldName, char value) {
        return write(fieldName, value, InternalFieldTypeIDS.CHAR, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setDouble(@Nonnull String fieldName, double value) {
        return write(fieldName, value, InternalFieldTypeIDS.DOUBLE, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setFloat(@Nonnull String fieldName, float value) {
        return write(fieldName, value, InternalFieldTypeIDS.FLOAT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setShort(@Nonnull String fieldName, short value) {
        return write(fieldName, value, InternalFieldTypeIDS.SHORT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setGenericRecord(@Nonnull String fieldName, @Nullable GenericRecord value) {
        //TODO sancar what if value is null
        if (((CompactGenericRecord) value).getSchema().getNumberOfVariableLengthFields() == 0) {
            return write(fieldName, value, InternalFieldTypeIDS.FIXED_SIZE, true);
        }
        return write(fieldName, value, InternalFieldTypeIDS.VAR_SIZE, false);
    }

    //TODO sancar fix the rest of the methods

    @Nonnull
    @Override
    public GenericRecordBuilder setDecimal(@Nonnull String fieldName, @Nullable BigDecimal value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setTime(@Nonnull String fieldName, @Nullable LocalTime value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setDate(@Nonnull String fieldName, @Nullable LocalDate value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setTimestamp(@Nonnull String fieldName, @Nullable LocalDateTime value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setTimestampWithTimezone(@Nonnull String fieldName, @Nullable OffsetDateTime value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setGenericRecordArray(@Nonnull String fieldName, @Nullable GenericRecord[] value) {
        //TODO sancar present what if value is null or empty
        //We should onot put class to the parameters as solution, because the purpose of the
        //GenericRecord API is not to rely on classes being available on the remote.
        //Think of EntryProcessor situation.
        //TODO should it be always VAR_SIZE on this API.
        //TODO should it be always VAR_SIZE on programmatic. And FIXED_SIZE only when generated from schema yaml.

        Schema schema = ((CompactGenericRecord) value[0]).getSchema();
        boolean isFixedSize = true;
        if (schema.getNumberOfVariableLengthFields() == 0) {
            //first item was fixed but,
            // now check if every field has the exact same schema. If not,
            // this field is not an array of fixed size items
            for (GenericRecord genericRecord : value) {
                if (!((CompactGenericRecord) genericRecord).getSchema().equals(schema)) {
                    isFixedSize = false;
                }
            }

        }
        if(isFixedSize) {
            return write(fieldName, value, InternalFieldTypeIDS.FIXED_SIZE_ARRAY, false);
        }
        return write(fieldName, value, InternalFieldTypeIDS.VAR_SIZE_ARRAY, false);

    }

    @Nonnull
    @Override
    public GenericRecordBuilder setByteArray(@Nonnull String fieldName, byte[] value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setBooleanArray(@Nonnull String fieldName, boolean[] value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setCharArray(@Nonnull String fieldName, char[] value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setIntArray(@Nonnull String fieldName, int[] value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setLongArray(@Nonnull String fieldName, long[] value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setDoubleArray(@Nonnull String fieldName, double[] value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setFloatArray(@Nonnull String fieldName, float[] value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setShortArray(@Nonnull String fieldName, short[] value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setStringArray(@Nonnull String fieldName, String[] value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setDecimalArray(@Nonnull String fieldName, BigDecimal[] value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setTimeArray(@Nonnull String fieldName, LocalTime[] value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setDateArray(@Nonnull String fieldName, LocalDate[] value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setTimestampArray(@Nonnull String fieldName, LocalDateTime[] value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    @Nonnull
    @Override
    public GenericRecordBuilder setTimestampWithTimezoneArray(@Nonnull String fieldName, OffsetDateTime[] value) {
        return write(fieldName, value, InternalFieldTypeIDS.INT, true);
    }

    protected abstract GenericRecordBuilder write(@Nonnull String fieldName, Object value, int internalFieldTypeId, boolean isFixedSize);

    public static void checkTypeWithSchema(Schema schema, @Nonnull String fieldName, int internalFieldTypeId) {
        FieldDescriptor fd = schema.getField(fieldName);
        if (fd == null) {
            throw new HazelcastSerializationException("Invalid field name: '" + fieldName + "' for " + schema);
        }
        if (fd.getInternalFieldTypeId() != internalFieldTypeId) {
            throw new HazelcastSerializationException("Invalid field type: '" + fieldName
                    + "' for " + schema + ", expected : " + fd.getType() + ", given : " + internalFieldTypeId);
        }
    }

}
