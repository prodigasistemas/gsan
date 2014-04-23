package gcom.gui.faturamento;

import org.apache.struts.action.ActionForm;

public class AtualizarFaturaClienteResponsavelActionForm extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	

	private String faturaId;
	
	private String clienteId;
	
	private String mesAnoReferencia;
	
	private String imovelId;
	
	private String inscricao;

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public String getFaturaId() {
		return faturaId;
	}

	public void setFaturaId(String faturaId) {
		this.faturaId = faturaId;
	}

	public String getImovelId() {
		return imovelId;
	}

	public void setImovelId(String imovelId) {
		this.imovelId = imovelId;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	
}
