package com.amf.engine;

import java.io.Serializable;

public class Entity implements Serializable {
    
    private Location screenLocation;
    
    public Location getScreenLocation() {
        return screenLocation;
    }
    
    public void setScreenLocation(Location location) {
        screenLocation = location;
    }
    
}