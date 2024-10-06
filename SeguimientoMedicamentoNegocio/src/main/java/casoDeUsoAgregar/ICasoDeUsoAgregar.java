/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoDeUsoAgregar;

import dto.MedicamentoDTO;

/**
 *
 * @author USER
 */
public interface ICasoDeUsoAgregar {
     public boolean AgregarMedicamento(MedicamentoDTO medicamentoDTO) throws CasoDeUsoAgregarException;
}
