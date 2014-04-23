package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.action.ActionForm;

public class InserirDadosPavimentosOrdemServicoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	// Dados Gerais
	private String numeroOS;

	private String descricaoTipoServico;

	private String idPavimentoRua;

	private String idPavimentoCalcada;

	private String metragemPavimentoRua;

	private String metragemPavimentoCalcada;


	public String getDescricaoTipoServico() {
		return descricaoTipoServico;
	}


	public void setDescricaoTipoServico(String descricaoTipoServico) {
		this.descricaoTipoServico = descricaoTipoServico;
	}


	public String getIdPavimentoCalcada() {
		return idPavimentoCalcada;
	}


	public void setIdPavimentoCalcada(String idPavimentoCalcada) {
		this.idPavimentoCalcada = idPavimentoCalcada;
	}


	public String getIdPavimentoRua() {
		return idPavimentoRua;
	}


	public void setIdPavimentoRua(String idPavimentoRua) {
		this.idPavimentoRua = idPavimentoRua;
	}


	public String getMetragemPavimentoCalcada() {
		return metragemPavimentoCalcada;
	}


	public void setMetragemPavimentoCalcada(String metragemPavimentoCalcada) {
		this.metragemPavimentoCalcada = metragemPavimentoCalcada;
	}


	public String getMetragemPavimentoRua() {
		return metragemPavimentoRua;
	}


	public void setMetragemPavimentoRua(String metragemPavimentoRua) {
		this.metragemPavimentoRua = metragemPavimentoRua;
	}


	public String getNumeroOS() {
		return numeroOS;
	}


	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}


	public void resetarConsultarDadosOSPopup() {
	    	this.numeroOS = null;
	    	this.descricaoTipoServico = null;
	    	this.idPavimentoRua = null;
	        this.idPavimentoCalcada = null;
	        this.metragemPavimentoRua = null;
	        this.metragemPavimentoCalcada = null;
	    }


}
