package gcom.gui.cobranca.parcelamento;

import org.apache.struts.action.ActionForm;

public class InserirPrestacoesParcelamentoPerfilActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String quantidadeMaximaPrestacao;
	
	private String taxaJuros;
	
	private String percentualMinimoEntrada;
	
	private String percentualTarifaMinimaImovel;
	
	private String percentualValorReparcelado;
	
	private String valorMaxPercFaixaValor;

	private String percentualPercFaixaValor;

	private String quantidadeMaxPrestacaoEspecial;
	
	private String fatorQuantidadePrestacoes;
	
	private String indicadorMediaValorContas;
	
	private String indicadorValorUltimaContaEmAtraso;
	
	public String getPercentualMinimoEntrada() {
		return percentualMinimoEntrada;
	}

	public String getPercentualPercFaixaValor() {
		return percentualPercFaixaValor;
	}

	public void setPercentualPercFaixaValor(String percentualPercFaixaValor) {
		this.percentualPercFaixaValor = percentualPercFaixaValor;
	}

	public String getValorMaxPercFaixaValor() {
		return valorMaxPercFaixaValor;
	}

	public void setValorMaxPercFaixaValor(String valorMaxPercFaixaValor) {
		this.valorMaxPercFaixaValor = valorMaxPercFaixaValor;
	}

	public void setPercentualMinimoEntrada(String percentualMinimoEntrada) {
		this.percentualMinimoEntrada = percentualMinimoEntrada;
	}

	public String getPercentualTarifaMinimaImovel() {
		return percentualTarifaMinimaImovel;
	}

	public void setPercentualTarifaMinimaImovel(String percentualTarifaMinimaImovel) {
		this.percentualTarifaMinimaImovel = percentualTarifaMinimaImovel;
	}

	public String getQuantidadeMaximaPrestacao() {
		return quantidadeMaximaPrestacao;
	}

	public void setQuantidadeMaximaPrestacao(String quantidadeMaximaPrestacao) {
		this.quantidadeMaximaPrestacao = quantidadeMaximaPrestacao;
	}

	public String getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(String taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public String getPercentualValorReparcelado() {
		return percentualValorReparcelado;
	}

	public void setPercentualValorReparcelado(String percentualValorReparcelado) {
		this.percentualValorReparcelado = percentualValorReparcelado;
	}

	public String getQuantidadeMaxPrestacaoEspecial() {
		return quantidadeMaxPrestacaoEspecial;
	}

	public void setQuantidadeMaxPrestacaoEspecial(
			String quantidadeMaxPrestacaoEspecial) {
		this.quantidadeMaxPrestacaoEspecial = quantidadeMaxPrestacaoEspecial;
	}

	public String getFatorQuantidadePrestacoes() {
		return fatorQuantidadePrestacoes;
	}

	public void setFatorQuantidadePrestacoes(String fatorQuantidadePrestacoes) {
		this.fatorQuantidadePrestacoes = fatorQuantidadePrestacoes;
	}

	public String getIndicadorMediaValorContas() {
		return indicadorMediaValorContas;
	}

	public void setIndicadorMediaValorContas(String indicadorMediaValorContas) {
		this.indicadorMediaValorContas = indicadorMediaValorContas;
	}

	public String getIndicadorValorUltimaContaEmAtraso() {
		return indicadorValorUltimaContaEmAtraso;
	}

	public void setIndicadorValorUltimaContaEmAtraso(
			String indicadorValorUltimaContaEmAtraso) {
		this.indicadorValorUltimaContaEmAtraso = indicadorValorUltimaContaEmAtraso;
	}
	
	
}

