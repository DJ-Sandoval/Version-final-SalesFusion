
package modelo;

/**
 *
 * @author Jose
 */
public class Medidas {
    private int id;
    private String nombre;
    private String nombre_corto;
    private String estado;
    
    // Constructor vacio

    public Medidas() {
    }
    
    
    // Constructor con parametros

    public Medidas(int id, String nombre, String nombre_corto, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.nombre_corto = nombre_corto;
        this.estado = estado;
    }
    
    // Getter and Setter

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

    public String getNombre_corto() {
        return nombre_corto;
    }

    public void setNombre_corto(String nombre_corto) {
        this.nombre_corto = nombre_corto;
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
        return "Medidas{" + "id=" + id + ", nombre=" + nombre + ", nombre_corto=" + nombre_corto + ", estado=" + estado + '}';
    }
    
}
