package persistencia;

import conexionEM.Conexion;
import conexionEM.IConexion;
import entidades.Medicamento;
import entidades.Usuario;
import interfaces.IMedicamentoDAO;
import excepciones.PersistenciaExcepcion;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class MedicamentoDAO implements IMedicamentoDAO {

    private final IConexion conexion;

    public MedicamentoDAO() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregar(Medicamento medicamento) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        em.getTransaction().begin();

        try {
            Usuario usuario = em.find(Usuario.class, medicamento.getUsuario().getId());

            if (usuario == null) {
                throw new PersistenciaExcepcion("Usuario no encontrado con ID: " + medicamento.getUsuario().getId());
            }

            medicamento.setUsuario(usuario);
            em.persist(medicamento);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new PersistenciaExcepcion("Error al agregar el medicamento", e);

        } finally {
            em.close();
        }
    }

    @Override
    public Medicamento obtener(String codigo) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        em.getTransaction().begin();

        try {
            Medicamento medicamento = em.createQuery("SELECT m FROM Medicamento m WHERE m.codigo = :codigo", Medicamento.class)
                    .setParameter("codigo", codigo)
                    .getSingleResult();
            em.getTransaction().commit();
            return medicamento;
        } catch (NoResultException e) {
            em.getTransaction().rollback();
            throw new PersistenciaExcepcion("No se encontró ningún medicamento con el código: " + codigo, e);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new PersistenciaExcepcion("Error al obtener el medicamento", e);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean editar(Medicamento medicamento) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        em.getTransaction().begin();
        try {
            Medicamento medicamentoBuscado = em.createQuery("SELECT m FROM Medicamento m WHERE m.codigo = :codigo", Medicamento.class)
                    .setParameter("codigo", medicamento.getCodigo())
                    .getSingleResult();
            if (medicamentoBuscado != null) {
                em.merge(medicamento);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                throw new PersistenciaExcepcion("No se encontró el medicamento para editar.");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new PersistenciaExcepcion("Error al editar el medicamento", e);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminar(Medicamento medicamento) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        em.getTransaction().begin();
        try {
            Medicamento medicamentoBuscado = em.createQuery("SELECT m FROM Medicamento m WHERE m.codigo = :codigo", Medicamento.class)
                    .setParameter("codigo", medicamento.getCodigo())
                    .getSingleResult();

            if (medicamentoBuscado != null) {
                em.remove(medicamentoBuscado);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                throw new PersistenciaExcepcion("El medicamento no fue encontrado para eliminar.");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new PersistenciaExcepcion("Error al eliminar el medicamento", e);
        } finally {
            em.close();
        }
    }

}
