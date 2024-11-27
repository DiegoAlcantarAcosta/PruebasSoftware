/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package persistencia;

import conexionEM.Conexion;
import entidades.Medicamento;
import entidades.Usuario;
import excepciones.PersistenciaExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        try {
            Conexion conexion = new Conexion();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
            Medicamento medicamento = new Medicamento(123, "Aspirina", 2.5, "Oral", 1);
            Usuario usuario = new Usuario(1L, "usuarioTest", "test123");
            usuarioDAO.registrar(usuario);
            medicamento.setUsuario(usuario);
            MedicamentoDAO medicamentoDAO = new MedicamentoDAO(conexion);
            medicamentoDAO.agregar(medicamento);

            
            
        } catch (PersistenciaExcepcion ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
