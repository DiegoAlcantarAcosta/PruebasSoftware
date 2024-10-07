//package test;
//
//
//import conexionEM.Conexion;
//import conexionEM.IConexion;
//import entidades.Medicamento;
//import javax.persistence.EntityManager;
//
//public class Pruebas {
//private final IConexion conexion;
//
//    public Pruebas() {
//        conexion = new Conexion();
//    }
//    
//    
//    public void pruebasxd(){
//     
//    
//    Medicamento medicamento1 = new Medicamento("mitroson", 1.0f, 1.0d, "Oral", 1);
//    Medicamento medicamento2 = new Medicamento("simon", 1.0f, 1.0d, "Oral", 1);
//    EntityManager em = conexion.abrir();
//        
//
//        try {
//            em.getTransaction().begin();
//            em.persist(medicamento1);
//            em.persist(medicamento2);
//            em.getTransaction().commit();
//
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//            e.printStackTrace();
//            throw e;
//        } finally {
//            em.close();
//        }
//}
//}
