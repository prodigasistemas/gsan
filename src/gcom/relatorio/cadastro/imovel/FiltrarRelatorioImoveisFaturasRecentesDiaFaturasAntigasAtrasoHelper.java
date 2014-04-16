package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;
import java.util.Collection;


/**
 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e Faturas Antigas em Atraso
 * 
 * @author Rafael Pinto
 * @date 08/01/2008
 */
public class FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer unidadeNegocio;
	private Integer gerenciaRegional;
	
	private Integer localidadeInicial;
	private Integer setorComercialInicial;
	private Integer rotaInicial;
	private Integer sequencialRotalInicial;

	private Integer localidadeFinal;
	private Integer setorComercialFinal;
	private Integer rotaFinal;
	private Integer sequencialRotalFinal;
	
	private Collection<Integer> situacaoLigacaoAgua;
	private Collection<Integer> categorias;

	private Integer referenciaFaturasDiaInicial;
	private Integer referenciaFaturasDiaFinal;

	private Integer referenciaFaturasAtrasoInicial;
	private Integer referenciaFaturasAtrasoFinal;


	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Integer getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Integer localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Integer getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Integer localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Integer getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(Integer rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public Integer getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(Integer rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public Integer getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(Integer setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public Integer getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(Integer setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public Integer getSequencialRotalFinal() {
		return sequencialRotalFinal;
	}

	public void setSequencialRotalFinal(Integer sequencialRotalFinal) {
		this.sequencialRotalFinal = sequencialRotalFinal;
	}

	public Integer getSequencialRotalInicial() {
		return sequencialRotalInicial;
	}

	public void setSequencialRotalInicial(Integer sequencialRotalInicial) {
		this.sequencialRotalInicial = sequencialRotalInicial;
	}

	public Collection<Integer> getCategorias() {
		return categorias;
	}

	public void setCategorias(Collection<Integer> categorias) {
		this.categorias = categorias;
	}

	public Integer getReferenciaFaturasAtrasoFinal() {
		return referenciaFaturasAtrasoFinal;
	}

	public void setReferenciaFaturasAtrasoFinal(Integer referenciaFaturasAtrasoFinal) {
		this.referenciaFaturasAtrasoFinal = referenciaFaturasAtrasoFinal;
	}

	public Integer getReferenciaFaturasAtrasoInicial() {
		return referenciaFaturasAtrasoInicial;
	}

	public void setReferenciaFaturasAtrasoInicial(
			Integer referenciaFaturasAtrasoInicial) {
		this.referenciaFaturasAtrasoInicial = referenciaFaturasAtrasoInicial;
	}

	public Integer getReferenciaFaturasDiaFinal() {
		return referenciaFaturasDiaFinal;
	}

	public void setReferenciaFaturasDiaFinal(Integer referenciaFaturasDiaFinal) {
		this.referenciaFaturasDiaFinal = referenciaFaturasDiaFinal;
	}

	public Integer getReferenciaFaturasDiaInicial() {
		return referenciaFaturasDiaInicial;
	}

	public void setReferenciaFaturasDiaInicial(Integer referenciaFaturasDiaInicial) {
		this.referenciaFaturasDiaInicial = referenciaFaturasDiaInicial;
	}

	public Collection<Integer> getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(Collection<Integer> situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	
	
	
}
