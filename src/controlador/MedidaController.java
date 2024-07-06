
package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import modelo.Combo;
import modelo.MedidaDao;
import modelo.Medidas;
import modelo.Proveedor;
import modelo.Tables;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import vista.PanelAdmin;
import vista.FrmMedida;
import vista.FrmModificarMedida;
import vista.FrmNuevaMedida;
import vista.frmNuevoProducto;

/**
 *
 * @author Jose
 */
public class MedidaController implements ActionListener, MouseListener, KeyListener {
   private Medidas med;
   private MedidaDao mdDao;
   private PanelAdmin views;
   private FrmNuevaMedida medida;
   private FrmMedida nuevo;
   private FrmModificarMedida modificarMedida;
   private frmNuevoProducto productoNuevo;
   DefaultTableModel modelo = new DefaultTableModel();

    public MedidaController(Medidas med, MedidaDao mdDao, PanelAdmin views, FrmNuevaMedida medida, FrmMedida nuevo, FrmModificarMedida modificarMedida, frmNuevoProducto productoNuevo) {
        this.med = med;
        this.mdDao = mdDao;
        this.views = views;
        this.nuevo = nuevo;
        this.medida = medida;
        this.modificarMedida = modificarMedida;
        this.productoNuevo = productoNuevo;
        this.modificarMedida.btnModificarMed.addActionListener(this);
        this.medida.btnRegistrarMed.addActionListener(this);
        this.nuevo.btnRegistrarMed.addActionListener(this);
        //this.views.btnModificarMed.addActionListener(this);
        //this.views.btnNuevoMed.addActionListener(this);
        this.views.btnRegistrarMed.addActionListener(this);
        this.views.txtBuscarMed.addKeyListener(this);
        this.views.JMenuEliminarMed.addActionListener(this);
        this.views.JMenuReingresarMed.addActionListener(this);
        this.views.jMenuEditarMed.addActionListener(this);
        this.views.TableMed.addMouseListener(this);
        
        llenarMedida();
        AutoCompleteDecorator.decorate(productoNuevo.cbxMedidaPro);
        listarMedidas();

    }
    
    // Metodos
    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == medida.btnRegistrarMed) {
            if (medida.txtNombreMed.getText().equals("") 
                    || medida.txtNombreCortoMed.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "no dejes ESPACIOS EN BLANCO", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                med.setNombre(medida.txtNombreMed.getText());
                med.setNombre_corto(medida.txtNombreCortoMed.getText());
                if (mdDao.registrar(med)) {
                    limpiarTable();
                    listarMedidas();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Medida registrada");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar Medida", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
       } else if (e.getSource() == modificarMedida.btnModificarMed) {
            if (views.txtIdMed.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                if (modificarMedida.txtNombreMed.getText().equals("")
                        || modificarMedida.txtNombreCortoMed.getText().equals("")) {
                     JOptionPane.showMessageDialog(null, "no dejes ESPACIOS EN BLANCO", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    med.setNombre(modificarMedida.txtNombreMed.getText());
                    med.setNombre_corto(modificarMedida.txtNombreCortoMed.getText());
                    med.setId(Integer.parseInt(views.txtIdMed.getText()));
                    if (mdDao.modificar(med)) {
                        limpiarTable();
                        listarMedidas();
                        limpiar();
                        JOptionPane.showMessageDialog(null, "Medida modificada");
                         modificarMedida.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar proveedor", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
       } else if (e.getSource() == views.jMenuEditarMed) {
           if (views.txtIdMed.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "Selecciona una fila", "Información", JOptionPane.INFORMATION_MESSAGE);
           } else {
               int id = Integer.parseInt(views.txtIdMed.getText());
               modificarMedida.setVisible(true);
           }
       }
       
       else if (e.getSource() == views.JMenuEliminarMed) {
            if (views.txtIdMed.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int id = Integer.parseInt(views.txtIdMed.getText());
                if (mdDao.accion("Inactivo", id)) {
                    limpiarTable();
                    listarMedidas();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Medida eliminada");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar Medida");
                }
            }
        } else if (e.getSource() == views.JMenuReingresarMed){
            if (views.txtIdMed.getText().equals("")) {  
                JOptionPane.showMessageDialog(null, "Selecciona una fila", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                 int id = Integer.parseInt(views.txtIdMed.getText());
                if (mdDao.accion("Activo", id)) {
                    limpiarTable();
                    listarMedidas();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Medida reingresada");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al reingresar medida", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } 
        } else {
            limpiar();
        }
    }
    
    public void listarMedidas() {
        Tables color = new Tables();
        views.TableMed.setDefaultRenderer(views.TableMed.getColumnClass(0), color);
        List<Medidas> lista = mdDao.ListaMedida(views.txtBuscarMed.getText());
        modelo = (DefaultTableModel) views.TableMed.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getNombre_corto();
            ob[3] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        // Establecer modelo a tabla
        views.TableMed.setModel(modelo);
        // Establecer color a tabla
        
        JTableHeader header = views.TableMed.getTableHeader();
        Font headerFont = new Font("SansSerif", Font.BOLD, 16);
        header.setFont(headerFont);
         // Configurar el borde de la tabla
        //header.setBorder(new LineBorder(new Color(0,118,184)));
        //header.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        header.setOpaque(false);
         header.setBackground(new Color(30,30,30)); //16,49,114 0,81,135
        header.setForeground(new Color(255,255,255));
    }
    
    // Metodo para limpiar tabla
    public void limpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    public void limpiar() {
        medida.txtNombreMed.setText("");
        medida.txtNombreCortoMed.setText("");
        views.txtIdMed.setText("");
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
         if (e.getSource() == views.TableMed) {
            int fila = views.TableMed.rowAtPoint(e.getPoint());
            views.txtIdMed.setText(views.TableMed.getValueAt(fila, 0).toString());
            modificarMedida.txtNombreMed.setText(views.TableMed.getValueAt(fila, 1).toString());
            modificarMedida.txtNombreCortoMed.setText(views.TableMed.getValueAt(fila, 2).toString());
             //modificarMedida.setVisible(true);
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

    @Override
    public void keyTyped(KeyEvent e) {
   }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarMed) {
            limpiarTable();
            listarMedidas();
        }
    }
    
     // Metodo para llenar comboBox Proveedor
    private void llenarMedida() {
         List<Medidas> lista = mdDao.ListaMedida(views.txtBuscarMed.getText());   
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            productoNuevo.cbxMedidaPro.addItem(new Combo(id, nombre));
            
        }
    }

}
