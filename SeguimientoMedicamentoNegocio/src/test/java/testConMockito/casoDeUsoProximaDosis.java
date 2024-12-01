/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testConMockito;

import Herramientas.Conversor;
import casoDeUsoProximaDosis.CasoDeUsoProximaDosis;
import casoDeUsoProximaDosis.CasoDeUsoProximaDosisException;
import entidades.Medicamento;
import entidades.Registro;
import excepciones.PersistenciaExcepcion;
import interfaces.IMedicamentoDAO;
import interfaces.IRegistroDAO;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author USER
 */
public class casoDeUsoProximaDosis {

    @Mock
    private IMedicamentoDAO medicamentoDAO;

    @Mock
    private IRegistroDAO registroDAO;

    @Mock
    private Conversor conversor;

    @InjectMocks
    private CasoDeUsoProximaDosis casoDeUsoProximaDosis;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsultarUltimaTomaSuccess() throws Exception {
        int codigo = 1;
        int codigoUsuario = 123;
        Medicamento medicamento = new Medicamento();
        medicamento.setCodigo(codigo);
        medicamento.setFrecuencia(4); 

        Date ultimaToma = new GregorianCalendar(2024, Calendar.NOVEMBER, 29, 10, 0).getTime();

        Registro registro = new Registro();
        registro.setHoraConsumo(ultimaToma);

        Date expectedProximaDosis = new GregorianCalendar(2024, Calendar.NOVEMBER, 29, 14, 0).getTime(); 

        when(medicamentoDAO.obtener(codigo, codigoUsuario)).thenReturn(medicamento);
        when(registroDAO.consultarUltimaToma(medicamento)).thenReturn(registro);

        Date proximaDosis = casoDeUsoProximaDosis.consultarUltimaToma(codigo, codigoUsuario);

        assertNotNull(proximaDosis);
        assertEquals(expectedProximaDosis, proximaDosis);
        verify(medicamentoDAO).obtener(codigo, codigoUsuario);
        verify(registroDAO).consultarUltimaToma(medicamento);
    }

    @Test
    void testConsultarUltimaTomaPersistenciaExcepcion() {
        try {
            int codigo = 1;
            int codigoUsuario = 123;
            Medicamento medicamento = new Medicamento();
            medicamento.setCodigo(codigo);
            when(medicamentoDAO.obtener(codigo, codigoUsuario)).thenReturn(medicamento);
            when(registroDAO.consultarUltimaToma(medicamento)).thenThrow(new PersistenciaExcepcion("Database error"));

            Date resultado = casoDeUsoProximaDosis.consultarUltimaToma(codigo, codigoUsuario);

            assertNull(resultado);

            verify(medicamentoDAO).obtener(codigo, codigoUsuario);
            verify(registroDAO).consultarUltimaToma(medicamento);

        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoProximaDosis.class.getName()).log(Level.SEVERE, null, ex);
            fail("No debería llegar aquí, ya que la excepción debe ser manejada dentro del método");
        } catch (CasoDeUsoProximaDosisException ex) {
            Logger.getLogger(casoDeUsoProximaDosis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
