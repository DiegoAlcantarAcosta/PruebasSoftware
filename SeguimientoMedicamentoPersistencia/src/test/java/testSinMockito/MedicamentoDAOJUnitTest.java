/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testSinMockito;

import conexionEM.Conexion;
import entidades.Medicamento;
import entidades.Usuario;
import excepciones.PersistenciaExcepcion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
public class MedicamentoDAOJUnitTest {

    private static EntityManager em;
    private static Conexion conexion;
    private static MedicamentoDAO medicamentoDAO;
    private static Usuario usuario;

    @BeforeAll
    static void setUp() throws PersistenciaExcepcion {
        conexion = new Conexion();
        em = conexion.abrir();
        medicamentoDAO = new MedicamentoDAO(conexion);

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
    void testAgregarMedicamento() throws PersistenciaExcepcion {
        Medicamento medicamento = new Medicamento(123, "Aspirina", 2.5, "Oral", 1);
        medicamento.setUsuario(usuario);

        Medicamento medicamentoGuardado = medicamentoDAO.agregar(medicamento);

        assertNotNull(medicamentoGuardado);
        assertNotNull(medicamentoGuardado.getId());
        assertEquals("Aspirina", medicamentoGuardado.getNombre());
    }

    @Test
    void testObtenerMedicamento() throws PersistenciaExcepcion {
        Medicamento medicamento = new Medicamento();
        medicamento.setCodigo(123);
        medicamento.setNombre("Ibuprofeno");
        medicamento.setFrecuencia(2.0);
        medicamento.setTipoConsumo("Oral");
        medicamento.setCantidad(1);
        medicamento.setUsuario(usuario);

        medicamentoDAO.agregar(medicamento);

        Medicamento encontrado = medicamentoDAO.obtener(123, 101);

        assertNotNull(encontrado);
        assertEquals(123, encontrado.getCodigo());
        assertEquals("Ibuprofeno", encontrado.getNombre());
    }

    @Test
    void testObtenerMedicamentoNoExistente() {
        int codigoInvalido = 9999;
        int codigoUsuario = 1;

        PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class, () -> {
            medicamentoDAO.obtener(codigoInvalido, codigoUsuario);
        });

        assertEquals("No se encontró el medicamento", exception.getMessage());
    }

    @Test
    void testEditarMedicamento() throws PersistenciaExcepcion {
        Medicamento medicamento = new Medicamento();
        medicamento.setCodigo(124);
        medicamento.setNombre("Paracetamol");
        medicamento.setFrecuencia(3.0);
        medicamento.setTipoConsumo("Oral");
        medicamento.setCantidad(1);
        medicamento.setUsuario(usuario);

        Medicamento registrado = medicamentoDAO.agregar(medicamento);
        registrado.setNombre("Aspirina");
        registrado.setFrecuencia(2.0);

        boolean resultado = medicamentoDAO.editar(registrado, 101);

        assertTrue(resultado);
        Medicamento editado = medicamentoDAO.obtener(124, 101);
        assertEquals("Aspirina", editado.getNombre());
        assertEquals(2.0, editado.getFrecuencia());
    }

    @Test
    void testEliminarMedicamento() throws PersistenciaExcepcion {
        Medicamento medicamento = new Medicamento();
        medicamento.setCodigo(122);
        medicamento.setNombre("Paracetamol");
        medicamento.setFrecuencia(3.0);
        medicamento.setTipoConsumo("Oral");
        medicamento.setCantidad(1);
        medicamento.setUsuario(usuario);

        Medicamento registrado = medicamentoDAO.agregar(medicamento);

        boolean resultado = medicamentoDAO.eliminar(122, 101);

        assertTrue(resultado);

        PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class, () -> {
            medicamentoDAO.obtener(123, 1);
        });

        assertEquals("No se encontró el medicamento", exception.getMessage());
    }
}