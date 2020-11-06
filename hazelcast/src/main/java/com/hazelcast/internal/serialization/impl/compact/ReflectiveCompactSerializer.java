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

import com.hazelcast.function.BiConsumerEx;
import com.hazelcast.internal.memory.impl.UnsafeUtil;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hazelcast.nio.serialization.compact.Schema;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

public class ReflectiveCompactSerializer {

    Unsafe unsafe = UnsafeUtil.UNSAFE;

    private final Map<Class, BiConsumerEx<CompactWriter, Object>[]> writersCache = new ConcurrentHashMap<>();
    private final Map<Class, BiConsumerEx<CompactReader, Object>[]> readersCache = new ConcurrentHashMap<>();

    public ReflectiveCompactSerializer() {
    }

    public void write(CompactWriter writer, Object object) throws IOException {
        Class<?> clazz = object.getClass();
        if (writeFast(clazz, writer, object)) {
            return;
        }
        createFastReadWriteCaches(clazz);
        writeFast(clazz, writer, object);

    }

    private boolean writeFast(Class clazz, CompactWriter writer, Object object) {
        BiConsumerEx<CompactWriter, Object>[] consumers = writersCache.get(clazz);
        if (consumers != null) {
            for (BiConsumerEx<CompactWriter, Object> consumer : consumers) {
                consumer.accept(writer, object);
            }
            return true;
        }
        return false;
    }

    private boolean readFast(Class clazz, CompactReader reader, Object object) {
        BiConsumerEx<CompactReader, Object>[] consumers = readersCache.get(clazz);
        if (consumers != null) {
            for (BiConsumerEx<CompactReader, Object> consumer : consumers) {
                consumer.accept(reader, object);
            }
            return true;
        }
        return false;
    }

