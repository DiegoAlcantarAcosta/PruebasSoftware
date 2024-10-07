package casoDeUsoRegistrarse;

import dto.UsuarioDTO;

public interface ICasoDeUsoRegistrarse {
    public boolean registrarse(UsuarioDTO usuarioDTO) throws CasoDeUsoRegistrarseException;
}
