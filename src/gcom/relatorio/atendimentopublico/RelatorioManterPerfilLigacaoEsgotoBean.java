package gcom.relatorio.atendimentopublico;

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

public class RelatorioManterPerfilLigacaoEsgotoBean implements RelatorioBean {
	
	private String codigo;

	private String descricao;
	
	private String percentualEsgotoConsumidaColetada;
	
	private String indicadorUso;

	/**
	 * Construtor da classe RelatorioManterBaciaBean
	 * 
	 * @param id
	 *            Descrição do parâmetro
	 * @param descricao
	 *            Descrição do parâmetro
	 * @param sistemaEsgoto
	 *            Descrição do parâmetro
	 */

	public RelatorioManterPerfilLigacaoEsgotoBean(String codigo,
			String descricao, String percentualEsgotoConsumidaColetada, String indicadorUso) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.percentualEsgotoConsumidaColetada= percentualEsgotoConsumidaColetada;
		this.indicadorUso= indicadorUso;
	}

	/**
	 * Retorna o valor do id
	 * 
	 * @return O valor do id
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
	 * Seta o valor do id
	 * 
	 * @param id
	 *            O novo valor do id
	 */

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * Seta o valor de sistemaEsgoto
	 * 
	 * @param nome
	 *            O novo valor de sistemaEsgoto
	 */


	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getPercentualEsgotoConsumidaColetada() {
		return percentualEsgotoConsumidaColetada;
	}

	public void setPercentualEsgotoConsumidaColetada(
			String percentualEsgotoConsumidaColetada) {
		this.percentualEsgotoConsumidaColetada = percentualEsgotoConsumidaColetada;
	}
	
	

	
}
