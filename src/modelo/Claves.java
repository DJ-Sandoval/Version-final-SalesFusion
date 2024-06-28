
package modelo;



/**
 *
 * @author Jose
 */
public class Claves {
   private int claveCompra;
   private int claveVenta;
   
   public Claves() {
   }
   
   public Claves(int claveCompra, int claveVenta) {
       this.claveCompra = claveCompra;
       this.claveVenta = claveVenta;
   }
   
   // Getter and setter

    public int getClaveCompra() {
        return claveCompra;
    }

    public void setClaveCompra(int claveCompra) {
        this.claveCompra = claveCompra;
    }

    public int getClaveVenta() {
        return claveVenta;
    }

    public void setClaveVenta(int claveVenta) {
        this.claveVenta = claveVenta;
    }
   
}
