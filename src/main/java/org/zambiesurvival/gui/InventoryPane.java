package main.java.org.zambiesurvival.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import main.java.org.zambiesurvival.engine.Inventory;
import main.java.org.zambiesurvival.engine.entity.Entity;
import main.java.org.zambiesurvival.engine.Location;
import main.java.org.zambiesurvival.engine.entity.Survivor;
import main.java.org.zambiesurvival.engine.item.Item;

public class InventoryPane extends UIPane{
    /**
     * Inventory of selected entity.
     */
    private Inventory inventory;
    
    /**
     * Survivor at the current location.
     */
    private Survivor survivor;

    public InventoryPane(Location placement, int size) {
        super(4,size, new MatrixDimension(2,2), placement);
        
        for(int i=0;i<super.getButtons().length;i++){
            super.getButtons()[i] = new InventoryButton(size);
        }
    }
    
    public void setSurvivor(Survivor s){
        survivor = s;
        inventory = s.getInventory();
    }
    
    public Entity getSurvivor(){
        return survivor;
    }
    
    @Override
    public void render(Graphics2D g, JPanel container){
        updateItems();
        super.render(g, container);
    }
    
    private void updateItems(){
        if(inventory == null){
            for(int i=0;i<super.getButtons().length;i++){
                ((InventoryButton)this.getButtons()[i]).setItem(null);
            }
        }
        else{
            inventory.checkForDepletion();
            for(int i=0;i<inventory.getSlots();i++){
                if(this.getButtons()[i] instanceof InventoryButton){
                    ((InventoryButton)this.getButtons()[i]).setItem(inventory.getItem(i));   
                }
                
            }
        }
    }
    
    public class InventoryButton extends GraphicButton{ 
        private Item item;
        
        public final int itemIconSize = 10;
        
        public InventoryButton(int size) {
            super(size);
        }            

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }
        
        @Override
        public void drawButtonIcon(Graphics2D g, Location l){
            super.drawButtonIcon(g, l);
            
            if(this.item != null){            
                int x = ((size-itemIconSize)/2) + l.x;
            
                int y = ((size-itemIconSize)/2) + l.y;
                    
                Location dl = new Location(x,y);//drawnLocation.
            
            
                g.setColor(Color.CYAN);//represents item;
                g.fill(new Rectangle2D.Double(x, y, itemIconSize, itemIconSize));
            }
        }
        
        @Override
        public void executeWhenClicked(){
            if(item != null){
                item.use();
                System.out.println("Item used: "+item.getQuantity());
            }
        }

        @Override
        public void executeWhenHovering(){
            //System.out.println("Hovering");
        }
        
    }
    
}
    
    

    
    

