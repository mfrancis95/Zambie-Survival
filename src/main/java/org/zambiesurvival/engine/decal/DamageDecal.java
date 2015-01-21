package main.java.org.zambiesurvival.engine.decal;

import java.awt.Color;
import main.java.org.zambiesurvival.engine.Location;

public class DamageDecal extends FadingDecal{

    public DamageDecal(Location location) {
        super(location, 100, Color.BLUE);
    }
    
}