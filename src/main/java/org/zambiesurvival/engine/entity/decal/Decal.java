package main.java.org.zambiesurvival.engine.entity.decal;

import main.java.org.zambiesurvival.engine.entity.Entity;
import main.java.org.zambiesurvival.engine.state.WorldState;

public abstract class Decal extends Entity {
    
    public Decal(int health) {
        this.health = health;
    }

    public void update(WorldState world) {
        health--;
        if (health <= 0) {
            destroyed = true;
        }
    }
    
}