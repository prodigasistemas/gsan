package gcom.relatorio.seguranca;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterOperacaoBean implements RelatorioBean {
	
	private String id;

	private String descricao;
	
	private String argumentoPesquisa;
	
	private String operacaoTipo;

	private String funcionalidade;
	
	/**
	 * Construtor da classe RelatorioManterMunicipioBean
	 * 
	 * @param id
	 *            Descrição do parâmetro
	 * @param descricao
	 *            Descrição do parâmetro
	 * @param sistemaEsgoto
	 *            Descrição do parâmetro
	 */

	public RelatorioManterOperacaoBean(String id,
			String descricao, String argumentoPesquisa, String operacaoTipo, String funcionalidade) {
		this.id = id;
		this.descricao = descricao;
		this.argumentoPesquisa = argumentoPesquisa;
		this.operacaoTipo = operacaoTipo;
		this.funcionalidade = funcionalidade;
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getArgumentoPesquisa() {
		return argumentoPesquisa;
	}

	public void setArgumentoPesquisa(String argumentoPesquisa) {
		this.argumentoPesquisa = argumentoPesquisa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public String getOperacaoTipo() {
		return operacaoTipo;
	}

	public void setOperacaoTipo(String operacaoTipo) {
		this.operacaoTipo = operacaoTipo;
	}
		
}
