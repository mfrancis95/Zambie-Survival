package main.java.org.zambiesurvival.engine;

import main.java.org.zambiesurvival.engine.item.Item;

public class Inventory {
    
    private final Item[] items;
    
    public Inventory(int slots) {
        items = new Item[slots];
    }
    
    public boolean addItem(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                return true;
            }
        }
        return false;
    }
    
    public Item getItem(int index) {
       try {
           return items[index];
       }
       catch (ArrayIndexOutOfBoundsException ex) {
           return null;
       }
    }
    
    public int getSlots() {
        return items.length;
    }
    
}