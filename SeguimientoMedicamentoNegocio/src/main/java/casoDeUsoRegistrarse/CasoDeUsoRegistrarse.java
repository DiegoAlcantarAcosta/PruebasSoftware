package casoDeUsoRegistrarse;

import conversor.Conversor;
import dto.UsuarioDTO;
import excepciones.PersistenciaExcepcion;
import interfaces.IUsuarioDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.UsuarioDAO;

public class CasoDeUsoRegistrarse implements ICasoDeUsoRegistrarse{

    private IUsuarioDAO usuarioDAO;
    private Conversor conversor;
    
    public CasoDeUsoRegistrarse() {
        this.usuarioDAO = new UsuarioDAO();
        conversor = new Conversor();
        
    }
    
    @Override
    public boolean registrarse(UsuarioDTO usuarioDTO) throws CasoDeUsoRegistrarseException {
        try {
            if(usuarioDAO.registrar(conversor.usuarioDTOaEntity(usuarioDTO))){
                return true;
            }
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(CasoDeUsoRegistrarse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
