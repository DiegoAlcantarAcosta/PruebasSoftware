package casoDeUsoMarcarTomado;

import dto.RegistroDTO;

public interface ICasoDeUsoMarcarTomado {
    public boolean tomaDeMedicamento(RegistroDTO registroDTO) throws CasoDeUsoMarcarTomadoException;
}
