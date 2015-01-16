package main.java.org.zambiesurvival.engine.item;

import java.awt.Graphics2D;
import static main.java.org.zambiesurvival.engine.item.ArmorItem.description;
import static main.java.org.zambiesurvival.engine.item.ArmorItem.name;
import main.java.org.zambiesurvival.engine.state.WorldState;

public class BandageItem extends HealingItem {
    
    public static final String name = "Bandage";
    public static final String description = "It's like duct tape but for your body.";

    public BandageItem(int quantity) {
        super(5, quantity, 5);
    }

    public void render(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void render(Graphics2D g, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(WorldState world) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }

}