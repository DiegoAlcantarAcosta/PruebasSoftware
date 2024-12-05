/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testConMockito;

import conexionEM.Conexion;
import entidades.Medicamento;
import entidades.Registro;
import excepciones.PersistenciaExcepcion;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import persistencia.RegistroDAO;

/**
 *
 * @author USER
 */
public class TDDTest {

    @Mock
    private Conexion mockConexion;

    @Mock
    private EntityManager mockEntityManager;

    @InjectMocks
    private RegistroDAO registroDAO;

     @BeforeEach
    void setUp() {
        Conexion conexionMock = mock(Conexion.class);
        EntityManager entityManagerMock = mock(EntityManager.class);
        when(conexionMock.abrir()).thenReturn(entityManagerMock);
        registroDAO = new RegistroDAO(conexionMock);
    }

    @Test
    void testObtenerRegistrosRecientes_Exito() throws PersistenciaExcepcion {
        // Simular un medicamento válido con código 1
        Medicamento medicamento = new Medicamento(1, "Paracetamol", 2, "ml", 2);

        // Ejecutar el método
        List<Registro> registros = registroDAO.obtenerRegistrosRecientes(medicamento);

        // Verificar resultados
        assertNotNull(registros, "La lista de registros no debe ser nula");
        assertEquals(3, registros.size(), "La lista debe contener 3 registros");
        assertTrue(registros.stream().allMatch(r -> r.getMedicamento().equals(medicamento)), 
                "Todos los registros deben pertenecer al medicamento correcto");
    }

    @Test
    void testObtenerRegistrosRecientes_NoHayRegistros() {
        // Simular un medicamento con código diferente de 1
        Medicamento medicamento = new Medicamento(2, "Ibuprofeno", 2, "ml", 2);

        // Ejecutar y verificar excepción
        PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class, 
                () -> registroDAO.obtenerRegistrosRecientes(medicamento),
                "Debe lanzar PersistenciaExcepcion");

        assertEquals("No hay registros de ese medicamento", exception.getMessage(), 
                "El mensaje de la excepción no coincide");
    }

    @Test
    void testObtenerRegistrosRecientes_MedicamentoNulo() {
        // Simular un medicamento nulo
        Medicamento medicamento = null;

        // Ejecutar y verificar excepción
        PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class, 
                () -> registroDAO.obtenerRegistrosRecientes(medicamento),
                "Debe lanzar PersistenciaExcepcion");

        assertEquals("El medicamento no puede ser nulo", exception.getMessage(), 
                "El mensaje de la excepción no coincide");
    }
}