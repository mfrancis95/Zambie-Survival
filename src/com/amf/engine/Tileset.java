package com.amf.engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class Tileset {
    
    private final BufferedImage fallback;
    
    private final Map<Location, BufferedImage> tiles;
    
    public Tileset(String resource, int tileSize) {
        fallback = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_INT_RGB);
        tiles = new HashMap<>();
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/resources/" + resource));
            for (int x = 0; x < image.getWidth(); x += tileSize) {
                for (int y = 0; y < image.getHeight(); y += tileSize) {
                    tiles.put(new Location(x / tileSize, y / tileSize), image.getSubimage(x, y, tileSize, tileSize));
                }
            }
        } 
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public BufferedImage getTile(Location location) {
        BufferedImage tile = tiles.get(location);
        return tile == null ? fallback : tile;
    }
    
}