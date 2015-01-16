package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.entity.Entity;
import static main.java.org.zambiesurvival.engine.item.ArmorItem.description;
import static main.java.org.zambiesurvival.engine.item.ArmorItem.name;
import main.java.org.zambiesurvival.engine.state.WorldState;

public abstract class ShootingItem extends WeaponItem {
    
    public static final String name = "Shooting Item";
    public static final String description = "???";

    public ShootingItem(int maxQuantity, int quantity, int power, int range) {
        super(maxQuantity, quantity, power, range);
    }
    
    public void shoot(WorldState world, Entity entity) {
        attack(world, entity);
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }
    
}