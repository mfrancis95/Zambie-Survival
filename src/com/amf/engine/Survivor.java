package com.amf.engine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Survivor extends Entity {

    //can it ever be full? Money/credit system???
    private Inventory inventory;
    
    public String[] getInventory() {
        return inventory.arrayOfInventory();
    }
    
    @Override
    public void performAction(String action) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        Location location = getScreenLocation();
        g.fillOval(location.x, location.y, 32, 32);
        if (isSelected()) {
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(2));
            g.drawOval(location.x, location.y, 32, 32);
        }
    }

    private class Inventory {

        /* int representing the number of items of a certain kind
         *defaulted to 10 unique items. Each item can go in specific spot.
         *if no like, can switch back to HashMap. This way seems easier to handle 
         given we have an invariant of which item goes where */
        int[] inventory = new int[10];

        public void addToInventory(int index) {
            inventory[index] += 1;
        }
        
        public String[] arrayOfInventory() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        public boolean containsItem(int index) {
            return inventory[index] != 0;
        }
        
        //Assumes contains item was called already because idk if we 
        //want to do error handling
        public void removeItem(int index) {
            inventory[index] -= 1;
        }

    }

}
