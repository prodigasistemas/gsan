package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * 
 * Bean do [UC0227] Gerar Curva ABC Debitos 
 *
 * @author Ivan Sérgio
 * @date 03/09/2007
 */
public class RelatorioGerarCurvaAbcDebitosBean implements RelatorioBean {
	
	private String referenciaCobrancaInicial;
	private String referenciaCobrancaFinal;
	private String categoria;
	private String subCategoria;
	private String classificacao;
	private String situacaoAgua;
	private String intervaloMeses;
	private String idGerenciaRegional;
	private String nomeGerenciaRegional;
	private String idLocalidade;
	private String nomeLocalidade;
	private String idMunicipio;
	private String nomeMunicipio;
	private String idSetorComercial;
	private String codigoSetorComercial;
	private String nomeSetorComercial;
	
	// Parte de Debito Faixa Valoes
	private BigDecimal faixaInicial;
	private BigDecimal faixaFinal;
	private Integer idFaixa;
	
	private Integer quantidadeLigacoes = 0;
	private BigDecimal percLigacoes = new BigDecimal(0.00).setScale(2);
	private Integer ligacoesAcumuladas = 0;
	private BigDecimal percLigacoesAcumulado = new BigDecimal(0.00).setScale(2);
	
	private BigDecimal valor = new BigDecimal(0.00).setScale(2);
	private BigDecimal percValor = new BigDecimal(0.00).setScale(2);
	private BigDecimal valorAcumulado = new BigDecimal(0.00).setScale(2);
	private BigDecimal percValorAcumulado = new BigDecimal(0.00).setScale(2);
	private BigDecimal valorMedio = new BigDecimal(0.00).setScale(2);
	
	private String idClassificacao;
	
	public String getReferenciaCobrancaFinal() {
		return referenciaCobrancaFinal;
	}
	public void setReferenciaCobrancaFinal(String referenciaCobrancaFinal) {
		this.referenciaCobrancaFinal = referenciaCobrancaFinal;
	}
	public String getReferenciaCobrancaInicial() {
		return referenciaCobrancaInicial;
	}
	public void setReferenciaCobrancaInicial(String referenciaCobrancaInicial) {
		this.referenciaCobrancaInicial = referenciaCobrancaInicial;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public String getIntervaloMeses() {
		return intervaloMeses;
	}
	public void setIntervaloMeses(String intervaloMeses) {
		this.intervaloMeses = intervaloMeses;
	}
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}
	public String getSituacaoAgua() {
		return situacaoAgua;
	}
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}
	public String getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}
	
	// Formata a Situacao
	public String getSituacao() {
		String retorno = "";
		
		if (this.getSituacaoAgua() != "") {
			retorno = this.getSituacaoAgua() + " ";
		}
		
		if (this.getIntervaloMeses() != "") {
			retorno += this.getIntervaloMeses();
		}
		
		return retorno;
	}
	public BigDecimal getFaixaFinal() {
		return faixaFinal;
	}
	public void setFaixaFinal(BigDecimal faixaFinal) {
		this.faixaFinal = faixaFinal;
	}
	public BigDecimal getFaixaInicial() {
		return faixaInicial;
	}
	public void setFaixaInicial(BigDecimal faixaInicial) {
		this.faixaInicial = faixaInicial;
	}
	public Integer getIdFaixa() {
		return idFaixa;
	}
	public void setIdFaixa(Integer idFaixa) {
		this.idFaixa = idFaixa;
	}
	public Integer getLigacoesAcumuladas() {
		return ligacoesAcumuladas;
	}
	public void setLigacoesAcumuladas(Integer ligacoesAcumuladas) {
		this.ligacoesAcumuladas = ligacoesAcumuladas;
	}
	public BigDecimal getPercLigacoes() {
		return percLigacoes;
	}
	public void setPercLigacoes(BigDecimal percLigacoes) {
		this.percLigacoes = percLigacoes;
	}
	public BigDecimal getPercLigacoesAcumulado() {
		return percLigacoesAcumulado;
	}
	public void setPercLigacoesAcumulado(BigDecimal percLigacoesAcumulado) {
		this.percLigacoesAcumulado = percLigacoesAcumulado;
	}
	public BigDecimal getPercValor() {
		return percValor;
	}
	public void setPercValor(BigDecimal percValor) {
		this.percValor = percValor;
	}
	public BigDecimal getPercValorAcumulado() {
		return percValorAcumulado;
	}
	public void setPercValorAcumulado(BigDecimal percValorAcumulado) {
		this.percValorAcumulado = percValorAcumulado;
	}
	public Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}
	public void setQuantidadeLigacoes(Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getValorAcumulado() {
		return valorAcumulado;
	}
	public void setValorAcumulado(BigDecimal valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}
	public BigDecimal getValorMedio() {
		return valorMedio;
	}
	public void setValorMedio(BigDecimal valorMedio) {
		this.valorMedio = valorMedio;
	}
	public String getIdClassificacao() {
		return idClassificacao;
	}
	public void setIdClassificacao(String idClassificacao) {
		this.idClassificacao = idClassificacao;
	}
	
	// Formata a descricao da classificacao
	public String getDescricaoClassificacao() {
		String retorno = "";
		
		if (this.classificacao.trim().equalsIgnoreCase("REGIONAL")) {
			retorno = this.idGerenciaRegional + " - " + this.nomeGerenciaRegional;
		}else if (this.classificacao.trim().equalsIgnoreCase("LOCAL")){
			retorno = this.idGerenciaRegional + " - " + this.nomeGerenciaRegional + " - " +
					  this.nomeLocalidade;
		}else if (this.classificacao.trim().equals("MUNICIPIO")){
			retorno = this.idGerenciaRegional + " - " + this.nomeGerenciaRegional + " - " +
					  this.nomeMunicipio;
		}else if (this.classificacao.trim().equals("ESTADO")){
			retorno = "ESTADO";
		}else {
			retorno = this.idGerenciaRegional + " - " + this.nomeGerenciaRegional + " - " +
			  		  this.nomeLocalidade + " - " + this.codigoSetorComercial + " - " +
			  		  this.nomeSetorComercial;
		}
		
		return retorno;
	}
	
	// Formata a Referencia cobranca
	public String getReferenciaCobranca() {
		String retorno = "";
		
		if (this.getReferenciaCobrancaInicial() != null &&
				!this.getReferenciaCobrancaInicial().equals("") &&
				this.getReferenciaCobrancaFinal() != null &&
				!this.getReferenciaCobrancaInicial().equals("")) {
			
			retorno = this.getReferenciaCobrancaInicial() + " à " + this.getReferenciaCobrancaFinal();
		}

		return retorno;
	}
}
