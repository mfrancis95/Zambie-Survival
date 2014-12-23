package com.amf.game.mike;

import com.amf.engine.Entity;
import com.amf.engine.Game;
import com.amf.engine.GameStateAdapter;
import com.amf.engine.GridMap;
import com.amf.engine.Location;
import com.amf.engine.Survivor;
import com.amf.engine.Tileset;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainGameState extends GameStateAdapter {

    private GridMap map;

    private Survivor player;

    private Tileset tileset;
    
    private boolean iKeyReleased;

    public void init() {
        map = new GridMap(32);
        player = new Survivor();
        tileset = new Tileset("tileset.png", 32);
        iKeyReleased = false;

        map.addEntity(player, 2, 2);
        for (int x = 0; x < 22; x++) {
            for (int y = 0; y < 15; y++) {
                map.addTile(new Location((int) (Math.random() * 6), (int) (Math.random() * 10)), x, y);
            }
        }
    }
    
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_I) {
            iKeyReleased = true;
        }
    }

    public void mousePressed(MouseEvent me) {
        int mapX = me.getX() / 32;
        int mapY = me.getY() / 32;
        Location location = map.getLocation(player);
        int differenceX = mapX - location.x;
        int differenceY = mapY - location.y;
        if (!player.isMoving() && differenceX == 0 && differenceY == 0) {
            player.setSelected(!player.isSelected());
        }
        else if (player.isSelected()) {
            player.move(map, new Location(mapX, mapY));
        }
    }

    public void render(Graphics2D g) {
        for (int x = 0; x < 22; x++) {
            for (int y = 0; y < 15; y++) {
                Location location = map.getTile(x, y);
                g.drawImage(tileset.getTile(location), null, x * 32, y * 32);
            }
        }
        g.setColor(Color.GRAY);
        for (int x = 0; x < 720; x += 32) {
            g.drawLine(x, 0, x, 480);
        }
        for (int y = 0; y < 480; y += 32) {
            g.drawLine(0, y, 720, y);
        }
        for (Entity e : map.getEntities()) {
            e.render(g);
        }
    }

    public void update(Game game) {
        if (iKeyReleased) {
            iKeyReleased = false;
            game.enterState("Inventory", true);
        }
        else {
            for (Entity e : map.getEntities()) {
                e.update(map);
            }
        }
    }

}