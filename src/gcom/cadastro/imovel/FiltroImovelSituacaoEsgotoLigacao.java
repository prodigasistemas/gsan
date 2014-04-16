package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro da Situação da Ligação de Esgoto do Imovel
 * @author Rômulo Aurelio
 * @since 24/03/2006
 */

public class FiltroImovelSituacaoEsgotoLigacao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroImovelSituacaoTipo object
	 */
	public FiltroImovelSituacaoEsgotoLigacao() {
	}

	/**
	 * Constructor for the FiltroImovelSituacaoTipo object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroImovelSituacaoEsgotoLigacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

}
