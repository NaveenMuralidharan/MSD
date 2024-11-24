package assignment05;

import java.io.FileWriter;
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
        testArrayStack.push(7777);
//        testArrayStack.pop();
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

    public static void main(String[] args){

        ArrayList<Integer> ns = new ArrayList<>();
        for(int n = 4; n < 21; n++){
            ns.add((int)Math.pow(2,n));
        }




        //convert to int[]
        int[] problemSizes = new int[ns.size()];
        for(int i = 0; i < problemSizes.length; i++){
            problemSizes[i] = ns.get(i);
        }

        var timer = new PushPopPeekTimer(problemSizes, 10);
        var results = timer.run();
        try (FileWriter writer = new FileWriter("results.csv")) {
            writer.write("n, time \n");
            System.out.println("n, time");
            for (var result : results) {
                writer.write(result.n() + ", " + result.avgNanoSecs()+ "\n");
                System.out.println(result.n() + ", " + result.avgNanoSecs());
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
