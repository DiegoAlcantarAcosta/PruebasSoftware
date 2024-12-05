package casoDeUsoIniciarSesion;

import Herramientas.Conversor;
import Herramientas.HashContra;
import conexionEM.Conexion;
import dto.UsuarioDTO;
import excepciones.PersistenciaExcepcion;
import interfaces.IUsuarioDAO;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.UsuarioDAO;

public class CasoDeUsoIniciarSesion implements ICasoDeUsoIniciarSesion{

    private IUsuarioDAO usuarioDAO;
    private Conversor conversor;
    private HashContra hashContra;
    
    public CasoDeUsoIniciarSesion() {
        this.usuarioDAO = new UsuarioDAO(new Conexion());
        this.conversor = new Conversor();
        this.hashContra= new HashContra();
        
    }
    
    @Override
    public int iniciarSesion(UsuarioDTO usuarioDTO) throws CasoDeUsoIniciarSesionException {
        try {
            String contra=hashContra.hashearPassword(usuarioDTO.getContrasenia());
            usuarioDTO.setContrasenia(contra);
            return usuarioDAO.iniciarSesion(conversor.usuarioDTOaEntity(usuarioDTO));
                
            
        } catch (PersistenciaExcepcion ex) {
            throw new CasoDeUsoIniciarSesionException(ex.getMessage());
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CasoDeUsoIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    @Override
    public void configurarCodigo()throws CasoDeUsoIniciarSesionException{
        
    }
    
}
