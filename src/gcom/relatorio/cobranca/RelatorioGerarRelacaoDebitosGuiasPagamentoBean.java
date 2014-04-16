package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * Bean do [UC0227] Gerar Relação de Débitos 
 *
 * @author Rafael Santos
 * @date 25/05/2006
 */
public class RelatorioGerarRelacaoDebitosGuiasPagamentoBean implements RelatorioBean {
	
	
	private String guiaDataVencimento;
	private String guiaValorOriginal;
	
	private JRBeanCollectionDataSource jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean;
	
	private ArrayList arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean;
	
	
	
	/**
	 * Construtor de RelatorioGerarRelacaoDebitosGuiasPagamentoBean 
	 * 
	 * @param guiaDataVencimento
	 * @param guiaValorOriginal
	 */
	public RelatorioGerarRelacaoDebitosGuiasPagamentoBean(String guiaDataVencimento, String guiaValorOriginal,Collection colecaoRelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean) {
		this.guiaDataVencimento = guiaDataVencimento;
		this.guiaValorOriginal = guiaValorOriginal;
		
		this.arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean = new ArrayList();
		this.arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean.addAll(colecaoRelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean);
		this.jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean = new JRBeanCollectionDataSource(this.arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean);
		
	}	
	
	/**
	 * @return Retorna o campo guiaDataVencimento.
	 */
	public String getGuiaDataVencimento() {
		return guiaDataVencimento;
	}
	/**
	 * @param guiaDataVencimento O guiaDataVencimento a ser setado.
	 */
	public void setGuiaDataVencimento(String guiaDataVencimento) {
		this.guiaDataVencimento = guiaDataVencimento;
	}
	/**
	 * @return Retorna o campo guiaValorOriginal.
	 */
	public String getGuiaValorOriginal() {
		return guiaValorOriginal;
	}
	/**
	 * @param guiaValorOriginal O guiaValorOriginal a ser setado.
	 */
	public void setGuiaValorOriginal(String guiaValorOriginal) {
		this.guiaValorOriginal = guiaValorOriginal;
	}

	/**
	 * @return Retorna o campo arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean.
	 */
	public ArrayList getArrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean() {
		return arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean;
	}

	/**
	 * @param arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean O arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean a ser setado.
	 */
	public void setArrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean(
			ArrayList arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean) {
		this.arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean = arrayRelatorioGerarRelacaoDebitosGuiasPagamentoBean;
	}

	/**
	 * @return Retorna o campo jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean.
	 */
	public JRBeanCollectionDataSource getJrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean() {
		return jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean;
	}

	/**
	 * @param jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean O jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean a ser setado.
	 */
	public void setJrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean(
			JRBeanCollectionDataSource jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean) {
		this.jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean = jrColecaoRelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean;
	}


}
