package gcom.gui.relatorio.financeiro;

import org.apache.struts.action.ActionForm;

/**
 * [UC0824] Gerar Relatório dos Parâmetros Contábeis
 * 
 * @author Bruno Barros
 * @data 07/07/2008
 */
public class GerarRelatorioParametrosContabeisActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	public static final String SELECAO_RELATORIO_FATURAMENTO = "F";
	public static final String SELECAO_RELATORIO_ARRECADACAO = "A";
    
    private String selecaoRelatorio;
    private String referenciaContabil;
    
	public String getReferenciaContabil() {
		return referenciaContabil;
	}
	public void setReferenciaContabil(String referenciaContabil) {
		this.referenciaContabil = referenciaContabil;
	}
	public String getSelecaoRelatorio() {
		return selecaoRelatorio;
	}
	public void setTipoRelatorio(String tipoRelatorio) {
		this.selecaoRelatorio = tipoRelatorio;
	}
    
    
    
}
