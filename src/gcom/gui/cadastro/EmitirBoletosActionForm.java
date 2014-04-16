package gcom.gui.cadastro;

import org.apache.struts.action.ActionForm;

/**
 * [UC0925] Emitir Boletos
 * 
 * @author Vivianne Sousa
 * @data 13/07/2009
 */
public class EmitirBoletosActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
    
    private String grupoFaturamento;

	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

}
