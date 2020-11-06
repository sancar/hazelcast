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

import com.hazelcast.nio.serialization.compact.FieldDefinition;
import com.hazelcast.nio.serialization.compact.Schema;

import java.util.Collection;
import java.util.Map;

public class SchemaImpl implements Schema {

    private final Map<String, FieldDefinition> fieldDefinitionMap;
    private final long id;
    private final byte[] serialized;
    private final int numberOfComplexFields;
    private final int primitiveOffsetEnd;
    private final String className;

    public SchemaImpl(String className, Map<String, FieldDefinition> fieldDefinitionMap, long id, byte[] serialized,
                      int numberOfComplexFields, int primitiveOffsetEnd) {
        this.className = className;
        this.fieldDefinitionMap = fieldDefinitionMap;
        this.id = id;
        this.serialized = serialized;
        this.numberOfComplexFields = numberOfComplexFields;
        this.primitiveOffsetEnd = primitiveOffsetEnd;
    }

    @Override
    public Collection<FieldDefinition> getFields() {
        return fieldDefinitionMap.values();
    }

    public int getNumberOfComplexFields() {
        return numberOfComplexFields;
    }

    public int getPrimitiveOffsetEnd() {
        return primitiveOffsetEnd;
    }

    public long getId() {
        return id;
    }

    public byte[] getSerialized() {
        return serialized;
    }

    @Override
    public int getFieldCount() {
        return fieldDefinitionMap.size();
    }

    @Override
    public FieldDefinition getField(String fieldName) {
        return fieldDefinitionMap.get(fieldName);
    }

    @Override
    public boolean hasField(String fieldName) {
        return fieldDefinitionMap.containsKey(fieldName);
    }

    @Override
    public String toString() {
        return "SchemaImpl{" +
                "map=" + fieldDefinitionMap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SchemaImpl schema = (SchemaImpl) o;

        return id == schema.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
