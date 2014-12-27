/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.java.org.zambiesurvival.uitools;

import main.java.org.zambiesurvival.engine.Location;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Nelnel33
 */
public class GraphicButton{
    public static final int NOTHING = 0;
    public static final int HOVERING = 1;
    public static final int CLICKED = 2;
    
    private int state;    
    private int size;
        
    public GraphicButton(int size){
        this.size = size;
        
        this.state = NOTHING;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }  
        
    /**
     * Override to use.
     * Executes a certain command when clicked.
     */
    public void executeWhenClicked(){
        System.out.println("Clicked");
    }
    
    /**
     * Override to use.
     * Executes a certain command when hovering.
     */
    public void executeWhenHovering(){
        System.out.println("Hovering");
    }
    
    /**
     * Override to use.
     * Sets the image for the particular button.
     * @param resource 
     */
    public void setImage(String resource){
        
    }
        
    /**
    * Draws an inventoryIcon
    * @param g
    * @param l
    */
    public void drawButtonIcon(Graphics2D g, Location l){
        if(state == NOTHING){
            g.setColor(Color.RED);
        }
        else if(state == HOVERING){
            g.setColor(Color.YELLOW);
        }
        else if(state == CLICKED){
            g.setColor(Color.GREEN);
        }
        else{
            g.setColor(Color.MAGENTA);
            System.out.println("Error, invalid state");
        }
        g.fill(new Rectangle2D.Double(l.x, l.y, size, size));
        
        g.setColor(Color.WHITE);
        g.draw(new Rectangle2D.Double(l.x, l.y, size, size));      
    }
    
    /**
     * Updates all the buttons in a certain pane
     * @param g
     * All the buttons in the pane.
     * @param selfIndex
     * Index of the state being updated.
     * @param state 
     * State to update the particular index to.
     * @param onMove
     * True if operates when the mouse moves else false.
     */
    public void update(GraphicButton[] g, int selfIndex, int state, boolean onMove){
        for(int i=0;i<g.length;i++){
            if(i == selfIndex){
                g[i].setState(state);
                
                if(state == CLICKED){
                    executeWhenClicked();
                }
                else if(state == HOVERING){
                    executeWhenHovering();
                }
                else{
                    System.out.println("State is nothing, does not execute any command");
                }               
            }
            else if(g[i].getState() == CLICKED && onMove){
                System.out.println("GraphicButton["+i+"]"+" is clicked and does not update on hover.");
            }
            else{
                g[i].setState(NOTHING);
            }
        }
    }
}
