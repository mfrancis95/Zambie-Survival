package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.entity.Entity;
import main.java.org.zambiesurvival.engine.state.WorldState;

public abstract class WeaponItem extends Item {
    
    protected final int power, range;
    
    public WeaponItem(int maxQuantity, int quantity, int power, int range) {
        super(maxQuantity, quantity);
        this.power = power;
        this.range = range;
    }
    
    protected void attack(WorldState world, Entity entity) {
        //range
        entity.setHealth(Math.max(0, entity.getHealth() - power));
    }
    
}