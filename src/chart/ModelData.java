
package chart;

/**
 *
 * @author Jose
 */
public class ModelData {
   private String categoria;
    private int cantidad;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ModelData(String categoria, int cantidad) {
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    public ModelData() {
    }
}
