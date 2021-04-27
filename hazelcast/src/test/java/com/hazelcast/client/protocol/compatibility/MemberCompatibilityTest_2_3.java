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
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aHotRestartConfig;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfAttributeConfigs;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfCacheEventData;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfCacheSimpleEntryListenerConfigs;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfClientBwListEntries;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfCpMembers;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfData;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfDataToData;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfDataToListOfData;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfDistributedObjectInfo;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfIndexConfigs;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfIntegerToInteger;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfIntegerToUUID;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfListOfData;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfListenerConfigHolders;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfLongToByteArray;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfLongs;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfMCEvents;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfMemberInfos;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfQueryCacheConfigHolders;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfQueryCacheEventData;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfScheduledTaskHandler;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aListOfSqlColumnMetadata;
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
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aLongArray;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aMapStoreConfigHolder;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aMerkleTreeConfig;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aMigrationState;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aNearCacheConfigHolder;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aPagingPredicateHolder;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aQueryCacheEventData;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aQueueStoreConfigHolder;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aRaftGroupId;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aRingbufferStoreConfigHolder;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aSimpleEntryView;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aSqlPage;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aString;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aTimedExpiryPolicyFactoryConfig;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aUUID;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.aWanReplicationRef;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.anAddress;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.anAnchorDataListHolder;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.anEventJournalConfig;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.anEvictionConfigHolder;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.anIndexConfig;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.anInt;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.anSqlError;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.anSqlQueryId;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.anXid;
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.isEqual;
=======
import static com.hazelcast.client.protocol.compatibility.ReferenceObjects.*;
>>>>>>> 94a59c88de (Serialization 2.0)
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
<<<<<<< HEAD
=======
import static org.junit.Assert.assertFalse;
>>>>>>> 94a59c88de (Serialization 2.0)

@RunWith(HazelcastParallelClassRunner.class)
@Category({QuickTest.class, ParallelJVMTest.class})
public class MemberCompatibilityTest_2_3 {
<<<<<<< HEAD
    private final List<ClientMessage> clientMessages = new ArrayList<>();
=======
    private List<ClientMessage> clientMessages = new ArrayList<>();
>>>>>>> 94a59c88de (Serialization 2.0)

    @Before
    public void setUp() throws IOException {
        File file = new File(getClass().getResource("/2.3.protocol.compatibility.binary").getFile());
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
    public void test_ClientAuthenticationCodec_decodeRequest() {
        int fileClientMessageIndex = 0;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientAuthenticationCodec.RequestParameters parameters = ClientAuthenticationCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.clusterName));
        assertTrue(isEqual(aString, parameters.username));
        assertTrue(isEqual(aString, parameters.password));
        assertTrue(isEqual(aUUID, parameters.uuid));
        assertTrue(isEqual(aString, parameters.clientType));
        assertTrue(isEqual(aByte, parameters.serializationVersion));
        assertTrue(isEqual(aString, parameters.clientHazelcastVersion));
        assertTrue(isEqual(aString, parameters.clientName));
        assertTrue(isEqual(aListOfStrings, parameters.labels));
    }

    @Test
    public void test_ClientAuthenticationCodec_encodeResponse() {
        int fileClientMessageIndex = 1;
        ClientMessage encoded = ClientAuthenticationCodec.encodeResponse(aByte, anAddress, aUUID, aByte, aString, anInt, aUUID, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAuthenticationCustomCodec_decodeRequest() {
        int fileClientMessageIndex = 2;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientAuthenticationCustomCodec.RequestParameters parameters = ClientAuthenticationCustomCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.clusterName));
        assertTrue(isEqual(aByteArray, parameters.credentials));
        assertTrue(isEqual(aUUID, parameters.uuid));
        assertTrue(isEqual(aString, parameters.clientType));
        assertTrue(isEqual(aByte, parameters.serializationVersion));
        assertTrue(isEqual(aString, parameters.clientHazelcastVersion));
        assertTrue(isEqual(aString, parameters.clientName));
        assertTrue(isEqual(aListOfStrings, parameters.labels));
    }

    @Test
    public void test_ClientAuthenticationCustomCodec_encodeResponse() {
        int fileClientMessageIndex = 3;
        ClientMessage encoded = ClientAuthenticationCustomCodec.encodeResponse(aByte, anAddress, aUUID, aByte, aString, anInt, aUUID, aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAddClusterViewListenerCodec_decodeRequest() {
        int fileClientMessageIndex = 4;
    }

    @Test
    public void test_ClientAddClusterViewListenerCodec_encodeResponse() {
        int fileClientMessageIndex = 5;
        ClientMessage encoded = ClientAddClusterViewListenerCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAddClusterViewListenerCodec_encodeMembersViewEvent() {
        int fileClientMessageIndex = 6;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = ClientAddClusterViewListenerCodec.encodeMembersViewEvent(anInt, aListOfMemberInfos);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAddClusterViewListenerCodec_encodePartitionsViewEvent() {
        int fileClientMessageIndex = 7;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = ClientAddClusterViewListenerCodec.encodePartitionsViewEvent(anInt, aListOfUUIDToListOfIntegers);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientCreateProxyCodec_decodeRequest() {
        int fileClientMessageIndex = 8;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientCreateProxyCodec.RequestParameters parameters = ClientCreateProxyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aString, parameters.serviceName));
    }

    @Test
    public void test_ClientCreateProxyCodec_encodeResponse() {
        int fileClientMessageIndex = 9;
        ClientMessage encoded = ClientCreateProxyCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientDestroyProxyCodec_decodeRequest() {
        int fileClientMessageIndex = 10;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientDestroyProxyCodec.RequestParameters parameters = ClientDestroyProxyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aString, parameters.serviceName));
    }

    @Test
    public void test_ClientDestroyProxyCodec_encodeResponse() {
        int fileClientMessageIndex = 11;
        ClientMessage encoded = ClientDestroyProxyCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAddPartitionLostListenerCodec_decodeRequest() {
        int fileClientMessageIndex = 12;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ClientAddPartitionLostListenerCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ClientAddPartitionLostListenerCodec_encodeResponse() {
        int fileClientMessageIndex = 13;
        ClientMessage encoded = ClientAddPartitionLostListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAddPartitionLostListenerCodec_encodePartitionLostEvent() {
        int fileClientMessageIndex = 14;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = ClientAddPartitionLostListenerCodec.encodePartitionLostEvent(anInt, anInt, aUUID);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientRemovePartitionLostListenerCodec_decodeRequest() {
        int fileClientMessageIndex = 15;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, ClientRemovePartitionLostListenerCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ClientRemovePartitionLostListenerCodec_encodeResponse() {
        int fileClientMessageIndex = 16;
        ClientMessage encoded = ClientRemovePartitionLostListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientGetDistributedObjectsCodec_decodeRequest() {
        int fileClientMessageIndex = 17;
    }

    @Test
    public void test_ClientGetDistributedObjectsCodec_encodeResponse() {
        int fileClientMessageIndex = 18;
        ClientMessage encoded = ClientGetDistributedObjectsCodec.encodeResponse(aListOfDistributedObjectInfo);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAddDistributedObjectListenerCodec_decodeRequest() {
        int fileClientMessageIndex = 19;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ClientAddDistributedObjectListenerCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ClientAddDistributedObjectListenerCodec_encodeResponse() {
        int fileClientMessageIndex = 20;
        ClientMessage encoded = ClientAddDistributedObjectListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAddDistributedObjectListenerCodec_encodeDistributedObjectEvent() {
        int fileClientMessageIndex = 21;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = ClientAddDistributedObjectListenerCodec.encodeDistributedObjectEvent(aString, aString, aString, aUUID);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientRemoveDistributedObjectListenerCodec_decodeRequest() {
        int fileClientMessageIndex = 22;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, ClientRemoveDistributedObjectListenerCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ClientRemoveDistributedObjectListenerCodec_encodeResponse() {
        int fileClientMessageIndex = 23;
        ClientMessage encoded = ClientRemoveDistributedObjectListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientPingCodec_decodeRequest() {
        int fileClientMessageIndex = 24;
    }

    @Test
    public void test_ClientPingCodec_encodeResponse() {
        int fileClientMessageIndex = 25;
        ClientMessage encoded = ClientPingCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientStatisticsCodec_decodeRequest() {
        int fileClientMessageIndex = 26;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientStatisticsCodec.RequestParameters parameters = ClientStatisticsCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aLong, parameters.timestamp));
        assertTrue(isEqual(aString, parameters.clientAttributes));
        assertTrue(isEqual(aByteArray, parameters.metricsBlob));
    }

    @Test
    public void test_ClientStatisticsCodec_encodeResponse() {
        int fileClientMessageIndex = 27;
        ClientMessage encoded = ClientStatisticsCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientDeployClassesCodec_decodeRequest() {
        int fileClientMessageIndex = 28;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfStringToByteArray, ClientDeployClassesCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ClientDeployClassesCodec_encodeResponse() {
        int fileClientMessageIndex = 29;
        ClientMessage encoded = ClientDeployClassesCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientCreateProxiesCodec_decodeRequest() {
        int fileClientMessageIndex = 30;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aListOfStringToString, ClientCreateProxiesCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ClientCreateProxiesCodec_encodeResponse() {
        int fileClientMessageIndex = 31;
        ClientMessage encoded = ClientCreateProxiesCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientLocalBackupListenerCodec_decodeRequest() {
        int fileClientMessageIndex = 32;
    }

    @Test
    public void test_ClientLocalBackupListenerCodec_encodeResponse() {
        int fileClientMessageIndex = 33;
        ClientMessage encoded = ClientLocalBackupListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientLocalBackupListenerCodec_encodeBackupEvent() {
        int fileClientMessageIndex = 34;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = ClientLocalBackupListenerCodec.encodeBackupEvent(aLong);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientTriggerPartitionAssignmentCodec_decodeRequest() {
        int fileClientMessageIndex = 35;
    }

    @Test
    public void test_ClientTriggerPartitionAssignmentCodec_encodeResponse() {
        int fileClientMessageIndex = 36;
        ClientMessage encoded = ClientTriggerPartitionAssignmentCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAddMigrationListenerCodec_decodeRequest() {
        int fileClientMessageIndex = 37;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, ClientAddMigrationListenerCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ClientAddMigrationListenerCodec_encodeResponse() {
        int fileClientMessageIndex = 38;
        ClientMessage encoded = ClientAddMigrationListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAddMigrationListenerCodec_encodeMigrationEvent() {
        int fileClientMessageIndex = 39;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = ClientAddMigrationListenerCodec.encodeMigrationEvent(aMigrationState, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientAddMigrationListenerCodec_encodeReplicaMigrationEvent() {
        int fileClientMessageIndex = 40;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = ClientAddMigrationListenerCodec.encodeReplicaMigrationEvent(aMigrationState, anInt, anInt, aUUID, aUUID, aBoolean, aLong);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientRemoveMigrationListenerCodec_decodeRequest() {
        int fileClientMessageIndex = 41;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, ClientRemoveMigrationListenerCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ClientRemoveMigrationListenerCodec_encodeResponse() {
        int fileClientMessageIndex = 42;
        ClientMessage encoded = ClientRemoveMigrationListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
<<<<<<< HEAD
    public void test_MapPutCodec_decodeRequest() {
        int fileClientMessageIndex = 43;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
=======
    public void test_ClientSendSchemaCodec_decodeRequest() {
        int fileClientMessageIndex = 43;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientSendSchemaCodec.RequestParameters parameters = ClientSendSchemaCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aSchema, parameters.schema));
        assertTrue(isEqual(aLong, parameters.schemaId));
    }

    @Test
    public void test_ClientSendSchemaCodec_encodeResponse() {
        int fileClientMessageIndex = 44;
        ClientMessage encoded = ClientSendSchemaCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientFetchSchemaCodec_decodeRequest() {
        int fileClientMessageIndex = 45;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aLong, ClientFetchSchemaCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ClientFetchSchemaCodec_encodeResponse() {
        int fileClientMessageIndex = 46;
        ClientMessage encoded = ClientFetchSchemaCodec.encodeResponse(aSchema);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ClientSendAllSchemasCodec_decodeRequest() {
        int fileClientMessageIndex = 47;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aMapOfLongToSchema, ClientSendAllSchemasCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ClientSendAllSchemasCodec_encodeResponse() {
        int fileClientMessageIndex = 48;
        ClientMessage encoded = ClientSendAllSchemasCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapPutCodec_decodeRequest() {
        int fileClientMessageIndex = 49;
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
>>>>>>> 94a59c88de (Serialization 2.0)
        MapPutCodec.RequestParameters parameters = MapPutCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.ttl));
    }

    @Test
    public void test_MapPutCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 44;
=======
        int fileClientMessageIndex = 50;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapPutCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapGetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 45;
=======
        int fileClientMessageIndex = 51;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapGetCodec.RequestParameters parameters = MapGetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MapGetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 46;
=======
        int fileClientMessageIndex = 52;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapGetCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapRemoveCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 47;
=======
        int fileClientMessageIndex = 53;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapRemoveCodec.RequestParameters parameters = MapRemoveCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MapRemoveCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 48;
=======
        int fileClientMessageIndex = 54;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapRemoveCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapReplaceCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 49;
=======
        int fileClientMessageIndex = 55;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapReplaceCodec.RequestParameters parameters = MapReplaceCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MapReplaceCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 50;
=======
        int fileClientMessageIndex = 56;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapReplaceCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapReplaceIfSameCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 51;
=======
        int fileClientMessageIndex = 57;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapReplaceIfSameCodec.RequestParameters parameters = MapReplaceIfSameCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.testValue));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MapReplaceIfSameCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 52;
=======
        int fileClientMessageIndex = 58;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapReplaceIfSameCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapContainsKeyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 53;
=======
        int fileClientMessageIndex = 59;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapContainsKeyCodec.RequestParameters parameters = MapContainsKeyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MapContainsKeyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 54;
=======
        int fileClientMessageIndex = 60;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapContainsKeyCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapContainsValueCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 55;
=======
        int fileClientMessageIndex = 61;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapContainsValueCodec.RequestParameters parameters = MapContainsValueCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_MapContainsValueCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 56;
=======
        int fileClientMessageIndex = 62;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapContainsValueCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapRemoveIfSameCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 57;
=======
        int fileClientMessageIndex = 63;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapRemoveIfSameCodec.RequestParameters parameters = MapRemoveIfSameCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MapRemoveIfSameCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 58;
=======
        int fileClientMessageIndex = 64;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapRemoveIfSameCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapDeleteCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 59;
=======
        int fileClientMessageIndex = 65;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapDeleteCodec.RequestParameters parameters = MapDeleteCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MapDeleteCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 60;
=======
        int fileClientMessageIndex = 66;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapDeleteCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapFlushCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 61;
=======
        int fileClientMessageIndex = 67;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MapFlushCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MapFlushCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 62;
=======
        int fileClientMessageIndex = 68;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapFlushCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapTryRemoveCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 63;
=======
        int fileClientMessageIndex = 69;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapTryRemoveCodec.RequestParameters parameters = MapTryRemoveCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.timeout));
    }

    @Test
    public void test_MapTryRemoveCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 64;
=======
        int fileClientMessageIndex = 70;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapTryRemoveCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapTryPutCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 65;
=======
        int fileClientMessageIndex = 71;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapTryPutCodec.RequestParameters parameters = MapTryPutCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.timeout));
    }

    @Test
    public void test_MapTryPutCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 66;
=======
        int fileClientMessageIndex = 72;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapTryPutCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapPutTransientCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 67;
=======
        int fileClientMessageIndex = 73;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapPutTransientCodec.RequestParameters parameters = MapPutTransientCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.ttl));
    }

    @Test
    public void test_MapPutTransientCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 68;
=======
        int fileClientMessageIndex = 74;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapPutTransientCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapPutIfAbsentCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 69;
=======
        int fileClientMessageIndex = 75;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapPutIfAbsentCodec.RequestParameters parameters = MapPutIfAbsentCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.ttl));
    }

    @Test
    public void test_MapPutIfAbsentCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 70;
=======
        int fileClientMessageIndex = 76;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapPutIfAbsentCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapSetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 71;
=======
        int fileClientMessageIndex = 77;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapSetCodec.RequestParameters parameters = MapSetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.ttl));
    }

    @Test
    public void test_MapSetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 72;
=======
        int fileClientMessageIndex = 78;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapSetCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapLockCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 73;
=======
        int fileClientMessageIndex = 79;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapLockCodec.RequestParameters parameters = MapLockCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.ttl));
        assertTrue(isEqual(aLong, parameters.referenceId));
    }

    @Test
    public void test_MapLockCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 74;
=======
        int fileClientMessageIndex = 80;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapLockCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapTryLockCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 75;
=======
        int fileClientMessageIndex = 81;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapTryLockCodec.RequestParameters parameters = MapTryLockCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.lease));
        assertTrue(isEqual(aLong, parameters.timeout));
        assertTrue(isEqual(aLong, parameters.referenceId));
    }

    @Test
    public void test_MapTryLockCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 76;
=======
        int fileClientMessageIndex = 82;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapTryLockCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapIsLockedCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 77;
=======
        int fileClientMessageIndex = 83;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapIsLockedCodec.RequestParameters parameters = MapIsLockedCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
    }

    @Test
    public void test_MapIsLockedCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 78;
=======
        int fileClientMessageIndex = 84;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapIsLockedCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapUnlockCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 79;
=======
        int fileClientMessageIndex = 85;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapUnlockCodec.RequestParameters parameters = MapUnlockCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.referenceId));
    }

    @Test
    public void test_MapUnlockCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 80;
=======
        int fileClientMessageIndex = 86;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapUnlockCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddInterceptorCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 81;
=======
        int fileClientMessageIndex = 87;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAddInterceptorCodec.RequestParameters parameters = MapAddInterceptorCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.interceptor));
    }

    @Test
    public void test_MapAddInterceptorCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 82;
=======
        int fileClientMessageIndex = 88;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddInterceptorCodec.encodeResponse(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapRemoveInterceptorCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 83;
=======
        int fileClientMessageIndex = 89;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapRemoveInterceptorCodec.RequestParameters parameters = MapRemoveInterceptorCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aString, parameters.id));
    }

    @Test
    public void test_MapRemoveInterceptorCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 84;
=======
        int fileClientMessageIndex = 90;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapRemoveInterceptorCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddEntryListenerToKeyWithPredicateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 85;
=======
        int fileClientMessageIndex = 91;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAddEntryListenerToKeyWithPredicateCodec.RequestParameters parameters = MapAddEntryListenerToKeyWithPredicateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.predicate));
        assertTrue(isEqual(aBoolean, parameters.includeValue));
        assertTrue(isEqual(anInt, parameters.listenerFlags));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_MapAddEntryListenerToKeyWithPredicateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 86;
=======
        int fileClientMessageIndex = 92;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddEntryListenerToKeyWithPredicateCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddEntryListenerToKeyWithPredicateCodec_encodeEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 87;
=======
        int fileClientMessageIndex = 93;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = MapAddEntryListenerToKeyWithPredicateCodec.encodeEntryEvent(aData, aData, aData, aData, anInt, aUUID, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddEntryListenerWithPredicateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 88;
=======
        int fileClientMessageIndex = 94;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAddEntryListenerWithPredicateCodec.RequestParameters parameters = MapAddEntryListenerWithPredicateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.predicate));
        assertTrue(isEqual(aBoolean, parameters.includeValue));
        assertTrue(isEqual(anInt, parameters.listenerFlags));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_MapAddEntryListenerWithPredicateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 89;
=======
        int fileClientMessageIndex = 95;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddEntryListenerWithPredicateCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddEntryListenerWithPredicateCodec_encodeEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 90;
=======
        int fileClientMessageIndex = 96;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = MapAddEntryListenerWithPredicateCodec.encodeEntryEvent(aData, aData, aData, aData, anInt, aUUID, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddEntryListenerToKeyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 91;
=======
        int fileClientMessageIndex = 97;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAddEntryListenerToKeyCodec.RequestParameters parameters = MapAddEntryListenerToKeyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aBoolean, parameters.includeValue));
        assertTrue(isEqual(anInt, parameters.listenerFlags));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_MapAddEntryListenerToKeyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 92;
=======
        int fileClientMessageIndex = 98;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddEntryListenerToKeyCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddEntryListenerToKeyCodec_encodeEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 93;
=======
        int fileClientMessageIndex = 99;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = MapAddEntryListenerToKeyCodec.encodeEntryEvent(aData, aData, aData, aData, anInt, aUUID, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddEntryListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 94;
=======
        int fileClientMessageIndex = 100;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAddEntryListenerCodec.RequestParameters parameters = MapAddEntryListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aBoolean, parameters.includeValue));
        assertTrue(isEqual(anInt, parameters.listenerFlags));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_MapAddEntryListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 95;
