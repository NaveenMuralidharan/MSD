import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {


        Fraction f1 = new Fraction();
        Fraction f2 = new Fraction(-8,16);
        Fraction f3 = new Fraction(-16,-24);
        Fraction f4 = new Fraction(8,-24);

        System.out.println("f1 "+f1.toString());
        System.out.println("f2 "+f2.toString());
        System.out.println("f3 "+f3.toString());
        System.out.println("f4 "+f4.toString());

        System.out.println(f1.plus(f2));

        System.out.println(f3.minus(f4));

        System.out.println(f3.times(f4));

        System.out.println(f3.dividedBy(f4));

        System.out.println(f3.reciprocal());

        System.out.println(f3.toDouble());

        ArrayList<Fraction> fractions = new ArrayList<>();
        fractions.add(new Fraction(1,8));
        fractions.add(new Fraction(1,4));
        fractions.add(new Fraction(1,2));
        fractions.add(new Fraction(1,6));

        Collections.sort(fractions);
        System.out.println(fractions);
    }
}