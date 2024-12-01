package persistencia;

import conexionEM.Conexion;
import conexionEM.IConexion;
import entidades.Usuario;
import excepciones.PersistenciaExcepcion;
import interfaces.IUsuarioDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class UsuarioDAO implements IUsuarioDAO {

    private final IConexion conexion;

    public UsuarioDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public Usuario registrar(Usuario usuario) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            List<Usuario> usuarioConElMismoNombre = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario",
                Usuario.class)
                .setParameter("nombreUsuario", usuario.getNombreUsuario())
                .getResultList();
            em.persist(usuario);
            if(usuarioConElMismoNombre.isEmpty()){
               transaction.commit();
                return usuario; 
            }else{
                throw new PersistenciaExcepcion("Ese nombre de usuario ya está en uso");
            }
            
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaExcepcion("Error al registrar el usuario", e);
        } finally {
            em.close();
        }
    }

    @Override
    public int iniciarSesion(Usuario usuario) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Usuario usuarioNuevo = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.contrasenia = :contrasenia", Usuario.class)
                    .setParameter("nombreUsuario", usuario.getNombreUsuario())
                    .setParameter("contrasenia", usuario.getContrasenia())
                    .getSingleResult();
            transaction.commit();
            return usuarioNuevo.getCodigo();
        } catch (NoResultException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaExcepcion("Usuario o contraseña incorrectos", e);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaExcepcion("Error al iniciar sesión", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Usuario buscarUsuarioPorCodigo(int codigo) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Usuario usuarioEncontrado = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.codigo = :codigo", Usuario.class)
                    .setParameter("codigo", codigo)
                    .getSingleResult();
            transaction.commit();
            return usuarioEncontrado;
        } catch (NoResultException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaExcepcion("Usuario no encontrado con el código: " + codigo, e);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaExcepcion("Error al buscar el usuario por código", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void configurarCodigo() throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Usuario usuarioEncontrado = em.createQuery(
                    "SELECT u FROM Usuario u ORDER BY u.codigo DESC", Usuario.class)
                    .setMaxResults(1)
                    .getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaExcepcion("Error al buscar el último usuario", e);
        } finally {
            em.close();
        }
    }
}
