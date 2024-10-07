package dto;

public class MedicamentoDTO {
    private int codigoDueno;
    private int codigo;
    private String nombre;
    private double frecuencia;
    private String tipoConsumo;
    private int Cantidad;

    public MedicamentoDTO() {
    }

    public MedicamentoDTO(int codigo, String nombre, double frecuencia, String tipoConsumo, int Cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.frecuencia = frecuencia;
        this.tipoConsumo = tipoConsumo;
        this.Cantidad = Cantidad;
    }
    

    public MedicamentoDTO(int codigoDueno, int codigo, String nombre, double frecuencia, String tipoConsumo, int Cantidad) {
        this.codigoDueno = codigoDueno;
        this.codigo = codigo;
        this.nombre = nombre;
        this.frecuencia = frecuencia;
        this.tipoConsumo = tipoConsumo;
        this.Cantidad = Cantidad;
    }

    public int getCodigoDueno() {
        return codigoDueno;
    }

    public void setCodigoDueno(int codigoDueno) {
        this.codigoDueno = codigoDueno;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    
    

    
}
