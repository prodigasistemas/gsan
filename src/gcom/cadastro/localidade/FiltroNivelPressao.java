package gcom.cadastro.localidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroNivelPressao
 * 
 * @author Hugo Leonardo
 * @date 27/04/2010
 */
public class FiltroNivelPressao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroNivelPressao
	 */
	public FiltroNivelPressao() {
	}

	/**
	 * Constructor for the FiltroNivelPressao
	 * 
	 * @param campoOrderBy
	 */
	public FiltroNivelPressao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	/**
	 * Description of the Field
	 */
    public final static String INDICADOR_NIVEL_PRESSAO = "indicativoNivelPressao";
	
	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricao";

	/**
	 * Description of the Field
	 */
	public final static String ULTIMAALTERACAO = "ultimaAlteracao";

}
