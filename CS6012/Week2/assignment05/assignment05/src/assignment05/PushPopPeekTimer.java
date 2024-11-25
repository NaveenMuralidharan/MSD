package assignment05;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PushPopPeekTimer extends TimerTemplate{

    /**
     * Create a timer
     *
     * @param problemSizes array of N's to use
     * @param timesToLoop  number of times to repeat the tests
     */
    public PushPopPeekTimer(int[] problemSizes, int timesToLoop) {
        super(problemSizes, timesToLoop);
    }

    @Override
    protected void setup(int n) {
        // Generate an array of even numbers
        for (int i = 0; i < n + 1; i++) {
            testArrayStack.push(i);
//            testLinkedStack.push(i);
        }
    }

    @Override
    protected void timingIteration(int n) {
//        testArrayStack.push(7777);
        testArrayStack.pop();
//        testArrayStack.peek();

//        testLinkedStack.push(7777);
//        testLinkedStack.pop();
//        testLinkedStack.peek();

    }

    @Override
    protected void compensationIteration(int n) {

    }

    private LinkedListStack<Integer> testLinkedStack = new LinkedListStack<>();
    private ArrayStack<Integer> testArrayStack = new ArrayStack<>();

//    public static void main(String[] args){
//
//        ArrayList<Integer> ns = new ArrayList<>();
//        for(int n = 4; n < 21; n++){
//            ns.add((int)Math.pow(2,n));
//        }
//
//        //convert to int[]
//        int[] problemSizes = new int[ns.size()];
//        for(int i = 0; i < problemSizes.length; i++){
//            problemSizes[i] = ns.get(i);
//        }
//
//        var timer = new PushPopPeekTimer(problemSizes, 10);
//        var results = timer.run();
//        try (FileWriter writer = new FileWriter("results.csv")) {
//            writer.write("n, time \n");
//            System.out.println("n, time");
//            for (var result : results) {
//                writer.write(result.n() + ", " + result.avgNanoSecs()+ "\n");
//                System.out.println(result.n() + ", " + result.avgNanoSecs());
//            }
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//    }

    public static void comparePush() {
        // you spin me round baby, right round
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000);

        try (FileWriter fw = new FileWriter(new File("comparePush.csv"))) {
            fw.write("size of the array" + "," + "time of ArrayStack" + "," + "time of LinkedListStack" + "\n"); // write to file.

            // Do the experiment multiple times, and average out the results
            long totalTimeArrayStack = 0;
            long startArrayStack;
            long stopArrayStack;
            long totalTimeLinkedListStack = 0;
            long startLinkedListStack;
            long stopLinkedListStack;

            ArrayStack<Integer> arrayStack = new ArrayStack<>();
            LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
            for (int addNum = 1; addNum <= 10000; addNum++) {
                // TIME IT!
                startArrayStack = System.nanoTime();
                arrayStack.push(addNum);
                stopArrayStack = System.nanoTime();
                totalTimeArrayStack = stopArrayStack - startArrayStack;

                startLinkedListStack = System.nanoTime();
                linkedListStack.push(addNum);
                stopLinkedListStack = System.nanoTime();
                totalTimeLinkedListStack = stopLinkedListStack - startLinkedListStack;

                double ArrayStackAverageTime = totalTimeArrayStack;
                double LinkedListStackAverageTime = totalTimeLinkedListStack;

                fw.write(addNum + "," + ArrayStackAverageTime + "," + LinkedListStackAverageTime + "\n"); // write to file.

            }

        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        comparePush();
    }

}
