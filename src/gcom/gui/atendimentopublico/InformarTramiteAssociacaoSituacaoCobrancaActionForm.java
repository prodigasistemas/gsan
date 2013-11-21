package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1159] Informar Tramite por Situação de Cobrança
 * 
 * @author Mariana Victor
 * @date 14/04/2011
 */
public class InformarTramiteAssociacaoSituacaoCobrancaActionForm extends
		ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idSituacaoCobranca;
	
	private String descricaoSituacaoCobranca;
	
	private String idTipoSolicitacao;
	
	private String idTipoEspecificacao;
	
	private String idUnidadeAtendimento;
	
	private String descricaoUnidadeAtendimento;

	public String getIdSituacaoCobranca() {
		return idSituacaoCobranca;
	}

	public void setIdSituacaoCobranca(String idSituacaoCobranca) {
		this.idSituacaoCobranca = idSituacaoCobranca;
	}

	public String getDescricaoSituacaoCobranca() {
		return descricaoSituacaoCobranca;
	}

	public void setDescricaoSituacaoCobranca(String descricaoSituacaoCobranca) {
		this.descricaoSituacaoCobranca = descricaoSituacaoCobranca;
	}

	public String getIdTipoSolicitacao() {
		return idTipoSolicitacao;
	}

	public void setIdTipoSolicitacao(String idTipoSolicitacao) {
		this.idTipoSolicitacao = idTipoSolicitacao;
	}

	public String getIdTipoEspecificacao() {
		return idTipoEspecificacao;
	}

	public void setIdTipoEspecificacao(String idTipoEspecificacao) {
		this.idTipoEspecificacao = idTipoEspecificacao;
	}

	public String getDescricaoUnidadeAtendimento() {
		return descricaoUnidadeAtendimento;
	}

	public void setDescricaoUnidadeAtendimento(String descricaoUnidadeAtendimento) {
		this.descricaoUnidadeAtendimento = descricaoUnidadeAtendimento;
	}

	public String getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}

	public void setIdUnidadeAtendimento(String idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
	}

}
