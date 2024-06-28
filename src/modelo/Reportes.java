/**
 *  Clase para programar los reportes de cada modulo
 */

package modelo;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import modelo.Conexion;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose
 */
public class Reportes {
    // Variables
     private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion cn = new Conexion();
    
    /* ********************************************************************
    * metodo para crear reportes de los clientes registrados en el sistema
    *********************************************************************** */
     public void generarReporteClientes() {
        Document documento = new Document();
        try {
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Clientes.pdf"));
            documento.open();

            // Imagen del encabezado
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/img/header2.png");
            header.scaleToFit(128, 128);
            header.setAlignment(Element.ALIGN_LEFT);
            
            // Formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.add("Reporte creado por SalesFusionv2.2\n © DevSandoval\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.setAlignment(Element.ALIGN_CENTER);
            parrafo.add("Reporte de Clientes \n\n");
            
            // Crear una tabla con dos columnas
            PdfPTable tablaEncabezado = new PdfPTable(2);
            tablaEncabezado.setWidthPercentage(100); // Ancho de la tabla al 100%
            tablaEncabezado.setWidths(new int[]{1, 3}); // Proporción del ancho de las columnas

            // Agregar la imagen a la primera celda
            PdfPCell celdaImagen = new PdfPCell(header);
            celdaImagen.setBorder(Rectangle.NO_BORDER); // Sin bordes
            tablaEncabezado.addCell(celdaImagen);

            // Agregar el párrafo a la segunda celda
            PdfPCell celdaParrafo = new PdfPCell(parrafo);
            celdaParrafo.setBorder(Rectangle.NO_BORDER); // Sin bordes
            celdaParrafo.setVerticalAlignment(Element.ALIGN_RIGHT); // Alineación vertical al medio
            tablaEncabezado.addCell(celdaParrafo);

            // Agregar la tabla al documento
            documento.add(tablaEncabezado);

            // Creación de la tabla de datos
            PdfPTable tabla = new PdfPTable(8);
            tabla.setWidthPercentage(100); // Ancho de la tabla al 100%
            float[] tamanioCliente = new float[]{12.5f,12.5f,12.5f,12.5f,12.5f,12.5f,12.5f,12.5f};
            tabla.setWidths(tamanioCliente);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla.getDefaultCell().setBorder(0);
            // Encabezado de clientes
            PdfPCell codCli = new PdfPCell(new Phrase("Codigo", negrita));
            PdfPCell nombreCli = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell teleCli = new PdfPCell(new Phrase("Telefono", negrita));
            PdfPCell direCli = new PdfPCell(new Phrase("Direccion", negrita));
            PdfPCell combiCli = new PdfPCell(new Phrase("Combinacion", negrita));
            PdfPCell yoguCli = new PdfPCell(new Phrase("Yogurt", negrita));
            PdfPCell helaCli = new PdfPCell(new Phrase("Helado", negrita));
            PdfPCell cobeCli = new PdfPCell(new Phrase("Cobertura", negrita));
            
            // Eliminar bordes de los encabezados
            codCli.setBorder(0);
            nombreCli.setBorder(0);
            teleCli.setBorder(0);
            direCli.setBorder(0);
            combiCli.setBorder(0);
            yoguCli.setBorder(0);
            helaCli.setBorder(0);
            cobeCli.setBorder(0);
            
            // Color de fondo del encabezado
            codCli.setBackgroundColor(BaseColor.DARK_GRAY);
            nombreCli.setBackgroundColor(BaseColor.DARK_GRAY);
            teleCli.setBackgroundColor(BaseColor.DARK_GRAY);
            direCli.setBackgroundColor(BaseColor.DARK_GRAY);
            combiCli.setBackgroundColor(BaseColor.DARK_GRAY);
            yoguCli.setBackgroundColor(BaseColor.DARK_GRAY);
            helaCli.setBackgroundColor(BaseColor.DARK_GRAY);
            cobeCli.setBackgroundColor(BaseColor.DARK_GRAY);
            
           // Agregar los encabezados del producto
           tabla.addCell(codCli);
           tabla.addCell(nombreCli);
           tabla.addCell(teleCli);
           tabla.addCell(direCli);
           tabla.addCell(combiCli);
           tabla.addCell(yoguCli);
           tabla.addCell(helaCli);
           tabla.addCell(cobeCli);
           
           // Creando la consulta y la agregamos a un String
           String consultaCliente = "SELECT id, nombre, telefono, direccion, combinacion, yogurt, helado, cobertura FROM clientes";
            try {
                con = cn.getConexion(); // Inicializamos la conexión
                ps = con.prepareStatement(consultaCliente);
                rs = ps.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                        tabla.addCell(rs.getString(6));
                        tabla.addCell(rs.getString(7));
                        tabla.addCell(rs.getString(8));
                    } while (rs.next());
                    documento.add(tabla);
                }
             } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
             }
             documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }

    }
     
    /* ********************************************************************
    * metodo para crear reportes de los productos registrados en el sistema
    **********************************************************************/ 
    public void generarReporteProducto() {
        Document documento = new Document();
        try {
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Productos.pdf"));
            documento.open();
            
            // Imagen del encabezado
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/img/header2.png");
            header.scaleToFit(128,128);
            header.setAlignment(Element.ALIGN_LEFT);
            
            // Formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.add("Reporte creado por SalesFusionV2.2\n © DevSandoval\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.setAlignment(Element.ALIGN_CENTER);
            parrafo.add("Reporte de productos\n\n");
            
            // Crear una tabla con dos columnas
            PdfPTable tablaEncabezado = new PdfPTable(2);
            tablaEncabezado.setWidthPercentage(100); // ANcho de la tabla al 100%
            tablaEncabezado.setWidths(new int[]{1, 3}); // Proporcion del ancho de las columnas
            
            // Agregar la imagen a la primera celda
            PdfPCell celdaImagen = new PdfPCell(header);
            celdaImagen.setBorder(Rectangle.NO_BORDER); // Sin bordes
            tablaEncabezado.addCell(celdaImagen);
            
            // Agregar el parrafo ala segunda celda
            PdfPCell celdaParrafo = new PdfPCell(parrafo);
            celdaParrafo.setBorder(Rectangle.NO_BORDER); // Sin bordes
            celdaParrafo.setVerticalAlignment(Element.ALIGN_MIDDLE); // Alineacion vertical al medio
            tablaEncabezado.addCell(celdaParrafo);
            
            // Agregar la tabla al documento
            documento.add(tablaEncabezado);

            // Creacion de la tabla de datos
            // Creacion de la tabla de datos
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100); // Ancho de la tabla al 100%
            float[] tamanioProductos = new float[]{20f, 20f, 20f, 20f, 20f};
            tabla.setWidths(tamanioProductos);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla.getDefaultCell().setBorder(0);

// Encabezado de productos
            PdfPCell idPro = new PdfPCell(new Phrase("Id", negrita));
            PdfPCell codPro = new PdfPCell(new Phrase("Codigo", negrita));
            PdfPCell desPro = new PdfPCell(new Phrase("Descripcion", negrita));
            PdfPCell prec = new PdfPCell(new Phrase("Precio", negrita));
            PdfPCell cantPro = new PdfPCell(new Phrase("Stock", negrita));

// Eliminar bordes de los encabezados
            idPro.setBorder(0);
            codPro.setBorder(0);
            desPro.setBorder(0);
            prec.setBorder(0);
            cantPro.setBorder(0);

// Color de fondo del encabezado
            idPro.setBackgroundColor(BaseColor.DARK_GRAY);
            codPro.setBackgroundColor(BaseColor.DARK_GRAY);
            desPro.setBackgroundColor(BaseColor.DARK_GRAY);
            prec.setBackgroundColor(BaseColor.DARK_GRAY);
            cantPro.setBackgroundColor(BaseColor.DARK_GRAY);

// Agregar los encabezados del producto
            tabla.addCell(idPro);
            tabla.addCell(codPro);
            tabla.addCell(desPro);
            tabla.addCell(prec);
            tabla.addCell(cantPro);

// Creando la consulta y la agregamos a un String
            String consultaProducto = "SELECT id, codigo, descripcion, precio_venta, cantidad FROM productos";
            try {
                con = cn.getConexion(); // Inicializamos la conexion
                ps = con.prepareStatement(consultaProducto);
                rs = ps.executeQuery();
                while (rs.next()) {  // Cambiado a while en lugar de if
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    tabla.addCell(rs.getString(4));
                    tabla.addCell(rs.getString(5));
                }
                documento.add(tabla);
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();

            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
    
    /* ********************************************************************
    * metodo para crear reportes de las compras registradas en el sistema
    **********************************************************************/ 
    public void generarReporteCompras() {
        Document documento = new Document();
        try {
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Compras.pdf"));
            documento.open();

            // Imagen del encabezado
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/img/header2.png");
            header.scaleToFit(128, 128);
            header.setAlignment(Element.ALIGN_LEFT);

            // Formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.add("Reporte creado por SalesFusionV2.2\n © DevSandoval\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.setAlignment(Element.ALIGN_CENTER);
            parrafo.add("Reporte de compras\n\n");

            // Crear una tabla con dos columnas
            PdfPTable tablaEncabezado = new PdfPTable(2);
            tablaEncabezado.setWidthPercentage(100); // ANcho de la tabla al 100%
            tablaEncabezado.setWidths(new int[]{1, 3}); // Proporcion del ancho de las columnas

            // Agregar la imagen a la primera celda
            PdfPCell celdaImagen = new PdfPCell(header);
            celdaImagen.setBorder(Rectangle.NO_BORDER); // Sin bordes
            tablaEncabezado.addCell(celdaImagen);

            // Agregar el parrafo ala segunda celda
            PdfPCell celdaParrafo = new PdfPCell(parrafo);
            celdaParrafo.setBorder(Rectangle.NO_BORDER); // Sin bordes
            celdaParrafo.setVerticalAlignment(Element.ALIGN_MIDDLE); // Alineacion vertical al medio
            tablaEncabezado.addCell(celdaParrafo);

            // Agregar la tabla al documento
            documento.add(tablaEncabezado);

            // Creacion de la tabla de datos
            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100); // Ancho de la tabla al 100%
            float[] tamanioCompras = new float[]{25f, 25f, 25f, 25f};
            tabla.setWidths(tamanioCompras);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla.getDefaultCell().setBorder(0);

            // Encabezado de compras
            PdfPCell idCom = new PdfPCell(new Phrase("Id", negrita));
            PdfPCell idprCom = new PdfPCell(new Phrase("Id_Proveedor", negrita));
            PdfPCell totalCom = new PdfPCell(new Phrase("Total", negrita));
            PdfPCell fecha = new PdfPCell(new Phrase("Fecha", negrita));

            // Eliminar bordes de los encabezados
            idCom.setBorder(0);
            idprCom.setBorder(0);
            totalCom.setBorder(0);
            fecha.setBorder(0);

            // Color de fondo del encabezado
            idCom.setBackgroundColor(BaseColor.DARK_GRAY);
            idprCom.setBackgroundColor(BaseColor.DARK_GRAY);
            totalCom.setBackgroundColor(BaseColor.DARK_GRAY);
            fecha.setBackgroundColor(BaseColor.DARK_GRAY);

            // Agregar los encabezados de la compra
            tabla.addCell(idCom);
            tabla.addCell(idprCom);
            tabla.addCell(totalCom);
            tabla.addCell(fecha);

            // Creando la consulta y la agregamos a un String
            String consultaProducto = "SELECT id, id_proveedor, total, fecha FROM compras";
            try {
                con = cn.getConexion(); // Inicializamos la conexion
                ps = con.prepareStatement(consultaProducto);
                rs = ps.executeQuery();
                while (rs.next()) {  // Cambiado a while en lugar de if
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    tabla.addCell(rs.getString(4));
                }
                documento.add(tabla);
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();

            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
    
     /* ********************************************************************
    * metodo para crear reportes de los usuarios registrados en el sistema
    **********************************************************************/ 
    public void generarReporteUsuarios() {
        Document documento = new Document();
        try {
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Usuarios.pdf"));
            documento.open();

            // Imagen del encabezado
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/img/header2.png");
            header.scaleToFit(128, 128);
            header.setAlignment(Element.ALIGN_LEFT);

            // Formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.add("Reporte creado por SalesFusionV2.2\n © DevSandoval\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.setAlignment(Element.ALIGN_CENTER);
            parrafo.add("Reporte de usuarios\n\n");

            // Crear una tabla con dos columnas
            PdfPTable tablaEncabezado = new PdfPTable(2);
            tablaEncabezado.setWidthPercentage(100); // ANcho de la tabla al 100%
            tablaEncabezado.setWidths(new int[]{1, 3}); // Proporcion del ancho de las columnas

            // Agregar la imagen a la primera celda
            PdfPCell celdaImagen = new PdfPCell(header);
            celdaImagen.setBorder(Rectangle.NO_BORDER); // Sin bordes
            tablaEncabezado.addCell(celdaImagen);

            // Agregar el parrafo ala segunda celda
            PdfPCell celdaParrafo = new PdfPCell(parrafo);
            celdaParrafo.setBorder(Rectangle.NO_BORDER); // Sin bordes
            celdaParrafo.setVerticalAlignment(Element.ALIGN_MIDDLE); // Alineacion vertical al medio
            tablaEncabezado.addCell(celdaParrafo);

            // Agregar la tabla al documento
            documento.add(tablaEncabezado);

            // Creacion de la tabla de datos
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100); // Ancho de la tabla al 100%
            float[] tamanioUsuarios = new float[]{20f, 20f, 20f, 20f, 20f};
            tabla.setWidths(tamanioUsuarios);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla.getDefaultCell().setBorder(0);

            // Encabezado de compras
            PdfPCell idUs = new PdfPCell(new Phrase("Id", negrita));
            PdfPCell usuaUs = new PdfPCell(new Phrase("Usuario", negrita));
            PdfPCell nombreUs = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cajaUs = new PdfPCell(new Phrase("Caja", negrita));
            PdfPCell rolUs = new PdfPCell(new Phrase("Rol", negrita));

            // Eliminar bordes de los encabezados
            idUs.setBorder(0);
            usuaUs.setBorder(0);
            nombreUs.setBorder(0);
            cajaUs.setBorder(0);
            rolUs.setBorder(0);

            // Color de fondo del encabezado
            idUs.setBackgroundColor(BaseColor.DARK_GRAY);
            usuaUs.setBackgroundColor(BaseColor.DARK_GRAY);
            nombreUs.setBackgroundColor(BaseColor.DARK_GRAY);
            cajaUs.setBackgroundColor(BaseColor.DARK_GRAY);
            rolUs.setBackgroundColor(BaseColor.DARK_GRAY);

            // Agregar los encabezados de la compra
            tabla.addCell(idUs);
            tabla.addCell(usuaUs);
            tabla.addCell(nombreUs);
            tabla.addCell(cajaUs);
            tabla.addCell(rolUs);

            // Creando la consulta y la agregamos a un String
            String consultaUsuario = "SELECT id, usuario, nombre, caja, rol FROM usuarios";
            try {
                con = cn.getConexion(); // Inicializamos la conexion
                ps = con.prepareStatement(consultaUsuario);
                rs = ps.executeQuery();
                while (rs.next()) {  // Cambiado a while en lugar de if
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    tabla.addCell(rs.getString(4));
                    tabla.addCell(rs.getString(5));
                }
                documento.add(tabla);
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();

            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
    
     /* ********************************************************************
    * metodo para crear reportes de los proveedores registrados en el sistema
    **********************************************************************/ 
    public void generarReporteProveedor() {
        Document documento = new Document();
        try {
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Proveedor.pdf"));
            documento.open();

            // Imagen del encabezado
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/img/header2.png");
            header.scaleToFit(128, 128);
            header.setAlignment(Element.ALIGN_LEFT);

            // Formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.add("Reporte creado por SalesFusionV2.2\n © DevSandoval\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.setAlignment(Element.ALIGN_CENTER);
            parrafo.add("Reporte de Proveedores\n\n");

            // Crear una tabla con dos columnas
            PdfPTable tablaEncabezado = new PdfPTable(2);
            tablaEncabezado.setWidthPercentage(100); // ANcho de la tabla al 100%
            tablaEncabezado.setWidths(new int[]{1, 3}); // Proporcion del ancho de las columnas

            // Agregar la imagen a la primera celda
            PdfPCell celdaImagen = new PdfPCell(header);
            celdaImagen.setBorder(Rectangle.NO_BORDER); // Sin bordes
            tablaEncabezado.addCell(celdaImagen);

            // Agregar el parrafo ala segunda celda
            PdfPCell celdaParrafo = new PdfPCell(parrafo);
            celdaParrafo.setBorder(Rectangle.NO_BORDER); // Sin bordes
            celdaParrafo.setVerticalAlignment(Element.ALIGN_MIDDLE); // Alineacion vertical al medio
            tablaEncabezado.addCell(celdaParrafo);

            // Agregar la tabla al documento
            documento.add(tablaEncabezado);

            // Creacion de la tabla de datos
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100); // Ancho de la tabla al 100%
            float[] tamanioProveedores = new float[]{20f, 20f, 20f, 20f, 20f};
            tabla.setWidths(tamanioProveedores);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla.getDefaultCell().setBorder(0);

            // Encabezado de compras
            PdfPCell idProv = new PdfPCell(new Phrase("Id", negrita));
            PdfPCell rfcProv = new PdfPCell(new Phrase("Rfc", negrita));
            PdfPCell proProv = new PdfPCell(new Phrase("Proveedor", negrita));
            PdfPCell telProv = new PdfPCell(new Phrase("Telefono", negrita));
            PdfPCell direProv = new PdfPCell(new Phrase("Direccion", negrita));

            // Eliminar bordes de los encabezados
            idProv.setBorder(0);
            rfcProv.setBorder(0);
            proProv.setBorder(0);
            telProv.setBorder(0);
            direProv.setBorder(0);

            // Color de fondo del encabezado
            idProv.setBackgroundColor(BaseColor.DARK_GRAY);
            rfcProv.setBackgroundColor(BaseColor.DARK_GRAY);
            proProv.setBackgroundColor(BaseColor.DARK_GRAY);
            telProv.setBackgroundColor(BaseColor.DARK_GRAY);
            direProv.setBackgroundColor(BaseColor.DARK_GRAY);

            // Agregar los encabezados de la compra
            tabla.addCell(idProv);
            tabla.addCell(rfcProv);
            tabla.addCell(proProv);
            tabla.addCell(telProv);
            tabla.addCell(direProv);

            // Creando la consulta y la agregamos a un String
            String consultaProveedor = "SELECT id, rfc, proveedor, telefono, direccion FROM proveedor";
            try {
                con = cn.getConexion(); // Inicializamos la conexion
                ps = con.prepareStatement(consultaProveedor);
                rs = ps.executeQuery();
                while (rs.next()) {  // Cambiado a while en lugar de if
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    tabla.addCell(rs.getString(4));
                    tabla.addCell(rs.getString(5));
                }
                documento.add(tabla);
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();

            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
    
    /* ********************************************************************
    * metodo para crear reportes de las categorias registradas en el sistema
    **********************************************************************/ 
    public void generarReporteCategorias() {
        Document documento = new Document();
        try {
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Categoria.pdf"));
            documento.open();

            // Imagen del encabezado
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/img/header2.png");
            header.scaleToFit(128, 128);
            header.setAlignment(Element.ALIGN_LEFT);

            // Formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.add("Reporte creado por SalesFusionV2.2\n © DevSandoval\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.setAlignment(Element.ALIGN_CENTER);
            parrafo.add("Reporte de Categorias\n\n");

            // Crear una tabla con dos columnas
            PdfPTable tablaEncabezado = new PdfPTable(2);
            tablaEncabezado.setWidthPercentage(100); // ANcho de la tabla al 100%
            tablaEncabezado.setWidths(new int[]{1, 3}); // Proporcion del ancho de las columnas

            // Agregar la imagen a la primera celda
            PdfPCell celdaImagen = new PdfPCell(header);
            celdaImagen.setBorder(Rectangle.NO_BORDER); // Sin bordes
            tablaEncabezado.addCell(celdaImagen);

            // Agregar el parrafo ala segunda celda
            PdfPCell celdaParrafo = new PdfPCell(parrafo);
            celdaParrafo.setBorder(Rectangle.NO_BORDER); // Sin bordes
            celdaParrafo.setVerticalAlignment(Element.ALIGN_MIDDLE); // Alineacion vertical al medio
            tablaEncabezado.addCell(celdaParrafo);

            // Agregar la tabla al documento
            documento.add(tablaEncabezado);

            // Creacion de la tabla de datos
            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100); // Ancho de la tabla al 100%
            float[] tamanioProveedores = new float[]{33f, 33.33f, 33.33f};
            tabla.setWidths(tamanioProveedores);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla.getDefaultCell().setBorder(0);

            // Encabezado de categorias
            PdfPCell idCat = new PdfPCell(new Phrase("Id", negrita));
            PdfPCell cat = new PdfPCell(new Phrase("Categoria", negrita));
            PdfPCell estCat = new PdfPCell(new Phrase("Estado", negrita));

            // Eliminar bordes de los encabezados
            idCat.setBorder(0);
            cat.setBorder(0);
            estCat.setBorder(0);

            // Color de fondo del encabezado
            idCat.setBackgroundColor(BaseColor.DARK_GRAY);
            cat.setBackgroundColor(BaseColor.DARK_GRAY);
            estCat.setBackgroundColor(BaseColor.DARK_GRAY);
            

            // Agregar los encabezados de la categoria
            tabla.addCell(idCat);
            tabla.addCell(cat);
            tabla.addCell(estCat);
           

            // Creando la consulta y la agregamos a un String
            String consultaCategoria = "SELECT id, categoria, estado FROM categorias";
            try {
                con = cn.getConexion(); // Inicializamos la conexion
                ps = con.prepareStatement(consultaCategoria);
                rs = ps.executeQuery();
                while (rs.next()) {  // Cambiado a while en lugar de if
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    
                }
                documento.add(tabla);
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();

            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
    
    /* ********************************************************************
    * metodo para crear reportes de los proveedores registrados en el sistema
    **********************************************************************/ 
    public void generarReporteMedidas() {
        Document documento = new Document();
        try {
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Medidas.pdf"));
            documento.open();

            // Imagen del encabezado
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/img/header2.png");
            header.scaleToFit(128, 128);
            header.setAlignment(Element.ALIGN_LEFT);

            // Formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.add("Reporte creado por SalesFusionV2.2\n © DevSandoval\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.setAlignment(Element.ALIGN_CENTER);
            parrafo.add("Reporte de Medidas\n\n");

            // Crear una tabla con dos columnas
            PdfPTable tablaEncabezado = new PdfPTable(2);
            tablaEncabezado.setWidthPercentage(100); // ANcho de la tabla al 100%
            tablaEncabezado.setWidths(new int[]{1, 3}); // Proporcion del ancho de las columnas

            // Agregar la imagen a la primera celda
            PdfPCell celdaImagen = new PdfPCell(header);
            celdaImagen.setBorder(Rectangle.NO_BORDER); // Sin bordes
            tablaEncabezado.addCell(celdaImagen);

            // Agregar el parrafo ala segunda celda
            PdfPCell celdaParrafo = new PdfPCell(parrafo);
            celdaParrafo.setBorder(Rectangle.NO_BORDER); // Sin bordes
            celdaParrafo.setVerticalAlignment(Element.ALIGN_MIDDLE); // Alineacion vertical al medio
            tablaEncabezado.addCell(celdaParrafo);

            // Agregar la tabla al documento
            documento.add(tablaEncabezado);

            // Creacion de la tabla de datos
            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100); // Ancho de la tabla al 100%
            float[] tamanioMedidas = new float[]{25f, 25f, 25f, 25f};
            tabla.setWidths(tamanioMedidas);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla.getDefaultCell().setBorder(0);

            // Encabezado de medidas
            PdfPCell idMed = new PdfPCell(new Phrase("Id", negrita));
            PdfPCell med = new PdfPCell(new Phrase("Medida", negrita));
            PdfPCell nomcMed = new PdfPCell(new Phrase("Nombre_Corto", negrita));
            PdfPCell esMed = new PdfPCell(new Phrase("Estado", negrita));

            // Eliminar bordes de los encabezados
            idMed.setBorder(0);
            med.setBorder(0);
            nomcMed.setBorder(0);
            esMed.setBorder(0);

            // Color de fondo del encabezado
            idMed.setBackgroundColor(BaseColor.DARK_GRAY);
            med.setBackgroundColor(BaseColor.DARK_GRAY);
            nomcMed.setBackgroundColor(BaseColor.DARK_GRAY);
            esMed.setBackgroundColor(BaseColor.DARK_GRAY);
   

            // Agregar los encabezados de la medida
            tabla.addCell(idMed);
            tabla.addCell(med);
            tabla.addCell(nomcMed);
            tabla.addCell(esMed);

            // Creando la consulta y la agregamos a un String
            String consultaMedidas = "SELECT id, medida, nombre_corto, estado FROM medidas";
            try {
                con = cn.getConexion(); // Inicializamos la conexion
                ps = con.prepareStatement(consultaMedidas);
                rs = ps.executeQuery();
                while (rs.next()) {  // Cambiado a while en lugar de if
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    tabla.addCell(rs.getString(4));
                }
                documento.add(tabla);
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();

            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
    
}