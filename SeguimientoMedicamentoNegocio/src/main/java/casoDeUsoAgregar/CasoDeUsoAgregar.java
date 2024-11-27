package casoDeUsoAgregar;

import Herramientas.Conversor;
import dto.MedicamentoDTO;
import dto.UsuarioDTO;
import entidades.Medicamento;
import excepciones.PersistenciaExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaces.IMedicamentoDAO;
import interfaces.IUsuarioDAO;
import persistencia.MedicamentoDAO;
import persistencia.UsuarioDAO;

public class CasoDeUsoAgregar implements ICasoDeUsoAgregar {

    private IUsuarioDAO usuarioDAO;
    private IMedicamentoDAO medicamentoDAO;
    private Conversor conversor;

    public CasoDeUsoAgregar() {
        this.usuarioDAO = new UsuarioDAO();
        this.medicamentoDAO = new MedicamentoDAO();
        conversor = new Conversor();

    }

    @Override
    public boolean AgregarMedicamento(UsuarioDTO usuarioDTO, MedicamentoDTO medicamentoDTO) throws CasoDeUsoAgregarException {

        try {
                Medicamento medTemp = conversor.medicamentoDTOaEntity(medicamentoDTO);
                medTemp.setUsuario(usuarioDAO.buscarUsuarioPorCodigo(usuarioDTO.getCodigo()));
                medicamentoDAO.agregar(medTemp);
                return true;
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
