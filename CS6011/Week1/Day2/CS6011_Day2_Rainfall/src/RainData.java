import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class RainData {

    Scanner fileReader;
    ArrayList<String> fileWords = new ArrayList<>();
    ArrayList<String> stats = new ArrayList<>();
    double[] rainInches = new double[12];
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    double totalRain = 0.0;
    double totalAvgRain = 0.0;

    public RainData(String fileName) throws FileNotFoundException {

        fileReader = new Scanner( new FileInputStream(fileName) );
        while(fileReader.hasNextLine()){
            String word = fileReader.nextLine();
            fileWords.add(word);
        }
        
    }

    public void compute(){
        int count = (fileWords.size()-2)/12;

        for(int i=1; i<fileWords.size()-1; i++){
            String word = fileWords.get(i);
            int firstSpace = word.indexOf(" ");
            String month = word.substring(0, firstSpace);
            String remaining = word.substring(firstSpace+1);
            int secondSpace = remaining.indexOf(" ");
            String rainFall = remaining.substring(secondSpace+1);
            double rain = Double.parseDouble(rainFall);

            for(int j=0; j<months.length; j++){
                if(month.equals(months[j])){
                    rainInches[j] += rain;
                }
            }
        }
        for(int i=0; i<rainInches.length; i++){
            rainInches[i] /= count;
            totalRain += rainInches[i];
        }
        totalAvgRain = totalRain / 12;

    }

    public void writeResults(String outputFile) throws FileNotFoundException {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        PrintWriter pw = new PrintWriter(new FileOutputStream(outputFile));

        pw.println(fileWords.get(0)+ " Rainfall Statistics: ");
        pw.println("The overall average rainfall amount is " + numberFormat.format(totalAvgRain));


        for(int i=0; i<months.length; i++){
            String rain = numberFormat.format(rainInches[i]);
            pw.println("The average rainfall amount for " + months[i] + " is " + rain + " inches.");
        }

        pw.close();
    }

}
