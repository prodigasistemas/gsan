package gcom.faturamento.credito;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * @author Vivianne Sousa
 * @created 13/11/2008
 */
public class FiltroCreditoARealizarHistorico extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroCreditoARealizarHistorico object
	 */
	public FiltroCreditoARealizarHistorico() {
	}

	/**
	 * Constructor for the FiltroCreditoARealizarHistorico object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroCreditoARealizarHistorico(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	/**
	 * Description of the Field
	 */
	public final static String IMOVEL_ID = "imovel.id";
	
}	
