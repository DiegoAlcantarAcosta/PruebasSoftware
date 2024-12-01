/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testConMockito;

import Herramientas.Conversor;
import casoDeUsoEditar.CasoDeUsoEditar;
import casoDeUsoEditar.CasoDeUsoEditarException;
import dto.MedicamentoDTO;
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
import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
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
public class casoDeUsoEditarTest {

    @Mock
    private IMedicamentoDAO medicamentoDAO;

    @Mock
    private Conversor conversor;

    @InjectMocks
    private CasoDeUsoEditar casoDeUsoEditar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void editarMedicamento_exito(){
        try {
            MedicamentoDTO medicamentoDTO = new MedicamentoDTO();
            medicamentoDTO.setCodigo(123);
            
            Medicamento medicamento = new Medicamento();
            medicamento.setId(1L);
            
            int codigoUsuario = 456;
            
            when(medicamentoDAO.obtener(123, codigoUsuario)).thenReturn(medicamento);
            when(conversor.medicamentoDTOaEntity(medicamentoDTO)).thenReturn(new Medicamento());
            when(medicamentoDAO.editar(any(Medicamento.class), eq(codigoUsuario))).thenReturn(true);
            
            boolean resultado = casoDeUsoEditar.EditarMedicamento(medicamentoDTO, codigoUsuario);
            
            assertTrue(resultado);
            
            verify(medicamentoDAO, times(1)).obtener(123, codigoUsuario);
            verify(conversor, times(1)).medicamentoDTOaEntity(medicamentoDTO);
            verify(medicamentoDAO, times(1)).editar(any(Medicamento.class), eq(codigoUsuario));
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoEditarTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CasoDeUsoEditarException ex) {
            Logger.getLogger(casoDeUsoEditarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void editarMedicamento_falloEnPersistencia(){
        try {
            MedicamentoDTO medicamentoDTO = new MedicamentoDTO();
            medicamentoDTO.setCodigo(123);
            
            int codigoUsuario = 456;
            
            when(medicamentoDAO.obtener(123, codigoUsuario))
                    .thenThrow(new PersistenciaExcepcion("Error en persistencia"));
            
            CasoDeUsoEditarException exception = assertThrows(CasoDeUsoEditarException.class, () ->
                    casoDeUsoEditar.EditarMedicamento(medicamentoDTO, codigoUsuario)
            );
            
            assertEquals("Error en persistencia", exception.getMessage());
            
            verify(medicamentoDAO, times(1)).obtener(123, codigoUsuario);
            verify(conversor, never()).medicamentoDTOaEntity(any());
            verify(medicamentoDAO, never()).editar(any(), anyInt());
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoEditarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    void editarMedicamento_falloEnEdicion(){
        try {
            MedicamentoDTO medicamentoDTO = new MedicamentoDTO();
            medicamentoDTO.setCodigo(123);
            
            Medicamento medicamento = new Medicamento();
            medicamento.setId(1L);
            
            int codigoUsuario = 456;
            
            when(medicamentoDAO.obtener(123, codigoUsuario)).thenReturn(medicamento);
            when(conversor.medicamentoDTOaEntity(medicamentoDTO)).thenReturn(new Medicamento());
            when(medicamentoDAO.editar(any(Medicamento.class), eq(codigoUsuario))).thenReturn(false);
            
            boolean resultado = casoDeUsoEditar.EditarMedicamento(medicamentoDTO, codigoUsuario);
            
            assertFalse(resultado);
            
            verify(medicamentoDAO, times(1)).obtener(123, codigoUsuario);
            verify(conversor, times(1)).medicamentoDTOaEntity(medicamentoDTO);
            verify(medicamentoDAO, times(1)).editar(any(Medicamento.class), eq(codigoUsuario));
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoEditarTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CasoDeUsoEditarException ex) {
            Logger.getLogger(casoDeUsoEditarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}