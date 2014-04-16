package gcom.cadastro.localidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroCondicaoAbastecimentoAgua
 * 
 * @author Hugo Leonardo
 * @date 27/04/2010
 */
public class FiltroCondicaoAbastecimentoAgua extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroCondicaoAbastecimentoAgua
	 */
	public FiltroCondicaoAbastecimentoAgua() {
	}

	/**
	 * Constructor for the FiltroCondicaoAbastecimentoAgua
	 * 
	 * @param campoOrderBy
	 */
	public FiltroCondicaoAbastecimentoAgua(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
    public final static String NIVEL_PRESSAO = "nivelPressao";
	
	/**
	 * Description of the Field
	 */
    public final static String NIVEL_PRESSAO_ID = "nivelPressao.id";
	
	/**
	 * Description of the Field
	 */
	public final static String GRAU_INTERMITENCIA = "grauIntermitencia";
    
	/**
	 * Description of the Field
	 */
	public final static String GRAU_INTERMITENCIA_ID = "grauIntermitencia.id";

	/**
	 * Description of the Field
	 */
	public final static String ULTIMAALTERACAO = "ultimaAlteracao";

}
