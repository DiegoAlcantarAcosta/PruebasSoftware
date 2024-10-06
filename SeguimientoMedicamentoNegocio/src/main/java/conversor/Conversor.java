package conversor;

import dto.MedicamentoDTO;
import entidades.Medicamento;

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
}
