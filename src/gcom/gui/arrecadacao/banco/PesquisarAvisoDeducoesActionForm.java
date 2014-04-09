package gcom.gui.arrecadacao.banco;

import org.apache.struts.action.ActionForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 2 de Junho de 2004
 */
public class PesquisarAvisoDeducoesActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String tipoDeducaoInclusao;
	
	private String valorDeducaoInclusao;

	public String getValorDeducaoInclusao() {
		return valorDeducaoInclusao;
	}

	public void setValorDeducaoInclusao(String valorDeducaoInclusao) {
		this.valorDeducaoInclusao = valorDeducaoInclusao;
	}

	public String getTipoDeducaoInclusao() {
		return tipoDeducaoInclusao;
	}

	public void setTipoDeducaoInclusao(String tipoDeducaoInclusao) {
		this.tipoDeducaoInclusao = tipoDeducaoInclusao;
	}
	
	
}

