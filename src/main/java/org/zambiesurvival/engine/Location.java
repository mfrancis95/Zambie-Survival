package main.java.org.zambiesurvival.engine;

import java.io.Serializable;

public class Location implements Serializable {
    
    public final int x, y;
    
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }    
    
    public Location add(Location location) {
        return new Location(x + location.x, y + location.y);
    }
    
    public Location add(int x, int y) {
        return new Location(this.x + x, this.y + y);
    }
    
    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            Location location = (Location) obj;
            return x == location.x && y == location.y;
        }
        else {
            return false;
        }
    }

    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + x;
        hash = 47 * hash + y;
        return hash;
    }
    
    public Location next(Direction direction) {
        switch (direction) {
            case NORTH:
                return add(0, -1);
            case SOUTH:
                return add(0, 1);
            case EAST:
                return add(1, 0);
            case WEST:
                return add(-1, 0);
            default:
                return null;
        }
    }
    
    public Location subtract(Location location) {
        return new Location(x - location.x, y - location.y);
    }
    
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
    
}