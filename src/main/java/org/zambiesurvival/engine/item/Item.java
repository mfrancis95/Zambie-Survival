package main.java.org.zambiesurvival.engine.item;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.entity.Entity;

public abstract class Item extends Entity {
    
    public final int maxQuantity;
    
    protected int quantity;

    public Item(int maxQuantity) {
        super();
        this.maxQuantity = maxQuantity;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public abstract void render(Graphics2D g, int x, int y);
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}