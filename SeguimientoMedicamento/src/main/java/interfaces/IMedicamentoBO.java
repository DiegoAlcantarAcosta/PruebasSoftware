package interfaces;

import dto.MedicamentoDTO;

public interface IMedicamentoBO {
    public boolean AgregarMedicamento(MedicamentoDTO medicamentoDTO);
    public boolean EditarMedicamento(MedicamentoDTO medicamentoDTO);
    public boolean EliminarMedicamento(MedicamentoDTO medicamentoDTO);
    public MedicamentoDTO ObtenerMedicamento(String Nombre);
}
