package main.java.org.zambiesurvival.engine.item;

public abstract class ArmorItem extends Item {

    protected int defense;
    
    protected final boolean stackable = false;
    
    public ArmorItem() {
        super(1);
    }
    
}