package main.java.org.zambiesurvival.engine;

import main.java.org.zambiesurvival.engine.entity.Survivor;
import main.java.org.zambiesurvival.engine.entity.Entity;
import main.java.org.zambiesurvival.engine.entity.Zambie;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.org.zambiesurvival.engine.entity.Barricade;

public class WorldState extends GameStateAdapter {

    private int currentEntity, currentEntityActions;

    private List<Entity> entities;

    private Map<Location, Location> tiles;
    
    private ImageSheet tileset;

    public final int tileSize;

    public WorldState(int tileSize) {
        this.tileSize = tileSize;
    }

    public void addEntity(Location location, Entity entity) {
        entity.setMapLocation(location);
        entity.setWorldLocation(new Location(location.x * tileSize, location.y * tileSize - 8));
        entities.add(entity);
    }

    public Entity getEntity(Location location) {
        for (Entity entity : entities) {
            if (entity.getMapLocation().equals(location)) {
                return entity;
            }
        }
        return null;
    }

    public void init() {
        currentEntity = currentEntityActions = 0;
        tileset = ImageSheet.load("Tileset.png", 32);
        ImageSheet.load("Survivor.png", 32);
        ImageSheet.load("Zambies.png", 32);
        entities = new ArrayList<>();
        addEntity(new Location(5, 7), new Survivor());
        addEntity(new Location(14, 7), new Survivor());
        addEntity(new Location(6, 7), new Barricade());
        addEntity(new Location(13, 7), new Barricade());
        for (int i = 0; i < 10; i++) {
            Location location = new Location((int) (Math.random() * 20), (int) (Math.random() * 15));
            while (getEntity(location) != null) {
                location = new Location((int) (Math.random() * 20), (int) (Math.random() * 15));
            }
            addEntity(location, new Zambie());
        }
        tiles = new HashMap<>();
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 15; y++) {
                tiles.put(new Location(x, y), new Location((int) (Math.random() * 6), (int) (Math.random() * 10)));
            }
        }
    }

    public void keyPressed(KeyEvent ke) {
        Entity entity = entities.get(currentEntity);
        if (entity instanceof Survivor) {
            switch (ke.getKeyCode()) {
                case KeyEvent.VK_UP:
                    entity.setDestination(Direction.NORTH);
                    break;
                case KeyEvent.VK_DOWN:
                    entity.setDestination(Direction.SOUTH);
                    break;
                case KeyEvent.VK_LEFT:
                    entity.setDestination(Direction.WEST);
                    break;
                case KeyEvent.VK_RIGHT:
                    entity.setDestination(Direction.EAST);
                    break;
                case KeyEvent.VK_P:
                    entity.pass();
                default:
            }
        }
    }

    public void render(Graphics2D g) {
        for (Location location : tiles.keySet()) {
            g.drawImage(tileset.getImage(tiles.get(location)), location.x * tileSize, location.y * tileSize, null);
        }
        g.setColor(Color.GRAY);
        for (int x = 0; x < 640; x += tileSize) {
            g.drawLine(x, 0, x, 480);
        }
        for (int y = 0; y < 480; y += tileSize) {
            g.drawLine(0, y, 640, y);
        }
        Location location = entities.get(currentEntity).getWorldLocation();
        g.setColor(Color.WHITE);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g.fillOval(location.x - 16, location.y - 8, tileSize + 32, tileSize + 32);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        for (Entity entity : entities) {
            location = entity.getWorldLocation();
            g.setColor(Color.BLACK);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g.fillOval(location.x + 4, location.y + 20, tileSize - 8, tileSize / 2);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
            entity.render(g);
        }
    }

    public void update(Game game) {
        Entity entity = entities.get(currentEntity);
        if (currentEntityActions < entity.getActions()) {
            if (entity.isMoving()) {
                Location mapLocation = entity.getMapLocation();
                Location screenLocation = entity.getWorldLocation();
                int destinationScreenX = mapLocation.x * 32;
                int destinationScreenY = mapLocation.y * 32 - 8;
                if (screenLocation.x < destinationScreenX) {
                    entity.setWorldLocation(screenLocation.add(1, 0));
                } 
                else if (screenLocation.x > destinationScreenX) {
                    entity.setWorldLocation(screenLocation.add(-1, 0));
                }
                if (screenLocation.y < destinationScreenY) {
                    entity.setWorldLocation(screenLocation.add(0, 1));
                } 
                else if (screenLocation.y > destinationScreenY) {
                    entity.setWorldLocation(screenLocation.add(0, -1));
                }
                if (screenLocation.x == destinationScreenX && screenLocation.y == destinationScreenY) {
                    entity.endAction();
                    currentEntityActions++;
                }      
            }
            else if (entity.isPassing()) {
                entity.endAction();
                currentEntityActions = Integer.MAX_VALUE;
            }
            else {
                entity.update(this);
            } 
        }
        else {
            currentEntity = currentEntity == entities.size() - 1 ? 0 : currentEntity + 1;
            while (entities.get(currentEntity).getActions() == 0) {
                currentEntity = currentEntity == entities.size() - 1 ? 0 : currentEntity + 1;
            }
            currentEntityActions = 0;
        }
    }

}