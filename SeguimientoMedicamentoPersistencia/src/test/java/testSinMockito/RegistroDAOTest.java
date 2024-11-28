/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testSinMockito;

import conexionEM.Conexion;
import entidades.Medicamento;
import entidades.Registro;
import entidades.Usuario;
import excepciones.PersistenciaExcepcion;
import java.time.Instant;
import java.util.Date;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import persistencia.MedicamentoDAO;
import persistencia.RegistroDAO;

/**
 *
 * @author USER
 */
public class RegistroDAOTest {
    private static EntityManager em;
    private static Conexion conexion;
    private static RegistroDAO registroDAO;
    private static Medicamento medicamento;
    private static Usuario usuario;
    
    @BeforeAll
    static void setUp() throws PersistenciaExcepcion {
        conexion = new Conexion();
        em = conexion.abrir();
        registroDAO = new RegistroDAO(conexion);

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Medicamento").executeUpdate();
        em.createQuery("DELETE FROM Usuario").executeUpdate();
        
        Usuario usuarioCreado = new Usuario(101, "usuarioTest", "test123");
        
        em.persist(usuarioCreado);
        usuario = usuarioCreado;
        
        Medicamento medicamentoCreado=new Medicamento(101,"Paracetamol", 2, "ml", 2, usuario);
        
        em.persist(medicamentoCreado);
        em.getTransaction().commit();
        medicamento = medicamentoCreado;
    }

    @AfterAll
    static void tearDown() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Medicamento").executeUpdate();
        em.createQuery("DELETE FROM Usuario").executeUpdate();
        em.createQuery("DELETE FROM Registro").executeUpdate();
        
        em.getTransaction().commit();
        if (em.isOpen()) {
            em.close();
        }
    }

    @Test
    void testTomaDeMedicamentoExitoso() throws PersistenciaExcepcion {

        Registro registro = new Registro();
        registro.setMedicamento(medicamento);
        registro.setHoraConsumo(new Date());

        boolean resultado = registroDAO.tomaDeMedicamento(registro);

        assertTrue(resultado);
        Registro registrado = em.find(Registro.class, registro.getId());
        assertNotNull(registrado);
        assertEquals(medicamento.getId(), registrado.getMedicamento().getId());
    }

    @Test
    void testTomaDeMedicamentoSinMedicamento() {
        // Crear registro sin medicamento
        Registro registro = new Registro();

        PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class, () -> {
            registroDAO.tomaDeMedicamento(registro);
        });

        assertEquals("El medicamento no puede ser nulo.", exception.getMessage());
    }

    @Test
    void testConsultarUltimaTomaExitoso() throws PersistenciaExcepcion {
        

        Registro registro1 = new Registro();
        registro1.setMedicamento(medicamento);
        registro1.setHoraConsumo(new Date());
        Registro registro2 = new Registro();
        registro2.setMedicamento(medicamento);
        registro2.setHoraConsumo(new Date());

        em.getTransaction().begin();
        em.persist(registro1);
        em.persist(registro2);
        em.getTransaction().commit();

        Registro ultimoRegistro = registroDAO.consultarUltimaToma(medicamento);

        assertNotNull(ultimoRegistro);
        assertEquals(registro2.getHoraConsumo(), ultimoRegistro.getHoraConsumo());
    }

    @Test
    void testConsultarUltimaTomaSinRegistros() throws PersistenciaExcepcion {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Registro").executeUpdate();
        em.getTransaction().commit();

        Registro ultimoRegistro = registroDAO.consultarUltimaToma(medicamento);

        assertNull(ultimoRegistro);
    }

    @Test
    void testConsultarUltimaTomaMedicamentoNulo() {
        PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class, () -> {
            registroDAO.consultarUltimaToma(null);
        });

        assertEquals("El medicamento no puede ser nulo o tener un ID nulo.", exception.getMessage());
    }
}