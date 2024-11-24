package assignment05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {
    private SinglyLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new SinglyLinkedList<>();
    }

    @Test
    public void testInsertFirst() {
        list.insertFirst("A");
        assertEquals(1, list.size());
        assertEquals("A", list.getFirst());

        list.insertFirst("B");
        assertEquals(2, list.size());
        assertEquals("B", list.getFirst());
    }

    @Test
    public void testInsertAtIndex() {
        list.insertFirst("A");
        list.insertFirst("B");
        list.insert(1, "C");
        assertEquals(3, list.size());
        assertEquals("C", list.get(1));

        list.insert(2, "D");
        assertEquals(4, list.size());
        assertEquals("D", list.get(2));
    }

    @Test
    public void testDeleteFirst() {
        list.insertFirst("A");
        list.insertFirst("B");

        assertEquals("B", list.deleteFirst());
        assertEquals(1, list.size());
        assertEquals("A", list.getFirst());

        assertEquals("A", list.deleteFirst());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testDeleteAtIndex() {
        list.insertFirst("A");
        list.insertFirst("B");
        list.insertFirst("C");

        assertEquals("B", list.delete(1));  // Deletes second element
        assertEquals(2, list.size());
        assertEquals("C", list.get(0));
        assertEquals("A", list.get(1));

        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(2));  // Out of bounds
    }

    @Test
    public void testGetFirst() {
        list.insertFirst("A");
        list.insertFirst("B");

        assertEquals("B", list.getFirst());

        assertThrows(NoSuchElementException.class, () -> {
            SinglyLinkedList<String> emptyList = new SinglyLinkedList<>();
            emptyList.getFirst();
        });
    }

    @Test
    public void testGetAtIndex() {
        list.insertFirst("A");
        list.insertFirst("B");

        assertEquals("B", list.get(0));
        assertEquals("A", list.get(1));

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));  // Index out of bounds
    }

    @Test
    public void testIndexOf() {
        list.insertFirst("A");
        list.insertFirst("B");
        list.insertFirst("C");

        assertEquals(0, list.indexOf("C"));
        assertEquals(1, list.indexOf("B"));
        assertEquals(2, list.indexOf("A"));
        assertEquals(-1, list.indexOf("D"));  // Element not found
    }

    @Test
    public void testClear() {
        list.insertFirst("A");
        list.insertFirst("B");
        assertEquals(2, list.size());

        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testToArray() {
        list.insertFirst("A");
        list.insertFirst("B");
        list.insertFirst("C");

        Object[] array = list.toArray();
        assertEquals(3, array.length);
        assertEquals("C", array[0]);
        assertEquals("B", array[1]);
        assertEquals("A", array[2]);
    }

    @Test
    public void testIterator() {
        list.insertFirst("A");
        list.insertFirst("B");
        list.insertFirst("C");

        Iterator<String> iterator = list.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("C", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertFalse(iterator.hasNext());

        // Test remove() while iterating
        iterator = list.iterator();
        iterator.next();
        iterator.remove();  // Removes "C"

        assertEquals(2, list.size());
        assertEquals("B", list.getFirst());

        // Test IllegalStateException if remove is called without next
        iterator = list.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void testRemoveFromEmptyList() {
        assertThrows(NoSuchElementException.class, () -> list.deleteFirst());
        assertThrows(NoSuchElementException.class, () -> list.getFirst());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());

        list.insertFirst("A");
        assertFalse(list.isEmpty());

        list.deleteFirst();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size());

        list.insertFirst("A");
        assertEquals(1, list.size());

        list.insertFirst("B");
        list.insertFirst("C");
        assertEquals(3, list.size());

        list.deleteFirst();
        assertEquals(2, list.size());

        list.clear();
        assertEquals(0, list.size());
    }


}