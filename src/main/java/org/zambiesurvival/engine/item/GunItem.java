package main.java.org.zambiesurvival.engine.item;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.state.WorldState;

public class GunItem extends ShootingItem {

    public GunItem(int maxQuantity, int quantity) {
        super(maxQuantity, quantity, 10, 2);
    }
    
    protected GunItem(int maxQuantity, int quantity, int power, int range) {
        super(maxQuantity, quantity, power, range);
    }

    @Override
    public void render(Graphics2D g, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void render(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(WorldState world) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}