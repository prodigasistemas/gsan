package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro da Situação do Imóvel Tipo
 * @author Rômulo Aurelio
 * @since 24/03/2006
 */
public class FiltroImovelSituacaoTipo extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroImovelSituacaoTipo object
	 */
	public FiltroImovelSituacaoTipo() {
	}

	/**
	 * Constructor for the FiltroImovelSituacaoTipo object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroImovelSituacaoTipo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	public final static String DESCRICAO_IMOVEL_SITUACAO_TIPO = "descricaoImovelSituacaoTipo";

}
