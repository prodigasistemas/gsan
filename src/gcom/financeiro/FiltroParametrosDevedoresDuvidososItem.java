package gcom.financeiro;


import gcom.util.filtro.Filtro;

public class FiltroParametrosDevedoresDuvidososItem extends Filtro {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroParametrosDevedoresDuvidososItem object
	 */
	public FiltroParametrosDevedoresDuvidososItem() {
	}

	/**
	 * Constructor for the FiltroParametrosDevedoresDuvidososItem object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroParametrosDevedoresDuvidososItem(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
		
	
}
