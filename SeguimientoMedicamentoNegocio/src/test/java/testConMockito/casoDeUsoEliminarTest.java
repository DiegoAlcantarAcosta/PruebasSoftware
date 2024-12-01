/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testConMockito;

import Herramientas.Conversor;
import casoDeUsoEliminar.CasoDeUsoEliminar;
import casoDeUsoEliminar.CasoDeUsoEliminarException;
import conexionEM.Conexion;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import persistencia.MedicamentoDAO;

/**
 *
 * @author USER
 */
public class casoDeUsoEliminarTest {

    @Mock
    private IMedicamentoDAO medicamentoDAO;

    @Mock
    private Conversor conversor;

    @InjectMocks
    private CasoDeUsoEliminar casoDeUsoEliminar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void eliminarMedicamento_exito() throws Exception {
        int codigo = 123;
        int codigoUsuario = 456;

        when(medicamentoDAO.eliminar(codigo, codigoUsuario)).thenReturn(true);

        boolean resultado = casoDeUsoEliminar.EliminarMedicamento(codigo, codigoUsuario);

        assertTrue(resultado);

        verify(medicamentoDAO, times(1)).eliminar(codigo, codigoUsuario);
    }

    @Test
    void eliminarMedicamento_fallo() throws Exception {
        int codigo = 123;
        int codigoUsuario = 456;

        when(medicamentoDAO.eliminar(codigo, codigoUsuario)).thenReturn(false);

        boolean resultado = casoDeUsoEliminar.EliminarMedicamento(codigo, codigoUsuario);

        assertFalse(resultado);

        verify(medicamentoDAO, times(1)).eliminar(codigo, codigoUsuario);
    }

    @Test
    void eliminarMedicamento_falloEnPersistencia() {
        try {
            int codigo = 123;
            int codigoUsuario = 456;
            
            when(medicamentoDAO.eliminar(codigo, codigoUsuario))
                    .thenThrow(new PersistenciaExcepcion("Error en persistencia"));
            
            CasoDeUsoEliminarException exception = assertThrows(CasoDeUsoEliminarException.class, () ->
                    casoDeUsoEliminar.EliminarMedicamento(codigo, codigoUsuario)
            );
            
            assertEquals("Error en persistencia", exception.getMessage());
            
            verify(medicamentoDAO, times(1)).eliminar(codigo, codigoUsuario);
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoEliminarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}