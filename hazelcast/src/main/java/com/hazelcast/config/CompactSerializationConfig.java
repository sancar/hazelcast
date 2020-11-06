package com.hazelcast.config;

import com.hazelcast.nio.serialization.compact.CompactSerializer;

public class CompactSerializationConfig {
    /**
     * Register class to be serialized via compact serializer.
     * Overrides Portable,Identified,Java Serializable or GlobalSerializer
     * <p>
     * class name is determined automatically from clazz. It is full path including package by default
     * fields are determined automatically from class via reflection
     *
     * @param clazz Class to be serialized via compact serializer
     */
    public <T> void register(Class<T> clazz) {
    }

    /**
     * Register class to be serialized via compact serializer.
     * Overrides Portable,Identified,Java Serializable or GlobalSerializer
     * <p>
     * fields are determined automatically from class via reflection
     *
     * @param clazz Class to be serialized via compact serializer
     */
    public <T> void register(Class<T> clazz, String aliasClassName) {
    }

    /**
     * Register class to be serialized via compact serializer.
     * Overrides Portable,Identified,Java Serializable or GlobalSerializer
     *
     * @param clazz Class to be serialized via compact serializer
     */
    public <T> void register(Class<T> clazz, String aliasClassName, CompactSerializer<T> explicitSerializer) {
    }

    /**
     * Register class to be serialized via compact serializer.
     * Overrides Portable,Identified,Java Serializable or GlobalSerializer
     * class name is determined automatically from clazz. It is full path including package by default
     *
     * @param clazz Class to be serialized via compact serializer
     */
    public <T> void register(Class<T> clazz, CompactSerializer<T> explicitSerializer) {
    }
}
