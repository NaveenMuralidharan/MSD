import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Prints "hello world".
        System.out.println("Hello world!");

        //Create an array of 10 integers.
        //Fill the array with random values.
        //Print out each value in the array.
        //Print out the sum of the numbers in the array.
        Random rand = new Random();
        int[] arr = new int[10];

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(1000);
            System.out.println(arr[i]);
            sum += arr[i];
        }
        System.out.println("sum is " + sum);

        //Ask the user to input their name and age.
        //Display whether or not they are old enough to vote, and which
        // generation they belong to (iGen, Millennial, Gen X, Boomer,
        // Greatest Generation).
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        int age = 0;
        boolean isValidAge = false;

        do {
            System.out.println("Enter your age: ");
            try {
                age = Integer.parseInt(scanner.nextLine());
                if (age <= 0 || age > 200) {
                    System.out.println("You did not enter a valid age");
                } else {
                    isValidAge = true;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("You did not enter a valid age");
            }

        } while(isValidAge == false);

        System.out.println(age);
        if(age > 18)
            System.out.println(name+" is old enough to vote");
        else
            System.out.println(name+" is not old enough to vote");

        if(age < 21)
            System.out.println(name+" is an iGen");
        else if(age < 41)
            System.out.println(name+" is a millenial");
        else if(age < 61)
            System.out.println(name+" is of the generation X");
        else if(age < 81)
            System.out.println(name+" is a baby boomer");
        else
            System.out.println(name+" is of the greatest generation");
    }
}