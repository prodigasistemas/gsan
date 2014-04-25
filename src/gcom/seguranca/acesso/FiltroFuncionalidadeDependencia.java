package gcom.seguranca.acesso;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * Filtro utilizado na pesquisa de FuncionalidadeDependencia
 * 
 * @author Rômulo Aurélio
 * @date 29/04/2006
 */
public class FiltroFuncionalidadeDependencia extends Filtro implements
		Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroRotaAcaoCriterio object
	 */
	public FiltroFuncionalidadeDependencia() {
	}

	/**
	 * Constructor for the FiltroRotaAcaoCriterio object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroFuncionalidadeDependencia(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String COMP_ID = "comp_id";

	/**
	 * Description of the Field
	 */
	public final static String FUNCIONALIDADE_ID = "funcionalidade.id";

	/**
	 * Description of the Field
	 */
	public final static String FUNCIONALIDADE_DEPENDENCIA = "funcionalidadeDependencia.id";
}
