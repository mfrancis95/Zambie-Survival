package main.java.org.zambiesurvival.engine.item;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.entity.Entity;

public abstract class Item {

    public final int MAX_QUANTITY;

    protected int quantity;

    public Item(int maxQuantity, int quantity) {
        MAX_QUANTITY = maxQuantity;
        this.quantity = quantity;
    }
    
    public abstract String getName();
    
    public abstract String getDescription();

    public int getQuantity() {
        return quantity;
    }

    public abstract void render(Graphics2D g, int x, int y);

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void effect(Entity entity){
    }

    public boolean use(Entity entity) {
        if (quantity > 0) {
            quantity--;
            effect(entity);
            return true;
        }
        return false;
    }
    
}