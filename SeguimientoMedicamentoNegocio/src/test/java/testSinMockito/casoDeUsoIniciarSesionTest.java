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
        
        conexion = new Conexion();
        em = conexion.abrir();
        usuarioDAO = new UsuarioDAO(conexion);
        casoDeUsoIniciarSesion = new CasoDeUsoIniciarSesion();

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Usuario").executeUpdate();

        Usuario usuarioCreado = new Usuario(101, "usuarioTest", "test123");
        em.persist(usuarioCreado);

        em.getTransaction().commit();
        usuario = usuarioCreado;
    }

    @AfterAll
    static void tearDown() {
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
            UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123", 101);
            
            int resultado = casoDeUsoIniciarSesion.iniciarSesion(usuarioDTO);
            
            assertEquals(101, resultado);
        } catch (CasoDeUsoIniciarSesionException ex) {
            Logger.getLogger(casoDeUsoIniciarSesionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testIniciarSesionConContrasenaIncorrecta(){
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "incorrecta", 101);
            
            int resultado = casoDeUsoIniciarSesion.iniciarSesion(usuarioDTO);
            
            assertEquals(-1, resultado);
        } catch (CasoDeUsoIniciarSesionException ex) {
            Logger.getLogger(casoDeUsoIniciarSesionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testIniciarSesionConUsuarioNoExistente(){
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioInvalido", "test123", 999);
            
            int resultado = casoDeUsoIniciarSesion.iniciarSesion(usuarioDTO);
            
            assertEquals(-1, resultado);
        } catch (CasoDeUsoIniciarSesionException ex) {
            Logger.getLogger(casoDeUsoIniciarSesionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testIniciarSesionConExcepcionDePersistencia(){
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123", 101);
            
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Usuario").executeUpdate();
            em.getTransaction().commit();
            
            int resultado = casoDeUsoIniciarSesion.iniciarSesion(usuarioDTO);
            
            assertEquals(-1, resultado); 
        } catch (CasoDeUsoIniciarSesionException ex) {
            Logger.getLogger(casoDeUsoIniciarSesionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}