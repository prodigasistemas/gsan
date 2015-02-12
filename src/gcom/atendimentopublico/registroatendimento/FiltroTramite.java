package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro para pesquisar o Trâmite
 * 
 * @author Leonardo Regis
 * @since 11/08/2006
 *
 */
public class FiltroTramite  extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroTramite object
	 */
	public FiltroTramite() {
	}

	/**
	 * Constructor for the FiltroTramite object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroTramite(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	public final static String REGISTRO_ATENDIMENTO_ID = "registroAtendimento.id";
}
