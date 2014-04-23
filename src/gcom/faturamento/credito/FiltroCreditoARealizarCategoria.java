package gcom.faturamento.credito;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de um credito a realizar categoria
 * @author Vivianne Sousa
 * @since 12/06/2007
 *
 */
public class FiltroCreditoARealizarCategoria extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroDebitoACobrar object
	 */
	public FiltroCreditoARealizarCategoria() {
	}

	/**
	 * Constructor for the FiltroDebitoACobrar object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroCreditoARealizarCategoria(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String ID_CREDITO_A_REALIZAR = "creditoARealizar.id";
	
	/**
	 * Description of the Field
	 */
	public final static String ID_CATEGORIA = "categoria.id";
}
