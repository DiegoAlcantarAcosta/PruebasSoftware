package casoDeUsoRegistrarse;

import Herramientas.Conversor;
import Herramientas.HashContra;
import dto.UsuarioDTO;
import excepciones.PersistenciaExcepcion;
import interfaces.IUsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.UsuarioDAO;
import entidades.Medicamento;
import entidades.Usuario;
import java.security.NoSuchAlgorithmException;

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
            List<Medicamento> meds=new ArrayList<>();
            String contra=HashContra.hashearPassword(usuarioDTO.getContrasenia());
            usuarioDTO.setContrasenia(contra);
            Usuario usuario=new Usuario(usuarioDTO.getNombreUsuario(), usuarioDTO.getContrasenia());
            usuario.setMedicamentos(meds);
            if(usuarioDAO.registrar(usuario)){
                return true;
            }
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(CasoDeUsoRegistrarse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CasoDeUsoRegistrarse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
