/*
 * Copyright (c) 2008-2020, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License 2.0 (the "License");
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

import com.hazelcast.internal.util.EmptyStatement;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SchemaBuilder {

    private static final long EMPTY = 0xc15d213aa4d7a795L;
    private static final long[] FP_TABLE = new long[256];

    static {
        for (int i = 0; i < 256; i++) {
            long fp = i;
            for (int j = 0; j < 8; j++) {
                fp = (fp >>> 1) ^ (EMPTY & -(fp & 1L));
            }
            FP_TABLE[i] = fp;
        }
    }

    private final TreeMap<String, FieldDescriptor> fieldDefinitionMap = new TreeMap<>(Comparator.naturalOrder());
    private final String className;

    protected SchemaBuilder(String className) {
        this.className = className;
    }

//    public SchemaBuilder addIntField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.INT));
//        return this;
//    }
//
//    public SchemaBuilder addLongField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.LONG));
//        return this;
//    }
//
//    public SchemaBuilder addUTFField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.UTF));
//        return this;
//    }
//
//    public SchemaBuilder addBooleanField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.BOOLEAN));
//        return this;
//    }
//
//    public SchemaBuilder addByteField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.BYTE));
//        return this;
//    }
//
//    public SchemaBuilder addBooleanArrayField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.BOOLEAN_ARRAY));
//        return this;
//    }
//
//    public SchemaBuilder addCharField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.CHAR));
//        return this;
//    }
//
//    public SchemaBuilder addDoubleField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.DOUBLE));
//        return this;
//    }
//
//    public SchemaBuilder addFloatField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.FLOAT));
//        return this;
//    }
//
//    public SchemaBuilder addShortField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.SHORT));
//        return this;
//    }
//
//    public SchemaBuilder addByteArrayField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.BYTE_ARRAY));
//        return this;
//    }
//
//    public SchemaBuilder addCharArrayField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.CHAR_ARRAY));
//        return this;
//    }
//
//    public SchemaBuilder addIntArrayField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.INT_ARRAY));
//        return this;
//    }
//
//    public SchemaBuilder addLongArrayField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.LONG_ARRAY));
//        return this;
//    }
//
//    public SchemaBuilder addDoubleArrayField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.DOUBLE_ARRAY));
//        return this;
//    }
//
//    public SchemaBuilder addFloatArrayField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.FLOAT_ARRAY));
//        return this;
//    }
//
//    public SchemaBuilder addShortArrayField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.SHORT_ARRAY));
//        return this;
//    }
//
//    public SchemaBuilder addUTFArrayField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.UTF_ARRAY));
//        return this;
//    }
//
//    public SchemaBuilder addObjectField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.PORTABLE));
//        return this;
//    }
//
//    public SchemaBuilder addObjectArrayField(String fieldName) {
//        addField(new FieldDescriptorImpl(fieldName, FieldType.PORTABLE_ARRAY));
//        return this;
//    }


    private static long fingerprint64(byte[] buf) {
        long fp = EMPTY;
        for (byte b : buf) {
            fp = (fp >>> 8) ^ FP_TABLE[(int) (fp ^ b) & 0xff];
        }
        return fp;
    }

    private static byte[] toBytes(String className, Map<String, FieldDescriptor> fieldDefinitionMap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream objectOutputStream = new DataOutputStream(out);

        try {
            writeBasicString(objectOutputStream, className);
            Collection<FieldDescriptor> fields = fieldDefinitionMap.values();
            objectOutputStream.writeInt(fields.size());
            for (FieldDescriptor field : fields) {
                writeBasicString(objectOutputStream, field.getName());
                out.write(field.getType().getId());
            }
        } catch (IOException e) {
            // ByteArrayOutputStream guarantees that underlying stream will grow without throwing exception.
            EmptyStatement.ignore(e);
        }
        return out.toByteArray();
    }

    private static void writeBasicString(DataOutputStream out, String value) throws IOException {
        byte[] bytes = value.getBytes();
        out.writeInt(bytes.length);
        out.write(bytes);
    }

    public Schema build() {
        List<FieldDescriptor> definiteSizedList = fieldDefinitionMap.values().stream()
                .filter(fieldDescriptor -> fieldDescriptor.getType().hasDefiniteSize())
                .sorted(Comparator.comparingInt(o -> o.getType().getTypeSize())).collect(Collectors.toList());
        int offset = 0;
        for (FieldDescriptor value : definiteSizedList) {
            FieldDescriptorImpl fieldDefinition = (FieldDescriptorImpl) value;
            fieldDefinition.setOffset(offset);
            offset += fieldDefinition.getType().getTypeSize();
        }

        int index = 0;
        List<FieldDescriptor> varSizeList = fieldDefinitionMap.values().stream()
                .filter(fieldDescriptor -> !fieldDescriptor.getType().hasDefiniteSize())
                .collect(Collectors.toList());

        for (FieldDescriptor value : varSizeList) {
            FieldDescriptorImpl fieldDefinition = (FieldDescriptorImpl) value;
            fieldDefinition.setIndex(index++);
        }
        byte[] serializedSchema = toBytes(className, fieldDefinitionMap);
        long schemaId = fingerprint64(serializedSchema);
        return new SchemaImpl(className, fieldDefinitionMap, schemaId, serializedSchema, index, offset);
    }

    protected void addField(FieldDescriptor fieldDefinition) {
        fieldDefinitionMap.put(fieldDefinition.getName(), fieldDefinition);
    }
}
