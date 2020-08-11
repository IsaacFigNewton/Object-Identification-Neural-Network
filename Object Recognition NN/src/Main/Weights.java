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
import images.APImage;
import images.Pixel;
import java.io.*;
import java.util.*;

public class Weights {
    private static final int MAX_WEIGHT_LAYERS = 5;
    private static final int MAX_SIZE = 256;
//    private static double [] [] [] allWeights = new double [MAX_WEIGHT_LAYERS][MAX_SIZE][MAX_SIZE];
    
    /*
            0          1             2            3                    4
    Input ---> Kernel ---> Max Pool ---> Flatten ---> Fully Connected ---> Soft-Max Output
    */
    public int layer;
    
    public double [] [] weights;
    public String name = "Weights";
    public int size = 256;
    
    public static File weightsFolder = new File("Weights\\");
    public static String [] weightsFolderList = weightsFolder.list();
    
    
    //constructors
    public Weights (int l) {
        layer = l;
        
        weights = new double [size][size];
    }
    public Weights (int l, String n) {
        layer = l;
        
        name = n;
        
        weights = new double [size][size];
    }
    public Weights (int l, int s) {
        layer = l;
        
        if (s <= MAX_SIZE)
        size = s;
        else
        size = MAX_SIZE;
        
        weights = new double [size][size];
    }
    public Weights (int l, String n, int s) {
        layer = l;
        
        name = n;
        
        if (s <= MAX_SIZE)
        size = s;
        else
        size = MAX_SIZE;
        
        weights = new double [size][size];
    }
    public Weights (int l, double [] [] w) {
        layer = l;
        
        size = w.length;
        
        //read all w's values into m
        for (int y = 0; y < w.length; y++) {
            for (int x = 0; x < w.length; x++) {
                weights[x][y] = w[x][y];
            }
        }
    }
    public Weights (int l, String n, double [] [] w) {
        layer = l;
        
        name = n;
        
        size = w.length;
        
        //read all w's values into m
        for (int y = 0; y < w.length; y++) {
            for (int x = 0; x < w.length; x++) {
                weights[x][y] = w[x][y];
            }
        }
    }
    //copy constructor
    public Weights (Weights w) {
        layer = w.layer;
        
        name = w.name;
        
        size = w.size;
        
        //read all w's values into m
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                weights[x][y] = w.getWeight(x, y);
            }
        }
        
        weights = new double [size][size];
    }
    
    //getters
    public double getWeight(int x, int y) {
        return weights[x][y];
    }
    
    //setters
    public void setWeight(int x, int y, double v) {
        weights[x][y] = v;
    }
    
    //recorders
//    //idunno why I wrote this, but maybe it'll be useful later
//    public void setAllWeights () {
//        for (int y = 0; y < size; y++) {
//            for (int x = 0; x < size; x++) {
//                allWeights[layer][x][y] = weights[x][y];
//            }
//        }
//    }
    public void getWeightsText (String path)  throws FileNotFoundException{
       try {
           File weightsFile = new File(path);
           Scanner myReader = new Scanner(weightsFile);
           while (myReader.hasNextLine()) {
               for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    double data = (double) Double.parseDouble(myReader.nextLine());
                    weights[x][y] = data;
                }
               }
           }
           myReader.close();
       } catch (FileNotFoundException e) {
           System.out.println("Couldn't find the file");
           e.printStackTrace();
       }
   }
   public void setWeightsText (String path) throws FileNotFoundException{
       try {
           File weightsFile = new File(path);
           Scanner myReader = new Scanner(weightsFile);
           while (myReader.hasNextLine()) {
               for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    double data = (double) Double.parseDouble(myReader.nextLine());
                    data = weights[x][y];
                }
               }
           }
           myReader.close();
       } catch (FileNotFoundException e) {
           System.out.println("An error occurred.");
           e.printStackTrace();
       }
   }
   
   //toString
   public String toString() {
       return name;
   }
}
