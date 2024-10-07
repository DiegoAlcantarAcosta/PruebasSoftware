package conexionEM;

import javax.persistence.EntityManager;

public interface IConexion {
    
    /**
     * Abre una conexión con la base de datos utilizando JPA.
     * 
     * @return Un objeto EntityManager que representa la conexión establecida.
     */
    EntityManager abrir();
    
}
