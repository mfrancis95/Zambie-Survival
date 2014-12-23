package com.amf.engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class Tileset {
    
    private final BufferedImage fallback;
    
    private final Map<Location, BufferedImage> tiles;
    
    private int width, height;
    
    public Tileset(String resource, int tileSize) {
        fallback = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_INT_RGB);
        tiles = new LinkedHashMap<>();
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/resources/" + resource));
            width = image.getWidth();
            height = image.getHeight();
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
    
    public int getHeight() {
        return height;
    }
    
    public BufferedImage getFallbackTile() {
        return fallback;
    }
    
    public BufferedImage getTile(Location location) {
        BufferedImage tile = tiles.get(location);
        return tile == null ? fallback : tile;
    }
    
    public Collection<Location> getTileLocations() {
        return tiles.keySet();
    }
    
    public int getWidth() {
        return width;
    }
    
}