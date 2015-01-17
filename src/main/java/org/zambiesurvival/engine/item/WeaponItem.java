package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.entity.Entity;
import static main.java.org.zambiesurvival.engine.item.ArmorItem.description;
import static main.java.org.zambiesurvival.engine.item.ArmorItem.name;
import main.java.org.zambiesurvival.engine.state.WorldState;

public abstract class WeaponItem extends Item {
    
    public static final String name = "Weapon";
    public static final String description = "???";
    
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
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }
    
}