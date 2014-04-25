package gcom.gui.arrecadacao;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0005] MANTER ARRECADADOR
 * 
 * @author Marcio Roberto
 * @date 08/02/2007
 */


public class ManterArrecadadorActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	private String idAgente;
	 
	private String idCliente;

	private String nomeCliente;

	private String idImovel;

	private String inscricaoImovel; 

	private String inscricaoEstadual;

	public String getIdAgente() {
		return idAgente;
	}

	public void setIdAgente(String idAgente) {
		this.idAgente = idAgente;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


}
