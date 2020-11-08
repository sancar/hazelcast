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

package com.hazelcast.internal.serialization.impl.compact;

import com.hazelcast.nio.serialization.FieldType;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.HazelcastSerializationException;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * This builder is build by user thread via
 * {@link GenericRecord.Builder#compact(String)} method.
 * <p>
 * It is only job to carry the objects including their types and the class name.
 */
public class DeserializedGenericRecordBuilder extends AbstractGenericRecordBuilder {

    private final Map<String, Object> objects = new HashMap<>();
    private final SchemaBuilderImpl schemaBuilder;

    public DeserializedGenericRecordBuilder(String className) {
        schemaBuilder = new SchemaBuilderImpl(className);
    }

    /**
     * @return newly created GenericRecord
     * TODO sancar fix this javadoc
     * @throws HazelcastSerializationException if a field is not written when building with builder from
     *                                         {@link GenericRecord.Builder#compact(String)} (ClassDefinition)} and
     *                                         {@link GenericRecord#newBuilder()}
     */
    @Nonnull
    @Override
    public GenericRecord build() {
        return new DeserializedGenericRecord(schemaBuilder.build(), objects);
    }


    protected GenericRecord.Builder write(String fieldName, Object value, FieldType fieldType) {
        if (objects.putIfAbsent(fieldName, value) != null) {
            throw new HazelcastSerializationException("Field can only be written once");
        }
        schemaBuilder.addField(new FieldDescriptorImpl(fieldName, fieldType));
        return this;
    }


}
