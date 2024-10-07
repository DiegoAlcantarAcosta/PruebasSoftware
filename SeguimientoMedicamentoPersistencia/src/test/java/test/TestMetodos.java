//package test;
//
//import entidades.Medicamento;
//import excepciones.PersistenciaExcepcion;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import persistencia.MedicamentoDAO;
//
//public class TestMetodos {
//    
//    public TestMetodos() {
//    }
//    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
//    
//    @Test
//    public void testAgregarMedicamento() throws PersistenciaExcepcion {
//        MedicamentoDAO medicamentoDAO=new MedicamentoDAO();
//        Medicamento medicamento = new Medicamento("Ibuprofeno", 1.0f, 1.0d, "ml", 500);
//        boolean resultado=medicamentoDAO.agregar(medicamento);
//        assertEquals(true, resultado);
//    }
//    
//    /*@Test
//    public void testObtenerMedicamento() throws PersistenciaExcepcion {
//        MedicamentoDAO medicamentoDAO=new MedicamentoDAO();
//        Medicamento medicamento=medicamentoDAO.obtener("mitroson");
//        assertEquals( "mitroson", medicamento.getNombre());
//    }
//    
//    @Test
//    public void testEditarMedicamento() throws PersistenciaExcepcion {
//        MedicamentoDAO medicamentoDAO=new MedicamentoDAO();
//        Medicamento medicamento=medicamentoDAO.obtener("mitroson");
//        medicamento.setNombre("OtroNombre");
//        boolean resultado=medicamentoDAO.editar(medicamento);
//        assertEquals( true, resultado);
//    }
//    
//    @Test
//    public void testEliminarMedicamento() throws PersistenciaExcepcion {
//        MedicamentoDAO medicamentoDAO=new MedicamentoDAO();
//        Medicamento medicamento=medicamentoDAO.obtener("simon");
//        boolean resultado=medicamentoDAO.eliminar(medicamento);
//        assertEquals( true, resultado);
//    }*/
//
//}
