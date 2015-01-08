package main.java.org.zambiesurvival.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import main.java.org.zambiesurvival.engine.Location;

/**
 *
 * @author Nelnel33
 */
public class GraphicToolTip {
    public static final int CHAR_SIZE = 13;
    public static final int CHAR_PER_LINE = 20;
    public static final int TOTAL_LINES = 20;
    
    private String title;
    private String description;

    public GraphicToolTip(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void render(Graphics2D g, Location l){
        int width = CHAR_SIZE*CHAR_PER_LINE;
        int height = CHAR_SIZE*TOTAL_LINES;
        
        int titleX = l.x+(CHAR_SIZE*0)+(5);
        int titleY = l.y+(CHAR_SIZE*1);
        
        int descX = l.x+(CHAR_SIZE*0)+(5);
        int descY = l.y+(CHAR_SIZE*3);
        
        g.draw(new Rectangle2D.Double(l.x, l.y, width, height));
        
        g.drawString(title, titleX, titleY);
        g.drawString(description, descX, descY);
    }    
    
    /**
    * For testing purposes.
    */
    public static class Test extends JPanel{
        public GraphicToolTip pane;

        
        public Test() {
            pane = new GraphicToolTip("A Title", "A Description");
            
            //this.addMouseListener(mouse);
            //this.addMouseMotionListener(mouse);
        }
        
        @Override
        public void paintComponent(Graphics g){
            pane.render((Graphics2D)g, new Location(10,10));

        }
        /*
        private final MouseAdapter mouse = new MouseAdapter() {      
            
        @Override
        public void mouseClicked(MouseEvent e) {
            pane.mouseClicked(e, Test.this);
        }

        @Override
        public void mouseMoved(MouseEvent e){
            pane.mouseMoved(e, Test.this);
        }          
        
    }; */
        
    }
    
    public static void main(String[] args){
        JFrame frame = new JFrame("test");
        
        GraphicToolTip.Test pane = new GraphicToolTip.Test();
        
        frame.setPreferredSize(new Dimension(720,480));
        frame.setLayout(new GridLayout(1,1));
        frame.add(pane);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
