package assignment07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CollisionExperiment extends TimerTemplate {

    private List<String> stringsToAdd;
    private ChainingHashTable tableBad;
    private ChainingHashTable tableMediocre;
    private ChainingHashTable tableGood;

    public CollisionExperiment(int[] problemSizes, int timesToLoop) {
        super(problemSizes, timesToLoop);
        this.stringsToAdd = new ArrayList<>();
    }

    private void generateRandomStrings(int n) {
        // Generate n random strings, each of length n
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random rnd = new Random();

        stringsToAdd.clear();

        for (int i = 0; i < n; ++i) {
            StringBuilder salt = new StringBuilder();
            float randomSaltLength = (float) n;

            while ((float) salt.length() < randomSaltLength) {
                int index = (int) (rnd.nextFloat() * (float) SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }

            stringsToAdd.add(salt.toString());
        }
    }

    @Override
    protected void setup(int n) {
        generateRandomStrings(n);

        tableBad = new ChainingHashTable(n, new BadHashFunctor());
        tableMediocre = new ChainingHashTable(n, new MediocreHashFunctor());
        tableGood = new ChainingHashTable(n, new GoodHashFunctor());
    }

    @Override
    protected void timingIteration(int n) {
        for (String str : stringsToAdd) {
            tableBad.add(str);
            tableMediocre.add(str);
            tableGood.add(str);
        }
    }

    @Override
    protected void compensationIteration(int n) {
        // Nothing to do in compensation for this experiment
    }

    public List<CollisionResult> runExperiment() {
        List<CollisionResult> results = new ArrayList<>();

        Result[] resultsBad = run();
        Result[] resultsMediocre = run();
        Result[] resultsGood = run();

        for (int i = 0; i < resultsBad.length; i++) {
            int collisionsBad = tableBad.getCollisions();
            int collisionsMediocre = tableMediocre.getCollisions();
            int collisionsGood = tableGood.getCollisions();

            results.add(new CollisionResult(
                    resultsBad[i].n(), collisionsBad, collisionsMediocre, collisionsGood
            ));
        }

        return results;
    }

    public record CollisionResult(int problemSize, int collisionsBad, int collisionsMediocre, int collisionsGood) {}

    public static void main(String[] args) {
        int[] problemSizes = {100, 500, 1000, 5000, 10000};
        int timesToLoop = 5;

        CollisionExperiment experiment = new CollisionExperiment(problemSizes, timesToLoop);

        List<CollisionResult> results = experiment.runExperiment();


        for (CollisionResult result : results) {
            System.out.println("Problem Size: " + result.problemSize
                    + " | Bad Hash Collisions: " + result.collisionsBad
                    + " | Mediocre Hash Collisions: " + result.collisionsMediocre
                    + " | Good Hash Collisions: " + result.collisionsGood);
        }

    }
}

