package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Registro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (name = "horaConsumo", nullable = false)
    Date horaConsumo;
    
    @Column (name = "cantidadConsumo", nullable = false)
    int cantidadConsumo;
    
    @Column (name = "tomado", nullable = false)
    boolean tomado;

    @JoinColumn (name = "id_medicamento", nullable = false)
    private Medicamento IdMedicamento;

    public Registro(Medicamento medicamento, Date horaConsumo, int cantidadConsumo, boolean tomado, Medicamento IdMedicamento) {
        this.horaConsumo = horaConsumo;
        this.cantidadConsumo = cantidadConsumo;
        this.tomado = tomado;
        this.IdMedicamento = IdMedicamento;
    }

    
    
    public Registro(Long id, Medicamento medicamento, Date horaConsumo, int cantidadConsumo, boolean tomado, Medicamento IdMedicamento) {
        this.id = id;
        this.horaConsumo = horaConsumo;
        this.cantidadConsumo = cantidadConsumo;
        this.tomado = tomado;
        this.IdMedicamento = IdMedicamento;
    }

    public Registro() {
    }


    public Date getHoraConsumo() {
        return horaConsumo;
    }

    public void setHoraConsumo(Date horaConsumo) {
        this.horaConsumo = horaConsumo;
    }

    public int getCantidadConsumo() {
        return cantidadConsumo;
    }

    public void setCantidadConsumo(int cantidadConsumo) {
        this.cantidadConsumo = cantidadConsumo;
    }

    public boolean isTomado() {
        return tomado;
    }

    public void setTomado(boolean tomado) {
        this.tomado = tomado;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

}
