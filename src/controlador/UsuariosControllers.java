
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
import modelo.Tables;
import modelo.UsuarioDao;
import modelo.Usuarios;
import vista.FrmModificarUsuario;
import vista.FrmNuevoUsuario;
import vista.PanelAdmin;

/**
 *
 * @author Jose
 */
public class UsuariosControllers implements ActionListener, MouseListener, KeyListener {

    private Usuarios us;
    private UsuarioDao usDao;
    private PanelAdmin views;
    private FrmNuevoUsuario usuarioNuevo;
    private FrmModificarUsuario usuarioModificado;

    DefaultTableModel modelo = new DefaultTableModel();

    // Constructor
    public UsuariosControllers(Usuarios us, UsuarioDao usDao, PanelAdmin views, FrmNuevoUsuario usuarioNuevo, FrmModificarUsuario usuarioModificado) {
        this.us = us;
        this.usDao = usDao;
        this.views = views;
        this.usuarioModificado = usuarioModificado;
        this.usuarioNuevo = usuarioNuevo;
        this.usuarioNuevo.btnRegistrarUser.addActionListener(this);
        this.views.btnRegistrarUser.addActionListener(this);
        this.views.TableUsers.addMouseListener(this);
        //this.views.btnModificarUser.addActionListener(this);
        this.usuarioModificado.btnModificarUser.addActionListener(this);
        this.views.JMenuEliminarUsers.addActionListener(this);
        this.views.JMenuReingresarUsers.addActionListener(this);
        this.views.txtBuscarUsers.addKeyListener(this);
        listarUsuarios();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == usuarioNuevo.btnRegistrarUser) {
            if (usuarioNuevo.txtUsuarioUser.getText().equals("")
                    || usuarioNuevo.txtNombreUser.getText().equals("")
                    || String.valueOf(usuarioNuevo.txtClaveUser.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(null, "no dejes ESPACIOS EN BLANCO");
            } else {
                us.setUsuario(usuarioNuevo.txtUsuarioUser.getText());
                us.setNombre(usuarioNuevo.txtNombreUser.getText());
                us.setClave(String.valueOf(usuarioNuevo.txtClaveUser.getPassword()));
                us.setCaja(usuarioNuevo.cbxCajaUser.getSelectedItem().toString());
                us.setRol(usuarioNuevo.cbxRolUser.getSelectedItem().toString());
                if (usDao.registrar(us)) {
                    limpiarTable();
                    listarUsuarios();
                    limpiar2();
                    JOptionPane.showMessageDialog(null, "Usuario Registrado con exito");
                    views.txtIdUser.setText("");
                    usuarioNuevo.dispose();
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar el usuario");
                }
            }
        } else if (e.getSource() == usuarioModificado.btnModificarUser) {
            if (usuarioModificado.txtUsuarioUser.getText().equals("")
                    || usuarioModificado.txtNombreUser.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila");                
            } else {
                us.setUsuario(usuarioModificado.txtUsuarioUser.getText());
                us.setNombre(usuarioModificado.txtNombreUser.getText());
                us.setClave(String.valueOf(usuarioModificado.txtClaveUser.getPassword()));
                us.setCaja(usuarioModificado.cbxCajaUser.getSelectedItem().toString());
                us.setRol(usuarioModificado.cbxRolUser.getSelectedItem().toString());
                us.setId(Integer.parseInt(views.txtIdUser.getText()));
                if (usDao.modificar(us)) {
                    limpiarTable();
                    listarUsuarios();
                    JOptionPane.showMessageDialog(null, "Usuario Modificado con exito");
                    usuarioModificado.dispose();
                    views.txtIdUser.setText("");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar el usuario");
                }
            }
        } else if (e.getSource() == views.JMenuEliminarUsers) {
            if (views.txtIdUser.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
            } else {
                int id = Integer.parseInt(views.txtIdUser.getText());
                if (usDao.accion("Inactivo", id)) {
                    limpiarTable();
                    listarUsuarios();
                    limpiar2();
                    JOptionPane.showMessageDialog(null, "Usuario eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar usuario");
                }
            }
        } else if (e.getSource() == views.JMenuReingresarUsers) {
            if (views.txtIdUser.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar");
            } else {
                int id = Integer.parseInt(views.txtIdUser.getText());
                if (usDao.accion("Activo", id)) {
                    limpiarTable();
                    listarUsuarios();
                    limpiar2();
                    JOptionPane.showMessageDialog(null, "Usuario reingresado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al reingresar usuario");
                }
            }
        } else {
            limpiar2();
        }
        
    }

    // Listar usuarios
    public void listarUsuarios() {
        Tables color = new Tables();
        views.TableUsers.setDefaultRenderer(views.TableUsers.getColumnClass(0), color);
        List<Usuarios> lista = usDao.ListaUsuario(views.txtBuscarUsers.getText());
        modelo = (DefaultTableModel) views.TableUsers.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getUsuario();
            ob[2] = lista.get(i).getNombre();
            ob[3] = lista.get(i).getCaja();
            ob[4] = lista.get(i).getRol();
            ob[5] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        // Establecer modelo a tabla
        views.TableUsers.setModel(modelo);
        
        // Establecer color a tabla
        JTableHeader header = views.TableUsers.getTableHeader();
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

    // Metodo para limpiar campos
    public void limpiar() {
        usuarioModificado.txtUsuarioUser.getText();
        usuarioModificado.txtNombreUser.getText();
        usuarioModificado.txtClaveUser.getText();
    }

    // Evento del mouse para el JTable
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableUsers) {
            int fila = views.TableUsers.rowAtPoint(e.getPoint());
            views.txtIdUser.setText(views.TableUsers.getValueAt(fila, 0).toString());
            usuarioModificado.txtUsuarioUser.setText(views.TableUsers.getValueAt(fila, 1).toString());
            usuarioModificado.txtNombreUser.setText(views.TableUsers.getValueAt(fila, 2).toString());
            usuarioModificado.cbxCajaUser.setSelectedItem(views.TableUsers.getValueAt(fila, 3).toString());
            usuarioModificado.cbxRolUser.setSelectedItem(views.TableUsers.getValueAt(fila, 4).toString());
            usuarioModificado.txtClaveUser.setEnabled(false);
            usuarioModificado.setVisible(true);
            //views.btnRegistrarUser.setEnabled(false);
        }
    }
    
    // Metodos Mouse
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
    
    // Metodos KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarUsers) {
            limpiarTable();
            listarUsuarios();
        }
    }
    
    private void limpiar2() {
        //views.txtIdUser.setText("");
        //views.txtUsuarioUser.setText("");
        //views.txtNombreUser.setText("");
       // views.txtClaveUser.setText("");
    }

}
