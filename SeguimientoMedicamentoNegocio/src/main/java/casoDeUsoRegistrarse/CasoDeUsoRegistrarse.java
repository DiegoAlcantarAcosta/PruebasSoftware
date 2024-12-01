package casoDeUsoRegistrarse;

import Herramientas.Conversor;
import Herramientas.HashContra;
import conexionEM.Conexion;
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
    private HashContra hashContra;
    
    public CasoDeUsoRegistrarse() {
        this.usuarioDAO = new UsuarioDAO(new Conexion());
        conversor = new Conversor();
        this.hashContra= new HashContra();
    }
    
    @Override
    public boolean registrarse(UsuarioDTO usuarioDTO) throws CasoDeUsoRegistrarseException {
        try {
            List<Medicamento> meds=new ArrayList<>();
            String contra=hashContra.hashearPassword(usuarioDTO.getContrasenia());
            usuarioDTO.setContrasenia(contra);
            Usuario usuario=new Usuario(usuarioDTO.getNombreUsuario(), usuarioDTO.getContrasenia());
            usuario.setMedicamentos(meds);
            if(usuarioDAO.registrar(usuario)!=null){
                return true;
            }
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(CasoDeUsoRegistrarse.class.getName()).log(Level.SEVERE, null, ex);
            throw new CasoDeUsoRegistrarseException(ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CasoDeUsoRegistrarse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
