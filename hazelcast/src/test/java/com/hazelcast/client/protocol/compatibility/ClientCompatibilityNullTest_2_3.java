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

package com.hazelcast.client.protocol.compatibility;

import com.hazelcast.client.HazelcastClientUtil;
import com.hazelcast.client.impl.protocol.ClientMessage;
import com.hazelcast.client.impl.protocol.ClientMessageReader;
<<<<<<< HEAD
import com.hazelcast.client.impl.protocol.codec.AtomicLongAddAndGetCodec;
import com.hazelcast.client.impl.protocol.codec.AtomicLongAlterCodec;
import com.hazelcast.client.impl.protocol.codec.AtomicLongApplyCodec;
import com.hazelcast.client.impl.protocol.codec.AtomicLongCompareAndSetCodec;
import com.hazelcast.client.impl.protocol.codec.AtomicLongGetAndAddCodec;
import com.hazelcast.client.impl.protocol.codec.AtomicLongGetAndSetCodec;
import com.hazelcast.client.impl.protocol.codec.AtomicLongGetCodec;
import com.hazelcast.client.impl.protocol.codec.AtomicRefApplyCodec;
import com.hazelcast.client.impl.protocol.codec.AtomicRefCompareAndSetCodec;
import com.hazelcast.client.impl.protocol.codec.AtomicRefContainsCodec;
import com.hazelcast.client.impl.protocol.codec.AtomicRefGetCodec;
import com.hazelcast.client.impl.protocol.codec.AtomicRefSetCodec;
import com.hazelcast.client.impl.protocol.codec.CPGroupCreateCPGroupCodec;
import com.hazelcast.client.impl.protocol.codec.CPGroupDestroyCPObjectCodec;
import com.hazelcast.client.impl.protocol.codec.CPSessionCloseSessionCodec;
import com.hazelcast.client.impl.protocol.codec.CPSessionCreateSessionCodec;
import com.hazelcast.client.impl.protocol.codec.CPSessionGenerateThreadIdCodec;
import com.hazelcast.client.impl.protocol.codec.CPSessionHeartbeatSessionCodec;
import com.hazelcast.client.impl.protocol.codec.CPSubsystemAddGroupAvailabilityListenerCodec;
import com.hazelcast.client.impl.protocol.codec.CPSubsystemAddMembershipListenerCodec;
import com.hazelcast.client.impl.protocol.codec.CPSubsystemRemoveGroupAvailabilityListenerCodec;
import com.hazelcast.client.impl.protocol.codec.CPSubsystemRemoveMembershipListenerCodec;
import com.hazelcast.client.impl.protocol.codec.CacheAddEntryListenerCodec;
import com.hazelcast.client.impl.protocol.codec.CacheAddNearCacheInvalidationListenerCodec;
import com.hazelcast.client.impl.protocol.codec.CacheAddPartitionLostListenerCodec;
import com.hazelcast.client.impl.protocol.codec.CacheClearCodec;
import com.hazelcast.client.impl.protocol.codec.CacheContainsKeyCodec;
import com.hazelcast.client.impl.protocol.codec.CacheCreateConfigCodec;
import com.hazelcast.client.impl.protocol.codec.CacheDestroyCodec;
import com.hazelcast.client.impl.protocol.codec.CacheEntryProcessorCodec;
import com.hazelcast.client.impl.protocol.codec.CacheEventJournalReadCodec;
import com.hazelcast.client.impl.protocol.codec.CacheEventJournalSubscribeCodec;
import com.hazelcast.client.impl.protocol.codec.CacheFetchNearCacheInvalidationMetadataCodec;
import com.hazelcast.client.impl.protocol.codec.CacheGetAllCodec;
import com.hazelcast.client.impl.protocol.codec.CacheGetAndRemoveCodec;
import com.hazelcast.client.impl.protocol.codec.CacheGetAndReplaceCodec;
import com.hazelcast.client.impl.protocol.codec.CacheGetCodec;
import com.hazelcast.client.impl.protocol.codec.CacheGetConfigCodec;
import com.hazelcast.client.impl.protocol.codec.CacheIterateCodec;
import com.hazelcast.client.impl.protocol.codec.CacheIterateEntriesCodec;
import com.hazelcast.client.impl.protocol.codec.CacheListenerRegistrationCodec;
import com.hazelcast.client.impl.protocol.codec.CacheLoadAllCodec;
import com.hazelcast.client.impl.protocol.codec.CacheManagementConfigCodec;
import com.hazelcast.client.impl.protocol.codec.CachePutAllCodec;
import com.hazelcast.client.impl.protocol.codec.CachePutCodec;
import com.hazelcast.client.impl.protocol.codec.CachePutIfAbsentCodec;
import com.hazelcast.client.impl.protocol.codec.CacheRemoveAllCodec;
import com.hazelcast.client.impl.protocol.codec.CacheRemoveAllKeysCodec;
import com.hazelcast.client.impl.protocol.codec.CacheRemoveCodec;
import com.hazelcast.client.impl.protocol.codec.CacheRemoveEntryListenerCodec;
import com.hazelcast.client.impl.protocol.codec.CacheRemoveInvalidationListenerCodec;
import com.hazelcast.client.impl.protocol.codec.CacheRemovePartitionLostListenerCodec;
import com.hazelcast.client.impl.protocol.codec.CacheReplaceCodec;
import com.hazelcast.client.impl.protocol.codec.CacheSetExpiryPolicyCodec;
import com.hazelcast.client.impl.protocol.codec.CacheSizeCodec;
import com.hazelcast.client.impl.protocol.codec.CardinalityEstimatorAddCodec;
import com.hazelcast.client.impl.protocol.codec.CardinalityEstimatorEstimateCodec;
import com.hazelcast.client.impl.protocol.codec.ClientAddClusterViewListenerCodec;
import com.hazelcast.client.impl.protocol.codec.ClientAddDistributedObjectListenerCodec;
import com.hazelcast.client.impl.protocol.codec.ClientAddMigrationListenerCodec;
import com.hazelcast.client.impl.protocol.codec.ClientAddPartitionLostListenerCodec;
import com.hazelcast.client.impl.protocol.codec.ClientAuthenticationCodec;
import com.hazelcast.client.impl.protocol.codec.ClientAuthenticationCustomCodec;
import com.hazelcast.client.impl.protocol.codec.ClientCreateProxiesCodec;
import com.hazelcast.client.impl.protocol.codec.ClientCreateProxyCodec;
import com.hazelcast.client.impl.protocol.codec.ClientDeployClassesCodec;
import com.hazelcast.client.impl.protocol.codec.ClientDestroyProxyCodec;
import com.hazelcast.client.impl.protocol.codec.ClientGetDistributedObjectsCodec;
import com.hazelcast.client.impl.protocol.codec.ClientLocalBackupListenerCodec;
import com.hazelcast.client.impl.protocol.codec.ClientPingCodec;
import com.hazelcast.client.impl.protocol.codec.ClientRemoveDistributedObjectListenerCodec;
import com.hazelcast.client.impl.protocol.codec.ClientRemoveMigrationListenerCodec;
import com.hazelcast.client.impl.protocol.codec.ClientRemovePartitionLostListenerCodec;
import com.hazelcast.client.impl.protocol.codec.ClientStatisticsCodec;
import com.hazelcast.client.impl.protocol.codec.ClientTriggerPartitionAssignmentCodec;
import com.hazelcast.client.impl.protocol.codec.ContinuousQueryAddListenerCodec;
import com.hazelcast.client.impl.protocol.codec.ContinuousQueryDestroyCacheCodec;
import com.hazelcast.client.impl.protocol.codec.ContinuousQueryMadePublishableCodec;
import com.hazelcast.client.impl.protocol.codec.ContinuousQueryPublisherCreateCodec;
import com.hazelcast.client.impl.protocol.codec.ContinuousQueryPublisherCreateWithValueCodec;
import com.hazelcast.client.impl.protocol.codec.ContinuousQuerySetReadCursorCodec;
import com.hazelcast.client.impl.protocol.codec.CountDownLatchAwaitCodec;
import com.hazelcast.client.impl.protocol.codec.CountDownLatchCountDownCodec;
import com.hazelcast.client.impl.protocol.codec.CountDownLatchGetCountCodec;
import com.hazelcast.client.impl.protocol.codec.CountDownLatchGetRoundCodec;
import com.hazelcast.client.impl.protocol.codec.CountDownLatchTrySetCountCodec;
import com.hazelcast.client.impl.protocol.codec.DurableExecutorDisposeResultCodec;
import com.hazelcast.client.impl.protocol.codec.DurableExecutorIsShutdownCodec;
import com.hazelcast.client.impl.protocol.codec.DurableExecutorRetrieveAndDisposeResultCodec;
import com.hazelcast.client.impl.protocol.codec.DurableExecutorRetrieveResultCodec;
import com.hazelcast.client.impl.protocol.codec.DurableExecutorShutdownCodec;
import com.hazelcast.client.impl.protocol.codec.DurableExecutorSubmitToPartitionCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddCacheConfigCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddCardinalityEstimatorConfigCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddDurableExecutorConfigCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddExecutorConfigCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddFlakeIdGeneratorConfigCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddListConfigCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddMapConfigCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddMultiMapConfigCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddPNCounterConfigCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddQueueConfigCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddReliableTopicConfigCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddReplicatedMapConfigCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddRingbufferConfigCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddScheduledExecutorConfigCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddSetConfigCodec;
import com.hazelcast.client.impl.protocol.codec.DynamicConfigAddTopicConfigCodec;
import com.hazelcast.client.impl.protocol.codec.ExecutorServiceCancelOnMemberCodec;
import com.hazelcast.client.impl.protocol.codec.ExecutorServiceCancelOnPartitionCodec;
import com.hazelcast.client.impl.protocol.codec.ExecutorServiceIsShutdownCodec;
import com.hazelcast.client.impl.protocol.codec.ExecutorServiceShutdownCodec;
import com.hazelcast.client.impl.protocol.codec.ExecutorServiceSubmitToMemberCodec;
import com.hazelcast.client.impl.protocol.codec.ExecutorServiceSubmitToPartitionCodec;
import com.hazelcast.client.impl.protocol.codec.FencedLockGetLockOwnershipCodec;
import com.hazelcast.client.impl.protocol.codec.FencedLockLockCodec;
import com.hazelcast.client.impl.protocol.codec.FencedLockTryLockCodec;
import com.hazelcast.client.impl.protocol.codec.FencedLockUnlockCodec;
import com.hazelcast.client.impl.protocol.codec.FlakeIdGeneratorNewIdBatchCodec;
import com.hazelcast.client.impl.protocol.codec.ListAddAllCodec;
import com.hazelcast.client.impl.protocol.codec.ListAddAllWithIndexCodec;
import com.hazelcast.client.impl.protocol.codec.ListAddCodec;
import com.hazelcast.client.impl.protocol.codec.ListAddListenerCodec;
import com.hazelcast.client.impl.protocol.codec.ListAddWithIndexCodec;
import com.hazelcast.client.impl.protocol.codec.ListClearCodec;
import com.hazelcast.client.impl.protocol.codec.ListCompareAndRemoveAllCodec;
import com.hazelcast.client.impl.protocol.codec.ListCompareAndRetainAllCodec;
import com.hazelcast.client.impl.protocol.codec.ListContainsAllCodec;
import com.hazelcast.client.impl.protocol.codec.ListContainsCodec;
import com.hazelcast.client.impl.protocol.codec.ListGetAllCodec;
import com.hazelcast.client.impl.protocol.codec.ListGetCodec;
import com.hazelcast.client.impl.protocol.codec.ListIndexOfCodec;
import com.hazelcast.client.impl.protocol.codec.ListIsEmptyCodec;
import com.hazelcast.client.impl.protocol.codec.ListIteratorCodec;
import com.hazelcast.client.impl.protocol.codec.ListLastIndexOfCodec;
import com.hazelcast.client.impl.protocol.codec.ListListIteratorCodec;
import com.hazelcast.client.impl.protocol.codec.ListRemoveCodec;
import com.hazelcast.client.impl.protocol.codec.ListRemoveListenerCodec;
import com.hazelcast.client.impl.protocol.codec.ListRemoveWithIndexCodec;
import com.hazelcast.client.impl.protocol.codec.ListSetCodec;
import com.hazelcast.client.impl.protocol.codec.ListSizeCodec;
import com.hazelcast.client.impl.protocol.codec.ListSubCodec;
import com.hazelcast.client.impl.protocol.codec.MCAddWanBatchPublisherConfigCodec;
import com.hazelcast.client.impl.protocol.codec.MCApplyMCConfigCodec;
import com.hazelcast.client.impl.protocol.codec.MCChangeClusterStateCodec;
import com.hazelcast.client.impl.protocol.codec.MCChangeClusterVersionCodec;
import com.hazelcast.client.impl.protocol.codec.MCChangeWanReplicationStateCodec;
import com.hazelcast.client.impl.protocol.codec.MCCheckWanConsistencyCodec;
import com.hazelcast.client.impl.protocol.codec.MCClearWanQueuesCodec;
import com.hazelcast.client.impl.protocol.codec.MCGetCPMembersCodec;
import com.hazelcast.client.impl.protocol.codec.MCGetClusterMetadataCodec;
import com.hazelcast.client.impl.protocol.codec.MCGetMapConfigCodec;
import com.hazelcast.client.impl.protocol.codec.MCGetMemberConfigCodec;
import com.hazelcast.client.impl.protocol.codec.MCGetSystemPropertiesCodec;
import com.hazelcast.client.impl.protocol.codec.MCGetThreadDumpCodec;
import com.hazelcast.client.impl.protocol.codec.MCGetTimedMemberStateCodec;
import com.hazelcast.client.impl.protocol.codec.MCInterruptHotRestartBackupCodec;
import com.hazelcast.client.impl.protocol.codec.MCMatchMCConfigCodec;
import com.hazelcast.client.impl.protocol.codec.MCPollMCEventsCodec;
import com.hazelcast.client.impl.protocol.codec.MCPromoteLiteMemberCodec;
import com.hazelcast.client.impl.protocol.codec.MCPromoteToCPMemberCodec;
import com.hazelcast.client.impl.protocol.codec.MCReadMetricsCodec;
import com.hazelcast.client.impl.protocol.codec.MCRemoveCPMemberCodec;
import com.hazelcast.client.impl.protocol.codec.MCResetCPSubsystemCodec;
import com.hazelcast.client.impl.protocol.codec.MCRunConsoleCommandCodec;
import com.hazelcast.client.impl.protocol.codec.MCRunGcCodec;
import com.hazelcast.client.impl.protocol.codec.MCRunScriptCodec;
import com.hazelcast.client.impl.protocol.codec.MCShutdownClusterCodec;
import com.hazelcast.client.impl.protocol.codec.MCShutdownMemberCodec;
import com.hazelcast.client.impl.protocol.codec.MCTriggerForceStartCodec;
import com.hazelcast.client.impl.protocol.codec.MCTriggerHotRestartBackupCodec;
import com.hazelcast.client.impl.protocol.codec.MCTriggerPartialStartCodec;
import com.hazelcast.client.impl.protocol.codec.MCUpdateMapConfigCodec;
import com.hazelcast.client.impl.protocol.codec.MCWanSyncMapCodec;
import com.hazelcast.client.impl.protocol.codec.MapAddEntryListenerCodec;
import com.hazelcast.client.impl.protocol.codec.MapAddEntryListenerToKeyCodec;
import com.hazelcast.client.impl.protocol.codec.MapAddEntryListenerToKeyWithPredicateCodec;
import com.hazelcast.client.impl.protocol.codec.MapAddEntryListenerWithPredicateCodec;
import com.hazelcast.client.impl.protocol.codec.MapAddIndexCodec;
import com.hazelcast.client.impl.protocol.codec.MapAddInterceptorCodec;
import com.hazelcast.client.impl.protocol.codec.MapAddNearCacheInvalidationListenerCodec;
import com.hazelcast.client.impl.protocol.codec.MapAddPartitionLostListenerCodec;
import com.hazelcast.client.impl.protocol.codec.MapAggregateCodec;
import com.hazelcast.client.impl.protocol.codec.MapAggregateWithPredicateCodec;
import com.hazelcast.client.impl.protocol.codec.MapClearCodec;
import com.hazelcast.client.impl.protocol.codec.MapContainsKeyCodec;
import com.hazelcast.client.impl.protocol.codec.MapContainsValueCodec;
import com.hazelcast.client.impl.protocol.codec.MapDeleteCodec;
import com.hazelcast.client.impl.protocol.codec.MapEntriesWithPagingPredicateCodec;
import com.hazelcast.client.impl.protocol.codec.MapEntriesWithPredicateCodec;
import com.hazelcast.client.impl.protocol.codec.MapEntrySetCodec;
import com.hazelcast.client.impl.protocol.codec.MapEventJournalReadCodec;
import com.hazelcast.client.impl.protocol.codec.MapEventJournalSubscribeCodec;
import com.hazelcast.client.impl.protocol.codec.MapEvictAllCodec;
import com.hazelcast.client.impl.protocol.codec.MapEvictCodec;
import com.hazelcast.client.impl.protocol.codec.MapExecuteOnAllKeysCodec;
import com.hazelcast.client.impl.protocol.codec.MapExecuteOnKeyCodec;
import com.hazelcast.client.impl.protocol.codec.MapExecuteOnKeysCodec;
import com.hazelcast.client.impl.protocol.codec.MapExecuteWithPredicateCodec;
import com.hazelcast.client.impl.protocol.codec.MapFetchEntriesCodec;
import com.hazelcast.client.impl.protocol.codec.MapFetchKeysCodec;
import com.hazelcast.client.impl.protocol.codec.MapFetchNearCacheInvalidationMetadataCodec;
import com.hazelcast.client.impl.protocol.codec.MapFetchWithQueryCodec;
import com.hazelcast.client.impl.protocol.codec.MapFlushCodec;
import com.hazelcast.client.impl.protocol.codec.MapForceUnlockCodec;
import com.hazelcast.client.impl.protocol.codec.MapGetAllCodec;
import com.hazelcast.client.impl.protocol.codec.MapGetCodec;
import com.hazelcast.client.impl.protocol.codec.MapGetEntryViewCodec;
import com.hazelcast.client.impl.protocol.codec.MapIsEmptyCodec;
import com.hazelcast.client.impl.protocol.codec.MapIsLockedCodec;
import com.hazelcast.client.impl.protocol.codec.MapKeySetCodec;
import com.hazelcast.client.impl.protocol.codec.MapKeySetWithPagingPredicateCodec;
import com.hazelcast.client.impl.protocol.codec.MapKeySetWithPredicateCodec;
import com.hazelcast.client.impl.protocol.codec.MapLoadAllCodec;
import com.hazelcast.client.impl.protocol.codec.MapLoadGivenKeysCodec;
import com.hazelcast.client.impl.protocol.codec.MapLockCodec;
import com.hazelcast.client.impl.protocol.codec.MapProjectCodec;
import com.hazelcast.client.impl.protocol.codec.MapProjectWithPredicateCodec;
import com.hazelcast.client.impl.protocol.codec.MapPutAllCodec;
import com.hazelcast.client.impl.protocol.codec.MapPutCodec;
import com.hazelcast.client.impl.protocol.codec.MapPutIfAbsentCodec;
import com.hazelcast.client.impl.protocol.codec.MapPutIfAbsentWithMaxIdleCodec;
import com.hazelcast.client.impl.protocol.codec.MapPutTransientCodec;
import com.hazelcast.client.impl.protocol.codec.MapPutTransientWithMaxIdleCodec;
import com.hazelcast.client.impl.protocol.codec.MapPutWithMaxIdleCodec;
import com.hazelcast.client.impl.protocol.codec.MapRemoveAllCodec;
import com.hazelcast.client.impl.protocol.codec.MapRemoveCodec;
import com.hazelcast.client.impl.protocol.codec.MapRemoveEntryListenerCodec;
import com.hazelcast.client.impl.protocol.codec.MapRemoveIfSameCodec;
import com.hazelcast.client.impl.protocol.codec.MapRemoveInterceptorCodec;
import com.hazelcast.client.impl.protocol.codec.MapRemovePartitionLostListenerCodec;
import com.hazelcast.client.impl.protocol.codec.MapReplaceCodec;
import com.hazelcast.client.impl.protocol.codec.MapReplaceIfSameCodec;
import com.hazelcast.client.impl.protocol.codec.MapSetCodec;
import com.hazelcast.client.impl.protocol.codec.MapSetTtlCodec;
import com.hazelcast.client.impl.protocol.codec.MapSetWithMaxIdleCodec;
import com.hazelcast.client.impl.protocol.codec.MapSizeCodec;
import com.hazelcast.client.impl.protocol.codec.MapSubmitToKeyCodec;
import com.hazelcast.client.impl.protocol.codec.MapTryLockCodec;
import com.hazelcast.client.impl.protocol.codec.MapTryPutCodec;
import com.hazelcast.client.impl.protocol.codec.MapTryRemoveCodec;
import com.hazelcast.client.impl.protocol.codec.MapUnlockCodec;
import com.hazelcast.client.impl.protocol.codec.MapValuesCodec;
import com.hazelcast.client.impl.protocol.codec.MapValuesWithPagingPredicateCodec;
import com.hazelcast.client.impl.protocol.codec.MapValuesWithPredicateCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapAddEntryListenerCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapAddEntryListenerToKeyCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapClearCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapContainsEntryCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapContainsKeyCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapContainsValueCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapDeleteCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapEntrySetCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapForceUnlockCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapGetCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapIsLockedCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapKeySetCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapLockCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapPutAllCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapPutCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapRemoveCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapRemoveEntryCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapRemoveEntryListenerCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapSizeCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapTryLockCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapUnlockCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapValueCountCodec;
import com.hazelcast.client.impl.protocol.codec.MultiMapValuesCodec;
import com.hazelcast.client.impl.protocol.codec.PNCounterAddCodec;
import com.hazelcast.client.impl.protocol.codec.PNCounterGetCodec;
import com.hazelcast.client.impl.protocol.codec.PNCounterGetConfiguredReplicaCountCodec;
import com.hazelcast.client.impl.protocol.codec.QueueAddAllCodec;
import com.hazelcast.client.impl.protocol.codec.QueueAddListenerCodec;
import com.hazelcast.client.impl.protocol.codec.QueueClearCodec;
import com.hazelcast.client.impl.protocol.codec.QueueCompareAndRemoveAllCodec;
import com.hazelcast.client.impl.protocol.codec.QueueCompareAndRetainAllCodec;
import com.hazelcast.client.impl.protocol.codec.QueueContainsAllCodec;
import com.hazelcast.client.impl.protocol.codec.QueueContainsCodec;
import com.hazelcast.client.impl.protocol.codec.QueueDrainToCodec;
import com.hazelcast.client.impl.protocol.codec.QueueDrainToMaxSizeCodec;
import com.hazelcast.client.impl.protocol.codec.QueueIsEmptyCodec;
import com.hazelcast.client.impl.protocol.codec.QueueIteratorCodec;
import com.hazelcast.client.impl.protocol.codec.QueueOfferCodec;
import com.hazelcast.client.impl.protocol.codec.QueuePeekCodec;
import com.hazelcast.client.impl.protocol.codec.QueuePollCodec;
import com.hazelcast.client.impl.protocol.codec.QueuePutCodec;
import com.hazelcast.client.impl.protocol.codec.QueueRemainingCapacityCodec;
import com.hazelcast.client.impl.protocol.codec.QueueRemoveCodec;
import com.hazelcast.client.impl.protocol.codec.QueueRemoveListenerCodec;
import com.hazelcast.client.impl.protocol.codec.QueueSizeCodec;
import com.hazelcast.client.impl.protocol.codec.QueueTakeCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapAddEntryListenerCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapAddEntryListenerToKeyCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapAddEntryListenerToKeyWithPredicateCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapAddEntryListenerWithPredicateCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapAddNearCacheEntryListenerCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapClearCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapContainsKeyCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapContainsValueCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapEntrySetCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapGetCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapIsEmptyCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapKeySetCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapPutAllCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapPutCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapRemoveCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapRemoveEntryListenerCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapSizeCodec;
import com.hazelcast.client.impl.protocol.codec.ReplicatedMapValuesCodec;
import com.hazelcast.client.impl.protocol.codec.RingbufferAddAllCodec;
import com.hazelcast.client.impl.protocol.codec.RingbufferAddCodec;
import com.hazelcast.client.impl.protocol.codec.RingbufferCapacityCodec;
import com.hazelcast.client.impl.protocol.codec.RingbufferHeadSequenceCodec;
import com.hazelcast.client.impl.protocol.codec.RingbufferReadManyCodec;
import com.hazelcast.client.impl.protocol.codec.RingbufferReadOneCodec;
import com.hazelcast.client.impl.protocol.codec.RingbufferRemainingCapacityCodec;
import com.hazelcast.client.impl.protocol.codec.RingbufferSizeCodec;
import com.hazelcast.client.impl.protocol.codec.RingbufferTailSequenceCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorCancelFromMemberCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorCancelFromPartitionCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorDisposeFromMemberCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorDisposeFromPartitionCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorGetAllScheduledFuturesCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorGetDelayFromMemberCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorGetDelayFromPartitionCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorGetResultFromMemberCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorGetResultFromPartitionCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorGetStatsFromMemberCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorGetStatsFromPartitionCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorIsCancelledFromMemberCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorIsCancelledFromPartitionCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorIsDoneFromMemberCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorIsDoneFromPartitionCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorShutdownCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorSubmitToMemberCodec;
import com.hazelcast.client.impl.protocol.codec.ScheduledExecutorSubmitToPartitionCodec;
import com.hazelcast.client.impl.protocol.codec.SemaphoreAcquireCodec;
import com.hazelcast.client.impl.protocol.codec.SemaphoreAvailablePermitsCodec;
import com.hazelcast.client.impl.protocol.codec.SemaphoreChangeCodec;
import com.hazelcast.client.impl.protocol.codec.SemaphoreDrainCodec;
import com.hazelcast.client.impl.protocol.codec.SemaphoreGetSemaphoreTypeCodec;
import com.hazelcast.client.impl.protocol.codec.SemaphoreInitCodec;
import com.hazelcast.client.impl.protocol.codec.SemaphoreReleaseCodec;
import com.hazelcast.client.impl.protocol.codec.SetAddAllCodec;
import com.hazelcast.client.impl.protocol.codec.SetAddCodec;
import com.hazelcast.client.impl.protocol.codec.SetAddListenerCodec;
import com.hazelcast.client.impl.protocol.codec.SetClearCodec;
import com.hazelcast.client.impl.protocol.codec.SetCompareAndRemoveAllCodec;
import com.hazelcast.client.impl.protocol.codec.SetCompareAndRetainAllCodec;
import com.hazelcast.client.impl.protocol.codec.SetContainsAllCodec;
import com.hazelcast.client.impl.protocol.codec.SetContainsCodec;
import com.hazelcast.client.impl.protocol.codec.SetGetAllCodec;
import com.hazelcast.client.impl.protocol.codec.SetIsEmptyCodec;
import com.hazelcast.client.impl.protocol.codec.SetRemoveCodec;
import com.hazelcast.client.impl.protocol.codec.SetRemoveListenerCodec;
import com.hazelcast.client.impl.protocol.codec.SetSizeCodec;
import com.hazelcast.client.impl.protocol.codec.SqlCloseCodec;
import com.hazelcast.client.impl.protocol.codec.SqlExecuteCodec;
import com.hazelcast.client.impl.protocol.codec.SqlExecute_reservedCodec;
import com.hazelcast.client.impl.protocol.codec.SqlFetchCodec;
import com.hazelcast.client.impl.protocol.codec.SqlFetch_reservedCodec;
import com.hazelcast.client.impl.protocol.codec.TopicAddMessageListenerCodec;
import com.hazelcast.client.impl.protocol.codec.TopicPublishAllCodec;
import com.hazelcast.client.impl.protocol.codec.TopicPublishCodec;
import com.hazelcast.client.impl.protocol.codec.TopicRemoveMessageListenerCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionCommitCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionCreateCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionRollbackCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalListAddCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalListRemoveCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalListSizeCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapContainsKeyCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapContainsValueCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapDeleteCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapGetCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapGetForUpdateCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapIsEmptyCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapKeySetCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapKeySetWithPredicateCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapPutCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapPutIfAbsentCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapRemoveCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapRemoveIfSameCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapReplaceCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapReplaceIfSameCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapSetCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapSizeCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapValuesCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMapValuesWithPredicateCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMultiMapGetCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMultiMapPutCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMultiMapRemoveCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMultiMapRemoveEntryCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMultiMapSizeCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalMultiMapValueCountCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalQueueOfferCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalQueuePeekCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalQueuePollCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalQueueSizeCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalQueueTakeCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalSetAddCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalSetRemoveCodec;
import com.hazelcast.client.impl.protocol.codec.TransactionalSetSizeCodec;
import com.hazelcast.client.impl.protocol.codec.XATransactionClearRemoteCodec;
import com.hazelcast.client.impl.protocol.codec.XATransactionCollectTransactionsCodec;
import com.hazelcast.client.impl.protocol.codec.XATransactionCommitCodec;
import com.hazelcast.client.impl.protocol.codec.XATransactionCreateCodec;
import com.hazelcast.client.impl.protocol.codec.XATransactionFinalizeCodec;
import com.hazelcast.client.impl.protocol.codec.XATransactionPrepareCodec;
import com.hazelcast.client.impl.protocol.codec.XATransactionRollbackCodec;
=======
import com.hazelcast.client.impl.protocol.codec.*;
>>>>>>> 94a59c88de (Serialization 2.0)
import com.hazelcast.test.HazelcastParallelClassRunner;
import com.hazelcast.test.annotation.ParallelJVMTest;
import com.hazelcast.test.annotation.QuickTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hazelcast.client.impl.protocol.ClientMessage.IS_FINAL_FLAG;
<<<<<<< HEAD
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aBoolean;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aByte;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aByteArray;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aCacheConfigHolder;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aCpMember;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aData;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfCacheEventData;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfClientBwListEntries;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfCpMembers;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfData;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfDataToData;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfDataToListOfData;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfDistributedObjectInfo;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfIntegerToInteger;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfIntegerToUUID;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfLongToByteArray;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfLongs;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfMCEvents;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfMemberInfos;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfQueryCacheEventData;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfScheduledTaskHandler;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfStringToByteArray;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfStringToListOfIntegerToLong;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfStringToString;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfStrings;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfUUIDToListOfIntegers;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfUUIDToUUID;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfUUIDs;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfUuidToLong;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfXids;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aLong;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aMigrationState;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aPagingPredicateHolder;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aQueryCacheEventData;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aRaftGroupId;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aString;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aUUID;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.anAnchorDataListHolder;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.anIndexConfig;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.anInt;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.anSqlQueryId;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.anXid;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.isEqual;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

