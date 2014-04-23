package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtar da Ordem de Serviço
 * 
 * @author Rafael Santos
 * @since 09/1/2006
 * 
 */
public class FiltroOrdemServicoProgramacao extends Filtro implements
		Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroOrdemServico object
	 */
	public FiltroOrdemServicoProgramacao() {
	}

	/**
	 * 
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroOrdemServicoProgramacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_ATIVO = "indicadorAtivo";
	
	public final static String ID_ARQUIVO = "arquivo.id";

	/**
	 * Description of the Field
	 */
	public final static String PROGRAMACAO_ROTEIRO = "programacaoRoteiro";
	
	public final static String ID_EQUIPE = "equipe.id";
	
	public final static String DATA_PROGRAMACAO = "programacaoRoteiro.dataRoteiro";

}
