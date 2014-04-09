package gcom.gui.cadastro.tarifasocial;

import gcom.gui.ControladorAlteracaoGcomActionForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

/**
 * Consulta o imovel da tarifa social
 * 
 * @author thiago toscano
 * @date 
 */
public class CancelarManterDadosTarifaSocialActionForm extends ControladorAlteracaoGcomActionForm {
	private static final long serialVersionUID = 1L;
	private String idImovel = "";	
	private String idImovelEconomia = "";

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		this.idImovel = "";
		this.idImovelEconomia = "";
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdImovelEconomia() {
		return idImovelEconomia;
	}

	public void setIdImovelEconomia(String idImovelEconomia) {
		this.idImovelEconomia = idImovelEconomia;
	}
}
