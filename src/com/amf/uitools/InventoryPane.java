package com.amf.uitools;

import com.amf.engine.Entity;
import com.amf.engine.Inventory;
import com.amf.engine.Location;
import java.awt.Dimension;
import java.util.ArrayList;

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
     * Entity at the current location.
     */
    private Entity entity;
    
    /**
     * Location to be selected if you need to use the item on something.
     */
    private Location selectedLocation;

    public InventoryPane() {
        super(3, SIZE, new Dimension(300,100), new MatrixDimension(1,3));
        
        inventory = new Inventory();
    }
    
    

    
    
}
