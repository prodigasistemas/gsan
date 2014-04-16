package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro para pesquisar o Solicitante Fone
 * 
 * @author Rafael Pinto
 * @since 09/08/2006
 *
 */
public class FiltroSolicitanteFone extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroCliente object
	 */
	public FiltroSolicitanteFone() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroSolicitanteFone(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	

	/**
	 * Description of the Field
	 */
	public final static String REGISTRO_ATENDIMENTO_SOLICITANTE_ID = "registroAtendimentoSolicitante.id";
	
	
}
