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
        // Configuración inicial
        conexion = new Conexion();
        em = conexion.abrir();
        medicamentoDAO = new MedicamentoDAO(conexion);
        casoDeUsoEliminar = new CasoDeUsoEliminar();

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Medicamento").executeUpdate();
        em.createQuery("DELETE FROM Usuario").executeUpdate();

        // Crear un usuario para las pruebas
        Usuario usuarioCreado = new Usuario(101, "usuarioTest", "test123");
        em.persist(usuarioCreado);

        em.getTransaction().commit();
        usuario = usuarioCreado;
    }

    @AfterAll
    static void tearDown() {
        // Limpieza de datos
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
            // Agregar un medicamento a la base de datos
            Medicamento medicamento = new Medicamento(1, "Aspirina", 2.5, "Oral", 1);
            medicamento.setUsuario(usuario);
            medicamentoDAO.agregar(medicamento);
            
            // Verifica que el medicamento se haya agregado correctamente
            Medicamento medicamentoExistente = medicamentoDAO.obtener(1, 101);
            assertNotNull(medicamentoExistente);
            
            // Eliminar el medicamento usando el caso de uso
            boolean resultado = casoDeUsoEliminar.EliminarMedicamento(1, 101);
            assertTrue(resultado);
            
            // Verificar que el medicamento haya sido eliminado
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
            // Intentar eliminar un medicamento que no existe
            boolean resultado = casoDeUsoEliminar.EliminarMedicamento(999, 101);
            assertFalse(resultado);
        } catch (CasoDeUsoEliminarException ex) {
            Logger.getLogger(casoDeUsoEliminarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testEliminarMedicamentoConUsuarioIncorrecto(){
        try {
            // Agregar un medicamento a la base de datos
            Medicamento medicamento = new Medicamento(2, "Ibuprofeno", 3.0, "Oral", 1);
            medicamento.setUsuario(usuario);
            medicamentoDAO.agregar(medicamento);
            
            // Intentar eliminar el medicamento con un usuario incorrecto
            boolean resultado = casoDeUsoEliminar.EliminarMedicamento(2, 999);  // Usuario no válido
            assertFalse(resultado);
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoEliminarTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CasoDeUsoEliminarException ex) {
            Logger.getLogger(casoDeUsoEliminarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
