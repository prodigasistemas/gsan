package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtar da Ordem de Serviço
 * @author Rafael Santos
 * @since 09/1/2006
 *
 */
public class FiltroFiscalizacaoSituacao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor for the FiltroOrdemServico object
	 */
	public FiltroFiscalizacaoSituacao() {
	}

	/**
	 * 
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroFiscalizacaoSituacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricaoFiscalizacaoSituacao";
	

	
	public final static String INDICADOR_ATUALIZACAO_AUTOS_INFRACAO = "indicadorAtualizacaoAutosInfracao";
	
	
	public final static String ATENDIMENTO_MOTIVO_ENCERRAMENTO = "atendimentoMotivoEncerramento";
	
}

