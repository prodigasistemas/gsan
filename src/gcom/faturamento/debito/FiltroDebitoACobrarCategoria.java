package gcom.faturamento.debito;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de um debito a cobrar categoria
 * @author Rafael Santos
 * @since 18/03/2006
 *
 */
public class FiltroDebitoACobrarCategoria extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroDebitoACobrar object
	 */
	public FiltroDebitoACobrarCategoria() {
	}

	/**
	 * Constructor for the FiltroDebitoACobrar object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroDebitoACobrarCategoria(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String ID_DEBITO_A_COBRAR = "debitoACobrar.id";
	
	/**
	 * Description of the Field
	 */
	public final static String ID_CATEGORIA = "categoria.id";
}
