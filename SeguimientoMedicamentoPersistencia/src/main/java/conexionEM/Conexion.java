/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionEM;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Implementación de la interfaz IConexion que se encarga de abrir la conexión a la base de datos utilizando JPA.
 */
public class Conexion implements IConexion {

    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }
    @Override
    public EntityManager abrir() {
        return emf.createEntityManager();
    }
    @Override
    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
