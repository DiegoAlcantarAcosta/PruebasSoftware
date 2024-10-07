package casoDeUsoIniciarSesion;

import conversor.Conversor;
import dto.UsuarioDTO;
import excepciones.PersistenciaExcepcion;
import interfaces.IUsuarioDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.UsuarioDAO;

public class CasoDeUsoIniciarSesion implements ICasoDeUsoIniciarSesion{

    private IUsuarioDAO usuarioDAO;
    private Conversor conversor;
    
    public CasoDeUsoIniciarSesion() {
        this.usuarioDAO = new UsuarioDAO();
        conversor = new Conversor();
        
    }
    
    @Override
    public int iniciarSesion(UsuarioDTO usuarioDTO) throws CasoDeUsoIniciarSesionException {
        try {
            return usuarioDAO.iniciarSesion(conversor.usuarioDTOaEntity(usuarioDTO));
                
            
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(CasoDeUsoIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return -1;
    }

    
    
}
