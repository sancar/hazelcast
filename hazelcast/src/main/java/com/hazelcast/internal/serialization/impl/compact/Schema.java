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

import com.hazelcast.internal.serialization.impl.compact.schema.SchemaDataSerializerHook;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.FieldType;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Represents the schema of a class.
 * Consists of field definitions and the class name.
 */
public class Schema implements IdentifiedDataSerializable {

    private String typeName;
    private TreeMap<String, FieldDescriptor> fieldDefinitionMap;
    private int numberOfComplexFields;
    private int primitivesLength;
    private transient long schemaId;
    private boolean isSchemaIdSet;

    public Schema() {
    }

    public Schema(String typeName, TreeMap<String, FieldDescriptor> fieldDefinitionMap) {
        this.typeName = typeName;
        this.fieldDefinitionMap = fieldDefinitionMap;
        init();
    }

    private void init() {
        List<FieldDescriptor> booleanFieldsList = fieldDefinitionMap.values().stream()
                .filter(fieldDescriptor -> fieldDescriptor.getType().equals(FieldType.BOOLEAN))
                .collect(Collectors.toList());
        int offset = 0;
        int bitOffset = 0;
        for (FieldDescriptor fieldDefinition : booleanFieldsList) {
            fieldDefinition.setOffset(offset);
            fieldDefinition.setBitOffset((byte) (bitOffset % Byte.SIZE));
            bitOffset++;
            if (bitOffset % Byte.SIZE == 0) {
                offset += 1;
            }
        }
        if (bitOffset % Byte.SIZE != 0) {
            offset++;
        }

        List<FieldDescriptor> fixedSizeList = fieldDefinitionMap.values().stream()
                .filter(FieldDescriptor::isFixedSize)
                .filter(fieldDescriptor -> !fieldDescriptor.getType().equals(FieldType.BOOLEAN))
                .sorted(Comparator.comparingInt(o -> o.getType().getTypeSize())).collect(Collectors.toList());
        for (FieldDescriptor fieldDefinition : fixedSizeList) {
            fieldDefinition.setOffset(offset);
            offset += fieldDefinition.getType().getTypeSize();
        }

        primitivesLength = offset;

        int index = 0;
        List<FieldDescriptor> varSizeList = fieldDefinitionMap.values().stream()
                .filter(fieldDescriptor -> !fieldDescriptor.isFixedSize())
                .collect(Collectors.toList());

        for (FieldDescriptor fieldDefinition : varSizeList) {
            fieldDefinition.setIndex(index++);
        }

        numberOfComplexFields = index;
    }

    /**
     * The class name provided when building a schema
     * In java, when it is not configured explicitly, this falls back to full class name including the path.
     *
     * @return name of the class
     */
    public String getTypeName() {
        return typeName;
    }

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

    public int getFieldCount() {
        return fieldDefinitionMap.size();
    }

    public FieldDescriptor getField(String fieldName) {
        return fieldDefinitionMap.get(fieldName);
    }

    public boolean hasField(String fieldName) {
        return fieldDefinitionMap.containsKey(fieldName);
    }

    public long getSchemaId() {
        assert isSchemaIdSet;
        return schemaId;
    }

    public void setSchemaId(long schemaId) {
        this.isSchemaIdSet = true;
        this.schemaId = schemaId;
    }

    public boolean isSchemaIdSet() {
        return isSchemaIdSet;
    }

    @Override
    public String toString() {
        return "Schema {"
                + " className = " + typeName
                + " numberOfComplexFields = " + numberOfComplexFields
                + " primitivesLength = " + primitivesLength
                + ", map = " + fieldDefinitionMap
                + '}';
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeString(typeName);
        out.writeInt(fieldDefinitionMap.size());
        Collection<FieldDescriptor> fields = fieldDefinitionMap.values();
        for (FieldDescriptor descriptor : fields) {
            out.writeString(descriptor.getFieldName());
            out.writeInt(descriptor.getInternalFieldTypeId());
            out.writeBoolean(descriptor.isFixedSize());
        }
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        typeName = in.readString();
        int fieldDefinitionsSize = in.readInt();
        fieldDefinitionMap = new TreeMap<>(Comparator.naturalOrder());
        for (int i = 0; i < fieldDefinitionsSize; i++) {
            String fieldName = in.readString();
            int type = in.readInt();
            boolean isFixedSize = in.readBoolean();
            FieldDescriptor descriptor = new FieldDescriptor(fieldName, type, isFixedSize);
            fieldDefinitionMap.put(fieldName, descriptor);
        }
        init();
    }

    @Override
    public int getFactoryId() {
        return SchemaDataSerializerHook.F_ID;
    }

    @Override
    public int getClassId() {
        return SchemaDataSerializerHook.SCHEMA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Schema schema = (Schema) o;
        return numberOfComplexFields == schema.numberOfComplexFields
                && primitivesLength == schema.primitivesLength
                && schemaId == schema.schemaId
                && Objects.equals(typeName, schema.typeName)
                && Objects.equals(fieldDefinitionMap, schema.fieldDefinitionMap);
    }

    @Override
    public int hashCode() {
        return (int) schemaId;
    }
}
