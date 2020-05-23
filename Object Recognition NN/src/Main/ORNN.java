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
import java.io.*;
import java.util.*;

public class ORNN{
    
   public static APImage theImage = new APImage("placeholder.jpg");
   public static int predCategory;
   public static int outCategory;
   public static int error;
   public static final int NN_SIZE = 65536;
   //Assuming 4 hidden layers with 256 nodes each, 2 outputs, 
   public static double [][] weights = new double [NN_SIZE][NN_SIZE];
   
   public static void main(String[]args){
        Scanner reader = new Scanner(System.in);
        
        trainNN();
   }

   public static void getWeights () {
       try {
           File weightsFile = new File("weights.txt");
           Scanner myReader = new Scanner(weightsFile);
           while (myReader.hasNextLine()) {
               for (int x = 0; x < NN_SIZE; x++) {
                for (int y = 0; y < NN_SIZE; y++) {
                    double data = (double) Double.parseDouble(myReader.nextLine());
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
               for (int x = 0; x < NN_SIZE; x++) {
                for (int y = 0; y < NN_SIZE; y++) {
                    double data = (double) Double.parseDouble(myReader.nextLine());
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
               for (int x = 0; x < NN_SIZE; x++) {
                for (int y = 0; y < NN_SIZE; y++) {
                    double data = (double) Double.parseDouble(myReader.nextLine());
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
        int categoryNum;
        int num0s = 3;
        String category = "";
        File categoryFolder;
        
        String [] filesInCategory = new String [0];
        
        ArrayList<String> files = new ArrayList<String> ();
        int fileIndex = 0;
        String fileName = "";
        String pathway = "";
       
       //get a random category
       categoryNum = (int)(Math.random()*257 + 1);
       
       //find the number of preceding 0s in the folder name
       int temp = categoryNum;
       while (temp != 0) {
           num0s--;
           temp %= 10;
       }
       
       //add that # 0s to the category name
       for (int i = 0; i < num0s; i++) {
           category += "0";
       }
       
       //finish composing the category name
       category += categoryNum + ".*";
       
       //create a list of potential files
       categoryFolder = new File("ObjectCategories" + "\\" + category);
       filesInCategory = categoryFolder.list();
       
       //select a random file
       fileIndex = (int) (Math.random()*filesInCategory.length);
       fileName = filesInCategory[fileIndex];
       
       theImage = new APImage("ObjectCategories" + "\\" + category + "\\" + fileName);
   }
   
   public static APImage scaleImage () {
       APImage scaledImg = new APImage("placeholder.jpg");
       //scale the image to output a training file of the correct size
       return scaledImg;
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
      getCategory(r, g, b);
   }
   
   public static void getCategory(int [][] r, int [][] g, int [][] b) {
       //Access NN weight values in text document
       //Write NN code here with each pixel's rgb values in their respective array
       //Replace 1 with output.
       outCategory = 0;
   }
   
   public static void backpropagate(int [][] weights){
       
       double cost = 2*(predCategory - outCategory);
       
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
       if (outCategory < 0.5)
           System.out.println("The given image does not have a face in it");
       else
           System.out.println("The given image does not have a face in it");
   }
}


