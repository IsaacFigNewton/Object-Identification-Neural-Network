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
//import Main.ImagePrep;

public class ORNN{
    
   public static APImage theImage = new APImage("placeholder.png");
   
   public static File weightsFolder = new File("Weights\\");
   public static String [] weightList = weightsFolder.list();
   
   public static int predCategory;
   public static int outCategory;
   public static int error;
   
   public static final int NN_INPUT_SIZE = 512;
   //calculate weights for CNN kernel layer
   public static double [][] kernelWeights = new double [NN_INPUT_SIZE][NN_INPUT_SIZE];
   //calculate CNN kernel layer size
   public static final int KERNEL_LAYER_SIZE = (NN_INPUT_SIZE / 3);
   //continue with next layers once more learned in the area
   
   //set up monochrome array for image pixels
   public static int [][] m = new int [NN_INPUT_SIZE][NN_INPUT_SIZE];
   
   //                                                                           Main Class \/
   public static void main(String[]args) throws IOException{
        Scanner reader = new Scanner(System.in);
        
        //debug
        System.out.println("1");
        
        trainNN();
   }

   public static void getWeights () {
       try {
           File weightsFile = new File("weights.txt");
           Scanner myReader = new Scanner(weightsFile);
           while (myReader.hasNextLine()) {
               for (int y = 0; y < NN_INPUT_SIZE; y++) {
                for (int x = 0; x < NN_INPUT_SIZE; x++) {
                    double data = (double) Double.parseDouble(myReader.nextLine());
                    kernelWeights[x][y] = data;
                }
               }
           }
           myReader.close();
       } catch (FileNotFoundException e) {
           System.out.println("Couldn't find the file");
           e.printStackTrace();
       }
   }
   
   public static void setWeights () {
       try {
           File weightsFile = new File("weights.txt");
           Scanner myReader = new Scanner(weightsFile);
           while (myReader.hasNextLine()) {
               for (int y = 0; y < NN_INPUT_SIZE; y++) {
                for (int x = 0; x < NN_INPUT_SIZE; x++) {
                    double data = (double) Double.parseDouble(myReader.nextLine());
                    kernelWeights[x][y] = data;
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
          String fileName = "weights_" + weightList.length + ".txt";
          File weightsFile = new File(fileName);
          
          //write the weights to a new file by name fileName
//          try {
//           Scanner myReader = new Scanner(weightsFile);
//           while (myReader.hasNextLine()) {
//               for (int x = 0; x < NN_SIZE; x++) {
//                for (int y = 0; y < NN_SIZE; y++) {
//                    double data = (double) Double.parseDouble(myReader.nextLine());
//                    weights[x][y] = data;
//                }
//               }
//           }
//           myReader.close();
//       } catch (FileNotFoundException e) {
//           System.out.println("An error occurred.");
//           e.printStackTrace();
//       }
   }
   
   public static void getImage () throws IOException {
        try {
            //debug
            System.out.println("3");
            
            //instantiate variables
            int categoryIndex;
            String category;
            File categoryFolder;
            File fileFolder;

            String [] categoriesInFolder;
            String [] filesInCategory;

            ArrayList<String> files = new ArrayList<String> ();
            int fileIndex = 0;
            String fileName = "";
            String pathway = "";

           //debug
           System.out.println("3.1");
            
           //get a random category
           categoryIndex = (int)(Math.random()*257);
           
           //debug
           System.out.println("3.2");
           
           //create a list of potential categories
           categoryFolder = new File("ObjectCategories");
           categoriesInFolder = categoryFolder.list();
           //choose category
           category = categoriesInFolder[categoryIndex];
           
           //debug
           System.out.println("3.3");
           System.out.println(category);
           if (categoryFolder.isDirectory())
           System.out.println(categoriesInFolder);
           
           //create a list of potential files
           fileFolder = new File("ObjectCategories\\" + category);
           filesInCategory = fileFolder.list();
           
           //debug
           System.out.println("3.4");
           if (fileFolder.isDirectory())
           System.out.println(filesInCategory);

           //select a random file
           fileIndex = (int) (Math.random()*filesInCategory.length);
           fileName = filesInCategory[fileIndex];
           
           //debug
           System.out.println("3.5");

           String filePath = "ObjectCategories" + "\\" + category + "\\" + fileName;
           predCategory = categoryIndex;
    //       theImage = new APImage(new String("ObjectCategories" + "\\" + category + "\\" + fileName));

           //debug
           System.out.println("4");
           if (categoryFolder.isDirectory())
           System.out.println(filesInCategory);
           
           //prepare the image for reading and set theImage to it
           theImage = ImagePrep.prepImage(filePath);
           
           //debug
           System.out.println("8");
           theImage.draw();
           
        } catch (IOException ex) {
            System.out.println("Something went wrong while getting and processing the training image.");
            ex.printStackTrace();
        }
   }
     
   public static void readImage(){
      Scanner reader = new Scanner(System.in);
      
      //initialize values
      int width = theImage.getWidth();
      int height = theImage.getHeight();
      
      //initialize greyscale/monochrome value array
      m = new int [height][width];


      // Visit all pixels except for the left column and 
      // bottom row
      for (int y = 1; y < height; y++) {
         for (int x = 0; x < width; x++){
            // Obtain info for a pixel and add it to pixel color arrays
            // at a position corresponding to the y and x values respectively
            Pixel pixel = theImage.getPixel(x, y);
            m[x][y] = pixel.getGreen();
            
            }
      }
      getCategory(m);
   }
   
   public static void getCategory(int [][] monochrome) {
       //Access NN weight values in text document
       getWeights();
       //Write NN code here with each pixel's rgb values in their respective array
       //Replace 1 with output.
       outCategory = 0;
   }
   
   public static void backpropagate(int [][] weights){
       
       double cost = 2*(predCategory - outCategory);
       
       //The for loop is to get the indexes of each weight
       //                                                   IMPORTANT: Figure out how to get x and y lengths of weights
       for (int y = 0; y < weights.length; y++){
        for (int x = 0; x < weights.length; x++){
        //backpropagate using backpropagateWeight
        }
       }
   }
   
   public static void backpropagateWeight(int [] weights, int currentIndex, double cost){
       //backpropagate for 1 weight with respect to each weight in a refactored way.
   }
   
   public static void trainNN () throws IOException{
       //debug
       System.out.println("2");
       
       getImage();
//       int width = theImage.getWidth();
//       int height = theImage.getHeight();
//       int [][] weights = new int [width][height];
//       readImage();
//       backpropagate(weights);
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


