package org.mustabelmo.grouper.tests;

import org.junit.Assert;
import org.junit.Test;
import org.mustabelmo.grouper.ArrayListGrouper;
import org.mustabelmo.grouper.Grouper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
        Assert.assertEquals(1, grouper.get(2).size());
        
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
    
    @Test
    public void testIterator() {
        Grouper<Integer, String> grouper = new ArrayListGrouper<>();
        grouper.put(1, "a");
        grouper.put(1, "b");
        grouper.put(1, "c");
        grouper.put(1, "c");
        grouper.put(2, "c");
        Iterator<String> iterator = grouper.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("a", iterator.next());
        Assert.assertEquals("b", iterator.next());
        Assert.assertEquals("c", iterator.next());
        Assert.assertEquals("c", iterator.next());
        Assert.assertEquals("c", iterator.next());
        Assert.assertFalse(iterator.hasNext());
    
    }
    @Test
    public void testPutAll() {
        Grouper<Integer, String> grouper = new ArrayListGrouper<>();
        grouper.putAll(1, Arrays.asList("a", "b", "c"));
        List<String> reduced = new ArrayList<>();
        grouper.reduceTo(reduced);
        Assert.assertEquals(3, reduced.size());
        Assert.assertEquals("a", reduced.get(0));
        Assert.assertEquals("b", reduced.get(1));
        Assert.assertEquals("c", reduced.get(2));
    }
    
    @Test
    public void testFindGroupsOf() {
        Grouper<Integer, String> grouper = new ArrayListGrouper<>();
        grouper.putAll(1, Arrays.asList("a", "b", "c"));
        grouper.putAll(2, Arrays.asList("a", "e", "f"));
        Collection<Integer> groupsOfA = grouper.findGroupsOf("a");
        Iterator<Integer> iterator = groupsOfA.iterator();
        Assert.assertEquals(2, groupsOfA.size());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        System.out.println(grouper);
        
    }

}
