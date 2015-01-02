package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.entity.Entity;

public abstract class Item extends Entity {
    
    protected int defense, power;

    public Item() {
        super(0);
    }
    
    public int getDefense() {
        return defense;
    }
    
    public int getPower() {
        return power;
    }
    
    public void setDefense(int defense) {
        this.defense = defense;
    }
    
    public void setPower(int power) {
        this.power = power;
    }
    
}