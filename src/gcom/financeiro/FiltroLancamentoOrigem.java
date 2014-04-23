package gcom.financeiro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroLancamentoOrigem extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroFinanciamentoTipo object
	 */
	public FiltroLancamentoOrigem() {
	}

	/**
	 * Constructor for the FiltroFinanciamentoTipo object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroLancamentoOrigem(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
		
	
	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricao";
	
}
