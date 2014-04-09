package gcom.gui.arrecadacao;

import org.apache.struts.validator.ValidatorActionForm;

public class RegistrarMovimentoArredadadoresActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoAgente;

	private String nomeArrecadador;
	
	private String idTipoMovimento;
	
	private String codigoConvenio;

	public String getCodigoAgente() {
		return codigoAgente;
	}

	public void setCodigoAgente(String codigoAgente) {
		this.codigoAgente = codigoAgente;
	}

	public String getCodigoConvenio() {
		return codigoConvenio;
	}

	public void setCodigoConvenio(String codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}

	public String getIdTipoMovimento() {
		return idTipoMovimento;
	}

	public void setIdTipoMovimento(String idTipoMovimento) {
		this.idTipoMovimento = idTipoMovimento;
	}

	public String getNomeArrecadador() {
		return nomeArrecadador;
	}

	public void setNomeArrecadador(String nomeArrecadador) {
		this.nomeArrecadador = nomeArrecadador;
	}


}
