/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.File;

/**
 *
 * @author Owner
 */
public class Layer {
    /*
                0             1             2               3                   4
    Input ---> Kernel ---> Max Pool ---> Flatten ---> Fully Connected ---> Soft-Max Output
    */
    public double [] [] layerIn;
    public int layer;
    public int size = 256;
    private final int MAX_SIZE = 256;
    public Weights weights;
    
    //constructors
    public Layer (int l, int s) {
        layer = l;
        
        weights = new Weights(l, s);
        
        if (s <= MAX_SIZE)
        size = s;
        else
        size = MAX_SIZE;
        
        layerIn = new double [size][size];
    }
    public Layer (int l, int s, int [] [] in, Weights w) {
        layer = l;
        
        weights = new Weights(w);
        
        size = s;
        
        weights = new Weights(l, s);
        
        //read all w's values into m
        for (int y = 0; y < in.length; y++) {
            for (int x = 0; x < in.length; x++) {
                layerIn[x][y] = (double) in[x][y];
            }
        }
    }
    public Layer (int l, int s, double [] [] in, Weights w) {
        layer = l;
        
        weights = new Weights(w);
        
        size = in.length;
        
        //read all w's values into m
        for (int y = 0; y < in.length; y++) {
            for (int x = 0; x < in.length; x++) {
                layerIn[x][y] = in[x][y];
            }
        }
    }
        
    //getters
    public double getNodeInput(int x, int y) {
        return layerIn[x][y];
    }
    
    //setters
    public void setNodeInput(int x, int y, double v) {
        layerIn[x][y] = v;
    }
    public void setLayerInput(double [] [] w) {
        layerIn = w;
    }
    
    
    
    
    //layer compute-ers
    //kernel
    public double [] [] getL0Out() {
        //set size of layerOut to input size - 2
        double [] [] layerOut = new double [size-2][size-2];
        
        //fill up output in case of misuse
        for (int y = 1; y < size-1; y++) {
                for (int x = 1; x < size-1; x++) {
                layerOut [x][y] = layerIn [x][y];
            }
        }
        
        if (layer == 0) {
            //do required stuffs to input to get output
            for (int y = 1; y < size-1; y++) {
                for (int x = 1; x < size-1; x++) {
                    //the matrix/sensor/thingy that convolves the input from a 3x3 array down to a 1x1 output value
                    /*
                    I know something's gonna come up later that shows a problem with the difference in sizing for the different layers,
                    I can feel it, so I'm leaving a debugging message here
                    */
                    System.out.println("Maybe error here @ getL0Out method in Layer.java");
                    //top layer of sensor thingy
                    layerOut [x][y] = layerIn [x-1][y-1] * weights.getWeight(0, 0);
                    layerOut [x][y] += layerIn [x][y-1] * weights.getWeight(1, 0);
                    layerOut [x][y] += layerIn [x+1][y-1] * weights.getWeight(2, 0);
                    //middle layer
                    layerOut [x][y] += layerIn [x-1][y] * weights.getWeight(0, 1);
                    layerOut [x][y] += layerIn [x][y] * weights.getWeight(1, 1);
                    layerOut [x][y] += layerIn [x+1][y] * weights.getWeight(2, 1);
                    //bottom layer
                    layerOut [x][y] += layerIn [x-1][y+1] * weights.getWeight(0, 2);
                    layerOut [x][y] += layerIn [x][y+1] * weights.getWeight(1, 2);
                    layerOut [x][y] += layerIn [x+1][y+1] * weights.getWeight(2, 2);
                }
            }
            
            return layerOut;
        } else {
            //if the layer being used by method is not weightsernel layer
            System.out.println("This method only works on the weightsernel layer. (Layer 0)");
            return null;
        }
    }
    public double [] [] getL1Out() {
        //initialize max pool weights
        weights = new Weights (0, 3);
        //set size of layerOut to layerIn.length-2
        double [] [] layerOut = new double [size-2][size-2];
        
        //fill up output in case of misuse
        for (int y = 1; y < size-1; y++) {
                for (int x = 1; x < size-1; x++) {
                layerOut [x][y] = layerIn [x][y];
            }
        }
        
        if (layer == 0) {
            //do required stuffs to input to get output
            for (int y = 1; y < size-1; y++) {
                for (int x = 1; x < size-1; x++) {
                    //the matrix/sensor/thingy that convolves the input from a 3x3 array down to a 1x1 output value
                    /*
                    I know something's gonna come up later that shows a problem with the difference in sizing for the different layers,
                    I can feel it, so I'm leaving a debugging message here
                    */
                    System.out.println("Maybe error here @ getL0Out method in Layer.java");
                    //top layer of sensor thingy
                    layerOut [x][y] = layerIn [x-1][y-1] * weights.getWeight(0, 0);
                    layerOut [x][y] += layerIn [x][y-1] * weights.getWeight(1, 0);
                    layerOut [x][y] += layerIn [x+1][y-1] * weights.getWeight(2, 0);
                    //middle layer
                    layerOut [x][y] += layerIn [x-1][y] * weights.getWeight(0, 1);
                    layerOut [x][y] += layerIn [x][y] * weights.getWeight(1, 1);
                    layerOut [x][y] += layerIn [x+1][y] * weights.getWeight(2, 1);
                    //bottom layer
                    layerOut [x][y] += layerIn [x-1][y+1] * weights.getWeight(0, 2);
                    layerOut [x][y] += layerIn [x][y+1] * weights.getWeight(1, 2);
                    layerOut [x][y] += layerIn [x+1][y+1] * weights.getWeight(2, 2);
                }
            }
            
            return layerOut;
        } else {
            //if the layer being used by method is not weightsernel layer
            System.out.println("This method only works on the weightsernel layer. (Layer 0)");
            return null;
        }
    }
}
