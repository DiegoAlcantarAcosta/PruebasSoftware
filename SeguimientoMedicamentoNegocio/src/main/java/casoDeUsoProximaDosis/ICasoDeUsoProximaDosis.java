package casoDeUsoProximaDosis;

import dto.MedicamentoDTO;
import java.util.Date;

public interface ICasoDeUsoProximaDosis {
    public Date consultarUltimaToma(int codigo,int codigoUsuario) throws CasoDeUsoProximaDosisException;
}
