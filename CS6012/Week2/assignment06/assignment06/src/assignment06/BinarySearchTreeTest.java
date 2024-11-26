package assignment06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    private BinarySearchTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTree<>();
    }

    // Add Tests
    @Test
    void testAddElement() {
        assertTrue(tree.add(10));
        assertTrue(tree.add(20));
        assertTrue(tree.add(5));
        assertFalse(tree.add(10)); // Duplicate, should not add again
    }

    @Test
    void testAddNullElement() {
        assertThrows(NullPointerException.class, () -> tree.add(null));
    }

    @Test
    void testAddAllElements() {
        Collection<Integer> elements = Arrays.asList(1, 2, 3, 4, 5);
        assertTrue(tree.addAll(elements));
        assertFalse(tree.addAll(elements)); // All are duplicates now
    }

    // Contains Tests
    @Test
    void testContainsElement() {
        tree.add(10);
        tree.add(20);
        tree.add(5);
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(20));
        assertTrue(tree.contains(5));
        assertFalse(tree.contains(100)); // Element not in the tree
    }

    @Test
    void testContainsNullElement() {
        assertThrows(NullPointerException.class, () -> tree.contains(null));
    }

    @Test
    void testContainsAllElements() {
        tree.add(10);
        tree.add(20);
        Collection<Integer> elements = Arrays.asList(10, 20);
        assertTrue(tree.containsAll(elements));

        Collection<Integer> missingElements = Arrays.asList(10, 30);
        assertFalse(tree.containsAll(missingElements));
    }

    // Remove Tests
    @Test
    void testRemoveElement() {
        tree.add(10);
        tree.add(20);
        assertTrue(tree.remove(10));
        assertFalse(tree.contains(10));
        assertTrue(tree.remove(20));
        assertFalse(tree.contains(20));
    }

    @Test
    void testRemoveNullElement() {
        assertThrows(NullPointerException.class, () -> tree.remove(null));
    }

    @Test
    void testRemoveNonExistentElement() {
        tree.add(10);
        tree.add(20);
        assertFalse(tree.remove(30)); // Element doesn't exist in the tree
    }

    @Test
    void testRemoveAllElements() {
        tree.add(10);
        tree.add(20);
        tree.add(30);
        Collection<Integer> elementsToRemove = Arrays.asList(10, 20);

        assertTrue(tree.removeAll(elementsToRemove));
        assertFalse(tree.contains(10));
        assertFalse(tree.contains(20));
        assertTrue(tree.contains(30));
    }

    // Edge Case Tests
    @Test
    void testAddEmptyTree() {
        assertTrue(tree.add(10)); // First item should be added
    }

    @Test
    void testAddSingleElement() {
        tree.add(10);
        assertTrue(tree.contains(10));
    }

    @Test
    void testAddDuplicates() {
        tree.add(10);
        assertFalse(tree.add(10)); // Duplicate, should not be added again
        assertTrue(tree.contains(10));
    }

    @Test
    void testSize() {
        assertEquals(0, tree.size());
        tree.add(10);
        tree.add(20);
        assertEquals(2, tree.size());
        tree.remove(10);
        assertEquals(1, tree.size());
    }

    // First and Last Element Tests
    @Test
    void testFirstElement() {
        tree.add(10);
        tree.add(20);
        tree.add(5);
        assertEquals(5, tree.first()); // The smallest element
    }

    @Test
    void testFirstElementWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> tree.first());
    }

    @Test
    void testLastElement() {
        tree.add(10);
        tree.add(20);
        tree.add(5);
        assertEquals(20, tree.last()); // The largest element
    }

    @Test
    void testLastElementWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> tree.last());
    }

    // In-order Traversal Tests
    @Test
    void testToArrayList() {
        tree.add(10);
        tree.add(20);
        tree.add(5);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(5, 10, 20));
        assertEquals(expected, tree.toArrayList());
    }

    // Clear Tests
    @Test
    void testClear() {
        tree.add(10);
        tree.add(20);
        tree.add(5);
        tree.clear();
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    // isEmpty Tests
    @Test
    void testIsEmpty() {
        assertTrue(tree.isEmpty());
        tree.add(10);
        assertFalse(tree.isEmpty());
        tree.remove(10);
        assertTrue(tree.isEmpty());
    }

    // Performance Tests (Large dataset)
    @Test
    void testAddLargeNumberOfElements() {
        for (int i = 0; i < 1000; i++) {
            assertTrue(tree.add(i));
        }
        assertEquals(1000, tree.size());
    }

    @Test
    void testRemoveLargeNumberOfElements() {
        for (int i = 0; i < 1000; i++) {
            tree.add(i);
        }
        for (int i = 0; i < 500; i++) {
            assertTrue(tree.remove(i));
        }
        assertEquals(500, tree.size());
    }

    // Null Element Handling in addAll and removeAll
    @Test
    void testAddAllWithNullElement() {
        Collection<Integer> elements = Arrays.asList(10, null, 20);
        assertThrows(NullPointerException.class, () -> tree.addAll(elements));
    }

    @Test
    void testRemoveAllWithNullElement() {
        Collection<Integer> elements = Arrays.asList(10, null, 20);
        assertThrows(NullPointerException.class, () -> tree.removeAll(elements));
    }
}
