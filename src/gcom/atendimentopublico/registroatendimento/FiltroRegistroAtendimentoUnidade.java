package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro para pesquisar o Registro de Atendimento Unidade
 * @author Rafael Pinto
 * @since 10/08/2006
 *
 */
public class FiltroRegistroAtendimentoUnidade extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroCliente object
	 */
	public FiltroRegistroAtendimentoUnidade() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroRegistroAtendimentoUnidade(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	/**
	 * Description of the Field
	 */
	public final static String REGISTRO_ATENDIMENTO_ID = "registroAtendimento.id";

	/**
	 * Description of the Field
	 */
	public final static String UNIDADE_ORGANIZACIONAL_ID = "unidadeOrganizacional.id";
	
	/**
	 * Description of the Field
	 */
	public final static String ATENDIMENTO_RELACAO_TIPO = "atendimentoRelacaoTipo.id";
	

}
