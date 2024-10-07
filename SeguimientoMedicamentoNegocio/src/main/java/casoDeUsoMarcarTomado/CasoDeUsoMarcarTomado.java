package casoDeUsoMarcarTomado;

import Herramientas.Conversor;
import dto.MedicamentoDTO;
import dto.RegistroDTO;
import dto.UsuarioDTO;
import entidades.Medicamento;
import entidades.Registro;
import excepciones.PersistenciaExcepcion;
import interfaces.IMedicamentoDAO;
import interfaces.IRegistroDAO;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.MedicamentoDAO;
import persistencia.RegistroDAO;

public class CasoDeUsoMarcarTomado implements ICasoDeUsoMarcarTomado {

    private IMedicamentoDAO medicamentoDAO;
    private IRegistroDAO registroDAO;
    private Conversor conversor;

    public CasoDeUsoMarcarTomado() {
        this.medicamentoDAO = new MedicamentoDAO();
        this.registroDAO = new RegistroDAO();
        conversor = new Conversor();
    }

    @Override
    public boolean tomaDeMedicamento(RegistroDTO registroDTO, MedicamentoDTO medicamentoDTO, UsuarioDTO usuarioDTO) throws CasoDeUsoMarcarTomadoException {
        try {
            Medicamento medicamento = medicamentoDAO.obtener(medicamentoDTO.getCodigo(), usuarioDTO.getCodigo());
            Registro registroTemp = registroDAO.consultarUltimaToma(medicamento);
            if (registroTemp != null) {
                Date ultimaToma = registroTemp.getHoraConsumo();

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(ultimaToma);
                calendar.add(Calendar.HOUR_OF_DAY, (int) medicamento.getFrecuencia());
                Date siguienteToma = calendar.getTime();

                Calendar inicioRango = Calendar.getInstance();
                inicioRango.setTime(siguienteToma);
                inicioRango.add(Calendar.MINUTE, -10);

                Calendar finRango = Calendar.getInstance();
                finRango.setTime(siguienteToma);
                finRango.add(Calendar.MINUTE, 10);

                Date ahora = new Date();

                if (ahora.before(inicioRango.getTime())) {
                    long diferenciaMilis = siguienteToma.getTime() - ahora.getTime();
                    long minutosRestantes = TimeUnit.MILLISECONDS.toMinutes(diferenciaMilis);
                    long horasRestantes = minutosRestantes / 60;
                    minutosRestantes = minutosRestantes % 60;
                    throw new CasoDeUsoMarcarTomadoException("La siguiente toma es en " + horasRestantes + " horas y " + minutosRestantes + " minutos.");
                } else if (ahora.after(finRango.getTime())) {
                    long diferenciaMilis = ahora.getTime() - finRango.getTime().getTime();
                    long minutosTarde = TimeUnit.MILLISECONDS.toMinutes(diferenciaMilis);
                    long horasTarde = minutosTarde / 60;
                    minutosTarde = minutosTarde % 60;
                    Registro registro = conversor.registroDTOaEntity(registroDTO);
                    registro.setMedicamento(medicamento);
                    registroDAO.tomaDeMedicamento(registro);
                    throw new CasoDeUsoMarcarTomadoException("Lo tomaste por " + horasTarde + " horas y " + minutosTarde + " minutos tarde.");
                }
            } else {
                Registro registro = conversor.registroDTOaEntity(registroDTO);
                registro.setMedicamento(medicamento);
                registroDAO.tomaDeMedicamento(registro);
                return true;
            }

        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
