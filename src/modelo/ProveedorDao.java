
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose
 */
public class ProveedorDao {
   Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    // Metodo para registrar Proveedores
     public boolean registrar(Proveedor prov) {
       String sql = "INSERT INTO Proveedor (rfc, proveedor, telefono, direccion) VALUES (?,?,?,?)";
       try {
           con = cn.getConexion();
           ps = con.prepareStatement(sql);
           ps.setString(1, prov.getRfc());
           ps.setString(2, prov.getNombre());
           ps.setString(3, prov.getTelefono());
           ps.setString(4, prov.getDireccion());
           ps.execute();
           return true;
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.toString());
           return false;
       }
   }
     
      // Listar Proveedores listaProveedores
    public List ListaProveedor(String valor) {
        List<Proveedor> listaProveedores = new ArrayList();
        // Consulta para ordenar registros listados y buscar en la caja de texto
        String sql = "SELECT * FROM Proveedor ORDER BY estado ASC";
        String buscar = "SELECT * FROM Proveedor WHERE rfc LIKE '%" + valor + "%' OR proveedor LIKE '%" + valor + "%'";
        try {
            con = cn.getConexion();
            if (valor.equalsIgnoreCase("")) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                ps = con.prepareStatement(buscar);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                Proveedor prov = new Proveedor();
                prov.setId(rs.getInt("id"));
                prov.setRfc(rs.getString("rfc"));
                prov.setNombre(rs.getString("proveedor"));
                prov.setTelefono(rs.getString("telefono"));
                prov.setDireccion(rs.getString("direccion"));
                prov.setEstado(rs.getString("estado"));
                listaProveedores.add(prov);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaProveedores;
    }
    
    // Metodo para modificar
   public boolean modificar(Proveedor prov) {
       String sql = "UPDATE Proveedor SET rfc = ?, proveedor = ?, telefono =?, direccion = ? WHERE id = ?";
       try {
           con = cn.getConexion();
           ps = con.prepareStatement(sql);
           ps.setString(1, prov.getRfc());
           ps.setString(2, prov.getNombre());
           ps.setString(3, prov.getTelefono());
           ps.setString(4, prov.getDireccion());
           ps.setInt(5, prov.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.toString());
           return false;
       }
   }
   
   public boolean accion(String estado, int id) {
       String sql = "UPDATE Proveedor SET estado = ? WHERE id = ?";
       try {
           con = cn.getConexion();
           ps = con.prepareStatement(sql);
           ps.setString(1,estado);
           ps.setInt(2, id);
           ps.execute();
           return true;
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.toString());
           return false;
        }
   }
   
    // Metodo para los datos de la empresa
   public Proveedor getDatos(int id_compra) {
        String sql = "SELECT p.*, c.id_proveedor FROM proveedor p INNER JOIN compras c ON p.id = c.id_proveedor WHERE c.id = ?";
        Proveedor prov = new Proveedor();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_compra);
            rs = ps.executeQuery();
            if (rs.next()) {
                prov.setId(rs.getInt("id"));
                prov.setRfc(rs.getString("rfc"));
                prov.setNombre(rs.getString("proveedor"));
                prov.setTelefono(rs.getString("telefono"));
                prov.setDireccion(rs.getString("direccion"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return prov;
   }
}
