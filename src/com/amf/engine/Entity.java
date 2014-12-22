package com.amf.engine;

import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Entity implements Serializable {
    
    private Direction direction = Direction.SOUTH;
    
    private int health = 100;
    
    private boolean moving, selected;
    
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
    
    public boolean isMoving() {
        return moving;
    }
    
    public boolean isSelected() {
        return selected;
    }
    
    public boolean move(GridMap map, Location location) {
        Location from = map.getLocation(this);
        if (!from.isAdjacentTo(location)) {
            selected = false;
            return false;
        }
        else if (map.getEntity(location) != null) {
            selected = false;
            return false;
        }
        else {
            map.move(from, location);
            moving = true;
            selected = false;
            return true;
        }
    }
    
    public abstract void performAction(String action);
    
    public void render(Graphics2D g) {}
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }

    public void setScreenLocation(Location location) {
        screenLocation = location;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public void update(GridMap map) {
        if (moving) {
            Location location = map.getLocation(this);
            int destinationScreenX = location.x * 32;
            int destinationScreenY = location.y * 32;
            if (screenLocation.x < destinationScreenX) {
                setScreenLocation(screenLocation.add(2, 0));
            } 
            else if (screenLocation.x > destinationScreenX) {
                setScreenLocation(screenLocation.add(-2, 0));
            }
            if (screenLocation.y < destinationScreenY) {
                setScreenLocation(screenLocation.add(0, 2));
            } 
            else if (screenLocation.y > destinationScreenY) {
                setScreenLocation(screenLocation.add(0, -2));
            }
            if (screenLocation.x == destinationScreenX && screenLocation.y == destinationScreenY) {
                moving = false;
            }
        }
    }
    
}