package com.amf.game.nelson;

import com.amf.engine.Location;
import com.amf.engine.World;
import com.amf.engine.ImageSheet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class NelsonGame extends JFrame {
    
    private World<Point> map = new World<>(32);
    
    private Point player = new Point(64, 64);
    
    private ImageSheet tileset = new ImageSheet("tileset.png", 32);
    
    private boolean moving = false, selected = false;
    
    private MouseListener mouse = new MouseAdapter() {
        
        public void mousePressed(MouseEvent me) {
            int mapX = me.getX() / 32;
            int mapY = me.getY() / 32;
            Location location = map.getLocation(player);
            int differenceX = mapX - location.x;
            int differenceY = mapY - location.y;
            if (selected) {
                selected = false;
                if (differenceX == 1 && differenceY == 0) {
                    map.move(location, location.add(1, 0));
                }
                else if (differenceX == -1 && differenceY == 0) {
                    map.move(location, location.add(-1, 0));
                }
                else if (differenceX == 0 && differenceY == 1) {
                    map.move(location, location.add(0, 1));
                }
                else if (differenceX == 0 && differenceY == -1) {
                    map.move(location, location.add(0, -1));
                }
                else {
                    return;
                }
                moving = true;
            }
            else if (differenceX == 0 && differenceY == 0) {
                selected = true;
            }
        }
        
    };
    
    private Timer timer;
    
    public NelsonGame() {
        super("Zambie Survival");
        map.addEntity(player, 2, 2);
        for (int x = 0; x < 22; x++) {
            for (int y = 0; y < 15; y++) {
                map.addTile(new Location((int) (Math.random() * 6), (int) (Math.random() * 7)), x, y);
            }
        }
        add(new GamePanel());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        timer = new Timer(16, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (moving) {
                    Location location = map.getLocation(player);
                    int destinationScreenX = location.x * 32;
                    int destinationScreenY = location.y * 32;
                    if (player.x < destinationScreenX) {
                        player.x += 2;
                    }
                    else if (player.x > destinationScreenX) {
                        player.x -= 2;
                    }
                    if (player.y < destinationScreenY) {
                        player.y += 2;
                    }
                    else if (player.y > destinationScreenY) {
                        player.y -= 2;
                    }
                    if (player.x == destinationScreenX && player.y == destinationScreenY) {
                        moving = false;
                    }
                }
                repaint();
            }
            
        });
        timer.start();
    }
    
    private class GamePanel extends JPanel {
        
        public GamePanel() {
            super();
            addMouseListener(mouse);
            setPreferredSize(new Dimension(720, 480));
        }
        
        public void paintComponent(Graphics bork) {
            Graphics2D g = (Graphics2D) bork;
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
            g.setColor(Color.RED);
            g.fillOval(player.x, player.y, 32, 32);
            if (selected) {
                g.setColor(Color.WHITE);
                g.setStroke(new BasicStroke(2));
                g.drawOval(player.x, player.y, 32, 32);
            }
        }
    }
    
    public static void main(String[] args) {
        new NelsonGame();
    }
    
}