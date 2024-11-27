package test;


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

class RegistroDAOTest {
    
    @InjectMocks
    private RegistroDAO registroDAO;
    @Mock
    private Conexion conexionMock;
    @Mock
    private EntityManager entityManagerMock;
    
    @BeforeEach
    void setUp() {
        // Crear los mocks necesarios
        conexionMock = mock(Conexion.class);
        entityManagerMock = mock(EntityManager.class);
        
        // Configurar el mock de la conexión para devolver el EntityManager
        when(conexionMock.abrir()).thenReturn(entityManagerMock);
        
        // Instanciar el objeto bajo prueba
        registroDAO = new RegistroDAO(conexionMock);
    }

    @Test
    void testTomaDeMedicamentoExito() throws PersistenciaExcepcion {
        // Preparar los datos de prueba
        Medicamento medicamento = new Medicamento();
        medicamento.setId(1L);
        Registro registro = new Registro();
        registro.setMedicamento(medicamento);
        registro.setHoraConsumo(new Date());
        registro.setCantidadConsumo(2);
        registro.setTomado(true);

        // Configurar el mock para simular la persistencia
        doNothing().when(entityManagerMock).persist(any(Registro.class));
        when(entityManagerMock.getTransaction()).thenReturn(mock(javax.persistence.EntityTransaction.class));
        
        // Ejecutar el método
        boolean resultado = registroDAO.tomaDeMedicamento(registro);

        // Verificar que el resultado sea verdadero
        assertTrue(resultado);
        
        // Verificar que el método persist se haya llamado correctamente
        verify(entityManagerMock, times(1)).persist(registro);
    }

    @Test
    void testTomaDeMedicamentoConMedicamentoNulo() {
        // Preparar el registro con medicamento nulo
        Registro registro = new Registro();
        registro.setMedicamento(null);

        // Ejecutar el método y verificar que se lanza una excepción
        PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class, () -> {
            registroDAO.tomaDeMedicamento(registro);
        });

        // Verificar el mensaje de la excepción
        assertEquals("El medicamento no puede ser nulo.", exception.getMessage());
    }

    @Test
void testConsultarUltimaTomaExito() throws PersistenciaExcepcion {
    // Preparar los datos de prueba
    Medicamento medicamento = new Medicamento();
    medicamento.setId(1L);

    // Crear el registro esperado
    Registro registroEsperado = new Registro();
    registroEsperado.setMedicamento(medicamento);
    registroEsperado.setHoraConsumo(new Date());
    registroEsperado.setCantidadConsumo(1);
    registroEsperado.setTomado(true);

    // Crear el mock de TypedQuery (el tipo correcto)
    TypedQuery<Registro> queryMock = mock(TypedQuery.class);

    // Crear el mock de EntityTransaction
    EntityTransaction transactionMock = mock(EntityTransaction.class);
    
    // Configurar el mock de EntityManager
    when(entityManagerMock.getTransaction()).thenReturn(transactionMock);
    when(transactionMock.isActive()).thenReturn(true); // Asegurarse de que la transacción esté activa
    when(entityManagerMock.createQuery(anyString(), eq(Registro.class))).thenReturn(queryMock);
    when(queryMock.setParameter(anyString(), any())).thenReturn(queryMock);
    when(queryMock.setMaxResults(anyInt())).thenReturn(queryMock);
    when(queryMock.getSingleResult()).thenReturn(registroEsperado);

    // Ejecutar el método
    Registro registro = registroDAO.consultarUltimaToma(medicamento);

    // Verificar que el resultado es el esperado
    assertNotNull(registro);
    assertEquals(medicamento, registro.getMedicamento());
    assertEquals(registroEsperado.getHoraConsumo(), registro.getHoraConsumo());
}

@Test
void testConsultarUltimaTomaNoResultado() throws PersistenciaExcepcion {
    // Preparar los datos de prueba
    Medicamento medicamento = new Medicamento();
    medicamento.setId(1L);

    // Crear el mock de TypedQuery (el tipo correcto)
    TypedQuery<Registro> queryMock = mock(TypedQuery.class);

    // Crear el mock de EntityTransaction
    EntityTransaction transactionMock = mock(EntityTransaction.class);
    
    // Configurar el mock de EntityManager
    when(entityManagerMock.getTransaction()).thenReturn(transactionMock);
    when(transactionMock.isActive()).thenReturn(true); // Asegurarse de que la transacción esté activa
    when(entityManagerMock.createQuery(anyString(), eq(Registro.class))).thenReturn(queryMock);
    when(queryMock.setParameter(anyString(), any())).thenReturn(queryMock);
    when(queryMock.setMaxResults(anyInt())).thenReturn(queryMock);
    when(queryMock.getSingleResult()).thenThrow(new NoResultException("No result"));

    // Ejecutar el método
    Registro registro = registroDAO.consultarUltimaToma(medicamento);

    // Verificar que el resultado es null (ya que no hay resultado)
    assertNull(registro);
}

    @Test
    void testConsultarUltimaTomaConMedicamentoNulo() {
        // Preparar los datos de prueba
        Medicamento medicamento = null;

        // Ejecutar el método y verificar que se lanza una excepción
        PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class, () -> {
            registroDAO.consultarUltimaToma(medicamento);
        });

        // Verificar el mensaje de la excepción
        assertEquals("El medicamento no puede ser nulo o tener un ID nulo.", exception.getMessage());
    }

    @Test
    void testConsultarUltimaTomaConMedicamentoIdNulo() {
        // Preparar los datos de prueba
        Medicamento medicamento = new Medicamento();
        medicamento.setId(null);

        // Ejecutar el método y verificar que se lanza una excepción
        PersistenciaExcepcion exception = assertThrows(PersistenciaExcepcion.class, () -> {
            registroDAO.consultarUltimaToma(medicamento);
        });

        // Verificar el mensaje de la excepción
        assertEquals("El medicamento no puede ser nulo o tener un ID nulo.", exception.getMessage());
    }
}