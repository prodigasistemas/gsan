package gcom.gui.micromedicao;

import org.apache.struts.action.ActionForm;

public class InserirItemServicoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	private String descricaoAbreviada;
	
	private String codigoConstanteCalculo;
	
	private String codigoItem;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getCodigoConstanteCalculo() {
		return codigoConstanteCalculo;
	}

	public void setCodigoConstanteCalculo(String codigoConstanteCalculo) {
		this.codigoConstanteCalculo = codigoConstanteCalculo;
	}

	public String getCodigoItem() {
		return codigoItem;
	}

	public void setCodigoItem(String codigoItem) {
		this.codigoItem = codigoItem;
	}
}
