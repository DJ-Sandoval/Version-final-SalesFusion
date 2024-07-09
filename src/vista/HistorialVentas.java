
package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Jose
 */
public class HistorialVentas extends javax.swing.JFrame {
    // Listas
    ArrayList<Integer> listaCantidad = new ArrayList<>();
    ArrayList<String> listaFechas = new ArrayList<>();
    // Variables
    int cantidadResultados = 0;
    // Vectores
    String[] vector_fechaVenta;
    int[] vector_status_cantidad;
    // Variables de conexion
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    /**
     * Creates new form HistorialVentas
     */
    public HistorialVentas() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Historial Ventas");  
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/grafico-de-torta.png"));
        setIconImage(icon.getImage());
        this.metodoContador();
        vector_fechaVenta = new String[cantidadResultados];
        vector_status_cantidad = new int[cantidadResultados];
        this.metodoAlmacenaData();
         
        
        
    }
    
    // Metodo para determinar la cantidad de resultados a graficar
private int metodoContador() {
    try {
        con = cn.getConexion();
        ps = con.prepareStatement(
            "SELECT v.fecha, COUNT(v.fecha) AS ventas, SUM(dv.cantidad) AS totalCantidad " +
            "FROM detalle_ventas dv " +
            "JOIN ventas v ON dv.id_venta = v.id " +
            "WHERE v.fecha BETWEEN ? AND ? " +
            "GROUP BY v.fecha"
        );
        ps.setString(1, InterGraficas.fecha_inicio);
        ps.setString(2, InterGraficas.fecha_fin);
        rs = ps.executeQuery();
        while (rs.next()) {
            cantidadResultados++;
        }
        con.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error en: " + e);
    }
    return cantidadResultados;
}

// Metodo para almacenar en las listas los resultados a graficar
private void metodoAlmacenaData() {
    try {
        con = cn.getConexion();
        ps = con.prepareStatement(
            "SELECT v.fecha, COUNT(v.fecha) AS ventas, SUM(dv.cantidad) AS totalCantidad " +
            "FROM detalle_ventas dv " +
            "JOIN ventas v ON dv.id_venta = v.id " +
            "WHERE v.fecha BETWEEN ? AND ? " +
            "GROUP BY v.fecha"
        );
        ps.setString(1, InterGraficas.fecha_inicio);
        ps.setString(2, InterGraficas.fecha_fin);
        rs = ps.executeQuery();
        int contador = 0;
        while (rs.next()) {
            vector_fechaVenta[contador] = rs.getString("fecha");
            listaFechas.add(vector_fechaVenta[contador]);
            vector_status_cantidad[contador] = rs.getInt("totalCantidad");
            listaCantidad.add(vector_status_cantidad[contador]);
            contador++;
        }
        con.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error en: " + e);
    }
}
    
    // Metodo para determinar cual es el dia de mayor cantidad de ventas
    public int metodoMayorVenta(ArrayList<Integer> listaCantidad) {
        int mayor = 0;
        for (int i = 0; i < listaCantidad.size(); i++) {
            if (listaCantidad.get(i) > mayor) {
                mayor = listaCantidad.get(i);
            }
        }
        return mayor;
    }
    
    // Metodo para graficar
    @Override
public void paint(Graphics g) {
    // Llamamos al constructor de la clase 
    super.paint(g);
    // Creamos nuestras variables 
    int mayorVenta = metodoMayorVenta(listaCantidad);
    int largo_NuevoIngreso = 0;
    int parametro1 = 133;
    int parametroFecha = 118;
    int parametro3 = 100;

    for (int i = 0; i < listaCantidad.size(); i++) {
        largo_NuevoIngreso = listaCantidad.get(i) * 400 / mayorVenta;
        switch (i) {
            case 0:
                g.setColor(new Color(140, 0, 75));
                break;
            case 1:
                g.setColor(new Color(255, 0, 0));
                break;
            case 2:
                g.setColor(new Color(0, 0, 0));
                break;
            case 3:
                g.setColor(new Color(255, 255, 255));
                break;
            case 4:
                g.setColor(new Color(0, 85, 0));
                break;
            case 5:
                g.setColor(new Color(0, 123, 167));
                break;
            case 6:
                g.setColor(new Color(255, 127, 0));
                break;
            default:
                g.setColor(new Color(17, 251, 216));
                break;
        }
        g.fillRect(100, parametro3, largo_NuevoIngreso, 40);
        g.drawString(listaFechas.get(i), 10, parametroFecha);
        g.drawString("Cantidad: " + listaCantidad.get(i), 10, parametro1);
        parametro1 += 50;
        parametroFecha += 50;
        parametro3 += 50;
    }

    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(30, 30, 30));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Fechas graficadas apartir del historial de ventas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel1)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(625, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HistorialVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistorialVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistorialVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistorialVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HistorialVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables






}
