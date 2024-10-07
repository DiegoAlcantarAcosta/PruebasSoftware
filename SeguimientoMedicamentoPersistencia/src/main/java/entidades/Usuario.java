package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {
    
    private static int contadorCodigo = 1;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="codigo", nullable = false)
    private int codigo;
    
    @Column(name = "nombreUsuario", nullable = false)
    private String nombreUsuario;
    
    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Medicamento> medicamentos;

    public Usuario() {
    }

    public Usuario(int codigo, String nombreUsuario, String contrasenia) {
        this.codigo = codigo;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }
    
    

    public Usuario(Long id, String nombreUsuario, String contrasenia) {
        this.id = id;
        this.codigo=contadorCodigo++;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public Usuario(String nombreUsuario, String contrasenia) {
        this.codigo=contadorCodigo++;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public static int getContadorCodigo() {
        return contadorCodigo;
    }

    public static void setContadorCodigo(int contadorCodigo) {
        Usuario.contadorCodigo = contadorCodigo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", codigo=" + codigo + ", nombreUsuario=" + nombreUsuario + ", contrasenia=" + contrasenia + ", medicamentos=" + medicamentos + '}';
    }

    
    
    
    
}
