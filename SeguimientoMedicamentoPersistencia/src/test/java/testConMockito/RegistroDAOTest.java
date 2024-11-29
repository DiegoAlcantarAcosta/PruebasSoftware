package testConMockito;


import conexionEM.Conexion;
import conexionEM.IConexion;
import entidades.Medicamento;
import entidades.Registro;
import excepciones.PersistenciaExcepcion;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.NoResultException;
import java.util.Date;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import persistencia.RegistroDAO;

public class RegistroDAOTest {
    @Mock
    private Conexion conexionMock;
    @Mock
    private EntityManager entityManagerMock;
    
    @InjectMocks
    private RegistroDAO registroDAO;
    

    @BeforeEach
    void setUp() {
        conexionMock = mock(Conexion.class);
        entityManagerMock = mock(EntityManager.class);
        when(conexionMock.abrir()).thenReturn(entityManagerMock);
        registroDAO = new RegistroDAO(conexionMock);
    }

    @Test
    void testTomaDeMedicamentoExito() throws PersistenciaExcepcion {
        Medicamento medicamento = new Medicamento();
        medicamento.setId(1L);
        Registro registro = new Registro();
        registro.setMedicamento(medicamento);
        registro.setHoraConsumo(new Date());
        registro.setCantidadConsumo(2);
        registro.setTomado(true);

        doNothing().when(entityManagerMock).persist(any(Registro.class));
        when(entityManagerMock.getTransaction()).thenReturn(mock(javax.persistence.EntityTransaction.class));

        boolean resultado = registroDAO.tomaDeMedicamento(registro);

        assertTrue(resultado);
        verify(entityManagerMock, times(1)).persist(registro);
    }

    @Test
    void testTomaDeMedicamentoConMedicamentoNulo() {
        Registro registro = new Registro();
        registro.setMedicamento(null);

        PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class, () -> {
            registroDAO.tomaDeMedicamento(registro);
        });

        assertEquals("El medicamento no puede ser nulo.", exception.getMessage());
    }

    @Test
    void testConsultarUltimaTomaExito() throws PersistenciaExcepcion {
        Medicamento medicamento = new Medicamento();
        medicamento.setId(1L);

        Registro registroEsperado = new Registro();
        registroEsperado.setMedicamento(medicamento);
        registroEsperado.setHoraConsumo(new Date());
        registroEsperado.setCantidadConsumo(1);
        registroEsperado.setTomado(true);

        TypedQuery<Registro> queryMock = mock(TypedQuery.class);
        EntityTransaction transactionMock = mock(EntityTransaction.class);

        when(entityManagerMock.getTransaction()).thenReturn(transactionMock);
        when(transactionMock.isActive()).thenReturn(true);
        when(entityManagerMock.createQuery(anyString(), eq(Registro.class))).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), any())).thenReturn(queryMock);
        when(queryMock.setMaxResults(anyInt())).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(registroEsperado);

        Registro registro = registroDAO.consultarUltimaToma(medicamento);

        assertNotNull(registro);
        assertEquals(medicamento, registro.getMedicamento());
        assertEquals(registroEsperado.getHoraConsumo(), registro.getHoraConsumo());
    }

    @Test
    void testConsultarUltimaTomaNoResultado() throws PersistenciaExcepcion {
        Medicamento medicamento = new Medicamento();
        medicamento.setId(1L);

        TypedQuery<Registro> queryMock = mock(TypedQuery.class);
        EntityTransaction transactionMock = mock(EntityTransaction.class);

        when(entityManagerMock.getTransaction()).thenReturn(transactionMock);
        when(transactionMock.isActive()).thenReturn(true);
        when(entityManagerMock.createQuery(anyString(), eq(Registro.class))).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), any())).thenReturn(queryMock);
        when(queryMock.setMaxResults(anyInt())).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenThrow(new NoResultException("No result"));

        Registro registro = registroDAO.consultarUltimaToma(medicamento);

        assertNull(registro);
    }

    @Test
    void testConsultarUltimaTomaConMedicamentoNulo() {
        Medicamento medicamento = null;

        PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class, () -> {
            registroDAO.consultarUltimaToma(medicamento);
        });

        assertEquals("El medicamento no puede ser nulo o tener un ID nulo.", exception.getMessage());
    }

    @Test
    void testConsultarUltimaTomaConMedicamentoIdNulo() {
        Medicamento medicamento = new Medicamento();
        medicamento.setId(null);

        PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class, () -> {
            registroDAO.consultarUltimaToma(medicamento);
        });

        assertEquals("El medicamento no puede ser nulo o tener un ID nulo.", exception.getMessage());
    }
}
