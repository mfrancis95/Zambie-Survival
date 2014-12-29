package main.java.org.zambiesurvival.engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class ImageSheet {
    
    private final BufferedImage fallbackImage;
    
    private final Map<Location, BufferedImage> images;
    
    private static final Map<String, ImageSheet> imageSheets = new HashMap<>();
    
    private final int tileSize, width, height;
    
    private ImageSheet(String resource, int tileSize) throws IOException {
        fallbackImage = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_INT_RGB);
        images = new LinkedHashMap<>();
        this.tileSize = tileSize;
        BufferedImage image = ImageIO.read(getClass().getResource("/main/resources/" + resource));
        width = image.getWidth();
        height = image.getHeight();
        for (int x = 0; x < image.getWidth(); x += tileSize) {
            for (int y = 0; y < image.getHeight(); y += tileSize) {
                images.put(new Location(x / tileSize, y / tileSize), image.getSubimage(x, y, tileSize, tileSize));
            }
        }
    }
    
    public static ImageSheet get(String name) {
        return imageSheets.get(name);
    }
    
    public int getHeight() {
        return height;
    }
    
    public BufferedImage getFallbackImage() {
        return fallbackImage;
    }
    
    public BufferedImage getImage(Location location) {
        BufferedImage tile = images.get(location);
        return tile == null ? fallbackImage : tile;
    }
    
    public Collection<Location> getImageLocations() {
        return images.keySet();
    }
    
    public int getTileSize() {
        return tileSize;
    }
    
    public int getWidth() {
        return width;
    }
    
    public static ImageSheet load(String resource, int tileSize) {
        try {
            ImageSheet imageSheet = new ImageSheet(resource, tileSize);
            imageSheets.put(resource, imageSheet);
            return imageSheet;
        }
        catch (IOException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
}