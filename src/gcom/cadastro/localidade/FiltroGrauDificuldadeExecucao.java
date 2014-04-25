package gcom.cadastro.localidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroGrauDificuldadeExecucao
 * 
 * @author Hugo Leonardo
 * @date 27/04/2010
 */
public class FiltroGrauDificuldadeExecucao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroGrauDificuldadeExecucao
	 */
	public FiltroGrauDificuldadeExecucao() {
	}

	/**
	 * Constructor for the FiltroGrauDificuldadeExecucao
	 * 
	 * @param campoOrderBy
	 */
	public FiltroGrauDificuldadeExecucao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
    public final static String INDICADOR_GRAU_DIFICULDADE_EXECUCAO = "indicativoGrauDificuldadeExecucao";
	
	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricao";

	/**
	 * Description of the Field
	 */
	public final static String ULTIMAALTERACAO = "ultimaAlteracao";

}
