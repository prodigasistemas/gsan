package gcom.relatorio.cobranca.contratoparcelamento;

import gcom.relatorio.RelatorioBean;

import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * @author Paulo Diniz
 * @date 25/03/2011
 * 
 */
public class RelatorioManterContratoParcelamentoPorClienteBean implements RelatorioBean {
	
	private JRBeanCollectionDataSource arrayJRDadosPagamento;
	private JRBeanCollectionDataSource arrayJRDadosCondicoesParcel;
	private String numeroContrato;
	private String numeroContratoAnterior;
	private String tipoRelacaoAnterior;
	private String clienteDescricao;
	private String usuarioDescricao;
	private String dataContrato;
	private String dataImplantacao;
	private String dataReferenciaInicial;
	private String dataReferenciaFinal;
	private String dataVencimentoInicial;
	private String dataVencimentoFinal;
	private String situacaoPagamento;
	private String situacaoCancelamento;
	private String motivoCancelamento;
	private String dataCancelamento;
	private String observacao;
	private String totalDebito;
	private String totalDebitoAtualizado;
	private String totalAcrescImpontualidade;
	private String totalDebitoComAcresc;
	private String taxaJuros;
	private String valorParcelado;
	private String totalJuros;
	private String valorParceladoACobrar;
	
	
	public RelatorioManterContratoParcelamentoPorClienteBean() {
		super();
	}


	public JRBeanCollectionDataSource getArrayJRDadosPagamento() {
		return arrayJRDadosPagamento;
	}


	public void setArrayJRDadosPagamento(
			List<SubRelatorioDadosPagtoManterContratoParcelamentoPorClienteBean> colecaoSubCliBean) {
		this.arrayJRDadosPagamento = new JRBeanCollectionDataSource(colecaoSubCliBean);
	}

	public JRBeanCollectionDataSource getArrayJRDadosCondicoesParcel() {
		return arrayJRDadosCondicoesParcel;
	}

	public void setArrayJRDadosCondicoesParcel(
			List<SubRelatorioCondicoesPagtoManterContratoParcelamentoPorClienteBean> colecaoSubCliBean) {
		this.arrayJRDadosCondicoesParcel = new JRBeanCollectionDataSource(colecaoSubCliBean);
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}


	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}


	public String getNumeroContratoAnterior() {
		return numeroContratoAnterior;
	}


	public void setNumeroContratoAnterior(String numeroContratoAnterior) {
		this.numeroContratoAnterior = numeroContratoAnterior;
	}


	public String getTipoRelacaoAnterior() {
		return tipoRelacaoAnterior;
	}


	public void setTipoRelacaoAnterior(String tipoRelacaoAnterior) {
		this.tipoRelacaoAnterior = tipoRelacaoAnterior;
	}


	public String getClienteDescricao() {
		return clienteDescricao;
	}


	public void setClienteDescricao(String clienteDescricao) {
		this.clienteDescricao = clienteDescricao;
	}


	public String getUsuarioDescricao() {
		return usuarioDescricao;
	}


	public void setUsuarioDescricao(String usuarioDescricao) {
		this.usuarioDescricao = usuarioDescricao;
	}


	public String getDataContrato() {
		return dataContrato;
	}


	public void setDataContrato(String dataContrato) {
		this.dataContrato = dataContrato;
	}


	public String getDataImplantacao() {
		return dataImplantacao;
	}


	public void setDataImplantacao(String dataImplantacao) {
		this.dataImplantacao = dataImplantacao;
	}


	public String getDataReferenciaInicial() {
		return dataReferenciaInicial;
	}


	public void setDataReferenciaInicial(String dataReferenciaInicial) {
		this.dataReferenciaInicial = dataReferenciaInicial;
	}


	public String getDataReferenciaFinal() {
		return dataReferenciaFinal;
	}


	public void setDataReferenciaFinal(String dataReferenciaFinal) {
		this.dataReferenciaFinal = dataReferenciaFinal;
	}


	public String getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}


	public void setDataVencimentoInicial(String dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}


	public String getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}


	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}


	public String getSituacaoPagamento() {
		return situacaoPagamento;
	}


	public void setSituacaoPagamento(String situacaoPagamento) {
		this.situacaoPagamento = situacaoPagamento;
	}


	public String getSituacaoCancelamento() {
		return situacaoCancelamento;
	}


	public void setSituacaoCancelamento(String situacaoCancelamento) {
		this.situacaoCancelamento = situacaoCancelamento;
	}


	public String getMotivoCancelamento() {
		return motivoCancelamento;
	}


	public void setMotivoCancelamento(String motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}


	public String getDataCancelamento() {
		return dataCancelamento;
	}


	public void setDataCancelamento(String dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public String getTotalDebito() {
		return totalDebito;
	}


	public void setTotalDebito(String totalDebito) {
		this.totalDebito = totalDebito;
	}


	public String getTotalDebitoAtualizado() {
		return totalDebitoAtualizado;
	}


	public void setTotalDebitoAtualizado(String totalDebitoAtualizado) {
		this.totalDebitoAtualizado = totalDebitoAtualizado;
	}


	public String getTotalAcrescImpontualidade() {
		return totalAcrescImpontualidade;
	}


	public void setTotalAcrescImpontualidade(String totalAcrescImpontualidade) {
		this.totalAcrescImpontualidade = totalAcrescImpontualidade;
	}


	public String getTotalDebitoComAcresc() {
		return totalDebitoComAcresc;
	}


	public void setTotalDebitoComAcresc(String totalDebitoComAcresc) {
		this.totalDebitoComAcresc = totalDebitoComAcresc;
	}


	public String getTaxaJuros() {
		return taxaJuros;
	}


	public void setTaxaJuros(String taxaJuros) {
		this.taxaJuros = taxaJuros;
	}


	public String getValorParcelado() {
		return valorParcelado;
	}


	public void setValorParcelado(String valorParcelado) {
		this.valorParcelado = valorParcelado;
	}


	public String getTotalJuros() {
		return totalJuros;
	}


	public void setTotalJuros(String totalJuros) {
		this.totalJuros = totalJuros;
	}


	public String getValorParceladoACobrar() {
		return valorParceladoACobrar;
	}


	public void setValorParceladoACobrar(String valorParceladoACobrar) {
		this.valorParceladoACobrar = valorParceladoACobrar;
	}
	

}
