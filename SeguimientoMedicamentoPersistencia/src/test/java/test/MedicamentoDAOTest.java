package test;
import conexionEM.IConexion;
import entidades.Medicamento;
import entidades.Usuario;
import excepciones.PersistenciaExcepcion;
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
    private IConexion mockConexion;

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
        medicamento.setCodigo(1);
        medicamento.setNombre("Paracetamol");

        when(mockQuery.setParameter("codigo", 1)).thenReturn(mockQuery);
        when(mockQuery.setParameter("codigoUsuario", 1)).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(medicamento);

        boolean resultado = medicamentoDAO.editar(medicamento, 1);

        assertTrue(resultado);
        verify(mockEntityManager).merge(medicamento);
    }

    @Test
    public void testEditarMedicamentoNoExistente() {
        when(mockQuery.setParameter("codigo", 1)).thenReturn(mockQuery);
        when(mockQuery.setParameter("codigoUsuario", 1)).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenThrow(new NoResultException("No result"));

        Medicamento medicamento = new Medicamento();
        medicamento.setCodigo(1);

        PersistenciaExcepcion thrown = assertThrows(PersistenciaExcepcion.class, () -> {
            medicamentoDAO.editar(medicamento, 1);
        });

        assertEquals("No se encontró el medicamento para editar.", thrown.getMessage());
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