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

import com.hazelcast.config.CompactSerializationConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.internal.serialization.Data;
import com.hazelcast.internal.serialization.SerializationService;
import com.hazelcast.internal.serialization.impl.DefaultSerializationServiceBuilder;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hazelcast.test.HazelcastParallelClassRunner;
import com.hazelcast.test.annotation.ParallelJVMTest;
import com.hazelcast.test.annotation.QuickTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.hazelcast.nio.serialization.GenericRecord.Builder.compact;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(HazelcastParallelClassRunner.class)
@Category({QuickTest.class, ParallelJVMTest.class})
public class CompactTest {

    { //TODO sancar cleanup
        System.setProperty("com.hazelcast.serialization.compact.debug", "true");
    }

    MetaDataService metaDataService = new MetaDataService() {
        private final Map<Object, byte[]> map = new ConcurrentHashMap<>();

        @Override
        public byte[] get(Object key) {
            return map.get(key);
        }

        @Override
        public Object put(Object key, byte[] metaData) {
            return map.put(key, metaData);
        }
    };



    @Test
    public void testDefaultsReflection_insideCollection() {
        SerializationService serializationService = new DefaultSerializationServiceBuilder().build();

        NodeDTO node = new NodeDTO(new NodeDTO(new NodeDTO(2), 1), 0);

        EmployeeDTO employeeDTO = new EmployeeDTO(30, 102310312);
        long[] ids = new long[2];
        ids[0] = 22;
        ids[1] = 44;

        EmployeeDTO[] employeeDTOS = new EmployeeDTO[5];
        for (int j = 0; j < employeeDTOS.length; j++) {
            employeeDTOS[j] = new EmployeeDTO(20 + j, j * 100);
        }
        EmployerDTO employerDTO = new EmployerDTO("nbss", 40, ids, employeeDTO, employeeDTOS);

        ArrayList<Object> expected = new ArrayList<>();
        expected.add(node);
        expected.add(employerDTO);

        Data data = serializationService.toData(expected);
        ArrayList<Object> arrayList = serializationService.toObject(data);
        assertEquals(node, arrayList.get(0));
        assertEquals(employerDTO, arrayList.get(1));
    }

    @Test
    public void testDefaultsReflection_recursive() {
        SerializationService serializationService = new DefaultSerializationServiceBuilder().build();

        NodeDTO node = new NodeDTO(new NodeDTO(new NodeDTO(2), 1), 0);

        Data data = serializationService.toData(node);

        Object object = serializationService.toObject(data);
        NodeDTO o = (NodeDTO) object;

        assertEquals(node, o);
    }


    @Test
    public void testDefaultsReflection_nested() {
        SerializationService serializationService = new DefaultSerializationServiceBuilder().build();

        EmployeeDTO employeeDTO = new EmployeeDTO(30, 102310312);
        long[] ids = new long[2];
        ids[0] = 22;
        ids[1] = 44;

        EmployeeDTO[] employeeDTOS = new EmployeeDTO[5];
        for (int j = 0; j < employeeDTOS.length; j++) {
            employeeDTOS[j] = new EmployeeDTO(20 + j, j * 100);
        }
        EmployerDTO employerDTO = new EmployerDTO("nbss", 40, ids, employeeDTO, employeeDTOS);

        Data data = serializationService.toData(employerDTO);

        Object object = serializationService.toObject(data);
        EmployerDTO o = (EmployerDTO) object;
        assertEquals(employerDTO, o);
    }


