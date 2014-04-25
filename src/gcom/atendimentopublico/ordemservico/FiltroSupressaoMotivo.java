package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

/**
 * Filtrar Supressao Motivo
 * @author Rafael Pinto
 * @since 19/7/2006
 *
 */
public class FiltroSupressaoMotivo extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor for the FiltroOrdemServico object
	 */
	public FiltroSupressaoMotivo() {
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

	public FiltroSupressaoMotivo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

}
