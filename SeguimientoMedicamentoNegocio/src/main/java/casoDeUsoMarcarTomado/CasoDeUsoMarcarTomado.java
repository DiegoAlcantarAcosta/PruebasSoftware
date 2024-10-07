package casoDeUsoMarcarTomado;

import conversor.Conversor;
import dto.MedicamentoDTO;
import dto.RegistroDTO;
import dto.UsuarioDTO;
import entidades.Medicamento;
import entidades.Registro;
import excepciones.PersistenciaExcepcion;
import interfaces.IMedicamentoDAO;
import interfaces.IRegistroDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.MedicamentoDAO;
import persistencia.RegistroDAO;

public class CasoDeUsoMarcarTomado implements ICasoDeUsoMarcarTomado {
    private IMedicamentoDAO medicamentoDAO;
    private IRegistroDAO registroDAO;
    private Conversor conversor;

    public CasoDeUsoMarcarTomado() {
        this.medicamentoDAO=new MedicamentoDAO();
        this.registroDAO = new RegistroDAO();
        conversor = new Conversor();
    }

    @Override
    public boolean tomaDeMedicamento(RegistroDTO registroDTO,MedicamentoDTO medicamentoDTO,UsuarioDTO usuarioDTO) throws CasoDeUsoMarcarTomadoException {
        try {
            Medicamento medicamento=medicamentoDAO.obtener(medicamentoDTO.getCodigo(), usuarioDTO.getCodigo());
            Registro registro=conversor.registroDTOaEntity(registroDTO);
            registro.setMedicamento(medicamento);
            registroDAO.tomaDeMedicamento(registro);
            return true;
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
