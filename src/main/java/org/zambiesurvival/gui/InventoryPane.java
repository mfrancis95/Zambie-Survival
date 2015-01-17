package main.java.org.zambiesurvival.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import main.java.org.zambiesurvival.engine.Inventory;
import main.java.org.zambiesurvival.engine.Location;
import main.java.org.zambiesurvival.engine.entity.Entity;
import main.java.org.zambiesurvival.engine.entity.Survivor;
import main.java.org.zambiesurvival.engine.decal.TextDecal;
import main.java.org.zambiesurvival.engine.item.Item;

public class InventoryPane extends UIPane{
    /**
     * Inventory of selected entity.
     */
    private Inventory inventory;
    
    /**
     * Place into MainGame's MouseAdapter.
     * @param e 
     * @param container 
     */
    @Override
    public void mouseClicked(MouseEvent e, JPanel container) {
        super.mouseClicked(e,container);
    }

    /**
     * Place into MainGame's MouseAdapter.
     * @param e 
     * @param container 
     */
    @Override
    public void mouseMoved(MouseEvent e, JPanel container){
        super.mouseMoved(e,container);
    } 

    public InventoryPane(Location placement, int size) {
        super(4,size, new MatrixDimension(2,2), placement);
        
        for(int i=0;i<super.getTotalButtons();i++){
            super.setButton(i, new InventoryButton(size, this));
        }
    }
    
    @Override
    public void setSurvivor(Survivor s){
        inventory = s.getInventory();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    @Override
    public void render(Graphics2D g, JPanel container){
        updateItems();
        super.render(g, container);
    }
    
    private void updateItems(){
        if(inventory == null){
            for(int i=0;i<super.getTotalButtons();i++){
                ((InventoryButton)this.getButton(i)).setItem(null);
                ((InventoryButton)this.getButton(i)).setItemIndex(-1);
            }
        }
        else{
            inventory.checkForDepletion();
            for(int i=0;i<inventory.getSlots();i++){
                if(this.getButton(i) instanceof InventoryButton){
                    ((InventoryButton)this.getButton(i)).setItem(inventory.getItem(i));   
                    ((InventoryButton)this.getButton(i)).setItemIndex(i);
                }
                
            }
        }
    }
    
     /**
     * Each item use needs to decrement the entity who used the item's actions.
     * Not sure how to do that.
     * @param useOn 
     * @param me 
     */
    public void inventoryPaneManager(Entity useOn, MouseEvent me){
        if(this.hasSelectedButton()){
            if(useOn != null){
                this.getInventory().useItem(this.getCurrentItemIndex(), useOn);
                System.out.println(useOn.toString());       
                cancelOperation(me);
            }
        }
        if(useOn == null){
            cancelOperation(me);
        }   
    }
    
    public class InventoryButton extends GraphicButton{ 
        private Item item;
        private int itemIndex;
        
        public final int itemIconSize = 10;
        
        public InventoryButton(int size, InventoryPane pane) {
            super(size, pane);
            itemIndex = -1;
        }            

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public int getItemIndex() {
            return itemIndex;
        }

        public void setItemIndex(int itemIndex) {
            this.itemIndex = itemIndex;
        }
        
        @Override
        public void drawButtonIcon(Graphics2D g, Location l){
            super.drawButtonIcon(g, l);
            
            if(this.item != null){            
                int x = ((size-itemIconSize)/2) + l.x;
            
                int y = ((size-itemIconSize)/2) + l.y;
                    
                Location dl = new Location(x,y);//drawnLocation.
            
                g.setColor(Color.BLACK);//represents item;
                g.fill(new Rectangle2D.Double(x, y, itemIconSize, itemIconSize));
                g.drawString(""+item.getQuantity(), x, y);
            }
        }
        
        @Override
        public void executeWhenClicked(MouseEvent me){
            if(item != null && itemIndex != -1){
                InventoryPane.super.setHasSelectedButton(true);
                InventoryPane.super.setCurrentItemIndex(this.itemIndex);
            }
        }

        @Override
        public void executeWhenHovering(MouseEvent me){
            if(item != null && itemIndex != -1){
                int x = me.getX();
                int y = me.getY();
                
                
                String info = item.getName()+"."+"Amt: "+item.getQuantity()+"."+item.getDescription();
                GraphicTextDecal gt = new GraphicTextDecal(info, 8, new Location(x,y), Color.BLACK, false);
                TextDecal tt = new TextDecal(300, gt);
                this.setToolTip(tt);
            }
            else{
                this.setToolTip(null);
            }
        }
        
    }
    
}
    
    

    
    

