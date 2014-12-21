package com.amf.engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class Tileset {
    
    private Map<Location, BufferedImage> tiles;
    
    public Tileset(String resource) {
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/resources/" + resource));
            tiles = new HashMap<>();
            for (int x = 0; x < image.getWidth(); x += 32) {
                for (int y = 0; y < image.getHeight(); y += 32) {
                    tiles.put(new Location(x / 32, y / 32), image.getSubimage(x, y, 32, 32));
                }
            }
        } 
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public BufferedImage getTile(Location location) {
        return tiles.get(location);
    }
    
}