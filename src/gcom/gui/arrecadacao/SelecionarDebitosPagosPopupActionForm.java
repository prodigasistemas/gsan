package gcom.gui.arrecadacao;

import org.apache.struts.action.ActionForm;

public class SelecionarDebitosPagosPopupActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idImovel;
	
	private String descricaoImovel;
	
	private String idTipoDocumento;
	
	private String referenciaConta;
	
	private String descricaoReferenciaConta;
	
	private String idGuiaPagamento;
	
	private String descricaoGuiaPagamento;
	
	private String idDebitoACobrar;
	
	private String descricaoDebitoACobrar;

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(String idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getDescricaoDebitoACobrar() {
		return descricaoDebitoACobrar;
	}

	public void setDescricaoDebitoACobrar(String descricaoDebitoACobrar) {
		this.descricaoDebitoACobrar = descricaoDebitoACobrar;
	}

	public String getDescricaoGuiaPagamento() {
		return descricaoGuiaPagamento;
	}

	public void setDescricaoGuiaPagamento(String descricaoGuiaPagamento) {
		this.descricaoGuiaPagamento = descricaoGuiaPagamento;
	}

	public String getDescricaoReferenciaConta() {
		return descricaoReferenciaConta;
	}

	public void setDescricaoReferenciaConta(String descricaoReferenciaConta) {
		this.descricaoReferenciaConta = descricaoReferenciaConta;
	}

	public String getIdDebitoACobrar() {
		return idDebitoACobrar;
	}

	public void setIdDebitoACobrar(String idDebitoACobrar) {
		this.idDebitoACobrar = idDebitoACobrar;
	}

	public String getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	public void setIdGuiaPagamento(String idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}

	public String getReferenciaConta() {
		return referenciaConta;
	}

	public void setReferenciaConta(String referenciaConta) {
		this.referenciaConta = referenciaConta;
	}

}
