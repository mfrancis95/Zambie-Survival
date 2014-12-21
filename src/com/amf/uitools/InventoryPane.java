/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.amf.uitools;

import com.amf.engine.GridMap;
import com.amf.engine.Location;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InventoryPane<T> extends JPanel{
    /**
     * Total number of items in inventory
     */
    public static final int TOTAL_ITEMS = 9;
    
    /**
     * Inventory of selected entity.
     */
    private Object[] inventory;
    
    /**
     * Where each item will be displayed.
     */
    private JButton[] selectors;
    
    /**
     * Location of the current entity.
     */
    private Location currentLocation;
    
    /**
     * Entity at the current location.
     */
    private T entity;
    
    public InventoryPane(){
        init();
    }
        
    private void init(){
        inventory = new Object[TOTAL_ITEMS];
        selectors = new JButton[TOTAL_ITEMS];
        
        for(int i=0;i<TOTAL_ITEMS;i++){
            selectors[i] = new JButton();
        }
    }  
    
    private void addActions(){

    }
    
    /**
     * Sets the current entity and location.
     * When your mouse clicks on a location on the grid it should set
     * reset the Entity in the InventoryPane
     * @param l 
     * @param entity 
     */
    public void setEntity(Location l, T entity){
        currentLocation = l;
    }
    
    
    
}
