package gcom.gui.faturamento.consumotarifa;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class InserirConsumoTarifaSubCategoriaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String slcDescTarifa;

	private String dataVigencia;
	

	public String getDataVigencia() {
		return dataVigencia;
	}

	public void setDataVigencia(String dataVigencia) {
		this.dataVigencia = dataVigencia;
	}

	public String getSlcDescTarifa() {
		return slcDescTarifa;
	}

	public void setSlcDescTarifa(String slcDescTarifa) {
		this.slcDescTarifa = slcDescTarifa;
	}
	
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		dataVigencia = null;
	}

}
