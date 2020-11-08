package com.hazelcast.internal.serialization.impl.compact;

import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;

import java.io.IOException;

public interface InternalCompactSerializer<T, Reader extends CompactReader> {

    /**
     * @param in     reader to read fields of an object
     * @return the object created as a result of read method
     * @throws IOException
     */
    T read(Reader in) throws IOException;

    /**
     * @param out    CompactWriter to serialize the fields onto
     * @param object to be serialized
     * @throws IOException
     */
    void write(CompactWriter out, T object) throws IOException;
}
