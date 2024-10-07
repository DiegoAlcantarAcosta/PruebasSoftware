package casoDeUsoEditar;

import Herramientas.Conversor;
import dto.MedicamentoDTO;
import excepciones.PersistenciaExcepcion;
import interfaces.IMedicamentoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.MedicamentoDAO;

public class CasoDeUsoEditar implements ICasoDeUsoEditar{
    
    private IMedicamentoDAO medicamentoDAO;
    private Conversor conversor;
    
    public CasoDeUsoEditar() {
        this.medicamentoDAO = new MedicamentoDAO();
        conversor = new Conversor();
        
    }
    
    public boolean EditarMedicamento(MedicamentoDTO medicamentoDTO,int codigoUsuario) throws CasoDeUsoEditarException{
        try {
            if(medicamentoDAO.editar(conversor.medicamentoDTOaEntity(medicamentoDTO),codigoUsuario)){
                return true;
            }
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
