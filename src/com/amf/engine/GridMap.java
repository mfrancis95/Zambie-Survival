package com.amf.engine;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GridMap implements Serializable {
    
    public final int squareSize;
    
    private final Map<Location, Entity> entities;
    
    private final Map<Location, Location> tiles;
    
    public GridMap(int squareSize) {
        this.squareSize = squareSize;
        entities = new HashMap<>();
        tiles = new HashMap<>();
    }
    
    public void addEntity(Entity entity, int x, int y) {
        entity.setScreenLocation(new Location(x * squareSize, y * squareSize));
        entities.put(new Location(x, y), entity);
    }
    
    public void addTile(Location location, int x, int y) {
        tiles.put(new Location(x, y), location);
    }
    
    public Entity getEntity(int x, int y) {
        return entities.get(new Location(x, y));
    }
    
    public Location getLocation(Entity entity) {
        for (Location l : entities.keySet()) {
            if (entities.get(l) == entity) {
                 return l;
            }
        }
        return null;
    }
    
    public Location getTile(int x, int y) {
        return tiles.get(new Location(x, y));
    }
    
    public void move(Location from, Location to) {
        Entity entity = entities.remove(from);
        if (entity != null) {
            entities.put(to, entity);
        }
    }
    
    public Entity removeEntity(int x, int y) {
        return entities.remove(new Location(x, y));
    }
    
    public Location removeTile(int x, int y) {
        return tiles.remove(new Location(x, y));
    }
    
}