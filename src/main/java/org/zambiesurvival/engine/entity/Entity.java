package main.java.org.zambiesurvival.engine.entity;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.Direction;
import main.java.org.zambiesurvival.engine.Inventory;
import main.java.org.zambiesurvival.engine.Location;
import main.java.org.zambiesurvival.engine.state.WorldState;

public abstract class Entity {
    
    protected final int actions;
    
    protected Direction destination, direction = Direction.SOUTH;
    
    protected int health = 100;
    
    protected final Inventory inventory;
    
    protected Location mapLocation, worldLocation;
    
    protected boolean moving, passing;
    
    public Entity() {
        this(0, 0);
    }
    
    public Entity(int actions) {
        this(actions, 0);
    }
    
    public Entity(int actions, int inventorySlots) {
        this.actions = actions;
        inventory = new Inventory(inventorySlots);
    }
    
    public void endAction() {
        destination = null;
        moving = false;
        passing = false;
    }
    
    public int getActions() {
        return actions;
    }
    
    public Direction getDestination() {
        return destination;
    }
    
    public Direction getDirection() {
        return direction;
    }

    public int getHealth() {
        return health;
    }
    
    public Inventory getInventory() {
        return inventory;
    }
    
    public Location getMapLocation() {
        return mapLocation;
    }

    public Location getWorldLocation() {
        return worldLocation;
    }
    
    public boolean isMoving() {
        return moving;
    }
    
    public boolean isPassing() {
        return passing;
    }
    
    public boolean move(WorldState world) {
        if (destination == null) {
            return false;
        }
        Location location = mapLocation.next(destination);
        if (location.x < 0 || location.x >= 20 || location.y < 0 || location.y >= 15 || world.getEntity(location) != null) {
            return false;
        }
        else {
            direction = destination;
            destination = null;
            mapLocation = mapLocation.next(direction);
            moving = true;
            return true;
        }
    }
    
    public void pass() {
        passing = true;
    }
    
    public abstract void render(Graphics2D g);
    
    public void setDestination(Direction direction) {
        destination = direction;
    }    
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    public void setMapLocation(Location location) {
        mapLocation = location;
    }

    public void setWorldLocation(Location location) {
        worldLocation = location;
    }
    
    public abstract void update(WorldState world);
    
}