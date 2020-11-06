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

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.test.TestHazelcastFactory;
import com.hazelcast.config.Config;
import com.hazelcast.config.GlobalSerializerConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.compact.Compact;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hazelcast.nio.serialization.compact.Schema;
import com.hazelcast.query.Predicates;
import com.hazelcast.test.HazelcastParallelClassRunner;
import com.hazelcast.test.annotation.ParallelJVMTest;
import com.hazelcast.test.annotation.QuickTest;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(HazelcastParallelClassRunner.class)
@Category({QuickTest.class, ParallelJVMTest.class})
public class CompactWithClientTest {

    TestHazelcastFactory factory = new TestHazelcastFactory();

    @After
    public void teardown() {
        factory.shutdownAll();
    }

    @Test
    public void testDefaultsReflection_hasCollection() throws IOException {
        factory.newHazelcastInstance();
        ClientConfig clientConfig = new ClientConfig();
        SerializationConfig serializationConfig = clientConfig.getSerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
        Compact compact = new Compact();
        compact.register(EmployeeDTO.class, 1);
        compact.register(EmployeeGroup.class, 2);
        globalSerializerConfig.setImplementation(compact);
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);

        HazelcastInstance hazelcastClient = factory.newHazelcastClient(clientConfig);
        IMap<Object, Object> map = hazelcastClient.getMap("test");


