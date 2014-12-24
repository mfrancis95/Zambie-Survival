package com.amf.engine;

public class BigGun extends Gun {

    @Override
    public void use(Survivor owner, Entity target, GridMap map) {
        super.use(owner, target, map);
        map.move(null, null); //screen location or map?
    }
    
}
