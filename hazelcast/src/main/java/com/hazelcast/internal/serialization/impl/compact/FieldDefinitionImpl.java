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
import com.hazelcast.nio.serialization.compact.FieldDefinition;

import javax.annotation.Nonnull;

public class FieldDefinitionImpl implements FieldDefinition {

    private final String fieldName;
    private final FieldType type;
    private int index = -1;
    private int offset = -1;

    public FieldDefinitionImpl(@Nonnull String fieldName, @Nonnull FieldType type) {
        this.fieldName = fieldName;
        this.type = type;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public FieldType getType() {
        return type;
    }

    @Override
    public String getName() {
        return fieldName;
    }

    /**
     *
     * @return the index of offset of the non-primitive field. if field is primitive returns -1
     */
    public int getIndex() {
        return index;
    }

    /**
     * @return the offset to read  the primitive field from. If field is not primitive returns -1
     */
    public int getOffset() {
        return offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FieldDefinitionImpl that = (FieldDefinitionImpl) o;

        if (index != that.index) {
            return false;
        }
        if (!fieldName.equals(that.fieldName)) {
            return false;
        }
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = fieldName.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + index;
        return result;
    }

    @Override
    public String toString() {
        return "FieldDefinitionImpl{" +
                "fieldName='" + fieldName + '\'' +
                ", type=" + type +
                ", index=" + index +
                ", offset=" + offset +
                '}';
    }
}