    @Test
    public void testWithExplicitSerializer_nested() {
        SerializationConfig serializationConfig = new SerializationConfig();
        CompactSerializationConfig compactSerializationConfig = serializationConfig.getCompactSerializationConfig();
        compactSerializationConfig.register(EmployeeDTO.class, "employee",
                new CompactSerializer<EmployeeDTO>() {
                    @Override
                    public EmployeeDTO read(CompactReader in) {
                        return new EmployeeDTO(in.readInt("a"), in.readLong("i"));
                    }

                    @Override
                    public void write(CompactWriter out, EmployeeDTO object) {
                        out.writeInt("a", object.getAge());
                        out.writeLong("i", object.getId());
                    }
                });
        compactSerializationConfig.register(EmployerDTO.class, "employer",
                new CompactSerializer<EmployerDTO>() {
                    @Override
                    public EmployerDTO read(CompactReader in) {
                        String name = in.readUTF("n");
                        int age = in.readInt("a");
                        long[] ids = in.readLongArray("ids");
                        EmployeeDTO s = in.readObject("s");
                        EmployeeDTO[] ss = in.readObjectArray("ss", EmployeeDTO.class);
                        return new EmployerDTO(name, age, ids, s, ss);
                    }

                    @Override
                    public void write(CompactWriter out, EmployerDTO object) {
                        out.writeUTF("n", object.getName());
                        out.writeInt("a", object.getAge());
                        out.writeLongArray("ids", object.getIds());
                        out.writeObject("s", object.getSingleEmployee());
                        out.writeObjectArray("ss", object.getOtherEmployees());
                    }
                });

        SerializationService serializationService = new DefaultSerializationServiceBuilder().setConfig(serializationConfig).build();

        EmployeeDTO employeeDTO = new EmployeeDTO(30, 102310312);
        long[] ids = new long[2];
        ids[0] = 22;
        ids[1] = 44;

        EmployeeDTO[] employeeDTOS = new EmployeeDTO[5];
        for (int j = 0; j < employeeDTOS.length; j++) {
            employeeDTOS[j] = new EmployeeDTO(20 + j, j * 100);
        }
        EmployerDTO employerDTO = new EmployerDTO("nbss", 40, ids, employeeDTO, employeeDTOS);

        Data data = serializationService.toData(employerDTO);

        Object object = serializationService.toObject(data);
        EmployerDTO o = (EmployerDTO) object;
        assertEquals(employerDTO, o);
    }

    @Test
    public void testDefaultsReflection() {
        SerializationService serializationService = new DefaultSerializationServiceBuilder().build();
        EmployeeDTO employeeDTO = new EmployeeDTO(30, 102310312);
        Data data = serializationService.toData(employeeDTO);
        EmployeeDTO object = serializationService.toObject(data);
        assertEquals(employeeDTO, object);
    }

    @Test
    public void testWithExplicitSerializer() {
        SerializationConfig serializationConfig = new SerializationConfig();
        serializationConfig.getCompactSerializationConfig().register(EmployeeDTO.class, "employee",
                new CompactSerializer<EmployeeDTO>() {
                    @Override
                    public EmployeeDTO read(CompactReader in) {
                        return new EmployeeDTO(in.readInt("a"), in.readLong("i"));
                    }

                    @Override
                    public void write(CompactWriter out, EmployeeDTO object) {
                        out.writeInt("a", object.getAge());
                        out.writeLong("i", object.getId());
                    }
                });

        SerializationService serializationService = new DefaultSerializationServiceBuilder().setConfig(serializationConfig).build();

        EmployeeDTO employeeDTO = new EmployeeDTO(30, 102310312);
        Data data = serializationService.toData(employeeDTO);

        Object object = serializationService.toObject(data);
        EmployeeDTO actual = (EmployeeDTO) object;

        assertEquals(employeeDTO, actual);
    }


