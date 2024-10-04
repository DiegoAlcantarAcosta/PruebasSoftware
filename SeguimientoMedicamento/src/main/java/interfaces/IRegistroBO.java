package interfaces;

import dto.MedicamentoDTO;
import dto.RegistroDTO;

public interface IRegistroBO {
    public boolean tomaDeMedicamento(RegistroDTO registroDTO);
    
    public Double consultarUltimaToma(MedicamentoDTO medicamentoDTO);
}
