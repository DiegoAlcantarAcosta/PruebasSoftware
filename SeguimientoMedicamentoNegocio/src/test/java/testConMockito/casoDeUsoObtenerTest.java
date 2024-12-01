/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testConMockito;

import Herramientas.Conversor;
import casoDeUsoObtener.CasoDeUsoObtener;
import casoDeUsoObtener.CasoDeUsoObtenerException;
import dto.MedicamentoDTO;
import dto.UsuarioDTO;
import entidades.Medicamento;
import excepciones.PersistenciaExcepcion;
import interfaces.IMedicamentoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author USER
 */
public class casoDeUsoObtenerTest {

    @Mock
    private IMedicamentoDAO medicamentoDAO;

    @Mock
    private Conversor conversor;

    @InjectMocks
    private CasoDeUsoObtener casoDeUsoObtener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerMedicamentoSuccess() throws Exception {
        int codigo = 1;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCodigo(123);
        Medicamento medicamento = new Medicamento();
        medicamento.setCodigo(codigo);
        
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO();
        
        when(medicamentoDAO.obtener(codigo, usuarioDTO.getCodigo())).thenReturn(medicamento);
        when(conversor.medicamentoEntityaDTO(medicamento)).thenReturn(medicamentoDTO);

        MedicamentoDTO resultado = casoDeUsoObtener.ObtenerMedicamento(codigo, usuarioDTO);

        assertNotNull(resultado);
        assertEquals(medicamentoDTO, resultado);
        verify(medicamentoDAO).obtener(codigo, usuarioDTO.getCodigo());
        verify(conversor).medicamentoEntityaDTO(medicamento);
    }

    @Test
    void testObtenerMedicamentoNotFound() throws Exception {
        int codigo = 1;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCodigo(123);
        when(medicamentoDAO.obtener(codigo, usuarioDTO.getCodigo())).thenReturn(null);
        MedicamentoDTO resultado = casoDeUsoObtener.ObtenerMedicamento(codigo, usuarioDTO);

        assertNull(resultado);
        verify(medicamentoDAO).obtener(codigo, usuarioDTO.getCodigo());
        verifyNoInteractions(conversor); 
    }
    @Test
    void testObtenerMedicamentoNullPointerException() {
        int codigo = 1;
        UsuarioDTO usuarioDTO = null;
        assertThrows(NullPointerException.class, () -> {
            casoDeUsoObtener.ObtenerMedicamento(codigo, usuarioDTO);
        });
    }
}