    @Test
    public void testGenericRecordHashcode_Equals() {
        SerializationService serializationService = new DefaultSerializationServiceBuilder()
                .build();

        GenericRecord.Builder builder = compact("fooBarClassName");
        builder.writeInt("foo", 1);
        builder.writeLong("bar", 1231L);
        builder.writeLongArray("barArray", new long[]{1L, 2L});
        builder.writeBigDecimal("dec", new BigDecimal(12131321));
        builder.writeGenericRecord("nestedField",
                compact("nested").writeInt("a", 2).build());
        builder.writeGenericRecordArray("nestedFieldArray", new GenericRecord[]{
                compact("nested").writeInt("a", 2).build(),
                compact("nested").writeInt("a", 3).build(),
        });
        GenericRecord expectedGenericRecord = builder.build();

        Data data = serializationService.toData(expectedGenericRecord);

        Object object = serializationService.toObject(data);
        GenericRecord genericRecord = (GenericRecord) object;

        assertTrue(expectedGenericRecord.equals(genericRecord));
        assertTrue(genericRecord.equals(expectedGenericRecord));
        assertEquals(expectedGenericRecord.hashCode(), genericRecord.hashCode());
    }

    @Test
    public void testOverridenClassNameWithAlias() {
        SerializationConfig serializationConfig = new SerializationConfig();
        serializationConfig.getCompactSerializationConfig().register(EmployeeDTO.class, "employee");

        SerializationService serializationService = new DefaultSerializationServiceBuilder().setConfig(serializationConfig).build();

        EmployeeDTO employeeDTO = new EmployeeDTO(30, 102310312);
        Data data = serializationService.toData(employeeDTO);

        Object object = serializationService.toObject(data);
        EmployeeDTO actual = (EmployeeDTO) object;

        assertEquals(employeeDTO, actual);
    }

    @Test
    public void testDeserializedToGenericRecordWhenClassNotFoundOnClassPath() {
        SerializationConfig serializationConfig = new SerializationConfig();
        serializationConfig.getCompactSerializationConfig().register(EmployeeDTO.class, "employee");

        SerializationService serializationService = new DefaultSerializationServiceBuilder()
                .setMetaDataService(metaDataService)
                .setConfig(serializationConfig)
                .build();

        EmployeeDTO employeeDTO = new EmployeeDTO(30, 102310312);
        Data data = serializationService.toData(employeeDTO);

        SerializationService readerService = new DefaultSerializationServiceBuilder()
                .setMetaDataService(metaDataService)
                .build();
        GenericRecord genericRecord = readerService.toObject(data);

        assertEquals(employeeDTO.getAge(), genericRecord.readInt("age"));
        assertEquals(employeeDTO.getId(), genericRecord.readLong("id"));
    }

    @Test
    public void testFieldOrder() throws IOException {
        EmployeeDTO employeeDTO = new EmployeeDTO(30, 102310312);
        long[] ids = new long[2];
        ids[0] = 22;
        ids[1] = 44;

        EmployeeDTO[] employeeDTOS = new EmployeeDTO[5];
        for (int j = 0; j < employeeDTOS.length; j++) {
            employeeDTOS[j] = new EmployeeDTO(20 + j, j * 100);
        }

        SchemaBuilder schemaBuilder = new SchemaBuilder("className");
        SchemaWriter writer = new SchemaWriter(schemaBuilder);

        ReflectiveCompactSerializer reflectiveCompactSerializer = new ReflectiveCompactSerializer();
        EmployerDTO employerDTO = new EmployerDTO("nbss", 40, ids, employeeDTO, employeeDTOS);
        reflectiveCompactSerializer.write(writer, employerDTO);

        Schema schema = schemaBuilder.build();

        assertEquals(((FieldDescriptorImpl) schema.getField("age")).getOffset(), 0);
        assertEquals(((FieldDescriptorImpl) schema.getField("age")).getIndex(), -1);

        assertEquals(((FieldDescriptorImpl) schema.getField("ids")).getOffset(), -1);
        assertEquals(((FieldDescriptorImpl) schema.getField("ids")).getIndex(), 0);

        assertEquals(((FieldDescriptorImpl) schema.getField("name")).getOffset(), -1);
        assertEquals(((FieldDescriptorImpl) schema.getField("name")).getIndex(), 1);

        assertEquals(((FieldDescriptorImpl) schema.getField("otherEmployees")).getOffset(), -1);
        assertEquals(((FieldDescriptorImpl) schema.getField("otherEmployees")).getIndex(), 2);

        assertEquals(((FieldDescriptorImpl) schema.getField("singleEmployee")).getOffset(), -1);
        assertEquals(((FieldDescriptorImpl) schema.getField("singleEmployee")).getIndex(), 3);
    }

