/*
 * Copyright (c) 2008-2021, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.sql;

import com.hazelcast.client.test.TestHazelcastFactory;
import com.hazelcast.config.Config;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.internal.util.FilteringClassLoader;
import com.hazelcast.map.IMap;
import com.hazelcast.test.HazelcastParallelParametersRunnerFactory;
import com.hazelcast.test.HazelcastTestSupport;
import com.hazelcast.test.annotation.ParallelJVMTest;
import com.hazelcast.test.annotation.QuickTest;
import example.serialization.EmployeeDTO;
import example.serialization.EmployerDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assume.assumeTrue;

/**
 * Verifies that the member can handle queries on Compact when it does not have the necessary classes on the server
 */
@RunWith(Parameterized.class)
@Parameterized.UseParametersRunnerFactory(HazelcastParallelParametersRunnerFactory.class)
@Category({QuickTest.class, ParallelJVMTest.class})
public class CompactQueryTest extends HazelcastTestSupport {
    @Parameterized.Parameter
    public InMemoryFormat inMemoryFormat;

    @Parameterized.Parameter(1)
    public boolean clusterHaveUserClasses;

    public TestHazelcastFactory factory = new TestHazelcastFactory();

    @Parameterized.Parameters(name = "inMemoryFormat:{0}, clusterHaveUserClasses:{1}")
    public static Collection<Object[]> parameters() {
        return asList(new Object[][]{
                {InMemoryFormat.BINARY, true},
                {InMemoryFormat.BINARY, false},
                {InMemoryFormat.OBJECT, true},
                {InMemoryFormat.OBJECT, false}
        });
    }

    @Before
    public void setup() {
        MapConfig mapConfig = new MapConfig("default");
        mapConfig.setInMemoryFormat(inMemoryFormat);
        Config config = smallInstanceConfig();
        config.addMapConfig(mapConfig);
        if (!clusterHaveUserClasses) {
            List<String> excludes = singletonList("example.serialization");
            FilteringClassLoader classLoader = new FilteringClassLoader(excludes, null);
            config.setClassLoader(classLoader);
        }
        factory.newHazelcastInstance(config);
    }

    @After
    public void cleanup() {
        factory.terminateAll();
    }

    @Test
    public void testQueryOnPrimitive() {
        HazelcastInstance client = factory.newHazelcastClient();
        IMap<Integer, Object> map = client.getMap("test");
        for (int i = 0; i < 10; i++) {
            map.put(i, new EmployeeDTO(i, i));
        }

        SqlResult rows = client.getSql().execute("SELECT * FROM test WHERE age >= 5");
        AtomicInteger integer = new AtomicInteger(0);
        rows.iterator().forEachRemaining(sqlRow -> integer.incrementAndGet());

        assertEquals(5, integer.get());
    }

    @Test
    public void testQueryOnObject() {
        //To be ble to run comparison methods on objects on the server we need the classes
        assumeTrue(clusterHaveUserClasses);
        HazelcastInstance client = factory.newHazelcastClient();
        IMap<Integer, Object> map = client.getMap("test");
        for (int i = 0; i < 10; i++) {
            map.put(i, new EmployerDTO(String.valueOf(i), i, null, new EmployeeDTO(i, i), null));
        }
        SqlService clientSql = client.getSql();

        EmployeeDTO expected = new EmployeeDTO(5, 5);
        SqlResult rows = clientSql.execute("SELECT name FROM test WHERE singleEmployee = ?", expected);

        Iterator<SqlRow> iterator = rows.iterator();
        SqlRow row = iterator.next();
        assertFalse(iterator.hasNext());

        assertEquals(String.valueOf(5), row.getObject("name"));
    }

}
