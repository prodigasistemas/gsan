package gcom.relatorio.seguranca;

import gcom.relatorio.RelatorioBean;

public class RelatorioAlteracoesSistemaColunaBean implements RelatorioBean {
	// TIPO USUARIO
	private String unidadeSuperior;
	private String unidadeOrganizacional;
	private String usuario;

	// TIPO LOCALIDADE
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String localidade;

	private String meio;
	private Integer quantidade;
	
	
	
	public RelatorioAlteracoesSistemaColunaBean() {
	}

	// TIPO USUARIO
	public RelatorioAlteracoesSistemaColunaBean(String unidadeSuperior,
			String unidadeOrganizacional, String usuario, String meio,
			Integer quantidade) {
		this.unidadeSuperior = unidadeSuperior;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.usuario = usuario;
		this.meio = meio;
		this.quantidade = quantidade;
	}

	// TIPO LOCALIDADE
	public RelatorioAlteracoesSistemaColunaBean(String meio,
			Integer quantidade, String gerenciaRegional, String unidadeNegocio,
			String localidade) {
		this.gerenciaRegional = gerenciaRegional;
		this.unidadeNegocio = unidadeNegocio;
		this.localidade = localidade;
		this.meio = meio;
		this.quantidade = quantidade;
	}

	public String getUnidadeSuperior() {
		return unidadeSuperior;
	}

	public void setUnidadeSuperior(String unidadeSuperior) {
		this.unidadeSuperior = unidadeSuperior;
	}

	public String getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(String unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getMeio() {
		return meio;
	}

	public void setMeio(String meio) {
		this.meio = meio;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

}
