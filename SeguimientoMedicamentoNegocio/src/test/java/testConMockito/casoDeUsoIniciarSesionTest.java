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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author USER
 */
public class casoDeUsoIniciarSesionTest {

    @Mock
    private IUsuarioDAO usuarioDAOMock;

    @Mock
    private Conversor conversorMock;

    @Mock
    private HashContra hashContraMock;

    @InjectMocks
    private CasoDeUsoIniciarSesion casoDeUsoIniciarSesion;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIniciarSesionExito() throws CasoDeUsoIniciarSesionException, NoSuchAlgorithmException {
        try {
            // Preparar datos de entrada
            String username = "usuarioTest";
            String contrasena = "contrasenaTest";
            UsuarioDTO usuarioDTO = new UsuarioDTO(username, contrasena);
            
            // Simular el comportamiento de las dependencias
            when(hashContraMock.hashearPassword(contrasena)).thenReturn("hashedPassword");
            when(conversorMock.usuarioDTOaEntity(usuarioDTO)).thenReturn(new Usuario(username, "hashedPassword"));
            when(usuarioDAOMock.iniciarSesion(any())).thenReturn(1);  // Simulamos que el login es exitoso
            
            // Llamar al método
            int result = casoDeUsoIniciarSesion.iniciarSesion(usuarioDTO);
            
            // Verificar que el resultado sea el esperado
            assertEquals(1, result);
            
            // Verificar que las interacciones esperadas hayan ocurrido
            verify(hashContraMock).hashearPassword(contrasena);
            verify(conversorMock).usuarioDTOaEntity(usuarioDTO);
            verify(usuarioDAOMock).iniciarSesion(any());
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoIniciarSesionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testIniciarSesionPersistenciaExcepcion() throws CasoDeUsoIniciarSesionException, NoSuchAlgorithmException {
        try {
            // Preparar datos de entrada
            String username = "usuarioTest";
            String contrasena = "contrasenaTest";
            UsuarioDTO usuarioDTO = new UsuarioDTO(username, contrasena);
            
            // Simular una excepción en el método iniciarSesion del DAO
            when(hashContraMock.hashearPassword(contrasena)).thenReturn("hashedPassword");
            when(conversorMock.usuarioDTOaEntity(usuarioDTO)).thenReturn(new Usuario(username, "hashedPassword"));
            when(usuarioDAOMock.iniciarSesion(any())).thenThrow(new PersistenciaExcepcion("Error en la persistencia"));
            
            // Llamar al método y verificar que la excepción sea lanzada correctamente
            assertThrows(CasoDeUsoIniciarSesionException.class, () -> {
                casoDeUsoIniciarSesion.iniciarSesion(usuarioDTO);
            });
            
            verify(hashContraMock).hashearPassword(contrasena);
            verify(conversorMock).usuarioDTOaEntity(usuarioDTO);
            verify(usuarioDAOMock).iniciarSesion(any());
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoIniciarSesionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testIniciarSesionNoSuchAlgorithmException() throws CasoDeUsoIniciarSesionException {
        // Preparar datos de entrada
        String username = "usuarioTest";
        String contrasena = "contrasenaTest";
        UsuarioDTO usuarioDTO = new UsuarioDTO(username, contrasena);

        // Simular que el hasheo de la contraseña falla
        try {
            when(hashContraMock.hashearPassword(contrasena)).thenThrow(new NoSuchAlgorithmException("Algorithm not found"));

            // Llamar al método y verificar que no se lance una excepción personalizada
            int result = casoDeUsoIniciarSesion.iniciarSesion(usuarioDTO);
            assertEquals(-1, result);  // Asegurarse de que retorne -1 en caso de error
        } catch (NoSuchAlgorithmException e) {
            // Esto es esperado
            assertTrue(e.getMessage().contains("Algorithm not found"));
        }
    }
}