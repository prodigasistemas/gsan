package gcom.gui.micromedicao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0218] Manter Resolução de Diretoria
 * @author Rafael Corrêa
 * @since 10/04/2006
 */
public class GerarRAImoveisAnormalidadeActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String solicitacaoTipo;
	
	private String solicitacaoTipoEspecificacao;
	
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	/**
	 * @return Retorna o campo idSolicitacaoTipo.
	 */
	public String getSolicitacaoTipo() {
		return solicitacaoTipo;
	}

	/**
	 * @param idSolicitacaoTipo O idSolicitacaoTipo a ser setado.
	 */
	public void setSolicitacaoTipo(String solicitacaoTipo) {
		this.solicitacaoTipo = solicitacaoTipo;
	}

	/**
	 * @return Retorna o campo idSolicitacaoTipoEspecificacao.
	 */
	public String getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}

	/**
	 * @param idSolicitacaoTipoEspecificacao O idSolicitacaoTipoEspecificacao a ser setado.
	 */
	public void setSolicitacaoTipoEspecificacao(
			String solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}

}
