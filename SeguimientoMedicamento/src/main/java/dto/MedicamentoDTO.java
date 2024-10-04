package dto;

public class MedicamentoDTO {
    String nombre;
    double frecuencia;
    double horaPrimeraDosis;
    String tipoConsumo;
    int Cantidad;

    public MedicamentoDTO() {
    }
    
    public MedicamentoDTO(String nombre, double frecuencia, double horaPrimeraDosis, String tipoConsumo, int Cantidad) {
        this.nombre = nombre;
        this.frecuencia = frecuencia;
        this.horaPrimeraDosis = horaPrimeraDosis;
        this.tipoConsumo = tipoConsumo;
        this.Cantidad = Cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(double frecuencia) {
        this.frecuencia = frecuencia;
    }

    public double getHoraPrimeraDosis() {
        return horaPrimeraDosis;
    }

    public void setHoraPrimeraDosis(double horaPrimeraDosis) {
        this.horaPrimeraDosis = horaPrimeraDosis;
    }

    public String getTipoConsumo() {
        return tipoConsumo;
    }

    public void setTipoConsumo(String tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MedicamentoDTO{");
        sb.append("nombre=").append(nombre);
        sb.append(", frecuencia=").append(frecuencia);
        sb.append(", horaPrimeraDosis=").append(horaPrimeraDosis);
        sb.append(", tipoConsumo=").append(tipoConsumo);
        sb.append(", Cantidad=").append(Cantidad);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
}
