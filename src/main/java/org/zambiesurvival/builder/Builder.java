package main.java.org.zambiesurvival.builder;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.filechooser.FileNameExtensionFilter;
import main.java.org.zambiesurvival.engine.ImageSheet;
import main.java.org.zambiesurvival.engine.Location;

public class Builder extends JFrame {
    
    private final JFileChooser chooser = new JFileChooser();
    
    private Location hoverTile = new Location(0, 0), selectedTile = new Location(0, 0);
    
    private Map<Location, Location> map = new HashMap<>();
    
    private final Stroke stroke = new BasicStroke(2);
    
    private final ImageSheet tileset = ImageSheet.load("Tileset.png", 32);
    
    private final Timer timer;
    
    private final JButton newMapButton = new JButton("New Map");
    private final JButton loadMapButton = new JButton("Load Map");
    private final JButton saveMapButton = new JButton("Save Map");
    private final JButton quitButton = new JButton("Quit");
    
    private final ActionListener action = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "New Map":
                    map = new HashMap<>();
                    break;
                case "Load Map":
                    if (chooser.showOpenDialog(Builder.this) == JFileChooser.APPROVE_OPTION) {
                        try {
                            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(chooser.getSelectedFile()));
                            map = (HashMap<Location, Location>) ois.readObject();
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
                            String fileName = chooser.getSelectedFile().toString();
                            if (!fileName.endsWith(".zsm")) {
                                fileName += ".zsm";
                            }
                            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
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
        }
    };
    
    private final MouseAdapter mapMouse = new MouseAdapter() {
        
        public void mouseDragged(MouseEvent me) {
            mouseMoved(me);
            mousePressed(me);
        }
        
        public void mouseMoved(MouseEvent me) {
            int tileSize = tileset.getTileSize();
            hoverTile = new Location(me.getX() / tileSize, me.getY() / tileSize);
        }
        
        public void mousePressed(MouseEvent me) {
            if (SwingUtilities.isLeftMouseButton(me)) {
                map.put(hoverTile, selectedTile);
            }
            else {
                map.remove(hoverTile);
            }
        }
        
    };
    
    private final MouseListener tilesetMouse = new MouseAdapter() {
        
        public void mousePressed(MouseEvent me) {
            int tileSize = tileset.getTileSize();
            Location location = new Location(me.getX() / tileSize, me.getY() / tileSize);
            if (tileset.getImage(location) != tileset.getFallbackImage()) {
                selectedTile = location;
            }
        }
        
    };
    
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
            int tileSize = tileset.getTileSize();
            for (Location location : map.keySet()) {
                g.drawImage(tileset.getImage(map.get(location)), location.x * tileSize, location.y * tileSize, null);
            }
            g.setColor(Color.GRAY);
            for (int x = 0; x < 640; x += tileSize) {
                g.drawLine(x, 0, x, 640);
            }
            for (int y = 0; y < 640; y += tileSize) {
                g.drawLine(0, y, 640, y);
            }
            g.setColor(Color.RED);
            g.setStroke(stroke);
            g.drawRect(hoverTile.x * tileSize, hoverTile.y * tileSize, tileSize, tileSize);
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
            int tileSize = tileset.getTileSize();
            for (Location location : tileset.getImageLocations()) {
                g.drawImage(tileset.getImage(location), null, location.x * tileSize, location.y * tileSize);
            }
            g.setColor(Color.RED);
            g.setStroke(stroke);
            g.drawRect(selectedTile.x * tileSize, selectedTile.y * tileSize, tileSize, tileSize);
        }
    }
    
    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (info.getName().equals("Nimbus")) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (Exception ex) {}
        new Builder();
    }
    
}