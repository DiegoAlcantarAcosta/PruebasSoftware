/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testSinMockito;

import casoDeUsoIniciarSesion.CasoDeUsoIniciarSesion;
import casoDeUsoIniciarSesion.CasoDeUsoIniciarSesionException;
import conexionEM.Conexion;
import dto.UsuarioDTO;
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
import persistencia.UsuarioDAO;

/**
 *
 * @author USER
 */
public class casoDeUsoIniciarSesionTest {

    private static EntityManager em;
    private static Conexion conexion;
    private static UsuarioDAO usuarioDAO;
    private static CasoDeUsoIniciarSesion casoDeUsoIniciarSesion;
    private static Usuario usuario;

    @BeforeAll
    static void setUp() throws PersistenciaExcepcion {
        // Configuración inicial
        conexion = new Conexion();
        em = conexion.abrir();
        usuarioDAO = new UsuarioDAO(conexion);
        casoDeUsoIniciarSesion = new CasoDeUsoIniciarSesion();

        em.getTransaction().begin();
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
        em.createQuery("DELETE FROM Usuario").executeUpdate();
        em.getTransaction().commit();
        if (em.isOpen()) {
            em.close();
        }
    }

    @Test
    void testIniciarSesionExitoso() {
        try {
            // Prepara los datos de entrada
            UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123", 101);
            
            // Iniciar sesión exitosamente
            int resultado = casoDeUsoIniciarSesion.iniciarSesion(usuarioDTO);
            
            assertEquals(101, resultado);
        } catch (CasoDeUsoIniciarSesionException ex) {
            Logger.getLogger(casoDeUsoIniciarSesionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testIniciarSesionConContrasenaIncorrecta(){
        try {
            // Proporciona una contraseña incorrecta
            UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "incorrecta", 101);
            
            // Intentar iniciar sesión con la contraseña incorrecta
            int resultado = casoDeUsoIniciarSesion.iniciarSesion(usuarioDTO);
            
            assertEquals(-1, resultado); // Se espera que devuelva -1 en caso de fallo
        } catch (CasoDeUsoIniciarSesionException ex) {
            Logger.getLogger(casoDeUsoIniciarSesionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testIniciarSesionConUsuarioNoExistente(){
        try {
            // Usuario que no existe
            UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioInvalido", "test123", 999);
            
            // Intentar iniciar sesión con un usuario no existente
            int resultado = casoDeUsoIniciarSesion.iniciarSesion(usuarioDTO);
            
            assertEquals(-1, resultado); // Se espera que devuelva -1 en caso de fallo
        } catch (CasoDeUsoIniciarSesionException ex) {
            Logger.getLogger(casoDeUsoIniciarSesionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testIniciarSesionConExcepcionDePersistencia(){
        try {
            // Forzar una excepción en el DAO
            UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123", 101);
            
            // Eliminar el usuario de la base de datos para forzar un fallo
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Usuario").executeUpdate();
            em.getTransaction().commit();
            
            // Intentar iniciar sesión con un usuario eliminado
            int resultado = casoDeUsoIniciarSesion.iniciarSesion(usuarioDTO);
            
            assertEquals(-1, resultado); // Se espera que devuelva -1 en caso de fallo
        } catch (CasoDeUsoIniciarSesionException ex) {
            Logger.getLogger(casoDeUsoIniciarSesionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}