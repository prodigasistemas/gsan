package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * [UCXXXX] - Gerar Relatório Analisar Metas Ciclo
 * 
 * @author Genival Barbosa
 * @date 21/07/2009
 */
public class RelatorioAnalisarMetasCicloBean implements RelatorioBean {

	private String idAcaoCobranca;
	
	private String anoMesCobranca;
	
	// P - Principal
	// G - Gerencia regional
	// U - Unidade de negocio
	// L - localidade
	
	private String nomeGerencia;
	
	private String idGerencia;
	
	private String nomeUnidade;
	
	private String idUnidade;
	
	private String nomeLocalidade;
	
	private String idLocalidade;
	
	
	
	private String metaOriginal;
	
	private String metaAtual;
	
	private String qtdImoveisSituacao;
		
	private String qtdTotalRealizada;
		
	private String valorTotalRealizada;
		
	private String qtdTotalRestante;
		
	private String valorTotalRestante;

	public RelatorioAnalisarMetasCicloBean(String nomeGerencia, String idGerencia, 
			String nomeUnidade, String idUnidade, String nomeLocalidade, String idLocalidade, 
			String metaOriginal, String metaAtual, 
			String qtdImoveisSituacao, String qtdTotalRealizada, 
			String valorTotalRealizada, String qtdTotalRestante, String valorTotalRestante, 
			String idAcaoCobranca, String anoMesCobranca) {
		
		
		this.nomeGerencia = nomeGerencia;
		this.idGerencia = idGerencia;
		this.nomeUnidade = nomeUnidade;
		this.idUnidade = idUnidade;
		this.nomeLocalidade = nomeLocalidade;
		this.metaOriginal = metaOriginal;
		this.metaAtual = metaAtual;
		this.qtdImoveisSituacao = qtdImoveisSituacao;
		this.qtdTotalRealizada = qtdTotalRealizada;
		this.valorTotalRealizada = valorTotalRealizada;
		this.qtdTotalRestante = qtdTotalRestante;
		this.valorTotalRestante = valorTotalRestante;
		this.idAcaoCobranca = idAcaoCobranca;
		this.anoMesCobranca = anoMesCobranca;
	}

	public String getMetaAtual() {
		return metaAtual;
	}

	public void setMetaAtual(String metaAtual) {
		this.metaAtual = metaAtual;
	}

	public String getMetaOriginal() {
		return metaOriginal;
	}

	public void setMetaOriginal(String metaOriginal) {
		this.metaOriginal = metaOriginal;
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

	public String getQtdImoveisSituacao() {
		return qtdImoveisSituacao;
	}

	public void setQtdImoveisSituacao(String qtdImoveisSituacao) {
		this.qtdImoveisSituacao = qtdImoveisSituacao;
	}

	public String getQtdTotalRealizada() {
		return qtdTotalRealizada;
	}

	public void setQtdTotalRealizada(String qtdTotalRealizada) {
		this.qtdTotalRealizada = qtdTotalRealizada;
	}

	public String getQtdTotalRestante() {
		return qtdTotalRestante;
	}

	public void setQtdTotalRestante(String qtdTotalRestante) {
		this.qtdTotalRestante = qtdTotalRestante;
	}

	public String getValorTotalRealizada() {
		return valorTotalRealizada;
	}

	public void setValorTotalRealizada(String valorTotalRealizada) {
		this.valorTotalRealizada = valorTotalRealizada;
	}

	public String getValorTotalRestante() {
		return valorTotalRestante;
	}

	public void setValorTotalRestante(String valorTotalRestante) {
		this.valorTotalRestante = valorTotalRestante;
	}

	public String getIdGerencia() {
		return idGerencia;
	}

	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getAnoMesCobranca() {
		return anoMesCobranca;
	}

	public void setAnoMesCobranca(String anoMesCobranca) {
		this.anoMesCobranca = anoMesCobranca;
	}

	public String getIdAcaoCobranca() {
		return idAcaoCobranca;
	}

	public void setIdAcaoCobranca(String idAcaoCobranca) {
		this.idAcaoCobranca = idAcaoCobranca;
	}
	
}
