package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.WorldState;
import main.java.org.zambiesurvival.engine.entity.Entity;
import main.java.org.zambiesurvival.engine.entity.Survivor;

public abstract class Item {
    
    private int defense, power;

    public abstract void use(Survivor owner, Entity target, WorldState world);
    
}