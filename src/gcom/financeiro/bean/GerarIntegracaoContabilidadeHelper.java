package gcom.financeiro.bean;

import java.math.BigDecimal;
import java.util.Date;

public class GerarIntegracaoContabilidadeHelper {

	private Short numeroCartao;
	
	private String mesDiaInformado;
	
	private Integer idTipoLancamento;
	
	private Integer folha;
	
	private Integer indicadorLinha;
	
	private String numeroPrefixoContabil;
	
	private Integer numeroRazaoSocial;
	
	private Integer resto;
	
	private Integer cont;
	
	private Integer custo;
	
	private Integer analise;
	
	private Integer digito;
	
	private Integer terceiros;
	
	private Integer referencial;
	
	private String mesInformado;
	
	private BigDecimal valorLancamento;
	
	private Integer indicadorDebitoConta;
	
	private Integer cartao2;
	
	private Date dataInformada;
	
	private Integer versao;
	
	private Integer diaInformado;
	
	private Integer sequencial;
	
	private Integer idLocalidade;
	
	private Integer contaCredito;
	
	private Integer contaDebito;
	
	private String creditoDebito;
	
	private Integer codigoCentroCustoCredito;
	
	private Integer codigoCentroCustoDebito;
	
	private String numeroContaCredito;
	
	private String numeroContaDebito;
	
	private String indicadorCentroCusto;
	
	private String numeroHistoricoCreditoOuCredito;
	
	
	
	public String getNumeroContaCredito() {
		return numeroContaCredito;
	}

	public void setNumeroContaCredito(String numeroContaCredito) {
		this.numeroContaCredito = numeroContaCredito;
	}

	public String getNumeroContaDebito() {
		return numeroContaDebito;
	}

	public void setNumeroContaDebito(String numeroContaDebito) {
		this.numeroContaDebito = numeroContaDebito;
	}

	public Integer getContaCredito() {
		return contaCredito;
	}

	public void setContaCredito(Integer contaCredito) {
		this.contaCredito = contaCredito;
	}

	public Integer getContaDebito() {
		return contaDebito;
	}

	public void setContaDebito(Integer contaDebito) {
		this.contaDebito = contaDebito;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public GerarIntegracaoContabilidadeHelper(){}

	public Integer getAnalise() {
		return analise;
	}

	public void setAnalise(Integer analise) {
		this.analise = analise;
	}

	public Integer getCartao2() {
		return cartao2;
	}

	public void setCartao2(Integer cartao2) {
		this.cartao2 = cartao2;
	}

	public Integer getCont() {
		return cont;
	}

	public void setCont(Integer cont) {
		this.cont = cont;
	}

	public Integer getCusto() {
		return custo;
	}

	public void setCusto(Integer custo) {
		this.custo = custo;
	}

	public Date getDataInformada() {
		return dataInformada;
	}

	public void setDataInformada(Date dataInformada) {
		this.dataInformada = dataInformada;
	}

	public Integer getDiaInformado() {
		return diaInformado;
	}

	public void setDiaInformado(Integer diaInformado) {
		this.diaInformado = diaInformado;
	}

	public Integer getDigito() {
		return digito;
	}

	public void setDigito(Integer digito) {
		this.digito = digito;
	}

	public Integer getFolha() {
		return folha;
	}

	public void setFolha(Integer folha) {
		this.folha = folha;
	}

	public Integer getIdTipoLancamento() {
		return idTipoLancamento;
	}

	public void setIdTipoLancamento(Integer idTipoLancamento) {
		this.idTipoLancamento = idTipoLancamento;
	}

	public Integer getIndicadorDebitoConta() {
		return indicadorDebitoConta;
	}

	public void setIndicadorDebitoConta(Integer indicadorDebitoConta) {
		this.indicadorDebitoConta = indicadorDebitoConta;
	}

	public Integer getIndicadorLinha() {
		return indicadorLinha;
	}

	public void setIndicadorLinha(Integer indicadorLinha) {
		this.indicadorLinha = indicadorLinha;
	}

	public String getMesDiaInformado() {
		return mesDiaInformado;
	}

	public void setMesDiaInformado(String mesDiaInformado) {
		this.mesDiaInformado = mesDiaInformado;
	}

	public String getMesInformado() {
		return mesInformado;
	}

	public void setMesInformado(String mesInformado) {
		this.mesInformado = mesInformado;
	}

	public Short getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(Short numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNumeroPrefixoContabil() {
		return numeroPrefixoContabil;
	}

	public void setNumeroPrefixoContabil(String numeroPrefixoContabil) {
		this.numeroPrefixoContabil = numeroPrefixoContabil;
	}

	public Integer getNumeroRazaoSocial() {
		return numeroRazaoSocial;
	}

	public void setNumeroRazaoSocial(Integer numeroRazaoSocial) {
		this.numeroRazaoSocial = numeroRazaoSocial;
	}

	public Integer getReferencial() {
		return referencial;
	}

	public void setReferencial(Integer referencial) {
		this.referencial = referencial;
	}

	public Integer getResto() {
		return resto;
	}

	public void setResto(Integer resto) {
		this.resto = resto;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public Integer getTerceiros() {
		return terceiros;
	}

	public void setTerceiros(Integer terceiros) {
		this.terceiros = terceiros;
	}

	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}

	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public String getCreditoDebito() {
		return creditoDebito;
	}

	public void setCreditoDebito(String creditoDebito) {
		this.creditoDebito = creditoDebito;
	}

	public Integer getCodigoCentroCustoCredito() {
		return codigoCentroCustoCredito;
	}

	public void setCodigoCentroCustoCredito(Integer codigoCentroCustoCredito) {
		this.codigoCentroCustoCredito = codigoCentroCustoCredito;
	}

	public Integer getCodigoCentroCustoDebito() {
		return codigoCentroCustoDebito;
	}

	public void setCodigoCentroCustoDebito(Integer codigoCentroCustoDebito) {
		this.codigoCentroCustoDebito = codigoCentroCustoDebito;
	}

	public String getIndicadorCentroCusto() {
		return indicadorCentroCusto;
	}

	public void setIndicadorCentroCusto(String indicadorCentroCusto) {
		this.indicadorCentroCusto = indicadorCentroCusto;
	}

	public String getNumeroHistoricoCreditoOuCredito() {
		return numeroHistoricoCreditoOuCredito;
	}

	public void setNumeroHistoricoCreditoOuCredito(
			String numeroHistoricoCreditoOuCredito) {
		this.numeroHistoricoCreditoOuCredito = numeroHistoricoCreditoOuCredito;
	}


}
