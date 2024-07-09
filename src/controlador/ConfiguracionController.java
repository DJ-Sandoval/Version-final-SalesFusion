
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Configuracion;
import modelo.UsuarioDao;
import vista.PanelAdmin;

/**
 *
 * @author Jose
 */
public class ConfiguracionController implements ActionListener {
    private Configuracion cof;
    private UsuarioDao Cdao;
    private PanelAdmin views;

    public ConfiguracionController(Configuracion cof, UsuarioDao Cdao, PanelAdmin views) {
        this.cof = cof;
        this.Cdao = Cdao;
        this.views = views;
        cof = Cdao.getConfig();
        views.txtIdEmpresa.setText(""+ cof.getId());
        views.txtRfcEmpresa.setText(cof.getRfc());
        views.txtNombreEmpresa.setText(cof.getNombre());
        views.txtTelefonoEmpresa.setText(cof.getTelefono());
        views.txtDireccionEmpresa.setText(cof.getDireccion());
        views.txtMensaje.setText(cof.getMensaje());
        views.txtCodigoPostalEmpresa.setText(cof.getCodigoPostal());
        this.views.btnModificarEmpresa.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnModificarEmpresa) {
            if (views.txtIdEmpresa.getText().equals("") || 
                    views.txtRfcEmpresa.getText().equals("") || views.txtNombreEmpresa.getText().equals("") ||
                    views.txtTelefonoEmpresa.getText().equals("") 
                    || views.txtDireccionEmpresa.getText().equals("")
                    ||views.txtMensaje.getText().equals("") || views.txtCodigoPostalEmpresa.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "NO DEJES ESPACIOS EN BLANCO", "Campo vacio", JOptionPane.INFORMATION_MESSAGE);
            } else {
                cof.setRfc(views.txtRfcEmpresa.getText());
                cof.setNombre(views.txtNombreEmpresa.getText());
                cof.setTelefono(views.txtTelefonoEmpresa.getText());
                cof.setDireccion(views.txtDireccionEmpresa.getText());
                cof.setMensaje(views.txtMensaje.getText());
                cof.setCodigoPostal(views.txtCodigoPostalEmpresa.getText());
                cof.setId(Integer.parseInt(views.txtIdEmpresa.getText()));
                boolean respuesta = Cdao.modificar(cof);
                if (respuesta) {
                    JOptionPane.showMessageDialog(null, "Datos de empresa configurados correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo modificar los datos de empresa", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    
    
    
    
}
