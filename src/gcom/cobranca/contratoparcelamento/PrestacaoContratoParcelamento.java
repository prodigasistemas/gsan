package gcom.cobranca.contratoparcelamento;

import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PrestacaoContratoParcelamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;
	
	private Integer numero;
	
	private BigDecimal valor;
	
	private BigDecimal valorPagamento;
	
	private Date dataVencimento;
	
	private Date ultimaAlteracao;
	
	private Date dataPagamento;
	
	private ContratoParcelamento contratoParcelamento;
	
	
	//Variáveis 'Transient' de controle para Manter Contrato Parcelamento Item. 
	private boolean prestacaoPaga;
	private Integer itensPagos;
	
	/** default constructor */
	public PrestacaoContratoParcelamento() {
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public ContratoParcelamento getContratoParcelamento() {
		return contratoParcelamento;
	}

	public void setContratoParcelamento(ContratoParcelamento contratoParcelamento) {
		this.contratoParcelamento = contratoParcelamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Integer getItensPagos() {
		return itensPagos;
	}

	public void setItensPagos(Integer itensPagos) {
		this.itensPagos = itensPagos;
	}
	
	public String getDataPagamentoFormatada(){
		return Util.formatarData(this.dataPagamento);
	}
	
	public String getDataVencimentoFormatada(){
		return Util.formatarData(this.dataVencimento);
	}

	public boolean getPrestacaoPaga() {
		return prestacaoPaga;
	}

	public void setPrestacaoPaga(boolean prestacaoPaga) {
		this.prestacaoPaga = prestacaoPaga;
	}

}
