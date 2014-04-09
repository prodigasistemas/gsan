package gcom.cadastro.localidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroGrauIntermitencia
 * 
 * @author Hugo Leonardo
 * @date 27/04/2010
 */
public class FiltroGrauIntermitencia extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroGrauIntermitencia
	 */
	public FiltroGrauIntermitencia() {
	}

	/**
	 * Constructor for the FiltroGrauIntermitencia
	 * 
	 * @param campoOrderBy
	 */
	public FiltroGrauIntermitencia(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
    public final static String INDICADOR_GRAU_INTERMITENCIA = "indicativoGrauIntermitencia";
	
	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricao";

	/**
	 * Description of the Field
	 */
	public final static String ULTIMAALTERACAO = "ultimaAlteracao";

}
