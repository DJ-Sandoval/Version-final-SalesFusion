
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
public class CajaDao {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
     // Metodo para registrar Categoria
     public boolean registrar(Cajas caja) {
       String sql = "INSERT INTO cajas (caja) VALUES (?)";
       try {
           con = cn.getConexion();
           ps = con.prepareStatement(sql);
           ps.setString(1, caja.getNombre());
           ps.execute();
           return true;
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.toString());
           return false;
       }
   }
     
    // Listar cajas
    public List ListaCaja(String valor) {
        List<Cajas> listaCaja = new ArrayList();
        try {
            con = cn.getConexion();
            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT * FROM cajas ORDER BY estado ASC";
                ps = con.prepareStatement(sql);
            } else {
                String buscar = "SELECT * FROM cajas WHERE caja LIKE '%" + valor + "%' OR estado LIKE '%" + valor + "%'";
                ps = con.prepareStatement(buscar);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Cajas caja = new Cajas();
                caja.setId(rs.getInt("id"));
                caja.setNombre(rs.getString("caja"));
                caja.setEstado(rs.getString("estado"));
                listaCaja.add(caja);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaCaja;
    }
   
   // Metodo para modificar
   public boolean modificar(Cajas caja) {
       String sql = "UPDATE cajas SET caja = ?  WHERE id = ?";
       try {
           con = cn.getConexion();
           ps = con.prepareStatement(sql);
           ps.setString(1, caja.getNombre());
           ps.setInt(2, caja.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.toString());
           return false;
       }
   }
   
   public boolean accion(String estado, int id) {
       String sql = "UPDATE cajas SET estado = ? WHERE id = ?";
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
   
   // AbrirCaja
    public String abrirCaja(double monto, int id_user) {
        // Validacion para que no deje abrir la caja
        String consulta = "SELECT d.*, u.nombre FROM detalle_cajas d INNER JOIN usuarios u ON d.id_user = u.id WHERE d.estado = 1";
        String sql = "INSERT INTO detalle_cajas (monto_inicial, id_user) VALUES (?,?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            if (rs.next()) {
                return "existe";
            } else {
                ps = con.prepareStatement(sql);
                ps.setDouble(1, monto);
                ps.setInt(2, id_user);
                ps.execute();
                return "registrado";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
        }
    }
     
   // Listar apertura
       // Listar cajas
      public List ListarAperturas(String valor) {
        List<AperturaCierre> lista = new ArrayList();
        try {
            con = cn.getConexion();
            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT d.*, u.nombre FROM detalle_cajas d INNER JOIN usuarios u ON d.id_user = u.id ORDER BY d.fecha_apertura DESC";
                ps = con.prepareStatement(sql);
            } else {
                String sql = "SELECT d.*, u.nombre FROM detalle_cajas d INNER JOIN usuarios u ON d.id_user = u.id WHERE u.nombre LIKE '%" + valor + "%' OR d.fecha_apertura LIKE '%" + valor + "%'";
                ps = con.prepareStatement(sql);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                AperturaCierre apertura = new AperturaCierre();
                apertura.setFecha_apertura(rs.getString("fecha_apertura"));
                apertura.setFecha_cierre(rs.getString("fecha_cierre"));
                apertura.setMonto_inicial(rs.getDouble("monto_inicial"));
                apertura.setMonto_final(rs.getDouble("monto_final"));
                apertura.setTotal_ventas(rs.getInt("total_ventas"));
                apertura.setNombre_usuario(rs.getString("nombre"));
                lista.add(apertura);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return lista;
    }
    
    
    
    
    
     public double montoFinal(int id_user) {
    double montoInicial = 0.00;
    double ventasTotales = 0.00;
    String fechaApertura = "";

    // Obtener el monto inicial y la fecha de apertura
    String sqlMontoInicial = "SELECT monto_inicial, fecha_apertura FROM detalle_cajas WHERE id_user = ? AND estado = 1";
    try {
        con = cn.getConexion();
        ps = con.prepareStatement(sqlMontoInicial);
        ps.setInt(1, id_user);
        rs = ps.executeQuery();
        if (rs.next()) {
            montoInicial = rs.getDouble("monto_inicial");
            fechaApertura = rs.getString("fecha_apertura");
        }
        rs.close();
        ps.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
    }

    // Mostrar el monto inicial y la fecha de apertura
    System.out.println("Monto Inicial: " + montoInicial);
    System.out.println("Fecha Apertura: " + fechaApertura);

    // Obtener las ventas totales desde la fecha de apertura
    String sqlVentas = "SELECT SUM(total) AS monto_total FROM ventas WHERE id_user = ? AND fecha >= ?";
    try {
        ps = con.prepareStatement(sqlVentas);
        ps.setInt(1, id_user);
        ps.setString(2, fechaApertura);
        rs = ps.executeQuery();
        if (rs.next()) {
            ventasTotales = rs.getDouble("monto_total");
        }
        rs.close();
        ps.close();
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.toString());
         }

         // Calcular monto final
         double montoFinal = montoInicial + ventasTotales;

// Crear el mensaje combinado
         String mensaje = "Ventas Totales: " + ventasTotales + "\nMonto final calculado: " + montoFinal;

// Mostrar el mensaje combinado
         JOptionPane.showMessageDialog(null, mensaje);

         return montoFinal;
}



    
    // Cerrar con el total de ventas
    public int totalVentas(int id_user) {
        int total = 0;
        String sql = "SELECT Count(*) AS total_ventas FROM ventas WHERE id_user = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total_ventas");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return total;
    }
    
    
    
     // Método para cerrar caja
public boolean cerrarCaja(AperturaCierre apertura) {
    String sql = "UPDATE detalle_cajas SET fecha_cierre = ?, monto_final = ?, total_ventas = ?, estado = 0 WHERE id_user = ? AND estado = 1";
    try {
        con = cn.getConexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, apertura.getFecha_cierre());
        ps.setDouble(2, apertura.getMonto_final());
        ps.setInt(3, apertura.getTotal_ventas());
        ps.setInt(4, apertura.getId_usuario());
        ps.execute();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}
     
   // Metodo para verificar si la caja esta cerrada
      // Método para verificar si la caja ya está cerrada
    public boolean verificarCajaCerrada(int idUsuario) {
        String sql = "SELECT COUNT(*) FROM detalle_cajas WHERE id_user = ? AND estado = 1";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0; // Si el resultado es 0, no hay cajas abiertas
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return false; // Si hay un error o hay cajas abiertas, retorna falso
    }

}
