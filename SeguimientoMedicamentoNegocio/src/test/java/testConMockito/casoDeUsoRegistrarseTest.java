/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testConMockito;

import Herramientas.Conversor;
import Herramientas.HashContra;
import casoDeUsoRegistrarse.CasoDeUsoRegistrarse;
import casoDeUsoRegistrarse.CasoDeUsoRegistrarseException;
import dto.UsuarioDTO;
import entidades.Usuario;
import excepciones.PersistenciaExcepcion;
import interfaces.IUsuarioDAO;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author USER
 */
public class casoDeUsoRegistrarseTest{

    @Mock
    private IUsuarioDAO usuarioDAO;

    @Mock
    private Conversor conversor;

    @Mock
    private HashContra hashContra;

    @InjectMocks
    private CasoDeUsoRegistrarse casoDeUsoRegistrarse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarseSuccess(){
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setNombreUsuario("testUser");
            usuarioDTO.setContrasenia("password");
            
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario("testUser");
            usuario.setContrasenia("hashedPassword");
            
            when(hashContra.hashearPassword("password")).thenReturn("hashedPassword");
            when(usuarioDAO.registrar(any(Usuario.class))).thenReturn(usuario);
            
            // Ejecución
            boolean resultado = casoDeUsoRegistrarse.registrarse(usuarioDTO);
            
            // Verificación
            assertTrue(resultado);
            verify(hashContra).hashearPassword("password");
            verify(usuarioDAO).registrar(any(Usuario.class));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(casoDeUsoRegistrarseTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoRegistrarseTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CasoDeUsoRegistrarseException ex) {
            Logger.getLogger(casoDeUsoRegistrarseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
void testRegistrarsePersistenciaExcepcion() {
    try {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombreUsuario("testUser");
        usuarioDTO.setContrasenia("password");

        when(hashContra.hashearPassword("password")).thenReturn("hashedPassword");
        when(usuarioDAO.registrar(any(Usuario.class))).thenThrow(new PersistenciaExcepcion("Database error"));

        boolean resultado = casoDeUsoRegistrarse.registrarse(usuarioDTO);

        assertFalse(resultado);
        verify(hashContra).hashearPassword("password");
        verify(usuarioDAO).registrar(any(Usuario.class));

    } catch (PersistenciaExcepcion ex) {
        Logger.getLogger(CasoDeUsoRegistrarse.class.getName()).log(Level.SEVERE, null, ex);
        fail("No debería llegar aquí, ya que la excepción debe ser manejada dentro del método");
    }   catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(casoDeUsoRegistrarseTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CasoDeUsoRegistrarseException ex) {
            Logger.getLogger(casoDeUsoRegistrarseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    @Test
    void testRegistrarseSinContraseña() {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setNombreUsuario("testUser");
            boolean resultado = casoDeUsoRegistrarse.registrarse(usuarioDTO);
            assertFalse(resultado);
        } catch (CasoDeUsoRegistrarseException ex) {
            Logger.getLogger(casoDeUsoRegistrarseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void testRegistrarseSinUsuario(){
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setContrasenia("password");
            
            boolean resultado = casoDeUsoRegistrarse.registrarse(usuarioDTO);
            
            assertFalse(resultado);
        } catch (CasoDeUsoRegistrarseException ex) {
            Logger.getLogger(casoDeUsoRegistrarseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}