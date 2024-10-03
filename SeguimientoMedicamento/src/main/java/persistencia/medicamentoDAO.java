/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import conexionEM.Conexion;
import conexionEM.IConexion;
import entidades.Medicamento;
import interfaces.IMedicamentoDAO;
import excepciones.PersistenciaExcepcion;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author haloa
 */
public class medicamentoDAO implements IMedicamentoDAO {

    private final IConexion conexion;

    public medicamentoDAO() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregar(Medicamento medicamento) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        em.getTransaction().begin();

        try {
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
    public Medicamento obtener(String nombre) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        em.getTransaction().begin();

        try {
            Medicamento medicamento = em.createQuery("SELECT m FROM Medicamento m WHERE m.nombre = :nombre", Medicamento.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
            em.getTransaction().commit();
            return medicamento;
        } catch (NoResultException e) {
            em.getTransaction().rollback();
            throw new PersistenciaExcepcion("No se encontró ningún medicamento con el nombre: " + nombre, e);
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
            Medicamento medicamentoBuscado = em.find(Medicamento.class, 1L);
            em.merge(medicamentoBuscado);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminar(Medicamento medicamento) throws PersistenciaExcepcion {
        EntityManager em = conexion.abrir();
        em.getTransaction().begin();
        try {
            Medicamento medicamentoBuscado = em.find(Medicamento.class, medicamento.getId());

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
