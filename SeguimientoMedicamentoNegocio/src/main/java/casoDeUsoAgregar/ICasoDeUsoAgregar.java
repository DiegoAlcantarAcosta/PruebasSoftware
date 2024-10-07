package casoDeUsoAgregar;

import dto.MedicamentoDTO;
import dto.UsuarioDTO;

public interface ICasoDeUsoAgregar {
     public boolean AgregarMedicamento(UsuarioDTO usuarioDTO,MedicamentoDTO medicamentoDTO) throws CasoDeUsoAgregarException;
}
