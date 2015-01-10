package main.java.org.zambiesurvival.engine.item;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.entity.Barricade;
import main.java.org.zambiesurvival.engine.state.WorldState;

public class BarricadeItem extends SpawningItem {

    public BarricadeItem() {
        super(1, new Barricade());
    }

    public void render(Graphics2D g, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void render(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void update(WorldState world) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}