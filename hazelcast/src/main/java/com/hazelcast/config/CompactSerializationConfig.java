package com.hazelcast.config;

import com.hazelcast.internal.util.TriTuple;
import com.hazelcast.nio.serialization.compact.CompactSerializer;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.hazelcast.internal.util.Preconditions.checkNotNull;

public class CompactSerializationConfig {

    private final Map<String, TriTuple<Class, String, CompactSerializer>> classNameToRegistryMap;
    private final Map<Class, TriTuple<Class, String, CompactSerializer>> classToRegistryMap;

    public CompactSerializationConfig(CompactSerializationConfig compactSerializationConfig) {
        this.classNameToRegistryMap = compactSerializationConfig.classNameToRegistryMap;
        this.classToRegistryMap = compactSerializationConfig.classToRegistryMap;
    }

    public CompactSerializationConfig() {
        this.classNameToRegistryMap = new ConcurrentHashMap<>();
        this.classToRegistryMap = new ConcurrentHashMap<>();
    }


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
        TriTuple<Class, String, CompactSerializer> registry = TriTuple.of(clazz, clazz.getName(), null);
        TriTuple<Class, String, CompactSerializer> oldRegistry = classNameToRegistryMap.putIfAbsent(clazz.getName(), registry);
        if (oldRegistry != null) {
            throw new InvalidConfigurationException("Already have a registry for class name " + clazz.getName());
        }
        oldRegistry = classToRegistryMap.putIfAbsent(clazz, registry);
        if (oldRegistry != null) {
            throw new InvalidConfigurationException("Already have a registry for class " + clazz);
        }
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
        checkNotNull(aliasClassName, "aliasClassName");
        TriTuple<Class, String, CompactSerializer> registry = TriTuple.of(clazz, aliasClassName, null);
        TriTuple<Class, String, CompactSerializer> oldRegistry = classNameToRegistryMap.putIfAbsent(aliasClassName, registry);
        if (oldRegistry != null) {
            throw new InvalidConfigurationException("Already have a registry for class name " + clazz.getName());
        }
        oldRegistry = classToRegistryMap.putIfAbsent(clazz, registry);
        if (oldRegistry != null) {
            throw new InvalidConfigurationException("Already have a registry for class " + clazz);
        }
    }

    /**
     * Register class to be serialized via compact serializer.
     * Overrides Portable,Identified,Java Serializable or GlobalSerializer
     *
     * @param clazz Class to be serialized via compact serializer
     */
    public <T> void register(Class<T> clazz, String aliasClassName, CompactSerializer<T> explicitSerializer) {
        checkNotNull(aliasClassName, "aliasClassName");
        checkNotNull(explicitSerializer, "explicitSerializer");
        TriTuple<Class, String, CompactSerializer> registry = TriTuple.of(clazz, aliasClassName, explicitSerializer);
        TriTuple<Class, String, CompactSerializer> oldRegistry = classNameToRegistryMap.putIfAbsent(aliasClassName, registry);
        if (oldRegistry != null) {
            throw new InvalidConfigurationException("Already have a registry for class name " + aliasClassName);
        }
        oldRegistry = classToRegistryMap.putIfAbsent(clazz, registry);
        if (oldRegistry != null) {
            throw new InvalidConfigurationException("Already have a registry for class " + clazz);
        }
    }

    /**
     * Register class to be serialized via compact serializer.
     * Overrides Portable,Identified,Java Serializable or GlobalSerializer
     * class name is determined automatically from clazz. It is full path including package by default
     *
     * @param clazz Class to be serialized via compact serializer
     */
    public <T> void register(Class<T> clazz, CompactSerializer<T> explicitSerializer) {
        checkNotNull(explicitSerializer, "explicitSerializer");
        TriTuple<Class, String, CompactSerializer> registry = TriTuple.of(clazz, clazz.getName(), explicitSerializer);
        TriTuple<Class, String, CompactSerializer> oldRegistry = classNameToRegistryMap.putIfAbsent(clazz.getName(), registry);
        if (oldRegistry != null) {
            throw new InvalidConfigurationException("Already have a registry for class name " + clazz.getName());
        }
        oldRegistry = classToRegistryMap.putIfAbsent(clazz, registry);
        if (oldRegistry != null) {
            throw new InvalidConfigurationException("Already have a registry for class " + clazz);
        }
    }

    public Map<String, TriTuple<Class, String, CompactSerializer>> getRegistries() {
        return Collections.unmodifiableMap(classNameToRegistryMap);
    }

}
