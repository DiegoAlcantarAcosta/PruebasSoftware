package casoDeUsoProximaDosis;

import dto.MedicamentoDTO;

public interface ICasoDeUsoProximaDosis {
    public Double consultarUltimaToma(MedicamentoDTO medicamentoDTO) throws CasoDeUsoProximaDosisException;
}
