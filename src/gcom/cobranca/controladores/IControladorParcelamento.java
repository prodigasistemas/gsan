package gcom.cobranca.controladores;

import gcom.cobranca.bean.CancelarParcelamentoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

public interface IControladorParcelamento {
	
	public void cancelarParcelamentos(Usuario usuario, int idFuncionalidadeIniciada) throws ControladorException;
	
	public void cancelarParcelamento(CancelarParcelamentoHelper helper, Usuario usuarioLogado) throws ControladorException;
	
	public CancelarParcelamentoHelper pesquisarParcelamentoParaCancelar(Integer idParcelamento);
}
