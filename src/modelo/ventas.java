
package modelo;

/**
 *
 * @author Jose
 */
public class ventas {
   // Atributos
   private int id;
   private int id_cliente;
   private String nombreCliente;
   private double total;
   private String fecha;
   
   // Constructor vacio

    public ventas() {
    }
    
    // Constructor con parametros
    public ventas(int id, int id_cliente, String nombreCliente, double total, String fecha) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.nombreCliente = nombreCliente;
        this.total = total;
        this.fecha = fecha;
    }
    
    
    //  Getter And Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
