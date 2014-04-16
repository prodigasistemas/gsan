package gcom.gui.arrecadacao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Marcio Roberto
 * @date 26/01/2007
 */
public class InserirPagamentoSituacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	String descricao;

	String descricaoAbreviada;

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

	
}