    public Object read(Class associatedClass, Schema schema, CompactReader reader) throws IOException {
        try {
            Object object;
            Constructor constructor = associatedClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            object = constructor.newInstance();
            if (readFast(associatedClass, reader, object)) {
                return object;
            }
            createFastReadWriteCaches(associatedClass);
            readFast(associatedClass, reader, object);
            return object;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.stream(type.getDeclaredFields())
                .filter(f -> !Modifier.isStatic(f.getModifiers()))
                .filter(f -> !Modifier.isTransient(f.getModifiers()))
                .collect(toList()));
        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }
        return fields;
    }

    private void createFastReadWriteCaches(Class clazz) {
        //get inherited fields as well
        List<Field> allFields = getAllFields(new LinkedList<>(), clazz);
        BiConsumerEx<CompactWriter, Object>[] writers = new BiConsumerEx[allFields.size()];
        BiConsumerEx<CompactReader, Object>[] readers = new BiConsumerEx[allFields.size()];

        int index = 0;
        //because getDeclaredFields does not guarantee that it return in same order each time it is called.
        allFields.sort(Comparator.comparing(Field::getName));
        for (Field field : allFields) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            String name = field.getName();
            if (Byte.TYPE.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.setByte(o, reader.readByte(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeByte(name, field.getByte(o));
            } else if (Short.TYPE.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.setShort(o, reader.readShort(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeShort(name, field.getShort(o));
            } else if (Integer.TYPE.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.setInt(o, reader.readInt(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeInt(name, field.getInt(o));
            } else if (Long.TYPE.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readLong(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeLong(name, field.getLong(o));
            } else if (Float.TYPE.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.setFloat(o, reader.readFloat(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeFloat(name, field.getFloat(o));
            } else if (Double.TYPE.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.setDouble(o, reader.readDouble(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeDouble(name, field.getDouble(o));
            } else if (Boolean.TYPE.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.setBoolean(o, reader.readBoolean(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeBoolean(name, field.getBoolean(o));
            } else if (Character.TYPE.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.setChar(o, reader.readChar(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeChar(name, field.getChar(o));
            } else if (String.class.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readUTF(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeUTF(name, (String) field.get(o));
            } else if (Byte.class.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readByte(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeByte(name, (byte) field.get(o));
            } else if (Short.class.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readShort(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeShort(name, (short) field.get(o));
            } else if (Integer.class.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readInt(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeInt(name, (int) field.get(o));
            } else if (Long.class.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readLong(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeLong(name, (long) field.get(o));
            } else if (Double.class.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readDouble(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeDouble(name, (double) field.get(o));
            } else if (Boolean.class.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readBoolean(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeBoolean(name, (boolean) field.get(o));
            } else if (Character.class.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readChar(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeChar(name, (char) field.get(o));
            } else if (BigInteger.class.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readBigInteger(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeBigInteger(name, (BigInteger) field.get(o));
            } else if (BigDecimal.class.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readBigDecimal(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeBigDecimal(name, (BigDecimal) field.get(o));
            } else if (LocalTime.class.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readLocalTime(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeLocalTime(name, (LocalTime) field.get(o));
            } else if (LocalDate.class.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readLocalDate(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeLocalDate(name, (LocalDate) field.get(o));
            } else if (LocalDateTime.class.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readLocalDateTime(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeLocalDateTime(name, (LocalDateTime) field.get(o));
            } else if (OffsetDateTime.class.equals(type)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readOffsetDateTime(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeOffsetDateTime(name, (OffsetDateTime) field.get(o));
            } else if (type.isArray()) {
                Class<?> componentType = type.getComponentType();
                if (Byte.TYPE.equals(componentType)) {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readByteArray(name));
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeByteArray(name, (byte[]) field.get(o));
                } else if (Short.TYPE.equals(componentType)) {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readShortArray(name));
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeShortArray(name, (short[]) field.get(o));
                } else if (Integer.TYPE.equals(componentType)) {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readIntArray(name));
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeIntArray(name, (int[]) field.get(o));
                } else if (Long.TYPE.equals(componentType)) {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readLongArray(name));
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeLongArray(name, (long[]) field.get(o));
                } else if (Float.TYPE.equals(componentType)) {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readFloatArray(name));
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeFloatArray(name, (float[]) field.get(o));
                } else if (Double.TYPE.equals(componentType)) {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readDoubleArray(name));
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeDoubleArray(name, (double[]) field.get(o));
                } else if (Boolean.TYPE.equals(componentType)) {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readBooleanArray(name));
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeBooleanArray(name, (boolean[]) field.get(o));
                } else if (Character.TYPE.equals(componentType)) {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readCharArray(name));
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeCharArray(name, (char[]) field.get(o));
                } else if (String.class.equals(componentType)) {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readUTFArray(name));
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeUTFArray(name, (String[]) field.get(o));
                } else if (BigInteger.class.equals(componentType)) {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readBigIntegerArray(name));
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeBigIntegerArray(name, (BigInteger[]) field.get(o));
                } else if (BigDecimal.class.equals(componentType)) {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readBigDecimalArray(name));
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeBigDecimalArray(name, (BigDecimal[]) field.get(o));
                } else if (LocalTime.class.equals(componentType)) {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readLocalTimeArray(name));
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeLocalTimeArray(name, (LocalTime[]) field.get(o));
                } else if (LocalDate.class.equals(componentType)) {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readLocalDateArray(name));
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeLocalDateArray(name, (LocalDate[]) field.get(o));
                } else if (LocalDateTime.class.equals(componentType)) {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readLocalDateTimeArray(name));
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeLocalDateTimeArray(name, (LocalDateTime[]) field.get(o));
                } else if (OffsetDateTime.class.equals(componentType)) {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readOffsetDateTimeArray(name));
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeOffsetDateTimeArray(name, (OffsetDateTime[]) field.get(o));
                } else {
                    readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> {
                        Object[] objects = reader.readObjectArray(name);
                        long offset = unsafe.objectFieldOffset(field);
                        unsafe.putObject(o, offset, objects);
//                    field.set(o, objects); Can not set Object[] to Employee[]
                    };
                    writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeObjectArray(name, (Object[]) field.get(o));
                }
            } else if (type.equals(ArrayList.class)) {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readObjectArrayList(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeObjectArrayList(name, (ArrayList<Object>) field.get(o));
            } else {
                readers[index] = (BiConsumerEx<CompactReader, Object>) (reader, o) -> field.set(o, reader.readObject(name));
                writers[index++] = (BiConsumerEx<CompactWriter, Object>) (w, o) -> w.writeObject(name, field.get(o));
            }
        }

        writersCache.put(clazz, writers);
        readersCache.put(clazz, readers);
    }
}