/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author IsaacMRudnick
 */
/*

Example 5.1

You can reset maximum heap size when running the program if you run out of memory
for large images, as follows:

java -Xmx<new size>m <program file name>

where <new size> is an integer.  Example:

java -Xmx128m TestDraw

allows up to 128 megabytes of heap space.
*/

import images.APImage;
import images.Pixel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FRNN{
    
   public static APImage theImage = new APImage("placeholder.jpg");
   public static int predIsFace;
   public static int outIsFace;
   //Assuming 4 hidden layers with 256 nodes each, 2 outputs, 
   public static double [][] weights = new double [65536][65536];
   
   public static void main(String[]args){
        Scanner reader = new Scanner(System.in);
        
        trainNN();
   }

   public static void getWeights () {
       try {
           File weightsFile = new File("weights.txt");
           Scanner myReader = new Scanner(weightsFile);
           while (myReader.hasNextLine()) {
               for (int x = 0; x < 65536; x++) {
                for (int y = 0; y < 65536; y++) {
                    double data = (double) myReader.nextLine();
                    weights[x][y] = data;
                }
               }
           }
           myReader.close();
       } catch (FileNotFoundException e) {
           System.out.println("An error occurred.");
           e.printStackTrace();
       }
   }
   
   public static void setWeights () {
       try {
           File weightsFile = new File("weights.txt");
           Scanner myReader = new Scanner(weightsFile);
           while (myReader.hasNextLine()) {
               for (int x = 0; x < 65536; x++) {
                for (int y = 0; y < 65536; y++) {
                    double data = (double) myReader.nextLine();
                    weights[x][y] = data;
                }
               }
           }
           myReader.close();
       } catch (FileNotFoundException e) {
           System.out.println("An error occurred.");
           e.printStackTrace();
       }
   }
   
      public static void newWeights () {
       try {
           File weightsFile = new File("weights.txt");
           Scanner myReader = new Scanner(weightsFile);
           while (myReader.hasNextLine()) {
               for (int x = 0; x < 65536; x++) {
                for (int y = 0; y < 65536; y++) {
                    double data = (double) myReader.nextLine();
                    weights[x][y] = data;
                }
               }
           }
           myReader.close();
       } catch (FileNotFoundException e) {
           System.out.println("An error occurred.");
           e.printStackTrace();
       }
   }
   
   public static void getImage () {
       String fileName;
        if (Math.random()<0.5){
           predIsFace = 0;
           //Choose random image from non-face folder and set placeholder to image name.
           fileName = "placeholder.jpg";
        }else{
           predIsFace = 1;
           //Choose random image from face folder and set placeholder to image name.
           fileName = "placeholder.jpg";
        }
       
       theImage = new APImage(fileName);
   }
   
   public static void readImage(){
      Scanner reader = new Scanner(System.in);
      
      //initialize values
      int width = theImage.getWidth();
      int height = theImage.getHeight();
      int [][] r = new int [height][width];
      int [][] g = new int [height][width];
      int [][] b = new int [height][width];

      // Visit all pixels except for the left column and 
      // bottom row
      for (int x = 0; x < width; x++) {
         for (int y = 1; y < height; y++){
            // Obtain info for a pixel and add it to pixel color arrays
            // at a position corresponding to the y and x values respectively
            Pixel pixel = theImage.getPixel(x, y);
            r[x][y] = pixel.getRed();
            g[x][y] = pixel.getGreen();
            b[x][y] = pixel.getBlue();
            
            }
      }
      isFace(r, g, b);
   }
   
   public static void isFace(int [][] r, int [][] g, int [][] b) {
       //Access NN weight values in text document
       //Write NN code here with each pixel's rgb values in their respective array
       //Replace 1 with output.
       outIsFace = 1;
   }
   
   public static void backpropagate(int [][] weights){
       double cost = 2*(predIsFace-outIsFace);
       
       //The for loop is to get the indexes of each weight
       //                                                   IMPORTANT: Figure out how to get x and y lengths of weights
       for(int x = 0; x < weights.length; x++){
        for(int y = 0; y < weights.length; y++){
        //backpropagate using backpropagateWeight
        }
       }
   }
   
   public static void backpropagateWeight(int [] weights, int currentIndex, double cost){
       //backpropagate for 1 weight with respect to each weight in a refactored way.
   }
   
   public static void trainNN() {
       getImage();
       int width = theImage.getWidth();
       int height = theImage.getHeight();
       int [][] weights = new int [width][height];
       readImage();
       backpropagate(weights);
   }
   
   public static void useNN() {
       theImage = new APImage("placeholder.jpg");
       readImage();
       if (outIsFace < 0.5)
           System.out.println("The given image does not have a face in it");
       else
           System.out.println("The given image does not have a face in it");
   }
}


