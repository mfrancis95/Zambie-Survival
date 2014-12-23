package com.amf.builder;

import com.amf.engine.GridMap;
import com.amf.engine.Location;
import com.amf.engine.Tileset;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Builder extends JFrame {
    
    private JFileChooser chooser = new JFileChooser();
    
    private GridMap map = new GridMap(32);
    
    private final Tileset tileset = new Tileset("tileset.png", 32);
    
    private Location hoverTile = new Location(0, 0);
    
    private Location selectedTile = new Location(0, 0);
    
    private final ActionListener action = (ActionEvent e) -> {
        switch (e.getActionCommand()) {
            case "New Map":
                map = new GridMap(32);
                break;
            case "Load Map":
                if (chooser.showOpenDialog(Builder.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(chooser.getSelectedFile()));
                        map = (GridMap) ois.readObject();
                        ois.close();
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(Builder.this, ex);
                    }
                }
                break;
            case "Save Map":
                if (chooser.showSaveDialog(Builder.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(chooser.getSelectedFile().toString() + ".zsm"));
                        oos.writeObject(map);
                        oos.close();
                    }
                    catch (IOException ex) {
                        JOptionPane.showMessageDialog(Builder.this, ex);
                    }
                }
                break;
            case "Quit":
                System.exit(0);
        }
    };
    
    private final MouseAdapter mapMouse = new MouseAdapter() {
        
        public void mouseDragged(MouseEvent me) {
            mouseMoved(me);
            mousePressed(me);
        }
        
        public void mouseMoved(MouseEvent me) {
            hoverTile = new Location(me.getX() / 32, me.getY() / 32);
        }
        
        public void mousePressed(MouseEvent me) {
            Location location = new Location(me.getX() / 32, me.getY() / 32);
            if (SwingUtilities.isLeftMouseButton(me)) {
                map.addTile(selectedTile, location.x, location.y);
            }
            else {
                map.removeTile(location.x, location.y);
            }
        }
        
    };
    
    private final MouseListener tilesetMouse = new MouseAdapter() {
        
        public void mousePressed(MouseEvent me) {
            Location location = new Location(me.getX() / 32, me.getY() / 32);
            if (tileset.getTile(location) != tileset.getFallbackTile()) {
                selectedTile = location;
            }
        }
        
    };
    
    private final Timer timer;
    
    private final JButton newMapButton = new JButton("New Map");
    private final JButton loadMapButton = new JButton("Load Map");
    private final JButton saveMapButton = new JButton("Save Map");
    private final JButton quitButton = new JButton("Quit");
    
    public Builder() {
        super("Zambie Survival Map Builder");
        chooser.setFileFilter(new FileNameExtensionFilter("Zambie Survival Map", "zsm"));
        newMapButton.addActionListener(action);
        loadMapButton.addActionListener(action);
        saveMapButton.addActionListener(action);
        quitButton.addActionListener(action);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        JPanel northPanel = new JPanel();
        northPanel.add(newMapButton);
        northPanel.add(loadMapButton);
        northPanel.add(saveMapButton);
        northPanel.add(quitButton);
        add(northPanel, BorderLayout.NORTH);
        add(new MapPanel(), BorderLayout.CENTER);
        add(new TilesetPanel(), BorderLayout.WEST);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        timer = new Timer(16, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                repaint();
            }
            
        });
        timer.start();
    }
    
    private class MapPanel extends JPanel {
        
        public MapPanel() {
            super();
            addMouseListener(mapMouse);
            addMouseMotionListener(mapMouse);
            setPreferredSize(new Dimension(640, 480));
        }
        
        public void paintComponent(Graphics bork) {
            Graphics2D g = (Graphics2D) bork;
            for (Location l : map.getTileLocations()) {
                g.drawImage(tileset.getTile(map.getTile(l)), null, l.x * 32, l.y * 32);
            }
            g.setColor(Color.GRAY);
            for (int x = 0; x < 640; x += 32) {
                g.drawLine(x, 0, x, 640);
            }
            for (int y = 0; y < 640; y += 32) {
                g.drawLine(0, y, 640, y);
            }
            g.setColor(Color.RED);
            g.setStroke(new BasicStroke(2));
            g.drawRect(hoverTile.x * 32, hoverTile.y * 32, 32, 32);
        }
    }
    
    private class TilesetPanel extends JPanel {
        
        public TilesetPanel() {
            super();
            addMouseListener(tilesetMouse);
            setPreferredSize(new Dimension(tileset.getWidth(), tileset.getHeight()));
        }
        
        public void paintComponent(Graphics bork) {
            Graphics2D g = (Graphics2D) bork;
            for (Location l : tileset.getTileLocations()) {
                g.drawImage(tileset.getTile(l), null, l.x * 32, l.y * 32);
            }
            g.setColor(Color.RED);
            g.setStroke(new BasicStroke(2));
            g.drawRect(selectedTile.x * 32, selectedTile.y * 32, 32, 32);
        }
    }
    
    public static void main(String[] args) {
        new Builder();
    }
    
}