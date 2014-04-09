package gcom.relatorio.cadastro.localidade;

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
 * @author Rafael Corrêa
 * @version 1.0
 */

public class RelatorioManterLocalidadeBean implements RelatorioBean {
	private String codigo;

	private String gerenciaRegional;

	private String unidadeNegocio;

	private String nome;

	private String indicadorUso;

	/**
	 * Construtor da classe RelatorioManterLogradouroBean
	 * 
	 * @param codigo
	 *            Descrição do parâmetro
	 * @param gerenciaRegional
	 *            Descrição do parâmetro
	 * @param nome
	 *            Descrição do parâmetro
	 * @param indicadorUso
	 *            Descrição do parâmetro
	 */

	public RelatorioManterLocalidadeBean(String codigo,
			String gerenciaRegional, String unidadeNegocio, String nome,
			String indicadorUso) {
		this.codigo = codigo;
		this.gerenciaRegional = gerenciaRegional;
		this.unidadeNegocio = unidadeNegocio;
		this.nome = nome;
		this.indicadorUso = indicadorUso;
	}

	/**
	 * Retorna o valor de codigo
	 * 
	 * @return O valor de codigo
	 */

	public String getCodigo() {
		return codigo;
	}

	/**
	 * Retorna o valor de gerenciaRegional
	 * 
	 * @return O valor de gerenciaRegional
	 */

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * Seta o valor de codigo
	 * 
	 * @param codigo
	 *            O novo valor de codigo
	 */

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Seta o valor de gerenciaRegional
	 * 
	 * @param gerenciaRegional
	 *            O novo valor de gerenciaRegional
	 */

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * Seta o valor de indicadorUso
	 * 
	 * @param indicadorUso
	 *            O novo valor de indicadorUso
	 */

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * Seta o valor de nome
	 * 
	 * @param nome
	 *            O novo valor de nome
	 */

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o valor de nome
	 * 
	 * @return O valor de nome
	 */

	public String getNome() {
		return nome;
	}

	/**
	 * Retorna o valor de indicadorUso
	 * 
	 * @return O valor de indicadorUso
	 */

	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio
	 *            O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

}
