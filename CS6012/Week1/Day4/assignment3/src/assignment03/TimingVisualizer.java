package assignment03;

import java.util.ArrayList;

public class TimingVisualizer extends TimerTemplate {

    public BinarySearchSet<Integer> ints = new BinarySearchSet<>();

    public TimingVisualizer(int[] problemSizes, int timesToLoop){
        super(problemSizes, timesToLoop);
    }

    @Override
    protected void setup(int n) {
        for(int i=0; i<n; i++){
            ints.add(i);
        }
    }

    @Override
    protected void timingIteration(int n) {
        ints.contains(2134);
    }

    @Override
    protected void compensationIteration(int n) {

    }

    public static void main(String[] args){

        ArrayList<Integer> ns = new ArrayList<>();
        for(int n = 10; n < 21; n++){
            ns.add((int) Math.pow(2,n));
        }

        //convert to int[]
        int[] problemSizes = new int[ns.size()];
        for(int i = 0; i < problemSizes.length; i++){
            problemSizes[i] = ns.get(i);
        }

        var timer = new TimingVisualizer(problemSizes, 1000000);
        var results = timer.run();
        System.out.println("n, time");
        for(var result: results){
            System.out.println(result.n() + ", " + result.avgNanoSecs());
        }
    }


}
