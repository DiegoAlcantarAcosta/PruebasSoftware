package casoDeUsoProximaDosis;

import conversor.Conversor;
import dto.MedicamentoDTO;
import excepciones.PersistenciaExcepcion;
import interfaces.IRegistroDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.RegistroDAO;
import dto.RegistroDTO;
import entidades.Medicamento;
import interfaces.IMedicamentoDAO;
import persistencia.MedicamentoDAO;


public class CasoDeUsoProximaDosis implements ICasoDeUsoProximaDosis{

    private IRegistroDAO registroDAO;
    private Conversor conversor;

    public CasoDeUsoProximaDosis() {
        this.registroDAO = new RegistroDAO();
        conversor = new Conversor();
    }
    
    @Override
    public Double consultarUltimaToma(MedicamentoDTO medicamentoDTO) throws CasoDeUsoProximaDosisException {
        try {
            IMedicamentoDAO medicamentoDAO = new MedicamentoDAO();
            return registroDAO.consultarUltimaToma(medicamentoDAO.obtener(medicamentoDTO.getNombre()));
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
