package main.java.org.zambiesurvival.engine.decal;

import java.awt.Color;
import main.java.org.zambiesurvival.engine.Location;

public class HealingDecal extends FadingDecal{

    public HealingDecal(Location location) {
        super(location, 100, Color.RED);
    }
    
}