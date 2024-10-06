/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import entidades.Medicamento;
import excepciones.PersistenciaExcepcion;
/**
 *
 * @author haloa
 */
public interface IMedicamentoDAO  {
    public boolean agregar(Medicamento medicamento) throws PersistenciaExcepcion;
    
    public Medicamento obtener(String nombre) throws PersistenciaExcepcion;
    
    public boolean editar(Medicamento medicamento) throws PersistenciaExcepcion;
    
    public boolean eliminar(Medicamento medicamento) throws PersistenciaExcepcion;
}
