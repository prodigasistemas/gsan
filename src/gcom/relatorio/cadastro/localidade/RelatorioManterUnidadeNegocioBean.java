package gcom.relatorio.cadastro.localidade;

import gcom.relatorio.RelatorioBean;

/**
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterUnidadeNegocioBean implements RelatorioBean {
	
	private String codigo;

	private String nome;
	
	private String nomeAbreviado;

	private String gerenciaRegional;
	
	public RelatorioManterUnidadeNegocioBean(String codigo, String nome, String nomeAbreviado,
			 String gerenciaRegional){
		this.codigo = codigo;
		this.nome = nome;
		this.nomeAbreviado = nomeAbreviado;
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	
}
