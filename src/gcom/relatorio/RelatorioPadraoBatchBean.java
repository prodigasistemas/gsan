package gcom.relatorio;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Rafael Pinto
 * @created 27/08/2007
 */
public class RelatorioPadraoBatchBean implements RelatorioBean {
		
	private String observacao;

	public RelatorioPadraoBatchBean(String observacao) {
		this.observacao = observacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	
}
