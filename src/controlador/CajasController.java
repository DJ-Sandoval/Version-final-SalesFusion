
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
import controlador.CajasController;
import java.util.Date;
import modelo.AperturaCierre;
import modelo.CajaDao;
import modelo.Cajas;
import vista.FrmCaja;
import vista.FrmModificarCaja;
import vista.FrmModificarCategoria;
import vista.FrmModificarUsuario;
import vista.FrmNuevaCategoria;
import vista.FrmNuevoUsuario;
import vista.frmNuevoProducto;
import vista.FrmAperturaCaja;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
/**
 *
 * @author Jose
 */
public class CajasController implements ActionListener, MouseListener, KeyListener {
    // Atributos de instancia
    private final Cajas caja;
    private final CajaDao cjDao;
    private final PanelAdmin views;
    private final FrmCaja cjN;
    private final FrmModificarCaja cjM;
    private final FrmNuevoUsuario usN;
    private final FrmModificarUsuario usM;
    private final FrmAperturaCaja apC;
    DefaultTableModel modelo = new DefaultTableModel();

    public CajasController(Cajas caja, CajaDao cjDao, PanelAdmin views, FrmCaja cjN, FrmModificarCaja cjM, FrmNuevoUsuario usN, FrmModificarUsuario usM, FrmAperturaCaja apC) {
        this.caja = caja;
        this.cjDao = cjDao;
        this.views = views;
        this.cjN = cjN;
        this.cjM = cjM;
        this.usN = usN;
        this.usM = usM;
        this.apC = apC;
        //this.views.btnNuevoCat.addActionListener(this);
        this.cjM.btnModificarCaja.addActionListener(this);
        this.cjN.btnRegistrarCaja.addActionListener(this);
        this.views.txtBuscarCaja.addKeyListener(this);
        this.views.JMenuEliminarCaja.addActionListener(this);
        this.views.JMenuReingresarCaja.addActionListener(this);
        this.views.jMenuIEditarCaja.addActionListener(this);
        this.views.TableCaja.addMouseListener(this);
        //this.views.btnCajaCt.addActionListener(this);
        this.apC.btnAbrirApertura.addActionListener(this);
        this.views.btnCerrarCaja.addActionListener(this);
        //llenarCategoria();
        AutoCompleteDecorator.decorate(usN.cbxCajaUser);
        AutoCompleteDecorator.decorate(usM.cbxCajaUser);
        listarCajas();
        llenarCaja();
        
    }
    
    
    // Metodo de accion
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cjN.btnRegistrarCaja) {
            registrarCaja();
        } else if (e.getSource() == cjM.btnModificarCaja) {
            modificarCaja();
        } 
        else if (e.getSource() == views.JMenuEliminarCaja) {
            eliminarCaja();
        } else if (e.getSource() == views.JMenuReingresarCaja){
            reingresarCaja();
        } else if (e.getSource() == views.jMenuIEditarCaja) {
           editarCaja();
        } else if (e.getSource() == views.btnCajaCt) {
            views.jTabbedPane1.setSelectedIndex(10);
            limpiarTable();
            listarCajas();
        }
        // Apertura y cierre
        else if (e.getSource() == apC.btnAbrirApertura) {
            abrirCaja();
        } else if (e.getSource() == views.btnCerrarCaja) {
            cerrarCaja();
        }
        /*
        
        */
        
    }
    private void listarCajas() {
        Tables color = new Tables();
        views.TableCaja.setDefaultRenderer(views.TableCaja.getColumnClass(0), color);
        List<Cajas> lista = cjDao.ListaCaja(views.txtBuscarCaja.getText());
        modelo = (DefaultTableModel) views.TableCaja.getModel();
        Object[] ob = new Object[3];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        // Establecer modelo a tabla
        views.TableCaja.setModel(modelo);
        // Establecer color a tabla
        
//        JTableHeader header = views.TableCat.getTableHeader();
//        Font headerFont = new Font("SansSerif", Font.BOLD, 16);
//        header.setFont(headerFont);
//        //views.TableProveedor.setDefaultRenderer(Object.class, new EstilosDarkTable(color1, color2));
//        header.setOpaque(false);
//         header.setBackground(new Color(30,30,30)); //16,49,114 0,81,135
//        header.setForeground(new Color(255,255,255));
    }
    
    // Metodos de Mouse
    @Override
    public void mouseClicked(MouseEvent e) {
         if (e.getSource() == views.TableCaja) {
            int fila = views.TableCaja.rowAtPoint(e.getPoint());
            views.txtIdCaja.setText(views.TableCaja.getValueAt(fila, 0).toString());
            cjM.txtNombreCaja.setText(views.TableCaja.getValueAt(fila, 1).toString());
//            cjM.setVisible(true);
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
        if (e.getSource() == views.txtBuscarCaja) {
            limpiarTable();
            listarCajas();
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
        cjM.txtNombreCaja.setText("");
        views.txtBuscarCaja.setText("");
        views.txtIdCaja.setText("");
    }
   
     // Metodo para llenar comboBox Categorias
    private void llenarCaja() {
         List<Cajas> lista = cjDao.ListaCaja(views.txtBuscarCaja.getText());   
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            usN.cbxCajaUser.addItem(new Combo(id, nombre));
            
            usM.cbxCajaUser.addItem(new Combo(id, nombre));
        }
    }
    
    private void registrarCaja() {
        if (cjN.txtNombreCaja.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "no dejes ESPACIOS EN BLANCO", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            caja.setNombre(cjN.txtNombreCaja.getText());
            if (cjDao.registrar(caja)) {
                limpiarTable();
                listarCajas();
                limpiar();
                JOptionPane.showMessageDialog(null, "Caja registrada");
                cjN.txtNombreCaja.setText("");
                cjN.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar la caja", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void modificarCaja() {
        if (cjM.txtNombreCaja.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (cjM.txtNombreCaja.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "no dejes ESPACIOS EN BLANCO", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                caja.setNombre(cjM.txtNombreCaja.getText());
                caja.setId(Integer.parseInt(views.txtIdCaja.getText()));
                if (cjDao.modificar(caja)) {
                    limpiarTable();
                    listarCajas();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Caja modificada");
                    cjM.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar caja", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void eliminarCaja() {
        if (views.txtIdCaja.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int id = Integer.parseInt(views.txtIdCaja.getText());
            if (cjDao.accion("Inactivo", id)) {
                limpiarTable();
                listarCajas();
                limpiar();
                JOptionPane.showMessageDialog(null, "Caja eliminada");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar Caja");
            }
        }
    }

    private void reingresarCaja() {
        if (views.txtIdCaja.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int id = Integer.parseInt(views.txtIdCaja.getText());
            if (cjDao.accion("Activo", id)) {
                limpiarTable();
                listarCajas();
                limpiar();
                JOptionPane.showMessageDialog(null, "Caja reingresada");
            } else {
                JOptionPane.showMessageDialog(null, "Error al reingresar caja", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void editarCaja() {
        // Verificamos que el campo no este vacio
        if (views.txtIdCaja.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "campo vacio", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int id = Integer.parseInt(views.txtIdCaja.getText());
            cjM.setVisible(true);
        }
    }
    
    // Método para abrir caja
private void abrirCaja() {
    if (apC.txtMontoInicial.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Ingrese el monto a iniciar", "Información", JOptionPane.INFORMATION_MESSAGE);
    } else {
        double monto_inicial = Double.parseDouble(apC.txtMontoInicial.getText());
        int id_usuario = Integer.parseInt(views.txtIdUsuario.getText());
        String resultado = cjDao.abrirCaja(monto_inicial, id_usuario);
        if ("existe".equalsIgnoreCase(resultado)) {
            JOptionPane.showMessageDialog(null, "La caja ya está abierta");
            apC.dispose();
        } else if ("registrado".equalsIgnoreCase(resultado)) {
            limpiarTable();
            listarAperturas();
            NuevoApertura();
            JOptionPane.showMessageDialog(null, "Caja abierta");
            apC.dispose();
            apC.txtMontoInicial.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Error al abrir la caja", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

  
    public void cerrarCaja() {
        int pregunta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cerrar la caja?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (pregunta == JOptionPane.YES_OPTION) {
            double monto_final = cjDao.montoFinal(Integer.parseInt(views.txtIdUsuario.getText()));
            int total_ventas = cjDao.totalVentas(Integer.parseInt(views.txtIdUsuario.getText()));

            // Crear un objeto AperturaCierre con los valores obtenidos
            AperturaCierre aper = new AperturaCierre();
            aper.setFecha_cierre(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            aper.setMonto_final(monto_final);
            aper.setTotal_ventas(total_ventas);
            aper.setId_usuario(Integer.parseInt(views.txtIdUsuario.getText()));

            if (cjDao.cerrarCaja(aper)) {
                JOptionPane.showMessageDialog(null, "Caja cerrada");
                limpiarTable();
                listarAperturas();
            } else {
                JOptionPane.showMessageDialog(null, "Error al cerrar la caja");
            }
        }
    }

    


     
       
     
     
 

     
     private void NuevoApertura() {
         apC.txtMontoInicial.setText("");
     }
     
     public void listarAperturas() {
    Tables color = new Tables();
    views.TableApertura.setDefaultRenderer(views.TableApertura.getColumnClass(0), color);
    List<AperturaCierre> lista = cjDao.ListarAperturas(views.txtBuscarApertura.getText());
    DefaultTableModel modelo = (DefaultTableModel) views.TableApertura.getModel();
    modelo.setRowCount(0); // Limpiar la tabla antes de añadir los nuevos datos
    Object[] ob = new Object[6];
    for (AperturaCierre apertura : lista) {
        ob[0] = apertura.getFecha_apertura();
        ob[1] = apertura.getFecha_cierre();
        ob[2] = apertura.getMonto_inicial();
        ob[3] = apertura.getMonto_final();
        ob[4] = apertura.getTotal_ventas();
        ob[5] = apertura.getNombre_usuario();
        modelo.addRow(ob);
    }
    // Establecer modelo a tabla
    views.TableApertura.setModel(modelo);
}
     
     
     
}
