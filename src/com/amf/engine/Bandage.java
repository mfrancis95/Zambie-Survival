package com.amf.engine;

public class Bandage implements Item {

    public static final int MAX_QUANTITY = 5, RECOVER = 10;

    @Override
    public void use(Survivor owner, Entity target, GridMap map) {
        if (owner.hasItemInInventory(this)/* && isValid*/) {
            owner.setHealth(Math.min(Survivor.MAX_HEALTH, owner.getHealth() + RECOVER));
            owner.removeItemFromInventory(this);
        }
    }

}
