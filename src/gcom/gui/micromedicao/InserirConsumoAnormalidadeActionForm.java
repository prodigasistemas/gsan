package gcom.gui.micromedicao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Marcio Roberto
 * @date 26/01/2007
 */
public class InserirConsumoAnormalidadeActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	String descricao;

	String descricaoAbreviada;

	String mensagemConta;

	String IndicadorUso;
	
	private String indicadorRevisaoComPermissaoEspecial;

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

	public String getIndicadorUso() {
		return IndicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		IndicadorUso = indicadorUso;
	}

	public String getMensagemConta() {
		return mensagemConta;
	}

	public void setMensagemConta(String mensagemConta) {
		this.mensagemConta = mensagemConta;
	}

	public String getIndicadorRevisaoComPermissaoEspecial() {
		return indicadorRevisaoComPermissaoEspecial;
	}

	public void setIndicadorRevisaoComPermissaoEspecial(
			String indicadorRevisaoComPermissaoEspecial) {
		this.indicadorRevisaoComPermissaoEspecial = indicadorRevisaoComPermissaoEspecial;
	}

}
