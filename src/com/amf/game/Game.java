package com.amf.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JFrame {
    
    private int playerMapX = 1, playerMapY = 1;
    private int playerScreenX = 32, playerScreenY = 32;
    
    private boolean moving = false, selected = false;
    
    private MouseListener mouse = new MouseAdapter() {
        
        public void mouseClicked(MouseEvent me) {
            int mapX = me.getX() / 32;
            int mapY = me.getY() / 32;
            int differenceX = mapX - playerMapX;
            int differenceY = mapY - playerMapY;
            if (selected) {
                selected = false;
                if (differenceX == 1 && differenceY == 0) {
                    playerMapX++;
                }
                else if (differenceX == -1 && differenceY == 0) {
                    playerMapX--;
                }
                else if (differenceX == 0 && differenceY == 1) {
                    playerMapY++;
                }
                else if (differenceX == 0 && differenceY == -1) {
                    playerMapY--;
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
    
    public Game() {
        super("Zambie Survival");
        add(new GamePanel());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        timer = new Timer(16, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (moving) {
                    int destinationScreenX = playerMapX * 32;
                    int destinationScreenY = playerMapY * 32;
                    if (playerScreenX < destinationScreenX) {
                        playerScreenX += 2;
                    }
                    else if (playerScreenX > destinationScreenX) {
                        playerScreenX -= 2;
                    }
                    if (playerScreenY < destinationScreenY) {
                        playerScreenY += 2;
                    }
                    else if (playerScreenY > destinationScreenY) {
                        playerScreenY -= 2;
                    }
                    if (playerScreenX == destinationScreenX && playerScreenY == destinationScreenY) {
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
            g.setColor(Color.ORANGE);
            g.fillRect(0, 0, 720, 480);
            g.setColor(Color.GRAY);
            for (int x = 0; x < 720; x += 32) {
                g.drawLine(x, 0, x, 480);
            }
            for (int y = 0; y < 480; y += 32) {
                g.drawLine(0, y, 720, y);
            }
            g.setColor(Color.RED);
            g.fillOval(playerScreenX, playerScreenY, 32, 32);
            if (selected) {
                g.setColor(Color.BLUE);
                g.drawOval(playerScreenX, playerScreenY, 32, 32);
            }
        }
    }
    
    public static void main(String[] args) {
        new Game();
    }
    
}