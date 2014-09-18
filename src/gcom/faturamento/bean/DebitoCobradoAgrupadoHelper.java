package gcom.faturamento.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * [UC ] 
 * @author Tiago Moreno
 * @date 29/08/2007
 */
public class DebitoCobradoAgrupadoHelper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idDebitoTipo;
	private String descricaoDebitoTipo;
	private Integer numeroPrestacao;
	private Integer numeroPrestacaoDebito;
	private BigDecimal valorDebito;
	
	public DebitoCobradoAgrupadoHelper(Integer idDebitoTipo, String descricaoDebitoTipo, Integer numeroPrestacao, Integer numeroPrestacaoDebito, BigDecimal valorDebito) {
		
		
		this.idDebitoTipo = idDebitoTipo;
		this.descricaoDebitoTipo = descricaoDebitoTipo;
		this.numeroPrestacao = numeroPrestacao;
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
		this.valorDebito = valorDebito;
	}
	
	public DebitoCobradoAgrupadoHelper() {
	
	}

	public String getDescricaoDebitoTipo() {
		return descricaoDebitoTipo;
	}

	public void setDescricaoDebitoTipo(String descricaoDebitoTipo) {
		this.descricaoDebitoTipo = descricaoDebitoTipo;
	}

	public Integer getIdDebitoTipo() {
		return idDebitoTipo;
	}

	public void setIdDebitoTipo(Integer idDebitoTipo) {
		this.idDebitoTipo = idDebitoTipo;
	}

	public Integer getNumeroPrestacao() {
		return numeroPrestacao;
	}

	public void setNumeroPrestacao(Integer numeroPrestacao) {
		this.numeroPrestacao = numeroPrestacao;
	}

	public Integer getNumeroPrestacaoDebito() {
		return numeroPrestacaoDebito;
	}

	public void setNumeroPrestacaoDebito(Integer numeroPrestacaoDebito) {
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

		
	
	
	
}
