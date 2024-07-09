
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Clientes;
import modelo.ClientesDao;
import vista.PanelAdmin;
import vista.frmModificarCliente;
import vista.frmNuevoCliente;

/**
 *
 * @author Jose
 */
public class botonAddController implements ActionListener {
  Clientes cli = new Clientes();
  
  
  
  
  
  
  private final PanelAdmin  vista;  

    public botonAddController(PanelAdmin vista) {
        this.vista = vista;
        this.vista.btnScroll.addActionListener(this);
        this.vista.jMenuCliente.addActionListener(this);
    }
    
    // Metodo para controlar la accion del boton add
    @Override
    public void actionPerformed(ActionEvent e) {
        // Caso para el boton de add cliente
        if (e.getSource() == vista.jMenuCliente) {
            
           vista.jTabbedPane1.setSelectedIndex(1);
        }
    }
  
    
}
