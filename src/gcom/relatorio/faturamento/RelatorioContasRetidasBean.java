package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * @author Pamela Gatinho
 * @date 15/09/2011
 */
public class RelatorioContasRetidasBean implements RelatorioBean {
	
	private String anoMesReferencia;
	private String unidadeDeNegocio;
	private String grupo;
	private Integer qtdContasRetidas;
	
	public RelatorioContasRetidasBean() {
		
	}
	
	public RelatorioContasRetidasBean (String anoMesReferencia,
			String unidadeDeNegocio, String grupo,
			Integer qtdContasRetidas) {
		
		this.anoMesReferencia = anoMesReferencia;
		this.unidadeDeNegocio = unidadeDeNegocio;
		this.grupo = grupo;
		this.qtdContasRetidas = qtdContasRetidas;
	}
	
	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}
	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
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
