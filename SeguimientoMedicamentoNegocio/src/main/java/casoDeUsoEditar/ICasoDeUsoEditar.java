package casoDeUsoEditar;

import dto.MedicamentoDTO;

public interface ICasoDeUsoEditar {

    public boolean EditarMedicamento(MedicamentoDTO medicamentoDTO,int codigoUsuario) throws CasoDeUsoEditarException;
    
}