=======
        int fileClientMessageIndex = 101;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddEntryListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddEntryListenerCodec_encodeEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 96;
=======
        int fileClientMessageIndex = 102;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = MapAddEntryListenerCodec.encodeEntryEvent(aData, aData, aData, aData, anInt, aUUID, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapRemoveEntryListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 97;
=======
        int fileClientMessageIndex = 103;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapRemoveEntryListenerCodec.RequestParameters parameters = MapRemoveEntryListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.registrationId));
    }

    @Test
    public void test_MapRemoveEntryListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 98;
=======
        int fileClientMessageIndex = 104;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapRemoveEntryListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddPartitionLostListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 99;
=======
        int fileClientMessageIndex = 105;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAddPartitionLostListenerCodec.RequestParameters parameters = MapAddPartitionLostListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_MapAddPartitionLostListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 100;
=======
        int fileClientMessageIndex = 106;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddPartitionLostListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddPartitionLostListenerCodec_encodeMapPartitionLostEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 101;
=======
        int fileClientMessageIndex = 107;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = MapAddPartitionLostListenerCodec.encodeMapPartitionLostEvent(anInt, aUUID);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapRemovePartitionLostListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 102;
=======
        int fileClientMessageIndex = 108;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapRemovePartitionLostListenerCodec.RequestParameters parameters = MapRemovePartitionLostListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.registrationId));
    }

    @Test
    public void test_MapRemovePartitionLostListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 103;
=======
        int fileClientMessageIndex = 109;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapRemovePartitionLostListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapGetEntryViewCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 104;
=======
        int fileClientMessageIndex = 110;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapGetEntryViewCodec.RequestParameters parameters = MapGetEntryViewCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MapGetEntryViewCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 105;
=======
        int fileClientMessageIndex = 111;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapGetEntryViewCodec.encodeResponse(aSimpleEntryView, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapEvictCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 106;
=======
        int fileClientMessageIndex = 112;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapEvictCodec.RequestParameters parameters = MapEvictCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MapEvictCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 107;
=======
        int fileClientMessageIndex = 113;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapEvictCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapEvictAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 108;
=======
        int fileClientMessageIndex = 114;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MapEvictAllCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MapEvictAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 109;
=======
        int fileClientMessageIndex = 115;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapEvictAllCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapLoadAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 110;
=======
        int fileClientMessageIndex = 116;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapLoadAllCodec.RequestParameters parameters = MapLoadAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aBoolean, parameters.replaceExistingValues));
    }

    @Test
    public void test_MapLoadAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 111;
=======
        int fileClientMessageIndex = 117;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapLoadAllCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapLoadGivenKeysCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 112;
=======
        int fileClientMessageIndex = 118;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapLoadGivenKeysCodec.RequestParameters parameters = MapLoadGivenKeysCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.keys));
        assertTrue(isEqual(aBoolean, parameters.replaceExistingValues));
    }

    @Test
    public void test_MapLoadGivenKeysCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 113;
=======
        int fileClientMessageIndex = 119;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapLoadGivenKeysCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapKeySetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 114;
=======
        int fileClientMessageIndex = 120;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MapKeySetCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MapKeySetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 115;
=======
        int fileClientMessageIndex = 121;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapKeySetCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapGetAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 116;
=======
        int fileClientMessageIndex = 122;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapGetAllCodec.RequestParameters parameters = MapGetAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.keys));
    }

    @Test
    public void test_MapGetAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 117;
=======
        int fileClientMessageIndex = 123;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapGetAllCodec.encodeResponse(aListOfDataToData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapValuesCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 118;
=======
        int fileClientMessageIndex = 124;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MapValuesCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MapValuesCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 119;
=======
        int fileClientMessageIndex = 125;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapValuesCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapEntrySetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 120;
=======
        int fileClientMessageIndex = 126;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MapEntrySetCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MapEntrySetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 121;
=======
        int fileClientMessageIndex = 127;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapEntrySetCodec.encodeResponse(aListOfDataToData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapKeySetWithPredicateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 122;
=======
        int fileClientMessageIndex = 128;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapKeySetWithPredicateCodec.RequestParameters parameters = MapKeySetWithPredicateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.predicate));
    }

    @Test
    public void test_MapKeySetWithPredicateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 123;
=======
        int fileClientMessageIndex = 129;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapKeySetWithPredicateCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapValuesWithPredicateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 124;
=======
        int fileClientMessageIndex = 130;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapValuesWithPredicateCodec.RequestParameters parameters = MapValuesWithPredicateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.predicate));
    }

    @Test
    public void test_MapValuesWithPredicateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 125;
=======
        int fileClientMessageIndex = 131;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapValuesWithPredicateCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapEntriesWithPredicateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 126;
=======
        int fileClientMessageIndex = 132;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapEntriesWithPredicateCodec.RequestParameters parameters = MapEntriesWithPredicateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.predicate));
    }

    @Test
    public void test_MapEntriesWithPredicateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 127;
=======
        int fileClientMessageIndex = 133;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapEntriesWithPredicateCodec.encodeResponse(aListOfDataToData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddIndexCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 128;
=======
        int fileClientMessageIndex = 134;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAddIndexCodec.RequestParameters parameters = MapAddIndexCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anIndexConfig, parameters.indexConfig));
    }

    @Test
    public void test_MapAddIndexCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 129;
=======
        int fileClientMessageIndex = 135;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddIndexCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapSizeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 130;
=======
        int fileClientMessageIndex = 136;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MapSizeCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MapSizeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 131;
=======
        int fileClientMessageIndex = 137;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapSizeCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapIsEmptyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 132;
=======
        int fileClientMessageIndex = 138;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MapIsEmptyCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MapIsEmptyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 133;
=======
        int fileClientMessageIndex = 139;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapIsEmptyCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapPutAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 134;
=======
        int fileClientMessageIndex = 140;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapPutAllCodec.RequestParameters parameters = MapPutAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfDataToData, parameters.entries));
        assertTrue(parameters.isTriggerMapLoaderExists);
        assertTrue(isEqual(aBoolean, parameters.triggerMapLoader));
    }

    @Test
    public void test_MapPutAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 135;
=======
        int fileClientMessageIndex = 141;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapPutAllCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapClearCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 136;
=======
        int fileClientMessageIndex = 142;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MapClearCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MapClearCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 137;
=======
        int fileClientMessageIndex = 143;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapClearCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapExecuteOnKeyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 138;
=======
        int fileClientMessageIndex = 144;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapExecuteOnKeyCodec.RequestParameters parameters = MapExecuteOnKeyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.entryProcessor));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MapExecuteOnKeyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 139;
=======
        int fileClientMessageIndex = 145;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapExecuteOnKeyCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapSubmitToKeyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 140;
=======
        int fileClientMessageIndex = 146;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapSubmitToKeyCodec.RequestParameters parameters = MapSubmitToKeyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.entryProcessor));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MapSubmitToKeyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 141;
=======
        int fileClientMessageIndex = 147;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapSubmitToKeyCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapExecuteOnAllKeysCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 142;
=======
        int fileClientMessageIndex = 148;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapExecuteOnAllKeysCodec.RequestParameters parameters = MapExecuteOnAllKeysCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.entryProcessor));
    }

    @Test
    public void test_MapExecuteOnAllKeysCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 143;
=======
        int fileClientMessageIndex = 149;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapExecuteOnAllKeysCodec.encodeResponse(aListOfDataToData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapExecuteWithPredicateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 144;
=======
        int fileClientMessageIndex = 150;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapExecuteWithPredicateCodec.RequestParameters parameters = MapExecuteWithPredicateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.entryProcessor));
        assertTrue(isEqual(aData, parameters.predicate));
    }

    @Test
    public void test_MapExecuteWithPredicateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 145;
=======
        int fileClientMessageIndex = 151;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapExecuteWithPredicateCodec.encodeResponse(aListOfDataToData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapExecuteOnKeysCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 146;
=======
        int fileClientMessageIndex = 152;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapExecuteOnKeysCodec.RequestParameters parameters = MapExecuteOnKeysCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.entryProcessor));
        assertTrue(isEqual(aListOfData, parameters.keys));
    }

    @Test
    public void test_MapExecuteOnKeysCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 147;
=======
        int fileClientMessageIndex = 153;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapExecuteOnKeysCodec.encodeResponse(aListOfDataToData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapForceUnlockCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 148;
=======
        int fileClientMessageIndex = 154;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapForceUnlockCodec.RequestParameters parameters = MapForceUnlockCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.referenceId));
    }

    @Test
    public void test_MapForceUnlockCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 149;
=======
        int fileClientMessageIndex = 155;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapForceUnlockCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapKeySetWithPagingPredicateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 150;
=======
        int fileClientMessageIndex = 156;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapKeySetWithPagingPredicateCodec.RequestParameters parameters = MapKeySetWithPagingPredicateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aPagingPredicateHolder, parameters.predicate));
    }

    @Test
    public void test_MapKeySetWithPagingPredicateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 151;
=======
        int fileClientMessageIndex = 157;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapKeySetWithPagingPredicateCodec.encodeResponse(aListOfData, anAnchorDataListHolder);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapValuesWithPagingPredicateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 152;
=======
        int fileClientMessageIndex = 158;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapValuesWithPagingPredicateCodec.RequestParameters parameters = MapValuesWithPagingPredicateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aPagingPredicateHolder, parameters.predicate));
    }

    @Test
    public void test_MapValuesWithPagingPredicateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 153;
=======
        int fileClientMessageIndex = 159;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapValuesWithPagingPredicateCodec.encodeResponse(aListOfData, anAnchorDataListHolder);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapEntriesWithPagingPredicateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 154;
=======
        int fileClientMessageIndex = 160;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapEntriesWithPagingPredicateCodec.RequestParameters parameters = MapEntriesWithPagingPredicateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aPagingPredicateHolder, parameters.predicate));
    }

    @Test
    public void test_MapEntriesWithPagingPredicateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 155;
=======
        int fileClientMessageIndex = 161;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapEntriesWithPagingPredicateCodec.encodeResponse(aListOfDataToData, anAnchorDataListHolder);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapFetchKeysCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 156;
=======
        int fileClientMessageIndex = 162;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapFetchKeysCodec.RequestParameters parameters = MapFetchKeysCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfIntegerToInteger, parameters.iterationPointers));
        assertTrue(isEqual(anInt, parameters.batch));
    }

    @Test
    public void test_MapFetchKeysCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 157;
=======
        int fileClientMessageIndex = 163;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapFetchKeysCodec.encodeResponse(aListOfIntegerToInteger, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapFetchEntriesCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 158;
=======
        int fileClientMessageIndex = 164;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapFetchEntriesCodec.RequestParameters parameters = MapFetchEntriesCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfIntegerToInteger, parameters.iterationPointers));
        assertTrue(isEqual(anInt, parameters.batch));
    }

    @Test
    public void test_MapFetchEntriesCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 159;
=======
        int fileClientMessageIndex = 165;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapFetchEntriesCodec.encodeResponse(aListOfIntegerToInteger, aListOfDataToData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAggregateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 160;
=======
        int fileClientMessageIndex = 166;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAggregateCodec.RequestParameters parameters = MapAggregateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.aggregator));
    }

    @Test
    public void test_MapAggregateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 161;
=======
        int fileClientMessageIndex = 167;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAggregateCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAggregateWithPredicateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 162;
=======
        int fileClientMessageIndex = 168;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAggregateWithPredicateCodec.RequestParameters parameters = MapAggregateWithPredicateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.aggregator));
        assertTrue(isEqual(aData, parameters.predicate));
    }

    @Test
    public void test_MapAggregateWithPredicateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 163;
=======
        int fileClientMessageIndex = 169;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAggregateWithPredicateCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapProjectCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 164;
=======
        int fileClientMessageIndex = 170;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapProjectCodec.RequestParameters parameters = MapProjectCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.projection));
    }

    @Test
    public void test_MapProjectCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 165;
=======
        int fileClientMessageIndex = 171;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapProjectCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapProjectWithPredicateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 166;
=======
        int fileClientMessageIndex = 172;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapProjectWithPredicateCodec.RequestParameters parameters = MapProjectWithPredicateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.projection));
        assertTrue(isEqual(aData, parameters.predicate));
    }

    @Test
    public void test_MapProjectWithPredicateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 167;
=======
        int fileClientMessageIndex = 173;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapProjectWithPredicateCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapFetchNearCacheInvalidationMetadataCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 168;
=======
        int fileClientMessageIndex = 174;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapFetchNearCacheInvalidationMetadataCodec.RequestParameters parameters = MapFetchNearCacheInvalidationMetadataCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aListOfStrings, parameters.names));
        assertTrue(isEqual(aUUID, parameters.uuid));
    }

    @Test
    public void test_MapFetchNearCacheInvalidationMetadataCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 169;
=======
        int fileClientMessageIndex = 175;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapFetchNearCacheInvalidationMetadataCodec.encodeResponse(aListOfStringToListOfIntegerToLong, aListOfIntegerToUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapRemoveAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 170;
=======
        int fileClientMessageIndex = 176;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapRemoveAllCodec.RequestParameters parameters = MapRemoveAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.predicate));
    }

    @Test
    public void test_MapRemoveAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 171;
=======
        int fileClientMessageIndex = 177;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapRemoveAllCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddNearCacheInvalidationListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 172;
=======
        int fileClientMessageIndex = 178;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapAddNearCacheInvalidationListenerCodec.RequestParameters parameters = MapAddNearCacheInvalidationListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.listenerFlags));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_MapAddNearCacheInvalidationListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 173;
=======
        int fileClientMessageIndex = 179;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapAddNearCacheInvalidationListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddNearCacheInvalidationListenerCodec_encodeIMapInvalidationEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 174;
=======
        int fileClientMessageIndex = 180;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = MapAddNearCacheInvalidationListenerCodec.encodeIMapInvalidationEvent(aData, aUUID, aUUID, aLong);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapAddNearCacheInvalidationListenerCodec_encodeIMapBatchInvalidationEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 175;
=======
        int fileClientMessageIndex = 181;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = MapAddNearCacheInvalidationListenerCodec.encodeIMapBatchInvalidationEvent(aListOfData, aListOfUUIDs, aListOfUUIDs, aListOfLongs);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapFetchWithQueryCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 176;
=======
        int fileClientMessageIndex = 182;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapFetchWithQueryCodec.RequestParameters parameters = MapFetchWithQueryCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfIntegerToInteger, parameters.iterationPointers));
        assertTrue(isEqual(anInt, parameters.batch));
        assertTrue(isEqual(aData, parameters.projection));
        assertTrue(isEqual(aData, parameters.predicate));
    }

    @Test
    public void test_MapFetchWithQueryCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 177;
=======
        int fileClientMessageIndex = 183;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapFetchWithQueryCodec.encodeResponse(aListOfData, aListOfIntegerToInteger);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapEventJournalSubscribeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 178;
=======
        int fileClientMessageIndex = 184;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MapEventJournalSubscribeCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MapEventJournalSubscribeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 179;
=======
        int fileClientMessageIndex = 185;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapEventJournalSubscribeCodec.encodeResponse(aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapEventJournalReadCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 180;
=======
        int fileClientMessageIndex = 186;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapEventJournalReadCodec.RequestParameters parameters = MapEventJournalReadCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.startSequence));
        assertTrue(isEqual(anInt, parameters.minSize));
        assertTrue(isEqual(anInt, parameters.maxSize));
        assertTrue(isEqual(aData, parameters.predicate));
        assertTrue(isEqual(aData, parameters.projection));
    }

    @Test
    public void test_MapEventJournalReadCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 181;
=======
        int fileClientMessageIndex = 187;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapEventJournalReadCodec.encodeResponse(anInt, aListOfData, aLongArray, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapSetTtlCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 182;
=======
        int fileClientMessageIndex = 188;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapSetTtlCodec.RequestParameters parameters = MapSetTtlCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.ttl));
    }

    @Test
    public void test_MapSetTtlCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 183;
=======
        int fileClientMessageIndex = 189;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapSetTtlCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapPutWithMaxIdleCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 184;
=======
        int fileClientMessageIndex = 190;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapPutWithMaxIdleCodec.RequestParameters parameters = MapPutWithMaxIdleCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.ttl));
        assertTrue(isEqual(aLong, parameters.maxIdle));
    }

    @Test
    public void test_MapPutWithMaxIdleCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 185;
=======
        int fileClientMessageIndex = 191;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapPutWithMaxIdleCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapPutTransientWithMaxIdleCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 186;
=======
        int fileClientMessageIndex = 192;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapPutTransientWithMaxIdleCodec.RequestParameters parameters = MapPutTransientWithMaxIdleCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.ttl));
        assertTrue(isEqual(aLong, parameters.maxIdle));
    }

    @Test
    public void test_MapPutTransientWithMaxIdleCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 187;
=======
        int fileClientMessageIndex = 193;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapPutTransientWithMaxIdleCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapPutIfAbsentWithMaxIdleCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 188;
=======
        int fileClientMessageIndex = 194;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapPutIfAbsentWithMaxIdleCodec.RequestParameters parameters = MapPutIfAbsentWithMaxIdleCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.ttl));
        assertTrue(isEqual(aLong, parameters.maxIdle));
    }

    @Test
    public void test_MapPutIfAbsentWithMaxIdleCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 189;
=======
        int fileClientMessageIndex = 195;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapPutIfAbsentWithMaxIdleCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MapSetWithMaxIdleCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 190;
=======
        int fileClientMessageIndex = 196;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MapSetWithMaxIdleCodec.RequestParameters parameters = MapSetWithMaxIdleCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.ttl));
        assertTrue(isEqual(aLong, parameters.maxIdle));
    }

    @Test
    public void test_MapSetWithMaxIdleCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 191;
=======
        int fileClientMessageIndex = 197;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MapSetWithMaxIdleCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapPutCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 192;
=======
        int fileClientMessageIndex = 198;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapPutCodec.RequestParameters parameters = MultiMapPutCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MultiMapPutCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 193;
=======
        int fileClientMessageIndex = 199;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapPutCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapGetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 194;
=======
        int fileClientMessageIndex = 200;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapGetCodec.RequestParameters parameters = MultiMapGetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MultiMapGetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 195;
=======
        int fileClientMessageIndex = 201;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapGetCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapRemoveCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 196;
=======
        int fileClientMessageIndex = 202;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapRemoveCodec.RequestParameters parameters = MultiMapRemoveCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MultiMapRemoveCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 197;
=======
        int fileClientMessageIndex = 203;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapRemoveCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapKeySetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 198;
=======
        int fileClientMessageIndex = 204;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MultiMapKeySetCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MultiMapKeySetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 199;
=======
        int fileClientMessageIndex = 205;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapKeySetCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapValuesCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 200;
=======
        int fileClientMessageIndex = 206;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MultiMapValuesCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MultiMapValuesCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 201;
=======
        int fileClientMessageIndex = 207;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapValuesCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapEntrySetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 202;
=======
        int fileClientMessageIndex = 208;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MultiMapEntrySetCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MultiMapEntrySetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 203;
=======
        int fileClientMessageIndex = 209;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapEntrySetCodec.encodeResponse(aListOfDataToData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapContainsKeyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 204;
=======
        int fileClientMessageIndex = 210;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapContainsKeyCodec.RequestParameters parameters = MultiMapContainsKeyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MultiMapContainsKeyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 205;
=======
        int fileClientMessageIndex = 211;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapContainsKeyCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapContainsValueCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 206;
=======
        int fileClientMessageIndex = 212;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapContainsValueCodec.RequestParameters parameters = MultiMapContainsValueCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_MultiMapContainsValueCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 207;
=======
        int fileClientMessageIndex = 213;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapContainsValueCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapContainsEntryCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 208;
=======
        int fileClientMessageIndex = 214;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapContainsEntryCodec.RequestParameters parameters = MultiMapContainsEntryCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MultiMapContainsEntryCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 209;
=======
        int fileClientMessageIndex = 215;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapContainsEntryCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapSizeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 210;
=======
        int fileClientMessageIndex = 216;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MultiMapSizeCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MultiMapSizeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 211;
=======
        int fileClientMessageIndex = 217;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapSizeCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapClearCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 212;
=======
        int fileClientMessageIndex = 218;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MultiMapClearCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MultiMapClearCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 213;
=======
        int fileClientMessageIndex = 219;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapClearCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapValueCountCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 214;
=======
        int fileClientMessageIndex = 220;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapValueCountCodec.RequestParameters parameters = MultiMapValueCountCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MultiMapValueCountCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 215;
=======
        int fileClientMessageIndex = 221;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapValueCountCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapAddEntryListenerToKeyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 216;
=======
        int fileClientMessageIndex = 222;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapAddEntryListenerToKeyCodec.RequestParameters parameters = MultiMapAddEntryListenerToKeyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aBoolean, parameters.includeValue));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_MultiMapAddEntryListenerToKeyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 217;
