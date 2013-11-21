package gcom.cobranca.contratoparcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroPrestacaoContratoParcelamento extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 */
	public FiltroPrestacaoContratoParcelamento() {
	}

	/**
	 * Constructor
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroPrestacaoContratoParcelamento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Código
	 */
	public final static String CONTRATO_PARCELAMENTO = "contratoParcelamento";

	public final static String ID = "id";
	
	public final static String NUMERO = "numero";

	public final static String CONTRATO_PARCELAMENTO_ID = "contratoParcelamento.id";
	
	public final static String CONTRATO_PARCELAMENTO_NUMERO = "contratoParcelamento.numero";



}
