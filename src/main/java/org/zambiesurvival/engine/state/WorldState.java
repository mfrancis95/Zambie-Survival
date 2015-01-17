package main.java.org.zambiesurvival.engine.state;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import main.java.org.zambiesurvival.engine.Direction;
import main.java.org.zambiesurvival.engine.Game;
import main.java.org.zambiesurvival.engine.ImageSheet;
import main.java.org.zambiesurvival.engine.Location;
import main.java.org.zambiesurvival.engine.entity.Barricade;
import main.java.org.zambiesurvival.engine.entity.Entity;
import main.java.org.zambiesurvival.engine.entity.Survivor;
import main.java.org.zambiesurvival.engine.entity.Zambie;
import main.java.org.zambiesurvival.engine.entity.decal.DamageDecal;
import main.java.org.zambiesurvival.engine.entity.decal.Decal;
import main.java.org.zambiesurvival.engine.entity.decal.FadingDecal;
import main.java.org.zambiesurvival.engine.entity.decal.HealingDecal;
import main.java.org.zambiesurvival.engine.entity.decal.TextDecal;
import main.java.org.zambiesurvival.engine.item.BandageItem;
import main.java.org.zambiesurvival.engine.item.BarricadeItem;
import main.java.org.zambiesurvival.engine.item.BigGunItem;
import main.java.org.zambiesurvival.engine.item.MedkitItem;
import main.java.org.zambiesurvival.gui.GraphicButton;
import main.java.org.zambiesurvival.gui.GraphicTextDecal;
import main.java.org.zambiesurvival.gui.InventoryPane;

public class WorldState extends GameStateAdapter {
    
    private Entity clickedEntity;
    
    private final Location inventoryPlacement = new Location(648,25);//change if necessary

    private int currentEntity, currentEntityActions;
    
    private List<Decal> decals;
    
    private List<Entity> entities;
    
    private InventoryPane inventoryPane;

    private Map<Location, Location> tiles;
    
    private ImageSheet tileset;

    public final int tileSize;

    public WorldState(int tileSize) {
        this.tileSize = tileSize;
    }
    
    public void addDecal(Location worldLocation, Decal decal) {
        decal.setWorldLocation(worldLocation);
        decals.add(decal);
    }
    
    public void addTextDecal(TextDecal decal){
        decal.setWorldLocation(decal.graphicTextDecal.getLocation());
        decals.add(decal);
    }

    public void addEntity(Location mapLocation, Entity entity) {
        entity.setMapLocation(mapLocation);
        entity.setWorldLocation(new Location(mapLocation.x * tileSize, mapLocation.y * tileSize - 8));
        entities.add(entity);
    }
    
    public Entity getClickedEntity() {
        return clickedEntity;
    }
    
    public Entity getEntityAtMouse(MouseEvent me){
        return getEntity(new Location(me.getX() / tileSize, me.getY() / tileSize));
    }

    public Entity getEntity(Location mapLocation) {
        for (Entity entity : entities) {
            if (entity.getMapLocation().equals(mapLocation)) {
                return entity;
            }
        }
        return null;
    }

    public void init() {
        currentEntity = currentEntityActions = 0;
        inventoryPane = new InventoryPane(inventoryPlacement, tileSize);
        tileset = ImageSheet.load("Tileset.png", 32);
        ImageSheet.load("Survivor.png", 32);
        ImageSheet.load("Zambies.png", 32);
        decals = new LinkedList<>();
        entities = new ArrayList<>();
        
        createEntities();
        
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
    
    public void createEntities(){
        addEntity(new Location(5, 7), new Survivor());
        entities.get(0).getInventory().addItem(new MedkitItem());
        entities.get(0).getInventory().addItem(new BandageItem(4));
        entities.get(0).getInventory().addItem(new BarricadeItem());
        entities.get(0).getInventory().addItem(new BigGunItem(10,3));
        addDecal(new Location(100, 100), new FadingDecal(200, Color.MAGENTA, new Dimension(16,16)));
        addDecal(new Location(200, 200), new HealingDecal());
        addDecal(new Location(300, 300), new DamageDecal());
        
        addTextDecal(new TextDecal(new GraphicTextDecal("Boom", new Location(200,200)), 100));
        
        addTextDecal(new TextDecal(new GraphicTextDecal("Shaka", new Location(250,200)), 110));

        addTextDecal(new TextDecal(new GraphicTextDecal("Laka", new Location(300,200)), 120));
        
        addEntity(new Location(14, 7), new Survivor());
        addEntity(new Location(6, 7), new Barricade());
        addEntity(new Location(13, 7), new Barricade());
    }
    
    public void keyPressed(KeyEvent ke) {
        Entity entity = entities.get(currentEntity);
        if (entity instanceof Survivor && !entity.isMoving()) {
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
    
    @Override
    public void mouseClicked(MouseEvent me) {
        clickedEntity = getEntityAtMouse(me);
        
        inventoryPane.mouseClicked(me, null);
        inventoryPane.inventoryPaneManager(clickedEntity, me);
    }
    
    @Override
    public void mouseMoved(MouseEvent me) {
        inventoryPane.mouseMoved(me, null);
    }

    public void render(Graphics2D g) {
        renderTiles(g);
        renderEntities(g);
        renderDecals(g);
        renderBorders(g);
        renderInventory(g);
        renderActionPane(g);
        renderInfoPane(g);
        renderStatusPane(g);
    }
    
    private void renderBorders(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillRect(640, 0, 8, 480);
        g.fillRect(712, 0, 8, 480);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(648, 0, 64, 480);
    }
    
    private void renderDecals(Graphics2D g) {
        for (Decal decal : decals) {
            decal.render(g);
        }
    }
    
    private void renderEntities(Graphics2D g) {
        Location location = entities.get(currentEntity).getWorldLocation();
        
        //Render white circle that indicates current Entity.
        g.setColor(Color.WHITE);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g.fillOval(location.x - 16, location.y - 8, tileSize + 32, tileSize + 32);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        
        //Render all entities' shadows.
        for (Entity entity : entities) {
            location = entity.getWorldLocation();
            g.setColor(Color.BLACK);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g.fillOval(location.x + 4, location.y + 20, tileSize - 8, tileSize / 2);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
        
        //Render all entities.
        for (Entity entity : entities) {
            entity.render(g);
        }
    }
    
    private void renderInventory(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Inventory", 654, 20);
        
        inventoryPane.render(g, null);        
    }
    
    private void renderActionPane(Graphics2D g){
        
    }
    
    private void renderInfoPane(Graphics2D g){
        
    }
    
    private void renderStatusPane(Graphics2D g){
        
    }
    
    private void renderTiles(Graphics2D g) {
        for (Location location : tiles.keySet()) {
            g.drawImage(tileset.getImage(tiles.get(location)), location.x * tileSize, location.y * tileSize, null);
        }
    }

    public void update(Game game) {
        ListIterator<Decal> iterator = decals.listIterator();
        Decal decal;
        while (iterator.hasNext()) {
            decal = iterator.next();
            if (decal.isDestroyed()) {
                iterator.remove();
            }
            else {
                decal.update(this);
            }
        }
        Entity entity = entities.get(currentEntity);
        if (entity instanceof Survivor) {
            inventoryPane.setSurvivor((Survivor) entity);
        }
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