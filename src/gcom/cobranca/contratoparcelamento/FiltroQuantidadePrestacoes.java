package gcom.cobranca.contratoparcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroQuantidadePrestacoes extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 */
	public FiltroQuantidadePrestacoes() {
	}

	/**
	 * Constructor
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroQuantidadePrestacoes(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Código
	 */
	public final static String CONTRATO_PARCELAMENTO_RD = "contratoRD";

	public final static String ID = "id";
	
	public final static String QTD_FATURAS_PARCELADAS = "qtdFaturasParceladas";

	public final static String CONTRATO_PARCELAMENTO_RD_ID = "contratoRD.id";
	
	public final static String CONTRATO_PARCELAMENTO_RD_NUMERO = "contratoRD.numero";



}
