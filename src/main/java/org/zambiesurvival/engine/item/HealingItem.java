package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.WorldState;
import main.java.org.zambiesurvival.engine.entity.Entity;
import main.java.org.zambiesurvival.engine.entity.Survivor;

public abstract class HealingItem extends Item {
    
    public HealingItem(int power) {
        this.power = power;
        defense = 0;
    }
    
    public abstract void heal(Survivor user, Entity target, WorldState world);

}