package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtar da Ordem de Serviço
 * @author Rafael Santos
 * @since 09/1/2006
 *
 */
public class FiltroOrdemServico extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor for the FiltroOrdemServico object
	 */
	public FiltroOrdemServico() {
	}

	/**
	 * 
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroOrdemServico(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	
		/**
	 * Description of the Field
	 */
	//este campo ainda não foi defenido (só na Iteração 7)
	public final static String DATA_ENCERRAMENTO = "dataEncerramento";
	
	public final static String REGISTRO_ATENDIMENTO_ID = "registroAtendimento.id";
	
	public final static String ID_IMOVEL = "imovel.id";
    
    public final static String IMOVEL = "imovel";
	
	public final static String ATENDIMENTO_MOTIVO_ENCERRAMENTO = "atendimentoMotivoEncerramento";
	
	public final static String ATENDIMENTO_MOTIVO_ENCERRAMENTO_IC_EXECUCAO = "atendimentoMotivoEncerramento.indicadorExecucao";
	
	public final static String ATENDIMENTO_MOTIVO_ENCERRAMENTO_ID = "atendimentoMotivoEncerramento.id";
	
	public final static String REGISTRO_ATENDIMENTO = "registroAtendimento";
	
	public final static String DEBITO_TIPO = "servicoTipo.debitoTipo";
	
	public final static String SOLICITACAO_TIPO_ESPECIFICACAO = "registroAtendimento.solicitacaoTipoEspecificacao";
	
	public final static String CREDITO_TIPO = "servicoTipo.creditoTipo";	
	
	public final static String SERVICO_TIPO = "servicoTipo";
	
	public final static String DESCRICAO_TIPO = "servicoTipo.descricao";
	
	public final static String SERVICO_TIPO_PRIORIDADE_ATUAL = "servicoTipoPrioridadeAtual";
	
	public final static String OS_REFERIDA_RETORNO_TIPO = "osReferidaRetornoTipo";
	
	public final static String COBRANCA_DOCUMENTO = "cobrancaDocumento";

	public final static String COBRANCA_DOCUMENTO_COBRANCA_ACAO_ATIV_CRONOG_ID = "cobrancaDocumento.cobrancaAcaoAtividadeCronograma.id";
	
	public final static String COBRANCA_GRUPO_ID = "cobrancaDocumento.cobrancaAcaoAtividadeCronograma.cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.cobrancaGrupo.id";

    public final static String COBRANCA_ACAO_ID = "cobrancaDocumento.cobrancaAcaoAtividadeCronograma.cobrancaAcaoCronograma.cobrancaAcao.id";
    
    public final static String COBRANCA_ACAO = "cobrancaDocumento.cobrancaAcaoAtividadeCronograma.cobrancaAcaoCronograma.cobrancaAcao";
    
    public final static String COBRANCA_GRUPO = "cobrancaDocumento.cobrancaAcaoAtividadeCronograma.cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.cobrancaGrupo";
    
    public final static String COBRANCA_GRUPO_CRONOGRAMA_MES_MES_ANO = "cobrancaDocumento.cobrancaAcaoAtividadeCronograma.cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.anoMesReferencia";
	
	public final static String UNIDADE_ORGANIZACIONAL_ATUAL = "unidadeAtual";
	
	public final static String SITUACAO = "situacao";
	
	public final static String ID_SERVICO_TIPO = "servicoTipo.id";
	
	public final static String DATA_FISCALIZACAO_SITUACAO = "dataFiscalizacaoSituacao";
	
	public final static String FISCALIZACAO_SITUACAO_ID = "fiscalizacaoSituacao"; 
	
	
}

