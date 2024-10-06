package casoDeUsoObtener;

import dto.MedicamentoDTO;

public interface ICasoDeUsoObtener {
    public MedicamentoDTO ObtenerMedicamento(String Nombre) throws CasoDeUsoObtenerException;
}
