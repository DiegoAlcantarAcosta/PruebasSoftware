package casoDeUsoProximaDosis;

import conversor.Conversor;
import dto.MedicamentoDTO;
import excepciones.PersistenciaExcepcion;
import interfaces.IRegistroDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.RegistroDAO;
import dto.RegistroDTO;
import dto.UsuarioDTO;
import entidades.Medicamento;
import interfaces.IMedicamentoDAO;
import java.util.Calendar;
import java.util.Date;
import persistencia.MedicamentoDAO;


public class CasoDeUsoProximaDosis implements ICasoDeUsoProximaDosis{
    private IMedicamentoDAO medicamentoDAO;
    private IRegistroDAO registroDAO;
    private Conversor conversor;

    public CasoDeUsoProximaDosis() {
        this.medicamentoDAO=new MedicamentoDAO();
        this.registroDAO = new RegistroDAO();
        conversor = new Conversor();
    }
    
    @Override
    public Date consultarUltimaToma(int codigo, int codigoUsuario) throws CasoDeUsoProximaDosisException {
        try {
            Medicamento medicamento = medicamentoDAO.obtener(codigo, codigoUsuario);
            Date ultimaToma = registroDAO.consultarUltimaToma(medicamento).getHoraConsumo();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ultimaToma);
            calendar.add(Calendar.HOUR_OF_DAY, (int) medicamento.getFrecuencia());

            return calendar.getTime();
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
    
}
