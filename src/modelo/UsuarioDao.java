
package modelo;

//import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Jose
 */
public class UsuarioDao {
     Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public Usuarios login(String usuario, String clave){
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND clave = ?";
        Usuarios us = new Usuarios();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            if (rs.next()) {
                us.setId(rs.getInt("id"));
                us.setUsuario(rs.getString("usuario"));
                us.setNombre(rs.getString("nombre"));
                us.setRol(rs.getString("rol"));
                us.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return us;
    }
    /*
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
     public Usuarios login(String usuario, String clave) {
        String sql = "SELECT * FROM Usuarios WHERE usuario = ? AND clave = ?";
        Usuarios us = new Usuarios();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            if (rs.next()) {
                us.setId(rs.getInt("id"));
                us.setUsuario(rs.getString("usuario"));
                us.setNombre(rs.getString("nombre"));
                us.setCaja(rs.getString("caja"));
                us.setRol(rs.getString("rol"));
                us.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.toString());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
            }
        }
        return us;
    }
     public boolean loginUser(Usuarios objeto) {
    boolean respuesta = false;
    String sql = "SELECT usuario, clave FROM Usuarios WHERE usuario = ? AND clave = ?";
    
    try (Connection cn = Conexion.conectar();
         PreparedStatement pst = cn.prepareStatement(sql)) {
        
        pst.setString(1, objeto.getUsuario());
        pst.setString(2, objeto.getClave());
        
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                respuesta = true;
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al Iniciar Sesion: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Error al Iniciar Sesion: " + e.getMessage());
    }
    
    return respuesta;
}

   /*
   public Usuarios loginUser(Usuarios objeto) {
        String sql = "SELECT * FROM Usuarios WHERE usuario = ? AND clave = ?";
        Usuarios us = new Usuarios();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, objeto.getUsuario());
            ps.setString(2, objeto.getClave());
            rs = ps.executeQuery();
            if (rs.next()) {
                us.setId(rs.getInt("id"));
                us.setUsuario(rs.getString("usuario"));
                us.setNombre(rs.getString("nombre"));
                us.setCaja(rs.getString("caja"));
                us.setRol(rs.getString("rol"));
                us.setEstado(rs.getString("estado"));
            } else {
                us = null; // No se encontró el usuario
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return us;
    }
   */

   public boolean registrar(Usuarios us) {
       String sql = "INSERT INTO Usuarios (usuario, nombre, clave, id_caja, rol) VALUES (?,?,?,?,?)";
       try {
           con = cn.getConexion();
           ps = con.prepareStatement(sql);
           ps.setString(1, us.getUsuario());
           ps.setString(2, us.getNombre());
           ps.setString(3, us.getClave());
           ps.setInt(4, us.getCaja());
           ps.setString(5, us.getRol());
           ps.execute();
           return true;
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.toString());
           return false;
       }
   }
   
    // Listar Usuarios
    public List ListaUsuario(String valor) {
        List<Usuarios> listaUsers = new ArrayList();
        // Consulta para ordenar registros listados y buscar en la caja de texto
        String sql = "SELECT u.*, c.caja AS caja FROM usuarios u INNER JOIN cajas c ON u.id_caja = c.id ORDER BY u.estado ASC";
        String buscar = "SELECT u.*, c.caja AS caja FROM usuarios u INNER JOIN cajas c ON u.id_caja = c.id WHERE u.usuario LIKE '%" + valor + "%' OR u.nombre LIKE '%"+ valor+"%' OR c.caja LIKE '%"+ valor +"%'";
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
                Usuarios us = new Usuarios();
                us.setId(rs.getInt("id"));
                us.setUsuario(rs.getString("usuario"));
                us.setNombre(rs.getString("nombre"));
                us.setCaja(rs.getInt("id_caja"));
                us.setNombre_caja(rs.getString("caja"));
                us.setRol(rs.getString("rol"));
                us.setEstado(rs.getString("estado"));
                listaUsers.add(us);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaUsers;
    }
   
   // Modificar registro
   public boolean modificar(Usuarios us) {
       String sql = "UPDATE Usuarios SET usuario = ?, nombre = ?, id_caja =?, rol = ? WHERE id = ?";
       try {
           con = cn.getConexion();
           ps = con.prepareStatement(sql);
           ps.setString(1, us.getUsuario());
           ps.setString(2, us.getNombre());
           ps.setInt(3, us.getCaja());
           ps.setString(4, us.getRol());
           ps.setInt(5, us.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.toString());
           return false;
       }
   }
   
   public boolean accion(String estado, int id) {
       String sql = "UPDATE Usuarios SET estado = ? WHERE id = ?";
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
   public Configuracion getConfig() {
        String sql = "SELECT * FROM configuracion";
        Configuracion cof = new Configuracion();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                cof.setId(rs.getInt("id"));
                cof.setRfc(rs.getString("rfc"));
                cof.setNombre(rs.getString("nombre"));
                cof.setTelefono(rs.getString("Telefono"));
                cof.setDireccion(rs.getString("direccion"));
                cof.setCodigoPostal(rs.getString("codigoPostal"));
                cof.setMensaje(rs.getString("mensaje"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return cof;
   }
   
   // Metodo para modificar la configuracion
   public boolean modificar(Configuracion cof) {
       String sql = "UPDATE configuracion SET rfc=?, nombre=?, telefono=?, direccion=?, codigoPostal=?, mensaje=? WHERE id=?";
       try {
           con = cn.getConexion();
           ps = con.prepareStatement(sql);
           ps.setString(1, cof.getRfc());
           ps.setString(2, cof.getNombre());
           ps.setString(3, cof.getTelefono());
           ps.setString(4, cof.getDireccion());
           ps.setString(5, cof.getCodigoPostal());
           ps.setString(6, cof.getMensaje());
           ps.setInt(7, cof.getId());
           
           int rowsAffected = ps.executeUpdate();
           return rowsAffected > 0;
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.toString());
           return false;
       }
   }
   
   // Metodo para eliminar
   
   
   
   
   
   
}
