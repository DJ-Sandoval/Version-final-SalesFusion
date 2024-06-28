
package vista;

import controlador.ProductosController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.Productos;
import modelo.ProductosDao;

/**
 *
 * @author Jose
 */
public class FrmActualizarStock extends javax.swing.JDialog implements KeyListener {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    //  Instancia de clases
    private ProductosDao prDao = new ProductosDao();
    private Productos prod;
    private PanelAdmin views;
    private ProductosController pC;
    /**
     * Creates new form FrmActualizarStock
     */
    public FrmActualizarStock(java.awt.Frame parent, boolean modal, PanelAdmin views) {
        super(parent, modal);
        initComponents();
        this.setTitle("Actualizar Stock de productos");
        this.setLocationRelativeTo(null);
        this.txtIdProductoStock.addKeyListener(this);
        this.txtProductoStock.addKeyListener(this);
        this.txtStockActual.setEnabled(false);
        this.views = views;
      
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
        jLabel2 = new javax.swing.JLabel();
        txtIdProductoStock = new javax.swing.JTextField();
        txtProductoStock = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtStockActual = new javax.swing.JTextField();
        txtStockNuevo = new javax.swing.JTextField();
        btnActualizarStock = new controlador.MyButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(23, 23, 23));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Actualizar Stock De Productos");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("idProducto:");

        txtIdProductoStock.setBackground(new java.awt.Color(47, 47, 47));
        txtIdProductoStock.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtIdProductoStock.setForeground(new java.awt.Color(255, 255, 255));

        txtProductoStock.setBackground(new java.awt.Color(47, 47, 47));
        txtProductoStock.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtProductoStock.setForeground(new java.awt.Color(255, 255, 255));
        txtProductoStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductoStockKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Producto:");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("StockActual:");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Stock Nuevo:");

        txtStockActual.setBackground(new java.awt.Color(47, 47, 47));
        txtStockActual.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtStockActual.setForeground(new java.awt.Color(255, 255, 255));
        txtStockActual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockActualKeyPressed(evt);
            }
        });

        txtStockNuevo.setBackground(new java.awt.Color(47, 47, 47));
        txtStockNuevo.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtStockNuevo.setForeground(new java.awt.Color(255, 255, 255));

        btnActualizarStock.setBackground(new java.awt.Color(51, 102, 255));
        btnActualizarStock.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarStock.setText("Actualizar");
        btnActualizarStock.setBorderColor(new java.awt.Color(51, 102, 255));
        btnActualizarStock.setColor(new java.awt.Color(51, 102, 255));
        btnActualizarStock.setColorClick(new java.awt.Color(21, 21, 21));
        btnActualizarStock.setColorOver(new java.awt.Color(51, 102, 255));
        btnActualizarStock.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(btnActualizarStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtProductoStock)
                            .addComponent(txtStockActual)
                            .addComponent(txtStockNuevo)
                            .addComponent(txtIdProductoStock, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdProductoStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductoStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtStockActual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtStockNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btnActualizarStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
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

    private void txtProductoStockKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoStockKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            this.txtStockActual.requestFocus();
        }
    }//GEN-LAST:event_txtProductoStockKeyPressed

    private void txtStockActualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockActualKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            this.txtStockNuevo.requestFocus();
        }
    }//GEN-LAST:event_txtStockActualKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         PanelAdmin views = new PanelAdmin(); 
         
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
            java.util.logging.Logger.getLogger(FrmActualizarStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmActualizarStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmActualizarStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmActualizarStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmActualizarStock dialog = new FrmActualizarStock(new javax.swing.JFrame(), true, views);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public controlador.MyButton btnActualizarStock;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField txtIdProductoStock;
    public javax.swing.JTextField txtProductoStock;
    public javax.swing.JTextField txtStockActual;
    public javax.swing.JTextField txtStockNuevo;
    // End of variables declaration//GEN-END:variables
    
    // Metodo para limpiar campos
    public void limpiarCampos() {
       txtIdProductoStock.setText("");
       txtProductoStock.setText("");
       txtStockActual.setText("");
       txtStockNuevo.setText("");
    }
    
    // Metodo de validacion
    public boolean validar(String valor) {
        int num;
        try {
            num= Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    // Manejo del evento KeyPressed para los campos de id, producto y cantidad
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == txtIdProductoStock) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // Validar si no se encuentra vacio el campo de buscar id de producto
                if (txtIdProductoStock.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "NO DEJES ESPACIOS EN BLANCO");
                    JOptionPane.showMessageDialog(null, "Ingrese el codigo");
                } else {
                    String cod = txtIdProductoStock.getText();
                    prod = prDao.buscarStock(cod);
                    txtIdProductoStock.setText("" + prod.getId());
                    txtProductoStock.setText(prod.getDescripcion());
                    txtStockActual.setText("" + prod.getCantidad());
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
   }

    
        }
    
    
    
    
