package gcom.arrecadacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MovimentoCartaoRejeita implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String numeroCartao;
	
	private Date dataCompra;
	
	private BigDecimal valorVenda;
	
	private Short numeroParcela;
	
	private Short numeroParcelaDebito;
	
	private Date ultimaAlteracao;
	
	private ArrecadadorMovimentoItem arrecadadorMovimentoItem;
	
	public MovimentoCartaoRejeita(){}

	public ArrecadadorMovimentoItem getArrecadadorMovimentoItem() {
		return arrecadadorMovimentoItem;
	}

	public void setArrecadadorMovimentoItem(
			ArrecadadorMovimentoItem arrecadadorMovimentoItem) {
		this.arrecadadorMovimentoItem = arrecadadorMovimentoItem;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public Short getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Short numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public Short getNumeroParcelaDebito() {
		return numeroParcelaDebito;
	}

	public void setNumeroParcelaDebito(Short numeroParcelaDebito) {
		this.numeroParcelaDebito = numeroParcelaDebito;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
}
