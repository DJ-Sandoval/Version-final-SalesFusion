
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
public class CategoriaDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
     // Metodo para registrar Categoria
     public boolean registrar(Categorias cat) {
       String sql = "INSERT INTO categorias (categoria) VALUES (?)";
       try {
           con = cn.getConexion();
           ps = con.prepareStatement(sql);
           ps.setString(1, cat.getNombre());
           ps.execute();
           return true;
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.toString());
           return false;
       }
   }
     
   // Listar categorias
   public List ListaCategoria(String valor) {
        List<Categorias> listaCategoria = new ArrayList();
        // Consulta para ordenar registros listados y buscar en la caja de texto
        String sql = "SELECT * FROM categorias ORDER BY estado ASC";
        String buscar = "SELECT * FROM categorias WHERE categoria LIKE '%" + valor + "%'";
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
                Categorias cat = new Categorias();
                cat.setId(rs.getInt("id"));
                cat.setNombre(rs.getString("categoria"));
                cat.setEstado(rs.getString("estado"));
                listaCategoria.add(cat);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaCategoria;
    }   
   
   // Metodo para modificar
   public boolean modificar(Categorias cat) {
       String sql = "UPDATE categorias SET categoria = ?  WHERE id = ?";
       try {
           con = cn.getConexion();
           ps = con.prepareStatement(sql);
           ps.setString(1, cat.getNombre());
           ps.setInt(2, cat.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.toString());
           return false;
       }
   }
   
   public boolean accion(String estado, int id) {
       String sql = "UPDATE categorias SET estado = ? WHERE id = ?";
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
