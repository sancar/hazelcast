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

package com.hazelcast.nio.serialization.compact;

import java.util.Collection;

/**
 * Represents the schema of a class.
 * Consists of field definitions and the class name.
 */
public interface Schema {

    /**
     * The class name provided when building a schema
     * In java, when it is not configured explicitly, this falls back to full class name including the path.
     *
     * @return name of the class
     */
    String getClassName();

    Collection<FieldDefinition> getFields();

    int getFieldCount();

    FieldDefinition getField(String fieldName);

    boolean hasField(String fieldName);

}
