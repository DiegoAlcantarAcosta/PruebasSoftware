/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoDeUsoAgregar;

import conversor.Conversor;
import dto.MedicamentoDTO;
import excepciones.PersistenciaExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaces.IMedicamentoDAO;
import persistencia.MedicamentoDAO;

/**
 *
 * @author USER
 */
public class CasoDeUsoAgregar implements ICasoDeUsoAgregar{

    private IMedicamentoDAO medicamentoDAO;
    private Conversor conversor;
    
    public CasoDeUsoAgregar() {
        this.medicamentoDAO = new MedicamentoDAO();
        conversor = new Conversor();
        
    }
    
    @Override
    public boolean AgregarMedicamento(MedicamentoDTO medicamentoDTO) throws CasoDeUsoAgregarException {
        
        try {
            if (medicamentoDAO.agregar(conversor.DTOaEntity(medicamentoDTO))) {
                return true;
            }
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
