
package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import modelo.Combo;
import modelo.Productos;
import modelo.ProductosDao;
import modelo.Tables;
import vista.PanelAdmin;
import controlador.ProveedorController;
import modelo.Categorias;
import modelo.MedidaDao;
import modelo.Medidas;
import modelo.Proveedor;
import modelo.ProveedorDao;
import vista.frmNuevoProducto;
import vista.frmModificarProducto;
import modelo.MedidaDao;
import modelo.CategoriaDao;
import vista.FrmActualizarStock;

/**
 *
 * @author Jose
 */
public class ProductosController implements ActionListener, MouseListener, KeyListener {
    // Instanciar clases
    private ProveedorDao prDao;
    private Productos prod;
    private ProductosDao proDao;
    private PanelAdmin views;
    private frmNuevoProducto productoNuevo;
    private frmModificarProducto productoModificado;
    private MedidaDao mdDao;
    private CategoriaDao ctDao;
    private FrmActualizarStock stockAc;
    //private ProveedorController provC;
    
    
   
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp;

    // Constructor con isntancias
    public ProductosController(ProveedorDao prDao, Productos prod, ProductosDao proDao, PanelAdmin views, frmNuevoProducto productoNuevo, frmModificarProducto productoModificado, MedidaDao mdDao, CategoriaDao ctDao, FrmActualizarStock stockAc) {
        this.prod = prod;
        this.proDao = proDao;
        this.views = views;
        this.prDao = prDao;
        this.productoNuevo = productoNuevo;
        this.productoModificado = productoModificado;
        this.mdDao = mdDao;
        this.ctDao = ctDao;
        this.stockAc = stockAc;
        this.stockAc.btnActualizarStock.addActionListener(this);
        this.productoNuevo.btnRegistrarPro.addActionListener(this);
        this.productoModificado.btnModificarPro.addActionListener(this);
        this.views.JMenuEliminarProd.addActionListener(this);
        this.views.JMenuReingresarProd.addActionListener(this);
        this.views.TableProductos.addMouseListener(this); 
        this.views.txtCodNC.addKeyListener(this);
        this.views.txtCantNC.addKeyListener(this);
        this.views.txtPagarConNC.addKeyListener(this);
        this.views.btnGenerarCompra.addActionListener(this);
        //proDao.generarReporte(1);
        listarProductos();
        llenarProveedor();
        llenarMedida();
        llenarCategoria();
    }
    
    // Metodos de accion
    