=======
        int fileClientMessageIndex = 223;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapAddEntryListenerToKeyCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapAddEntryListenerToKeyCodec_encodeEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 218;
=======
        int fileClientMessageIndex = 224;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = MultiMapAddEntryListenerToKeyCodec.encodeEntryEvent(aData, aData, aData, aData, anInt, aUUID, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapAddEntryListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 219;
=======
        int fileClientMessageIndex = 225;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapAddEntryListenerCodec.RequestParameters parameters = MultiMapAddEntryListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aBoolean, parameters.includeValue));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_MultiMapAddEntryListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 220;
=======
        int fileClientMessageIndex = 226;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapAddEntryListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapAddEntryListenerCodec_encodeEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 221;
=======
        int fileClientMessageIndex = 227;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = MultiMapAddEntryListenerCodec.encodeEntryEvent(aData, aData, aData, aData, anInt, aUUID, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapRemoveEntryListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 222;
=======
        int fileClientMessageIndex = 228;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapRemoveEntryListenerCodec.RequestParameters parameters = MultiMapRemoveEntryListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.registrationId));
    }

    @Test
    public void test_MultiMapRemoveEntryListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 223;
=======
        int fileClientMessageIndex = 229;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapRemoveEntryListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapLockCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 224;
=======
        int fileClientMessageIndex = 230;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapLockCodec.RequestParameters parameters = MultiMapLockCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.ttl));
        assertTrue(isEqual(aLong, parameters.referenceId));
    }

    @Test
    public void test_MultiMapLockCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 225;
=======
        int fileClientMessageIndex = 231;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapLockCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapTryLockCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 226;
=======
        int fileClientMessageIndex = 232;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapTryLockCodec.RequestParameters parameters = MultiMapTryLockCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.lease));
        assertTrue(isEqual(aLong, parameters.timeout));
        assertTrue(isEqual(aLong, parameters.referenceId));
    }

    @Test
    public void test_MultiMapTryLockCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 227;
=======
        int fileClientMessageIndex = 233;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapTryLockCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapIsLockedCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 228;
=======
        int fileClientMessageIndex = 234;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapIsLockedCodec.RequestParameters parameters = MultiMapIsLockedCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
    }

    @Test
    public void test_MultiMapIsLockedCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 229;
=======
        int fileClientMessageIndex = 235;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapIsLockedCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapUnlockCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 230;
=======
        int fileClientMessageIndex = 236;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapUnlockCodec.RequestParameters parameters = MultiMapUnlockCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.referenceId));
    }

    @Test
    public void test_MultiMapUnlockCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 231;
=======
        int fileClientMessageIndex = 237;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapUnlockCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapForceUnlockCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 232;
=======
        int fileClientMessageIndex = 238;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapForceUnlockCodec.RequestParameters parameters = MultiMapForceUnlockCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.referenceId));
    }

    @Test
    public void test_MultiMapForceUnlockCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 233;
=======
        int fileClientMessageIndex = 239;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapForceUnlockCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapRemoveEntryCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 234;
=======
        int fileClientMessageIndex = 240;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapRemoveEntryCodec.RequestParameters parameters = MultiMapRemoveEntryCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MultiMapRemoveEntryCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 235;
=======
        int fileClientMessageIndex = 241;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapRemoveEntryCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapDeleteCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 236;
=======
        int fileClientMessageIndex = 242;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapDeleteCodec.RequestParameters parameters = MultiMapDeleteCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_MultiMapDeleteCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 237;
=======
        int fileClientMessageIndex = 243;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapDeleteCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MultiMapPutAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 238;
=======
        int fileClientMessageIndex = 244;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MultiMapPutAllCodec.RequestParameters parameters = MultiMapPutAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfDataToListOfData, parameters.entries));
    }

    @Test
    public void test_MultiMapPutAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 239;
=======
        int fileClientMessageIndex = 245;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MultiMapPutAllCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueOfferCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 240;
=======
        int fileClientMessageIndex = 246;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        QueueOfferCodec.RequestParameters parameters = QueueOfferCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.timeoutMillis));
    }

    @Test
    public void test_QueueOfferCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 241;
=======
        int fileClientMessageIndex = 247;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueOfferCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueuePutCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 242;
=======
        int fileClientMessageIndex = 248;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        QueuePutCodec.RequestParameters parameters = QueuePutCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_QueuePutCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 243;
=======
        int fileClientMessageIndex = 249;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueuePutCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueSizeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 244;
=======
        int fileClientMessageIndex = 250;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, QueueSizeCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_QueueSizeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 245;
=======
        int fileClientMessageIndex = 251;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueSizeCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueRemoveCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 246;
=======
        int fileClientMessageIndex = 252;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        QueueRemoveCodec.RequestParameters parameters = QueueRemoveCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_QueueRemoveCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 247;
=======
        int fileClientMessageIndex = 253;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueRemoveCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueuePollCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 248;
=======
        int fileClientMessageIndex = 254;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        QueuePollCodec.RequestParameters parameters = QueuePollCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.timeoutMillis));
    }

    @Test
    public void test_QueuePollCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 249;
=======
        int fileClientMessageIndex = 255;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueuePollCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueTakeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 250;
=======
        int fileClientMessageIndex = 256;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, QueueTakeCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_QueueTakeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 251;
=======
        int fileClientMessageIndex = 257;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueTakeCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueuePeekCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 252;
=======
        int fileClientMessageIndex = 258;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, QueuePeekCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_QueuePeekCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 253;
=======
        int fileClientMessageIndex = 259;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueuePeekCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueIteratorCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 254;
=======
        int fileClientMessageIndex = 260;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, QueueIteratorCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_QueueIteratorCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 255;
=======
        int fileClientMessageIndex = 261;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueIteratorCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueDrainToCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 256;
=======
        int fileClientMessageIndex = 262;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, QueueDrainToCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_QueueDrainToCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 257;
=======
        int fileClientMessageIndex = 263;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueDrainToCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueDrainToMaxSizeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 258;
=======
        int fileClientMessageIndex = 264;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        QueueDrainToMaxSizeCodec.RequestParameters parameters = QueueDrainToMaxSizeCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.maxSize));
    }

    @Test
    public void test_QueueDrainToMaxSizeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 259;
=======
        int fileClientMessageIndex = 265;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueDrainToMaxSizeCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueContainsCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 260;
=======
        int fileClientMessageIndex = 266;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        QueueContainsCodec.RequestParameters parameters = QueueContainsCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_QueueContainsCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 261;
=======
        int fileClientMessageIndex = 267;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueContainsCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueContainsAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 262;
=======
        int fileClientMessageIndex = 268;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        QueueContainsAllCodec.RequestParameters parameters = QueueContainsAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.dataList));
    }

    @Test
    public void test_QueueContainsAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 263;
=======
        int fileClientMessageIndex = 269;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueContainsAllCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueCompareAndRemoveAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 264;
=======
        int fileClientMessageIndex = 270;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        QueueCompareAndRemoveAllCodec.RequestParameters parameters = QueueCompareAndRemoveAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.dataList));
    }

    @Test
    public void test_QueueCompareAndRemoveAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 265;
=======
        int fileClientMessageIndex = 271;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueCompareAndRemoveAllCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueCompareAndRetainAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 266;
=======
        int fileClientMessageIndex = 272;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        QueueCompareAndRetainAllCodec.RequestParameters parameters = QueueCompareAndRetainAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.dataList));
    }

    @Test
    public void test_QueueCompareAndRetainAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 267;
=======
        int fileClientMessageIndex = 273;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueCompareAndRetainAllCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueClearCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 268;
=======
        int fileClientMessageIndex = 274;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, QueueClearCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_QueueClearCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 269;
=======
        int fileClientMessageIndex = 275;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueClearCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueAddAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 270;
=======
        int fileClientMessageIndex = 276;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        QueueAddAllCodec.RequestParameters parameters = QueueAddAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.dataList));
    }

    @Test
    public void test_QueueAddAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 271;
=======
        int fileClientMessageIndex = 277;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueAddAllCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueAddListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 272;
=======
        int fileClientMessageIndex = 278;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        QueueAddListenerCodec.RequestParameters parameters = QueueAddListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aBoolean, parameters.includeValue));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_QueueAddListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 273;
=======
        int fileClientMessageIndex = 279;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueAddListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueAddListenerCodec_encodeItemEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 274;
=======
        int fileClientMessageIndex = 280;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = QueueAddListenerCodec.encodeItemEvent(aData, aUUID, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueRemoveListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 275;
=======
        int fileClientMessageIndex = 281;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        QueueRemoveListenerCodec.RequestParameters parameters = QueueRemoveListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.registrationId));
    }

    @Test
    public void test_QueueRemoveListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 276;
=======
        int fileClientMessageIndex = 282;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueRemoveListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueRemainingCapacityCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 277;
=======
        int fileClientMessageIndex = 283;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, QueueRemainingCapacityCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_QueueRemainingCapacityCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 278;
=======
        int fileClientMessageIndex = 284;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueRemainingCapacityCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_QueueIsEmptyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 279;
=======
        int fileClientMessageIndex = 285;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, QueueIsEmptyCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_QueueIsEmptyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 280;
=======
        int fileClientMessageIndex = 286;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = QueueIsEmptyCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TopicPublishCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 281;
=======
        int fileClientMessageIndex = 287;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TopicPublishCodec.RequestParameters parameters = TopicPublishCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.message));
    }

    @Test
    public void test_TopicPublishCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 282;
=======
        int fileClientMessageIndex = 288;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TopicPublishCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TopicAddMessageListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 283;
=======
        int fileClientMessageIndex = 289;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TopicAddMessageListenerCodec.RequestParameters parameters = TopicAddMessageListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_TopicAddMessageListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 284;
=======
        int fileClientMessageIndex = 290;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TopicAddMessageListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TopicAddMessageListenerCodec_encodeTopicEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 285;
=======
        int fileClientMessageIndex = 291;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = TopicAddMessageListenerCodec.encodeTopicEvent(aData, aLong, aUUID);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TopicRemoveMessageListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 286;
=======
        int fileClientMessageIndex = 292;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TopicRemoveMessageListenerCodec.RequestParameters parameters = TopicRemoveMessageListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.registrationId));
    }

    @Test
    public void test_TopicRemoveMessageListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 287;
=======
        int fileClientMessageIndex = 293;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TopicRemoveMessageListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TopicPublishAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 288;
=======
        int fileClientMessageIndex = 294;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TopicPublishAllCodec.RequestParameters parameters = TopicPublishAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.messages));
    }

    @Test
    public void test_TopicPublishAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 289;
=======
        int fileClientMessageIndex = 295;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TopicPublishAllCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListSizeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 290;
=======
        int fileClientMessageIndex = 296;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, ListSizeCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ListSizeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 291;
=======
        int fileClientMessageIndex = 297;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListSizeCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListContainsCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 292;
=======
        int fileClientMessageIndex = 298;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListContainsCodec.RequestParameters parameters = ListContainsCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_ListContainsCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 293;
=======
        int fileClientMessageIndex = 299;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListContainsCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListContainsAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 294;
=======
        int fileClientMessageIndex = 300;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListContainsAllCodec.RequestParameters parameters = ListContainsAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.values));
    }

    @Test
    public void test_ListContainsAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 295;
=======
        int fileClientMessageIndex = 301;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListContainsAllCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListAddCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 296;
=======
        int fileClientMessageIndex = 302;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListAddCodec.RequestParameters parameters = ListAddCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_ListAddCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 297;
=======
        int fileClientMessageIndex = 303;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListAddCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListRemoveCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 298;
=======
        int fileClientMessageIndex = 304;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListRemoveCodec.RequestParameters parameters = ListRemoveCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_ListRemoveCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 299;
=======
        int fileClientMessageIndex = 305;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListRemoveCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListAddAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 300;
=======
        int fileClientMessageIndex = 306;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListAddAllCodec.RequestParameters parameters = ListAddAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.valueList));
    }

    @Test
    public void test_ListAddAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 301;
=======
        int fileClientMessageIndex = 307;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListAddAllCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListCompareAndRemoveAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 302;
=======
        int fileClientMessageIndex = 308;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListCompareAndRemoveAllCodec.RequestParameters parameters = ListCompareAndRemoveAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.values));
    }

    @Test
    public void test_ListCompareAndRemoveAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 303;
=======
        int fileClientMessageIndex = 309;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListCompareAndRemoveAllCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListCompareAndRetainAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 304;
=======
        int fileClientMessageIndex = 310;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListCompareAndRetainAllCodec.RequestParameters parameters = ListCompareAndRetainAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.values));
    }

    @Test
    public void test_ListCompareAndRetainAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 305;
=======
        int fileClientMessageIndex = 311;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListCompareAndRetainAllCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListClearCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 306;
=======
        int fileClientMessageIndex = 312;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, ListClearCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ListClearCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 307;
=======
        int fileClientMessageIndex = 313;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListClearCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListGetAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 308;
=======
        int fileClientMessageIndex = 314;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, ListGetAllCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ListGetAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 309;
=======
        int fileClientMessageIndex = 315;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListGetAllCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListAddListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 310;
=======
        int fileClientMessageIndex = 316;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListAddListenerCodec.RequestParameters parameters = ListAddListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aBoolean, parameters.includeValue));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_ListAddListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 311;
=======
        int fileClientMessageIndex = 317;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListAddListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListAddListenerCodec_encodeItemEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 312;
=======
        int fileClientMessageIndex = 318;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = ListAddListenerCodec.encodeItemEvent(aData, aUUID, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListRemoveListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 313;
=======
        int fileClientMessageIndex = 319;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListRemoveListenerCodec.RequestParameters parameters = ListRemoveListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.registrationId));
    }

    @Test
    public void test_ListRemoveListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 314;
=======
        int fileClientMessageIndex = 320;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListRemoveListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListIsEmptyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 315;
=======
        int fileClientMessageIndex = 321;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, ListIsEmptyCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ListIsEmptyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 316;
=======
        int fileClientMessageIndex = 322;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListIsEmptyCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListAddAllWithIndexCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 317;
=======
        int fileClientMessageIndex = 323;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListAddAllWithIndexCodec.RequestParameters parameters = ListAddAllWithIndexCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.index));
        assertTrue(isEqual(aListOfData, parameters.valueList));
    }

    @Test
    public void test_ListAddAllWithIndexCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 318;
=======
        int fileClientMessageIndex = 324;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListAddAllWithIndexCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListGetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 319;
=======
        int fileClientMessageIndex = 325;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListGetCodec.RequestParameters parameters = ListGetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.index));
    }

    @Test
    public void test_ListGetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 320;
=======
        int fileClientMessageIndex = 326;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListGetCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListSetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 321;
=======
        int fileClientMessageIndex = 327;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListSetCodec.RequestParameters parameters = ListSetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.index));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_ListSetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 322;
=======
        int fileClientMessageIndex = 328;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListSetCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListAddWithIndexCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 323;
=======
        int fileClientMessageIndex = 329;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListAddWithIndexCodec.RequestParameters parameters = ListAddWithIndexCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.index));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_ListAddWithIndexCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 324;
=======
        int fileClientMessageIndex = 330;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListAddWithIndexCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListRemoveWithIndexCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 325;
=======
        int fileClientMessageIndex = 331;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListRemoveWithIndexCodec.RequestParameters parameters = ListRemoveWithIndexCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.index));
    }

    @Test
    public void test_ListRemoveWithIndexCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 326;
=======
        int fileClientMessageIndex = 332;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListRemoveWithIndexCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListLastIndexOfCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 327;
=======
        int fileClientMessageIndex = 333;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListLastIndexOfCodec.RequestParameters parameters = ListLastIndexOfCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_ListLastIndexOfCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 328;
=======
        int fileClientMessageIndex = 334;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListLastIndexOfCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListIndexOfCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 329;
=======
        int fileClientMessageIndex = 335;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListIndexOfCodec.RequestParameters parameters = ListIndexOfCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_ListIndexOfCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 330;
=======
        int fileClientMessageIndex = 336;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListIndexOfCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListSubCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 331;
=======
        int fileClientMessageIndex = 337;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListSubCodec.RequestParameters parameters = ListSubCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.from));
        assertTrue(isEqual(anInt, parameters.to));
    }

    @Test
    public void test_ListSubCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 332;
=======
        int fileClientMessageIndex = 338;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListSubCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListIteratorCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 333;
=======
        int fileClientMessageIndex = 339;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, ListIteratorCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ListIteratorCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 334;
=======
        int fileClientMessageIndex = 340;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListIteratorCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ListListIteratorCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 335;
=======
        int fileClientMessageIndex = 341;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ListListIteratorCodec.RequestParameters parameters = ListListIteratorCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.index));
    }

    @Test
    public void test_ListListIteratorCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 336;
=======
        int fileClientMessageIndex = 342;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ListListIteratorCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetSizeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 337;
=======
        int fileClientMessageIndex = 343;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, SetSizeCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_SetSizeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 338;
=======
        int fileClientMessageIndex = 344;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetSizeCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetContainsCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 339;
=======
        int fileClientMessageIndex = 345;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SetContainsCodec.RequestParameters parameters = SetContainsCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_SetContainsCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 340;
=======
        int fileClientMessageIndex = 346;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetContainsCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetContainsAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 341;
=======
        int fileClientMessageIndex = 347;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SetContainsAllCodec.RequestParameters parameters = SetContainsAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.items));
    }

    @Test
    public void test_SetContainsAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 342;
=======
        int fileClientMessageIndex = 348;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetContainsAllCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetAddCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 343;
=======
        int fileClientMessageIndex = 349;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SetAddCodec.RequestParameters parameters = SetAddCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_SetAddCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 344;
=======
        int fileClientMessageIndex = 350;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetAddCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetRemoveCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 345;
=======
        int fileClientMessageIndex = 351;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SetRemoveCodec.RequestParameters parameters = SetRemoveCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_SetRemoveCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 346;
=======
        int fileClientMessageIndex = 352;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetRemoveCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetAddAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 347;
=======
        int fileClientMessageIndex = 353;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SetAddAllCodec.RequestParameters parameters = SetAddAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.valueList));
    }

    @Test
    public void test_SetAddAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 348;
=======
        int fileClientMessageIndex = 354;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetAddAllCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetCompareAndRemoveAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 349;
=======
        int fileClientMessageIndex = 355;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SetCompareAndRemoveAllCodec.RequestParameters parameters = SetCompareAndRemoveAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.values));
    }

    @Test
    public void test_SetCompareAndRemoveAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 350;
=======
        int fileClientMessageIndex = 356;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetCompareAndRemoveAllCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetCompareAndRetainAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 351;
=======
        int fileClientMessageIndex = 357;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SetCompareAndRetainAllCodec.RequestParameters parameters = SetCompareAndRetainAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.values));
    }

    @Test
    public void test_SetCompareAndRetainAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 352;
=======
        int fileClientMessageIndex = 358;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetCompareAndRetainAllCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetClearCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 353;
=======
        int fileClientMessageIndex = 359;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, SetClearCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_SetClearCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 354;
=======
        int fileClientMessageIndex = 360;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetClearCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetGetAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 355;
=======
        int fileClientMessageIndex = 361;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, SetGetAllCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_SetGetAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 356;
=======
        int fileClientMessageIndex = 362;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetGetAllCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetAddListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 357;
=======
        int fileClientMessageIndex = 363;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SetAddListenerCodec.RequestParameters parameters = SetAddListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aBoolean, parameters.includeValue));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_SetAddListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 358;
=======
        int fileClientMessageIndex = 364;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetAddListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetAddListenerCodec_encodeItemEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 359;
=======
        int fileClientMessageIndex = 365;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = SetAddListenerCodec.encodeItemEvent(aData, aUUID, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetRemoveListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 360;
=======
        int fileClientMessageIndex = 366;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SetRemoveListenerCodec.RequestParameters parameters = SetRemoveListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.registrationId));
    }

    @Test
    public void test_SetRemoveListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 361;
=======
        int fileClientMessageIndex = 367;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetRemoveListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SetIsEmptyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 362;
