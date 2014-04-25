package gcom.operacional.abastecimento;

import gcom.util.filtro.Filtro;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 24/08/2006
 */
public class FiltroManutencaoProgramacao extends Filtro {
	private static final long serialVersionUID = 1L;
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String ANOMESREFERENCIA = "anoMesReferencia";

	/**
	 * Description of the Field
	 */
	public final static String DATA_INICIO = "dataInicio";

	/**
	 * Description of the Field
	 */
	public final static String DATA_FIM = "dataFim";

	/**
	 * Description of the Field
	 */
	public final static String SITUACAO = "situacao";

	/**
	 * Description of the Field
	 */

	public final static String DESCRICAO = "descricao";

	/**
	 * Description of the Field
	 */
	public final static String HORA_INICIO = "horaInicio";

	/**
	 * Description of the Field
	 */

	public final static String HORA_FIM = "horaFim";

	/**
	 * Description of the Field
	 */

	/**
	 * Description of the Field
	 */
	public final static String SISTEMAS_ABASTECIMENTO = "sistemaAbastecimento.id";

	public final static String SETOR_ABASTECIMENTO = "setorAbastecimento.id";

	public final static String DISTRITO_OPERACIONAL = "distritoOperacional.id";

	public final static String ZONA_ABASTECIMENTO = "zonaAbastecimento.id";

	public final static String BAIRRO_AREA = "bairroArea.id";

	public final static String BAIRRO = "bairro.id";

	public final static String MUNICIPIO = "municipio.id";

	/**
	 * 
	 * 
	 * Construtor de FiltroManutencaoProgramacao
	 * 
	 */

	public FiltroManutencaoProgramacao() {

	}

	/**
	 * Construtor da classe FiltroManutencaoProgramacao
	 * 
	 * @param campoOrderBy
	 *            Descrição do parâmetro
	 */
	public FiltroManutencaoProgramacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

}
