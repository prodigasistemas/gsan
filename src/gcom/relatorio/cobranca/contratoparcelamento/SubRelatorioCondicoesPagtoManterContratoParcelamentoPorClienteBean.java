package gcom.relatorio.cobranca.contratoparcelamento;

import gcom.relatorio.RelatorioBean;

/**
 * 
 * @author Paulo Diniz
 * @date 25/03/2011
 * 
 */
public class SubRelatorioCondicoesPagtoManterContratoParcelamentoPorClienteBean implements RelatorioBean {
	
	
	private String parcelaNumero;
	private String valorParcel;
	private String dataVencParcel;
	private String situacaoParcel;
	
	public SubRelatorioCondicoesPagtoManterContratoParcelamentoPorClienteBean() {
		super();
	}

	public String getParcelaNumero() {
		return parcelaNumero;
	}

	public void setParcelaNumero(String parcelaNumero) {
		this.parcelaNumero = parcelaNumero;
	}

	public String getValorParcel() {
		return valorParcel;
	}

	public void setValorParcel(String valorParcel) {
		this.valorParcel = valorParcel;
	}

	public String getDataVencParcel() {
		return dataVencParcel;
	}

	public void setDataVencParcel(String dataVencParcel) {
		this.dataVencParcel = dataVencParcel;
	}

	public String getSituacaoParcel() {
		return situacaoParcel;
	}

	public void setSituacaoParcel(String situacaoParcel) {
		this.situacaoParcel = situacaoParcel;
	}


}
