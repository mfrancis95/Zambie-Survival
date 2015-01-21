package main.java.org.zambiesurvival.engine.item;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.entity.Barricade;

public class BarricadeItem extends SpawningItem {

    public BarricadeItem() {
        super(1, new Barricade());
    }

    public void render(Graphics2D g, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String getName(){
        return "Barricade";
    }
    
    public String getDescription(){
        return "I've always wondered, why don't the Zambies just climb over this?";
    }

}