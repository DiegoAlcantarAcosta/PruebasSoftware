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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    void editarMedicamento_exito() {
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO();
        Medicamento medicamento = new Medicamento();
        int codigoUsuario = 123;
        try {
            when(conversor.medicamentoDTOaEntity(medicamentoDTO)).thenReturn(medicamento);
            when(medicamentoDAO.editar(medicamento, codigoUsuario)).thenReturn(true);

            boolean resultado = casoDeUsoEditar.EditarMedicamento(medicamentoDTO, codigoUsuario);
            assertTrue(resultado);
            verify(conversor, times(1)).medicamentoDTOaEntity(medicamentoDTO);
            verify(medicamentoDAO, times(1)).editar(medicamento, codigoUsuario);
        } catch (Exception e) {
            fail("Excepción no esperada: " + e.getMessage());
        }
    }

    @Test
    void editarMedicamento_falloEnPersistencia() {
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO();
        Medicamento medicamento = new Medicamento();
        int codigoUsuario = 123;
        try {
            when(conversor.medicamentoDTOaEntity(medicamentoDTO)).thenReturn(medicamento);
            when(medicamentoDAO.editar(medicamento, codigoUsuario)).thenThrow(new PersistenciaExcepcion("Error en persistencia"));

            boolean resultado = casoDeUsoEditar.EditarMedicamento(medicamentoDTO, codigoUsuario);
            assertFalse(resultado);
            verify(conversor, times(1)).medicamentoDTOaEntity(medicamentoDTO);
            verify(medicamentoDAO, times(1)).editar(medicamento, codigoUsuario);
        } catch (Exception e) {
            // Si ocurre una excepción, validamos que haya sido de tipo CasoDeUsoEditarException
            assertTrue(e instanceof CasoDeUsoEditarException);
        }
    }

    @Test
    void editarMedicamento_noSeEdita() {
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO();
        Medicamento medicamento = new Medicamento();
        int codigoUsuario = 123;
        try {
            when(conversor.medicamentoDTOaEntity(medicamentoDTO)).thenReturn(medicamento);
            when(medicamentoDAO.editar(medicamento, codigoUsuario)).thenReturn(false);

            boolean resultado = casoDeUsoEditar.EditarMedicamento(medicamentoDTO, codigoUsuario);
            assertFalse(resultado);
            verify(conversor, times(1)).medicamentoDTOaEntity(medicamentoDTO);
            verify(medicamentoDAO, times(1)).editar(medicamento, codigoUsuario);
        } catch (Exception e) {
            fail("Excepción no esperada: " + e.getMessage());
        }
    }
}
