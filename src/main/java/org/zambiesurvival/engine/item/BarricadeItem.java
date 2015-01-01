package main.java.org.zambiesurvival.engine.item;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.WorldState;
import main.java.org.zambiesurvival.engine.entity.Barricade;

public class BarricadeItem extends SpawningItem {
    
    public BarricadeItem(int maxQuantity) {
        super(maxQuantity, new Barricade());
    }

    public void render(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(WorldState world) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}