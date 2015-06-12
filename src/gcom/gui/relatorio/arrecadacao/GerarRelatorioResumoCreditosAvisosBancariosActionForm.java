package gcom.gui.relatorio.arrecadacao;

import org.apache.struts.validator.ValidatorForm;

public class GerarRelatorioResumoCreditosAvisosBancariosActionForm  extends ValidatorForm {

	private static final long serialVersionUID = -4097621007089384199L;
	
	private String dataConsulta;

	public String getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(String dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

}
