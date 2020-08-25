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

import com.hazelcast.config.GlobalSerializerConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.internal.serialization.Data;
import com.hazelcast.internal.serialization.SerializationService;
import com.hazelcast.internal.serialization.impl.DefaultSerializationServiceBuilder;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.compact.Compact;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hazelcast.nio.serialization.compact.Schema;
import com.hazelcast.test.HazelcastParallelClassRunner;
import com.hazelcast.test.annotation.ParallelJVMTest;
import com.hazelcast.test.annotation.QuickTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(HazelcastParallelClassRunner.class)
@Category({QuickTest.class, ParallelJVMTest.class})
public class CompactTest {

    @Test
    public void testDefaultsReflection_hasCollection() {
        SerializationConfig serializationConfig = new SerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
        Compact compact = new Compact();
        compact.register(EmployeeDTO.class, 1);
        compact.register(EmployeeGroup.class, 2);
        globalSerializerConfig.setImplementation(compact);
        globalSerializerConfig.setOverrideJavaSerialization(true);
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);
        SerializationService serializationService = new DefaultSerializationServiceBuilder().setConfig(serializationConfig).build();


        EmployeeDTO employeeDTO = new EmployeeDTO(30, 102310312);
        ArrayList<EmployeeDTO> arrayList = new ArrayList<>();
        arrayList.add(employeeDTO);
        arrayList.add(employeeDTO);

        EmployeeGroup expected = new EmployeeGroup(arrayList, 5);

        Data data = serializationService.toData(expected);
        EmployeeGroup actual = serializationService.toObject(data);

        assertEquals(expected, actual);
    }

    @Test
    public void testDefaultsReflection_insideCollection() {
        SerializationConfig serializationConfig = new SerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
        Compact compact = new Compact();
        compact.register(EmployeeDTO.class, 1);
        compact.register(EmployerDTO.class, 2);
        compact.register(NodeDTO.class, 3);
        globalSerializerConfig.setImplementation(compact);
        globalSerializerConfig.setOverrideJavaSerialization(true);
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);
        SerializationService serializationService = new DefaultSerializationServiceBuilder().setConfig(serializationConfig).build();

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
    public void testDefaultsReflection_recursive() throws IOException {
        SerializationConfig serializationConfig = new SerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
        Compact compact = new Compact();
        compact.register(NodeDTO.class, 1);
        globalSerializerConfig.setImplementation(compact);
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);
        SerializationService serializationService = new DefaultSerializationServiceBuilder().setConfig(serializationConfig).build();

        NodeDTO node = new NodeDTO(new NodeDTO(new NodeDTO(2), 1), 0);

        Data data = serializationService.toData(node);

        Object object = serializationService.toObject(data);
        NodeDTO o = (NodeDTO) object;

        assertEquals(node, o);
    }


    @Test
    public void testDefaultsReflection_nested() throws IOException {
        SerializationConfig serializationConfig = new SerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
        Compact compact = new Compact();
        compact.register(EmployeeDTO.class, 1);
        compact.register(EmployerDTO.class, 2);
        globalSerializerConfig.setImplementation(compact);
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);
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
        SerializationConfig serializationConfig = new SerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
        Compact compact = new Compact();
        compact.register(EmployeeDTO.class, 1);
        globalSerializerConfig.setImplementation(compact);
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);
        SerializationService serializationService = new DefaultSerializationServiceBuilder().setConfig(serializationConfig).build();

        EmployeeDTO employeeDTO = new EmployeeDTO(30, 102310312);

        Data data = serializationService.toData(employeeDTO);

        EmployeeDTO object = serializationService.toObject(data);
        assertEquals(employeeDTO, object);
    }

    @Test
    public void testWithExplicitSerializer() throws IOException {
        SerializationConfig serializationConfig = new SerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();

        Compact compact = new Compact();
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);

        compact.register(EmployeeDTO.class, 1, new CompactSerializer<EmployeeDTO>() {
            @Override
            public EmployeeDTO read(@Nullable Class associatedClass, Schema schema, CompactReader in) throws IOException {
                return new EmployeeDTO(in.readInt("age"), in.readLong("id"));
            }

            @Override
            public void write(Class clazz, CompactWriter out, EmployeeDTO object) throws IOException {
                out.writeInt("age", object.getAge());
                out.writeLong("id", object.getId());
            }
        });

        globalSerializerConfig.setImplementation(compact);

        SerializationService serializationService = new DefaultSerializationServiceBuilder().setConfig(serializationConfig).build();


        EmployeeDTO employeeDTO = new EmployeeDTO(30, 102310312);
        Data data = serializationService.toData(employeeDTO);

        Object object = serializationService.toObject(data);
        EmployeeDTO actual = (EmployeeDTO) object;

        assertEquals(employeeDTO, actual);
    }


    @Test
    public void testSerializeWithGenericRecord() throws IOException {
        SerializationConfig serializationConfig = new SerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
        Compact compact = new Compact();
        globalSerializerConfig.setImplementation(compact);
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);
        SerializationService serializationService = new DefaultSerializationServiceBuilder().setConfig(serializationConfig).build();

        Schema writeSchema = new SchemaBuilder().addIntField("foo").addLongField("bar").build();

        GenericRecord.Builder builder = GenericRecord.Builder.compact(compact, 1, writeSchema);
        builder.writeInt("foo", 1);
        builder.writeLong("bar", 1231L);
        GenericRecord expectedGenericRecord = builder.build();

        Data data = serializationService.toData(expectedGenericRecord);

        Object object = serializationService.toObject(data);
        GenericRecord genericRecord = (GenericRecord) object;

        assertEquals(expectedGenericRecord, genericRecord);
        assertEquals(1, genericRecord.readInt("foo"));
        assertEquals(1231L, genericRecord.readLong("bar"));

    }


}
