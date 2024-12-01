/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testConMockito;

import Herramientas.Conversor;
import Herramientas.HashContra;
import casoDeUsoIniciarSesion.CasoDeUsoIniciarSesion;
import casoDeUsoIniciarSesion.CasoDeUsoIniciarSesionException;
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
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import persistencia.UsuarioDAO;

/**
 *
 * @author USER
 */
public class casoDeUsoIniciarSesionTest {

    @Mock
    private IUsuarioDAO usuarioDAO;

    @Mock
    private Conversor conversor;

    @Mock
    private HashContra hashContra;

    @InjectMocks
    private CasoDeUsoIniciarSesion casoDeUsoIniciarSesion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIniciarSesionSuccess() throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setContrasenia("password");
        Usuario usuario= new Usuario();

        when(hashContra.hashearPassword("password")).thenReturn("hashedPassword");
        when(conversor.usuarioDTOaEntity(usuarioDTO)).thenReturn(usuario);
        when(usuarioDAO.iniciarSesion(usuario)).thenReturn(1);

        int resultado = casoDeUsoIniciarSesion.iniciarSesion(usuarioDTO);

        assertEquals(1, resultado);
        verify(hashContra).hashearPassword("password");
        verify(conversor).usuarioDTOaEntity(usuarioDTO);
        verify(usuarioDAO).iniciarSesion(usuario);
    }

    @Test
    void testIniciarSesionPersistenciaExcepcion() throws Exception {

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setContrasenia("password");
        Usuario usuario= new Usuario();

        when(hashContra.hashearPassword("password")).thenReturn("hashedPassword");
        when(conversor.usuarioDTOaEntity(usuarioDTO)).thenReturn(usuario);
        when(usuarioDAO.iniciarSesion(usuario)).thenThrow(new PersistenciaExcepcion("Database error"));

        CasoDeUsoIniciarSesionException exception = assertThrows(CasoDeUsoIniciarSesionException.class, () -> {
            casoDeUsoIniciarSesion.iniciarSesion(usuarioDTO);
        });

        assertEquals("Database error", exception.getMessage());
        verify(hashContra).hashearPassword("password");
        verify(conversor).usuarioDTOaEntity(usuarioDTO);
        verify(usuarioDAO).iniciarSesion(usuario);
    }

    @Test
    void testIniciarSesionNoSuchAlgorithmException() throws Exception {
        
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setContrasenia("password");

        when(hashContra.hashearPassword("password")).thenThrow(new NoSuchAlgorithmException("Hashing error"));

        int resultado = casoDeUsoIniciarSesion.iniciarSesion(usuarioDTO);

        assertEquals(-1, resultado);
        verify(hashContra).hashearPassword("password");
        verifyNoInteractions(conversor, usuarioDAO);
    }

    @Test
    void testConfigurarCodigo() {
        assertDoesNotThrow(() -> casoDeUsoIniciarSesion.configurarCodigo());
    }
}