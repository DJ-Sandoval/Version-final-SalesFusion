package modelo;

/**
 *
 * @author Jose
 */
public class Usuarios {
   // Atributos
   private int id;
   private String usuario;
   private String nombre;
   private String clave;
   private String caja;
   private String rol;
   private String estado;
   
   // Constructor vacio
   public Usuarios() {
       id = 0;
       usuario = "";
       nombre = "";
       clave = "";
       caja = "";
       rol = "";
       estado = "";
   }
   
   // Constructor con parametros
    public Usuarios(int id, String usuario, String nombre, String clave, String caja, String rol, String estado) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.clave = clave;
        this.caja = caja;
        this.rol = rol;
        this.estado = estado;
    }
   
    // Getteo y Setteo

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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
        return "Usuarios{" + "id=" + id + ", usuario=" + usuario + ", nombre=" + nombre + ", clave=" + clave + ", caja=" + caja + ", rol=" + rol + ", estado=" + estado + '}';
    }
    
}
