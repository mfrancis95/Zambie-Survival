package main.java.org.zambiesurvival.engine.item;

public abstract class ArmorItem extends Item {

    protected int defense;
    
    public ArmorItem(int maxQuantity, int quantity, int defense) {
        super(maxQuantity, quantity);
        this.defense = defense;
    }
    
}