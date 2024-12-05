/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testSinMockito;

import conexionEM.Conexion;
import entidades.Medicamento;
import entidades.Registro;
import excepciones.PersistenciaExcepcion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import persistencia.RegistroDAO;

/**
 *
 * @author USER
 */
public class TDDTest {
    private static EntityManager em;
    private static Conexion conexion;
    private static RegistroDAO registroDAO;
    @BeforeAll
    static void setUp() throws PersistenciaExcepcion {
        conexion = new Conexion();
        em = conexion.abrir();
        registroDAO = new RegistroDAO(conexion);
    
        
    }

    @Test
    void testObtenerRegistrosRecientes_Exito() throws PersistenciaExcepcion{
        
            Medicamento medicamento = new Medicamento(1, "Paracetamol", 2,"ml",2);
            List<Registro> registros = registroDAO.obtenerRegistrosRecientes(medicamento);
            assertNotNull(registros, "La lista de registros no debe ser nula");
            assertEquals(3, registros.size(), "La lista debe contener 3 registros");
            assertTrue(registros.stream().allMatch(r -> r.getMedicamento().equals(medicamento)), "Todos los registros deben pertenecer al medicamento correcto");
        
    }

    @Test
    void testObtenerRegistrosRecientes_NoHayRegistros() throws PersistenciaExcepcion {
        
            Medicamento medicamento = new Medicamento(2, "Paracetamol", 2,"ml",2);
            
            // Ejecutar y verificar que se lanza la excepción
            PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class,
                    () -> registroDAO.obtenerRegistrosRecientes(medicamento),
                    "Debe lanzar PersistenciaExcepcion");
            
            assertEquals("No hay registros de ese medicamento", exception.getMessage(), "El mensaje de la excepción no coincide");
       
    }

    @Test
    void testObtenerRegistrosRecientes_MedicamentoNulo() {
        // Preparar un medicamento nulo
        Medicamento medicamento=null;
        // Ejecutar y verificar que se lanza la excepción
        PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class,
                () -> registroDAO.obtenerRegistrosRecientes(medicamento),
                "Debe lanzar PersistenciaExcepcion");
        assertEquals("El medicamento no puede ser nulo", exception.getMessage(), "El mensaje de la excepción no coincide");
    }
}