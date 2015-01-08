package main.java.org.zambiesurvival.engine.entity.decal;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.state.WorldState;

public class HealingDecal extends Decal {
    
    public HealingDecal() {
        super(100);
    }

    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, health / 100f));
        g.fillOval(worldLocation.x, worldLocation.y, 8, 8);
    }
    
    public void update(WorldState world) {
        if (health % 4 == 0) {
            worldLocation = worldLocation.add(0, -1);
        }
        super.update(world);
    }
    
}