package assignment04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class SortingPerformance {

    // Number of iterations for averaging the results
    private static final int ITERATIONS = 10;

    public static void main(String[] args) {
        int[] listSizes = {100, 500, 1000, 5000, 10000};

        // Record times for each sorting method
        for (int size : listSizes) {
            // Generate lists for each case
            ArrayList<Integer> bestCase = SortUtil.generateBestCase(size);
            ArrayList<Integer> avgCase = SortUtil.generateAverageCase(size);
            ArrayList<Integer> worstCase = SortUtil.generateWorstCase(size);

            // MergeSort (with optimal threshold value from previous experiments)
            long mergeSortBestCaseTime = benchmarkMergeSort(bestCase, 50);
            long mergeSortAvgCaseTime = benchmarkMergeSort(avgCase, 50);
            long mergeSortWorstCaseTime = benchmarkMergeSort(worstCase, 50);

            // QuickSort (using best pivot strategy: RANDOM for avg, MIDDLE for best/worst case)
            long quickSortBestCaseTime = benchmarkQuickSort(bestCase, SortUtil.PivotStrategy.RANDOM);
            long quickSortAvgCaseTime = benchmarkQuickSort(avgCase, SortUtil.PivotStrategy.RANDOM);
            long quickSortWorstCaseTime = benchmarkQuickSort(worstCase, SortUtil.PivotStrategy.RANDOM);

            // Output results
            System.out.println("Size: " + size);
            System.out.println("MergeSort Best Case: " + mergeSortBestCaseTime + " ns");
            System.out.println("MergeSort Average Case: " + mergeSortAvgCaseTime + " ns");
            System.out.println("MergeSort Worst Case: " + mergeSortWorstCaseTime + " ns");
            System.out.println("QuickSort Best Case: " + quickSortBestCaseTime + " ns");
            System.out.println("QuickSort Average Case: " + quickSortAvgCaseTime + " ns");
            System.out.println("QuickSort Worst Case: " + quickSortWorstCaseTime + " ns");
        }
    }

    private static long benchmarkMergeSort(ArrayList<Integer> list, int insertionSortThreshold) {
        long totalTime = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            ArrayList<Integer> copyList = new ArrayList<>(list);
            long startTime = System.nanoTime();
            SortUtil.mergesort(copyList, Comparator.naturalOrder(), insertionSortThreshold);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return totalTime / ITERATIONS;
    }

    private static long benchmarkQuickSort(ArrayList<Integer> list, SortUtil.PivotStrategy pivotStrategy) {
        long totalTime = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            ArrayList<Integer> copyList = new ArrayList<>(list);
            long startTime = System.nanoTime();
            SortUtil.quicksort(copyList, Comparator.naturalOrder(), pivotStrategy);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return totalTime / ITERATIONS;
    }
}

