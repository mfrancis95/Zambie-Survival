package com.amf.engine;

public class Location {
    
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
    
}