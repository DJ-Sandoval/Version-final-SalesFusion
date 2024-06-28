
package seguridad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import modelo.ProductosDao;
import modelo.compras;
import modelo.comprasDao;
import vista.PanelAdmin;

/**
 *
 * @author Jose
 */
public class ComprasController implements ActionListener,  MouseListener, KeyListener {
    private final compras com;
    private final PanelAdmin vista;
    private final comprasDao cpDao;
    DefaultTableModel modelo = new DefaultTableModel();
    
    // Constructor para inicializar las clases

    public ComprasController(compras com, PanelAdmin vista, comprasDao cpDao) {
        this.com = com;
        this.vista = vista;
        this.cpDao = cpDao;
        this.vista.txtBuscarCompra.addKeyListener(this);
        this.vista.btnHistorialCompras.addActionListener(this);
        this.vista.TableCompras.addMouseListener(this);
        this.vista.btnEliminarCompra.addActionListener(this);
        listarCompras();
    }
    
    // Metodos mouse para el jTable
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.TableCompras) {
           int fila = vista.TableCompras.rowAtPoint(e.getPoint());
           vista.txtIdCompra.setText(vista.TableCompras.getValueAt(fila, 0).toString());         
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
   }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    

    
    
    // Metodos importantes
    public void listarCompras() {
        List<compras> lista = cpDao.ListaCompras(vista.txtBuscarCompra.getText());
        modelo = (DefaultTableModel) vista.TableCompras.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombreProveedor();
            ob[2] = lista.get(i).getTotal();
            ob[3] = lista.get(i).getFecha();
            modelo.addRow(ob);
        }
        vista.TableCompras.setModel(modelo);
        JTableHeader header = vista.TableCompras.getTableHeader();
        header.setOpaque(false);
    }
    
    public void limpiarTable() {
        for (int i = 0; i< modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    // Metodo para controlar la accion del boton eliminar compra
    @Override
    public void actionPerformed(ActionEvent e) {
        // para el boton de generar reporte
        if (e.getSource() == vista.btnHistorialCompras) {
            if (vista.txtIdCompra.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                int id_Compra = Integer.parseInt(vista.txtIdCompra.getText());
                ProductosDao proDao = new ProductosDao();
                proDao.generarReporte(id_Compra);               
            }
        } else if (e.getSource() == vista.btnEliminarCompra) {
            if (vista.txtIdCompra.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            int id_Compra = Integer.parseInt(vista.txtIdCompra.getText());
            String password = JOptionPane.showInputDialog("Ingrese la clave para eliminar la compra:");
            if (password != null) {
                boolean eliminado = cpDao.eliminarConAutenticacion(id_Compra, password);
                if (eliminado) {
                    JOptionPane.showMessageDialog(null, "Compra eliminada correctamente");
                    limpiarTable();
                    vista.txtIdCompra.setText("");
                    listarCompras(); // Llamar al método para volver a listar las compras
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar la compra. Verifique la clave y vuelva a intentarlo.");
                }
            }
        }
        
        }
        
   }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == vista.txtBuscarCompra) {
            limpiarTable();
            listarCompras();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
   }

   
    
}
