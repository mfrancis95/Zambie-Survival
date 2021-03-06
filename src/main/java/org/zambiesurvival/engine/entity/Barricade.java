package main.java.org.zambiesurvival.engine.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.state.WorldState;

public class Barricade extends Entity {
    
    public Barricade() {
        super();
    }

    public void render(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.fillRect(worldLocation.x + 4, worldLocation.y + 16, 24, 16);
    }

    public void update(WorldState world) {}
    
}