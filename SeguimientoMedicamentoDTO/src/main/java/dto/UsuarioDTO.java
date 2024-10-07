package dto;

public class UsuarioDTO {
    private String nombreUsuario;
    private String contrasenia;
    private int codigo;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombreUsuario, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }
    
    

    public UsuarioDTO(String nombreUsuario, String contrasenia, int codigo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.codigo = codigo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
}
