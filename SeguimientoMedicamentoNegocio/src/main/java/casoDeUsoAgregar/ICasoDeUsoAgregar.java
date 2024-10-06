package casoDeUsoAgregar;

import dto.MedicamentoDTO;

public interface ICasoDeUsoAgregar {
     public boolean AgregarMedicamento(MedicamentoDTO medicamentoDTO) throws CasoDeUsoAgregarException;
}
