package gcom.gui.relatorio.cadastro.imovel;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC00727] Gerar Relatório de Imóveis por Consumo Médio
 * 
 * @author Bruno Barros
 * 
 * @date 12/12/2007
 */

public class GerarRelatorioImoveisConsumoMedioActionForm extends
		ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String unidadeNegocio;

	private String gerenciaRegional;

	private String localidadeInicial;

	private String nomeLocalidadeInicial;

	private String setorComercialInicial;

	private String nomeSetorComercialInicial;

	private String rotaInicial;

	private String sequencialRotaInicial;

	private String localidadeFinal;

	private String nomeLocalidadeFinal;

	private String setorComercialFinal;

	private String nomeSetorComercialFinal;

	private String rotaFinal;

	private String sequencialRotaFinal;

	private String consumoMedioAguaInicial;

	private String consumoMedioAguaFinal;

	private String[] perfisImovel;

	private String consumoMedioEsgotoInicial;

	private String consumoMedioEsgotoFinal;

	private String indicadorMedicaoComHidrometro;
	
	private String anoMesReferencia;

	public void reset() {

		this.localidadeInicial = null;
		this.nomeLocalidadeInicial = null;

		this.setorComercialInicial = null;
		this.nomeSetorComercialInicial = null;

		this.rotaInicial = null;
		this.sequencialRotaInicial = null;

		this.localidadeFinal = null;
		this.nomeLocalidadeFinal = null;

		this.setorComercialFinal = null;
		this.nomeSetorComercialFinal = null;

		this.rotaFinal = null;
		this.sequencialRotaFinal = null;

		this.gerenciaRegional = null;
		this.unidadeNegocio = null;

		this.consumoMedioAguaInicial = null;
		this.consumoMedioAguaFinal = null;

		this.perfisImovel = null;

		this.consumoMedioEsgotoInicial = null;
		this.consumoMedioEsgotoFinal = null;

		this.indicadorMedicaoComHidrometro = null;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}

	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}

	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}

	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}

	public String getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(String setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public String getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(String setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public String getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public String getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(String rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public String getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(String sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public String getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(String sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public String getConsumoMedioAguaFinal() {

		return consumoMedioAguaFinal;
	}

	public void setConsumoMedioAguaFinal(String consumoMedioAguaFinal) {

		this.consumoMedioAguaFinal = consumoMedioAguaFinal;
	}

	public String getConsumoMedioAguaInicial() {

		return consumoMedioAguaInicial;
	}
	
	public String[] getPerfisImovel() {
		return perfisImovel;
	}

	public void setPerfisImovel(String[] perfisImovel) {
		this.perfisImovel = perfisImovel;
	}

	public void setConsumoMedioAguaInicial(String consumoMedioAguaInicial) {

		this.consumoMedioAguaInicial = consumoMedioAguaInicial;
	}

	public String getConsumoMedioEsgotoFinal() {

		return consumoMedioEsgotoFinal;
	}

	public void setConsumoMedioEsgotoFinal(String consumoMedioEsgotoFinal) {

		this.consumoMedioEsgotoFinal = consumoMedioEsgotoFinal;
	}

	public String getConsumoMedioEsgotoInicial() {

		return consumoMedioEsgotoInicial;
	}

	public void setConsumoMedioEsgotoInicial(String consumoMedioEsgotoInicial) {

		this.consumoMedioEsgotoInicial = consumoMedioEsgotoInicial;
	}

	public String getIndicadorMedicaoComHidrometro() {
		return indicadorMedicaoComHidrometro;
	}

	public void setIndicadorMedicaoComHidrometro(
			String indicadorMedicaoComHidrometro) {
		this.indicadorMedicaoComHidrometro = indicadorMedicaoComHidrometro;
	}

	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	
}
