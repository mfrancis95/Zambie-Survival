package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.entity.Entity;
import main.java.org.zambiesurvival.engine.state.WorldState;

public abstract class MeleeWeaponItem extends WeaponItem {

    public MeleeWeaponItem(int maxQuantity, int quantity, int power) {
        super(maxQuantity, quantity, power, 1);
    }

    public void hit(WorldState world, Entity entity) {
        attack(world, entity);
    }
    
}