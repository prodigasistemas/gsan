package gcom.relatorio.arrecadacao.pagamento;

import gcom.cobranca.parcelamento.Parcelamento;
import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 * [UC0379] Emitir Guia de Pagamento
 * @author Vivianne Sousa
 * @date 22/09/2006
 */
public class RelatorioEmitirGuiaPagamentoDetailBean implements RelatorioBean {
	
	private String descricaoServicosTarifas; 
	private String valor; 
	private Boolean exibirDetalhesParcelamento;
	
	private String periodoDebito;
	private String valorAtualizadoParcelamento;
	private String valorNegociadoParcelamento;
	private String valorAVistaParcelamento;
	private String valorEntradaParcelamento;
	private String qtdParcelasParcelamento;
	private String numeroContratoParcelamento;
	private String dataVencimentoParcela;
	private String valorParcela;
	
	public RelatorioEmitirGuiaPagamentoDetailBean(
			String descricaoServicosTarifas,
			String valor,
			Boolean exibirDetalhesParcelamento) {
		this.descricaoServicosTarifas = descricaoServicosTarifas;
		this.valor = valor;
		this.exibirDetalhesParcelamento = exibirDetalhesParcelamento;
	}

	public String getDescricaoServicosTarifas() {
		return descricaoServicosTarifas;
	}

	public void setDescricaoServicosTarifas(String descricaoServicosTarifas) {
		this.descricaoServicosTarifas = descricaoServicosTarifas;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Boolean getExibirDetalhesParcelamento() {
		return exibirDetalhesParcelamento;
	}

	public void setExibirDetalhesParcelamento(Boolean exibirDetalhesParcelamento) {
		this.exibirDetalhesParcelamento = exibirDetalhesParcelamento;
	}

	public String getPeriodoDebito() {
		return periodoDebito;
	}

	public void setPeriodoDebito(String periodoDebito) {
		this.periodoDebito = periodoDebito;
	}

	public String getValorAtualizadoParcelamento() {
		return valorAtualizadoParcelamento;
	}

	public void setValorAtualizadoParcelamento(String valorAtualizadoParcelamento) {
		this.valorAtualizadoParcelamento = valorAtualizadoParcelamento;
	}

	public String getValorNegociadoParcelamento() {
		return valorNegociadoParcelamento;
	}

	public void setValorNegociadoParcelamento(String valorNegociadoParcelamento) {
		this.valorNegociadoParcelamento = valorNegociadoParcelamento;
	}

	public String getValorAVistaParcelamento() {
		return valorAVistaParcelamento;
	}

	public void setValorAVistaParcelamento(String valorAVistaParcelamento) {
		this.valorAVistaParcelamento = valorAVistaParcelamento;
	}

	public String getValorEntradaParcelamento() {
		return valorEntradaParcelamento;
	}

	public void setValorEntradaParcelamento(String valorEntradaParcelamento) {
		this.valorEntradaParcelamento = valorEntradaParcelamento;
	}

	public String getQtdParcelasParcelamento() {
		return qtdParcelasParcelamento;
	}

	public void setQtdParcelasParcelamento(String qtdParcelasParcelamento) {
		this.qtdParcelasParcelamento = qtdParcelasParcelamento;
	}

	public String getNumeroContratoParcelamento() {
		return numeroContratoParcelamento;
	}

	public void setNumeroContratoParcelamento(String numeroContratoParcelamento) {
		this.numeroContratoParcelamento = numeroContratoParcelamento;
	}

	public String getDataVencimentoParcela() {
		return dataVencimentoParcela;
	}

	public void setDataVencimentoParcela(String dataVencimentoParcela) {
		this.dataVencimentoParcela = dataVencimentoParcela;
	}

	public String getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(String valorParcela) {
		this.valorParcela = valorParcela;
	}

	public void preencherDadosParcelamento(Parcelamento parcelamento, Integer[] periodoDebitos, Short diaVencimento) {
		
		this.periodoDebito = Util.formatarAnoMesParaMesAno(periodoDebitos[0].intValue()) + " a " + Util.formatarAnoMesParaMesAno(periodoDebitos[1].intValue());
		this.valorAtualizadoParcelamento= Util.formatarMoedaRealComCifrao(parcelamento.getValorDebitoAtualizado());
		this.valorNegociadoParcelamento= Util.formatarMoedaRealComCifrao(parcelamento.getValorParcelado());
		this.valorAVistaParcelamento= Util.formatarMoedaRealComCifrao(parcelamento.getValorNegociado());
		this.valorEntradaParcelamento= Util.formatarMoedaRealComCifrao(parcelamento.getValorEntrada());
		this.qtdParcelasParcelamento= parcelamento.getNumeroPrestacoes().toString();
		this.numeroContratoParcelamento= parcelamento.getId().toString();
		this.dataVencimentoParcela= diaVencimento.toString();
		this.valorParcela= Util.formatarMoedaRealComCifrao(parcelamento.getValorPrestacao());
	}
	
}
