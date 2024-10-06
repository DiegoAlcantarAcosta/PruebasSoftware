/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoDeUsoEditar;

import casoDeUsoEditar.CasoDeUsoEditarException;
import conversor.Conversor;
import dto.MedicamentoDTO;
import excepciones.PersistenciaExcepcion;
import interfaces.IMedicamentoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.MedicamentoDAO;

/**
 *
 * @author USER
 */
public class CasoDeUsoEditar implements ICasoDeUsoEditar{
    
    private IMedicamentoDAO medicamentoDAO;
    private Conversor conversor;
    
    public CasoDeUsoEditar() {
        this.medicamentoDAO = new MedicamentoDAO();
        conversor = new Conversor();
        
    }
    
    public boolean EditarMedicamento(MedicamentoDTO medicamentoDTO) throws CasoDeUsoEditarException{
        try {
            if(medicamentoDAO.editar(conversor.DTOaEntity(medicamentoDTO))){
                return true;
            }
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
