package lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiffUtilTest {
    private int[] arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8;

    @org.junit.jupiter.api.BeforeEach
    protected void setUp() throws Exception{
       arr1 = new int[0];
       arr2 = new int[] { 3, 3, 3 };
       arr3 = new int[] { 52, 4, -8, 0, -17 };
       arr4 = new int[] {1,2};
       arr5 = new int[] {0,0,0,0,0};
       arr6 = new int[] {2,3,4,5,6};
       arr7 = new int[] {2};
       arr8 = new int[] {-2,-7,-9,-8};
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

//    @org.junit.jupiter.api.Test
//    void findSmallestDiff() {
//    }
    @Test
    public void emptyArray() {
        assertEquals(-1, DiffUtil.findSmallestDiff(arr1));
    }

    @Test
    public void allArrayElementsEqual() {
        assertEquals(0, DiffUtil.findSmallestDiff(arr2));
    }

    @Test
    public void smallRandomArrayElements() {
        assertEquals(4, DiffUtil.findSmallestDiff(arr3));
    }

    @Test
    public void twoArrayElements() {
        assertEquals(1, DiffUtil.findSmallestDiff(arr4));
    }

    @Test
    public void zeroArrayElements() {
        assertEquals(0, DiffUtil.findSmallestDiff(arr5));
    }

    @Test
    public void oneDiffArrayElements() {
        assertEquals(1, DiffUtil.findSmallestDiff(arr6));
    }

    @Test
    public void oneArrayElements() {
        assertEquals(-1, DiffUtil.findSmallestDiff(arr7));
    }

    @Test
    public void negativeArrayElements() {
        assertEquals(1, DiffUtil.findSmallestDiff(arr8));
    }

}