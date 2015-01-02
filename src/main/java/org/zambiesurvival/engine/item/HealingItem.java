package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.state.WorldState;
import main.java.org.zambiesurvival.engine.entity.Survivor;

public abstract class HealingItem extends Item {
    
    protected final int power;
    
    public HealingItem(int maxQuantity, int quantity, int power) {
        super(maxQuantity, quantity);
        this.power = power;
    }
    
    public void heal(WorldState world, Survivor survivor) {
        survivor.setHealth(Math.min(100, survivor.getHealth() + power));
    }

}