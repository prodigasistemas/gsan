package gcom.relatorio.cobranca.contratoparcelamento;

import gcom.relatorio.RelatorioBean;

/**
 * 
 * @author Paulo Diniz
 * @date 25/03/2011
 * 
 */
public class RelatorioManterContratoParcelamentoRDBean implements RelatorioBean {
	
	private String numero;
	private String assunto;
	private String vigenciaFinal;
	private String vigenciaInicial;
	private String indicadorDebitoAcrescimo;
	private String indicadorParcelamentoJuros;
	private String indicadorInformarParcela;
	private String qtdFaturasParceladas;
	private String formaPagamento;
	private String parcelas;
	private String taxaJuros;
	
	public RelatorioManterContratoParcelamentoRDBean() {
		super();
	}
	

	public RelatorioManterContratoParcelamentoRDBean(String numero, String assunto, String vigenciaFinal, String vigenciaInicial, String indicadorDebitoAcrescimo, String indicadorParcelamentoJuros, String indicadorInformarParcela, String qtdFaturasParceladas, String formaPagamento, String parcelas, String taxaJuros) {
		super();
		this.numero = numero;
		this.assunto = assunto;
		this.vigenciaFinal = vigenciaFinal;
		this.vigenciaInicial = vigenciaInicial;
		this.indicadorDebitoAcrescimo = indicadorDebitoAcrescimo;
		this.indicadorParcelamentoJuros = indicadorParcelamentoJuros;
		this.indicadorInformarParcela = indicadorInformarParcela;
		this.qtdFaturasParceladas = qtdFaturasParceladas;
		this.formaPagamento = formaPagamento;
		this.parcelas = parcelas;
		this.taxaJuros = taxaJuros;
	}



	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getIndicadorDebitoAcrescimo() {
		return indicadorDebitoAcrescimo;
	}

	public void setIndicadorDebitoAcrescimo(String indicadorDebitoAcrescimo) {
		this.indicadorDebitoAcrescimo = indicadorDebitoAcrescimo;
	}

	public String getIndicadorInformarParcela() {
		return indicadorInformarParcela;
	}

	public void setIndicadorInformarParcela(String indicadorInformarParcela) {
		this.indicadorInformarParcela = indicadorInformarParcela;
	}

	public String getIndicadorParcelamentoJuros() {
		return indicadorParcelamentoJuros;
	}

	public void setIndicadorParcelamentoJuros(String indicadorParcelamentoJuros) {
		this.indicadorParcelamentoJuros = indicadorParcelamentoJuros;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getParcelas() {
		return parcelas;
	}

	public void setParcelas(String parcelas) {
		this.parcelas = parcelas;
	}

	public String getQtdFaturasParceladas() {
		return qtdFaturasParceladas;
	}

	public void setQtdFaturasParceladas(String qtdFaturasParceladas) {
		this.qtdFaturasParceladas = qtdFaturasParceladas;
	}

	public String getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(String taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public String getVigenciaFinal() {
		return vigenciaFinal;
	}

	public void setVigenciaFinal(String vigenciaFinal) {
		this.vigenciaFinal = vigenciaFinal;
	}

	public String getVigenciaInicial() {
		return vigenciaInicial;
	}

	public void setVigenciaInicial(String vigenciaInicial) {
		this.vigenciaInicial = vigenciaInicial;
	}

	
	
}
