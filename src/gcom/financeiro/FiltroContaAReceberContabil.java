package gcom.financeiro;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de contas a receber
 * @created 02/09/2012
 */
public class FiltroContaAReceberContabil extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroResumoContasAReceber object
	 */
	public FiltroContaAReceberContabil() {
	}

	/**
	 * Constructor for the FiltroResumoContasAReceber object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroContaAReceberContabil(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
		
	
	/**
	 * Description of the Field
	 */
	public final static String ANO_MES_REFERENCIA = "anoMesReferencia";
	
}
