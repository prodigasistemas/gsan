package gcom.gui.seguranca.acesso;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Form responsável por manipular os dados no atualizar operação
 * 
 * @author Pedro Alexandre
 * @date 01/08/2006
 */
public class AtualizarOperacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idOperacao;
	
	private String descricao;

	private String descricaoAbreviada;

	private String caminhoUrl;

	private String idFuncionalidade;
	
	private String descricaoFuncionalidade;
	
	private String idTipoOperacao;
	
	private String idArgumentoPesquisa;
	
	private String descricaoArgumentoPesquisa;
	
	private String[] idTabelas;
	
	private String idOperacaoPesquisa;
	
	private String descricaoOperacaoPesquisa;

	public String getCaminhoUrl() {
		return caminhoUrl;
	}

	public void setCaminhoUrl(String caminhoUrl) {
		this.caminhoUrl = caminhoUrl;
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

	public String getIdArgumentoPesquisa() {
		return idArgumentoPesquisa;
	}

	public void setIdArgumentoPesquisa(String idArgumentoPesquisa) {
		this.idArgumentoPesquisa = idArgumentoPesquisa;
	}

	public String getIdFuncionalidade() {
		return idFuncionalidade;
	}

	public void setIdFuncionalidade(String idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}

	public String getIdOperacaoPesquisa() {
		return idOperacaoPesquisa;
	}

	public void setIdOperacaoPesquisa(String idOperacaoPesquisa) {
		this.idOperacaoPesquisa = idOperacaoPesquisa;
	}

	public String[] getIdTabelas() {
		return idTabelas;
	}

	public void setIdTabelas(String[] idTabelas) {
		this.idTabelas = idTabelas;
	}

	public String getIdTipoOperacao() {
		return idTipoOperacao;
	}

	public void setIdTipoOperacao(String idTipoOperacao) {
		this.idTipoOperacao = idTipoOperacao;
	}

	public String getDescricaoFuncionalidade() {
		return descricaoFuncionalidade;
	}

	public void setDescricaoFuncionalidade(String descricaoFuncionalidade) {
		this.descricaoFuncionalidade = descricaoFuncionalidade;
	}

	public String getDescricaoArgumentoPesquisa() {
		return descricaoArgumentoPesquisa;
	}

	public void setDescricaoArgumentoPesquisa(String descricaoArgumentoPesquisa) {
		this.descricaoArgumentoPesquisa = descricaoArgumentoPesquisa;
	}

	public String getDescricaoOperacaoPesquisa() {
		return descricaoOperacaoPesquisa;
	}

	public void setDescricaoOperacaoPesquisa(String descricaoOperacaoPesquisa) {
		this.descricaoOperacaoPesquisa = descricaoOperacaoPesquisa;
	}

	/**
	 * @return Retorna o campo idOperacao.
	 */
	public String getIdOperacao() {
		return idOperacao;
	}

	/**
	 * @param idOperacao O idOperacao a ser setado.
	 */
	public void setIdOperacao(String idOperacao) {
		this.idOperacao = idOperacao;
	}

	
}
