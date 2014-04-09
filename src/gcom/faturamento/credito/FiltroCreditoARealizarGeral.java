package gcom.faturamento.credito;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de um crédito a realizar
 * @author Pedro Alexandre
 * @created 09 de Janeiro de 2006
 */
public class FiltroCreditoARealizarGeral extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroCreditoARealizar object
	 */
	public FiltroCreditoARealizarGeral() {
	}

	/**
	 * Constructor for the FiltroCreditoARealizar object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroCreditoARealizarGeral(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
}	
