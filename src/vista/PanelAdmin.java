
package vista;

import java.sql.SQLException;
import chart.ModelChart;
import chart.ModelData;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import controlador.CategoriaController;
import controlador.ClientesController;
import controlador.MedidaController;
import controlador.Noticias;
import controlador.ProductosController;
import controlador.ProveedorController;
import controlador.UsuariosControllers;
import controlador.textPrompt;
import event.MenuEvent;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import modelo.CategoriaDao;
import modelo.Categorias;
import modelo.Clientes;
import modelo.ClientesDao;
import modelo.MedidaDao;
import modelo.Medidas;
import modelo.Productos;
import modelo.ProductosDao;
import modelo.Proveedor;
import modelo.ProveedorDao;
import modelo.UsuarioDao;
import modelo.Usuarios;
import chart.PieChartExample;
import controlador.ConfiguracionController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import modelo.Conexion;
import modelo.Configuracion;
import modelo.compras;
import modelo.comprasDao;
import seguridad.ComprasController;
import vista.FrmNuevaMedida;
import vista.frmNuevoCliente;
import vista.frmModificarCliente;
import vista.frmNuevoProducto;
import controlador.ContenedoresController;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/**
 *
 * @author Jose
 */
public class PanelAdmin extends javax.swing.JFrame {

    Usuarios us = new Usuarios();
    UsuarioDao usDao = new UsuarioDao();

    Proveedor prov = new Proveedor();
    ProveedorDao prDao = new ProveedorDao();

    Clientes cli = new Clientes();
    ClientesDao clDao = new ClientesDao();

    Categorias cat = new Categorias();
    CategoriaDao ctDao = new CategoriaDao();

    Medidas med = new Medidas();
    MedidaDao mdDao = new MedidaDao();

    Productos prod = new Productos();
    ProductosDao proDao = new ProductosDao();
    
    Configuracion cof = new Configuracion();
    
    compras com = new compras();
    comprasDao comDao = new comprasDao();
    
    
    // JDialog
    FrmReportes report = new FrmReportes(this, true);
    FrmActualizarStock stock = new FrmActualizarStock(this, true, this);
    FrmNuevaMedida medida2 = new FrmNuevaMedida(this, true);
    FrmMedida medida = new FrmMedida();
    FrmModificarMedida modificarMedida = new FrmModificarMedida(this, true);
    FrmNuevaCategoria categoria = new FrmNuevaCategoria(this, true);
    FrmModificarCategoria modificarCategoria = new FrmModificarCategoria(this, true);
    FrmNuevoUsuario usuario = new FrmNuevoUsuario(this, true);
    FrmModificarUsuario usuarioModificado = new FrmModificarUsuario(this, true);
    FrmNuevoProveedor proveedor = new FrmNuevoProveedor(this, true);
    frmModificarProveedor proveedorModificado = new frmModificarProveedor(this,true);
    frmNuevoCliente clientes = new frmNuevoCliente(this, true);
    frmModificarCliente clienteModificado = new frmModificarCliente(this, true);
    frmNuevoProducto productoNuevo = new frmNuevoProducto(this, true);
    frmModificarProducto productoModificado = new frmModificarProducto(this, true);
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Creates new form PanelAdmin
     */
    public PanelAdmin() {
        initComponents();
        chart.setTitle("Productos con mas compras");
        chart.addLegend("Producto", Color.decode("#7b4397"), Color.decode("#dc2430"));
        chart.addLegend("Cantidad", Color.decode("#e65c00"), Color.decode("#F9D423"));      
        setData();
        // Obtener los datos y crear la gráfica
        // Obtener los datos y crear la gráfica
        // Obtener los datos y crear la gráfica
        ProductosDao productosDao = new ProductosDao();
        // Obtener los datos y crear la gráfica

        //textPrompt placeHolder1 = new textPrompt("Search...", txtBuscarProducto);
        textPrompt placeHolder2 = new textPrompt("Search...", txtBuscarMed);
        textPrompt placeHolder3 = new textPrompt("Search...",txtBuscarUsers);
        textPrompt placeHolder4 = new textPrompt("Search...", txtBuscarCategoria);
        textPrompt placeHolder5 = new textPrompt("Search...", txtBuscarProv);
        textPrompt placeHolder6 = new textPrompt("Search...", txtBuscarCli);
        textPrompt placeHolder7 = new textPrompt("Search...", txtBuscarCompra);
        textPrompt placeHolder8 = new textPrompt("Search...", txtBuscarProducto);
        
        
        this.setLocationRelativeTo(null);
        this.setSize(1319, 682);
        this.setTitle("SalesFusionv2 PanelAdmin");
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        iniciar();
        // Instancia de controlador
        UsuariosControllers users = new UsuariosControllers(us, usDao, this, usuario, usuarioModificado);
        ClientesController cliente = new ClientesController(cli, clDao, this, clientes, clienteModificado);
        ProveedorController proveedores = new ProveedorController(prov, prDao, this, proveedor, proveedorModificado, productoNuevo);
        CategoriaController categorias = new CategoriaController(cat, ctDao, this, categoria, modificarCategoria, productoNuevo);
        MedidaController medidas = new MedidaController(med, mdDao, this, medida2,  medida, modificarMedida, productoNuevo);
            ProductosController productos = new ProductosController(prDao, prod, proDao, this, productoNuevo, productoModificado, mdDao, ctDao, stock);
        ConfiguracionController config = new ConfiguracionController(cof, usDao, this);
        ComprasController compras = new ComprasController(com, this, comDao);
        ContenedoresController contenedor = new ContenedoresController(this);
        
        
        // Menu
        MenuEvent event = new MenuEvent() {
            @Override
            public void menuSelected(int index) {
                System.out.println("Selected : " + index);
                switch (index) {
                    case 0: // Inicio
                        ProductosDao productosDao = new ProductosDao();
                        PanelAdmin.this.jTabbedPane1.setSelectedIndex(12);
                        Map<String, Integer> data = productosDao.obtenerCantidadPorCategoria();
                        //new PieChartExample(data, panelGrafica);
                        setVisible(true);
                        break;
                    case 1: // Nueva Venta
                        PanelAdmin.this.jTabbedPane1.setSelectedIndex(6);
                        break;
                    case 2: // Nueva Compra
                        PanelAdmin.this.jTabbedPane1.setSelectedIndex(11);
                        break;
                    case 3: // Productos
                        PanelAdmin.this.jTabbedPane1.setSelectedIndex(0);
                        break;
                    case 4: // Clientes
                        PanelAdmin.this.jTabbedPane1.setSelectedIndex(1);
                        break;
                    case 5: // Usuarios
                        PanelAdmin.this.jTabbedPane1.setSelectedIndex(3);
                        break;
                    case 6: // Proveedores
                        PanelAdmin.this.jTabbedPane1.setSelectedIndex(2);
                        break;
                    case 7: // Medidas
                        PanelAdmin.this.jTabbedPane1.setSelectedIndex(5);
                        break;
                    case 8: // Categorias
                        PanelAdmin.this.jTabbedPane1.setSelectedIndex(4);
                        break;
                    case 9: // Configuraciones
                        PanelAdmin.this.jTabbedPane1.setSelectedIndex(9);
                        break;
                    case 10: // Reportes
                         report.setVisible(true);
                        break;
                    case 11: // stock
                            stock.setVisible(true);
                            break;
                    case 12: // HistorialVentas
                        JOptionPane.showMessageDialog(null,"Tranquilo Viejo aun no es tiempo","Informacion", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 13: // Salir
                        JOptionPane.showMessageDialog(null,"Tranquilo Viejo aun no es tiempo","Informacion", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 14: // Salir
                        PanelAdmin.this.jTabbedPane1.setSelectedIndex(8);
                        break;
                    case 15: // Inventario productos 
                         desertar();
                         break;
                }
            }
        };
        menu.initMenu(event);

        // Imagen
        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/posIcono.png"));
        this.setIconImage(icono);

        // Timer para la hora
        // Crear un Timer para actualizar la hora cada segundo
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblHora.setText(obtenerHoraActual());
            }
        });

// Iniciar el Timer
        timer.start();

