package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * Esta classe tem por finalidade gerar o formulário que exibirá os registros de atendimento
 * encerrados
 *
 * @author Raphael Rossiter
 * @date 17/08/2006
 */
public class PesquisarRegistrosAtendimentoEncerradosLocalOcorrenciaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idSolicitacaoTipo;
	
	private String descricaoSolicitacaoTipo;
	
	private String idSolicitacaoTipoEspecificacao;
	
	private String descricaoSolicitacaoTipoEspecificacao;
	
	private String idImovel;
	
	private String inscricaoImovel;
	
	private String raSelected;

	public String getDescricaoSolicitacaoTipo() {
		return descricaoSolicitacaoTipo;
	}

	public void setDescricaoSolicitacaoTipo(String descricaoSolicitacaoTipo) {
		this.descricaoSolicitacaoTipo = descricaoSolicitacaoTipo;
	}

	public String getDescricaoSolicitacaoTipoEspecificacao() {
		return descricaoSolicitacaoTipoEspecificacao;
	}

	public void setDescricaoSolicitacaoTipoEspecificacao(
			String descricaoSolicitacaoTipoEspecificacao) {
		this.descricaoSolicitacaoTipoEspecificacao = descricaoSolicitacaoTipoEspecificacao;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdSolicitacaoTipo() {
		return idSolicitacaoTipo;
	}

	public void setIdSolicitacaoTipo(String idSolicitacaoTipo) {
		this.idSolicitacaoTipo = idSolicitacaoTipo;
	}

	public String getIdSolicitacaoTipoEspecificacao() {
		return idSolicitacaoTipoEspecificacao;
	}

	public void setIdSolicitacaoTipoEspecificacao(
			String idSolicitacaoTipoEspecificacao) {
		this.idSolicitacaoTipoEspecificacao = idSolicitacaoTipoEspecificacao;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getRaSelected() {
		return raSelected;
	}

	public void setRaSelected(String raSelected) {
		this.raSelected = raSelected;
	}
	
	

	
}
