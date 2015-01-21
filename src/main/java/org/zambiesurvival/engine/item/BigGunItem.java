package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.entity.Entity;
import main.java.org.zambiesurvival.engine.state.WorldState;

public class BigGunItem extends GunItem {

    public BigGunItem(int maxQuantity, int quantity) {
        super(maxQuantity, quantity, 15, 3);
    }
    
    public String getDescription(){
        return "I bet this is to compensate for something.";
    }
    
    public String getName(){
        return "Big Gun";
    }
    
    public void shoot(WorldState world, Entity entity) {
        super.shoot(world, entity);
        //move dis bitch
    }
    
}