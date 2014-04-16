package gcom.gui.micromedicao;

import org.apache.struts.validator.ValidatorActionForm;


/**
 *  
 * @author 	Vinícius Medeiros 
 * @date  	03/06/2008
 */

public class AtualizarConsumoAnormalidadeActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String descricaoAbreviada;
	private String mensagemConta;
	private String dataUltimaAlteracao;
	private String indicadorUso;
	private String indicadorRevisaoComPermissaoEspecial;

	
	public String getMensagemConta() {
		return mensagemConta;
	}

	public void setMensagemConta(String mensagemConta) {
		this.mensagemConta = mensagemConta;
	}

	public String getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(String dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getIndicadorRevisaoComPermissaoEspecial() {
		return indicadorRevisaoComPermissaoEspecial;
	}

	public void setIndicadorRevisaoComPermissaoEspecial(
			String indicadorRevisaoComPermissaoEspecial) {
		this.indicadorRevisaoComPermissaoEspecial = indicadorRevisaoComPermissaoEspecial;
	}
	
}
