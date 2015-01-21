package main.java.org.zambiesurvival.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameContainer {
    
    private final Game game;
    
    private final boolean fullscreen, volatileImage;
    
    private int width, height;
    
    private final JFrame window;
    
    private final Timer timer = new Timer(16, new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            game.update();
            window.repaint();
        }
        
    });
    
    public GameContainer(Game game, String title, int width, int height, boolean volatileImage, boolean fullscreen) {
        this.game = game;
        window = new JFrame(title);
        this.width = width;
        this.height = height;
        this.volatileImage = volatileImage;
        this.fullscreen = fullscreen;
        window.setContentPane(new GamePanel()); 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
    }
    
    public void start() {
        window.dispose();
        if (fullscreen) {
            window.setExtendedState(JFrame.MAXIMIZED_BOTH);
            window.setUndecorated(true);
        }
        window.setVisible(true);
        timer.start();
    }
    
    private class GamePanel extends JPanel {
        
        BufferedImage image;
        
        GamePanel() {
            super();
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            addKeyListener(game);
            addMouseListener(game);
            addMouseMotionListener(game);
            setFocusable(true);
            setPreferredSize(new Dimension(width, height));            
        }
        
        public void paintComponent(Graphics bork) {
            super.paintComponent(bork);
            Image image = volatileImage ? createVolatileImage(width, height) : this.image;
            Graphics2D g = (Graphics2D) image.getGraphics();
            game.render(g);
            Dimension dimension = getSize();
            bork.drawImage(image, 0, 0, dimension.width, dimension.height, null);
        }
        
    }
    
}