package casoDeUsoMarcarTomado;

import conversor.Conversor;
import dto.RegistroDTO;
import excepciones.PersistenciaExcepcion;
import interfaces.IRegistroDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.RegistroDAO;

public class CasoDeUsoMarcarTomado implements ICasoDeUsoMarcarTomado {

    private IRegistroDAO registroDAO;
    private Conversor conversor;

    public CasoDeUsoMarcarTomado() {
        this.registroDAO = new RegistroDAO();
        conversor = new Conversor();
    }

    @Override
    public boolean tomaDeMedicamento(RegistroDTO registroDTO) throws CasoDeUsoMarcarTomadoException {
        try {
            registroDAO.tomaDeMedicamento(conversor.registroDTOaEntity(registroDTO));
            return true;
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
