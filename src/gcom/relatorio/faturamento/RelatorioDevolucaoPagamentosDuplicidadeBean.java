package gcom.relatorio.faturamento;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * classe responsável por criar o Relatório Devolução dos Pagamentos em Duplicidade.
 * 
 * [UC1129] Gerar Relatório Devolução dos Pagamentos em Duplicidade
 * 
 * @author Hugo Leonardo
 *
 * @date 10/03/2011
 */
public class RelatorioDevolucaoPagamentosDuplicidadeBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String localidade;
	private String nomeLocalidade;
	private String numeroRA;
	private String matricula;
	private String mesAnoPagamentoDuplicidade;
	private BigDecimal valorPagamentoDuplicidade;
	private String mesAnoConta;
	private BigDecimal valorConta;
	private BigDecimal creditoRealizado;
	private BigDecimal creditoARealizar;
	private String dataAtualizacao;
	private String gerencia;
	private String nomeGerencia;
	private String unidade;
	private String nomeUnidade;

	public RelatorioDevolucaoPagamentosDuplicidadeBean(){
		
	}
	
	public RelatorioDevolucaoPagamentosDuplicidadeBean(String gerencia, String nomeGerencia, 
			String unidade, String nomeUnidade, 
			String localidade, String nomeLocalidade, 
			String numeroRA, String matricula, String mesAnoPagamentoDuplicidade, 
			BigDecimal valorPagamentoDuplicidade, String mesAnoConta, BigDecimal valorConta,
			BigDecimal creditoRealizado, BigDecimal creditoARealizar, String dataAtualizacao) {
		
		this.gerencia = gerencia;
		this.nomeGerencia = nomeGerencia;
		this.unidade = unidade;
		this.nomeUnidade = nomeUnidade;
		this.localidade = localidade;
		this.nomeLocalidade = nomeLocalidade;
		this.numeroRA = numeroRA;
		this.matricula = matricula;
		this.mesAnoPagamentoDuplicidade = mesAnoPagamentoDuplicidade;
		this.valorPagamentoDuplicidade = valorPagamentoDuplicidade;
		this.mesAnoConta = mesAnoConta;
		this.valorConta = valorConta;
		this.creditoRealizado = creditoRealizado;
		this.creditoARealizar = creditoARealizar;
		this.dataAtualizacao = dataAtualizacao;
	}

	public BigDecimal getCreditoARealizar() {
		return creditoARealizar;
	}

	public void setCreditoARealizar(BigDecimal creditoARealizar) {
		this.creditoARealizar = creditoARealizar;
	}

	public BigDecimal getCreditoRealizado() {
		return creditoRealizado;
	}

	public void setCreditoRealizado(BigDecimal creditoRealizado) {
		this.creditoRealizado = creditoRealizado;
	}

	public String getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMesAnoConta() {
		return mesAnoConta;
	}

	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}

	public String getMesAnoPagamentoDuplicidade() {
		return mesAnoPagamentoDuplicidade;
	}

	public void setMesAnoPagamentoDuplicidade(String mesAnoPagamentoDuplicidade) {
		this.mesAnoPagamentoDuplicidade = mesAnoPagamentoDuplicidade;
	}

	public String getNumeroRA() {
		return numeroRA;
	}

	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}

	public BigDecimal getValorConta() {
		return valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public BigDecimal getValorPagamentoDuplicidade() {
		return valorPagamentoDuplicidade;
	}

	public void setValorPagamentoDuplicidade(BigDecimal valorPagamentoDuplicidade) {
		this.valorPagamentoDuplicidade = valorPagamentoDuplicidade;
	}

	public String getGerencia() {
		return gerencia;
	}

	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}

	public String getNomeGerencia() {
		return nomeGerencia;
	}

	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
}
