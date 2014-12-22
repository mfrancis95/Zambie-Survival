package com.amf.engine;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface GameState extends KeyListener, MouseListener, MouseMotionListener {
    
    void init();
    
    void render(Graphics2D g);
    
    void update(Game game);
    
}