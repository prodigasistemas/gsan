package gcom.cobranca.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * [UC0214] Efetuar Parcelamento de Débitos 
 *
 * @author Roberta Costa
 * @date 28/03/2006
 */
public class OpcoesParcelamentoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Short indicadorQuantidadeParcelas;
	
	private Short quantidadePrestacao;
	
	private BigDecimal valorEntradaMinima;
	
	private BigDecimal valorPrestacao;

	private BigDecimal taxaJuros;
	
	private Short indicadorValorPrestacaoMaiorValorLimite;
	
	/**
	 * Construtor de OpcoesParcelamentoHelper 
	 */
	public OpcoesParcelamentoHelper() {
	}

	/**
	 * @return Retorna o campo valorEntradaMinima.
	 */
	public BigDecimal getValorEntradaMinima() {
		return valorEntradaMinima;
	}

	/**
	 * @param valorEntradaMinima O valorEntradaMinima a ser setado.
	 */
	public void setValorEntradaMinima(BigDecimal valorEntradaMinima) {
		this.valorEntradaMinima = valorEntradaMinima;
	}

	/**
	 * @return Retorna o campo quantidadePrestacao.
	 */
	public Short getQuantidadePrestacao() {
		return quantidadePrestacao;
	}

	/**
	 * @param quantidadePrestacao O quantidadePrestacao a ser setado.
	 */
	public void setQuantidadePrestacao(Short quantidadePrestacao) {
		this.quantidadePrestacao = quantidadePrestacao;
	}

	/**
	 * @return Retorna o campo taxaJuros.
	 */
	public BigDecimal getTaxaJuros() {
		return taxaJuros;
	}

	/**
	 * @param taxaJuros O taxaJuros a ser setado.
	 */
	public void setTaxaJuros(BigDecimal taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	/**
	 * @return Retorna o campo valorPrestacao.
	 */
	public BigDecimal getValorPrestacao() {
		return valorPrestacao;
	}

	/**
	 * @param valorPrestacao O valorPrestacao a ser setado.
	 */
	public void setValorPrestacao(BigDecimal valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}

	/**
	 * @return Retorna o campo indicadorQuantidadeParcelas.
	 */
	public Short getIndicadorQuantidadeParcelas() {
		return indicadorQuantidadeParcelas;
	}

	/**
	 * @param indicadorQuantidadeParcelas O indicadorQuantidadeParcelas a ser setado.
	 */
	public void setIndicadorQuantidadeParcelas(Short indicadorQuantidadeParcelas) {
		this.indicadorQuantidadeParcelas = indicadorQuantidadeParcelas;
	}

	/**
	 * @return Retorna o campo indicadorValorPrestacaoMaiorValorLimite.
	 */
	public Short getIndicadorValorPrestacaoMaiorValorLimite() {
		return indicadorValorPrestacaoMaiorValorLimite;
	}

	/**
	 * @param indicadorValorPrestacaoMaiorValorLimite O indicadorValorPrestacaoMaiorValorLimite a ser setado.
	 */
	public void setIndicadorValorPrestacaoMaiorValorLimite(
			Short indicadorValorPrestacaoMaiorValorLimite) {
		this.indicadorValorPrestacaoMaiorValorLimite = indicadorValorPrestacaoMaiorValorLimite;
	}
	
	
}
