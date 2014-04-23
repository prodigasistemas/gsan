package gcom.arrecadacao.pagamento;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de uma guia de pagamento
 * @author Pedro Alexandre
 * @created 09 de Janeiro de 2006
 */
public class FiltroGuiaPagamento extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor for the FiltroGuiaPagamento object
	 */
	public FiltroGuiaPagamento() {
	}

	/**
	 * Constructor for the FiltroGuiaPagamento object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroGuiaPagamento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
		
	
	/**
	 * Description of the Field
	 */
	public final static String ANO_MES_REFERENCIA_CONTABIL = "anoMesReferenciaContabil";
	
	/**
	 * Description of the Field
	 */
	public final static String IMOVEL_ID = "imovel.id";
	
	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_ID = "cliente.id";
	
	public final static String DEBITO_CREDITO_SITUACAO_ATUAL = "debitoCreditoSituacaoAtual";
	
	public final static String DEBITO_CREDITO_SITUACAO_ATUAL_ID = "debitoCreditoSituacaoAtual.id";
	
	public final static String DEBITO_TIPO_ID = "debitoTipo.id";
	
	public final static String DEBITO_TIPO = "debitoTipo";
	
	public final static String EMISSAO_GUIA_PAGAMENTO = "dataEmissao";
	
	public final static String DATA_VENCIMENTO = "dataVencimento";
	
	public final static String PARCELAMENTO_ID = "parcelamento.id";
	
	public final static String REGISTRO_ATENDIMENTO_ID = "registroAtendimento.id";
	
	public final static String REGISTRO_ATENDIMENTO = "registroAtendimento";
    
    public final static String NUMERO_PRESTACAO_DEBITO = "numeroPrestacaoDebito";
	
    public final static String ORDEM_SERVICO = "ordemServico";
    
    public final static String FINANCIAMENTO_TIPO_ID = "financiamentoTipo.id";
    
    public final static String LOCALIDADE = "localidade";
    
    public final static String CLIENTE = "cliente";
    
}
