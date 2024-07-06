
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ventas;

/**
 *
 * @author Jose
 */
public class ventasDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public List listaVentas(String valor) {
        List<ventas> lista = new ArrayList();
        String sql = "SELECT v.*, c.nombre FROM ventas v INNER JOIN clientes c ON v.id_cliente = c.id ORDER BY v.id DESC";
        String buscar = " SELECT v.*, c.nombre FROM ventas v INNER JOIN clientes c ON v.id_cliente = c.id WHERE c.nombre LIKE '%"+ valor +"%' OR v.fecha LIKE '%"+valor+"%'";
        try {
            con = cn.getConexion();
            if (valor.equalsIgnoreCase("")) {
                ps = con.prepareStatement(sql);
            } else {
                ps = con.prepareStatement(buscar);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                ventas vt = new ventas();
                vt.setId(rs.getInt("id"));
                vt.setId_cliente(rs.getInt("id_cliente"));
                vt.setTotal(rs.getDouble("total"));
                vt.setFecha(rs.getString("fecha"));
                vt.setNombreCliente(rs.getString("nombre"));
                lista.add(vt);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return lista;
    }
    
    
    // Metodos de seguridad y verificacion
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
    String sqlDetalle = "DELETE FROM detalle_ventas WHERE id_venta = ?";
    // Consulta SQL para eliminar la compra
    String sqlCompra = "DELETE FROM ventas WHERE id = ?";

    try {
        con = cn.getConexion();
        // Eliminar registros en detalle_venta
        ps = con.prepareStatement(sqlDetalle);
        ps.setInt(1, id);
        ps.executeUpdate();

        // Eliminar la venta
        ps = con.prepareStatement(sqlCompra);
        ps.setInt(1, id);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}
    
    public String verificarCaja(int id_user) {
        String consulta = "SELECT * FROM detalle_cajas WHERE estado = ? AND id_user = ?";
        con = cn.getConexion();
        try {
            ps = con.prepareStatement(consulta);
            ps.setInt(1, 1);
            ps.setInt(2, id_user);
            rs = ps.executeQuery();
            if (rs.next()) {
                return "existe";
            } else {
                return "no";
            }
        } catch (SQLException e) {
            return "error";
        }
    }

    
}
