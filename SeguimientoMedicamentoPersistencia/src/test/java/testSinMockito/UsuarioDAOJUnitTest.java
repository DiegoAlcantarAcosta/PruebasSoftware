/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testSinMockito;

import conexionEM.Conexion;
import entidades.Usuario;
import excepciones.PersistenciaExcepcion;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import javax.persistence.*;
import java.util.*;
import persistencia.UsuarioDAO;
/**
 *
 * @author USER
 */
public class UsuarioDAOJUnitTest {
    private EntityManager em;
    private Conexion conexion;
    private UsuarioDAO usuarioDAO;
    



    @BeforeEach
    void setUp() {
        conexion = new Conexion();
        em = conexion.abrir();
        usuarioDAO = new UsuarioDAO(conexion);
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Usuario").executeUpdate();
        em.getTransaction().commit();
    }

    @AfterEach
    void tearDown() {
        if (em.isOpen()) {
            em.close();
        }
    }

   

    @Test
    void testRegistrarUsuario() throws PersistenciaExcepcion {
        Usuario usuario = new Usuario();
        usuario.setCodigo(1);
        usuario.setNombreUsuario("usuarioPrueba");
        usuario.setContrasenia("contraseña123");

        Usuario registrado = usuarioDAO.registrar(usuario);

        // Verificar que el usuario fue registrado correctamente
        assertNotNull(registrado);
        assertEquals("usuarioPrueba", registrado.getNombreUsuario());
        assertNotNull(registrado.getCodigo());
    }

    @Test
    void testIniciarSesionUsuarioCorrecto() throws PersistenciaExcepcion {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("usuarioLogin");
        usuario.setContrasenia("login123");

        // Registrar usuario primero
        usuarioDAO.registrar(usuario);

        // Iniciar sesión con los datos correctos
        int codigo = usuarioDAO.iniciarSesion(usuario);

        assertEquals(usuario.getCodigo(), codigo);
    }

    @Test
    void testIniciarSesionUsuarioIncorrecto() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("usuarioInexistente");
        usuario.setContrasenia("claveIncorrecta");

        PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class, () -> {
            usuarioDAO.iniciarSesion(usuario);
        });

        assertEquals("Usuario o contraseña incorrectos", exception.getMessage());
    }

    @Test
    void testBuscarUsuarioPorCodigo() throws PersistenciaExcepcion {
        Usuario usuario = new Usuario();
        usuario.setCodigo(101);
        usuario.setNombreUsuario("usuarioBuscar");
        usuario.setContrasenia("buscar123");

        // Registrar usuario
        Usuario registrado = usuarioDAO.registrar(usuario);

        // Buscar usuario por código
        Usuario encontrado = usuarioDAO.buscarUsuarioPorCodigo(registrado.getCodigo());

        // Verificar que el usuario fue encontrado
        assertNotNull(encontrado);
        assertEquals(registrado.getCodigo(), encontrado.getCodigo());
    }

    @Test
    void testBuscarUsuarioPorCodigoNoExistente() {
        int codigoInvalido = 9999;

        PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class, () -> {
            usuarioDAO.buscarUsuarioPorCodigo(codigoInvalido);
        });

        assertTrue(exception.getMessage().contains("Usuario no encontrado con el código: " + codigoInvalido));
    }
}
