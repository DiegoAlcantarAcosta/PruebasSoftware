package interfaces;

import entidades.Usuario;
import excepciones.PersistenciaExcepcion;

public interface IUsuarioDAO {
    public Usuario registrar(Usuario usuario) throws PersistenciaExcepcion;
    
    public int iniciarSesion(Usuario usuario) throws PersistenciaExcepcion;
    
    public Usuario buscarUsuarioPorCodigo(int codigo) throws PersistenciaExcepcion;
    
    public void configurarCodigo() throws PersistenciaExcepcion;
}
