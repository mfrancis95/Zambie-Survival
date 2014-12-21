package com.amf.game;

public class GridMap<T> {
    
    public final int squareSize;
    
    private final T[][] entities;
    
    public GridMap(int squareSize, int width, int height) {
        this.squareSize = squareSize;
        entities = (T[][]) new Object[width][height];
    }
    
    public void addEntity(T entity, int x, int y) {
        entities[x][y] = entity;
    }
    
    public T getEntity(int x, int y) {
        return entities[x][y];
    }
    
    public int getHeight() {
        return entities[0].length;
    }
    
    public int getWidth() {
        return entities.length;
    }
    
}