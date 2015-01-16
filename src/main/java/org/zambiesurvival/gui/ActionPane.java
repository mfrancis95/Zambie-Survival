/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.java.org.zambiesurvival.gui;

import java.awt.event.MouseEvent;
import main.java.org.zambiesurvival.engine.Location;

public class ActionPane extends UIPane{
    
    public static final int TYPES_OF_ENTITY_ACTIONS = 3;
    
    public ActionPane(Location placement, int size) {
        super(TYPES_OF_ENTITY_ACTIONS, size, new MatrixDimension(2,2), placement);
        
        this.setButton(0, new MoveButton(size, this));
        this.setButton(1, new AttackButton(size, this));
        this.setButton(2, new DoNothingButton(size, this));
    }
    
    public MoveButton getMoveButton(){
        return (MoveButton)this.getButton(0);
    }
    
    public AttackButton getAttackButton(){
        return (AttackButton)this.getButton(1);
    }
    
    public DoNothingButton getDoNothingButton(){
        return (DoNothingButton)this.getButton(3);
    }
    
    public class MoveButton extends GraphicButton{

        public MoveButton(int size, UIPane pane) {
            super(size, pane);
        }
        
        @Override
        public void executeWhenClicked(MouseEvent me){
            
        }              
    }
    
    public class AttackButton extends GraphicButton{

        public AttackButton(int size, UIPane pane) {
            super(size, pane);
        }
        
        @Override
        public void executeWhenClicked(MouseEvent me){
            
        }
    }
    
    public class DoNothingButton extends GraphicButton{

        public DoNothingButton(int size, UIPane pane) {
            super(size, pane);
        }
        
        @Override
        public void executeWhenClicked(MouseEvent me){
            
        }
    }
}
