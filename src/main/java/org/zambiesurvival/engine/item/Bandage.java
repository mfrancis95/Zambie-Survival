package main.java.org.zambiesurvival.engine.item;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.WorldState;
import main.java.org.zambiesurvival.engine.entity.Entity;
import main.java.org.zambiesurvival.engine.entity.Survivor;

public class Bandage extends HealingItem {

    public Bandage(int power) {
        super(power);
    }

    public void heal(Survivor user, Entity target, WorldState world) {
        user.setHealth(Math.min(MAX_HEALTH, user.getHealth() + power));
    }
    
    public void render(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(WorldState world) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}