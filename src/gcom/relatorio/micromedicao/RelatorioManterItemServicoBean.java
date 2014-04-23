package gcom.relatorio.micromedicao;

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
 * @author Rodrigo Cabral
 * @version 1.0
 */

public class RelatorioManterItemServicoBean implements RelatorioBean {
	
	private String descricao;

	private String descricaoAbreviada;
	
	private String indicadorUso;
	
	private String codigoConstanteCalculo;	
	
	private String codigoItem;

	public RelatorioManterItemServicoBean(String descricao, String descricaoAbreviada, String indicadorUso, String codigoConstanteCalculo, String codigoItem) {
		super();
		// TODO Auto-generated constructor stub
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.codigoConstanteCalculo = codigoConstanteCalculo;
		this.codigoItem = codigoItem;
		
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

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getCodigoConstanteCalculo() {
		return codigoConstanteCalculo;
	}

	public void setCodigoConstCalculo(String codigoConstanteCalculo) {
		this.codigoConstanteCalculo = codigoConstanteCalculo;
	}

	public String getCodigoItem() {
		return codigoItem;
	}

	public void setCodigoItem(String codigoItem) {
		this.codigoItem = codigoItem;
	}


}