        //second loop for covering cached code path
        for (int i = 0; i < 2; i++) {
            EmployeeDTO employeeDTO = new EmployeeDTO(i, 102310312);
            ArrayList<EmployeeDTO> arrayList = new ArrayList<>();
            arrayList.add(employeeDTO);
            arrayList.add(employeeDTO);

            EmployeeGroup expected = new EmployeeGroup(arrayList, i);

            map.put(i, expected);
            EmployeeGroup actual = (EmployeeGroup) map.get(i);

            assertEquals(actual, expected);
        }
    }

    @Test
    public void testDefaultsReflection_insideCollection() throws IOException {
        factory.newHazelcastInstance();
        ClientConfig clientConfig = new ClientConfig();
        SerializationConfig serializationConfig = clientConfig.getSerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
        Compact compact = new Compact();
        compact.register(EmployeeDTO.class, 1);
        compact.register(EmployerDTO.class, 2);
        compact.register(NodeDTO.class, 3);
        globalSerializerConfig.setImplementation(compact);
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);

        HazelcastInstance hazelcastClient = factory.newHazelcastClient(clientConfig);
        IMap<Object, Object> map = hazelcastClient.getMap("test");

        //second loop for covering cached code path
        for (int i = 0; i < 2; i++) {
            NodeDTO node = new NodeDTO(new NodeDTO(new NodeDTO(2), i), 0);

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

            map.put(i, expected);
            ArrayList arrayList = (ArrayList) map.get(i);

            assertEquals(node, arrayList.get(0));
            assertEquals(employerDTO, arrayList.get(1));
        }
    }


    @Test
    public void testDefaultsReflection_recursive() throws IOException {
        factory.newHazelcastInstance();
        ClientConfig clientConfig = new ClientConfig();
        SerializationConfig serializationConfig = clientConfig.getSerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
        Compact compact = new Compact();
        compact.register(NodeDTO.class, 1);
        globalSerializerConfig.setImplementation(compact);
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);

        HazelcastInstance hazelcastClient = factory.newHazelcastClient(clientConfig);
        IMap<Object, Object> map = hazelcastClient.getMap("test");


        //second loop for covering cached code path
        for (int i = 0; i < 2; i++) {
            NodeDTO node = new NodeDTO(new NodeDTO(new NodeDTO(2), i), 0);
            map.put(i, node);
            NodeDTO o = (NodeDTO) map.get(i);
            assertEquals(node, o);
        }
    }


    @Test
    public void testDefaultsReflection_nested() throws IOException {
        factory.newHazelcastInstance();
        ClientConfig clientConfig = new ClientConfig();
        SerializationConfig serializationConfig = clientConfig.getSerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
        Compact compact = new Compact();
        compact.register(EmployeeDTO.class, 1);
        compact.register(EmployerDTO.class, 2);
        globalSerializerConfig.setImplementation(compact);
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);

        HazelcastInstance hazelcastClient = factory.newHazelcastClient(clientConfig);
        IMap<Object, Object> map = hazelcastClient.getMap("test");


        //second loop for covering cached code path
        for (int i = 0; i < 2; i++) {
            EmployeeDTO employeeDTO = new EmployeeDTO(i, 102310312);

            long[] ids = new long[2];
            ids[0] = i + 2;
            ids[1] = i + 4;

            EmployeeDTO[] employeeDTOS = new EmployeeDTO[5];
            for (int j = 0; j < employeeDTOS.length; j++) {
                employeeDTOS[j] = new EmployeeDTO(i + j, i + j * 100);
            }

            EmployerDTO employerDTO = new EmployerDTO("nbss", i + 10, ids, employeeDTO, employeeDTOS);

            map.put(i, employerDTO);
            EmployerDTO o = (EmployerDTO) map.get(i);
            assertEquals(employerDTO, o);
        }

    }

    @Test
    public void testDefaultsReflection() throws IOException {
        factory.newHazelcastInstance();
        ClientConfig clientConfig = new ClientConfig();
        SerializationConfig serializationConfig = clientConfig.getSerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
        Compact compact = new Compact();
        compact.register(EmployeeDTO.class, 1);
        globalSerializerConfig.setImplementation(compact);
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);

        HazelcastInstance hazelcastClient = factory.newHazelcastClient(clientConfig);
        IMap<Object, Object> map = hazelcastClient.getMap("test");

        //second loop for covering cached code path
        for (int i = 0; i < 2; i++) {
            EmployeeDTO employeeDTO = new EmployeeDTO(i, 102310312);
            map.put(i, employeeDTO);
            EmployeeDTO o = (EmployeeDTO) map.get(i);
            assertEquals(employeeDTO, o);
        }

    }

    @Test
    public void testWithExplicitSerializer() throws IOException {
        factory.newHazelcastInstance();
        ClientConfig clientConfig = new ClientConfig();
        SerializationConfig serializationConfig = clientConfig.getSerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
        Compact compact = new Compact();
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);
        compact.register(EmployeeDTO.class, 1, new CompactSerializer<EmployeeDTO>() {
            @Override
            public EmployeeDTO read(Schema schema, CompactReader in) throws IOException {
                return new EmployeeDTO(in.readInt("age"), in.readLong("id"));
            }

            @Override
            public void write(CompactWriter out, EmployeeDTO object) throws IOException {
                out.writeInt("age", object.getAge());
                out.writeLong("id", object.getId());
            }
        });

        globalSerializerConfig.setImplementation(compact);

        HazelcastInstance hazelcastClient = factory.newHazelcastClient(clientConfig);

        IMap<Object, Object> map = hazelcastClient.getMap("test");

        //second loop for covering cached code path
        for (int i = 0; i < 2; i++) {
            EmployeeDTO employeeDTO = new EmployeeDTO(i, 102310312);
            map.put(i, employeeDTO);
            EmployeeDTO actual = (EmployeeDTO) map.get(i);

            assertEquals(employeeDTO, actual);
        }
    }


    @Test
    public void testSerializeWithGenericRecord() throws IOException {
        factory.newHazelcastInstance();
        ClientConfig clientConfig = new ClientConfig();
        SerializationConfig serializationConfig = clientConfig.getSerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
        Compact compact = new Compact();
        globalSerializerConfig.setImplementation(compact);
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);
        HazelcastInstance hazelcastClient = factory.newHazelcastClient(clientConfig);
        IMap<Object, Object> map = hazelcastClient.getMap("test");


        //second loop for covering cached code path
        for (int i = 0; i < 2; i++) {
            Schema writeSchema = new SchemaBuilder().addIntField("foo").addLongField("bar").build();
            GenericRecord.Builder builder = GenericRecord.Builder.compact(compact, 1, writeSchema);
            builder.writeInt("foo", 1);
            builder.writeLong("bar", 1231L);
            GenericRecord expectedGenericRecord = builder.build();


            map.put(i, expectedGenericRecord);
            CompactGenericRecord genericRecord = (CompactGenericRecord) map.get(i);
            assertEquals(expectedGenericRecord, genericRecord);

            assertEquals(1, genericRecord.readInt("foo"));
            assertEquals(1231L, genericRecord.readLong("bar"));
        }


    }

    @Test
    public void testPredicate() {
        {
            SerializationConfig serializationConfig = new SerializationConfig();
            GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
            Compact compact = new Compact();
            globalSerializerConfig.setImplementation(compact);
            serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);

            Config config = new Config();
            config.setSerializationConfig(serializationConfig);
            factory.newHazelcastInstance(config);
        }
        SerializationConfig serializationConfig = new SerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
        Compact compact = new Compact();
        compact.register(EmployeeDTO.class, 1);
        globalSerializerConfig.setImplementation(compact);
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setSerializationConfig(serializationConfig);
        HazelcastInstance hazelcastClient = factory.newHazelcastClient(clientConfig);
        IMap<Object, Object> map = hazelcastClient.getMap("test");

        for (int i = 0; i < 2; i++) {
            EmployeeDTO employeeDTO = new EmployeeDTO(i, 102310312);
            map.put(i, employeeDTO);
        }

        Collection<Object> values = map.values(Predicates.sql("age > 0"));
        assertEquals(1, values.size());
        assertThat(values, containsInAnyOrder(new EmployeeDTO(1, 102310312)));
    }

}
