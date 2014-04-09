package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * 
 * Bean do [UC0227] Gerar Relação de Débitos 
 *
 * @author Rafael Santos
 * @date 25/05/2006
 */
public class RelatorioGerarRelacaoDebitosContasTotalBean implements RelatorioBean {
	
	private String totalValorOriginal;
	private String totalContasAtualizado;
	
	/**
	 * Construtor de RelatorioGerarRelacaoDebitosContasTotalBean 
	 * 
	 * @param contaValorTotalOriginal
	 * @param contaValorTotalAtualizado
	 */
	public RelatorioGerarRelacaoDebitosContasTotalBean(String totalValorOriginal, String totalContasAtualizado) {
		this.totalValorOriginal = totalValorOriginal;
		this.totalContasAtualizado = totalContasAtualizado;
	}

	/**
	 * @return Retorna o campo contaValorTotalAtualizado.
	 */
	public String getTotalContasAtualizado() {
		return totalContasAtualizado;
	}

	/**
	 * @param contaValorTotalAtualizado O contaValorTotalAtualizado a ser setado.
	 */
	public void setTotalContasAtualizado(String totalContasAtualizado) {
		this.totalContasAtualizado = totalContasAtualizado;
	}

	/**
	 * @return Retorna o campo contaValorTotalOriginal.
	 */
	public String getTotalValorOriginal() {
		return totalValorOriginal;
	}

	/**
	 * @param contaValorTotalOriginal O contaValorTotalOriginal a ser setado.
	 */
	public void setTotalValorOriginal(String totalValorOriginal) {
		this.totalValorOriginal = totalValorOriginal;
	}	

}
