package gcom.gui.relatorio.big;

import org.apache.struts.validator.ValidatorActionForm;

public class RelatorioBIGActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String mesAno;

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
}
