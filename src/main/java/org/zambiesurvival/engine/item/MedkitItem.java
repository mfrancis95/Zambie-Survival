package main.java.org.zambiesurvival.engine.item;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.WorldState;
import main.java.org.zambiesurvival.engine.entity.Entity;
import main.java.org.zambiesurvival.engine.entity.Survivor;

public class Medkit extends HealingItem {
    
    private int radius = 1;

    public Medkit(int power) {
        super(power);
    }

    public void heal(Survivor user, Entity target, WorldState world) {
        //heal entire radius
    }

    public void render(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(WorldState world) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}