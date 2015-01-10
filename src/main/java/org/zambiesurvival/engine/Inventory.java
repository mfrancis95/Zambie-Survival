package main.java.org.zambiesurvival.engine;

import main.java.org.zambiesurvival.engine.item.BandageItem;
import main.java.org.zambiesurvival.engine.item.Item;

public class Inventory {
    
    private final Item[] items;
    
    public Inventory(int slots) {
        items = new Item[slots];
    }
    
    public boolean addItem(Item item) {
        boolean added = false;
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                return true;
            }
            else if (items[i].getClass().equals(item.getClass()) && item instanceof BandageItem) {
                int max = item.MAX_QUANTITY, newQuantity = items[i].getQuantity() + item.getQuantity();
                if (newQuantity == max) {
                    items[i].setQuantity(max);
                    return true;
                }
                if (newQuantity < max) {
                    items[i].setQuantity(newQuantity);
                    return true;
                }
                else {
                    item.setQuantity(max - items[i].getQuantity());
                    items[i].setQuantity(max);
                    added = true;
                }
            }
        }
        return added;
    }

    public Item getItem(int index) {
       try {
           return items[index];
       }
       catch (ArrayIndexOutOfBoundsException ex) {
           return null;
       }
    }
    
    public void checkForDepletion(){
        for(int i=0;i<items.length;i++){
            if(items[i] != null && items[i].getQuantity() == 0){
                items[i] = null;
            }
        }
    }
    
    public void useItem(int i){
       try {
           if(items[i] == null){
               System.out.println("Item is null");
           }
           else{
               items[i].use();
           }
       }
       catch (ArrayIndexOutOfBoundsException ex) {
           System.out.println("Out of bounds");
       }
    }
    
    public int getSlots() {
        return items.length;
    }
    
    public int getTotalItems() {
        int counter = 0;
        for(Item item: items){
            if(item != null){
                counter++;
            }
        }
        
        return counter;
    }
    
}
