package testConMockito;
import conexionEM.Conexion;
import conexionEM.IConexion;
import entidades.Medicamento;
import entidades.Usuario;
import excepciones.PersistenciaExcepcion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import persistencia.MedicamentoDAO;

public class MedicamentoDAOTest {

    @Mock
    private Conexion mockConexion;

    @Mock
    private EntityManager mockEntityManager;

    @Mock
    private EntityTransaction mockTransaction;

    @Mock
    private TypedQuery<Medicamento> mockQuery;

    @InjectMocks
    private MedicamentoDAO medicamentoDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockConexion.abrir()).thenReturn(mockEntityManager);
        when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
        when(mockEntityManager.createQuery(anyString(), eq(Medicamento.class)))
            .thenReturn(mockQuery);
    }

    @Test
    public void testAgregarMedicamentoExitoso() throws PersistenciaExcepcion {
        
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(mockEntityManager.find(Usuario.class, 1L)).thenReturn(usuario);

        Medicamento medicamento = new Medicamento();
        medicamento.setUsuario(usuario);

        when(mockEntityManager.createQuery(anyString(), eq(Medicamento.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(anyString(), any())).thenReturn(mockQuery);

        Medicamento resultado = medicamentoDAO.agregar(medicamento);

        assertNotNull(resultado);
        assertEquals(medicamento, resultado);

        verify(mockEntityManager).persist(medicamento);
    }

    @Test
    public void testAgregarMedicamentoUsuarioNoEncontrado() {
        when(mockEntityManager.find(Usuario.class, 1)).thenReturn(null);

        Medicamento medicamento = new Medicamento();
        medicamento.setUsuario(new Usuario());

        PersistenciaExcepcion thrown = assertThrows(PersistenciaExcepcion.class, () -> {
            medicamentoDAO.agregar(medicamento);
        });

        assertEquals("Error al agregar el medicamento", thrown.getMessage());
    }

    @Test
    public void testObtenerMedicamentoExitoso() throws PersistenciaExcepcion {
        Medicamento medicamento = new Medicamento();
        when(mockQuery.setParameter("codigo", 1)).thenReturn(mockQuery);
        when(mockQuery.setParameter("codigoUsuario", 1)).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(medicamento);

        Medicamento resultado = medicamentoDAO.obtener(1, 1);

        assertNotNull(resultado);
    }

    @Test
    public void testObtenerMedicamentoNoExistente() {
        when(mockQuery.setParameter("codigo", 1)).thenReturn(mockQuery);
        when(mockQuery.setParameter("codigoUsuario", 1)).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenThrow(new NoResultException("No result"));

        PersistenciaExcepcion thrown = assertThrows(PersistenciaExcepcion.class, () -> {
            medicamentoDAO.obtener(1, 1);
        });

        assertEquals("No se encontró el medicamento", thrown.getMessage());
    }

    @Test
    public void testEditarMedicamentoExitoso() throws PersistenciaExcepcion {
        Medicamento medicamento = new Medicamento();
        medicamento.setId(1L);
        medicamento.setNombre("Omeprazol");
        medicamento.setFrecuencia(8);
        medicamento.setTipoConsumo("Oral");
        medicamento.setCantidad(10);

        when(mockQuery.setParameter("id", medicamento.getId())).thenReturn(mockQuery);
        when(mockQuery.setParameter("codigoUsuario", 1)).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(medicamento);

        List<Medicamento> medicamentosDuplicados = new ArrayList<>();
        when(mockQuery.setParameter("nombre", medicamento.getNombre())).thenReturn(mockQuery);
        when(mockQuery.setParameter("codigoUsuario", 1)).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(medicamentosDuplicados);

        boolean resultado = medicamentoDAO.editar(medicamento, 1);

        assertTrue(resultado);

        verify(mockEntityManager).merge(any(Medicamento.class));

        verify(mockTransaction).commit();
    }

    @Test
    public void testEditarMedicamentoNoExistente() {
        when(mockQuery.setParameter("id", 1L)).thenReturn(mockQuery);
        when(mockQuery.setParameter("codigoUsuario", 1)).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenThrow(new NoResultException("No result"));

        Medicamento medicamento = new Medicamento();
        medicamento.setId(1L);

        PersistenciaExcepcion thrown = assertThrows(PersistenciaExcepcion.class, () -> {
            medicamentoDAO.editar(medicamento, 1);
        });

        assertEquals("No se encontró el medicamento para editar.", thrown.getMessage());

        verify(mockEntityManager.getTransaction()).rollback();
    }

    @Test
    public void testEliminarMedicamentoExitoso() throws PersistenciaExcepcion {
        Medicamento medicamento = new Medicamento();
        medicamento.setCodigo(1);

        when(mockQuery.setParameter("codigo", 1)).thenReturn(mockQuery);
        when(mockQuery.setParameter("codigoUsuario", 1)).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(medicamento);

        boolean resultado = medicamentoDAO.eliminar(1, 1);

        assertTrue(resultado);
        verify(mockEntityManager).remove(medicamento);
    }

    @Test
    public void testEliminarMedicamentoNoExistente() {
        when(mockQuery.setParameter("codigo", 1)).thenReturn(mockQuery);
        when(mockQuery.setParameter("codigoUsuario", 1)).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenThrow(new NoResultException("No result"));

        PersistenciaExcepcion thrown = assertThrows(PersistenciaExcepcion.class, () -> {
            medicamentoDAO.eliminar(1, 1);
        });

        assertEquals("El medicamento no fue encontrado para eliminar.", thrown.getMessage());
    }
}