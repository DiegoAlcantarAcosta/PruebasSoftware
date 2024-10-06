package persistencia;

import entidades.Medicamento;
import entidades.Registro;
import excepciones.PersistenciaExcepcion;
import interfaces.IRegistroDAO;

public class RegistroDAO implements IRegistroDAO {

    @Override
    public boolean tomaDeMedicamento(Registro registro) throws PersistenciaExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Double consultarUltimaToma(Medicamento medicamento) throws PersistenciaExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
