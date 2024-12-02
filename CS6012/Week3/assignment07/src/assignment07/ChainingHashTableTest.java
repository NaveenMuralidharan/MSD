package assignment07;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChainingHashTableTest {
    private ChainingHashTable tableBad;
    private ChainingHashTable tableMediocre;
    private ChainingHashTable tableGood;

    @BeforeEach
    void setUp() {
        // Setup the ChainingHashTable instances with different hash functors
        tableBad = new ChainingHashTable(10, new BadHashFunctor());
        tableMediocre = new ChainingHashTable(10, new MediocreHashFunctor());
        tableGood = new ChainingHashTable(10, new GoodHashFunctor());
    }

    @Test
    void testAddAndContains() {
        // Test adding items and checking if they're present
        tableBad.add("apple");
        tableBad.add("banana");
        assertTrue(tableBad.contains("apple"));
        assertTrue(tableBad.contains("banana"));
        assertFalse(tableBad.contains("orange"));
    }

    @Test
    void testAddDuplicate() {
        // Test adding a duplicate item (shouldn't add)
        tableMediocre.add("apple");
        assertFalse(tableMediocre.add("apple"));
    }

    @Test
    void testRemove() {
        // Test removing an item
        tableMediocre.add("apple");
        assertTrue(tableMediocre.remove("apple"));
        assertFalse(tableMediocre.contains("apple"));
    }

    @Test
    void testRemoveNonExistent() {
        // Test removing an item that doesn't exist
        tableGood.add("apple");
        assertFalse(tableGood.remove("banana"));
    }

    @Test
    void testClear() {
        // Test clearing the table
        tableGood.add("apple");
        tableGood.add("banana");
        tableGood.clear();
        assertTrue(tableGood.isEmpty());
        assertFalse(tableGood.contains("apple"));
        assertFalse(tableGood.contains("banana"));
    }

    @Test
    void testRehashing() {
        // Test that the table resizes (rehashes) when the load factor is exceeded
        for (int i = 0; i < 1000; i++) {
            tableMediocre.add("item" + i);
        }
        assertEquals(1000, tableMediocre.size());
        assertTrue(tableMediocre.contains("item500"));
        assertTrue(tableMediocre.contains("item999"));
    }



}