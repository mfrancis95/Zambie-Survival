package main.java.org.zambiesurvival.engine.entity.decal;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.state.WorldState;

public class FadingDecal extends Decal {
    
    private float timeOnScreen;//should not be accessible by subclasses
    private Color background;
    private Dimension size;
    
    public FadingDecal(float timeOnScreen, Color background, Dimension size) {
        super((int)timeOnScreen);
        this.timeOnScreen = timeOnScreen;
        this.background = background;
        this.size = size;
    }

    public void render(Graphics2D g) {
        g.setColor(background);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, health / timeOnScreen));
        g.fillOval(worldLocation.x, worldLocation.y, size.width, size.height);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
    
    public void update(WorldState world) {
        if (health % 4 == 0) {
            worldLocation = worldLocation.add(0, -1);
        }
        super.update(world);
    }
    
}