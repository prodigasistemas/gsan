package gcom.gui.faturamento;


import org.apache.struts.validator.ValidatorActionForm;

public class GerarTxtContasProjetosEspeciaisForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String mesAno;
	private String idCliente;
	private String nomeCliente;

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

	public void reset() {

		this.mesAno = "";
		this.idCliente = "";
		this.nomeCliente = "";

	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

}
