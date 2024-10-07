package casoDeUsoIniciarSesion;

import dto.UsuarioDTO;

public interface ICasoDeUsoIniciarSesion {
    public int iniciarSesion(UsuarioDTO usuarioDTO) throws CasoDeUsoIniciarSesionException;
}
