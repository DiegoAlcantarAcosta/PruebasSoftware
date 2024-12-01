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
        conexion = new Conexion();
        em = conexion.abrir();
        usuarioDAO = new UsuarioDAO(conexion);
        medicamentoDAO = new MedicamentoDAO(conexion);
        casoDeUsoAgregar = new CasoDeUsoAgregar();

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
    void testAgregarMedicamentoExitoso() throws CasoDeUsoAgregarException {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123",101);
            MedicamentoDTO medicamentoDTO = new MedicamentoDTO(1, "Aspirina", 2.5, "Oral", 1);
            boolean resultado = casoDeUsoAgregar.AgregarMedicamento(usuarioDTO, medicamentoDTO);
            
            assertTrue(resultado);

            Medicamento encontrado = medicamentoDAO.obtener(1, 101);
            assertNotNull(encontrado);
            assertEquals("Aspirina", encontrado.getNombre());
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoAgregarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testAgregarMedicamentoUsuarioNoExistente() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioInvalido", "clave123",999);
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(2, "Ibuprofeno", 3.0, "Oral", 1);

        assertThrows(CasoDeUsoAgregarException.class, () -> {
            casoDeUsoAgregar.AgregarMedicamento(usuarioDTO, medicamentoDTO);
        });
    }

    @Test
    void testAgregarMedicamentoDuplicado() throws PersistenciaExcepcion {
        Medicamento medicamento = new Medicamento(3, "Paracetamol", 1.0, "Oral", 1);
        medicamento.setUsuario(usuario);
        medicamentoDAO.agregar(medicamento);

        UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123",101);
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(3, "Paracetamol", 1.0, "Oral", 1);

        assertThrows(CasoDeUsoAgregarException.class, () -> {
            casoDeUsoAgregar.AgregarMedicamento(usuarioDTO, medicamentoDTO);
        });
    }
}