package gcom.operacional;

import gcom.util.filtro.Filtro;


/**
 * Descrição da classe
 * 
 * @author Rafael Pinto
 * @date 04/12/2006
 */
public class FiltroSetorAbastecimento extends Filtro {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroSetorAbastecimento object
	 */
	public FiltroSetorAbastecimento() {
	}

	/**
	 * Constructor for the FiltroSetorAbastecimento object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroSetorAbastecimento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	public final static String INDICADOR_USO = "indicadorUso";
	
	public final static String SISTEMA_ABASTECIMENTO_ID = "sistemaAbastecimento.id";

}
