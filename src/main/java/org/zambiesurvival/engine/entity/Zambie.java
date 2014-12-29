package main.java.org.zambiesurvival.engine.entity;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.Direction;
import main.java.org.zambiesurvival.engine.ImageSheet;
import main.java.org.zambiesurvival.engine.Location;
import main.java.org.zambiesurvival.engine.WorldState;

public class Zambie extends Entity {
    
    private int animationTicks = (int) (Math.random() * 60), ticks;
    
    private final int spriteColumn = (int) (Math.random() * 4) * 3;
    
    private final ImageSheet sprites = ImageSheet.get("Zambies.png");
    
    public Zambie() {
        super(1);
    }
    
    public void render(Graphics2D g) {
        int x;
        if (animationTicks++ < 30) {
            x = spriteColumn;
        }
        else if (animationTicks < 60) {
            x = spriteColumn + 2;
        }
        else {
            x = spriteColumn;
            animationTicks = 0;
        }
        int y;
        switch (getDirection()) {
            case NORTH:
                y = 3;
                break;
            case EAST:
                y = 2;
                break;
            case SOUTH:
                y = 0;
                break;
            default:
                y = 1;
        }
        g.drawImage(sprites.getImage(new Location(x, y)), screenLocation.x, screenLocation.y, null);
    }
    
    public void update(WorldState world) {
        try {
            if (ticks++ == 60) {
                while (!move(world, Direction.values()[(int) (Math.random() * 5)])) {}
                ticks = 0;
            }
        }
        catch (Exception ex) {
            pass();
            ticks = 0;
        }
    }
    
}