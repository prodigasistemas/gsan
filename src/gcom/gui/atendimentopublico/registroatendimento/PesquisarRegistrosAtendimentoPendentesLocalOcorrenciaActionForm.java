package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Esta classe tem por finalidade gerar o formulário que exibirá os registros de atendimento
 * pendentes para um mesmo local de ocorrência
 *
 * @author Raphael Rossiter
 * @date 17/08/2006
 */
public class PesquisarRegistrosAtendimentoPendentesLocalOcorrenciaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idSolicitacaoTipo;
	
	private String descricaoSolicitacaoTipo;
	
	private String idSolicitacaTipoEspecificacao;
	
	private String descricaoSolicitacaTipoEspecificacao;
	
	private String raSelected;

	public String getDescricaoSolicitacaoTipo() {
		return descricaoSolicitacaoTipo;
	}

	public void setDescricaoSolicitacaoTipo(String descricaoSolicitacaoTipo) {
		this.descricaoSolicitacaoTipo = descricaoSolicitacaoTipo;
	}

	public String getDescricaoSolicitacaTipoEspecificacao() {
		return descricaoSolicitacaTipoEspecificacao;
	}

	public void setDescricaoSolicitacaTipoEspecificacao(
			String descricaoSolicitacaTipoEspecificacao) {
		this.descricaoSolicitacaTipoEspecificacao = descricaoSolicitacaTipoEspecificacao;
	}

	public String getIdSolicitacaoTipo() {
		return idSolicitacaoTipo;
	}

	public void setIdSolicitacaoTipo(String idSolicitacaoTipo) {
		this.idSolicitacaoTipo = idSolicitacaoTipo;
	}

	public String getIdSolicitacaTipoEspecificacao() {
		return idSolicitacaTipoEspecificacao;
	}

	public void setIdSolicitacaTipoEspecificacao(
			String idSolicitacaTipoEspecificacao) {
		this.idSolicitacaTipoEspecificacao = idSolicitacaTipoEspecificacao;
	}

	public String getRaSelected() {
		return raSelected;
	}

	public void setRaSelected(String raSelected) {
		this.raSelected = raSelected;
	}

}
