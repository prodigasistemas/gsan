package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Pedro Alexandre
 * @created 08 de Fevereiro de 2006
 */

public class FiltroCobrancaAcao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroCobrancaAcao object
	 */
	public FiltroCobrancaAcao() {
	}

	/**
	 * Constructor for the FiltroCobrancaAcao object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroCobrancaAcao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String COBRANCA_ACAO_PRECEDENTE_ID = "cobrancaAcaoPredecessora.id";
	
	public final static String COBRANCA_ACAO_PREDECESSORA = "cobrancaAcaoPredecessora";
	
	public final static String SERVICO_TIPO = "servicoTipo";

	/**
	 * Description of the Field
	 */
	public final static String DOCUMENTO_TIPO = "documentoTipo";

	public final static String COBRANCAO_CRITERIO = "cobrancaCriterio";

	/**
	 * Description of the Field
	 */
	public final static String SERVICO_TIPO_PREDECESSORA = "cobrancaAcaoPredecessora.servicoTipo";

	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricaoCobrancaAcao";

	public final static String ORDEM_REALIZACAO = "ordemRealizacao";

	public final static String INDICADOR_USO = "indicadorUso";

	public final static String INDICADOR_CRONOGRAMA = "indicadorCronograma";

	public final static String INDICADOR_OBRIGATORIEDADE = "indicadorObrigatoriedade";

	public final static String INDICADOR_REPETICAO = "indicadorRepeticao";

	public final static String INDICADOR_SUSPENSAO_ABASTECIMENTO = "indicadorSuspensaoAbastecimento";

	public final static String NUMERO_DIAS_VALIDADE = "numeroDiasValidade";
	
	public final static String NUMERO_DIAS_MINIMO_ACAO_PRECEDENTE = "numeroDiasMinimoAcaoPrecedente";
	
	public final static String INDICADOR_COBRANCA_DEB_A_COBRAR = "indicadorCobrancaDebACobrar";
	
	public final static String INDICADOR_CREDITO_A_REALIZAR = "indicadorCreditosARealizar";

	public final static String INDICADOR_NOTAS_PROMISSORIA = "indicadorNotasPromissoria";
	
	public final static String INDICADOR_ORDENAR_MAIOR_VALOR = "indicadorOrdenarMaiorValor";
	
	public final static String INDICADOR_VALIDAR_ITEM = "indicadorValidarItem";
	
	public final static String INDICADOR_GERACAO_TAXA = "indicadorGeracaoTaxa";
	
	public final static String INDICADOR_METAS_CRONOGRAMA = "indicadorMetasCronograma";
	
	public final static String INDICADOR_ORDENAMENTO_CRONOGRAMA = "indicadorOrdenamentoCronograma";
	
	public final static String INDICADOR_ORDENAMENTO_EVENTUAL = "indicadorOrdenamentoEventual";
	
	public final static String INDICADOR_DEBITO_INTERFERE_ACAO = "indicadorDebitoInterfereAcao";
	
	public final static String NUMERO_DIAS_REMUNERACAO_TERCEIRO = "numeroDiasRemuneracaoTerceiro";
	
	
	public final static String INDICADOR_ACRESCIMO_IMPONTUALIDADE = "indicadorAcrescimoImpontualidade";
	
	public final static String LIGACAO_ESGOTO_SITUACAO_ID = "ligacaoEsgotoSituacao.id";
	
	public final static String LIGACAO_ESGOTO_SITUACAO = "ligacaoEsgotoSituacao";
	
	public final static String LIGACAO_AGUA_SITUACAO_ID = "ligacaoAguaSituacao.id";
	
	public final static String LIGACAO_AGUA_SITUACAO = "ligacaoAguaSituacao";
	
	public final static String DOCUMENTO_TIPO_ID = "documentoTipo.id";

	public final static String COBRANCAO_CRITERIO_ID = "cobrancaCriterio.id";
	
	public final static String INDICADOR_BOLETIM = "indicadorBoletim";
	
	public final static String INDICADOR_DEBITO = "indicadorDebito";
	
	public final static String SERVICO_TIPO_ID_ACAO_COBRANCA = "servicoTipo.id";
	

}
