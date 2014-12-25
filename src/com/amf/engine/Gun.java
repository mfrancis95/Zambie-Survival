package com.amf.engine;

public class Gun implements Item {
    
    public static final int DAMAGE = 15, MAX_QUANTITY = 1;

    @Override
    public void use(Survivor owner, Entity target, GridMap map) {
        if (owner.hasItemInInventory(this)/* && isValid*/) {
            target.setHealth(Math.max(0, target.getHealth() + DAMAGE));
            owner.removeItemFromInventory(this);
        }
    }

}
