package casoDeUsoEditar;

import Herramientas.Conversor;
import conexionEM.Conexion;
import dto.MedicamentoDTO;
import entidades.Medicamento;
import excepciones.PersistenciaExcepcion;
import interfaces.IMedicamentoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.MedicamentoDAO;

public class CasoDeUsoEditar implements ICasoDeUsoEditar{
    
    private IMedicamentoDAO medicamentoDAO;
    private Conversor conversor;
    
    public CasoDeUsoEditar() {
        this.medicamentoDAO = new MedicamentoDAO(new Conexion());
        conversor = new Conversor();
        
    }
    
    public boolean EditarMedicamento(MedicamentoDTO medicamentoDTO,int codigoUsuario) throws CasoDeUsoEditarException{
        try {
            Long id=medicamentoDAO.obtener(medicamentoDTO.getCodigo(), codigoUsuario).getId();
            Medicamento medicamento=conversor.medicamentoDTOaEntity(medicamentoDTO);
            medicamento.setId(id);
            if(medicamentoDAO.editar(medicamento,codigoUsuario)){
                return true;
            }
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
            throw new CasoDeUsoEditarException(ex.getMessage());
        }
        return false;
    }
    
}
