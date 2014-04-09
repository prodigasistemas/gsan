package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtar da OsProgramNaoEncerMotivo
 * @author Rafael Santos
 * @since 09/1/2006
 *
 */
public class FiltroOsProgramNaoEncerMotivo extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor for the FiltroOrdemServico object
	 */
	public FiltroOsProgramNaoEncerMotivo() {
	}

	/**
	 * 
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroOsProgramNaoEncerMotivo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	
	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricao";
	
	public final static String INDICADOR_USO = "indicadorUso";
	
}
