package gcom.gui.relatorio.seguranca;

import gcom.cadastro.unidade.UnidadeOrganizacional;

import java.io.Serializable;
import java.util.Collection;

public class GerarRelatorioAlteracoesSistemaColunaHelper implements
		Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipoRelatorio;
	private String idFuncionalidade;
	private String idOperacao;
	private String idColuna;
	private String descColuna;
	private String idMeioSolicitacao;
	private String periodoInicial;
	private String periodoFinal;
	
	//Caso tipo Usuario
	private String idUnidadeSuperior;
	private Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional;
	private String idUsuario;
	
	//Caso tipo Localidade
	private String idGerenciaRegional;
	private String idUnidadeNegocio;
	private String idLocalidade;
		
	
	
	public GerarRelatorioAlteracoesSistemaColunaHelper() {
	}
	
	
	//Construtor para o tipo Usuario
	public GerarRelatorioAlteracoesSistemaColunaHelper(String tipoRelatorio,
			String idFuncionalidade, String idOperacao, String idColuna,String descColuna,
			String idMeioSolicitacao, String periodoInicial,
			String periodoFinal, String idUnidadeSuperior,
			Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional,
			String idUsuario) {
		this.tipoRelatorio = tipoRelatorio;
		this.idFuncionalidade = idFuncionalidade;
		this.idOperacao = idOperacao;
		this.idColuna = idColuna;
		this.descColuna = descColuna;
		this.idMeioSolicitacao = idMeioSolicitacao;
		this.periodoInicial = periodoInicial;
		this.periodoFinal = periodoFinal;
		this.idUnidadeSuperior = idUnidadeSuperior;
		this.colecaoUnidadeOrganizacional = colecaoUnidadeOrganizacional;
		this.idUsuario = idUsuario;
	}
	//Construtor para o tipo Localidade
	public GerarRelatorioAlteracoesSistemaColunaHelper(String tipoRelatorio,
			String idFuncionalidade, String idOperacao, String idColuna,String descColuna,
			String idMeioSolicitacao, String periodoInicial,
			String periodoFinal, String idGerenciaRegional,
			String idUnidadeNegocio, String idLocalidade) {
		this.tipoRelatorio = tipoRelatorio;
		this.idFuncionalidade = idFuncionalidade;
		this.idOperacao = idOperacao;
		this.idColuna = idColuna;
		this.descColuna = descColuna;
		this.idMeioSolicitacao = idMeioSolicitacao;
		this.periodoInicial = periodoInicial;
		this.periodoFinal = periodoFinal;
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idLocalidade = idLocalidade;
	}
	public String getTipoRelatorio() {
		return tipoRelatorio;
	}
	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	public String getIdFuncionalidade() {
		return idFuncionalidade;
	}
	public void setIdFuncionalidade(String idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}
	public String getIdOperacao() {
		return idOperacao;
	}
	public void setIdOperacao(String idOperacao) {
		this.idOperacao = idOperacao;
	}
	public String getIdColuna() {
		return idColuna;
	}
	public void setIdColuna(String idColuna) {
		this.idColuna = idColuna;
	}
	public String getIdMeioSolicitacao() {
		return idMeioSolicitacao;
	}
	public void setIdMeioSolicitacao(String idMeioSolicitacao) {
		this.idMeioSolicitacao = idMeioSolicitacao;
	}
	public String getPeriodoInicial() {
		return periodoInicial;
	}
	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}
	public String getPeriodoFinal() {
		return periodoFinal;
	}
	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
	public String getIdUnidadeSuperior() {
		return idUnidadeSuperior;
	}
	public void setIdUnidadeSuperior(String idUnidadeSuperior) {
		this.idUnidadeSuperior = idUnidadeSuperior;
	}
	public Collection<UnidadeOrganizacional> getColecaoUnidadeOrganizacional() {
		return colecaoUnidadeOrganizacional;
	}
	public void setColecaoUnidadeOrganizacional(
			Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional) {
		this.colecaoUnidadeOrganizacional = colecaoUnidadeOrganizacional;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}


	public String getDescColuna() {
		return descColuna;
	}


	public void setDescColuna(String descColuna) {
		this.descColuna = descColuna;
	}
	
	
	
	


}
