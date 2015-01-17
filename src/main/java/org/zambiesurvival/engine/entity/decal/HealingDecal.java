/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.java.org.zambiesurvival.engine.entity.decal;

import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author Nelnel33
 */
public class HealingDecal extends FadingDecal{

    public HealingDecal() {
        super(100,Color.RED, new Dimension(16,16));//tilesize
    }
    
}
