package com.amf.engine;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;

public class Game implements KeyListener, MouseListener, MouseMotionListener {
    
    private GameState currentState;
    
    private Map<String, GameState> states;
    
    public Game() {
        init();
    }
    
    public void addState(String name, GameState state) {
        states.put(name, state);
    }
    
    public void enterState(String name, boolean init) {
        currentState = states.get(name);
        if (init) {
            currentState.init();
        }
    }
    
    public void init() {
        currentState = new GameStateAdapter() {};
        states = new HashMap<>();
    }
    
    public void keyPressed(KeyEvent ke) {
        currentState.keyPressed(ke);
    }
    
    public void keyReleased(KeyEvent ke) {
        currentState.keyReleased(ke);
    }
    
    public void keyTyped(KeyEvent ke) {
        currentState.keyTyped(ke);
    }
    
    public void mouseClicked(MouseEvent me) {
        currentState.mouseClicked(me);
    }
    
    public void mouseDragged(MouseEvent me) {
        currentState.mouseDragged(me);
    }
    
    public void mouseEntered(MouseEvent me) {
        currentState.mouseEntered(me);
    }
    
    public void mouseExited(MouseEvent me) {
        currentState.mouseExited(me);
    }
    
    public void mouseMoved(MouseEvent me) {
        currentState.mouseMoved(me);
    }
    
    public void mousePressed(MouseEvent me) {
        currentState.mousePressed(me);
    }
    
    public void mouseReleased(MouseEvent me) {
        currentState.mouseReleased(me);
    }
    
    public void render(Graphics2D g) {
        currentState.render(g);
    }
    
    public void update() {
        currentState.update(this);
    }
    
}