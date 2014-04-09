package gcom.gui.cadastro.unidade;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Rafael Pinto
 * @created 25/07/2006
 */
public class PesquisarUnidadeOrganizacionalActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
    private String unidadeTipo;
    private String nivelHierarquico;
    private String idLocalidade;
    private String descricaoLocalidade;
    private String gerenciaRegional;
    private String descricao;
    private String tipoPesquisa;
    private String sigla;
    private String idEmpresa;
    private String idUnidadeSuperior;
    private String descricaoUnidadeSuperior;

    private String idUnidadeCentralizadora;
    
    private String unidadeEsgoto;
    private String unidadeAbreRegistro;
    private String unidadeAceita;
    private String meioSolicitacao;

	public String getMeioSolicitacao() {
		return meioSolicitacao;
	}

	public void setMeioSolicitacao(String meioSolicitacao) {
		this.meioSolicitacao = meioSolicitacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdUnidadeCentralizadora() {
		return idUnidadeCentralizadora;
	}

	public void setIdUnidadeCentralizadora(String idUnidadeCentralizadora) {
		this.idUnidadeCentralizadora = idUnidadeCentralizadora;
	}

	public String getIdUnidadeSuperior() {
		return idUnidadeSuperior;
	}

	public void setIdUnidadeSuperior(String idUnidadeSuperior) {
		this.idUnidadeSuperior = idUnidadeSuperior;
	}

	public String getNivelHierarquico() {
		return nivelHierarquico;
	}

	public void setNivelHierarquico(String nivelHierarquico) {
		this.nivelHierarquico = nivelHierarquico;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getUnidadeTipo() {
		return unidadeTipo;
	}

	public void setUnidadeTipo(String unidadeTipo) {
		this.unidadeTipo = unidadeTipo;
	}

	public String getDescricaoUnidadeSuperior() {
		return descricaoUnidadeSuperior;
	}

	public void setDescricaoUnidadeSuperior(String descricaoUnidadeSuperior) {
		this.descricaoUnidadeSuperior = descricaoUnidadeSuperior;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getUnidadeAbreRegistro() {
		return unidadeAbreRegistro;
	}

	public void setUnidadeAbreRegistro(String unidadeAbreRegistro) {
		this.unidadeAbreRegistro = unidadeAbreRegistro;
	}

	public String getUnidadeAceita() {
		return unidadeAceita;
	}

	public void setUnidadeAceita(String unidadeAceita) {
		this.unidadeAceita = unidadeAceita;
	}

	public String getUnidadeEsgoto() {
		return unidadeEsgoto;
	}

	public void setUnidadeEsgoto(String unidadeEsgoto) {
		this.unidadeEsgoto = unidadeEsgoto;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

}
