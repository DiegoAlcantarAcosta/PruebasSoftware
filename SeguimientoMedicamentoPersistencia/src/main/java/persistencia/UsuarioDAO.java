package persistencia;

import conexionEM.Conexion;
import conexionEM.IConexion;
import entidades.Usuario;
import excepciones.PersistenciaExcepcion;
import interfaces.IUsuarioDAO;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UsuarioDAO implements IUsuarioDAO {

    private final IConexion conexion;

    public UsuarioDAO() {
        conexion = new Conexion();
    }

    @Override
    public boolean registrar(Usuario usuario) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        em.getTransaction().begin();

        try {
            em.persist(usuario);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new PersistenciaExcepcion("Error al registrar el usuario", e);
        } finally {
            em.close();
        }
    }

    @Override
    public int iniciarSesion(Usuario usuario) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        em.getTransaction().begin();;
        
        try {
            Usuario usuarioNuevo = em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.contrasenia = :contrasenia", Usuario.class)
                    .setParameter("nombreUsuario", usuario.getNombreUsuario())
                    .setParameter("contrasenia", usuario.getContrasenia())
                    .getSingleResult();
            return usuarioNuevo.getCodigo();

        } catch (NoResultException e) {
            em.getTransaction().rollback();
            throw new PersistenciaExcepcion("Usuario o contraseña incorrectos", e);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new PersistenciaExcepcion("Error al iniciar sesión", e);
        } finally {
            em.close();
        }
    }
    
    @Override
    public Usuario buscarUsuarioPorCodigo(int codigo) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        em.getTransaction().begin();

        try {
            Usuario usuarioEncontrado = em.createQuery("SELECT u FROM Usuario u WHERE u.codigo = :codigo", Usuario.class)
                    .setParameter("codigo", codigo)
                    .getSingleResult();
            System.out.println(usuarioEncontrado);
            em.getTransaction().commit();
            return usuarioEncontrado;

        } catch (NoResultException e) {
            em.getTransaction().rollback();
            throw new PersistenciaExcepcion("Usuario no encontrado con el código: " + codigo, e);

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new PersistenciaExcepcion("Error al buscar el usuario por código", e);

        } finally {
            em.close();
        }
    }

}
