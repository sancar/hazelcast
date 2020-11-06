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

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.cluster.Member;
import com.hazelcast.config.GlobalSerializerConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.map.EntryProcessor;
import com.hazelcast.map.IMap;
import com.hazelcast.nio.serialization.GenericRecord;
import com.hazelcast.nio.serialization.Serializer;
import com.hazelcast.nio.serialization.compact.Compact;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hazelcast.nio.serialization.compact.Schema;
import com.hazelcast.query.impl.predicates.SqlPredicate;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CompactSerializationCodeSample {

    public static class EmployeeDTO {

        private int age;
        private long id;

        public EmployeeDTO() {
        }

        public EmployeeDTO(int age, long id) {
            this.age = age;
            this.id = id;
        }

        @Override
        public String toString() {
            return "EmployeeDTO{" +
                    "age=" + age +
                    ", id=" + id +
                    '}';
        }
    }

    public static class MyCallable implements Callable, Serializable, HazelcastInstanceAware {

        private HazelcastInstance instance;

        @Override
        public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
            this.instance = hazelcastInstance;
        }

        @Override
        public Object call() throws Exception {
            IMap<Object, Object> map = instance.getMap("test");
            CompactGenericRecord o = (CompactGenericRecord) map.get(1);
            map.put(2, o);


            //TODO SANCAR
            Serializer implementation = instance.getConfig().getSerializationConfig().getGlobalSerializerConfig().getImplementation();
            Compact compact = (Compact) implementation;
            GenericRecord.Builder builder = GenericRecord.Builder.compact(compact, o.getClassID(), o.getSchema());
            builder.writeUTF("name", "foo");
            builder.writeInt("age", 50);
            GenericRecord record = builder.build();
            map.put(3, record);

            return o.readInt("age");
        }
    }

    public static void main(String[] args) throws IOException {
        Hazelcast.newHazelcastInstance();
        {
            ClientConfig config = new ClientConfig();
            SerializationConfig serializationConfig = config.getSerializationConfig();

            GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();

            Compact compact = new Compact();

            compact.register(EmployeeDTO.class, 1, new CompactSerializer<EmployeeDTO>() {
                @Override
                public EmployeeDTO read(Schema schema, CompactReader in) throws IOException {
                    return new EmployeeDTO(in.readInt("age"), in.readLong("id"));
                }

                @Override
                public void write(CompactWriter out, EmployeeDTO object) throws IOException {
                    out.writeInt("age", object.age);
                    out.writeLong("id", object.id);
                }
            });

            globalSerializerConfig.setImplementation(compact);
            serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);

            HazelcastInstance client = HazelcastClient.newHazelcastClient(config);

            IMap<Object, Object> map = client.getMap("test");
            map.put(1, new EmployeeDTO(30, 102310312));
            EmployeeDTO o = (EmployeeDTO) map.get(1);
            System.out.println("(EmployeeDTO) map.get(1) => " + o);

            Collection values = map.values(new SqlPredicate("age == 30"));
            System.out.println("map.values(new SqlPredicate(\"age == 30\")).size => " + values.size());

            System.out.println("map.size => " + map.size());

            //EntryProcessor Example. Since Server has no serialization config, it returns GenericPortableRecord
            map.executeOnEntries((EntryProcessor<Object, Object, Object>) entry -> {
                GenericRecord value = (GenericRecord) entry.getValue();
//                System.out.println("EntryProcessor value.getInt(\"age\") => " + value.getInt("age"));
                System.out.println("EntryProcessor value => " + value);
                return null;
            });

            //Since Server has no serialization config, map.get on MyCallable returns GenericPortableRecord
            IExecutorService executorService = client.getExecutorService("x");
            Map<Member, Future<Object>> result = executorService.submitToAllMembers(new MyCallable());
            result.forEach((member, response) -> {
                try {
                    System.out.println("executorService.submitToAllMembers => " + member + " " + response.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });

            o = (EmployeeDTO) map.get(2);
            System.out.println("(EmployeeDTO) map.get(2) => " + o);

            map.clear();
        }


        {// No serialization config needed case
            System.out.println(" No serialization config needed case ");

            ClientConfig config = new ClientConfig();
            HazelcastInstance client = HazelcastClient.newHazelcastClient(config);

            IMap<Object, Object> map = client.getMap("test");

            Schema schema = new SchemaBuilder()
                    .addUTFField("name").addIntField("age").build();

            Compact compact = new Compact();
            GenericRecord.Builder builder = GenericRecord.Builder.compact(compact, 1, schema);
            builder.writeUTF("name", "foo");
            builder.writeInt("age", 50);
            map.put(1, builder.build());

            GenericRecord o = (GenericRecord) map.get(1);
            System.out.println("(GenericRecord) map.get(1).getInt(\"age\") =>" + o.readInt("age"));

            System.out.println("map.clear");
            map.clear();

            map.put(1, o);
            o = (GenericRecord) map.get(1);
            System.out.println("(GenericRecord) map.get(1).getInt(\"age\") => " + o.readInt("age"));


            Collection values = map.values(new SqlPredicate("age == 2"));
            System.out.println("map.values(new SqlPredicate(\"age == 2\").size() => " + values.size());

            //EntryProcessor Example. Since Server has no serialization config, it returns GenericRecord
            map.executeOnEntries((EntryProcessor<Object, Object, Object>) entry -> {
                GenericRecord value = (GenericRecord) entry.getValue();
//                System.out.println("EntryProcessor value.getInt(\"age\")) => " + value.readInt("age"));
                System.out.println("EntryProcessor value => " + value);
                return null;
            });

            //Since Server has no serialization config, map.get on MyCallable returns GenericRecord
            IExecutorService executorService = client.getExecutorService("x");
            Map<Member, Future<Object>> result = executorService.submitToAllMembers(new MyCallable());
            result.forEach((member, response) -> {
                try {
                    System.out.println("executorService.submitToAllMembers => " + member + " " + response.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });

            o = (GenericRecord) map.get(2);
            System.out.println("(GenericRecord) map.get(2).getInt(\"age\") => " + o.readInt("age"));

        }
        Hazelcast.shutdownAll();
        HazelcastClient.shutdownAll();
    }


}
