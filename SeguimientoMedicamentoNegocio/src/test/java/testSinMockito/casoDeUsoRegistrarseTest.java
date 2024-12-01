/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testSinMockito;

import casoDeUsoRegistrarse.CasoDeUsoRegistrarse;
import casoDeUsoRegistrarse.CasoDeUsoRegistrarseException;
import conexionEM.Conexion;
import dto.UsuarioDTO;
import entidades.Usuario;
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
public class casoDeUsoRegistrarseTest {

    private static EntityManager em;
    private static Conexion conexion;
    private static UsuarioDAO usuarioDAO;
    private static CasoDeUsoRegistrarse casoDeUsoRegistrarse;
    private static Usuario usuario;

    @BeforeAll
    static void setUp(){
        conexion = new Conexion();
        em = conexion.abrir();
        usuarioDAO = new UsuarioDAO(conexion);
        casoDeUsoRegistrarse = new CasoDeUsoRegistrarse();

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Usuario").executeUpdate();
        em.getTransaction().commit();
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
    void testRegistrarUsuarioExitoso() {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123", 101);
            
            boolean resultado = casoDeUsoRegistrarse.registrarse(usuarioDTO);
            
            assertTrue(resultado);
        } catch (CasoDeUsoRegistrarseException ex) {
            Logger.getLogger(casoDeUsoRegistrarseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testRegistrarUsuarioConUsuarioExistente() {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123", 101);

            casoDeUsoRegistrarse.registrarse(usuarioDTO);

            casoDeUsoRegistrarse.registrarse(usuarioDTO);
            fail("Se esperaba una excepción de tipo CasoDeUsoRegistrarseException al intentar registrar un usuario existente.");

        } catch (CasoDeUsoRegistrarseException ex) {
            Logger.getLogger(casoDeUsoRegistrarseTest.class.getName()).log(Level.INFO, "Prueba exitosa: " + ex.getMessage());
        }
    }
}
