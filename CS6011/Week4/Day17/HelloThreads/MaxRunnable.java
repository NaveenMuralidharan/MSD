public class MaxRunnable implements Runnable{
    int min;
    int max;
    public MaxRunnable(int min, int max) {
        this.min = min;
        this.max = max;

    }


    @Override
    public void run() {
        for(int i=min; i<max; i++){
            HelloThreads.answer += i;
        }
    }
}
