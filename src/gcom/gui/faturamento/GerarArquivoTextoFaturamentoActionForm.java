package gcom.gui.faturamento;

import org.apache.struts.action.ActionForm;

public class GerarArquivoTextoFaturamentoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idCliente;

	private String mesAno;

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

}
