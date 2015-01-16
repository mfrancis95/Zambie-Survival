package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.entity.Entity;
import static main.java.org.zambiesurvival.engine.item.ArmorItem.description;
import static main.java.org.zambiesurvival.engine.item.ArmorItem.name;
import main.java.org.zambiesurvival.engine.state.WorldState;

public abstract class MeleeWeaponItem extends WeaponItem {
    
    public static final String name = "Melee Weapon";
    public static final String description = "???";

    public MeleeWeaponItem(int maxQuantity, int quantity, int power) {
        super(maxQuantity, quantity, power, 1);
    }

    public void hit(WorldState world, Entity entity) {
        attack(world, entity);
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }
    
}