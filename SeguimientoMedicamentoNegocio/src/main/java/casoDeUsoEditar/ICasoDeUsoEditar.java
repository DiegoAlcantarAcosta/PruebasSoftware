/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoDeUsoEditar;

import dto.MedicamentoDTO;

/**
 *
 * @author USER
 */
public interface ICasoDeUsoEditar {

    public boolean EditarMedicamento(MedicamentoDTO medicamentoDTO) throws CasoDeUsoEditarException;
    
}
