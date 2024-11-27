package interfaces;

import entidades.Medicamento;
import excepciones.PersistenciaExcepcion;

public interface IMedicamentoDAO  {
    
    public Medicamento agregar(Medicamento medicamento) throws PersistenciaExcepcion ;
    
    public Medicamento obtener(int codigo, int codigoUsuario) throws PersistenciaExcepcion ;
    
    public boolean editar(Medicamento medicamento,int codigoUsuario) throws PersistenciaExcepcion;
    
    public boolean eliminar(int codigo, int codigoUsuario) throws PersistenciaExcepcion;
    
}
