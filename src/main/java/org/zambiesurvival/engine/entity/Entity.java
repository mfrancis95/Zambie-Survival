package main.java.org.zambiesurvival.engine.entity;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.Direction;
import main.java.org.zambiesurvival.engine.Location;
import main.java.org.zambiesurvival.engine.WorldState;
import main.java.org.zambiesurvival.engine.item.Item;

public abstract class Entity {
    
    protected final int actions;
    
    protected Direction destination, direction = Direction.SOUTH;
    
    protected int health = 100;
    
    protected Inventory inventory = new Inventory();
    
    protected Location mapLocation, screenLocation;
    
    protected boolean moving, passing;
    
    public final int MAX_HEALTH = 100;
    
    public Entity(int actions) {
        this.actions = actions;
    }
    
    public void endAction() {
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
    
    public Location getMapLocation() {
        return mapLocation;
    }

    public Location getScreenLocation() {
        return screenLocation;
    }
    
    public boolean isMoving() {
        return moving;
    }
    
    public boolean isPassing() {
        return passing;
    }
    
    public boolean move(WorldState world, Direction direction) {
        Location location = mapLocation.next(direction);
        if (location.x < 0 || location.x >= 20 || location.y < 0 || location.y >= 15 || world.getEntity(location) != null) {
            return false;
        }
        else {
            this.direction = direction;
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

    public void setScreenLocation(Location location) {
        screenLocation = location;
    }
    
    public abstract void update(WorldState world);
    
    protected class Inventory {
        
        //Set to 11 for now since the designnotes has 11 unique items
        //Bandage, MedKit, Food, Drank, Gun, BigGun, Ammo, Melee Weapon, Armor, Barricade, Healthtotem
        private final int[] inventory = new int[11];
        
        public boolean addItem(Item item) {
            return false;
        }
        
        public boolean contains(Item item) {
            return inventory[getIndexOfItem(item)] != 0;
        }
        
        private int getIndexOfItem(Item item) {
            return 0;
        }
        
    }
    
}