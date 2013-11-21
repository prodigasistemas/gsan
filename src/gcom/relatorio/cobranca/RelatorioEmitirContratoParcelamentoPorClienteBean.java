package gcom.relatorio.cobranca;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1141] Emitir Contrato de Parcelamento por Cliente
 * 
 * Classe responsável por emitir o relatório com os dados de um Contrato de Parcelamento por Cliente
 * 
 * @author Mariana Victor
 *
 * @date 28/04/2011
 */
public class RelatorioEmitirContratoParcelamentoPorClienteBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String idConta;
	
	private String idParcela;
	
	private String numeroContrato;
	
	private String numeroContratoAnt;
	
	private String tipoRelacao;
	
	private Date dataContrato;
	
	private Date dataImplContrato;
	
	private String usResponsavel;
	
	private String tipoCliente;
	
	private String codigoCliente;
	
	private String nomeCliente;
	
	private String cnpjCliente;
	
	private String debitoSelecionado;
	
	private String periodoReferenciaDebito;
	
	private String periodoVencimentoDebito;
	
	private String observacaoDebito;
	
	private String situacaoCancelamento;
	
	private String situacaoPagamento;
	
	private String resolucaoDiretoria;
	
	private String debitoAcrescimos;
	
	private String parcelamentoJuros;
	
	private String informarValorParcela;
	
	private String formaPagamento;
	
	private BigDecimal valorDebito;
	
	private BigDecimal valorAcrescImpontualidade;
	
	private BigDecimal taxaJuros;
	
	private BigDecimal valorJurosParc;
	
	private BigDecimal valorParcelado;
	
	private BigDecimal valorParceladoACobrar;
	
	private BigDecimal totalDebito;
	
	private BigDecimal totalDebitoAtualizado;
	
	private String totalDocsDebitoACobrar;
	
	private String totalValorACobrar;
	
	private JRBeanCollectionDataSource arrayJRDadosClientesVinculados;
	
	private JRBeanCollectionDataSource arrayJRDadosParcelas;
	
	private JRBeanCollectionDataSource arrayJRDadosContas;
	
	private JRBeanCollectionDataSource getArrayJRDadosDebitosACobrar;
	
	public RelatorioEmitirContratoParcelamentoPorClienteBean() {
		super();
	}
	
	public JRBeanCollectionDataSource getArrayJRDadosDebitosACobrar() {
		return getArrayJRDadosDebitosACobrar;
	}
	
	public void setArrayJRDadosDebitosACobrar(List<RelatorioEmitirContratoParcelamentoPorClienteSubDebitoACobrarBean> colecaoSubDebitoACobrarBean) {
		this.getArrayJRDadosDebitosACobrar = new JRBeanCollectionDataSource(colecaoSubDebitoACobrarBean);
	}
	
	public JRBeanCollectionDataSource getArrayJRDadosClientesVinculados() {
		return arrayJRDadosClientesVinculados;
	}


	public void setArrayJRDadosClientesVinculados(
			List<RelatorioEmitirContratoParcelamentoPorClienteSubCliBean> colecaoSubCliBean) {
		this.arrayJRDadosClientesVinculados = new JRBeanCollectionDataSource(colecaoSubCliBean);
	}


	public JRBeanCollectionDataSource getArrayJRDadosParcelas() {
		return arrayJRDadosParcelas;
	}


	public void setArrayJRDadosParcelas(
			List<RelatorioEmitirContratoParcelamentoPorClienteSubParcBean> colecaoSubParcBean) {
		this.arrayJRDadosParcelas = new JRBeanCollectionDataSource(colecaoSubParcBean);
	}


	public JRBeanCollectionDataSource getArrayJRDadosContas() {
		return arrayJRDadosContas;
	}

	public void setArrayJRDadosContas(List<RelatorioEmitirContratoParcelamentoPorClienteSubContaBean> colecaoSubContaBean) {
		this.arrayJRDadosContas = new JRBeanCollectionDataSource(colecaoSubContaBean);
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}


	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}


	public String getCodigoCliente() {
		return codigoCliente;
	}


	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}


	public Date getDataContrato() {
		return dataContrato;
	}


	public void setDataContrato(Date dataContrato) {
		this.dataContrato = dataContrato;
	}


	public Date getDataImplContrato() {
		return dataImplContrato;
	}


	public void setDataImplContrato(Date dataImplContrato) {
		this.dataImplContrato = dataImplContrato;
	}


	public String getDebitoAcrescimos() {
		return debitoAcrescimos;
	}


	public void setDebitoAcrescimos(String debitoAcrescimos) {
		this.debitoAcrescimos = debitoAcrescimos;
	}


	public String getDebitoSelecionado() {
		return debitoSelecionado;
	}


	public void setDebitoSelecionado(String debitoSelecionado) {
		this.debitoSelecionado = debitoSelecionado;
	}


	public String getFormaPagamento() {
		return formaPagamento;
	}


	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}


	public String getIdConta() {
		return idConta;
	}


	public void setIdConta(String idConta) {
		this.idConta = idConta;
	}


	public String getIdParcela() {
		return idParcela;
	}


	public void setIdParcela(String idParcela) {
		this.idParcela = idParcela;
	}


	public String getInformarValorParcela() {
		return informarValorParcela;
	}


	public void setInformarValorParcela(String informarValorParcela) {
		this.informarValorParcela = informarValorParcela;
	}


	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	public String getNumeroContrato() {
		return numeroContrato;
	}


	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}


	public String getNumeroContratoAnt() {
		return numeroContratoAnt;
	}


	public void setNumeroContratoAnt(String numeroContratoAnt) {
		this.numeroContratoAnt = numeroContratoAnt;
	}


	public String getObservacaoDebito() {
		return observacaoDebito;
	}


	public void setObservacaoDebito(String observacaoDebito) {
		this.observacaoDebito = observacaoDebito;
	}


	public String getParcelamentoJuros() {
		return parcelamentoJuros;
	}


	public void setParcelamentoJuros(String parcelamentoJuros) {
		this.parcelamentoJuros = parcelamentoJuros;
	}


	public String getPeriodoReferenciaDebito() {
		return periodoReferenciaDebito;
	}


	public void setPeriodoReferenciaDebito(String periodoReferenciaDebito) {
		this.periodoReferenciaDebito = periodoReferenciaDebito;
	}


	public String getPeriodoVencimentoDebito() {
		return periodoVencimentoDebito;
	}


	public void setPeriodoVencimentoDebito(String periodoVencimentoDebito) {
		this.periodoVencimentoDebito = periodoVencimentoDebito;
	}


	public String getResolucaoDiretoria() {
		return resolucaoDiretoria;
	}


	public void setResolucaoDiretoria(String resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}


	public BigDecimal getTaxaJuros() {
		return taxaJuros;
	}


	public void setTaxaJuros(BigDecimal taxaJuros) {
		this.taxaJuros = taxaJuros;
	}


	public String getTipoCliente() {
		return tipoCliente;
	}


	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}


	public String getTipoRelacao() {
		return tipoRelacao;
	}


	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}


	public String getUsResponsavel() {
		return usResponsavel;
	}


	public void setUsResponsavel(String usResponsavel) {
		this.usResponsavel = usResponsavel;
	}


	public BigDecimal getValorAcrescImpontualidade() {
		return valorAcrescImpontualidade;
	}


	public void setValorAcrescImpontualidade(BigDecimal valorAcrescImpontualidade) {
		this.valorAcrescImpontualidade = valorAcrescImpontualidade;
	}


	public BigDecimal getValorDebito() {
		return valorDebito;
	}


	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}


	public BigDecimal getValorJurosParc() {
		return valorJurosParc;
	}


	public void setValorJurosParc(BigDecimal valorJurosParc) {
		this.valorJurosParc = valorJurosParc;
	}


	public BigDecimal getValorParcelado() {
		return valorParcelado;
	}


	public void setValorParcelado(BigDecimal valorParcelado) {
		this.valorParcelado = valorParcelado;
	}


	public BigDecimal getValorParceladoACobrar() {
		return valorParceladoACobrar;
	}


	public void setValorParceladoACobrar(BigDecimal valorParceladoACobrar) {
		this.valorParceladoACobrar = valorParceladoACobrar;
	}


	public String getSituacaoCancelamento() {
		return situacaoCancelamento;
	}


	public void setSituacaoCancelamento(String situacaoCancelamento) {
		this.situacaoCancelamento = situacaoCancelamento;
	}


	public String getSituacaoPagamento() {
		return situacaoPagamento;
	}


	public void setSituacaoPagamento(String situacaoPagamento) {
		this.situacaoPagamento = situacaoPagamento;
	}

	public BigDecimal getTotalDebito() {
		return totalDebito;
	}

	public void setTotalDebito(BigDecimal totalDebito) {
		this.totalDebito = totalDebito;
	}

	public BigDecimal getTotalDebitoAtualizado() {
		return totalDebitoAtualizado;
	}

	public void setTotalDebitoAtualizado(BigDecimal totalDebitoAtualizado) {
		this.totalDebitoAtualizado = totalDebitoAtualizado;
	}
	
	public String getTotalDocsDebitoACobrar() {
		return totalDocsDebitoACobrar;
	}

	public void setTotalDocsDebitoACobrar(String totalDocsDebitoACobrar) {
		this.totalDocsDebitoACobrar = totalDocsDebitoACobrar;
	}

	public String getTotalValorACobrar() {
		return totalValorACobrar;
	}

	public void setTotalValorACobrar(String totalValorACobrar) {
		this.totalValorACobrar = totalValorACobrar;
	}

}
