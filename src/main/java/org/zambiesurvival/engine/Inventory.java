package main.java.org.zambiesurvival.engine;

import main.java.org.zambiesurvival.engine.item.Item;

public class Inventory {
    
    private final Item[] items;
    
    public Inventory(int slots) {
        items = new Item[slots];
    }
    
    public void addItem(Item item) {
        items[0] = item;
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