
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Conexion;
import vista.PanelAdmin;

/**
 *
 * @author Jose
 */
public class ContenedoresController implements ActionListener, MouseListener {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    
    private final PanelAdmin vista;

    public ContenedoresController(PanelAdmin vista) {
        this.vista = vista;
        contarCompras();
        contarProductos();
        contarClientes();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
    
    // Moldes de metodo para el conteo
    
    // Metodo para contar productos
     public void contarProductos()  {
       String sql = "SELECT COUNT(*) AS total_registro FROM productos";
       try {
          con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                 int totalRegistros = rs.getInt("total_registro");
                 vista.ConteoProductos(totalRegistros);
            }
       } catch (SQLException e) {
         e.printStackTrace(); 
       }
    }
    
    // Metodo para contar Compras
    public void contarCompras()  {
       String sql = "SELECT COUNT(*) AS total_registro FROM compras";
       try {
          con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                 int totalRegistros = rs.getInt("total_registro");
                 vista.ConteoCompras(totalRegistros);
            }
       } catch (SQLException e) {
         e.printStackTrace(); 
       }
    }
    
    // Metodo para contar Clientes
     public void contarClientes()  {
       String sql = "SELECT COUNT(*) AS total_registro FROM clientes";
       try {
          con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                 int totalRegistros = rs.getInt("total_registro");
                 vista.ConteoClientes(totalRegistros);
            }
       } catch (SQLException e) {
         e.printStackTrace(); 
       }
    }
    // Metodo para contar Ventas
    public void contarVentas() {
        String sql = "SELECT COUNT(*) AS total_registro FROM ventas";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int totalRegistros = rs.getInt("total_registro");
                vista.ConteoVentas(totalRegistros);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}
