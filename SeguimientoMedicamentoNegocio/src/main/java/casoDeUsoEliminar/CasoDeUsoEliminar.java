/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoDeUsoEliminar;

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
public class CasoDeUsoEliminar implements ICasoDeUsoEliminar {
    
    private IMedicamentoDAO medicamentoDAO;
    private Conversor conversor;
    
    public CasoDeUsoEliminar() {
        this.medicamentoDAO = new MedicamentoDAO();
        conversor = new Conversor();
        
    }
    
    @Override
    public boolean EliminarMedicamento(int codigo,int codigoUsuario) throws CasoDeUsoEliminarException{
        try {
            if(medicamentoDAO.eliminar(codigo,codigoUsuario)){
                return true;
            }
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
