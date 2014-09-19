package gcom.gui.cobranca.spcserasa;

import java.math.BigDecimal;

public class NegativacaoHelper {
	
	private Integer idSituacaoCobranca;
	
	private Integer idNegativador; 
	
	private String descricao;

	private Integer somatorioQuantidadeInclusoes;

	private BigDecimal somatorioValorDebito;

	private BigDecimal somatorioValorPendente;
	
	private BigDecimal somatorioValorPago;
	
	private BigDecimal somatorioValorParcelado;
	
	private BigDecimal somatorioValorCancelado;
	
	private BigDecimal percentualQtd;
	
	private BigDecimal percentualValor;
	
	private BigDecimal valorDinamico;
	
	//****************************************************
	// RM3755
	// Autor: Ivan Sergio
	// Data 14/01/2011
	//****************************************************
	private BigDecimal somatorioValorPotencial;
	private BigDecimal somatorioValorFactivel;
	private BigDecimal somatorioValorLigado;
	private BigDecimal somatorioValorEmAnalise;
	private BigDecimal somatorioValorCortado;
	private BigDecimal somatorioValorSuprimido;
	
	private Integer quantidadeInclusao;
	//****************************************************	
	
	public Integer getQuantidadeInclusao() {
		return quantidadeInclusao;
	}
	public void setQuantidadeInclusao(Integer quantidadeInclusao) {
		this.quantidadeInclusao = quantidadeInclusao;
	}
	public NegativacaoHelper() {
		super();
		
	}
	// [UC676 - SB0002]
	public NegativacaoHelper(Integer idSituacaoCobranca, String descricao, Integer somatorioQuantidadeInclusoes,
			BigDecimal somatorioValorDebito, BigDecimal somatorioValorPendente, BigDecimal somatorioValorPago,
			BigDecimal somatorioValorParcelado, BigDecimal somatoriovalorCancelado){
		this.idSituacaoCobranca = idSituacaoCobranca;
		this.descricao = descricao;
		this.somatorioQuantidadeInclusoes = somatorioQuantidadeInclusoes;
		this.somatorioValorDebito = somatorioValorDebito;
		this.somatorioValorPendente = somatorioValorPendente;
		this.somatorioValorPago = somatorioValorPago;
		this.somatorioValorParcelado = somatorioValorParcelado;
		this.somatorioValorCancelado = somatoriovalorCancelado;
	}
	// [UC676 - SB0001]
	public NegativacaoHelper(Integer idNegativador, Integer somatorioQuantidadeInclusoes,
			BigDecimal somatorioValorDebito, BigDecimal somatorioValorPendente, BigDecimal somatorioValorPago,
			BigDecimal somatorioValorParcelado, BigDecimal somatoriovalorCancelado){
		this.idNegativador = idNegativador;
		this.somatorioQuantidadeInclusoes = somatorioQuantidadeInclusoes;
		this.somatorioValorDebito = somatorioValorDebito;
		this.somatorioValorPendente = somatorioValorPendente;
		this.somatorioValorPago = somatorioValorPago;
		this.somatorioValorParcelado = somatorioValorParcelado;
		this.somatorioValorCancelado = somatoriovalorCancelado;
	}

	
	
	public Integer getIdSituacaoCobranca() {
		return idSituacaoCobranca;
	}

	public void setIdSituacaoCobranca(Integer idSituacaoCobranca) {
		this.idSituacaoCobranca = idSituacaoCobranca;
	}

	public Integer getSomatorioQuantidadeInclusoes() {
		return somatorioQuantidadeInclusoes;
	}

	public void setSomatorioQuantidadeInclusoes(Integer somatorioQuantidadeInclusoes) {
		this.somatorioQuantidadeInclusoes = somatorioQuantidadeInclusoes;
	}

	public BigDecimal getSomatorioValorCancelado() {
		return somatorioValorCancelado;
	}

	public void setSomatorioValorCancelado(BigDecimal somatoriovalorCancelado) {
		this.somatorioValorCancelado = somatoriovalorCancelado;
	}

	public BigDecimal getSomatorioValorDebito() {
		return somatorioValorDebito;
	}

	public void setSomatorioValorDebito(BigDecimal somatorioValorDebito) {
		this.somatorioValorDebito = somatorioValorDebito;
	}

	public BigDecimal getSomatorioValorPago() {
		return somatorioValorPago;
	}

	public void setSomatorioValorPago(BigDecimal somatorioValorPago) {
		this.somatorioValorPago = somatorioValorPago;
	}

	public BigDecimal getSomatorioValorParcelado() {
		return somatorioValorParcelado;
	}

	public void setSomatorioValorParcelado(BigDecimal somatorioValorParcelado) {
		this.somatorioValorParcelado = somatorioValorParcelado;
	}

	public BigDecimal getSomatorioValorPendente() {
		return somatorioValorPendente;
	}

	public void setSomatorioValorPendente(BigDecimal somatorioValorPendente) {
		this.somatorioValorPendente = somatorioValorPendente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getIdNegativador() {
		return idNegativador;
	}

	public void setIdNegativador(Integer idNegativador) {
		this.idNegativador = idNegativador;
	}
	
	public BigDecimal getPercentualQtd() {
		return percentualQtd;
	}

	public void setPercentualQtd(BigDecimal percentualQtd) {
		this.percentualQtd = percentualQtd;
	}

	public BigDecimal getPercentualValor() {
		return percentualValor;
	}

	public void setPercentualValor(BigDecimal percentualValor) {
		this.percentualValor = percentualValor;
	}
	
	public BigDecimal getValorDinamico() {
		return valorDinamico;
	}
	public void setValorDinamico(BigDecimal valorDinamico) {
		this.valorDinamico = valorDinamico;
	}
	public BigDecimal getSomatorioValorCortado() {
		return somatorioValorCortado;
	}
	public void setSomatorioValorCortado(BigDecimal somatorioValorCortado) {
		this.somatorioValorCortado = somatorioValorCortado;
	}
	public BigDecimal getSomatorioValorEmAnalise() {
		return somatorioValorEmAnalise;
	}
	public void setSomatorioValorEmAnalise(BigDecimal somatorioValorEmAnalise) {
		this.somatorioValorEmAnalise = somatorioValorEmAnalise;
	}
	public BigDecimal getSomatorioValorFactivel() {
		return somatorioValorFactivel;
	}
	public void setSomatorioValorFactivel(BigDecimal somatorioValorFactivel) {
		this.somatorioValorFactivel = somatorioValorFactivel;
	}
	public BigDecimal getSomatorioValorLigado() {
		return somatorioValorLigado;
	}
	public void setSomatorioValorLigado(BigDecimal somatorioValorLigado) {
		this.somatorioValorLigado = somatorioValorLigado;
	}
	public BigDecimal getSomatorioValorPotencial() {
		return somatorioValorPotencial;
	}
	public void setSomatorioValorPotencial(BigDecimal somatorioValorPotencial) {
		this.somatorioValorPotencial = somatorioValorPotencial;
	}
	public BigDecimal getSomatorioValorSuprimido() {
		return somatorioValorSuprimido;
	}
	public void setSomatorioValorSuprimido(BigDecimal somatorioValorSuprimido) {
		this.somatorioValorSuprimido = somatorioValorSuprimido;
	}
}
