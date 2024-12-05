/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testConMockito;

import Herramientas.Conversor;
import casoDeUsoMarcarTomado.CasoDeUsoMarcarTomado;
import casoDeUsoMarcarTomado.CasoDeUsoMarcarTomadoException;
import dto.MedicamentoDTO;
import dto.RegistroDTO;
import dto.UsuarioDTO;
import entidades.Medicamento;
import entidades.Registro;
import excepciones.PersistenciaExcepcion;
import interfaces.IMedicamentoDAO;
import interfaces.IRegistroDAO;
import java.util.Calendar;
import java.util.Date;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author USER
 */
public class casoDeUsoMarcarTomadoTest {

    @Mock
    private IMedicamentoDAO medicamentoDAO;

    @Mock
    private IRegistroDAO registroDAO;

    @InjectMocks
    private CasoDeUsoMarcarTomado casoDeUso;

    private MedicamentoDTO medicamentoDTO;
    private UsuarioDTO usuarioDTO;
    private RegistroDTO registroDTO;
    private Medicamento medicamento;
    private Registro ultimoRegistro;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        medicamentoDTO = new MedicamentoDTO(1001, 2001, "Ibuprofeno", 4, "Oral", 10);
        usuarioDTO = new UsuarioDTO("Juan Pérez", "aaa", 1001);
        registroDTO = new RegistroDTO(new Date(), 1, true);

        medicamento = new Medicamento(2001, "Ibuprofeno", 4, "Oral", 10);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -5);
        ultimoRegistro = new Registro(calendar.getTime(), 1, true);
    }

    @Test
    public void testTomaDeMedicamento_ExitoPrimeraToma() throws CasoDeUsoMarcarTomadoException, PersistenciaExcepcion {
        
            when(medicamentoDAO.obtener(2001, 1001)).thenReturn(medicamento);
            when(registroDAO.consultarUltimaToma(medicamento)).thenReturn(null);
            
            boolean resultado = casoDeUso.tomaDeMedicamento(registroDTO, medicamentoDTO, usuarioDTO);
            
            assertTrue(resultado);
            verify(registroDAO).tomaDeMedicamento(any(Registro.class));
        
    }

    @Test
    public void testTomaDeMedicamento_AntesDeTiempo() throws CasoDeUsoMarcarTomadoException, PersistenciaExcepcion {
        
            Calendar calendar = Calendar.getInstance();
            ultimoRegistro = new Registro(calendar.getTime(), 1, true);
            
            when(medicamentoDAO.obtener(2001, 1001)).thenReturn(medicamento);
            when(registroDAO.consultarUltimaToma(medicamento)).thenReturn(ultimoRegistro);
            
            try {
                casoDeUso.tomaDeMedicamento(registroDTO, medicamentoDTO, usuarioDTO);
            } catch (CasoDeUsoMarcarTomadoException ex) {
                assertTrue(ex.getMessage().contains("La siguiente toma"));
            }
    }

    @Test
    public void testTomaDeMedicamento_FueraDeRango() throws CasoDeUsoMarcarTomadoException {
        try {
            when(medicamentoDAO.obtener(2001, 1001)).thenReturn(medicamento);
            when(registroDAO.consultarUltimaToma(medicamento)).thenReturn(ultimoRegistro);
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ultimoRegistro.getHoraConsumo());
            calendar.add(Calendar.HOUR_OF_DAY, 4); 
            calendar.add(Calendar.MINUTE, 15); 
            Date fueraDeRango = calendar.getTime();

            registroDTO.setHoraConsumo(fueraDeRango);
            
            try {
                casoDeUso.tomaDeMedicamento(registroDTO, medicamentoDTO, usuarioDTO);
                fail("Se esperaba CasoDeUsoMarcarTomadoException fuera del rango permitido");
            } catch (CasoDeUsoMarcarTomadoException ex) {
                assertTrue(ex.getMessage().contains("Lo tomaste por"));
            }
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(casoDeUsoMarcarTomadoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}