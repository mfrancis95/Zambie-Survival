package main.java.org.zambiesurvival.engine.item;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.entity.Entity;
import static main.java.org.zambiesurvival.engine.item.ArmorItem.description;
import static main.java.org.zambiesurvival.engine.item.ArmorItem.name;
import main.java.org.zambiesurvival.engine.state.WorldState;

public class BigGunItem extends GunItem {
    
    public static final String name = "Big Gun";
    public static final String description = "I bet this is to compensate for something.";

    public BigGunItem(int maxQuantity, int quantity) {
        super(maxQuantity, quantity, 15, 3);
    }

    @Override
    public void render(Graphics2D g, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void render(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void shoot(WorldState world, Entity entity) {
        super.shoot(world, entity);
        //move dis bitch
    }

    @Override
    public void update(WorldState world) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }
    
}