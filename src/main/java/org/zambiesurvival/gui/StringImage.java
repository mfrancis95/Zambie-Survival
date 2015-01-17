package main.java.org.zambiesurvival.gui;

import java.awt.Color;
import main.java.org.zambiesurvival.exceptionhandling.CharacterNotSupportedException;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.org.zambiesurvival.engine.ImageSheet;
import main.java.org.zambiesurvival.engine.Location;

/**
 *
 * @author Nelnel33
 */
public class StringImage {
    /**
     * Size of the font in pixels.
     */
    private final int fontSize;
    
    /**
     * Location of the StringImage.
     */
    private final Location location;
    
    /**
     * Color of the Background.
     */
    private Color background;
    
    /**
     * Spacing between each character.
     */
    private final int spacing;
    
    /**
     * Height of the StringImage.
     */
    private final int height;
    
    /**
     * Image where the font for the StringImage is loaded.
     */
    private static final ImageSheet font = ImageSheet.load("font.png", 8);//is 8 because of the tilesize in the picture! DONOT CHANGE!
    
    /**
     * The String that will be displayed for the StringImage.
     */
    private final String string;
    
    /**
     * Array of images that represent each character of the String.
     */
    private final BufferedImage[] images;

    public StringImage(String string, int fontSize, Location location, Color background){
        if(string == null){
            this.string = "";
        }
        else{
            this.string = string;
        }
        
        this.fontSize = fontSize;
        this.location = location;
        this.background = background;
        spacing = calculateSpacing(fontSize);
        height  = calculateHeight(fontSize);
        
        images = new BufferedImage[this.string.length()];
        createImages();
    }
    
    public static int calculateHeight(int fontSize) {
        int s = calculateSpacing(fontSize);
        return fontSize + (2 * s);
    }

    public static int calculateSpacing(int fontSize) {
        return fontSize / 4;
    }
    
    private void createImages(){
        for(int i=0;i<string.length();i++){
            char c = string.charAt(i);
            images[i] = getCharacterImage(c);
        }
    }

    public String getString() {
        return string;
    }

    public BufferedImage getImage(int i) {
        return images[i];
    }
    
    public void render(Graphics2D g){
        Location l = this.location;
        
        if(background != null){
            int bwidth = (fontSize*string.length());// + (string.length()+1)*spacing;
            int bheight = height;
            g.setColor(background);
            g.fill(new Rectangle2D.Double(l.x, l.y, bwidth, bheight));
        }
        
        for(int i=0;i<images.length;i++){
            g.drawImage(images[i], l.x + (fontSize*i) + (fontSize/4), l.y + (spacing),fontSize,fontSize, null);
        }       
    }
    
    /**
     * Returns the BufferedImage for a character.
     * Currently only has support for:
     * numbers: 0 to 9.
     * lower-case alphabet: A to Z.
     * upper-case alphabet: a to z.
     * NEEDS MORE SUPPORT! ADD IF NECESSARY!
     * @param c
     * @return
     * @throws CharacterNotSupportedException 
     */
    public static BufferedImage getCharacterImage(char c) throws CharacterNotSupportedException{
        int a = (int)'a';
        int o = (int)'o';
        int p = (int)'p';
        int z = (int)'z';
        
        int zero = (int)'0';
        int nine = (int)'9';
        
        int ac = (int)'A';
        int oc = (int)'O';
        int pc = (int)'P';
        int zc = (int)'Z';
        
        int ci = (int)c;
        
        //System.out.print(c);
        
        if(ci>=zero && ci<=nine){        
            for(int i=zero;i<=nine;i++){//from 0 to 9
                int d = i-zero;//difference between i and zero; a number from 0 to nine-zero.
                if(ci == i){
                    return font.getImage(new Location(d,3));
                }
            }
        }
        else if(ci>=ac && ci<=zc){
            for(int i=ac;i<=oc;i++){//from A to O
                int d = i-ac;//difference between i and ac. a number from 0 to pc-ac.
                if(ci == i){
                    return font.getImage(new Location(d+1,4));
                }
            }
            for(int j=pc;j<=zc;j++){//from P to Z
                int d = j-pc;//difference between j and pc. a number from 0 to zc-pc.
                if(ci == j){
                    return font.getImage(new Location(d,5));
                }
            }            
        }        
        else if(ci>=a && ci<=z){
            for(int i=a;i<=o;i++){
                int d = i-a;//difference between i and a. a number from 0 to p-a.
                if(ci == i){
                    return font.getImage(new Location(d+1,6));
                }
            }
            for(int j=p;j<=z;j++){//from p to z
                int d = j-p;//difference between j and p. Number from 0 to z-p.
                if(ci == j){
                    return font.getImage(new Location(d,7));
                }
            }   
        }
        else if(ci == ' '){//space
            return font.getImage(new Location(0,0));
        }
        else if(ci == ','){
            return font.getImage(new Location(12,2));
        }
        else if(ci == '.'){
            return font.getImage(new Location(14,2));
        }
        else if(ci == '!'){
            return font.getImage(new Location(1,2));
        }
        else if(ci == '\''){//apostrophe or '
            return font.getImage(new Location(7,2));
        }
        else if(ci == '#'){
            return font.getImage(new Location(3,2));
        }
        else if(ci == '?'){
            return font.getImage(new Location(15,3));
        }
        else if(ci == ':'){
            return font.getImage(new Location(11,3));
        }
        else if(ci == ';'){
            return font.getImage(new Location(12,3));
        }
        
        throw new CharacterNotSupportedException("Character "+c+" is not supported in StringImage");
    }
    
    
}
