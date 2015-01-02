package main.java.org.zambiesurvival.engine.item;

import java.awt.Color;
import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.state.WorldState;

public class MedkitItem extends HealingItem {

    public MedkitItem() {
        super(1, 1, 25);
    }

    public void render(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void render(Graphics2D g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillRect(x + 8, y + 8, 16, 16);
        g.setColor(Color.BLACK);
        g.drawRect(x + 8, y + 8, 16, 16);
        g.setColor(Color.RED);
        g.fillRect(x + 12, y + 12, 8, 8);
    }

    public void update(WorldState world) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}