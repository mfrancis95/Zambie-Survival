package main.java.org.zambiesurvival.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import main.java.org.zambiesurvival.engine.Location;
/**
 *
 * @author Nelnel33
 */
public class GraphicTextDecal {        
    public static final int SPACING = 2;
    
    /**
     * What the GraphicTextDecal says.
     */
    private final String statement;
    
    /**
     * Size of the GraphicTextDecal.
     */
    private final Dimension size;
    
    /**
     * Size of each character on the GraphicTextDecal.
     */
    private final int charSize;
    
    /**
     * Number of characters per line.
     */
    private final int charPerLine;
    
    /**
     * Total number of lines in the GraphicTextDecal.
     */
    private final int totalLines;
    
    private final String[] lines; 
    
    private final StringImage[] stringImages;
    
    public GraphicTextDecal(
            String statement,   
            int charPerLine) {
        this.statement = statement;
        this.charSize = StringImage.FONT_SIZE;
        this.charPerLine = charPerLine;
        if(statement.length()%charPerLine == 0){
            this.totalLines = statement.length()/charPerLine;
        }
        else{
            this.totalLines = (statement.length()/charPerLine)+1;
        }
        this.size = new Dimension(charSize*charPerLine, StringImage.HEIGHT*totalLines);
        
        lines = new String[totalLines];
        stringImages = new StringImage[totalLines];
        breakString();
        createStringImages();
    }
    
    public GraphicTextDecal(String statement){
        this.statement = statement;
        this.charSize = StringImage.FONT_SIZE;
        lines = breakStringByChar(statement, '.');
        this.charPerLine = largestString(lines).length();
        totalLines = lines.length;
        this.size = new Dimension(charSize*charPerLine, StringImage.HEIGHT*totalLines);
        stringImages = new StringImage[totalLines];
        createStringImages();
    }
    
    private void breakString(){
        int remainder = statement.length()%charPerLine;
        for(int i=0;i<statement.length()/charPerLine;i++){
            lines[i] = statement.substring((charPerLine*i),(charPerLine*i)+(charPerLine));
        }
        if(remainder != 0){
            for(int i=0;i<lines.length;i++){
                if(lines[i] == null){
                    lines[i] = statement.substring(statement.length()-remainder, statement.length());
                    break;
                }
            }
        }       
    }
    
    private void createStringImages(){
        for(int i=0;i<stringImages.length;i++){
            if(lines[i] == null){
                break;
            }
            else{
                stringImages[i] = new StringImage(lines[i]);
            }
        }
    }
    
    public static String[] breakStringByChar(String string, char c){
        int numOfChar = 0;
        for(int i=0;i<string.length();i++){//loops to see how many periods there are
            if(string.charAt(i) == c){
                numOfChar++;
            }
        }        
        if(numOfChar == 0){
            String[] arr = new String[1];
            arr[0] = string;
            return arr;
        }
        String[] array = new String[numOfChar];
        int q = 0;
        int counter1=0;
        for(int j=0;j<string.length();j++){
            if(string.charAt(j)==c){
                String temp = string.substring(q,j+1);
                String temp2 = temp.trim();
                array[counter1] = temp2;
                q=j+1;
                counter1++;
            }           
        }
        return array;        
    }
    public static String largestString(String[] array){
        int counter = 0;
        String mostCharacterString = "";
        for(int i=0;i<array.length;i++){
            if(array[i].length()>counter){
                counter=array[i].length();  
                mostCharacterString=array[i];
            }
        }
        return mostCharacterString;
    }
    
    public String getStatement() {
        return statement;
    }
    
    /**
     * Gets the size of each character.
     * @return 
     */
    public int getCharSize() {
        return charSize;
    }

    public int getCharPerLine() {
        return charPerLine;
    }

    public int getTotalLines() {
        return totalLines;
    }    
    
    public Dimension getSize(){
        return size;
    }
    
    public void render(Graphics2D g, Location l, Color background){
        int width = size.width;
        int height = size.height;
        
        int sx = l.x+(charSize*0);
        int sy = l.y+(charSize*0);
        
        g.setColor(background);
        g.fill(new Rectangle2D.Double(l.x, l.y, width, height));
        
        for(int i=0;i<stringImages.length;i++){
            if(stringImages[i] != null){
                stringImages[i].render(g, new Location(l.x,l.y+(i*StringImage.HEIGHT)), null);
            }
            else{
                System.out.println("stringImages["+i+"] was not rendered because it is null");
            }
        }
    }    
    
    /**
    * For testing purposes.
    */
    public static class Test extends JPanel{
        public GraphicTextDecal pane;
        public GraphicTextDecal hitpointSplat;

        
        public Test() {
            pane = new GraphicTextDecal("Greetings. I am the one who knocks. I am the one who is the best. I will rape you, motheryaka.");
            hitpointSplat = new GraphicTextDecal("50");
        }
        
        @Override
        public void paintComponent(Graphics g){
            pane.render((Graphics2D)g, new Location(10,10), Color.BLACK);
            hitpointSplat.render((Graphics2D)g, new Location(300,300), Color.RED);
        }
        
    }
    
    public static void main(String[] args){
        JFrame frame = new JFrame("test");
        
        GraphicTextDecal.Test pane = new GraphicTextDecal.Test();
        
        frame.setPreferredSize(new Dimension(720,480));
        frame.setLayout(new GridLayout(1,1));
        frame.add(pane);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    
    
    
    
    
}
