public class CounterRunnable implements Runnable{


    @Override
    public void run() {
        for(int i=1; i<=100; i++){
            if(i % 10 == 0){
                System.out.println("hello number " + i + "from thread number "+Thread.currentThread().threadId());
            } else {
                System.out.print("hello number " + i + "from thread number "+Thread.currentThread().threadId());
            }
        }
    }
}
