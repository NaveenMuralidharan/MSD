public class HelloThreads {


    public static void main(String[] args) throws InterruptedException {

//        sayHello();
        badSum();
        System.out.println("computed answer "+answer);
        correctSum();
    }

    public static void sayHello() throws InterruptedException {
        CounterRunnable counter = new CounterRunnable();
        for(int i=0; i<10; i++){
            Thread t = new Thread(counter);
            t.start();
            t.join();
        }

    }

    static int answer;

    public static void badSum() throws InterruptedException {
        answer = 0;
        int maxValue = 100;
        int numThreads = 10;
        for(int i=0; i<numThreads; i++){
            int min = i*maxValue/numThreads;
            int max = Math.min((i+1)*maxValue/numThreads, maxValue);
            MaxRunnable maxCounter = new MaxRunnable(min, max);
            Thread t = new Thread(maxCounter);
            t.start();
//            t.join();
        }

    }

    public static void correctSum(){
        int maxValue = 100;
        System.out.println("Correct sum "+maxValue * (maxValue -1)/2);
    }

}
//What happens? Do all the threads run in order?
//The threads do run in order since the join()method ensures the main thread will wait for t to finish executing before starting the next t.
//Run your program a couple of times - does the same thread always print the 1st hello? The last hello?
//The same thread always prints the first hello and same one prints last hello regardless of how many times I run the code.
//What's happening? Why do the results seem random?
//I get the same sum, 799980000 when using one thread or multiple, because the join statement ensures one thread finishes before starting another
//if I run without the join statement I get random sums because the answer static variable's value might be impacted by one thread while another is still adding i to it, leading to random results.
//when maxValue is changed to 100 and run without joining threads, the frequency of random answers decreases, this is because
//the less time taken to iterate through 10 indices in run()function reduce number of race conditions impacting the final answer.
