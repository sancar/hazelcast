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

import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.GenericRecordBuilder;
import com.hazelcast.nio.serialization.HazelcastSerializationException;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * This builder is build by user thread via
 * {@link GenericRecordBuilder#compact(String)} method.
 * <p>
 * It is only job to carry the objects including their types and the class name.
 */
public class DeserializedGenericRecordBuilder extends AbstractGenericRecordBuilder {

    private final TreeMap<String, Object> objects = new TreeMap<>();
    private final TreeMap<String, FieldDescriptor> fieldDefinitionMap = new TreeMap<>(Comparator.naturalOrder());
    private final String className;

    public DeserializedGenericRecordBuilder(String className) {
        this.className = className;
    }

    /**
     * @return newly created GenericRecord
     * @throws HazelcastSerializationException if a field is not written when building with builder from
     *                                         {@link GenericRecordBuilder#compact(String)} (ClassDefinition)} and
     *                                         {@link GenericRecord#newBuilder()}
     */
    @Nonnull
    @Override
    public GenericRecord build() {
        Schema schema = new Schema(className, fieldDefinitionMap);
        return new DeserializedGenericRecord(schema, objects);
    }


    protected GenericRecordBuilder write(@Nonnull String fieldName, Object value, int internalFieldTypeId,
                                         boolean isFixedSize) {
        if (objects.putIfAbsent(fieldName, value) != null) {
            throw new HazelcastSerializationException("Field can only be written once");
        }
        fieldDefinitionMap.put(fieldName, new FieldDescriptor(fieldName, internalFieldTypeId, isFixedSize));
        return this;
    }
}
