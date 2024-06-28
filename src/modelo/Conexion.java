
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;
/**
 *
 * @author Jose
 */
public class Conexion {
     Connection con;
    public Connection getConexion(){
        try {
            String db = "jdbc:mysql://localhost:3306/sistema pos venta";
            con = DriverManager.getConnection(db, "root", "");
            return con;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return null;
    }
     /*
    public Connection getConexion() {
        try {
            String db = "jdbc:sqlite:C:\\Users\\Jose\\Documents\\bases1/sistemapos.s3db";
            con = DriverManager.getConnection(db);
            return con;
        } catch (SQLException e)   {        
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return null;
    }
    */
      

    
    // Metodo alterno para login
    public static Connection conectar() {
        try { 
            Connection cn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Jose\\Documents\\bases1/sistemapos.s3db");
            System.out.println("conexion" + cn);
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexion local " + e);
        }
        return null;
    }
    
    
    public Connection getConexion3() {
        try {
            String db = "jdbc:sqlite:C:\\Users\\Jose\\Documents\\bases1/sistemapos.s3db";
            con = DriverManager.getConnection(db);
            return con;
        } catch (SQLException e)   {        
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return con;
    }
}
