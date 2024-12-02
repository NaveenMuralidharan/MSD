package assignment04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class MergeSortTimingExperiment {

    private static final int NUM_TRIALS = 5; // Number of trials per test case
    private static final Random random = new Random(42); // Fixed seed for reproducibility

    public static void main(String[] args) {
        // Input sizes for the experiment
        int[] inputSizes = {100, 500, 1000, 5000, 10000};

        // Different threshold values to test
        int[] thresholds = {5, 10, 20, 30, 50, Integer.MAX_VALUE}; // Added case with no Insertion Sort

        // Running the experiment for different thresholds
        for (int threshold : thresholds) {
            System.out.println("Testing with threshold: " + (threshold == Integer.MAX_VALUE ? "No Insertion Sort" : threshold));
            // Record average runtime for each threshold and input size
            for (int size : inputSizes) {
                long avgTime = runMergeSortExperiment(size, threshold);
                System.out.printf("Size: %d, Avg Time: %d ns\n", size, avgTime);
            }
        }
    }

    /**
     * Runs the merge sort experiment for a given input size and threshold.
     *
     * @param size the size of the list to sort
     * @param threshold the threshold to switch from merge sort to insertion sort
     * @return the average time taken to sort the list in nanoseconds
     */
    private static long runMergeSortExperiment(int size, int threshold) {
        long totalTime = 0;

        for (int i = 0; i < NUM_TRIALS; i++) {
            // Generate a random input list
            ArrayList<Integer> list = generatePermutedList(size);
            Comparator<Integer> comparator = Integer::compareTo;

            // Measure time taken for sorting
            long startTime = System.nanoTime();
            SortUtil.mergesort(list, comparator, threshold); // Use mergesort with given threshold
            long endTime = System.nanoTime();

            totalTime += (endTime - startTime);  // Accumulate time in nanoseconds
        }

        // Return average time in nanoseconds
        return totalTime / NUM_TRIALS;  // Average time over all trials
    }

    /**
     * Generates a permuted list for the given size using a fixed random seed.
     * This ensures the same permutation is used across different trials.
     *
     * @param size the size of the list to generate
     * @return a randomly permuted ArrayList of integers from 1 to size
     */
    private static ArrayList<Integer> generatePermutedList(int size) {
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }

        // Fisher-Yates shuffle to permute the list
        for (int i = size - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Swap elements at i and j
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }

        return list;
    }
}
