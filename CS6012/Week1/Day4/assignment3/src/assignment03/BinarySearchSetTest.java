package assignment03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
class BinarySearchSetTest {

    private BinarySearchSet<Integer> set;
    private BinarySearchSet<String> set3;

    @BeforeEach
    void setUp() {
        set = new BinarySearchSet<>();
        set3 = new BinarySearchSet<>();
    }

    @Test
    void testAddAndSize() {
        assertTrue(set.add(5));
        assertTrue(set.add(3));
        assertTrue(set.add(8));
        assertFalse(set.add(5));
        assertEquals(set.size(), 3);
        assertTrue(set3.add("hello"));
        assertTrue(set3.add("hi"));
        assertFalse(set3.add("hi"));
        assertEquals(set3.size(), 2);
    }

    @Test
    void testContains() {
        set.add(5);
        set.add(3);
        set.add(8);
        assertTrue(set.contains(5));
        assertFalse(set.contains(4));
        assertTrue(set3.add("hello"));
        assertTrue(set3.add("hi"));
        assertTrue(set3.contains("hi"));
        assertFalse(set3.contains("hola"));
    }

    @Test
    void testRemove() {
        set.add(5);
        set.add(3);
        set.add(8);

        assertTrue(set.remove(5));
        assertFalse(set.remove(5));
        assertFalse(set.contains(5));

        assertTrue(set3.add("hello"));
        assertTrue(set3.add("hi"));
        assertTrue(set3.remove("hi"));
        assertFalse(set3.remove("hi"));
        assertFalse(set3.contains("hi"));
    }


    @Test
    void testFirstAndLast() {
        set.add(5);
        set.add(3);
        set.add(8);

        assertEquals(set.first(), 3);
        assertEquals(set.last(), 8);

        BinarySearchSet<Integer> set2 = new BinarySearchSet<>();
        assertThrows(NoSuchElementException.class, ()->{
            set2.first();
        });
        assertThrows(NoSuchElementException.class, ()->{
            set2.last();
        });
    }

    @Test
    void testAddAll() {
        set.add(5);
        set.add(3);

        ArrayList<Integer> otherSet = new ArrayList<>();
        otherSet.add(8);
        otherSet.add(10);

        // Test adding all elements from another collection
        set.addAll(otherSet);
        assertTrue(set.contains(8));
        assertTrue(set.contains(10));
    }

    @Test
    void testToArray(){
        set.add(5);
        set.add(3);

        Object[] set2 = set.toArray();
        assertEquals(set2.length, 2);
        assertEquals(set2[0], 3);

    }

    @Test
    void testClear(){
        set.add(5);
        set.add(3);
        set.clear();
        assertEquals(set.size(), 0);
    }

    @Test
    void testIterator(){
        set.add(5);
        set.add(3);
        set.add(4);
        set.add(9);

        Iterator<Integer> setIterator = set.iterator();
        setIterator.next();
        while(setIterator.hasNext()){
            setIterator.remove();
        }
        assertEquals(set.size(),1);
    }


}