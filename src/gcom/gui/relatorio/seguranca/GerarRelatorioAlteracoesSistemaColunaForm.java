package gcom.gui.relatorio.seguranca;

import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.seguranca.acesso.Operacao;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

public class GerarRelatorioAlteracoesSistemaColunaForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipoRelatorio;
	private String tipoRelatorioEscolhido;
	private String idFuncionalidade;
	private String descFuncionalidade;
	private String idOperacao;
	private Collection<Operacao> colecaoOperacoes;
	private String idColuna;
	private String descColuna;
	private String idMeioSolicitacao;
	private Collection<MeioSolicitacao> colecaoMeiosSolicitacao;
	private String periodoInicial;
	private String periodoFinal;
	
	//Caso tipo Usuario
	private String idUnidadeSuperior;
	private String descUnidadeSuperior;
	private String idUnidadeOrganizacional;
	private String descUnidadeOrganizacional;
	private Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional;
	private String idUsuario;
	private String descUsuario;
	
	//Caso tipo Localidade
	private String idGerenciaRegional;
	private String descGerenciaRegional;
	private String idUnidadeNegocio;
	private String idLocalidade;
	private String descLocalidade;
	
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
	public String getDescFuncionalidade() {
		return descFuncionalidade;
	}
	public void setDescFuncionalidade(String descFuncionalidade) {
		this.descFuncionalidade = descFuncionalidade;
	}
	public String getIdColuna() {
		return idColuna;
	}
	public void setIdColuna(String idColuna) {
		this.idColuna = idColuna;
	}
	public String getDescColuna() {
		return descColuna;
	}
	public void setDescColuna(String descColuna) {
		this.descColuna = descColuna;
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
	public String getDescUnidadeSuperior() {
		return descUnidadeSuperior;
	}
	public void setDescUnidadeSuperior(String descUnidadeSuperior) {
		this.descUnidadeSuperior = descUnidadeSuperior;
	}
	public String getIdUnidadeOrganizacional() {
		return idUnidadeOrganizacional;
	}
	public void setIdUnidadeOrganizacional(String idUnidadeOrganizacional) {
		this.idUnidadeOrganizacional = idUnidadeOrganizacional;
	}
	public String getDescUnidadeOrganizacional() {
		return descUnidadeOrganizacional;
	}
	public void setDescUnidadeOrganizacional(String descUnidadeOrganizacional) {
		this.descUnidadeOrganizacional = descUnidadeOrganizacional;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getDescUsuario() {
		return descUsuario;
	}
	public void setDescUsuario(String descUsuario) {
		this.descUsuario = descUsuario;
	}
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public String getDescGerenciaRegional() {
		return descGerenciaRegional;
	}
	public void setDescGerenciaRegional(String descGerenciaRegional) {
		this.descGerenciaRegional = descGerenciaRegional;
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
	public String getDescLocalidade() {
		return descLocalidade;
	}
	public void setDescLocalidade(String descLocalidade) {
		this.descLocalidade = descLocalidade;
	}
	public Collection<Operacao> getColecaoOperacoes() {
		return colecaoOperacoes;
	}
	public void setColecaoOperacoes(Collection<Operacao> colecaoOperacoes) {
		this.colecaoOperacoes = colecaoOperacoes;
	}
	public String getIdOperacao() {
		return idOperacao;
	}
	public void setIdOperacao(String idOperacao) {
		this.idOperacao = idOperacao;
	}
	public String getIdMeioSolicitacao() {
		return idMeioSolicitacao;
	}
	public void setIdMeioSolicitacao(String idMeioSolicitacao) {
		this.idMeioSolicitacao = idMeioSolicitacao;
	}
	public Collection<MeioSolicitacao> getColecaoMeiosSolicitacao() {
		return colecaoMeiosSolicitacao;
	}
	public void setColecaoMeiosSolicitacao(
			Collection<MeioSolicitacao> colecaoMeiosSolicitacao) {
		this.colecaoMeiosSolicitacao = colecaoMeiosSolicitacao;
	}
	public Collection<UnidadeOrganizacional> getColecaoUnidadeOrganizacional() {
		return colecaoUnidadeOrganizacional;
	}
	public void setColecaoUnidadeOrganizacional(
			Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional) {
		this.colecaoUnidadeOrganizacional = colecaoUnidadeOrganizacional;
	}
	public String getTipoRelatorioEscolhido() {
		return tipoRelatorioEscolhido;
	}
	public void setTipoRelatorioEscolhido(String tipoRelatorioEscolhido) {
		this.tipoRelatorioEscolhido = tipoRelatorioEscolhido;
	}
}
