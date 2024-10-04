package negocio;

import dto.MedicamentoDTO;
import dto.RegistroDTO;
import interfaces.IRegistroBO;

public class RegistroBO implements IRegistroBO{

    @Override
    public boolean tomaDeMedicamento(RegistroDTO registroDTO) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Double consultarUltimaToma(MedicamentoDTO medicamentoDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
