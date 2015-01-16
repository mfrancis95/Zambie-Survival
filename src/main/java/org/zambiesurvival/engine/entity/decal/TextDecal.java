/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.java.org.zambiesurvival.engine.entity.decal;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.gui.GraphicTextDecal;

/**
 *
 * @author Nelnel33
 */
public class TextDecal extends Decal{
    
    public final GraphicTextDecal graphicTextDecal;

    public TextDecal(GraphicTextDecal decal, int timeOnScreen) {
        super(timeOnScreen);
        
        this.graphicTextDecal = decal;
    }

    @Override
    public void render(Graphics2D g) {
        graphicTextDecal.render(g);
    }
    
}
