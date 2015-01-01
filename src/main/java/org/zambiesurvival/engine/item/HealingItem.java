package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.WorldState;
import main.java.org.zambiesurvival.engine.entity.Survivor;

public abstract class HealingItem extends Item {
    
    public HealingItem(int maxQuantity, int power) {
        super(maxQuantity);
        this.power = power;
    }
    
    public void heal(WorldState world, Survivor survivor) {
        survivor.setHealth(Math.min(survivor.MAX_HEALTH, survivor.getHealth() + power));
    }
    
}