package com.amf.engine;

public class Barricade extends Entity implements Item {
    
    public static final int MAX_QUANTITY = 5;

    @Override
    public Direction getDirection() {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    @Override
    public void performAction(String action) {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    @Override
    public void setDirection(Direction direction) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void use(Survivor owner, Entity target, GridMap map) {
        //map.addTile(null, MAX_QUANTITY, MAX_QUANTITY);
    }
    
}
