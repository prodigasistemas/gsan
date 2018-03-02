package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Imovel;

import org.apache.struts.action.ActionForm;

public class ComunicadoAltoConsumoActionForm extends ActionForm {

	private static final long serialVersionUID = -3158010250754440813L;
	
	private Imovel imovel;
	private String referencia;
	private String ultimaAlteracao;
	
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
}
