package com.amf.engine;

import java.io.Serializable;

public class Location implements Serializable {
    
    public final int x, y;
    
    public Location() {
        this(0, 0);
    }
    
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Location add(int x, int y) {
        return new Location(this.x + x, this.y + y);
    }
    
    public Direction directionTo(Location location) {
        if (x == location.x) {
            if (y == location.y) {
                return null;
            }
            else {
                return y > location.y ? Direction.SOUTH : Direction.NORTH;
            }
        }
        else if (y == location.y) {
            return x > location.x ? Direction.WEST : Direction.EAST;
        }
        else {
            return null;
        }
    }
    
    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            Location location = (Location) obj;
            return x == location.x && y == location.y;
        }
        return false;
    }

    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + x;
        hash = 47 * hash + y;
        return hash;
    }
    
    public boolean isAdjacentTo(Location location) {
        return Math.abs(x - location.x) + Math.abs(y - location.y) == 1;
    }
    
}