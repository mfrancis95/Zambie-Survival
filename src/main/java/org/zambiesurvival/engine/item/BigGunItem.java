package main.java.org.zambiesurvival.engine.item;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.entity.Entity;
import main.java.org.zambiesurvival.engine.state.WorldState;

public class BigGunItem extends GunItem {

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
    
}