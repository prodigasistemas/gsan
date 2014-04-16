package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * [UC0459] Informar Dados da Agencia Reguladora
 * 
 * @author Kássia Albuquerque
 * @date 10/04/2007
 */

public class FiltroRaDadosAgenciaReguladora extends Filtro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FiltroRaDadosAgenciaReguladora(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroRaDadosAgenciaReguladora() {
	}

	public final static String ID = "id";

	public final static String AGENCIA_REGULADORA = "agenciaReguladora";
	
	public final static String DATA_PREVISAO_ORIGINAL = "dataPrevisaoOriginal";

	public final static String DATA_PREVISAO_ATUAL = "dataPrevisaoAtual";
	
	public final static String DESCRICAO_RECLAMACAO = "descricaoReclamacao";
	
	public final static String REGISTRO_ATENDIMENTO_ID = "registroAtendimento.id";
	
	public final static String MOTIVO_RECLAMACAO_ID = "agenciaReguladoraMotReclamacao.id";
	
	public final static String MOTIVO_ENCERRAMENTO_ID = "atendimentoMotivoEncerramento.id";
	
	public final static String INDICADOR_SITUACAO_AGENCIA = "codigoSituacao";

	public final static String INDICADOR_SITUACAO_RA = "codigoSituacaoArpe";
	
	public final static String MOTIVO_RETORNO_ID = "agenciaReguladoraMotRetorno.id";
	
	public final static String DATA_RECLAMACAO = "dataReclamacao";

	public final static String DATA_RETORNO = "dataRetorno";

}
