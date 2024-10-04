package negocio;

import dto.MedicamentoDTO;
import entidades.Medicamento;
import excepciones.PersistenciaExcepcion;
import interfaces.IMedicamentoBO;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.MedicamentoDAO;

public class MedicamentoBO implements IMedicamentoBO {

    private MedicamentoDAO medicamentoDAO;

    public MedicamentoBO() {
        medicamentoDAO = new MedicamentoDAO();
    }

    private Medicamento DTOaEntity(MedicamentoDTO medicamentoDTO) {
        Medicamento medicamento = new Medicamento(
                medicamentoDTO.getNombre(),
                medicamentoDTO.getFrecuencia(),
                medicamentoDTO.getHoraPrimeraDosis(),
                medicamentoDTO.getTipoConsumo(),
                medicamentoDTO.getCantidad()
        );
        return medicamento;
    }

    private MedicamentoDTO EntityaDTO(Medicamento medicamento) {
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(
                medicamento.getNombre(),
                medicamento.getFrecuencia(),
                medicamento.getHoraPrimeraDosis(),
                medicamento.getTipoConsumo(),
                medicamento.getCantidad()
        );
        return medicamentoDTO;
    }

    @Override
    public boolean AgregarMedicamento(MedicamentoDTO medicamentoDTO) {
        try {
            if (medicamentoDAO.agregar(DTOaEntity(medicamentoDTO))) {
                return true;
            }
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(MedicamentoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean EditarMedicamento(MedicamentoDTO medicamentoDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean EliminarMedicamento(MedicamentoDTO medicamentoDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public MedicamentoDTO ObtenerMedicamento(String Nombre) {
        try {
            Medicamento medicamento = medicamentoDAO.obtener(Nombre);
            if (medicamento != (null)) {
                return EntityaDTO(medicamento);
            }
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(MedicamentoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
