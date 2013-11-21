package gcom.gui.relatorio.cadastro;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

public class GerarRelatorioAlteracoesCpfCnpjActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String opcaoTipoRelatorio;
	private Integer tipoRelatorioEscolhido;
	private String[] meio = null;
	private String dataInicial = "";
	private String dataFinal = "";
	
	// Relatório por Usuário
	private String idUnidadeSuperior;
	private String descUnidadeSuperior;
	private String idUnidadeOrganizacional;
	private String descUnidadeOrganizacional;
	private Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional;
	private String idUsuario;
	private String descUsuario;
	private Collection<Usuario> colecaoUsuario;
	
	//Relatório por Localidade
	private String opcaoTotalizacao;
	private String idGerenciaRegional;
	private String idGerenciaRegionalporLocalidade;
	private String idUnidadeNegocio;
	private String idLocalidade;
	private String descLocalidade;
	

	public GerarRelatorioAlteracoesCpfCnpjActionForm() {
		super();
	}

	public String getOpcaoTipoRelatorio() {
		return opcaoTipoRelatorio;
	}

	public void setOpcaoTipoRelatorio(String opcaoTipoRelatorio) {
		this.opcaoTipoRelatorio = opcaoTipoRelatorio;
	}

	public Collection<UnidadeOrganizacional> getColecaoUnidadeOrganizacional() {
		return colecaoUnidadeOrganizacional;
	}

	public void setColecaoUnidadeOrganizacional(
			Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional) {
		this.colecaoUnidadeOrganizacional = colecaoUnidadeOrganizacional;
	}

	public Collection<Usuario> getColecaoUsuario() {
		return colecaoUsuario;
	}

	public void setColecaoUsuario(Collection<Usuario> colecaoUsuario) {
		this.colecaoUsuario = colecaoUsuario;
	}

	public String getDescUnidadeOrganizacional() {
		return descUnidadeOrganizacional;
	}

	public void setDescUnidadeOrganizacional(String descUnidadeOrganizacional) {
		this.descUnidadeOrganizacional = descUnidadeOrganizacional;
	}

	public String getDescUnidadeSuperior() {
		return descUnidadeSuperior;
	}

	public void setDescUnidadeSuperior(String descUnidadeSuperior) {
		this.descUnidadeSuperior = descUnidadeSuperior;
	}

	public String getDescUsuario() {
		return descUsuario;
	}

	public void setDescUsuario(String descUsuario) {
		this.descUsuario = descUsuario;
	}

	public String getIdUnidadeOrganizacional() {
		return idUnidadeOrganizacional;
	}

	public void setIdUnidadeOrganizacional(String idUnidadeOrganizacional) {
		this.idUnidadeOrganizacional = idUnidadeOrganizacional;
	}

	public String getIdUnidadeSuperior() {
		return idUnidadeSuperior;
	}

	public void setIdUnidadeSuperior(String idUnidadeSuperior) {
		this.idUnidadeSuperior = idUnidadeSuperior;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getTipoRelatorioEscolhido() {
		return tipoRelatorioEscolhido;
	}

	public void setTipoRelatorioEscolhido(Integer tipoRelatorioEscolhido) {
		this.tipoRelatorioEscolhido = tipoRelatorioEscolhido;
	}

	public String[] getMeio() {
		return meio;
	}

	public void setMeio(String[] meio) {
		this.meio = meio;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDescLocalidade() {
		return descLocalidade;
	}

	public void setDescLocalidade(String descLocalidade) {
		this.descLocalidade = descLocalidade;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdGerenciaRegionalporLocalidade() {
		return idGerenciaRegionalporLocalidade;
	}

	public void setIdGerenciaRegionalporLocalidade(
			String idGerenciaRegionalporLocalidade) {
		this.idGerenciaRegionalporLocalidade = idGerenciaRegionalporLocalidade;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	
}
