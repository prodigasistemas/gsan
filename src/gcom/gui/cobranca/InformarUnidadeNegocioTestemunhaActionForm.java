package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0796] Informar Unidade Negócio Testemunha
 * @author Rafael Corrêa
 * @since 16/05/2008
 */
public class InformarUnidadeNegocioTestemunhaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idUnidadeNegocio;
	
	private String idTestemunha;
	
	private String loginTestemunha;
	
	private String nomeTestemunha;
	
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	/**
	 * @return Retorna o campo idTestemunha.
	 */
	public String getIdTestemunha() {
		return idTestemunha;
	}


	/**
	 * @param idTestemunha O idTestemunha a ser setado.
	 */
	public void setIdTestemunha(String idTestemunha) {
		this.idTestemunha = idTestemunha;
	}


	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}


	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}


	/**
	 * @return Retorna o campo nomeTestemunha.
	 */
	public String getNomeTestemunha() {
		return nomeTestemunha;
	}


	/**
	 * @param nomeTestemunha O nomeTestemunha a ser setado.
	 */
	public void setNomeTestemunha(String nomeTestemunha) {
		this.nomeTestemunha = nomeTestemunha;
	}

	/**
	 * @return Retorna o campo loginTestemunha.
	 */
	public String getLoginTestemunha() {
		return loginTestemunha;
	}

	/**
	 * @param loginTestemunha O loginTestemunha a ser setado.
	 */
	public void setLoginTestemunha(String loginTestemunha) {
		this.loginTestemunha = loginTestemunha;
	}

}