@RunWith(HazelcastParallelClassRunner.class)
@Category({QuickTest.class, ParallelJVMTest.class})
public class ClientCompatibilityNullTest_2_3 {
    private final List<ClientMessage> clientMessages = new ArrayList<>();

    @Before
    public void setUp() throws IOException {
        File file = new File(getClass().getResource("/2.3.protocol.compatibility.null.binary").getFile());
        InputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        inputStream.read(data);
        ByteBuffer buffer = ByteBuffer.wrap(data);
        ClientMessageReader reader = new ClientMessageReader(0);
        while (reader.readFrom(buffer, true)) {
            clientMessages.add(reader.getClientMessage());
            reader.reset();
        }
    }

    @Test
    public void test_ClientAuthenticationCodec_encodeRequest() {
        int fileClientMessageIndex = 0;
        ClientMessage encoded = ClientAuthenticationCodec.encodeRequest(aString, null, null, null, aString, aByte, aString, aString, aListOfStrings);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAuthenticationCodec_decodeResponse() {
        int fileClientMessageIndex = 1;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientAuthenticationCodec.ResponseParameters parameters = ClientAuthenticationCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aByte, parameters.status));
        assertTrue(isEqual(null, parameters.address));
        assertTrue(isEqual(null, parameters.memberUuid));
        assertTrue(isEqual(aByte, parameters.serializationVersion));
        assertTrue(isEqual(aString, parameters.serverHazelcastVersion));
        assertTrue(isEqual(anInt, parameters.partitionCount));
        assertTrue(isEqual(aUUID, parameters.clusterId));
        assertTrue(isEqual(aBoolean, parameters.failoverSupported));
    }

    @Test
    public void test_ClientAuthenticationCustomCodec_encodeRequest() {
        int fileClientMessageIndex = 2;
        ClientMessage encoded = ClientAuthenticationCustomCodec.encodeRequest(aString, aByteArray, null, aString, aByte, aString, aString, aListOfStrings);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAuthenticationCustomCodec_decodeResponse() {
        int fileClientMessageIndex = 3;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientAuthenticationCustomCodec.ResponseParameters parameters = ClientAuthenticationCustomCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aByte, parameters.status));
        assertTrue(isEqual(null, parameters.address));
        assertTrue(isEqual(null, parameters.memberUuid));
        assertTrue(isEqual(aByte, parameters.serializationVersion));
        assertTrue(isEqual(aString, parameters.serverHazelcastVersion));
        assertTrue(isEqual(anInt, parameters.partitionCount));
        assertTrue(isEqual(aUUID, parameters.clusterId));
        assertTrue(isEqual(aBoolean, parameters.failoverSupported));
    }

    @Test
    public void test_ClientAddClusterViewListenerCodec_encodeRequest() {
        int fileClientMessageIndex = 4;
        ClientMessage encoded = ClientAddClusterViewListenerCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAddClusterViewListenerCodec_decodeResponse() {
        int fileClientMessageIndex = 5;
    }

    private static class ClientAddClusterViewListenerCodecHandler extends ClientAddClusterViewListenerCodec.AbstractEventHandler {
        @Override
        public void handleMembersViewEvent(int version, java.util.Collection<com.hazelcast.internal.cluster.MemberInfo> memberInfos) {
            assertTrue(isEqual(anInt, version));
            assertTrue(isEqual(aListOfMemberInfos, memberInfos));
        }
        @Override
        public void handlePartitionsViewEvent(int version, java.util.Collection<java.util.Map.Entry<java.util.UUID, java.util.List<java.lang.Integer>>> partitions) {
            assertTrue(isEqual(anInt, version));
            assertTrue(isEqual(aListOfUUIDToListOfIntegers, partitions));
        }
    }

    @Test
    public void test_ClientAddClusterViewListenerCodec_handleMembersViewEvent() {
        int fileClientMessageIndex = 6;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientAddClusterViewListenerCodecHandler handler = new ClientAddClusterViewListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_ClientAddClusterViewListenerCodec_handlePartitionsViewEvent() {
        int fileClientMessageIndex = 7;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientAddClusterViewListenerCodecHandler handler = new ClientAddClusterViewListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_ClientCreateProxyCodec_encodeRequest() {
        int fileClientMessageIndex = 8;
        ClientMessage encoded = ClientCreateProxyCodec.encodeRequest(aString, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientCreateProxyCodec_decodeResponse() {
        int fileClientMessageIndex = 9;
    }

    @Test
    public void test_ClientDestroyProxyCodec_encodeRequest() {
        int fileClientMessageIndex = 10;
        ClientMessage encoded = ClientDestroyProxyCodec.encodeRequest(aString, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientDestroyProxyCodec_decodeResponse() {
        int fileClientMessageIndex = 11;
    }

    @Test
    public void test_ClientAddPartitionLostListenerCodec_encodeRequest() {
        int fileClientMessageIndex = 12;
        ClientMessage encoded = ClientAddPartitionLostListenerCodec.encodeRequest(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAddPartitionLostListenerCodec_decodeResponse() {
        int fileClientMessageIndex = 13;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, ClientAddPartitionLostListenerCodec.decodeResponse(fromFile)));
    }

    private static class ClientAddPartitionLostListenerCodecHandler extends ClientAddPartitionLostListenerCodec.AbstractEventHandler {
        @Override
        public void handlePartitionLostEvent(int partitionId, int lostBackupCount, java.util.UUID source) {
            assertTrue(isEqual(anInt, partitionId));
            assertTrue(isEqual(anInt, lostBackupCount));
            assertTrue(isEqual(null, source));
        }
    }

    @Test
    public void test_ClientAddPartitionLostListenerCodec_handlePartitionLostEvent() {
        int fileClientMessageIndex = 14;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientAddPartitionLostListenerCodecHandler handler = new ClientAddPartitionLostListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_ClientRemovePartitionLostListenerCodec_encodeRequest() {
        int fileClientMessageIndex = 15;
        ClientMessage encoded = ClientRemovePartitionLostListenerCodec.encodeRequest(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientRemovePartitionLostListenerCodec_decodeResponse() {
        int fileClientMessageIndex = 16;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ClientRemovePartitionLostListenerCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ClientGetDistributedObjectsCodec_encodeRequest() {
        int fileClientMessageIndex = 17;
        ClientMessage encoded = ClientGetDistributedObjectsCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientGetDistributedObjectsCodec_decodeResponse() {
        int fileClientMessageIndex = 18;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfDistributedObjectInfo, ClientGetDistributedObjectsCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ClientAddDistributedObjectListenerCodec_encodeRequest() {
        int fileClientMessageIndex = 19;
        ClientMessage encoded = ClientAddDistributedObjectListenerCodec.encodeRequest(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAddDistributedObjectListenerCodec_decodeResponse() {
        int fileClientMessageIndex = 20;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, ClientAddDistributedObjectListenerCodec.decodeResponse(fromFile)));
    }

    private static class ClientAddDistributedObjectListenerCodecHandler extends ClientAddDistributedObjectListenerCodec.AbstractEventHandler {
        @Override
        public void handleDistributedObjectEvent(java.lang.String name, java.lang.String serviceName, java.lang.String eventType, java.util.UUID source) {
            assertTrue(isEqual(aString, name));
            assertTrue(isEqual(aString, serviceName));
            assertTrue(isEqual(aString, eventType));
            assertTrue(isEqual(aUUID, source));
        }
    }

    @Test
    public void test_ClientAddDistributedObjectListenerCodec_handleDistributedObjectEvent() {
        int fileClientMessageIndex = 21;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientAddDistributedObjectListenerCodecHandler handler = new ClientAddDistributedObjectListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_ClientRemoveDistributedObjectListenerCodec_encodeRequest() {
        int fileClientMessageIndex = 22;
        ClientMessage encoded = ClientRemoveDistributedObjectListenerCodec.encodeRequest(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientRemoveDistributedObjectListenerCodec_decodeResponse() {
        int fileClientMessageIndex = 23;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ClientRemoveDistributedObjectListenerCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ClientPingCodec_encodeRequest() {
        int fileClientMessageIndex = 24;
        ClientMessage encoded = ClientPingCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientPingCodec_decodeResponse() {
        int fileClientMessageIndex = 25;
    }

    @Test
    public void test_ClientStatisticsCodec_encodeRequest() {
        int fileClientMessageIndex = 26;
        ClientMessage encoded = ClientStatisticsCodec.encodeRequest(aLong, aString, aByteArray);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientStatisticsCodec_decodeResponse() {
        int fileClientMessageIndex = 27;
    }

    @Test
    public void test_ClientDeployClassesCodec_encodeRequest() {
        int fileClientMessageIndex = 28;
        ClientMessage encoded = ClientDeployClassesCodec.encodeRequest(aListOfStringToByteArray);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientDeployClassesCodec_decodeResponse() {
        int fileClientMessageIndex = 29;
    }

    @Test
    public void test_ClientCreateProxiesCodec_encodeRequest() {
        int fileClientMessageIndex = 30;
        ClientMessage encoded = ClientCreateProxiesCodec.encodeRequest(aListOfStringToString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientCreateProxiesCodec_decodeResponse() {
        int fileClientMessageIndex = 31;
    }

    @Test
    public void test_ClientLocalBackupListenerCodec_encodeRequest() {
        int fileClientMessageIndex = 32;
        ClientMessage encoded = ClientLocalBackupListenerCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientLocalBackupListenerCodec_decodeResponse() {
        int fileClientMessageIndex = 33;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, ClientLocalBackupListenerCodec.decodeResponse(fromFile)));
    }

    private static class ClientLocalBackupListenerCodecHandler extends ClientLocalBackupListenerCodec.AbstractEventHandler {
        @Override
        public void handleBackupEvent(long sourceInvocationCorrelationId) {
            assertTrue(isEqual(aLong, sourceInvocationCorrelationId));
        }
    }

    @Test
    public void test_ClientLocalBackupListenerCodec_handleBackupEvent() {
        int fileClientMessageIndex = 34;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientLocalBackupListenerCodecHandler handler = new ClientLocalBackupListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_ClientTriggerPartitionAssignmentCodec_encodeRequest() {
        int fileClientMessageIndex = 35;
        ClientMessage encoded = ClientTriggerPartitionAssignmentCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientTriggerPartitionAssignmentCodec_decodeResponse() {
        int fileClientMessageIndex = 36;
    }

    @Test
    public void test_ClientAddMigrationListenerCodec_encodeRequest() {
        int fileClientMessageIndex = 37;
        ClientMessage encoded = ClientAddMigrationListenerCodec.encodeRequest(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAddMigrationListenerCodec_decodeResponse() {
        int fileClientMessageIndex = 38;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, ClientAddMigrationListenerCodec.decodeResponse(fromFile)));
    }

    private static class ClientAddMigrationListenerCodecHandler extends ClientAddMigrationListenerCodec.AbstractEventHandler {
        @Override
        public void handleMigrationEvent(com.hazelcast.partition.MigrationState migrationState, int type) {
            assertTrue(isEqual(aMigrationState, migrationState));
            assertTrue(isEqual(anInt, type));
        }
        @Override
        public void handleReplicaMigrationEvent(com.hazelcast.partition.MigrationState migrationState, int partitionId, int replicaIndex, java.util.UUID sourceUuid, java.util.UUID destUuid, boolean success, long elapsedTime) {
            assertTrue(isEqual(aMigrationState, migrationState));
            assertTrue(isEqual(anInt, partitionId));
            assertTrue(isEqual(anInt, replicaIndex));
            assertTrue(isEqual(null, sourceUuid));
            assertTrue(isEqual(null, destUuid));
            assertTrue(isEqual(aBoolean, success));
            assertTrue(isEqual(aLong, elapsedTime));
        }
    }

    @Test
    public void test_ClientAddMigrationListenerCodec_handleMigrationEvent() {
        int fileClientMessageIndex = 39;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientAddMigrationListenerCodecHandler handler = new ClientAddMigrationListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_ClientAddMigrationListenerCodec_handleReplicaMigrationEvent() {
        int fileClientMessageIndex = 40;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientAddMigrationListenerCodecHandler handler = new ClientAddMigrationListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_ClientRemoveMigrationListenerCodec_encodeRequest() {
        int fileClientMessageIndex = 41;
        ClientMessage encoded = ClientRemoveMigrationListenerCodec.encodeRequest(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientRemoveMigrationListenerCodec_decodeResponse() {
        int fileClientMessageIndex = 42;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ClientRemoveMigrationListenerCodec.decodeResponse(fromFile)));
    }

    @Test
<<<<<<< HEAD
    public void test_MapPutCodec_encodeRequest() {
        int fileClientMessageIndex = 43;
=======
    public void test_ClientSendSchemaCodec_encodeRequest() {
        int fileClientMessageIndex = 43;
        ClientMessage encoded = ClientSendSchemaCodec.encodeRequest(aSchema);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientSendSchemaCodec_decodeResponse() {
        int fileClientMessageIndex = 44;
    }

    @Test
    public void test_ClientFetchSchemaCodec_encodeRequest() {
        int fileClientMessageIndex = 45;
        ClientMessage encoded = ClientFetchSchemaCodec.encodeRequest(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientFetchSchemaCodec_decodeResponse() {
        int fileClientMessageIndex = 46;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, ClientFetchSchemaCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ClientSendAllSchemasCodec_encodeRequest() {
        int fileClientMessageIndex = 47;
        ClientMessage encoded = ClientSendAllSchemasCodec.encodeRequest(aListOfSchemas);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientSendAllSchemasCodec_decodeResponse() {
        int fileClientMessageIndex = 48;
    }

    @Test
    public void test_MapPutCodec_encodeRequest() {
        int fileClientMessageIndex = 49;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapPutCodec.encodeRequest(aString, aData, aData, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapPutCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 44;
=======
        int fileClientMessageIndex = 50;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MapPutCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapGetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 45;
=======
        int fileClientMessageIndex = 51;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapGetCodec.encodeRequest(aString, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapGetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 46;
=======
        int fileClientMessageIndex = 52;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MapGetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapRemoveCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 47;
=======
        int fileClientMessageIndex = 53;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapRemoveCodec.encodeRequest(aString, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapRemoveCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 48;
=======
        int fileClientMessageIndex = 54;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MapRemoveCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapReplaceCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 49;
=======
        int fileClientMessageIndex = 55;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapReplaceCodec.encodeRequest(aString, aData, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapReplaceCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 50;
=======
        int fileClientMessageIndex = 56;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MapReplaceCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapReplaceIfSameCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 51;
=======
        int fileClientMessageIndex = 57;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapReplaceIfSameCodec.encodeRequest(aString, aData, aData, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapReplaceIfSameCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 52;
=======
        int fileClientMessageIndex = 58;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MapReplaceIfSameCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapContainsKeyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 53;
=======
        int fileClientMessageIndex = 59;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapContainsKeyCodec.encodeRequest(aString, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapContainsKeyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 54;
=======
        int fileClientMessageIndex = 60;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MapContainsKeyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapContainsValueCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 55;
=======
        int fileClientMessageIndex = 61;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapContainsValueCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapContainsValueCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 56;
=======
        int fileClientMessageIndex = 62;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MapContainsValueCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapRemoveIfSameCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 57;
=======
        int fileClientMessageIndex = 63;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapRemoveIfSameCodec.encodeRequest(aString, aData, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapRemoveIfSameCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 58;
=======
        int fileClientMessageIndex = 64;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MapRemoveIfSameCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapDeleteCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 59;
=======
        int fileClientMessageIndex = 65;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapDeleteCodec.encodeRequest(aString, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapDeleteCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 60;
=======
        int fileClientMessageIndex = 66;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MapFlushCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 61;
=======
        int fileClientMessageIndex = 67;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapFlushCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapFlushCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 62;
=======
        int fileClientMessageIndex = 68;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MapTryRemoveCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 63;
=======
        int fileClientMessageIndex = 69;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapTryRemoveCodec.encodeRequest(aString, aData, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapTryRemoveCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 64;
=======
        int fileClientMessageIndex = 70;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MapTryRemoveCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapTryPutCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 65;
=======
        int fileClientMessageIndex = 71;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapTryPutCodec.encodeRequest(aString, aData, aData, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapTryPutCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 66;
=======
        int fileClientMessageIndex = 72;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MapTryPutCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapPutTransientCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 67;
=======
        int fileClientMessageIndex = 73;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapPutTransientCodec.encodeRequest(aString, aData, aData, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapPutTransientCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 68;
=======
        int fileClientMessageIndex = 74;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MapPutIfAbsentCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 69;
=======
        int fileClientMessageIndex = 75;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapPutIfAbsentCodec.encodeRequest(aString, aData, aData, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapPutIfAbsentCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 70;
=======
        int fileClientMessageIndex = 76;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MapPutIfAbsentCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapSetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 71;
=======
        int fileClientMessageIndex = 77;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapSetCodec.encodeRequest(aString, aData, aData, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapSetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 72;
=======
        int fileClientMessageIndex = 78;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MapLockCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 73;
=======
        int fileClientMessageIndex = 79;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapLockCodec.encodeRequest(aString, aData, aLong, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapLockCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 74;
=======
        int fileClientMessageIndex = 80;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MapTryLockCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 75;
=======
        int fileClientMessageIndex = 81;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapTryLockCodec.encodeRequest(aString, aData, aLong, aLong, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapTryLockCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 76;
=======
        int fileClientMessageIndex = 82;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MapTryLockCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapIsLockedCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 77;
=======
        int fileClientMessageIndex = 83;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapIsLockedCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapIsLockedCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 78;
=======
        int fileClientMessageIndex = 84;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MapIsLockedCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapUnlockCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 79;
=======
        int fileClientMessageIndex = 85;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapUnlockCodec.encodeRequest(aString, aData, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapUnlockCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 80;
=======
        int fileClientMessageIndex = 86;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MapAddInterceptorCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 81;
=======
        int fileClientMessageIndex = 87;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddInterceptorCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddInterceptorCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 82;
=======
        int fileClientMessageIndex = 88;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MapAddInterceptorCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapRemoveInterceptorCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 83;
=======
        int fileClientMessageIndex = 89;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapRemoveInterceptorCodec.encodeRequest(aString, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapRemoveInterceptorCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 84;
=======
        int fileClientMessageIndex = 90;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MapRemoveInterceptorCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapAddEntryListenerToKeyWithPredicateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 85;
=======
        int fileClientMessageIndex = 91;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddEntryListenerToKeyWithPredicateCodec.encodeRequest(aString, aData, aData, aBoolean, anInt, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddEntryListenerToKeyWithPredicateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 86;
=======
        int fileClientMessageIndex = 92;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, MapAddEntryListenerToKeyWithPredicateCodec.decodeResponse(fromFile)));
    }

    private static class MapAddEntryListenerToKeyWithPredicateCodecHandler extends MapAddEntryListenerToKeyWithPredicateCodec.AbstractEventHandler {
        @Override
        public void handleEntryEvent(com.hazelcast.internal.serialization.Data key, com.hazelcast.internal.serialization.Data value, com.hazelcast.internal.serialization.Data oldValue, com.hazelcast.internal.serialization.Data mergingValue, int eventType, java.util.UUID uuid, int numberOfAffectedEntries) {
            assertTrue(isEqual(null, key));
            assertTrue(isEqual(null, value));
            assertTrue(isEqual(null, oldValue));
            assertTrue(isEqual(null, mergingValue));
            assertTrue(isEqual(anInt, eventType));
            assertTrue(isEqual(aUUID, uuid));
            assertTrue(isEqual(anInt, numberOfAffectedEntries));
        }
    }

    @Test
    public void test_MapAddEntryListenerToKeyWithPredicateCodec_handleEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 87;
=======
        int fileClientMessageIndex = 93;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAddEntryListenerToKeyWithPredicateCodecHandler handler = new MapAddEntryListenerToKeyWithPredicateCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_MapAddEntryListenerWithPredicateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 88;
=======
        int fileClientMessageIndex = 94;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddEntryListenerWithPredicateCodec.encodeRequest(aString, aData, aBoolean, anInt, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddEntryListenerWithPredicateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 89;
=======
        int fileClientMessageIndex = 95;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, MapAddEntryListenerWithPredicateCodec.decodeResponse(fromFile)));
    }

    private static class MapAddEntryListenerWithPredicateCodecHandler extends MapAddEntryListenerWithPredicateCodec.AbstractEventHandler {
        @Override
        public void handleEntryEvent(com.hazelcast.internal.serialization.Data key, com.hazelcast.internal.serialization.Data value, com.hazelcast.internal.serialization.Data oldValue, com.hazelcast.internal.serialization.Data mergingValue, int eventType, java.util.UUID uuid, int numberOfAffectedEntries) {
            assertTrue(isEqual(null, key));
            assertTrue(isEqual(null, value));
            assertTrue(isEqual(null, oldValue));
            assertTrue(isEqual(null, mergingValue));
            assertTrue(isEqual(anInt, eventType));
            assertTrue(isEqual(aUUID, uuid));
            assertTrue(isEqual(anInt, numberOfAffectedEntries));
        }
    }

    @Test
    public void test_MapAddEntryListenerWithPredicateCodec_handleEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 90;
=======
        int fileClientMessageIndex = 96;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAddEntryListenerWithPredicateCodecHandler handler = new MapAddEntryListenerWithPredicateCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_MapAddEntryListenerToKeyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 91;
=======
        int fileClientMessageIndex = 97;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddEntryListenerToKeyCodec.encodeRequest(aString, aData, aBoolean, anInt, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddEntryListenerToKeyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 92;
=======
        int fileClientMessageIndex = 98;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, MapAddEntryListenerToKeyCodec.decodeResponse(fromFile)));
    }

    private static class MapAddEntryListenerToKeyCodecHandler extends MapAddEntryListenerToKeyCodec.AbstractEventHandler {
        @Override
        public void handleEntryEvent(com.hazelcast.internal.serialization.Data key, com.hazelcast.internal.serialization.Data value, com.hazelcast.internal.serialization.Data oldValue, com.hazelcast.internal.serialization.Data mergingValue, int eventType, java.util.UUID uuid, int numberOfAffectedEntries) {
            assertTrue(isEqual(null, key));
            assertTrue(isEqual(null, value));
            assertTrue(isEqual(null, oldValue));
            assertTrue(isEqual(null, mergingValue));
            assertTrue(isEqual(anInt, eventType));
            assertTrue(isEqual(aUUID, uuid));
            assertTrue(isEqual(anInt, numberOfAffectedEntries));
        }
    }

    @Test
    public void test_MapAddEntryListenerToKeyCodec_handleEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 93;
=======
        int fileClientMessageIndex = 99;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAddEntryListenerToKeyCodecHandler handler = new MapAddEntryListenerToKeyCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_MapAddEntryListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 94;
=======
        int fileClientMessageIndex = 100;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddEntryListenerCodec.encodeRequest(aString, aBoolean, anInt, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddEntryListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 95;
=======
        int fileClientMessageIndex = 101;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, MapAddEntryListenerCodec.decodeResponse(fromFile)));
    }

    private static class MapAddEntryListenerCodecHandler extends MapAddEntryListenerCodec.AbstractEventHandler {
        @Override
        public void handleEntryEvent(com.hazelcast.internal.serialization.Data key, com.hazelcast.internal.serialization.Data value, com.hazelcast.internal.serialization.Data oldValue, com.hazelcast.internal.serialization.Data mergingValue, int eventType, java.util.UUID uuid, int numberOfAffectedEntries) {
            assertTrue(isEqual(null, key));
            assertTrue(isEqual(null, value));
            assertTrue(isEqual(null, oldValue));
            assertTrue(isEqual(null, mergingValue));
            assertTrue(isEqual(anInt, eventType));
            assertTrue(isEqual(aUUID, uuid));
            assertTrue(isEqual(anInt, numberOfAffectedEntries));
        }
    }

    @Test
    public void test_MapAddEntryListenerCodec_handleEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 96;
=======
        int fileClientMessageIndex = 102;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAddEntryListenerCodecHandler handler = new MapAddEntryListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_MapRemoveEntryListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 97;
=======
        int fileClientMessageIndex = 103;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapRemoveEntryListenerCodec.encodeRequest(aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapRemoveEntryListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 98;
=======
        int fileClientMessageIndex = 104;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MapRemoveEntryListenerCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapAddPartitionLostListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 99;
=======
        int fileClientMessageIndex = 105;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddPartitionLostListenerCodec.encodeRequest(aString, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddPartitionLostListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 100;
=======
        int fileClientMessageIndex = 106;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, MapAddPartitionLostListenerCodec.decodeResponse(fromFile)));
    }

    private static class MapAddPartitionLostListenerCodecHandler extends MapAddPartitionLostListenerCodec.AbstractEventHandler {
        @Override
        public void handleMapPartitionLostEvent(int partitionId, java.util.UUID uuid) {
            assertTrue(isEqual(anInt, partitionId));
            assertTrue(isEqual(aUUID, uuid));
        }
    }

    @Test
    public void test_MapAddPartitionLostListenerCodec_handleMapPartitionLostEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 101;
=======
        int fileClientMessageIndex = 107;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAddPartitionLostListenerCodecHandler handler = new MapAddPartitionLostListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_MapRemovePartitionLostListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 102;
=======
        int fileClientMessageIndex = 108;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapRemovePartitionLostListenerCodec.encodeRequest(aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapRemovePartitionLostListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 103;
=======
        int fileClientMessageIndex = 109;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MapRemovePartitionLostListenerCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapGetEntryViewCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 104;
=======
        int fileClientMessageIndex = 110;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapGetEntryViewCodec.encodeRequest(aString, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapGetEntryViewCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 105;
=======
        int fileClientMessageIndex = 111;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapGetEntryViewCodec.ResponseParameters parameters = MapGetEntryViewCodec.decodeResponse(fromFile);
        assertTrue(isEqual(null, parameters.response));
        assertTrue(isEqual(aLong, parameters.maxIdle));
    }

    @Test
    public void test_MapEvictCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 106;
=======
        int fileClientMessageIndex = 112;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapEvictCodec.encodeRequest(aString, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapEvictCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 107;
=======
        int fileClientMessageIndex = 113;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MapEvictCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapEvictAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 108;
=======
        int fileClientMessageIndex = 114;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapEvictAllCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapEvictAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 109;
=======
        int fileClientMessageIndex = 115;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MapLoadAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 110;
=======
        int fileClientMessageIndex = 116;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapLoadAllCodec.encodeRequest(aString, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapLoadAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 111;
=======
        int fileClientMessageIndex = 117;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MapLoadGivenKeysCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 112;
=======
        int fileClientMessageIndex = 118;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapLoadGivenKeysCodec.encodeRequest(aString, aListOfData, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapLoadGivenKeysCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 113;
=======
        int fileClientMessageIndex = 119;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MapKeySetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 114;
=======
        int fileClientMessageIndex = 120;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapKeySetCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapKeySetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 115;
=======
        int fileClientMessageIndex = 121;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, MapKeySetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapGetAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 116;
=======
        int fileClientMessageIndex = 122;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapGetAllCodec.encodeRequest(aString, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapGetAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 117;
=======
        int fileClientMessageIndex = 123;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfDataToData, MapGetAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapValuesCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 118;
=======
        int fileClientMessageIndex = 124;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapValuesCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapValuesCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 119;
=======
        int fileClientMessageIndex = 125;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, MapValuesCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapEntrySetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 120;
=======
        int fileClientMessageIndex = 126;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapEntrySetCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapEntrySetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 121;
=======
        int fileClientMessageIndex = 127;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfDataToData, MapEntrySetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapKeySetWithPredicateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 122;
=======
        int fileClientMessageIndex = 128;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapKeySetWithPredicateCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapKeySetWithPredicateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 123;
=======
        int fileClientMessageIndex = 129;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, MapKeySetWithPredicateCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapValuesWithPredicateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 124;
=======
        int fileClientMessageIndex = 130;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapValuesWithPredicateCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapValuesWithPredicateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 125;
=======
        int fileClientMessageIndex = 131;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, MapValuesWithPredicateCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapEntriesWithPredicateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 126;
=======
        int fileClientMessageIndex = 132;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapEntriesWithPredicateCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapEntriesWithPredicateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 127;
=======
        int fileClientMessageIndex = 133;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfDataToData, MapEntriesWithPredicateCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapAddIndexCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 128;
=======
        int fileClientMessageIndex = 134;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddIndexCodec.encodeRequest(aString, anIndexConfig);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddIndexCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 129;
=======
        int fileClientMessageIndex = 135;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MapSizeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 130;
=======
        int fileClientMessageIndex = 136;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapSizeCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapSizeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 131;
=======
        int fileClientMessageIndex = 137;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, MapSizeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapIsEmptyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 132;
=======
        int fileClientMessageIndex = 138;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapIsEmptyCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapIsEmptyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 133;
=======
        int fileClientMessageIndex = 139;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MapIsEmptyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapPutAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 134;
=======
        int fileClientMessageIndex = 140;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapPutAllCodec.encodeRequest(aString, aListOfDataToData, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapPutAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 135;
=======
        int fileClientMessageIndex = 141;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MapClearCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 136;
=======
        int fileClientMessageIndex = 142;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapClearCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapClearCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 137;
=======
        int fileClientMessageIndex = 143;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MapExecuteOnKeyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 138;
=======
        int fileClientMessageIndex = 144;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapExecuteOnKeyCodec.encodeRequest(aString, aData, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapExecuteOnKeyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 139;
=======
        int fileClientMessageIndex = 145;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MapExecuteOnKeyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapSubmitToKeyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 140;
=======
        int fileClientMessageIndex = 146;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapSubmitToKeyCodec.encodeRequest(aString, aData, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapSubmitToKeyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 141;
=======
        int fileClientMessageIndex = 147;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MapSubmitToKeyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapExecuteOnAllKeysCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 142;
=======
        int fileClientMessageIndex = 148;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapExecuteOnAllKeysCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapExecuteOnAllKeysCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 143;
=======
        int fileClientMessageIndex = 149;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfDataToData, MapExecuteOnAllKeysCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapExecuteWithPredicateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 144;
=======
        int fileClientMessageIndex = 150;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapExecuteWithPredicateCodec.encodeRequest(aString, aData, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapExecuteWithPredicateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 145;
=======
        int fileClientMessageIndex = 151;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfDataToData, MapExecuteWithPredicateCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapExecuteOnKeysCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 146;
=======
        int fileClientMessageIndex = 152;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapExecuteOnKeysCodec.encodeRequest(aString, aData, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapExecuteOnKeysCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 147;
=======
        int fileClientMessageIndex = 153;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfDataToData, MapExecuteOnKeysCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapForceUnlockCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 148;
=======
        int fileClientMessageIndex = 154;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapForceUnlockCodec.encodeRequest(aString, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapForceUnlockCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 149;
=======
        int fileClientMessageIndex = 155;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MapKeySetWithPagingPredicateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 150;
=======
        int fileClientMessageIndex = 156;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapKeySetWithPagingPredicateCodec.encodeRequest(aString, aPagingPredicateHolder);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapKeySetWithPagingPredicateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 151;
=======
        int fileClientMessageIndex = 157;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapKeySetWithPagingPredicateCodec.ResponseParameters parameters = MapKeySetWithPagingPredicateCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aListOfData, parameters.response));
        assertTrue(isEqual(anAnchorDataListHolder, parameters.anchorDataList));
    }

    @Test
    public void test_MapValuesWithPagingPredicateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 152;
=======
        int fileClientMessageIndex = 158;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapValuesWithPagingPredicateCodec.encodeRequest(aString, aPagingPredicateHolder);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapValuesWithPagingPredicateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 153;
=======
        int fileClientMessageIndex = 159;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapValuesWithPagingPredicateCodec.ResponseParameters parameters = MapValuesWithPagingPredicateCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aListOfData, parameters.response));
        assertTrue(isEqual(anAnchorDataListHolder, parameters.anchorDataList));
    }

    @Test
    public void test_MapEntriesWithPagingPredicateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 154;
=======
        int fileClientMessageIndex = 160;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapEntriesWithPagingPredicateCodec.encodeRequest(aString, aPagingPredicateHolder);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapEntriesWithPagingPredicateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 155;
=======
        int fileClientMessageIndex = 161;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapEntriesWithPagingPredicateCodec.ResponseParameters parameters = MapEntriesWithPagingPredicateCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aListOfDataToData, parameters.response));
        assertTrue(isEqual(anAnchorDataListHolder, parameters.anchorDataList));
    }

    @Test
    public void test_MapFetchKeysCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 156;
=======
        int fileClientMessageIndex = 162;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapFetchKeysCodec.encodeRequest(aString, aListOfIntegerToInteger, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapFetchKeysCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 157;
=======
        int fileClientMessageIndex = 163;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapFetchKeysCodec.ResponseParameters parameters = MapFetchKeysCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aListOfIntegerToInteger, parameters.iterationPointers));
        assertTrue(isEqual(aListOfData, parameters.keys));
    }

    @Test
    public void test_MapFetchEntriesCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 158;
=======
        int fileClientMessageIndex = 164;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapFetchEntriesCodec.encodeRequest(aString, aListOfIntegerToInteger, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapFetchEntriesCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 159;
=======
        int fileClientMessageIndex = 165;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapFetchEntriesCodec.ResponseParameters parameters = MapFetchEntriesCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aListOfIntegerToInteger, parameters.iterationPointers));
        assertTrue(isEqual(aListOfDataToData, parameters.entries));
    }

    @Test
    public void test_MapAggregateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 160;
=======
        int fileClientMessageIndex = 166;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAggregateCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAggregateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 161;
=======
        int fileClientMessageIndex = 167;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MapAggregateCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapAggregateWithPredicateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 162;
=======
        int fileClientMessageIndex = 168;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAggregateWithPredicateCodec.encodeRequest(aString, aData, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAggregateWithPredicateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 163;
=======
        int fileClientMessageIndex = 169;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MapAggregateWithPredicateCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapProjectCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 164;
=======
        int fileClientMessageIndex = 170;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapProjectCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapProjectCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 165;
=======
        int fileClientMessageIndex = 171;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, MapProjectCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapProjectWithPredicateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 166;
=======
        int fileClientMessageIndex = 172;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapProjectWithPredicateCodec.encodeRequest(aString, aData, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapProjectWithPredicateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 167;
=======
        int fileClientMessageIndex = 173;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, MapProjectWithPredicateCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapFetchNearCacheInvalidationMetadataCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 168;
=======
        int fileClientMessageIndex = 174;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapFetchNearCacheInvalidationMetadataCodec.encodeRequest(aListOfStrings, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapFetchNearCacheInvalidationMetadataCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 169;
=======
        int fileClientMessageIndex = 175;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapFetchNearCacheInvalidationMetadataCodec.ResponseParameters parameters = MapFetchNearCacheInvalidationMetadataCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aListOfStringToListOfIntegerToLong, parameters.namePartitionSequenceList));
        assertTrue(isEqual(aListOfIntegerToUUID, parameters.partitionUuidList));
    }

    @Test
    public void test_MapRemoveAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 170;
=======
        int fileClientMessageIndex = 176;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapRemoveAllCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapRemoveAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 171;
=======
        int fileClientMessageIndex = 177;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MapAddNearCacheInvalidationListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 172;
=======
        int fileClientMessageIndex = 178;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddNearCacheInvalidationListenerCodec.encodeRequest(aString, anInt, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddNearCacheInvalidationListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 173;
=======
        int fileClientMessageIndex = 179;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, MapAddNearCacheInvalidationListenerCodec.decodeResponse(fromFile)));
    }

    private static class MapAddNearCacheInvalidationListenerCodecHandler extends MapAddNearCacheInvalidationListenerCodec.AbstractEventHandler {
        @Override
        public void handleIMapInvalidationEvent(com.hazelcast.internal.serialization.Data key, java.util.UUID sourceUuid, java.util.UUID partitionUuid, long sequence) {
            assertTrue(isEqual(null, key));
            assertTrue(isEqual(aUUID, sourceUuid));
            assertTrue(isEqual(aUUID, partitionUuid));
            assertTrue(isEqual(aLong, sequence));
        }
        @Override
        public void handleIMapBatchInvalidationEvent(java.util.Collection<com.hazelcast.internal.serialization.Data> keys, java.util.Collection<java.util.UUID> sourceUuids, java.util.Collection<java.util.UUID> partitionUuids, java.util.Collection<java.lang.Long> sequences) {
            assertTrue(isEqual(aListOfData, keys));
            assertTrue(isEqual(aListOfUUIDs, sourceUuids));
            assertTrue(isEqual(aListOfUUIDs, partitionUuids));
            assertTrue(isEqual(aListOfLongs, sequences));
        }
    }

    @Test
    public void test_MapAddNearCacheInvalidationListenerCodec_handleIMapInvalidationEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 174;
=======
        int fileClientMessageIndex = 180;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAddNearCacheInvalidationListenerCodecHandler handler = new MapAddNearCacheInvalidationListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_MapAddNearCacheInvalidationListenerCodec_handleIMapBatchInvalidationEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 175;
=======
        int fileClientMessageIndex = 181;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAddNearCacheInvalidationListenerCodecHandler handler = new MapAddNearCacheInvalidationListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_MapFetchWithQueryCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 176;
=======
        int fileClientMessageIndex = 182;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapFetchWithQueryCodec.encodeRequest(aString, aListOfIntegerToInteger, anInt, aData, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapFetchWithQueryCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 177;
=======
        int fileClientMessageIndex = 183;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapFetchWithQueryCodec.ResponseParameters parameters = MapFetchWithQueryCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aListOfData, parameters.results));
        assertTrue(isEqual(aListOfIntegerToInteger, parameters.iterationPointers));
    }

    @Test
    public void test_MapEventJournalSubscribeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 178;
=======
        int fileClientMessageIndex = 184;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapEventJournalSubscribeCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapEventJournalSubscribeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 179;
=======
        int fileClientMessageIndex = 185;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapEventJournalSubscribeCodec.ResponseParameters parameters = MapEventJournalSubscribeCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aLong, parameters.oldestSequence));
        assertTrue(isEqual(aLong, parameters.newestSequence));
    }

    @Test
    public void test_MapEventJournalReadCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 180;
=======
        int fileClientMessageIndex = 186;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapEventJournalReadCodec.encodeRequest(aString, aLong, anInt, anInt, null, null);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapEventJournalReadCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 181;
=======
        int fileClientMessageIndex = 187;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapEventJournalReadCodec.ResponseParameters parameters = MapEventJournalReadCodec.decodeResponse(fromFile);
        assertTrue(isEqual(anInt, parameters.readCount));
        assertTrue(isEqual(aListOfData, parameters.items));
        assertTrue(isEqual(null, parameters.itemSeqs));
        assertTrue(isEqual(aLong, parameters.nextSeq));
    }

    @Test
    public void test_MapSetTtlCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 182;
=======
        int fileClientMessageIndex = 188;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapSetTtlCodec.encodeRequest(aString, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapSetTtlCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 183;
=======
        int fileClientMessageIndex = 189;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MapSetTtlCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapPutWithMaxIdleCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 184;
=======
        int fileClientMessageIndex = 190;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapPutWithMaxIdleCodec.encodeRequest(aString, aData, aData, aLong, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapPutWithMaxIdleCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 185;
=======
        int fileClientMessageIndex = 191;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MapPutWithMaxIdleCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapPutTransientWithMaxIdleCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 186;
=======
        int fileClientMessageIndex = 192;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapPutTransientWithMaxIdleCodec.encodeRequest(aString, aData, aData, aLong, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapPutTransientWithMaxIdleCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 187;
=======
        int fileClientMessageIndex = 193;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MapPutTransientWithMaxIdleCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapPutIfAbsentWithMaxIdleCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 188;
=======
        int fileClientMessageIndex = 194;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapPutIfAbsentWithMaxIdleCodec.encodeRequest(aString, aData, aData, aLong, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapPutIfAbsentWithMaxIdleCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 189;
=======
        int fileClientMessageIndex = 195;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MapPutIfAbsentWithMaxIdleCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MapSetWithMaxIdleCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 190;
=======
        int fileClientMessageIndex = 196;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapSetWithMaxIdleCodec.encodeRequest(aString, aData, aData, aLong, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapSetWithMaxIdleCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 191;
=======
        int fileClientMessageIndex = 197;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MapSetWithMaxIdleCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapPutCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 192;
=======
        int fileClientMessageIndex = 198;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapPutCodec.encodeRequest(aString, aData, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapPutCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 193;
=======
        int fileClientMessageIndex = 199;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MultiMapPutCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapGetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 194;
=======
        int fileClientMessageIndex = 200;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapGetCodec.encodeRequest(aString, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapGetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 195;
=======
        int fileClientMessageIndex = 201;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, MultiMapGetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapRemoveCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 196;
=======
        int fileClientMessageIndex = 202;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapRemoveCodec.encodeRequest(aString, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapRemoveCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 197;
=======
        int fileClientMessageIndex = 203;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, MultiMapRemoveCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapKeySetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 198;
=======
        int fileClientMessageIndex = 204;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapKeySetCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapKeySetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 199;
=======
        int fileClientMessageIndex = 205;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, MultiMapKeySetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapValuesCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 200;
=======
        int fileClientMessageIndex = 206;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapValuesCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapValuesCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 201;
=======
        int fileClientMessageIndex = 207;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, MultiMapValuesCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapEntrySetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 202;
=======
        int fileClientMessageIndex = 208;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapEntrySetCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapEntrySetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 203;
=======
        int fileClientMessageIndex = 209;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfDataToData, MultiMapEntrySetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapContainsKeyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 204;
=======
        int fileClientMessageIndex = 210;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapContainsKeyCodec.encodeRequest(aString, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapContainsKeyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 205;
=======
        int fileClientMessageIndex = 211;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MultiMapContainsKeyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapContainsValueCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 206;
=======
        int fileClientMessageIndex = 212;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapContainsValueCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapContainsValueCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 207;
=======
        int fileClientMessageIndex = 213;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MultiMapContainsValueCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapContainsEntryCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 208;
=======
        int fileClientMessageIndex = 214;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapContainsEntryCodec.encodeRequest(aString, aData, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapContainsEntryCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 209;
=======
        int fileClientMessageIndex = 215;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MultiMapContainsEntryCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapSizeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 210;
=======
        int fileClientMessageIndex = 216;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapSizeCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapSizeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 211;
=======
        int fileClientMessageIndex = 217;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, MultiMapSizeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapClearCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 212;
=======
        int fileClientMessageIndex = 218;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapClearCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapClearCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 213;
=======
        int fileClientMessageIndex = 219;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MultiMapValueCountCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 214;
=======
        int fileClientMessageIndex = 220;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapValueCountCodec.encodeRequest(aString, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapValueCountCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 215;
=======
        int fileClientMessageIndex = 221;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, MultiMapValueCountCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapAddEntryListenerToKeyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 216;
=======
        int fileClientMessageIndex = 222;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapAddEntryListenerToKeyCodec.encodeRequest(aString, aData, aBoolean, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapAddEntryListenerToKeyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 217;
=======
        int fileClientMessageIndex = 223;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, MultiMapAddEntryListenerToKeyCodec.decodeResponse(fromFile)));
    }

    private static class MultiMapAddEntryListenerToKeyCodecHandler extends MultiMapAddEntryListenerToKeyCodec.AbstractEventHandler {
        @Override
        public void handleEntryEvent(com.hazelcast.internal.serialization.Data key, com.hazelcast.internal.serialization.Data value, com.hazelcast.internal.serialization.Data oldValue, com.hazelcast.internal.serialization.Data mergingValue, int eventType, java.util.UUID uuid, int numberOfAffectedEntries) {
            assertTrue(isEqual(null, key));
            assertTrue(isEqual(null, value));
            assertTrue(isEqual(null, oldValue));
            assertTrue(isEqual(null, mergingValue));
            assertTrue(isEqual(anInt, eventType));
            assertTrue(isEqual(aUUID, uuid));
            assertTrue(isEqual(anInt, numberOfAffectedEntries));
        }
    }

    @Test
    public void test_MultiMapAddEntryListenerToKeyCodec_handleEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 218;
=======
        int fileClientMessageIndex = 224;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapAddEntryListenerToKeyCodecHandler handler = new MultiMapAddEntryListenerToKeyCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_MultiMapAddEntryListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 219;
=======
        int fileClientMessageIndex = 225;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapAddEntryListenerCodec.encodeRequest(aString, aBoolean, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapAddEntryListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 220;
=======
        int fileClientMessageIndex = 226;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, MultiMapAddEntryListenerCodec.decodeResponse(fromFile)));
    }

    private static class MultiMapAddEntryListenerCodecHandler extends MultiMapAddEntryListenerCodec.AbstractEventHandler {
        @Override
        public void handleEntryEvent(com.hazelcast.internal.serialization.Data key, com.hazelcast.internal.serialization.Data value, com.hazelcast.internal.serialization.Data oldValue, com.hazelcast.internal.serialization.Data mergingValue, int eventType, java.util.UUID uuid, int numberOfAffectedEntries) {
            assertTrue(isEqual(null, key));
            assertTrue(isEqual(null, value));
            assertTrue(isEqual(null, oldValue));
            assertTrue(isEqual(null, mergingValue));
            assertTrue(isEqual(anInt, eventType));
            assertTrue(isEqual(aUUID, uuid));
            assertTrue(isEqual(anInt, numberOfAffectedEntries));
        }
    }

    @Test
    public void test_MultiMapAddEntryListenerCodec_handleEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 221;
=======
        int fileClientMessageIndex = 227;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapAddEntryListenerCodecHandler handler = new MultiMapAddEntryListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_MultiMapRemoveEntryListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 222;
=======
        int fileClientMessageIndex = 228;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapRemoveEntryListenerCodec.encodeRequest(aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapRemoveEntryListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 223;
=======
        int fileClientMessageIndex = 229;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MultiMapRemoveEntryListenerCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapLockCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 224;
=======
        int fileClientMessageIndex = 230;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapLockCodec.encodeRequest(aString, aData, aLong, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapLockCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 225;
=======
        int fileClientMessageIndex = 231;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MultiMapTryLockCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 226;
=======
        int fileClientMessageIndex = 232;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapTryLockCodec.encodeRequest(aString, aData, aLong, aLong, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapTryLockCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 227;
=======
        int fileClientMessageIndex = 233;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MultiMapTryLockCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapIsLockedCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 228;
=======
        int fileClientMessageIndex = 234;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapIsLockedCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapIsLockedCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 229;
=======
        int fileClientMessageIndex = 235;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MultiMapIsLockedCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapUnlockCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 230;
=======
        int fileClientMessageIndex = 236;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapUnlockCodec.encodeRequest(aString, aData, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapUnlockCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 231;
=======
        int fileClientMessageIndex = 237;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MultiMapForceUnlockCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 232;
=======
        int fileClientMessageIndex = 238;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapForceUnlockCodec.encodeRequest(aString, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapForceUnlockCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 233;
=======
        int fileClientMessageIndex = 239;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MultiMapRemoveEntryCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 234;
=======
        int fileClientMessageIndex = 240;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapRemoveEntryCodec.encodeRequest(aString, aData, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapRemoveEntryCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 235;
=======
        int fileClientMessageIndex = 241;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MultiMapRemoveEntryCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MultiMapDeleteCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 236;
=======
        int fileClientMessageIndex = 242;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapDeleteCodec.encodeRequest(aString, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapDeleteCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 237;
=======
        int fileClientMessageIndex = 243;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MultiMapPutAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 238;
=======
        int fileClientMessageIndex = 244;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapPutAllCodec.encodeRequest(aString, aListOfDataToListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapPutAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 239;
=======
        int fileClientMessageIndex = 245;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_QueueOfferCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 240;
=======
        int fileClientMessageIndex = 246;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueOfferCodec.encodeRequest(aString, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueOfferCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 241;
=======
        int fileClientMessageIndex = 247;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, QueueOfferCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueuePutCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 242;
=======
        int fileClientMessageIndex = 248;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueuePutCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueuePutCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 243;
=======
        int fileClientMessageIndex = 249;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_QueueSizeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 244;
=======
        int fileClientMessageIndex = 250;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueSizeCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueSizeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 245;
=======
        int fileClientMessageIndex = 251;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, QueueSizeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueueRemoveCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 246;
=======
        int fileClientMessageIndex = 252;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueRemoveCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueRemoveCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 247;
=======
        int fileClientMessageIndex = 253;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, QueueRemoveCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueuePollCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 248;
=======
        int fileClientMessageIndex = 254;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueuePollCodec.encodeRequest(aString, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueuePollCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 249;
=======
        int fileClientMessageIndex = 255;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, QueuePollCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueueTakeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 250;
=======
        int fileClientMessageIndex = 256;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueTakeCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueTakeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 251;
=======
        int fileClientMessageIndex = 257;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, QueueTakeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueuePeekCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 252;
=======
        int fileClientMessageIndex = 258;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueuePeekCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueuePeekCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 253;
=======
        int fileClientMessageIndex = 259;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, QueuePeekCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueueIteratorCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 254;
=======
        int fileClientMessageIndex = 260;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueIteratorCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueIteratorCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 255;
=======
        int fileClientMessageIndex = 261;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, QueueIteratorCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueueDrainToCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 256;
=======
        int fileClientMessageIndex = 262;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueDrainToCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueDrainToCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 257;
=======
        int fileClientMessageIndex = 263;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, QueueDrainToCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueueDrainToMaxSizeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 258;
=======
        int fileClientMessageIndex = 264;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueDrainToMaxSizeCodec.encodeRequest(aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueDrainToMaxSizeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 259;
=======
        int fileClientMessageIndex = 265;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, QueueDrainToMaxSizeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueueContainsCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 260;
=======
        int fileClientMessageIndex = 266;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueContainsCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueContainsCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 261;
=======
        int fileClientMessageIndex = 267;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, QueueContainsCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueueContainsAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 262;
=======
        int fileClientMessageIndex = 268;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueContainsAllCodec.encodeRequest(aString, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueContainsAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 263;
=======
        int fileClientMessageIndex = 269;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, QueueContainsAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueueCompareAndRemoveAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 264;
=======
        int fileClientMessageIndex = 270;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueCompareAndRemoveAllCodec.encodeRequest(aString, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueCompareAndRemoveAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 265;
=======
        int fileClientMessageIndex = 271;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, QueueCompareAndRemoveAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueueCompareAndRetainAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 266;
=======
        int fileClientMessageIndex = 272;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueCompareAndRetainAllCodec.encodeRequest(aString, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueCompareAndRetainAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 267;
=======
        int fileClientMessageIndex = 273;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, QueueCompareAndRetainAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueueClearCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 268;
=======
        int fileClientMessageIndex = 274;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueClearCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueClearCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 269;
=======
        int fileClientMessageIndex = 275;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_QueueAddAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 270;
=======
        int fileClientMessageIndex = 276;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueAddAllCodec.encodeRequest(aString, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueAddAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 271;
=======
        int fileClientMessageIndex = 277;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, QueueAddAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueueAddListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 272;
=======
        int fileClientMessageIndex = 278;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueAddListenerCodec.encodeRequest(aString, aBoolean, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueAddListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 273;
=======
        int fileClientMessageIndex = 279;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, QueueAddListenerCodec.decodeResponse(fromFile)));
    }

    private static class QueueAddListenerCodecHandler extends QueueAddListenerCodec.AbstractEventHandler {
        @Override
        public void handleItemEvent(com.hazelcast.internal.serialization.Data item, java.util.UUID uuid, int eventType) {
            assertTrue(isEqual(null, item));
            assertTrue(isEqual(aUUID, uuid));
            assertTrue(isEqual(anInt, eventType));
        }
    }

    @Test
    public void test_QueueAddListenerCodec_handleItemEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 274;
=======
        int fileClientMessageIndex = 280;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        QueueAddListenerCodecHandler handler = new QueueAddListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_QueueRemoveListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 275;
=======
        int fileClientMessageIndex = 281;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueRemoveListenerCodec.encodeRequest(aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueRemoveListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 276;
=======
        int fileClientMessageIndex = 282;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, QueueRemoveListenerCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueueRemainingCapacityCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 277;
=======
        int fileClientMessageIndex = 283;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueRemainingCapacityCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueRemainingCapacityCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 278;
=======
        int fileClientMessageIndex = 284;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, QueueRemainingCapacityCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_QueueIsEmptyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 279;
=======
        int fileClientMessageIndex = 285;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueIsEmptyCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueIsEmptyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 280;
=======
        int fileClientMessageIndex = 286;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, QueueIsEmptyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TopicPublishCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 281;
=======
        int fileClientMessageIndex = 287;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TopicPublishCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TopicPublishCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 282;
=======
        int fileClientMessageIndex = 288;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_TopicAddMessageListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 283;
=======
        int fileClientMessageIndex = 289;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TopicAddMessageListenerCodec.encodeRequest(aString, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TopicAddMessageListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 284;
=======
        int fileClientMessageIndex = 290;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, TopicAddMessageListenerCodec.decodeResponse(fromFile)));
    }

    private static class TopicAddMessageListenerCodecHandler extends TopicAddMessageListenerCodec.AbstractEventHandler {
        @Override
        public void handleTopicEvent(com.hazelcast.internal.serialization.Data item, long publishTime, java.util.UUID uuid) {
            assertTrue(isEqual(aData, item));
            assertTrue(isEqual(aLong, publishTime));
            assertTrue(isEqual(aUUID, uuid));
        }
    }

    @Test
    public void test_TopicAddMessageListenerCodec_handleTopicEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 285;
=======
        int fileClientMessageIndex = 291;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TopicAddMessageListenerCodecHandler handler = new TopicAddMessageListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_TopicRemoveMessageListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 286;
=======
        int fileClientMessageIndex = 292;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TopicRemoveMessageListenerCodec.encodeRequest(aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TopicRemoveMessageListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 287;
=======
        int fileClientMessageIndex = 293;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, TopicRemoveMessageListenerCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TopicPublishAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 288;
=======
        int fileClientMessageIndex = 294;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TopicPublishAllCodec.encodeRequest(aString, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TopicPublishAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 289;
=======
        int fileClientMessageIndex = 295;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_ListSizeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 290;
=======
        int fileClientMessageIndex = 296;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListSizeCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListSizeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 291;
=======
        int fileClientMessageIndex = 297;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, ListSizeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListContainsCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 292;
=======
        int fileClientMessageIndex = 298;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListContainsCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListContainsCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 293;
=======
        int fileClientMessageIndex = 299;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ListContainsCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListContainsAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 294;
=======
        int fileClientMessageIndex = 300;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListContainsAllCodec.encodeRequest(aString, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListContainsAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 295;
=======
        int fileClientMessageIndex = 301;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ListContainsAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListAddCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 296;
=======
        int fileClientMessageIndex = 302;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListAddCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListAddCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 297;
=======
        int fileClientMessageIndex = 303;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ListAddCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListRemoveCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 298;
=======
        int fileClientMessageIndex = 304;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListRemoveCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListRemoveCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 299;
=======
        int fileClientMessageIndex = 305;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ListRemoveCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListAddAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 300;
=======
        int fileClientMessageIndex = 306;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListAddAllCodec.encodeRequest(aString, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListAddAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 301;
=======
        int fileClientMessageIndex = 307;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ListAddAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListCompareAndRemoveAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 302;
=======
        int fileClientMessageIndex = 308;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListCompareAndRemoveAllCodec.encodeRequest(aString, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListCompareAndRemoveAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 303;
=======
        int fileClientMessageIndex = 309;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ListCompareAndRemoveAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListCompareAndRetainAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 304;
=======
        int fileClientMessageIndex = 310;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListCompareAndRetainAllCodec.encodeRequest(aString, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListCompareAndRetainAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 305;
=======
        int fileClientMessageIndex = 311;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ListCompareAndRetainAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListClearCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 306;
=======
        int fileClientMessageIndex = 312;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListClearCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListClearCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 307;
=======
        int fileClientMessageIndex = 313;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_ListGetAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 308;
=======
        int fileClientMessageIndex = 314;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListGetAllCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListGetAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 309;
=======
        int fileClientMessageIndex = 315;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, ListGetAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListAddListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 310;
=======
        int fileClientMessageIndex = 316;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListAddListenerCodec.encodeRequest(aString, aBoolean, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListAddListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 311;
=======
        int fileClientMessageIndex = 317;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, ListAddListenerCodec.decodeResponse(fromFile)));
    }

    private static class ListAddListenerCodecHandler extends ListAddListenerCodec.AbstractEventHandler {
        @Override
        public void handleItemEvent(com.hazelcast.internal.serialization.Data item, java.util.UUID uuid, int eventType) {
            assertTrue(isEqual(null, item));
            assertTrue(isEqual(aUUID, uuid));
            assertTrue(isEqual(anInt, eventType));
        }
    }

    @Test
    public void test_ListAddListenerCodec_handleItemEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 312;
=======
        int fileClientMessageIndex = 318;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListAddListenerCodecHandler handler = new ListAddListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_ListRemoveListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 313;
=======
        int fileClientMessageIndex = 319;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListRemoveListenerCodec.encodeRequest(aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListRemoveListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 314;
=======
        int fileClientMessageIndex = 320;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ListRemoveListenerCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListIsEmptyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 315;
=======
        int fileClientMessageIndex = 321;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListIsEmptyCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListIsEmptyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 316;
=======
        int fileClientMessageIndex = 322;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ListIsEmptyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListAddAllWithIndexCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 317;
=======
        int fileClientMessageIndex = 323;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListAddAllWithIndexCodec.encodeRequest(aString, anInt, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListAddAllWithIndexCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 318;
=======
        int fileClientMessageIndex = 324;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ListAddAllWithIndexCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListGetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 319;
=======
        int fileClientMessageIndex = 325;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListGetCodec.encodeRequest(aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListGetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 320;
=======
        int fileClientMessageIndex = 326;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, ListGetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListSetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 321;
=======
        int fileClientMessageIndex = 327;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListSetCodec.encodeRequest(aString, anInt, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListSetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 322;
=======
        int fileClientMessageIndex = 328;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, ListSetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListAddWithIndexCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 323;
=======
        int fileClientMessageIndex = 329;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListAddWithIndexCodec.encodeRequest(aString, anInt, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListAddWithIndexCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 324;
=======
        int fileClientMessageIndex = 330;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_ListRemoveWithIndexCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 325;
=======
        int fileClientMessageIndex = 331;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListRemoveWithIndexCodec.encodeRequest(aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListRemoveWithIndexCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 326;
=======
        int fileClientMessageIndex = 332;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, ListRemoveWithIndexCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListLastIndexOfCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 327;
=======
        int fileClientMessageIndex = 333;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListLastIndexOfCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListLastIndexOfCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 328;
=======
        int fileClientMessageIndex = 334;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, ListLastIndexOfCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListIndexOfCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 329;
=======
        int fileClientMessageIndex = 335;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListIndexOfCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListIndexOfCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 330;
=======
        int fileClientMessageIndex = 336;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, ListIndexOfCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListSubCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 331;
=======
        int fileClientMessageIndex = 337;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListSubCodec.encodeRequest(aString, anInt, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListSubCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 332;
=======
        int fileClientMessageIndex = 338;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, ListSubCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListIteratorCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 333;
=======
        int fileClientMessageIndex = 339;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListIteratorCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListIteratorCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 334;
=======
        int fileClientMessageIndex = 340;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, ListIteratorCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ListListIteratorCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 335;
=======
        int fileClientMessageIndex = 341;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListListIteratorCodec.encodeRequest(aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListListIteratorCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 336;
=======
        int fileClientMessageIndex = 342;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, ListListIteratorCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SetSizeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 337;
=======
        int fileClientMessageIndex = 343;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetSizeCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetSizeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 338;
=======
        int fileClientMessageIndex = 344;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, SetSizeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SetContainsCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 339;
=======
        int fileClientMessageIndex = 345;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetContainsCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetContainsCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 340;
=======
        int fileClientMessageIndex = 346;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, SetContainsCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SetContainsAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 341;
=======
        int fileClientMessageIndex = 347;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetContainsAllCodec.encodeRequest(aString, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetContainsAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 342;
=======
        int fileClientMessageIndex = 348;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, SetContainsAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SetAddCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 343;
=======
        int fileClientMessageIndex = 349;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetAddCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetAddCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 344;
=======
        int fileClientMessageIndex = 350;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, SetAddCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SetRemoveCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 345;
=======
        int fileClientMessageIndex = 351;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetRemoveCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetRemoveCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 346;
=======
        int fileClientMessageIndex = 352;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, SetRemoveCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SetAddAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 347;
=======
        int fileClientMessageIndex = 353;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetAddAllCodec.encodeRequest(aString, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetAddAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 348;
=======
        int fileClientMessageIndex = 354;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, SetAddAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SetCompareAndRemoveAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 349;
=======
        int fileClientMessageIndex = 355;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetCompareAndRemoveAllCodec.encodeRequest(aString, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetCompareAndRemoveAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 350;
=======
        int fileClientMessageIndex = 356;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, SetCompareAndRemoveAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SetCompareAndRetainAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 351;
=======
        int fileClientMessageIndex = 357;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetCompareAndRetainAllCodec.encodeRequest(aString, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetCompareAndRetainAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 352;
=======
        int fileClientMessageIndex = 358;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, SetCompareAndRetainAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SetClearCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 353;
=======
        int fileClientMessageIndex = 359;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetClearCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetClearCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 354;
=======
        int fileClientMessageIndex = 360;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_SetGetAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 355;
=======
        int fileClientMessageIndex = 361;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetGetAllCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetGetAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 356;
=======
        int fileClientMessageIndex = 362;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, SetGetAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SetAddListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 357;
=======
        int fileClientMessageIndex = 363;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetAddListenerCodec.encodeRequest(aString, aBoolean, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetAddListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 358;
=======
        int fileClientMessageIndex = 364;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, SetAddListenerCodec.decodeResponse(fromFile)));
    }

    private static class SetAddListenerCodecHandler extends SetAddListenerCodec.AbstractEventHandler {
        @Override
        public void handleItemEvent(com.hazelcast.internal.serialization.Data item, java.util.UUID uuid, int eventType) {
            assertTrue(isEqual(null, item));
            assertTrue(isEqual(aUUID, uuid));
            assertTrue(isEqual(anInt, eventType));
        }
    }

    @Test
    public void test_SetAddListenerCodec_handleItemEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 359;
=======
        int fileClientMessageIndex = 365;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SetAddListenerCodecHandler handler = new SetAddListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_SetRemoveListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 360;
=======
        int fileClientMessageIndex = 366;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetRemoveListenerCodec.encodeRequest(aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetRemoveListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 361;
=======
        int fileClientMessageIndex = 367;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, SetRemoveListenerCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SetIsEmptyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 362;
=======
        int fileClientMessageIndex = 368;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetIsEmptyCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetIsEmptyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 363;
=======
        int fileClientMessageIndex = 369;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, SetIsEmptyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_FencedLockLockCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 364;
=======
        int fileClientMessageIndex = 370;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = FencedLockLockCodec.encodeRequest(aRaftGroupId, aString, aLong, aLong, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_FencedLockLockCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 365;
=======
        int fileClientMessageIndex = 371;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, FencedLockLockCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_FencedLockTryLockCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 366;
=======
        int fileClientMessageIndex = 372;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = FencedLockTryLockCodec.encodeRequest(aRaftGroupId, aString, aLong, aLong, aUUID, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_FencedLockTryLockCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 367;
=======
        int fileClientMessageIndex = 373;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, FencedLockTryLockCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_FencedLockUnlockCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 368;
=======
        int fileClientMessageIndex = 374;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = FencedLockUnlockCodec.encodeRequest(aRaftGroupId, aString, aLong, aLong, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_FencedLockUnlockCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 369;
=======
        int fileClientMessageIndex = 375;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, FencedLockUnlockCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_FencedLockGetLockOwnershipCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 370;
=======
        int fileClientMessageIndex = 376;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = FencedLockGetLockOwnershipCodec.encodeRequest(aRaftGroupId, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_FencedLockGetLockOwnershipCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 371;
=======
        int fileClientMessageIndex = 377;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        FencedLockGetLockOwnershipCodec.ResponseParameters parameters = FencedLockGetLockOwnershipCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aLong, parameters.fence));
        assertTrue(isEqual(anInt, parameters.lockCount));
        assertTrue(isEqual(aLong, parameters.sessionId));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_ExecutorServiceShutdownCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 372;
=======
        int fileClientMessageIndex = 378;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ExecutorServiceShutdownCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ExecutorServiceShutdownCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 373;
=======
        int fileClientMessageIndex = 379;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_ExecutorServiceIsShutdownCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 374;
=======
        int fileClientMessageIndex = 380;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ExecutorServiceIsShutdownCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ExecutorServiceIsShutdownCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 375;
=======
        int fileClientMessageIndex = 381;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ExecutorServiceIsShutdownCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ExecutorServiceCancelOnPartitionCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 376;
=======
        int fileClientMessageIndex = 382;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ExecutorServiceCancelOnPartitionCodec.encodeRequest(aUUID, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ExecutorServiceCancelOnPartitionCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 377;
=======
        int fileClientMessageIndex = 383;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ExecutorServiceCancelOnPartitionCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ExecutorServiceCancelOnMemberCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 378;
=======
        int fileClientMessageIndex = 384;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ExecutorServiceCancelOnMemberCodec.encodeRequest(aUUID, aUUID, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ExecutorServiceCancelOnMemberCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 379;
=======
        int fileClientMessageIndex = 385;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ExecutorServiceCancelOnMemberCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ExecutorServiceSubmitToPartitionCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 380;
=======
        int fileClientMessageIndex = 386;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ExecutorServiceSubmitToPartitionCodec.encodeRequest(aString, aUUID, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ExecutorServiceSubmitToPartitionCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 381;
=======
        int fileClientMessageIndex = 387;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, ExecutorServiceSubmitToPartitionCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ExecutorServiceSubmitToMemberCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 382;
=======
        int fileClientMessageIndex = 388;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ExecutorServiceSubmitToMemberCodec.encodeRequest(aString, aUUID, aData, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ExecutorServiceSubmitToMemberCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 383;
=======
        int fileClientMessageIndex = 389;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, ExecutorServiceSubmitToMemberCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_AtomicLongApplyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 384;
=======
        int fileClientMessageIndex = 390;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicLongApplyCodec.encodeRequest(aRaftGroupId, aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicLongApplyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 385;
=======
        int fileClientMessageIndex = 391;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, AtomicLongApplyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_AtomicLongAlterCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 386;
=======
        int fileClientMessageIndex = 392;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicLongAlterCodec.encodeRequest(aRaftGroupId, aString, aData, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicLongAlterCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 387;
=======
        int fileClientMessageIndex = 393;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, AtomicLongAlterCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_AtomicLongAddAndGetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 388;
=======
        int fileClientMessageIndex = 394;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicLongAddAndGetCodec.encodeRequest(aRaftGroupId, aString, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicLongAddAndGetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 389;
=======
        int fileClientMessageIndex = 395;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, AtomicLongAddAndGetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_AtomicLongCompareAndSetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 390;
=======
        int fileClientMessageIndex = 396;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicLongCompareAndSetCodec.encodeRequest(aRaftGroupId, aString, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicLongCompareAndSetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 391;
=======
        int fileClientMessageIndex = 397;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, AtomicLongCompareAndSetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_AtomicLongGetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 392;
=======
        int fileClientMessageIndex = 398;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicLongGetCodec.encodeRequest(aRaftGroupId, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicLongGetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 393;
=======
        int fileClientMessageIndex = 399;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, AtomicLongGetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_AtomicLongGetAndAddCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 394;
=======
        int fileClientMessageIndex = 400;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicLongGetAndAddCodec.encodeRequest(aRaftGroupId, aString, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicLongGetAndAddCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 395;
=======
        int fileClientMessageIndex = 401;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, AtomicLongGetAndAddCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_AtomicLongGetAndSetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 396;
=======
        int fileClientMessageIndex = 402;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicLongGetAndSetCodec.encodeRequest(aRaftGroupId, aString, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicLongGetAndSetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 397;
=======
        int fileClientMessageIndex = 403;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, AtomicLongGetAndSetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_AtomicRefApplyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 398;
=======
        int fileClientMessageIndex = 404;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicRefApplyCodec.encodeRequest(aRaftGroupId, aString, aData, anInt, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicRefApplyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 399;
=======
        int fileClientMessageIndex = 405;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, AtomicRefApplyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_AtomicRefCompareAndSetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 400;
=======
        int fileClientMessageIndex = 406;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicRefCompareAndSetCodec.encodeRequest(aRaftGroupId, aString, null, null);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicRefCompareAndSetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 401;
=======
        int fileClientMessageIndex = 407;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, AtomicRefCompareAndSetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_AtomicRefContainsCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 402;
=======
        int fileClientMessageIndex = 408;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicRefContainsCodec.encodeRequest(aRaftGroupId, aString, null);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicRefContainsCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 403;
=======
        int fileClientMessageIndex = 409;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, AtomicRefContainsCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_AtomicRefGetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 404;
=======
        int fileClientMessageIndex = 410;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicRefGetCodec.encodeRequest(aRaftGroupId, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicRefGetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 405;
=======
        int fileClientMessageIndex = 411;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, AtomicRefGetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_AtomicRefSetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 406;
=======
        int fileClientMessageIndex = 412;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicRefSetCodec.encodeRequest(aRaftGroupId, aString, null, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicRefSetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 407;
=======
        int fileClientMessageIndex = 413;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, AtomicRefSetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CountDownLatchTrySetCountCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 408;
=======
        int fileClientMessageIndex = 414;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CountDownLatchTrySetCountCodec.encodeRequest(aRaftGroupId, aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CountDownLatchTrySetCountCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 409;
=======
        int fileClientMessageIndex = 415;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, CountDownLatchTrySetCountCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CountDownLatchAwaitCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 410;
=======
        int fileClientMessageIndex = 416;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CountDownLatchAwaitCodec.encodeRequest(aRaftGroupId, aString, aUUID, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CountDownLatchAwaitCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 411;
=======
        int fileClientMessageIndex = 417;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, CountDownLatchAwaitCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CountDownLatchCountDownCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 412;
=======
        int fileClientMessageIndex = 418;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CountDownLatchCountDownCodec.encodeRequest(aRaftGroupId, aString, aUUID, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CountDownLatchCountDownCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 413;
=======
        int fileClientMessageIndex = 419;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_CountDownLatchGetCountCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 414;
=======
        int fileClientMessageIndex = 420;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CountDownLatchGetCountCodec.encodeRequest(aRaftGroupId, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CountDownLatchGetCountCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 415;
=======
        int fileClientMessageIndex = 421;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, CountDownLatchGetCountCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CountDownLatchGetRoundCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 416;
=======
        int fileClientMessageIndex = 422;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CountDownLatchGetRoundCodec.encodeRequest(aRaftGroupId, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CountDownLatchGetRoundCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 417;
=======
        int fileClientMessageIndex = 423;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, CountDownLatchGetRoundCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SemaphoreInitCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 418;
=======
        int fileClientMessageIndex = 424;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SemaphoreInitCodec.encodeRequest(aRaftGroupId, aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SemaphoreInitCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 419;
=======
        int fileClientMessageIndex = 425;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, SemaphoreInitCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SemaphoreAcquireCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 420;
=======
        int fileClientMessageIndex = 426;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SemaphoreAcquireCodec.encodeRequest(aRaftGroupId, aString, aLong, aLong, aUUID, anInt, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SemaphoreAcquireCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 421;
=======
        int fileClientMessageIndex = 427;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, SemaphoreAcquireCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SemaphoreReleaseCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 422;
=======
        int fileClientMessageIndex = 428;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SemaphoreReleaseCodec.encodeRequest(aRaftGroupId, aString, aLong, aLong, aUUID, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SemaphoreReleaseCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 423;
=======
        int fileClientMessageIndex = 429;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, SemaphoreReleaseCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SemaphoreDrainCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 424;
=======
        int fileClientMessageIndex = 430;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SemaphoreDrainCodec.encodeRequest(aRaftGroupId, aString, aLong, aLong, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SemaphoreDrainCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 425;
=======
        int fileClientMessageIndex = 431;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, SemaphoreDrainCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SemaphoreChangeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 426;
=======
        int fileClientMessageIndex = 432;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SemaphoreChangeCodec.encodeRequest(aRaftGroupId, aString, aLong, aLong, aUUID, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SemaphoreChangeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 427;
=======
        int fileClientMessageIndex = 433;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, SemaphoreChangeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SemaphoreAvailablePermitsCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 428;
=======
        int fileClientMessageIndex = 434;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SemaphoreAvailablePermitsCodec.encodeRequest(aRaftGroupId, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SemaphoreAvailablePermitsCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 429;
=======
        int fileClientMessageIndex = 435;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, SemaphoreAvailablePermitsCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_SemaphoreGetSemaphoreTypeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 430;
=======
        int fileClientMessageIndex = 436;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SemaphoreGetSemaphoreTypeCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SemaphoreGetSemaphoreTypeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 431;
=======
        int fileClientMessageIndex = 437;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, SemaphoreGetSemaphoreTypeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ReplicatedMapPutCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 432;
=======
        int fileClientMessageIndex = 438;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapPutCodec.encodeRequest(aString, aData, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapPutCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 433;
=======
        int fileClientMessageIndex = 439;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, ReplicatedMapPutCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ReplicatedMapSizeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 434;
=======
        int fileClientMessageIndex = 440;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapSizeCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapSizeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 435;
=======
        int fileClientMessageIndex = 441;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, ReplicatedMapSizeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ReplicatedMapIsEmptyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 436;
=======
        int fileClientMessageIndex = 442;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapIsEmptyCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapIsEmptyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 437;
=======
        int fileClientMessageIndex = 443;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ReplicatedMapIsEmptyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ReplicatedMapContainsKeyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 438;
=======
        int fileClientMessageIndex = 444;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapContainsKeyCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapContainsKeyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 439;
=======
        int fileClientMessageIndex = 445;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ReplicatedMapContainsKeyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ReplicatedMapContainsValueCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 440;
=======
        int fileClientMessageIndex = 446;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapContainsValueCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapContainsValueCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 441;
=======
        int fileClientMessageIndex = 447;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ReplicatedMapContainsValueCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ReplicatedMapGetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 442;
=======
        int fileClientMessageIndex = 448;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapGetCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapGetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 443;
=======
        int fileClientMessageIndex = 449;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, ReplicatedMapGetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ReplicatedMapRemoveCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 444;
=======
        int fileClientMessageIndex = 450;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapRemoveCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapRemoveCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 445;
=======
        int fileClientMessageIndex = 451;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, ReplicatedMapRemoveCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ReplicatedMapPutAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 446;
=======
        int fileClientMessageIndex = 452;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapPutAllCodec.encodeRequest(aString, aListOfDataToData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapPutAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 447;
=======
        int fileClientMessageIndex = 453;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_ReplicatedMapClearCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 448;
=======
        int fileClientMessageIndex = 454;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapClearCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapClearCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 449;
=======
        int fileClientMessageIndex = 455;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerToKeyWithPredicateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 450;
=======
        int fileClientMessageIndex = 456;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapAddEntryListenerToKeyWithPredicateCodec.encodeRequest(aString, aData, aData, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerToKeyWithPredicateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 451;
=======
        int fileClientMessageIndex = 457;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, ReplicatedMapAddEntryListenerToKeyWithPredicateCodec.decodeResponse(fromFile)));
    }

    private static class ReplicatedMapAddEntryListenerToKeyWithPredicateCodecHandler extends ReplicatedMapAddEntryListenerToKeyWithPredicateCodec.AbstractEventHandler {
        @Override
        public void handleEntryEvent(com.hazelcast.internal.serialization.Data key, com.hazelcast.internal.serialization.Data value, com.hazelcast.internal.serialization.Data oldValue, com.hazelcast.internal.serialization.Data mergingValue, int eventType, java.util.UUID uuid, int numberOfAffectedEntries) {
            assertTrue(isEqual(null, key));
            assertTrue(isEqual(null, value));
            assertTrue(isEqual(null, oldValue));
            assertTrue(isEqual(null, mergingValue));
            assertTrue(isEqual(anInt, eventType));
            assertTrue(isEqual(aUUID, uuid));
            assertTrue(isEqual(anInt, numberOfAffectedEntries));
        }
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerToKeyWithPredicateCodec_handleEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 452;
=======
        int fileClientMessageIndex = 458;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapAddEntryListenerToKeyWithPredicateCodecHandler handler = new ReplicatedMapAddEntryListenerToKeyWithPredicateCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerWithPredicateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 453;
=======
        int fileClientMessageIndex = 459;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapAddEntryListenerWithPredicateCodec.encodeRequest(aString, aData, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerWithPredicateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 454;
=======
        int fileClientMessageIndex = 460;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, ReplicatedMapAddEntryListenerWithPredicateCodec.decodeResponse(fromFile)));
    }

    private static class ReplicatedMapAddEntryListenerWithPredicateCodecHandler extends ReplicatedMapAddEntryListenerWithPredicateCodec.AbstractEventHandler {
        @Override
        public void handleEntryEvent(com.hazelcast.internal.serialization.Data key, com.hazelcast.internal.serialization.Data value, com.hazelcast.internal.serialization.Data oldValue, com.hazelcast.internal.serialization.Data mergingValue, int eventType, java.util.UUID uuid, int numberOfAffectedEntries) {
            assertTrue(isEqual(null, key));
            assertTrue(isEqual(null, value));
            assertTrue(isEqual(null, oldValue));
            assertTrue(isEqual(null, mergingValue));
            assertTrue(isEqual(anInt, eventType));
            assertTrue(isEqual(aUUID, uuid));
            assertTrue(isEqual(anInt, numberOfAffectedEntries));
        }
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerWithPredicateCodec_handleEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 455;
=======
        int fileClientMessageIndex = 461;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapAddEntryListenerWithPredicateCodecHandler handler = new ReplicatedMapAddEntryListenerWithPredicateCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerToKeyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 456;
=======
        int fileClientMessageIndex = 462;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapAddEntryListenerToKeyCodec.encodeRequest(aString, aData, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerToKeyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 457;
=======
        int fileClientMessageIndex = 463;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, ReplicatedMapAddEntryListenerToKeyCodec.decodeResponse(fromFile)));
    }

    private static class ReplicatedMapAddEntryListenerToKeyCodecHandler extends ReplicatedMapAddEntryListenerToKeyCodec.AbstractEventHandler {
        @Override
        public void handleEntryEvent(com.hazelcast.internal.serialization.Data key, com.hazelcast.internal.serialization.Data value, com.hazelcast.internal.serialization.Data oldValue, com.hazelcast.internal.serialization.Data mergingValue, int eventType, java.util.UUID uuid, int numberOfAffectedEntries) {
            assertTrue(isEqual(null, key));
            assertTrue(isEqual(null, value));
            assertTrue(isEqual(null, oldValue));
            assertTrue(isEqual(null, mergingValue));
            assertTrue(isEqual(anInt, eventType));
            assertTrue(isEqual(aUUID, uuid));
            assertTrue(isEqual(anInt, numberOfAffectedEntries));
        }
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerToKeyCodec_handleEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 458;
=======
        int fileClientMessageIndex = 464;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapAddEntryListenerToKeyCodecHandler handler = new ReplicatedMapAddEntryListenerToKeyCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 459;
=======
        int fileClientMessageIndex = 465;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapAddEntryListenerCodec.encodeRequest(aString, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 460;
=======
        int fileClientMessageIndex = 466;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, ReplicatedMapAddEntryListenerCodec.decodeResponse(fromFile)));
    }

    private static class ReplicatedMapAddEntryListenerCodecHandler extends ReplicatedMapAddEntryListenerCodec.AbstractEventHandler {
        @Override
        public void handleEntryEvent(com.hazelcast.internal.serialization.Data key, com.hazelcast.internal.serialization.Data value, com.hazelcast.internal.serialization.Data oldValue, com.hazelcast.internal.serialization.Data mergingValue, int eventType, java.util.UUID uuid, int numberOfAffectedEntries) {
            assertTrue(isEqual(null, key));
            assertTrue(isEqual(null, value));
            assertTrue(isEqual(null, oldValue));
            assertTrue(isEqual(null, mergingValue));
            assertTrue(isEqual(anInt, eventType));
            assertTrue(isEqual(aUUID, uuid));
            assertTrue(isEqual(anInt, numberOfAffectedEntries));
        }
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerCodec_handleEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 461;
=======
        int fileClientMessageIndex = 467;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapAddEntryListenerCodecHandler handler = new ReplicatedMapAddEntryListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_ReplicatedMapRemoveEntryListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 462;
=======
        int fileClientMessageIndex = 468;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapRemoveEntryListenerCodec.encodeRequest(aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapRemoveEntryListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 463;
=======
        int fileClientMessageIndex = 469;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ReplicatedMapRemoveEntryListenerCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ReplicatedMapKeySetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 464;
=======
        int fileClientMessageIndex = 470;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapKeySetCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapKeySetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 465;
=======
        int fileClientMessageIndex = 471;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, ReplicatedMapKeySetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ReplicatedMapValuesCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 466;
=======
        int fileClientMessageIndex = 472;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapValuesCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapValuesCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 467;
=======
        int fileClientMessageIndex = 473;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, ReplicatedMapValuesCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ReplicatedMapEntrySetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 468;
=======
        int fileClientMessageIndex = 474;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapEntrySetCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapEntrySetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 469;
=======
        int fileClientMessageIndex = 475;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfDataToData, ReplicatedMapEntrySetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ReplicatedMapAddNearCacheEntryListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 470;
=======
        int fileClientMessageIndex = 476;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapAddNearCacheEntryListenerCodec.encodeRequest(aString, aBoolean, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapAddNearCacheEntryListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 471;
=======
        int fileClientMessageIndex = 477;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, ReplicatedMapAddNearCacheEntryListenerCodec.decodeResponse(fromFile)));
    }

    private static class ReplicatedMapAddNearCacheEntryListenerCodecHandler extends ReplicatedMapAddNearCacheEntryListenerCodec.AbstractEventHandler {
        @Override
        public void handleEntryEvent(com.hazelcast.internal.serialization.Data key, com.hazelcast.internal.serialization.Data value, com.hazelcast.internal.serialization.Data oldValue, com.hazelcast.internal.serialization.Data mergingValue, int eventType, java.util.UUID uuid, int numberOfAffectedEntries) {
            assertTrue(isEqual(null, key));
            assertTrue(isEqual(null, value));
            assertTrue(isEqual(null, oldValue));
            assertTrue(isEqual(null, mergingValue));
            assertTrue(isEqual(anInt, eventType));
            assertTrue(isEqual(aUUID, uuid));
            assertTrue(isEqual(anInt, numberOfAffectedEntries));
        }
    }

    @Test
    public void test_ReplicatedMapAddNearCacheEntryListenerCodec_handleEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 472;
=======
        int fileClientMessageIndex = 478;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapAddNearCacheEntryListenerCodecHandler handler = new ReplicatedMapAddNearCacheEntryListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_TransactionalMapContainsKeyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 473;
=======
        int fileClientMessageIndex = 479;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapContainsKeyCodec.encodeRequest(aString, aUUID, aLong, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapContainsKeyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 474;
=======
        int fileClientMessageIndex = 480;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, TransactionalMapContainsKeyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMapGetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 475;
=======
        int fileClientMessageIndex = 481;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapGetCodec.encodeRequest(aString, aUUID, aLong, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapGetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 476;
=======
        int fileClientMessageIndex = 482;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, TransactionalMapGetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMapGetForUpdateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 477;
=======
        int fileClientMessageIndex = 483;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapGetForUpdateCodec.encodeRequest(aString, aUUID, aLong, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapGetForUpdateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 478;
=======
        int fileClientMessageIndex = 484;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, TransactionalMapGetForUpdateCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMapSizeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 479;
=======
        int fileClientMessageIndex = 485;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapSizeCodec.encodeRequest(aString, aUUID, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapSizeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 480;
=======
        int fileClientMessageIndex = 486;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, TransactionalMapSizeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMapIsEmptyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 481;
=======
        int fileClientMessageIndex = 487;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapIsEmptyCodec.encodeRequest(aString, aUUID, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapIsEmptyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 482;
=======
        int fileClientMessageIndex = 488;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, TransactionalMapIsEmptyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMapPutCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 483;
=======
        int fileClientMessageIndex = 489;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapPutCodec.encodeRequest(aString, aUUID, aLong, aData, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapPutCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 484;
=======
        int fileClientMessageIndex = 490;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, TransactionalMapPutCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMapSetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 485;
=======
        int fileClientMessageIndex = 491;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapSetCodec.encodeRequest(aString, aUUID, aLong, aData, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapSetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 486;
=======
        int fileClientMessageIndex = 492;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_TransactionalMapPutIfAbsentCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 487;
=======
        int fileClientMessageIndex = 493;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapPutIfAbsentCodec.encodeRequest(aString, aUUID, aLong, aData, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapPutIfAbsentCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 488;
=======
        int fileClientMessageIndex = 494;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, TransactionalMapPutIfAbsentCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMapReplaceCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 489;
=======
        int fileClientMessageIndex = 495;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapReplaceCodec.encodeRequest(aString, aUUID, aLong, aData, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapReplaceCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 490;
=======
        int fileClientMessageIndex = 496;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, TransactionalMapReplaceCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMapReplaceIfSameCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 491;
=======
        int fileClientMessageIndex = 497;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapReplaceIfSameCodec.encodeRequest(aString, aUUID, aLong, aData, aData, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapReplaceIfSameCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 492;
=======
        int fileClientMessageIndex = 498;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, TransactionalMapReplaceIfSameCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMapRemoveCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 493;
=======
        int fileClientMessageIndex = 499;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapRemoveCodec.encodeRequest(aString, aUUID, aLong, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapRemoveCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 494;
=======
        int fileClientMessageIndex = 500;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, TransactionalMapRemoveCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMapDeleteCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 495;
=======
        int fileClientMessageIndex = 501;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapDeleteCodec.encodeRequest(aString, aUUID, aLong, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapDeleteCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 496;
=======
        int fileClientMessageIndex = 502;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_TransactionalMapRemoveIfSameCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 497;
=======
        int fileClientMessageIndex = 503;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapRemoveIfSameCodec.encodeRequest(aString, aUUID, aLong, aData, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapRemoveIfSameCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 498;
=======
        int fileClientMessageIndex = 504;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, TransactionalMapRemoveIfSameCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMapKeySetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 499;
=======
        int fileClientMessageIndex = 505;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapKeySetCodec.encodeRequest(aString, aUUID, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapKeySetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 500;
=======
        int fileClientMessageIndex = 506;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, TransactionalMapKeySetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMapKeySetWithPredicateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 501;
=======
        int fileClientMessageIndex = 507;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapKeySetWithPredicateCodec.encodeRequest(aString, aUUID, aLong, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapKeySetWithPredicateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 502;
=======
        int fileClientMessageIndex = 508;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, TransactionalMapKeySetWithPredicateCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMapValuesCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 503;
=======
        int fileClientMessageIndex = 509;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapValuesCodec.encodeRequest(aString, aUUID, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapValuesCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 504;
=======
        int fileClientMessageIndex = 510;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, TransactionalMapValuesCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMapValuesWithPredicateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 505;
=======
        int fileClientMessageIndex = 511;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapValuesWithPredicateCodec.encodeRequest(aString, aUUID, aLong, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapValuesWithPredicateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 506;
=======
        int fileClientMessageIndex = 512;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, TransactionalMapValuesWithPredicateCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMapContainsValueCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 507;
=======
        int fileClientMessageIndex = 513;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapContainsValueCodec.encodeRequest(aString, aUUID, aLong, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapContainsValueCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 508;
=======
        int fileClientMessageIndex = 514;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, TransactionalMapContainsValueCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMultiMapPutCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 509;
=======
        int fileClientMessageIndex = 515;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMultiMapPutCodec.encodeRequest(aString, aUUID, aLong, aData, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMultiMapPutCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 510;
=======
        int fileClientMessageIndex = 516;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, TransactionalMultiMapPutCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMultiMapGetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 511;
=======
        int fileClientMessageIndex = 517;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMultiMapGetCodec.encodeRequest(aString, aUUID, aLong, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMultiMapGetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 512;
=======
        int fileClientMessageIndex = 518;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, TransactionalMultiMapGetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMultiMapRemoveCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 513;
=======
        int fileClientMessageIndex = 519;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMultiMapRemoveCodec.encodeRequest(aString, aUUID, aLong, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMultiMapRemoveCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 514;
=======
        int fileClientMessageIndex = 520;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, TransactionalMultiMapRemoveCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMultiMapRemoveEntryCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 515;
=======
        int fileClientMessageIndex = 521;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMultiMapRemoveEntryCodec.encodeRequest(aString, aUUID, aLong, aData, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMultiMapRemoveEntryCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 516;
=======
        int fileClientMessageIndex = 522;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, TransactionalMultiMapRemoveEntryCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMultiMapValueCountCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 517;
=======
        int fileClientMessageIndex = 523;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMultiMapValueCountCodec.encodeRequest(aString, aUUID, aLong, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMultiMapValueCountCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 518;
=======
        int fileClientMessageIndex = 524;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, TransactionalMultiMapValueCountCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalMultiMapSizeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 519;
=======
        int fileClientMessageIndex = 525;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMultiMapSizeCodec.encodeRequest(aString, aUUID, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMultiMapSizeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 520;
=======
        int fileClientMessageIndex = 526;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, TransactionalMultiMapSizeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalSetAddCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 521;
=======
        int fileClientMessageIndex = 527;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalSetAddCodec.encodeRequest(aString, aUUID, aLong, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalSetAddCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 522;
=======
        int fileClientMessageIndex = 528;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, TransactionalSetAddCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalSetRemoveCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 523;
=======
        int fileClientMessageIndex = 529;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalSetRemoveCodec.encodeRequest(aString, aUUID, aLong, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalSetRemoveCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 524;
=======
        int fileClientMessageIndex = 530;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, TransactionalSetRemoveCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalSetSizeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 525;
=======
        int fileClientMessageIndex = 531;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalSetSizeCodec.encodeRequest(aString, aUUID, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalSetSizeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 526;
=======
        int fileClientMessageIndex = 532;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, TransactionalSetSizeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalListAddCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 527;
=======
        int fileClientMessageIndex = 533;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalListAddCodec.encodeRequest(aString, aUUID, aLong, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalListAddCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 528;
=======
        int fileClientMessageIndex = 534;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, TransactionalListAddCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalListRemoveCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 529;
=======
        int fileClientMessageIndex = 535;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalListRemoveCodec.encodeRequest(aString, aUUID, aLong, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalListRemoveCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 530;
=======
        int fileClientMessageIndex = 536;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, TransactionalListRemoveCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalListSizeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 531;
=======
        int fileClientMessageIndex = 537;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalListSizeCodec.encodeRequest(aString, aUUID, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalListSizeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 532;
=======
        int fileClientMessageIndex = 538;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, TransactionalListSizeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalQueueOfferCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 533;
=======
        int fileClientMessageIndex = 539;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalQueueOfferCodec.encodeRequest(aString, aUUID, aLong, aData, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalQueueOfferCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 534;
=======
        int fileClientMessageIndex = 540;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, TransactionalQueueOfferCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalQueueTakeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 535;
=======
        int fileClientMessageIndex = 541;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalQueueTakeCodec.encodeRequest(aString, aUUID, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalQueueTakeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 536;
=======
        int fileClientMessageIndex = 542;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, TransactionalQueueTakeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalQueuePollCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 537;
=======
        int fileClientMessageIndex = 543;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalQueuePollCodec.encodeRequest(aString, aUUID, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalQueuePollCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 538;
=======
        int fileClientMessageIndex = 544;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, TransactionalQueuePollCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalQueuePeekCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 539;
=======
        int fileClientMessageIndex = 545;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalQueuePeekCodec.encodeRequest(aString, aUUID, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalQueuePeekCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 540;
=======
        int fileClientMessageIndex = 546;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, TransactionalQueuePeekCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionalQueueSizeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 541;
=======
        int fileClientMessageIndex = 547;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalQueueSizeCodec.encodeRequest(aString, aUUID, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalQueueSizeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 542;
=======
        int fileClientMessageIndex = 548;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, TransactionalQueueSizeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CacheAddEntryListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 543;
=======
        int fileClientMessageIndex = 549;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheAddEntryListenerCodec.encodeRequest(aString, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheAddEntryListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 544;
=======
        int fileClientMessageIndex = 550;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, CacheAddEntryListenerCodec.decodeResponse(fromFile)));
    }

    private static class CacheAddEntryListenerCodecHandler extends CacheAddEntryListenerCodec.AbstractEventHandler {
        @Override
        public void handleCacheEvent(int type, java.util.Collection<com.hazelcast.cache.impl.CacheEventData> keys, int completionId) {
            assertTrue(isEqual(anInt, type));
            assertTrue(isEqual(aListOfCacheEventData, keys));
            assertTrue(isEqual(anInt, completionId));
        }
    }

    @Test
    public void test_CacheAddEntryListenerCodec_handleCacheEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 545;
=======
        int fileClientMessageIndex = 551;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheAddEntryListenerCodecHandler handler = new CacheAddEntryListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_CacheClearCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 546;
=======
        int fileClientMessageIndex = 552;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheClearCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheClearCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 547;
=======
        int fileClientMessageIndex = 553;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_CacheRemoveAllKeysCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 548;
=======
        int fileClientMessageIndex = 554;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheRemoveAllKeysCodec.encodeRequest(aString, aListOfData, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheRemoveAllKeysCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 549;
=======
        int fileClientMessageIndex = 555;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_CacheRemoveAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 550;
=======
        int fileClientMessageIndex = 556;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheRemoveAllCodec.encodeRequest(aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheRemoveAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 551;
=======
        int fileClientMessageIndex = 557;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_CacheContainsKeyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 552;
=======
        int fileClientMessageIndex = 558;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheContainsKeyCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheContainsKeyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 553;
=======
        int fileClientMessageIndex = 559;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, CacheContainsKeyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CacheCreateConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 554;
=======
        int fileClientMessageIndex = 560;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheCreateConfigCodec.encodeRequest(aCacheConfigHolder, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheCreateConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 555;
=======
        int fileClientMessageIndex = 561;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, CacheCreateConfigCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CacheDestroyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 556;
=======
        int fileClientMessageIndex = 562;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheDestroyCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheDestroyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 557;
=======
        int fileClientMessageIndex = 563;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_CacheEntryProcessorCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 558;
=======
        int fileClientMessageIndex = 564;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheEntryProcessorCodec.encodeRequest(aString, aData, aData, aListOfData, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheEntryProcessorCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 559;
=======
        int fileClientMessageIndex = 565;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, CacheEntryProcessorCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CacheGetAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 560;
=======
        int fileClientMessageIndex = 566;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheGetAllCodec.encodeRequest(aString, aListOfData, null);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheGetAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 561;
=======
        int fileClientMessageIndex = 567;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfDataToData, CacheGetAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CacheGetAndRemoveCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 562;
=======
        int fileClientMessageIndex = 568;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheGetAndRemoveCodec.encodeRequest(aString, aData, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheGetAndRemoveCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 563;
=======
        int fileClientMessageIndex = 569;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, CacheGetAndRemoveCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CacheGetAndReplaceCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 564;
=======
        int fileClientMessageIndex = 570;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheGetAndReplaceCodec.encodeRequest(aString, aData, aData, null, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheGetAndReplaceCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 565;
=======
        int fileClientMessageIndex = 571;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, CacheGetAndReplaceCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CacheGetConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 566;
=======
        int fileClientMessageIndex = 572;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheGetConfigCodec.encodeRequest(aString, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheGetConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 567;
=======
        int fileClientMessageIndex = 573;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, CacheGetConfigCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CacheGetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 568;
=======
        int fileClientMessageIndex = 574;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheGetCodec.encodeRequest(aString, aData, null);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheGetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 569;
=======
        int fileClientMessageIndex = 575;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, CacheGetCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CacheIterateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 570;
=======
        int fileClientMessageIndex = 576;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheIterateCodec.encodeRequest(aString, aListOfIntegerToInteger, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheIterateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 571;
=======
        int fileClientMessageIndex = 577;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheIterateCodec.ResponseParameters parameters = CacheIterateCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aListOfIntegerToInteger, parameters.iterationPointers));
        assertTrue(isEqual(aListOfData, parameters.keys));
    }

    @Test
    public void test_CacheListenerRegistrationCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 572;
=======
        int fileClientMessageIndex = 578;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheListenerRegistrationCodec.encodeRequest(aString, aData, aBoolean, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheListenerRegistrationCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 573;
=======
        int fileClientMessageIndex = 579;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_CacheLoadAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 574;
=======
        int fileClientMessageIndex = 580;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheLoadAllCodec.encodeRequest(aString, aListOfData, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheLoadAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 575;
=======
        int fileClientMessageIndex = 581;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_CacheManagementConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 576;
=======
        int fileClientMessageIndex = 582;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheManagementConfigCodec.encodeRequest(aString, aBoolean, aBoolean, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheManagementConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 577;
=======
        int fileClientMessageIndex = 583;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_CachePutIfAbsentCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 578;
=======
        int fileClientMessageIndex = 584;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CachePutIfAbsentCodec.encodeRequest(aString, aData, aData, null, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CachePutIfAbsentCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 579;
=======
        int fileClientMessageIndex = 585;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, CachePutIfAbsentCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CachePutCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 580;
=======
        int fileClientMessageIndex = 586;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CachePutCodec.encodeRequest(aString, aData, aData, null, aBoolean, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CachePutCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 581;
=======
        int fileClientMessageIndex = 587;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, CachePutCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CacheRemoveEntryListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 582;
=======
        int fileClientMessageIndex = 588;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheRemoveEntryListenerCodec.encodeRequest(aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheRemoveEntryListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 583;
=======
        int fileClientMessageIndex = 589;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, CacheRemoveEntryListenerCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CacheRemoveInvalidationListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 584;
=======
        int fileClientMessageIndex = 590;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheRemoveInvalidationListenerCodec.encodeRequest(aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheRemoveInvalidationListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 585;
=======
        int fileClientMessageIndex = 591;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, CacheRemoveInvalidationListenerCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CacheRemoveCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 586;
=======
        int fileClientMessageIndex = 592;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheRemoveCodec.encodeRequest(aString, aData, null, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheRemoveCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 587;
=======
        int fileClientMessageIndex = 593;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, CacheRemoveCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CacheReplaceCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 588;
=======
        int fileClientMessageIndex = 594;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheReplaceCodec.encodeRequest(aString, aData, null, aData, null, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheReplaceCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 589;
=======
        int fileClientMessageIndex = 595;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, CacheReplaceCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CacheSizeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 590;
=======
        int fileClientMessageIndex = 596;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheSizeCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheSizeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 591;
=======
        int fileClientMessageIndex = 597;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, CacheSizeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CacheAddPartitionLostListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 592;
=======
        int fileClientMessageIndex = 598;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheAddPartitionLostListenerCodec.encodeRequest(aString, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheAddPartitionLostListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 593;
=======
        int fileClientMessageIndex = 599;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, CacheAddPartitionLostListenerCodec.decodeResponse(fromFile)));
    }

    private static class CacheAddPartitionLostListenerCodecHandler extends CacheAddPartitionLostListenerCodec.AbstractEventHandler {
        @Override
        public void handleCachePartitionLostEvent(int partitionId, java.util.UUID uuid) {
            assertTrue(isEqual(anInt, partitionId));
            assertTrue(isEqual(aUUID, uuid));
        }
    }

    @Test
    public void test_CacheAddPartitionLostListenerCodec_handleCachePartitionLostEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 594;
=======
        int fileClientMessageIndex = 600;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheAddPartitionLostListenerCodecHandler handler = new CacheAddPartitionLostListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_CacheRemovePartitionLostListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 595;
=======
        int fileClientMessageIndex = 601;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheRemovePartitionLostListenerCodec.encodeRequest(aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheRemovePartitionLostListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 596;
=======
        int fileClientMessageIndex = 602;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, CacheRemovePartitionLostListenerCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CachePutAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 597;
=======
        int fileClientMessageIndex = 603;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CachePutAllCodec.encodeRequest(aString, aListOfDataToData, null, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CachePutAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 598;
=======
        int fileClientMessageIndex = 604;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_CacheIterateEntriesCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 599;
=======
        int fileClientMessageIndex = 605;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheIterateEntriesCodec.encodeRequest(aString, aListOfIntegerToInteger, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheIterateEntriesCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 600;
=======
        int fileClientMessageIndex = 606;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheIterateEntriesCodec.ResponseParameters parameters = CacheIterateEntriesCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aListOfIntegerToInteger, parameters.iterationPointers));
        assertTrue(isEqual(aListOfDataToData, parameters.entries));
    }

    @Test
    public void test_CacheAddNearCacheInvalidationListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 601;
=======
        int fileClientMessageIndex = 607;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheAddNearCacheInvalidationListenerCodec.encodeRequest(aString, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheAddNearCacheInvalidationListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 602;
=======
        int fileClientMessageIndex = 608;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, CacheAddNearCacheInvalidationListenerCodec.decodeResponse(fromFile)));
    }

    private static class CacheAddNearCacheInvalidationListenerCodecHandler extends CacheAddNearCacheInvalidationListenerCodec.AbstractEventHandler {
        @Override
        public void handleCacheInvalidationEvent(java.lang.String name, com.hazelcast.internal.serialization.Data key, java.util.UUID sourceUuid, java.util.UUID partitionUuid, long sequence) {
            assertTrue(isEqual(aString, name));
            assertTrue(isEqual(null, key));
            assertTrue(isEqual(null, sourceUuid));
            assertTrue(isEqual(aUUID, partitionUuid));
            assertTrue(isEqual(aLong, sequence));
        }
        @Override
        public void handleCacheBatchInvalidationEvent(java.lang.String name, java.util.Collection<com.hazelcast.internal.serialization.Data> keys, java.util.Collection<java.util.UUID> sourceUuids, java.util.Collection<java.util.UUID> partitionUuids, java.util.Collection<java.lang.Long> sequences) {
            assertTrue(isEqual(aString, name));
            assertTrue(isEqual(aListOfData, keys));
            assertTrue(isEqual(aListOfUUIDs, sourceUuids));
            assertTrue(isEqual(aListOfUUIDs, partitionUuids));
            assertTrue(isEqual(aListOfLongs, sequences));
        }
    }

    @Test
    public void test_CacheAddNearCacheInvalidationListenerCodec_handleCacheInvalidationEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 603;
=======
        int fileClientMessageIndex = 609;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheAddNearCacheInvalidationListenerCodecHandler handler = new CacheAddNearCacheInvalidationListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_CacheAddNearCacheInvalidationListenerCodec_handleCacheBatchInvalidationEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 604;
=======
        int fileClientMessageIndex = 610;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheAddNearCacheInvalidationListenerCodecHandler handler = new CacheAddNearCacheInvalidationListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_CacheFetchNearCacheInvalidationMetadataCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 605;
=======
        int fileClientMessageIndex = 611;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheFetchNearCacheInvalidationMetadataCodec.encodeRequest(aListOfStrings, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheFetchNearCacheInvalidationMetadataCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 606;
=======
        int fileClientMessageIndex = 612;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheFetchNearCacheInvalidationMetadataCodec.ResponseParameters parameters = CacheFetchNearCacheInvalidationMetadataCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aListOfStringToListOfIntegerToLong, parameters.namePartitionSequenceList));
        assertTrue(isEqual(aListOfIntegerToUUID, parameters.partitionUuidList));
    }

    @Test
    public void test_CacheEventJournalSubscribeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 607;
=======
        int fileClientMessageIndex = 613;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheEventJournalSubscribeCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheEventJournalSubscribeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 608;
=======
        int fileClientMessageIndex = 614;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheEventJournalSubscribeCodec.ResponseParameters parameters = CacheEventJournalSubscribeCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aLong, parameters.oldestSequence));
        assertTrue(isEqual(aLong, parameters.newestSequence));
    }

    @Test
    public void test_CacheEventJournalReadCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 609;
=======
        int fileClientMessageIndex = 615;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheEventJournalReadCodec.encodeRequest(aString, aLong, anInt, anInt, null, null);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheEventJournalReadCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 610;
=======
        int fileClientMessageIndex = 616;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheEventJournalReadCodec.ResponseParameters parameters = CacheEventJournalReadCodec.decodeResponse(fromFile);
        assertTrue(isEqual(anInt, parameters.readCount));
        assertTrue(isEqual(aListOfData, parameters.items));
        assertTrue(isEqual(null, parameters.itemSeqs));
        assertTrue(isEqual(aLong, parameters.nextSeq));
    }

    @Test
    public void test_CacheSetExpiryPolicyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 611;
=======
        int fileClientMessageIndex = 617;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheSetExpiryPolicyCodec.encodeRequest(aString, aListOfData, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheSetExpiryPolicyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 612;
=======
        int fileClientMessageIndex = 618;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, CacheSetExpiryPolicyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_XATransactionClearRemoteCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 613;
=======
        int fileClientMessageIndex = 619;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = XATransactionClearRemoteCodec.encodeRequest(anXid);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_XATransactionClearRemoteCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 614;
=======
        int fileClientMessageIndex = 620;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_XATransactionCollectTransactionsCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 615;
=======
        int fileClientMessageIndex = 621;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = XATransactionCollectTransactionsCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_XATransactionCollectTransactionsCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 616;
=======
        int fileClientMessageIndex = 622;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfXids, XATransactionCollectTransactionsCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_XATransactionFinalizeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 617;
=======
        int fileClientMessageIndex = 623;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = XATransactionFinalizeCodec.encodeRequest(anXid, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_XATransactionFinalizeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 618;
=======
        int fileClientMessageIndex = 624;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_XATransactionCommitCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 619;
=======
        int fileClientMessageIndex = 625;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = XATransactionCommitCodec.encodeRequest(aUUID, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_XATransactionCommitCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 620;
=======
        int fileClientMessageIndex = 626;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_XATransactionCreateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 621;
=======
        int fileClientMessageIndex = 627;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = XATransactionCreateCodec.encodeRequest(anXid, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_XATransactionCreateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 622;
=======
        int fileClientMessageIndex = 628;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, XATransactionCreateCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_XATransactionPrepareCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 623;
=======
        int fileClientMessageIndex = 629;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = XATransactionPrepareCodec.encodeRequest(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_XATransactionPrepareCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 624;
=======
        int fileClientMessageIndex = 630;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_XATransactionRollbackCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 625;
=======
        int fileClientMessageIndex = 631;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = XATransactionRollbackCodec.encodeRequest(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_XATransactionRollbackCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 626;
=======
        int fileClientMessageIndex = 632;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_TransactionCommitCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 627;
=======
        int fileClientMessageIndex = 633;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionCommitCodec.encodeRequest(aUUID, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionCommitCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 628;
=======
        int fileClientMessageIndex = 634;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_TransactionCreateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 629;
=======
        int fileClientMessageIndex = 635;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionCreateCodec.encodeRequest(aLong, anInt, anInt, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionCreateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 630;
=======
        int fileClientMessageIndex = 636;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, TransactionCreateCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_TransactionRollbackCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 631;
=======
        int fileClientMessageIndex = 637;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionRollbackCodec.encodeRequest(aUUID, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionRollbackCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 632;
=======
        int fileClientMessageIndex = 638;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_ContinuousQueryPublisherCreateWithValueCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 633;
=======
        int fileClientMessageIndex = 639;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ContinuousQueryPublisherCreateWithValueCodec.encodeRequest(aString, aString, aData, anInt, anInt, aLong, aBoolean, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ContinuousQueryPublisherCreateWithValueCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 634;
=======
        int fileClientMessageIndex = 640;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfDataToData, ContinuousQueryPublisherCreateWithValueCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ContinuousQueryPublisherCreateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 635;
=======
        int fileClientMessageIndex = 641;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ContinuousQueryPublisherCreateCodec.encodeRequest(aString, aString, aData, anInt, anInt, aLong, aBoolean, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ContinuousQueryPublisherCreateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 636;
=======
        int fileClientMessageIndex = 642;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfData, ContinuousQueryPublisherCreateCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ContinuousQueryMadePublishableCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 637;
=======
        int fileClientMessageIndex = 643;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ContinuousQueryMadePublishableCodec.encodeRequest(aString, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ContinuousQueryMadePublishableCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 638;
=======
        int fileClientMessageIndex = 644;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ContinuousQueryMadePublishableCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ContinuousQueryAddListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 639;
=======
        int fileClientMessageIndex = 645;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ContinuousQueryAddListenerCodec.encodeRequest(aString, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ContinuousQueryAddListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 640;
=======
        int fileClientMessageIndex = 646;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, ContinuousQueryAddListenerCodec.decodeResponse(fromFile)));
    }

    private static class ContinuousQueryAddListenerCodecHandler extends ContinuousQueryAddListenerCodec.AbstractEventHandler {
        @Override
        public void handleQueryCacheSingleEvent(com.hazelcast.map.impl.querycache.event.QueryCacheEventData data) {
            assertTrue(isEqual(aQueryCacheEventData, data));
        }
        @Override
        public void handleQueryCacheBatchEvent(java.util.Collection<com.hazelcast.map.impl.querycache.event.QueryCacheEventData> events, java.lang.String source, int partitionId) {
            assertTrue(isEqual(aListOfQueryCacheEventData, events));
            assertTrue(isEqual(aString, source));
            assertTrue(isEqual(anInt, partitionId));
        }
    }

    @Test
    public void test_ContinuousQueryAddListenerCodec_handleQueryCacheSingleEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 641;
=======
        int fileClientMessageIndex = 647;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ContinuousQueryAddListenerCodecHandler handler = new ContinuousQueryAddListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_ContinuousQueryAddListenerCodec_handleQueryCacheBatchEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 642;
=======
        int fileClientMessageIndex = 648;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ContinuousQueryAddListenerCodecHandler handler = new ContinuousQueryAddListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_ContinuousQuerySetReadCursorCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 643;
=======
        int fileClientMessageIndex = 649;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ContinuousQuerySetReadCursorCodec.encodeRequest(aString, aString, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ContinuousQuerySetReadCursorCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 644;
=======
        int fileClientMessageIndex = 650;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ContinuousQuerySetReadCursorCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ContinuousQueryDestroyCacheCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 645;
=======
        int fileClientMessageIndex = 651;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ContinuousQueryDestroyCacheCodec.encodeRequest(aString, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ContinuousQueryDestroyCacheCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 646;
=======
        int fileClientMessageIndex = 652;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ContinuousQueryDestroyCacheCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_RingbufferSizeCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 647;
=======
        int fileClientMessageIndex = 653;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferSizeCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferSizeCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 648;
=======
        int fileClientMessageIndex = 654;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, RingbufferSizeCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_RingbufferTailSequenceCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 649;
=======
        int fileClientMessageIndex = 655;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferTailSequenceCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferTailSequenceCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 650;
=======
        int fileClientMessageIndex = 656;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, RingbufferTailSequenceCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_RingbufferHeadSequenceCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 651;
=======
        int fileClientMessageIndex = 657;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferHeadSequenceCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferHeadSequenceCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 652;
=======
        int fileClientMessageIndex = 658;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, RingbufferHeadSequenceCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_RingbufferCapacityCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 653;
=======
        int fileClientMessageIndex = 659;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferCapacityCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferCapacityCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 654;
=======
        int fileClientMessageIndex = 660;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, RingbufferCapacityCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_RingbufferRemainingCapacityCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 655;
=======
        int fileClientMessageIndex = 661;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferRemainingCapacityCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferRemainingCapacityCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 656;
=======
        int fileClientMessageIndex = 662;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, RingbufferRemainingCapacityCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_RingbufferAddCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 657;
=======
        int fileClientMessageIndex = 663;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferAddCodec.encodeRequest(aString, anInt, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferAddCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 658;
=======
        int fileClientMessageIndex = 664;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, RingbufferAddCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_RingbufferReadOneCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 659;
=======
        int fileClientMessageIndex = 665;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferReadOneCodec.encodeRequest(aString, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferReadOneCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 660;
=======
        int fileClientMessageIndex = 666;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, RingbufferReadOneCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_RingbufferAddAllCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 661;
=======
        int fileClientMessageIndex = 667;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferAddAllCodec.encodeRequest(aString, aListOfData, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferAddAllCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 662;
=======
        int fileClientMessageIndex = 668;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, RingbufferAddAllCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_RingbufferReadManyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 663;
=======
        int fileClientMessageIndex = 669;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferReadManyCodec.encodeRequest(aString, aLong, anInt, anInt, null);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferReadManyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 664;
=======
        int fileClientMessageIndex = 670;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        RingbufferReadManyCodec.ResponseParameters parameters = RingbufferReadManyCodec.decodeResponse(fromFile);
        assertTrue(isEqual(anInt, parameters.readCount));
        assertTrue(isEqual(aListOfData, parameters.items));
        assertTrue(isEqual(null, parameters.itemSeqs));
        assertTrue(isEqual(aLong, parameters.nextSeq));
    }

    @Test
    public void test_DurableExecutorShutdownCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 665;
=======
        int fileClientMessageIndex = 671;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DurableExecutorShutdownCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DurableExecutorShutdownCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 666;
=======
        int fileClientMessageIndex = 672;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DurableExecutorIsShutdownCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 667;
=======
        int fileClientMessageIndex = 673;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DurableExecutorIsShutdownCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DurableExecutorIsShutdownCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 668;
=======
        int fileClientMessageIndex = 674;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, DurableExecutorIsShutdownCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_DurableExecutorSubmitToPartitionCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 669;
=======
        int fileClientMessageIndex = 675;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DurableExecutorSubmitToPartitionCodec.encodeRequest(aString, aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DurableExecutorSubmitToPartitionCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 670;
=======
        int fileClientMessageIndex = 676;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, DurableExecutorSubmitToPartitionCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_DurableExecutorRetrieveResultCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 671;
=======
        int fileClientMessageIndex = 677;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DurableExecutorRetrieveResultCodec.encodeRequest(aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DurableExecutorRetrieveResultCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 672;
=======
        int fileClientMessageIndex = 678;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, DurableExecutorRetrieveResultCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_DurableExecutorDisposeResultCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 673;
=======
        int fileClientMessageIndex = 679;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DurableExecutorDisposeResultCodec.encodeRequest(aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DurableExecutorDisposeResultCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 674;
=======
        int fileClientMessageIndex = 680;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DurableExecutorRetrieveAndDisposeResultCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 675;
=======
        int fileClientMessageIndex = 681;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DurableExecutorRetrieveAndDisposeResultCodec.encodeRequest(aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DurableExecutorRetrieveAndDisposeResultCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 676;
=======
        int fileClientMessageIndex = 682;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, DurableExecutorRetrieveAndDisposeResultCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CardinalityEstimatorAddCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 677;
=======
        int fileClientMessageIndex = 683;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CardinalityEstimatorAddCodec.encodeRequest(aString, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CardinalityEstimatorAddCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 678;
=======
        int fileClientMessageIndex = 684;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_CardinalityEstimatorEstimateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 679;
=======
        int fileClientMessageIndex = 685;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CardinalityEstimatorEstimateCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CardinalityEstimatorEstimateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 680;
=======
        int fileClientMessageIndex = 686;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, CardinalityEstimatorEstimateCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ScheduledExecutorShutdownCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 681;
=======
        int fileClientMessageIndex = 687;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorShutdownCodec.encodeRequest(aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorShutdownCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 682;
=======
        int fileClientMessageIndex = 688;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_ScheduledExecutorSubmitToPartitionCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 683;
=======
        int fileClientMessageIndex = 689;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorSubmitToPartitionCodec.encodeRequest(aString, aByte, aString, aData, aLong, aLong, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorSubmitToPartitionCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 684;
=======
        int fileClientMessageIndex = 690;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_ScheduledExecutorSubmitToMemberCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 685;
=======
        int fileClientMessageIndex = 691;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorSubmitToMemberCodec.encodeRequest(aString, aUUID, aByte, aString, aData, aLong, aLong, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorSubmitToMemberCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 686;
=======
        int fileClientMessageIndex = 692;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_ScheduledExecutorGetAllScheduledFuturesCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 687;
=======
        int fileClientMessageIndex = 693;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorGetAllScheduledFuturesCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorGetAllScheduledFuturesCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 688;
=======
        int fileClientMessageIndex = 694;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfScheduledTaskHandler, ScheduledExecutorGetAllScheduledFuturesCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ScheduledExecutorGetStatsFromPartitionCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 689;
=======
        int fileClientMessageIndex = 695;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorGetStatsFromPartitionCodec.encodeRequest(aString, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorGetStatsFromPartitionCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 690;
=======
        int fileClientMessageIndex = 696;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorGetStatsFromPartitionCodec.ResponseParameters parameters = ScheduledExecutorGetStatsFromPartitionCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aLong, parameters.lastIdleTimeNanos));
        assertTrue(isEqual(aLong, parameters.totalIdleTimeNanos));
        assertTrue(isEqual(aLong, parameters.totalRuns));
        assertTrue(isEqual(aLong, parameters.totalRunTimeNanos));
        assertTrue(isEqual(aLong, parameters.lastRunDurationNanos));
    }

    @Test
    public void test_ScheduledExecutorGetStatsFromMemberCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 691;
=======
        int fileClientMessageIndex = 697;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorGetStatsFromMemberCodec.encodeRequest(aString, aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorGetStatsFromMemberCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 692;
=======
        int fileClientMessageIndex = 698;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorGetStatsFromMemberCodec.ResponseParameters parameters = ScheduledExecutorGetStatsFromMemberCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aLong, parameters.lastIdleTimeNanos));
        assertTrue(isEqual(aLong, parameters.totalIdleTimeNanos));
        assertTrue(isEqual(aLong, parameters.totalRuns));
        assertTrue(isEqual(aLong, parameters.totalRunTimeNanos));
        assertTrue(isEqual(aLong, parameters.lastRunDurationNanos));
    }

    @Test
    public void test_ScheduledExecutorGetDelayFromPartitionCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 693;
=======
        int fileClientMessageIndex = 699;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorGetDelayFromPartitionCodec.encodeRequest(aString, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorGetDelayFromPartitionCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 694;
=======
        int fileClientMessageIndex = 700;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, ScheduledExecutorGetDelayFromPartitionCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ScheduledExecutorGetDelayFromMemberCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 695;
=======
        int fileClientMessageIndex = 701;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorGetDelayFromMemberCodec.encodeRequest(aString, aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorGetDelayFromMemberCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 696;
=======
        int fileClientMessageIndex = 702;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, ScheduledExecutorGetDelayFromMemberCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ScheduledExecutorCancelFromPartitionCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 697;
=======
        int fileClientMessageIndex = 703;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorCancelFromPartitionCodec.encodeRequest(aString, aString, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorCancelFromPartitionCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 698;
=======
        int fileClientMessageIndex = 704;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ScheduledExecutorCancelFromPartitionCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ScheduledExecutorCancelFromMemberCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 699;
=======
        int fileClientMessageIndex = 705;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorCancelFromMemberCodec.encodeRequest(aString, aString, aUUID, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorCancelFromMemberCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 700;
=======
        int fileClientMessageIndex = 706;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ScheduledExecutorCancelFromMemberCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ScheduledExecutorIsCancelledFromPartitionCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 701;
=======
        int fileClientMessageIndex = 707;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorIsCancelledFromPartitionCodec.encodeRequest(aString, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorIsCancelledFromPartitionCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 702;
=======
        int fileClientMessageIndex = 708;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ScheduledExecutorIsCancelledFromPartitionCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ScheduledExecutorIsCancelledFromMemberCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 703;
=======
        int fileClientMessageIndex = 709;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorIsCancelledFromMemberCodec.encodeRequest(aString, aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorIsCancelledFromMemberCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 704;
=======
        int fileClientMessageIndex = 710;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ScheduledExecutorIsCancelledFromMemberCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ScheduledExecutorIsDoneFromPartitionCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 705;
=======
        int fileClientMessageIndex = 711;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorIsDoneFromPartitionCodec.encodeRequest(aString, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorIsDoneFromPartitionCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 706;
=======
        int fileClientMessageIndex = 712;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ScheduledExecutorIsDoneFromPartitionCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ScheduledExecutorIsDoneFromMemberCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 707;
=======
        int fileClientMessageIndex = 713;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorIsDoneFromMemberCodec.encodeRequest(aString, aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorIsDoneFromMemberCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 708;
=======
        int fileClientMessageIndex = 714;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ScheduledExecutorIsDoneFromMemberCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ScheduledExecutorGetResultFromPartitionCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 709;
=======
        int fileClientMessageIndex = 715;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorGetResultFromPartitionCodec.encodeRequest(aString, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorGetResultFromPartitionCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 710;
=======
        int fileClientMessageIndex = 716;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, ScheduledExecutorGetResultFromPartitionCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ScheduledExecutorGetResultFromMemberCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 711;
=======
        int fileClientMessageIndex = 717;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorGetResultFromMemberCodec.encodeRequest(aString, aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorGetResultFromMemberCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 712;
=======
        int fileClientMessageIndex = 718;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, ScheduledExecutorGetResultFromMemberCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_ScheduledExecutorDisposeFromPartitionCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 713;
=======
        int fileClientMessageIndex = 719;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorDisposeFromPartitionCodec.encodeRequest(aString, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorDisposeFromPartitionCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 714;
=======
        int fileClientMessageIndex = 720;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_ScheduledExecutorDisposeFromMemberCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 715;
=======
        int fileClientMessageIndex = 721;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorDisposeFromMemberCodec.encodeRequest(aString, aString, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorDisposeFromMemberCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 716;
=======
        int fileClientMessageIndex = 722;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddMultiMapConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 717;
=======
        int fileClientMessageIndex = 723;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddMultiMapConfigCodec.encodeRequest(aString, aString, null, aBoolean, anInt, anInt, aBoolean, null, aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddMultiMapConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 718;
=======
        int fileClientMessageIndex = 724;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddRingbufferConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 719;
=======
        int fileClientMessageIndex = 725;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddRingbufferConfigCodec.encodeRequest(aString, anInt, anInt, anInt, anInt, aString, null, null, aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddRingbufferConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 720;
=======
        int fileClientMessageIndex = 726;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddCardinalityEstimatorConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 721;
=======
        int fileClientMessageIndex = 727;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddCardinalityEstimatorConfigCodec.encodeRequest(aString, anInt, anInt, null, aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddCardinalityEstimatorConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 722;
=======
        int fileClientMessageIndex = 728;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddListConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 723;
=======
        int fileClientMessageIndex = 729;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddListConfigCodec.encodeRequest(aString, null, anInt, anInt, anInt, aBoolean, null, aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddListConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 724;
=======
        int fileClientMessageIndex = 730;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddSetConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 725;
=======
        int fileClientMessageIndex = 731;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddSetConfigCodec.encodeRequest(aString, null, anInt, anInt, anInt, aBoolean, null, aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddSetConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 726;
=======
        int fileClientMessageIndex = 732;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddReplicatedMapConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 727;
=======
        int fileClientMessageIndex = 733;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddReplicatedMapConfigCodec.encodeRequest(aString, aString, aBoolean, aBoolean, aString, null, null, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddReplicatedMapConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 728;
=======
        int fileClientMessageIndex = 734;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddTopicConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 729;
=======
        int fileClientMessageIndex = 735;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddTopicConfigCodec.encodeRequest(aString, aBoolean, aBoolean, aBoolean, null);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddTopicConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 730;
=======
        int fileClientMessageIndex = 736;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddExecutorConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 731;
=======
        int fileClientMessageIndex = 737;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddExecutorConfigCodec.encodeRequest(aString, anInt, anInt, aBoolean, null);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddExecutorConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 732;
=======
        int fileClientMessageIndex = 738;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddDurableExecutorConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 733;
=======
        int fileClientMessageIndex = 739;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddDurableExecutorConfigCodec.encodeRequest(aString, anInt, anInt, anInt, null, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddDurableExecutorConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 734;
=======
        int fileClientMessageIndex = 740;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddScheduledExecutorConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 735;
=======
        int fileClientMessageIndex = 741;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddScheduledExecutorConfigCodec.encodeRequest(aString, anInt, anInt, anInt, null, aString, anInt, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddScheduledExecutorConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 736;
=======
        int fileClientMessageIndex = 742;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddQueueConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 737;
=======
        int fileClientMessageIndex = 743;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddQueueConfigCodec.encodeRequest(aString, null, anInt, anInt, anInt, anInt, aBoolean, null, null, aString, anInt, null);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddQueueConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 738;
=======
        int fileClientMessageIndex = 744;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddMapConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 739;
=======
        int fileClientMessageIndex = 745;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddMapConfigCodec.encodeRequest(aString, anInt, anInt, anInt, anInt, null, aBoolean, aString, aString, anInt, aString, null, null, aBoolean, null, null, null, null, null, null, null, null, null, null, null, null, anInt, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddMapConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 740;
=======
        int fileClientMessageIndex = 746;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddReliableTopicConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 741;
=======
        int fileClientMessageIndex = 747;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddReliableTopicConfigCodec.encodeRequest(aString, null, anInt, aBoolean, aString, null);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddReliableTopicConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 742;
=======
        int fileClientMessageIndex = 748;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddCacheConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 743;
        ClientMessage encoded = DynamicConfigAddCacheConfigCodec.encodeRequest(aString, null, null, aBoolean, aBoolean, aBoolean, aBoolean, null, null, null, null, anInt, anInt, aString, null, null, anInt, aBoolean, null, null, null, null, null, null, null, null, null);
=======
        int fileClientMessageIndex = 749;
        ClientMessage encoded = DynamicConfigAddCacheConfigCodec.encodeRequest(aString, null, null, aBoolean, aBoolean, aBoolean, aBoolean, null, null, null, null, anInt, anInt, aString, null, null, anInt, aBoolean, null, null, null, null, null, null, null, null);
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddCacheConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 744;
=======
        int fileClientMessageIndex = 750;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddFlakeIdGeneratorConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 745;
=======
        int fileClientMessageIndex = 751;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddFlakeIdGeneratorConfigCodec.encodeRequest(aString, anInt, aLong, aBoolean, aLong, aLong, anInt, anInt, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddFlakeIdGeneratorConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 746;
=======
        int fileClientMessageIndex = 752;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddPNCounterConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 747;
=======
        int fileClientMessageIndex = 753;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddPNCounterConfigCodec.encodeRequest(aString, anInt, aBoolean, null);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddPNCounterConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 748;
=======
        int fileClientMessageIndex = 754;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_FlakeIdGeneratorNewIdBatchCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 749;
=======
        int fileClientMessageIndex = 755;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = FlakeIdGeneratorNewIdBatchCodec.encodeRequest(aString, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_FlakeIdGeneratorNewIdBatchCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 750;
=======
        int fileClientMessageIndex = 756;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        FlakeIdGeneratorNewIdBatchCodec.ResponseParameters parameters = FlakeIdGeneratorNewIdBatchCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aLong, parameters.base));
        assertTrue(isEqual(aLong, parameters.increment));
        assertTrue(isEqual(anInt, parameters.batchSize));
    }

    @Test
    public void test_PNCounterGetCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 751;
=======
        int fileClientMessageIndex = 757;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = PNCounterGetCodec.encodeRequest(aString, aListOfUuidToLong, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_PNCounterGetCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 752;
=======
        int fileClientMessageIndex = 758;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        PNCounterGetCodec.ResponseParameters parameters = PNCounterGetCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aLong, parameters.value));
        assertTrue(isEqual(aListOfUuidToLong, parameters.replicaTimestamps));
        assertTrue(isEqual(anInt, parameters.replicaCount));
    }

    @Test
    public void test_PNCounterAddCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 753;
=======
        int fileClientMessageIndex = 759;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = PNCounterAddCodec.encodeRequest(aString, aLong, aBoolean, aListOfUuidToLong, aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_PNCounterAddCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 754;
=======
        int fileClientMessageIndex = 760;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        PNCounterAddCodec.ResponseParameters parameters = PNCounterAddCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aLong, parameters.value));
        assertTrue(isEqual(aListOfUuidToLong, parameters.replicaTimestamps));
        assertTrue(isEqual(anInt, parameters.replicaCount));
    }

    @Test
    public void test_PNCounterGetConfiguredReplicaCountCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 755;
=======
        int fileClientMessageIndex = 761;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = PNCounterGetConfiguredReplicaCountCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_PNCounterGetConfiguredReplicaCountCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 756;
=======
        int fileClientMessageIndex = 762;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, PNCounterGetConfiguredReplicaCountCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CPGroupCreateCPGroupCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 757;
=======
        int fileClientMessageIndex = 763;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPGroupCreateCPGroupCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPGroupCreateCPGroupCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 758;
=======
        int fileClientMessageIndex = 764;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aRaftGroupId, CPGroupCreateCPGroupCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CPGroupDestroyCPObjectCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 759;
=======
        int fileClientMessageIndex = 765;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPGroupDestroyCPObjectCodec.encodeRequest(aRaftGroupId, aString, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPGroupDestroyCPObjectCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 760;
=======
        int fileClientMessageIndex = 766;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_CPSessionCreateSessionCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 761;
=======
        int fileClientMessageIndex = 767;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSessionCreateSessionCodec.encodeRequest(aRaftGroupId, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSessionCreateSessionCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 762;
=======
        int fileClientMessageIndex = 768;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CPSessionCreateSessionCodec.ResponseParameters parameters = CPSessionCreateSessionCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aLong, parameters.sessionId));
        assertTrue(isEqual(aLong, parameters.ttlMillis));
        assertTrue(isEqual(aLong, parameters.heartbeatMillis));
    }

    @Test
    public void test_CPSessionCloseSessionCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 763;
=======
        int fileClientMessageIndex = 769;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSessionCloseSessionCodec.encodeRequest(aRaftGroupId, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSessionCloseSessionCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 764;
=======
        int fileClientMessageIndex = 770;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, CPSessionCloseSessionCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CPSessionHeartbeatSessionCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 765;
=======
        int fileClientMessageIndex = 771;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSessionHeartbeatSessionCodec.encodeRequest(aRaftGroupId, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSessionHeartbeatSessionCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 766;
=======
        int fileClientMessageIndex = 772;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_CPSessionGenerateThreadIdCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 767;
=======
        int fileClientMessageIndex = 773;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSessionGenerateThreadIdCodec.encodeRequest(aRaftGroupId);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSessionGenerateThreadIdCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 768;
=======
        int fileClientMessageIndex = 774;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, CPSessionGenerateThreadIdCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MCReadMetricsCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 769;
=======
        int fileClientMessageIndex = 775;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCReadMetricsCodec.encodeRequest(aUUID, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCReadMetricsCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 770;
=======
        int fileClientMessageIndex = 776;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MCReadMetricsCodec.ResponseParameters parameters = MCReadMetricsCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aListOfLongToByteArray, parameters.elements));
        assertTrue(isEqual(aLong, parameters.nextSequence));
    }

    @Test
    public void test_MCChangeClusterStateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 771;
=======
        int fileClientMessageIndex = 777;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCChangeClusterStateCodec.encodeRequest(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCChangeClusterStateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 772;
=======
        int fileClientMessageIndex = 778;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCGetMapConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 773;
=======
        int fileClientMessageIndex = 779;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCGetMapConfigCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCGetMapConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 774;
=======
        int fileClientMessageIndex = 780;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MCGetMapConfigCodec.ResponseParameters parameters = MCGetMapConfigCodec.decodeResponse(fromFile);
        assertTrue(isEqual(anInt, parameters.inMemoryFormat));
        assertTrue(isEqual(anInt, parameters.backupCount));
        assertTrue(isEqual(anInt, parameters.asyncBackupCount));
        assertTrue(isEqual(anInt, parameters.timeToLiveSeconds));
        assertTrue(isEqual(anInt, parameters.maxIdleSeconds));
        assertTrue(isEqual(anInt, parameters.maxSize));
        assertTrue(isEqual(anInt, parameters.maxSizePolicy));
        assertTrue(isEqual(aBoolean, parameters.readBackupData));
        assertTrue(isEqual(anInt, parameters.evictionPolicy));
        assertTrue(isEqual(aString, parameters.mergePolicy));
    }

    @Test
    public void test_MCUpdateMapConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 775;
=======
        int fileClientMessageIndex = 781;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCUpdateMapConfigCodec.encodeRequest(aString, anInt, anInt, anInt, aBoolean, anInt, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCUpdateMapConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 776;
=======
        int fileClientMessageIndex = 782;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCGetMemberConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 777;
=======
        int fileClientMessageIndex = 783;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCGetMemberConfigCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCGetMemberConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 778;
=======
        int fileClientMessageIndex = 784;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MCGetMemberConfigCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MCRunGcCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 779;
=======
        int fileClientMessageIndex = 785;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCRunGcCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCRunGcCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 780;
=======
        int fileClientMessageIndex = 786;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCGetThreadDumpCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 781;
=======
        int fileClientMessageIndex = 787;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCGetThreadDumpCodec.encodeRequest(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCGetThreadDumpCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 782;
=======
        int fileClientMessageIndex = 788;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MCGetThreadDumpCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MCShutdownMemberCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 783;
=======
        int fileClientMessageIndex = 789;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCShutdownMemberCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCShutdownMemberCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 784;
=======
        int fileClientMessageIndex = 790;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCPromoteLiteMemberCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 785;
=======
        int fileClientMessageIndex = 791;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCPromoteLiteMemberCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCPromoteLiteMemberCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 786;
=======
        int fileClientMessageIndex = 792;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCGetSystemPropertiesCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 787;
=======
        int fileClientMessageIndex = 793;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCGetSystemPropertiesCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCGetSystemPropertiesCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 788;
=======
        int fileClientMessageIndex = 794;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfStringToString, MCGetSystemPropertiesCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MCGetTimedMemberStateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 789;
=======
        int fileClientMessageIndex = 795;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCGetTimedMemberStateCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCGetTimedMemberStateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 790;
=======
        int fileClientMessageIndex = 796;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MCGetTimedMemberStateCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MCMatchMCConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 791;
=======
        int fileClientMessageIndex = 797;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCMatchMCConfigCodec.encodeRequest(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCMatchMCConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 792;
=======
        int fileClientMessageIndex = 798;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MCMatchMCConfigCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MCApplyMCConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 793;
=======
        int fileClientMessageIndex = 799;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCApplyMCConfigCodec.encodeRequest(aString, anInt, aListOfClientBwListEntries);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCApplyMCConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 794;
=======
        int fileClientMessageIndex = 800;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCGetClusterMetadataCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 795;
=======
        int fileClientMessageIndex = 801;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCGetClusterMetadataCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCGetClusterMetadataCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 796;
=======
        int fileClientMessageIndex = 802;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MCGetClusterMetadataCodec.ResponseParameters parameters = MCGetClusterMetadataCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aByte, parameters.currentState));
        assertTrue(isEqual(aString, parameters.memberVersion));
        assertTrue(isEqual(null, parameters.jetVersion));
        assertTrue(isEqual(aLong, parameters.clusterTime));
    }

    @Test
    public void test_MCShutdownClusterCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 797;
=======
        int fileClientMessageIndex = 803;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCShutdownClusterCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCShutdownClusterCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 798;
=======
        int fileClientMessageIndex = 804;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCChangeClusterVersionCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 799;
=======
        int fileClientMessageIndex = 805;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCChangeClusterVersionCodec.encodeRequest(aByte, aByte);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCChangeClusterVersionCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 800;
=======
        int fileClientMessageIndex = 806;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCRunScriptCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 801;
=======
        int fileClientMessageIndex = 807;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCRunScriptCodec.encodeRequest(aString, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCRunScriptCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 802;
=======
        int fileClientMessageIndex = 808;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MCRunScriptCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MCRunConsoleCommandCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 803;
=======
        int fileClientMessageIndex = 809;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCRunConsoleCommandCodec.encodeRequest(null, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCRunConsoleCommandCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 804;
=======
        int fileClientMessageIndex = 810;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MCRunConsoleCommandCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MCChangeWanReplicationStateCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 805;
=======
        int fileClientMessageIndex = 811;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCChangeWanReplicationStateCodec.encodeRequest(aString, aString, aByte);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCChangeWanReplicationStateCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 806;
=======
        int fileClientMessageIndex = 812;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCClearWanQueuesCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 807;
=======
        int fileClientMessageIndex = 813;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCClearWanQueuesCodec.encodeRequest(aString, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCClearWanQueuesCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 808;
=======
        int fileClientMessageIndex = 814;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCAddWanBatchPublisherConfigCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 809;
=======
        int fileClientMessageIndex = 815;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCAddWanBatchPublisherConfigCodec.encodeRequest(aString, aString, null, aString, anInt, anInt, anInt, anInt, anInt, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCAddWanBatchPublisherConfigCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 810;
=======
        int fileClientMessageIndex = 816;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MCAddWanBatchPublisherConfigCodec.ResponseParameters parameters = MCAddWanBatchPublisherConfigCodec.decodeResponse(fromFile);
        assertTrue(isEqual(aListOfStrings, parameters.addedPublisherIds));
        assertTrue(isEqual(aListOfStrings, parameters.ignoredPublisherIds));
    }

    @Test
    public void test_MCWanSyncMapCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 811;
=======
        int fileClientMessageIndex = 817;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCWanSyncMapCodec.encodeRequest(aString, aString, anInt, null);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCWanSyncMapCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 812;
=======
        int fileClientMessageIndex = 818;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, MCWanSyncMapCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MCCheckWanConsistencyCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 813;
=======
        int fileClientMessageIndex = 819;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCCheckWanConsistencyCodec.encodeRequest(aString, aString, null);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCCheckWanConsistencyCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 814;
=======
        int fileClientMessageIndex = 820;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(null, MCCheckWanConsistencyCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MCPollMCEventsCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 815;
=======
        int fileClientMessageIndex = 821;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCPollMCEventsCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCPollMCEventsCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 816;
=======
        int fileClientMessageIndex = 822;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfMCEvents, MCPollMCEventsCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MCGetCPMembersCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 817;
=======
        int fileClientMessageIndex = 823;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCGetCPMembersCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCGetCPMembersCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 818;
=======
        int fileClientMessageIndex = 824;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfUUIDToUUID, MCGetCPMembersCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MCPromoteToCPMemberCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 819;
=======
        int fileClientMessageIndex = 825;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCPromoteToCPMemberCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCPromoteToCPMemberCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 820;
=======
        int fileClientMessageIndex = 826;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCRemoveCPMemberCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 821;
=======
        int fileClientMessageIndex = 827;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCRemoveCPMemberCodec.encodeRequest(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCRemoveCPMemberCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 822;
=======
        int fileClientMessageIndex = 828;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCResetCPSubsystemCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 823;
=======
        int fileClientMessageIndex = 829;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCResetCPSubsystemCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCResetCPSubsystemCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 824;
=======
        int fileClientMessageIndex = 830;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCTriggerPartialStartCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 825;
=======
        int fileClientMessageIndex = 831;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCTriggerPartialStartCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCTriggerPartialStartCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 826;
=======
        int fileClientMessageIndex = 832;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MCTriggerPartialStartCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MCTriggerForceStartCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 827;
=======
        int fileClientMessageIndex = 833;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCTriggerForceStartCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCTriggerForceStartCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 828;
=======
        int fileClientMessageIndex = 834;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MCTriggerForceStartCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_MCTriggerHotRestartBackupCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 829;
=======
        int fileClientMessageIndex = 835;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCTriggerHotRestartBackupCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCTriggerHotRestartBackupCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 830;
=======
        int fileClientMessageIndex = 836;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCInterruptHotRestartBackupCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 831;
=======
        int fileClientMessageIndex = 837;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCInterruptHotRestartBackupCodec.encodeRequest();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCInterruptHotRestartBackupCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 832;
=======
        int fileClientMessageIndex = 838;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_SqlExecute_reservedCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 833;
=======
        int fileClientMessageIndex = 839;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SqlExecute_reservedCodec.encodeRequest(aString, aListOfData, aLong, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SqlExecute_reservedCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 834;
=======
        int fileClientMessageIndex = 840;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SqlExecute_reservedCodec.ResponseParameters parameters = SqlExecute_reservedCodec.decodeResponse(fromFile);
        assertTrue(isEqual(null, parameters.queryId));
        assertTrue(isEqual(null, parameters.rowMetadata));
        assertTrue(isEqual(null, parameters.rowPage));
        assertTrue(isEqual(aBoolean, parameters.rowPageLast));
        assertTrue(isEqual(aLong, parameters.updateCount));
        assertTrue(isEqual(null, parameters.error));
    }

    @Test
    public void test_SqlFetch_reservedCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 835;
=======
        int fileClientMessageIndex = 841;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SqlFetch_reservedCodec.encodeRequest(anSqlQueryId, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SqlFetch_reservedCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 836;
=======
        int fileClientMessageIndex = 842;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SqlFetch_reservedCodec.ResponseParameters parameters = SqlFetch_reservedCodec.decodeResponse(fromFile);
        assertTrue(isEqual(null, parameters.rowPage));
        assertTrue(isEqual(aBoolean, parameters.rowPageLast));
        assertTrue(isEqual(null, parameters.error));
    }

    @Test
    public void test_SqlCloseCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 837;
=======
        int fileClientMessageIndex = 843;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SqlCloseCodec.encodeRequest(anSqlQueryId);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SqlCloseCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 838;
=======
        int fileClientMessageIndex = 844;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_SqlExecuteCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 839;
=======
        int fileClientMessageIndex = 845;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SqlExecuteCodec.encodeRequest(aString, aListOfData, aLong, anInt, null, aByte, anSqlQueryId);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SqlExecuteCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 840;
=======
        int fileClientMessageIndex = 846;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SqlExecuteCodec.ResponseParameters parameters = SqlExecuteCodec.decodeResponse(fromFile);
        assertTrue(isEqual(null, parameters.rowMetadata));
        assertTrue(isEqual(null, parameters.rowPage));
        assertTrue(isEqual(aLong, parameters.updateCount));
        assertTrue(isEqual(null, parameters.error));
    }

    @Test
    public void test_SqlFetchCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 841;
=======
        int fileClientMessageIndex = 847;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SqlFetchCodec.encodeRequest(anSqlQueryId, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SqlFetchCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 842;
=======
        int fileClientMessageIndex = 848;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SqlFetchCodec.ResponseParameters parameters = SqlFetchCodec.decodeResponse(fromFile);
        assertTrue(isEqual(null, parameters.rowPage));
        assertTrue(isEqual(null, parameters.error));
    }

    @Test
    public void test_CPSubsystemAddMembershipListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 843;
=======
        int fileClientMessageIndex = 849;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSubsystemAddMembershipListenerCodec.encodeRequest(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSubsystemAddMembershipListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 844;
=======
        int fileClientMessageIndex = 850;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, CPSubsystemAddMembershipListenerCodec.decodeResponse(fromFile)));
    }

    private static class CPSubsystemAddMembershipListenerCodecHandler extends CPSubsystemAddMembershipListenerCodec.AbstractEventHandler {
        @Override
        public void handleMembershipEventEvent(com.hazelcast.cp.CPMember member, byte type) {
            assertTrue(isEqual(aCpMember, member));
            assertTrue(isEqual(aByte, type));
        }
    }

    @Test
    public void test_CPSubsystemAddMembershipListenerCodec_handleMembershipEventEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 845;
=======
        int fileClientMessageIndex = 851;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CPSubsystemAddMembershipListenerCodecHandler handler = new CPSubsystemAddMembershipListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_CPSubsystemRemoveMembershipListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 846;
=======
        int fileClientMessageIndex = 852;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSubsystemRemoveMembershipListenerCodec.encodeRequest(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSubsystemRemoveMembershipListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 847;
=======
        int fileClientMessageIndex = 853;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, CPSubsystemRemoveMembershipListenerCodec.decodeResponse(fromFile)));
    }

    @Test
    public void test_CPSubsystemAddGroupAvailabilityListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 848;
=======
        int fileClientMessageIndex = 854;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSubsystemAddGroupAvailabilityListenerCodec.encodeRequest(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSubsystemAddGroupAvailabilityListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 849;
=======
        int fileClientMessageIndex = 855;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, CPSubsystemAddGroupAvailabilityListenerCodec.decodeResponse(fromFile)));
    }

    private static class CPSubsystemAddGroupAvailabilityListenerCodecHandler extends CPSubsystemAddGroupAvailabilityListenerCodec.AbstractEventHandler {
        @Override
        public void handleGroupAvailabilityEventEvent(com.hazelcast.cp.internal.RaftGroupId groupId, java.util.Collection<com.hazelcast.cp.CPMember> members, java.util.Collection<com.hazelcast.cp.CPMember> unavailableMembers) {
            assertTrue(isEqual(aRaftGroupId, groupId));
            assertTrue(isEqual(aListOfCpMembers, members));
            assertTrue(isEqual(aListOfCpMembers, unavailableMembers));
        }
    }

    @Test
    public void test_CPSubsystemAddGroupAvailabilityListenerCodec_handleGroupAvailabilityEventEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 850;
=======
        int fileClientMessageIndex = 856;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CPSubsystemAddGroupAvailabilityListenerCodecHandler handler = new CPSubsystemAddGroupAvailabilityListenerCodecHandler();
        handler.handle(fromFile);
    }

    @Test
    public void test_CPSubsystemRemoveGroupAvailabilityListenerCodec_encodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 851;
=======
        int fileClientMessageIndex = 857;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSubsystemRemoveGroupAvailabilityListenerCodec.encodeRequest(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSubsystemRemoveGroupAvailabilityListenerCodec_decodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 852;
=======
        int fileClientMessageIndex = 858;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, CPSubsystemRemoveGroupAvailabilityListenerCodec.decodeResponse(fromFile)));
    }

    private void compareClientMessages(ClientMessage binaryMessage, ClientMessage encodedMessage) {
        ClientMessage.Frame binaryFrame, encodedFrame;

        ClientMessage.ForwardFrameIterator binaryFrameIterator = binaryMessage.frameIterator();
        ClientMessage.ForwardFrameIterator encodedFrameIterator = encodedMessage.frameIterator();
        assertTrue("Client message that is read from the binary file does not have any frames", binaryFrameIterator.hasNext());

        while (binaryFrameIterator.hasNext()) {
            binaryFrame = binaryFrameIterator.next();
            encodedFrame = encodedFrameIterator.next();
            assertNotNull("Encoded client message has less frames.", encodedFrame);

            if (binaryFrame.isEndFrame() && !encodedFrame.isEndFrame()) {
                if (encodedFrame.isBeginFrame()) {
                    HazelcastClientUtil.fastForwardToEndFrame(encodedFrameIterator);
                }
                encodedFrame = HazelcastClientUtil.fastForwardToEndFrame(encodedFrameIterator);
            }

            boolean isFinal = binaryFrameIterator.peekNext() == null;
            int flags = isFinal ? encodedFrame.flags | IS_FINAL_FLAG : encodedFrame.flags;
            assertArrayEquals("Frames have different contents", binaryFrame.content, Arrays.copyOf(encodedFrame.content, binaryFrame.content.length));
            assertEquals("Frames have different flags", binaryFrame.flags, flags);
        }
    }
}
