/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoDeUsoEliminar;

import dto.MedicamentoDTO;

/**
 *
 * @author USER
 */
public interface ICasoDeUsoEliminar {
    
    public boolean EliminarMedicamento(int codigo,int codigoUsuario) throws CasoDeUsoEliminarException;
    
}
