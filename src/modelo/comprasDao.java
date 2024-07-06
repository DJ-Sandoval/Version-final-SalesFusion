
package modelo;

import java.security.Security;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import seguridad.ClavesSecurity;

/**
 *
 * @author Jose
 */
public class comprasDao {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    /*
    // Listar Proveedores listaProveedores
    public List ListaCompras(String valor) {
        List<compras> lista = new ArrayList();
        // Consulta para ordenar registros listados y buscar en la caja de texto
        String sql = "SELECT c.*, p.proveedor FROM compras c INNER JOIN proveedor p ON c.id_proveedor = p.id ORDER BY c.id DESC";
        String buscar = "SELECT c.*, p.proveedor FROM compras c INNER JOIN proveedor p ON c.id_proveedor = p.id WHERE p.proveedor LIKE ? OR c.fecha LIKE ?";
//String buscar = "SELECT c.*, p.proveedor FROM compras c INNER JOIN proveedor p ON c.id_proveedor = p.idWHERE p.proveedor LIKE '%" + valor + "%' OR c.fecha LIKE '%" + valor + "%'";
        try {
            con = cn.getConexion();
            if (valor.equalsIgnoreCase("")) {
                ps = con.prepareStatement(sql);
            } else {
                ps = con.prepareStatement(buscar);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                compras com = new compras();
                com.setId(rs.getInt("id"));
                com.setId_proveedor(rs.getInt("id_proveedor"));
                com.setTotal(rs.getDouble("total"));
                com.setFecha(rs.getString("fecha"));
                com.setNombreProveedor(rs.getString("proveedor"));
                lista.add(com);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return lista;
    }
*/
    
    // Listar Compras
public List<compras> ListaCompras(String valor) {
    List<compras> lista = new ArrayList<>();
    // Consulta para ordenar registros listados y buscar en la caja de texto
    String sql = "SELECT c.*, p.proveedor FROM compras c INNER JOIN proveedor p ON c.id_proveedor = p.id ORDER BY c.id DESC";
    String buscar = "SELECT c.*, p.proveedor FROM compras c INNER JOIN proveedor p ON c.id_proveedor = p.id WHERE p.proveedor LIKE ? OR c.fecha LIKE ?";
    try {
        con = cn.getConexion();
        if (valor.equalsIgnoreCase("")) {
            ps = con.prepareStatement(sql);
        } else {
            // Uso de PreparedStatement para evitar inyección SQL
            ps = con.prepareStatement(buscar);
            ps.setString(1, "%" + valor + "%");
            ps.setString(2, "%" + valor + "%");
        }
        rs = ps.executeQuery();
        while (rs.next()) {
            compras com = new compras();
            com.setId(rs.getInt("id"));
            com.setId_proveedor(rs.getInt("id_proveedor"));
            com.setTotal(rs.getDouble("total"));
            com.setFecha(rs.getString("fecha"));
            com.setNombreProveedor(rs.getString("proveedor")); // Cambié "nombreProveedor" a "proveedor" ya que la consulta selecciona "p.proveedor"
            lista.add(com);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
    }
    return lista;
}

    // Método de autenticación
    private static boolean authenticate(String password) {
        // Define your password here
        String correctPassword = "12345";
        return password.equals(correctPassword);
    }

    // Método para eliminar un registro con autenticación
    public boolean eliminarConAutenticacion(int id, String password) {
    // Verificar la autenticación
    if (!authenticate(password)) {
        JOptionPane.showMessageDialog(null, "Clave incorrecta. No se puede eliminar la compra");
        return false;
    }

    // Consulta SQL para eliminar los registros dependientes
    String sqlDetalle = "DELETE FROM detalle_compra WHERE id_compra = ?";
    // Consulta SQL para eliminar la compra
    String sqlCompra = "DELETE FROM compras WHERE id = ?";

    try {
        con = cn.getConexion();
        // Eliminar registros en detalle_compra
        ps = con.prepareStatement(sqlDetalle);
        ps.setInt(1, id);
        ps.executeUpdate();

        // Eliminar la compra
        ps = con.prepareStatement(sqlCompra);
        ps.setInt(1, id);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}
    
    // Metodo para refrescar tabla
    public void refrescarTablaCompras() {
        
    }

}
