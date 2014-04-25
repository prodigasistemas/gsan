package gcom.gui.faturamento.consumotarifa;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ManterConsumoTarifaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descTarifa;
	private String dataVigencia;
	
	public String getDataVigencia() {
		return dataVigencia;
	}
	public void setDataVigencia(String dataVigencia) {
		this.dataVigencia = dataVigencia;
	}
	public String getDescTarifa() {
		return descTarifa;
	}
	public void setDescTarifa(String descTarifa) {
		this.descTarifa = descTarifa;
	}


}
