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
import Main.ImageResizer;

public class ImagePrep {
    private static APImage img = new APImage("placeholder.png");
    
    ImagePrep () {
        img = new APImage("placeholder.png");
    }
    ImagePrep (String path) {
        img = new APImage(path);
    }
    public static APImage prepImage (String path) {
        
        ImageResizer rImg = new ImageResizer();
        //scale image
        rImg.resizeImage(path);
        
        //convert to black and white
        for (Pixel p : img) {
            int avg = (p.getRed() + p.getGreen() + p.getBlue())/3;
            p.setRed(avg);
            p.setGreen(avg);
            p.setBlue(avg);
        }
        
        return img;
    }
}
