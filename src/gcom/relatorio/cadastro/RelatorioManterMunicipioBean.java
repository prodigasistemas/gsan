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

public class RelatorioManterMunicipioBean implements RelatorioBean {
	
	private String id;

	private String nome;
	
	private String uf;
	
	private String cepInicio;

	private String cepFim;
	
	private String indicadorUso;
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

	public RelatorioManterMunicipioBean(String id,
			String nome, String uf, String cepInicio, String cepFim, String indicadorUso) {
		this.id = id;
		this.nome = nome;
		this.uf = uf;
		this.cepInicio = cepInicio;
		this.cepFim = cepFim;
		this.indicadorUso = indicadorUso;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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

	public String getCepFim() {
		return cepFim;
	}

	public void setCepFim(String cepFim) {
		this.cepFim = cepFim;
	}

	public String getCepInicio() {
		return cepInicio;
	}

	public void setCepInicio(String cepInicio) {
		this.cepInicio = cepInicio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
}
