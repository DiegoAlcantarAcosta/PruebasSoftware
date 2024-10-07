package interfaces;

import entidades.Usuario;
import excepciones.PersistenciaExcepcion;

public interface IUsuarioDAO {
    public boolean registrar(Usuario usuario) throws PersistenciaExcepcion;
    
    public boolean iniciarSesion(Usuario usuario) throws PersistenciaExcepcion;
}
