package com.amf.uitools;

import com.amf.engine.Location;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Nelnel33
 */
public class UIPane{
    /**
     * Total number of buttons in this pane.
     */
    public final int totalItems;
    
    /**
     * Size of the each button in pane.
     */
    public final int size;//TEMP VALUE
    
    /**
     * Dimension of the Pane - width x height.
     */
    public final Dimension dimension;    
    
    /**
     * row by col(y by x)(how you want to layout the buttons).
     */
    public final MatrixDimension layout;
    
    /**
     * Location on the JPanel
     */
    public final Location placement;
    
    /**
     * Which button the mouse is currently on.
     */
    private int mouseIndex;
    
    private GraphicButton[] buttons;
    
    /*
    private final MouseAdapter mouse = new MouseAdapter() {        
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if(x <= layout.col*size && y <= layout.row*size){
                try{
                    int i = calculateMouseIndex(x,y);
                    System.out.println(i);
            
                    GraphicButton curr = buttons[i];
            
                    curr.update(buttons, i, GraphicButton.CLICKED);
                
                    //repaint();
                
                } catch(ArrayIndexOutOfBoundsException aie){
                    System.out.println("Not an nxn matrix(not square)");
                }
            } 
        }

        @Override
        public void mouseMoved(MouseEvent e){
            int x = e.getX();
            int y = e.getY();            
            
            if(x <= layout.col*size && y <= layout.row*size){
                try{
                    int mi = mouseIndex;
                    int i = calculateMouseIndex(x,y);
                    System.out.println(i);
                    
                    if(i != mi){
                        GraphicButton curr = buttons[i];            
                        curr.update(buttons, i, GraphicButton.HOVERING);
                
                        //repaint();
                    }
                } catch(ArrayIndexOutOfBoundsException aie){
                    System.out.println("Not an nxn matrix(not square)");
                }
            }
        }          
    };    
    */
    
    /**
     * Place into MainGame's MouseAdapter.
     * @param e 
     */
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(x <= (layout.col*size)+placement.x && y <= (layout.row*size)+placement.y &&
            x >= placement.x && y >= placement.y){
            try{
                int i = calculateMouseIndex(x,y);
                System.out.println(i);
            
                GraphicButton curr = buttons[i];
            
                curr.update(buttons, i, GraphicButton.CLICKED);
                
                //repaint();
                
            } catch(ArrayIndexOutOfBoundsException aie){
                //System.out.println("Not an nxn matrix(not square)");
            }
        } 
    }

    /**
     * Place into MainGame's MouseAdapter.
     * @param e 
     */
    public void mouseMoved(MouseEvent e){
        int x = e.getX();
        int y = e.getY();            
            
        if(x <= (layout.col*size)+placement.x && y <= (layout.row*size)+placement.y &&
            x >= placement.x && y >= placement.y){
            try{
                int mi = mouseIndex;
                int i = calculateMouseIndex(x,y);
                System.out.println(i);
                
                if(i != mi){
                    GraphicButton curr = buttons[i];            
                    curr.update(buttons, i, GraphicButton.HOVERING);
                
                    //repaint();
                }
            } catch(ArrayIndexOutOfBoundsException aie){
                //System.out.println("Not an nxn matrix(not square)");
            }
        }
    } 
    
    /**
     * Calculates the index of the button being pressed.
     * @param x - x value of the mouse(the column)
     * @param y - y value of the mouse(the row)
     * @return 
     */
    private int calculateMouseIndex(int x, int y){
        return mouseIndex = convertMatrixToVectorIndex((int)(((y-placement.y)+size)/size), (int)(((x-placement.x)+size)/size), layout.col) - 1; 
    }
    
    /**
     * Works differently from the method in MatrixDimension Class.
     * Converts a matrix index to a vector index.
     * @param row
     * Can be from 1 to n.
     * @param col
     * Can be from 0 to n-1.
     * @param maxcol
     * @return 
     */
    private int convertMatrixToVectorIndex(int row, int col, int maxcol){
        return ((row-1)*maxcol)+col;
    }
    
    /**
     * If portrait then use y value for calculate mouse index.(x<y)
     * If landscape then use x value for calculate mouse index.(y<x)
     * @return 
     */
    private boolean isPortrait(){
        return dimension.getHeight()>dimension.getWidth();
    }

    
    /**
     * Creates a new UIPane
     * @param TOTAL_ITEMS
     * Total number of GraphicButtons in the UIPane.
     * @param ICON_SIZE
     * Size of the GraphicButtons in the UIPane.
     * @param LAYOUT 
     * Layout of the UIPane - row x col.
     * @param placement
     * @throws IllegalArgumentException
     */
    public UIPane(int TOTAL_ITEMS, int ICON_SIZE, MatrixDimension LAYOUT, Location placement) throws IllegalArgumentException {
        this.totalItems = TOTAL_ITEMS;
        this.size = ICON_SIZE;
        this.layout = LAYOUT;
        this.placement = placement;
        
        if(layout.col*layout.row < totalItems){
            throw new IllegalArgumentException("Cannot have more items then matrix is able to hold.");
        }
        
        this.dimension = new Dimension((layout.col*size)+placement.x, (layout.row*size)+placement.y);
        
        buttons = new GraphicButton[TOTAL_ITEMS];
        
        for(int i=0;i<TOTAL_ITEMS;i++){
            buttons[i] = new GraphicButton(ICON_SIZE);
        }
        
        //this.addMouseListener(mouse);
        //this.addMouseMotionListener(mouse);
       
        //setBackground(Color.BLUE);
    }   

    public int getMouseIndex() {
        return mouseIndex;
    }

    public GraphicButton[] getButtons() {
        return buttons;
    }    

    public void render(Graphics2D g){
        //super.paintComponent(og);
        //Graphics2D g = (Graphics2D)og;
        
        for(int c=0;c<layout.col;c++){
            for(int r=0;r<layout.row;r++){
                int z = convertMatrixToVectorIndex(r+1,c, layout.col);
                try{
                    buttons[z].drawButtonIcon(g, new Location(placement.x+(c*size), placement.y+(r*size)));
                } catch(ArrayIndexOutOfBoundsException aie){
                    //System.out.println("Not an nxn matrix(not square)");
                }
            }
        }        
    }
    
    /**
     * For testing purposes.
     */
    public static class Test extends JPanel{
        public UIPane pane;
        public UIPane otherpane;
        
        public Test() {
            pane = new UIPane(3,100, new MatrixDimension(3,1), new Location(0,0));
            otherpane = new UIPane(5,100,new MatrixDimension(5,1), new Location(100,0));
            
            this.addMouseListener(mouse);
            this.addMouseMotionListener(mouse);
        }
        
        @Override
        public void paintComponent(Graphics g){
            pane.render((Graphics2D)g);
            otherpane.render((Graphics2D)g);
        }
        
        private final MouseAdapter mouse = new MouseAdapter() {      
            
        @Override
        public void mouseClicked(MouseEvent e) {
            pane.mouseClicked(e);
            //repaint();
            otherpane.mouseClicked(e);
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e){
            pane.mouseMoved(e);
            //repaint();
            otherpane.mouseMoved(e);
            repaint();
        }          
    }; 
        
    }
    
    public static void main(String[] args){
        JFrame frame = new JFrame("test");
        
        Test pane = new Test();
        
        frame.setPreferredSize(new Dimension(720,480));
        frame.setLayout(new GridLayout(1,1));
        frame.add(pane);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
}
