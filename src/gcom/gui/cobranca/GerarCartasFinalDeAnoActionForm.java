package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author Vivianne Sousa
 * @data 10/11/2009
 */
public class GerarCartasFinalDeAnoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
    
    private String grupoFaturamento;
    private String acao;

	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}
    

	
}
