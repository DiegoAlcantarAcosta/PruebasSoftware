/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testSinMockito;

import casoDeUsoObtener.CasoDeUsoObtener;
import casoDeUsoObtener.CasoDeUsoObtenerException;
import conexionEM.Conexion;
import dto.MedicamentoDTO;
import dto.UsuarioDTO;
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
import persistencia.UsuarioDAO;

/**
 *
 * @author USER
 */
public class casoDeUsoObtenerTest {

    private static EntityManager em;
    private static Conexion conexion;
    private static UsuarioDAO usuarioDAO;
    private static MedicamentoDAO medicamentoDAO;
    private static CasoDeUsoObtener casoDeUsoObtener;
    private static Usuario usuario;

    @BeforeAll
    static void setUp() {
        conexion = new Conexion();
        em = conexion.abrir();
        usuarioDAO = new UsuarioDAO(conexion);
        medicamentoDAO = new MedicamentoDAO(conexion);
        casoDeUsoObtener = new CasoDeUsoObtener();

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
    void testObtenerMedicamentoExistente(){
        try {
            Medicamento medicamento = new Medicamento(123, "Aspirina", 2.5, "Oral", 1);
            medicamento.setUsuario(usuario);
            medicamentoDAO.agregar(medicamento);
            
            MedicamentoDTO medicamentoDTO = casoDeUsoObtener.ObtenerMedicamento(123, new UsuarioDTO("usuarioTest", "test123", 101));
            
            assertNotNull(medicamentoDTO);
            assertEquals(123, medicamentoDTO.getCodigo());
            assertEquals("Aspirina", medicamentoDTO.getNombre());
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoObtenerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CasoDeUsoObtenerException ex) {
            Logger.getLogger(casoDeUsoObtenerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testObtenerMedicamentoNoExistente(){
        try {
            MedicamentoDTO medicamentoDTO = casoDeUsoObtener.ObtenerMedicamento(9999, new UsuarioDTO("usuarioTest", "test123", 101));
            
            assertNull(medicamentoDTO);
        } catch (CasoDeUsoObtenerException ex) {
            Logger.getLogger(casoDeUsoObtenerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testObtenerMedicamentoConUsuarioIncorrecto(){
        try {
            Medicamento medicamento = new Medicamento(124, "Paracetamol", 1.0, "Oral", 1);
            medicamento.setUsuario(usuario);
            medicamentoDAO.agregar(medicamento);
            
            MedicamentoDTO medicamentoDTO = casoDeUsoObtener.ObtenerMedicamento(124, new UsuarioDTO("usuarioInvalido", "test123", 999));
            
            assertNull(medicamentoDTO);
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoObtenerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CasoDeUsoObtenerException ex) {
            Logger.getLogger(casoDeUsoObtenerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testObtenerMedicamentoConErrorDePersistencia() {
        assertDoesNotThrow(() -> casoDeUsoObtener.ObtenerMedicamento(123, new UsuarioDTO("usuarioTest", "test123", 101)));
    }
}