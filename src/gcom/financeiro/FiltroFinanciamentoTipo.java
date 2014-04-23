package gcom.financeiro;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

public class FiltroFinanciamentoTipo extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroFinanciamentoTipo object
	 */
	public FiltroFinanciamentoTipo() {
	}

	/**
	 * Constructor for the FiltroFinanciamentoTipo object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroFinanciamentoTipo(String campoOrderBy) {
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
	
	
	public final static String INDICADOR_USO = "indicadorUso";
	
	
}
