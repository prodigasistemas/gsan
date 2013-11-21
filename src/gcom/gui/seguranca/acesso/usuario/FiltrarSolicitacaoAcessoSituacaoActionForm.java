package gcom.gui.seguranca.acesso.usuario;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author: Wallace Thierre
 * @Data: 05/11/2010
 * 
 */
public class FiltrarSolicitacaoAcessoSituacaoActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String descricao;

	private String indicadorUso;

	private String codigoSituacao;

	private String indicadorAtualizar;

	private String tipoPesquisa;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getCodigoSituacao() {
		return codigoSituacao;
	}

	public void setCodigoSituacao(String codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

}
