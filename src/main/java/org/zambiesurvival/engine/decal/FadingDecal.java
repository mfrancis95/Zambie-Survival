package main.java.org.zambiesurvival.engine.decal;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.Location;

public class FadingDecal extends Decal {
    
    protected final Color color;
    
    public FadingDecal(Location location, int lifespan, Color color) {
        super(location, lifespan);
        this.color = color;
    }

    public void render(Graphics2D g) {
        g.setColor(color);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, lifespan / 100f));
        g.fillOval(location.x, location.y, 8, 8);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
    
    public void update() {
        if (lifespan % 4 == 0) {
            location = location.add(0, -1);
        }
        super.update();
    }
    
}