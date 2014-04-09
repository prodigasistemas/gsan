package gcom.faturamento.bean;

import java.io.Serializable;

/** @author Hibernate CodeGenerator */
public class FaturaItemClienteResponsavelHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String matricula;
	private String consumo;
	private String valor;
	
	//Inicio - Usado somente no relatório RelatorioRelacaoAnaliticaFaturas
	private String inscricao;
	private String categoria;
	private String qtdEconomias;
	private String leituraAnterior;
	private String leituraAtual;
	private String media;
	private String consumoFaturado;
	private String endereco;
	private String dataLeitura;
	private String dataVencimento;
	private String consumoAgua;
	private String rateioAgua;
	private String valorAgua;
	private String consumoEsgoto;
	private String rateioEsgoto;
	private String valorEsgoto;
	private String debitoCobrado;
	private String creditoRealizado;
	private String valorImpostos;
	//Fim - Usado somente no relatório RelatorioRelacaoAnaliticaFaturas

	public FaturaItemClienteResponsavelHelper() {}
	
	public FaturaItemClienteResponsavelHelper(
			String nome,
			String matricula,
			String consumo,
			String valor) {
		
			this.nome = nome;
			this.matricula = matricula;
			this.consumo = consumo;
			this.valor = valor;
	}

	public String getValorImpostos() {
		return valorImpostos;
	}

	public void setValorImpostos(String valorImpostos) {
		this.valorImpostos = valorImpostos;
	}

	public String getConsumo() {
		return consumo;
	}

	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getConsumoAgua() {
		return consumoAgua;
	}

	public void setConsumoAgua(String consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public String getConsumoEsgoto() {
		return consumoEsgoto;
	}

	public void setConsumoEsgoto(String consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	public String getConsumoFaturado() {
		return consumoFaturado;
	}

	public void setConsumoFaturado(String consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}

	public String getCreditoRealizado() {
		return creditoRealizado;
	}

	public void setCreditoRealizado(String creditoRealizado) {
		this.creditoRealizado = creditoRealizado;
	}

	public String getDataLeitura() {
		return dataLeitura;
	}

	public void setDataLeitura(String dataLeitura) {
		this.dataLeitura = dataLeitura;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getDebitoCobrado() {
		return debitoCobrado;
	}

	public void setDebitoCobrado(String debitoCobrado) {
		this.debitoCobrado = debitoCobrado;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public String getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getQtdEconomias() {
		return qtdEconomias;
	}

	public void setQtdEconomias(String qtdEconomias) {
		this.qtdEconomias = qtdEconomias;
	}

	public String getRateioAgua() {
		return rateioAgua;
	}

	public void setRateioAgua(String rateioAgua) {
		this.rateioAgua = rateioAgua;
	}

	public String getRateioEsgoto() {
		return rateioEsgoto;
	}

	public void setRateioEsgoto(String rateioEsgoto) {
		this.rateioEsgoto = rateioEsgoto;
	}

	public String getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(String valorAgua) {
		this.valorAgua = valorAgua;
	}

	public String getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(String valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
