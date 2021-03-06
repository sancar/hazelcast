/*
 * Copyright (c) 2008-2018, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.spi.impl.merge;

import com.hazelcast.cache.CacheEntryView;
import com.hazelcast.cache.impl.record.CacheRecord;
import com.hazelcast.cardinality.impl.hyperloglog.HyperLogLog;
import com.hazelcast.collection.impl.collection.CollectionItem;
import com.hazelcast.collection.impl.queue.QueueItem;
import com.hazelcast.core.EntryView;
import com.hazelcast.map.impl.record.Record;
import com.hazelcast.multimap.impl.MultiMapContainer;
import com.hazelcast.multimap.impl.MultiMapMergeContainer;
import com.hazelcast.multimap.impl.MultiMapRecord;
import com.hazelcast.nio.serialization.Data;
import com.hazelcast.replicatedmap.impl.record.ReplicatedRecord;
import com.hazelcast.scheduledexecutor.impl.ScheduledTaskDescriptor;
import com.hazelcast.spi.merge.MergingEntry;
import com.hazelcast.spi.merge.MergingValue;
import com.hazelcast.spi.merge.SplitBrainMergeTypes.AtomicLongMergeTypes;
import com.hazelcast.spi.merge.SplitBrainMergeTypes.AtomicReferenceMergeTypes;
import com.hazelcast.spi.merge.SplitBrainMergeTypes.CacheMergeTypes;
import com.hazelcast.spi.merge.SplitBrainMergeTypes.CardinalityEstimatorMergeTypes;
import com.hazelcast.spi.merge.SplitBrainMergeTypes.CollectionMergeTypes;
import com.hazelcast.spi.merge.SplitBrainMergeTypes.MapMergeTypes;
import com.hazelcast.spi.merge.SplitBrainMergeTypes.MultiMapMergeTypes;
import com.hazelcast.spi.merge.SplitBrainMergeTypes.QueueMergeTypes;
import com.hazelcast.spi.merge.SplitBrainMergeTypes.ReplicatedMapMergeTypes;
import com.hazelcast.spi.merge.SplitBrainMergeTypes.RingbufferMergeTypes;
import com.hazelcast.spi.merge.SplitBrainMergeTypes.ScheduledExecutorMergeTypes;
import com.hazelcast.spi.serialization.SerializationService;

/**
 * Provides static factory methods to create {@link MergingValue} and {@link MergingEntry} instances.
 *
 * @since 3.10
 */
public final class MergingValueFactory {

    private MergingValueFactory() {
    }

    public static CollectionMergeTypes createMergingValue(SerializationService serializationService, CollectionItem item) {
        return new CollectionMergingValueImpl(serializationService)
                .setValue(item.getValue());
    }

    public static QueueMergeTypes createMergingValue(SerializationService serializationService, QueueItem item) {
        return new QueueMergingValueImpl(serializationService)
                .setValue(item.getData());
    }

    public static AtomicLongMergeTypes createMergingValue(SerializationService serializationService, Long value) {
        return new AtomicLongMergingValueImpl(serializationService)
                .setValue(value);
    }

    public static AtomicReferenceMergeTypes createMergingValue(SerializationService serializationService, Data value) {
        return new AtomicReferenceMergingValueImpl(serializationService)
                .setValue(value);
    }

    public static MapMergeTypes createMergingEntry(SerializationService serializationService, EntryView<Data, Data> entryView) {
        return new MapMergingEntryImpl(serializationService)
                .setKey(entryView.getKey())
                .setValue(entryView.getValue())
                .setCreationTime(entryView.getCreationTime())
                .setExpirationTime(entryView.getExpirationTime())
                .setLastStoredTime(entryView.getLastStoredTime())
                .setLastUpdateTime(entryView.getLastUpdateTime())
                .setLastAccessTime(entryView.getLastAccessTime())
                .setHits(entryView.getHits())
                .setTtl(entryView.getTtl())
                .setVersion(entryView.getVersion())
                .setCost(entryView.getCost());
    }

