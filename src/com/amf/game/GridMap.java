package com.amf.game;

import java.util.HashMap;
import java.util.Map;

public class GridMap<T> {
    
    public final int squareSize;
    
    private final Map<Location, T> entities;
    
    public GridMap(int squareSize) {
        this.squareSize = squareSize;
        entities = new HashMap<>();
    }
    
    public void addEntity(T entity, int x, int y) {
        entities.put(new Location(x, y), entity);
    }
    
    public T getEntity(int x, int y) {
        return entities.get(new Location(x, y));
    }
    
    public Location getLocation(T entity) {
        for (Location l : entities.keySet()) {
            if (entities.get(l) == entity) {
                 return l;
            }
        }
        return null;
    }
    
    public void move(Location from, Location to) {
        T entity = entities.remove(from);
        if (entity != null) {
            entities.put(to, entity);
        }
    }
    
}