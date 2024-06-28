
package modelo;

/**
 *
 * @author Jose
 */
public class compras {
  // Atributos 
  private int id;
  private int id_proveedor;
  private String nombreProveedor;
  private Double total;
  private String fecha;
  
  // Constructor vacio
  public compras() {
  }
  
  // Constructor con parametros
    public compras(int id, int id_proveedor, String nombreProveedor, Double total, String fecha) {
        this.id = id;
        this.id_proveedor = id_proveedor;
        this.nombreProveedor = nombreProveedor;
        this.total = total;
        this.fecha = fecha;
    }
    
    // Metodos getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
  
}
