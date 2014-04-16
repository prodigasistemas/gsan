package gcom.micromedicao.bean;

import java.io.Serializable;

/**
 * [UC0543] Associar Conjunto de Rotas a Critério de Cobrança
 * 
 * @author Raphael Rossiter
 * @date 25/01/2008
 */
public class AssociarConjuntoRotasCriterioCobrancaHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idCriterioCobranca;
	private Integer idCobrancaAcao;
	private Integer idGrupoCobranca;
	private Integer idGerencialRegional;
	private Integer idUnidadeNegocio;
	private Integer idLocalidadeInicial;
	private Integer idLocalidadeFinal;
	private Integer cdSetorComercialInicial;
	private Integer cdSetorComercialFinal;
	private Integer nnRotaInicial;
	private Integer nnRotaFinal;
	
	private boolean validarCriterioCobranca;
	
	public AssociarConjuntoRotasCriterioCobrancaHelper(){}

	public Integer getCdSetorComercialFinal() {
		return cdSetorComercialFinal;
	}

	public void setCdSetorComercialFinal(Integer cdSetorComercialFinal) {
		this.cdSetorComercialFinal = cdSetorComercialFinal;
	}

	public Integer getCdSetorComercialInicial() {
		return cdSetorComercialInicial;
	}

	public void setCdSetorComercialInicial(Integer cdSetorComercialInicial) {
		this.cdSetorComercialInicial = cdSetorComercialInicial;
	}

	public Integer getIdGerencialRegional() {
		return idGerencialRegional;
	}

	public void setIdGerencialRegional(Integer idGerencialRegional) {
		this.idGerencialRegional = idGerencialRegional;
	}

	public Integer getIdGrupoCobranca() {
		return idGrupoCobranca;
	}

	public void setIdGrupoCobranca(Integer idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}

	public Integer getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(Integer idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public Integer getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(Integer idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public Integer getNnRotaFinal() {
		return nnRotaFinal;
	}

	public void setNnRotaFinal(Integer nnRotaFinal) {
		this.nnRotaFinal = nnRotaFinal;
	}

	public Integer getNnRotaInicial() {
		return nnRotaInicial;
	}

	public void setNnRotaInicial(Integer nnRotaInicial) {
		this.nnRotaInicial = nnRotaInicial;
	}

	public Integer getIdCobrancaAcao() {
		return idCobrancaAcao;
	}

	public void setIdCobrancaAcao(Integer idCobrancaAcao) {
		this.idCobrancaAcao = idCobrancaAcao;
	}

	public Integer getIdCriterioCobranca() {
		return idCriterioCobranca;
	}

	public void setIdCriterioCobranca(Integer idCriterioCobranca) {
		this.idCriterioCobranca = idCriterioCobranca;
	}

	public boolean isValidarCriterioCobranca() {
		return validarCriterioCobranca;
	}

	public void setValidarCriterioCobranca(boolean validarCriterioCobranca) {
		this.validarCriterioCobranca = validarCriterioCobranca;
	}

	
	
	
}
