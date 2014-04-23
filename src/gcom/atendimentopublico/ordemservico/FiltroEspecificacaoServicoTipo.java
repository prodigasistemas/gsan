package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Descrição da classe 
 *
 * @author Rômulo Aurélio
 * @date 01/12/2006
 */
public class FiltroEspecificacaoServicoTipo extends Filtro implements
		Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroRotaAcaoCriterio object
	 */
	public FiltroEspecificacaoServicoTipo() {
	}

	/**
	 * Constructor for the FiltroRotaAcaoCriterio object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroEspecificacaoServicoTipo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String COMP_ID = "comp_id";

	/**
	 * Description of the Field
	 */
	public final static String SOLICITACAO_TIPO_ESPECIFICACAO_ID = "solicitacaoTipoEspecificacao.id";

	/**
	 * Description of the Field
	 */
	public final static String SERVICO_TIPO = "servicoTipo.id";
}

