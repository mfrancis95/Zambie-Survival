package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.Location;
import main.java.org.zambiesurvival.engine.state.WorldState;
import main.java.org.zambiesurvival.engine.entity.Entity;

public abstract class SpawningItem extends Item {
    
    protected final Entity entity;
    
    public SpawningItem(int maxQuantity, Entity entity) {
        super(maxQuantity, 1);
        this.entity = entity;
    }
    
    public void spawn(WorldState world, Location location) {
        world.addEntity(location, entity);
    }

}