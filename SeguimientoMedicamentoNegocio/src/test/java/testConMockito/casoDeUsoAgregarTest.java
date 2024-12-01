/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testConMockito;

import Herramientas.Conversor;
import casoDeUsoAgregar.CasoDeUsoAgregar;
import casoDeUsoAgregar.CasoDeUsoAgregarException;
import dto.MedicamentoDTO;
import dto.UsuarioDTO;
import entidades.Medicamento;
import entidades.Usuario;
import excepciones.PersistenciaExcepcion;
import interfaces.IMedicamentoDAO;
import interfaces.IUsuarioDAO;
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
public class casoDeUsoAgregarTest {

    @Mock
    private IUsuarioDAO usuarioDAO;

    @Mock
    private IMedicamentoDAO medicamentoDAO;

    @Mock
    private Conversor conversor;

    @InjectMocks
    private CasoDeUsoAgregar casoDeUsoAgregar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void agregarMedicamento_exito() throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCodigo(123);

        MedicamentoDTO medicamentoDTO = new MedicamentoDTO();
        Medicamento medicamento = new Medicamento();
        Usuario usuario = new Usuario();
        when(conversor.medicamentoDTOaEntity(medicamentoDTO)).thenReturn(medicamento);
        when(usuarioDAO.buscarUsuarioPorCodigo(123)).thenReturn(usuario);

        boolean resultado = casoDeUsoAgregar.AgregarMedicamento(usuarioDTO, medicamentoDTO);

        assertTrue(resultado);
        verify(conversor, times(1)).medicamentoDTOaEntity(medicamentoDTO);
        verify(usuarioDAO, times(1)).buscarUsuarioPorCodigo(123);
        verify(medicamentoDAO, times(1)).agregar(medicamento);
        assertEquals(usuario, medicamento.getUsuario());
    }

    @Test
    void agregarMedicamento_falloEnPersistencia() {
        try {
            // Crea el UsuarioDTO y MedicamentoDTO de prueba
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setCodigo(123);
            
            MedicamentoDTO medicamentoDTO = new MedicamentoDTO();
            
            // Crea un medicamento temporal
            Medicamento medicamento = new Medicamento();
            
            // Mockea el comportamiento del conversor y los DAOs
            when(conversor.medicamentoDTOaEntity(medicamentoDTO)).thenReturn(medicamento);
            when(usuarioDAO.buscarUsuarioPorCodigo(123)).thenThrow(new PersistenciaExcepcion("Error en persistencia"));
            
            // Ejecuta el método y captura la excepción
            CasoDeUsoAgregarException exception = assertThrows(CasoDeUsoAgregarException.class, () -> {
                casoDeUsoAgregar.AgregarMedicamento(usuarioDTO, medicamentoDTO);
            });
            
            // Verifica el mensaje de la excepción
            assertEquals("Error en persistencia", exception.getMessage());
            
            // Verifica las interacciones con los mocks
            verify(conversor, times(1)).medicamentoDTOaEntity(medicamentoDTO);
            verify(usuarioDAO, times(1)).buscarUsuarioPorCodigo(123);
            verify(medicamentoDAO, never()).agregar(any());
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoAgregarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
