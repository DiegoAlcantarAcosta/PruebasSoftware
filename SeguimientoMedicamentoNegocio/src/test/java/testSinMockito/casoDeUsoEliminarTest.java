/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testSinMockito;

import casoDeUsoEliminar.CasoDeUsoEliminar;
import casoDeUsoEliminar.CasoDeUsoEliminarException;
import conexionEM.Conexion;
import entidades.Medicamento;
import entidades.Usuario;
import excepciones.PersistenciaExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import persistencia.MedicamentoDAO;

/**
 *
 * @author USER
 */
public class casoDeUsoEliminarTest {

    private static EntityManager em;
    private static Conexion conexion;
    private static MedicamentoDAO medicamentoDAO;
    private static CasoDeUsoEliminar casoDeUsoEliminar;
    private static Usuario usuario;

    @BeforeAll
    static void setUp() throws PersistenciaExcepcion {
       
        conexion = new Conexion();
        em = conexion.abrir();
        medicamentoDAO = new MedicamentoDAO(conexion);
        casoDeUsoEliminar = new CasoDeUsoEliminar();

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Medicamento").executeUpdate();
        em.createQuery("DELETE FROM Usuario").executeUpdate();

        Usuario usuarioCreado = new Usuario(101, "usuarioTest", "test123");
        em.persist(usuarioCreado);

        em.getTransaction().commit();
        usuario = usuarioCreado;
    }

    @AfterAll
    static void tearDown() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Medicamento").executeUpdate();
        em.createQuery("DELETE FROM Usuario").executeUpdate();
        em.getTransaction().commit();
        if (em.isOpen()) {
            em.close();
        }
    }

    @Test
    void testEliminarMedicamentoExitoso(){
        try {
            Medicamento medicamento = new Medicamento(1, "Aspirina", 2.5, "Oral", 1);
            medicamento.setUsuario(usuario);
            medicamentoDAO.agregar(medicamento);
            
            Medicamento medicamentoExistente = medicamentoDAO.obtener(1, 101);
            assertNotNull(medicamentoExistente);
            
            boolean resultado = casoDeUsoEliminar.EliminarMedicamento(1, 101);
            assertTrue(resultado);
            
            Medicamento medicamentoEliminado = medicamentoDAO.obtener(1, 101);
            assertNull(medicamentoEliminado);
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoEliminarTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CasoDeUsoEliminarException ex) {
            Logger.getLogger(casoDeUsoEliminarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testEliminarMedicamentoNoExistente(){
        try {
            boolean resultado = casoDeUsoEliminar.EliminarMedicamento(999, 101);
            assertFalse(resultado);
        } catch (CasoDeUsoEliminarException ex) {
            Logger.getLogger(casoDeUsoEliminarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testEliminarMedicamentoConUsuarioIncorrecto(){
        try {
            Medicamento medicamento = new Medicamento(2, "Ibuprofeno", 3.0, "Oral", 1);
            medicamento.setUsuario(usuario);
            medicamentoDAO.agregar(medicamento);
            
            boolean resultado = casoDeUsoEliminar.EliminarMedicamento(2, 999); 
            assertFalse(resultado);
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoEliminarTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CasoDeUsoEliminarException ex) {
            Logger.getLogger(casoDeUsoEliminarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
