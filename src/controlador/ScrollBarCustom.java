
package controlador;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;
/**
 *
 * @author Jose
 */
public class ScrollBarCustom extends JScrollBar {
    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(10, 10));
        setForeground(new Color( 62,108,219));
        setOpaque(false);
    }
}
