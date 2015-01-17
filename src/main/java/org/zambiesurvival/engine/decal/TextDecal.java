package main.java.org.zambiesurvival.engine.decal;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.gui.GraphicTextDecal;

public class TextDecal extends Decal{
    
    public final GraphicTextDecal graphicTextDecal;

    public TextDecal(int lifespan, GraphicTextDecal decal) {
        super(lifespan);
        this.graphicTextDecal = decal;
    }

    public void render(Graphics2D g) {
        graphicTextDecal.render(g);
    }
    
}