=======
        int fileClientMessageIndex = 368;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, SetIsEmptyCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_SetIsEmptyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 363;
=======
        int fileClientMessageIndex = 369;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SetIsEmptyCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_FencedLockLockCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 364;
=======
        int fileClientMessageIndex = 370;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        FencedLockLockCodec.RequestParameters parameters = FencedLockLockCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.sessionId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aUUID, parameters.invocationUid));
    }

    @Test
    public void test_FencedLockLockCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 365;
=======
        int fileClientMessageIndex = 371;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = FencedLockLockCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_FencedLockTryLockCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 366;
=======
        int fileClientMessageIndex = 372;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        FencedLockTryLockCodec.RequestParameters parameters = FencedLockTryLockCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.sessionId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aUUID, parameters.invocationUid));
        assertTrue(isEqual(aLong, parameters.timeoutMs));
    }

    @Test
    public void test_FencedLockTryLockCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 367;
=======
        int fileClientMessageIndex = 373;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = FencedLockTryLockCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_FencedLockUnlockCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 368;
=======
        int fileClientMessageIndex = 374;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        FencedLockUnlockCodec.RequestParameters parameters = FencedLockUnlockCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.sessionId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aUUID, parameters.invocationUid));
    }

    @Test
    public void test_FencedLockUnlockCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 369;
=======
        int fileClientMessageIndex = 375;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = FencedLockUnlockCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_FencedLockGetLockOwnershipCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 370;
=======
        int fileClientMessageIndex = 376;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        FencedLockGetLockOwnershipCodec.RequestParameters parameters = FencedLockGetLockOwnershipCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
    }

    @Test
    public void test_FencedLockGetLockOwnershipCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 371;
=======
        int fileClientMessageIndex = 377;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = FencedLockGetLockOwnershipCodec.encodeResponse(aLong, anInt, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ExecutorServiceShutdownCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 372;
=======
        int fileClientMessageIndex = 378;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, ExecutorServiceShutdownCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ExecutorServiceShutdownCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 373;
=======
        int fileClientMessageIndex = 379;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ExecutorServiceShutdownCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ExecutorServiceIsShutdownCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 374;
=======
        int fileClientMessageIndex = 380;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, ExecutorServiceIsShutdownCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ExecutorServiceIsShutdownCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 375;
=======
        int fileClientMessageIndex = 381;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ExecutorServiceIsShutdownCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ExecutorServiceCancelOnPartitionCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 376;
=======
        int fileClientMessageIndex = 382;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ExecutorServiceCancelOnPartitionCodec.RequestParameters parameters = ExecutorServiceCancelOnPartitionCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aUUID, parameters.uuid));
        assertTrue(isEqual(aBoolean, parameters.interrupt));
    }

    @Test
    public void test_ExecutorServiceCancelOnPartitionCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 377;
=======
        int fileClientMessageIndex = 383;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ExecutorServiceCancelOnPartitionCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ExecutorServiceCancelOnMemberCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 378;
=======
        int fileClientMessageIndex = 384;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ExecutorServiceCancelOnMemberCodec.RequestParameters parameters = ExecutorServiceCancelOnMemberCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aUUID, parameters.uuid));
        assertTrue(isEqual(aUUID, parameters.memberUUID));
        assertTrue(isEqual(aBoolean, parameters.interrupt));
    }

    @Test
    public void test_ExecutorServiceCancelOnMemberCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 379;
=======
        int fileClientMessageIndex = 385;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ExecutorServiceCancelOnMemberCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ExecutorServiceSubmitToPartitionCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 380;
=======
        int fileClientMessageIndex = 386;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ExecutorServiceSubmitToPartitionCodec.RequestParameters parameters = ExecutorServiceSubmitToPartitionCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.uuid));
        assertTrue(isEqual(aData, parameters.callable));
    }

    @Test
    public void test_ExecutorServiceSubmitToPartitionCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 381;
=======
        int fileClientMessageIndex = 387;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ExecutorServiceSubmitToPartitionCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ExecutorServiceSubmitToMemberCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 382;
=======
        int fileClientMessageIndex = 388;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ExecutorServiceSubmitToMemberCodec.RequestParameters parameters = ExecutorServiceSubmitToMemberCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.uuid));
        assertTrue(isEqual(aData, parameters.callable));
        assertTrue(isEqual(aUUID, parameters.memberUUID));
    }

    @Test
    public void test_ExecutorServiceSubmitToMemberCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 383;
=======
        int fileClientMessageIndex = 389;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ExecutorServiceSubmitToMemberCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicLongApplyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 384;
=======
        int fileClientMessageIndex = 390;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        AtomicLongApplyCodec.RequestParameters parameters = AtomicLongApplyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.function));
    }

    @Test
    public void test_AtomicLongApplyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 385;
=======
        int fileClientMessageIndex = 391;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicLongApplyCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicLongAlterCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 386;
=======
        int fileClientMessageIndex = 392;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        AtomicLongAlterCodec.RequestParameters parameters = AtomicLongAlterCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.function));
        assertTrue(isEqual(anInt, parameters.returnValueType));
    }

    @Test
    public void test_AtomicLongAlterCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 387;
=======
        int fileClientMessageIndex = 393;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicLongAlterCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicLongAddAndGetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 388;
=======
        int fileClientMessageIndex = 394;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        AtomicLongAddAndGetCodec.RequestParameters parameters = AtomicLongAddAndGetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.delta));
    }

    @Test
    public void test_AtomicLongAddAndGetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 389;
=======
        int fileClientMessageIndex = 395;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicLongAddAndGetCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicLongCompareAndSetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 390;
=======
        int fileClientMessageIndex = 396;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        AtomicLongCompareAndSetCodec.RequestParameters parameters = AtomicLongCompareAndSetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.expected));
        assertTrue(isEqual(aLong, parameters.updated));
    }

    @Test
    public void test_AtomicLongCompareAndSetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 391;
=======
        int fileClientMessageIndex = 397;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicLongCompareAndSetCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicLongGetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 392;
=======
        int fileClientMessageIndex = 398;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        AtomicLongGetCodec.RequestParameters parameters = AtomicLongGetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
    }

    @Test
    public void test_AtomicLongGetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 393;
=======
        int fileClientMessageIndex = 399;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicLongGetCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicLongGetAndAddCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 394;
=======
        int fileClientMessageIndex = 400;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        AtomicLongGetAndAddCodec.RequestParameters parameters = AtomicLongGetAndAddCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.delta));
    }

    @Test
    public void test_AtomicLongGetAndAddCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 395;
=======
        int fileClientMessageIndex = 401;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicLongGetAndAddCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicLongGetAndSetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 396;
=======
        int fileClientMessageIndex = 402;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        AtomicLongGetAndSetCodec.RequestParameters parameters = AtomicLongGetAndSetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.newValue));
    }

    @Test
    public void test_AtomicLongGetAndSetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 397;
=======
        int fileClientMessageIndex = 403;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicLongGetAndSetCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicRefApplyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 398;
=======
        int fileClientMessageIndex = 404;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        AtomicRefApplyCodec.RequestParameters parameters = AtomicRefApplyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.function));
        assertTrue(isEqual(anInt, parameters.returnValueType));
        assertTrue(isEqual(aBoolean, parameters.alter));
    }

    @Test
    public void test_AtomicRefApplyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 399;
=======
        int fileClientMessageIndex = 405;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicRefApplyCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicRefCompareAndSetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 400;
=======
        int fileClientMessageIndex = 406;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        AtomicRefCompareAndSetCodec.RequestParameters parameters = AtomicRefCompareAndSetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.oldValue));
        assertTrue(isEqual(aData, parameters.newValue));
    }

    @Test
    public void test_AtomicRefCompareAndSetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 401;
=======
        int fileClientMessageIndex = 407;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicRefCompareAndSetCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicRefContainsCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 402;
=======
        int fileClientMessageIndex = 408;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        AtomicRefContainsCodec.RequestParameters parameters = AtomicRefContainsCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_AtomicRefContainsCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 403;
=======
        int fileClientMessageIndex = 409;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicRefContainsCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicRefGetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 404;
=======
        int fileClientMessageIndex = 410;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        AtomicRefGetCodec.RequestParameters parameters = AtomicRefGetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
    }

    @Test
    public void test_AtomicRefGetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 405;
=======
        int fileClientMessageIndex = 411;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicRefGetCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_AtomicRefSetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 406;
=======
        int fileClientMessageIndex = 412;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        AtomicRefSetCodec.RequestParameters parameters = AtomicRefSetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.newValue));
        assertTrue(isEqual(aBoolean, parameters.returnOldValue));
    }

    @Test
    public void test_AtomicRefSetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 407;
=======
        int fileClientMessageIndex = 413;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = AtomicRefSetCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CountDownLatchTrySetCountCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 408;
=======
        int fileClientMessageIndex = 414;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CountDownLatchTrySetCountCodec.RequestParameters parameters = CountDownLatchTrySetCountCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.count));
    }

    @Test
    public void test_CountDownLatchTrySetCountCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 409;
=======
        int fileClientMessageIndex = 415;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CountDownLatchTrySetCountCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CountDownLatchAwaitCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 410;
=======
        int fileClientMessageIndex = 416;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CountDownLatchAwaitCodec.RequestParameters parameters = CountDownLatchAwaitCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.invocationUid));
        assertTrue(isEqual(aLong, parameters.timeoutMs));
    }

    @Test
    public void test_CountDownLatchAwaitCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 411;
=======
        int fileClientMessageIndex = 417;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CountDownLatchAwaitCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CountDownLatchCountDownCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 412;
=======
        int fileClientMessageIndex = 418;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CountDownLatchCountDownCodec.RequestParameters parameters = CountDownLatchCountDownCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.invocationUid));
        assertTrue(isEqual(anInt, parameters.expectedRound));
    }

    @Test
    public void test_CountDownLatchCountDownCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 413;
=======
        int fileClientMessageIndex = 419;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CountDownLatchCountDownCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CountDownLatchGetCountCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 414;
=======
        int fileClientMessageIndex = 420;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CountDownLatchGetCountCodec.RequestParameters parameters = CountDownLatchGetCountCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
    }

    @Test
    public void test_CountDownLatchGetCountCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 415;
=======
        int fileClientMessageIndex = 421;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CountDownLatchGetCountCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CountDownLatchGetRoundCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 416;
=======
        int fileClientMessageIndex = 422;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CountDownLatchGetRoundCodec.RequestParameters parameters = CountDownLatchGetRoundCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
    }

    @Test
    public void test_CountDownLatchGetRoundCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 417;
=======
        int fileClientMessageIndex = 423;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CountDownLatchGetRoundCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SemaphoreInitCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 418;
=======
        int fileClientMessageIndex = 424;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SemaphoreInitCodec.RequestParameters parameters = SemaphoreInitCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.permits));
    }

    @Test
    public void test_SemaphoreInitCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 419;
=======
        int fileClientMessageIndex = 425;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SemaphoreInitCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SemaphoreAcquireCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 420;
=======
        int fileClientMessageIndex = 426;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SemaphoreAcquireCodec.RequestParameters parameters = SemaphoreAcquireCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.sessionId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aUUID, parameters.invocationUid));
        assertTrue(isEqual(anInt, parameters.permits));
        assertTrue(isEqual(aLong, parameters.timeoutMs));
    }

    @Test
    public void test_SemaphoreAcquireCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 421;
=======
        int fileClientMessageIndex = 427;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SemaphoreAcquireCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SemaphoreReleaseCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 422;
=======
        int fileClientMessageIndex = 428;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SemaphoreReleaseCodec.RequestParameters parameters = SemaphoreReleaseCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.sessionId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aUUID, parameters.invocationUid));
        assertTrue(isEqual(anInt, parameters.permits));
    }

    @Test
    public void test_SemaphoreReleaseCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 423;
=======
        int fileClientMessageIndex = 429;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SemaphoreReleaseCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SemaphoreDrainCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 424;
=======
        int fileClientMessageIndex = 430;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SemaphoreDrainCodec.RequestParameters parameters = SemaphoreDrainCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.sessionId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aUUID, parameters.invocationUid));
    }

    @Test
    public void test_SemaphoreDrainCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 425;
=======
        int fileClientMessageIndex = 431;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SemaphoreDrainCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SemaphoreChangeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 426;
=======
        int fileClientMessageIndex = 432;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SemaphoreChangeCodec.RequestParameters parameters = SemaphoreChangeCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.sessionId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aUUID, parameters.invocationUid));
        assertTrue(isEqual(anInt, parameters.permits));
    }

    @Test
    public void test_SemaphoreChangeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 427;
=======
        int fileClientMessageIndex = 433;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SemaphoreChangeCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SemaphoreAvailablePermitsCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 428;
=======
        int fileClientMessageIndex = 434;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SemaphoreAvailablePermitsCodec.RequestParameters parameters = SemaphoreAvailablePermitsCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.name));
    }

    @Test
    public void test_SemaphoreAvailablePermitsCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 429;
=======
        int fileClientMessageIndex = 435;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SemaphoreAvailablePermitsCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SemaphoreGetSemaphoreTypeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 430;
=======
        int fileClientMessageIndex = 436;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, SemaphoreGetSemaphoreTypeCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_SemaphoreGetSemaphoreTypeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 431;
=======
        int fileClientMessageIndex = 437;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SemaphoreGetSemaphoreTypeCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapPutCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 432;
=======
        int fileClientMessageIndex = 438;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapPutCodec.RequestParameters parameters = ReplicatedMapPutCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.ttl));
    }

    @Test
    public void test_ReplicatedMapPutCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 433;
=======
        int fileClientMessageIndex = 439;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapPutCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapSizeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 434;
=======
        int fileClientMessageIndex = 440;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, ReplicatedMapSizeCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ReplicatedMapSizeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 435;
=======
        int fileClientMessageIndex = 441;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapSizeCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapIsEmptyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 436;
=======
        int fileClientMessageIndex = 442;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, ReplicatedMapIsEmptyCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ReplicatedMapIsEmptyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 437;
=======
        int fileClientMessageIndex = 443;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapIsEmptyCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapContainsKeyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 438;
=======
        int fileClientMessageIndex = 444;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapContainsKeyCodec.RequestParameters parameters = ReplicatedMapContainsKeyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
    }

    @Test
    public void test_ReplicatedMapContainsKeyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 439;
=======
        int fileClientMessageIndex = 445;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapContainsKeyCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapContainsValueCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 440;
=======
        int fileClientMessageIndex = 446;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapContainsValueCodec.RequestParameters parameters = ReplicatedMapContainsValueCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_ReplicatedMapContainsValueCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 441;
=======
        int fileClientMessageIndex = 447;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapContainsValueCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapGetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 442;
=======
        int fileClientMessageIndex = 448;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapGetCodec.RequestParameters parameters = ReplicatedMapGetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
    }

    @Test
    public void test_ReplicatedMapGetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 443;
=======
        int fileClientMessageIndex = 449;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapGetCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapRemoveCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 444;
=======
        int fileClientMessageIndex = 450;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapRemoveCodec.RequestParameters parameters = ReplicatedMapRemoveCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
    }

    @Test
    public void test_ReplicatedMapRemoveCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 445;
=======
        int fileClientMessageIndex = 451;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapRemoveCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapPutAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 446;
=======
        int fileClientMessageIndex = 452;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapPutAllCodec.RequestParameters parameters = ReplicatedMapPutAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfDataToData, parameters.entries));
    }

    @Test
    public void test_ReplicatedMapPutAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 447;
=======
        int fileClientMessageIndex = 453;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapPutAllCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapClearCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 448;
=======
        int fileClientMessageIndex = 454;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, ReplicatedMapClearCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ReplicatedMapClearCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 449;
=======
        int fileClientMessageIndex = 455;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapClearCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerToKeyWithPredicateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 450;
=======
        int fileClientMessageIndex = 456;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapAddEntryListenerToKeyWithPredicateCodec.RequestParameters parameters = ReplicatedMapAddEntryListenerToKeyWithPredicateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.predicate));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerToKeyWithPredicateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 451;
=======
        int fileClientMessageIndex = 457;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapAddEntryListenerToKeyWithPredicateCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerToKeyWithPredicateCodec_encodeEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 452;
=======
        int fileClientMessageIndex = 458;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = ReplicatedMapAddEntryListenerToKeyWithPredicateCodec.encodeEntryEvent(aData, aData, aData, aData, anInt, aUUID, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerWithPredicateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 453;
=======
        int fileClientMessageIndex = 459;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapAddEntryListenerWithPredicateCodec.RequestParameters parameters = ReplicatedMapAddEntryListenerWithPredicateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.predicate));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerWithPredicateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 454;
=======
        int fileClientMessageIndex = 460;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapAddEntryListenerWithPredicateCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerWithPredicateCodec_encodeEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 455;
=======
        int fileClientMessageIndex = 461;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = ReplicatedMapAddEntryListenerWithPredicateCodec.encodeEntryEvent(aData, aData, aData, aData, anInt, aUUID, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerToKeyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 456;
=======
        int fileClientMessageIndex = 462;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapAddEntryListenerToKeyCodec.RequestParameters parameters = ReplicatedMapAddEntryListenerToKeyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerToKeyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 457;
=======
        int fileClientMessageIndex = 463;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapAddEntryListenerToKeyCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerToKeyCodec_encodeEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 458;
=======
        int fileClientMessageIndex = 464;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = ReplicatedMapAddEntryListenerToKeyCodec.encodeEntryEvent(aData, aData, aData, aData, anInt, aUUID, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 459;
=======
        int fileClientMessageIndex = 465;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapAddEntryListenerCodec.RequestParameters parameters = ReplicatedMapAddEntryListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 460;
=======
        int fileClientMessageIndex = 466;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapAddEntryListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapAddEntryListenerCodec_encodeEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 461;
=======
        int fileClientMessageIndex = 467;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = ReplicatedMapAddEntryListenerCodec.encodeEntryEvent(aData, aData, aData, aData, anInt, aUUID, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapRemoveEntryListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 462;
=======
        int fileClientMessageIndex = 468;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapRemoveEntryListenerCodec.RequestParameters parameters = ReplicatedMapRemoveEntryListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.registrationId));
    }

    @Test
    public void test_ReplicatedMapRemoveEntryListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 463;
=======
        int fileClientMessageIndex = 469;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapRemoveEntryListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapKeySetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 464;
=======
        int fileClientMessageIndex = 470;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, ReplicatedMapKeySetCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ReplicatedMapKeySetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 465;
=======
        int fileClientMessageIndex = 471;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapKeySetCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapValuesCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 466;
=======
        int fileClientMessageIndex = 472;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, ReplicatedMapValuesCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ReplicatedMapValuesCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 467;
=======
        int fileClientMessageIndex = 473;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapValuesCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapEntrySetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 468;
=======
        int fileClientMessageIndex = 474;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, ReplicatedMapEntrySetCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ReplicatedMapEntrySetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 469;
=======
        int fileClientMessageIndex = 475;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapEntrySetCodec.encodeResponse(aListOfDataToData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapAddNearCacheEntryListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 470;
=======
        int fileClientMessageIndex = 476;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ReplicatedMapAddNearCacheEntryListenerCodec.RequestParameters parameters = ReplicatedMapAddNearCacheEntryListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aBoolean, parameters.includeValue));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_ReplicatedMapAddNearCacheEntryListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 471;
=======
        int fileClientMessageIndex = 477;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ReplicatedMapAddNearCacheEntryListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ReplicatedMapAddNearCacheEntryListenerCodec_encodeEntryEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 472;
=======
        int fileClientMessageIndex = 478;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = ReplicatedMapAddNearCacheEntryListenerCodec.encodeEntryEvent(aData, aData, aData, aData, anInt, aUUID, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapContainsKeyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 473;
=======
        int fileClientMessageIndex = 479;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapContainsKeyCodec.RequestParameters parameters = TransactionalMapContainsKeyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
    }

    @Test
    public void test_TransactionalMapContainsKeyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 474;
=======
        int fileClientMessageIndex = 480;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapContainsKeyCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapGetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 475;
=======
        int fileClientMessageIndex = 481;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapGetCodec.RequestParameters parameters = TransactionalMapGetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
    }

    @Test
    public void test_TransactionalMapGetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 476;
=======
        int fileClientMessageIndex = 482;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapGetCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapGetForUpdateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 477;
=======
        int fileClientMessageIndex = 483;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapGetForUpdateCodec.RequestParameters parameters = TransactionalMapGetForUpdateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
    }

    @Test
    public void test_TransactionalMapGetForUpdateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 478;
=======
        int fileClientMessageIndex = 484;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapGetForUpdateCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapSizeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 479;
