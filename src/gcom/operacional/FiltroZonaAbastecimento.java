package gcom.operacional;

import gcom.util.filtro.Filtro;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 31/08/2006
 */
public class FiltroZonaAbastecimento extends Filtro {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroZonaAbastecimento object
	 */
	public FiltroZonaAbastecimento() {
	}

	/**
	 * Constructor for the FiltroZonaAbastecimento object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroZonaAbastecimento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Descricao da Zona de Abastecimento
	 */
	public final static String DESCRICAO = "descricao";

	/**
	 * Descricao Abreviada da Zona de Abastecimento
	 */
	public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";

	/**
	 * Indicador de Uso da Zona de Abastecimento
	 */
	public final static String INDICADOR_USO = "indicadorUso";

}
