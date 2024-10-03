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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "Medicamento")
public class Medicamento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (name = "nombre", nullable = false)
    String nombre;
    
    @Column (name = "frecuencia", nullable = false)
    double frecuencia;
    
    @Column (name = "horaPrimerDosis", nullable = false)
    double horaPrimeraDosis;
    
    @Column (name = "tipoConsumo", nullable = false)
    String tipoConsumo;
    
    @Column (name = "cantidad", nullable = false)
    int cantidad;

    @OneToMany(mappedBy = "IdMedicamento",cascade = CascadeType.ALL)
    private List<Registro> lista;

    public Medicamento(String nombre, double frecuencia, double horaPrimeraDosis, String tipoConsumo, int cantidad) {
        this.nombre = nombre;
        this.frecuencia = frecuencia;
        this.horaPrimeraDosis = horaPrimeraDosis;
        this.tipoConsumo = tipoConsumo;
        this.cantidad = cantidad;
    }

    public Medicamento() {
    }

    public Medicamento(Long id, String nombre, double frecuencia, double horaPrimeraDosis, String tipoConsumo, int cantidad) {
        this.id = id;
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

    public List<Registro> getLista() {
        return lista;
    }

    public void setLista(List<Registro> lista) {
        this.lista = lista;
    }
    
   
    
}
