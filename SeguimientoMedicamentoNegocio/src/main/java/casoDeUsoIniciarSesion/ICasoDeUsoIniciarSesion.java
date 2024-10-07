package casoDeUsoIniciarSesion;

import dto.UsuarioDTO;

public interface ICasoDeUsoIniciarSesion {
    public boolean iniciarSesion(UsuarioDTO usuarioDTO) throws CasoDeUsoIniciarSesionException;
}
