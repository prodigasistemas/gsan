package gcom.cobranca.controladores;

import gcom.cobranca.repositorios.dto.CancelarParcelamentoDTO;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

public interface IControladorParcelamento {
	
	public void cancelarParcelamentos(Usuario usuario, int idFuncionalidadeIniciada) throws ControladorException;
	
	public void cancelarParcelamento(CancelarParcelamentoDTO dto, Usuario usuarioLogado) throws ControladorException;
	
	public CancelarParcelamentoDTO pesquisarParcelamentoParaCancelar(Integer idParcelamento);
}
