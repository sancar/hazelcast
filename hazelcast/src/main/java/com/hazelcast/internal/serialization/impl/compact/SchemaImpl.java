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

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;

/**
 * Represents the schema of a class.
 * Consists of field definitions and the class name.
 */
public class SchemaImpl implements Schema {

    private final String className;
    private final TreeMap<String, FieldDescriptor> fieldDefinitionMap;
    private final long schemaId;
    private final byte[] serialized;
    private final int numberOfComplexFields;
    private final int primitivesLength;

    public SchemaImpl(String className, TreeMap<String, FieldDescriptor> fieldDefinitionMap, long schemaId, byte[] serialized,
                      int numberOfComplexFields, int primitivesLength) {
        this.className = className;
        this.fieldDefinitionMap = fieldDefinitionMap;
        this.schemaId = schemaId;
        this.serialized = serialized;
        this.numberOfComplexFields = numberOfComplexFields;
        this.primitivesLength = primitivesLength;
    }

    /**
     * The class name provided when building a schema
     * In java, when it is not configured explicitly, this falls back to full class name including the path.
     *
     * @return name of the class
     */
    public String getClassName() {
        return className;
    }

    @Override
    public Collection<FieldDescriptor> getFields() {
        return fieldDefinitionMap.values();
    }

    public Set<String> getFieldNames() {
        return fieldDefinitionMap.keySet();
    }

    public int getNumberOfVariableLengthFields() {
        return numberOfComplexFields;
    }

    public int getPrimitivesLength() {
        return primitivesLength;
    }

    public long getSchemaId() {
        return schemaId;
    }

    public byte[] getSerializedSchema() {
        return serialized;
    }

    @Override
    public int getFieldCount() {
        return fieldDefinitionMap.size();
    }

    @Override
    public FieldDescriptor getField(String fieldName) {
        return fieldDefinitionMap.get(fieldName);
    }

    @Override
    public boolean hasField(String fieldName) {
        return fieldDefinitionMap.containsKey(fieldName);
    }

    @Override
    public String toString() {
        return "Schema {" +
                " className = " + className +
                ", map = " + fieldDefinitionMap +
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

        return schemaId == schema.schemaId;
    }

    @Override
    public int hashCode() {
        return (int) (schemaId);
    }
}
