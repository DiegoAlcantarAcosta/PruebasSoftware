package conversor;

import dto.MedicamentoDTO;
import dto.RegistroDTO;
import dto.UsuarioDTO;
import entidades.Medicamento;
import entidades.Registro;
import entidades.Usuario;

public class Conversor {


    public Conversor() {
    }

     public Registro registroDTOaEntity(RegistroDTO registroDTO) {
        Registro registro = new Registro(
                registroDTO.getHoraConsumo(),
                registroDTO.getCantidadConsumo(),
                registroDTO.isTomado()
        );
        return registro;
    }
     
     public RegistroDTO registroEntityaDTO(Registro registro) {
        RegistroDTO registroDTO = new RegistroDTO(
                registro.getHoraConsumo(),
                registro.getCantidadConsumo(),
                registro.isTomado()
        );
        return registroDTO;
    }
    
    public Medicamento medicamentoDTOaEntity(MedicamentoDTO medicamentoDTO) {
        Medicamento medicamento = new Medicamento(
                
                medicamentoDTO.getCodigo(),
                medicamentoDTO.getNombre(),
                medicamentoDTO.getFrecuencia(),
                medicamentoDTO.getHoraPrimeraDosis(),
                medicamentoDTO.getTipoConsumo(),
                medicamentoDTO.getCantidad()
        );
        return medicamento;
    }

    public MedicamentoDTO medicamentoEntityaDTO(Medicamento medicamento) {
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(
                medicamento.getCodigo(),
                medicamento.getNombre(),
                medicamento.getFrecuencia(),
                medicamento.getHoraPrimeraDosis(),
                medicamento.getTipoConsumo(),
                medicamento.getCantidad()
        );
        return medicamentoDTO;
    }
    
    public UsuarioDTO usuarioEntityaDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuario.getNombreUsuario(),
                usuario.getContrasenia(),
                usuario.getCodigo()
        );
        return usuarioDTO;
    }
    
    public Usuario usuarioDTOaEntity(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario(usuarioDTO.getCodigo(),
                usuarioDTO.getNombreUsuario(),
                usuarioDTO.getContrasenia()
        );
        return usuario;
        
    }
}
