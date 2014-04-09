package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioHistoricoFaturamentoImovelBean implements RelatorioBean {
	
	private String matriculaImovel;

	private String inscricaoImovel;

	private String situacaoAguaImovel;

	private String situacaoEsgotoImovel;

	private JRBeanCollectionDataSource colecaoContas;

	private JRBeanCollectionDataSource colecaoContaHistorico;

	private JRBeanCollectionDataSource colecaoDebitoCobrar;

	private JRBeanCollectionDataSource colecaoDebitoCobrarHistorico;

	private JRBeanCollectionDataSource colecaoCreditoRealizar;

	private JRBeanCollectionDataSource colecaoCreditoRealizarHistorico;

	private JRBeanCollectionDataSource colecaoGuiaPagamento;

	private JRBeanCollectionDataSource colecaoGuiaPagamentoHistorico;

	public RelatorioHistoricoFaturamentoImovelBean() {
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String id) {
		this.inscricaoImovel = id;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String codigo) {
		this.matriculaImovel = codigo;
	}

	public String getSituacaoAguaImovel() {
		return situacaoAguaImovel;
	}

	public void setSituacaoAguaImovel(String codAgencia) {
		this.situacaoAguaImovel = codAgencia;
	}

	public String getSituacaoEsgotoImovel() {
		return situacaoEsgotoImovel;
	}

	public void setSituacaoEsgotoImovel(String nomeBanco) {
		this.situacaoEsgotoImovel = nomeBanco;
	}

	public JRBeanCollectionDataSource getColecaoContas() {
		return colecaoContas;
	}

	public void setColecaoContas(
			JRBeanCollectionDataSource colecaoClienteImovelHelper) {
		this.colecaoContas = colecaoClienteImovelHelper;
	}

	public JRBeanCollectionDataSource getColecaoContaHistorico() {
		return colecaoContaHistorico;
	}

	public void setColecaoContaHistorico(
			JRBeanCollectionDataSource coelcaoClienteImovelEconomia) {
		
		this.colecaoContaHistorico = coelcaoClienteImovelEconomia;
	}

	public JRBeanCollectionDataSource getColecaoDebitoCobrar() {
		return colecaoDebitoCobrar;
	}

	public void setColecaoDebitoCobrar(
			JRBeanCollectionDataSource colecaoDebitoCobrar) {
		this.colecaoDebitoCobrar = colecaoDebitoCobrar;
	}

	public JRBeanCollectionDataSource getColecaoDebitoCobrarHistorico() {
		return colecaoDebitoCobrarHistorico;
	}

	public void setColecaoDebitoCobrarHistorico(
			JRBeanCollectionDataSource colecaoDebitoCobrarHistorico) {
		this.colecaoDebitoCobrarHistorico = colecaoDebitoCobrarHistorico;
	}

	public JRBeanCollectionDataSource getColecaoCreditoRealizar() {
		return colecaoCreditoRealizar;
	}

	public void setColecaoCreditoRealizar(
			JRBeanCollectionDataSource colecaoCreditoRealizar) {
		this.colecaoCreditoRealizar = colecaoCreditoRealizar;
	}

	public JRBeanCollectionDataSource getColecaoCreditoRealizarHistorico() {
		return colecaoCreditoRealizarHistorico;
	}

	public void setColecaoCreditoRealizarHistorico(
			JRBeanCollectionDataSource colecaoCreditoRealizarHistorico) {
		this.colecaoCreditoRealizarHistorico = colecaoCreditoRealizarHistorico;
	}

	public JRBeanCollectionDataSource getColecaoGuiaPagamento() {
		return colecaoGuiaPagamento;
	}

	public void setColecaoGuiaPagamento(
			JRBeanCollectionDataSource colecaoGuiaPagamento) {
		this.colecaoGuiaPagamento = colecaoGuiaPagamento;
	}

	public JRBeanCollectionDataSource getColecaoGuiaPagamentoHistorico() {
		return colecaoGuiaPagamentoHistorico;
	}

	public void setColecaoGuiaPagamentoHistorico(
			JRBeanCollectionDataSource colecaoGuiaPagamentoHistorico) {
		this.colecaoGuiaPagamentoHistorico = colecaoGuiaPagamentoHistorico;
	}
}
