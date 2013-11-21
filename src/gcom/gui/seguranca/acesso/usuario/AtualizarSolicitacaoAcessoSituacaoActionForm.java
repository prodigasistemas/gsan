package gcom.gui.seguranca.acesso.usuario;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * < <Form Atualizar Solicitação Acesso Situação>>
 * 
 * @author: Wallace Thierre
 * @Data: 08/11/2010
 * 
 */
public class AtualizarSolicitacaoAcessoSituacaoActionForm extends
		ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String descricao;

	private String indicadorUso;

	private String codigoSituacao;

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

}
