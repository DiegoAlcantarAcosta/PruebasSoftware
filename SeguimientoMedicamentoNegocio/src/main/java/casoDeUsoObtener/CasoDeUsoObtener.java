package casoDeUsoObtener;

import Herramientas.Conversor;
import conexionEM.Conexion;
import dto.MedicamentoDTO;
import dto.UsuarioDTO;
import entidades.Medicamento;
import excepciones.PersistenciaExcepcion;
import interfaces.IMedicamentoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.MedicamentoDAO;

public class CasoDeUsoObtener implements ICasoDeUsoObtener {

    private IMedicamentoDAO medicamentoDAO;
    private Conversor conversor;

    public CasoDeUsoObtener() {
        this.medicamentoDAO = new MedicamentoDAO(new Conexion());
        conversor = new Conversor();
    }

    @Override
    public MedicamentoDTO ObtenerMedicamento(int codigo, UsuarioDTO usuarioDTO) throws CasoDeUsoObtenerException {
        try {
            Medicamento medicamento = medicamentoDAO.obtener(codigo, usuarioDTO.getCodigo());
            if (medicamento == null) {
                return null;
            } else {
                return conversor.medicamentoEntityaDTO(medicamento);
            }

        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(CasoDeUsoObtener.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
