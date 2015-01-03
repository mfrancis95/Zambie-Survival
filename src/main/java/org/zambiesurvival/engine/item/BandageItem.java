package main.java.org.zambiesurvival.engine.item;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.state.WorldState;

public class BandageItem extends HealingItem {

    public BandageItem(int quantity) {
        super(5, quantity, 5);
    }

    public void render(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void render(Graphics2D g, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(WorldState world) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}