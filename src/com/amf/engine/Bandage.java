package com.amf.engine;

public class Bandage implements Item {

    public static final int MAX_QUANTITY = 5, RECOVER = 10;

    @Override
    public void use(Survivor owner, Entity target, GridMap map) {
        //if (ableToUseItem) {
            owner.setHealth(Math.min(Survivor.MAX_HEALTH, owner.getHealth() + RECOVER));
        //}
        if (owner.getInventory()[0] != 0) {
            owner.getInventory()[0] -= 1;
        }
    }

}
