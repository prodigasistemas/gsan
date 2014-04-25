package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro da Situação do Imóvel
 * @author Roberta Costa
 * @since 06/03/2006
 */
public class FiltroImovelSituacao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroCliente object
	 */
	public FiltroImovelSituacao() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroImovelSituacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String SITUACAO_ESCGOTO_ID = "ligacaoEsgotoSituacao.id";

	/**
	 * Description of the Field
	 */
	public final static String SITUACAO_AGUA_ID = "ligacaoAguaSituacao.id";

	/**
	 * Description of the Field
	 */
	public final static String SITUACAO_TIPO = "imovelSituacaoTipo.id";
}
