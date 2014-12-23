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
public class UIPane extends JPanel{
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
     * Which button the mouse is currently on.
     */
    private int mouseIndex;
    
    private GraphicButton[] buttons;

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
                
                    repaint();
                
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
                
                        repaint();
                    }
                } catch(ArrayIndexOutOfBoundsException aie){
                    System.out.println("Not an nxn matrix(not square)");
                }
            }
        }          
    };
    
    /**
     * Calculates the index of the button being pressed.
     * @param x - x value of the mouse(the column)
     * @param y - y value of the mouse(the row)
     * @return 
     */
    private int calculateMouseIndex(int x, int y){
        return mouseIndex = convertMatrixToVectorIndex((int)((y+size)/size), (int)((x+size)/size), layout.col) - 1; 
    }
    
    /**
     * Converts a matrix index to a vector index.
     * @param row
     * @param col
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
     * @throws IllegalArgumentException
     */
    public UIPane(int TOTAL_ITEMS, int ICON_SIZE, MatrixDimension LAYOUT) throws IllegalArgumentException {
        this.totalItems = TOTAL_ITEMS;
        this.size = ICON_SIZE;
        this.layout = LAYOUT;
        
        if(layout.col*layout.row < totalItems){
            throw new IllegalArgumentException("Cannot have more items then matrix is able to hold.");
        }
        
        this.dimension = new Dimension(layout.col*size, layout.row*size);
        
        buttons = new GraphicButton[TOTAL_ITEMS];
        
        for(int i=0;i<TOTAL_ITEMS;i++){
            buttons[i] = new GraphicButton(ICON_SIZE);
        }
        
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
       
        setBackground(Color.BLUE);
    }   

    public int getMouseIndex() {
        return mouseIndex;
    }

    public GraphicButton[] getButtons() {
        return buttons;
    }    
    
    @Override
    public void paintComponent(Graphics og){
        super.paintComponent(og);
        Graphics2D g = (Graphics2D)og;
        
        for(int c=0;c<layout.col;c++){
            for(int r=0;r<layout.row;r++){
                int z = convertMatrixToVectorIndex(r+1,c, layout.col);
                try{
                    buttons[z].drawButtonIcon(g, new Location(c*size,r*size));
                } catch(ArrayIndexOutOfBoundsException aie){
                    System.out.println("Not an nxn matrix(not square)");
                }
            }
        }        
    }
    
    public static void main(String[] args){
        JFrame frame = new JFrame("test");
        
        UIPane pane = new UIPane(23,100, new MatrixDimension(5,5));
        
        frame.setPreferredSize(pane.dimension);
        frame.setLayout(new GridLayout(1,1));
        frame.add(pane);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
}
