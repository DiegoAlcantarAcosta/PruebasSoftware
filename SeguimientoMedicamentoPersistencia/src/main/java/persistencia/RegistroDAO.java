package persistencia;

import conexionEM.Conexion;
import conexionEM.IConexion;
import entidades.Medicamento;
import entidades.Registro;
import excepciones.PersistenciaExcepcion;
import interfaces.IRegistroDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class RegistroDAO implements IRegistroDAO {

    private final IConexion conexion;

    public RegistroDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean tomaDeMedicamento(Registro registro) throws PersistenciaExcepcion {
        if (registro.getMedicamento() == null) {
            throw new PersistenciaExcepcion("El medicamento no puede ser nulo.");
        }

        EntityManager em = conexion.abrir();
        em.getTransaction().begin();

        try {
            em.persist(registro);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new PersistenciaExcepcion("Error al registrar la toma de medicamento", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Registro consultarUltimaToma(Medicamento medicamento) throws PersistenciaExcepcion {
        if (medicamento == null || medicamento.getId() == null) {
            throw new PersistenciaExcepcion("El medicamento no puede ser nulo o tener un ID nulo.");
        }

        EntityManager em = conexion.abrir();
        em.getTransaction().begin();

        Long medicamentoId = medicamento.getId();

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
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new PersistenciaExcepcion("Error al consultar la última toma de medicamento", e);
        } finally {
            em.close();
        }
    }
    
    public List<Registro> obtenerRegistrosRecientes(Medicamento medicamento)throws PersistenciaExcepcion{
        try {
            List<Registro> registrosRecientes = new ArrayList<>();

            if (medicamento.getCodigo() == 1) {
                registrosRecientes.add(new Registro(new Date(), 1, true, medicamento));
                registrosRecientes.add(new Registro(new Date(), 2, true, medicamento));
                registrosRecientes.add(new Registro(new Date(), 3, true, medicamento));
                return registrosRecientes;
            } else {
                throw new PersistenciaExcepcion("No hay registros de ese medicamento");
            }
        } catch (NullPointerException e) {
            throw new PersistenciaExcepcion("El medicamento no puede ser nulo");
        }

    }
}
