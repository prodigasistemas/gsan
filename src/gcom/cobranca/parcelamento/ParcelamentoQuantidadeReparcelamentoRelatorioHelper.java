package gcom.cobranca.parcelamento;

import java.io.Serializable;
import java.math.BigDecimal;


/** @author Hibernate CodeGenerator */
public class ParcelamentoQuantidadeReparcelamentoRelatorioHelper implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer idReparcelamento;
    private Integer idPrestacao;
    private Short quantidadeMaximaReparcelamento;
    private Short quantidadeMaximaPrestacoes;
    private BigDecimal taxaJuros;
    private BigDecimal percentualMinimoEntrada;

    /** default constructor */
    public ParcelamentoQuantidadeReparcelamentoRelatorioHelper() {
    }

	public Integer getIdPrestacao() {
		return idPrestacao;
	}

	public void setIdPrestacao(Integer idPrestacao) {
		this.idPrestacao = idPrestacao;
	}

	public Integer getIdReparcelamento() {
		return idReparcelamento;
	}

	public void setIdReparcelamento(Integer idReparcelamento) {
		this.idReparcelamento = idReparcelamento;
	}

	public BigDecimal getPercentualMinimoEntrada() {
		return percentualMinimoEntrada;
	}

	public void setPercentualMinimoEntrada(BigDecimal percentualMinimoEntrada) {
		this.percentualMinimoEntrada = percentualMinimoEntrada;
	}

	public Short getQuantidadeMaximaPrestacoes() {
		return quantidadeMaximaPrestacoes;
	}

	public void setQuantidadeMaximaPrestacoes(Short quantidadeMaximaPrestacoes) {
		this.quantidadeMaximaPrestacoes = quantidadeMaximaPrestacoes;
	}

	public Short getQuantidadeMaximaReparcelamento() {
		return quantidadeMaximaReparcelamento;
	}

	public void setQuantidadeMaximaReparcelamento(
			Short quantidadeMaximaReparcelamento) {
		this.quantidadeMaximaReparcelamento = quantidadeMaximaReparcelamento;
	}

	public BigDecimal getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(BigDecimal taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

}
