package main.java.org.zambiesurvival.engine.state;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import main.java.org.zambiesurvival.engine.Game;

public interface GameState extends KeyListener, MouseListener, MouseMotionListener {
    
    void init();
    
    void render(Graphics2D g);
    
    void update(Game game);
    
}