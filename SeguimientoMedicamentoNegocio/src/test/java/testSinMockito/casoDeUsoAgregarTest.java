/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testSinMockito;

import casoDeUsoAgregar.CasoDeUsoAgregar;
import casoDeUsoAgregar.CasoDeUsoAgregarException;
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
public class casoDeUsoAgregarTest {

    private static EntityManager em;
    private static Conexion conexion;
    private static UsuarioDAO usuarioDAO;
    private static MedicamentoDAO medicamentoDAO;
    private static CasoDeUsoAgregar casoDeUsoAgregar;
    private static Usuario usuario;

    @BeforeAll
    static void setUp(){
        // Configuración inicial
        conexion = new Conexion();
        em = conexion.abrir();
        usuarioDAO = new UsuarioDAO(conexion);
        medicamentoDAO = new MedicamentoDAO(conexion);
        casoDeUsoAgregar = new CasoDeUsoAgregar();

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Medicamento").executeUpdate();
        em.createQuery("DELETE FROM Usuario").executeUpdate();

        // Crea un usuario para las pruebas
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
    void testAgregarMedicamentoExitoso() throws CasoDeUsoAgregarException {
        try {
            // Prepara los DTOs
            UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123",101);
            MedicamentoDTO medicamentoDTO = new MedicamentoDTO(1, "Aspirina", 2.5, "Oral", 1);
            boolean resultado = casoDeUsoAgregar.AgregarMedicamento(usuarioDTO, medicamentoDTO);
            
            assertTrue(resultado);
            
            // Verifica que el medicamento fue agregado
            Medicamento encontrado = medicamentoDAO.obtener(1, 101);
            assertNotNull(encontrado);
            assertEquals("Aspirina", encontrado.getNombre());
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoAgregarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testAgregarMedicamentoUsuarioNoExistente() {
        // Usuario no existente
        UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioInvalido", "clave123",999);
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(2, "Ibuprofeno", 3.0, "Oral", 1);

        // Verifica que lanza la excepción
        assertThrows(CasoDeUsoAgregarException.class, () -> {
            casoDeUsoAgregar.AgregarMedicamento(usuarioDTO, medicamentoDTO);
        });
    }

    @Test
    void testAgregarMedicamentoDuplicado() throws PersistenciaExcepcion {
        // Medicamento existente en la base de datos
        Medicamento medicamento = new Medicamento(3, "Paracetamol", 1.0, "Oral", 1);
        medicamento.setUsuario(usuario);
        medicamentoDAO.agregar(medicamento);

        // Intenta agregar el mismo medicamento
        UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123",101);
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(3, "Paracetamol", 1.0, "Oral", 1);

        assertThrows(CasoDeUsoAgregarException.class, () -> {
            casoDeUsoAgregar.AgregarMedicamento(usuarioDTO, medicamentoDTO);
        });
    }
}
