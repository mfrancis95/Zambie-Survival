package main.java.org.zambiesurvival.engine.item;

<<<<<<< HEAD
import main.java.org.zambiesurvival.engine.WorldState;
=======
import main.java.org.zambiesurvival.engine.state.WorldState;
>>>>>>> master
import main.java.org.zambiesurvival.engine.entity.Survivor;

public abstract class HealingItem extends Item {
    
<<<<<<< HEAD
=======
    protected final int power;
    
>>>>>>> master
    public HealingItem(int maxQuantity, int power) {
        super(maxQuantity);
        this.power = power;
    }
    
    public void heal(WorldState world, Survivor survivor) {
<<<<<<< HEAD
        survivor.setHealth(Math.min(survivor.MAX_HEALTH, survivor.getHealth() + power));
    }
    
=======
        survivor.setHealth(Math.max(100, survivor.getHealth() + power));
    }

>>>>>>> master
}