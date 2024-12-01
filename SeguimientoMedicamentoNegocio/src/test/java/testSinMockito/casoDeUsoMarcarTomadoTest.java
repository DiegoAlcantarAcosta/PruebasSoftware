/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testSinMockito;

import casoDeUsoMarcarTomado.CasoDeUsoMarcarTomado;
import casoDeUsoMarcarTomado.CasoDeUsoMarcarTomadoException;
import conexionEM.Conexion;
import dto.MedicamentoDTO;
import dto.RegistroDTO;
import dto.UsuarioDTO;
import entidades.Medicamento;
import entidades.Registro;
import entidades.Usuario;
import excepciones.PersistenciaExcepcion;
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

/**
 *
 * @author USER
 */
public class casoDeUsoMarcarTomadoTest {

    private static EntityManager em;
    private static Conexion conexion;
    private static MedicamentoDAO medicamentoDAO;
    private static RegistroDAO registroDAO;
    private static CasoDeUsoMarcarTomado casoDeUsoMarcarTomado;
    private static Usuario usuario;
    private static Medicamento medicamento;

    @BeforeAll
    static void setUp() throws PersistenciaExcepcion {
        // Configuración inicial
        conexion = new Conexion();
        em = conexion.abrir();
        medicamentoDAO = new MedicamentoDAO(conexion);
        registroDAO = new RegistroDAO(conexion);
        casoDeUsoMarcarTomado = new CasoDeUsoMarcarTomado();

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Registro").executeUpdate();
        em.createQuery("DELETE FROM Medicamento").executeUpdate();
        em.createQuery("DELETE FROM Usuario").executeUpdate();

        // Crear un usuario para las pruebas
        Usuario usuarioCreado = new Usuario(101, "usuarioTest", "test123");
        em.persist(usuarioCreado);

        // Crear un medicamento
        medicamento = new Medicamento(1, "Aspirina", 2.5, "Oral", 1);
        medicamento.setUsuario(usuarioCreado);
        em.persist(medicamento);

        em.getTransaction().commit();
        usuario = usuarioCreado;
    }

    @AfterAll
    static void tearDown() {
        // Limpieza de datos
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
    void testTomaDeMedicamentoExitoso(){
        try {
            // Crear un registro DTO con hora de consumo actual
            Date fechaOriginal = new Date();
        
        // Usar Calendar para sumar 2 horas
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaOriginal);  // Establecer la fecha original
        calendar.add(Calendar.HOUR, 2);    // Sumar 2 horas
        calendar.add(Calendar.MINUTE, 10);    // Sumar 2 horas
        
        // Obtener la nueva fec
            RegistroDTO registroDTO = new RegistroDTO();
            registroDTO.setHoraConsumo( calendar.getTime());
            
            // Crear un medicamentoDTO y un usuarioDTO
            MedicamentoDTO medicamentoDTO = new MedicamentoDTO(1, "Aspirina", 2.5, "Oral", 1);
            UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123", 101);
            
            // Simular toma de medicamento
            boolean resultado = casoDeUsoMarcarTomado.tomaDeMedicamento(registroDTO, medicamentoDTO, usuarioDTO);
            
            assertTrue(resultado);
            
            // Verificar que el registro se ha insertado correctamente
            Registro registro = registroDAO.consultarUltimaToma(medicamento);
            assertNotNull(registro);
            assertEquals(medicamento.getCodigo(), registro.getMedicamento().getCodigo());
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoMarcarTomadoTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CasoDeUsoMarcarTomadoException ex) {
            Logger.getLogger(casoDeUsoMarcarTomadoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testTomaDeMedicamentoTemprano() {
        // Crear un registro DTO con hora de consumo más temprano de lo permitido
        RegistroDTO registroDTO = new RegistroDTO();
        registroDTO.setHoraConsumo(new Date());

        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(1, "Aspirina", 2.5, "Oral", 1);
        UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123", 101);

        try {
            // Intentar tomar el medicamento demasiado pronto
            casoDeUsoMarcarTomado.tomaDeMedicamento(registroDTO, medicamentoDTO, usuarioDTO);
            fail("Se esperaba excepción por tomar el medicamento demasiado pronto");
        } catch (CasoDeUsoMarcarTomadoException ex) {
            assertTrue(ex.getMessage().contains("La siguiente toma es en"));
        }
    }

    @Test
    void testTomaDeMedicamentoTarde() {
        // Crear un registro DTO con hora de consumo más tarde de lo permitido
        RegistroDTO registroDTO = new RegistroDTO();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -3); // Simulando que se toma 3 horas tarde
        registroDTO.setHoraConsumo(calendar.getTime());

        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(1, "Aspirina", 2.5, "Oral", 1);
        UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123", 101);

        try {
            // Intentar tomar el medicamento tarde
            casoDeUsoMarcarTomado.tomaDeMedicamento(registroDTO, medicamentoDTO, usuarioDTO);
        } catch (CasoDeUsoMarcarTomadoException ex) {
            assertTrue(ex.getMessage().contains("Lo tomaste por"));
        }
    }

    @Test
    void testTomaDeMedicamentoSinRegistroAnterior() throws CasoDeUsoMarcarTomadoException {
        try {
            // Crear un registro DTO con hora de consumo actual
            RegistroDTO registroDTO = new RegistroDTO();
            registroDTO.setHoraConsumo(new Date());
            
            MedicamentoDTO medicamentoDTO = new MedicamentoDTO(1, "Aspirina", 2.5, "Oral", 1);
            UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123", 101);
            
            // Eliminar cualquier registro previo para que no haya uno anterior
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Registro").executeUpdate();
            em.getTransaction().commit();
            
            // Simular toma de medicamento sin registro previo
            boolean resultado = casoDeUsoMarcarTomado.tomaDeMedicamento(registroDTO, medicamentoDTO, usuarioDTO);
            
            assertTrue(resultado);
            
            // Verificar que el registro se ha insertado correctamente
            Registro registro = registroDAO.consultarUltimaToma(medicamento);
            assertNotNull(registro);
            assertEquals(medicamento.getCodigo(), registro.getMedicamento().getCodigo());
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoMarcarTomadoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