=======
        int fileClientMessageIndex = 485;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapSizeCodec.RequestParameters parameters = TransactionalMapSizeCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_TransactionalMapSizeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 480;
=======
        int fileClientMessageIndex = 486;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapSizeCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapIsEmptyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 481;
=======
        int fileClientMessageIndex = 487;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapIsEmptyCodec.RequestParameters parameters = TransactionalMapIsEmptyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_TransactionalMapIsEmptyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 482;
=======
        int fileClientMessageIndex = 488;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapIsEmptyCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapPutCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 483;
=======
        int fileClientMessageIndex = 489;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapPutCodec.RequestParameters parameters = TransactionalMapPutCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aLong, parameters.ttl));
    }

    @Test
    public void test_TransactionalMapPutCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 484;
=======
        int fileClientMessageIndex = 490;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapPutCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapSetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 485;
=======
        int fileClientMessageIndex = 491;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapSetCodec.RequestParameters parameters = TransactionalMapSetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_TransactionalMapSetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 486;
=======
        int fileClientMessageIndex = 492;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapSetCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapPutIfAbsentCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 487;
=======
        int fileClientMessageIndex = 493;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapPutIfAbsentCodec.RequestParameters parameters = TransactionalMapPutIfAbsentCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_TransactionalMapPutIfAbsentCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 488;
=======
        int fileClientMessageIndex = 494;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapPutIfAbsentCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapReplaceCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 489;
=======
        int fileClientMessageIndex = 495;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapReplaceCodec.RequestParameters parameters = TransactionalMapReplaceCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_TransactionalMapReplaceCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 490;
=======
        int fileClientMessageIndex = 496;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapReplaceCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapReplaceIfSameCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 491;
=======
        int fileClientMessageIndex = 497;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapReplaceIfSameCodec.RequestParameters parameters = TransactionalMapReplaceIfSameCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.oldValue));
        assertTrue(isEqual(aData, parameters.newValue));
    }

    @Test
    public void test_TransactionalMapReplaceIfSameCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 492;
=======
        int fileClientMessageIndex = 498;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapReplaceIfSameCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapRemoveCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 493;
=======
        int fileClientMessageIndex = 499;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapRemoveCodec.RequestParameters parameters = TransactionalMapRemoveCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
    }

    @Test
    public void test_TransactionalMapRemoveCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 494;
=======
        int fileClientMessageIndex = 500;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapRemoveCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapDeleteCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 495;
=======
        int fileClientMessageIndex = 501;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapDeleteCodec.RequestParameters parameters = TransactionalMapDeleteCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
    }

    @Test
    public void test_TransactionalMapDeleteCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 496;
=======
        int fileClientMessageIndex = 502;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapDeleteCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapRemoveIfSameCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 497;
=======
        int fileClientMessageIndex = 503;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapRemoveIfSameCodec.RequestParameters parameters = TransactionalMapRemoveIfSameCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_TransactionalMapRemoveIfSameCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 498;
=======
        int fileClientMessageIndex = 504;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapRemoveIfSameCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapKeySetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 499;
=======
        int fileClientMessageIndex = 505;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapKeySetCodec.RequestParameters parameters = TransactionalMapKeySetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_TransactionalMapKeySetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 500;
=======
        int fileClientMessageIndex = 506;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapKeySetCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapKeySetWithPredicateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 501;
=======
        int fileClientMessageIndex = 507;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapKeySetWithPredicateCodec.RequestParameters parameters = TransactionalMapKeySetWithPredicateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.predicate));
    }

    @Test
    public void test_TransactionalMapKeySetWithPredicateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 502;
=======
        int fileClientMessageIndex = 508;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapKeySetWithPredicateCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapValuesCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 503;
=======
        int fileClientMessageIndex = 509;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapValuesCodec.RequestParameters parameters = TransactionalMapValuesCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_TransactionalMapValuesCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 504;
=======
        int fileClientMessageIndex = 510;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapValuesCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapValuesWithPredicateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 505;
=======
        int fileClientMessageIndex = 511;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapValuesWithPredicateCodec.RequestParameters parameters = TransactionalMapValuesWithPredicateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.predicate));
    }

    @Test
    public void test_TransactionalMapValuesWithPredicateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 506;
=======
        int fileClientMessageIndex = 512;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapValuesWithPredicateCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMapContainsValueCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 507;
=======
        int fileClientMessageIndex = 513;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMapContainsValueCodec.RequestParameters parameters = TransactionalMapContainsValueCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_TransactionalMapContainsValueCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 508;
=======
        int fileClientMessageIndex = 514;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMapContainsValueCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMultiMapPutCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 509;
=======
        int fileClientMessageIndex = 515;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMultiMapPutCodec.RequestParameters parameters = TransactionalMultiMapPutCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_TransactionalMultiMapPutCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 510;
=======
        int fileClientMessageIndex = 516;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMultiMapPutCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMultiMapGetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 511;
=======
        int fileClientMessageIndex = 517;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMultiMapGetCodec.RequestParameters parameters = TransactionalMultiMapGetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
    }

    @Test
    public void test_TransactionalMultiMapGetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 512;
=======
        int fileClientMessageIndex = 518;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMultiMapGetCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMultiMapRemoveCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 513;
=======
        int fileClientMessageIndex = 519;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMultiMapRemoveCodec.RequestParameters parameters = TransactionalMultiMapRemoveCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
    }

    @Test
    public void test_TransactionalMultiMapRemoveCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 514;
=======
        int fileClientMessageIndex = 520;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMultiMapRemoveCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMultiMapRemoveEntryCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 515;
=======
        int fileClientMessageIndex = 521;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMultiMapRemoveEntryCodec.RequestParameters parameters = TransactionalMultiMapRemoveEntryCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_TransactionalMultiMapRemoveEntryCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 516;
=======
        int fileClientMessageIndex = 522;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMultiMapRemoveEntryCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMultiMapValueCountCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 517;
=======
        int fileClientMessageIndex = 523;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMultiMapValueCountCodec.RequestParameters parameters = TransactionalMultiMapValueCountCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.key));
    }

    @Test
    public void test_TransactionalMultiMapValueCountCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 518;
=======
        int fileClientMessageIndex = 524;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMultiMapValueCountCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalMultiMapSizeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 519;
=======
        int fileClientMessageIndex = 525;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalMultiMapSizeCodec.RequestParameters parameters = TransactionalMultiMapSizeCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_TransactionalMultiMapSizeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 520;
=======
        int fileClientMessageIndex = 526;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalMultiMapSizeCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalSetAddCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 521;
=======
        int fileClientMessageIndex = 527;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalSetAddCodec.RequestParameters parameters = TransactionalSetAddCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.item));
    }

    @Test
    public void test_TransactionalSetAddCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 522;
=======
        int fileClientMessageIndex = 528;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalSetAddCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalSetRemoveCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 523;
=======
        int fileClientMessageIndex = 529;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalSetRemoveCodec.RequestParameters parameters = TransactionalSetRemoveCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.item));
    }

    @Test
    public void test_TransactionalSetRemoveCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 524;
=======
        int fileClientMessageIndex = 530;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalSetRemoveCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalSetSizeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 525;
=======
        int fileClientMessageIndex = 531;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalSetSizeCodec.RequestParameters parameters = TransactionalSetSizeCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_TransactionalSetSizeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 526;
=======
        int fileClientMessageIndex = 532;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalSetSizeCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalListAddCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 527;
=======
        int fileClientMessageIndex = 533;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalListAddCodec.RequestParameters parameters = TransactionalListAddCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.item));
    }

    @Test
    public void test_TransactionalListAddCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 528;
=======
        int fileClientMessageIndex = 534;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalListAddCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalListRemoveCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 529;
=======
        int fileClientMessageIndex = 535;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalListRemoveCodec.RequestParameters parameters = TransactionalListRemoveCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.item));
    }

    @Test
    public void test_TransactionalListRemoveCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 530;
=======
        int fileClientMessageIndex = 536;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalListRemoveCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalListSizeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 531;
=======
        int fileClientMessageIndex = 537;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalListSizeCodec.RequestParameters parameters = TransactionalListSizeCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_TransactionalListSizeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 532;
=======
        int fileClientMessageIndex = 538;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalListSizeCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalQueueOfferCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 533;
=======
        int fileClientMessageIndex = 539;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalQueueOfferCodec.RequestParameters parameters = TransactionalQueueOfferCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aData, parameters.item));
        assertTrue(isEqual(aLong, parameters.timeout));
    }

    @Test
    public void test_TransactionalQueueOfferCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 534;
=======
        int fileClientMessageIndex = 540;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalQueueOfferCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalQueueTakeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 535;
=======
        int fileClientMessageIndex = 541;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalQueueTakeCodec.RequestParameters parameters = TransactionalQueueTakeCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_TransactionalQueueTakeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 536;
=======
        int fileClientMessageIndex = 542;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalQueueTakeCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalQueuePollCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 537;
=======
        int fileClientMessageIndex = 543;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalQueuePollCodec.RequestParameters parameters = TransactionalQueuePollCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.timeout));
    }

    @Test
    public void test_TransactionalQueuePollCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 538;
=======
        int fileClientMessageIndex = 544;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalQueuePollCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalQueuePeekCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 539;
=======
        int fileClientMessageIndex = 545;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalQueuePeekCodec.RequestParameters parameters = TransactionalQueuePeekCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
        assertTrue(isEqual(aLong, parameters.timeout));
    }

    @Test
    public void test_TransactionalQueuePeekCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 540;
=======
        int fileClientMessageIndex = 546;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalQueuePeekCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionalQueueSizeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 541;
=======
        int fileClientMessageIndex = 547;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionalQueueSizeCodec.RequestParameters parameters = TransactionalQueueSizeCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.txnId));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_TransactionalQueueSizeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 542;
=======
        int fileClientMessageIndex = 548;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionalQueueSizeCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheAddEntryListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 543;
=======
        int fileClientMessageIndex = 549;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheAddEntryListenerCodec.RequestParameters parameters = CacheAddEntryListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_CacheAddEntryListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 544;
=======
        int fileClientMessageIndex = 550;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheAddEntryListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheAddEntryListenerCodec_encodeCacheEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 545;
=======
        int fileClientMessageIndex = 551;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = CacheAddEntryListenerCodec.encodeCacheEvent(anInt, aListOfCacheEventData, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheClearCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 546;
=======
        int fileClientMessageIndex = 552;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, CacheClearCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_CacheClearCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 547;
=======
        int fileClientMessageIndex = 553;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheClearCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheRemoveAllKeysCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 548;
=======
        int fileClientMessageIndex = 554;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheRemoveAllKeysCodec.RequestParameters parameters = CacheRemoveAllKeysCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.keys));
        assertTrue(isEqual(anInt, parameters.completionId));
    }

    @Test
    public void test_CacheRemoveAllKeysCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 549;
=======
        int fileClientMessageIndex = 555;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheRemoveAllKeysCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheRemoveAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 550;
=======
        int fileClientMessageIndex = 556;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheRemoveAllCodec.RequestParameters parameters = CacheRemoveAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.completionId));
    }

    @Test
    public void test_CacheRemoveAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 551;
=======
        int fileClientMessageIndex = 557;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheRemoveAllCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheContainsKeyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 552;
=======
        int fileClientMessageIndex = 558;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheContainsKeyCodec.RequestParameters parameters = CacheContainsKeyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
    }

    @Test
    public void test_CacheContainsKeyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 553;
=======
        int fileClientMessageIndex = 559;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheContainsKeyCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheCreateConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 554;
=======
        int fileClientMessageIndex = 560;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheCreateConfigCodec.RequestParameters parameters = CacheCreateConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aCacheConfigHolder, parameters.cacheConfig));
        assertTrue(isEqual(aBoolean, parameters.createAlsoOnOthers));
    }

    @Test
    public void test_CacheCreateConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 555;
=======
        int fileClientMessageIndex = 561;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheCreateConfigCodec.encodeResponse(aCacheConfigHolder);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheDestroyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 556;
=======
        int fileClientMessageIndex = 562;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, CacheDestroyCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_CacheDestroyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 557;
=======
        int fileClientMessageIndex = 563;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheDestroyCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheEntryProcessorCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 558;
=======
        int fileClientMessageIndex = 564;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheEntryProcessorCodec.RequestParameters parameters = CacheEntryProcessorCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.entryProcessor));
        assertTrue(isEqual(aListOfData, parameters.arguments));
        assertTrue(isEqual(anInt, parameters.completionId));
    }

    @Test
    public void test_CacheEntryProcessorCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 559;
=======
        int fileClientMessageIndex = 565;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheEntryProcessorCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheGetAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 560;
=======
        int fileClientMessageIndex = 566;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheGetAllCodec.RequestParameters parameters = CacheGetAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.keys));
        assertTrue(isEqual(aData, parameters.expiryPolicy));
    }

    @Test
    public void test_CacheGetAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 561;
=======
        int fileClientMessageIndex = 567;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheGetAllCodec.encodeResponse(aListOfDataToData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheGetAndRemoveCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 562;
=======
        int fileClientMessageIndex = 568;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheGetAndRemoveCodec.RequestParameters parameters = CacheGetAndRemoveCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(anInt, parameters.completionId));
    }

    @Test
    public void test_CacheGetAndRemoveCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 563;
=======
        int fileClientMessageIndex = 569;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheGetAndRemoveCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheGetAndReplaceCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 564;
=======
        int fileClientMessageIndex = 570;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheGetAndReplaceCodec.RequestParameters parameters = CacheGetAndReplaceCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aData, parameters.expiryPolicy));
        assertTrue(isEqual(anInt, parameters.completionId));
    }

    @Test
    public void test_CacheGetAndReplaceCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 565;
=======
        int fileClientMessageIndex = 571;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheGetAndReplaceCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheGetConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 566;
=======
        int fileClientMessageIndex = 572;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheGetConfigCodec.RequestParameters parameters = CacheGetConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aString, parameters.simpleName));
    }

    @Test
    public void test_CacheGetConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 567;
=======
        int fileClientMessageIndex = 573;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheGetConfigCodec.encodeResponse(aCacheConfigHolder);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheGetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 568;
=======
        int fileClientMessageIndex = 574;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheGetCodec.RequestParameters parameters = CacheGetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.expiryPolicy));
    }

    @Test
    public void test_CacheGetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 569;
=======
        int fileClientMessageIndex = 575;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheGetCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheIterateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 570;
=======
        int fileClientMessageIndex = 576;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheIterateCodec.RequestParameters parameters = CacheIterateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfIntegerToInteger, parameters.iterationPointers));
        assertTrue(isEqual(anInt, parameters.batch));
    }

    @Test
    public void test_CacheIterateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 571;
=======
        int fileClientMessageIndex = 577;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheIterateCodec.encodeResponse(aListOfIntegerToInteger, aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheListenerRegistrationCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 572;
=======
        int fileClientMessageIndex = 578;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheListenerRegistrationCodec.RequestParameters parameters = CacheListenerRegistrationCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.listenerConfig));
        assertTrue(isEqual(aBoolean, parameters.shouldRegister));
        assertTrue(isEqual(aUUID, parameters.uuid));
    }

    @Test
    public void test_CacheListenerRegistrationCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 573;
=======
        int fileClientMessageIndex = 579;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheListenerRegistrationCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheLoadAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 574;
=======
        int fileClientMessageIndex = 580;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheLoadAllCodec.RequestParameters parameters = CacheLoadAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.keys));
        assertTrue(isEqual(aBoolean, parameters.replaceExistingValues));
    }

    @Test
    public void test_CacheLoadAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 575;
=======
        int fileClientMessageIndex = 581;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheLoadAllCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheManagementConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 576;
=======
        int fileClientMessageIndex = 582;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheManagementConfigCodec.RequestParameters parameters = CacheManagementConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aBoolean, parameters.isStat));
        assertTrue(isEqual(aBoolean, parameters.enabled));
        assertTrue(isEqual(aUUID, parameters.uuid));
    }

    @Test
    public void test_CacheManagementConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 577;
=======
        int fileClientMessageIndex = 583;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheManagementConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CachePutIfAbsentCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 578;
=======
        int fileClientMessageIndex = 584;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CachePutIfAbsentCodec.RequestParameters parameters = CachePutIfAbsentCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aData, parameters.expiryPolicy));
        assertTrue(isEqual(anInt, parameters.completionId));
    }

    @Test
    public void test_CachePutIfAbsentCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 579;
=======
        int fileClientMessageIndex = 585;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CachePutIfAbsentCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CachePutCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 580;
=======
        int fileClientMessageIndex = 586;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CachePutCodec.RequestParameters parameters = CachePutCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.value));
        assertTrue(isEqual(aData, parameters.expiryPolicy));
        assertTrue(isEqual(aBoolean, parameters.get));
        assertTrue(isEqual(anInt, parameters.completionId));
    }

    @Test
    public void test_CachePutCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 581;
=======
        int fileClientMessageIndex = 587;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CachePutCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheRemoveEntryListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 582;
=======
        int fileClientMessageIndex = 588;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheRemoveEntryListenerCodec.RequestParameters parameters = CacheRemoveEntryListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.registrationId));
    }

    @Test
    public void test_CacheRemoveEntryListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 583;
=======
        int fileClientMessageIndex = 589;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheRemoveEntryListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheRemoveInvalidationListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 584;
=======
        int fileClientMessageIndex = 590;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheRemoveInvalidationListenerCodec.RequestParameters parameters = CacheRemoveInvalidationListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.registrationId));
    }

    @Test
    public void test_CacheRemoveInvalidationListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 585;
=======
        int fileClientMessageIndex = 591;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheRemoveInvalidationListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheRemoveCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 586;
=======
        int fileClientMessageIndex = 592;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheRemoveCodec.RequestParameters parameters = CacheRemoveCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.currentValue));
        assertTrue(isEqual(anInt, parameters.completionId));
    }

    @Test
    public void test_CacheRemoveCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 587;
=======
        int fileClientMessageIndex = 593;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheRemoveCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheReplaceCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 588;
=======
        int fileClientMessageIndex = 594;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheReplaceCodec.RequestParameters parameters = CacheReplaceCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.key));
        assertTrue(isEqual(aData, parameters.oldValue));
        assertTrue(isEqual(aData, parameters.newValue));
        assertTrue(isEqual(aData, parameters.expiryPolicy));
        assertTrue(isEqual(anInt, parameters.completionId));
    }

    @Test
    public void test_CacheReplaceCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 589;
=======
        int fileClientMessageIndex = 595;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheReplaceCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheSizeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 590;
=======
        int fileClientMessageIndex = 596;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, CacheSizeCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_CacheSizeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 591;
=======
        int fileClientMessageIndex = 597;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheSizeCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheAddPartitionLostListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 592;
=======
        int fileClientMessageIndex = 598;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheAddPartitionLostListenerCodec.RequestParameters parameters = CacheAddPartitionLostListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_CacheAddPartitionLostListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 593;
=======
        int fileClientMessageIndex = 599;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheAddPartitionLostListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheAddPartitionLostListenerCodec_encodeCachePartitionLostEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 594;
=======
        int fileClientMessageIndex = 600;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = CacheAddPartitionLostListenerCodec.encodeCachePartitionLostEvent(anInt, aUUID);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheRemovePartitionLostListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 595;
=======
        int fileClientMessageIndex = 601;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheRemovePartitionLostListenerCodec.RequestParameters parameters = CacheRemovePartitionLostListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aUUID, parameters.registrationId));
    }

    @Test
    public void test_CacheRemovePartitionLostListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 596;
=======
        int fileClientMessageIndex = 602;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheRemovePartitionLostListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CachePutAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 597;
=======
        int fileClientMessageIndex = 603;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CachePutAllCodec.RequestParameters parameters = CachePutAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfDataToData, parameters.entries));
        assertTrue(isEqual(aData, parameters.expiryPolicy));
        assertTrue(isEqual(anInt, parameters.completionId));
    }

    @Test
    public void test_CachePutAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 598;
=======
        int fileClientMessageIndex = 604;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CachePutAllCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheIterateEntriesCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 599;
=======
        int fileClientMessageIndex = 605;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheIterateEntriesCodec.RequestParameters parameters = CacheIterateEntriesCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfIntegerToInteger, parameters.iterationPointers));
        assertTrue(isEqual(anInt, parameters.batch));
    }

    @Test
    public void test_CacheIterateEntriesCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 600;
