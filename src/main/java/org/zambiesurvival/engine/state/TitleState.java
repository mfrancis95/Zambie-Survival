package main.java.org.zambiesurvival.engine.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import main.java.org.zambiesurvival.engine.Game;
import main.java.org.zambiesurvival.engine.Location;
import main.java.org.zambiesurvival.gui.GraphicTextDecal;

public class TitleState extends GameStateAdapter {
    
    private boolean keyReleased;
    
    private final GraphicTextDecal title = new GraphicTextDecal("Zambie Survival", 32, new Location(120, 160), true);
    private final GraphicTextDecal subtitle = new GraphicTextDecal("Press any key to start", 16, new Location(180, 260), true);
    
    public void init() {
        keyReleased = false;
    }
    
    public void keyReleased(KeyEvent ke) {
        keyReleased = true;
    }
    
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 720, 480);
        title.render(g);
        subtitle.render(g);
    }
    
    public void update(Game game) {
        if (keyReleased) {
            game.enterState("World", true);
        }
    }
    
}