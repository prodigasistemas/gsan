package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;
import java.util.Collection;


public class FiltrarRelatorioImoveisConsumoMedioHelper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
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
	
	private Integer consumoMedioAguaInicial;
	private Integer consumoMedioAguaFinal;
	
	private Collection<Integer> colecaoPerfisImovel = null;
	
	private Integer consumoMedioEsgotoInicial;
	private Integer consumoMedioEsgotoFinal;
	
	private Integer indicadorMedicaoComHidrometro;
	
	private Integer anoMesReferencia;
	
	
	
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Integer getGerenciaRegional() {
	
		return gerenciaRegional;
	}
	
	public void setGerenciaRegional(Integer gerenciaRegional) {
	
		this.gerenciaRegional = gerenciaRegional;
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
	
	public Integer getUnidadeNegocio() {
	
		return unidadeNegocio;
	}
	
	public void setUnidadeNegocio(Integer unidadeNegocio) {
	
		this.unidadeNegocio = unidadeNegocio;
	}

	
	public Integer getConsumoMedioAguaFinal() {
	
		return consumoMedioAguaFinal;
	}

	
	public void setConsumoMedioAguaFinal(Integer consumoMedioAguaFinal) {
	
		this.consumoMedioAguaFinal = consumoMedioAguaFinal;
	}

	
	public Integer getConsumoMedioAguaInicial() {
	
		return consumoMedioAguaInicial;
	}

	
	public void setConsumoMedioAguaInicial(Integer consumoMedioAguaInicial) {
	
		this.consumoMedioAguaInicial = consumoMedioAguaInicial;
	}
	
	public Collection<Integer> getColecaoPerfisImovel() {
		return colecaoPerfisImovel;
	}

	public void setColecaoPerfisImovel(Collection<Integer> colecaoPerfisImovel) {
		this.colecaoPerfisImovel = colecaoPerfisImovel;
	}

	public Integer getConsumoMedioEsgotoFinal() {
	
		return consumoMedioEsgotoFinal;
	}

	
	public void setConsumoMedioEsgotoFinal(Integer consumoMedioEsgotoFinal) {
	
		this.consumoMedioEsgotoFinal = consumoMedioEsgotoFinal;
	}

	
	public Integer getConsumoMedioEsgotoInicial() {
	
		return consumoMedioEsgotoInicial;
	}

	
	public void setConsumoMedioEsgotoInicial(Integer consumoMedioEsgotoInicial) {
	
		this.consumoMedioEsgotoInicial = consumoMedioEsgotoInicial;
	}

	public Integer getIndicadorMedicaoComHidrometro() {
		return indicadorMedicaoComHidrometro;
	}

	public void setIndicadorMedicaoComHidrometro(
			Integer indicadorMedicaoComHidrometro) {
		this.indicadorMedicaoComHidrometro = indicadorMedicaoComHidrometro;
	}
}
