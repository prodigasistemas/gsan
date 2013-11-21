package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorForm;

public class ComandosAcaoCobrancaFiltrarActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String periodoReferenciaContaInicial;
	private String periodoReferenciaContaFinal;
	private String idGrupoCobranca;
	private String idAcaoCobranca;
	private String nomeOrdemServico;
	private String idOrdemServicoConsulta;
	private String idOrdemServico;
	private String motivoNaoAceitacao;
	private String observacao;

	public ComandosAcaoCobrancaFiltrarActionForm() {
		super();
	}
	
	public ComandosAcaoCobrancaFiltrarActionForm(String periodoReferenciaContaInicial,
			String periodoReferenciaContaFinal, String idGrupoCobranca) {
		super();
		this.periodoReferenciaContaInicial = periodoReferenciaContaInicial;
		this.periodoReferenciaContaFinal = periodoReferenciaContaFinal;
		this.idGrupoCobranca = idGrupoCobranca;
	}

	public String getPeriodoReferenciaContaFinal() {
		return periodoReferenciaContaFinal;
	}

	public void setPeriodoReferenciaContaFinal(String periodoReferenciaContaFinal) {
		this.periodoReferenciaContaFinal = periodoReferenciaContaFinal;
	}

	public String getPeriodoReferenciaContaInicial() {
		return periodoReferenciaContaInicial;
	}

	public void setPeriodoReferenciaContaInicial(
			String periodoReferenciaContaInicial) {
		this.periodoReferenciaContaInicial = periodoReferenciaContaInicial;
	}

	public String getIdGrupoCobranca() {
		return idGrupoCobranca;
	}

	public void setIdGrupoCobranca(String idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}

	public String getIdAcaoCobranca() {
		return idAcaoCobranca;
	}

	public void setIdAcaoCobranca(String idAcaoCobranca) {
		this.idAcaoCobranca = idAcaoCobranca;
	}

	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}

	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}

	public String getIdOrdemServicoConsulta() {
		return idOrdemServicoConsulta;
	}

	public void setIdOrdemServicoConsulta(String idOrdemServicoConsulta) {
		this.idOrdemServicoConsulta = idOrdemServicoConsulta;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getMotivoNaoAceitacao() {
		return motivoNaoAceitacao;
	}

	public void setMotivoNaoAceitacao(String motivoNaoAceitacao) {
		this.motivoNaoAceitacao = motivoNaoAceitacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
}
