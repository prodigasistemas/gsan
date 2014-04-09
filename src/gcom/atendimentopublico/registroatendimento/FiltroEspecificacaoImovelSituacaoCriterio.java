package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

/**
 * Filtro da Especificação da Situação do Imóvel Critério
 * 
 * @author Rafael Pinto
 * @since 09/11/2006
 */
public class FiltroEspecificacaoImovelSituacaoCriterio extends Filtro {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroEspecificacaoImovelSituacaoCriterio object
	 */
	public FiltroEspecificacaoImovelSituacaoCriterio() {
	}

	/**
	 * Constructor for the FiltroEspecificacaoImovelSituacaoCriterio object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroEspecificacaoImovelSituacaoCriterio(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	public final static String ESPECIFICAO_SITUACAO_IMOVEL_ID = "especificacaoImovelSituacao.id";

}
