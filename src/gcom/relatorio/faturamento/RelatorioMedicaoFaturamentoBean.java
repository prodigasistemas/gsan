package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

public class RelatorioMedicaoFaturamentoBean implements RelatorioBean {

	private String anoMesReferencia;
	private String unidadeDeNegocio;
	private String grupo;
	private String empresa;
	private Integer qtdContasLidasEImpressas;
	private Integer qtdContasSoLidas;
	private Integer qtdContasSoImpressas;
	
	public RelatorioMedicaoFaturamentoBean() {
		
	}
	
	public RelatorioMedicaoFaturamentoBean(String anoMesReferencia,
			String unidadeDeNegocio, String grupo, String empresa,
			Integer qtdContasLidasEImpressas, Integer qtdContasSoLidas,
			Integer qtdContasSoImpressas) {
		this.anoMesReferencia = anoMesReferencia;
		this.unidadeDeNegocio = unidadeDeNegocio;
		this.grupo = grupo;
		this.empresa = empresa;
		this.qtdContasLidasEImpressas = qtdContasLidasEImpressas;
		this.qtdContasSoImpressas = qtdContasSoImpressas;
		this.qtdContasSoLidas = qtdContasSoLidas;
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
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public Integer getQtdContasLidasEImpressas() {
		return qtdContasLidasEImpressas;
	}
	public void setQtdContasLidasEImpressas(Integer qtdContasLidasEImpressas) {
		this.qtdContasLidasEImpressas = qtdContasLidasEImpressas;
	}
	public Integer getQtdContasSoLidas() {
		return qtdContasSoLidas;
	}
	public void setQtdContasSoLidas(Integer qtdContasSoLidas) {
		this.qtdContasSoLidas = qtdContasSoLidas;
	}
	public Integer getQtdContasSoImpressas() {
		return qtdContasSoImpressas;
	}
	public void setQtdContasSoImpressas(Integer qtdContasSoImpressas) {
		this.qtdContasSoImpressas = qtdContasSoImpressas;
	}
	
	
	
}
