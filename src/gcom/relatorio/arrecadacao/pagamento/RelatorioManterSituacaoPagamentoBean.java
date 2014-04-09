package gcom.relatorio.arrecadacao.pagamento;

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

public class RelatorioManterSituacaoPagamentoBean implements RelatorioBean {
	private String codigo;

	private String descricao;

	private String descricaoAbreviada;

	private String indicadorUso;

	/**
	 * Construtor da classe RelatorioManterSituacaoPagamentoBean
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

	public RelatorioManterSituacaoPagamentoBean(String codigo,
			String descricao, String descricaoAbreviada,
			String indicadorUso) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoAbreviada= descricaoAbreviada;
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
	 * Retorna o valor da descricao
	 * 
	 * @return O valor da descricao
	 */

	public String getDescricao() {
		return descricao;
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

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	/**
	 * Retorna o valor da descricaoAbreviada
	 * 
	 * @return O valor da descricaoAbreviada
	 */

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	/**
	 * Retorna o valor de indicadorUso
	 * 
	 * @return O valor de indicadorUso
	 */

	public String getIndicadorUso() {
		return indicadorUso;
	}

	
}
