package com.amf.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameContainer {
    
    private final Game game;
    
    private final JFrame window;
    
    private final GamePanel gamePanel;
    
    private final Timer timer = new Timer(16, new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            game.update();
            window.repaint();
        }
        
    });
    
    public GameContainer(Game game, String title) {
        this.game = game;
        window = new JFrame(title);
        gamePanel = new GamePanel();
        window.add(gamePanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
    }
    
    public void start() {
        window.setVisible(true);
        timer.start();
    }
    
    private class GamePanel extends JPanel {
        
        GamePanel() {
            super();
            addKeyListener(game);
            addMouseListener(game);
            addMouseMotionListener(game);
            setFocusable(true);
            setPreferredSize(new Dimension(720, 480));
        }
        
        public void paintComponent(Graphics bork) {
            super.paintComponent(bork);
            Graphics2D g = (Graphics2D) bork;
            game.render(g);
        }
        
    }
    
}