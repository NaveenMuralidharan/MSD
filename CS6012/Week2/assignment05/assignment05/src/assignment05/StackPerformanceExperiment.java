package assignment05;

import java.util.*;

public class StackPerformanceExperiment {
    public static void main(String[] args) {
        // Different sizes for N
        int[] problemSizes = {10000, 50000, 100000, 200000};

        // Measure the time for each operation
        for (int N : problemSizes) {
            // Measure push time for ArrayStack
            ArrayStack<Integer> arrayStack = new ArrayStack<>();
            long arrayPushTime = measurePushTime(arrayStack, N);
            System.out.println("ArrayStack Push Time (N=" + N + "): " + arrayPushTime + " ms");

            // Measure push time for LinkedListStack
            LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
            long linkedPushTime = measurePushTime(linkedListStack, N);
            System.out.println("LinkedListStack Push Time (N=" + N + "): " + linkedPushTime + " ms");

            // Measure pop time for ArrayStack
            long arrayPopTime = measurePopTime(arrayStack, N);
            System.out.println("ArrayStack Pop Time (N=" + N + "): " + arrayPopTime + " ms");

            // Measure pop time for LinkedListStack
            long linkedPopTime = measurePopTime(linkedListStack, N);
            System.out.println("LinkedListStack Pop Time (N=" + N + "): " + linkedPopTime + " ms");

            // Measure peek time for ArrayStack
            long arrayPeekTime = measurePeekTime(arrayStack, N);
            System.out.println("ArrayStack Peek Time (N=" + N + "): " + arrayPeekTime + " ms");

            // Measure peek time for LinkedListStack
            long linkedPeekTime = measurePeekTime(linkedListStack, N);
            System.out.println("LinkedListStack Peek Time (N=" + N + "): " + linkedPeekTime + " ms");
        }
    }

    // Measure the time it takes to push N elements to the stack
    private static long measurePushTime(Stack<Integer> stack, int N) {
        long startTime = System.nanoTime();
        for (int i = 0; i < N; i++) {
            stack.push(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime); // Convert to milliseconds
    }

    // Measure the time it takes to pop N elements from the stack
    private static long measurePopTime(Stack<Integer> stack, int N) {
        // First, push N elements to the stack
        for (int i = 0; i < N; i++) {
            stack.push(i);
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < N; i++) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime); // Convert to milliseconds
    }

    // Measure the time it takes to peek the top element N times
    private static long measurePeekTime(Stack<Integer> stack, int N) {
        // First, push N elements to the stack
        for (int i = 0; i < N; i++) {
            stack.push(i);
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < N; i++) {
            stack.peek();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime); // Convert to milliseconds
    }
}

