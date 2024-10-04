package interfaces;

import entidades.Medicamento;
import entidades.Registro;
import excepciones.PersistenciaExcepcion;

public interface IRegistroDAO {
    
    public boolean tomaDeMedicamento(Registro registro) throws PersistenciaExcepcion;
    
    public Double consultarUltimaToma(Medicamento medicamento) throws PersistenciaExcepcion;
}
