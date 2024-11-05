package assign01;


import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * Represents a grayscale (black and white) image as a 2D array of "pixel" brightnesses
 * 255 is "white" 127 is "gray" 0 is "black" with intermediate values in between
 * Author: Ben Jones and ***STUDENT FILL YOUR NAME IN***
 */
public class GrayscaleImage {
    private double[][] imageData; // the actual image data


    /**
     * Initialize an image from a 2D array of doubles
     * This constructor creates a copy of the input array
     * @param data initial pixel values
     * @throws IllegalArgumentException if the input array is empty or "jagged" meaning not all rows are the same length
     */
    public GrayscaleImage(double[][] data){
        if(data.length == 0 || data[0].length == 0){
            throw new IllegalArgumentException("Image is empty");
        }

        imageData = new double[data.length][data[0].length];
        for(var row = 0; row < imageData.length; row++){
            if(data[row].length != imageData[row].length){
                throw new IllegalArgumentException("All rows must have the same length");
            }
            for(var col = 0; col < imageData[row].length; col++){
                imageData[row][col] = data[row][col];
            }
        }
    }

    /**
     * Fetches an image from the specified URL and converts it to grayscale
     * Uses the AWT Graphics2D class to do the conversion, so it may add
     * an item to your dock/menu bar as if you're loading a GUI program
     * @param url where to download the image
     * @throws IOException if the image can't be downloaded for some reason
     */
    public GrayscaleImage(URL url) throws IOException {
        var inputImage = ImageIO.read(url);
        //convert input image to grayscale
        //based on (https://stackoverflow.com/questions/6881578/how-to-convert-between-color-models)
        var grayImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d= grayImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, null);
        g2d.dispose();
        imageData = new double[grayImage.getHeight()][grayImage.getWidth()];

        //raster is basically a width x height x 1 3-dimensional array
        var grayRaster = grayImage.getRaster();
        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){
                //getSample parameters are x (our column) and y (our row), so they're "backwards"
                imageData[row][col] = grayRaster.getSampleDouble(col, row, 0);
            }
        }
    }

    public void savePNG(File filename) throws IOException {
        var outputImage = new BufferedImage(imageData[0].length, imageData.length, BufferedImage.TYPE_BYTE_GRAY);
        var raster = outputImage.getRaster();
        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){
                raster.setSample(col, row, 0, imageData[row][col]);
            }
        }
        ImageIO.write(outputImage, "png", filename);
    }

    ///Methods to be filled in by students below

    /**
     * Get the pixel brightness value at the specified coordinates
     * (0,0) is the top left corner of the image, (width -1, height -1) is the bottom right corner
     * @param x horizontal position, increases left to right
     * @param y vertical position, **increases top to bottom**
     * @return the brightness value at the specified coordinates
     * @throws IllegalArgumentException if x, y are not within the image width/height
     */
    public double getPixel(int x, int y){
        //ensure x is within num of columns
        if(x < 0 || y < 0){
            throw new IllegalArgumentException();
        }
        if(y >= imageData.length || x >= imageData[0].length){
            throw new IllegalArgumentException();
        }
        return imageData[y][x];


    }

    /**
     * Two images are equal if they have the same size and each corresponding pixel
     * in the two images is exactly equal
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof GrayscaleImage)){
            return false;
        }

        GrayscaleImage otherImage = (GrayscaleImage)other;
        //check if length and width is same
        if(this.imageData.length != otherImage.imageData.length || this.imageData[0].length != otherImage.imageData[0].length){
            return false;
        }
        // precision threshold
        double epsilon = 0.001;
        //iterate through each imageData and check if pixel values match
        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){

                    if(Math.abs(this.imageData[row][col] - otherImage.imageData[row][col]) > 0.001){
                        return false;
                    }

            }
        }
        return  true;

    }


    /**
     * Computes the average of all values in image data
     * @return the average of the imageData array
     */
    public double averageBrightness(){
        double sum = 0;
        double count = 0;
        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){
                sum += this.imageData[row][col];
                count += 1;
            }
        }
        return sum / count;

    }

    /**
     * Return a new GrayScale image where the average new average brightness is 127
     * To do this, uniformly scale each pixel (ie, multiply each imageData entry by the same value)
     * Due to rounding, the new average brightness will not be 127 exactly, but should be very close
     * The original image should not be modified
     * @return a GrayScale image with pixel data uniformly rescaled so that its averageBrightness() is 127
     */
    public GrayscaleImage normalized(){
        double avgBrightness = 127/this.averageBrightness();
        GrayscaleImage normalizedImage = new GrayscaleImage(imageData);

        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){
                normalizedImage.imageData[col][row] = this.imageData[col][row] * avgBrightness;
            }
        }
        return normalizedImage;
    }


    /**
     * Returns a new grayscale image that has been "mirrored" across the y-axis
     * In other words, each row of the image should be reversed
     * The original image should be unchanged
     * @return a new GrayscaleImage that is a mirrored version of the this
     */
    public GrayscaleImage mirrored(){

        GrayscaleImage mirrorImage = new GrayscaleImage(new double[this.imageData.length][this.imageData[0].length]);
        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){
                mirrorImage.imageData[row][col] = this.imageData[row][this.imageData[0].length-1-col];
            }
        }
        return mirrorImage;
    }

    /**
     * Returns a new GrayscaleImage of size width x height, containing the part of `this`
     * from startRow -> startRow + height, startCol -> startCol + width
     * The original image should be unmodified
     * @param startRow
     * @param startCol
     * @param width
     * @param height
     * @return A new GrayscaleImage containing the sub-image in the specified rectangle
     * @throws IllegalArgumentException if the specified rectangle goes outside the bounds of the original image
     */
    public GrayscaleImage cropped(int startRow, int startCol, int width, int height){

        if((startRow + height) > this.imageData.length || (startCol + width) > this.imageData[0].length){
            throw new IllegalArgumentException();
        }

        GrayscaleImage croppedImage = new GrayscaleImage(new double[height][width]);

        for(int row = 0; row < height; row++){
            for(int col = 0; col < width; col++){
                croppedImage.imageData[row][col] = this.imageData[startRow+row][startCol+col];
            }
        }

        return croppedImage;
//        return new GrayscaleImage(new double[][]{{4}});

    }

    /**
     * Returns a new "centered" square image (new width == new height)
     * For example, if the width is 20 pixels greater than the height,
     * this should return a height x height image, with 10 pixels removed from the left and right
     * edges of the image
     * If the number of pixels to be removed is odd, remove 1 fewer pixel from the left or top part
     * (note this convention should be SIMPLER/EASIER to implement than the alternative)
     * The original image should not be changed
     * @return a new, square, GrayscaleImage
     */
    public GrayscaleImage squarified(){

        //check if already a square and return it
        if(this.imageData.length == this.imageData[0].length){
            return new GrayscaleImage(this.imageData);
        } else {
            int centeredSquareSize;
            int startRow = 0;
            int startCol = 0;

            //if width is greater than height
            if(this.imageData.length < this.imageData[0].length){
                //crop excess from width
                centeredSquareSize = this.imageData.length;
                startCol = (this.imageData[0].length - this.imageData.length) / 2;
            } else {
                //if height is greater than width
                //crop excess height
                centeredSquareSize = this.imageData[0].length;
                startRow = (this.imageData[0].length - this.imageData.length) / 2;
            }

            //crop the square and return it
            return this.cropped(startRow, startCol, centeredSquareSize, centeredSquareSize);

        }


    }


}
