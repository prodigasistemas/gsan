package gcom.gui.relatorio.cobranca;

import org.apache.struts.action.ActionForm;

public class GerarRelatorioAnalisePerdasCreditosActionForm extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	
	private String mesAno;
	
	public GerarRelatorioAnalisePerdasCreditosActionForm(){}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	
	
}
