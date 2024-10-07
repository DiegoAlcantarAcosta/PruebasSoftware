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
//    Medicamento medicamento1 = new Medicamento(1,"mitroson", 1.0f, 1.0d, "Oral", 1);
//    Medicamento m=new Medicamento
//    EntityManager em = conexion.abrir();
//        
//
//        try {
//            em.getTransaction().begin();
//            em.persist(medicamento1);
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
