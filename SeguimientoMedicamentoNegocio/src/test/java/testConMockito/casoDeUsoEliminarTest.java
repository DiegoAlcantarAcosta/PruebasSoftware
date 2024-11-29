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

/**
 *
 * @author USER
 */
public class casoDeUsoEliminarTest {

    @Mock
    private IMedicamentoDAO medicamentoDAO;

    @Mock
    private Conexion conexion;

    @InjectMocks
    private CasoDeUsoEliminar casoDeUsoEliminar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void eliminarMedicamento_exito() {
        // Datos de entrada para el test
        int codigo = 123;
        int codigoUsuario = 456;

        try {
            // Simular que el medicamento se encuentra y se elimina correctamente
            when(medicamentoDAO.eliminar(codigo, codigoUsuario)).thenReturn(true);

            // Ejecutar el caso de uso
            boolean resultado = casoDeUsoEliminar.EliminarMedicamento(codigo, codigoUsuario);

            // Verificar que el resultado es verdadero
            assertTrue(resultado);

            // Verificar que el método eliminar fue invocado con los parámetros correctos
            verify(medicamentoDAO, times(1)).eliminar(codigo, codigoUsuario);
        } catch (Exception e) {
            fail("Excepción no esperada: " + e.getMessage());
        }
    }

    @Test
    void eliminarMedicamento_noSeEncuentra() {
        // Datos de entrada para el test
        int codigo = 123;
        int codigoUsuario = 456;

        try {
            // Simular que el medicamento no se encuentra
            when(medicamentoDAO.eliminar(codigo, codigoUsuario)).thenThrow(new PersistenciaExcepcion("El medicamento no fue encontrado para eliminar."));

            // Ejecutar el caso de uso
            boolean resultado = casoDeUsoEliminar.EliminarMedicamento(codigo, codigoUsuario);

            // Verificar que el resultado es falso
            assertFalse(resultado);

            // Verificar que el método eliminar fue invocado con los parámetros correctos
            verify(medicamentoDAO, times(1)).eliminar(codigo, codigoUsuario);
        } catch (PersistenciaExcepcion e) {
            // Validar que la excepción esperada sea de tipo PersistenciaExcepcion
            assertTrue(e instanceof PersistenciaExcepcion);
        } catch (Exception e) {
            fail("Excepción no esperada: " + e.getMessage());
        }
    }

    @Test
    void eliminarMedicamento_falloEnPersistencia() {
        // Datos de entrada para el test
        int codigo = 123;
        int codigoUsuario = 456;

        try {
            // Simular un fallo general durante la eliminación
            when(medicamentoDAO.eliminar(codigo, codigoUsuario)).thenThrow(new PersistenciaExcepcion("Error al eliminar el medicamento"));

            // Ejecutar el caso de uso
            boolean resultado = casoDeUsoEliminar.EliminarMedicamento(codigo, codigoUsuario);

            // Verificar que el resultado es falso
            assertFalse(resultado);

            // Verificar que el método eliminar fue invocado con los parámetros correctos
            verify(medicamentoDAO, times(1)).eliminar(codigo, codigoUsuario);
        } catch (PersistenciaExcepcion e) {
            // Validar que la excepción esperada sea de tipo PersistenciaExcepcion
            assertTrue(e instanceof PersistenciaExcepcion);
        } catch (Exception e) {
            fail("Excepción no esperada: " + e.getMessage());
        }
    }
}