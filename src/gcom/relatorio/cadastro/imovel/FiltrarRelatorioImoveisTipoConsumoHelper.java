package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;
import java.util.Collection;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de imoveis por Tipo de Consumo 
 *
 * @author Bruno Barros
 * @date 10/01/2008
 */
public class FiltrarRelatorioImoveisTipoConsumoHelper implements Serializable{
	
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
	
	private Collection<Integer> tiposConsumo;
	
	private Integer referenciaInicial;
	private Integer referenciaFinal;
	
	public Collection<Integer> getTiposConsumo() {
	
		return tiposConsumo;
	}

	
	public void setTiposConsumo(Collection<Integer> tiposConsumo) {
	
		this.tiposConsumo = tiposConsumo;
	}

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


	
	public Integer getReferenciaInicial() {
	
		return referenciaInicial;
	}


	
	public void setReferenciaInicial(Integer referenciaInicial) {
	
		this.referenciaInicial = referenciaInicial;
	}


	
	public Integer getReferenciaFinal() {
	
		return referenciaFinal;
	}


	
	public void setReferenciaFinal(Integer referenciaFinal) {
	
		this.referenciaFinal = referenciaFinal;
	}	
}
