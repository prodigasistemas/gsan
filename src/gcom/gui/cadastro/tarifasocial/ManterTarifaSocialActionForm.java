package gcom.gui.cadastro.tarifasocial;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0217] Inserir Resolução de Diretoria
 * @author Rafael Corrêa
 * @since 30/03/2006
 */
public class ManterTarifaSocialActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String registroAtendimento;
	
	private String nomeRegistroAtendimento;
	
	private String idImovel;
	
	private String inscricaoImovel;
	
	private String qtdeEconomias;
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Retorna o campo nomeRegistroAtendimento.
	 */
	public String getNomeRegistroAtendimento() {
		return nomeRegistroAtendimento;
	}

	/**
	 * @param nomeRegistroAtendimento O nomeRegistroAtendimento a ser setado.
	 */
	public void setNomeRegistroAtendimento(String nomeRegistroAtendimento) {
		this.nomeRegistroAtendimento = nomeRegistroAtendimento;
	}

	/**
	 * @return Retorna o campo registroAtendimento.
	 */
	public String getRegistroAtendimento() {
		return registroAtendimento;
	}

	/**
	 * @param registroAtendimento O registroAtendimento a ser setado.
	 */
	public void setRegistroAtendimento(String registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	/**
	 * @return Retorna o campo qtdeEconomias.
	 */
	public String getQtdeEconomias() {
		return qtdeEconomias;
	}

	/**
	 * @param qtdeEconomias O qtdeEconomias a ser setado.
	 */
	public void setQtdeEconomias(String qtdeEconomias) {
		this.qtdeEconomias = qtdeEconomias;
	}


}
