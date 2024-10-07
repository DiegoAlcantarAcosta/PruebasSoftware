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
    public boolean iniciarSesion(Usuario usuario) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        em.getTransaction().begin();;
        
        try {
            Usuario usuarioNuevo = em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario", Usuario.class)
                    .setParameter("nombreUsuario", usuario.getNombreUsuario())
                    .getSingleResult();

            if (usuarioNuevo != null && usuario.getContrasenia().equals(usuario.getContrasenia())) {
                em.getTransaction().commit();
                return true;
            } else {
                return false;
            }
        } catch (NoResultException e) {
            em.getTransaction().rollback();
            throw new PersistenciaExcepcion("Usuario no encontrado", e);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new PersistenciaExcepcion("Error al iniciar sesión", e);
        } finally {
            em.close();
        }
    }

}
