package gcom.relatorio.faturamento;

import java.io.Serializable;

import gcom.util.Util;

public class RelatorioContasRetidasHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5116263322342672220L;
	private String anoMesReferencia;
	private String unidadeDeNegocio;
	private String grupo;
	private Integer qtdContasRetidas;
	
	public RelatorioContasRetidasHelper(){}
	
	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}
	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = Util.formatarAnoMesParaMesAnoSemBarra(anoMesReferencia);
	}
	public String getUnidadeDeNegocio() {
		return unidadeDeNegocio;
	}
	public void setUnidadeDeNegocio(String unidadeDeNegocio) {
		this.unidadeDeNegocio = unidadeDeNegocio;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public Integer getQtdContasRetidas() {
		return qtdContasRetidas;
	}
	public void setQtdContasRetidas(Integer qtdContasRetidas) {
		this.qtdContasRetidas = qtdContasRetidas;
	}
}
