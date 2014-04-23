package gcom.atendimentopublico.ligacaoagua;

import gcom.util.filtro.Filtro;

/**
 * Filtrar Supressao Motivo
 * 
 * @author Rafael Pinto
 * @since 19/7/2006
 * 
 */
public class FiltroSupressaoTipo extends Filtro {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroOrdemServico object
	 */
	public FiltroSupressaoTipo() {
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricao";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_USO = "indicadorUso";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_TOTAL = "indicadorTotal";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_PARCIAL = "indicadorParcial";

	public FiltroSupressaoTipo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

}
