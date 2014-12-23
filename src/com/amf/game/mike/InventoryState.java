package com.amf.game.mike;

import com.amf.engine.Game;
import com.amf.engine.GameStateAdapter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class InventoryState extends GameStateAdapter {
    
    private boolean iKeyReleased;
    
    public void init() {
        iKeyReleased = false;
    }
    
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_I) {
            iKeyReleased = true;
        }
    }
    
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 720, 480);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("Your Inventory", 70, 100);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Press I to return to the game", 230, 450);
    }
    
    public void update(Game game) {
        if (iKeyReleased) {
            game.enterState("Main Game", false);
        }
    }
    
}