package casoDeUsoAgregar;

import conversor.Conversor;
import dto.MedicamentoDTO;
import excepciones.PersistenciaExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaces.IMedicamentoDAO;
import persistencia.MedicamentoDAO;

public class CasoDeUsoAgregar implements ICasoDeUsoAgregar{

    private IMedicamentoDAO medicamentoDAO;
    private Conversor conversor;
    
    public CasoDeUsoAgregar() {
        this.medicamentoDAO = new MedicamentoDAO();
        conversor = new Conversor();
        
    }
    
    @Override
    public boolean AgregarMedicamento(MedicamentoDTO medicamentoDTO) throws CasoDeUsoAgregarException {
        
        try {
            if (medicamentoDAO.agregar(conversor.DTOaEntity(medicamentoDTO))) {
                return true;
            }
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
