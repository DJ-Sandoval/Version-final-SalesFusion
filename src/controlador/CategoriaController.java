
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import modelo.CategoriaDao;
import modelo.Categorias;
import modelo.Combo;
import modelo.Tables;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import vista.PanelAdmin;
import controlador.CategoriaController;
import vista.FrmModificarCategoria;
import vista.FrmNuevaCategoria;
import vista.frmNuevoProducto;

/**
 *
 * @author Jose
 */
public class CategoriaController implements ActionListener, MouseListener, KeyListener {
    // Atributos de instancia
    private final Categorias cat;
    private final CategoriaDao ctDao;
    private final PanelAdmin views;
    private final FrmNuevaCategoria ctC;
    private final FrmModificarCategoria ctM;
    private final frmNuevoProducto productoNuevo;
    DefaultTableModel modelo = new DefaultTableModel();

    public CategoriaController(Categorias cat, CategoriaDao ctDao, PanelAdmin views, FrmNuevaCategoria ctC, FrmModificarCategoria ctM, frmNuevoProducto productoNuevo) {
        this.cat = cat;
        this.ctDao = ctDao;
        this.views = views;
        this.ctC = ctC;
        this.ctM = ctM;
        this.productoNuevo = productoNuevo;
        this.views.btnNuevoCat.addActionListener(this);
        this.ctM.btnModificarCat.addActionListener(this);
        this.ctC.btnRegistrarCat.addActionListener(this);
        this.views.txtBuscarCategoria.addKeyListener(this);
        this.views.JMenuEliminarCat.addActionListener(this);
        this.views.JMenuReingresarCat.addActionListener(this);
        this.views.TableCat.addMouseListener(this);
        llenarCategoria();
        AutoCompleteDecorator.decorate(productoNuevo.cbxCategoriaPro);
        listarCategorias();
    }
    
    
    // Metodo de accion
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ctC.btnRegistrarCat) {
            if (ctC.txtNombreCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "no dejes ESPACIOS EN BLANCO", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                cat.setNombre(ctC.txtNombreCat.getText());
                if (ctDao.registrar(cat)) {
                    limpiarTable();
                    listarCategorias();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Categoria registrada");
                    ctC.txtNombreCat.setText("");
                     ctM.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar categoria", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == ctM.btnModificarCat) {
            if (views.txtIdCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                if (ctM.txtNombreCat.getText().equals("")) {
                     JOptionPane.showMessageDialog(null, "no dejes ESPACIOS EN BLANCO", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    cat.setNombre(ctM.txtNombreCat.getText());
                    cat.setId(Integer.parseInt(views.txtIdCat.getText()));
                    if (ctDao.modificar(cat)) {
                        limpiarTable();
                        listarCategorias();
                        limpiar();
                        JOptionPane.showMessageDialog(null, "Categoria modificada");
                        ctM.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar categoria", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else if (e.getSource() == views.JMenuEliminarCat) {
            if (views.txtIdCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int id = Integer.parseInt(views.txtIdCat.getText());
                if (ctDao.accion("Inactivo", id)) {
                    limpiarTable();
                    listarCategorias();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Categoria eliminada");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar Categoria");
                }
            }
        } else if (e.getSource() == views.JMenuReingresarCat){
            if (views.txtIdCat.getText().equals("")) {  
                JOptionPane.showMessageDialog(null, "Selecciona una fila", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                 int id = Integer.parseInt(views.txtIdCat.getText());
                if (ctDao.accion("Activo", id)) {
                    limpiarTable();
                    listarCategorias();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Categoria reingresado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al reingresar categoria", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } 
        } else {
            limpiar();
        }
    }
    private void listarCategorias() {
        Tables color = new Tables();
        views.TableCat.setDefaultRenderer(views.TableCat.getColumnClass(0), color);
        List<Categorias> lista = ctDao.ListaCategoria(views.txtBuscarCategoria.getText());
        modelo = (DefaultTableModel) views.TableCat.getModel();
        Object[] ob = new Object[3];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        // Establecer modelo a tabla
        views.TableCat.setModel(modelo);
        // Establecer color a tabla
        
        JTableHeader header = views.TableCat.getTableHeader();
        Font headerFont = new Font("SansSerif", Font.BOLD, 16);
        header.setFont(headerFont);
        //views.TableProveedor.setDefaultRenderer(Object.class, new EstilosDarkTable(color1, color2));
        header.setOpaque(false);
         header.setBackground(new Color(30,30,30)); //16,49,114 0,81,135
        header.setForeground(new Color(255,255,255));
    }
    
    // Metodos de Mouse
    @Override
    public void mouseClicked(MouseEvent e) {
         if (e.getSource() == views.TableCat) {
            int fila = views.TableCat.rowAtPoint(e.getPoint());
            views.txtIdCat.setText(views.TableCat.getValueAt(fila, 0).toString());
            ctM.txtNombreCat.setText(views.TableCat.getValueAt(fila, 1).toString());
            ctM.setVisible(true);
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
    
    // Metodos key
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarCategoria) {
            limpiarTable();
            listarCategorias();
        }
   }
    
    // Metodo para limpiar tabla
    public void limpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
     // Metodo para limpiar
    public void limpiar() {
        ctM.txtNombreCat.setText("");
        views.txtBuscarCategoria.setText("");
        views.txtIdCat.setText("");
    }
    
    // Metodo para llenar comboBox Proveedor
    private void llenarCategoria() {
         List<Categorias> lista = ctDao.ListaCategoria(views.txtBuscarCategoria.getText());   
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            productoNuevo.cbxCategoriaPro.addItem(new Combo(id, nombre));
        }
    }
    
}
