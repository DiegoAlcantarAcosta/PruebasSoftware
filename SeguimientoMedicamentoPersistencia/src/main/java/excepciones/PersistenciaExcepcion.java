/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author haloa
 */
public class PersistenciaExcepcion extends Exception {

    public PersistenciaExcepcion() {
    }

    public PersistenciaExcepcion(String message) {
        super(message);
    }

    public PersistenciaExcepcion(String message, Throwable cause) {
        super(message, cause);
    }
    
}
