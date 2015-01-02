package main.java.org.zambiesurvival.engine.item;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.entity.Entity;

public abstract class Item extends Entity {
    
<<<<<<< HEAD
    protected int maxQuantity, power, quantity = 0;
    
    protected boolean stackable;

    public Item(int maxQuantity) {
        super(0);
=======
    public final int maxQuantity;
    
    protected int quantity;

    public Item(int maxQuantity) {
        super();
>>>>>>> master
        this.maxQuantity = maxQuantity;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
<<<<<<< HEAD
    public boolean isStackable() {
        return stackable;
    }
=======
    public abstract void render(Graphics2D g, int x, int y);
>>>>>>> master
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}