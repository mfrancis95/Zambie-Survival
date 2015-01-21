package main.java.org.zambiesurvival.engine.item;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.state.WorldState;

public class BandageItem extends HealingItem {

    public BandageItem(int quantity) {
        super(5, quantity, 5);
    }
    
    public String getDescription(){
        return "It's like duct tape but for your body.";
    }
    
    public String getName(){
        return "Bandage";
    }

    public void render(Graphics2D g, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}