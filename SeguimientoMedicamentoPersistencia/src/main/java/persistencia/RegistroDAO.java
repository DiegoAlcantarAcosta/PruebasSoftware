package persistencia;

import conexionEM.Conexion;
import conexionEM.IConexion;
import entidades.Medicamento;
import entidades.Registro;
import excepciones.PersistenciaExcepcion;
import interfaces.IRegistroDAO;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class RegistroDAO implements IRegistroDAO {

    private final IConexion conexion;

    public RegistroDAO() {
        conexion = new Conexion();
    }

    @Override
    public boolean tomaDeMedicamento(Registro registro) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        em.getTransaction().begin();
        try {
            em.persist(registro);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new PersistenciaExcepcion("Error al registrar la toma de medicamento", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Registro consultarUltimaToma(Medicamento medicamento) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        em.getTransaction().begin();
        
        Long medicamentoId=medicamento.getId();

        try {
            Registro registro = em.createQuery(
                    "SELECT r FROM Registro r WHERE r.medicamento.id = :medicamentoId ORDER BY r.horaConsumo DESC",
                    Registro.class)
                    .setParameter("medicamentoId", medicamentoId)
                    .setMaxResults(1)
                    .getSingleResult();

            em.getTransaction().commit();
            return registro;
        } catch (NoResultException e) {
            em.getTransaction().rollback();
            return null;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new PersistenciaExcepcion("Error al consultar la última toma de medicamento", e);
        } finally {
            em.close();
        }
    }
}

