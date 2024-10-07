package casoDeUsoObtener;

import dto.MedicamentoDTO;
import dto.UsuarioDTO;

public interface ICasoDeUsoObtener {
    public MedicamentoDTO ObtenerMedicamento(int codigo,UsuarioDTO usuarioDTO) throws CasoDeUsoObtenerException;
}
