package datastructures;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//import datastructures.concrete.DoubleLinkedList;
import datastructures.interfaces.IList;

//import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class should contain all the tests you implement to verify that
 * your 'delete' method behaves as specified.
 *
 * This test _extends_ your TestDoubleLinkedList class. This means that when
 * you run this test, not only will your tests run, all of the ones in
 * TestDoubleLinkedList will also run.
 *
 * This also means that you can use any helper methods defined within
 * TestDoubleLinkedList here. In particular, you may find using the
 * 'assertListMatches' and 'makeBasicList' helper methods to be useful.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDeleteFunctionality extends TestDoubleLinkedList {
    
    @Test(timeout=SECOND)
    public void testDeleteBasic() {
        IList<String> list = this.makeBasicList();
        list.add("d");
        list.add("e");
        list.add("f");
        
        list.delete(0);
        this.assertListMatches(new String[] {"b", "c", "d", "e", "f"}, list);
        list.delete(2);
        this.assertListMatches(new String[] {"b", "c", "e", "f"}, list);
        list.delete(3);
        this.assertListMatches(new String[] {"b", "c", "e"}, list);
    }
    
    @Test(timeout=SECOND)
    public void testDeleteAndInsert() {
        IList<String> list = this.makeBasicList();

        list.delete(0);
        this.assertListMatches(new String[] {"b", "c"}, list);
        list.insert(0, "a");
        this.assertListMatches(new String[] {"a", "b", "c"}, list);
        list.insert(1, "d");
        this.assertListMatches(new String[] {"a", "d", "b", "c"}, list);
        list.delete(3);
        this.assertListMatches(new String[] {"a", "d", "b"}, list);
    }
    
    @Test(timeout=SECOND)
    public void testDeleteOutOfBounds() {
        IList<String> list = this.makeBasicList();
        
        try {
            list.delete(-1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
        try {
            list.delete(3);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
    }
    
    @Test(timeout = SECOND)
    public void testDeleteFromEmpty() {
        IList<String> list = this.makeBasicList();
        list.delete(0);
        list.delete(0);
        list.delete(0);
        
        try {
            list.delete(0);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ex) {
            // do nothing
        }
    }
    
    @Test(timeout = SECOND)
    public void testDeleteValue() {
        IList<String> list = this.makeBasicList();
        
        assertEquals(list.delete(1), "b");
        assertEquals(list.delete(1), "c");
        assertEquals(list.delete(0), "a");
    }
    
    @Test(timeout=SECOND)
    public void testDeleteEndAndSingleElement() {
        IList<String> list = this.makeBasicList();
        
        list.delete(2);
        this.assertListMatches(new String[] {"a", "b"}, list);
        list.delete(1);
        list.delete(0);
        this.assertListMatches(new String[] {}, list);
    }

}
