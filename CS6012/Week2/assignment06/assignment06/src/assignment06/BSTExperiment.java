package assignment06;

import java.util.*;

public class BSTExperiment {
    public static void main(String[] args) {
        int[] problemSizes = {10000, 20000, 50000, 100000, 200000};

        for (int N : problemSizes) {
            // Step 1: Create and populate the BST with sorted order
            BinarySearchTree<Integer> sortedBST = new BinarySearchTree<>();
            for (int i = 1; i <= N; i++) {
                sortedBST.add(i);
            }
            long sortedTime = measureContainsTime(sortedBST, N);
            System.out.println("Sorted Order - N = " + N + ", Time = " + sortedTime + " ns");

            // Step 2: Create and populate the BST with random order
            BinarySearchTree<Integer> randomBST = new BinarySearchTree<>();
            List<Integer> randomItems = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                randomItems.add(i);
            }
            Collections.shuffle(randomItems);
            for (int item : randomItems) {
                randomBST.add(item);
            }
            long randomTime = measureContainsTime(randomBST, N);
            System.out.println("Random Order - N = " + N + ", Time = " + randomTime + " ns");
        }
    }

    private static long measureContainsTime(BinarySearchTree<Integer> bst, int N) {
        long startTime = System.nanoTime();
        for (int i = 1; i <= N; i++) {
            bst.contains(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime);
    }
}

