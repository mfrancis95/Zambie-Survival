package main.java.org.zambiesurvival.engine.item;

public abstract class ArmorItem extends Item {
    
    public static final String name = "Armor";
    public static final String description = "???";

    protected int defense;
    
    public ArmorItem(int maxQuantity, int quantity, int defense) {
        super(maxQuantity, quantity);
        this.defense = defense;
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }
    
}