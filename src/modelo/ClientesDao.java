
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
public class ClientesDao {

    // Variables de conexion
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public String registrar(Clientes cl) {
        String consulta = "SELECT * FROM clientes WHERE nombre = ?, telefono = ?";
       String sql = "INSERT INTO clientes (nombre, telefono, direccion, combinacion, yogurt, helado, cobertura) VALUES (?,?,?,?,?,?,?)";
       try {
           con = cn.getConexion();
           ps = con.prepareStatement(consulta);
           ps.setString(1, cl.getNombre());
           ps.setString(2, cl.getTelefono());
           rs = ps.executeQuery();
           if (rs.next()) {
               return "existe";
           } else {
           ps = con.prepareStatement(sql);
           ps.setString(1, cl.getNombre());
           ps.setString(2, cl.getTelefono());
           ps.setString(3, cl.getDireccion());
           ps.setString(4, cl.getCombinacion());
           ps.setString(5, cl.getYogurt());
           ps.setString(6, cl.getHelado());
           ps.setString(7, cl.getCobertura());
           ps.execute();
           return "registrado";
           }
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.toString());
           return "error";
       }
       
   }
    
       // Listar Usuarios
    public List ListaCliente(String valor) {
        List<Clientes> listaCli = new ArrayList();
        // Consulta para ordenar registros listados y buscar en la caja de texto
        String sql = "SELECT * FROM clientes ORDER BY estado ASC";
        String buscar = "SELECT * FROM clientes WHERE nombre LIKE '%" + valor + "%' OR telefono LIKE '%" + valor + "%'";
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
                Clientes cli = new Clientes();
                cli.setId(rs.getInt("id"));
                cli.setNombre(rs.getString("nombre"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setCombinacion(rs.getString("combinacion"));
                cli.setYogurt(rs.getString("yogurt"));
                cli.setHelado(rs.getString("helado"));
                cli.setCobertura(rs.getString("cobertura"));
                cli.setEstado(rs.getString("estado"));
                listaCli.add(cli);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaCli;
    }
   
    // Modificar registro
    public String modificar(Clientes cl) {
        String consulta = "SELECT * FROM clientes WHERE nombre = ? AND telefono = ? AND id != ?";
        String sql = "UPDATE clientes SET nombre = ?, telefono = ?, direccion =?, combinacion = ?, yogurt = ?, helado = ?, cobertura = ? WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(consulta);
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getTelefono());
            ps.setInt(3, cl.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                return "existe";
            } else {
                ps = con.prepareStatement(sql);
                ps.setString(1, cl.getNombre());
                ps.setString(2, cl.getTelefono());
                ps.setString(3, cl.getDireccion());
                ps.setString(4, cl.getCombinacion());
                ps.setString(5, cl.getYogurt());
                ps.setString(6, cl.getHelado());
                ps.setString(7, cl.getCobertura());
                ps.setInt(8, cl.getId());
                ps.execute();
                return "modificado";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
       }
   }
   
   public boolean accion(String estado, int id) {
       String sql = "UPDATE clientes SET estado = ? WHERE id = ?";
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


   
}
