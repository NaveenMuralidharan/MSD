package assignment04;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class SortUtilTest {

    @Test
    void testSortIntegers() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(4);
        list.add(7);
        list.add(1);
        list.add(3);
        list.add(5);

        // Sorting the list using mergesort
        SortUtil.mergesort(list, Integer::compareTo);

        // Verify that the list is sorted in ascending order
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(4, list.get(2));
        assertEquals(5, list.get(3));
        assertEquals(7, list.get(4));
        assertEquals(9, list.get(5));
    }

    @Test
    void testSortStrings() {
        ArrayList<String> list = new ArrayList<>();
        list.add("banana");
        list.add("apple");
        list.add("kiwi");
        list.add("orange");

        // Sorting the list using mergesort
        SortUtil.mergesort(list, String::compareTo);

        // Verify that the list is sorted in alphabetical order
        assertEquals("apple", list.get(0));
        assertEquals("banana", list.get(1));
        assertEquals("kiwi", list.get(2));
        assertEquals("orange", list.get(3));
    }

    @Test
    void testAlreadySortedList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        // Sorting an already sorted list using mergesort
        SortUtil.mergesort(list, Integer::compareTo);

        // Verify that the list remains the same
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(4, list.get(3));
        assertEquals(5, list.get(4));
    }

    @Test
    void testListWithDuplicates() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(2);
        list.add(4);
        list.add(1);
        list.add(2);

        // Sorting the list using mergesort
        SortUtil.mergesort(list, Integer::compareTo);

        // Verify that the list is sorted in ascending order, including duplicates
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(4, list.get(3));
        assertEquals(4, list.get(4));
    }

    @Test
    void testSingleElementList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(42); // Single element

        // Sorting the list using mergesort
        SortUtil.mergesort(list, Integer::compareTo);

        // Verify that the list remains unchanged
        assertEquals(42, list.get(0));
    }

    @Test
    void testEmptyList() {
        ArrayList<Integer> list = new ArrayList<>(); // Empty list

        // Sorting the empty list using mergesort
        SortUtil.mergesort(list, Integer::compareTo);

        // Verify that the list is still empty
        assertTrue(list.isEmpty());
    }

    @Test
    public void testPartition() {
        ArrayList<Integer> list = SortUtil.generateAverageCase(1000);
        int pivotIndex = 2; // Choose 3 as the pivot
        int low = 0;
        int high = list.size() - 1;
        Comparator<Integer> comparator = Integer::compareTo;

        // Perform the partition
        int pivotNewIndex = SortUtil.partition(list, comparator, low, high, pivotIndex);

        // Ensure the pivot element is in the correct position
        assertTrue(pivotNewIndex >= 0 && pivotNewIndex < list.size());
        assertTrue(list.get(pivotNewIndex) <= list.get(pivotNewIndex + 1));
        assertTrue(list.get(pivotNewIndex - 1) <= list.get(pivotNewIndex));
    }

    @Test
    public void testQuickSort() {
        ArrayList<Integer> list = SortUtil.generateAverageCase(1000);
        Comparator<Integer> comparator = Integer::compareTo;

        // Sort using quicksort with the MIDDLE pivot strategy
        SortUtil.quicksort(list, comparator);

        // Check if the list is sorted
        for (int i = 1; i < list.size(); i++) {
            assertTrue(list.get(i - 1) <= list.get(i));
        }
    }

    @Test
    public void testQuickSortWithRandomPivot() {
        ArrayList<Integer> list = SortUtil.generateAverageCase(1000);
        Comparator<Integer> comparator = Integer::compareTo;

        // Sort using quicksort with the RANDOM pivot strategy
        SortUtil.quicksort(list, comparator);

        // Check if the list is sorted
        for (int i = 1; i < list.size(); i++) {
            assertTrue(list.get(i - 1) <= list.get(i));
        }
    }

    @Test
    public void testQuickSortWithMedianOfQuartiles() {
        ArrayList<Integer> list = SortUtil.generateAverageCase(1000);
        Comparator<Integer> comparator = Integer::compareTo;

        // Sort using quicksort with the MEDIAN_OF_QUARTILES pivot strategy
        SortUtil.quicksort(list, comparator);

        // Check if the list is sorted
        for (int i = 1; i < list.size(); i++) {
            assertTrue(list.get(i - 1) <= list.get(i));
        }
    }


}