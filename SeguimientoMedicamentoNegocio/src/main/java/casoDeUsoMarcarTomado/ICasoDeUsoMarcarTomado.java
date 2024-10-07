package casoDeUsoMarcarTomado;

import dto.MedicamentoDTO;
import dto.RegistroDTO;
import dto.UsuarioDTO;

public interface ICasoDeUsoMarcarTomado {
    public boolean tomaDeMedicamento(RegistroDTO registroDTO,MedicamentoDTO medicamentoDTO,UsuarioDTO usuarioDTO) throws CasoDeUsoMarcarTomadoException;
}
