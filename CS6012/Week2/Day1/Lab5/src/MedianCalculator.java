import java.util.Comparator;

public class MedianCalculator {

    public static <E extends Comparable<E>> E findMedian(E[] array){
        if(array == null || array.length == 0){
            throw new IllegalArgumentException("array cannot be empty");
        }

        java.util.Arrays.sort(array);

        int middle = array.length / 2;

        if(array.length % 2 == 0){
            return array[middle - 1];
        } else {
            return array[middle];
        }

    }

    public static <E> E findMedianWithComparator(E[]array, Comparator<E> c){
        if(array == null || array.length == 0){
            throw new IllegalArgumentException("array cannot be empty");
        }

        java.util.Arrays.sort(array, c);

        int middle = array.length / 2;

        if(array.length % 2 == 0){
            return array[middle - 1];
        } else {
            return array[middle];
        }
    }




}
