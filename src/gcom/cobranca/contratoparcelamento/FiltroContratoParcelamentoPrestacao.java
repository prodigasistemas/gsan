package gcom.cobranca.contratoparcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroContratoParcelamentoPrestacao extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 */
	public FiltroContratoParcelamentoPrestacao() {
	}

	/**
	 * Constructor
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroContratoParcelamentoPrestacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Código
	 */
	public final static String ID = "id";

	public final static String CONTRATO_PARCEL = "contratoParcelamento";
	
	public final static String CONTRATO_PARCEL_ID = "contratoParcelamento.id";
	
	public final static String VALOR = "valor";
	
	public final static String VALOR_PAGAMENTO = "valorPagamento";
	
	public final static String NUMERO = "numero";
	

}
