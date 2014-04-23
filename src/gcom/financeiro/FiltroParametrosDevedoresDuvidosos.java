package gcom.financeiro;


import gcom.util.filtro.Filtro;

public class FiltroParametrosDevedoresDuvidosos extends Filtro {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroFinanciamentoTipo object
	 */
	public FiltroParametrosDevedoresDuvidosos() {
	}

	/**
	 * Constructor for the FiltroFinanciamentoTipo object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroParametrosDevedoresDuvidosos(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	public final static String ANO_MES_REFERENCIA_CONTABIL = "anoMesReferenciaContabil";
		
	
}
