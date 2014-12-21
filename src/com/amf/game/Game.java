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
    
    private int playerX = 128, playerY = 128;
    private int destinationX, destinationY;
    
    private boolean moving = false, selected = false;
    
    private MouseListener mouse = new MouseAdapter() {
        
        public void mouseClicked(MouseEvent me) {
            int mouseX = me.getX();
            int mouseY = me.getY();
            if (selected) {
                destinationX = mouseX - 16;
                destinationY = mouseY - 16;
                moving = true;
                selected = false;
            }
            else if (mouseX >= playerX - 32 && mouseX <= playerX + 32 && mouseY >= playerY - 32 && mouseY <= playerY + 32) {
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
                    if (playerX < destinationX) {
                        playerX++;
                    }
                    else if (playerX > destinationX) {
                        playerX--;
                    }
                    if (playerY < destinationY) {
                        playerY++;
                    }
                    else if (playerY > destinationY) {
                        playerY--;
                    }
                    if (playerX == destinationX && playerY == destinationY) {
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
            g.fillOval(playerX, playerY, 32, 32);
            if (selected) {
                g.setColor(Color.BLUE);
                g.drawOval(playerX, playerY, 32, 32);
            }
        }
    }
    
    public static void main(String[] args) {
        new Game();
    }
    
}