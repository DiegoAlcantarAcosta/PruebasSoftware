
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testSinMockito;

import casoDeUsoProximaDosis.CasoDeUsoProximaDosis;
import casoDeUsoProximaDosis.CasoDeUsoProximaDosisException;
import conexionEM.Conexion;
import entidades.Medicamento;
import entidades.Registro;
import entidades.Usuario;
import java.util.Calendar;
import java.util.Date;
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
import persistencia.RegistroDAO;
import persistencia.UsuarioDAO;

/**
 *
 * @author USER
 */
public class casoDeUsoProximaDosisTest {

    private static EntityManager em;
    private static Conexion conexion;
    private static UsuarioDAO usuarioDAO;
    private static MedicamentoDAO medicamentoDAO;
    private static RegistroDAO registroDAO;
    private static CasoDeUsoProximaDosis casoDeUsoProximaDosis;
    private static Usuario usuario;
    private static Medicamento medicamento;
    private static Registro registro;

    @BeforeAll
    static void setUp() {
        conexion = new Conexion();
        em = conexion.abrir();
        usuarioDAO = new UsuarioDAO(conexion);
        medicamentoDAO = new MedicamentoDAO(conexion);
        registroDAO = new RegistroDAO(conexion);
        casoDeUsoProximaDosis = new CasoDeUsoProximaDosis();

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Registro").executeUpdate();
        em.createQuery("DELETE FROM Medicamento").executeUpdate();
        em.createQuery("DELETE FROM Usuario").executeUpdate();

        // Crear usuario para las pruebas
        Usuario usuarioCreado = new Usuario(101, "usuarioTest", "test123");
        em.persist(usuarioCreado);

        medicamento = new Medicamento(123, "Aspirina", 2.5, "Oral", 1);
        medicamento.setUsuario(usuarioCreado);
        em.persist(medicamento);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.NOVEMBER, 30, 10, 0, 0);
        registro = new Registro();
        registro.setMedicamento(medicamento);
        registro.setHoraConsumo(calendar.getTime());
        em.persist(registro);

        em.getTransaction().commit();
        usuario = usuarioCreado;
    }

    @AfterAll
    static void tearDown() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Registro").executeUpdate();
        em.createQuery("DELETE FROM Medicamento").executeUpdate();
        em.createQuery("DELETE FROM Usuario").executeUpdate();
        em.getTransaction().commit();
        if (em.isOpen()) {
            em.close();
        }
    }

    @Test
    void testConsultarProximaDosis(){
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2024, Calendar.NOVEMBER, 30, 12, 00, 0); 
            calendar.set(Calendar.MILLISECOND, 0); 
            Date expectedProximaDosis = calendar.getTime();
            
            Date actualProximaDosis = casoDeUsoProximaDosis.consultarUltimaToma(medicamento.getCodigo(), usuario.getCodigo());  // Suponiendo que los códigos son correctos
            calendar.setTime(actualProximaDosis);
            calendar.set(Calendar.MILLISECOND, 0);
            actualProximaDosis=calendar.getTime();
            
            assertEquals(expectedProximaDosis.getTime(), actualProximaDosis.getTime(), "Las fechas no coinciden");
        } catch (CasoDeUsoProximaDosisException ex) {
            Logger.getLogger(casoDeUsoProximaDosisTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testConsultarProximaDosisMedicamentoNoExistente(){
        try {
            Date proximaDosis = casoDeUsoProximaDosis.consultarUltimaToma(9999, usuario.getCodigo());
            
            assertNull(proximaDosis);
        } catch (CasoDeUsoProximaDosisException ex) {
            Logger.getLogger(casoDeUsoProximaDosisTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testConsultarProximaDosisConUsuarioIncorrecto(){
        try {
            Date proximaDosis = casoDeUsoProximaDosis.consultarUltimaToma(medicamento.getCodigo(), 9999);
            
            assertNull(proximaDosis);
        } catch (CasoDeUsoProximaDosisException ex) {
            Logger.getLogger(casoDeUsoProximaDosisTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testConsultarProximaDosisConErrorDePersistencia() {

        assertDoesNotThrow(() -> casoDeUsoProximaDosis.consultarUltimaToma(medicamento.getCodigo(), usuario.getCodigo()));
    }
}