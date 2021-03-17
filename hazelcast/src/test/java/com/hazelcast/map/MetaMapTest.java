package com.hazelcast.map;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.test.HazelcastParallelClassRunner;
import com.hazelcast.test.HazelcastTestSupport;
import com.hazelcast.test.TestHazelcastInstanceFactory;
import com.hazelcast.test.annotation.ParallelJVMTest;
import com.hazelcast.test.annotation.QuickTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.util.Map;

@RunWith(HazelcastParallelClassRunner.class)
@Category({QuickTest.class, ParallelJVMTest.class})
public class MetaMapTest extends HazelcastTestSupport {

    @Test
    public void test() {
        TestHazelcastInstanceFactory factory = createHazelcastInstanceFactory(3);
        factory.newHazelcastInstance(getConfig());
        factory.newHazelcastInstance(getConfig());
        HazelcastInstance instance = factory.newHazelcastInstance(getConfig());

        IMap<Object, Object> map = instance.getMap("test");

        map.submitToKey(1, new EntryProcessor<Object, Object, Object>() {
            @Override
            public Object process(Map.Entry<Object, Object> entry) {
                try {
                    System.out.println(Thread.currentThread().getName() + " sleeping ");
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        System.out.println("Thread is blocked ");

        IMap<Object, Object> map2 = instance.getMap("##meta##test2");
        System.out.println("get second map ");


        map2.submitToKey(1, new EntryProcessor<Object, Object, Object>() {
            @Override
            public Object process(Map.Entry<Object, Object> entry) {
                System.out.println("Should say meta operation thread " + Thread.currentThread().getName());
                return null;
            }
        });
        System.out.println("map2.submitToKey ");

        
        //should not block
        map2.put(1, 2);
    }
}