=======
        int fileClientMessageIndex = 606;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheIterateEntriesCodec.encodeResponse(aListOfIntegerToInteger, aListOfDataToData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheAddNearCacheInvalidationListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 601;
=======
        int fileClientMessageIndex = 607;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheAddNearCacheInvalidationListenerCodec.RequestParameters parameters = CacheAddNearCacheInvalidationListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_CacheAddNearCacheInvalidationListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 602;
=======
        int fileClientMessageIndex = 608;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheAddNearCacheInvalidationListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheAddNearCacheInvalidationListenerCodec_encodeCacheInvalidationEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 603;
=======
        int fileClientMessageIndex = 609;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = CacheAddNearCacheInvalidationListenerCodec.encodeCacheInvalidationEvent(aString, aData, aUUID, aUUID, aLong);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheAddNearCacheInvalidationListenerCodec_encodeCacheBatchInvalidationEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 604;
=======
        int fileClientMessageIndex = 610;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = CacheAddNearCacheInvalidationListenerCodec.encodeCacheBatchInvalidationEvent(aString, aListOfData, aListOfUUIDs, aListOfUUIDs, aListOfLongs);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheFetchNearCacheInvalidationMetadataCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 605;
=======
        int fileClientMessageIndex = 611;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheFetchNearCacheInvalidationMetadataCodec.RequestParameters parameters = CacheFetchNearCacheInvalidationMetadataCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aListOfStrings, parameters.names));
        assertTrue(isEqual(aUUID, parameters.uuid));
    }

    @Test
    public void test_CacheFetchNearCacheInvalidationMetadataCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 606;
=======
        int fileClientMessageIndex = 612;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheFetchNearCacheInvalidationMetadataCodec.encodeResponse(aListOfStringToListOfIntegerToLong, aListOfIntegerToUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheEventJournalSubscribeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 607;
=======
        int fileClientMessageIndex = 613;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, CacheEventJournalSubscribeCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_CacheEventJournalSubscribeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 608;
=======
        int fileClientMessageIndex = 614;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheEventJournalSubscribeCodec.encodeResponse(aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheEventJournalReadCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 609;
=======
        int fileClientMessageIndex = 615;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheEventJournalReadCodec.RequestParameters parameters = CacheEventJournalReadCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.startSequence));
        assertTrue(isEqual(anInt, parameters.minSize));
        assertTrue(isEqual(anInt, parameters.maxSize));
        assertTrue(isEqual(aData, parameters.predicate));
        assertTrue(isEqual(aData, parameters.projection));
    }

    @Test
    public void test_CacheEventJournalReadCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 610;
=======
        int fileClientMessageIndex = 616;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheEventJournalReadCodec.encodeResponse(anInt, aListOfData, aLongArray, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CacheSetExpiryPolicyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 611;
=======
        int fileClientMessageIndex = 617;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CacheSetExpiryPolicyCodec.RequestParameters parameters = CacheSetExpiryPolicyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.keys));
        assertTrue(isEqual(aData, parameters.expiryPolicy));
    }

    @Test
    public void test_CacheSetExpiryPolicyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 612;
=======
        int fileClientMessageIndex = 618;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CacheSetExpiryPolicyCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_XATransactionClearRemoteCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 613;
=======
        int fileClientMessageIndex = 619;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anXid, XATransactionClearRemoteCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_XATransactionClearRemoteCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 614;
=======
        int fileClientMessageIndex = 620;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = XATransactionClearRemoteCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_XATransactionCollectTransactionsCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 615;
=======
        int fileClientMessageIndex = 621;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_XATransactionCollectTransactionsCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 616;
=======
        int fileClientMessageIndex = 622;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = XATransactionCollectTransactionsCodec.encodeResponse(aListOfXids);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_XATransactionFinalizeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 617;
=======
        int fileClientMessageIndex = 623;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        XATransactionFinalizeCodec.RequestParameters parameters = XATransactionFinalizeCodec.decodeRequest(fromFile);
        assertTrue(isEqual(anXid, parameters.xid));
        assertTrue(isEqual(aBoolean, parameters.isCommit));
    }

    @Test
    public void test_XATransactionFinalizeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 618;
=======
        int fileClientMessageIndex = 624;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = XATransactionFinalizeCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_XATransactionCommitCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 619;
=======
        int fileClientMessageIndex = 625;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        XATransactionCommitCodec.RequestParameters parameters = XATransactionCommitCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aUUID, parameters.transactionId));
        assertTrue(isEqual(aBoolean, parameters.onePhase));
    }

    @Test
    public void test_XATransactionCommitCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 620;
=======
        int fileClientMessageIndex = 626;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = XATransactionCommitCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_XATransactionCreateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 621;
=======
        int fileClientMessageIndex = 627;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        XATransactionCreateCodec.RequestParameters parameters = XATransactionCreateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(anXid, parameters.xid));
        assertTrue(isEqual(aLong, parameters.timeout));
    }

    @Test
    public void test_XATransactionCreateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 622;
=======
        int fileClientMessageIndex = 628;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = XATransactionCreateCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_XATransactionPrepareCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 623;
=======
        int fileClientMessageIndex = 629;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, XATransactionPrepareCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_XATransactionPrepareCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 624;
=======
        int fileClientMessageIndex = 630;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = XATransactionPrepareCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_XATransactionRollbackCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 625;
=======
        int fileClientMessageIndex = 631;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, XATransactionRollbackCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_XATransactionRollbackCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 626;
=======
        int fileClientMessageIndex = 632;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = XATransactionRollbackCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionCommitCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 627;
=======
        int fileClientMessageIndex = 633;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionCommitCodec.RequestParameters parameters = TransactionCommitCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aUUID, parameters.transactionId));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_TransactionCommitCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 628;
=======
        int fileClientMessageIndex = 634;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionCommitCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionCreateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 629;
=======
        int fileClientMessageIndex = 635;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionCreateCodec.RequestParameters parameters = TransactionCreateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aLong, parameters.timeout));
        assertTrue(isEqual(anInt, parameters.durability));
        assertTrue(isEqual(anInt, parameters.transactionType));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_TransactionCreateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 630;
=======
        int fileClientMessageIndex = 636;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionCreateCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_TransactionRollbackCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 631;
=======
        int fileClientMessageIndex = 637;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        TransactionRollbackCodec.RequestParameters parameters = TransactionRollbackCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aUUID, parameters.transactionId));
        assertTrue(isEqual(aLong, parameters.threadId));
    }

    @Test
    public void test_TransactionRollbackCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 632;
=======
        int fileClientMessageIndex = 638;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = TransactionRollbackCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ContinuousQueryPublisherCreateWithValueCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 633;
=======
        int fileClientMessageIndex = 639;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ContinuousQueryPublisherCreateWithValueCodec.RequestParameters parameters = ContinuousQueryPublisherCreateWithValueCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.mapName));
        assertTrue(isEqual(aString, parameters.cacheName));
        assertTrue(isEqual(aData, parameters.predicate));
        assertTrue(isEqual(anInt, parameters.batchSize));
        assertTrue(isEqual(anInt, parameters.bufferSize));
        assertTrue(isEqual(aLong, parameters.delaySeconds));
        assertTrue(isEqual(aBoolean, parameters.populate));
        assertTrue(isEqual(aBoolean, parameters.coalesce));
    }

    @Test
    public void test_ContinuousQueryPublisherCreateWithValueCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 634;
=======
        int fileClientMessageIndex = 640;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ContinuousQueryPublisherCreateWithValueCodec.encodeResponse(aListOfDataToData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ContinuousQueryPublisherCreateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 635;
=======
        int fileClientMessageIndex = 641;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ContinuousQueryPublisherCreateCodec.RequestParameters parameters = ContinuousQueryPublisherCreateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.mapName));
        assertTrue(isEqual(aString, parameters.cacheName));
        assertTrue(isEqual(aData, parameters.predicate));
        assertTrue(isEqual(anInt, parameters.batchSize));
        assertTrue(isEqual(anInt, parameters.bufferSize));
        assertTrue(isEqual(aLong, parameters.delaySeconds));
        assertTrue(isEqual(aBoolean, parameters.populate));
        assertTrue(isEqual(aBoolean, parameters.coalesce));
    }

    @Test
    public void test_ContinuousQueryPublisherCreateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 636;
=======
        int fileClientMessageIndex = 642;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ContinuousQueryPublisherCreateCodec.encodeResponse(aListOfData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ContinuousQueryMadePublishableCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 637;
=======
        int fileClientMessageIndex = 643;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ContinuousQueryMadePublishableCodec.RequestParameters parameters = ContinuousQueryMadePublishableCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.mapName));
        assertTrue(isEqual(aString, parameters.cacheName));
    }

    @Test
    public void test_ContinuousQueryMadePublishableCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 638;
=======
        int fileClientMessageIndex = 644;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ContinuousQueryMadePublishableCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ContinuousQueryAddListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 639;
=======
        int fileClientMessageIndex = 645;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ContinuousQueryAddListenerCodec.RequestParameters parameters = ContinuousQueryAddListenerCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.listenerName));
        assertTrue(isEqual(aBoolean, parameters.localOnly));
    }

    @Test
    public void test_ContinuousQueryAddListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 640;
=======
        int fileClientMessageIndex = 646;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ContinuousQueryAddListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ContinuousQueryAddListenerCodec_encodeQueryCacheSingleEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 641;
=======
        int fileClientMessageIndex = 647;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = ContinuousQueryAddListenerCodec.encodeQueryCacheSingleEvent(aQueryCacheEventData);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ContinuousQueryAddListenerCodec_encodeQueryCacheBatchEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 642;
=======
        int fileClientMessageIndex = 648;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = ContinuousQueryAddListenerCodec.encodeQueryCacheBatchEvent(aListOfQueryCacheEventData, aString, anInt);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ContinuousQuerySetReadCursorCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 643;
=======
        int fileClientMessageIndex = 649;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ContinuousQuerySetReadCursorCodec.RequestParameters parameters = ContinuousQuerySetReadCursorCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.mapName));
        assertTrue(isEqual(aString, parameters.cacheName));
        assertTrue(isEqual(aLong, parameters.sequence));
    }

    @Test
    public void test_ContinuousQuerySetReadCursorCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 644;
=======
        int fileClientMessageIndex = 650;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ContinuousQuerySetReadCursorCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ContinuousQueryDestroyCacheCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 645;
=======
        int fileClientMessageIndex = 651;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ContinuousQueryDestroyCacheCodec.RequestParameters parameters = ContinuousQueryDestroyCacheCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.mapName));
        assertTrue(isEqual(aString, parameters.cacheName));
    }

    @Test
    public void test_ContinuousQueryDestroyCacheCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 646;
=======
        int fileClientMessageIndex = 652;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ContinuousQueryDestroyCacheCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferSizeCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 647;
=======
        int fileClientMessageIndex = 653;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, RingbufferSizeCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_RingbufferSizeCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 648;
=======
        int fileClientMessageIndex = 654;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferSizeCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferTailSequenceCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 649;
=======
        int fileClientMessageIndex = 655;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, RingbufferTailSequenceCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_RingbufferTailSequenceCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 650;
=======
        int fileClientMessageIndex = 656;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferTailSequenceCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferHeadSequenceCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 651;
=======
        int fileClientMessageIndex = 657;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, RingbufferHeadSequenceCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_RingbufferHeadSequenceCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 652;
=======
        int fileClientMessageIndex = 658;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferHeadSequenceCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferCapacityCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 653;
=======
        int fileClientMessageIndex = 659;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, RingbufferCapacityCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_RingbufferCapacityCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 654;
=======
        int fileClientMessageIndex = 660;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferCapacityCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferRemainingCapacityCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 655;
=======
        int fileClientMessageIndex = 661;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, RingbufferRemainingCapacityCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_RingbufferRemainingCapacityCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 656;
=======
        int fileClientMessageIndex = 662;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferRemainingCapacityCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferAddCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 657;
=======
        int fileClientMessageIndex = 663;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        RingbufferAddCodec.RequestParameters parameters = RingbufferAddCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.overflowPolicy));
        assertTrue(isEqual(aData, parameters.value));
    }

    @Test
    public void test_RingbufferAddCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 658;
=======
        int fileClientMessageIndex = 664;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferAddCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferReadOneCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 659;
=======
        int fileClientMessageIndex = 665;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        RingbufferReadOneCodec.RequestParameters parameters = RingbufferReadOneCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.sequence));
    }

    @Test
    public void test_RingbufferReadOneCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 660;
=======
        int fileClientMessageIndex = 666;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferReadOneCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferAddAllCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 661;
=======
        int fileClientMessageIndex = 667;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        RingbufferAddAllCodec.RequestParameters parameters = RingbufferAddAllCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfData, parameters.valueList));
        assertTrue(isEqual(anInt, parameters.overflowPolicy));
    }

    @Test
    public void test_RingbufferAddAllCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 662;
=======
        int fileClientMessageIndex = 668;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferAddAllCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_RingbufferReadManyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 663;
=======
        int fileClientMessageIndex = 669;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        RingbufferReadManyCodec.RequestParameters parameters = RingbufferReadManyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.startSequence));
        assertTrue(isEqual(anInt, parameters.minCount));
        assertTrue(isEqual(anInt, parameters.maxCount));
        assertTrue(isEqual(aData, parameters.filter));
    }

    @Test
    public void test_RingbufferReadManyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 664;
=======
        int fileClientMessageIndex = 670;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = RingbufferReadManyCodec.encodeResponse(anInt, aListOfData, aLongArray, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DurableExecutorShutdownCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 665;
=======
        int fileClientMessageIndex = 671;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, DurableExecutorShutdownCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_DurableExecutorShutdownCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 666;
=======
        int fileClientMessageIndex = 672;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DurableExecutorShutdownCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DurableExecutorIsShutdownCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 667;
=======
        int fileClientMessageIndex = 673;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, DurableExecutorIsShutdownCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_DurableExecutorIsShutdownCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 668;
=======
        int fileClientMessageIndex = 674;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DurableExecutorIsShutdownCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DurableExecutorSubmitToPartitionCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 669;
=======
        int fileClientMessageIndex = 675;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DurableExecutorSubmitToPartitionCodec.RequestParameters parameters = DurableExecutorSubmitToPartitionCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aData, parameters.callable));
    }

    @Test
    public void test_DurableExecutorSubmitToPartitionCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 670;
=======
        int fileClientMessageIndex = 676;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DurableExecutorSubmitToPartitionCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DurableExecutorRetrieveResultCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 671;
=======
        int fileClientMessageIndex = 677;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DurableExecutorRetrieveResultCodec.RequestParameters parameters = DurableExecutorRetrieveResultCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.sequence));
    }

    @Test
    public void test_DurableExecutorRetrieveResultCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 672;
=======
        int fileClientMessageIndex = 678;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DurableExecutorRetrieveResultCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DurableExecutorDisposeResultCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 673;
=======
        int fileClientMessageIndex = 679;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DurableExecutorDisposeResultCodec.RequestParameters parameters = DurableExecutorDisposeResultCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.sequence));
    }

    @Test
    public void test_DurableExecutorDisposeResultCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 674;
=======
        int fileClientMessageIndex = 680;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DurableExecutorDisposeResultCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DurableExecutorRetrieveAndDisposeResultCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 675;
=======
        int fileClientMessageIndex = 681;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DurableExecutorRetrieveAndDisposeResultCodec.RequestParameters parameters = DurableExecutorRetrieveAndDisposeResultCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.sequence));
    }

    @Test
    public void test_DurableExecutorRetrieveAndDisposeResultCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 676;
=======
        int fileClientMessageIndex = 682;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DurableExecutorRetrieveAndDisposeResultCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CardinalityEstimatorAddCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 677;
=======
        int fileClientMessageIndex = 683;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CardinalityEstimatorAddCodec.RequestParameters parameters = CardinalityEstimatorAddCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.hash));
    }

    @Test
    public void test_CardinalityEstimatorAddCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 678;
=======
        int fileClientMessageIndex = 684;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CardinalityEstimatorAddCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CardinalityEstimatorEstimateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 679;
=======
        int fileClientMessageIndex = 685;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, CardinalityEstimatorEstimateCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_CardinalityEstimatorEstimateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 680;
=======
        int fileClientMessageIndex = 686;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CardinalityEstimatorEstimateCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorShutdownCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 681;
=======
        int fileClientMessageIndex = 687;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorShutdownCodec.RequestParameters parameters = ScheduledExecutorShutdownCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aUUID, parameters.memberUuid));
    }

    @Test
    public void test_ScheduledExecutorShutdownCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 682;
=======
        int fileClientMessageIndex = 688;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorShutdownCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorSubmitToPartitionCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 683;
=======
        int fileClientMessageIndex = 689;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorSubmitToPartitionCodec.RequestParameters parameters = ScheduledExecutorSubmitToPartitionCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aByte, parameters.type));
        assertTrue(isEqual(aString, parameters.taskName));
        assertTrue(isEqual(aData, parameters.task));
        assertTrue(isEqual(aLong, parameters.initialDelayInMillis));
        assertTrue(isEqual(aLong, parameters.periodInMillis));
        assertTrue(parameters.isAutoDisposableExists);
        assertTrue(isEqual(aBoolean, parameters.autoDisposable));
    }

    @Test
    public void test_ScheduledExecutorSubmitToPartitionCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 684;
=======
        int fileClientMessageIndex = 690;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorSubmitToPartitionCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorSubmitToMemberCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 685;
=======
        int fileClientMessageIndex = 691;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorSubmitToMemberCodec.RequestParameters parameters = ScheduledExecutorSubmitToMemberCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aUUID, parameters.memberUuid));
        assertTrue(isEqual(aByte, parameters.type));
        assertTrue(isEqual(aString, parameters.taskName));
        assertTrue(isEqual(aData, parameters.task));
        assertTrue(isEqual(aLong, parameters.initialDelayInMillis));
        assertTrue(isEqual(aLong, parameters.periodInMillis));
        assertTrue(parameters.isAutoDisposableExists);
        assertTrue(isEqual(aBoolean, parameters.autoDisposable));
    }

    @Test
    public void test_ScheduledExecutorSubmitToMemberCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 686;
=======
        int fileClientMessageIndex = 692;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorSubmitToMemberCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorGetAllScheduledFuturesCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 687;
=======
        int fileClientMessageIndex = 693;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, ScheduledExecutorGetAllScheduledFuturesCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_ScheduledExecutorGetAllScheduledFuturesCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 688;
=======
        int fileClientMessageIndex = 694;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorGetAllScheduledFuturesCodec.encodeResponse(aListOfScheduledTaskHandler);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorGetStatsFromPartitionCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 689;
=======
        int fileClientMessageIndex = 695;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorGetStatsFromPartitionCodec.RequestParameters parameters = ScheduledExecutorGetStatsFromPartitionCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aString, parameters.taskName));
    }

    @Test
    public void test_ScheduledExecutorGetStatsFromPartitionCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 690;
=======
        int fileClientMessageIndex = 696;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorGetStatsFromPartitionCodec.encodeResponse(aLong, aLong, aLong, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorGetStatsFromMemberCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 691;
=======
        int fileClientMessageIndex = 697;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorGetStatsFromMemberCodec.RequestParameters parameters = ScheduledExecutorGetStatsFromMemberCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aString, parameters.taskName));
        assertTrue(isEqual(aUUID, parameters.memberUuid));
    }

    @Test
    public void test_ScheduledExecutorGetStatsFromMemberCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 692;
=======
        int fileClientMessageIndex = 698;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorGetStatsFromMemberCodec.encodeResponse(aLong, aLong, aLong, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorGetDelayFromPartitionCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 693;
=======
        int fileClientMessageIndex = 699;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorGetDelayFromPartitionCodec.RequestParameters parameters = ScheduledExecutorGetDelayFromPartitionCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aString, parameters.taskName));
    }

    @Test
    public void test_ScheduledExecutorGetDelayFromPartitionCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 694;
=======
        int fileClientMessageIndex = 700;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorGetDelayFromPartitionCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorGetDelayFromMemberCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 695;
=======
        int fileClientMessageIndex = 701;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorGetDelayFromMemberCodec.RequestParameters parameters = ScheduledExecutorGetDelayFromMemberCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aString, parameters.taskName));
        assertTrue(isEqual(aUUID, parameters.memberUuid));
    }

    @Test
    public void test_ScheduledExecutorGetDelayFromMemberCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 696;
=======
        int fileClientMessageIndex = 702;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorGetDelayFromMemberCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorCancelFromPartitionCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 697;
=======
        int fileClientMessageIndex = 703;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorCancelFromPartitionCodec.RequestParameters parameters = ScheduledExecutorCancelFromPartitionCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aString, parameters.taskName));
        assertTrue(isEqual(aBoolean, parameters.mayInterruptIfRunning));
    }

    @Test
    public void test_ScheduledExecutorCancelFromPartitionCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 698;
=======
        int fileClientMessageIndex = 704;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorCancelFromPartitionCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorCancelFromMemberCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 699;
