import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

class FractionTest {
    
    @Test
    public void testPlus() {
        Fraction f1 = new Fraction(1,3);
        Fraction f2 = new Fraction(3,3);
        Fraction f3 = f1.plus(f2);
        Assertions.assertEquals(f3.toString(), "4/3");
    }

    @Test
    public void testMinus() {
        Fraction f1 = new Fraction(7,3);
        Fraction f2 = new Fraction(-3,3);
        Fraction f3 = f1.minus(f2);
        Assertions.assertEquals(f3.toString(), "10/3");
    }

    @Test
    public void testTimes() {
        Fraction f1 = new Fraction(7,3);
        Fraction f2 = new Fraction(-3,3);
        Fraction f3 = f1.times(f2);
        Assertions.assertEquals(f3.toString(), "-7/3");
    }

    @Test
    public void testDividedBy() {
        Fraction f1 = new Fraction(7,3);
        Fraction f2 = new Fraction(5,3);
        Fraction f3 = f1.dividedBy(f2);
        Assertions.assertEquals(f3.toString(), "7/5");
    }

    @Test
    public void testReciprocal() {
        Fraction f1 = new Fraction(7,3);
        Fraction f2 = f1.reciprocal();
        Assertions.assertEquals(f2.toString(), "3/7");
    }

    @Test
    public void toDouble() {
        Fraction f1 = new Fraction(1,2);
        Assertions.assertEquals(f1.toDouble(), 0.5);
    }


}