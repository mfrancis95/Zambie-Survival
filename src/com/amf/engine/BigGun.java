package com.amf.engine;

public class BigGun extends Gun {

    @Override
    public void use(Survivor owner, Entity target, GridMap map) {
        int originalAmount = owner.getAmountOfItem(this);
        super.use(owner, target, map);
        if (originalAmount != 0 && originalAmount != owner.getAmountOfItem(this)) {
            map.move(null, null); //screen location or map?
        }
    }
    
}
