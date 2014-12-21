package com.amf.engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tileset {
    
    private BufferedImage image;
    
    public Tileset(String resource) {
        try {
            image = ImageIO.read(getClass().getResource("/resources/" + resource));
        } 
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public BufferedImage getTile(int x, int y) {
        return image.getSubimage(x, y, 32, 32);
    }
    
}