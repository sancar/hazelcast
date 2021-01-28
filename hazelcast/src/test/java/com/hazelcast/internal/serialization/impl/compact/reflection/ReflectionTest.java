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

package com.hazelcast.internal.serialization.impl.compact.reflection;

import com.hazelcast.internal.serialization.Data;
import com.hazelcast.internal.serialization.SerializationService;
import com.hazelcast.internal.serialization.impl.DefaultSerializationServiceBuilder;
import com.hazelcast.internal.serialization.impl.compact.EmployeeDTO;
import com.hazelcast.test.HazelcastParallelClassRunner;
import com.hazelcast.test.annotation.ParallelJVMTest;
import com.hazelcast.test.annotation.QuickTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(HazelcastParallelClassRunner.class)
@Category({QuickTest.class, ParallelJVMTest.class})
public class ReflectionTest {

    { //TODO sancar cleanup
        System.setProperty("com.hazelcast.serialization.compact.debug", "true");
    }

    @Test
    public void testPrimitiveCollectionField() {
        SerializationService serializationService = new DefaultSerializationServiceBuilder().build();

        Collection<Integer> objects = new ArrayList<>();
        objects.add(4);
        objects.add(5);
        SampleClasses.PrimitiveCollection expected =
                new SampleClasses.PrimitiveCollection(objects);

        Data data = serializationService.toData(expected);
        SampleClasses.PrimitiveCollection actual = serializationService.toObject(data);

        assertEquals(expected.integers, actual.integers);
    }

    @Test
    public void testBigIntegerArrayCollection() {
        SerializationService serializationService = new DefaultSerializationServiceBuilder().build();

        ArrayList objects = new ArrayList<>();
        objects.add(new BigInteger[]{BigInteger.valueOf(2)});
        objects.add(new BigInteger[]{BigInteger.valueOf(2), BigInteger.valueOf(100)});
        SampleClasses.BigIntegerArrayCollection expected =
                new SampleClasses.BigIntegerArrayCollection(objects);

        Data data = serializationService.toData(expected);
        SampleClasses.BigIntegerArrayCollection actual = serializationService.toObject(data);

        assertEquals(expected.collectionOfBigIntegerArray.size(), actual.collectionOfBigIntegerArray.size());
        Iterator<BigInteger[]> iterator = actual.collectionOfBigIntegerArray.iterator();
        for (BigInteger[] bigIntegers : expected.collectionOfBigIntegerArray) {
            assertArrayEquals(bigIntegers, iterator.next());
        }
    }

    @Test
    public void testCollectionOfComposed() {
        SerializationService serializationService = new DefaultSerializationServiceBuilder().build();

        ArrayList objects = new ArrayList<>();
        objects.add(new EmployeeDTO(1, 32231312));
        objects.add(new EmployeeDTO(2, 343434343));
        SampleClasses.CollectionOfComposed expected =
                new SampleClasses.CollectionOfComposed(objects);

        Data data = serializationService.toData(expected);
        SampleClasses.CollectionOfComposed actual = serializationService.toObject(data);

        assertEquals(expected.list.size(), actual.list.size());
        Iterator<EmployeeDTO> iterator = actual.list.iterator();
        for (EmployeeDTO employeeDTO : expected.list) {
            assertEquals(employeeDTO, iterator.next());
        }
    }

    @Test
    public void testCollectionOfComposedArray() {
        SerializationService serializationService = new DefaultSerializationServiceBuilder().build();

        ArrayList objects = new ArrayList<>();
        objects.add(new EmployeeDTO[]{new EmployeeDTO(1, 32231312), new EmployeeDTO(2, 32231312)});
        objects.add(new EmployeeDTO[]{new EmployeeDTO(3, 343434343)});
        SampleClasses.CollectionOfComposedArray expected =
                new SampleClasses.CollectionOfComposedArray(objects);

        Data data = serializationService.toData(expected);
        SampleClasses.CollectionOfComposedArray actual = serializationService.toObject(data);

        assertEquals(expected.collectionOfEmployeeArray.size(), actual.collectionOfEmployeeArray.size());
        Iterator<EmployeeDTO[]> iterator = actual.collectionOfEmployeeArray.iterator();
        for (EmployeeDTO[] employeeDTO : expected.collectionOfEmployeeArray) {
            assertArrayEquals(employeeDTO, iterator.next());
        }
    }

    @Test
    public void testCollectionOfCollection() {
        SerializationService serializationService = new DefaultSerializationServiceBuilder().build();

        ArrayList objects = new ArrayList<>();
        objects.add(Arrays.asList(new EmployeeDTO(1, 32231312), new EmployeeDTO(2, 32231312)));
        objects.add(Arrays.asList(new EmployeeDTO(3, 343434343)));
        objects.add(Collections.emptyList());
        SampleClasses.CollectionOfCollection expected =
                new SampleClasses.CollectionOfCollection(objects);

        Data data = serializationService.toData(expected);
        SampleClasses.CollectionOfCollection actual = serializationService.toObject(data);

        assertEquals(expected.collectionOfCollecion.size(), actual.collectionOfCollecion.size());
        Iterator<Collection<EmployeeDTO>> iterator = actual.collectionOfCollecion.iterator();
        for (Collection employeeDTO : expected.collectionOfCollecion) {
            assertEquals(employeeDTO, iterator.next());
        }
    }

}
