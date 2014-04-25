package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * 
 * Bean do [UC0227] Gerar Relação de Débitos 
 *
 * @author Rafael Santos
 * @date 25/05/2006
 */
public class RelatorioGerarRelacaoDebitosGuiasPagamentoTotalBean implements RelatorioBean {
	
	
	private String guiaValorTotal;
	
	
	/**
	 * @return Retorna o campo guiaValorTotal.
	 */
	public String getGuiaValorTotal() {
		return guiaValorTotal;
	}
	/**
	 * @param guiaValorTotal O guiaValorTotal a ser setado.
	 */
	public void setGuiaValorTotal(String guiaValorTotal) {
		this.guiaValorTotal = guiaValorTotal;
	}

}
