
package modelo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Map;
import java.util.HashMap;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Jose
 */
public class ProductosDao {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Metodo para registrar Proveedores
    public boolean registrar(Productos prod) {
        if (existeProducto(prod.getCodigo())) {
            JOptionPane.showMessageDialog(null, "el producto ya existe");
            return false;
        }
        String sql = "INSERT INTO productos (codigo, descripcion, precio_compra, precio_venta, id_proveedor, id_medida, id_categoria) VALUES (?,?,?,?,?,?,?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, prod.getCodigo());
            ps.setString(2, prod.getDescripcion());
            ps.setDouble(3, prod.getPrecio_compra());
            ps.setDouble(4, prod.getPrecio_venta());
            ps.setInt(5, prod.getId_proveedor());
            ps.setInt(6, prod.getId_medida());
            ps.setInt(7, prod.getId_categoria());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    // Metodo para verificar si ya existe un producto
    public boolean existeProducto(String codigo) {
        String sql = "SELECT * FROM productos WHERE codigo = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            return rs.next(); // si hay un resultado, devuelve true
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    // Listar Proveedores listaProveedores
    public List ListaProductos(String valor) {
        List<Productos> listaProductos = new ArrayList();
        // Consulta para ordenar registros listados y buscar en la caja de texto
        String sql = "SELECT * FROM productos ORDER BY estado ASC";
        String buscar = "SELECT * FROM productos  WHERE codigo LIKE '%" + valor + "%' OR descripcion LIKE '%" + valor + "%'";
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
                Productos prod = new Productos();
                prod.setId(rs.getInt("id"));
                prod.setCodigo(rs.getString("codigo"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecio_venta(rs.getInt("precio_compra"));
                prod.setCantidad(rs.getInt("cantidad"));
                prod.setEstado(rs.getString("estado"));
                listaProductos.add(prod);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaProductos;
    }

    // Metodo para modificar
    public boolean modificar(Productos prod) {
        String sql = "UPDATE productos SET codigo = ?, descripcion = ?, precio_compra =?, precio_venta = ?, id_proveedor = ?, id_medida = ?, id_categoria = ? WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, prod.getCodigo());
            ps.setString(2, prod.getDescripcion());
            ps.setDouble(3, prod.getPrecio_compra());
            ps.setDouble(4, prod.getPrecio_venta());
            ps.setInt(5, prod.getId_proveedor());
            ps.setInt(6, prod.getId_medida());
            ps.setInt(7, prod.getId_categoria());
            ps.setInt(8, prod.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    public boolean accion(String estado, int id) {
        String sql = "UPDATE productos SET estado = ? WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    public Productos buscarPro(int id) {
        String sql = "SELECT p.*, pr.id, pr.proveedor, m.id, m.medida, c.id, c.categoria FROM productos p INNER JOIN proveedor pr ON p.id_proveedor = pr.id INNER JOIN medidas m ON p.id_medida = m.id INNER JOIN categorias c ON p.id_categoria = c.id WHERE p.id = ?";
        Productos prod = new Productos();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                prod.setCodigo(rs.getString("codigo"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecio_compra(rs.getDouble("precio_compra"));
                prod.setPrecio_venta(rs.getDouble("precio_venta"));
                prod.setId_proveedor(rs.getInt("id_proveedor"));
                prod.setId_medida(rs.getInt("id_medida"));
                prod.setId_categoria(rs.getInt("id_categoria"));
                prod.setProveedor(rs.getString("proveedor"));
                prod.setMedida(rs.getString("medida"));
                prod.setCat(rs.getString("categoria"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return prod;
    }

    // Método para obtener la cantidad de productos por categoría
    public Map<String, Integer> obtenerCantidadPorCategoria() {
        Map<String, Integer> datos = new HashMap<>();
        String sql = "SELECT c.categoria, COUNT(p.id) AS cantidad FROM productos p "
                + "INNER JOIN categorias c ON p.id_categoria = c.id "
                + "GROUP BY c.categoria";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                datos.put(rs.getString("categoria"), rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return datos;
    }

    // Modulo compras
     public Productos buscarCodigo(String codigo) {
        String sql = "SELECT * FROM productos WHERE codigo = ?";
        Productos prod = new Productos();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                prod.setId(rs.getInt("id"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecio_compra(rs.getDouble("precio_compra"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return prod;
    }
     
     // Metodos para actualizar stock
     public Productos buscarStock(String codigo) {
         String sql = "SELECT * FROM productos WHERE codigo = ?";
         Productos prod = new Productos();
         try {
             con = cn.getConexion();
             ps = con.prepareStatement(sql);
             ps.setString(1, codigo);
             rs = ps.executeQuery();
             if (rs.next()) {
                 prod.setId(rs.getInt("id"));
                 prod.setDescripcion(rs.getString("descripcion"));
                 prod.setCantidad(rs.getInt("cantidad"));
             }
         } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
         return prod;
     }
     
     // Metodo para actualizar stock de producto
     public boolean actualizarStock(int cant, int id) {
         String sql = "UPDATE productos SET cantidad = ? WHERE id = ?";
         try {
             con = cn.getConexion();
             ps = con.prepareStatement(sql);
             ps.setInt(1, cant);
             ps.setInt(2, id);
             ps.execute();
             return true;
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.toString());
             return false;
         }
     }
         
    // Metodo para comprar
    public boolean registrarCompra(int id, String total) {
        String sql = "INSERT INTO compras (id_proveedor, total) VALUES (?,?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, total);
            ps.execute();
            return true;
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
           return false;
        }
    }
    
    public boolean registrarCompraDetalle(int id_compra, int id, double precio, int cant, double sub_total) {
        String sql = "INSERT INTO detalle_compra (id_compra, id_producto, precio, cantidad, subtotal) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_compra);
            ps.setInt(2, id);
            ps.setDouble(3, precio);
            ps.setInt(4, cant);
            ps.setDouble(5, sub_total);
            ps.execute();
            return true;
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
           return false;
        }
    }
    
     // Modulo compras
     public Productos buscarId(int id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        Productos prod = new Productos();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                prod.setCantidad(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return prod;
    }
    // Metodo para los datos de la empresa
   
    
      // Metodo para modificar
    public boolean ActualizarStockCompra(int cant, int id) {
        String sql = "UPDATE productos SET cantidad = ? WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    public int id_compra() {
        int id = 0;
        String sql = "SELECT MAX(id) AS id FROM compras";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return id;
    }
    
    // Listar Proveedores listaProveedores
    public List ListaDetalle(int id_compra) {
        List<Productos> listaProductos = new ArrayList();
        // Consulta para ordenar registros listados y buscar en la caja de texto
        String sql = "SELECT c.*, d.*, p.id, p.descripcion FROM compras c INNER JOIN detalle_compra d ON d.id_compra = c.id INNER JOIN productos p ON p.id = d.id_producto WHERE c.id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_compra);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos prod = new Productos();
                prod.setCantidad(rs.getInt("cantidad"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecio_compra(rs.getDouble("precio"));
                prod.setPrecio_venta(rs.getDouble("subtotal"));
                listaProductos.add(prod);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaProductos;
    }
    
    public void generarReporte(int id_compra) {
       double totalGeneral = 0.00;
       String fecha = "";
       String nomProveedor = "";
       String dirProveedor = "";
       String telProveedor = "";
       String mensaje = "";
       
       try {
           // Agregando un font ala letra
           Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
          String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
          FileOutputStream archivo ;
          File salida = new File(url + File.separator + "compra.pdf");
          archivo = new FileOutputStream(salida);
          Document doc = new Document();
          PdfWriter.getInstance(doc, archivo);
          doc.open();
          // Contenido de reporte
          PdfPTable empresa = new PdfPTable(4);
          empresa.setWidthPercentage(100);
          float[] tamanioEncabezado = new float[]{15f, 15f, 40f, 30f};
          empresa.setWidths(tamanioEncabezado);
          empresa.setHorizontalAlignment(Element.ALIGN_LEFT);
          empresa.getDefaultCell().setBorder(0);
          // Capturar y agregar Logotipo
          Image img = Image.getInstance(getClass().getResource("/img/ReporteProv.png"));
          empresa.addCell(img);
          empresa.addCell("");
          
           // Consulta los datos de la empresa
           String sql = "SELECT * FROM configuracion";
           try {
               con = cn.getConexion();
               ps = con.prepareStatement(sql);
               rs = ps.executeQuery();
               if (rs.next()) {
                   mensaje = rs.getString("mensaje");
                   // Agregar los datos de la empresa
                   empresa.addCell("Rfc: " + rs.getString("rfc") + "\nNombre: " + rs.getString("nombre")
                   + "\nTelefono: " + rs.getString("telefono") + "\nDireccion: " + rs.getString("direccion")
                   + "\nCodigoPostal: " + rs.getString("codigoPostal"));
               }
           } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, e.toString());
           }
           // Consulta los datos de proveedor
           String consultaProveedor = "SELECT p.proveedor, p.telefono, p.direccion, c.total, c.fecha FROM compras c INNER JOIN proveedor p ON c.id_proveedor = p.id WHERE c.id = " + id_compra;
           try {
               con = cn.getConexion();
               ps = con.prepareStatement(consultaProveedor);
               rs = ps.executeQuery();
               if (rs.next()) {
                   // Agregar los datos del proveedor
                   nomProveedor = rs.getString("proveedor");
                   dirProveedor = rs.getString("telefono");
                   telProveedor = rs.getString("direccion");
                   totalGeneral = rs.getDouble("total");
                   fecha = rs.getString("fecha");
               }
           } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, e.toString());
           }       
           
           
           
           
           
          // Datos del vendedor
          empresa.addCell("Nº Compra: " + id_compra + "\nVendedor: " + "Jose Armando"
                  + "\nFecha: " + fecha);
          
          doc.add(empresa);
          // Fin de datos de empresa
          
          
          
          doc.add(Chunk.NEWLINE);
          
          // Titulo Proveedor
          Paragraph titProveedor = new Paragraph();
          titProveedor.add("Datos del proveedor");
          titProveedor.setAlignment(Element.ALIGN_CENTER);
          doc.add(titProveedor);
          doc.add(Chunk.NEWLINE);
          
          
          
          // Mostrar datos del proveedor
           PdfPTable proveedor = new PdfPTable(3);
          proveedor.setWidthPercentage(100);
          float[] tamanioProveedor = new float[]{40f, 20f, 40f};
          proveedor.setWidths(tamanioProveedor);
          proveedor.setHorizontalAlignment(Element.ALIGN_LEFT);
           proveedor.getDefaultCell().setBorder(0);
           // Encabezado Proveedor
           PdfPCell nomPr = new PdfPCell(new Phrase("Nombre", negrita));
           PdfPCell telPr = new PdfPCell(new Phrase("Telefono", negrita));
           PdfPCell direPr = new PdfPCell(new Phrase("Direccion", negrita));
           
           // Eliminar bordes de los encabezados
           nomPr.setBorder(0);
           telPr.setBorder(0);
           direPr.setBorder(0);
           
           // Color de fondo del encabezado
           nomPr.setBackgroundColor(BaseColor.DARK_GRAY);
           telPr.setBackgroundColor(BaseColor.DARK_GRAY);
           direPr.setBackgroundColor(BaseColor.DARK_GRAY);
           
           // Agregar los encabezados del proveedor
           proveedor.addCell(nomPr);
           proveedor.addCell(telPr);
           proveedor.addCell(direPr);
           
           // Agregar datos del proveedor
            proveedor.addCell(nomProveedor);
           proveedor.addCell(telProveedor);
           proveedor.addCell(dirProveedor);
           
          doc.add(proveedor);
          // fin proveedor
          doc.add(Chunk.NEWLINE);
          
          // Titulo Productos
          Paragraph titProductos = new Paragraph();
          titProductos.add("Detalles de la compra");
          titProductos.setAlignment(Element.ALIGN_CENTER);
          doc.add(titProductos);
          doc.add(Chunk.NEWLINE);
          
          // Mostrar productos
           PdfPTable producto = new PdfPTable(4);
           producto.setWidthPercentage(100);
           float[] tamanioProducto = new float[]{50f, 10f, 20f, 20f};
           producto.setWidths(tamanioProducto);
           producto.setHorizontalAlignment(Element.ALIGN_LEFT);
           producto.getDefaultCell().setBorder(0);
           // Encabezado de Productos
           PdfPCell desPro = new PdfPCell(new Phrase("Descripcion", negrita));
           PdfPCell cantPro = new PdfPCell(new Phrase("Cantidad", negrita));
           PdfPCell precioPro = new PdfPCell(new Phrase("Precio", negrita));
           PdfPCell subPro= new PdfPCell(new Phrase("SubTotal", negrita));
           
           // Eliminar bordes de los encabezados
           desPro.setBorder(0);
           cantPro.setBorder(0);
           precioPro.setBorder(0);
           subPro.setBorder(0);
           
           // Color de fondo del encabezado
           desPro.setBackgroundColor(BaseColor.DARK_GRAY);
           cantPro.setBackgroundColor(BaseColor.DARK_GRAY);
           precioPro.setBackgroundColor(BaseColor.DARK_GRAY);
           subPro.setBackgroundColor(BaseColor.DARK_GRAY);
           
           // Agregar los encabezados del producto
           producto.addCell(desPro);
           producto.addCell(cantPro);
           producto.addCell(precioPro);
           producto.addCell(subPro);
           
           // Consulta de datos de productos
           String consultaProductos = "SELECT d.precio, d.cantidad, d.subtotal, p.descripcion FROM compras c INNER JOIN detalle_compra d ON c.id = d.id_compra INNER JOIN productos p ON d.id_producto = p.id WHERE c.id = " + id_compra;
           try {
               con = cn.getConexion();
               ps = con.prepareStatement(consultaProductos);
               rs = ps.executeQuery();
               while (rs.next()) {
                   // Agregar los datos del producto
                   producto.addCell(rs.getString("descripcion"));
                   producto.addCell(rs.getString("cantidad"));
                   producto.addCell(rs.getString("precio"));
                   producto.addCell(rs.getString("subtotal"));
               }
           } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, e.toString());
           }

           doc.add(producto);
           doc.add(Chunk.NEWLINE);
           // Fin detalle productos
           // Mostrar total a pagar
           Paragraph total = new Paragraph();
           total.add("Total a pagar: " + totalGeneral);
           total.setAlignment(Element.ALIGN_RIGHT);
           doc.add(total);
           doc.add(Chunk.NEWLINE);

           // Mostrar mensaje
           Paragraph agradecimiento = new Paragraph();
           agradecimiento.add(mensaje);
           agradecimiento.setAlignment(Element.ALIGN_CENTER);
           doc.add(agradecimiento);
           doc.add(Chunk.NEWLINE);
           
           // Cerrar Archivo
           doc.close();
          archivo.close();
          Desktop.getDesktop().open(salida);
       } catch (DocumentException | HeadlessException | IOException e) {
           
       }
       
    }
}
