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

    // Enum for pivot strategies for easy switching
    public enum PivotStrategy {
        MIDDLE, // Middle index as the pivot
        MEDIAN_OF_QUARTILES, // Median of 25%, 50%, and 75% as the pivot
        RANDOM // Random pivot
    }

    /**
     * Public method to sort an ArrayList using quicksort.
     *
     * @param list the ArrayList to be sorted
     * @param comparator the comparator to compare elements
     * @param pivotStrategy the strategy used to choose the pivot element
     */
    public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comparator, PivotStrategy pivotStrategy) {
        quicksort(list, comparator, 0, list.size() - 1, pivotStrategy);
    }

    /**
     * Recursive quicksort method that sorts the ArrayList using the specified pivot strategy.
     *
     * @param list the ArrayList to be sorted
     * @param comparator the comparator to compare elements
     * @param low the starting index of the sublist to be sorted
     * @param high the ending index of the sublist to be sorted
     * @param pivotStrategy the strategy used to choose the pivot element
     */
    private static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comparator, int low, int high, PivotStrategy pivotStrategy) {
        // Base case: if low >= high, the sublist has one or no elements, no sorting needed
        if (low >= high) {
            return;
        }

        // Choose pivot based on the selected strategy
        int pivotIndex = choosePivot(list, comparator, low, high, pivotStrategy);

        // Partition the list based on the pivot
        int pivotNewIndex = partition(list, comparator, low, high, pivotIndex);

        // Recursively sort the left and right partitions
        quicksort(list, comparator, low, pivotNewIndex - 1, pivotStrategy);
        quicksort(list, comparator, pivotNewIndex + 1, high, pivotStrategy);
    }

    /**
     * Partitions the list into two halves around the pivot element.
     *
     * @param list the ArrayList to be partitioned
     * @param comparator the comparator to compare elements
     * @param low the starting index of the sublist to be partitioned
     * @param high the ending index of the sublist to be partitioned
     * @param pivotIndex the index of the pivot element
     * @return the index where the pivot element should be placed
     */
    public static <T> int partition(ArrayList<T> list, Comparator<? super T> comparator, int low, int high, int pivotIndex) {
        T pivot = list.get(pivotIndex);
        // Move pivot to the end
        swap(list, pivotIndex, high);

        int i = low;
        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                swap(list, i, j);
                i++;
            }
        }

        // Move pivot to its correct position
        swap(list, i, high);
        return i;
    }

    /**
     * Helper method to swap two elements in the list.
     *
     * @param list the ArrayList containing the elements to swap
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private static <T> void swap(ArrayList<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * Helper method to choose the pivot based on the selected strategy.
     *
     * @param list the ArrayList containing the elements
     * @param comparator the comparator to compare elements
     * @param low the starting index of the sublist to be sorted
     * @param high the ending index of the sublist to be sorted
     * @param pivotStrategy the strategy used to choose the pivot element
     * @return the index of the selected pivot element
     */
    private static <T> int choosePivot(ArrayList<T> list, Comparator<? super T> comparator, int low, int high, PivotStrategy pivotStrategy) {
        switch (pivotStrategy) {
            case MIDDLE:
                return middle(list, low, high); // Select the middle index as the pivot
            case MEDIAN_OF_QUARTILES:
                return medianOfQuartiles(list, comparator, low, high); // Select the median of 25%, 50%, and 75%
            case RANDOM:
                return randomPivot(list, low, high); // Select a random element as pivot
            default:
                throw new IllegalArgumentException("Unknown pivot strategy");
        }
    }

    /**
     * Helper method to select the middle index as the pivot.
     *
     * @param list the ArrayList containing the elements
     * @param low the starting index of the sublist to be sorted
     * @param high the ending index of the sublist to be sorted
     * @return the index of the middle element
     */
    private static <T> int middle(ArrayList<T> list, int low, int high) {
        // Select the middle index of the list
        return low + (high - low) / 2;
    }

    /**
     * Helper method to select the median of 25%, 50%, and 75% as the pivot.
     *
     * @param list the ArrayList containing the elements
     * @param comparator the comparator to compare elements
     * @param low the starting index of the sublist to be sorted
     * @param high the ending index of the sublist to be sorted
     * @return the index of the pivot element
     */
    private static <T> int medianOfQuartiles(ArrayList<T> list, Comparator<? super T> comparator, int low, int high) {
        int mid = low + (high - low) / 2;
        int quartile25 = low + (high - low) / 4;
        int quartile75 = high - (high - low) / 4;

        T first = list.get(low);
        T middle = list.get(mid);
        T third = list.get(quartile25);
        T last = list.get(quartile75);
        T finalElement = list.get(high);

        // Find median of the four elements: first, middle, quartile25, quartile75, and last.
        T[] candidates = (T[]) new Object[]{first, middle, third, last, finalElement};
        java.util.Arrays.sort(candidates, comparator);
        T pivotValue = candidates[2]; // Median of 25%, 50%, 75%

        // Find the index of the pivot in the list
        return list.indexOf(pivotValue);
    }

    /**
     * Helper method to select a random pivot.
     *
     * @param list the ArrayList containing the elements
     * @param low the starting index of the sublist to be sorted
     * @param high the ending index of the sublist to be sorted
     * @return the index of the randomly selected pivot
     */
    private static <T> int randomPivot(ArrayList<T> list, int low, int high) {
        Random rand = new Random();
        return rand.nextInt(high - low + 1) + low; // Select a random index between low and high
    }


}
