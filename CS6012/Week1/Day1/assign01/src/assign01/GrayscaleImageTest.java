package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrayscaleImageTest {

    private GrayscaleImage smallSquare;
    private GrayscaleImage smallWide;
    private GrayscaleImage invalidSquare;

    @BeforeEach
    void setUp() {
        smallSquare = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        smallWide = new GrayscaleImage(new double[][]{{1,2,3},{4,5,6}});
//        invalidSquare = new GrayscaleImage(new double[][]{{1,2,5}, {2,5,6,7}, {4,5,6,8,9}});
    }

    @Test
    void testInvalidSquare(){
        assertThrows(IllegalArgumentException.class, ()->{
            new GrayscaleImage(new double[][]{{1,2,5}, {2,5,6,7}, {4,5,6,8,9}});
        });
    }

    @Test
    void testGetPixel(){
        assertEquals(smallSquare.getPixel(0,0), 1);
        assertEquals(smallSquare.getPixel(1,0), 2);
        assertEquals(smallSquare.getPixel(0,1), 3);
        assertEquals(smallSquare.getPixel(1,1), 4);
        //negative index error
        assertThrows(IllegalArgumentException.class, ()->{
            smallSquare.getPixel(-1,2);
        });
        //out of bounds index error
        assertThrows(IllegalArgumentException.class, ()->{
            smallSquare.getPixel(3,5);
        });
    }

    @Test
    void testEquals() {
        assertEquals(smallSquare, smallSquare);
        var equivalent = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        assertEquals(smallSquare, equivalent);
        assertFalse(smallSquare.equals(smallWide));
        //test precision
        var test1 = new GrayscaleImage(new double[][]{{1.002,2,3},{3,4,7}});
        var test2 = new GrayscaleImage(new double[][]{{1.004,2,3},{3,4,7}});
        assertFalse(test1.equals(test2));
        var test3 = new GrayscaleImage(new double[][]{{1,2.002,3},{3,4,7}});
        var test4 = new GrayscaleImage(new double[][]{{1,2.001,3},{3,4,7}});
        assertEquals(test3, test4);
    }

    @Test
    void averageBrightness() {
        assertEquals(smallSquare.averageBrightness(), 2.5, 2.5*.001);
        var bigZero = new GrayscaleImage(new double[1000][1000]);
        assertEquals(bigZero.averageBrightness(), 0);
        var test1 = new GrayscaleImage(new double[][]{{1,2,3},{3,4,7}});
        assertEquals(test1.averageBrightness(), 3.33, 3.33+0.001);
    }

    @Test
    void normalized() {
        var smallNorm = smallSquare.normalized();
        assertEquals(smallNorm.averageBrightness(), 127, 127*.001);
        var scale = 127/2.5;
        var expectedNorm = new GrayscaleImage(new double[][]{{scale, 2*scale},{3*scale, 4*scale}});
        for(var row = 0; row < 2; row++){
            for(var col = 0; col < 2; col++){
                assertEquals(smallNorm.getPixel(col, row), expectedNorm.getPixel(col, row),
                        expectedNorm.getPixel(col, row)*.001,
                        "pixel at row: " + row + " col: " + col + " incorrect");
            }
        }
    }

    @Test
    void mirrored() {
        var expected = new GrayscaleImage(new double[][]{{2,1},{4,3}});
        assertEquals(smallSquare.mirrored(), expected);
        var test1 = new GrayscaleImage(new double[][]{{1,2,3},{3,4,7}});
        var test2 = new GrayscaleImage(new double[][]{{3,2,2},{7,4,3}});
        assertFalse(test1.mirrored().equals(test2));
    }

    @Test
    void cropped() {
        var cropped = smallSquare.cropped(1,1,1,1);
        assertEquals(cropped, new GrayscaleImage(new double[][]{{4}}));
        var test1 = new GrayscaleImage(new double[][]{{1,2,3},{3,4,7}});
        var test1Cropped = test1.cropped(0,1,2,2);
        assertEquals(test1Cropped, new GrayscaleImage(new double[][]{{2,3}, {4,7}}));
        var test2 = new GrayscaleImage(new double[][]{{1,2,3},{3,4,7}});
        var test2Cropped = test1.cropped(0,1,2,2);
        assertFalse(test1Cropped.equals(new GrayscaleImage(new double[][]{{4,3}, {4,7}})));

    }

    @Test
    void squarified() {
        var squared = smallWide.squarified();
        var expected = new GrayscaleImage(new double[][]{{1,2},{4,5}});
        assertEquals(squared, expected);
        var test1 = new GrayscaleImage(new double[][]{{1,2,3},{3,4,7}});
        var test1Squarified = test1.squarified();
        var test1Expected = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        assertEquals(test1Squarified, test1Expected);
        var test1Unexpected = new GrayscaleImage(new double[][]{{2,3},{4,7}});
        assertFalse(test1Squarified.equals(test1Unexpected));
    }

    @Test
    void testGetPixelThrowsOnNegativeX(){
        assertThrows(IllegalArgumentException.class, () -> { smallSquare.getPixel(-1,0);});
    }
}
