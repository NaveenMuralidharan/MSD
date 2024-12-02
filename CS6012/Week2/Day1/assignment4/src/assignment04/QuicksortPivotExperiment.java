package assignment04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class QuicksortPivotExperiment {

    // Helper method to time the sorting process for each pivot strategy
    private static <T> long timeQuicksort(ArrayList<T> list, Comparator<? super T> comparator, SortUtil.PivotStrategy pivotStrategy, int iterations) {
        long totalTime = 0;

        // Run the quicksort multiple times and accumulate the total time
        for (int i = 0; i < iterations; i++) {
            // Create a copy of the list to ensure each sort is performed on the same data
            ArrayList<T> listCopy = new ArrayList<>(list);

            // Start time measurement
            long startTime = System.nanoTime();

            // Perform quicksort
            SortUtil.quicksort(listCopy, comparator, pivotStrategy);

            // End time measurement
            long endTime = System.nanoTime();

            // Accumulate the time for each iteration
            totalTime += (endTime - startTime);
        }

        // Return the average time (in nanoseconds)
        return totalTime / iterations;
    }

    public static void main(String[] args) {
        // Comparator for Integer sorting
        Comparator<Integer> comparator = Integer::compareTo;

        // List sizes to test
        int[] sizes = {100, 500, 1000, 5000, 10000};

        // Number of iterations per data point (for averaging)
        int iterations = 10;

        // Initialize Random to generate test cases
        Random random = new Random();

        // Loop over different list sizes
        for (int size : sizes) {
            // Generate a shuffled list (average case scenario)
            ArrayList<Integer> list = SortUtil.generateAverageCase(size);

            // Run the quicksort experiment for each pivot strategy and measure the time
            System.out.println("Size: " + size);

            // Time for the Middle pivot strategy
            long timeMiddle = timeQuicksort(list, comparator, SortUtil.PivotStrategy.MIDDLE, iterations);
            System.out.println("Pivot: MIDDLE, Average Time: " + timeMiddle + " ns");

            // Time for the Median of Quartiles pivot strategy
            long timeMedianOfQuartiles = timeQuicksort(list, comparator, SortUtil.PivotStrategy.MEDIAN_OF_QUARTILES, iterations);
            System.out.println("Pivot: MEDIAN_OF_QUARTILES, Average Time: " + timeMedianOfQuartiles + " ns");

            // Time for the Random pivot strategy
            long timeRandom = timeQuicksort(list, comparator, SortUtil.PivotStrategy.RANDOM, iterations);
            System.out.println("Pivot: RANDOM, Average Time: " + timeRandom + " ns");

            System.out.println();
        }
    }
}
