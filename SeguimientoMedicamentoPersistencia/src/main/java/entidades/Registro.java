package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Registros")
public class Registro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (name = "horaConsumo", nullable = false)
    private Date horaConsumo;
    
    @Column (name = "cantidadConsumo", nullable = false)
    private int cantidadConsumo;
    
    @Column (name = "tomado", nullable = false)
    private boolean tomado;

    @ManyToOne
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;

    public Registro(Long id, Date horaConsumo, int cantidadConsumo, boolean tomado, Medicamento medicamento) {
        this.id = id;
        this.horaConsumo = horaConsumo;
        this.cantidadConsumo = cantidadConsumo;
        this.tomado = tomado;
        this.medicamento = medicamento;
    }

    public Registro(Date horaConsumo, int cantidadConsumo, boolean tomado, Medicamento medicamento) {
        this.horaConsumo = horaConsumo;
        this.cantidadConsumo = cantidadConsumo;
        this.tomado = tomado;
        this.medicamento = medicamento;
    }

    public Registro(Date horaConsumo, int cantidadConsumo, boolean tomado) {
        this.horaConsumo = horaConsumo;
        this.cantidadConsumo = cantidadConsumo;
        this.tomado = tomado;
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
    
    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

}