    @Test
    public void testSchemaEvolution_GenericRecord() {
        SerializationService serializationService = new DefaultSerializationServiceBuilder()
                .setMetaDataService(metaDataService)
                .build();

        GenericRecord.Builder builder = compact("fooBarClassName");
        builder.writeInt("foo", 1);
        builder.writeLong("bar", 1231L);
        GenericRecord expectedGenericRecord = builder.build();

        Data data = serializationService.toData(expectedGenericRecord);

        SerializationService serializationService2 = new DefaultSerializationServiceBuilder()
                .setMetaDataService(metaDataService)
                .build();

        GenericRecord.Builder builder2 = compact("fooBarClassName");
        builder2.writeInt("foo", 1);
        builder2.writeLong("bar", 1231L);
        builder2.writeUTF("foobar", "new field");
        serializationService2.toData(builder2.build());

        Object object = serializationService2.toObject(data);
        GenericRecord genericRecord = (GenericRecord) object;

        assertFalse(genericRecord.hasField("foobar"));

        assertEquals(1, genericRecord.readInt("foo"));
        assertEquals(1231L, genericRecord.readLong("bar"));
    }

    @Test
    public void testSchemaEvolution_fieldAdded() {
        SerializationConfig serializationConfig = new SerializationConfig();
        //Using this registration to mimic schema evolution. This is usage is not advised.
        serializationConfig.getCompactSerializationConfig().register(EmployeeDTO.class, new CompactSerializer<EmployeeDTO>() {
            @Override
            public EmployeeDTO read(CompactReader in) throws IOException {
                throw new UnsupportedOperationException("We will not read from here on this test");
            }

            @Override
            public void write(CompactWriter out, EmployeeDTO object) throws IOException {
                out.writeInt("age", object.getAge());
                out.writeLong("id", object.getId());
                out.writeUTF("surname", "sir");
            }
        });

        SerializationService serializationService = new DefaultSerializationServiceBuilder()
                .setConfig(serializationConfig)
                .setMetaDataService(metaDataService)
                .build();


        EmployeeDTO expected = new EmployeeDTO(20, 102310312);
        Data data = serializationService.toData(expected);

        SerializationService serializationService2 = new DefaultSerializationServiceBuilder()
                .setMetaDataService(metaDataService)
                .build();


        EmployeeDTO actual = serializationService2.toObject(data);

        assertEquals(expected.getAge(), actual.getAge());
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void testSchemaEvolution_fieldRemoved() {
        SerializationConfig serializationConfig = new SerializationConfig();
        //Using this registration to mimic schema evolution. This is usage is not advised.
        serializationConfig.getCompactSerializationConfig().register(EmployeeDTO.class, new CompactSerializer<EmployeeDTO>() {
            @Override
            public EmployeeDTO read(CompactReader in) throws IOException {
                throw new UnsupportedOperationException("We will not read from here on this test");
            }

            @Override
            public void write(CompactWriter out, EmployeeDTO object) throws IOException {
                out.writeInt("age", object.getAge());
            }
        });

        SerializationService serializationService = new DefaultSerializationServiceBuilder()
                .setConfig(serializationConfig)
                .setMetaDataService(metaDataService)
                .build();


        EmployeeDTO expected = new EmployeeDTO(20, 102310312);
        Data data = serializationService.toData(expected);

        SerializationService serializationService2 = new DefaultSerializationServiceBuilder()
                .setMetaDataService(metaDataService)
                .build();

        EmployeeDTO actual = serializationService2.toObject(data);

        assertEquals(expected.getAge(), actual.getAge());
        assertEquals(0, actual.getId());
    }

}
