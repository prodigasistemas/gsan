package gcom.seguranca.acesso;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 26/04/2006
 */
public class FiltroModulo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroModulo object
	 */
	public FiltroModulo() {
	}

	/**
	 * Constructor for the FiltroModulo object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroModulo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	/**
	 * Descricao do modulo
	 */
	public final static String DESCRICAO_MODULO = "descricaoModulo";
	
	public final static String NUMERO_ORDEM_MENU = "numeroOrdemMenu";
}