     @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == productoNuevo.btnRegistrarPro) {
            if (productoNuevo.txtCodigoPro.getText().equals("")
                    || productoNuevo.txtDescripcionPro.getText().equals("")
                    || productoNuevo.txtPrecioCompraPro.getText().equals("")
                    || productoNuevo.txtPrecioVentaPro.getText().equals("")
                    || productoNuevo.cbxProveedorPr.getSelectedItem().equals("")
                    || productoNuevo.cbxCategoriaPro.getSelectedItem().equals("")
                    || productoNuevo.cbxMedidaPro.getSelectedItem().equals("")){                
                JOptionPane.showMessageDialog(null, "no dejes ESPACIOS EN BLANCO");
            } else {
                prod.setCodigo(productoNuevo.txtCodigoPro.getText());
                prod.setDescripcion(productoNuevo.txtDescripcionPro.getText());
                prod.setPrecio_compra(Double.parseDouble(productoNuevo.txtPrecioCompraPro.getText()));
                prod.setPrecio_venta(Double.parseDouble(productoNuevo.txtPrecioVentaPro.getText()));
                Combo itemP = (Combo) productoNuevo.cbxProveedorPr.getSelectedItem();
                Combo itemC = (Combo) productoNuevo.cbxCategoriaPro.getSelectedItem();
                Combo itemM = (Combo) productoNuevo.cbxMedidaPro.getSelectedItem();
                prod.setId_proveedor(itemP.getId());
                prod.setId_categoria(itemC.getId());
                prod.setId_medida(itemM.getId());               
                if (proDao.registrar(prod)) {
                    limpiarTable();
                    listarProductos();
                    
                    JOptionPane.showMessageDialog(null, "Producto Registrado con exito");
                    productoNuevo.dispose();
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar el producto");
                }
            }          
        } else if (e.getSource() == productoModificado.btnModificarPro) {
            if (productoModificado.txtCodigoPro.getText().equals("")
                    || productoModificado.txtDescripcionPro.getText().equals("")
                    || productoModificado.txtPrecioCompraPro.getText().equals("")
                    || productoModificado.txtPrecioVentaPro.getText().equals("")
                    || productoModificado.cbxProveedorPr.getSelectedItem().equals("")
                    || productoModificado.cbxCategoriaPro.getSelectedItem().equals("")
                    || productoModificado.cbxMedidaPro.getSelectedItem().equals("")){     
                JOptionPane.showMessageDialog(null, "Selecciona una fila");
            } else {
                prod.setCodigo(productoModificado.txtCodigoPro.getText());
                prod.setDescripcion(productoModificado.txtDescripcionPro.getText());
                prod.setPrecio_compra(Double.parseDouble(productoModificado.txtPrecioCompraPro.getText()));
                prod.setPrecio_venta(Double.parseDouble(productoModificado.txtPrecioVentaPro.getText()));
                Combo itemP = (Combo) productoModificado.cbxProveedorPr.getSelectedItem();
                Combo itemC = (Combo) productoModificado.cbxCategoriaPro.getSelectedItem();
                Combo itemM = (Combo) productoModificado.cbxMedidaPro.getSelectedItem();
                prod.setId_proveedor(itemP.getId());
                prod.setId_categoria(itemC.getId());
                prod.setId_medida(itemM.getId());
                prod.setId(Integer.parseInt(views.txtIdProducto.getText()));
                if (proDao.modificar(prod)) {
                    limpiarTable();
                    listarProductos();
                    
                    JOptionPane.showMessageDialog(null, "Producto Modificado con exito");
                    productoModificado.dispose();
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar el producto");
                }
            }
        } else if (e.getSource() == views.JMenuEliminarProd) {
            if (views.txtIdProducto.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
            } else {
                int id = Integer.parseInt(views.txtIdProducto.getText());
                if (proDao.accion("Inactivo", id)) {
                    limpiarTable();
                    listarProductos();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Producto eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar producto");
                }
            }
        } else if (e.getSource() == views.JMenuReingresarProd) {
            if (views.txtIdProducto.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar");
            } else {
                int id = Integer.parseInt(views.txtIdProducto.getText());
                if (proDao.accion("Activo", id)) {
                    limpiarTable();
                    listarProductos();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Producto reingresado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al reingresar producto");
                }
            
            }
        } else if (e.getSource() == stockAc.btnActualizarStock) {
            if (!stockAc.txtStockNuevo.getText().isEmpty()) {
        // Validamos que el usuario no ingrese otros caracteres no numéricos no deseados
        boolean validacion = validar(stockAc.txtStockNuevo.getText().trim());
        if (validacion) {
            // Validar que mi cantidad sea mayor que 0
            if (Integer.parseInt(stockAc.txtStockNuevo.getText()) > 0) {
                int stockActual = Integer.parseInt(stockAc.txtStockActual.getText().trim());
                int stockNuevo = Integer.parseInt(stockAc.txtStockNuevo.getText().trim());
                int nuevoStockTotal = stockActual + stockNuevo;
                int idProducto = Integer.parseInt(stockAc.txtIdProductoStock.getText().trim());

                if (proDao.actualizarStock(nuevoStockTotal, idProducto)) {
                    DefaultTableModel modelo = (DefaultTableModel) views.TableProductos.getModel();
                    
                    // Actualiza la fila correspondiente
                    int selectedRow = views.TableProductos.getSelectedRow();
                    if (selectedRow != -1) {
                        // Seleccionando la fila correspondiente de la tabla a actualizar
                        modelo.setValueAt(nuevoStockTotal, selectedRow, 4);
                        
                        // Notifica que se ha actualizado una fila específica
                        modelo.fireTableRowsUpdated(selectedRow, selectedRow);
                    }

                    // Limpia y vuelve a listar productos
                    limpiarTable();
                    listarProductos();

                    stockAc.txtIdProductoStock.setText("");
                    stockAc.txtProductoStock.setText("");
                    stockAc.txtStockActual.setText("");
                    stockAc.txtStockNuevo.setText("");

                    JOptionPane.showMessageDialog(null, "Stock Actualizado", "Información", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar stock", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cantidad debe ser mayor que 0", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Entrada inválida", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Campo de stock nuevo vacío", "Error", JOptionPane.ERROR_MESSAGE);
    }
        }
        
        
        else if (e.getSource() == views.btnGenerarCompra) {
            insertarCompra();
            
        } else {
        
        }
        
    }
    
    public void limpiarTableStock() {
    DefaultTableModel modelo = (DefaultTableModel) views.TableProductos.getModel();
    modelo.setRowCount(0);
    }
    
     // Metodo para limpiar tabla
    public void limpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    // Metodo para limpiar tabla
    public void limpiarTableDetalle() {
        for (int i = 0; i < tmp.getRowCount(); i++) {
            tmp.removeRow(i);
            i = i - 1;
        }
    }
    
    public void limpiar() {
        productoNuevo.txtCodigoPro.setText("");
        productoNuevo.txtDescripcionPro.setText("");
        productoNuevo.txtPrecioCompraPro.setText("");
        views.txtIdProducto.setText("");
        productoNuevo.txtPrecioVentaPro.setText("");
    }
    
     public void listarProductos() {
        Tables color = new Tables();
        views.TableProductos.setDefaultRenderer(views.TableProductos.getColumnClass(0), color);
        List<Productos> lista = proDao.ListaProductos(views.txtBuscarProducto.getText());
        modelo = (DefaultTableModel) views.TableProductos.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getCodigo();
            ob[2] = lista.get(i).getDescripcion();
            ob[3] = lista.get(i).getPrecio_venta();
            ob[4] = lista.get(i).getCantidad();
            ob[5] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        // Establecer modelo a tabla
        views.TableProductos.setModel(modelo);
        
        // Establecer color a tabla
        JTableHeader header = views.TableProductos.getTableHeader();
        Font headerFont = new Font("SansSerif", Font.BOLD, 16);
        header.setFont(headerFont);
        header.setOpaque(false);
        header.setBackground(new Color(30,30,30)); //16,49,114 0,81,135
        header.setForeground(new Color(255,255,255));
    }

    // Metodos de Mouse
    @Override
    public void mouseClicked(MouseEvent e) {
        
         if (e.getSource() == views.TableProductos) {
            int fila = views.TableProductos.rowAtPoint(e.getPoint());
            views.txtIdProducto.setText(views.TableProductos.getValueAt(fila, 0).toString());
            prod = proDao.buscarPro(Integer.parseInt(views.txtIdProducto.getText()));
            productoModificado.txtCodigoPro.setText(prod.getCodigo());
            productoModificado.txtDescripcionPro.setText(prod.getDescripcion());
            productoModificado.txtPrecioCompraPro.setText(""+prod.getPrecio_compra());
            productoModificado.txtPrecioVentaPro.setText(""+prod.getPrecio_venta());
            productoModificado.cbxProveedorPr.setSelectedItem(new Combo(prod.getId_proveedor(), prod.getProveedor()));
            productoModificado.cbxMedidaPro.setSelectedItem(new Combo(prod.getId_medida(), prod.getMedida()));
            productoModificado.cbxCategoriaPro.setSelectedItem(new Combo(prod.getId_categoria(), prod.getCat()));  
            productoModificado.setVisible(true);
         }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // Metodos de Key
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == views.txtCodNC) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (views.txtCodNC.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese el codigo");
                } else {
                    String cod = views.txtCodNC.getText();
                    prod = proDao.buscarCodigo(cod);
                     views.txtIdNC.setText(""+prod.getId());
                    views.txtProductoNC.setText(prod.getDescripcion());
                    views.txtPrecioNC.setText(""+prod.getPrecio_compra());
                    views.txtCantNC.requestFocus();
                }
            }
        } else if (e.getSource() == views.txtCantNC) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                int cant = Integer.parseInt(views.txtCantNC.getText());
                String desc = views.txtProductoNC.getText();
                double precio = Double.parseDouble(views.txtPrecioNC.getText());
                // Calcular el total
                int id = Integer.parseInt(views.txtIdNC.getText());
                if (cant > 0) {
                    tmp = (DefaultTableModel) views.TableNuevaCompra.getModel();
                    ArrayList lista = new ArrayList();
                    int item = 1;
                    lista.add(item);
                    lista.add(id);
                    lista.add(desc);
                    lista.add(cant);
                    lista.add(precio);
                    lista.add(cant * precio);
                    Object[] obj = new Object[5];
                    obj[0] = lista.get(1);
                    obj[1] = lista.get(2);
                    obj[2] = lista.get(3);
                    obj[3] = lista.get(4);
                    obj[4] = lista.get(5);
                    tmp.addRow(obj);
                    views.TableNuevaCompra.setModel(tmp);
                    JTableHeader header = views.TableNuevaCompra.getTableHeader();
                    Font headerFont = new Font("SansSerif", Font.BOLD, 16);
                    header.setOpaque(false);
                    header.setBackground(new Color(30, 30, 30)); //16,49,114 0,81,135
                    header.setForeground(new Color(255, 255, 255));
                    header.setFont(headerFont);
                    limpiarCampos();
                    calcularCompra();
                    views.txtCodNC.requestFocus();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtCantNC) {
                int cantidad;
                double precio;
            if (views.txtCantNC.getText().equals("")) {
                    cantidad = 1;
                    precio = Double.parseDouble(views.txtPrecioNC.getText());
                    views.txtTotalNC.setText(""+precio);
            } else {
                cantidad = Integer.parseInt(views.txtCantNC.getText());
                precio = Double.parseDouble(views.txtPrecioNC.getText());
                views.txtTotalNC.setText(""+cantidad * precio);
            }
        } else if (e.getSource() == views.txtPagarConNC) {
            int pagar;
            if (views.txtPagarConNC.getText().equals("")) {
                views.txtVueltoCompra.setText("");
            } else {
                pagar = Integer.parseInt(views.txtPagarConNC.getText());
                double total = Double.parseDouble(views.JLabelTotalCompra.getText());
                views.txtVueltoCompra.setText(""+ (pagar - total));
            }
        }
    }

    private void limpiarCampos() {
        views.txtCodNC.setText("");
        views.txtIdNC.setText("");
        views.txtProductoNC.setText("");
        views.txtCantNC.setText("");
        views.txtPrecioNC.setText("");
        views.txtTotalNC.setText("");
    }
    
    private void calcularCompra() {
        double total = 0.00;
        int numFila = views.TableNuevaCompra.getRowCount();
        for (int i = 0; i < numFila; i++) {
            total = total + Double.parseDouble(String.valueOf(views.TableNuevaCompra.getValueAt(i, 4))); // String.valueof(views.tabla de comora)
        }
        views.JLabelTotalCompra.setText(""+ total);
    }
    
    // Llenar los comboBox
    public void llenarProveedor() {
        List<Proveedor> lista = prDao.ListaProveedor(views.txtBuscarProv.getText());
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            //productoNuevo.cbxProveedorPr.addItem(new Combo(id, nombre));
            productoModificado.cbxProveedorPr.addItem(new Combo(id, nombre));           
        }
    } 
        // Metodo para llenar comboBox Proveedor
    private void llenarMedida() {  
         List<Medidas> lista = mdDao.ListaMedida(views.txtBuscarMed.getText());   
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            productoNuevo.cbxMedidaPro.addItem(new Combo(id, nombre));
            productoModificado.cbxMedidaPro.addItem(new Combo(id, nombre));
        }
    }
    
    // Metodo para llenar comboBox Categorias
    private void llenarCategoria() {
         List<Categorias> lista = ctDao.ListaCategoria(views.txtBuscarCategoria.getText());   
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            productoNuevo.cbxCategoriaPro.addItem(new Combo(id, nombre));
            productoModificado.cbxCategoriaPro.addItem(new Combo(id, nombre));
        }
    }
    
    private void insertarCompra() {
        Combo id_p = (Combo) views.cbxProvNC.getSelectedItem();
        int id_proveedor = id_p.getId();
        String total = views.JLabelTotalCompra.getText();
        if (proDao.registrarCompra(id_proveedor, total)) {
           int id_compra = proDao.id_compra();
            for (int i = 0; i < views.TableNuevaCompra.getRowCount(); i++) {
                double precio = Double.parseDouble(views.TableNuevaCompra.getValueAt(i, 3).toString());
                int cantidad = Integer.parseInt(views.TableNuevaCompra.getValueAt(i, 2).toString());
                int id = Integer.parseInt(views.TableNuevaCompra.getValueAt(i, 0).toString());
                double sub_total = precio * cantidad;
                proDao.registrarCompraDetalle(id_compra, id, precio, cantidad, sub_total);
                prod = proDao.buscarId(id);
                prod.getCantidad();
                int stockActual = prod.getCantidad() + cantidad;
                proDao.ActualizarStockCompra(stockActual, id);
            }
            limpiarTableDetalle();
            JOptionPane.showMessageDialog(null, "Compra generada");
            //proDao.generarReporte(2);
            proDao.generarReporte(id_compra);
//                
        }
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
    
    // Listar historial Compras
    public void listarCompras() {
        //views.TableProductos.setDefaultRenderer(views.TableProductos.getColumnClass(0), color);
        List<Productos> lista = proDao.ListaProductos(views.txtBuscarProducto.getText());
        modelo = (DefaultTableModel) views.TableProductos.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getCodigo();
            ob[2] = lista.get(i).getDescripcion();
            ob[3] = lista.get(i).getPrecio_venta();
            ob[4] = lista.get(i).getCantidad();
            ob[5] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        // Establecer modelo a tabla
        views.TableProductos.setModel(modelo);
        
        // Establecer color a tabla
        JTableHeader header = views.TableProductos.getTableHeader();
        Font headerFont = new Font("SansSerif", Font.BOLD, 16);
        header.setFont(headerFont);
        header.setOpaque(false);
        header.setBackground(new Color(30,30,30)); //16,49,114 0,81,135
        header.setForeground(new Color(255,255,255));
    }

    
    
        // Fuera del for
    
        
    
    

    

}
