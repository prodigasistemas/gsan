package gcom.relatorio.cobranca.contratoparcelamento;

import gcom.relatorio.RelatorioBean;

/**
 * 
 * @author Paulo Diniz
 * @date 25/03/2011
 * 
 */
public class SubRelatorioDadosPagtoManterContratoParcelamentoPorClienteBean implements RelatorioBean {
	
	
	private String parcelaNumero;
	private String dataPagto;
	private String dataVenc;
	private String valParcel;
	private String valPagto;
	private String qtdItensPagos;
	
	public SubRelatorioDadosPagtoManterContratoParcelamentoPorClienteBean() {
		super();
	}

	public String getParcelaNumero() {
		return parcelaNumero;
	}

	public void setParcelaNumero(String parcelaNumero) {
		this.parcelaNumero = parcelaNumero;
	}

	public String getDataPagto() {
		return dataPagto;
	}

	public void setDataPagto(String dataPagto) {
		this.dataPagto = dataPagto;
	}

	public String getDataVenc() {
		return dataVenc;
	}

	public void setDataVenc(String dataVenc) {
		this.dataVenc = dataVenc;
	}

	public String getValParcel() {
		return valParcel;
	}

	public void setValParcel(String valParcel) {
		this.valParcel = valParcel;
	}

	public String getValPagto() {
		return valPagto;
	}

	public void setValPagto(String valPagto) {
		this.valPagto = valPagto;
	}

	public String getQtdItensPagos() {
		return qtdItensPagos;
	}

	public void setQtdItensPagos(String qtdItensPagos) {
		this.qtdItensPagos = qtdItensPagos;
	}

	

}
