package gcom.faturamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroConsumoMinimoParametro extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroConsumoMinimoParametro object
	 */
	public FiltroConsumoMinimoParametro() {

	}

	/**
	 * Constructor for the FiltroConsumoMinimoParametro object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */

	public FiltroConsumoMinimoParametro(String campoOrderBy) {

		this.campoOrderBy = campoOrderBy;

	}

	public final static String ID = "id";

	public final static String ANO_MES_REFERENCIA = "anoMesReferencia";

	public final static String NUMERO_PARAMETRO_FINAL = "numeroParametroFinal";

	public final static String NUMERO_CONSUMO = "numeroConsumo";

	public final static String INDICADOR_USO = "indicadorUso";

	public final static String CATEGORIA = "categoria";
	
	public final static String CATEGORIA_ID = "categoria.id";

	public final static String SUBCATEGORIA = "subCategoria";
	
	public final static String SUBCATEGORIA_ID = "subCategoria.id";

}
