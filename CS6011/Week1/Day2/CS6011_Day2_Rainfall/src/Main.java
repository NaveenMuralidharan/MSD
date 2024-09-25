import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        RainData rainData = new RainData("rainfall_data.txt");
        rainData.compute();
        rainData.writeResults("rainfall_results.txt");
    }
}