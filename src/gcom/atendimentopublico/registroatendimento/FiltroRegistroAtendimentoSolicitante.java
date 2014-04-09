package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro para pesquisar o Registro de Atendimento
 * @author Rafael Santos
 * @since 09/01/2006
 * 
 */
public class FiltroRegistroAtendimentoSolicitante extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroCliente object
	 */
	public FiltroRegistroAtendimentoSolicitante() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroRegistroAtendimentoSolicitante(String campoOrderBy) {
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
	public final static String CLIENTE_ID = "cliente.id";	
	
	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_SOLICITANTE_PRINCIPAL = "indicadorSolicitantePrincipal";
}
