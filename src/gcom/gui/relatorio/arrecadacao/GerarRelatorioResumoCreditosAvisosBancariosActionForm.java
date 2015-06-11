package gcom.gui.relatorio.arrecadacao;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class GerarRelatorioResumoCreditosAvisosBancariosActionForm  extends ValidatorForm {

	private static final long serialVersionUID = -4097621007089384199L;
	
	private Date dataConsulta;

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

}