=======
        int fileClientMessageIndex = 705;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorCancelFromMemberCodec.RequestParameters parameters = ScheduledExecutorCancelFromMemberCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aString, parameters.taskName));
        assertTrue(isEqual(aUUID, parameters.memberUuid));
        assertTrue(isEqual(aBoolean, parameters.mayInterruptIfRunning));
    }

    @Test
    public void test_ScheduledExecutorCancelFromMemberCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 700;
=======
        int fileClientMessageIndex = 706;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorCancelFromMemberCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorIsCancelledFromPartitionCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 701;
=======
        int fileClientMessageIndex = 707;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorIsCancelledFromPartitionCodec.RequestParameters parameters = ScheduledExecutorIsCancelledFromPartitionCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aString, parameters.taskName));
    }

    @Test
    public void test_ScheduledExecutorIsCancelledFromPartitionCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 702;
=======
        int fileClientMessageIndex = 708;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorIsCancelledFromPartitionCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorIsCancelledFromMemberCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 703;
=======
        int fileClientMessageIndex = 709;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorIsCancelledFromMemberCodec.RequestParameters parameters = ScheduledExecutorIsCancelledFromMemberCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aString, parameters.taskName));
        assertTrue(isEqual(aUUID, parameters.memberUuid));
    }

    @Test
    public void test_ScheduledExecutorIsCancelledFromMemberCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 704;
=======
        int fileClientMessageIndex = 710;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorIsCancelledFromMemberCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorIsDoneFromPartitionCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 705;
=======
        int fileClientMessageIndex = 711;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorIsDoneFromPartitionCodec.RequestParameters parameters = ScheduledExecutorIsDoneFromPartitionCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aString, parameters.taskName));
    }

    @Test
    public void test_ScheduledExecutorIsDoneFromPartitionCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 706;
=======
        int fileClientMessageIndex = 712;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorIsDoneFromPartitionCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorIsDoneFromMemberCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 707;
=======
        int fileClientMessageIndex = 713;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorIsDoneFromMemberCodec.RequestParameters parameters = ScheduledExecutorIsDoneFromMemberCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aString, parameters.taskName));
        assertTrue(isEqual(aUUID, parameters.memberUuid));
    }

    @Test
    public void test_ScheduledExecutorIsDoneFromMemberCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 708;
=======
        int fileClientMessageIndex = 714;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorIsDoneFromMemberCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorGetResultFromPartitionCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 709;
=======
        int fileClientMessageIndex = 715;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorGetResultFromPartitionCodec.RequestParameters parameters = ScheduledExecutorGetResultFromPartitionCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aString, parameters.taskName));
    }

    @Test
    public void test_ScheduledExecutorGetResultFromPartitionCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 710;
=======
        int fileClientMessageIndex = 716;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorGetResultFromPartitionCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorGetResultFromMemberCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 711;
=======
        int fileClientMessageIndex = 717;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorGetResultFromMemberCodec.RequestParameters parameters = ScheduledExecutorGetResultFromMemberCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aString, parameters.taskName));
        assertTrue(isEqual(aUUID, parameters.memberUuid));
    }

    @Test
    public void test_ScheduledExecutorGetResultFromMemberCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 712;
=======
        int fileClientMessageIndex = 718;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorGetResultFromMemberCodec.encodeResponse(aData);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorDisposeFromPartitionCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 713;
=======
        int fileClientMessageIndex = 719;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorDisposeFromPartitionCodec.RequestParameters parameters = ScheduledExecutorDisposeFromPartitionCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aString, parameters.taskName));
    }

    @Test
    public void test_ScheduledExecutorDisposeFromPartitionCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 714;
=======
        int fileClientMessageIndex = 720;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorDisposeFromPartitionCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_ScheduledExecutorDisposeFromMemberCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 715;
=======
        int fileClientMessageIndex = 721;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ScheduledExecutorDisposeFromMemberCodec.RequestParameters parameters = ScheduledExecutorDisposeFromMemberCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.schedulerName));
        assertTrue(isEqual(aString, parameters.taskName));
        assertTrue(isEqual(aUUID, parameters.memberUuid));
    }

    @Test
    public void test_ScheduledExecutorDisposeFromMemberCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 716;
=======
        int fileClientMessageIndex = 722;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = ScheduledExecutorDisposeFromMemberCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddMultiMapConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 717;
=======
        int fileClientMessageIndex = 723;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddMultiMapConfigCodec.RequestParameters parameters = DynamicConfigAddMultiMapConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aString, parameters.collectionType));
        assertTrue(isEqual(aListOfListenerConfigHolders, parameters.listenerConfigs));
        assertTrue(isEqual(aBoolean, parameters.binary));
        assertTrue(isEqual(anInt, parameters.backupCount));
        assertTrue(isEqual(anInt, parameters.asyncBackupCount));
        assertTrue(isEqual(aBoolean, parameters.statisticsEnabled));
        assertTrue(isEqual(aString, parameters.splitBrainProtectionName));
        assertTrue(isEqual(aString, parameters.mergePolicy));
        assertTrue(isEqual(anInt, parameters.mergeBatchSize));
    }

    @Test
    public void test_DynamicConfigAddMultiMapConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 718;
=======
        int fileClientMessageIndex = 724;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddMultiMapConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddRingbufferConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 719;
=======
        int fileClientMessageIndex = 725;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddRingbufferConfigCodec.RequestParameters parameters = DynamicConfigAddRingbufferConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.capacity));
        assertTrue(isEqual(anInt, parameters.backupCount));
        assertTrue(isEqual(anInt, parameters.asyncBackupCount));
        assertTrue(isEqual(anInt, parameters.timeToLiveSeconds));
        assertTrue(isEqual(aString, parameters.inMemoryFormat));
        assertTrue(isEqual(aRingbufferStoreConfigHolder, parameters.ringbufferStoreConfig));
        assertTrue(isEqual(aString, parameters.splitBrainProtectionName));
        assertTrue(isEqual(aString, parameters.mergePolicy));
        assertTrue(isEqual(anInt, parameters.mergeBatchSize));
    }

    @Test
    public void test_DynamicConfigAddRingbufferConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 720;
=======
        int fileClientMessageIndex = 726;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddRingbufferConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddCardinalityEstimatorConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 721;
=======
        int fileClientMessageIndex = 727;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddCardinalityEstimatorConfigCodec.RequestParameters parameters = DynamicConfigAddCardinalityEstimatorConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.backupCount));
        assertTrue(isEqual(anInt, parameters.asyncBackupCount));
        assertTrue(isEqual(aString, parameters.splitBrainProtectionName));
        assertTrue(isEqual(aString, parameters.mergePolicy));
        assertTrue(isEqual(anInt, parameters.mergeBatchSize));
    }

    @Test
    public void test_DynamicConfigAddCardinalityEstimatorConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 722;
=======
        int fileClientMessageIndex = 728;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddCardinalityEstimatorConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddListConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 723;
=======
        int fileClientMessageIndex = 729;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddListConfigCodec.RequestParameters parameters = DynamicConfigAddListConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfListenerConfigHolders, parameters.listenerConfigs));
        assertTrue(isEqual(anInt, parameters.backupCount));
        assertTrue(isEqual(anInt, parameters.asyncBackupCount));
        assertTrue(isEqual(anInt, parameters.maxSize));
        assertTrue(isEqual(aBoolean, parameters.statisticsEnabled));
        assertTrue(isEqual(aString, parameters.splitBrainProtectionName));
        assertTrue(isEqual(aString, parameters.mergePolicy));
        assertTrue(isEqual(anInt, parameters.mergeBatchSize));
    }

    @Test
    public void test_DynamicConfigAddListConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 724;
=======
        int fileClientMessageIndex = 730;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddListConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddSetConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 725;
=======
        int fileClientMessageIndex = 731;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddSetConfigCodec.RequestParameters parameters = DynamicConfigAddSetConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfListenerConfigHolders, parameters.listenerConfigs));
        assertTrue(isEqual(anInt, parameters.backupCount));
        assertTrue(isEqual(anInt, parameters.asyncBackupCount));
        assertTrue(isEqual(anInt, parameters.maxSize));
        assertTrue(isEqual(aBoolean, parameters.statisticsEnabled));
        assertTrue(isEqual(aString, parameters.splitBrainProtectionName));
        assertTrue(isEqual(aString, parameters.mergePolicy));
        assertTrue(isEqual(anInt, parameters.mergeBatchSize));
    }

    @Test
    public void test_DynamicConfigAddSetConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 726;
=======
        int fileClientMessageIndex = 732;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddSetConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddReplicatedMapConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 727;
=======
        int fileClientMessageIndex = 733;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddReplicatedMapConfigCodec.RequestParameters parameters = DynamicConfigAddReplicatedMapConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aString, parameters.inMemoryFormat));
        assertTrue(isEqual(aBoolean, parameters.asyncFillup));
        assertTrue(isEqual(aBoolean, parameters.statisticsEnabled));
        assertTrue(isEqual(aString, parameters.mergePolicy));
        assertTrue(isEqual(aListOfListenerConfigHolders, parameters.listenerConfigs));
        assertTrue(isEqual(aString, parameters.splitBrainProtectionName));
        assertTrue(isEqual(anInt, parameters.mergeBatchSize));
    }

    @Test
    public void test_DynamicConfigAddReplicatedMapConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 728;
=======
        int fileClientMessageIndex = 734;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddReplicatedMapConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddTopicConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 729;
=======
        int fileClientMessageIndex = 735;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddTopicConfigCodec.RequestParameters parameters = DynamicConfigAddTopicConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aBoolean, parameters.globalOrderingEnabled));
        assertTrue(isEqual(aBoolean, parameters.statisticsEnabled));
        assertTrue(isEqual(aBoolean, parameters.multiThreadingEnabled));
        assertTrue(isEqual(aListOfListenerConfigHolders, parameters.listenerConfigs));
    }

    @Test
    public void test_DynamicConfigAddTopicConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 730;
=======
        int fileClientMessageIndex = 736;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddTopicConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddExecutorConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 731;
=======
        int fileClientMessageIndex = 737;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddExecutorConfigCodec.RequestParameters parameters = DynamicConfigAddExecutorConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.poolSize));
        assertTrue(isEqual(anInt, parameters.queueCapacity));
        assertTrue(isEqual(aBoolean, parameters.statisticsEnabled));
        assertTrue(isEqual(aString, parameters.splitBrainProtectionName));
    }

    @Test
    public void test_DynamicConfigAddExecutorConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 732;
=======
        int fileClientMessageIndex = 738;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddExecutorConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddDurableExecutorConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 733;
=======
        int fileClientMessageIndex = 739;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddDurableExecutorConfigCodec.RequestParameters parameters = DynamicConfigAddDurableExecutorConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.poolSize));
        assertTrue(isEqual(anInt, parameters.durability));
        assertTrue(isEqual(anInt, parameters.capacity));
        assertTrue(isEqual(aString, parameters.splitBrainProtectionName));
        assertTrue(parameters.isStatisticsEnabledExists);
        assertTrue(isEqual(aBoolean, parameters.statisticsEnabled));
    }

    @Test
    public void test_DynamicConfigAddDurableExecutorConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 734;
=======
        int fileClientMessageIndex = 740;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddDurableExecutorConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddScheduledExecutorConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 735;
=======
        int fileClientMessageIndex = 741;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddScheduledExecutorConfigCodec.RequestParameters parameters = DynamicConfigAddScheduledExecutorConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.poolSize));
        assertTrue(isEqual(anInt, parameters.durability));
        assertTrue(isEqual(anInt, parameters.capacity));
        assertTrue(isEqual(aString, parameters.splitBrainProtectionName));
        assertTrue(isEqual(aString, parameters.mergePolicy));
        assertTrue(isEqual(anInt, parameters.mergeBatchSize));
        assertTrue(parameters.isStatisticsEnabledExists);
        assertTrue(isEqual(aBoolean, parameters.statisticsEnabled));
    }

    @Test
    public void test_DynamicConfigAddScheduledExecutorConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 736;
=======
        int fileClientMessageIndex = 742;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddScheduledExecutorConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddQueueConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 737;
=======
        int fileClientMessageIndex = 743;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddQueueConfigCodec.RequestParameters parameters = DynamicConfigAddQueueConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfListenerConfigHolders, parameters.listenerConfigs));
        assertTrue(isEqual(anInt, parameters.backupCount));
        assertTrue(isEqual(anInt, parameters.asyncBackupCount));
        assertTrue(isEqual(anInt, parameters.maxSize));
        assertTrue(isEqual(anInt, parameters.emptyQueueTtl));
        assertTrue(isEqual(aBoolean, parameters.statisticsEnabled));
        assertTrue(isEqual(aString, parameters.splitBrainProtectionName));
        assertTrue(isEqual(aQueueStoreConfigHolder, parameters.queueStoreConfig));
        assertTrue(isEqual(aString, parameters.mergePolicy));
        assertTrue(isEqual(anInt, parameters.mergeBatchSize));
        assertTrue(parameters.isPriorityComparatorClassNameExists);
        assertTrue(isEqual(aString, parameters.priorityComparatorClassName));
    }

    @Test
    public void test_DynamicConfigAddQueueConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 738;
=======
        int fileClientMessageIndex = 744;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddQueueConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddMapConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 739;
=======
        int fileClientMessageIndex = 745;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddMapConfigCodec.RequestParameters parameters = DynamicConfigAddMapConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.backupCount));
        assertTrue(isEqual(anInt, parameters.asyncBackupCount));
        assertTrue(isEqual(anInt, parameters.timeToLiveSeconds));
        assertTrue(isEqual(anInt, parameters.maxIdleSeconds));
        assertTrue(isEqual(anEvictionConfigHolder, parameters.evictionConfig));
        assertTrue(isEqual(aBoolean, parameters.readBackupData));
        assertTrue(isEqual(aString, parameters.cacheDeserializedValues));
        assertTrue(isEqual(aString, parameters.mergePolicy));
        assertTrue(isEqual(anInt, parameters.mergeBatchSize));
        assertTrue(isEqual(aString, parameters.inMemoryFormat));
        assertTrue(isEqual(aListOfListenerConfigHolders, parameters.listenerConfigs));
        assertTrue(isEqual(aListOfListenerConfigHolders, parameters.partitionLostListenerConfigs));
        assertTrue(isEqual(aBoolean, parameters.statisticsEnabled));
        assertTrue(isEqual(aString, parameters.splitBrainProtectionName));
        assertTrue(isEqual(aMapStoreConfigHolder, parameters.mapStoreConfig));
        assertTrue(isEqual(aNearCacheConfigHolder, parameters.nearCacheConfig));
        assertTrue(isEqual(aWanReplicationRef, parameters.wanReplicationRef));
        assertTrue(isEqual(aListOfIndexConfigs, parameters.indexConfigs));
        assertTrue(isEqual(aListOfAttributeConfigs, parameters.attributeConfigs));
        assertTrue(isEqual(aListOfQueryCacheConfigHolders, parameters.queryCacheConfigs));
        assertTrue(isEqual(aString, parameters.partitioningStrategyClassName));
        assertTrue(isEqual(aData, parameters.partitioningStrategyImplementation));
        assertTrue(isEqual(aHotRestartConfig, parameters.hotRestartConfig));
        assertTrue(isEqual(anEventJournalConfig, parameters.eventJournalConfig));
        assertTrue(isEqual(aMerkleTreeConfig, parameters.merkleTreeConfig));
        assertTrue(isEqual(anInt, parameters.metadataPolicy));
        assertTrue(parameters.isPerEntryStatsEnabledExists);
        assertTrue(isEqual(aBoolean, parameters.perEntryStatsEnabled));
    }

    @Test
    public void test_DynamicConfigAddMapConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 740;
=======
        int fileClientMessageIndex = 746;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddMapConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddReliableTopicConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 741;
=======
        int fileClientMessageIndex = 747;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddReliableTopicConfigCodec.RequestParameters parameters = DynamicConfigAddReliableTopicConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfListenerConfigHolders, parameters.listenerConfigs));
        assertTrue(isEqual(anInt, parameters.readBatchSize));
        assertTrue(isEqual(aBoolean, parameters.statisticsEnabled));
        assertTrue(isEqual(aString, parameters.topicOverloadPolicy));
        assertTrue(isEqual(aData, parameters.executor));
    }

    @Test
    public void test_DynamicConfigAddReliableTopicConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 742;
=======
        int fileClientMessageIndex = 748;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddReliableTopicConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddCacheConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 743;
=======
        int fileClientMessageIndex = 749;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddCacheConfigCodec.RequestParameters parameters = DynamicConfigAddCacheConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aString, parameters.keyType));
        assertTrue(isEqual(aString, parameters.valueType));
        assertTrue(isEqual(aBoolean, parameters.statisticsEnabled));
        assertTrue(isEqual(aBoolean, parameters.managementEnabled));
        assertTrue(isEqual(aBoolean, parameters.readThrough));
        assertTrue(isEqual(aBoolean, parameters.writeThrough));
        assertTrue(isEqual(aString, parameters.cacheLoaderFactory));
        assertTrue(isEqual(aString, parameters.cacheWriterFactory));
        assertTrue(isEqual(aString, parameters.cacheLoader));
        assertTrue(isEqual(aString, parameters.cacheWriter));
        assertTrue(isEqual(anInt, parameters.backupCount));
        assertTrue(isEqual(anInt, parameters.asyncBackupCount));
        assertTrue(isEqual(aString, parameters.inMemoryFormat));
        assertTrue(isEqual(aString, parameters.splitBrainProtectionName));
        assertTrue(isEqual(aString, parameters.mergePolicy));
        assertTrue(isEqual(anInt, parameters.mergeBatchSize));
        assertTrue(isEqual(aBoolean, parameters.disablePerEntryInvalidationEvents));
        assertTrue(isEqual(aListOfListenerConfigHolders, parameters.partitionLostListenerConfigs));
        assertTrue(isEqual(aString, parameters.expiryPolicyFactoryClassName));
        assertTrue(isEqual(aTimedExpiryPolicyFactoryConfig, parameters.timedExpiryPolicyFactoryConfig));
        assertTrue(isEqual(aListOfCacheSimpleEntryListenerConfigs, parameters.cacheEntryListeners));
        assertTrue(isEqual(anEvictionConfigHolder, parameters.evictionConfig));
        assertTrue(isEqual(aWanReplicationRef, parameters.wanReplicationRef));
        assertTrue(isEqual(anEventJournalConfig, parameters.eventJournalConfig));
        assertTrue(isEqual(aHotRestartConfig, parameters.hotRestartConfig));
<<<<<<< HEAD
        assertTrue(parameters.isMerkleTreeConfigExists);
        assertTrue(isEqual(aMerkleTreeConfig, parameters.merkleTreeConfig));
=======
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_DynamicConfigAddCacheConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 744;
=======
        int fileClientMessageIndex = 750;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddCacheConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddFlakeIdGeneratorConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 745;
=======
        int fileClientMessageIndex = 751;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddFlakeIdGeneratorConfigCodec.RequestParameters parameters = DynamicConfigAddFlakeIdGeneratorConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.prefetchCount));
        assertTrue(isEqual(aLong, parameters.prefetchValidity));
        assertTrue(isEqual(aBoolean, parameters.statisticsEnabled));
        assertTrue(isEqual(aLong, parameters.nodeIdOffset));
        assertTrue(isEqual(aLong, parameters.epochStart));
        assertTrue(isEqual(anInt, parameters.bitsSequence));
        assertTrue(isEqual(anInt, parameters.bitsNodeId));
        assertTrue(isEqual(aLong, parameters.allowedFutureMillis));
    }

    @Test
    public void test_DynamicConfigAddFlakeIdGeneratorConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 746;
=======
        int fileClientMessageIndex = 752;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddFlakeIdGeneratorConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_DynamicConfigAddPNCounterConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 747;
=======
        int fileClientMessageIndex = 753;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        DynamicConfigAddPNCounterConfigCodec.RequestParameters parameters = DynamicConfigAddPNCounterConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.replicaCount));
        assertTrue(isEqual(aBoolean, parameters.statisticsEnabled));
        assertTrue(isEqual(aString, parameters.splitBrainProtectionName));
    }

    @Test
    public void test_DynamicConfigAddPNCounterConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 748;
=======
        int fileClientMessageIndex = 754;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = DynamicConfigAddPNCounterConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_FlakeIdGeneratorNewIdBatchCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 749;
