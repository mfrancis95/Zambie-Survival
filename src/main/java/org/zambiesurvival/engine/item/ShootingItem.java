package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.entity.Entity;
import main.java.org.zambiesurvival.engine.state.WorldState;

public abstract class ShootingItem extends WeaponItem {

    public ShootingItem(int maxQuantity, int quantity, int power, int range) {
        super(maxQuantity, quantity, power, range);
    }
    
    public void shoot(WorldState world, Entity entity) {
        attack(world, entity);
    }
    
}