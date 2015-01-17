package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.state.WorldState;
import main.java.org.zambiesurvival.engine.entity.Survivor;
import static main.java.org.zambiesurvival.engine.item.ArmorItem.description;
import static main.java.org.zambiesurvival.engine.item.ArmorItem.name;

public abstract class HealingItem extends Item {
    
    public static final String name = "Healing Item";
    public static final String description = "???";
    
    protected final int power;
    
    public HealingItem(int maxQuantity, int quantity, int power) {
        super(maxQuantity, quantity);
        this.power = power;
    }
    
    public void heal(WorldState world, Survivor survivor) {
        survivor.setHealth(Math.min(100, survivor.getHealth() + power));
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }

}