package gcom.gui.relatorio.cobranca;

import org.apache.struts.action.ActionForm;

public class FaixaForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	private Integer valorInicial;
	private Integer valorFinal;
	
	public void reset(){
		this.descricao = null;
		this.valorInicial = null;
		this.valorFinal = null;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(Integer valorInicial) {
		this.valorInicial = valorInicial;
	}
	public Integer getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(Integer valorFinal) {
		this.valorFinal = valorFinal;
	}
	
	
	
}
