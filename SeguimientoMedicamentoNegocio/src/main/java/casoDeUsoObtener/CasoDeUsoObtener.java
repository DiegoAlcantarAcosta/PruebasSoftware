package casoDeUsoObtener;

import conversor.Conversor;
import dto.MedicamentoDTO;
import entidades.Medicamento;
import excepciones.PersistenciaExcepcion;
import interfaces.IMedicamentoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.MedicamentoDAO;

public class CasoDeUsoObtener implements ICasoDeUsoObtener{

    private IMedicamentoDAO medicamentoDAO;
    private Conversor conversor;
    
    public CasoDeUsoObtener() {
        this.medicamentoDAO = new MedicamentoDAO();
        conversor = new Conversor();
    }
    
    @Override
    public MedicamentoDTO ObtenerMedicamento(String Nombre) throws CasoDeUsoObtenerException {
        try {
            Medicamento medicamento = medicamentoDAO.obtener(Nombre);
            if (medicamento != (null)) {
                return conversor.EntityaDTO(medicamento);
            }
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(CasoDeUsoObtener.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
