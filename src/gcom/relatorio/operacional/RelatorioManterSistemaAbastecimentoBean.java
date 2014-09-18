package gcom.relatorio.operacional;

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
 * @author Fernando Fontelles
 * @version 1.0
 */

public class RelatorioManterSistemaAbastecimentoBean implements RelatorioBean {
	
	private String codigo;

	private String descricao;
	
	private String indicadorUso;
	
	private String fonteCaptacaoDescricao;

	public RelatorioManterSistemaAbastecimentoBean(String codigo, String descricao, String indicadorUso, String fonteCaptacaoDescricao) {
		super();
		
		this.codigo = codigo;
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.fonteCaptacaoDescricao = fonteCaptacaoDescricao;
	}
	
	public String getFonteCaptacaoDescricao() {
		return fonteCaptacaoDescricao;
	}

	public void setFonteCaptacaoDescricao(String fonteCaptacaoDescricao) {
		this.fonteCaptacaoDescricao = fonteCaptacaoDescricao;
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

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	
	

	
}
