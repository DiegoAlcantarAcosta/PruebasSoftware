package dto;

import java.util.Date;

public class RegistroDTO {
    private Date horaConsumo;
    private int cantidadConsumo;
    private boolean tomado;

    public RegistroDTO() {
    
    }
    
    public RegistroDTO(Date horaConsumo, int cantidadConsumo, boolean tomado) {
        this.horaConsumo = horaConsumo;
        this.cantidadConsumo = cantidadConsumo;
        this.tomado = tomado;
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

}
