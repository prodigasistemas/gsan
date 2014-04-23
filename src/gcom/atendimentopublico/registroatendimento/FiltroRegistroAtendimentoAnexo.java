package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro para pesquisar os anexos de um registro de atendimento
 * @author Raphael Rossiter
 * @since 04/08/2009
 * 
 */
public class FiltroRegistroAtendimentoAnexo extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroRegistroAtendimentoAnexo object
	 */
	public FiltroRegistroAtendimentoAnexo() {
	}

	/**
	 * Constructor for the FiltroRegistroAtendimentoAnexo object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroRegistroAtendimentoAnexo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String REGISTRO_ATENDIMENTO_ID = "registroAtendimento.id";
}
