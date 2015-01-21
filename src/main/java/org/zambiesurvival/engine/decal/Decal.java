package main.java.org.zambiesurvival.engine.decal;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.Location;

public abstract class Decal {
    
    protected int lifespan;
    
    protected Location location;
    
    public Decal(Location location, int lifespan) {
        this.location = location;
        this.lifespan = lifespan;
    }
    
    public int getLifespan() {
        return lifespan;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public boolean isDestroyed() {
        return lifespan == 0;
    }
    
    public abstract void render(Graphics2D g);
    
    public void setLocation(Location location) {
        this.location = location;
    }

    public void update() {
        lifespan--;
    }
    
}