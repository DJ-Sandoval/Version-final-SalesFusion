
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
import modelo.Clientes;
import modelo.ClientesDao;
import modelo.Tables;
import vista.PanelAdmin;
import vista.frmModificarCliente;
import vista.frmNuevoCliente;

/**
 *
 * @author Jose
 */
public class ClientesController implements ActionListener, MouseListener, KeyListener {
    private Clientes cl;
    private ClientesDao clDao;
    private PanelAdmin views;
    private frmNuevoCliente clienteNuevo;
    private frmModificarCliente clienteModificado;
    DefaultTableModel modelo = new DefaultTableModel();
    // Constructor con parametros de instancia de clases

    public ClientesController(Clientes cl, ClientesDao clDao, PanelAdmin views, frmNuevoCliente clienteNuevo, frmModificarCliente clienteModificado) {
        this.cl = cl;
        this.clDao = clDao;
        this.views = views;
        this.clienteNuevo = clienteNuevo;
        this.clienteModificado = clienteModificado;
        this.clienteNuevo.btnRegistrarCli.addActionListener(this);
        this.clienteModificado.btnModificarCli.addActionListener(this);
        this.views.TableClientes.addMouseListener(this);
        this.views.JMenuEliminarCli.addActionListener(this);
        this.views.JMenuReingresarCli.addActionListener(this);
        this.views.JMenuEditarCli.addActionListener(this);
        this.views.txtBuscarCli.addKeyListener(this);
        this.views.jMenuCliente.addActionListener(this);
        listarClienter();
        
    }
    
