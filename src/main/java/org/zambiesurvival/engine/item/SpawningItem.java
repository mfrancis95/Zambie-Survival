package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.Location;
import main.java.org.zambiesurvival.engine.state.WorldState;
import main.java.org.zambiesurvival.engine.entity.Entity;
import static main.java.org.zambiesurvival.engine.item.ArmorItem.description;
import static main.java.org.zambiesurvival.engine.item.ArmorItem.name;

public abstract class SpawningItem extends Item {
    
    public static final String name = "Spawning Item";
    public static final String description = "???";
    
    protected final Entity entity;
    
    public SpawningItem(int maxQuantity, Entity entity) {
        super(maxQuantity, 1);
        this.entity = entity;
    }
    
    public void spawn(WorldState world, Location location) {
        world.addEntity(location, entity);
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }

}