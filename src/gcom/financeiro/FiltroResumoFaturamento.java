package gcom.financeiro;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de resumo de faturamento
 * @author Pedro Alexandre
 * @created 09 de Janeiro de 2006
 */
public class FiltroResumoFaturamento extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroResumoFaturamento object
	 */
	public FiltroResumoFaturamento() {
	}

	/**
	 * Constructor for the FiltroResumoFaturamento object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroResumoFaturamento(String campoOrderBy) {
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
