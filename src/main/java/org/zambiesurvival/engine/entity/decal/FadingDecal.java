package main.java.org.zambiesurvival.engine.entity.decal;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.state.WorldState;

public class FadingDecal extends Decal {
    
    private float timeOnScreen;//should not be accessible by subclasses
    private Color background;
    
    public FadingDecal(float timeOnScreen, Color background) {
        super((int)timeOnScreen);
        this.timeOnScreen = timeOnScreen;
        this.background = background;
    }

    public void render(Graphics2D g) {
        g.setColor(background);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, health / timeOnScreen));
        g.fillOval(worldLocation.x, worldLocation.y, 8, 8);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
    
    public void update(WorldState world) {
        if (health % 4 == 0) {
            worldLocation = worldLocation.add(0, -1);
        }
        super.update(world);
    }
    
}