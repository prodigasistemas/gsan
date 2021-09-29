package gcom.faturamento.credito;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de um crédito a realizar
 * @author Pedro Alexandre
 * @created 09 de Janeiro de 2006
 */
public class FiltroCreditoARealizar extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroCreditoARealizar object
	 */
	public FiltroCreditoARealizar() {
	}

	/**
	 * Constructor for the FiltroCreditoARealizar object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroCreditoARealizar(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	/**
	 * Description of the Field
	 */
	public final static String IMOVEL_ID = "imovel.id";

	/**
	 * Description of the Field
	 */
	public final static String ANO_MES_REFERENCIA_CREDITO = "anoMesReferenciaCredito";
	
	
	/**
	 * Description of the Field
	 */
	public final static String ANO_MES_REFERENCIA_CONTABIL = "anoMesReferenciaContabil";
	
	/**
	 * Description of the Field
	 */
	public final static String ANO_MES_COBRANCA_CREDITO = "anoMesCobrancaCredito";	
	
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_CREDITO_SITUACAO_ATUAL = "debitoCreditoSituacaoAtual.id";
	
	/**
	 * Description of the Field
	 */
	public final static String PARCELAMENTO_ID = "parcelamento.id";
	
	/**
	 * Description of the Field
	 */
	public final static String CREDITO_TIPO = "creditoTipo";
	
	/**
	 * Description of the Field
	 */
	public final static String REGISTRO_ATENDIMENTO = "registroAtendimento";
	
	/**
	 * Description of the Field
	 */
	public final static String IMOVEL = "imovel";
	
	/**
	 * Description of the Field
	 */
	public final static String ORDEM_SERVICO = "ordemServico";
	
	/**
	 * Description of the Field
	 */
	public final static String QUADRA = "quadra";
	
	/**
	 * Description of the Field
	 */
	public final static String LOCALIDADE = "localidade";
	
	/**
	 * Description of the Field
	 */
	public final static String LANCAMENTO_ITEM_CONTABIL = "lancamentoItemContabil";
	
	/**
	 * Description of the Field
	 */
	public final static String CREDITO_A_REALIZAR_CATEGORIA = "creditoARealizarCategoria";
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_CREDITO_SITUACAO_ATUAL_ = "debitoCreditoSituacaoAtual";
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_CREDITO_SITUACAO_ANTERIOR_ = "debitoCreditoSituacaoAnterior";
	
	/**
	 * Description of the Field
	 */
	public final static String CREDITO_ORIGEM = "creditoOrigem";
	
	/**
	 * Description of the Field
	 */
	public final static String ID_CREDITO_ORIGEM = "creditoOrigem.id";
	
	/**
	 * Description of the Field
	 */
	public final static String PARCELAMENTO = "parcelamento";
	
	/**
	 * Description of the Field
	 */
	public final static String DOCUMENTO_TIPO = "documentoTipo";
	
	/**
	 * Description of the Field
	 */
	public final static String USUARIO = "usuario";
	
	/**
	 * Description of the Field
	 */
	public final static String ID_REGISTRO_ATENDIMENTO = "registroAtendimento.id";
	
	/**
	 * Description of the Field
	 */
	public final static String ID_CREDITO_TIPO = "creditoTipo.id";
	
	/**
	 * Description of the Field
	 */
	public final static String VALOR_RESIDUAL_MES_ANTERIOR = "valorResidualMesAnterior";
	
	
}	
