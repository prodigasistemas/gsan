package gcom.relatorio.micromedicao;

import gcom.relatorio.RelatorioBean;

/**
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * Copyright: Copyright (c) 2004
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * 
 * @author Arthur Carvalho
 * @date 29/12/2009
 * @version 1.0
 */

public class RelatorioManterLeituristaBean implements RelatorioBean {
	
	private String codigo;
	
	private String descricaoLeiturista;
	
	private String ddd;
	
	private String numeroTelefone;
	
	private String descricaoEmpresa;
	
	private String numeroIMEI;
	
	private String indicadorUso;
	
	public RelatorioManterLeituristaBean (String codigo, String descricaoLeiturista,
			String ddd, String numeroTelefone, String descricaoEmpresa, String numeroIMEI,
			String indicadorUso) {
		this.codigo = codigo;
		this.descricaoLeiturista = descricaoLeiturista;
		this.ddd = ddd;
		this.numeroTelefone = numeroTelefone;
		this.descricaoEmpresa = descricaoEmpresa;
		this.numeroIMEI = numeroIMEI;
		this.indicadorUso = indicadorUso;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}

	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}

	public String getDescricaoLeiturista() {
		return descricaoLeiturista;
	}

	public void setDescricaoLeiturista(String descricaoLeiturista) {
		this.descricaoLeiturista = descricaoLeiturista;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getNumeroIMEI() {
		return numeroIMEI;
	}

	public void setNumeroIMEI(String numeroIMEI) {
		this.numeroIMEI = numeroIMEI;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}
	
	
	
	

	
}
