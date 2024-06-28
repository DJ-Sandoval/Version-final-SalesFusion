
package modelo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import static javax.swing.text.StyleConstants.setForeground;

/**
 *
 * @author Jose
 */
public class Tables extends DefaultTableCellRenderer {

    public Tables() {
        
      
    }
    
    
    
    
    
    @Override
    public Component getTableCellRendererComponent(
            JTable jtable, Object o, boolean isSelected, boolean hasFocus, int row, int col) {
        super.getTableCellRendererComponent(jtable, o, isSelected, hasFocus, row, col);

        // Definir los colores alternos para las filas
        Color colorPar = new Color(50,50,50); // Gris claro
        Color colorImpar = new Color(30,30,30); // Gris más claro

        // Definir los colores de texto alternos para las filas
        Color textoPar = Color.white; // Azul para filas pares
        Color textoImpar = Color.white; // Verde para filas impares

        // Aplicar color de fondo según el estado del valor de la celda y la fila
        Object cellValue = jtable.getValueAt(row, col);
        
        if (cellValue != null && cellValue.toString().equals("Inactivo")) {
            setBackground(Color.red);
            setForeground(Color.black);
        } else {
            if (row % 2 == 0) {
                setBackground(colorPar);
                setForeground(textoPar);
            } else {
                setBackground(colorImpar);
                setForeground(textoImpar);
            }
        }
        
        // Si la fila está seleccionada, sobrescribir el color de fondo y de texto
        if (isSelected) {
            setBackground(jtable.getSelectionBackground());
            setForeground(jtable.getSelectionForeground());
        }

        return this;
    }
    /*
   @Override
    public Component getTableCellRendererComponent(
            JTable jtable, Object o, boolean bln, boolean blnl, int row, int col) {
        super.getTableCellRendererComponent(jtable, o, bln, blnl, row, col);
        
        Object cellValue = jtable.getValueAt(row, col);
        
        if (cellValue != null && cellValue.toString().equals("Inactivo")) {
            setBackground(Color.red);
            setForeground(Color.black);
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }
        return this;
    }
    */
    
}