    // Metodo abstracto de accion para los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clienteNuevo.btnRegistrarCli) {
            if (clienteNuevo.txtNombreCliente.getText().equals("")
                    || clienteNuevo.txtTelefonoCli.getText().equals("")
                    || clienteNuevo.txtDireccionCli.getText().equals("")
                    || clienteNuevo.txtCombinacionCli.getText().equals("")
                    || clienteNuevo.txtYogurtCli.getText().equals("")
                    || clienteNuevo.txtHeladoCli.getText().equals("")
                    || clienteNuevo.txtCoberturaCli.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "no dejes ESPACIOS EN BLANCO", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                cl.setNombre(clienteNuevo.txtNombreCliente.getText());
                cl.setTelefono(clienteNuevo.txtTelefonoCli.getText());
                cl.setDireccion(clienteNuevo.txtDireccionCli.getText());
                cl.setCombinacion(clienteNuevo.txtCombinacionCli.getText());
                cl.setYogurt(clienteNuevo.txtYogurtCli.getText());
                cl.setHelado(clienteNuevo.txtHeladoCli.getText());
                cl.setCobertura(clienteNuevo.txtCoberturaCli.getText());
                String respuesta = clDao.registrar(cl);
                switch (respuesta) {
                    case "existe":
                        JOptionPane.showMessageDialog(null, "El cliente ya existe");
                        break;
                    case "registrado":
                        limpiarTable();
                        listarClienter();
                        limpiar();
                        clienteNuevo.dispose();
                        JOptionPane.showMessageDialog(null, "Cliente registrado", "Registro confirmado", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error al registrar", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        } else if (e.getSource() == clienteModificado.btnModificarCli) {
            if (views.txtIdCli.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                if (clienteModificado.txtNombreCliente.getText().equals("")
                        || clienteModificado.txtTelefonoCli.getText().equals("")
                        || clienteModificado.txtDireccionCli.getText().equals("")
                        || clienteModificado.txtCombinacionCli.getText().equals("")
                        || clienteModificado.txtYogurtCli.getText().equals("")
                        || clienteModificado.txtHeladoCli.getText().equals("")
                        || clienteModificado.txtCoberturaCli.getText().equals("")) {
                     JOptionPane.showMessageDialog(null, "no dejes ESPACIOS EN BLANCO", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    cl.setNombre(clienteModificado.txtNombreCliente.getText());
                    cl.setTelefono(clienteModificado.txtTelefonoCli.getText());
                    cl.setDireccion(clienteModificado.txtDireccionCli.getText());
                    cl.setCombinacion(clienteModificado.txtCombinacionCli.getText());
                    cl.setYogurt(clienteModificado.txtYogurtCli.getText());
                    cl.setHelado(clienteModificado.txtHeladoCli.getText());
                    cl.setCobertura(clienteModificado.txtCoberturaCli.getText());
                    cl.setId(Integer.parseInt(views.txtIdCli.getText()));
                    String respuesta = clDao.modificar(cl);
                    switch (respuesta) {
                        case "existe":
                            JOptionPane.showMessageDialog(null, "El cliente ya existe");
                            break;
                        case "modificado":
                            limpiarTable();
                            listarClienter();
                            limpiar();
                            JOptionPane.showMessageDialog(null, "Cliente modificado con exito");
                            clienteModificado.dispose();
                            views.txtIdCli.setText("");
                            break;
                        default:       
                        JOptionPane.showMessageDialog(null, "Error al modificar cliente", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
            }
        } else if (e.getSource() == views.JMenuEliminarCli){
            if (views.txtIdCli.getText().equals("")) { 
                 JOptionPane.showMessageDialog(null, "Selecciona una fila", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                 int id = Integer.parseInt(views.txtIdCli.getText());
                if (clDao.accion("Inactivo", id)) {
                    limpiarTable();
                    listarClienter();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Cliente eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar cliente");
                }
            }
        } else if (e.getSource() == views.JMenuReingresarCli){
            if (views.txtIdCli.getText().equals("")) {  
                JOptionPane.showMessageDialog(null, "Selecciona una fila", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                 int id = Integer.parseInt(views.txtIdCli.getText());
                if (clDao.accion("Activo", id)) {
                    limpiarTable();
                    listarClienter();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Cliente reingresado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al reingresar cliente", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == views.JMenuEditarCli) {
            if (views.txtIdCli.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "", "Campo vacio", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int id = Integer.parseInt(views.txtIdCli.getText());
                clienteModificado.setVisible(true);
            }
        } else if (e.getSource() == views.jMenuCliente) {
            views.jTabbedPane1.setSelectedIndex(1);
            limpiarTable();
            listarClienter();
        }
        else {
            limpiar();
        }
        
    }
    
    // Metodos para el JTable
     // Listar usuarios
    public void listarClienter() {
        Tables color = new Tables();
        views.TableClientes.setDefaultRenderer(views.TableClientes.getColumnClass(0), color);
        List<Clientes> lista = clDao.ListaCliente(views.txtBuscarCli.getText());
        modelo = (DefaultTableModel) views.TableClientes.getModel();
        Object[] ob = new Object[9];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getTelefono();
            ob[3] = lista.get(i).getDireccion();
            ob[4] = lista.get(i).getCombinacion();
            ob[5] = lista.get(i).getYogurt();
            ob[6] = lista.get(i).getHelado();
            ob[7] = lista.get(i).getCobertura();
            ob[8] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        // Establecer modelo a tabla
        views.TableClientes.setModel(modelo);
        // Establecer color a tabla
        JTableHeader header = views.TableClientes.getTableHeader();
        Font headerFont = new Font("SansSerif", Font.BOLD, 16);
        header.setFont(headerFont);
        header.setOpaque(false);
        header.setBackground(new Color(30,30,30)); //16,49,114 0,81,135
        header.setForeground(new Color(255,255,255));
    }

    public void limpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    // Metodos de mouse
    @Override
    public void mouseClicked(MouseEvent e) {
       if (e.getSource() == views.TableClientes) {
            int fila = views.TableClientes.rowAtPoint(e.getPoint());
            views.txtIdCli.setText(views.TableClientes.getValueAt(fila, 0).toString());
            clienteModificado.txtNombreCliente.setText(views.TableClientes.getValueAt(fila, 1).toString());
            clienteModificado.txtTelefonoCli.setText(views.TableClientes.getValueAt(fila, 2).toString());
            clienteModificado.txtDireccionCli.setText(views.TableClientes.getValueAt(fila, 3).toString());
            clienteModificado.txtCombinacionCli.setText(views.TableClientes.getValueAt(fila, 4).toString());
            clienteModificado.txtYogurtCli.setText(views.TableClientes.getValueAt(fila, 5).toString());
            clienteModificado.txtHeladoCli.setText(views.TableClientes.getValueAt(fila, 6).toString());
            clienteModificado.txtCoberturaCli.setText(views.TableClientes.getValueAt(fila, 7).toString());
            //clienteModificado.setVisible(true);
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
    
    // Metodo para limpiar campos de texto
    private void limpiar() {
        views.txtIdCli.setText("");
        clienteModificado.txtNombreCliente.setText("");
        clienteModificado.txtTelefonoCli.setText("");
        clienteModificado.txtDireccionCli.setText("");
        clienteModificado.txtCombinacionCli.setText("");
    }
    
    // Metodo de key
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarCli) {
            limpiarTable();
            listarClienter();
        }
    }
    
    


}
