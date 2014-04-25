package gcom.gui.cadastro.unidade;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Rafael Pinto
 * @created 25/07/2006
 */
public class PesquisarUnidadeSuperiorActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
    private String unidadeTipoFilho;
    private String nivelHierarquicoFilho;
    private String idLocalidadeFilho;
    private String descricaoLocalidadeFilho;
    private String gerenciaRegionalFilho;
    private String descricaoFilho;
    private String siglaFilho;
    private String idEmpresaFilho;

    private String idUnidadeCentralizadoraFilho;
    
    private String unidadeEsgotoFilho;
    private String unidadeAbreRegistroFilho;
    private String unidadeAceitaFilho;
    private String meioSolicitacaoFilho;

	public String getDescricaoFilho() {
		return descricaoFilho;
	}

	public void setDescricaoFilho(String descricaoFilho) {
		this.descricaoFilho = descricaoFilho;
	}

	public String getDescricaoLocalidadeFilho() {
		return descricaoLocalidadeFilho;
	}

	public void setDescricaoLocalidadeFilho(String descricaoLocalidadeFilho) {
		this.descricaoLocalidadeFilho = descricaoLocalidadeFilho;
	}

	public String getGerenciaRegionalFilho() {
		return gerenciaRegionalFilho;
	}

	public void setGerenciaRegionalFilho(String gerenciaRegionalFilho) {
		this.gerenciaRegionalFilho = gerenciaRegionalFilho;
	}

	public String getIdEmpresaFilho() {
		return idEmpresaFilho;
	}

	public void setIdEmpresaFilho(String idEmpresaFilho) {
		this.idEmpresaFilho = idEmpresaFilho;
	}

	public String getIdLocalidadeFilho() {
		return idLocalidadeFilho;
	}

	public void setIdLocalidadeFilho(String idLocalidadeFilho) {
		this.idLocalidadeFilho = idLocalidadeFilho;
	}

	public String getIdUnidadeCentralizadoraFilho() {
		return idUnidadeCentralizadoraFilho;
	}

	public void setIdUnidadeCentralizadoraFilho(String idUnidadeCentralizadoraFilho) {
		this.idUnidadeCentralizadoraFilho = idUnidadeCentralizadoraFilho;
	}

	public String getMeioSolicitacaoFilho() {
		return meioSolicitacaoFilho;
	}

	public void setMeioSolicitacaoFilho(String meioSolicitacaoFilho) {
		this.meioSolicitacaoFilho = meioSolicitacaoFilho;
	}

	public String getNivelHierarquicoFilho() {
		return nivelHierarquicoFilho;
	}

	public void setNivelHierarquicoFilho(String nivelHierarquicoFilho) {
		this.nivelHierarquicoFilho = nivelHierarquicoFilho;
	}

	public String getSiglaFilho() {
		return siglaFilho;
	}

	public void setSiglaFilho(String siglaFilho) {
		this.siglaFilho = siglaFilho;
	}

	public String getUnidadeAbreRegistroFilho() {
		return unidadeAbreRegistroFilho;
	}

	public void setUnidadeAbreRegistroFilho(String unidadeAbreRegistroFilho) {
		this.unidadeAbreRegistroFilho = unidadeAbreRegistroFilho;
	}

	public String getUnidadeAceitaFilho() {
		return unidadeAceitaFilho;
	}

	public void setUnidadeAceitaFilho(String unidadeAceitaFilho) {
		this.unidadeAceitaFilho = unidadeAceitaFilho;
	}

	public String getUnidadeEsgotoFilho() {
		return unidadeEsgotoFilho;
	}

	public void setUnidadeEsgotoFilho(String unidadeEsgotoFilho) {
		this.unidadeEsgotoFilho = unidadeEsgotoFilho;
	}

	public String getUnidadeTipoFilho() {
		return unidadeTipoFilho;
	}

	public void setUnidadeTipoFilho(String unidadeTipoFilho) {
		this.unidadeTipoFilho = unidadeTipoFilho;
	}

}
