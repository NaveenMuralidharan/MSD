import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
class MedianCalculatorTest {

    @Test
    void findMedianTest() {
        Integer[] numbers = { 1, 3, 2, 5, 4 };
        assertEquals(MedianCalculator.findMedian(numbers), 3);
        String[] words = { "apple", "orange", "banana", "grape", "pear" };
        assertEquals(MedianCalculator.findMedian(words), "grape");
        System.out.println(MedianCalculator.findMedian(new String[]{"bird", "cat", "dog", "ant"}));
    }

    @Test
    void findMedianWithComparatorTest() {
        Comparator<Integer> customComparator = (a,b) -> Integer.compare(b,a);
        Integer[] numbers = { 1, 3, 2, 5, 4 };
        assertEquals(MedianCalculator.findMedianWithComparator(numbers, customComparator), 3);

    }


}