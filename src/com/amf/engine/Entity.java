package com.amf.engine;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    
    private Direction direction;
    
    private int health = 100;
    
    private Location screenLocation;
    
    public Direction getDirection() {
        return direction;
    }

    public int getHealth() {
        return health;
    }

    public Location getScreenLocation() {
        return screenLocation;
    }
    
    public abstract void performAction(String action);
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // or just this.health = health depending on damage calculations
    public void setHealth(int change) {
        health = Math.max(0, health - change);
    }

    public void setScreenLocation(Location location) {
        screenLocation = location;
    }
    
}
