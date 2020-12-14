package com.hazelcast.internal.serialization.impl;

/**
 * Interface for abstracting indexed read methods to be used by {@link GenericRecordQueryReader}
 */
public interface IndexedReader {

    Object readIndexed(InternalGenericRecord record, String fieldName, int index);
}
