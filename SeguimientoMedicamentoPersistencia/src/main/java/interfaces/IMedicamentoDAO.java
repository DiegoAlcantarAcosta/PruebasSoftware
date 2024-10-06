package interfaces;

import entidades.Medicamento;
import excepciones.PersistenciaExcepcion;

public interface IMedicamentoDAO  {
    public boolean agregar(Medicamento medicamento) throws PersistenciaExcepcion;
    
    public Medicamento obtener(String nombre) throws PersistenciaExcepcion;
    
    public boolean editar(Medicamento medicamento) throws PersistenciaExcepcion;
    
    public boolean eliminar(Medicamento medicamento) throws PersistenciaExcepcion;
}
