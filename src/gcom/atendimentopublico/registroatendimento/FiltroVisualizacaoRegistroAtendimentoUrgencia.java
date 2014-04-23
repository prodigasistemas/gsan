package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro para pesquisar a VisualizacaoRegistroAtendimentoUrgencia
 * 
 * @author Daniel Alves
 * @since 03/06/2010
 *
 */
public class FiltroVisualizacaoRegistroAtendimentoUrgencia  extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroVisualizacaoRegistroAtendimentoUrgencia object
	 */
	public FiltroVisualizacaoRegistroAtendimentoUrgencia() {
	}

	/**
	 * Constructor for the FiltroVisualizacaoRegistroAtendimentoUrgencia object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroVisualizacaoRegistroAtendimentoUrgencia(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
}
