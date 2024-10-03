/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import entidades.Medicamento;
import entidades.Registro;
import excepciones.PersistenciaExcepcion;

/**
 *
 * @author haloa
 */
public interface IRegistroDAO {
    
    public boolean tomaDeMedicamento(Registro registro) throws PersistenciaExcepcion;
    
    public boolean consultarUltimaToma(Medicamento medicamento) throws PersistenciaExcepcion;
}
