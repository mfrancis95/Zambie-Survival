package main.java.org.zambiesurvival.engine.item;

import main.java.org.zambiesurvival.engine.entity.Entity;

public abstract class Item extends Entity {
    
    protected int maxQuantity, power, quantity = 0;
    
    protected boolean stackable;

    public Item(int maxQuantity) {
        super(0);
        this.maxQuantity = maxQuantity;
    }
    
    public int getPower() {
        return power;
    }
    
    public boolean isStackable() {
        return stackable;
    }
    
    public void setPower(int power) {
        this.power = power;
    }
    
}