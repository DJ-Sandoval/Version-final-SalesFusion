
package modelo;

/**
 *
 * @author Jose
 */
public class Categorias {
    private int id;
    private String nombre;
    private String estado;
    
    // Constructor vacio
    public Categorias() {
        id = 0;
        nombre = "";
        estado = "";
    }
    
    // Constructor vacio
    public Categorias(int id, String nombre, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }
    
    // Getteo y Setteo

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    // Metodo toString

    @Override
    public String toString() {
        return "Categorias{" + "id=" + id + ", nombre=" + nombre + ", estado=" + estado + '}';
    }
    
    
}
