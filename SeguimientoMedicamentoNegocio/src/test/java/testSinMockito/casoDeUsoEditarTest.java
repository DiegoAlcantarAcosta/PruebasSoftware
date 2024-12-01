/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testSinMockito;

import casoDeUsoEditar.CasoDeUsoEditar;
import casoDeUsoEditar.CasoDeUsoEditarException;
import conexionEM.Conexion;
import dto.MedicamentoDTO;
import entidades.Medicamento;
import entidades.Usuario;
import excepciones.PersistenciaExcepcion;
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
public class casoDeUsoEditarTest {

    private static EntityManager em;
    private static Conexion conexion;
    private static MedicamentoDAO medicamentoDAO;
    private static CasoDeUsoEditar casoDeUsoEditar;
    private static Usuario usuario;

    @BeforeAll
    static void setUp() throws PersistenciaExcepcion {
        
        conexion = new Conexion();
        em = conexion.abrir();
        medicamentoDAO = new MedicamentoDAO(conexion);
        casoDeUsoEditar = new CasoDeUsoEditar();

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
    void testEditarMedicamentoExitoso() throws PersistenciaExcepcion, CasoDeUsoEditarException {
        
        Medicamento medicamento = new Medicamento(1, "Nolotil", 2.5, "Oral", 1);
        medicamento.setUsuario(usuario);
        medicamentoDAO.agregar(medicamento);

        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(1, "Eutirox", 3.0, "Oral", 2);

        boolean resultado = casoDeUsoEditar.EditarMedicamento(medicamentoDTO, 101);

        assertTrue(resultado);
        Medicamento editado = medicamentoDAO.obtener(1, 101);
        assertNotNull(editado);
        assertEquals("Eutirox", editado.getNombre());
        assertEquals(3.0, editado.getFrecuencia());
        assertEquals(2, editado.getCantidad());
    }

    @Test
    void testEditarMedicamentoNoExistente() {
        
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(999, "Paracetamol", 1.0, "Oral", 1);

        assertThrows(CasoDeUsoEditarException.class, () -> {
            casoDeUsoEditar.EditarMedicamento(medicamentoDTO, 101);
        });
    }

    @Test
    void testEditarMedicamentoUsuarioIncorrecto() throws PersistenciaExcepcion {
        
        Medicamento medicamento = new Medicamento(2, "Aspirina", 2.5, "Oral", 1);
        medicamento.setUsuario(usuario);
        medicamentoDAO.agregar(medicamento);

        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(2, "Ibuprofeno", 3.0, "Oral", 2);

        assertThrows(CasoDeUsoEditarException.class, () -> {
            casoDeUsoEditar.EditarMedicamento(medicamentoDTO, 999); 
        });
    }
}
