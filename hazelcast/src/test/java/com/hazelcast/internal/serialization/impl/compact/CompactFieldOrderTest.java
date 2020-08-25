package com.hazelcast.internal.serialization.impl.compact;

import com.hazelcast.test.HazelcastParallelClassRunner;
import com.hazelcast.test.annotation.ParallelJVMTest;
import com.hazelcast.test.annotation.QuickTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(HazelcastParallelClassRunner.class)
@Category({QuickTest.class, ParallelJVMTest.class})
public class CompactFieldOrderTest {

    @Test
    public void test() throws IOException {
        //Test primitives comes first even with custom CompactSerializer
        throw new UnsupportedOperationException();
    }
}
