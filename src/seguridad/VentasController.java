 
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
import modelo.ventas;
import modelo.ventasDao;
import vista.PanelAdmin;
import modelo.Reportes;

/**
 *
 * @author Jose
 */
public class VentasController implements ActionListener, MouseListener, KeyListener {
    private final ventas vt;
    private final PanelAdmin vista;
    private final ventasDao vtDao;
    DefaultTableModel modelo = new DefaultTableModel();
    
    
    // Constructor para inicializar las clases
    public VentasController(ventas vt, PanelAdmin vista, ventasDao vtDao) {
        this.vt = vt;
        this.vista = vista;
        this.vtDao = vtDao;
        this.vista.txtIdVenta.addKeyListener(this);
        this.vista.btnHistorialVentas.addActionListener(this);
        this.vista.btnEliminarVenta.addActionListener(this);
        this.vista.TableVentas.addMouseListener(this);
        this.vista.txtBuscarVenta.addKeyListener(this);
        listarVentas();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Eliminar venta
        if (e.getSource() == vista.btnEliminarVenta) {
            if (vista.txtIdVenta.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                int id_venta = Integer.parseInt(vista.txtIdVenta.getText());
                String password = JOptionPane.showInputDialog("Ingrese la clave para eliminar la compra:");
                if (password != null) {
                    boolean eliminado = vtDao.eliminarConAutenticacion(id_venta, password);
                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "venta eliminada correctamente");
                        limpiarTable();
                        vista.txtIdVenta.setText("");
                        listarVentas(); // Llamar al método para volver a listar las ventas
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar la venta","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else if (e.getSource() == vista.btnHistorialVentas) {
            if (vista.txtIdVenta.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                Reportes reporte = new Reportes();
                reporte.generarReporteVentas();
            }
        }
    }
    
    // Metodos de mouse para las funciones de click con el teclado
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.TableVentas) {
           int fila = vista.TableVentas.rowAtPoint(e.getPoint());
           vista.txtIdVenta.setText(vista.TableVentas.getValueAt(fila, 0).toString());         
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
    
    // Metodos de escucha
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == vista.txtBuscarVenta) {
            limpiarTable();
            listarVentas();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    // Metodos 
    public void listarVentas() {
        List<ventas> lista = vtDao.listaVentas(vista.txtBuscarVenta.getText());
        modelo = (DefaultTableModel) vista.TableVentas.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombreCliente();
            ob[2] = lista.get(i).getTotal();
            ob[3] = lista.get(i).getFecha();
            modelo.addRow(ob);
        }
        vista.TableVentas.setModel(modelo);
    }
    
    public void limpiarTable() {
        for (int i = 0; i< modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    
    
}
