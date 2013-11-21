package gcom.gui.seguranca.acesso.usuario;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author: Wallace Thierre
 * @Data: 05/11/2010
 * 
 */
public class InserirSolicitacaoAcessoSituacaoActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** identifier field */
	private String descricao;

	/** persistent field */
	private Short indicadorUso;

	private Short codigoSituacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Short getCodigoSituacao() {
		return codigoSituacao;
	}

	public void setCodigoSituacao(Short codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}

}
