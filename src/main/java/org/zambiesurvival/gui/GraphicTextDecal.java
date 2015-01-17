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
    /**
     * Spacing between each character and row of characters.
     */
    private final int spacing;
    
    /**
     * Height of each row of words.
     */
    private final int height;
    
    /**
     * Supported Punctuation to break at.
     */
    public static final char[] PUNCTUATION = {'.','!','?'};
    
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
    private final int fontSize;
    
    /**
     * Number of characters per line.
     */
    private final int charPerLine;
    
    /**
     * Location of the GraphicTextDecal.
     */
    private Location location;
    
    /**
     * Color of the background.
     * If null then transparent background.
     */
    private final Color background;
    
    /**
     * Total number of lines in the GraphicTextDecal.
     */
    private final int totalLines;
    
    /**
     * Lines of substrings from the main statement.
     */
    private final String[] lines; 
    
    /**
     * StringImages of each of the substrings.
     */
    private final StringImage[] stringImages;     
    
    /**
     * Determines whether the image renders to the left or to the right of your cursor
     * if true - renders to right of cursor
     * if false - renders to left of cursor.
     */
    private final boolean renderToRight;
    
    
    public GraphicTextDecal(
            String statement,   
            int charPerLine,
            int fontSize,
            Location location,
            Color background,
            boolean renderToRight) {
        this.statement = statement;
        this.fontSize = fontSize;
        this.charPerLine = charPerLine;
        this.location = location;
        this.background = background;
        this.renderToRight = renderToRight;
        if(statement.length()%charPerLine == 0){
            this.totalLines = statement.length()/charPerLine;
        }
        else{
            this.totalLines = (statement.length()/charPerLine)+1;
        }
        
        spacing = StringImage.calculateSpacing(fontSize);
        height = StringImage.calculateHeight(fontSize);
        
        this.size = new Dimension(fontSize*charPerLine, height*totalLines);
        
        checkRenderToRight();
        
        lines = new String[totalLines];
        stringImages = new StringImage[totalLines];
        breakString();
        createStringImages();
    }
    
    public GraphicTextDecal(
            String statement,
            int fontSize,
            Location location, 
            Color background,
            boolean renderToRight){
        this.statement = statement;
        this.fontSize = fontSize;
        this.location = location;
        this.background = background;
        this.renderToRight = renderToRight;
        lines = breakStringByChar(statement, PUNCTUATION);
        this.charPerLine = largestString(lines).length();
        totalLines = lines.length;
        
        spacing = StringImage.calculateSpacing(fontSize);
        height = StringImage.calculateHeight(fontSize);
        
        this.size = new Dimension(fontSize*charPerLine, height*totalLines);
        
        checkRenderToRight();
        
        stringImages = new StringImage[totalLines];
        createStringImages();
    }
    
    public GraphicTextDecal(String statement, Location placement) {
        this(statement, 8, placement, Color.BLACK, true);
    }
    
    public GraphicTextDecal(String statement, int fontSize, Location placement, boolean renderToRight) {
        this(statement, fontSize, placement, new Color(0, 0, 0, 0), renderToRight);
    }
    
    private void checkRenderToRight(){
        
        if(renderToRight){
            //location = this.location;
        }
        else{
            int x = this.location.x;
            int y = this.location.y;
            
            this.location = new Location(x-this.size.width, y);
        }         
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
                stringImages[i] = new StringImage(lines[i],fontSize, new Location(location.x,location.y+(i*this.height)), null);
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
        int counter=0;
        for(int j=0;j<string.length();j++){
            if(string.charAt(j)==c){
                String temp = string.substring(q,j+1);
                String temp2 = temp.trim();
                array[counter] = temp2;
                q=j+1;
                counter++;
            }           
        }
        return array;        
    }
    
    public static String[] breakStringByChar(String string, char[] charArray){
        int numOfChars = 0;
        char letter;
        for(int i=0;i<string.length();i++){//loops to see how many periods there are
            for(int y=0;y<charArray.length;y++){
                letter=charArray[y];
                    if(string.charAt(i)==letter){
                        numOfChars++;
                    }
            }
        }
        if(numOfChars == 0){
            String[] arr = new String[1];
            arr[0] = string;
            return arr;
        }
        String[] array = new String[numOfChars];
        int q = 0;
        int counter=0;
        for(int j=0;j<string.length();j++){
            for(int o=0;o<charArray.length;o++){
                letter=charArray[o];
                if(string.charAt(j)==letter){
                    String temp = string.substring(q,j+1);
                    String temp2 = temp.trim();
                    array[counter] = temp2;
                    q=j+1;
                    counter++;
                } 
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
        return fontSize;
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

    public Location getLocation() {
        return location;
    }
    
    public void render(Graphics2D g){     
        if(background != null){
            g.setColor(background);
            g.fill(new Rectangle2D.Double(location.x, location.y, size.width, size.height));        
        }
        for(int i=0;i<stringImages.length;i++){
            if(stringImages[i] != null){
                stringImages[i].render(g);
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
        public GraphicTextDecal ex1;

        
        public Test() {
            pane = new GraphicTextDecal("Greetings! Am I the one who knocks? I am the one who knocks!", 12,  new Location(10,10), Color.BLACK, true);
            hitpointSplat = new GraphicTextDecal("50",64, new Location(300,300), Color.RED, false);
            ex1 = new GraphicTextDecal("Hello friend. I am the one who knocks.",8,  new Location(200,200), null, true);
        }
        
        @Override
        public void paintComponent(Graphics g){
            pane.render((Graphics2D)g);
            hitpointSplat.render((Graphics2D)g);
            ex1.render((Graphics2D)g);
            
            
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
