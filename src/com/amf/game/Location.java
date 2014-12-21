package com.amf.game;

public class Location {
    
    public final int x, y;
    
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Location add(int x, int y) {
        return new Location(this.x + y, this.y + y);
    }

    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + x;
        hash = 47 * hash + y;
        return hash;
    }
    
}