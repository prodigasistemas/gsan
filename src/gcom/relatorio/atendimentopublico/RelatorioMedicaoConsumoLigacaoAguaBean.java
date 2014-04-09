package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Ana Maria
 * @date 13/02/2007
 */
public class RelatorioMedicaoConsumoLigacaoAguaBean implements RelatorioBean {
	
    //Medição
	private String mesAnoMedicao;
	private String dataLeituraInformada;
	private String leituraInformada;
	private String dataLeituraFaturada;
	private String leituraFaturada;
	private String anormalidadeInformada;
	private String anormalidadeFaturada;
	private String leituraAtual;

	//Consumo
	private String mesAnoConsumo;
	private String consumoMedido;
	private String consumoFaturado;
	private String anormalidadeConsumo;
	private String diasConsumo;
	private String tipoConsumo;


	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioMedicaoConsumoLigacaoAguaBean(String mesAnoMedicao, String dataLeituraInformada, 
			String leituraInformada, String dataLeituraFaturada, String leituraFaturada,String anormalidadeInformada,
			String anormalidadeFaturada, String leituraAtual, String mesAnoConsumo, String consumoMedido, 
			String consumoFaturado, String anormalidadeConsumo, String diasConsumo, String tipoConsumo) {
		this.mesAnoMedicao = mesAnoMedicao;
		this.dataLeituraInformada = dataLeituraInformada;
		this.leituraInformada = leituraInformada;
		this.dataLeituraFaturada = dataLeituraFaturada;
		this.leituraFaturada = leituraFaturada;
		this.anormalidadeInformada = anormalidadeInformada;
		this.anormalidadeFaturada = anormalidadeFaturada;
		this.leituraAtual = leituraAtual;
		this.mesAnoConsumo = mesAnoConsumo;
		this.consumoMedido = consumoMedido;
		this.consumoFaturado = consumoFaturado;
		this.anormalidadeConsumo = anormalidadeConsumo;
		this.diasConsumo = diasConsumo;
		this.tipoConsumo = tipoConsumo;
	}

	/**
	 * @return Retorna o campo anormalidadeConsumo.
	 */
	public String getAnormalidadeConsumo() {
		return anormalidadeConsumo;
	}


	/**
	 * @param anormalidadeConsumo O anormalidadeConsumo a ser setado.
	 */
	public void setAnormalidadeConsumo(String anormalidadeConsumo) {
		this.anormalidadeConsumo = anormalidadeConsumo;
	}


	/**
	 * @return Retorna o campo anormalidadeFaturada.
	 */
	public String getAnormalidadeFaturada() {
		return anormalidadeFaturada;
	}


	/**
	 * @param anormalidadeFaturada O anormalidadeFaturada a ser setado.
	 */
	public void setAnormalidadeFaturada(String anormalidadeFaturada) {
		this.anormalidadeFaturada = anormalidadeFaturada;
	}


	/**
	 * @return Retorna o campo anormalidadeInformada.
	 */
	public String getAnormalidadeInformada() {
		return anormalidadeInformada;
	}


	/**
	 * @param anormalidadeInformada O anormalidadeInformada a ser setado.
	 */
	public void setAnormalidadeInformada(String anormalidadeInformada) {
		this.anormalidadeInformada = anormalidadeInformada;
	}


	/**
	 * @return Retorna o campo consumoFaturado.
	 */
	public String getConsumoFaturado() {
		return consumoFaturado;
	}


	/**
	 * @param consumoFaturado O consumoFaturado a ser setado.
	 */
	public void setConsumoFaturado(String consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}


	/**
	 * @return Retorna o campo consumoMedido.
	 */
	public String getConsumoMedido() {
		return consumoMedido;
	}


	/**
	 * @param consumoMedido O consumoMedido a ser setado.
	 */
	public void setConsumoMedido(String consumoMedido) {
		this.consumoMedido = consumoMedido;
	}


	/**
	 * @return Retorna o campo dataLeituraFaturada.
	 */
	public String getDataLeituraFaturada() {
		return dataLeituraFaturada;
	}


	/**
	 * @param dataLeituraFaturada O dataLeituraFaturada a ser setado.
	 */
	public void setDataLeituraFaturada(String dataLeituraFaturada) {
		this.dataLeituraFaturada = dataLeituraFaturada;
	}


	/**
	 * @return Retorna o campo dataLeituraInformada.
	 */
	public String getDataLeituraInformada() {
		return dataLeituraInformada;
	}


	/**
	 * @param dataLeituraInformada O dataLeituraInformada a ser setado.
	 */
	public void setDataLeituraInformada(String dataLeituraInformada) {
		this.dataLeituraInformada = dataLeituraInformada;
	}


	/**
	 * @return Retorna o campo diasConsumo.
	 */
	public String getDiasConsumo() {
		return diasConsumo;
	}


	/**
	 * @param diasConsumo O diasConsumo a ser setado.
	 */
	public void setDiasConsumo(String diasConsumo) {
		this.diasConsumo = diasConsumo;
	}


	/**
	 * @return Retorna o campo leituraAtual.
	 */
	public String getLeituraAtual() {
		return leituraAtual;
	}


	/**
	 * @param leituraAtual O leituraAtual a ser setado.
	 */
	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}


	/**
	 * @return Retorna o campo leituraFaturada.
	 */
	public String getLeituraFaturada() {
		return leituraFaturada;
	}


	/**
	 * @param leituraFaturada O leituraFaturada a ser setado.
	 */
	public void setLeituraFaturada(String leituraFaturada) {
		this.leituraFaturada = leituraFaturada;
	}


	/**
	 * @return Retorna o campo leituraInformada.
	 */
	public String getLeituraInformada() {
		return leituraInformada;
	}


	/**
	 * @param leituraInformada O leituraInformada a ser setado.
	 */
	public void setLeituraInformada(String leituraInformada) {
		this.leituraInformada = leituraInformada;
	}


	/**
	 * @return Retorna o campo mesAnoConsumo.
	 */
	public String getMesAnoConsumo() {
		return mesAnoConsumo;
	}


	/**
	 * @param mesAnoConsumo O mesAnoConsumo a ser setado.
	 */
	public void setMesAnoConsumo(String mesAnoConsumo) {
		this.mesAnoConsumo = mesAnoConsumo;
	}


	/**
	 * @return Retorna o campo mesAnoMedicao.
	 */
	public String getMesAnoMedicao() {
		return mesAnoMedicao;
	}


	/**
	 * @param mesAnoMedicao O mesAnoMedicao a ser setado.
	 */
	public void setMesAnoMedicao(String mesAnoMedicao) {
		this.mesAnoMedicao = mesAnoMedicao;
	}


	/**
	 * @return Retorna o campo tipoConsumo.
	 */
	public String getTipoConsumo() {
		return tipoConsumo;
	}


	/**
	 * @param tipoConsumo O tipoConsumo a ser setado.
	 */
	public void setTipoConsumo(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}

}
