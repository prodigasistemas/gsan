package gcom.arrecadacao;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de resumo de arrecadação
 * @author Bruno Barros
 * @created 07/07/2008
 */
public class FiltroResumoArrecadacao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroResumoArrecadacao object
	 */
	public FiltroResumoArrecadacao() {
	}

	/**
	 * Constructor for the FiltroResumoArrecadacao object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroResumoArrecadacao(String campoOrderBy) {
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
