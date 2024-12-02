package assignment07;

import java.io.FileWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class HashFunctorTimingExperiments extends TimerTemplate {
    ArrayList<String> stringsToAdd = new ArrayList();
    HashFunctor testFunctor = new BadHashFunctor();
    ChainingHashTable compensationTable;
    ChainingHashTable testTable;

    public HashFunctorTimingExperiments(int[] problemSizes, int timesToLoop) {
        super(problemSizes, timesToLoop);
    }

    protected void setup(int n) {
        for(int i = 0; i < n; ++i) {
            String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            float randomSaltLength = (float)n;

            while((float)salt.length() < randomSaltLength) {
                int index = (int)(rnd.nextFloat() * (float)SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }

            String saltStr = salt.toString();
            this.stringsToAdd.add(saltStr);
        }

        this.compensationTable = new ChainingHashTable(n * 2, this.testFunctor);
        this.compensationTable.addAll(this.stringsToAdd);
        this.testTable = new ChainingHashTable(n * 2, this.testFunctor);
    }

    protected void timingIteration(int n) {
        this.testTable.addAll(this.stringsToAdd);
        this.testTable.removeAll(this.stringsToAdd);
    }

    protected void compensationIteration(int n) {
        this.compensationTable.containsAll(this.stringsToAdd);
    }

    public static void main(String[] args) {
        ArrayList<Integer> ns = new ArrayList();
        ns.add(10);
        ns.add(10);
        ns.add(10);

        for(int n = 1; n <= 10001; n += 1000) {
            ns.add(n);
        }

        int[] problemSizes = new int[ns.size()];

        for(int i = 0; i < problemSizes.length; ++i) {
            problemSizes[i] = (Integer)ns.get(i);
        }

        HashFunctorTimingExperiments timer = new HashFunctorTimingExperiments(problemSizes, 10);
        TimerTemplate.Result[] results = timer.run();

        try {
            FileWriter writer = new FileWriter("results.csv");

            try {
                writer.write("n, time \n");
                System.out.println("n, time");
                TimerTemplate.Result[] var6 = results;
                int var7 = results.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    TimerTemplate.Result result = var6[var8];
                    int var10001 = result.n();
                    writer.write("" + var10001 + ", " + result.avgNanoSecs() + "\n");
                    PrintStream var10000 = System.out;
                    var10001 = result.n();
                    var10000.println("" + var10001 + ", " + result.avgNanoSecs());
                }
            } catch (Throwable var11) {
                try {
                    writer.close();
                } catch (Throwable var10) {
                    var11.addSuppressed(var10);
                }

                throw var11;
            }

            writer.close();
        } catch (Exception var12) {
            Exception exception = var12;
            System.out.println(exception.getMessage());
        }

    }
}
