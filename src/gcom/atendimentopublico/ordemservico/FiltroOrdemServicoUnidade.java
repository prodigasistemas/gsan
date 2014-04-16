package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro para pesquisar a Ordem de Serviço da Unidade
 * 
 * @author Leonardo Regis
 * @date 16/08/2006
 */
public class FiltroOrdemServicoUnidade extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroOrdemServicoUnidade object
	 */
	public FiltroOrdemServicoUnidade() {
	}

	/**
	 * Constructor for the FiltroOrdemServicoUnidade object
	 * 
	 * @param campoOrderBy
	 */
	public FiltroOrdemServicoUnidade(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * ID
	 */
	public final static String ID = "id";
	
	/**
	 * ID da OS
	 */
	public final static String ORDEM_SERVICO_ID = "ordemServico.id";

	/**
	 * ID da Unidade Organizacional
	 */
	public final static String UNIDADE_ORGANIZACIONAL_ID = "unidadeOrganizacional.id";
	
	/**
	 * ID do Atendimento Relação Tipo
	 */
	public final static String ATENDIMENTO_RELACAO_TIPO_ID = "atendimentoRelacaoTipo.id";





	
	/**
	 * Ultima Alteracao
	 */
	public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";
	
}
