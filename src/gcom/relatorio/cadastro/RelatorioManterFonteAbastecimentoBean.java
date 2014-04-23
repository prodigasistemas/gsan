package gcom.relatorio.cadastro;

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

public class RelatorioManterFonteAbastecimentoBean implements RelatorioBean {
	
	private String id;

	private String descricao;
	
	private String descricaoAbreviada;
	
	private String indicadorUso;

	private String indicadorCalcularVolumeFixo;
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

	public RelatorioManterFonteAbastecimentoBean(String id,
			String descricao, String descricaoAbreviada, String indicadorUso, String indicadorCalcularVolumeFixo) {
		this.id = id;
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.indicadorCalcularVolumeFixo = indicadorCalcularVolumeFixo;
	}

	/**
	 * Retorna o valor do id
	 * 
	 * @return O valor do id
	 */

	public String getId() {
		return id;
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

	public void setId(String id) {
		this.id = id;
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

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}


	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getIndicadorCalcularVolumeFixo() {
		return indicadorCalcularVolumeFixo;
	}

	public void setIndicadorCalcularVolumeFixo(String indicadorCalcularVolumeFixo) {
		this.indicadorCalcularVolumeFixo = indicadorCalcularVolumeFixo;
	}

	
	
	

	
}
