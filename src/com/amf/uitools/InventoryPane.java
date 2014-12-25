package com.amf.uitools;

import com.amf.engine.Entity;
import com.amf.engine.Inventory;
import com.amf.engine.Location;
import com.amf.engine.Survivor;

public class InventoryPane extends UIPane{
    public static final int SIZE = 100;//temp can change in future.
    
    /**
     * Inventory of selected entity.
     */
    private Inventory inventory;
        
    /**
     * Location of the current entity.
     */
    private Location currentLocation;
    
    /**
     * Survivor at the current location.
     */
    private Survivor survivor;
    
    /**
     * Location to be selected if you need to use the item on something.
     */
    private Location selectedLocation;

    public InventoryPane(Location placement, int size) {
        super(3,size, new MatrixDimension(1,3), placement);
    }
    
    public void setSurvivor(Survivor s){
        survivor = s;
    }
    
    public Entity getSurvivor(){
        return survivor;
    }
    
    

    
    
}
