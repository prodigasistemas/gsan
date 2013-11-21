package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Sávio Luiz
 * @since 26/09/2007
 */

public class FiltroIndicesAcrescimosImpontualidade extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroCobrancaCriterio object
	 */
	public FiltroIndicesAcrescimosImpontualidade() {
	}

	/**
	 * Constructor for the FiltroCobrancaCriterio object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroIndicesAcrescimosImpontualidade(String campoOrderBy) {
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
