package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroCobrancaForma extends Filtro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FiltroCobrancaForma() {
	}

	public FiltroCobrancaForma(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}


	public final static String ID = "id";
	public final static String IC_USO_CONTRATO_PARCEL_CLIENTE = "indicadorUsoContratoParcelamentoCliente";

}
