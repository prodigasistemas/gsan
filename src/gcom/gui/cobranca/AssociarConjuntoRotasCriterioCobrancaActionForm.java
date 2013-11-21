package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 28 de Junho de 2004
 */
public class AssociarConjuntoRotasCriterioCobrancaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idAcaoCobranca;

    private String idCriterioCobranca;
    private String descricaoCriterioCobranca;

    private String idGrupoCobranca;

    private String idGerenciaRegional;

    private String idUnidadeNegocio;

    private String idLocalidadeInicial;
    private String idLocalidadeFinal;
    
    private String descricaoLocalidadeInicial;
    private String descricaoLocalidadeFinal;
    
    private String codigoSetorComercialInicial;
    private String codigoSetorComercialFinal;
    
    private String descricaoSetorComercialInicial;
    private String descricaoSetorComercialFinal;
    
    private String numeroRotaInicial;
    private String numeroRotaFinal;
    
    private String qtdRotasSelecionadas;
    private String qtdRotasComCriterio;
    private String qtdRotasSemCriterio;
    
	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}
	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}
	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}
	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}
	public String getDescricaoCriterioCobranca() {
		return descricaoCriterioCobranca;
	}
	public void setDescricaoCriterioCobranca(String descricaoCriterioCobranca) {
		this.descricaoCriterioCobranca = descricaoCriterioCobranca;
	}
	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}
	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}
	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}
	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}
	public String getDescricaoSetorComercialFinal() {
		return descricaoSetorComercialFinal;
	}
	public void setDescricaoSetorComercialFinal(String descricaoSetorComercialFinal) {
		this.descricaoSetorComercialFinal = descricaoSetorComercialFinal;
	}
	public String getDescricaoSetorComercialInicial() {
		return descricaoSetorComercialInicial;
	}
	public void setDescricaoSetorComercialInicial(
			String descricaoSetorComercialInicial) {
		this.descricaoSetorComercialInicial = descricaoSetorComercialInicial;
	}
	public String getIdAcaoCobranca() {
		return idAcaoCobranca;
	}
	public void setIdAcaoCobranca(String idAcaoCobranca) {
		this.idAcaoCobranca = idAcaoCobranca;
	}
	public String getIdCriterioCobranca() {
		return idCriterioCobranca;
	}
	public void setIdCriterioCobranca(String idCriterioCobranca) {
		this.idCriterioCobranca = idCriterioCobranca;
	}
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public String getIdGrupoCobranca() {
		return idGrupoCobranca;
	}
	public void setIdGrupoCobranca(String idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}
	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}
	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public String getNumeroRotaFinal() {
		return numeroRotaFinal;
	}
	public void setNumeroRotaFinal(String numeroRotaFinal) {
		this.numeroRotaFinal = numeroRotaFinal;
	}
	public String getNumeroRotaInicial() {
		return numeroRotaInicial;
	}
	public void setNumeroRotaInicial(String numeroRotaInicial) {
		this.numeroRotaInicial = numeroRotaInicial;
	}
	public String getQtdRotasComCriterio() {
		return qtdRotasComCriterio;
	}
	public void setQtdRotasComCriterio(String qtdRotasComCriterio) {
		this.qtdRotasComCriterio = qtdRotasComCriterio;
	}
	public String getQtdRotasSelecionadas() {
		return qtdRotasSelecionadas;
	}
	public void setQtdRotasSelecionadas(String qtdRotasSelecionadas) {
		this.qtdRotasSelecionadas = qtdRotasSelecionadas;
	}
	public String getQtdRotasSemCriterio() {
		return qtdRotasSemCriterio;
	}
	public void setQtdRotasSemCriterio(String qtdRotasSemCriterio) {
		this.qtdRotasSemCriterio = qtdRotasSemCriterio;
	}

    

}
