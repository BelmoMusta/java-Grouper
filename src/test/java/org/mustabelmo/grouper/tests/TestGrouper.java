package org.mustabelmo.grouper.tests;

import org.junit.Assert;
import org.junit.Test;
import org.mustabelmo.grouper.ArrayListGrouper;
import org.mustabelmo.grouper.Grouper;
import org.mustabelmo.grouper.HashSetGrouper;

import java.util.Collection;

public class TestGrouper {
    @Test
    public void testArrayListGrouper() {
        Grouper<Integer, String> grouper = new ArrayListGrouper<>();
        grouper.put(1, "a");
        grouper.put(1, "b");
        grouper.put(1, "c");
        grouper.put(1, "c");
        grouper.put(2, "c");
        Assert.assertEquals(4, grouper.get(1).size());
        
    }

    @Test
    public void testHashSetGrouper() {
        Grouper<Integer, String> grouper = new HashSetGrouper<>();
        grouper.put(1, "a");
        grouper.put(1, "b");
        grouper.put(1, "c");
        grouper.put(1, "c");
        grouper.put(2, "c");
        System.out.println(grouper);

    }

    @Test
    public void testReduce() {
        Grouper<Integer, String> grouper = new ArrayListGrouper<>();
        grouper.put(1, "a");
        grouper.put(1, "b");
        grouper.put(1, "c");
        grouper.put(1, "c");
        grouper.put(2, "c");
        Collection<String> reduce = grouper.reduce();
        Assert.assertTrue(reduce.contains("a"));
        Assert.assertTrue(reduce.contains("b"));
        Assert.assertTrue(reduce.contains("c"));

    }

}
