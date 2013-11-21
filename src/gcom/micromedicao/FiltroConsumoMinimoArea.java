/**
 * 
 */
package gcom.micromedicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurelio
 * @date 14/05/2008
 */
public class FiltroConsumoMinimoArea extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroConsumoMinimoArea object
	 */
	public FiltroConsumoMinimoArea() {
	}

	/**
	 * Constructor for the FiltroConsumoMinimoArea object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroConsumoMinimoArea(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	public final static String ANO_MES_REFERENCIA = "anoMesReferencia";

	public final static String NUMERO_AREA_FINAL = "numeroAreaFinal";

	public final static String NUMERO_CONSUMO = "numeroConsumo";
	
	public final static String INDICADOR_USO = "indicadorUso";
	
	public final static String CATEGORIA = "categoria";		public final static String CATEGORIA_ID = "categoria.id";
	public final static String SUBCATEGORIA = "subCategoria";		public final static String SUBCATEGORIA_ID = "subCategoria.id";
}
