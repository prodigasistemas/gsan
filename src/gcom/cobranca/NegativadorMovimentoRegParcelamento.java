package gcom.cobranca;

import gcom.cobranca.parcelamento.Parcelamento;
import gcom.interceptor.ControleAlteracao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class NegativadorMovimentoRegParcelamento  implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    
    private BigDecimal valorParceladoEntrada;
    
    private BigDecimal valorParcelado;
    
    private BigDecimal valorParceladoEntradaPago;
    
    private BigDecimal valorParceladoCancelado;
    
    private BigDecimal valorParceladoNaoCobrado;
    
    private BigDecimal valorParceladoCobradoPago;
    
    private BigDecimal valorParceladoCobradoNaoPago;
    
    private Short numeroPrestacoes;
    
    private Short numeroPrestacoesNaoCobradas;
    
    private Short numeroPrestacoesCobradasPagas;
    
    private Short numeroPrestacoesCobradasNaoPagas;
    
    private Short indicadorParcelamentoAtivo;
    
    private Date ultimaAlteracao;

    private NegativadorMovimentoReg negativadorMovimentoReg;
    
    private Parcelamento parcelamento;
    
    /** full constructor */
    public NegativadorMovimentoRegParcelamento(Integer id,
    		BigDecimal valorParceladoEntrada,
    		BigDecimal valorParcelado,
    		BigDecimal valorParceladoEntradaPago,
    		BigDecimal valorParceladoCancelado,
    		BigDecimal valorParceladoNaoCobrado,
    		BigDecimal valorParceladoCobradoPago,
    		BigDecimal valorParceladoCobradoNaoPago,
    		Short numeroPrestacoes,
    		Short numeroPrestacoesNaoCobradas,
    		Short numeroPrestacoesCobradasPagas,
    		Short numeroPrestacoesCobradasNaoPagas,
    		Short indicadorParcelamentoAtivo,
    		Date ultimaAlteracao,
    		NegativadorMovimentoReg negativadorMovimentoReg,
    		Parcelamento parcelamento) {

    	this.id = id;
    	this.valorParceladoEntrada = valorParceladoEntrada;
    	this.valorParcelado = valorParcelado;
    	this.valorParceladoEntradaPago = valorParceladoEntradaPago;
    	this.valorParceladoCancelado = valorParceladoCancelado;
    	this.valorParceladoNaoCobrado = valorParceladoNaoCobrado;
    	this.valorParceladoCobradoPago = valorParceladoCobradoPago;
    	this.valorParceladoCobradoNaoPago = valorParceladoCobradoNaoPago;
    	this.numeroPrestacoes = numeroPrestacoes;
    	this.numeroPrestacoesNaoCobradas = numeroPrestacoesNaoCobradas;
    	this.numeroPrestacoesCobradasPagas = numeroPrestacoesCobradasPagas;
    	this.numeroPrestacoesCobradasNaoPagas = numeroPrestacoesCobradasNaoPagas;
    	this.indicadorParcelamentoAtivo = indicadorParcelamentoAtivo;
    	this.ultimaAlteracao = ultimaAlteracao;
    	this.negativadorMovimentoReg = negativadorMovimentoReg;
    	this.parcelamento = parcelamento;
    }

    /** default constructor */
    public NegativadorMovimentoRegParcelamento() {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorParcelamentoAtivo() {
		return indicadorParcelamentoAtivo;
	}

	public void setIndicadorParcelamentoAtivo(Short indicadorParcelamentoAtivo) {
		this.indicadorParcelamentoAtivo = indicadorParcelamentoAtivo;
	}

	public NegativadorMovimentoReg getNegativadorMovimentoReg() {
		return negativadorMovimentoReg;
	}

	public void setNegativadorMovimentoReg(
			NegativadorMovimentoReg negativadorMovimentoReg) {
		this.negativadorMovimentoReg = negativadorMovimentoReg;
	}

	public Short getNumeroPrestacoes() {
		return numeroPrestacoes;
	}

	public void setNumeroPrestacoes(Short numeroPrestacoes) {
		this.numeroPrestacoes = numeroPrestacoes;
	}

	public Short getNumeroPrestacoesCobradasNaoPagas() {
		return numeroPrestacoesCobradasNaoPagas;
	}

	public void setNumeroPrestacoesCobradasNaoPagas(
			Short numeroPrestacoesCobradasNaoPagas) {
		this.numeroPrestacoesCobradasNaoPagas = numeroPrestacoesCobradasNaoPagas;
	}

	public Short getNumeroPrestacoesCobradasPagas() {
		return numeroPrestacoesCobradasPagas;
	}

	public void setNumeroPrestacoesCobradasPagas(Short numeroPrestacoesCobradasPagas) {
		this.numeroPrestacoesCobradasPagas = numeroPrestacoesCobradasPagas;
	}

	public Short getNumeroPrestacoesNaoCobradas() {
		return numeroPrestacoesNaoCobradas;
	}

	public void setNumeroPrestacoesNaoCobradas(Short numeroPrestacoesNaoCobradas) {
		this.numeroPrestacoesNaoCobradas = numeroPrestacoesNaoCobradas;
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorParcelado() {
		return valorParcelado;
	}

	public void setValorParcelado(BigDecimal valorParcelado) {
		this.valorParcelado = valorParcelado;
	}

	public BigDecimal getValorParceladoCancelado() {
		return valorParceladoCancelado;
	}

	public void setValorParceladoCancelado(BigDecimal valorParceladoCancelado) {
		this.valorParceladoCancelado = valorParceladoCancelado;
	}

	public BigDecimal getValorParceladoCobradoNaoPago() {
		return valorParceladoCobradoNaoPago;
	}

	public void setValorParceladoCobradoNaoPago(
			BigDecimal valorParceladoCobradoNaoPago) {
		this.valorParceladoCobradoNaoPago = valorParceladoCobradoNaoPago;
	}

	public BigDecimal getValorParceladoCobradoPago() {
		return valorParceladoCobradoPago;
	}

	public void setValorParceladoCobradoPago(BigDecimal valorParceladoCobradoPago) {
		this.valorParceladoCobradoPago = valorParceladoCobradoPago;
	}

	public BigDecimal getValorParceladoEntrada() {
		return valorParceladoEntrada;
	}

	public void setValorParceladoEntrada(BigDecimal valorParceladoEntrada) {
		this.valorParceladoEntrada = valorParceladoEntrada;
	}

	public BigDecimal getValorParceladoEntradaPago() {
		return valorParceladoEntradaPago;
	}

	public void setValorParceladoEntradaPago(BigDecimal valorParceladoEntradaPago) {
		this.valorParceladoEntradaPago = valorParceladoEntradaPago;
	}

	public BigDecimal getValorParceladoNaoCobrado() {
		return valorParceladoNaoCobrado;
	}

	public void setValorParceladoNaoCobrado(BigDecimal valorParceladoNaoCobrado) {
		this.valorParceladoNaoCobrado = valorParceladoNaoCobrado;
	}

	
}
