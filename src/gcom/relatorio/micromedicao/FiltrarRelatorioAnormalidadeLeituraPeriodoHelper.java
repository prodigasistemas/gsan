package gcom.relatorio.micromedicao;

import java.io.Serializable;

/**
 *[UC0958] Gerar Relatório Juras, Multas e Débitos Cancelados
 * 
 * @author Marlon Patrick
 * @since 22/10/2009
 */
public class FiltrarRelatorioAnormalidadeLeituraPeriodoHelper implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private Integer anoMesReferenciaInicial;
	private Integer anoMesReferenciaFinal;
	private Integer quantidadeMeses;

	private Integer anormalidadeLeitura;

	private Integer grupoFaturamento;

	private Integer unidadeNegocio;

	private Integer localidadeInicial;
	private Integer localidadeFinal;

	private Integer setorComercialInicial;
	private Integer setorComercialFinal;

	private Integer rotaInicial;
	private Integer rotaFinal;

	private Integer sequencialRotaInicial;
	private Integer sequencialRotaFinal;
	
	public Integer getAnoMesReferenciaInicial() {
		return anoMesReferenciaInicial;
	}

	public void setAnoMesReferenciaInicial(Integer mesAnoReferenciaInicial) {
		this.anoMesReferenciaInicial = mesAnoReferenciaInicial;
	}

	public Integer getAnoMesReferenciaFinal() {
		return anoMesReferenciaFinal;
	}

	public void setAnoMesReferenciaFinal(Integer mesAnoReferenciaFinal) {
		this.anoMesReferenciaFinal = mesAnoReferenciaFinal;
	}

	public Integer getAnormalidadeLeitura() {
		return anormalidadeLeitura;
	}

	public void setAnormalidadeLeitura(Integer anormalidadeLeitura) {
		this.anormalidadeLeitura = anormalidadeLeitura;
	}

	public Integer getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(Integer grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Integer getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Integer localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Integer getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Integer localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Integer getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(Integer setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public Integer getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(Integer setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public Integer getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(Integer rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public Integer getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(Integer rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public Integer getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(Integer sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public Integer getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(Integer sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public Integer getQuantidadeMeses() {
		return quantidadeMeses;
	}

	public void setQuantidadeMeses(Integer quantidadeMeses) {
		this.quantidadeMeses = quantidadeMeses;
	}
}
