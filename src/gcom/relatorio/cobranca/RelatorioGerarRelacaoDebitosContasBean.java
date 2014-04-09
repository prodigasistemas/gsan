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
public class RelatorioGerarRelacaoDebitosContasBean implements RelatorioBean {
	
	private String contaIndicadorContaRevisao;
	private String contaMesAnoReferenciaConta;
	private String contaDataVencimentoConta;
	private String contaValorOriginal;
	private String contaCodigoBarras;
	
	
	private JRBeanCollectionDataSource arrayJR;
	
	private ArrayList arrayRelatorioGerarRelacaoDebitosContasTotalBean;	
	
	
	/**
	 * Construtor de RelatorioGerarRelacaoDebitosContasBean 
	 * 
	 * @param contaIndicadorContaRevisao
	 * @param contaMesAnoReferenciaConta
	 * @param contaDataVencimentoConta
	 * @param contaValorOriginal
	 * @param contaValorTotalOriginal
	 * @param contaCodigoBarras
	 * @param contaValorTotalAtualizado
	 * @param jrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean
	 * @param arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean
	 */
	public RelatorioGerarRelacaoDebitosContasBean(String contaIndicadorContaRevisao, String contaMesAnoReferenciaConta, String contaDataVencimentoConta, String contaValorOriginal,String contaCodigoBarras, Collection colecaoGerarRelacaoDebitosContasTotalBean) {
		this.contaIndicadorContaRevisao = contaIndicadorContaRevisao;
		this.contaMesAnoReferenciaConta = contaMesAnoReferenciaConta;
		this.contaDataVencimentoConta = contaDataVencimentoConta;
		this.contaValorOriginal = contaValorOriginal;
		this.contaCodigoBarras = contaCodigoBarras;
		
		this.arrayRelatorioGerarRelacaoDebitosContasTotalBean = new ArrayList();
		this.arrayRelatorioGerarRelacaoDebitosContasTotalBean.addAll(colecaoGerarRelacaoDebitosContasTotalBean);
		
		
		this.arrayJR = new JRBeanCollectionDataSource(this.arrayRelatorioGerarRelacaoDebitosContasTotalBean);
				
		
	}	

	/**
	 * Construtor de RelatorioGerarRelacaoDebitosContasBean 
	 * 
	 */
	public RelatorioGerarRelacaoDebitosContasBean() {
		
	}		
	
	/**
	 * @return Retorna o campo contaCodigoBarras.
	 */
	public String getContaCodigoBarras() {
		return contaCodigoBarras;
	}

	/**
	 * @param contaCodigoBarras O contaCodigoBarras a ser setado.
	 */
	public void setContaCodigoBarras(String contaCodigoBarras) {
		this.contaCodigoBarras = contaCodigoBarras;
	}

	/**
	 * @return Retorna o campo contaDataVencimentoConta.
	 */
	public String getContaDataVencimentoConta() {
		return contaDataVencimentoConta;
	}

	/**
	 * @param contaDataVencimentoConta O contaDataVencimentoConta a ser setado.
	 */
	public void setContaDataVencimentoConta(String contaDataVencimentoConta) {
		this.contaDataVencimentoConta = contaDataVencimentoConta;
	}

	/**
	 * @return Retorna o campo contaIndicadorContaRevisao.
	 */
	public String getContaIndicadorContaRevisao() {
		return contaIndicadorContaRevisao;
	}

	/**
	 * @param contaIndicadorContaRevisao O contaIndicadorContaRevisao a ser setado.
	 */
	public void setContaIndicadorContaRevisao(String contaIndicadorContaRevisao) {
		this.contaIndicadorContaRevisao = contaIndicadorContaRevisao;
	}

	/**
	 * @return Retorna o campo contaMesAnoReferenciaConta.
	 */
	public String getContaMesAnoReferenciaConta() {
		return contaMesAnoReferenciaConta;
	}

	/**
	 * @param contaMesAnoReferenciaConta O contaMesAnoReferenciaConta a ser setado.
	 */
	public void setContaMesAnoReferenciaConta(String contaMesAnoReferenciaConta) {
		this.contaMesAnoReferenciaConta = contaMesAnoReferenciaConta;
	}

	/**
	 * @return Retorna o campo contaValorOriginal.
	 */
	public String getContaValorOriginal() {
		return contaValorOriginal;
	}

	/**
	 * @param contaValorOriginal O contaValorOriginal a ser setado.
	 */
	public void setContaValorOriginal(String contaValorOriginal) {
		this.contaValorOriginal = contaValorOriginal;
	}

	/**
	 * @return Retorna o campo arrayJR.
	 */
	public JRBeanCollectionDataSource getArrayJR() {
		return arrayJR;
	}

	/**
	 * @param arrayJR O arrayJR a ser setado.
	 */
	public void setArrayJR(JRBeanCollectionDataSource arrayJR) {
		this.arrayJR = arrayJR;
	}

	/**
	 * @return Retorna o campo arrayRelatorioGerarRelacaoDebitosContasTotalBean.
	 */
	public ArrayList getArrayRelatorioGerarRelacaoDebitosContasTotalBean() {
		return arrayRelatorioGerarRelacaoDebitosContasTotalBean;
	}

	/**
	 * @param arrayRelatorioGerarRelacaoDebitosContasTotalBean O arrayRelatorioGerarRelacaoDebitosContasTotalBean a ser setado.
	 */
	public void setArrayRelatorioGerarRelacaoDebitosContasTotalBean(
			ArrayList arrayRelatorioGerarRelacaoDebitosContasTotalBean) {
		this.arrayRelatorioGerarRelacaoDebitosContasTotalBean = arrayRelatorioGerarRelacaoDebitosContasTotalBean;
	}

}
