package gcom.relatorio.atendimentopublico.ordemservico;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * [UC99999] Emitir Relatorio de Ordem de Serviço de Fiscalizacao
 * 
 * 
 * @author Paulo Diniz
 *
 * @date 15/08/2011
 */
public class RelatorioOrdemServicoFiscalizacaoAnaliticoBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String unidadeNegocio;
	private String gerenciaRegional;
	private String localidade;
	private BigDecimal quantidade;
	private String dataGeracaoFiscalizacao;
	private String dataEncerramentoFiscalizacao;
	private String tipoRetornoFiscalizacao;
	private String motivoEncerFiscalizacao;
	private String imovelFiscalizacao;
	private String clienteFiscalizada;
	private String dataExecucaoFiscalizada;
	private String tipoServicoFiscalizada;
	private String aceitacaoFiscalizada;
	private String motivoEncerFiscalizada;
	private String tipoRetornoFiscalizada;
	private String mesAnoReferencia;
	private String idFiscalizacao;
	private String idFiscalizada;
	
	public RelatorioOrdemServicoFiscalizacaoAnaliticoBean() {
		super();
	}


	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}


	public String getDataGeracaoFiscalizacao() {
		return dataGeracaoFiscalizacao;
	}


	public void setDataGeracaoFiscalizacao(String dataGeracaoFiscalizacao) {
		this.dataGeracaoFiscalizacao = dataGeracaoFiscalizacao;
	}


	public String getDataEncerramentoFiscalizacao() {
		return dataEncerramentoFiscalizacao;
	}


	public void setDataEncerramentoFiscalizacao(String dataEncerramentoFiscalizacao) {
		this.dataEncerramentoFiscalizacao = dataEncerramentoFiscalizacao;
	}


	public String getTipoRetornoFiscalizacao() {
		return tipoRetornoFiscalizacao;
	}


	public void setTipoRetornoFiscalizacao(String tipoRetornoFiscalizacao) {
		this.tipoRetornoFiscalizacao = tipoRetornoFiscalizacao;
	}


	public String getMotivoEncerFiscalizacao() {
		return motivoEncerFiscalizacao;
	}


	public void setMotivoEncerFiscalizacao(String motivoEncerFiscalizacao) {
		this.motivoEncerFiscalizacao = motivoEncerFiscalizacao;
	}


	public String getImovelFiscalizacao() {
		return imovelFiscalizacao;
	}


	public void setImovelFiscalizacao(String imovelFiscalizacao) {
		this.imovelFiscalizacao = imovelFiscalizacao;
	}


	public String getClienteFiscalizada() {
		return clienteFiscalizada;
	}


	public void setClienteFiscalizada(String clienteFiscalizada) {
		this.clienteFiscalizada = clienteFiscalizada;
	}


	public String getTipoServicoFiscalizada() {
		return tipoServicoFiscalizada;
	}


	public void setTipoServicoFiscalizada(String tipoServicoFiscalizada) {
		this.tipoServicoFiscalizada = tipoServicoFiscalizada;
	}


	public String getAceitacaoFiscalizada() {
		return aceitacaoFiscalizada;
	}


	public void setAceitacaoFiscalizada(String aceitacaoFiscalizada) {
		this.aceitacaoFiscalizada = aceitacaoFiscalizada;
	}


	public String getMotivoEncerFiscalizada() {
		return motivoEncerFiscalizada;
	}


	public void setMotivoEncerFiscalizada(String motivoEncerFiscalizada) {
		this.motivoEncerFiscalizada = motivoEncerFiscalizada;
	}


	public String getTipoRetornoFiscalizada() {
		return tipoRetornoFiscalizada;
	}


	public void setTipoRetornoFiscalizada(String tipoRetornoFiscalizada) {
		this.tipoRetornoFiscalizada = tipoRetornoFiscalizada;
	}


	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}


	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}


	public String getDataExecucaoFiscalizada() {
		return dataExecucaoFiscalizada;
	}


	public void setDataExecucaoFiscalizada(String dataExecucaoFiscalizada) {
		this.dataExecucaoFiscalizada = dataExecucaoFiscalizada;
	}


	public String getIdFiscalizacao() {
		return idFiscalizacao;
	}


	public void setIdFiscalizacao(String idFiscalizacao) {
		this.idFiscalizacao = idFiscalizacao;
	}


	public String getIdFiscalizada() {
		return idFiscalizada;
	}


	public void setIdFiscalizada(String idFiscalizada) {
		this.idFiscalizada = idFiscalizada;
	}

}
