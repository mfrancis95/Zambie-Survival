package main.java.org.zambiesurvival.engine;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class GameStateAdapter implements GameState {
    
    public void init() {}
    
    public void keyPressed(KeyEvent ke) {}
    
    public void keyReleased(KeyEvent ke) {}
    
    public void keyTyped(KeyEvent ke) {}
    
    public void mouseClicked(MouseEvent me) {}
    
    public void mouseDragged(MouseEvent me) {}
    
    public void mouseEntered(MouseEvent me) {}
    
    public void mouseExited(MouseEvent me) {}
    
    public void mouseMoved(MouseEvent me) {}
    
    public void mousePressed(MouseEvent me) {}
    
    public void mouseReleased(MouseEvent me) {}
    
    public void render(Graphics2D g) {}
    
    public void update(Game game) {}
    
}