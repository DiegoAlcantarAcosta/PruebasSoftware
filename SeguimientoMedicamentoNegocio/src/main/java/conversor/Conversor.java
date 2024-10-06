package conversor;

import dto.MedicamentoDTO;
import entidades.Medicamento;
import excepciones.PersistenciaExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conversor {


    public Conversor() {
    }

    public Medicamento DTOaEntity(MedicamentoDTO medicamentoDTO) {
        Medicamento medicamento = new Medicamento(
                medicamentoDTO.getNombre(),
                medicamentoDTO.getFrecuencia(),
                medicamentoDTO.getHoraPrimeraDosis(),
                medicamentoDTO.getTipoConsumo(),
                medicamentoDTO.getCantidad()
        );
        return medicamento;
    }

    public MedicamentoDTO EntityaDTO(Medicamento medicamento) {
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(
                medicamento.getNombre(),
                medicamento.getFrecuencia(),
                medicamento.getHoraPrimeraDosis(),
                medicamento.getTipoConsumo(),
                medicamento.getCantidad()
        );
        return medicamentoDTO;
    }
    /*public MedicamentoDTO ObtenerMedicamento(String Nombre) {
        try {
            Medicamento medicamento = medicamentoDAO.obtener(Nombre);
            if (medicamento != (null)) {
                return EntityaDTO(medicamento);
            }
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }*/
}
