package gcom.financeiro;


import gcom.util.filtro.Filtro;

public class FiltroResumoDevedoresDuvidosos extends Filtro {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroResumoDevedoresDuvidosos object
	 */
	public FiltroResumoDevedoresDuvidosos() {
	}

	/**
	 * Constructor for the FiltroResumoDevedoresDuvidosos object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroResumoDevedoresDuvidosos(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
		
	
}
