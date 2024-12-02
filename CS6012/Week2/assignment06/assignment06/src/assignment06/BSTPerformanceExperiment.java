package assignment06;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BSTPerformanceExperiment {
    public static void main(String[] args) {
        // Range of problem sizes (N)
        int[] problemSizes = {10000, 20000, 50000, 100000, 200000};

        // Record times for TreeSet and BinarySearchTree
        for (int N : problemSizes) {
            // Generate random items for the test
            List<Integer> randomItems = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                randomItems.add(i);
            }
            Collections.shuffle(randomItems);

            // Measure time for TreeSet insertions
            TreeSet<Integer> treeSet = new TreeSet<>();
            long treeSetInsertTime = measureInsertionTime(treeSet, randomItems);

            // Measure time for BinarySearchTree insertions
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            long bstInsertTime = measureInsertionTimeForBST(bst, randomItems);

            // Measure time for TreeSet contains method
            long treeSetContainsTime = measureContainsTimeForCollection(treeSet, randomItems);

            // Measure time for BinarySearchTree contains method
            long bstContainsTime = measureContainsTimeForBST(bst, randomItems);

            // Output the times (in milliseconds)
            System.out.println("N = " + N);
            System.out.println("TreeSet Insert Time: " + treeSetInsertTime + " ms");
            System.out.println("BST Insert Time: " + bstInsertTime + " ms");
            System.out.println("TreeSet Contains Time: " + treeSetContainsTime + " ms");
            System.out.println("BST Contains Time: " + bstContainsTime + " ms");
        }
    }

    // Measure time for inserting items into a Collection (TreeSet, BinarySearchTree, or similar)
    private static <T> long measureInsertionTime(Collection<T> collection, List<T> items) {
        long startTime = System.nanoTime();
        for (T item : items) {
            collection.add(item);
        }
        long endTime = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(endTime - startTime); // Convert to milliseconds
    }

    // Measure time for inserting items into BinarySearchTree (requires a specialized method)
    private static <T extends Comparable<? super T>> long measureInsertionTimeForBST(BinarySearchTree<T> bst, List<T> items) {
        long startTime = System.nanoTime();
        for (T item : items) {
            bst.add(item);
        }
        long endTime = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(endTime - startTime); // Convert to milliseconds
    }

    // Measure time for invoking 'contains' method on a Collection (TreeSet or BinarySearchTree)
    private static <T> long measureContainsTimeForCollection(Collection<T> collection, List<T> items) {
        long startTime = System.nanoTime();
        for (T item : items) {
            collection.contains(item);
        }
        long endTime = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(endTime - startTime); // Convert to milliseconds
    }

    // Measure time for invoking 'contains' method on a BinarySearchTree
    private static <T extends Comparable<? super T>> long measureContainsTimeForBST(BinarySearchTree<T> bst, List<T> items) {
        long startTime = System.nanoTime();
        for (T item : items) {
            bst.contains(item);
        }
        long endTime = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(endTime - startTime); // Convert to milliseconds
    }
}
