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

package com.hazelcast.nio.serialization;

import com.hazelcast.internal.serialization.InternalSerializationService;
import com.hazelcast.internal.serialization.impl.GenericRecordQueryReader;
import com.hazelcast.internal.serialization.impl.InternalGenericRecord;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.serialization.compact.MetaDataService;

import java.io.IOException;

public interface AdvancedSerializer extends Serializer {

    void setInternalSerializationService(InternalSerializationService internalSerializationService);

    void setMetaDataService(MetaDataService metaDataService);

    //Integration point for query system
    //Note that this will be used on the cluster side. So this implementation should be on the cluster side.
    InternalGenericRecord readAsInternalGenericRecord(ObjectDataInput input) throws IOException;
}
