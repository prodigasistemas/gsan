package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Arthur Carvalho
 * @date 13/02/2007
 */
public class RelatorioHistoricoMedicaoPocoBean implements RelatorioBean {
	
	private String mesAno;
	private String dataLeituraInformada;
	private String leituraInformada;
	private String dataLeituraFaturada;
	private String leituraFaturada;
	private String consumo;
	private String media;
	private String anormalidadeConsumo;	
	private String anormalidadeLeitura;	
	private String sitLeituraAtual;
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
	 * @return Retorna o campo anormalidadeLeitura.
	 */
	public String getAnormalidadeLeitura() {
		return anormalidadeLeitura;
	}
	/**
	 * @param anormalidadeLeitura O anormalidadeLeitura a ser setado.
	 */
	public void setAnormalidadeLeitura(String anormalidadeLeitura) {
		this.anormalidadeLeitura = anormalidadeLeitura;
	}
	/**
	 * @return Retorna o campo consumo.
	 */
	public String getConsumo() {
		return consumo;
	}
	/**
	 * @param consumo O consumo a ser setado.
	 */
	public void setConsumo(String consumo) {
		this.consumo = consumo;
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
	 * @return Retorna o campo mesAno.
	 */
	public String getMesAno() {
		return mesAno;
	}
	/**
	 * @param mesAno O mesAno a ser setado.
	 */
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	/**
	 * @return Retorna o campo sitLeituraAtual.
	 */
	public String getSitLeituraAtual() {
		return sitLeituraAtual;
	}
	/**
	 * @param sitLeituraAtual O sitLeituraAtual a ser setado.
	 */
	public void setSitLeituraAtual(String sitLeituraAtual) {
		this.sitLeituraAtual = sitLeituraAtual;
	}
	public RelatorioHistoricoMedicaoPocoBean(String mesAno, String dataLeituraInformada, String leituraInformada,
			String dataLeituraFaturada, String leituraFaturada, String consumo, String media, String anormalidadeConsumo, 
			String anormalidadeLeitura, String sitLeituraAtual) {
		this.mesAno = mesAno;
		this.dataLeituraInformada = dataLeituraInformada;
		this.leituraInformada = leituraInformada;
		this.dataLeituraFaturada = dataLeituraFaturada;
		this.leituraFaturada = leituraFaturada;
		this.consumo = consumo;
		this.media = media;
		this.anormalidadeConsumo = anormalidadeConsumo;
		this.anormalidadeLeitura = anormalidadeLeitura;
		this.sitLeituraAtual = sitLeituraAtual;
	}
	/**
	 * @return Retorna o campo media.
	 */
	public String getMedia() {
		return media;
	}
	/**
	 * @param media O media a ser setado.
	 */
	public void setMedia(String media) {
		this.media = media;
	}




}
