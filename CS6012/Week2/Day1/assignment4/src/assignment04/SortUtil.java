package assignment04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class SortUtil {

    // Threshold value for switching to Insertion Sort (Can be adjusted)
    private static final int INSERTION_SORT_THRESHOLD = 10;

    /**
     * Sorts the provided ArrayList using MergeSort, switching to Insertion Sort for small sublists.
     *
     * @param list the ArrayList to be sorted
     * @param comparator the Comparator used for comparing elements
     * @param <T> the type of the elements in the list
     */
    public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comparator) {
        if (list == null || list.size() < 2) {
            return; // No need to sort
        }

        // Pre-allocate a temporary list for merging
        ArrayList<T> temp = new ArrayList<>(list.size());

        // Perform the actual sorting
        mergesort(list, 0, list.size() - 1, temp, comparator);
    }

    /**
     * Helper method for performing the MergeSort recursively.
     *
     * @param list the list to sort
     * @param left the left index of the range to sort
     * @param right the right index of the range to sort
     * @param temp temporary list for merging
     * @param comparator the comparator used for comparing elements
     * @param <T> the type of the elements in the list
     */
    private static <T> void mergesort(ArrayList<T> list, int left, int right, ArrayList<T> temp, Comparator<? super T> comparator) {
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(list, left, right, comparator);  // Use Insertion Sort if the sublist is small enough
        } else {
            int mid = (left + right) / 2;
            mergesort(list, left, mid, temp, comparator);  // Left half
            mergesort(list, mid + 1, right, temp, comparator);  // Right half
            merge(list, left, mid, right, temp, comparator);  // Merge the sorted halves
        }
    }

    /**
     * Merges two sorted sublists into a single sorted list.
     *
     * @param list the list to merge
     * @param left the left index of the range
     * @param mid the midpoint index
     * @param right the right index of the range
     * @param temp temporary list for merging
     * @param comparator the comparator used for comparing elements
     * @param <T> the type of the elements in the list
     */
    private static <T> void merge(ArrayList<T> list, int left, int mid, int right, ArrayList<T> temp, Comparator<? super T> comparator) {
        int i = left, j = mid + 1, k = left;

        // Merge the two halves into the temporary list
        while (i <= mid && j <= right) {
            if (comparator.compare(list.get(i), list.get(j)) <= 0) {
                temp.add(k++, list.get(i++));
            } else {
                temp.add(k++, list.get(j++));
            }
        }

        // Copy remaining elements from the left half
        while (i <= mid) {
            temp.add(k++, list.get(i++));
        }

        // Copy remaining elements from the right half
        while (j <= right) {
            temp.add(k++, list.get(j++));
        }

        // Copy merged elements back into the original list
        for (i = left; i <= right; i++) {
            list.set(i, temp.get(i));
        }
    }

    /**
     * Insertion sort for a specific range in the list.
     *
     * @param list the list to sort
     * @param left the left index of the range to sort
     * @param right the right index of the range to sort
     * @param comparator the comparator used for comparing elements
     * @param <T> the type of the elements in the list
     */
    private static <T> void insertionSort(ArrayList<T> list, int left, int right, Comparator<? super T> comparator) {
        for (int i = left + 1; i <= right; i++) {
            T key = list.get(i);
            int j = i - 1;

            // Shift elements of list[0..i-1] that are greater than key
            while (j >= left && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }

            list.set(j + 1, key);
        }
    }

    /**
     * Generates a best-case scenario for sorting (ascending order).
     * @param size The number of elements to generate.
     * @return A sorted ArrayList with integers from 1 to size in ascending order.
     */
    public static ArrayList<Integer> generateBestCase(int size) {
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * Generates an average-case scenario (random permutation of numbers from 1 to size).
     * @param size The number of elements to generate.
     * @return A randomly shuffled ArrayList with integers from 1 to size.
     */
    /**
     * Generates an average-case scenario (random permutation of numbers from 1 to size)
     * using the Fisher–Yates shuffle algorithm.
     * @param size The number of elements to generate.
     * @return A randomly shuffled ArrayList with integers from 1 to size.
     */
    public static ArrayList<Integer> generateAverageCase(int size) {
        // Initialize an ArrayList with integers from 1 to size
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }

        // Apply Fisher-Yates shuffle
        Random random = new Random();
        for (int i = size - 1; i > 0; i--) {
            // Pick a random index from 0 to i
            int j = random.nextInt(i + 1);

            // Swap elements at i and j
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }

        return list;
    }

    /**
     * Generates a worst-case scenario for sorting (descending order).
     * @param size The number of elements to generate.
     * @return A sorted ArrayList with integers from size down to 1 in descending order.
     */
    public static ArrayList<Integer> generateWorstCase(int size) {
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = size; i >= 1; i--) {
            list.add(i);
        }
        return list;
    }

}
