package datastructures;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import datastructures.concrete.DoubleLinkedList;
import datastructures.interfaces.IList;

import static org.junit.Assert.assertTrue;

/**
 * This file should contain any tests that check and make sure your
 * delete method is efficient.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDeleteStress extends TestDoubleLinkedList {
    @Test(timeout=SECOND)
    public void testExample() {
        // Feel free to modify or delete this dummy test.
        assertTrue(true);
        assertEquals(3, 3);
    }
    
    @Test(timeout = 15 * SECOND)
    public void testAddAndDeleteAtFrontIsEfficient() {
        IList<Integer> list = new DoubleLinkedList<>();
        int cap = 5000000;
        for (int i = 0; i < cap; i++) {
            list.add(i * 2);
        }
        for (int i = 0; i < cap; i++) {
            list.delete(0);
        }
        assertEquals(0, list.size());
    }
    
    @Test(timeout = 15 * SECOND)
    public void testAddThenDeleteAtEndIsEfficient() {
        IList<Integer> list = new DoubleLinkedList<>();
        int cap = 5000000;
        for (int i = 0; i < cap; i++) {
            list.add(i * 2);
        }
        for (int i = 0; i < cap; i++) {
            list.delete(list.size() -1);
        }
        assertEquals(0, list.size());
    }
    
    @Test(timeout = 15 * SECOND)
    public void testAddThenDeleteNearBeginningIsEfficient() {
        IList<Integer> list = new DoubleLinkedList<>();
        int cap = 5000000;
        for (int i = 0; i < cap; i++) {
            list.add(i * 2);
        }
        for (int i = 0; i < cap - 5; i++) {
            list.delete(5);
        }
        assertEquals(5, list.size());
    }
    
}
