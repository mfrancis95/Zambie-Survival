package main.java.org.zambiesurvival.game.mike;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import main.java.org.zambiesurvival.engine.Game;
import main.java.org.zambiesurvival.engine.GameStateAdapter;

public class TitleState extends GameStateAdapter {
    
    private boolean keyReleased;
    
    public void init() {
        keyReleased = false;
    }
    
    public void keyReleased(KeyEvent ke) {
        keyReleased = true;
    }
    
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 720, 480);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("Zambie Survival", 170, 200);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Press any key to start", 230, 300);
    }
    
    public void update(Game game) {
        if (keyReleased) {
            game.enterState("World", true);
        }
    }
    
}