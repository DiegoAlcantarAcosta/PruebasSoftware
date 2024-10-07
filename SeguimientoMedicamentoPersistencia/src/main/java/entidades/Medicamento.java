/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Medicamento")
public class Medicamento implements Serializable {
    
    private static int contadorCodigo = 1;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="codigo", nullable = false)
    private int codigo;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "frecuencia", nullable = false)
    private double frecuencia;
    
    @Column(name = "horaPrimerDosis", nullable = false)
    private double horaPrimeraDosis;
    
    @Column(name = "tipoConsumo", nullable = false)
    private String tipoConsumo;
    
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL)
    private List<Registro> registros;

    public Medicamento() {
    }

    public Medicamento(int codigo, String nombre, double frecuencia, double horaPrimeraDosis, String tipoConsumo, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.frecuencia = frecuencia;
        this.horaPrimeraDosis = horaPrimeraDosis;
        this.tipoConsumo = tipoConsumo;
        this.cantidad = cantidad;
        this.usuario = usuario;
    }

    public Medicamento(Long id, String nombre, double frecuencia, double horaPrimeraDosis, String tipoConsumo, int cantidad, Usuario usuario, List<Registro> registros) {
        this.id = id;
        this.codigo = contadorCodigo++;
        this.nombre = nombre;
        this.frecuencia = frecuencia;
        this.horaPrimeraDosis = horaPrimeraDosis;
        this.tipoConsumo = tipoConsumo;
        this.cantidad = cantidad;
        this.usuario = usuario;
        this.registros = registros;
    }

    public Medicamento(String nombre, double frecuencia, double horaPrimeraDosis, String tipoConsumo, int cantidad) {
        this.codigo = contadorCodigo++;
        this.nombre = nombre;
        this.frecuencia = frecuencia;
        this.horaPrimeraDosis = horaPrimeraDosis;
        this.tipoConsumo = tipoConsumo;
        this.cantidad = cantidad;
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
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }
    
   
    
}
