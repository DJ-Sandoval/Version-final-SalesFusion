
package controlador;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Jose
 */
public class MenuButton extends JButton {
    public float getAnimate() {
        return animate;
    }
    
    public void setAnimate(float animate) {
        this.animate = animate;
        repaint();
    }
    
    public int getIndex() {
        return index;
    }
    private float animate;
    
    private int index;
    
    public MenuButton(int index) {
        this.index=index;
        setContentAreaFilled(false);
        setForeground(new Color(200,200,200));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setHorizontalAlignment(SwingConstants.LEFT);
        setBackground(new Color(60,63,65));
        setBorder(new EmptyBorder(8,20,8,15));
    }
    
    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double width = getWidth();
        double height = getHeight();
        double x = animate * width - width;
        Area area = new Area(new RoundRectangle2D.Double(x, 0, width, height, height, height));
        area.add(new Area(new Rectangle2D.Double(x,0, height, height)));
        
        //if (isSelected()) {
            g2.setColor(getBackground());
            g2.fill(area);
        //}      
        g2.dispose();
        super.paint(grphcs);
    }
}
