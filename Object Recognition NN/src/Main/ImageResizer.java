/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Owner
 */

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
 
/**
 * This program demonstrates how to resize an image.
 *
 * @author www.codejava.net
 *
 */
public class ImageResizer {
 
    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    
    public static void resize(String imagePath, int size) throws IOException {
        // reads input image
        File inputFile = new File(imagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(size,
                size, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, size, size, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = imagePath.substring(imagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(imagePath));
    }
 
//    /**
//     * Resizes an image by a percentage of original size (proportional).
//     * @param inputImagePath Path of the original image
//     * @param outputImagePath Path to save the resized image
//     * @param percent a double number specifies percentage of the output image
//     * over the input image.
//     * @throws IOException
//     */
//    public static void resize(String inputImagePath,
//            String outputImagePath, double percent) throws IOException {
//        File inputFile = new File(inputImagePath);
//        BufferedImage inputImage = ImageIO.read(inputFile);
//        int scaledWidth = (int) (inputImage.getWidth() * percent);
//        int scaledHeight = (int) (inputImage.getHeight() * percent);
//        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
//    }

    public static void resizeImage(String s) {
        String imagePath = s;
        int size = 512;
 
        try {
            // resize to a fixed width (not proportional)
            ImageResizer.resize(imagePath, size);
// 
//            // resize smaller by 50%
//            double percent = 0.5;
//            ImageResizer.resize(inputImagePath, outputImagePath2, percent);
// 
//            // resize bigger by 50%
//            percent = 1.5;
//            ImageResizer.resize(inputImagePath, outputImagePath3, percent);
 
        } catch (IOException ex) {
            System.out.println("Error resizing the image.");
            ex.printStackTrace();
        }
    }
 
}
