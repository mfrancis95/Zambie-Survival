package com.amf.engine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Zambie extends Entity {
    
    @Override
    public void performAction(String action) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void render(Graphics2D g) {
        g.setColor(Color.ORANGE);
        Location location = getScreenLocation();
        g.fillOval(location.x, location.y, 32, 32);
        if (isSelected()) {
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(2));
            g.drawOval(location.x, location.y, 32, 32);
        }
    }
    
}