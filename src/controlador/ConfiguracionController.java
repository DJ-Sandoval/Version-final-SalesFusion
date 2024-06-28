
package controlador;

import modelo.Configuracion;
import modelo.UsuarioDao;
import vista.PanelAdmin;

/**
 *
 * @author Jose
 */
public class ConfiguracionController {
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
    }
    
    
    
}
