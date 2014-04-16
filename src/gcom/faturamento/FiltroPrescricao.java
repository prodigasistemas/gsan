package gcom.faturamento;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Hugo Leonardo
 */
public class FiltroPrescricao extends Filtro {
	private static final long serialVersionUID = 1L;

	public FiltroPrescricao() {
    }

	/**
	 * Constructor for the FiltroServicoCobrancaValor
	 * 
	 * @param campoOrderBy
	 */
	public FiltroPrescricao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String ANO_MES_FATURAMENTO = "anoMesFaturamento";

	/**
	 * Description of the Field
	 */
	public final static String ULTIMAALTERACAO = "ultimaAlteracao";

	/**
	 * Description of the Field
	 */
	public final static String ESFERA_PODER_1_ID = "esferaPoder1.id";
	
	/**
	 * Description of the Field
	 */
	public final static String ESFERA_PODER_2_ID = "esferaPoder2.id";
	
	/**
	 * Description of the Field
	 */
	public final static String ESFERA_PODER_1 = "esferaPoder1";
	
	/**
	 * Description of the Field
	 */
	public final static String ESFERA_PODER_2 = "esferaPoder2";

}
