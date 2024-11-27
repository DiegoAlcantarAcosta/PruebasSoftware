package testConMockito;

import conexionEM.Conexion;
import conexionEM.IConexion;
import entidades.Usuario;
import excepciones.PersistenciaExcepcion;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import persistencia.UsuarioDAO;

public class UsuarioDAOTest {

    @Mock
    private Conexion conexionMock;

    @Mock
    private EntityManager entityManagerMock;

    @Mock
    private EntityTransaction transactionMock;

    @Mock
    private TypedQuery<Usuario> typedQueryMock;
    
    @InjectMocks
    private UsuarioDAO usuarioDAO;

     @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(conexionMock.abrir()).thenReturn(entityManagerMock);
        when(entityManagerMock.getTransaction()).thenReturn(transactionMock);
        usuarioDAO = new UsuarioDAO(conexionMock);
    }

    @Test
    public void testRegistrarUsuario() throws PersistenciaExcepcion {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("testUser");
        usuario.setContrasenia("testPassword");

        doNothing().when(transactionMock).begin();
        doNothing().when(transactionMock).commit();
        
        usuarioDAO.registrar(usuario);
        
        verify(entityManagerMock, times(1)).persist(usuario);
        verify(transactionMock, times(1)).begin();
        verify(transactionMock, times(1)).commit();
    }

    @Test
    public void testIniciarSesionExitoso() throws PersistenciaExcepcion {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("testUser");
        usuario.setContrasenia("testPassword");

        Usuario usuarioMock = new Usuario();
        usuarioMock.setCodigo(1);

        when(entityManagerMock.createQuery(anyString(), eq(Usuario.class))).thenReturn(typedQueryMock);
        when(typedQueryMock.setParameter(eq("nombreUsuario"), eq(usuario.getNombreUsuario()))).thenReturn(typedQueryMock);
        when(typedQueryMock.setParameter(eq("contrasenia"), eq(usuario.getContrasenia()))).thenReturn(typedQueryMock);
        when(typedQueryMock.getSingleResult()).thenReturn(usuarioMock);

        int codigo = usuarioDAO.iniciarSesion(usuario);

        assertEquals(1, codigo);
        verify(entityManagerMock, times(1)).createQuery(anyString(), eq(Usuario.class));
        verify(typedQueryMock, times(1)).setParameter(eq("nombreUsuario"), eq(usuario.getNombreUsuario()));
        verify(typedQueryMock, times(1)).setParameter(eq("contrasenia"), eq(usuario.getContrasenia()));
    }

    @Test
    public void testIniciarSesionNoExitoso() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("testUser");
        usuario.setContrasenia("wrongPassword");

        when(entityManagerMock.createQuery(anyString(), eq(Usuario.class))).thenReturn(typedQueryMock);
        when(typedQueryMock.setParameter(eq("nombreUsuario"), eq(usuario.getNombreUsuario()))).thenReturn(typedQueryMock);
        when(typedQueryMock.setParameter(eq("contrasenia"), eq(usuario.getContrasenia()))).thenReturn(typedQueryMock);
        when(typedQueryMock.getSingleResult()).thenThrow(NoResultException.class);

        assertThrows(PersistenciaExcepcion.class, () -> usuarioDAO.iniciarSesion(usuario));
    }

    @Test
    public void testBuscarUsuarioPorCodigoExitoso() throws PersistenciaExcepcion {
        int codigo = 1;
        Usuario usuarioMock = new Usuario();
        usuarioMock.setCodigo(codigo);

        when(entityManagerMock.createQuery(anyString(), eq(Usuario.class))).thenReturn(typedQueryMock);
        when(typedQueryMock.setParameter(eq("codigo"), eq(codigo))).thenReturn(typedQueryMock);
        when(typedQueryMock.getSingleResult()).thenReturn(usuarioMock);

        Usuario usuario = usuarioDAO.buscarUsuarioPorCodigo(codigo);

        assertEquals(codigo, usuario.getCodigo());
        verify(entityManagerMock, times(1)).createQuery(anyString(), eq(Usuario.class));
        verify(typedQueryMock, times(1)).setParameter(eq("codigo"), eq(codigo));
    }

    @Test
    public void testBuscarUsuarioPorCodigoNoExitoso() {
        int codigo = 1;

        when(entityManagerMock.createQuery(anyString(), eq(Usuario.class))).thenReturn(typedQueryMock);
        when(typedQueryMock.setParameter(eq("codigo"), eq(codigo))).thenReturn(typedQueryMock);
        when(typedQueryMock.getSingleResult()).thenThrow(NoResultException.class);

        assertThrows(PersistenciaExcepcion.class, () -> usuarioDAO.buscarUsuarioPorCodigo(codigo));
    }

    @Test
    public void testConfigurarCodigo() throws PersistenciaExcepcion {
        Usuario usuarioMock = new Usuario();
        usuarioMock.setCodigo(1);

        when(entityManagerMock.createQuery(anyString(), eq(Usuario.class))).thenReturn(typedQueryMock);
        when(typedQueryMock.setMaxResults(eq(1))).thenReturn(typedQueryMock);
        when(typedQueryMock.getSingleResult()).thenReturn(usuarioMock);

        usuarioDAO.configurarCodigo();

        verify(entityManagerMock, times(1)).createQuery(anyString(), eq(Usuario.class));
        verify(typedQueryMock, times(1)).setMaxResults(eq(1));
    }
}