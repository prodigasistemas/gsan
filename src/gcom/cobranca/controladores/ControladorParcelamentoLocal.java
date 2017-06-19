package gcom.cobranca.controladores;

import gcom.cobranca.repositorios.dto.CancelarParcelamentoDTO;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.ejb.EJBLocalObject;

public interface ControladorParcelamentoLocal extends EJBLocalObject, IControladorParcelamento {
	
	public void cancelarParcelamento(CancelarParcelamentoDTO parcelamentoDto, Usuario usuarioLogado);
	
	public CancelarParcelamentoDTO pesquisarParcelamentoParaCancelamento(Integer idParcelamento);
}
