package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC0204] Consultar Transferências
 * @author Rafael Corrêa
 * @since 22/08/2008
 */
public class ConsultarTransferenciasDebitoActionForm  extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idImovelOrigem;
	
	private String inscricaoImovelOrigem;
	
	private String idImovelDestino;
	
	private String inscricaoImovelDestino;
	
	private String dataInicio;
	
	private String dataFim;
	
	private String idUsuario;
	
	private String loginUsuario;
	
	private String nomeUsuario;
	
	private String tipoPesquisa;

	/**
	 * @return Retorna o campo idUsuario.
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario O idUsuario a ser setado.
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return Retorna o campo loginUsuario.
	 */
	public String getLoginUsuario() {
		return loginUsuario;
	}

	/**
	 * @param loginUsuario O loginUsuario a ser setado.
	 */
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	/**
	 * @return Retorna o campo nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * @param nomeUsuario O nomeUsuario a ser setado.
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	/**
	 * @return Retorna o campo tipoPesquisa.
	 */
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	/**
	 * @param tipoPesquisa O tipoPesquisa a ser setado.
	 */
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	/**
	 * @return Retorna o campo dataFim.
	 */
	public String getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim O dataFim a ser setado.
	 */
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * @return Retorna o campo dataInicio.
	 */
	public String getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio O dataInicio a ser setado.
	 */
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return Retorna o campo idImovelDestino.
	 */
	public String getIdImovelDestino() {
		return idImovelDestino;
	}

	/**
	 * @param idImovelDestino O idImovelDestino a ser setado.
	 */
	public void setIdImovelDestino(String idImovelDestino) {
		this.idImovelDestino = idImovelDestino;
	}

	/**
	 * @return Retorna o campo idImovelOrigem.
	 */
	public String getIdImovelOrigem() {
		return idImovelOrigem;
	}

	/**
	 * @param idImovelOrigem O idImovelOrigem a ser setado.
	 */
	public void setIdImovelOrigem(String idImovelOrigem) {
		this.idImovelOrigem = idImovelOrigem;
	}

	/**
	 * @return Retorna o campo inscricaoImovelDestino.
	 */
	public String getInscricaoImovelDestino() {
		return inscricaoImovelDestino;
	}

	/**
	 * @param inscricaoImovelDestino O inscricaoImovelDestino a ser setado.
	 */
	public void setInscricaoImovelDestino(String inscricaoImovelDestino) {
		this.inscricaoImovelDestino = inscricaoImovelDestino;
	}

	/**
	 * @return Retorna o campo inscricaoImovelOrigem.
	 */
	public String getInscricaoImovelOrigem() {
		return inscricaoImovelOrigem;
	}

	/**
	 * @param inscricaoImovelOrigem O inscricaoImovelOrigem a ser setado.
	 */
	public void setInscricaoImovelOrigem(String inscricaoImovelOrigem) {
		this.inscricaoImovelOrigem = inscricaoImovelOrigem;
	}
	
	

}
