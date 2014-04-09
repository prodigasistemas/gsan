package gcom.faturamento;

import gcom.util.filtro.Filtro;

/**
 * Descrição da classe
 * 
 * @author Eduardo Bianchi
 * @date 20/03/2007
 */
public class FiltroMotivoCancelamento extends Filtro {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for FiltroMotivoCancelamento object
	 */
	public FiltroMotivoCancelamento() {
	}

	/**
	 * Constructor for FiltroMotivoCancelamento object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroMotivoCancelamento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Descricao da Zona de Abastecimento
	 */
	public final static String DESCRICAO = "descricaoMotivoCancelamentoConta";

	/**
	 * Indicador de Uso da Zona de Abastecimento
	 */
	public final static String INDICADOR_USO = "indicadorUso";

}
