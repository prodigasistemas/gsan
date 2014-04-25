package gcom.gui.cadastro.sistemaparametro;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC_?_]Inserir Sistema Alteração Historico
 * 
 * @author Thiago Tenório
 * @date 27/11/2006
 */

public class InserirHistoricoAlteracaoSistemaActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	private String idFuncionalidade;

	private String descricaoFuncionalidade;

	private String dataAlteracao;

	private String tituloAlteracao;
	
	private String descricao;

	public String getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(String dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getDescricaoFuncionalidade() {
		return descricaoFuncionalidade;
	}

	public void setDescricaoFuncionalidade(String descricaoFuncionalidade) {
		this.descricaoFuncionalidade = descricaoFuncionalidade;
	}

	public String getIdFuncionalidade() {
		return idFuncionalidade;
	}

	public void setIdFuncionalidade(String idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}

	public String getTituloAlteracao() {
		return tituloAlteracao;
	}

	public void setTituloAlteracao(String tituloAlteracao) {
		this.tituloAlteracao = tituloAlteracao;
	}

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
