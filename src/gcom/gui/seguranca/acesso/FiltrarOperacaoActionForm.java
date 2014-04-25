package gcom.gui.seguranca.acesso;

import org.apache.struts.action.ActionForm;

public class FiltrarOperacaoActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idOperacao;
	private String descricaoOperacao;
	private String idTipoOperacao;
	private String idFuncionalidade;
	private String descricaoFuncionalidade;
	private String tipoPesquisa;
	
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
	 * @return Retorna o campo descricaoFuncionalidade.
	 */
	public String getDescricaoFuncionalidade() {
		return descricaoFuncionalidade;
	}
	/**
	 * @param descricaoFuncionalidade O descricaoFuncionalidade a ser setado.
	 */
	public void setDescricaoFuncionalidade(String descricaoFuncionalidade) {
		this.descricaoFuncionalidade = descricaoFuncionalidade;
	}
	/**
	 * @return Retorna o campo descricaoOperacao.
	 */
	public String getDescricaoOperacao() {
		return descricaoOperacao;
	}
	/**
	 * @param descricaoOperacao O descricaoOperacao a ser setado.
	 */
	public void setDescricaoOperacao(String descricaoOperacao) {
		this.descricaoOperacao = descricaoOperacao;
	}
	/**
	 * @return Retorna o campo idFuncionalidade.
	 */
	public String getIdFuncionalidade() {
		return idFuncionalidade;
	}
	/**
	 * @param idFuncionalidade O idFuncionalidade a ser setado.
	 */
	public void setIdFuncionalidade(String idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}
	/**
	 * @return Retorna o campo idOperacao.
	 */
	public String getIdOperacao() {
		return idOperacao;
	}
	/**
	 * @param idOperacao O idOperacao a ser setado.
	 */
	public void setIdOperacao(String idOperacao) {
		this.idOperacao = idOperacao;
	}
	/**
	 * @return Retorna o campo idTipoOperacao.
	 */
	public String getIdTipoOperacao() {
		return idTipoOperacao;
	}
	/**
	 * @param idTipoOperacao O idTipoOperacao a ser setado.
	 */
	public void setIdTipoOperacao(String idTipoOperacao) {
		this.idTipoOperacao = idTipoOperacao;
	}
	
	

	
	
}
