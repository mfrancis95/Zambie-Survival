/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.amf.uitools;

import com.amf.engine.Entity;
import com.amf.engine.Location;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InventoryPane extends JPanel implements ActionListener{
    /**
     * Total number of items in inventory
     */
    public static final int TOTAL_ITEMS = 3;
    
    /**
     * Inventory of selected entity.
     */
    private ArrayList inventory;
    
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
    private Entity entity;
    
    public InventoryPane(){
        init();
    }
        
    private void init(){
        setLayout(new GridLayout(1,3));
        inventory = new ArrayList();
        selectors = new JButton[TOTAL_ITEMS];
        
        for(int i=0;i<TOTAL_ITEMS;i++){
            selectors[i] = new JButton();
            add(selectors[i]);
        }
    }  
    
    private void addActions(){
        if(inventory.size() <= TOTAL_ITEMS && inventory.size() > 0){
            updateImages();
            for(int i=0;i<inventory.size();i++){     
                JButton b = selectors[i];
                b.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                    }                
                });
            }
            
        }        
        
        else{
            System.out.println("Does not work with current size: "+inventory.size());
        }        
    }
    
    private void updateImages(){
        for(int i=0;i<inventory.size();i++){
            //selectors[i].setText(inventory.get(i).getName()); or .getImage();                        
        }
    }
   
    /**
     * Sets the current entity and location.
     * When your mouse clicks on a location on the grid it should set
     * reset the Entity in the InventoryPane 
     * @param entity 
     */
    public void setEntity(Entity entity){
        this.entity = entity;
        //inventory = entity.inventory;
        addActions();
    }
    
    /**
     * Gets entity whose inventory is being displayed.
     * @return 
     */
    public Entity getEntity(){
        return entity;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
    
    
    
}
