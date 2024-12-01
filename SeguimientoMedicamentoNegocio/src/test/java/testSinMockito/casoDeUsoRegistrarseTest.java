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
        // Configuración inicial para las pruebas
        conexion = new Conexion();
        em = conexion.abrir();
        usuarioDAO = new UsuarioDAO(conexion);
        casoDeUsoRegistrarse = new CasoDeUsoRegistrarse();

        // Limpiar la base de datos antes de las pruebas
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Usuario").executeUpdate();
        em.getTransaction().commit();
    }

    @AfterAll
    static void tearDown() {
        // Limpieza de datos después de todas las pruebas
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Usuario").executeUpdate();
        em.getTransaction().commit();
        if (em.isOpen()) {
            em.close(); // Cerrar la conexión con la base de datos
        }
    }

    @Test
    void testRegistrarUsuarioExitoso() {
        try {
            // Datos válidos para registro
            UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123", 101);
            
            // Intentar registrar el usuario
            boolean resultado = casoDeUsoRegistrarse.registrarse(usuarioDTO);
            
            // Verificar que el usuario se haya registrado correctamente
            assertTrue(resultado);
        } catch (CasoDeUsoRegistrarseException ex) {
            Logger.getLogger(casoDeUsoRegistrarseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Test
//void testRegistrarUsuarioConUsuarioExistente() {
//    try {
//        // Crear un usuario en la base de datos
//        UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTest", "test123", 101);
//        
//        // Primero lo registramos
//        casoDeUsoRegistrarse.registrarse(usuarioDTO); 
//        
//        // Intentar registrar el mismo usuario nuevamente
//        casoDeUsoRegistrarse.registrarse(usuarioDTO);
//        fail("Se esperaba una excepción de tipo CasoDeUsoRegistrarseException al intentar registrar un usuario existente.");
//        
//    } catch (CasoDeUsoRegistrarseException ex) {
//        Logger.getLogger(casoDeUsoRegistrarseTest.class.getName()).log(Level.INFO, "Prueba exitosa: " + ex.getMessage());
//    }
//}
}