        //  Cambiar de color el selector de campos
        //Cliente
        //txtNombreCliente.setSelectionColor(new Color(92, 56, 255));
        // Producto
        // Usuario
        //Medida
        //Categoria
        //Proveedor
        //

    }
    
   public void setData() {
    try {
        List<ModelData> lists = new ArrayList<>();
        con = cn.getConexion();
        String sql = "SELECT p.descripcion AS producto, COUNT(c.id) AS cantidad "
                   + "FROM productos p "
                   + "INNER JOIN compras c ON p.id = c.id_proveedor "
                   + "GROUP BY p.descripcion "
                   + "ORDER BY cantidad DESC";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            String producto = rs.getString("producto");
            int cantidad = rs.getInt("cantidad");
            lists.add(new ModelData(producto, cantidad));
        }
        rs.close();
        ps.close();
        for (int i = lists.size() - 1; i >= 0; i--) {
            ModelData d = lists.get(i);
            chart.addData(new ModelChart(d.getCategoria(), new double[]{d.getCantidad()}));
        }
        chart.start();
    } catch (Exception e) {
        e.printStackTrace();
    
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

        JPopupUsuarios = new javax.swing.JPopupMenu();
        JMenuEliminarUsers = new javax.swing.JMenuItem();
        JMenuReingresarUsers = new javax.swing.JMenuItem();
        JPopupClientes = new javax.swing.JPopupMenu();
        JMenuEliminarCli = new javax.swing.JMenuItem();
        JMenuReingresarCli = new javax.swing.JMenuItem();
        JPopupProveedor = new javax.swing.JPopupMenu();
        JMenuEliminarProv = new javax.swing.JMenuItem();
        JMenuReingresarProv = new javax.swing.JMenuItem();
        JPopupCategorias = new javax.swing.JPopupMenu();
        JMenuEliminarCat = new javax.swing.JMenuItem();
        JMenuReingresarCat = new javax.swing.JMenuItem();
        JPopupMedidas = new javax.swing.JPopupMenu();
        JMenuEliminarMed = new javax.swing.JMenuItem();
        JMenuReingresarMed = new javax.swing.JMenuItem();
        JPopupProductos = new javax.swing.JPopupMenu();
        JMenuEliminarProd = new javax.swing.JMenuItem();
        JMenuReingresarProd = new javax.swing.JMenuItem();
        menu = new controlador.Menu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        PaginadorPro = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        TableProductos = new javax.swing.JTable();
        btnRegistrarPro = new controlador.MyButton();
        txtIdProducto = new controlador.MyTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableClientes = new javax.swing.JTable();
        PaginadorCli = new javax.swing.JPanel();
        btnRegistrarCli = new controlador.MyButton();
        txtBuscarCli = new controlador.MyTextField();
        txtIdCli = new controlador.MyTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        PaginadoProv = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableProveedor = new javax.swing.JTable();
        txtBuscarProv = new controlador.MyTextField();
        btnRegistrarProv = new controlador.MyButton();
        txtIdProv = new controlador.MyTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        TableUsers = new javax.swing.JTable();
        txtBuscarUsers = new controlador.MyTextField();
        btnRegistrarUser = new controlador.MyButton();
        txtIdUser = new controlador.MyTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        TableCat = new javax.swing.JTable();
        btnModificarCat = new controlador.MyButton();
        btnNuevoCat = new controlador.MyButton();
        txtBuscarCategoria = new controlador.MyTextField();
        jLabel18 = new javax.swing.JLabel();
        txtIdCat = new controlador.MyTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        TableMed = new javax.swing.JTable();
        btnRegistrarMed = new controlador.MyButton();
        btnModificarMed = new controlador.MyButton();
        txtBuscarMed = new controlador.MyTextField();
        jLabel72 = new javax.swing.JLabel();
        txtIdMed = new controlador.MyTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableNuevaVenta = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtCodNV = new javax.swing.JTextField();
        txtProductoNV = new javax.swing.JTextField();
        txtCantNV = new javax.swing.JTextField();
        txtPrecioNV = new javax.swing.JTextField();
        txtTotalNV = new javax.swing.JTextField();
        txtStockNV = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        btnGenerarVenta = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        cbxClientes = new javax.swing.JComboBox<>();
        txtPagarCon = new javax.swing.JTextField();
        txtVuelto = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        JLabelTotalPagar = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        TableVentas = new javax.swing.JTable();
        PaginadoVentas = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableCompras = new Table.TableDark();
        txtBuscarCompra = new controlador.MyTextField();
        btnHistorialCompras = new controlador.MyButton();
        btnEliminarCompra = new controlador.MyButton();
        txtIdCompra = new controlador.MyTextField();
        jPanel11 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtNombreEmpresa = new javax.swing.JTextField();
        txtTelefonoEmpresa = new javax.swing.JTextField();
        btnModificarEmpresa = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtDireccionEmpresa = new javax.swing.JTextArea();
        jLabel47 = new javax.swing.JLabel();
        txtRfcEmpresa = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        txtMensaje = new javax.swing.JTextArea();
        txtIdEmpresa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCodigoPostalEmpresa = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        TableNuevaCompra = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtCodNC = new javax.swing.JTextField();
        txtProductoNC = new javax.swing.JTextField();
        txtCantNC = new javax.swing.JTextField();
        txtPrecioNC = new javax.swing.JTextField();
        txtTotalNC = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        btnGenerarCompra = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        cbxProvNC = new javax.swing.JComboBox<>();
        txtPagarConNC = new javax.swing.JTextField();
        txtVueltoCompra = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        JLabelTotalCompra = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        txtIdNC = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        contenedorProductos = new controlador.PanelRound();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        lblConteoProductos = new javax.swing.JLabel();
        contenedorCompras = new controlador.PanelRound();
        jLabel15 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        lblConteoCompras = new javax.swing.JLabel();
        contenedroClientes = new controlador.PanelRound();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        lblConteoClientes = new javax.swing.JLabel();
        ContenedorVentas = new controlador.PanelRound();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        lblConteoVentas = new javax.swing.JLabel();
        panelShadow1 = new controlador.PanelShadow();
        chart = new chart.CurveLineChart();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableBajoStock = new Table.TableDark();
        jLabel16 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        pieChart1 = new chart.PieChart();
        jPanel23 = new javax.swing.JPanel();
        btnUser = new controlador.MyButton();
        btnScroll = new controlador.MyButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblGraficas = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        lblAcercaDe = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtBuscarProducto = new controlador.TextField();

        JMenuEliminarUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        JMenuEliminarUsers.setText("Eliminar");
        JPopupUsuarios.add(JMenuEliminarUsers);

        JMenuReingresarUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-informacion.png"))); // NOI18N
        JMenuReingresarUsers.setText("Reingresar");
        JPopupUsuarios.add(JMenuReingresarUsers);

        JMenuEliminarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        JMenuEliminarCli.setText("Eliminar");
        JPopupClientes.add(JMenuEliminarCli);

        JMenuReingresarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-informacion.png"))); // NOI18N
        JMenuReingresarCli.setText("Reingresar");
        JPopupClientes.add(JMenuReingresarCli);

        JMenuEliminarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        JMenuEliminarProv.setText("Eliminar");
        JPopupProveedor.add(JMenuEliminarProv);

        JMenuReingresarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-informacion.png"))); // NOI18N
        JMenuReingresarProv.setText("Reingresar");
        JPopupProveedor.add(JMenuReingresarProv);

        JMenuEliminarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        JMenuEliminarCat.setText("Eliminar");
        JPopupCategorias.add(JMenuEliminarCat);

        JMenuReingresarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-informacion.png"))); // NOI18N
        JMenuReingresarCat.setText("reingresar");
        JPopupCategorias.add(JMenuReingresarCat);

        JMenuEliminarMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        JMenuEliminarMed.setText("Eliminar");
        JPopupMedidas.add(JMenuEliminarMed);

        JMenuReingresarMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-informacion.png"))); // NOI18N
        JMenuReingresarMed.setText("Reingresar");
        JPopupMedidas.add(JMenuReingresarMed);

        JMenuEliminarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        JMenuEliminarProd.setText("Eliminar");
        JPopupProductos.add(JMenuEliminarProd);

        JMenuReingresarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-informacion.png"))); // NOI18N
        JMenuReingresarProd.setText("Reingresar");
        JPopupProductos.add(JMenuReingresarProd);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(32, 32, 32));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PaginadorPro.setBackground(new java.awt.Color(23, 23, 23));

        javax.swing.GroupLayout PaginadorProLayout = new javax.swing.GroupLayout(PaginadorPro);
        PaginadorPro.setLayout(PaginadorProLayout);
        PaginadorProLayout.setHorizontalGroup(
            PaginadorProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        PaginadorProLayout.setVerticalGroup(
            PaginadorProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(PaginadorPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 1040, 50));

        TableProductos.setBackground(new java.awt.Color(30, 30, 30));
        TableProductos.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        TableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Codigo", "Descripcion", "Precio", "Stock", "Estado"
            }
        ));
        TableProductos.setComponentPopupMenu(JPopupProductos);
        jScrollPane15.setViewportView(TableProductos);

        jPanel2.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 1040, 430));

        btnRegistrarPro.setBackground(new java.awt.Color(51, 102, 255));
        btnRegistrarPro.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarPro.setText("Nuevo");
        btnRegistrarPro.setBorderColor(new java.awt.Color(21, 21, 21));
        btnRegistrarPro.setColor(new java.awt.Color(51, 102, 255));
        btnRegistrarPro.setColorClick(new java.awt.Color(23, 23, 23));
        btnRegistrarPro.setColorOver(new java.awt.Color(51, 102, 255));
        btnRegistrarPro.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnRegistrarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarProActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegistrarPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, 80, 30));

        txtIdProducto.setBackground(new java.awt.Color(47, 47, 47));
        txtIdProducto.setForeground(new java.awt.Color(255, 255, 255));
        txtIdProducto.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtIdProducto.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/img/id-insignia.png"))); // NOI18N
        jPanel2.add(txtIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 100, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Producto");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jTabbedPane1.addTab("tab1", jPanel2);

        jPanel3.setBackground(new java.awt.Color(32, 32, 32));
        jPanel3.setPreferredSize(new java.awt.Dimension(980, 530));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableClientes.setBackground(new java.awt.Color(30, 30, 30));
        TableClientes.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        TableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Telefono", "Direccion", "Combinacion", "Yogurt", "Helado", "Cobertura", "Estado"
            }
        ));
        TableClientes.setComponentPopupMenu(JPopupClientes);
        jScrollPane2.setViewportView(TableClientes);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 1050, 430));

        PaginadorCli.setBackground(new java.awt.Color(23, 23, 23));

        javax.swing.GroupLayout PaginadorCliLayout = new javax.swing.GroupLayout(PaginadorCli);
        PaginadorCli.setLayout(PaginadorCliLayout);
        PaginadorCliLayout.setHorizontalGroup(
            PaginadorCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1050, Short.MAX_VALUE)
        );
        PaginadorCliLayout.setVerticalGroup(
            PaginadorCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel3.add(PaginadorCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 1050, 50));

        btnRegistrarCli.setBackground(new java.awt.Color(51, 102, 255));
        btnRegistrarCli.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarCli.setText("Nuevo");
        btnRegistrarCli.setBorderColor(new java.awt.Color(23, 23, 23));
        btnRegistrarCli.setColor(new java.awt.Color(51, 102, 255));
        btnRegistrarCli.setColorClick(new java.awt.Color(21, 21, 21));
        btnRegistrarCli.setColorOver(new java.awt.Color(51, 102, 255));
        btnRegistrarCli.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRegistrarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarCliActionPerformed(evt);
            }
        });
        jPanel3.add(btnRegistrarCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 40, -1, 30));

        txtBuscarCli.setBackground(new java.awt.Color(47, 47, 47));
        txtBuscarCli.setForeground(new java.awt.Color(255, 255, 255));
        txtBuscarCli.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtBuscarCli.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa_1.png"))); // NOI18N
        jPanel3.add(txtBuscarCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 210, 30));

        txtIdCli.setBackground(new java.awt.Color(47, 47, 47));
        txtIdCli.setForeground(new java.awt.Color(255, 255, 255));
        txtIdCli.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtIdCli.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/img/id-insignia.png"))); // NOI18N
        jPanel3.add(txtIdCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 100, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Cliente");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jTabbedPane1.addTab("tab2", jPanel3);

        jPanel4.setBackground(new java.awt.Color(32, 32, 32));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PaginadoProv.setBackground(new java.awt.Color(23, 23, 23));

        javax.swing.GroupLayout PaginadoProvLayout = new javax.swing.GroupLayout(PaginadoProv);
        PaginadoProv.setLayout(PaginadoProvLayout);
        PaginadoProvLayout.setHorizontalGroup(
            PaginadoProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1030, Short.MAX_VALUE)
        );
        PaginadoProvLayout.setVerticalGroup(
            PaginadoProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel4.add(PaginadoProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 1030, 60));

        TableProveedor.setBackground(new java.awt.Color(30, 30, 30));
        TableProveedor.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        TableProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "rfc", "Proveedor", "telefono", "Direccion", "Estado"
            }
        ));
        TableProveedor.setComponentPopupMenu(JPopupProveedor);
        jScrollPane5.setViewportView(TableProveedor);

        jPanel4.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1030, 410));

        txtBuscarProv.setBackground(new java.awt.Color(47, 47, 47));
        txtBuscarProv.setForeground(new java.awt.Color(255, 255, 255));
        txtBuscarProv.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtBuscarProv.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa_1.png"))); // NOI18N
        jPanel4.add(txtBuscarProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 200, 30));

        btnRegistrarProv.setBackground(new java.awt.Color(51, 102, 255));
        btnRegistrarProv.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarProv.setText("Registrar");
        btnRegistrarProv.setBorderColor(new java.awt.Color(21, 21, 21));
        btnRegistrarProv.setColor(new java.awt.Color(51, 102, 255));
        btnRegistrarProv.setColorClick(new java.awt.Color(23, 23, 23));
        btnRegistrarProv.setColorOver(new java.awt.Color(51, 102, 255));
        btnRegistrarProv.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRegistrarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarProvActionPerformed(evt);
            }
        });
        jPanel4.add(btnRegistrarProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 40, -1, 30));

        txtIdProv.setBackground(new java.awt.Color(47, 47, 47));
        txtIdProv.setForeground(new java.awt.Color(255, 255, 255));
        txtIdProv.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/img/id-insignia.png"))); // NOI18N
        jPanel4.add(txtIdProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 120, 30));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Proveedor");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jTabbedPane1.addTab("tab3", jPanel4);

        jPanel5.setBackground(new java.awt.Color(32, 32, 32));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableUsers.setBackground(new java.awt.Color(30, 30, 30));
        TableUsers.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        TableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Usuario", "Nombre", "Caja", "Rol", "Estado"
            }
        ));
        TableUsers.setComponentPopupMenu(JPopupUsuarios);
        jScrollPane8.setViewportView(TableUsers);

        jPanel5.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 1030, 490));

        txtBuscarUsers.setBackground(new java.awt.Color(47, 47, 47));
        txtBuscarUsers.setForeground(new java.awt.Color(255, 255, 255));
        txtBuscarUsers.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtBuscarUsers.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa_1.png"))); // NOI18N
        jPanel5.add(txtBuscarUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 150, 30));

        btnRegistrarUser.setBackground(new java.awt.Color(51, 102, 255));
        btnRegistrarUser.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarUser.setText("Registrar");
        btnRegistrarUser.setBorderColor(new java.awt.Color(23, 23, 23));
        btnRegistrarUser.setColor(new java.awt.Color(51, 102, 255));
        btnRegistrarUser.setColorClick(new java.awt.Color(21, 21, 21));
        btnRegistrarUser.setColorOver(new java.awt.Color(51, 102, 255));
        btnRegistrarUser.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRegistrarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarUserActionPerformed(evt);
            }
        });
        jPanel5.add(btnRegistrarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 30, -1, 30));

        txtIdUser.setBackground(new java.awt.Color(47, 47, 47));
        txtIdUser.setForeground(new java.awt.Color(255, 255, 255));
        txtIdUser.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/img/id-insignia.png"))); // NOI18N
        jPanel5.add(txtIdUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 110, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Usuarios");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jTabbedPane1.addTab("tab4", jPanel5);

        jPanel6.setBackground(new java.awt.Color(32, 32, 32));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableCat.setBackground(new java.awt.Color(30, 30, 30));
        TableCat.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        TableCat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Estado"
            }
        ));
        TableCat.setComponentPopupMenu(JPopupCategorias);
        jScrollPane9.setViewportView(TableCat);

        jPanel6.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 1010, 470));

        btnModificarCat.setBackground(new java.awt.Color(51, 102, 255));
        btnModificarCat.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarCat.setText("Modificar");
        btnModificarCat.setBorderColor(new java.awt.Color(21, 21, 21));
        btnModificarCat.setColor(new java.awt.Color(51, 102, 255));
        btnModificarCat.setColorClick(new java.awt.Color(21, 21, 21));
        btnModificarCat.setColorOver(new java.awt.Color(21, 102, 255));
        btnModificarCat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnModificarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCatActionPerformed(evt);
            }
        });
        jPanel6.add(btnModificarCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 30, -1, 30));

        btnNuevoCat.setBackground(new java.awt.Color(51, 102, 255));
        btnNuevoCat.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevoCat.setText("Registrar");
        btnNuevoCat.setBorderColor(new java.awt.Color(21, 21, 21));
        btnNuevoCat.setColor(new java.awt.Color(51, 102, 255));
        btnNuevoCat.setColorClick(new java.awt.Color(21, 21, 21));
        btnNuevoCat.setColorOver(new java.awt.Color(51, 102, 255));
        btnNuevoCat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnNuevoCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCatActionPerformed(evt);
            }
        });
        jPanel6.add(btnNuevoCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 30, -1, 30));

        txtBuscarCategoria.setBackground(new java.awt.Color(47, 47, 47));
        txtBuscarCategoria.setForeground(new java.awt.Color(255, 255, 255));
        txtBuscarCategoria.setCaretColor(new java.awt.Color(255, 255, 255));
        txtBuscarCategoria.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtBuscarCategoria.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtBuscarCategoria.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa_1.png"))); // NOI18N
        jPanel6.add(txtBuscarCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 37, 200, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Categoria");
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        txtIdCat.setBackground(new java.awt.Color(47, 47, 47));
        txtIdCat.setForeground(new java.awt.Color(255, 255, 255));
        txtIdCat.setCaretColor(new java.awt.Color(255, 255, 255));
        txtIdCat.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtIdCat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtIdCat.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/img/id-insignia.png"))); // NOI18N
        jPanel6.add(txtIdCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 37, 100, 30));

        jTabbedPane1.addTab("tab5", jPanel6);

        jPanel7.setBackground(new java.awt.Color(32, 32, 32));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableMed.setBackground(new java.awt.Color(23, 23, 23));
        TableMed.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        TableMed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Nombre Corto", "Estado"
            }
        ));
        TableMed.setComponentPopupMenu(JPopupMedidas);
        jScrollPane10.setViewportView(TableMed);

        jPanel7.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1040, 480));

        btnRegistrarMed.setBackground(new java.awt.Color(51, 102, 255));
        btnRegistrarMed.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarMed.setText("Registrar");
        btnRegistrarMed.setBorderColor(new java.awt.Color(21, 21, 21));
        btnRegistrarMed.setColor(new java.awt.Color(51, 102, 255));
        btnRegistrarMed.setColorClick(new java.awt.Color(21, 21, 21));
        btnRegistrarMed.setColorOver(new java.awt.Color(51, 102, 255));
        btnRegistrarMed.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRegistrarMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarMedActionPerformed(evt);
            }
        });
        jPanel7.add(btnRegistrarMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 30, -1, 30));

        btnModificarMed.setBackground(new java.awt.Color(51, 102, 255));
        btnModificarMed.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarMed.setText("Modificar");
        btnModificarMed.setBorderColor(new java.awt.Color(21, 21, 21));
        btnModificarMed.setColor(new java.awt.Color(51, 102, 255));
        btnModificarMed.setColorClick(new java.awt.Color(21, 21, 21));
        btnModificarMed.setColorOver(new java.awt.Color(51, 102, 255));
        btnModificarMed.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnModificarMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarMedActionPerformed(evt);
            }
        });
        jPanel7.add(btnModificarMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 30, -1, 30));

        txtBuscarMed.setBackground(new java.awt.Color(47, 47, 47));
        txtBuscarMed.setForeground(new java.awt.Color(255, 255, 255));
        txtBuscarMed.setCaretColor(new java.awt.Color(255, 255, 255));
        txtBuscarMed.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtBuscarMed.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBuscarMed.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa_1.png"))); // NOI18N
        jPanel7.add(txtBuscarMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 37, 210, 30));

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("Medida");
        jPanel7.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        txtIdMed.setBackground(new java.awt.Color(47, 47, 47));
        txtIdMed.setForeground(new java.awt.Color(255, 255, 255));
        txtIdMed.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtIdMed.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/img/id-insignia.png"))); // NOI18N
        jPanel7.add(txtIdMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 36, 120, 30));

        jTabbedPane1.addTab("tab6", jPanel7);

        jPanel8.setBackground(new java.awt.Color(47, 113, 214));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableNuevaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Descripcion", "Cantidad", "Precio", "Total"
            }
        ));
        jScrollPane6.setViewportView(TableNuevaVenta);

        jPanel8.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 137, 1010, 350));

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Codigo");
        jPanel8.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Producto");
        jPanel8.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, -1));

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Cant");
        jPanel8.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, -1, -1));

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Precio");
        jPanel8.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, -1, -1));

        txtCodNV.setBackground(new java.awt.Color(255, 255, 255));
        txtCodNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtCodNV.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.add(txtCodNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 160, 30));

        txtProductoNV.setBackground(new java.awt.Color(255, 255, 255));
        txtProductoNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtProductoNV.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.add(txtProductoNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 200, 30));

        txtCantNV.setBackground(new java.awt.Color(255, 255, 255));
        txtCantNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtCantNV.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.add(txtCantNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 60, 30));

        txtPrecioNV.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecioNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtPrecioNV.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.add(txtPrecioNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, -1, 30));

        txtTotalNV.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTotalNV.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.add(txtTotalNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, -1, 30));

        txtStockNV.setBackground(new java.awt.Color(255, 255, 255));
        txtStockNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtStockNV.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.add(txtStockNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 70, 30));

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Total");
        jPanel8.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, -1, -1));

        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Stock");
        jPanel8.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, -1, -1));

        btnGenerarVenta.setBackground(new java.awt.Color(255, 255, 255));
        btnGenerarVenta.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnGenerarVenta.setForeground(new java.awt.Color(0, 0, 0));
        btnGenerarVenta.setText("Generar");
        jPanel8.add(btnGenerarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 90, 90, 30));

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Cliente");
        jPanel8.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, -1, -1));

        cbxClientes.setBackground(new java.awt.Color(255, 255, 255));
        cbxClientes.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxClientes.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.add(cbxClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 500, 210, 30));

        txtPagarCon.setBackground(new java.awt.Color(255, 255, 255));
        txtPagarCon.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtPagarCon.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.add(txtPagarCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 500, 150, 30));

        txtVuelto.setBackground(new java.awt.Color(255, 255, 255));
        txtVuelto.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtVuelto.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.add(txtVuelto, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 500, 150, 30));

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Total Pagar");
        jPanel8.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 500, -1, -1));

        JLabelTotalPagar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        JLabelTotalPagar.setForeground(new java.awt.Color(0, 0, 0));
        JLabelTotalPagar.setText("-------");
        jPanel8.add(JLabelTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 500, -1, -1));

        jLabel33.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Pagar con");
        jPanel8.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 500, -1, -1));

        jLabel34.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Vuelto");
        jPanel8.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 500, -1, -1));

        jTabbedPane1.addTab("tab7", jPanel8);

        jPanel9.setBackground(new java.awt.Color(204, 153, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Cliente", "Total", "Fecha"
            }
        ));
        jScrollPane11.setViewportView(TableVentas);

        jPanel9.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 920, 420));

        javax.swing.GroupLayout PaginadoVentasLayout = new javax.swing.GroupLayout(PaginadoVentas);
        PaginadoVentas.setLayout(PaginadoVentasLayout);
        PaginadoVentasLayout.setHorizontalGroup(
            PaginadoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1020, Short.MAX_VALUE)
        );
        PaginadoVentasLayout.setVerticalGroup(
            PaginadoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel9.add(PaginadoVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 1020, 60));

        jTabbedPane1.addTab("tab8", jPanel9);

        jPanel10.setBackground(new java.awt.Color(32, 32, 32));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableCompras.setBackground(new java.awt.Color(30, 30, 30));
        TableCompras.setForeground(new java.awt.Color(255, 255, 255));
        TableCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Proveedor", "Total", "Fecha"
            }
        ));
        TableCompras.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jScrollPane3.setViewportView(TableCompras);

        jPanel10.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 90, 1040, 480));

        txtBuscarCompra.setBackground(new java.awt.Color(47, 47, 47));
        txtBuscarCompra.setForeground(new java.awt.Color(255, 255, 255));
        txtBuscarCompra.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtBuscarCompra.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa_1.png"))); // NOI18N
        jPanel10.add(txtBuscarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 220, 40));

        btnHistorialCompras.setBackground(new java.awt.Color(51, 102, 255));
        btnHistorialCompras.setForeground(new java.awt.Color(255, 255, 255));
        btnHistorialCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf.png"))); // NOI18N
        btnHistorialCompras.setText("Generar Reporte");
        btnHistorialCompras.setBorderColor(new java.awt.Color(30, 30, 30));
        btnHistorialCompras.setColor(new java.awt.Color(51, 102, 255));
        btnHistorialCompras.setColorClick(new java.awt.Color(21, 21, 21));
        btnHistorialCompras.setColorOver(new java.awt.Color(51, 102, 255));
        btnHistorialCompras.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jPanel10.add(btnHistorialCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 30, -1, -1));

        btnEliminarCompra.setBackground(new java.awt.Color(51, 102, 255));
        btnEliminarCompra.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar-base-de-datos.png"))); // NOI18N
        btnEliminarCompra.setText("Eliminar Compra");
        btnEliminarCompra.setBorderColor(new java.awt.Color(30, 30, 30));
        btnEliminarCompra.setColor(new java.awt.Color(51, 102, 255));
        btnEliminarCompra.setColorClick(new java.awt.Color(21, 21, 21));
        btnEliminarCompra.setColorOver(new java.awt.Color(51, 102, 255));
        btnEliminarCompra.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jPanel10.add(btnEliminarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, -1, -1));

        txtIdCompra.setBackground(new java.awt.Color(47, 47, 47));
        txtIdCompra.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/img/id-insignia.png"))); // NOI18N
        jPanel10.add(txtIdCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 35, 130, 30));

        jTabbedPane1.addTab("Compras", jPanel10);

        jPanel11.setBackground(new java.awt.Color(32, 32, 32));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(new java.awt.Color(23, 23, 23));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la empresa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel17.setForeground(new java.awt.Color(255, 255, 255));

        jLabel40.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Nombre");

        jLabel45.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Telefono");

        jLabel46.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Direccion");

        txtNombreEmpresa.setBackground(new java.awt.Color(47, 47, 47));
        txtNombreEmpresa.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNombreEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        txtTelefonoEmpresa.setBackground(new java.awt.Color(47, 47, 47));
        txtTelefonoEmpresa.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTelefonoEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        txtTelefonoEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btnModificarEmpresa.setBackground(new java.awt.Color(37, 99, 235));
        btnModificarEmpresa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnModificarEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarEmpresa.setText("Modificar");

        txtDireccionEmpresa.setBackground(new java.awt.Color(47, 47, 47));
        txtDireccionEmpresa.setColumns(20);
        txtDireccionEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccionEmpresa.setRows(5);
        txtDireccionEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane13.setViewportView(txtDireccionEmpresa);

        jLabel47.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Rfc");

        txtRfcEmpresa.setBackground(new java.awt.Color(47, 47, 47));
        txtRfcEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        txtRfcEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel48.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Mensaje");

        txtMensaje.setBackground(new java.awt.Color(47, 47, 47));
        txtMensaje.setColumns(20);
        txtMensaje.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtMensaje.setForeground(new java.awt.Color(255, 255, 255));
        txtMensaje.setRows(5);
        txtMensaje.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane14.setViewportView(txtMensaje);

        txtIdEmpresa.setBackground(new java.awt.Color(47, 47, 47));
        txtIdEmpresa.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("C/Postal:");

        txtCodigoPostalEmpresa.setBackground(new java.awt.Color(47, 47, 47));
        txtCodigoPostalEmpresa.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45)
                            .addComponent(jLabel47)
                            .addComponent(jLabel48))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtTelefonoEmpresa)
                            .addComponent(txtNombreEmpresa)
                            .addComponent(jScrollPane13)
                            .addComponent(txtRfcEmpresa)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(txtIdEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnModificarEmpresa))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoPostalEmpresa)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRfcEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addGap(27, 27, 27)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefonoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addGap(34, 34, 34)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel46)))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel48))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigoPostalEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarEmpresa)
                    .addComponent(txtIdEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jPanel11.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 330, 540));

        jPanel1.setBackground(new java.awt.Color(23, 23, 23));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel60.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("Sistema Integrado de Punto de venta(POS)");

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoChaskas65.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(133, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addGap(88, 88, 88))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addGap(99, 99, 99))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel60)
                .addGap(36, 36, 36)
                .addComponent(jLabel54)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 660, 540));

        jTabbedPane1.addTab("tab10", jPanel11);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1089, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab11", jPanel12);

        jPanel14.setBackground(new java.awt.Color(47, 113, 214));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableNuevaCompra.setBackground(new java.awt.Color(30, 30, 30));
        TableNuevaCompra.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        TableNuevaCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Descripcion", "Cantidad", "Precio", "Total"
            }
        ));
        jScrollPane7.setViewportView(TableNuevaCompra);

        jPanel14.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 107, 960, 380));

        jLabel35.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Codigo");
        jPanel14.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jLabel36.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Producto");
        jPanel14.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        jLabel37.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Cant");
        jPanel14.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, -1));

        jLabel38.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Precio");
        jPanel14.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, -1, -1));

        txtCodNC.setBackground(new java.awt.Color(255, 255, 255));
        txtCodNC.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtCodNC.setForeground(new java.awt.Color(0, 0, 0));
        jPanel14.add(txtCodNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 160, 30));

        txtProductoNC.setBackground(new java.awt.Color(255, 255, 255));
        txtProductoNC.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtProductoNC.setForeground(new java.awt.Color(0, 0, 0));
        jPanel14.add(txtProductoNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 200, 30));

        txtCantNC.setBackground(new java.awt.Color(255, 255, 255));
        txtCantNC.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtCantNC.setForeground(new java.awt.Color(0, 0, 0));
        jPanel14.add(txtCantNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 60, 30));

        txtPrecioNC.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecioNC.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtPrecioNC.setForeground(new java.awt.Color(0, 0, 0));
        jPanel14.add(txtPrecioNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, -1, 30));

        txtTotalNC.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalNC.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTotalNC.setForeground(new java.awt.Color(0, 0, 0));
        jPanel14.add(txtTotalNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, -1, 30));

        jLabel39.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Total");
        jPanel14.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, -1, -1));

        btnGenerarCompra.setBackground(new java.awt.Color(255, 255, 255));
        btnGenerarCompra.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnGenerarCompra.setForeground(new java.awt.Color(0, 0, 0));
        btnGenerarCompra.setText("Generar Compra");
        jPanel14.add(btnGenerarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 150, 30));

        jLabel41.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Proveedor");
        jPanel14.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, -1, -1));

        cbxProvNC.setBackground(new java.awt.Color(255, 255, 255));
        cbxProvNC.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxProvNC.setForeground(new java.awt.Color(0, 0, 0));
        jPanel14.add(cbxProvNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 500, 210, 30));

        txtPagarConNC.setBackground(new java.awt.Color(255, 255, 255));
        txtPagarConNC.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtPagarConNC.setForeground(new java.awt.Color(0, 0, 0));
        txtPagarConNC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPagarConNCActionPerformed(evt);
            }
        });
        jPanel14.add(txtPagarConNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 500, 150, 30));

        txtVueltoCompra.setBackground(new java.awt.Color(255, 255, 255));
        txtVueltoCompra.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtVueltoCompra.setForeground(new java.awt.Color(0, 0, 0));
        jPanel14.add(txtVueltoCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 500, 150, 30));

        jLabel42.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Total Pagar");
        jPanel14.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 500, -1, -1));

        JLabelTotalCompra.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        JLabelTotalCompra.setForeground(new java.awt.Color(0, 0, 0));
        JLabelTotalCompra.setText("-------");
        jPanel14.add(JLabelTotalCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 500, -1, -1));

        jLabel43.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Pagar con");
        jPanel14.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 500, -1, -1));

        jLabel44.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Vuelto");
        jPanel14.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 500, -1, -1));

        jLabel67.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Id");
        jPanel14.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, -1, -1));

        txtIdNC.setBackground(new java.awt.Color(255, 255, 255));
        txtIdNC.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtIdNC.setForeground(new java.awt.Color(0, 0, 0));
        jPanel14.add(txtIdNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, -1, 30));

        jTabbedPane1.addTab("Nueva Compra", jPanel14);

        jPanel21.setBackground(new java.awt.Color(33, 33, 33));

        contenedorProductos.setBackground(new java.awt.Color(8, 216, 144));
        contenedorProductos.setRoundBottomLeft(50);
        contenedorProductos.setRoundBottomRight(50);
        contenedorProductos.setRoundTopLeft(50);
        contenedorProductos.setRoundTopRight(50);

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("Productos");

        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/caja-abierta (1).png"))); // NOI18N

        lblConteoProductos.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblConteoProductos.setForeground(new java.awt.Color(255, 255, 255));
        lblConteoProductos.setText("12");

        javax.swing.GroupLayout contenedorProductosLayout = new javax.swing.GroupLayout(contenedorProductos);
        contenedorProductos.setLayout(contenedorProductosLayout);
        contenedorProductosLayout.setHorizontalGroup(
            contenedorProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorProductosLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(contenedorProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contenedorProductosLayout.createSequentialGroup()
                        .addComponent(lblConteoProductos)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(contenedorProductosLayout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                        .addComponent(jLabel66)))
                .addContainerGap())
        );
        contenedorProductosLayout.setVerticalGroup(
            contenedorProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorProductosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(contenedorProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65)
                    .addComponent(jLabel66))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblConteoProductos)
                .addGap(18, 18, 18))
        );

        contenedorCompras.setBackground(new java.awt.Color(240, 84, 84));
        contenedorCompras.setRoundBottomLeft(50);
        contenedorCompras.setRoundBottomRight(50);
        contenedorCompras.setRoundTopLeft(50);
        contenedorCompras.setRoundTopRight(50);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Compras");

        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/compras.png"))); // NOI18N

        lblConteoCompras.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblConteoCompras.setForeground(new java.awt.Color(255, 255, 255));
        lblConteoCompras.setText("12");

        javax.swing.GroupLayout contenedorComprasLayout = new javax.swing.GroupLayout(contenedorCompras);
        contenedorCompras.setLayout(contenedorComprasLayout);
        contenedorComprasLayout.setHorizontalGroup(
            contenedorComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorComprasLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(contenedorComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contenedorComprasLayout.createSequentialGroup()
                        .addComponent(lblConteoCompras)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(contenedorComprasLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(jLabel63)))
                .addContainerGap())
        );
        contenedorComprasLayout.setVerticalGroup(
            contenedorComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorComprasLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(contenedorComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel63)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblConteoCompras)
                .addGap(19, 19, 19))
        );

        contenedroClientes.setBackground(new java.awt.Color(248, 229, 89));
        contenedroClientes.setRoundBottomLeft(50);
        contenedroClientes.setRoundBottomRight(50);
        contenedroClientes.setRoundTopLeft(50);
        contenedroClientes.setRoundTopRight(50);

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Clientes");

        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/comentarios-de-los-clientes.png"))); // NOI18N

        lblConteoClientes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblConteoClientes.setForeground(new java.awt.Color(255, 255, 255));
        lblConteoClientes.setText("12");

        javax.swing.GroupLayout contenedroClientesLayout = new javax.swing.GroupLayout(contenedroClientes);
        contenedroClientes.setLayout(contenedroClientesLayout);
        contenedroClientesLayout.setHorizontalGroup(
            contenedroClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedroClientesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(contenedroClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contenedroClientesLayout.createSequentialGroup()
                        .addComponent(lblConteoClientes)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(contenedroClientesLayout.createSequentialGroup()
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addComponent(jLabel69)
                        .addGap(17, 17, 17))))
        );
        contenedroClientesLayout.setVerticalGroup(
            contenedroClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedroClientesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(contenedroClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(lblConteoClientes)
                .addGap(20, 20, 20))
        );

        ContenedorVentas.setBackground(new java.awt.Color(129, 14, 157));
        ContenedorVentas.setRoundBottomLeft(50);
        ContenedorVentas.setRoundBottomRight(50);
        ContenedorVentas.setRoundTopLeft(50);
        ContenedorVentas.setRoundTopRight(50);

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Ventas");

        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/etiqueta-de-precio.png"))); // NOI18N

        lblConteoVentas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblConteoVentas.setForeground(new java.awt.Color(255, 255, 255));
        lblConteoVentas.setText("12");

        javax.swing.GroupLayout ContenedorVentasLayout = new javax.swing.GroupLayout(ContenedorVentas);
        ContenedorVentas.setLayout(ContenedorVentasLayout);
        ContenedorVentasLayout.setHorizontalGroup(
            ContenedorVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorVentasLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(ContenedorVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ContenedorVentasLayout.createSequentialGroup()
                        .addComponent(lblConteoVentas)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(ContenedorVentasLayout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                        .addComponent(jLabel71)
                        .addGap(18, 18, 18))))
        );
        ContenedorVentasLayout.setVerticalGroup(
            ContenedorVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorVentasLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(ContenedorVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel71)
                    .addComponent(jLabel70))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblConteoVentas)
                .addGap(15, 15, 15))
        );

        panelShadow1.setBackground(new java.awt.Color(21, 21, 21));
        panelShadow1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelShadow1.setColorGradient(new java.awt.Color(32, 32, 32));

        chart.setFillColor(true);

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );

        TableBajoStock.setBackground(new java.awt.Color(30, 30, 30));
        TableBajoStock.setForeground(new java.awt.Color(255, 255, 255));
        TableBajoStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "idProducto", "Producto", "Stock"
            }
        ));
        TableBajoStock.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jScrollPane1.setViewportView(TableBajoStock);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Productos con bajo stock");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(contenedorProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(contenedorCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(192, 192, 192)))
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(contenedroClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(ContenedorVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(contenedroClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contenedorCompras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contenedorProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ContenedorVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("Inicio", jPanel21);

        jPanel22.setBackground(new java.awt.Color(32, 32, 32));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(598, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(214, Short.MAX_VALUE)
                .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );

        jTabbedPane1.addTab("Graficas", jPanel22);

        jPanel23.setBackground(new java.awt.Color(32, 32, 32));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1089, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab15", jPanel23);

        btnUser.setForeground(new java.awt.Color(0, 0, 0));
        btnUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gerente.png"))); // NOI18N
        btnUser.setText("Administrador");
        btnUser.setBorderColor(new java.awt.Color(21, 21, 21));
        btnUser.setColorClick(new java.awt.Color(21, 21, 21));
        btnUser.setColorOver(new java.awt.Color(255, 255, 255));
        btnUser.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnUser.setRadius(50);

        btnScroll.setBackground(new java.awt.Color(92, 56, 255));
        btnScroll.setForeground(new java.awt.Color(255, 255, 255));
        btnScroll.setText("+Add");
        btnScroll.setBorderColor(new java.awt.Color(21, 21, 21));
        btnScroll.setColor(new java.awt.Color(92, 56, 255));
        btnScroll.setColorClick(new java.awt.Color(21, 21, 21));
        btnScroll.setColorOver(new java.awt.Color(92, 56, 255));
        btnScroll.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnScroll.setPreferredSize(new java.awt.Dimension(72, 35));
        btnScroll.setRadius(10);
        btnScroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScrollActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        lblGraficas.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblGraficas.setForeground(new java.awt.Color(255, 255, 255));
        lblGraficas.setText("Graficas");
        lblGraficas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGraficasMouseClicked(evt);
            }
        });

        lblHora.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblHora.setForeground(new java.awt.Color(255, 255, 255));
        lblHora.setText("Loading");

        jLabel55.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("recuerda abrir caja");

        jLabel62.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Términos de servicio");

        lblAcercaDe.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblAcercaDe.setForeground(new java.awt.Color(255, 255, 255));
        lblAcercaDe.setText("Acerca de");
        lblAcercaDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAcercaDeMouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa_1.png"))); // NOI18N

        txtBuscarProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblGraficas)
                        .addGap(18, 18, 18)
                        .addComponent(lblAcercaDe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblHora)
                .addGap(18, 18, 18)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel62)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnScroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 1, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblGraficas)
                                    .addComponent(lblAcercaDe)
                                    .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHora)
                            .addComponent(jLabel55)
                            .addComponent(jLabel62))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnScrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScrollActionPerformed


    }//GEN-LAST:event_btnScrollActionPerformed

    private void lblAcercaDeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAcercaDeMouseClicked
        JOptionPane.showMessageDialog(this, "Sistema: SalesFusion" + "\nVersion: 2.2"
                + "\nProgramador: Jose Armando Sandoval Santana"
                + "\nSoporte: 3414190809"
                + "\nTodos los derechos reservados"
                + "\n", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_lblAcercaDeMouseClicked

    private void lblGraficasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGraficasMouseClicked
        ProductosDao productosDao = new ProductosDao();
        PanelAdmin.this.jTabbedPane1.setSelectedIndex(12);
        Map<String, Integer> data = productosDao.obtenerCantidadPorCategoria();
        //new PieChartExample(data, panelGrafica);
        setVisible(true);
    }//GEN-LAST:event_lblGraficasMouseClicked

    private void txtPagarConNCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPagarConNCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPagarConNCActionPerformed

    private void btnRegistrarMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarMedActionPerformed
        medida2.setVisible(true);
    }//GEN-LAST:event_btnRegistrarMedActionPerformed

    private void btnModificarMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarMedActionPerformed
        modificarMedida.setVisible(true);
        
    }//GEN-LAST:event_btnModificarMedActionPerformed

    private void btnNuevoCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCatActionPerformed
        categoria.setVisible(true);
    }//GEN-LAST:event_btnNuevoCatActionPerformed

    private void btnModificarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCatActionPerformed
        modificarCategoria.setVisible(true);
    }//GEN-LAST:event_btnModificarCatActionPerformed

    private void btnRegistrarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarUserActionPerformed
        usuario.setVisible(true);
    }//GEN-LAST:event_btnRegistrarUserActionPerformed

    private void btnRegistrarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarProvActionPerformed
        proveedor.setVisible(true);
    }//GEN-LAST:event_btnRegistrarProvActionPerformed

    private void btnRegistrarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarCliActionPerformed
        clientes.setVisible(true);
    }//GEN-LAST:event_btnRegistrarCliActionPerformed

    private void btnRegistrarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarProActionPerformed
        productoNuevo.setVisible(true);
    }//GEN-LAST:event_btnRegistrarProActionPerformed

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
            java.util.logging.Logger.getLogger(PanelAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());

        } catch (Exception e) {

        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public controlador.PanelRound ContenedorVentas;
    public javax.swing.JLabel JLabelTotalCompra;
    private javax.swing.JLabel JLabelTotalPagar;
    public javax.swing.JMenuItem JMenuEliminarCat;
    public javax.swing.JMenuItem JMenuEliminarCli;
    public javax.swing.JMenuItem JMenuEliminarMed;
    public javax.swing.JMenuItem JMenuEliminarProd;
    public javax.swing.JMenuItem JMenuEliminarProv;
    public javax.swing.JMenuItem JMenuEliminarUsers;
    public javax.swing.JMenuItem JMenuReingresarCat;
    public javax.swing.JMenuItem JMenuReingresarCli;
    public javax.swing.JMenuItem JMenuReingresarMed;
    public javax.swing.JMenuItem JMenuReingresarProd;
    public javax.swing.JMenuItem JMenuReingresarProv;
    public javax.swing.JMenuItem JMenuReingresarUsers;
    public javax.swing.JPopupMenu JPopupCategorias;
    private javax.swing.JPopupMenu JPopupClientes;
    public javax.swing.JPopupMenu JPopupMedidas;
    private javax.swing.JPopupMenu JPopupProductos;
    private javax.swing.JPopupMenu JPopupProveedor;
    private javax.swing.JPopupMenu JPopupUsuarios;
    private javax.swing.JPanel PaginadoProv;
    private javax.swing.JPanel PaginadoVentas;
    public javax.swing.JPanel PaginadorCli;
    private javax.swing.JPanel PaginadorPro;
    private Table.TableDark TableBajoStock;
    public javax.swing.JTable TableCat;
    public javax.swing.JTable TableClientes;
    public Table.TableDark TableCompras;
    public javax.swing.JTable TableMed;
    public javax.swing.JTable TableNuevaCompra;
    private javax.swing.JTable TableNuevaVenta;
    public javax.swing.JTable TableProductos;
    public javax.swing.JTable TableProveedor;
    public javax.swing.JTable TableUsers;
    private javax.swing.JTable TableVentas;
    public controlador.MyButton btnEliminarCompra;
    public javax.swing.JButton btnGenerarCompra;
    private javax.swing.JButton btnGenerarVenta;
    public controlador.MyButton btnHistorialCompras;
    public controlador.MyButton btnModificarCat;
    public javax.swing.JButton btnModificarEmpresa;
    public controlador.MyButton btnModificarMed;
    public controlador.MyButton btnNuevoCat;
    public controlador.MyButton btnRegistrarCli;
    public controlador.MyButton btnRegistrarMed;
    public controlador.MyButton btnRegistrarPro;
    public controlador.MyButton btnRegistrarProv;
    public controlador.MyButton btnRegistrarUser;
    private controlador.MyButton btnScroll;
    private controlador.MyButton btnUser;
    private javax.swing.JComboBox<String> cbxClientes;
    public javax.swing.JComboBox<Object> cbxProvNC;
    private chart.CurveLineChart chart;
    public controlador.PanelRound contenedorCompras;
    public controlador.PanelRound contenedorProductos;
    public controlador.PanelRound contenedroClientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAcercaDe;
    private javax.swing.JLabel lblConteoClientes;
    public javax.swing.JLabel lblConteoCompras;
    public javax.swing.JLabel lblConteoProductos;
    public javax.swing.JLabel lblConteoVentas;
    private javax.swing.JLabel lblGraficas;
    private javax.swing.JLabel lblHora;
    private controlador.Menu menu;
    private controlador.PanelShadow panelShadow1;
    private chart.PieChart pieChart1;
    public controlador.MyTextField txtBuscarCategoria;
    public controlador.MyTextField txtBuscarCli;
    public controlador.MyTextField txtBuscarCompra;
    public controlador.MyTextField txtBuscarMed;
    public controlador.TextField txtBuscarProducto;
    public controlador.MyTextField txtBuscarProv;
    public controlador.MyTextField txtBuscarUsers;
    public javax.swing.JTextField txtCantNC;
    private javax.swing.JTextField txtCantNV;
    public javax.swing.JTextField txtCodNC;
    private javax.swing.JTextField txtCodNV;
    public javax.swing.JTextField txtCodigoPostalEmpresa;
    public javax.swing.JTextArea txtDireccionEmpresa;
    public controlador.MyTextField txtIdCat;
    public controlador.MyTextField txtIdCli;
    public controlador.MyTextField txtIdCompra;
    public javax.swing.JTextField txtIdEmpresa;
    public controlador.MyTextField txtIdMed;
    public javax.swing.JTextField txtIdNC;
    public controlador.MyTextField txtIdProducto;
    public controlador.MyTextField txtIdProv;
    public controlador.MyTextField txtIdUser;
    public javax.swing.JTextArea txtMensaje;
    public javax.swing.JTextField txtNombreEmpresa;
    private javax.swing.JTextField txtPagarCon;
    public javax.swing.JTextField txtPagarConNC;
    public javax.swing.JTextField txtPrecioNC;
    private javax.swing.JTextField txtPrecioNV;
    public javax.swing.JTextField txtProductoNC;
    private javax.swing.JTextField txtProductoNV;
    public javax.swing.JTextField txtRfcEmpresa;
    private javax.swing.JTextField txtStockNV;
    public javax.swing.JTextField txtTelefonoEmpresa;
    public javax.swing.JTextField txtTotalNC;
    private javax.swing.JTextField txtTotalNV;
    private javax.swing.JTextField txtVuelto;
    public javax.swing.JTextField txtVueltoCompra;
    // End of variables declaration//GEN-END:variables

    // Funcion para cerrar sesion
    public void desertar() {
        // Muestra un cuadro de diálogo con la pregunta "¿Deseas salir?"
        int response = JOptionPane.showConfirmDialog(null, "¿Deseas Cerrar Sesion?", "Confirmación de salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        // Verifica la respuesta del usuario
        if (response == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Cerrando Sesion.....");
            // Aquí puedes poner el código para cerrar la aplicación si es necesario
            // Termina la aplicación
            Login l = new Login();
            l.setVisible(true);
            this.dispose();
        } else {
            // No hacer nada

        }
    }

    // Método para obtener la hora actual formateada
    private String obtenerHoraActual() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
        return ahora.format(formateador);
    }
    
    public void refrescarContenedores() {
        ContenedoresController docker = new ContenedoresController(this);
        docker.contarClientes();
        docker.contarCompras();
        docker.contarProductos();
    }
    
    
    
    public void iniciar() {
        ProductosDao productosDao = new ProductosDao();
        PanelAdmin.this.jTabbedPane1.setSelectedIndex(12);
        Map<String, Integer> data = productosDao.obtenerCantidadPorCategoria();
        //new PieChartExample(data, panelGrafica);
        setVisible(true);
        
        llenarTablaBajoStock(TableBajoStock, 100);
        refrescarContenedores();
    }
    // Metodos para seleccion de color
    
    public void ConteoCompras(int conteo) {
        lblConteoCompras.setText("" + conteo);
    }
    
    public void ConteoProductos(int conteo) {
        lblConteoProductos.setText("" + conteo);
    }
    
    public void ConteoClientes(int conteo) {
        lblConteoClientes.setText("" + conteo);
    }
    
    public void llenarTablaBajoStock(JTable TableBajoStock, int umbralBajoStock) {
    DefaultTableModel modelo = (DefaultTableModel) TableBajoStock.getModel();
    modelo.setRowCount(0); // Limpiar la tabla antes de llenarla
    String sql = "SELECT id, descripcion, cantidad FROM productos WHERE cantidad < ?";
    try {
        con = cn.getConexion();
        ps = con.prepareStatement(sql);
        ps.setInt(1, umbralBajoStock);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String descripcion = rs.getString("descripcion");
            int cantidad = rs.getInt("cantidad");
            
            // Agregar fila a la tabla
            modelo.addRow(new Object[]{id, descripcion, cantidad});
        }
        
        rs.close();
        ps.close();
        
        // Repintar la tabla para reflejar los cambios
        TableBajoStock.repaint();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
   
    

}