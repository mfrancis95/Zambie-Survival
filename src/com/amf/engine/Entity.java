package com.amf.engine;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    private Location screenLocation;

    private int health;
    
    public Entity(Location startLocation, int maxHealth) {
        screenLocation = startLocation;
        health = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public Location getScreenLocation() {
        return screenLocation;
    }
    
    public abstract void performAction(String action);

    // or just this.health = health depending on damage calculations
    public void setHealth(int change) {
        health = Math.max(0, change);
    }

    public void setScreenLocation(Location location) {
        screenLocation = location;
    }
    
}
