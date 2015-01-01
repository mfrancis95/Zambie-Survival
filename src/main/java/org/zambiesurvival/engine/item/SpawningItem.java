package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.WorldState;
import main.java.org.zambiesurvival.engine.entity.Entity;

public abstract class SpawningItem extends Item {
    
    protected Entity entity;

    public SpawningItem(int maxQuantity, Entity entity) {
        super(maxQuantity);
        this.entity = entity;
    }
    
    public void spawn(WorldState world, Entity entity) {
        world.addEntity(entity.getMapLocation(), entity);
    }

}