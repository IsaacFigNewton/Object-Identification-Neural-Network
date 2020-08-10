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
    public double [] [] weights;
    private String name;
    private int size = 256;
    private final int MAX_SIZE = 256;
    
    public static File weightsFolder = new File("Weights\\");
    public static String [] weightList = weightsFolder.list();
    
    
    //constructors
    public Weights () {
        name = "Weights";
        
        weights = new double [size][size];
    }
    public Weights (String n) {
        weights = new double [size][size];
        name = n;
    }
    public Weights (int s) {
        name = "Weights";
        
        if (s <= MAX_SIZE)
        size = s;
        else
        size = MAX_SIZE;
        
        weights = new double [size][size];
    }
    public Weights (String n, int s) {
        name = n;
        
        if (s <= MAX_SIZE)
        size = s;
        else
        size = MAX_SIZE;
        
        weights = new double [size][size];
    }
    public Weights (double [] [] w) {
        name = "Weights";
        
        size = w.length;
        
        //read all w's values into m
        for (int y = 0; y < w.length; y++) {
            for (int x = 0; x < w.length; x++) {
                weights[x][y] = w[x][y];
            }
        }
    }
    public Weights (String n, double [] [] w) {
        name = n;
        
        size = w.length;
        
        //read all w's values into m
        for (int y = 0; y < w.length; y++) {
            for (int x = 0; x < w.length; x++) {
                weights[x][y] = w[x][y];
            }
        }
        
        
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
    public void getWeightsText ()  throws FileNotFoundException{
       try {
           File weightsFile = new File("weights.txt");
           Scanner myReader = new Scanner(weightsFile);
           while (myReader.hasNextLine()) {
               for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    double data = (double) Double.parseDouble(myReader.nextLine());
                    this.weights[x][y] = data;
                }
               }
           }
           myReader.close();
       } catch (FileNotFoundException e) {
           System.out.println("Couldn't find the file");
           e.printStackTrace();
       }
   }
   
   public void setWeightsText () throws FileNotFoundException{
       try {
           File weightsFile = new File("weights.txt");
           Scanner myReader = new Scanner(weightsFile);
           while (myReader.hasNextLine()) {
               for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    double data = (double) Double.parseDouble(myReader.nextLine());
                    this.weights[x][y] = data;
                }
               }
           }
           myReader.close();
       } catch (FileNotFoundException e) {
           System.out.println("An error occurred.");
           e.printStackTrace();
       }
   }
}