    public static MapMergeTypes createMergingEntry(SerializationService serializationService, Record record) {
        return new MapMergingEntryImpl(serializationService)
                .setKey(record.getKey())
                .setValue(serializationService.toData(record.getValue()))
                .setCreationTime(record.getCreationTime())
                .setExpirationTime(record.getExpirationTime())
                .setLastStoredTime(record.getLastStoredTime())
                .setLastAccessTime(record.getLastAccessTime())
                .setLastStoredTime(record.getLastStoredTime())
                .setLastUpdateTime(record.getLastUpdateTime())
                .setHits(record.getHits())
                .setTtl(record.getTtl())
                .setVersion(record.getVersion())
                .setCost(record.getCost());
    }

    public static MapMergeTypes createMergingEntry(SerializationService serializationService, Record record, Data dataValue) {
        return new MapMergingEntryImpl(serializationService)
                .setKey(record.getKey())
                .setValue(dataValue)
                .setCreationTime(record.getCreationTime())
                .setExpirationTime(record.getExpirationTime())
                .setLastStoredTime(record.getLastStoredTime())
                .setLastAccessTime(record.getLastAccessTime())
                .setLastUpdateTime(record.getLastUpdateTime())
                .setHits(record.getHits())
                .setTtl(record.getTtl())
                .setVersion(record.getVersion())
                .setCost(record.getCost());
    }

    public static CacheMergeTypes createMergingEntry(SerializationService serializationService,
                                                     CacheEntryView<Data, Data> entryView) {
        return new CacheMergingEntryImpl(serializationService)
                .setKey(entryView.getKey())
                .setValue(entryView.getValue())
                .setCreationTime(entryView.getCreationTime())
                .setExpirationTime(entryView.getExpirationTime())
                .setLastAccessTime(entryView.getLastAccessTime())
                .setHits(entryView.getAccessHit());
    }

    public static <R extends CacheRecord> CacheMergeTypes createMergingEntry(SerializationService serializationService,
                                                                             Data key, Data value, R record) {
        return new CacheMergingEntryImpl(serializationService)
                .setKey(key)
                .setValue(value)
                .setCreationTime(record.getCreationTime())
                .setExpirationTime(record.getExpirationTime())
                .setLastAccessTime(record.getLastAccessTime())
                .setHits(record.getAccessHit());
    }

    public static ReplicatedMapMergeTypes createMergingEntry(SerializationService serializationService, ReplicatedRecord record) {
        return new ReplicatedMapMergingEntryImpl(serializationService)
                .setKey(record.getKeyInternal())
                .setValue(record.getValueInternal())
                .setCreationTime(record.getCreationTime())
                .setHits(record.getHits())
                .setLastAccessTime(record.getLastAccessTime())
                .setLastUpdateTime(record.getUpdateTime())
                .setTtl(record.getTtlMillis());
    }

    public static MultiMapMergeTypes createMergingEntry(SerializationService serializationService,
                                                        MultiMapMergeContainer container, MultiMapRecord record) {
        return new MultiMapMergingEntryImpl(serializationService)
                .setKey(container.getKey())
                .setValue(record.getObject())
                .setCreationTime(container.getCreationTime())
                .setLastAccessTime(container.getLastAccessTime())
                .setLastUpdateTime(container.getLastUpdateTime())
                .setHits(container.getHits());
    }

    public static MultiMapMergeTypes createMergingEntry(SerializationService serializationService, MultiMapContainer container,
                                                        Data key, MultiMapRecord record, int hits) {
        return new MultiMapMergingEntryImpl(serializationService)
                .setKey(key)
                .setValue(record.getObject())
                .setCreationTime(container.getCreationTime())
                .setLastAccessTime(container.getLastAccessTime())
                .setLastUpdateTime(container.getLastUpdateTime())
                .setHits(hits);
    }

    public static RingbufferMergeTypes createMergingEntry(SerializationService serializationService, Long key, Object value) {
        return new RingbufferMergingEntryImpl(serializationService)
                .setKey(key)
                .setValue(value);
    }

    public static CardinalityEstimatorMergeTypes createMergingEntry(SerializationService serializationService,
                                                                    String name, HyperLogLog hyperLogLog) {
        return new CardinalityEstimatorMergingEntry(serializationService)
                .setKey(name)
                .setValue(hyperLogLog);
    }

    public static ScheduledExecutorMergeTypes createMergingEntry(SerializationService serializationService,
                                                                 ScheduledTaskDescriptor task) {
        return new ScheduledExecutorMergingEntryImpl(serializationService)
                .setKey(task.getDefinition().getName())
                .setValue(task);
    }
}
