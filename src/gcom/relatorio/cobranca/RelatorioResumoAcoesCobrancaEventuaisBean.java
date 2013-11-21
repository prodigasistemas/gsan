package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

public class RelatorioResumoAcoesCobrancaEventuaisBean implements RelatorioBean{
		
	private String nomeRelatorio;
	private String descricaoResumoAcaoCobranca;
	private String descricaoCobrancaAcaoSituacao;
	private String descricaoIndicadorAcaoDebito;
	private String dataRealizacaoEmitir;
	private String dataRealizacaoEncerrar;
	private String indicadorDefinitivo;
	private String situacaoAcao;
	private Integer quantidadeDocumentoAcao;
	private Integer quantidadeDocumentoDebito;
	private String porcentagemQuantidadeAcao;
	private String porcentagemQuantidadeDebito;
	private String valorDocumentoAcao;
	private String valorDocumentoDebito;
	private String porcentagemValorAcao;
	private String porcentagemValorDebito;
	private Integer somatorioQuantidadesDocumentos;
	private String somatorioValoresDocumentos;
	
	public String getNomeRelatorio() {
		return nomeRelatorio;
	}
	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}
	public String getDescricaoResumoAcaoCobranca() {
		return descricaoResumoAcaoCobranca;
	}
	public void setDescricaoResumoAcaoCobranca(String descricaoResumoAcaoCobranca) {
		this.descricaoResumoAcaoCobranca = descricaoResumoAcaoCobranca;
	}
	public String getDescricaoCobrancaAcaoSituacao() {
		return descricaoCobrancaAcaoSituacao;
	}
	public void setDescricaoCobrancaAcaoSituacao(
			String descricaoCobrancaAcaoSituacao) {
		this.descricaoCobrancaAcaoSituacao = descricaoCobrancaAcaoSituacao;
	}
	public String getDescricaoIndicadorAcaoDebito() {
		return descricaoIndicadorAcaoDebito;
	}
	public void setDescricaoIndicadorAcaoDebito(String descricaoIndicadorAcaoDebito) {
		this.descricaoIndicadorAcaoDebito = descricaoIndicadorAcaoDebito;
	}
	public String getDataRealizacaoEmitir() {
		return dataRealizacaoEmitir;
	}
	public void setDataRealizacaoEmitir(String dataRealizacaoEmitir) {
		this.dataRealizacaoEmitir = dataRealizacaoEmitir;
	}
	public String getDataRealizacaoEncerrar() {
		return dataRealizacaoEncerrar;
	}
	public void setDataRealizacaoEncerrar(String dataRealizacaoEncerrar) {
		this.dataRealizacaoEncerrar = dataRealizacaoEncerrar;
	}
	public String getIndicadorDefinitivo() {
		return indicadorDefinitivo;
	}
	public void setIndicadorDefinitivo(String indicadorDefinitivo) {
		this.indicadorDefinitivo = indicadorDefinitivo;
	}
	public String getSituacaoAcao() {
		return situacaoAcao;
	}
	public void setSituacaoAcao(String situacaoAcao) {
		this.situacaoAcao = situacaoAcao;
	}
	public Integer getQuantidadeDocumentoAcao() {
		return quantidadeDocumentoAcao;
	}
	public void setQuantidadeDocumentoAcao(Integer quantidadeDocumentoAcao) {
		this.quantidadeDocumentoAcao = quantidadeDocumentoAcao;
	}
	public Integer getQuantidadeDocumentoDebito() {
		return quantidadeDocumentoDebito;
	}
	public void setQuantidadeDocumentoDebito(Integer quantidadeDocumentoDebito) {
		this.quantidadeDocumentoDebito = quantidadeDocumentoDebito;
	}
	public String getPorcentagemQuantidadeAcao() {
		return porcentagemQuantidadeAcao;
	}
	public void setPorcentagemQuantidadeAcao(String porcentagemQuantidadeAcao) {
		this.porcentagemQuantidadeAcao = porcentagemQuantidadeAcao;
	}
	public String getPorcentagemQuantidadeDebito() {
		return porcentagemQuantidadeDebito;
	}
	public void setPorcentagemQuantidadeDebito(String porcentagemQuantidadeDebito) {
		this.porcentagemQuantidadeDebito = porcentagemQuantidadeDebito;
	}
	public String getValorDocumentoAcao() {
		return valorDocumentoAcao;
	}
	public void setValorDocumentoAcao(String valorDocumentoAcao) {
		this.valorDocumentoAcao = valorDocumentoAcao;
	}
	public String getValorDocumentoDebito() {
		return valorDocumentoDebito;
	}
	public void setValorDocumentoDebito(String valorDocumentoDebito) {
		this.valorDocumentoDebito = valorDocumentoDebito;
	}
	public String getPorcentagemValorAcao() {
		return porcentagemValorAcao;
	}
	public void setPorcentagemValorAcao(String porcentagemValorAcao) {
		this.porcentagemValorAcao = porcentagemValorAcao;
	}
	public String getPorcentagemValorDebito() {
		return porcentagemValorDebito;
	}
	public void setPorcentagemValorDebito(String porcentagemValorDebito) {
		this.porcentagemValorDebito = porcentagemValorDebito;
	}
	public Integer getSomatorioQuantidadesDocumentos() {
		return somatorioQuantidadesDocumentos;
	}
	public void setSomatorioQuantidadesDocumentos(
			Integer somatorioQuantidadesDocumentos) {
		this.somatorioQuantidadesDocumentos = somatorioQuantidadesDocumentos;
	}
	public String getSomatorioValoresDocumentos() {
		return somatorioValoresDocumentos;
	}
	public void setSomatorioValoresDocumentos(String somatorioValoresDocumentos) {
		this.somatorioValoresDocumentos = somatorioValoresDocumentos;
	}
	
}