=======
        int fileClientMessageIndex = 755;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        FlakeIdGeneratorNewIdBatchCodec.RequestParameters parameters = FlakeIdGeneratorNewIdBatchCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(anInt, parameters.batchSize));
    }

    @Test
    public void test_FlakeIdGeneratorNewIdBatchCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 750;
=======
        int fileClientMessageIndex = 756;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = FlakeIdGeneratorNewIdBatchCodec.encodeResponse(aLong, aLong, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_PNCounterGetCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 751;
=======
        int fileClientMessageIndex = 757;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        PNCounterGetCodec.RequestParameters parameters = PNCounterGetCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aListOfUuidToLong, parameters.replicaTimestamps));
        assertTrue(isEqual(aUUID, parameters.targetReplicaUUID));
    }

    @Test
    public void test_PNCounterGetCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 752;
=======
        int fileClientMessageIndex = 758;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = PNCounterGetCodec.encodeResponse(aLong, aListOfUuidToLong, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_PNCounterAddCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 753;
=======
        int fileClientMessageIndex = 759;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        PNCounterAddCodec.RequestParameters parameters = PNCounterAddCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aLong, parameters.delta));
        assertTrue(isEqual(aBoolean, parameters.getBeforeUpdate));
        assertTrue(isEqual(aListOfUuidToLong, parameters.replicaTimestamps));
        assertTrue(isEqual(aUUID, parameters.targetReplicaUUID));
    }

    @Test
    public void test_PNCounterAddCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 754;
=======
        int fileClientMessageIndex = 760;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = PNCounterAddCodec.encodeResponse(aLong, aListOfUuidToLong, anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_PNCounterGetConfiguredReplicaCountCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 755;
=======
        int fileClientMessageIndex = 761;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, PNCounterGetConfiguredReplicaCountCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_PNCounterGetConfiguredReplicaCountCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 756;
=======
        int fileClientMessageIndex = 762;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = PNCounterGetConfiguredReplicaCountCodec.encodeResponse(anInt);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPGroupCreateCPGroupCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 757;
=======
        int fileClientMessageIndex = 763;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, CPGroupCreateCPGroupCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_CPGroupCreateCPGroupCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 758;
=======
        int fileClientMessageIndex = 764;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPGroupCreateCPGroupCodec.encodeResponse(aRaftGroupId);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPGroupDestroyCPObjectCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 759;
=======
        int fileClientMessageIndex = 765;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CPGroupDestroyCPObjectCodec.RequestParameters parameters = CPGroupDestroyCPObjectCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.serviceName));
        assertTrue(isEqual(aString, parameters.objectName));
    }

    @Test
    public void test_CPGroupDestroyCPObjectCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 760;
=======
        int fileClientMessageIndex = 766;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPGroupDestroyCPObjectCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSessionCreateSessionCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 761;
=======
        int fileClientMessageIndex = 767;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CPSessionCreateSessionCodec.RequestParameters parameters = CPSessionCreateSessionCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aString, parameters.endpointName));
    }

    @Test
    public void test_CPSessionCreateSessionCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 762;
=======
        int fileClientMessageIndex = 768;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSessionCreateSessionCodec.encodeResponse(aLong, aLong, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSessionCloseSessionCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 763;
=======
        int fileClientMessageIndex = 769;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CPSessionCloseSessionCodec.RequestParameters parameters = CPSessionCloseSessionCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aLong, parameters.sessionId));
    }

    @Test
    public void test_CPSessionCloseSessionCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 764;
=======
        int fileClientMessageIndex = 770;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSessionCloseSessionCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSessionHeartbeatSessionCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 765;
=======
        int fileClientMessageIndex = 771;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        CPSessionHeartbeatSessionCodec.RequestParameters parameters = CPSessionHeartbeatSessionCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aRaftGroupId, parameters.groupId));
        assertTrue(isEqual(aLong, parameters.sessionId));
    }

    @Test
    public void test_CPSessionHeartbeatSessionCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 766;
=======
        int fileClientMessageIndex = 772;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSessionHeartbeatSessionCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSessionGenerateThreadIdCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 767;
=======
        int fileClientMessageIndex = 773;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aRaftGroupId, CPSessionGenerateThreadIdCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_CPSessionGenerateThreadIdCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 768;
=======
        int fileClientMessageIndex = 774;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSessionGenerateThreadIdCodec.encodeResponse(aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCReadMetricsCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 769;
=======
        int fileClientMessageIndex = 775;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MCReadMetricsCodec.RequestParameters parameters = MCReadMetricsCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aUUID, parameters.uuid));
        assertTrue(isEqual(aLong, parameters.fromSequence));
    }

    @Test
    public void test_MCReadMetricsCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 770;
=======
        int fileClientMessageIndex = 776;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCReadMetricsCodec.encodeResponse(aListOfLongToByteArray, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCChangeClusterStateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 771;
=======
        int fileClientMessageIndex = 777;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anInt, MCChangeClusterStateCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MCChangeClusterStateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 772;
=======
        int fileClientMessageIndex = 778;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCChangeClusterStateCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCGetMapConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 773;
=======
        int fileClientMessageIndex = 779;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MCGetMapConfigCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MCGetMapConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 774;
=======
        int fileClientMessageIndex = 780;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCGetMapConfigCodec.encodeResponse(anInt, anInt, anInt, anInt, anInt, anInt, anInt, aBoolean, anInt, aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCUpdateMapConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 775;
=======
        int fileClientMessageIndex = 781;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MCUpdateMapConfigCodec.RequestParameters parameters = MCUpdateMapConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.mapName));
        assertTrue(isEqual(anInt, parameters.timeToLiveSeconds));
        assertTrue(isEqual(anInt, parameters.maxIdleSeconds));
        assertTrue(isEqual(anInt, parameters.evictionPolicy));
        assertTrue(isEqual(aBoolean, parameters.readBackupData));
        assertTrue(isEqual(anInt, parameters.maxSize));
        assertTrue(isEqual(anInt, parameters.maxSizePolicy));
    }

    @Test
    public void test_MCUpdateMapConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 776;
=======
        int fileClientMessageIndex = 782;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCUpdateMapConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCGetMemberConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 777;
=======
        int fileClientMessageIndex = 783;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCGetMemberConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 778;
=======
        int fileClientMessageIndex = 784;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCGetMemberConfigCodec.encodeResponse(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCRunGcCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 779;
=======
        int fileClientMessageIndex = 785;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCRunGcCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 780;
=======
        int fileClientMessageIndex = 786;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCRunGcCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCGetThreadDumpCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 781;
=======
        int fileClientMessageIndex = 787;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, MCGetThreadDumpCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MCGetThreadDumpCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 782;
=======
        int fileClientMessageIndex = 788;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCGetThreadDumpCodec.encodeResponse(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCShutdownMemberCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 783;
=======
        int fileClientMessageIndex = 789;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCShutdownMemberCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 784;
=======
        int fileClientMessageIndex = 790;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCShutdownMemberCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCPromoteLiteMemberCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 785;
=======
        int fileClientMessageIndex = 791;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCPromoteLiteMemberCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 786;
=======
        int fileClientMessageIndex = 792;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCPromoteLiteMemberCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCGetSystemPropertiesCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 787;
=======
        int fileClientMessageIndex = 793;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCGetSystemPropertiesCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 788;
=======
        int fileClientMessageIndex = 794;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCGetSystemPropertiesCodec.encodeResponse(aListOfStringToString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCGetTimedMemberStateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 789;
=======
        int fileClientMessageIndex = 795;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCGetTimedMemberStateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 790;
=======
        int fileClientMessageIndex = 796;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCGetTimedMemberStateCodec.encodeResponse(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCMatchMCConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 791;
=======
        int fileClientMessageIndex = 797;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aString, MCMatchMCConfigCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MCMatchMCConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 792;
=======
        int fileClientMessageIndex = 798;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCMatchMCConfigCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCApplyMCConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 793;
=======
        int fileClientMessageIndex = 799;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MCApplyMCConfigCodec.RequestParameters parameters = MCApplyMCConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.eTag));
        assertTrue(isEqual(anInt, parameters.clientBwListMode));
        assertTrue(isEqual(aListOfClientBwListEntries, parameters.clientBwListEntries));
    }

    @Test
    public void test_MCApplyMCConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 794;
=======
        int fileClientMessageIndex = 800;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCApplyMCConfigCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCGetClusterMetadataCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 795;
=======
        int fileClientMessageIndex = 801;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCGetClusterMetadataCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 796;
=======
        int fileClientMessageIndex = 802;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCGetClusterMetadataCodec.encodeResponse(aByte, aString, aString, aLong);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCShutdownClusterCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 797;
=======
        int fileClientMessageIndex = 803;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCShutdownClusterCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 798;
=======
        int fileClientMessageIndex = 804;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCShutdownClusterCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCChangeClusterVersionCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 799;
=======
        int fileClientMessageIndex = 805;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MCChangeClusterVersionCodec.RequestParameters parameters = MCChangeClusterVersionCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aByte, parameters.majorVersion));
        assertTrue(isEqual(aByte, parameters.minorVersion));
    }

    @Test
    public void test_MCChangeClusterVersionCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 800;
=======
        int fileClientMessageIndex = 806;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCChangeClusterVersionCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCRunScriptCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 801;
=======
        int fileClientMessageIndex = 807;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MCRunScriptCodec.RequestParameters parameters = MCRunScriptCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.engine));
        assertTrue(isEqual(aString, parameters.script));
    }

    @Test
    public void test_MCRunScriptCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 802;
=======
        int fileClientMessageIndex = 808;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCRunScriptCodec.encodeResponse(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCRunConsoleCommandCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 803;
=======
        int fileClientMessageIndex = 809;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MCRunConsoleCommandCodec.RequestParameters parameters = MCRunConsoleCommandCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.namespace));
        assertTrue(isEqual(aString, parameters.command));
    }

    @Test
    public void test_MCRunConsoleCommandCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 804;
=======
        int fileClientMessageIndex = 810;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCRunConsoleCommandCodec.encodeResponse(aString);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCChangeWanReplicationStateCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 805;
=======
        int fileClientMessageIndex = 811;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MCChangeWanReplicationStateCodec.RequestParameters parameters = MCChangeWanReplicationStateCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.wanReplicationName));
        assertTrue(isEqual(aString, parameters.wanPublisherId));
        assertTrue(isEqual(aByte, parameters.newState));
    }

    @Test
    public void test_MCChangeWanReplicationStateCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 806;
=======
        int fileClientMessageIndex = 812;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCChangeWanReplicationStateCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCClearWanQueuesCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 807;
=======
        int fileClientMessageIndex = 813;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MCClearWanQueuesCodec.RequestParameters parameters = MCClearWanQueuesCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.wanReplicationName));
        assertTrue(isEqual(aString, parameters.wanPublisherId));
    }

    @Test
    public void test_MCClearWanQueuesCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 808;
=======
        int fileClientMessageIndex = 814;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCClearWanQueuesCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCAddWanBatchPublisherConfigCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 809;
=======
        int fileClientMessageIndex = 815;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MCAddWanBatchPublisherConfigCodec.RequestParameters parameters = MCAddWanBatchPublisherConfigCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.name));
        assertTrue(isEqual(aString, parameters.targetCluster));
        assertTrue(isEqual(aString, parameters.publisherId));
        assertTrue(isEqual(aString, parameters.endpoints));
        assertTrue(isEqual(anInt, parameters.queueCapacity));
        assertTrue(isEqual(anInt, parameters.batchSize));
        assertTrue(isEqual(anInt, parameters.batchMaxDelayMillis));
        assertTrue(isEqual(anInt, parameters.responseTimeoutMillis));
        assertTrue(isEqual(anInt, parameters.ackType));
        assertTrue(isEqual(anInt, parameters.queueFullBehavior));
    }

    @Test
    public void test_MCAddWanBatchPublisherConfigCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 810;
=======
        int fileClientMessageIndex = 816;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCAddWanBatchPublisherConfigCodec.encodeResponse(aListOfStrings, aListOfStrings);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCWanSyncMapCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 811;
=======
        int fileClientMessageIndex = 817;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MCWanSyncMapCodec.RequestParameters parameters = MCWanSyncMapCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.wanReplicationName));
        assertTrue(isEqual(aString, parameters.wanPublisherId));
        assertTrue(isEqual(anInt, parameters.wanSyncType));
        assertTrue(isEqual(aString, parameters.mapName));
    }

    @Test
    public void test_MCWanSyncMapCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 812;
=======
        int fileClientMessageIndex = 818;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCWanSyncMapCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCCheckWanConsistencyCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 813;
=======
        int fileClientMessageIndex = 819;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        MCCheckWanConsistencyCodec.RequestParameters parameters = MCCheckWanConsistencyCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.wanReplicationName));
        assertTrue(isEqual(aString, parameters.wanPublisherId));
        assertTrue(isEqual(aString, parameters.mapName));
    }

    @Test
    public void test_MCCheckWanConsistencyCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 814;
=======
        int fileClientMessageIndex = 820;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCCheckWanConsistencyCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCPollMCEventsCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 815;
=======
        int fileClientMessageIndex = 821;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCPollMCEventsCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 816;
=======
        int fileClientMessageIndex = 822;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCPollMCEventsCodec.encodeResponse(aListOfMCEvents);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCGetCPMembersCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 817;
=======
        int fileClientMessageIndex = 823;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCGetCPMembersCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 818;
=======
        int fileClientMessageIndex = 824;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCGetCPMembersCodec.encodeResponse(aListOfUUIDToUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCPromoteToCPMemberCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 819;
=======
        int fileClientMessageIndex = 825;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCPromoteToCPMemberCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 820;
=======
        int fileClientMessageIndex = 826;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCPromoteToCPMemberCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCRemoveCPMemberCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 821;
=======
        int fileClientMessageIndex = 827;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, MCRemoveCPMemberCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_MCRemoveCPMemberCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 822;
=======
        int fileClientMessageIndex = 828;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCRemoveCPMemberCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCResetCPSubsystemCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 823;
=======
        int fileClientMessageIndex = 829;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCResetCPSubsystemCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 824;
=======
        int fileClientMessageIndex = 830;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCResetCPSubsystemCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCTriggerPartialStartCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 825;
=======
        int fileClientMessageIndex = 831;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCTriggerPartialStartCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 826;
=======
        int fileClientMessageIndex = 832;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCTriggerPartialStartCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCTriggerForceStartCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 827;
=======
        int fileClientMessageIndex = 833;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCTriggerForceStartCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 828;
=======
        int fileClientMessageIndex = 834;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCTriggerForceStartCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCTriggerHotRestartBackupCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 829;
=======
        int fileClientMessageIndex = 835;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCTriggerHotRestartBackupCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 830;
=======
        int fileClientMessageIndex = 836;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCTriggerHotRestartBackupCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_MCInterruptHotRestartBackupCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 831;
=======
        int fileClientMessageIndex = 837;
>>>>>>> 94a59c88de (Serialization 2.0)
    }

    @Test
    public void test_MCInterruptHotRestartBackupCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 832;
=======
        int fileClientMessageIndex = 838;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = MCInterruptHotRestartBackupCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SqlExecute_reservedCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 833;
=======
        int fileClientMessageIndex = 839;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SqlExecute_reservedCodec.RequestParameters parameters = SqlExecute_reservedCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.sql));
        assertTrue(isEqual(aListOfData, parameters.parameters));
        assertTrue(isEqual(aLong, parameters.timeoutMillis));
        assertTrue(isEqual(anInt, parameters.cursorBufferSize));
    }

    @Test
    public void test_SqlExecute_reservedCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 834;
=======
        int fileClientMessageIndex = 840;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SqlExecute_reservedCodec.encodeResponse(anSqlQueryId, aListOfSqlColumnMetadata, aListOfListOfData, aBoolean, aLong, anSqlError);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SqlFetch_reservedCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 835;
=======
        int fileClientMessageIndex = 841;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SqlFetch_reservedCodec.RequestParameters parameters = SqlFetch_reservedCodec.decodeRequest(fromFile);
        assertTrue(isEqual(anSqlQueryId, parameters.queryId));
        assertTrue(isEqual(anInt, parameters.cursorBufferSize));
    }

    @Test
    public void test_SqlFetch_reservedCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 836;
=======
        int fileClientMessageIndex = 842;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SqlFetch_reservedCodec.encodeResponse(aListOfListOfData, aBoolean, anSqlError);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SqlCloseCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 837;
=======
        int fileClientMessageIndex = 843;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(anSqlQueryId, SqlCloseCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_SqlCloseCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 838;
=======
        int fileClientMessageIndex = 844;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SqlCloseCodec.encodeResponse();
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SqlExecuteCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 839;
=======
        int fileClientMessageIndex = 845;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SqlExecuteCodec.RequestParameters parameters = SqlExecuteCodec.decodeRequest(fromFile);
        assertTrue(isEqual(aString, parameters.sql));
        assertTrue(isEqual(aListOfData, parameters.parameters));
        assertTrue(isEqual(aLong, parameters.timeoutMillis));
        assertTrue(isEqual(anInt, parameters.cursorBufferSize));
        assertTrue(isEqual(aString, parameters.schema));
        assertTrue(isEqual(aByte, parameters.expectedResultType));
        assertTrue(isEqual(anSqlQueryId, parameters.queryId));
    }

    @Test
    public void test_SqlExecuteCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 840;
=======
        int fileClientMessageIndex = 846;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SqlExecuteCodec.encodeResponse(aListOfSqlColumnMetadata, aSqlPage, aLong, anSqlError);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_SqlFetchCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 841;
=======
        int fileClientMessageIndex = 847;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        SqlFetchCodec.RequestParameters parameters = SqlFetchCodec.decodeRequest(fromFile);
        assertTrue(isEqual(anSqlQueryId, parameters.queryId));
        assertTrue(isEqual(anInt, parameters.cursorBufferSize));
    }

    @Test
    public void test_SqlFetchCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 842;
=======
        int fileClientMessageIndex = 848;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = SqlFetchCodec.encodeResponse(aSqlPage, anSqlError);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSubsystemAddMembershipListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 843;
=======
        int fileClientMessageIndex = 849;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, CPSubsystemAddMembershipListenerCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_CPSubsystemAddMembershipListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 844;
=======
        int fileClientMessageIndex = 850;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSubsystemAddMembershipListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSubsystemAddMembershipListenerCodec_encodeMembershipEventEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 845;
=======
        int fileClientMessageIndex = 851;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = CPSubsystemAddMembershipListenerCodec.encodeMembershipEventEvent(aCpMember, aByte);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSubsystemRemoveMembershipListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 846;
=======
        int fileClientMessageIndex = 852;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, CPSubsystemRemoveMembershipListenerCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_CPSubsystemRemoveMembershipListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 847;
=======
        int fileClientMessageIndex = 853;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSubsystemRemoveMembershipListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSubsystemAddGroupAvailabilityListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 848;
=======
        int fileClientMessageIndex = 854;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aBoolean, CPSubsystemAddGroupAvailabilityListenerCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_CPSubsystemAddGroupAvailabilityListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 849;
=======
        int fileClientMessageIndex = 855;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSubsystemAddGroupAvailabilityListenerCodec.encodeResponse(aUUID);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSubsystemAddGroupAvailabilityListenerCodec_encodeGroupAvailabilityEventEvent() {
<<<<<<< HEAD
        int fileClientMessageIndex = 850;
=======
        int fileClientMessageIndex = 856;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        ClientMessage encoded = CPSubsystemAddGroupAvailabilityListenerCodec.encodeGroupAvailabilityEventEvent(aRaftGroupId, aListOfCpMembers, aListOfCpMembers);
        compareClientMessages(fromFile, encoded);
    }

    @Test
    public void test_CPSubsystemRemoveGroupAvailabilityListenerCodec_decodeRequest() {
<<<<<<< HEAD
        int fileClientMessageIndex = 851;
=======
        int fileClientMessageIndex = 857;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        assertTrue(isEqual(aUUID, CPSubsystemRemoveGroupAvailabilityListenerCodec.decodeRequest(fromFile)));
    }

    @Test
    public void test_CPSubsystemRemoveGroupAvailabilityListenerCodec_encodeResponse() {
<<<<<<< HEAD
        int fileClientMessageIndex = 852;
=======
        int fileClientMessageIndex = 858;
>>>>>>> 94a59c88de (Serialization 2.0)
        ClientMessage encoded = CPSubsystemRemoveGroupAvailabilityListenerCodec.encodeResponse(aBoolean);
        ClientMessage fromFile = clientMessages.get(fileClientMessageIndex);
        compareClientMessages(fromFile, encoded);
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