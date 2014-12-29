package main.java.org.zambiesurvival.engine.entity;

import java.awt.Graphics2D;
import main.java.org.zambiesurvival.engine.ImageSheet;
import main.java.org.zambiesurvival.engine.Location;
import main.java.org.zambiesurvival.engine.WorldState;

public class Survivor extends Entity {
    
    private int animationTicks = (int) (Math.random() * 60);
    
    private ImageSheet sprites = ImageSheet.get("Survivor.png");
    
    public Survivor() {
        super(3);
    }

    public void render(Graphics2D g) {
        int x;
        if (animationTicks++ < 30) {
            x = 0;
        }
        else if (animationTicks < 60) {
            x = 2;
        }
        else {
            x = 0;
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
        g.drawImage(sprites.getImage(new Location(x, y)), worldLocation.x, worldLocation.y, null);
    }

    public void update(WorldState world) {
        if (destination != null) {
            move(world, destination);
            destination = null;
        }
    }

}