package main.java.org.zambiesurvival.engine.item;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.entity.Entity;

public abstract class Item extends Entity {

    public final int MAX_QUANTITY;

    protected int quantity;

    public Item(int maxQuantity, int quantity) {
        super();
        MAX_QUANTITY = maxQuantity;
        this.quantity = quantity;
    }

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