package com.amf.engine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Survivor extends Entity {

    //can it ever be full? Money/credit system???
    private Inventory inventory;

    public static final int MAX_HEALTH = 100;
    
    public void addItemToInventory(Item item) {
        inventory.addToInventory(item);
    }

    public int getAmountOfItem(Item item) {
        return inventory.amountOfItem(item);
    }

    public int[] getInventory() {
        return inventory.arrayOfInventory();
    }

    public boolean hasItemInInventory(Item item) {
        return inventory.containsItem(item);
    }

    @Override
    public void performAction(Action action) {
        switch (action) {
            case ATTACK:
                break;
            case MOVE:
                break;
            case USE_ITEM:
                break;
            case DO_NOTHING:
            default:
                break;
        }
    }

    public void removeItemFromInventory(Item item) {
        inventory.removeItem(item);
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

        private final int[] inventory = new int[10];

        public void addToInventory(Item item) {
            int index = itemToInt(item);
            inventory[index] += 1;
        }
        
        public int amountOfItem(Item item) {
            int index = itemToInt(item);
            return inventory[index];
        }

        public int[] arrayOfInventory() {
            int[] copy = new int[inventory.length];
            System.arraycopy(inventory, 0, copy, 0, copy.length);
            return copy;
        }

        public boolean containsItem(Item item) {
            int index = itemToInt(item);
            return inventory[index] != 0;
        }

        private int itemToInt(Item item) {
            int convertTo = 0;
            if (item instanceof Gun) {
                convertTo = 1;
            } else if (item instanceof BigGun) {
                convertTo = 2;
            }
            else if (item instanceof Barricade) {
                convertTo = 3;
            }
            return convertTo;
        }

        //Assumes contains item was called already because idk if we 
        //want to do error handling
        public void removeItem(Item item) {
            int index = itemToInt(item);
            if (inventory[index] != 0) {
                inventory[index] -= 1;
            }
        }

    }

}
